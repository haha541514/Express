package kyle.leis.eo.operation.housewaybill.bl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.billing.payable.bl.Payable;
import kyle.leis.eo.billing.receivable.bl.Receivable;
import kyle.leis.eo.customerservice.issue.bl.Issue;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillColumns;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybill.dax.Corewaybillcode;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderCheck;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderColumnsEX;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderDemand;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderMap;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderRow;
import kyle.leis.eo.operation.housewaybill.tp.MergePredictWaybillTrans;
import kyle.leis.eo.operation.housewaybill.tp.ModifyCoreWaybillStatusTrans;
import kyle.leis.eo.operation.housewaybill.tp.ParsePredictWaybillTrans;
import kyle.leis.eo.operation.housewaybill.tp.PureMergePredictWaybillTrans;
import kyle.leis.eo.operation.housewaybill.tp.SaveHousewaybillTrans;
import kyle.leis.eo.operation.predictwaybill.dax.PredictwaybillCheck;
import kyle.leis.eo.operation.predictwaybill.tp.ModifyStatusByCorewaybillTrans;
import kyle.leis.es.company.predicttemplate.bl.Predicttemplate;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeColumns;
import kyle.leis.es.company.shipperconsignee.dax.ShipperconsigneeDemand;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TcoCorporationDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.fs.dictionary.dictionarys.dax.DictionaryDemand;
import kyle.leis.fs.dictionary.productkind.da.ProductkindColumns;
import kyle.leis.fs.dictionary.productkind.dax.ProductkindDemand;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TdiCustomertype;
import kyle.leis.hi.TdiProductkind;

public class PredictOrderEX {
	/**
	 * �ͻ���������ص���ʾ��Ϣ
	 */
	private static Map<String, PredictOrderMap> s_hmPromptEWB;
	
	/** �ӿڵ��� **/
	private final boolean apiCall;
	
	public PredictOrderEX() {
		apiCall = false;
	}
	
	public PredictOrderEX(boolean apiCall) {
		this.apiCall = apiCall;
	}
	
