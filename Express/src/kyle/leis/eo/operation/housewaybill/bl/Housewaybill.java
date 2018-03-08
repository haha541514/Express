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
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.payable.bl.Payable;
import kyle.leis.eo.billing.receivable.bl.Receivable;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.eo.customerservice.issue.da.IssueColumns;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillDemand;
import kyle.leis.eo.operation.corewaybill.blx.CoreWayBillCheck;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybill.tp.ModifyCheckweightBWTrans;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.tp.ModifyPiecesTrans;
import kyle.leis.eo.operation.housewaybill.tp.SaveCNDHLImportDataTrans;
import kyle.leis.eo.operation.housewaybill.tp.SaveHousewaybillTrans;
import kyle.leis.eo.operation.housewaybill.tp.SaveWaybillTransaction;
import kyle.leis.eo.operation.specialtype.bl.Specialtype;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.businesslog.bl.Businesslog;

public class Housewaybill {
	
	public String modifyPieces(String strCwcode,
			List listCorewaybillpieces,
			String strOperId) throws Exception {
		HousewaybillColumns objOriginHwColumns = HousewaybillDemand.loadByCwcode(strCwcode);
		if (objOriginHwColumns == null || 
				listCorewaybillpieces == null || 
				listCorewaybillpieces.size() < 1) 
			return "";
		String strOriginChargeweight = objOriginHwColumns.getCwcwchargeweight();
		objOriginHwColumns.setCwcwgrossweight(new BigDecimal(CorewaybillpiecesDemand.sumGrossweight(listCorewaybillpieces)));
		boolean isChangeServerHawb = false;
		if (Integer.parseInt(objOriginHwColumns.getCwcwpieces()) != 
			listCorewaybillpieces.size())
			isChangeServerHawb = true;
		// ���»�üƷ�����
		ChargeweightParameter objCWParameter = CorewaybillDemand.transferToCWParameter(objOriginHwColumns, 
				listCorewaybillpieces);
		Chargeweight objChargeweight = new Chargeweight();
		ChargeweightResult objCWResult = objChargeweight.calculate(objCWParameter);
		objOriginHwColumns.setCwcwchargeweight(new BigDecimal(objCWResult.getChargeweight()));
		// ����Fedex��ʵ��������������Ҫ���¼���ʵ��
		if (!StringUtility.isNull(objCWResult.getGrossweight()))
			objOriginHwColumns.setCwcwgrossweight(new BigDecimal(objCWResult.getGrossweight()));
		if (new BigDecimal(strOriginChargeweight).compareTo(new BigDecimal(objCWResult.getChargeweight())) != 0)
			isChangeServerHawb = true;
		// ����
		ModifyPiecesTrans objModifyPiecesTrans = new ModifyPiecesTrans();
		objModifyPiecesTrans.setParam(objOriginHwColumns, listCorewaybillpieces, 
				strOperId, isChangeServerHawb);
		objModifyPiecesTrans.execute();
		
		return strCwcode;
	}
	
