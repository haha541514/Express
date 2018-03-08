package kyle.leis.eo.operation.housewaybill.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.billing.calculate.chargeweight.bl.Chargeweight;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightParameter;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightResult;
import kyle.leis.eo.billing.calculate.feecalculate.blx.AutoFeeCalculateThread;
import kyle.leis.eo.customerservice.track.bl.Track;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillDemand;
import kyle.leis.eo.operation.batchwaybill.tp.SaveBatchwaybillTrans;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.corewaybill.blx.CoreWayBillCheck;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.da.PredictOrderColumns;
import kyle.leis.eo.operation.housewaybill.da.PredictwaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.tp.SaveWaybillTransaction;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.hi.TchnChannel;

public class BatchHousewaybill {
	/**
	 * �������ȫ������
	 * @param objPOColumns
	 * @param strOperId
	 */
	public PromptUtilityCollection signInSignOut(PredictwaybillColumns objPWBColumns,
			List listCargoInfo,
			String strChncode,
			String strBwcodeDeparture,
			String strOperId,
			boolean isAutoSignout,
			boolean isPrealertSign,
			String strEecode) throws Exception {
		PredictOrderColumns objPOColumns = transfer(objPWBColumns, listCargoInfo);
		objPOColumns.setEeCode(strEecode);
		// ���ͻ��˵��Ƿ��ظ�
		CoreWayBillCheck objCoreWayBillCheck = new CoreWayBillCheck();
		PromptUtilityCollection objCheckEwbcode = objCoreWayBillCheck.checkCustomerEwbcode(objPOColumns.getStrCwcustomerewbcode(), 
				"NULL");
		if (!objCheckEwbcode.canGo(true))
			return objCheckEwbcode;
		// ����ΪԤ��
		PredictOrder objPredictOrder = new PredictOrder();
		InputAllQReturn objInputAllQReturn = objPredictOrder.save(objPOColumns, strOperId);
		PromptUtilityCollection objPUCollection = objInputAllQReturn.getPromptUtilityCollection();
		if (objPUCollection != null && !objPUCollection.canGo(true))
			return objPUCollection;
		// �Ƶ����÷�������
		List listHouseWayBill = objInputAllQReturn.getHWBResults();
		if (listHouseWayBill == null || listHouseWayBill.size() < 1) {
			objPUCollection = new PromptUtilityCollection();
			objPUCollection.add("E_001", 
					"����Ԥ������ʧ�ܣ�", 
					"BatchHousewaybill.signInSignOut");
			return objPUCollection;
		}
		String strCwcode = ((ForinputallColumns)listHouseWayBill.get(0)).getCwcode();
		HousewaybillColumns objHWColumns = HousewaybillDemand.loadByCwcode(strCwcode);
		// ���÷����̺ͷ�������
		if (!StringUtility.isNull(strChncode)) {
			objHWColumns.setSchnchncode(strChncode);
			TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
			objHWColumns.setScococode(objTchnChannel.getTcoCorporation().getCoCode());
		}
		// ���ü�����Ϣ
		List listCWPieces = buildPiecesInfo(objHWColumns);		
		// Ԥ���üƷ�������Ϊ����������룬����Ҫ���¼Ʒ�����
		if (isPrealertSign) {
			// ��������ϵ��
			ChargeweightParameter objCWParameter = CorewaybillDemand.transferToCWParameter(objHWColumns, 
					null);
			Chargeweight objChargeweight = new Chargeweight();
			ChargeweightResult objCWResult = objChargeweight.calculate(objCWParameter);
			objHWColumns.setCwcwvolumerate(Integer.parseInt(objCWResult.getVolumeRate()));
			listCWPieces = buildPrealertPiecesInfo(objHWColumns, 
					objPWBColumns.getCwcwchargeweight(),
					Integer.parseInt(objCWResult.getVolumeRate()));
			// ����Ʒ�����
			objCWResult = objChargeweight.getDefaultChargeWeight(objPWBColumns.getCwcwgrossweight(), 
					objPWBColumns.getCwcwchargeweight());
			objHWColumns.setCwcwchargeweight(new BigDecimal(objCWResult.getChargeweight()));
			objHWColumns.setCwscwscode("PR");
			objHWColumns.setSchnchncode("");
			objHWColumns.setScococode("");
			isAutoSignout = false;
		}	else {
			//���ó�������
			objHWColumns.setCwscwscode("SI");
			if (!StringUtility.isNull(objPWBColumns.getCwcwtransferchargeweight())) {
				objHWColumns.setCwcwtransferchargeweight(new BigDecimal(objPWBColumns.getCwcwtransferchargeweight()));
				objHWColumns.setCwcwserverchargeweight(new BigDecimal(objPWBColumns.getCwcwtransferchargeweight()));
			}
		}
		// �ջ�����������
		SaveWaybillTransaction objSWTrans = new SaveWaybillTransaction();
		objSWTrans.setSignInSignOutParam(objHWColumns, 
				strBwcodeDeparture, 
				listCWPieces, 
				null, 
				strOperId,
				isAutoSignout);
		objSWTrans.execute();
		// ��¼�켣
		if (!isPrealertSign) {
			Track objTrack = new Track();
			objTrack.addSingleTrack(strCwcode, 
					objHWColumns.getOdtdtcode(), 
					"AF", 
					strOperId, 
					DateFormatUtility.getStandardSysdate());			
		}
		// ���������켣
		if (isAutoSignout) {
			Track objTrack = new Track();
			objTrack.addSingleTrack(objHWColumns.getHwcwcode(), 
					objHWColumns.getOdtdtcode(), 
					"OC", 
					strOperId, 
					DateFormatUtility.getStandardSysdate());			
		}
		// ���¼Ʒ�
		AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(strCwcode,
				"",
				false);
		objAFCThread.start();
		if (objPUCollection == null)
			objPUCollection = new PromptUtilityCollection(); 
		return objPUCollection;
	}
	
