package kyle.leis.eo.operation.housewaybill.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.billing.calculate.chargeweight.bl.Chargeweight;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightParameter;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightResult;
import kyle.leis.eo.billing.calculate.feecalculate.blx.AutoFeeCalculateThread;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.eo.operation.corewaybill.blx.CoreWayBillCheck;
import kyle.leis.eo.operation.corewaybill.dax.Corewaybillcode;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderDemand;
import kyle.leis.eo.operation.housewaybill.dax.QQYXCreateZXLabel;
import kyle.leis.eo.operation.housewaybill.sv.HousewaybillDelegate;
import kyle.leis.eo.operation.housewaybill.tp.InputAllModifyTransaction;
import kyle.leis.eo.operation.housewaybill.tp.InputAllTransaction;
import kyle.leis.eo.operation.housewaybill.tp.JInputAllTransaction;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.eo.operation.predictwaybill.tp.ModifyStatusByCorewaybillTrans;
import kyle.leis.eo.operation.specialtype.bl.Specialtype;
import kyle.leis.es.businessrule.productrule.dax.ProductruleDemand;
import kyle.leis.es.company.shipperblacklist.da.ShipperblacklistColumns;
import kyle.leis.es.company.shipperblacklist.dax.ShipperblacklistDemand;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiWaybillcodekindDC;
import kyle.leis.fs.dictionary.dictionarys.dax.DictionaryDemand;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.fs.waybillcode.bl.AWaybillcode;
import kyle.leis.fs.waybillcode.bl.DHLCoreWaybillcode;
import kyle.leis.fs.waybillcode.dax.IWaybillcodeBasicData;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiWaybillcodekind;

public class Input {
	
	public InputAllQReturn inputAll(String strOperId,
			ForinputallColumns objFIAColumns, 
			List listCargo,
			List listWaybillPieces,
			boolean isCheckRepeatCustomerewbcode) throws Exception {
		return inputAll(strOperId,
				objFIAColumns,
				listCargo,
				listWaybillPieces,
				isCheckRepeatCustomerewbcode,
				null);
	}
	
	public void inputAllForPredict(String strOperId,
			ForinputallColumns objFIAColumns, 
			List listCargo,
			List listWaybillPieces,
			PredictwaybillColumns objPredictwaybillColumns) throws Exception {
		JInputAllTransaction objJInputAllTransaction = new JInputAllTransaction();
		objJInputAllTransaction.setParam(strOperId, 
				objFIAColumns, 
				listCargo, 
				listWaybillPieces, 
				objPredictwaybillColumns);
		objJInputAllTransaction.execute();
	}	
	