	public SavedResultUtility modify(HousewaybillColumns objHwColumns,
			List listCorewaybillpieces,
			List listIssueColumns,
			String strOperId,
			String strModifyRemark,
			boolean isIgnoreNotice) throws Exception {
		SavedResultUtility objSRUtility = new SavedResultUtility();
		buildSavedColumns(objHwColumns, listCorewaybillpieces);
		CoreWayBillCheck objCoreWayBillCheck = new CoreWayBillCheck();
		PromptUtilityCollection objPUCollection = objCoreWayBillCheck.checkBaseCorewaybill(objHwColumns,
				listCorewaybillpieces);
		if (StringUtility.isNull(objHwColumns.getHwcwcode()))
			objPUCollection.add("E_SIGNIN_001", 
					"�����ڵ��˵�", 
					"Housewaybill.modify");
		// ���ͻ��˵��Ƿ��ظ�
		/*
		PromptUtilityCollection objCheckEwbcode = objCoreWayBillCheck.checkCustomerEwbcode(objHwColumns.getCwcwcustomerewbcode(), 
				objHwColumns.getHwcwcode());
		objPUCollection.addAll(objCheckEwbcode);
		*/
		/* ��ͬ��ҵ��ģʽ���޸Ĳ�ͬ
		if (ReceivableDemand.isExistsUnModifyFee(objHwColumns.getHwcwcode()))
			objPUCollection.add("E_SIGNIN_008", 
					"��Ʊ���Ѿ����˻�������������޸ķ���", 
					"Housewaybill.modify");
		*/
		SignIn objSignIn = new SignIn();
		// �ջ���ƷЧ��
		String strErrorInfo = objSignIn.checkRestrictByPK(objHwColumns,
				listCorewaybillpieces);
		if (!StringUtility.isNull(strErrorInfo)) {
			objPUCollection = new PromptUtilityCollection();
			objPUCollection.add("E_MODIFY_001", 
					strErrorInfo, 
					"SignIn.save");
		}	
		boolean isRecalc = true;
		// �����̶������޸��ջ�����
		if (!StringUtility.isNull(strModifyRemark) && 
				strModifyRemark.equals("SRM")) {
			strModifyRemark = "";
			isRecalc = false;
		}
		if (objPUCollection.canGo(isIgnoreNotice)) {
			// ����
			SaveWaybillTransaction objSignInTrans = new SaveWaybillTransaction();
			objSignInTrans.setSignInParam(objHwColumns, 
					listCorewaybillpieces, 
					listIssueColumns, 
					strOperId);
			objSignInTrans.execute();
			Long lNewCwcode = objSignInTrans.getNewCwcode();
			objSRUtility.setColumns(HousewaybillDemand.loadByCwcode(String.valueOf(lNewCwcode)));
			// ��¼��ע����
			Businesslog objBusinesslog = new Businesslog();
			objBusinesslog.addBusinessLog(objHwColumns.getHwcwcode(), 
					strOperId, 
					strModifyRemark);
			//��¼����������������
			Specialtype objSpecialtype = new Specialtype();
			objSpecialtype.addOverLengthSpecialtype(listCorewaybillpieces,strOperId,String.valueOf(lNewCwcode));
			// �Ʒ�
			if (isRecalc) {
				AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(String.valueOf(lNewCwcode),
						IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
						strModifyRemark,
						true);			
				objAFCThread.start();
			}
		}
		objSRUtility.setPromptUtilityCollection(objPUCollection);
		return objSRUtility;
	}
	
