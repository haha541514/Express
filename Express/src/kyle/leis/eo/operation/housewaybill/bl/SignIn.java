package kyle.leis.eo.operation.housewaybill.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.dbaccess.query.HSingleQuery;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.billing.calculate.chargeweight.bl.Chargeweight;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightParameter;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightResult;
import kyle.leis.eo.billing.calculate.feecalculate.blx.AutoFeeCalculateThread;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateDemand;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateParameter;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateResult;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.calculate.freight.bl.Freight;
import kyle.leis.eo.billing.receivable.bl.Receivable;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import kyle.leis.eo.billing.receivable.dax.IReceivableBasicData;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.eo.customerservice.issue.da.IssueColumns;
import kyle.leis.eo.customerservice.issue.dax.IssueDemand;
import kyle.leis.eo.customerservice.track.bl.Track;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillDemand;
import kyle.leis.eo.operation.corewaybill.blx.CoreWayBillCheck;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybill.dax.ICorewaybillBasicData;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpackageColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderDemand;
import kyle.leis.eo.operation.housewaybill.tp.SavePackageWaybillTrans;
import kyle.leis.eo.operation.housewaybill.tp.SaveWaybillTransaction;
import kyle.leis.eo.operation.predictwaybill.tp.ModifyStatusByCorewaybillTrans;
import kyle.leis.eo.operation.specialtype.bl.Specialtype;
import kyle.leis.eo.operation.specialtype.dax.ISpecialtypeBasicData;
import kyle.leis.eo.operation.specialtype.tp.SaveSingleSpecialtypeTrans;
import kyle.leis.es.businessrule.weightrule.da.CalcweightvalueColumns;
import kyle.leis.es.businessrule.weightrule.da.CalcweightvalueQuery;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TcoCustomerDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.fs.dictionary.district.bl.DHLRemoteDistrict;
import kyle.leis.fs.dictionary.district.dax.DHLRemoteVerifyResult;
import kyle.leis.hi.TcoCustomer;
import kyle.leis.hi.TdiProductkind;

public class SignIn {
	public SavedResultUtility save(WaybillforpackageColumns objWFPColumns,
			String strAdtcode,
			String strCwcode,
			String strOldBrvid,
			String strOperId) throws Exception {
		// 返回值
		SavedResultUtility objSRUtility = new SavedResultUtility();
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		// 转换成运单列表
		HousewaybillColumns objHWColumns = HousewaybillDemand.buildHousewaybillByPackage(objWFPColumns, 
				strAdtcode);
		// 构造件数信息
		List<CorewaybillpiecesColumns> listCorewaybillpieces = new ArrayList<CorewaybillpiecesColumns>();
		CorewaybillpiecesColumns objCWPC = new CorewaybillpiecesColumns();
		objCWPC.setCpcomp_idcpid(1);
		if (!StringUtility.isNull(objWFPColumns.getCwcwcode()))
			objCWPC.setCpcomp_idcwcode(Long.parseLong(objWFPColumns.getCwcwcode()));
		objCWPC.setCpcpgrossweight(new BigDecimal(objHWColumns.getCwcwgrossweight()));
		objCWPC.setCpcplength(new BigDecimal("0"));
		objCWPC.setCpcpheight(new BigDecimal("0"));
		objCWPC.setCpcpwidth(new BigDecimal("0"));
		
		listCorewaybillpieces.add(objCWPC);
		// 剩余的列
		buildPackageSavedColumns(objHWColumns, strAdtcode, listCorewaybillpieces);
		
		CoreWayBillCheck objCoreWayBillCheck = new CoreWayBillCheck();
		if (strAdtcode.equals("A")) {
			objCoreWayBillCheck.checkFinanceRestrict(objHWColumns.getCcococode(), "001", true, objPUCollection);
		} else {
			objCoreWayBillCheck.checkFinanceRestrict(objHWColumns.getScococode(), "001", true, objPUCollection);
		}
		if (!objPUCollection.canGo(true)) {
			objSRUtility.setPromptUtilityCollection(objPUCollection);
			return objSRUtility;			
		}		
		
		// 构造问题列表
		List listIssue = null;
		if (!StringUtility.isNull(objWFPColumns.getBwvbwbvissuecontent())) {
			listIssue = new ArrayList();
			IssueColumns objIssueColumns = new IssueColumns();
			objIssueColumns.setIsutisutcode("501");
			objIssueColumns.setIhsihscode(objWFPColumns.getIhsihscode());
			objIssueColumns.setIsuiscontent(objWFPColumns.getBwvbwbvissuecontent());
			listIssue.add(objIssueColumns);
		}
		// 保存
		SavePackageWaybillTrans objSPWTrans = new SavePackageWaybillTrans();
		objSPWTrans.setParseParam(objWFPColumns, objHWColumns, 
				listCorewaybillpieces, 
				listIssue,
				strCwcode,
				strOldBrvid,
				strOperId);
		objSPWTrans.execute();
		Long lNewCwcode = objSPWTrans.getNewCwcode();
		// 自动计费
		String strBckcode = IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW;
		if (strAdtcode.equals("D"))
			strBckcode = IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW;
		AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(String.valueOf(lNewCwcode),
				strBckcode,
				true);			
		objAFCThread.start();		
		
		WaybillforpackageColumns objSavedWFPC = HousewaybillDemand.loadForPackage(String.valueOf(lNewCwcode));
		objSRUtility.setColumns(objSavedWFPC);
		objSRUtility.setPromptUtilityCollection(objPUCollection);
		
		return objSRUtility;
	}
	
