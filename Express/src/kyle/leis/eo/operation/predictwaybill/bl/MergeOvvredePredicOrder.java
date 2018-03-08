package kyle.leis.eo.operation.predictwaybill.bl;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillColumns;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybill.dax.Corewaybillcode;
import kyle.leis.eo.operation.housewaybill.bl.Input;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderDemand;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.eo.operation.predictwaybill.dax.MergeOvvredePredicOrderDemand;
import kyle.leis.eo.operation.predictwaybill.tp.MergePredictOrderbillTrans;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.dax.DictionaryDemand;
import kyle.leis.hi.TchnChannel;

public class MergeOvvredePredicOrder {
	/**
	 * �ϲ�
	 * @param strCocode
	 * @param objPOCEX
	 * @param strOperId
	 * @return
	 * @throws Exception
	 */
	public PromptUtilityCollection merge(String strCocode,
			PredictwaybillColumns objPOCEX,
			String strOperId) throws Exception {
		WaybillforpredictColumns objWFPC = MergeOvvredePredicOrderDemand.loadExistsRecord(strCocode, objPOCEX);
		if (objWFPC == null ) {
			PromptUtilityCollection objPU = new PromptUtilityCollection();
			objPU.add("E_001", "û����Ҫ�ϲ�������", "merge");
			return objPU;
		}
		String strCwcode = objWFPC.getCwcw_code();
		List<CargoinfoColumns> listCargoInfo = CargoInfoDemand.queryByCwCode(strCwcode);
		
//		List<CargoinfoColumns> listSourceCargoInfo = new ArrayList<CargoinfoColumns>();
		MergeOvvredePredicOrderDemand.merge(objPOCEX, listCargoInfo);

		MergePredictOrderbillTrans objMPOT = new MergePredictOrderbillTrans();
		objMPOT.setParam(objWFPC, objPOCEX, strOperId,listCargoInfo);
		objMPOT.execute();
		
//		����Ԥ���Ķ���
		Predictwaybill pw=new Predictwaybill();
		pw.withdraw(objPOCEX.getPwbpwb_code(), strOperId, false);
		
		
		return null;
	}
	

	/**
	 * ����
	 * @param strCocode
	 * @param objPOCEX
	 * @param strOperId
	 * @throws Exception
	 */
	public PromptUtilityCollection ovvride(String strCocode,
			PredictwaybillColumns objWFPC,
			String strOperId) throws Exception {
		// �����ظ����˵�
//		WaybillforpredictColumns objSCWC = MergePredictOrderDemand.loadExistsRecord(strCocode, objWFPC);
		SimplecorewaybillColumns objSCWC = CorewaybillDemand.loadSimpleCorewaybill(objWFPC.getPwbpwb_orderid(), 
				strCocode, true);	
		if (objSCWC == null) {
			PromptUtilityCollection objPU = new PromptUtilityCollection();
			objPU.add("E_001", "û����Ҫ���ǵ�����", "ovvride");
			return objPU;
		}
		objWFPC.setPwbcw_code(objSCWC.getCwcw_code());
		String strPE = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strPE) && strPE.startsWith("SLY"))  {
			String strEwbcode = PredictOrderDemand.buildEwbcode();
			objWFPC.setPwbpwb_serverewbcode(strEwbcode);
//			objWFPC.setCwcw_ewbcode(strEwbcode);
		} else {
			if(!StringUtility.isNull(objSCWC.getCwcw_code())){   //cwcode�ݴ�״̬û��
				HousewaybillColumns objHwcolumns = HousewaybillDemand.loadByCwcode(objSCWC.getCwcw_code());
				objWFPC.setPwbpwb_serverewbcode(objHwcolumns.getCwcwserverewbcode());
			}
//			HousewaybillColumns objHwcolumns = HousewaybillDemand.loadByCwcode(objSCWC.getPwbcw_code());
//			objWFPC.setPwbpwb_serverewbcode(objHwcolumns.getCwcwserverewbcode());
//			objWFPC.setCwcw_ewbcode(objHwcolumns.getCwcwewbcode());	
//			objWFPC.setCwcw_customerewbcode(objHwcolumns.getCwcwcustomerewbcode());
		}
		// ����
		InputAllQReturn objIAR = save(strCocode, objWFPC, strOperId, false, true);
		if (objIAR == null) {
			PromptUtilityCollection objPU = new PromptUtilityCollection();
			objPU.add("E_001", "�����������ֵΪ��", "ovvride");
			return objPU;
		}
