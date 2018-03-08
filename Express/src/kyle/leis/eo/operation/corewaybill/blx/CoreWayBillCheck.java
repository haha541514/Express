package kyle.leis.eo.operation.corewaybill.blx;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.DateUtility;
import kyle.common.util.jlang.IntervalTime;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.receivable.da.SumcorreceivableColumns;
import kyle.leis.eo.billing.receivable.da.SumcorreceivableQuery;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.eo.customerservice.issue.dax.IIssueBasicData;
import kyle.leis.eo.finance.dunning.bl.Dunning;
import kyle.leis.eo.finance.dunning.da.FinancestatisticsColumns;
import kyle.leis.eo.finance.dunning.dax.DunningDemand;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillDemand;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillColumns;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillQuery;
import kyle.leis.eo.operation.corewaybill.dax.ICorewaybillBasicData;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.es.company.customer.da.CusoperationColumns;
import kyle.leis.es.company.customer.da.CusoperationCondition;
import kyle.leis.es.company.customer.dax.CustomerDemand;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.businesslog.bl.Businesslog;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TcoCustomerDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.fs.waybillcode.dax.IWaybillcodeBasicData;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TcoCustomer;
import kyle.leis.hi.TdiProductkind;

public class CoreWayBillCheck {
	
	private boolean m_isArrearAllowSignout = false;
	private StringBuffer m_sbLogInfo;
	
	
	public PromptUtilityCollection checkSignIn(HousewaybillColumns objHwColumns,
			List listCorewaybillpieces) throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		if (StringUtility.isNull(objHwColumns.getAbwbwcode()))
			objPUCollection.add("E_SIGNIN_001", 
					"ǩ�뱣��ʱ�����ܵ�����Ϊ��", 
					"CoreWayBillCheck.check");
		if (StringUtility.isNull(objHwColumns.getCcococode()))
			objPUCollection.add("E_SIGNIN_002", 
					"ǩ�뱣��ʱ�����̲���Ϊ��", 
					"CoreWayBillCheck.check");
		if (StringUtility.isNull(objHwColumns.getCwcwcustomerewbcode()))
			objPUCollection.add("E_SIGNIN_003", 
					"�ͻ��˵��Ų���Ϊ��", 
					"CoreWayBillCheck.check");
		// Ч�鵥���Ƿ��ظ�
		if (StringUtility.isNull(objHwColumns.getHwcwcode())) {
			checkRepeatWaybill(objHwColumns, objPUCollection);
		}
		
		PromptUtilityCollection objPUBaseCollection = checkBaseCorewaybill(objHwColumns, 
				listCorewaybillpieces);
		objPUCollection.addAll(objPUBaseCollection);
		