	public SavedResultUtility save(HousewaybillColumns objHwColumns,
			List listCorewaybillpieces,
			List listIssueColumns,
			String strOperId,
			String strReceivableAmount,
			boolean isIgnoreNotice) throws Exception {
		return save(objHwColumns,
				listCorewaybillpieces,
				listIssueColumns,
				strOperId,
				strReceivableAmount,
				isIgnoreNotice,
				null);
	}
	
	public SavedResultUtility save(HousewaybillColumns objHwColumns,
			List listCorewaybillpieces,
			List listIssueColumns,
			String strOperId,
			String strReceivableAmount,
			boolean isIgnoreNotice,
			List listRvColumns) throws Exception {
		// 返回值
		SavedResultUtility objSRUtility = new SavedResultUtility();
		buildSavedColumns(objHwColumns, listCorewaybillpieces);
		// 收货效验
		CoreWayBillCheck objCoreWayBillCheck = new CoreWayBillCheck();
		PromptUtilityCollection objPUCollection = objCoreWayBillCheck.checkSignIn(objHwColumns,
				listCorewaybillpieces);
		// 检查欠费情况
		objCoreWayBillCheck.checkFinanceRestrict(objHwColumns.getCcococode(), "001", true, objPUCollection);
		
		if (objPUCollection.canGo(isIgnoreNotice)) {
				// 收货产品效验
				if (StringUtility.isNull(strReceivableAmount) ||
						new BigDecimal(strReceivableAmount).compareTo(new BigDecimal("0")) == 0) {
				String strErrorInfo = checkRestrictByPK(objHwColumns,
						listCorewaybillpieces);
				if (!StringUtility.isNull(strErrorInfo)) {
					objPUCollection = new PromptUtilityCollection();
					objPUCollection.add("E_SIGNIN_001", 
							strErrorInfo, 
							"SignIn.save");
					objSRUtility.setPromptUtilityCollection(objPUCollection);
					return objSRUtility;
				}
			} else {
				TcoCustomer objTcoCustomer = TcoCustomerDC.loadByKey(objHwColumns.getCcococode());
				if (!objTcoCustomer.getTdiCustomertype().getCtCode().equals("DC")) {
					objPUCollection = new PromptUtilityCollection();
					objPUCollection.add("E_SIGNIN_001", 
							"只有直客才能录入应收金额", 
							"SignIn.save");
					objSRUtility.setPromptUtilityCollection(objPUCollection);
					return objSRUtility;
				}
			}
			// 检查是否为ODA
			DHLRemoteVerifyResult objDHLRVResult = null;
			if (objHwColumns.getPkpkcode().startsWith("D")) {
				DHLRemoteDistrict objDHLRemoteDistrict = new DHLRemoteDistrict();
				objDHLRVResult = objDHLRemoteDistrict.verify(objHwColumns.getSdtdthubcode(), 
						objHwColumns.getHwhwconsigneepostcode(), 
						"");
				// 偏远则记录问题
				if (objDHLRVResult.getDHLRemoteVerifyResult())
					listIssueColumns.add(IssueDemand.buildODAIssueColumns("", objDHLRVResult.getRemark()));
			}
			// 保存
			SaveWaybillTransaction objSignInTrans = new SaveWaybillTransaction();
			objSignInTrans.setSignInParam(objHwColumns, 
					listCorewaybillpieces, 
					listIssueColumns, 
					strOperId);
			objSignInTrans.execute();
			Long lNewCwcode = objSignInTrans.getNewCwcode();
			HousewaybillColumns objSavedHWBColunns = HousewaybillDemand.loadByCwcode(String.valueOf(lNewCwcode));
			objSRUtility.setColumns(objSavedHWBColunns);
			// 收货需要删除预报数据
			if (objSavedHWBColunns != null && !StringUtility.isNull(objSavedHWBColunns.getHwcwcode())) {
				ModifyStatusByCorewaybillTrans objMSBCT = new ModifyStatusByCorewaybillTrans();
				objMSBCT.setParam(objSavedHWBColunns.getHwcwcode(), 
						objSavedHWBColunns.getCwscwscode(),
						strOperId);
				objMSBCT.execute();
			}
			// 新增收货轨迹
			Track objTrack = new Track();
			objTrack.addSingleTrack(String.valueOf(lNewCwcode), 
					objHwColumns.getOdtdtcode(), 
					"AF", 
					strOperId, 
					DateFormatUtility.getStandardSysdate());
			// 偏远则记录特殊类型
			if (objDHLRVResult != null && objDHLRVResult.getDHLRemoteVerifyResult()) {
				SaveSingleSpecialtypeTrans objSSSTrans = new SaveSingleSpecialtypeTrans();
				objSSSTrans.setParam(String.valueOf(lNewCwcode), 
						ISpecialtypeBasicData.SPECIALTYPE_ODA, 
						IReceivableBasicData.OPERID_SYSTEM, 
						objDHLRVResult.getRemark());
				objSSSTrans.execute();				
			}
			//记录超长超重特殊类型
			Specialtype objSpecialtype = new Specialtype();
			objSpecialtype.addOverLengthSpecialtype(listCorewaybillpieces,strOperId,String.valueOf(lNewCwcode));
			// 收货金额不为空时自动记录应收
			if (!StringUtility.isNull(strReceivableAmount) &&
					new BigDecimal(strReceivableAmount).compareTo(new BigDecimal("0")) > 0) {
				ReceivableColumns objReceivableColumns = ReceivableDemand.buildReceivalbe(objSavedHWBColunns, 
						new BigDecimal(objSavedHWBColunns.getCwcwchargeweight()), 
						new BigDecimal(strReceivableAmount), 
						"A0101", 
						"RMB", 
						"收货时自动记录应收", 
						strOperId);
				Receivable objReceivable = new Receivable();
				List<ReceivableColumns> listReceivable = new ArrayList<ReceivableColumns>();
				listReceivable.add(objReceivableColumns);
				objReceivable.save(listReceivable, 
						objSavedHWBColunns.getHwcwcode(), 
						strOperId);
			} else {
				if (listRvColumns != null && listRvColumns.size() > 0) {
					ReceivableDemand.buildReceivalbe(objSavedHWBColunns, 
							listRvColumns, 
							strOperId);
					Receivable objReceivable = new Receivable();
					objReceivable.save(listRvColumns, 
							objSavedHWBColunns.getHwcwcode(), 
							strOperId);		
				}
				// 自动计费
				AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(String.valueOf(lNewCwcode),
						IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
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
		// 获得计费重量
		// 邮编情况
		if (StringUtility.isNull(objHwColumns.getCwcwpostcodedestination()))
			objHwColumns.setCwcwpostcodedestination(objHwColumns.getHwhwconsigneepostcode());
		
		ChargeweightParameter objCWParameter = CorewaybillDemand.transferToCWParameter(objHwColumns, 
				listCorewaybillpieces);
		Chargeweight objChargeweight = new Chargeweight();
		ChargeweightResult objCWResult = objChargeweight.calculate(objCWParameter);
		objHwColumns.setCwcwchargeweight(new BigDecimal(objCWResult.getChargeweight()));
		objHwColumns.setCwcwvolumerate(Integer.parseInt(objCWResult.getVolumeRate()));
		// 由于Fedex的实重有限制所以需要重新计算实重
		if (!StringUtility.isNull(objCWResult.getGrossweight()))
			objHwColumns.setCwcwgrossweight(new BigDecimal(objCWResult.getGrossweight()));
		// 设置代理商、代理渠道
		String strBwcode = objHwColumns.getAbwbwcode();
		BatchwaybillColumns objArrivalBWColumns = BatchWayBillDemand.load(strBwcode);
		objHwColumns.setCcococode(objArrivalBWColumns.getCococode());
		objHwColumns.setCchnchncode(objArrivalBWColumns.getChnchncode());
		// 设置运单状态
		objHwColumns.setCwscwscode(ICorewaybillBasicData.COREWAYBILL_STATUS_SIGNIN);
		// 设置公司运单号、服务商运单号
		// objHwColumns.setCwcwewbcode(objHwColumns.getCwcwcustomerewbcode());
		objHwColumns.setCwcwserverewbcode(objHwColumns.getCwcwcustomerewbcode());
		if (!StringUtility.isNull(objHwColumns.getHwcwcode())) {
			HousewaybillColumns objSavedHWBColunns = HousewaybillDemand.loadByCwcode(String.valueOf(objHwColumns.getHwcwcode()));
			objHwColumns.setCwcwewbcode(objSavedHWBColunns.getCwcwewbcode());
			objHwColumns.setCwcwserverewbcode(objSavedHWBColunns.getCwcwserverewbcode());
			objHwColumns.setCwcwcustomerewbcode(objSavedHWBColunns.getCwcwcustomerewbcode());
		} else {
			String strEnterprise = SystempropertyDemand.getEnterprise();
			if (!StringUtility.isNull(strEnterprise) &&
					strEnterprise.equals("SLY")) {
				objHwColumns.setCwcwewbcode(PredictOrderDemand.buildEwbcode());
			} else {
				objHwColumns.setCwcwewbcode(objHwColumns.getCwcwcustomerewbcode());
			}
		}
		// 设置中转重量、服务商重量
		objHwColumns.setCwcwtransferchargeweight(new BigDecimal(objHwColumns.getCwcwchargeweight()));
		objHwColumns.setCwcwserverchargeweight(new BigDecimal(objHwColumns.getCwcwchargeweight()));
		objHwColumns.setCwcwtransfergrossweight(new BigDecimal(objHwColumns.getCwcwgrossweight()));
		objHwColumns.setCwcwtransferpieces(new BigDecimal(objHwColumns.getCwcwpieces()));
		objHwColumns.setOdtdtcode(TdiEnterpriseelementDC.loadByKey(objHwColumns.getEeeecode()).getTdiDistrict().getDtCode());
	}
	
	
	public String checkRestrictByPK(HousewaybillColumns objHwColumns,
			List listCorewaybillpieces) throws Exception {
		String strPkcode = objHwColumns.getPkpkcode();
		TdiProductkind objTPK = (TdiProductkind)HSingleQuery.load(TdiProductkind.class, strPkcode);
		String strSIRS = objTPK.getPkSigninrestrictsign();
		if (StringUtility.isNull(strSIRS))
			strSIRS = "N";
		// 检查实重跟材积的限制
		String strPgwrestrictdesc = objTPK.getPkPcwrestrictformula();
		String strPvwrestrictdesc = objTPK.getPkPdsrestrictformula();
		if (!StringUtility.isNull(strPgwrestrictdesc) || 
				!StringUtility.isNull(strPvwrestrictdesc)) {
			String strErrorInfo = checkPiecesRestrict(strPgwrestrictdesc,
					strPvwrestrictdesc,
					listCorewaybillpieces);
			if (!StringUtility.isNull(strErrorInfo))
				return strErrorInfo;
		}
		// 不受限制
		if (strSIRS.equals("N")) 
			return "";
		// 未查找到价格表则表示该客户受产品的限制
		FreightpriceCondition objFPCondition = FeeCalculateDemand.transferToCondition(objHwColumns, 
				IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW);
		// 转换为计费的参数
		FeeCalculateParameter objFCParameter = FeeCalculateDemand.transferToParameter(objHwColumns, 
				IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW);
		Freight objFreight = new Freight();
		FeeCalculateResult objFCResult = objFreight.calculate(objFPCondition, objFCParameter);
		if (objFCResult == null || StringUtility.isNull(objFCResult.getPricevalue()) || 
				new BigDecimal(objFCResult.getPricevalue()).compareTo(new BigDecimal("0")) <= 0)
			return "该客户禁止按您选择的销售产品走货，如有疑问，请联系市场部设置价格。";
		return "";
	}
	
	private void buildPackageSavedColumns(HousewaybillColumns objHwColumns,
			String strAdtcode,
			List listCorewaybillpieces) throws Exception {
		objHwColumns.setCwcwbatchwaybillsign("Y");
		// 设置代理商、代理渠道
		if (strAdtcode.equals("A")) {
			String strBwcode = objHwColumns.getAbwbwcode();
			BatchwaybillColumns objArrivalBWColumns = BatchWayBillDemand.load(strBwcode);
			objHwColumns.setCcococode(objArrivalBWColumns.getCococode());
			objHwColumns.setCchnchncode(objArrivalBWColumns.getChnchncode());
			objHwColumns.setCwscwscode(ICorewaybillBasicData.COREWAYBILL_STATUS_SIGNIN);
			objHwColumns.setAbwadddate(DateFormatUtility.getStandardDate(objArrivalBWColumns.getBwadddate()));
		} else {
			String strBwcode = objHwColumns.getDbwbwcode();
			BatchwaybillColumns objBWColumns = BatchWayBillDemand.load(strBwcode);
			objHwColumns.setScococode(objBWColumns.getCococode());
			objHwColumns.setSchnchncode(objBWColumns.getChnchncode());
			objHwColumns.setCwscwscode(ICorewaybillBasicData.COREWAYBILL_STATUS_INPUT);
			objHwColumns.setDbwadddate(DateFormatUtility.getStandardDate(objBWColumns.getBwadddate()));
		}
		if (!StringUtility.isNull(objHwColumns.getHwcwcode())) {
			HousewaybillColumns objSavedHWBColunns = HousewaybillDemand.loadByCwcode(String.valueOf(objHwColumns.getHwcwcode()));
			objHwColumns.setCwcwewbcode(objSavedHWBColunns.getCwcwewbcode());
			objHwColumns.setCwcwserverewbcode(objSavedHWBColunns.getCwcwserverewbcode());
			objHwColumns.setCwcwcustomerewbcode(objSavedHWBColunns.getCwcwcustomerewbcode());
		} else {
			objHwColumns.setCwcwewbcode(PredictOrderDemand.buildEwbcode());
			objHwColumns.setCwcwserverewbcode(objHwColumns.getCwcwewbcode());
			objHwColumns.setCwcwcustomerewbcode(objHwColumns.getCwcwewbcode());			
		}
		
		// 获得计费重量
		ChargeweightParameter objCWParameter = CorewaybillDemand.transferToCWParameter(objHwColumns, 
				listCorewaybillpieces);
		if (strAdtcode.equals("D")) {
			objCWParameter.setPdcode(IExpressPriceBasicData.PRICEDOMAIN_COSTS);
			objCWParameter.setCocode(objHwColumns.getSchnchncode());
			objCWParameter.setSearchDate(objHwColumns.getDbwadddate());
		}
		Chargeweight objChargeweight = new Chargeweight();
		ChargeweightResult objCWResult = objChargeweight.calculate(objCWParameter);
		objHwColumns.setCwcwchargeweight(new BigDecimal(objCWResult.getChargeweight()));
		objHwColumns.setCwcwvolumerate(Integer.parseInt(objCWResult.getVolumeRate()));		
		
		// 设置中转重量、服务商重量
		objHwColumns.setCwcwtransferchargeweight(new BigDecimal(objHwColumns.getCwcwchargeweight()));
		objHwColumns.setCwcwserverchargeweight(new BigDecimal(objHwColumns.getCwcwchargeweight()));
		objHwColumns.setCwcwtransfergrossweight(new BigDecimal(objHwColumns.getCwcwgrossweight()));
		objHwColumns.setCwcwtransferpieces(new BigDecimal(objHwColumns.getCwcwpieces()));
		objHwColumns.setCwcwcustomerchargeweight(new BigDecimal(objHwColumns.getCwcwchargeweight()));
		objHwColumns.setOdtdtcode(TdiEnterpriseelementDC.loadByKey(objHwColumns.getEeeecode()).getTdiDistrict().getDtCode());
		
	}	
	
	private String checkPiecesRestrict(String strPgwrestrictdesc,
			String strPvwrestrictdesc,
			List listCorewaybillpieces) throws Exception {
		if (listCorewaybillpieces == null || listCorewaybillpieces.size() < 1)
			return "";
		if (!StringUtility.isNull(strPgwrestrictdesc)) {
			strPgwrestrictdesc = strPgwrestrictdesc.toUpperCase();
		}
		if (!StringUtility.isNull(strPvwrestrictdesc)) {		
			strPvwrestrictdesc = strPvwrestrictdesc.toUpperCase();
		}
		String strOriginGwdesc = strPgwrestrictdesc;
		String strOriginVwdesc = strPvwrestrictdesc;
		
		for (int i = 0; i < listCorewaybillpieces.size(); i++) {
			StringBuffer sbConditionText = new StringBuffer();
			CorewaybillpiecesColumns objCWPColumns = (CorewaybillpiecesColumns)listCorewaybillpieces.get(i);
			if (!StringUtility.isNull(strPgwrestrictdesc)) {
				strPgwrestrictdesc = strPgwrestrictdesc.replaceAll("GW", objCWPColumns.getCpcpgrossweight());
				strPgwrestrictdesc = strPgwrestrictdesc.replaceAll(";", " and ");
				sbConditionText.append("(" + strPgwrestrictdesc + ")");
			}
			if (!StringUtility.isNull(strPvwrestrictdesc)) {
				strPvwrestrictdesc = strPvwrestrictdesc.replaceAll("L", objCWPColumns.getCpcplength());
				strPvwrestrictdesc = strPvwrestrictdesc.replaceAll("W", objCWPColumns.getCpcpwidth());
				strPvwrestrictdesc = strPvwrestrictdesc.replaceAll("H", objCWPColumns.getCpcpheight());
				strPvwrestrictdesc = strPvwrestrictdesc.replaceAll(";", " and ");
				if (!StringUtility.isNull(strPvwrestrictdesc)) {
					sbConditionText.append(" or ");
				}
				sbConditionText.append("(" + strPvwrestrictdesc + ")");
			}
			try {
				String strSqlText = "select count(1) from dual where " + sbConditionText.toString(); 
				CalcweightvalueQuery objCalcWVQuery = new CalcweightvalueQuery();
				List objList = objCalcWVQuery.getResults(strSqlText);
				// 不满足条件
				if (objList != null && objList.size() > 0) {
					CalcweightvalueColumns objCVC = (CalcweightvalueColumns)objList.get(0);
					String strCountReturn = objCVC.getWeightvalue();
					if (strCountReturn.equals("1")) {
						return "该货物的实重与材积不能选择此服务，该件的实重为：" + objCWPColumns.getCpcpgrossweight() +
						"长：" + objCWPColumns.getCpcplength() +
						"宽：" + objCWPColumns.getCpcpwidth() +
						"高：" + objCWPColumns.getCpcpheight() + "。要求：实重：" + strOriginGwdesc + "材积：" + strOriginVwdesc;
					}
				}
			} catch (Exception ex) {
				return "所选服务的实重与材积限制公式设置错误，设置的实重限制公式为：" + strPgwrestrictdesc +
				"，设置的材积限制公式为：" + strPvwrestrictdesc;
			}
		}
		return "";
	}
}