	/**
	 * �Ƶ�������
	 * @param objFIAColumns
	 * @param listCargoInfo
	 * @param isAutoSignout
	 * @param strOperId
	 * @return
	 * @throws Exception
	 */
	public PromptUtilityCollection simpleInputSignout(ForinputallColumns objFIAColumns,
			List listCargoInfo,
			boolean isAutoSignout,
			boolean isArrearAllowSignout,
			String strOperId) throws Exception {
		// �Ƶ�
		Input objInput = new Input();
		if (!StringUtility.isNull(objFIAColumns.getHwconsigneecompany()) && 
				objFIAColumns.getHwconsigneecompany().equals(".")) {
			objFIAColumns.setHwconsigneecompany("-");
		}
		if (!StringUtility.isNull(objFIAColumns.getHwconsigneename()) && 
				objFIAColumns.getHwconsigneename().equals(".")) {
			objFIAColumns.setHwconsigneename("-");
		}		
		InputAllQReturn objInputAllQReturn = objInput.modify(strOperId, 
				objFIAColumns, 
				listCargoInfo,
				false,
				false,
				false);
		PromptUtilityCollection objPUCollection = objInputAllQReturn.getPromptUtilityCollection();
		if (objPUCollection != null && !objPUCollection.canGo(true))
			return objPUCollection;
		if (objPUCollection == null)
			objPUCollection = new PromptUtilityCollection();
		// ���Զ��������˳�����
		if (!isAutoSignout) return objPUCollection;
		if (StringUtility.isNull(objFIAColumns.getChncode_Cwspchn())) {
			objPUCollection.add("E_002", "������������Ϊ��", "BatchHousewaybill.simpleInputSignout");
			return objPUCollection;
		}
		objFIAColumns.setCocode_Cwsp(TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn()).getTcoCorporation().getCoCode());
		// ���ó�������
		SimplebatchwaybillColumns objSBWBColumns = BatchWayBillDemand.getLatestUnCompleteBatchwaybill(objFIAColumns.getChncode_Cwspchn(), 
				objFIAColumns.getCocode_Cwsp(),
				objFIAColumns.getEecode(),
				DateFormatUtility.getStandardSysdate(), 
				"D");
		// �½���������
		String strBwcodeDeparture = "";
		if (objSBWBColumns == null) {
			// ����
			SaveBatchwaybillTrans objSBWBTrans = new SaveBatchwaybillTrans();
			objSBWBTrans.setDepartureParam(objFIAColumns.getCocode_Cwsp(), 
					objFIAColumns.getChncode_Cwspchn(), 
					DateFormatUtility.getStandardSysdate(), 
					objFIAColumns.getEecode(), 
					"0", 
					null);
			objSBWBTrans.execute();
			strBwcodeDeparture = String.valueOf(objSBWBTrans.getNewBwcode());
		} else {
			strBwcodeDeparture = objSBWBColumns.getBwbwcode();
		}
		// ����
		// String strCwcode = objFIAColumns.getCwcode();
		// HousewaybillColumns objHWColumns = HousewaybillDemand.loadByCwcode(strCwcode);	
		// List listCWPieces = CorewaybillpiecesDemand.load(strCwcode);
		SignOut objSignOut = new SignOut();
		SavedResultUtility objSRU = objSignOut.save(strBwcodeDeparture, 
				objFIAColumns.getCwserverewbcode(), 
				null, 
				strOperId, 
				false,
				isArrearAllowSignout,
				null,
				false);
		/*
		SaveWaybillTransaction objSWTrans = new SaveWaybillTransaction();
		objSWTrans.setSignOutParam(objHWColumns, 
				strBwcodeDeparture, 
				listCWPieces, 
				strOperId);*/
		PromptUtilityCollection objSOPUCollection = objSRU.getPromptUtilityCollection();
		if (objSOPUCollection == null)
			objSOPUCollection = new PromptUtilityCollection();
		if (objPUCollection != null)
			objSOPUCollection.addAll(objPUCollection);
		return objSOPUCollection;		
	}
	
	
	/**
	 * ͬ���˵�����
	 * @param objFIAColumns
	 * @param listCargoInfo
	 * @param listCWPieces
	 * @param strOperId
	 * @throws Exception
	 */
	public PromptUtilityCollection syncHousewaybill(ForinputallColumns objFIAColumns,
			List listCargoInfo,
			List listCWPieces,
			String strOperId) throws Exception {
		Input objInput = new Input();
		InputAllQReturn objInputAllQReturn = objInput.inputAll(strOperId, 
				objFIAColumns, 
				listCargoInfo, 
				listCWPieces,
				true);
		PromptUtilityCollection objPUCollection = objInputAllQReturn.getPromptUtilityCollection();
		if (objPUCollection != null && !objPUCollection.canGo(true))
			return objPUCollection;
		// �Ƶ����÷�������
		List listHouseWayBill = objInputAllQReturn.getHWBResults();		
		String strCwcode = ((ForinputallColumns)listHouseWayBill.get(0)).getCwcode();
		HousewaybillColumns objHWColumns = HousewaybillDemand.loadByCwcode(strCwcode);
		// ���ó�������
		SimplebatchwaybillColumns objSBWBColumns = BatchWayBillDemand.getLatestUnCompleteBatchwaybill(objHWColumns.getSchnchncode(), 
				objHWColumns.getScococode(), 
				objHWColumns.getEeeecode(), 
				DateFormatUtility.getStandardSysdate(), 
				"D");
		// �½���������
		String strBwcodeDeparture = objSBWBColumns.getBwbwcode();
		if (objSBWBColumns == null) {
			// ����
			SaveBatchwaybillTrans objSBWBTrans = new SaveBatchwaybillTrans();
			objSBWBTrans.setDepartureParam(objHWColumns.getScococode(), 
					objHWColumns.getSchnchncode(), 
					DateFormatUtility.getStandardSysdate(), 
					objHWColumns.getEeeecode(), 
					"0", 
					null);
			objSBWBTrans.execute();
			strBwcodeDeparture = String.valueOf(objSBWBTrans.getNewBwcode());
		}
		// ����
		SaveWaybillTransaction objSWTrans = new SaveWaybillTransaction();
		objSWTrans.setSignOutParam(objHWColumns, 
				strBwcodeDeparture, 
				listCWPieces, 
				strOperId,
				"");
		if (objPUCollection == null)
			objPUCollection = new PromptUtilityCollection();
		return objPUCollection;
	}
	
	private List buildPrealertPiecesInfo(HousewaybillColumns objHWColumns, 
			String strVolumechargeweight,
			int iVolumeRate) {
		if (StringUtility.isNull(strVolumechargeweight))
			strVolumechargeweight = "0";
		int iPieces = Integer.parseInt(objHWColumns.getCwcwpieces());
		List<CorewaybillpiecesColumns> listPiecesColumns = new ArrayList<CorewaybillpiecesColumns>();
		for (int i = 0; i < iPieces; i++) {
			CorewaybillpiecesColumns objCWPColumns = new CorewaybillpiecesColumns();
			objCWPColumns.setCpcomp_idcpid(i);
			objCWPColumns.setCpcomp_idcwcode(Long.parseLong(objHWColumns.getHwcwcode()));
			if (i == 0) {
				objCWPColumns.setCpcpgrossweight(new BigDecimal(objHWColumns.getCwcwgrossweight()));
				objCWPColumns.setCpcpheight(new BigDecimal(strVolumechargeweight));
				objCWPColumns.setCpcpwidth(new BigDecimal(String.valueOf(iVolumeRate)));
				objCWPColumns.setCpcplength(new BigDecimal("1"));		
				objCWPColumns.setCpcpselflabelcode(buildSelfLabelcode(objHWColumns.getCwcwcustomerewbcode(),
						1));
			}
			else {
				objCWPColumns.setCpcpgrossweight(new BigDecimal("0"));
				objCWPColumns.setCpcpheight(new BigDecimal("0"));
				objCWPColumns.setCpcpwidth(new BigDecimal("0"));
				objCWPColumns.setCpcplength(new BigDecimal("0"));	
				objCWPColumns.setCpcpselflabelcode(buildSelfLabelcode(objHWColumns.getCwcwcustomerewbcode(),
						i + 1));				
			}
			listPiecesColumns.add(objCWPColumns);
		}
		return listPiecesColumns;
	}	
	
    private String buildSelfLabelcode(String strCustomerEwbcode, int iIndex)
    {
    	String strSelfLabelcode = String.valueOf(iIndex);
        for (int i = 0; i < 3 - strSelfLabelcode.length(); i++)
        {
            strSelfLabelcode = "0" + strSelfLabelcode;
        }
        return strCustomerEwbcode + "H" + strSelfLabelcode;
    }	
	
	
	private List buildPiecesInfo(HousewaybillColumns objHWColumns) {
		int iPieces = Integer.parseInt(objHWColumns.getCwcwpieces());
		List<CorewaybillpiecesColumns> listPiecesColumns = new ArrayList<CorewaybillpiecesColumns>();
		for (int i = 0; i < iPieces; i++) {
			CorewaybillpiecesColumns objCWPColumns = new CorewaybillpiecesColumns();
			objCWPColumns.setCpcomp_idcpid(i);
			objCWPColumns.setCpcomp_idcwcode(Long.parseLong(objHWColumns.getHwcwcode()));
			if (i == 0) 
				objCWPColumns.setCpcpgrossweight(new BigDecimal(objHWColumns.getCwcwchargeweight()));
			else
				objCWPColumns.setCpcpgrossweight(new BigDecimal("0"));
			objCWPColumns.setCpcpheight(new BigDecimal("0"));
			objCWPColumns.setCpcpwidth(new BigDecimal("0"));
			objCWPColumns.setCpcplength(new BigDecimal("0"));
			listPiecesColumns.add(objCWPColumns);
		}
		return listPiecesColumns;
	}
	
	private PredictOrderColumns transfer(PredictwaybillColumns objPWBColumns,
			List listCargoInfo) {
		PredictOrderColumns objPredictOrderColumns = new PredictOrderColumns();
		objPredictOrderColumns.setStrCwcustomerewbcode(objPWBColumns.getCwcwcustomerewbcode());
		objPredictOrderColumns.setStrCwpostcodedestination(objPWBColumns.getCwhwhwconsigneepostcode());
		objPredictOrderColumns.setStrDtcode(objPWBColumns.getCwdtocodedtcode());
		objPredictOrderColumns.setStrHwconsigneeaddress1(objPWBColumns.getCwhwhwconsigneeaddress1());
		objPredictOrderColumns.setStrHwconsigneeaddress2(objPWBColumns.getCwhwhwconsigneeaddress2());
		objPredictOrderColumns.setStrHwconsigneeaddress3(objPWBColumns.getCwhwhwconsigneeaddress3());
		objPredictOrderColumns.setStrHwconsigneename(objPWBColumns.getCwhwhwconsigneename());
		objPredictOrderColumns.setStrHwconsigneetelephone(objPWBColumns.getCwhwhwconsigneetelephone());
		objPredictOrderColumns.setStrHwremark(objPWBColumns.getCwhwhwremark());
		objPredictOrderColumns.setStrPk_code(objPWBColumns.getCwpkpkcode());
		objPredictOrderColumns.setStrCwgrossweight(objPWBColumns.getCwcwgrossweight());
		objPredictOrderColumns.setStrCwpieces(objPWBColumns.getCwcwpieces());
		objPredictOrderColumns.setStrCocode(objPWBColumns.getCwcocuscocode());
		objPredictOrderColumns.setStrAdddate(objPWBColumns.getCwcwcreatedate());
		objPredictOrderColumns.setStrCwpostcodedestination(objPWBColumns.getCwPostcodeDestination());
		
		if (listCargoInfo != null && listCargoInfo.size() > 0) {
			String[] astrCiname = new String[listCargoInfo.size()];
			String[] astrCiUnitprice = new String[listCargoInfo.size()];
			String[] astrCiPieces = new String[listCargoInfo.size()];
			for (int i = 0; i < listCargoInfo.size(); i++) {
				CargoinfoColumns objCargoinfoColumns = (CargoinfoColumns)listCargoInfo.get(i);
				astrCiname[i] = objCargoinfoColumns.getCiciename();
				astrCiUnitprice[i] = objCargoinfoColumns.getCiciunitprice();
				astrCiPieces[i] = objCargoinfoColumns.getCicipieces();
			}
			objPredictOrderColumns.setStrCiename(astrCiname);
			objPredictOrderColumns.setStrCipieces(astrCiPieces);
			objPredictOrderColumns.setStrCiunitprice(astrCiUnitprice);
		}
		return objPredictOrderColumns;
	}

}