	public InputAllQReturn inputAllForService(String strOperId,
			ForinputallColumns objFIAColumns, 
			List listCargo,
			List listWaybillPieces) throws Exception {
		// ���ͻ��˵��Ƿ��ظ�
		CoreWayBillCheck objCoreWayBillCheck = new CoreWayBillCheck();
		PromptUtilityCollection objPUCollection = objCoreWayBillCheck.checkCustomerEwbcode(objFIAColumns.getCwcustomerewbcode(), 
				objFIAColumns.getCwcode());
		if (!objPUCollection.canGo(false)) {
			InputAllQReturn objlistWaybillpieces = new InputAllQReturn();
			objlistWaybillpieces.setPromptUtilityCollection(objPUCollection);
			return objlistWaybillpieces;			
		}
		// ѡ������
		PredictOrderDemand.buildChargeweightAndChannel(objFIAColumns, listWaybillPieces);
		String strChncode = objFIAColumns.getChncode_Cwspchn();
		// δ����������ϴ�����
		if (StringUtility.isNull(strChncode)) {
			InputAllQReturn objIAQR = new InputAllQReturn();
			objPUCollection.add("E_0002", 
					"�޷�����ϴ�����", 
					"Input.modify");
			objIAQR.setPromptUtilityCollection(objPUCollection);
			return objIAQR;				
		}
		// ����������ӵ�
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
		// ���������»�ȡ�������ӵ�
		// ���������͸��ӵ�����
		String strMainWaybillcode = IWaybillcodeBasicData.BCK_DHLMASTER_E;
		String strSubWaybillcode = IWaybillcodeBasicData.BCK_DHLCHILD_E;
		String strFromWebServiceSign = "N";
		if (objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind() != null) {
			strMainWaybillcode = objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind().getBckCode();
			strFromWebServiceSign = objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind().getBckFromwebservicesign();
		}
		if (objTchnChannel.getTdiWaybillcodekindByChnSubbillcodekind() != null)
			strSubWaybillcode = objTchnChannel.getTdiWaybillcodekindByChnSubbillcodekind().getBckCode();
		
		strMainWaybillcode = StringUtility.isNull(strMainWaybillcode) ? IWaybillcodeBasicData.BCK_DHLMASTER_E : strMainWaybillcode;
		strSubWaybillcode = StringUtility.isNull(strSubWaybillcode) ? IWaybillcodeBasicData.BCK_DHLCHILD_E : strSubWaybillcode;
		strFromWebServiceSign = StringUtility.isNull(strFromWebServiceSign) ? "N" : strFromWebServiceSign;
		// �µ���������ΪDHL��������ȡ����
		if (!strMainWaybillcode.equals(IWaybillcodeBasicData.BCK_DHLMASTER_E)) {
			Corewaybillcode objCorewaybillcode = new Corewaybillcode();
			// �ж��Ƿ�ԭ�����߻�
			// ���������DHL���������ض�Ҫ�������������DHL���������ҿ��԰�ԭ�����߻������ϵͳ���ӵ�
			// Ҳ����˵����������˹������Ҳ����DHL���������ֱ�Ӱ�ԭ���ų���
			AWaybillcode objWaybillcode = new DHLCoreWaybillcode();
			boolean isCorrectLabelcode = objWaybillcode.checkLabelcode(objFIAColumns.getCwserverewbcode()).canGo(false);
			boolean isSettingSOByOriginEWB = false;
			ProductruleDemand objProductruleDemand = new ProductruleDemand();
			String strRecordDate = objFIAColumns.getHwrecorddate();
			if (StringUtility.isNull(strRecordDate))
				strRecordDate = DateFormatUtility.getStandardSysdate();
			if (isCorrectLabelcode)
				isSettingSOByOriginEWB = objProductruleDemand.isSignOutByOriginEWB(strRecordDate, 
						strRecordDate, 
						objFIAColumns.getPk_code(), 
						objFIAColumns.getCocode());
			// ͨ��webservice����������ӵ�
			if (strFromWebServiceSign.equals("Y") && 
					!(isCorrectLabelcode && isSettingSOByOriginEWB)) {
				try {
					objPUCollection = objCorewaybillcode.setWaybillcodeByService(objFIAColumns, 
							listWaybillPieces, 
							listCargo, 
							null, 
							strMainWaybillcode, 
							"",
							strSubWaybillcode,
							objTchnChannel.getChnMasteraccount());
				} catch (Exception ex) {
					ex.printStackTrace();		
					objPUCollection.add("H_001", ex.getMessage(), "Input.setWaybillcode");
				}			
			} else {		
				objPUCollection = objCorewaybillcode.setWaybillcodeBySystem(objFIAColumns, 	
						listWaybillPieces, 	
						null, 
						strMainWaybillcode, 
						"", 
						strSubWaybillcode, 
						"",
						strOperId,
						isCorrectLabelcode,
						isSettingSOByOriginEWB);
			}
		}
		if (objPUCollection != null && !objPUCollection.canGo(false)) {
			InputAllQReturn objInputAllQReturn = new InputAllQReturn();
			objInputAllQReturn.setPromptUtilityCollection(objPUCollection);
			return objInputAllQReturn;			
		}
		
		JInputAllTransaction objJInputAllTransaction = new JInputAllTransaction();
		objJInputAllTransaction.setParam(strOperId, 
				objFIAColumns, 
				listCargo, 
				listWaybillPieces, 
				null);
		objJInputAllTransaction.execute();		
		
		// ��¼��ֵ���ص���������
		String strTotalcargo = CargoInfoDemand.sumCargovalue(listCargo);
		String strCkcode = CargoInfoDemand.getCargoCurrency(listCargo);
		Specialtype objSpecialtype = new Specialtype();
		objSpecialtype.addOverDeclare(objJInputAllTransaction.getSavedCwcode(), 
				strTotalcargo, 
				strCkcode, 
				strOperId,
				strChncode);	
		
		String strSsgCode = "";
		if (objTchnChannel != null && objTchnChannel.getTdiServerstructuregroup() != null)
			strSsgCode = objTchnChannel.getTdiServerstructuregroup().getSsgCode();
		// �߷��յ���
		objSpecialtype.addRestrictedElevatedRisk(String.valueOf(objJInputAllTransaction.getSavedCwcode()), 
				DistrictDemand.getCountrycodeByCity(objFIAColumns.getDtcode()), 
				strSsgCode, 
				strOperId);	
		// �Ʒ�		
		// ���¼Ʒ�
		AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(objJInputAllTransaction.getSavedCwcode(),
				IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
				true);
		objAFCThread.start();
		
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setCwcode(objJInputAllTransaction.getSavedCwcode());
		
		return HousewaybillDemand.queryInput(objFInputAllC);		
	}
	