		return objPUCollection;
	}
	
	public void checkFinanceRestrict(String strCocode,
			String strOtcode,
			boolean isCheckMaxReceivable,
			PromptUtilityCollection objPU) throws Exception {
		// �������߻���
		if (isCheckMaxReceivable)
			checkMaxReceivable(strCocode, objPU);
		// �Ƿ�Ϊ��ҪЧ���ȵĿͻ�
		CusoperationCondition objCusperationCondition = new CusoperationCondition();
		objCusperationCondition.setCmcocode(strCocode);
		objCusperationCondition.setUseCacheSign(true);
		List listResults = CustomerDemand.queryCusSORestrict(objCusperationCondition);
		if (listResults == null || listResults.size() < 1)
			return;
		boolean isExists = false;
		for (int i = 0; i < listResults.size(); i++) {
			CusoperationColumns objCusoperationColumns = (CusoperationColumns)listResults.get(i);
			if (objCusoperationColumns.getOtotcode().equals(strOtcode)) {
				isExists = true;
				break;
			}
		}
		if (!isExists) return;
		
		Dunning objDunning = new Dunning();
		String strCreditBalance = objDunning.getCreditbalance(strCocode);
		BigDecimal objCreditBalance = new BigDecimal(strCreditBalance);
		if (objCreditBalance.compareTo(new BigDecimal("0")) >= 0)
			return;
		objPU.add("E_CFR_001", "�ͻ�Ƿ�ѣ��벹����ú���ִ�иò���", "CoreWayBillCheck.checkFinanceRestrict");
	}
	
	/**
	 * �������߻���
	 * @param strCocode
	 * @param objPU
	 * @throws Exception
	 */
	private void checkMaxReceivable(String strCocode,
			PromptUtilityCollection objPU) throws Exception {
		TcoCustomer objTcoCustomer = TcoCustomerDC.loadByKey(strCocode);
		if (objTcoCustomer == null) return;
		if (!objTcoCustomer.getTcoCorporation().getTdiCorporationstatus().getCsCode().equals("R")) {
			objPU.add("E_CFR_003", "�ÿͻ���״̬��Ϊ����״̬���޷��߻�", "CoreWayBillCheck.checkMaxReceivable");
		}
		if (StringUtility.isNull(objTcoCustomer.getCmMaxrttype()) ||
				objTcoCustomer.getCmMaxreceivabletotal() == null ||
				objTcoCustomer.getCmMaxreceivabletotal().compareTo(new BigDecimal("0")) <= 0)
			return;
		String strMaxrttype = objTcoCustomer.getCmMaxrttype();
		if (StringUtility.isNull(strMaxrttype)) return;
		
		SumcorreceivableQuery objSRQ = new SumcorreceivableQuery();
		objSRQ.setCocode(strCocode);
		if (strMaxrttype.equals("M")) {
			String strBeginDay = DateFormatUtility.getStandardSysdate().substring(0, 7) + "-01 00:00:00";
			String strEndDay = DateFormatUtility.getStandardDate(DateUtility.getLastDayOfMonth(DateFormatUtility.getSysdate()));
			strEndDay = strEndDay.substring(0, 10) + " 23:59:59";
			objSRQ.setStartrvoccurdate(strBeginDay);
			objSRQ.setEndrvoccurdate(strEndDay);
			// objSRQ.setRvoccurdate(DateFormatUtility.getStandardSysdate().substring(0, 7));
		} else if (strMaxrttype.equals("Y")) {
			String strBeginDay = DateFormatUtility.getStandardSysdate().substring(0, 4) + "-01-01 00:00:00";
			String strEndDay = DateFormatUtility.getStandardDate(DateUtility.getLastDayOfYear(DateFormatUtility.getSysdate()));
			strEndDay = strEndDay.substring(0, 10) + " 23:59:59";
			objSRQ.setStartrvoccurdate(strBeginDay);
			objSRQ.setEndrvoccurdate(strEndDay);
			//objSRQ.setYrvoccurdate(DateFormatUtility.getStandardSysdate().substring(0, 4));
		} else if (strMaxrttype.equals("D")) {
			String strBeginDay = DateFormatUtility.getStandardSysdate().substring(0, 10) + " 00:00:00";
			String strEndDay = DateFormatUtility.getStandardSysdate().substring(0, 10) + " 23:59:59";
			objSRQ.setStartrvoccurdate(strBeginDay);
			objSRQ.setEndrvoccurdate(strEndDay);
			//objSRQ.setDrvoccurdate(DateFormatUtility.getStandardSysdate().substring(0, 10));
		}
		List listResults = objSRQ.getResults();
		if (listResults == null || listResults.size() < 1)
			return;
		SumcorreceivableColumns objSCRColumns = (SumcorreceivableColumns)listResults.get(0);
		String strRvtotal = objSCRColumns.getSumrvtotal();
		if (StringUtility.isNull(strRvtotal))
			strRvtotal = "0";
		if (objTcoCustomer.getCmMaxreceivabletotal().compareTo(new BigDecimal(strRvtotal)) <= 0) {
			objPU.add("E_CFR_002", "�ÿͻ��߻����Ѿ������������", "CoreWayBillCheck.checkMaxReceivable");
		}
	}
	
	private void checkRepeatWaybill(HousewaybillColumns objHwColumns,
			PromptUtilityCollection objPUCollection) throws Exception {
		// Ч�鵥���Ƿ��ظ�
		String strCustomerEwbcode = objHwColumns.getCwcwcustomerewbcode();
		List objList = HousewaybillDemand.loadNormalCollection(strCustomerEwbcode, 
				ICorewaybillBasicData.EWBCODE_TYPE_CUSTOMER);
		int iPreAlertTimes = 0;
		if (objList != null && objList.size() > 0) {
			for (int i = 0; i < objList.size(); i++) {
				HousewaybillColumns objHWBColumns = (HousewaybillColumns)objList.get(i);
				if (!objHWBColumns.getCwscwscode().equals("PR") &&
						!objHWBColumns.getCwscwscode().equals("CHP")) {
					objPUCollection.add("E_SIGNIN_008", 
							"�˵������ظ�", 
							"CoreWayBillCheck.check");
					break;
				} else {
					iPreAlertTimes++;
					if (iPreAlertTimes > 1) {
						objPUCollection.add("E_SIGNIN_008", 
								"�˵������ظ�", 
								"CoreWayBillCheck.check");
						break;
					}
					objHwColumns.setHwcwcode(Long.parseLong(objHWBColumns.getHwcwcode()));
				}
			}
		}		
	}
	
	public String getSOCheckLog() {
		return m_sbLogInfo.toString();
	}
	
	public PromptUtilityCollection checkBaseCorewaybill(HousewaybillColumns objHwColumns,
			List listCorewaybillpieces) throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();		
		if (StringUtility.isNull(objHwColumns.getCwcwchargeweight()))
			objPUCollection.add("E_SIGNIN_004", 
					"�ջ���������Ϊ��", 
					"CoreWayBillCheck.check");
		if (StringUtility.isNull(objHwColumns.getCwcwgrossweight()))
			objPUCollection.add("E_SIGNIN_005", 
					"�ջ�ʵ�ز���Ϊ��", 
					"CoreWayBillCheck.check");
		if (StringUtility.isNull(objHwColumns.getCwcwpieces()))
			objPUCollection.add("E_SIGNIN_006", 
					"�ջ���������Ϊ��", 
					"CoreWayBillCheck.check");
		if (listCorewaybillpieces == null || listCorewaybillpieces.size() < 1)
			objPUCollection.add("E_SIGNIN_007", 
					"�ջ������б���Ϊ��", 
					"CoreWayBillCheck.check");		
		return objPUCollection;
	}
	
	public PromptUtilityCollection checkSignOut(HousewaybillColumns objHwbColumns,
			String strBwcodeDeparture,
			List listCWPieces,
			String strOperId,
			boolean isArrearAllowSignout) throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();		
		String strCwcode = objHwbColumns.getHwcwcode();
		String strHasBwcodeDeparture = objHwbColumns.getDbwbwcode();
		String strIhscode = objHwbColumns.getIhsihscode();
		String strPmcode = objHwbColumns.getPmpmcode();
		// �Ƿ��Ѿ�ǩ��				
		if (!StringUtility.isNull(strHasBwcodeDeparture) && "SO".equals(objHwbColumns.getCwscwscode()))
			objPUCollection.add("E_SIGNOUT_002", 
					"�ÿ���Ѿ�ǩ��", 
					"CoreWayBillCheck.checkSignOut");
		if (!SystempropertyDemand.getEnterprise().startsWith("SLYIM") &&
				!objHwbColumns.getCwscwscode().equals(ICorewaybillBasicData.COREWAYBILL_STATUS_INPUT))
			objPUCollection.add("E_SIGNOUT_001", 
					"���״̬��Ϊ¼��״̬����ǩ��", 
					"CoreWayBillCheck.checkSignOut");
		// ��Ʒ����Ϊ��
		if (StringUtility.isNull(objHwbColumns.getPkpkcode()))
			objPUCollection.add("E_SIGNOUT_003", 
					"���۲�Ʒ����Ϊ��", 
					"CoreWayBillCheck.checkSignOut");			
		// ������������Ϊ��
		if (StringUtility.isNull(objHwbColumns.getSchnchncode()))
			objPUCollection.add("E_SIGNOUT_004", 
					"������������Ϊ��", 
					"CoreWayBillCheck.checkSignOut");
		
		IntervalTime objIT = new IntervalTime("������������Ƿ���˵�����һ��");
		
		// ����������������������ķ�������һ��
		BatchwaybillColumns objBwColumns = BatchWayBillDemand.load(strBwcodeDeparture);
		if (objBwColumns == null)
			objPUCollection.add("E_SIGNOUT_005", 
					"�����ڵĳ����ܵ�", 
					"CoreWayBillCheck.checkSignOut");
		if (StringUtility.isNull(objHwbColumns.getSchnchncode()) ||
				!objHwbColumns.getSchnchncode().equals(objBwColumns.getChnchncode()))
			objPUCollection.add("E_SIGNOUT_006", 
					"����ķ��������������ķ���������һ��", 
					"CoreWayBillCheck.checkSignOut");
		if (m_sbLogInfo == null)
			m_sbLogInfo = new StringBuffer();
		m_sbLogInfo.append(objIT.toString() + "\r\n");
		
		objIT = new IntervalTime("�����������");
		// �����DHL������뻻������ӡ��ǩ
		String strServerChannel = objHwbColumns.getSchnchncode();
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strServerChannel);		
		if (objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind() != null) {
			String strBckGroupcode = objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind().getBckGroupcode();
			if (strBckGroupcode.equals(IWaybillcodeBasicData.BCK_DHLMASTER)) {
				String strChangeServerEWBCodeSign = objHwbColumns.getHwhwserverewbchangedsign();
				String strLabelprintTimes = objHwbColumns.getHwhwlabelprinttimes();
				if (StringUtility.isNull(strLabelprintTimes))
					strLabelprintTimes = "0";
				// int iLabelprintTimes = Integer.parseInt(strLabelprintTimes);
				if (strChangeServerEWBCodeSign.equals("N"))
					objPUCollection.add("E_SIGNOUT_013", 
							"��ѡ�����ĵ�������Ҫ�󻻵����뻻�����ٳ���", 
							"CoreWayBillCheck.checkSignOut");
				/*
				if (iLabelprintTimes < 1)
					objPUCollection.add("H_SIGNOUT_001", 
							"��ѡ�����ĵ�������Ҫ���ӡ��ǩ����ȷ���Ƿ���Ҫ��ӡ��ǩ��ѡ�������ȡ��", 
							"CoreWayBillCheck.checkSignOut");					
			    */
			}
		}
		m_sbLogInfo.append(objIT.toString() + "\r\n");
		
		// Ч���������
		if (StringUtility.isNull(objHwbColumns.getCtctcode()))
			objPUCollection.add("E_SIGNOUT_007", 
					"�������Ͳ���Ϊ��", 
					"CoreWayBillCheck.checkSignOut");
		/*
		if (objHwbColumns.getCtctcode().equals(ICorewaybillBasicData.CARGOTYPE_WPX)) {
			// ��������������ʽ��Ʊ
			List objList = CargoInfoDemand.queryByCwCode(strCwcode);
			if (objList == null || objList.size() < 1)
				objPUCollection.add("E_SIGNOUT_008", 
						"��������Ҫ������ʽ��Ʊ", 
						"CoreWayBillCheck.checkSignOut");
		}*/
		
		objIT = new IntervalTime("������Ϣ");
		
		List objList = CorewaybillpiecesDemand.load(strCwcode);
		if (objList == null || objList.size() < 1)
			objPUCollection.add("E_SIGNOUT_009", 
					"����Ҫ�����м�����ϸ��Ϣ", 
					"CoreWayBillCheck.checkSignOut");
		if (!objHwbColumns.getCwcwtransferpieces().equals(String.valueOf(objList.size())))
			objPUCollection.add("E_SIGNOUT_010", 
					"�ӵ�������������һ��", 
					"CoreWayBillCheck.checkSignOut");
		// �Ƿ��Ѿ��ۼ�
		if (!StringUtility.isNull(strIhscode) && 
				(strIhscode.equals(IIssueBasicData.HOLD_STATUS_CONFIRM) ||
						strIhscode.equals(IIssueBasicData.HOLD_STATUS_RETURN)))
			objPUCollection.add("E_SIGNOUT_011", 
					"���Ϊ�ۼ����˼�״̬����ǩ��", 
					"CoreWayBillCheck.checkSignOut");
		// �Ƿ�Ϊ��Ѽ�
		if (strPmcode.equals("AFR")) {
			objPUCollection.add("E_SIGNOUT_012", 
					"��Ѽ�����ǩ��", 
					"CoreWayBillCheck.checkSignOut");			
		}
		m_sbLogInfo.append(objIT.toString() + "\r\n");
		/*
		String strEnterprise = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.equals("QQYX")) {
			// FP02,FE02���ǵ�Ʊ��������100KG���ϣ���Ʊ�걨��ֵ����USD780�ģ�
			// ��Ҫ���ֵ�ʱ�����һ����������������Ҫ��ORDER����
			String srrPkcode = objHwbColumns.getPkpkcode();
			if (srrPkcode.equals("A0628") || srrPkcode.equals("A0629")) {
				WaybillforpredictColumns objWFPC = HousewaybillDemand.loadForPredict(strCwcode);
				if (StringUtility.isNull(objWFPC.getHwhw_transactionid())) {
					BigDecimal objTotal = new BigDecimal(CargoInfoDemand.sumCargovalue(strCwcode));
					if (objTotal.compareTo(new BigDecimal("780")) > 0 || 
							new BigDecimal(objHwbColumns.getCwcwchargeweight()).compareTo(new BigDecimal("100")) > 0) {
						objPUCollection.add("H_SIGNOUT_013", 
								"��Ʊ��������100KG���ߵ�Ʊ�걨��ֵ����USD780����ҪORDER����", 
								"CoreWayBillCheck.checkSignOut");						
					}
				}
			}
		}
		*/
		/*
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.equals("KLEX")) {
			BigDecimal objCorReceivable = ReceivableDemand.sumCorReceivable(objHwbColumns.getCcococode(), 
					DateFormatUtility.getStandardSysdate().substring(0, 7));
			if (objCorReceivable.compareTo(new BigDecimal("10000")) > 0) {
				objPUCollection.add("H_SIGNOUT_014", 
						"�ÿͻ������Ѿ�����1000ŷԪ", 
						"CoreWayBillCheck.checkSignOut");	
			}
		}
		*/
		// �����Լ�Ƿ����
		String strCocode = objHwbColumns.getCcococode();
		String strEnterprise = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.equals("SLYIM"))
			strCocode = CustomerDemand.getTopParentCustomer(strCocode);
		checkFinanceForSignOut(objHwbColumns.getHwcwcode(),
				strCocode,
				strOperId,
				objHwbColumns.getPkpkcode(),
				objHwbColumns.getHwhwsignindate(),
				isArrearAllowSignout,
				objPUCollection);
		if (m_isArrearAllowSignout)
			objHwbColumns.setHwhwarrearsignout("Y");
		return objPUCollection;
	}
	
	public boolean checkFinanceForSignOut(String strCwcode,
			String strCocode,
			String strOperId,
			String strPkcode,
			String strSignindate,
			boolean isArrearAllowSignout,
			PromptUtilityCollection objPUCollection) throws Exception {
		String strEnterprise = SystempropertyDemand.getEnterprise();
		// ��������ӵ�Ϊ����״̬�����ж�Ƿ�����
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("SBD")) {
			if (CorewaybillpiecesDemand.hasSignOutPieces(strCwcode))
				return true;
		}
		// �Ƿ���Ҫ���������
		if (!SystempropertyDemand.getSignOutCheckFinance()) 
			return true;
		// ��Ʊ����������������
		IntervalTime objIT = new IntervalTime("����Ƿ���Ӧ�շ���");
		
		List listReceivable = ReceivableDemand.loadSimpleNormalFee(strCwcode);
		BigDecimal objBDZero = new BigDecimal("0");
		// �ٵ��˷ѱ������0
		BigDecimal objFreightTotal = ReceivableDemand.sumFeeTotal(listReceivable, 
				IFeeCalculateBasicData.FEEKIND_DETAIL_FREIGHT, 
				strCocode);
		if (objFreightTotal.compareTo(objBDZero) <= 0)
			objPUCollection.add("E_SIGNOUT_116", 
					"δ������ͻ��˷Ѳ���ǩ��", 
					"CoreWayBillCheck.checkSignOut");
		
		if (m_sbLogInfo == null)
			m_sbLogInfo = new StringBuffer();		
		m_sbLogInfo.append(objIT.toString() + "\r\n");
		
		
		TdiProductkind objTdiProductkind = TdiProductkindDC.loadByKey(strPkcode);
		// С����Ʒ��Ч���Ƿ�Ƿ��
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("SLY")) {
			if (objTdiProductkind.getTdiServerstructuregroup() != null &&
					!StringUtility.isNull(objTdiProductkind.getTdiServerstructuregroup().getSsgCode()) &&
					objTdiProductkind.getTdiServerstructuregroup().getSsgCode().startsWith("HKPK")) {
				m_isArrearAllowSignout = true;
				return true;				
			}
		}
		objIT = new IntervalTime("�ж����");
		// ������0���������
		Dunning objDunning = new Dunning();
		String strCreditBalance = objDunning.getCreditbalance(strCocode);
		BigDecimal objCreditBalance = new BigDecimal(strCreditBalance);
		if (objCreditBalance.compareTo(objBDZero) >= 0)
			return true;		
		// ����ǿ�Ƴ������ж����
		if (isArrearAllowSignout) {
			String strPkArrearallowsignout = objTdiProductkind.getPkArrearallowsignout();
			if (!StringUtility.isNull(strPkArrearallowsignout) &&
					strPkArrearallowsignout.equals("Y")) {
				TcoCustomer objTcoCustomer = TcoCustomerDC.loadByKey(strCocode);
				if (!StringUtility.isNull(objTcoCustomer.getCmArrearallowsignout()) &&
						objTcoCustomer.getCmArrearallowsignout().equals("Y")) {
					m_isArrearAllowSignout = true;
					return true;
				}
			}
		}
		// �Ѿ���������Ҫ���ж��Ƿ�Ƿ��
		if (ReceivableDemand.isAllWriteOff(listReceivable))
			return true;
		// ����Ҫ�ж����������Ƿ����0
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("WC")) {
			FinancestatisticsColumns objFSColumns = DunningDemand.load(strCocode);
			if (!StringUtility.isNull(objFSColumns.getFsfsbybalanceamount())) {
				if (new BigDecimal(objFSColumns.getFsfsbybalanceamount()).compareTo(new BigDecimal("0")) < 0) {
					objPUCollection.add("E_SIGNOUT_114", 
							"�ÿͻ����з���δ���壬δ����Ľ��Ϊ" + objFSColumns.getFsfsbybalanceamount() + "����ǩ��", 
							"CoreWayBillCheck.checkSignOut");
					return false;
				}
			}
		}
		if (StringUtility.isNull(strSignindate)) {
			objPUCollection.add("E_SIGNOUT_113", 
					"�ջ�����Ϊ���޷��ж�������ǩ��", 
					"CoreWayBillCheck.checkSignOut");
			return false;			
		}		
		// ���С��0ʱ������ÿͻ�δ���������-��Ʊ����Ӧ���ܷ��ô��ڵ���Ƿ�������Գ���
		/*
		objUndoSignout = objUndoSignout.add(objWayBillFeeTotal.multiply(new BigDecimal("-1")));
		BigDecimal objSignoutVerify = objUndoSignout.add(objCreditBalance);
		if (objSignoutVerify.compareTo(objBDZero) < 0) {
			objPUCollection.add("E_SIGNOUT_113", 
					"�ÿͻ�Ƿ�ѣ����Ϊ" + strCreditBalance + "����ǩ��", 
					"CoreWayBillCheck.checkSignOut");
			Businesslog objBusinesslog = new Businesslog();
			objBusinesslog.addBusinessLog(strCwcode, 
					strOperId, 
					"�ÿͻ�Ƿ�ѣ����Ϊ" + strCreditBalance + "����ǩ��");
			
			return false;
		}
		*/		
		// δ���������
		// ������������
		// ����˾���÷ֳ�3���֣��ɿۼ������ܳ�δ�����֣������
		// 1.�����+�ɿۼ�+���>0ʱ����δ�����ֶ��ܳ���������ܳ���������Ϊ:�����+�ɿۼ�+���-��Ʊ��Ӧ���ܶ� > 0
		// 2�������+�ɿۼ�+���<0ʱ�������ȫ�����ܳ�����δ�����ж�����Ϊ:�����+�ɿۼ�+���+��δ��-��Ʊ��Ӧ���ܶ� > 0
		// δ�����ۼ��ܶ�
		BigDecimal objHoldUndoSignout = ReceivableDemand.sumHoldUndoSignout(strCocode);
		// ����ۼ��ܶ�,��ֱ��ͳ�ƾɿۼ��ٶȿ�
		BigDecimal objTodayHoldUndoSignout = ReceivableDemand.sumTodayHoldUndoSignout(strCocode);
		// �����ܶ�
		BigDecimal objTodayUndoSignout = ReceivableDemand.sumTodayUndoSignout(strCocode);
		// �ɿۼ��ܶ�
		BigDecimal objYesHold = objHoldUndoSignout.add(objTodayHoldUndoSignout.multiply(new BigDecimal("-1")));
		// ��δ�����ܶ�
		//BigDecimal objYesUndoSignout = objUndoSignout.add(objTodayUndoSignout.multiply(new BigDecimal("-1")));
		//objYesUndoSignout = objYesUndoSignout.add(objYesHold.multiply(new BigDecimal("-1")));
		// �����+�ɿۼ�+���
		BigDecimal objSignoutVerify = objTodayUndoSignout.add(objYesHold);
		objSignoutVerify = objSignoutVerify.add(objCreditBalance);
		// �����
		if (strSignindate.substring(0, 10).equals(DateFormatUtility.getStandardSysdate().substring(0, 10))) {
			boolean isUndosignout = false;
			if (objSignoutVerify.compareTo(objBDZero) < 0) {
				isUndosignout = true;
			} else {
				// ��Ʊ����Ӧ���ܶ�
				BigDecimal objWayBillFeeTotal = ReceivableDemand.sumFeeTotal(listReceivable, 
						"", 
						strCocode);
				objSignoutVerify = objSignoutVerify.add(objWayBillFeeTotal.multiply(new BigDecimal("-1")));
				if (objSignoutVerify.compareTo(objBDZero) < 0)
					isUndosignout = true;
			}
			if (isUndosignout) {
				objPUCollection.add("E_SIGNOUT_113", 
						"�ÿͻ�Ƿ�ѣ����Ϊ" + strCreditBalance + 
						",����δ�����ܶ�" + objTodayUndoSignout.toString() +
						",�ɿۼ��ܶ�" + objYesHold.toString() +
						",��˵��������ǩ��", 
						"CoreWayBillCheck.checkSignOut");
				Businesslog objBusinesslog = new Businesslog();
				objBusinesslog.addBusinessLog(strCwcode, 
						strOperId, 
						"�ÿͻ�Ƿ�ѣ����Ϊ" + strCreditBalance + 
						",����δ�����ܶ�" + objTodayUndoSignout.toString() +
						",�ɿۼ��ܶ�" + objYesHold.toString() +
						",��˵��������ǩ��");
				return false;				
			}
		} else {
			// ��ʷ��
			// �� �����+�ɿۼ�+���>0ʱ����ʷ�������Գ�
			// ֻ����С��0ʱ����Ҫ�ж����м�-��Ʊ����Ƿ����0������0���ܳ�
			if (objSignoutVerify.compareTo(objBDZero) < 0) {
				// δ�����ܶ�
				BigDecimal objUndoSignout = ReceivableDemand.sumUndoSignout(strCocode);				
				// ��Ʊ����Ӧ���ܶ�
				BigDecimal objWayBillFeeTotal = ReceivableDemand.sumFeeTotal(listReceivable, 
						"", 
						strCocode);				
				objUndoSignout = objUndoSignout.add(objWayBillFeeTotal.multiply(new BigDecimal("-1")));
				objSignoutVerify = objUndoSignout.add(objCreditBalance);
				if (objSignoutVerify.compareTo(objBDZero) < 0) {
					objPUCollection.add("E_SIGNOUT_113", 
							"�ÿͻ�Ƿ�ѣ����Ϊ" + strCreditBalance + 
							",����δ�����ܶ�" + objTodayUndoSignout.toString() +
							",�ɿۼ��ܶ�" + objYesHold.toString() +
							",����δ�����ܶ�" + objUndoSignout.toString() +
							",�����ʷ������ǩ��", 
							"CoreWayBillCheck.checkSignOut");
					Businesslog objBusinesslog = new Businesslog();
					objBusinesslog.addBusinessLog(strCwcode, 
							strOperId, 
							"�ÿͻ�Ƿ�ѣ����Ϊ" + strCreditBalance + 
							",����δ�����ܶ�" + objTodayUndoSignout.toString() +
							",�ɿۼ��ܶ�" + objYesHold.toString() +
							",����δ�����ܶ�" + objUndoSignout.toString() + 
							",�����ʷ������ǩ��");
					
					return false;
				}				
			}
		} 
		
		m_sbLogInfo.append(objIT.toString() + "\r\n");
		
		return true;
	}
	
	public PromptUtilityCollection checkCustomerEwbcode(String strCustomerEwbcode,
			String StrCwcode) throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		// ��ѯ
		SimplecorewaybillQuery objSCWQuery = new SimplecorewaybillQuery();
		objSCWQuery.setCw_customerewbcode(strCustomerEwbcode);
		List objList = objSCWQuery.getResults();
		if (objList == null || objList.size() < 1)
			return objPUCollection;
		for (int i = 0; i < objList.size(); i++) {
			SimplecorewaybillColumns objSCWColumns = (SimplecorewaybillColumns)objList.get(i);
			if (!objSCWColumns.getCwcw_code().equals(StrCwcode)) {
				objPUCollection.add("E_OP_001", 
						"�ͻ��˵����Ѿ���ʹ��", 
						"checkSignIn.checkCustomerEwbcode");
				return objPUCollection;
			}
		}
		return objPUCollection;
	}
	
	public PromptUtilityCollection checkCEWBCodeWhenPredict(String strCustomerEwbcode,
			String strCocode) throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		// ��ѯ
		SimplecorewaybillQuery objSCWQuery = new SimplecorewaybillQuery();
		objSCWQuery.setCw_customerewbcode(strCustomerEwbcode);
		objSCWQuery.setCocodecustomer(strCocode);
		List objList = objSCWQuery.getResults();
		if (objList == null || objList.size() < 1)
			return objPUCollection;
		else {
			objPUCollection.add("E_OP_001", 
					"�ͻ��˵����Ѿ���ʹ��", 
					"checkSignIn.checkCustomerEwbcode");
			return objPUCollection;
		}
	}	
	
}