	private void buildSavedColumns(HousewaybillColumns objHwColumns,
			List listCorewaybillpieces) 
	throws Exception {
		HousewaybillColumns objOriginHwColumns = HousewaybillDemand.loadByCwcode(objHwColumns.getHwcwcode());
		// �жϷ������˵����Ƿ�һ�£������һ������Ϊ�޸ķ������˵�
		boolean isFlushServerchannel = true;
		if (!objOriginHwColumns.getCwcwserverewbcode().equals(objHwColumns.getCwcwserverewbcode()))
			isFlushServerchannel = false;
		// ��Ѽ�����շ�������
		if (objOriginHwColumns.getPmpmcode().equals("AFR"))
			isFlushServerchannel = false;		
		if (StringUtility.isNull(objHwColumns.getEeeecode()))
			objHwColumns.setEeeecode(objOriginHwColumns.getEeeecode());		
		// ��üƷ�����
		ChargeweightParameter objCWParameter = CorewaybillDemand.transferToCWParameter(objHwColumns, 
				listCorewaybillpieces);
		if (StringUtility.isNull(objCWParameter.getSearchDate()))
			objCWParameter.setSearchDate(objOriginHwColumns.getAbwadddate());
		if (StringUtility.isNull(objCWParameter.getCocode()))
			objCWParameter.setCocode(objOriginHwColumns.getCcococode());
		if (StringUtility.isNull(objCWParameter.getPostcode()))
			objCWParameter.setPostcode(objOriginHwColumns.getHwhwconsigneepostcode());
		if (!StringUtility.isNull(objOriginHwColumns.getDdtdtcode()))
			objCWParameter.setDtcode(objOriginHwColumns.getDdtdtcode());
		// if (!StringUtility.isNull(objOriginHwColumns.getAbwadddate()))
		
		
		Chargeweight objChargeweight = new Chargeweight();
		ChargeweightResult objCWResult = objChargeweight.calculate(objCWParameter);
		objHwColumns.setCwcwchargeweight(new BigDecimal(objCWResult.getChargeweight()));
		// ����Fedex��ʵ��������������Ҫ���¼���ʵ��
		if (!StringUtility.isNull(objCWResult.getGrossweight()))
			objHwColumns.setCwcwgrossweight(new BigDecimal(objCWResult.getGrossweight()));
		
		objHwColumns.setCwcwvolumerate(Integer.parseInt(objCWResult.getVolumeRate()));
		objHwColumns.setAbwadddate(DateFormatUtility.getStandardDate(objOriginHwColumns.getAbwadddate()));
		objHwColumns.setCcococode(objOriginHwColumns.getCcococode());
		// ���ù�˾�˵��š��������˵���
		objHwColumns.setCwcwcustomerewbcode(objHwColumns.getCwcwcustomerewbcode());
		objHwColumns.setCwcwewbcode(objHwColumns.getCwcwewbcode());
		objHwColumns.setCwcwserverewbcode(objHwColumns.getCwcwserverewbcode());
		// ������ת����������������	
		objHwColumns.setCwcwtransferchargeweight(new BigDecimal(objOriginHwColumns.getCwcwtransferchargeweight()));
		objHwColumns.setCwcwserverchargeweight(new BigDecimal(objHwColumns.getCwcwserverchargeweight()));
		objHwColumns.setCwcwtransfergrossweight(new BigDecimal(objOriginHwColumns.getCwcwtransfergrossweight()));
		objHwColumns.setCwcwtransferpieces(new BigDecimal(objOriginHwColumns.getCwcwtransferpieces()));
		
		// δ��������շ�������
		if (StringUtility.isNull(objOriginHwColumns.getDbwbwcode()) && isFlushServerchannel)
			objHwColumns.setSchnchncode("null");
	}
	