	public ChargeweightParameter transferToSWParameter(ForinputallColumns objFIC,
			List listWaybillPieces) 
	throws Exception {
		ChargeweightParameter objCWParameter = new ChargeweightParameter();
		
		objCWParameter.setDtcode(objFIC.getSidtcode());
		objCWParameter.setCocode(objFIC.getChncode_Cwspchn());
		objCWParameter.setGrossWeight(objFIC.getCwgrossweight());
		objCWParameter.setPdcode(IExpressPriceBasicData.PRICEDOMAIN_COSTS);
		objCWParameter.setPkcode("A");
		objCWParameter.setPostcode(objFIC.getHwconsigneepostcode());
		objCWParameter.setSearchDate(DateFormatUtility.getStandardSysdate());
		// ��ü�����ϸ��Ϣ
		objCWParameter.setWaybillpiecesCollection(listWaybillPieces);
		
		return objCWParameter;
	}		
	
	public InputAllQReturn inputAll(String strOperId,
			ForinputallColumns objFIAColumns, 
			List listCargo,
			List listWaybillPieces,
			boolean isCheckRepeatCustomerewbcode,
			PredictwaybillColumns objPredictwaybillColumns) throws Exception {
		// ���ͻ��˵��Ƿ��ظ�
		CoreWayBillCheck objCoreWayBillCheck = new CoreWayBillCheck();
		if (isCheckRepeatCustomerewbcode) {
			PromptUtilityCollection objPUCollection = objCoreWayBillCheck.checkCustomerEwbcode(objFIAColumns.getCwcustomerewbcode(), 
					objFIAColumns.getCwcode());
			if (!objPUCollection.canGo(false)) {
				InputAllQReturn objlistWaybillpieces = new InputAllQReturn();
				objlistWaybillpieces.setPromptUtilityCollection(objPUCollection);
				return objlistWaybillpieces;			
			}
		}
		// ��ѯԭ�˵�����
		if (!StringUtility.isNull(objFIAColumns.getCwcode())) {
			ForinputallColumns objOriginFC = HousewaybillDemand.load(objFIAColumns.getCwcode());
			if (objOriginFC != null && 
					(objOriginFC.getCwscode().equals("CTS") || 
							objOriginFC.getCwscode().equals("CHD")) ||
							objOriginFC.getCwscode().equals("CHP")) {
				// �����Ԥ�������»�÷���������
				ChargeweightParameter objCWParameter = transferToSWParameter(objFIAColumns,
						listWaybillPieces);
				Chargeweight objChargeweight = new Chargeweight();
				ChargeweightResult objCWResult = objChargeweight.calculate(objCWParameter);			
				if ("A01".equals(objCWResult.getServerweightrulekind())) {
					objFIAColumns.setCwserverchargeweight(new BigDecimal(objFIAColumns.getCwchargeweight()));
					objFIAColumns.setCwtransferchargeweight(new BigDecimal(objFIAColumns.getCwchargeweight()));
				} else {
					objFIAColumns.setCwserverchargeweight(new BigDecimal(objCWResult.getChargeweight()));
					objFIAColumns.setCwtransferchargeweight(new BigDecimal(objCWResult.getChargeweight()));
				}
			}
		}
		InputAllTransaction objInputAllT = new InputAllTransaction();
		objInputAllT.setParam(strOperId, objFIAColumns, 
				listCargo, listWaybillPieces, objPredictwaybillColumns);
		objInputAllT.execute();		
		
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setEwbcode(objFIAColumns.getCwewbcode());
		
		InputAllQReturn objIAQR = HousewaybillDemand.queryInput(objFInputAllC);
		List listHawb = objIAQR.getHWBResults();
		if (listHawb != null && listHawb.size() > 0) {
			ForinputallColumns fic = (ForinputallColumns)listHawb.get(0);
			String strSsgCode = "";
			if (!StringUtility.isNull(fic.getChncode_Cwspchn())) {
				TchnChannel objTchnChannel = TchnChannelDC.loadByKey(fic.getChncode_Cwspchn());
				if (objTchnChannel.getTdiServerstructuregroup() != null)
					strSsgCode = objTchnChannel.getTdiServerstructuregroup().getSsgCode();
			}
			if (!StringUtility.isNull(strSsgCode) &&
					strSsgCode.startsWith("DHL") &&
					!strSsgCode.equals("DHL-GlobeMail") &&
					!strSsgCode.equals("DHL-USGlobeMail")) {
				Specialtype objSpecialtype = new Specialtype();
				// �߷��յ���
				objSpecialtype.addRestrictedElevatedRisk(objInputAllT.getSavedCwcode(), 
						DistrictDemand.getCountrycodeByCity(objFIAColumns.getDtcode()), 
						strSsgCode, 
						strOperId);
			}
		}
		// ���¼Ʒ�
		AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(objInputAllT.getSavedCwcode(),
				IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
				true);
		objAFCThread.start();
		if (SystempropertyDemand.getEnterprise().startsWith("SBD")) {
			objAFCThread = new AutoFeeCalculateThread(objInputAllT.getSavedCwcode(),
					IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW,
					true);
			objAFCThread.start();			
		}		
		
		// �ջ���Ҫɾ��Ԥ������
		if (objPredictwaybillColumns == null && objIAQR != null) {
			List listResults = objIAQR.getHWBResults();
			if (listResults != null && listResults.size() > 0) {
				ForinputallColumns objFIASavedColumns = (ForinputallColumns)listResults.get(0);
				if (objFIASavedColumns != null && 
						!StringUtility.isNull(objFIASavedColumns.getCwcode())) {
					ModifyStatusByCorewaybillTrans objMSBCT = new ModifyStatusByCorewaybillTrans();
					objMSBCT.setParam(objFIASavedColumns.getCwcode(), 
							objFIASavedColumns.getCwscode(), 
							strOperId);
					objMSBCT.execute();
				}
			}
		}
		return objIAQR;
	}
	