	/**
	 * ��ģ�嵼��
	 * @param strCocode
	 * @param strPredictOrderTemplate
	 * @param listPredictOrderRow
	 * @param strOperId
	 * @param isAllowMerge
	 * @throws Exception
	 */
	public void save(String strCocode,
			String strPredictOrderTemplate,
			List<PredictOrderRow> listPredictOrderRow,
			String strOperId,
			String strPath,
			boolean isAllowMerge,
			boolean isAllowMergeAddress) throws Exception {
		if (s_hmPromptEWB == null)
			s_hmPromptEWB = new HashMap<String, PredictOrderMap>();
		if (s_hmPromptEWB.containsKey(strCocode))
			s_hmPromptEWB.remove(strCocode);
		// ��ʽת��
		Predicttemplate objPredicttemplate = new Predicttemplate();
		List<PredictOrderColumnsEX> listWaybill = objPredicttemplate.transferTostandard(strPredictOrderTemplate, 
				listPredictOrderRow);
		// �����Paypal��ʽ��Ҫ����Ƿ������˷���������
		if (!StringUtility.isNull(strPredictOrderTemplate) &&
				strPredictOrderTemplate.equals("1")) {
			ShipperconsigneeColumns objSCColumns = ShipperconsigneeDemand.loadByCustomer(strCocode);
			if (objSCColumns == null) {
				PredictOrderMap objPredictOrderMap = new PredictOrderMap();
				objPredictOrderMap.putPromptByrow("Paypal��ʽҪ���÷����������˺�", listPredictOrderRow);
				objPredictOrderMap.setUploadComplete(true);
				s_hmPromptEWB.put(strCocode, objPredictOrderMap);
				//��������				
				//Cwbimportlog objCwbimportlog=new Cwbimportlog();
				//CwbimportlogColumns cwbimport=objCwbimportlog.saveLog(strOperId,listPredictOrderRow.size(),listPredictOrderRow.size(),strPath,"Paypal��ʽҪ���÷����������˺�");
				return;			
			}
		}

		if (listWaybill == null || listWaybill.size() < 1) {
			PredictOrderMap objPredictOrderMap = new PredictOrderMap();
			objPredictOrderMap.putPromptByrow("ת���ɱ�׼ģ��ʧ��", listPredictOrderRow);
			objPredictOrderMap.setUploadComplete(true);
			s_hmPromptEWB.put(strCocode, objPredictOrderMap);
			
			//������־����				
			//Cwbimportlog objCwbimportlog=new Cwbimportlog();
			//CwbimportlogColumns cwbimport=objCwbimportlog.saveLog(strOperId,listPredictOrderRow.size(),listPredictOrderRow.size(),strPath,"ת���ɱ�׼ģ��ʧ��");
			return;
		}
		PredictOrderCheck objPOC = new PredictOrderCheck();
		// �ϲ���Ʒ+�ͻ��˵���
		PredictOrderMap objPredictOrderMap = objPOC.checkRepeatCustomerEWB(strCocode, 
				listWaybill, 
				isAllowMerge,
				"PC");
		listWaybill = objPOC.getFilterRepeatwaybill();
		// �ϲ��ռ�����+��ַ
		String strSystemPE = SystempropertyDemand.getEnterprise();
		if (isAllowMergeAddress &&
				!StringUtility.isNull(strSystemPE) &&
				!strSystemPE.startsWith("QQYX")) {
			objPredictOrderMap = objPOC.checkRepeatCustomerEWB(strCocode, 
					listWaybill, 
					true,
					"CN");
		}
		listWaybill = objPOC.getFilterRepeatwaybill();
		// ����ȱʡֵ
		PredictOrderDemand.setDefaultinfo(strCocode, listWaybill);
		// Ч��
		objPredictOrderMap = objPOC.check(strCocode, listWaybill, apiCall);
		// ����
		List<PredictOrderColumnsEX> listNormal = objPredictOrderMap.getNormalWaybill();
				
		if (listNormal != null && listNormal.size() < 1) {
			objPredictOrderMap.setUploadComplete(true);
			s_hmPromptEWB.put(strCocode, objPredictOrderMap);
			return;
		}
		s_hmPromptEWB.put(strCocode, objPredictOrderMap);
		for (PredictOrderColumnsEX objPOCEX : listNormal) {
			try {
				InputAllQReturn objIAR = save(strCocode, objPOCEX, strOperId, 
						false, false);
				PromptUtilityCollection objPUCollection = objIAR.getPromptUtilityCollection();
				if (objPUCollection != null && !objPUCollection.canGo(false))
					objPredictOrderMap.putPrompt("�������" + objPUCollection.toString(),
							objPOCEX);
				else {
					objPredictOrderMap.putSavedSeccess(objPOCEX);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				objPredictOrderMap.putPrompt("�������" + ex.getMessage(),
						objPOCEX);
			}
		}
		objPredictOrderMap.setUploadComplete(true);
		//s_hmPromptEWB.put(strCocode, objPredictOrderMap);
		
		
		
	}
	
	/**
	 * ����
	 * @param strCocode
	 * @param objPOCEX
	 * @param strOperId
	 * @return
	 * @throws Exception
	 */
	public InputAllQReturn save(String strCocode,
			PredictOrderColumnsEX objPOCEX,
			String strOperId,
			String strSccode) throws Exception {
		if (!StringUtility.isNull(strSccode)) {
			if(!strSccode.equals("")){
				ShipperconsigneeColumns objSCColumns = ShipperconsigneeDemand.load(strSccode);
				if (objSCColumns == null) {
					InputAllQReturn objIAR = new InputAllQReturn();
					PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
					objPUCollection.add("E_001", "�������˺Ų�����", "PredictOrderEX.save");
					objIAR.setPromptUtilityCollection(objPUCollection);
					return objIAR;
				}
				PredictOrderDemand.setShipperInfo(objPOCEX, objSCColumns);
			}			
		}else{
			if(strSccode.equals("")){
				ShipperconsigneeColumns objSCColumns =new ShipperconsigneeColumns();
				objSCColumns.setScscaddress1(objPOCEX.getWaybillforpredict().getHwhw_shipperaddress1());
				objSCColumns.setScscaddress2(objPOCEX.getWaybillforpredict().getHwhw_shipperaddress2());
				objSCColumns.setScscaddress3(objPOCEX.getWaybillforpredict().getHwhw_shipperaddress3());
				objSCColumns.setScsccompanyname(".");
				objSCColumns.setScscfax(objPOCEX.getWaybillforpredict().getHwhw_shipperfax());
				objSCColumns.setScscname(objPOCEX.getWaybillforpredict().getHwhw_shippername());
				objSCColumns.setScscpostcode(objPOCEX.getWaybillforpredict().getHwhw_shipperpostcode());
				objSCColumns.setScsctelephone(objPOCEX.getWaybillforpredict().getHwhw_shippertelephone());
				PredictOrderDemand.setShipperInfo(objPOCEX, objSCColumns);
			}
		}
		return save(strCocode, objPOCEX, strOperId, true, true);
	}	
	
	/**
	 * ����
	 * @param strCocode
	 * @param predictOrderRow
	 * @param strOperId
	 * @param strPredictOrderTemplate
	 * @return
	 * @throws Exception
	 */
	public InputAllQReturn save(String strCocode, PredictOrderRow predictOrderRow,
			String strOperId, String strPredictOrderTemplate) throws Exception {
		Predicttemplate objPredicttemplate = new Predicttemplate();
		List<PredictOrderRow> predictOrderRows = new ArrayList<PredictOrderRow>();
		predictOrderRows.add(predictOrderRow);
		List<PredictOrderColumnsEX> listWaybill = objPredicttemplate.transferTostandard(strPredictOrderTemplate, predictOrderRows);
		return save(strCocode, listWaybill.get(0), strOperId, "");
	}
	
	/**
	 * ����
	 * @param strCocode
	 * @param objPOCEX
	 * @param strOperId
	 * @throws Exception
	 */
	public PromptUtilityCollection ovvride(String strCocode,
			PredictOrderColumnsEX objPOCEX,
			String strOperId) throws Exception {
		// �����ظ����˵�
		WaybillforpredictColumns objWFPC = objPOCEX.getWaybillforpredict();
		SimplecorewaybillColumns objSCWC = CorewaybillDemand.loadSimpleCorewaybill(objWFPC.getCwcw_customerewbcode(), 
				strCocode, true);	
		if (objSCWC == null) {
			PromptUtilityCollection objPU = new PromptUtilityCollection();
			objPU.add("E_001", "û����Ҫ���ǵ�����", "ovvride");
			return objPU;
		}
		objWFPC.setCwcw_code(objSCWC.getCwcw_code());
		String strPE = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strPE) && strPE.startsWith("SLY"))  {
			String strEwbcode = PredictOrderDemand.buildEwbcode();
			objWFPC.setCwcw_serverewbcode(strEwbcode);
			objWFPC.setCwcw_ewbcode(strEwbcode);
		} else {
			HousewaybillColumns objHwcolumns = HousewaybillDemand.loadByCwcode(objSCWC.getCwcw_code());
			objWFPC.setCwcw_serverewbcode(objHwcolumns.getCwcwserverewbcode());
			objWFPC.setCwcw_ewbcode(objHwcolumns.getCwcwewbcode());	
			//objWFPC.setCwcw_customerewbcode(objHwcolumns.getCwcwcustomerewbcode());
		}
		// ����
		InputAllQReturn objIAR = save(strCocode, objPOCEX, strOperId, false, true);
		if (objIAR == null) {
			PromptUtilityCollection objPU = new PromptUtilityCollection();
			objPU.add("E_001", "�����������ֵΪ��", "ovvride");
			return objPU;
		}
		return objIAR.getPromptUtilityCollection();	
	}
	
	/**
	 * ����
	 * @param strCocode
	 * @param predictOrderRow
	 * @param strOperId
	 * @param strPredictOrderTemplate
	 * @return
	 * @throws Exception
	 */
	public PromptUtilityCollection ovvride(String strCocode, PredictOrderRow predictOrderRow,
			String strOperId, String strPredictOrderTemplate) throws Exception {
		Predicttemplate objPredicttemplate = new Predicttemplate();
		List<PredictOrderRow> predictOrderRows = new ArrayList<PredictOrderRow>();
		predictOrderRows.add(predictOrderRow);
		List<PredictOrderColumnsEX> listWaybill = objPredicttemplate.transferTostandard(strPredictOrderTemplate, predictOrderRows);
		return ovvride(strCocode, listWaybill.get(0), strOperId);
	}
	
	/**
	 * �ϰ�
	 * @param astrCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void merge(String[] astrCwcode,
			String strOperId) throws Exception {
		MergePredictWaybillTrans objMPWT = new MergePredictWaybillTrans();
		objMPWT.setParam(astrCwcode, strOperId);
		objMPWT.execute();
	}
	
	
	/**
	 * �ϲ�
	 * @param strCocode
	 * @param objPOCEX
	 * @param strOperId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public PromptUtilityCollection merge(String strCocode,
			PredictOrderColumnsEX objPOCEX,
			String strOperId) throws Exception {
		WaybillforpredictColumns objWFPC = PredictOrderDemand.loadExistsRecord(strCocode, objPOCEX);
		if (objWFPC == null ) {
			PromptUtilityCollection objPU = new PromptUtilityCollection();
			objPU.add("E_001", "û����Ҫ�ϲ�������", "merge");
			return objPU;
		}
		String strCwcode = objWFPC.getCwcw_code();
		List listCargoInfo = CargoInfoDemand.queryByCwCode(strCwcode);
		
		PredictOrderColumnsEX objDestPOCEX = new PredictOrderColumnsEX();
		objDestPOCEX.setListCargoInfo(listCargoInfo);
		objDestPOCEX.setWaybillforpredict(objWFPC);
		
		PredictOrderDemand.merge(objPOCEX, objDestPOCEX);

		PureMergePredictWaybillTrans objPMPWT = new PureMergePredictWaybillTrans();
		objPMPWT.setParam(objDestPOCEX, objPOCEX, strOperId);
		objPMPWT.execute();
		
		// Cargoinfo objCargoinfo = new Cargoinfo();
		// objCargoinfo.save(objDestPOCEX.getListCargoInfo(), strCwcode);
		
		return null;
	}
	
	/**
	 * �ϲ�
	 * @param strCocode
	 * @param predictOrderRow
	 * @param strOperId
	 * @param strPredictOrderTemplate
	 * @return
	 * @throws Exception
	 */
	public PromptUtilityCollection merge(String strCocode, PredictOrderRow predictOrderRow,
			String strOperId, String strPredictOrderTemplate) throws Exception {
		Predicttemplate objPredicttemplate = new Predicttemplate();
		List<PredictOrderRow> predictOrderRows = new ArrayList<PredictOrderRow>();
		predictOrderRows.add(predictOrderRow);
		List<PredictOrderColumnsEX> listWaybill = objPredicttemplate.transferTostandard(strPredictOrderTemplate, predictOrderRows);
		return merge(strCocode, listWaybill.get(0), strOperId);
	}
	
	/**
	 * �ۼ�
	 * @param strCocode
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void hold(String strCocode,
			String strCwcode,
			String strOperId) throws Exception {
		Issue objIssue = new Issue();
		objIssue.addHoldIssue(strCwcode, 
				"101", 
				"�ͻ�Ҫ��ۼ�", 
				strOperId);
	}
	
	public void hold(String strCocode,
			PredictOrderColumnsEX objPOCEX,
			String strOperId) throws Exception {
		WaybillforpredictColumns objWFPC = PredictOrderDemand.loadExistsRecord(strCocode, objPOCEX);
		
		Issue objIssue = new Issue();
		objIssue.addHoldIssue(objWFPC.getCwcw_code(), 
				"101", 
				"�ͻ�Ҫ��ۼ�", 
				strOperId);
	}	
	/**
	 * �ۼ�
	 * @param strCocode
	 * @param predictOrderRow
	 * @param strOperId
	 * @param strPredictOrderTemplate
	 * @throws Exception
	 */
	public void hold(String strCocode, PredictOrderRow predictOrderRow,
			String strOperId, String strPredictOrderTemplate) throws Exception {
		Predicttemplate objPredicttemplate = new Predicttemplate();
		List<PredictOrderRow> predictOrderRows = new ArrayList<PredictOrderRow>();
		predictOrderRows.add(predictOrderRow);
		List<PredictOrderColumnsEX> listWaybill = objPredicttemplate.transferTostandard(strPredictOrderTemplate, predictOrderRows);
		hold(strCocode, listWaybill.get(0), strOperId);
	}
	
	
	
	/**
	 * ���
	 * @param strCwcode
	 * @param listCargoInfo
	 * @param strOperId
	 * @throws Exception
	 */
	public void parse(String strCwcode,
			List<PredictOrderColumnsEX> listCargoInfo,
			String strOperId) throws Exception {
		if (listCargoInfo == null || listCargoInfo.size() < 1)
			return;
		ParsePredictWaybillTrans objPPWT = new ParsePredictWaybillTrans();
		objPPWT.setParam(strCwcode, listCargoInfo, strOperId);
		objPPWT.execute();
	}
	
	
	public void modifyCorewaybillStatus(String strCwcode, 
			String strCwscode,
			String strOperId) throws Exception {
		SaveHousewaybillTrans objSHT = new SaveHousewaybillTrans();
		objSHT.setCWStatusParam(strCwcode, strCwscode, strOperId);
		objSHT.execute();
		if (!StringUtility.isNull(strCwscode) && 
				strCwscode.indexOf("EL") >= 0) {
			// ɾ��Ӧ�շ���
			Receivable objReceivable = new Receivable();
			objReceivable.deleteAll(strCwcode, "0");
			// ɾ��Ӧ������
			Payable objPayable = new Payable();
			objPayable.deleteAll(strCwcode, "0");	
		}
		// �޸�Ԥ������
		ModifyStatusByCorewaybillTrans objMSBCTrans = new ModifyStatusByCorewaybillTrans();
		objMSBCTrans.setParam(strCwcode, 
				strCwscode, 
				strOperId);
		objMSBCTrans.execute();
		
	}
	public void modifyCorewaybillStatus(String[] strCwcode, 
			String strCwscode,
			String strOperId) throws Exception {
		ModifyCoreWaybillStatusTrans objSHT = new ModifyCoreWaybillStatusTrans();
		objSHT.setCWStatusParam(strCwcode, strCwscode, strOperId);
		objSHT.execute();
	}
	public void modifyAttacheinfosign(String strCwcode,
			String strAttacheinfosign,
			String strOperId) throws Exception {
		SaveHousewaybillTrans objSHT = new SaveHousewaybillTrans();
		objSHT.setAttacheinfosignParam(strCwcode, 
				strAttacheinfosign, 
				strOperId);
		objSHT.execute();		
	}
	
	public InputAllQReturn save(String strCocode,
			PredictOrderColumnsEX objPOCEX,
			String strOperId,
			boolean isNeedCheck,
			boolean isOnlyCheckBase) throws Exception {
		PromptUtilityCollection objPU = new PromptUtilityCollection();
		if (isNeedCheck) {
			InputAllQReturn objIAQR = new InputAllQReturn();
			
			List listStandardTemplate = null;
			if (SystempropertyDemand.getEnterprise().startsWith("SLYIM") &&
					apiCall) {
				listStandardTemplate = DictionaryDemand.queryStandardTemplate(true);
			} else {
				listStandardTemplate = DictionaryDemand.queryStandardTemplate();
			}			
			
			if (listStandardTemplate == null || listStandardTemplate.size() < 1) {
				objPU.add("E_001", "��׼ģ�廹û������", "save");
				objIAQR.setPromptUtilityCollection(objPU);
				return objIAQR;
			}			
			PredictOrderCheck objPOC = new PredictOrderCheck();
			String strErrorInfo = objPOC.check(strCocode, objPOCEX, listStandardTemplate, isOnlyCheckBase);
			if (!StringUtility.isNull(strErrorInfo)) {
				if (!StringUtility.isNull(objPOCEX.getPromptinfo()))
					strErrorInfo = strErrorInfo + ":" + objPOCEX.getPromptinfo();
				objPU.add("E_001", strErrorInfo, "save");
				objIAQR.setPromptUtilityCollection(objPU);
				return objIAQR;
			}
		}
		Input input = new Input();
		
		ForinputallColumns objFIAColumns = PredictOrderDemand.buildForinputAll(strCocode,
				objPOCEX);
		List listCargo = PredictOrderDemand.buildCargoinfo(objPOCEX, objFIAColumns);
		List listWaybillPieces = PredictOrderDemand.buildPiecesinfo(objPOCEX);
		PredictOrderDemand.buildChargeweightAndChannel(objFIAColumns, listWaybillPieces);
		String strEnterprise = SystempropertyDemand.getEnterprise();
		
		if (strEnterprise.startsWith("SLYIM") &&
				apiCall) {
			String strDtcode = PredictwaybillCheck.checkIMPostcode(objPU, 
					"",
					objFIAColumns.getHwConsigneecity(),
					objFIAColumns.getHwconsigneepostcode());
			if (objPU != null && !objPU.canGo(false)) {
				InputAllQReturn objIAQR = new InputAllQReturn();
				objIAQR.setPromptUtilityCollection(objPU);
				return objIAQR;				
			}
			objFIAColumns.setDtcode(strDtcode);
			//objFIAColumns.setSidtcode(strDtcode);
		}
		
		//��֤�ʱ�ͳ���
		if (isNeedCheck && !StringUtility.isNull(objFIAColumns.getChncode_Cwspchn())) {
			PredictOrderCheck objPOC = new PredictOrderCheck();
			String strErrorInfo = objPOC.checkPostAndCity(objFIAColumns, listCargo);
			if (!StringUtility.isNull(strErrorInfo)) {
				InputAllQReturn objIAQR = new InputAllQReturn();
				objPU = new PromptUtilityCollection();
				objPU.add("E_001", strErrorInfo, "save");
				objIAQR.setPromptUtilityCollection(objPU);
				return objIAQR;
			}
		}
		if (apiCall) {
			PredictOrderDemand.replaceShipperInfoByChnChannel(objFIAColumns.getChncode_Cwspchn(), objFIAColumns);
			// ��ǧapi�ͻ������ǡ���ҵ���A���ģ��غ���Ʒ���˵����ϴ����˵�״ֱ̬������Ϊ�Ƶ�״̬
			if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("SLY")) {
				TcoCorporation objTco = TcoCorporationDC.loadByKey(strCocode);
				TdiCustomertype objTCM = objTco.getTcoCustomer().getTdiCustomertype();
				TdiProductkind objTPK = TdiProductkindDC.loadByKey(objFIAColumns.getPk_code());
				if ("BPA".equals(objTCM.getCtCode()) && 
						objTPK.getTdiServerstructuregroup() != null &&
						objTPK.getTdiServerstructuregroup().getSsgCode().startsWith("DHL")) {
					objFIAColumns.setCwscode("IP");
				}
			}
		}
		if (listWaybillPieces != null && 
				listWaybillPieces.size() != Integer.parseInt(objFIAColumns.getCwpieces())) {
			objFIAColumns.setCwpieces(listWaybillPieces.size());
		}		
		
		// ���÷����̵���	
		// ����ͨ��service��ȡ������ֵΪnull��ʾ��������ͨ��service�ķ�ʽ����ȡ��������system
		objPU = setWaybillcodeByService(objFIAColumns, listWaybillPieces, listCargo);
		if (objPU == null) {
			Corewaybillcode objCorewaybillcode = new Corewaybillcode();
			objPU = objCorewaybillcode.setNewWaybillcodeBySystem(objFIAColumns,
					listWaybillPieces,
					objFIAColumns.getChncode_Cwspchn(),
					strOperId);
		}
		if (!objPU.canGo(false)) {
			InputAllQReturn objIAQR = new InputAllQReturn();
			objIAQR.setPromptUtilityCollection(objPU);
			return objIAQR;
		}
		// �����ɹ�
		if (!StringUtility.isNull(objFIAColumns.getServerwbckbckcode()) && 
				StringUtility.isNull(objFIAColumns.getHwbookingid())) {
			objFIAColumns.setCwewbcode(objFIAColumns.getCwserverewbcode());
		}
		return input.inputAll(strOperId, objFIAColumns, 
				listCargo, listWaybillPieces, false);		
	}
	
	private PromptUtilityCollection setWaybillcodeByService(ForinputallColumns objFIAColumns,
			List listWaybillPieces,
			List listCargo) throws Exception {
		if (StringUtility.isNull(objFIAColumns.getChncode_Cwspchn())) 
			return null;
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
		if (objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind() == null)
			return null;
		if (StringUtility.isNull(objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind().getBckFromwebservicesign()))
			return null;
		
		if (!apiCall) {
			String strPkcode = objFIAColumns.getPk_code();
			// ��ǧ��С���������϶����ٰ�service�ķ�ʽ��ȡ����
			if (SystempropertyDemand.getEnterprise().startsWith("SLY")) {
				ProductkindColumns objPKColumns = ProductkindDemand.queryBypkCode(strPkcode);
				PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
				if (!"HKPK".equals(objPKColumns.getSsgcode())) {
					return objPUCollection;
				}
			}
			if (objTchnChannel.getTdiServerstructuregroup() == null ||
					!objTchnChannel.getTdiServerstructuregroup().getSsgCode().equals("AUEMS"))
				return null;			
		}
		Corewaybillcode objCorewaybillcode = new Corewaybillcode();
		HousewaybillColumns objHWBColumns = new HousewaybillColumns();
		return objCorewaybillcode.setWaybillcodeByService(objFIAColumns, 
				listWaybillPieces, 
				listCargo, 
				objHWBColumns, 
				objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind().getBckCode(), 
				"_E", 
				"", 
				objTchnChannel.getChnMasteraccount());
	}
	
	public Map getSavedPromptEWB(String strCocode) {
		if (s_hmPromptEWB == null || !s_hmPromptEWB.containsKey(strCocode))
			return null;
		PredictOrderMap objPredictOrderMap = s_hmPromptEWB.get(strCocode);
		if (objPredictOrderMap == null)
			return null;
		return objPredictOrderMap.getPromptWaybill();
	}
	
	public int getSavedNormalSize(String strCocode) {
		if (s_hmPromptEWB == null || !s_hmPromptEWB.containsKey(strCocode))
			return 0;
		PredictOrderMap objPredictOrderMap = s_hmPromptEWB.get(strCocode);
		if (objPredictOrderMap == null)
			return 0;
		return objPredictOrderMap.getNormalWaybillSize();
	}
	/**
	 * ��ȡ�ѳɹ�����Ķ���
	 * @param strCocode
	 * @return
	 */
	public List<PredictOrderColumnsEX> getSavedSeccess(String strCocode){
		if (s_hmPromptEWB == null || !s_hmPromptEWB.containsKey(strCocode))
			return new ArrayList<PredictOrderColumnsEX>();;
		PredictOrderMap objPredictOrderMap = s_hmPromptEWB.get(strCocode);
		if (objPredictOrderMap == null)
			return new ArrayList<PredictOrderColumnsEX>();
		return objPredictOrderMap.getSavedSeccessWaybill();
	}
	/**
	 * ��ȡ�ϲ��ļ�¼
	 * @param strCocode
	 * @return
	 */
	public List<PredictOrderColumnsEX> getRepeatedWaybill(String strCocode){
		if (s_hmPromptEWB == null || !s_hmPromptEWB.containsKey(strCocode))
			return new ArrayList<PredictOrderColumnsEX>();;
		PredictOrderMap objPredictOrderMap = s_hmPromptEWB.get(strCocode);
		if (objPredictOrderMap == null)
			return new ArrayList<PredictOrderColumnsEX>();
		return objPredictOrderMap.getRepeatedWaybill();
	}
	/**
	 * �ϴ��Ƿ����
	 * @param strCocode
	 * @return
	 */
	public boolean isUploadComplete(String strCocode){
		if (s_hmPromptEWB == null || !s_hmPromptEWB.containsKey(strCocode))
			return false;
		PredictOrderMap objPredictOrderMap = s_hmPromptEWB.get(strCocode);
		if (objPredictOrderMap == null)
			return false;
		return objPredictOrderMap.isUploadComplete();
	}
}