	/**
	 * ��ӡLabel
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void printLabel(String strCwcode,
			String strLabelPrintremark,
			String strOperId) throws Exception {
		SaveHousewaybillTrans objSHWBTrans = new SaveHousewaybillTrans();
		objSHWBTrans.setPrintlabelParam(strCwcode, strOperId);
		objSHWBTrans.execute();
		if (StringUtility.isNull(strLabelPrintremark))
			strLabelPrintremark = "��ӡ��ǩ";
		// ��¼��ע
		Businesslog objBusinesslog = new Businesslog();
		objBusinesslog.addBusinessLog(strCwcode, 	
				strOperId, 
				strLabelPrintremark);				
		
	}
	
	/**
	 * �޸ķ�������
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void modifyServerChannel(String strCwcode,
			String strServerChannel,
			String strOperId) throws Exception {
		SaveHousewaybillTrans objSHWBTrans = new SaveHousewaybillTrans();
		objSHWBTrans.setModifyServerChannel(strCwcode, 
				strServerChannel, 
				strOperId);
		objSHWBTrans.execute();
	}
	
	/**
	 * ����
	 * @param strCwcode
	 * @param strOperId
	 * @return
	 * @throws Exception
	 */
	public PromptUtilityCollection eliminate(String strCwcode, 
			String strOperId) throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		if (ReceivableDemand.isExistsUnModifyFee(strCwcode))
			objPUCollection.add("E_SIGNIN_001", 
					"��Ʊ���Ѿ����˻����������������", 
					"Housewaybill.eliminate");
		if (objPUCollection.canGo(true)) {
			SaveHousewaybillTrans objSHWBTrans = new SaveHousewaybillTrans();
			objSHWBTrans.setEliminateParam(strCwcode, strOperId);
			objSHWBTrans.execute();
			// ɾ��Ӧ�շ���
			Receivable objReceivable = new Receivable();
			objReceivable.deleteAll(strCwcode, "0");
			// ɾ��Ӧ������
			Payable objPayable = new Payable();
			objPayable.deleteAll(strCwcode, "0");			
		}
		return objPUCollection;
	}	
	
	/**
	 * ��������
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void undoSignout(String strCwcode, 
			String strOperId,
			String strRemark,
			boolean bResetChangeEWBSign) throws Exception {
		SaveHousewaybillTrans objSHTrans = new SaveHousewaybillTrans();
		objSHTrans.setUndoSignOutParam(strCwcode, strOperId, bResetChangeEWBSign);
		objSHTrans.execute();
		// ��¼��ע����
		Businesslog objBusinesslog = new Businesslog();
		objBusinesslog.addBusinessLog(strCwcode, 
				strOperId, 
				strRemark);		
	}
	
	/**
	 * ��ӡ��Ʊ
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void printCargo(String strCwcode, 
			String strOperId) throws Exception {
		SaveHousewaybillTrans objSHWBTrans = new SaveHousewaybillTrans();
		objSHWBTrans.setPrintCargoprinttimesParam(strCwcode, strOperId);
		objSHWBTrans.execute();
	}
	
	/**
	 * �����˼�
	 * @param strCwcode	 
	 * @throws Exception
	 */
	public void undoBackprice(String strCwcode,
			String strOperId) throws Exception {
		SaveHousewaybillTrans objSHTrans = new SaveHousewaybillTrans();
		objSHTrans.setUndoBackpriceParam(strCwcode,
				strOperId);
		objSHTrans.execute();
		
		// ��¼��ע����
		Businesslog objBusinesslog = new Businesslog();
		objBusinesslog.addBusinessLog(strCwcode, 
				strOperId, 
				"�����˼�");
		// �Զ��Ʒ�
		AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(strCwcode,
				"",
				false);			
		objAFCThread.start();   		
		
	}
	
	/**
	 * �������Ѵ�ӡ״̬
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void undoToCustomerPrint(String strCwcode,
			String strOperId) throws Exception {
		SaveHousewaybillTrans objSHTrans = new SaveHousewaybillTrans();
		objSHTrans.setUndoToCustomerPrintParam(strCwcode,
				strOperId);
		objSHTrans.execute();	
	}	
	
	
	/**
	 * �˲�����
	 * @param strCusWeight
	 * @param strCheckWeight
	 * @param strOperId
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked"})
	public String checkWeight(HousewaybillColumns objHwColumns,
			List listCorewaybillpieces,
			String strEecode,
			String strOperId,
			boolean isHold,
			String strWeightCheckBWCode) throws Exception {
		// ֻ��¼�켣����ֻ����˲�������
		// ������������
		String strNewArrivalBwcode = BatchWayBillDemand.createLatestUncompleteABW(objHwColumns.getCcococode(), 
				"",
				strEecode,
				DateFormatUtility.getStandardSysdate(),
				strOperId);
		if (listCorewaybillpieces == null || listCorewaybillpieces.size() == 0) { 
			ModifyCheckweightBWTrans objMCBWTrans = new ModifyCheckweightBWTrans();	
			objMCBWTrans.setParam(objHwColumns.getHwcwcode(), 
					strWeightCheckBWCode, 
					strNewArrivalBwcode,
					strOperId);
			objMCBWTrans.execute();
			return null;
		}
		// ���±����µĵ�������
		objHwColumns.setAbwbwcode(Long.parseLong(strNewArrivalBwcode));
		// �ͻ�����
		BigDecimal iCusWeight = new BigDecimal(objHwColumns.getCwcwcustomerchargeweight());
		List listIssueColumns = new ArrayList();
		//�ۼ����
		String strFastenersmark = "N";
		//�������
		ChargeweightParameter objCWParameter = CorewaybillDemand.transferToCWParameter(objHwColumns, 
				listCorewaybillpieces);
		Chargeweight objChargeweight = new Chargeweight();
		ChargeweightResult objCWResult = objChargeweight.calculate(objCWParameter);
		BigDecimal iCheckWeight = new BigDecimal(objCWResult.getChargeweight());
		BigDecimal iGrossWeight = new BigDecimal(objHwColumns.getCwcwgrossweight());
		//�ͻ����������������
		if(iCusWeight.compareTo(iCheckWeight) == 0) {
			objHwColumns.setCwcwchargeweight(iCheckWeight);
			// objHwColumns.setCwcwgrossweight(iCusWeight);			
			objHwColumns.setHwhwweightcheckkind("P");
		}else{
			objHwColumns.setHwhwweightcheckkind("N");
			//����ϵͳ����Ϊ�ͻ�����
			// objHwColumns.setCwcwchargeweight(iCusWeight);
			// objHwColumns.setCwcwgrossweight(iCusWeight);
			//����ԭ���м�ʵ���Լ��������Ϣ
			List listCWBP = CorewaybillpiecesDemand.load(objHwColumns.getHwcwcode());
			Businesslog objBusinesslog = new Businesslog();
			objBusinesslog.addBusinessLog(objHwColumns.getHwcwcode(),strOperId,listCWBP);
			
			strFastenersmark = "N";
			//�޸�ϵͳ������Ϊ�˲�����
			objHwColumns.setCwcwchargeweight(iCheckWeight);
			objHwColumns.setCwcwgrossweight(iGrossWeight);
			// �������	
			if (!SystempropertyDemand.getEnterprise().startsWith("SBD")) {
				objHwColumns.setSchnchncode("null");
			}
			//��¼�������
			if (isHold) {
				IssueColumns objIssueColumns = new IssueColumns();
				objIssueColumns.setCwcwcode(Long.parseLong(objHwColumns.getHwcwcode()));
				objIssueColumns.setIsutisutcode("110");
				objIssueColumns.setIhsihscode("CH");
				objIssueColumns.setIsuiscontent(objHwColumns.getCwcwcustomerewbcode()+"���˵�����˾�ջ�����Ϊ"+iCheckWeight+"����˾����Ϊ"+iCusWeight+"��������������ȷ���������졣лл��");
				listIssueColumns.add(objIssueColumns);
				strFastenersmark = "Y";
			}
		}
		//����˲���Ϣ
		objHwColumns.setHwhwweightcheckdate(DateFormatUtility.getSysdate());
		objHwColumns.setHwhwopidweightcheck(Long.parseLong(strOperId));
		//����
		SaveWaybillTransaction objSignInTrans = new SaveWaybillTransaction();
		objSignInTrans.setCheckweight(objHwColumns, 
				listCorewaybillpieces, 
				listIssueColumns,
				strWeightCheckBWCode,
				strOperId);
		objSignInTrans.execute();
		// ��������
		Specialtype objSpecialtype = new Specialtype();
		objSpecialtype.addOverLengthSpecialtype(listCorewaybillpieces,
				strOperId,
				objHwColumns.getHwcwcode());		
		
        String strResult = objHwColumns.getHwhwweightcheckkind().concat(strFastenersmark);
        // �������������¼Ʒ�
        //if (objHwColumns.getHwhwweightcheckkind().equals("N")) {
			// �Զ��Ʒ�
			AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(objHwColumns.getHwcwcode(),
					IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
					true);			
			objAFCThread.start();        	
        //}
		return strResult;
	}
	
	public String saveCNDHLImportData(List listDHLConnectImport,
			String strOperID) {
		try {
			SaveCNDHLImportDataTrans objSCIDTrans = new SaveCNDHLImportDataTrans();
			objSCIDTrans.setParam(listDHLConnectImport, strOperID);
			objSCIDTrans.execute();
			return "";
		} catch (Exception ex) {
			ex.printStackTrace();
			return ex.getMessage();
		}
	}
}
