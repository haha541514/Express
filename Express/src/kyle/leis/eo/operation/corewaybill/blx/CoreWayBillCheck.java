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
					"签入保存时到货总单不能为空", 
					"CoreWayBillCheck.check");
		if (StringUtility.isNull(objHwColumns.getCcococode()))
			objPUCollection.add("E_SIGNIN_002", 
					"签入保存时代理商不能为空", 
					"CoreWayBillCheck.check");
		if (StringUtility.isNull(objHwColumns.getCwcwcustomerewbcode()))
			objPUCollection.add("E_SIGNIN_003", 
					"客户运单号不能为空", 
					"CoreWayBillCheck.check");
		// 效验单号是否重复
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
		// 检查最大走货量
		if (isCheckMaxReceivable)
			checkMaxReceivable(strCocode, objPU);
		// 是否为需要效验额度的客户
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
		objPU.add("E_CFR_001", "客户欠费，请补足费用后再执行该操作", "CoreWayBillCheck.checkFinanceRestrict");
	}
	
	/**
	 * 检查最大走货量
	 * @param strCocode
	 * @param objPU
	 * @throws Exception
	 */
	private void checkMaxReceivable(String strCocode,
			PromptUtilityCollection objPU) throws Exception {
		TcoCustomer objTcoCustomer = TcoCustomerDC.loadByKey(strCocode);
		if (objTcoCustomer == null) return;
		if (!objTcoCustomer.getTcoCorporation().getTdiCorporationstatus().getCsCode().equals("R")) {
			objPU.add("E_CFR_003", "该客户的状态不为发布状态，无法走货", "CoreWayBillCheck.checkMaxReceivable");
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
			objPU.add("E_CFR_002", "该客户走货量已经超过最大限制", "CoreWayBillCheck.checkMaxReceivable");
		}
	}
	
	private void checkRepeatWaybill(HousewaybillColumns objHwColumns,
			PromptUtilityCollection objPUCollection) throws Exception {
		// 效验单号是否重复
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
							"运单号码重复", 
							"CoreWayBillCheck.check");
					break;
				} else {
					iPreAlertTimes++;
					if (iPreAlertTimes > 1) {
						objPUCollection.add("E_SIGNIN_008", 
								"运单号码重复", 
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
					"收货重量不能为空", 
					"CoreWayBillCheck.check");
		if (StringUtility.isNull(objHwColumns.getCwcwgrossweight()))
			objPUCollection.add("E_SIGNIN_005", 
					"收货实重不能为空", 
					"CoreWayBillCheck.check");
		if (StringUtility.isNull(objHwColumns.getCwcwpieces()))
			objPUCollection.add("E_SIGNIN_006", 
					"收货件数不能为空", 
					"CoreWayBillCheck.check");
		if (listCorewaybillpieces == null || listCorewaybillpieces.size() < 1)
			objPUCollection.add("E_SIGNIN_007", 
					"收货件数列表不能为空", 
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
		// 是否已经签出				
		if (!StringUtility.isNull(strHasBwcodeDeparture) && "SO".equals(objHwbColumns.getCwscwscode()))
			objPUCollection.add("E_SIGNOUT_002", 
					"该快件已经签出", 
					"CoreWayBillCheck.checkSignOut");
		if (!SystempropertyDemand.getEnterprise().startsWith("SLYIM") &&
				!objHwbColumns.getCwscwscode().equals(ICorewaybillBasicData.COREWAYBILL_STATUS_INPUT))
			objPUCollection.add("E_SIGNOUT_001", 
					"快件状态不为录单状态不能签出", 
					"CoreWayBillCheck.checkSignOut");
		// 产品不能为空
		if (StringUtility.isNull(objHwbColumns.getPkpkcode()))
			objPUCollection.add("E_SIGNOUT_003", 
					"销售产品不能为空", 
					"CoreWayBillCheck.checkSignOut");			
		// 服务渠道不能为空
		if (StringUtility.isNull(objHwbColumns.getSchnchncode()))
			objPUCollection.add("E_SIGNOUT_004", 
					"服务渠道不能为空", 
					"CoreWayBillCheck.checkSignOut");
		
		IntervalTime objIT = new IntervalTime("检查主单渠道是否跟运单渠道一致");
		
		// 服务渠道必须与出货主单的服务渠道一致
		BatchwaybillColumns objBwColumns = BatchWayBillDemand.load(strBwcodeDeparture);
		if (objBwColumns == null)
			objPUCollection.add("E_SIGNOUT_005", 
					"不存在的出货总单", 
					"CoreWayBillCheck.checkSignOut");
		if (StringUtility.isNull(objHwbColumns.getSchnchncode()) ||
				!objHwbColumns.getSchnchncode().equals(objBwColumns.getChnchncode()))
			objPUCollection.add("E_SIGNOUT_006", 
					"快件的服务渠道和主单的服务渠道不一致", 
					"CoreWayBillCheck.checkSignOut");
		if (m_sbLogInfo == null)
			m_sbLogInfo = new StringBuffer();
		m_sbLogInfo.append(objIT.toString() + "\r\n");
		
		objIT = new IntervalTime("检查主单类型");
		// 如果是DHL件则必须换单，打印标签
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
							"所选渠道的单据类型要求换单，请换单后再出货", 
							"CoreWayBillCheck.checkSignOut");
				/*
				if (iLabelprintTimes < 1)
					objPUCollection.add("H_SIGNOUT_001", 
							"所选渠道的单据类型要求打印标签，请确认是否需要打印标签后选择继续或取消", 
							"CoreWayBillCheck.checkSignOut");					
			    */
			}
		}
		m_sbLogInfo.append(objIT.toString() + "\r\n");
		
		// 效验货物类型
		if (StringUtility.isNull(objHwbColumns.getCtctcode()))
			objPUCollection.add("E_SIGNOUT_007", 
					"货物类型不能为空", 
					"CoreWayBillCheck.checkSignOut");
		/*
		if (objHwbColumns.getCtctcode().equals(ICorewaybillBasicData.CARGOTYPE_WPX)) {
			// 包裹必须输入形式发票
			List objList = CargoInfoDemand.queryByCwCode(strCwcode);
			if (objList == null || objList.size() < 1)
				objPUCollection.add("E_SIGNOUT_008", 
						"包裹必须要输入形式发票", 
						"CoreWayBillCheck.checkSignOut");
		}*/
		
		objIT = new IntervalTime("检查件信息");
		
		List objList = CorewaybillpiecesDemand.load(strCwcode);
		if (objList == null || objList.size() < 1)
			objPUCollection.add("E_SIGNOUT_009", 
					"必须要包含有件的详细信息", 
					"CoreWayBillCheck.checkSignOut");
		if (!objHwbColumns.getCwcwtransferpieces().equals(String.valueOf(objList.size())))
			objPUCollection.add("E_SIGNOUT_010", 
					"子单必须与快件件数一致", 
					"CoreWayBillCheck.checkSignOut");
		// 是否已经扣件
		if (!StringUtility.isNull(strIhscode) && 
				(strIhscode.equals(IIssueBasicData.HOLD_STATUS_CONFIRM) ||
						strIhscode.equals(IIssueBasicData.HOLD_STATUS_RETURN)))
			objPUCollection.add("E_SIGNOUT_011", 
					"快件为扣件或退件状态不能签出", 
					"CoreWayBillCheck.checkSignOut");
		// 是否为免费件
		if (strPmcode.equals("AFR")) {
			objPUCollection.add("E_SIGNOUT_012", 
					"免费件不能签出", 
					"CoreWayBillCheck.checkSignOut");			
		}
		m_sbLogInfo.append(objIT.toString() + "\r\n");
		/*
		String strEnterprise = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.equals("QQYX")) {
			// FP02,FE02凡是单票重量超过100KG以上，单票申报价值超过USD780的，
			// 需要出仓的时候加上一个出仓条件，必须要有ORDER号码
			String srrPkcode = objHwbColumns.getPkpkcode();
			if (srrPkcode.equals("A0628") || srrPkcode.equals("A0629")) {
				WaybillforpredictColumns objWFPC = HousewaybillDemand.loadForPredict(strCwcode);
				if (StringUtility.isNull(objWFPC.getHwhw_transactionid())) {
					BigDecimal objTotal = new BigDecimal(CargoInfoDemand.sumCargovalue(strCwcode));
					if (objTotal.compareTo(new BigDecimal("780")) > 0 || 
							new BigDecimal(objHwbColumns.getCwcwchargeweight()).compareTo(new BigDecimal("100")) > 0) {
						objPUCollection.add("H_SIGNOUT_013", 
								"单票重量超过100KG或者单票申报价值超过USD780，需要ORDER号码", 
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
						"该客户单月已经超过1000欧元", 
						"CoreWayBillCheck.checkSignOut");	
			}
		}
		*/
		// 费用以及欠款检查
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
		// 如果存在子单为出货状态，则不判断欠费情况
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("SBD")) {
			if (CorewaybillpiecesDemand.hasSignOutPieces(strCwcode))
				return true;
		}
		// 是否需要检查费用情况
		if (!SystempropertyDemand.getSignOutCheckFinance()) 
			return true;
		// 此票件的所有正常费用
		IntervalTime objIT = new IntervalTime("检查是否有应收费用");
		
		List listReceivable = ReceivableDemand.loadSimpleNormalFee(strCwcode);
		BigDecimal objBDZero = new BigDecimal("0");
		// 速递运费必须大于0
		BigDecimal objFreightTotal = ReceivableDemand.sumFeeTotal(listReceivable, 
				IFeeCalculateBasicData.FEEKIND_DETAIL_FREIGHT, 
				strCocode);
		if (objFreightTotal.compareTo(objBDZero) <= 0)
			objPUCollection.add("E_SIGNOUT_116", 
					"未计算出客户运费不能签出", 
					"CoreWayBillCheck.checkSignOut");
		
		if (m_sbLogInfo == null)
			m_sbLogInfo = new StringBuffer();		
		m_sbLogInfo.append(objIT.toString() + "\r\n");
		
		
		TdiProductkind objTdiProductkind = TdiProductkindDC.loadByKey(strPkcode);
		// 小包产品不效验是否欠费
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("SLY")) {
			if (objTdiProductkind.getTdiServerstructuregroup() != null &&
					!StringUtility.isNull(objTdiProductkind.getTdiServerstructuregroup().getSsgCode()) &&
					objTdiProductkind.getTdiServerstructuregroup().getSsgCode().startsWith("HKPK")) {
				m_isArrearAllowSignout = true;
				return true;				
			}
		}
		objIT = new IntervalTime("判断余额");
		// 余额大于0则允许出货
		Dunning objDunning = new Dunning();
		String strCreditBalance = objDunning.getCreditbalance(strCocode);
		BigDecimal objCreditBalance = new BigDecimal(strCreditBalance);
		if (objCreditBalance.compareTo(objBDZero) >= 0)
			return true;		
		// 允许强制出货则不判断余额
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
		// 已经核销则不需要再判断是否欠费
		if (ReceivableDemand.isAllWriteOff(listReceivable))
			return true;
		// 万臣需要判断昨天的余额是否大于0
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("WC")) {
			FinancestatisticsColumns objFSColumns = DunningDemand.load(strCocode);
			if (!StringUtility.isNull(objFSColumns.getFsfsbybalanceamount())) {
				if (new BigDecimal(objFSColumns.getFsfsbybalanceamount()).compareTo(new BigDecimal("0")) < 0) {
					objPUCollection.add("E_SIGNOUT_114", 
							"该客户还有费用未付清，未付清的金额为" + objFSColumns.getFsfsbybalanceamount() + "不能签出", 
							"CoreWayBillCheck.checkSignOut");
					return false;
				}
			}
		}
		if (StringUtility.isNull(strSignindate)) {
			objPUCollection.add("E_SIGNOUT_113", 
					"收货日期为空无法判断余额，不能签出", 
					"CoreWayBillCheck.checkSignOut");
			return false;			
		}		
		// 余额小于0时，如果该客户未出货的余额-此票件的应收总费用大于等于欠款额则可以出货
		/*
		objUndoSignout = objUndoSignout.add(objWayBillFeeTotal.multiply(new BigDecimal("-1")));
		BigDecimal objSignoutVerify = objUndoSignout.add(objCreditBalance);
		if (objSignoutVerify.compareTo(objBDZero) < 0) {
			objPUCollection.add("E_SIGNOUT_113", 
					"该客户欠费，余额为" + strCreditBalance + "不能签出", 
					"CoreWayBillCheck.checkSignOut");
			Businesslog objBusinesslog = new Businesslog();
			objBusinesslog.addBusinessLog(strCwcode, 
					strOperId, 
					"该客户欠费，余额为" + strCreditBalance + "不能签出");
			
			return false;
		}
		*/		
		// 未出货的余额
		// 这里分两种情况
		// 将公司费用分成3部分，旧扣件，旧能出未出部分，当天件
		// 1.当天件+旧扣件+余额>0时，旧未出部分都能出，当天件能出货的条件为:当天件+旧扣件+余额-此票件应收总额 > 0
		// 2、当天件+旧扣件+余额<0时，当天件全部不能出，旧未出的判断条件为:当天件+旧扣件+余额+旧未出-此票件应收总额 > 0
		// 未出货扣件总额
		BigDecimal objHoldUndoSignout = ReceivableDemand.sumHoldUndoSignout(strCocode);
		// 当天扣件总额,比直接统计旧扣件速度快
		BigDecimal objTodayHoldUndoSignout = ReceivableDemand.sumTodayHoldUndoSignout(strCocode);
		// 当天总额
		BigDecimal objTodayUndoSignout = ReceivableDemand.sumTodayUndoSignout(strCocode);
		// 旧扣件总额
		BigDecimal objYesHold = objHoldUndoSignout.add(objTodayHoldUndoSignout.multiply(new BigDecimal("-1")));
		// 旧未出件总额
		//BigDecimal objYesUndoSignout = objUndoSignout.add(objTodayUndoSignout.multiply(new BigDecimal("-1")));
		//objYesUndoSignout = objYesUndoSignout.add(objYesHold.multiply(new BigDecimal("-1")));
		// 今天件+旧扣件+余额
		BigDecimal objSignoutVerify = objTodayUndoSignout.add(objYesHold);
		objSignoutVerify = objSignoutVerify.add(objCreditBalance);
		// 当天件
		if (strSignindate.substring(0, 10).equals(DateFormatUtility.getStandardSysdate().substring(0, 10))) {
			boolean isUndosignout = false;
			if (objSignoutVerify.compareTo(objBDZero) < 0) {
				isUndosignout = true;
			} else {
				// 此票件的应收总额
				BigDecimal objWayBillFeeTotal = ReceivableDemand.sumFeeTotal(listReceivable, 
						"", 
						strCocode);
				objSignoutVerify = objSignoutVerify.add(objWayBillFeeTotal.multiply(new BigDecimal("-1")));
				if (objSignoutVerify.compareTo(objBDZero) < 0)
					isUndosignout = true;
			}
			if (isUndosignout) {
				objPUCollection.add("E_SIGNOUT_113", 
						"该客户欠费，余额为" + strCreditBalance + 
						",当天未出件总额" + objTodayUndoSignout.toString() +
						",旧扣件总额" + objYesHold.toString() +
						",因此当天件不能签出", 
						"CoreWayBillCheck.checkSignOut");
				Businesslog objBusinesslog = new Businesslog();
				objBusinesslog.addBusinessLog(strCwcode, 
						strOperId, 
						"该客户欠费，余额为" + strCreditBalance + 
						",当天未出件总额" + objTodayUndoSignout.toString() +
						",旧扣件总额" + objYesHold.toString() +
						",因此当天件不能签出");
				return false;				
			}
		} else {
			// 历史件
			// 当 今天件+旧扣件+余额>0时，历史件都可以出
			// 只有在小于0时，需要判断所有件-此票金额是否大于0，大于0则能出
			if (objSignoutVerify.compareTo(objBDZero) < 0) {
				// 未出货总额
				BigDecimal objUndoSignout = ReceivableDemand.sumUndoSignout(strCocode);				
				// 此票件的应收总额
				BigDecimal objWayBillFeeTotal = ReceivableDemand.sumFeeTotal(listReceivable, 
						"", 
						strCocode);				
				objUndoSignout = objUndoSignout.add(objWayBillFeeTotal.multiply(new BigDecimal("-1")));
				objSignoutVerify = objUndoSignout.add(objCreditBalance);
				if (objSignoutVerify.compareTo(objBDZero) < 0) {
					objPUCollection.add("E_SIGNOUT_113", 
							"该客户欠费，余额为" + strCreditBalance + 
							",当天未出件总额" + objTodayUndoSignout.toString() +
							",旧扣件总额" + objYesHold.toString() +
							",所有未出件总额" + objUndoSignout.toString() +
							",因此历史件不能签出", 
							"CoreWayBillCheck.checkSignOut");
					Businesslog objBusinesslog = new Businesslog();
					objBusinesslog.addBusinessLog(strCwcode, 
							strOperId, 
							"该客户欠费，余额为" + strCreditBalance + 
							",当天未出件总额" + objTodayUndoSignout.toString() +
							",旧扣件总额" + objYesHold.toString() +
							",所有未出件总额" + objUndoSignout.toString() + 
							",因此历史件不能签出");
					
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
		// 查询
		SimplecorewaybillQuery objSCWQuery = new SimplecorewaybillQuery();
		objSCWQuery.setCw_customerewbcode(strCustomerEwbcode);
		List objList = objSCWQuery.getResults();
		if (objList == null || objList.size() < 1)
			return objPUCollection;
		for (int i = 0; i < objList.size(); i++) {
			SimplecorewaybillColumns objSCWColumns = (SimplecorewaybillColumns)objList.get(i);
			if (!objSCWColumns.getCwcw_code().equals(StrCwcode)) {
				objPUCollection.add("E_OP_001", 
						"客户运单号已经被使用", 
						"checkSignIn.checkCustomerEwbcode");
				return objPUCollection;
			}
		}
		return objPUCollection;
	}
	
	public PromptUtilityCollection checkCEWBCodeWhenPredict(String strCustomerEwbcode,
			String strCocode) throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		// 查询
		SimplecorewaybillQuery objSCWQuery = new SimplecorewaybillQuery();
		objSCWQuery.setCw_customerewbcode(strCustomerEwbcode);
		objSCWQuery.setCocodecustomer(strCocode);
		List objList = objSCWQuery.getResults();
		if (objList == null || objList.size() < 1)
			return objPUCollection;
		else {
			objPUCollection.add("E_OP_001", 
					"客户运单号已经被使用", 
					"checkSignIn.checkCustomerEwbcode");
			return objPUCollection;
		}
	}	
	
}