//		����Ԥ���Ķ���
		Predictwaybill pw=new Predictwaybill();
		pw.withdraw(objWFPC.getPwbpwb_code(), strOperId, false);
	
		return objIAR.getPromptUtilityCollection();	
	}
	
	public InputAllQReturn save(String strCocode,
			PredictwaybillColumns objPOCEX,
			String strOperId,
			boolean isNeedCheck,
			boolean isOnlyCheckBase) throws Exception {
		if (isNeedCheck) {
			InputAllQReturn objIAQR = new InputAllQReturn();
			PromptUtilityCollection objPU = new PromptUtilityCollection();
			List listStandardTemplate = DictionaryDemand.queryStandardTemplate();
			if (listStandardTemplate == null || listStandardTemplate.size() < 1) {
				objPU.add("E_001", "��׼ģ�廹û������", "save");
				objIAQR.setPromptUtilityCollection(objPU);
				return objIAQR;
			}		
			
			MergeOvvredePredicOrderDemand objPOC = new MergeOvvredePredicOrderDemand();
			String promptinfo = "";
			String strErrorInfo = objPOC.check(strCocode, objPOCEX, listStandardTemplate, isOnlyCheckBase,promptinfo);
			if (!StringUtility.isNull(strErrorInfo)) {
				if (!StringUtility.isNull(promptinfo))
					strErrorInfo = strErrorInfo + ":" + promptinfo;
				objPU.add("E_001", strErrorInfo, "save");
				objIAQR.setPromptUtilityCollection(objPU);
				return objIAQR;
			}
		}
		Input input = new Input();
		
		ForinputallColumns objFIAColumns = MergeOvvredePredicOrderDemand.buildForinputAll(strCocode,
				objPOCEX);
		List listCargo = MergeOvvredePredicOrderDemand.buildCargoinfo(objPOCEX, objFIAColumns);
		List listWaybillPieces = MergeOvvredePredicOrderDemand.buildPiecesinfo(objPOCEX);
		PredictOrderDemand.buildChargeweightAndChannel(objFIAColumns, listWaybillPieces);
		// ���÷����̵���	
		// ����ͨ��service��ȡ������ֵΪ������ʾ��������ͨ��service�ķ�ʽ����ȡ��������system
		if (setWaybillcodeByService(objFIAColumns, listWaybillPieces, listCargo) < 0) {
			Corewaybillcode objCorewaybillcode = new Corewaybillcode();
			PromptUtilityCollection objPU = objCorewaybillcode.setNewWaybillcodeBySystem(objFIAColumns,
					listWaybillPieces,
					objFIAColumns.getChncode_Cwspchn(),
					strOperId);
			if (!objPU.canGo(false)) {
				InputAllQReturn objIAQR = new InputAllQReturn();
				objIAQR.setPromptUtilityCollection(objPU);
				return objIAQR;
			}
		}
		// �����ɹ�
		if (!StringUtility.isNull(objFIAColumns.getServerwbckbckcode()) && 
				StringUtility.isNull(objFIAColumns.getHwbookingid())) {
			objFIAColumns.setCwewbcode(objFIAColumns.getCwserverewbcode());
		}
		return input.inputAll(strOperId, objFIAColumns, 
				listCargo, listWaybillPieces, false);		
	}
	private int setWaybillcodeByService(ForinputallColumns objFIAColumns,
			List listWaybillPieces,
			List listCargo) throws Exception {
		if (StringUtility.isNull(objFIAColumns.getChncode_Cwspchn())) 
			return -1;
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
		if (objTchnChannel.getTdiServerstructuregroup() == null ||
				!objTchnChannel.getTdiServerstructuregroup().getSsgCode().equals("AUEMS"))
			return -1;
		if (objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind() == null)
			return -1;
		Corewaybillcode objCorewaybillcode = new Corewaybillcode();
		HousewaybillColumns objHWBColumns = new HousewaybillColumns();
		objCorewaybillcode.setWaybillcodeByService(objFIAColumns, 
				listWaybillPieces, 
				listCargo, 
				objHWBColumns, 
				objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind().getBckCode(), 
				"_E", 
				"", 
				objTchnChannel.getChnMasteraccount());
		return 0;
	}
	
}