	public InputAllQReturn modify(String strOperId, 
			ForinputallColumns objFIAColumns, 
			List<Object> listCargo,
			boolean isIgnoreNotice,
			boolean isMasterwaybillFromSystem,
			boolean isReinputBywebservice) 
	throws Exception {
		return modify(strOperId, objFIAColumns, 
				listCargo, true, 
				isIgnoreNotice, 
				isMasterwaybillFromSystem,
				isReinputBywebservice);
	}
	
	public InputAllQReturn modify(String strOperId, 
			ForinputallColumns objFIAColumns, 
			List<Object> listCargo,
			boolean isReCalculateCharge,
			boolean isIgnoreNotice,
			boolean isMasterwaybillFromSystem,
			boolean isReinputBywebservice) throws Exception {
		// ���ͻ��˵��Ƿ��ظ�
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		/*
		CoreWayBillCheck objCoreWayBillCheck = new CoreWayBillCheck();
		PromptUtilityCollection objPUCollection = objCoreWayBillCheck.checkCustomerEwbcode(objFIAColumns.getCwcustomerewbcode(), 
				objFIAColumns.getCwcode());
		if (!objPUCollection.canGo(false)) {
			InputAllQReturn objlistWaybillpieces = new InputAllQReturn();
			objlistWaybillpieces.setPromptUtilityCollection(objPUCollection);
			return objlistWaybillpieces;			
		}		
		*/
		CoreWayBillCheck objCoreWayBillCheck = new CoreWayBillCheck();
		objCoreWayBillCheck.checkFinanceRestrict(objFIAColumns.getCocode(), "002", false, objPUCollection);
		if (!objPUCollection.canGo(false)) {
			InputAllQReturn objInputAllQReturn = new InputAllQReturn();
			objInputAllQReturn.setPromptUtilityCollection(objPUCollection);
			return objInputAllQReturn;			
		}
		// ��鷢���˹�˾�Ƿ��ں���֮��
		String strSsgCode = "";
		if (!StringUtility.isNull(objFIAColumns.getChncode_Cwspchn())) {
			TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
			strSsgCode = "DHL";
			if (objTchnChannel.getTdiServerstructuregroup() != null)
				strSsgCode = objTchnChannel.getTdiServerstructuregroup().getSsgCode();
			if (!StringUtility.isNull(objFIAColumns.getHwshippercompany())) {
				String strHwshippercompany = objFIAColumns.getHwshippercompany();
				/*
				if (!StringUtility.isNull(strHwshippercompany) && strHwshippercompany.endsWith("CO"))
					strHwshippercompany = strHwshippercompany.substring(0, strHwshippercompany.length() - 2).trim();
				*/
				String strHwconsigneecompany = objFIAColumns.getHwconsigneecompany();
				strHwconsigneecompany = strHwconsigneecompany.replaceAll("[.]", "");
				
				String strHwconsigneename = objFIAColumns.getHwconsigneename();
				strHwconsigneename = strHwconsigneename.replaceAll("[.]", "");
				if (strSsgCode.startsWith("DHL") && 
						(StringUtility.isNull(strHwconsigneecompany) ||
								StringUtility.isNull(strHwconsigneename))) {
					InputAllQReturn objlistWaybillpieces = new InputAllQReturn();
					objPUCollection.add("E_0002", 
							"�շ�����˾�����շ�����������Ϊ.���޸ĺ��ٱ���", 
							"Input.modify");
					objlistWaybillpieces.setPromptUtilityCollection(objPUCollection);
					return objlistWaybillpieces;					
				}				
				ShipperblacklistColumns objSBLColumns = ShipperblacklistDemand.loadByCompanyname(strSsgCode,
						strHwshippercompany);
				if (objSBLColumns != null) {
					InputAllQReturn objlistWaybillpieces = new InputAllQReturn();
					objPUCollection.add("E_0002", 
							"�����˹�˾�����ں�����֮�в��ܱ��棬���޸ķ����˹�˾���ٱ��档", 
							"Input.modify");
					objlistWaybillpieces.setPromptUtilityCollection(objPUCollection);
					return objlistWaybillpieces;				
				}
			}
			if ("SPE_EPARCEL".equals(strSsgCode)) {
				// ���»��Ŀ�ĵ�����
				String strEPDHubcode = DictionaryDemand.getEparcelDistrict(objFIAColumns.getHwconsigneepostcode());
				if (!StringUtility.isNull(strEPDHubcode)) {
					objFIAColumns.setDtcode(DistrictDemand.getNotCountryDtcodeByHubcode(strEPDHubcode));
					objFIAColumns.setDthubcode(strEPDHubcode);
				}
			}
		}
		// �����������ӵ�
		List listWaybillpieces = CorewaybillpiecesDemand.load(objFIAColumns.getCwcode());
		objPUCollection = setWaybillcode(listWaybillpieces, 
				objFIAColumns, 
				strOperId,
				listCargo,
				isIgnoreNotice,
				isMasterwaybillFromSystem,
				isReinputBywebservice);
		// ������ʾ�����ʶDHL��վ����δ�ɹ�
		// ����ʧ�������ñ��
		if (isIgnoreNotice || (objPUCollection != null && !objPUCollection.canGo(false))) {
			objFIAColumns.setServerwbckbckcode("");
			objFIAColumns.setHwhwserverewbchangedsign("N");
			objFIAColumns.setHwlabelprinttimes(Integer.parseInt("0"));
			objFIAColumns.setSubwbckbckcode("");			
		}
		if (objPUCollection != null && !objPUCollection.canGo(isIgnoreNotice)) {
			InputAllQReturn objInputAllQReturn = new InputAllQReturn();
			objInputAllQReturn.setPromptUtilityCollection(objPUCollection);
			return objInputAllQReturn;			
		}
		// ����
		InputAllModifyTransaction objIAMT = new InputAllModifyTransaction();
		objIAMT.setParam(strOperId, 
				objFIAColumns, 
				listCargo, 
				listWaybillpieces);
		objIAMT.execute();
		
		Long lNewCwcode = objIAMT.getNewCwcode();
		
		// ��¼��ֵ���ص���������
		String strTotalcargo = CargoInfoDemand.sumCargovalue(listCargo);
		String strCkcode = CargoInfoDemand.getCargoCurrency(listCargo);
		Specialtype objSpecialtype = new Specialtype();
		objSpecialtype.addOverDeclare(String.valueOf(lNewCwcode), 
				strTotalcargo, 
				strCkcode, 
				strOperId,
				objFIAColumns.getChncode_Cwspchn());		
		// �߷��յ���
		objSpecialtype.addRestrictedElevatedRisk(String.valueOf(lNewCwcode), 
				DistrictDemand.getCountrycodeByCity(objFIAColumns.getDtcode()), 
				strSsgCode, 
				strOperId);	
		// �Ʒ�
		if (isReCalculateCharge) {
			AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(String.valueOf(lNewCwcode),
					IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
					true);
			objAFCThread.start();
			if (SystempropertyDemand.getEnterprise().startsWith("SBD")) {
				objAFCThread = new AutoFeeCalculateThread(String.valueOf(lNewCwcode),
						IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW,
						true);
				objAFCThread.start();			
			}
		}
		// Ч��ODA���Ʒ�
		/*
		RemoteDistrictThread objRDThread = new RemoteDistrictThread(String.valueOf(lNewCwcode),
				objIAMT.getSavedCoreWayBill().getTdiDistrictByDtCodeDestination().getTdiDistrict().getDtHubcode(),
				objFIAColumns.getHwconsigneepostcode(),
				objFIAColumns.getHwConsigneecity());
		objRDThread.start(); 
		*/
		// ����ֵ
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setEwbcode(objFIAColumns.getCwewbcode());
		InputAllQReturn objIAQR = HousewaybillDemand.queryInput(objFInputAllC);		
		// Ǭ����Ҫ�ϴ���������ǧ
		if ((SystempropertyDemand.getEnterprise().startsWith("QX") || 
				SystempropertyDemand.getEnterprise().startsWith("HST")) && 
				!StringUtility.isNull(strSsgCode) &&
				strSsgCode.equals("DHL_BQ")) {
			try {
				return inputToOtherService(strOperId, objIAQR);
			} catch (Exception ex) {
				ex.printStackTrace();
				objPUCollection = new PromptUtilityCollection();
				objPUCollection.add("H_001", ex.getMessage(), "inputToOtherService");
				InputAllQReturn objInputAllQReturn = new InputAllQReturn();
				objInputAllQReturn.setPromptUtilityCollection(objPUCollection);
				return objInputAllQReturn;					
			}
		}
		// QQYX��Ҫ��������ı�ǩ
		if (SystempropertyDemand.getEnterprise().startsWith("QQYX")) {
			QQYXCreateZXLabel objQQCZXL = new QQYXCreateZXLabel(objIAQR);
			objQQCZXL.start();
		}
		// ����ֵ
		return objIAQR;
	}
	
	private InputAllQReturn inputToOtherService(String strOperID, 
			InputAllQReturn objIAQR) throws Exception {
		HousewaybillDelegate objHWBD = new HousewaybillDelegate();
		ForinputallColumns objFIAColumns = (ForinputallColumns)objIAQR.getHWBResults().get(0);
		String strCwcode = objFIAColumns.getCwcode();
		if (!StringUtility.isNull(objFIAColumns.getHwhwserverewbchangedsign()) &&
				objFIAColumns.getHwhwserverewbchangedsign().equals("Y"))
			return objIAQR;
		// �����ϴ�����
		HousewaybillDemand.buildInputToService(objFIAColumns, objIAQR.getPieces());
		InputAllQReturn objInputIAQR = objHWBD.inputAllForService("0", 
				objFIAColumns, 
				objIAQR.getCargoInfoResults(), 
				objIAQR.getPieces());
		// ���淵��ֵ
		PromptUtilityCollection objPUCollection = objInputIAQR.getPromptUtilityCollection();
		if (objPUCollection != null && !objPUCollection.canGo(false)) {
			InputAllQReturn objInputAllQReturn = new InputAllQReturn();
			objInputAllQReturn.setPromptUtilityCollection(objPUCollection);
			return objInputAllQReturn;			
		}
		// ������Ҫ���������
		List listWaybillPieces = objInputIAQR.getPieces();
		ForinputallColumns objInputFIAColumns = (ForinputallColumns)objInputIAQR.getHWBResults().get(0);
		ForinputallColumns objQueryFAColumns = HousewaybillDemand.load(strCwcode);
		HousewaybillDemand.buildSavedFromService(objQueryFAColumns, objInputFIAColumns);
		
		JInputAllTransaction objJInputAllTransaction = new JInputAllTransaction();
		objJInputAllTransaction.setParam(strOperID, 
				objQueryFAColumns, 
				objIAQR.getCargoInfoResults(), 
				listWaybillPieces, 
				null);
		objJInputAllTransaction.execute();
		// ����ֵ
		List list = new ArrayList();
		list.add(objQueryFAColumns);
		// ��������Ʒ��
		objInputIAQR.setCargoInfoResults(objIAQR.getCargoInfoResults());
		objInputIAQR.setHWBResults(list);
		
		return objInputIAQR;
	}
	
	
	/**
	 * �����������ӵ�
	 * @param listWaybillpieces
	 * @param objFIAColumns
	 * @param strOperId
	 * @return
	 * @throws Exception
	 */
	private PromptUtilityCollection setWaybillcode(List listWaybillpieces,
			ForinputallColumns objFIAColumns,
			String strOperId,
			List<Object> listCargo,
			boolean isIgnoreNotice,
			boolean isMasterwaybillFromSystem,
			boolean isReinputBywebservice) throws Exception {
		HousewaybillColumns objHWBColumns = HousewaybillDemand.loadByCwcode(objFIAColumns.getCwcode());
		// ǰ̨�Ƶ�ʱ��û�д���remark�������������ȡ
		if (StringUtility.isNull(objFIAColumns.getHwremark())) {
			String strRemark = objHWBColumns.getHwhwremark();
			if (!StringUtility.isNull(strRemark) && strRemark.length() > 128)
				strRemark = strRemark.substring(0, 120);
			objFIAColumns.setHwremark(strRemark);
		}
		// ���˵�������Ϊԭ�����˵���
		// ��������ͬʱ¼һƱ��ʱ�����Ƶ�������δ�������˵��Ÿ�������ʵ�Ļ�����
		objFIAColumns.setCwcustomerewbcode(objHWBColumns.getCwcwcustomerewbcode());
		objFIAColumns.setCwewbcode(objHWBColumns.getCwcwewbcode());
		objFIAColumns.setCwserverewbcode(objHWBColumns.getCwcwserverewbcode());
		//objFIAColumns.setHWSEWBChangedByWebSign("N");
		// ����������˺�Ϊ������Ҫ�Զ���ȡ�������ӵ�
		String strChncode = objFIAColumns.getChncode_Cwspchn();
		if (StringUtility.isNull(strChncode))
			return null;
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
		/*
		if (StringUtility.isNull(objTchnChannel.getChnMasteraccount()))
			return null;
		*/
		// ���������Ϊ�����������Ҫ�Զ���ȡ�������ӵ�
		/*
		if (!StringUtility.isNull(objHWBColumns.getCctctcode()) && 
				objHWBColumns.getCctctcode().equals("BP"))
			return null;
		*/
		// ���������»�ȡ�������ӵ�
		// ȡ��ԭ�������͸��ӵ�����
		String strOldMainWaybillcode = objHWBColumns.getServerwbckbckcode();
		String strOldSubWaybillcode = objHWBColumns.getSubwbckbckcode();
		strOldMainWaybillcode = StringUtility.isNull(strOldMainWaybillcode) ? IWaybillcodeBasicData.BCK_DHLMASTER_E : strOldMainWaybillcode;		
		strOldSubWaybillcode = StringUtility.isNull(strOldSubWaybillcode) ? IWaybillcodeBasicData.BCK_DHLCHILD_E : strOldSubWaybillcode;
		
		// ���������͸��ӵ�����
		String strMainWaybillcode = IWaybillcodeBasicData.BCK_DHLMASTER_E;
		String strSubWaybillcode = IWaybillcodeBasicData.BCK_DHLCHILD_E;
		String strFromWebServiceSign = "N";
		if (objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind() != null) {
			strMainWaybillcode = objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind().getBckCode();
			strFromWebServiceSign = objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind().getBckFromwebservicesign();
		}
		if (objTchnChannel.getTdiWaybillcodekindByChnSubbillcodekind() != null)
			strSubWaybillcode = objTchnChannel.getTdiWaybillcodekindByChnSubbillcodekind().getBckCode();
		
		// ���ñ��ΪĬ��ֵ
		String strOldServerewbchangedsign = objHWBColumns.getHwhwserverewbchangedsign();
		if (StringUtility.isNull(strOldServerewbchangedsign))
			strOldServerewbchangedsign = "N";
		objFIAColumns.setServerwbckbckcode(objHWBColumns.getServerwbckbckcode());
		objFIAColumns.setHwhwserverewbchangedsign(strOldServerewbchangedsign);
		// ͨ��Webservice�������
		String strSewbchangedbywebsign = objHWBColumns.getHwhwsewbchangedbywebsign();
		if (StringUtility.isNull(strSewbchangedbywebsign))
			strSewbchangedbywebsign = "N";		
		objFIAColumns.setHWSEWBChangedByWebSign(strSewbchangedbywebsign);
		// ��ӡ����
		String strLabelPrintTimes = objHWBColumns.getHwhwlabelprinttimes();
		if (StringUtility.isNull(strLabelPrintTimes))
			strLabelPrintTimes = "0";
		objFIAColumns.setHwlabelprinttimes(Integer.parseInt(strLabelPrintTimes));
		objFIAColumns.setSubwbckbckcode(objHWBColumns.getSubwbckbckcode());
		objFIAColumns.setPaymentaccount(objHWBColumns.getHwhwpaymentaccount());
		objFIAColumns.setMasteraccount(objHWBColumns.getEraccount());
		
		strMainWaybillcode = StringUtility.isNull(strMainWaybillcode) ? IWaybillcodeBasicData.BCK_DHLMASTER_E : strMainWaybillcode;
		strSubWaybillcode = StringUtility.isNull(strSubWaybillcode) ? IWaybillcodeBasicData.BCK_DHLCHILD_E : strSubWaybillcode;
		strFromWebServiceSign = StringUtility.isNull(strFromWebServiceSign) ? "N" : strFromWebServiceSign;
		// �µ���������ΪDHL��������ȡ����
		if (strMainWaybillcode.equals(IWaybillcodeBasicData.BCK_DHLMASTER_E))
			return null;
		// ��ȡ����		
		
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		Corewaybillcode objCorewaybillcode = new Corewaybillcode();
		// �ж��Ƿ�ԭ�����߻�
		// ���������DHL���������ض�Ҫ�������������DHL���������ҿ��԰�ԭ�����߻������ϵͳ���ӵ�
		// Ҳ����˵����������˹������Ҳ����DHL���������ֱ�Ӱ�ԭ���ų���
		AWaybillcode objWaybillcode = new DHLCoreWaybillcode();
		boolean isCorrectLabelcode = objWaybillcode.checkLabelcode(objFIAColumns.getCwserverewbcode()).canGo(false);
		boolean isSettingSOByOriginEWB = false;
		ProductruleDemand objProductruleDemand = new ProductruleDemand();
		String strRecordDate = objFIAColumns.getHwrecorddate();
		if (StringUtility.isNull(strRecordDate))
			strRecordDate = DateFormatUtility.getStandardSysdate();
		if (isCorrectLabelcode)
			isSettingSOByOriginEWB = objProductruleDemand.isSignOutByOriginEWB(strRecordDate, 
					strRecordDate, 
					objHWBColumns.getPkpkcode(), 
					objHWBColumns.getCcococode());
		// ͨ��webservice����������ӵ�
		if (!isMasterwaybillFromSystem && 
				strFromWebServiceSign.equals("Y") &&
				!(isCorrectLabelcode && isSettingSOByOriginEWB)) {
		// if (strFromWebServiceSign.equals(strFromWebServiceSign)) {
			try {
				// ������ʾ�����ʶDHL��վ����δ�ɹ�
				if (isIgnoreNotice) return null;
				// ��ǰ̨���������ϴ�����
				if (isReinputBywebservice)
					objHWBColumns.setHwhwserverewbchangedsign("N");
				objPUCollection = objCorewaybillcode.setWaybillcodeByService(objFIAColumns, 
						listWaybillpieces, 
						listCargo, 
						objHWBColumns, 
						strMainWaybillcode, 
						strOldMainWaybillcode,
						strSubWaybillcode,
						objTchnChannel.getChnMasteraccount());
			} catch (Exception ex) {
				ex.printStackTrace();		
				objPUCollection.add("H_001", ex.getMessage(), "Input.setWaybillcode");
			}			
		} else {		
			// ͨ�����ݹ���������������ӵ�
			// ����ǿ�ƻ���
			if (isMasterwaybillFromSystem &&
					(SystempropertyDemand.getEnterprise().equals("WC") ||
							SystempropertyDemand.getEnterprise().startsWith("SBD"))) {
				objHWBColumns.setHwhwserverewbchangedsign("N");
			}
			if (isMasterwaybillFromSystem) {
				TdiWaybillcodekind objTdiWaybillcodekind = TdiWaybillcodekindDC.loadByKey(strMainWaybillcode);
				if (!objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BCK_DHLMASTER)) {
					objPUCollection.add("E_001", "��ѡ�������鲻��ǿ�ƻ���", "Input.setWaybillcode");
					return objPUCollection;
				}
			}
			objPUCollection = objCorewaybillcode.setWaybillcodeBySystem(objFIAColumns, 	
					listWaybillpieces, 	
					objHWBColumns, 
					strMainWaybillcode, 
					strOldMainWaybillcode, 
					strSubWaybillcode, 
					strOldSubWaybillcode,
					strOperId,
					isCorrectLabelcode,
					isSettingSOByOriginEWB);
		}
		return objPUCollection;
	}
}
