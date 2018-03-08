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
		// 重新获得计费重量
		ChargeweightParameter objCWParameter = CorewaybillDemand.transferToCWParameter(objOriginHwColumns, 
				listCorewaybillpieces);
		Chargeweight objChargeweight = new Chargeweight();
		ChargeweightResult objCWResult = objChargeweight.calculate(objCWParameter);
		objOriginHwColumns.setCwcwchargeweight(new BigDecimal(objCWResult.getChargeweight()));
		// 由于Fedex的实重有限制所以需要重新计算实重
		if (!StringUtility.isNull(objCWResult.getGrossweight()))
			objOriginHwColumns.setCwcwgrossweight(new BigDecimal(objCWResult.getGrossweight()));
		if (new BigDecimal(strOriginChargeweight).compareTo(new BigDecimal(objCWResult.getChargeweight())) != 0)
			isChangeServerHawb = true;
		// 保存
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
					"不存在的运单", 
					"Housewaybill.modify");
		// 检查客户运单是否重复
		/*
		PromptUtilityCollection objCheckEwbcode = objCoreWayBillCheck.checkCustomerEwbcode(objHwColumns.getCwcwcustomerewbcode(), 
				objHwColumns.getHwcwcode());
		objPUCollection.addAll(objCheckEwbcode);
		*/
		/* 不同的业务模式，修改不同
		if (ReceivableDemand.isExistsUnModifyFee(objHwColumns.getHwcwcode()))
			objPUCollection.add("E_SIGNIN_008", 
					"该票件已经出账或核销不允许再修改费用", 
					"Housewaybill.modify");
		*/
		SignIn objSignIn = new SignIn();
		// 收货产品效验
		String strErrorInfo = objSignIn.checkRestrictByPK(objHwColumns,
				listCorewaybillpieces);
		if (!StringUtility.isNull(strErrorInfo)) {
			objPUCollection = new PromptUtilityCollection();
			objPUCollection.add("E_MODIFY_001", 
					strErrorInfo, 
					"SignIn.save");
		}	
		boolean isRecalc = true;
		// 服务商对账中修改收货重量
		if (!StringUtility.isNull(strModifyRemark) && 
				strModifyRemark.equals("SRM")) {
			strModifyRemark = "";
			isRecalc = false;
		}
		if (objPUCollection.canGo(isIgnoreNotice)) {
			// 保存
			SaveWaybillTransaction objSignInTrans = new SaveWaybillTransaction();
			objSignInTrans.setSignInParam(objHwColumns, 
					listCorewaybillpieces, 
					listIssueColumns, 
					strOperId);
			objSignInTrans.execute();
			Long lNewCwcode = objSignInTrans.getNewCwcode();
			objSRUtility.setColumns(HousewaybillDemand.loadByCwcode(String.valueOf(lNewCwcode)));
			// 记录备注内容
			Businesslog objBusinesslog = new Businesslog();
			objBusinesslog.addBusinessLog(objHwColumns.getHwcwcode(), 
					strOperId, 
					strModifyRemark);
			//记录超长超重特殊类型
			Specialtype objSpecialtype = new Specialtype();
			objSpecialtype.addOverLengthSpecialtype(listCorewaybillpieces,strOperId,String.valueOf(lNewCwcode));
			// 计费
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
		// 判断服务商运单号是否一致，如果不一致则认为修改服务商运单
		boolean isFlushServerchannel = true;
		if (!objOriginHwColumns.getCwcwserverewbcode().equals(objHwColumns.getCwcwserverewbcode()))
			isFlushServerchannel = false;
		// 免费件则不清空服务渠道
		if (objOriginHwColumns.getPmpmcode().equals("AFR"))
			isFlushServerchannel = false;		
		if (StringUtility.isNull(objHwColumns.getEeeecode()))
			objHwColumns.setEeeecode(objOriginHwColumns.getEeeecode());		
		// 获得计费重量
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
		// 由于Fedex的实重有限制所以需要重新计算实重
		if (!StringUtility.isNull(objCWResult.getGrossweight()))
			objHwColumns.setCwcwgrossweight(new BigDecimal(objCWResult.getGrossweight()));
		
		objHwColumns.setCwcwvolumerate(Integer.parseInt(objCWResult.getVolumeRate()));
		objHwColumns.setAbwadddate(DateFormatUtility.getStandardDate(objOriginHwColumns.getAbwadddate()));
		objHwColumns.setCcococode(objOriginHwColumns.getCcococode());
		// 设置公司运单号、服务商运单号
		objHwColumns.setCwcwcustomerewbcode(objHwColumns.getCwcwcustomerewbcode());
		objHwColumns.setCwcwewbcode(objHwColumns.getCwcwewbcode());
		objHwColumns.setCwcwserverewbcode(objHwColumns.getCwcwserverewbcode());
		// 设置中转重量、服务商重量	
		objHwColumns.setCwcwtransferchargeweight(new BigDecimal(objOriginHwColumns.getCwcwtransferchargeweight()));
		objHwColumns.setCwcwserverchargeweight(new BigDecimal(objHwColumns.getCwcwserverchargeweight()));
		objHwColumns.setCwcwtransfergrossweight(new BigDecimal(objOriginHwColumns.getCwcwtransfergrossweight()));
		objHwColumns.setCwcwtransferpieces(new BigDecimal(objOriginHwColumns.getCwcwtransferpieces()));
		
		// 未出货则清空服务渠道
		if (StringUtility.isNull(objOriginHwColumns.getDbwbwcode()) && isFlushServerchannel)
			objHwColumns.setSchnchncode("null");
	}
	
	/**
	 * 打印Label
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
			strLabelPrintremark = "打印标签";
		// 记录备注
		Businesslog objBusinesslog = new Businesslog();
		objBusinesslog.addBusinessLog(strCwcode, 	
				strOperId, 
				strLabelPrintremark);				
		
	}
	
	/**
	 * 修改服务渠道
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
	 * 作废
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
					"该票件已经出账或核销不允许再作废", 
					"Housewaybill.eliminate");
		if (objPUCollection.canGo(true)) {
			SaveHousewaybillTrans objSHWBTrans = new SaveHousewaybillTrans();
			objSHWBTrans.setEliminateParam(strCwcode, strOperId);
			objSHWBTrans.execute();
			// 删除应收费用
			Receivable objReceivable = new Receivable();
			objReceivable.deleteAll(strCwcode, "0");
			// 删除应付费用
			Payable objPayable = new Payable();
			objPayable.deleteAll(strCwcode, "0");			
		}
		return objPUCollection;
	}	
	
	/**
	 * 撤销出货
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
		// 记录备注内容
		Businesslog objBusinesslog = new Businesslog();
		objBusinesslog.addBusinessLog(strCwcode, 
				strOperId, 
				strRemark);		
	}
	
	/**
	 * 打印发票
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
	 * 撤销退件
	 * @param strCwcode	 
	 * @throws Exception
	 */
	public void undoBackprice(String strCwcode,
			String strOperId) throws Exception {
		SaveHousewaybillTrans objSHTrans = new SaveHousewaybillTrans();
		objSHTrans.setUndoBackpriceParam(strCwcode,
				strOperId);
		objSHTrans.execute();
		
		// 记录备注内容
		Businesslog objBusinesslog = new Businesslog();
		objBusinesslog.addBusinessLog(strCwcode, 
				strOperId, 
				"撤销退件");
		// 自动计费
		AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(strCwcode,
				"",
				false);			
		objAFCThread.start();   		
		
	}
	
	/**
	 * 撤销成已打印状态
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
	 * 核查重量
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
		// 只记录轨迹点则只保存核查主单号
		// 创建到货主单
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
		// 重新保存新的到货主单
		objHwColumns.setAbwbwcode(Long.parseLong(strNewArrivalBwcode));
		// 客户重量
		BigDecimal iCusWeight = new BigDecimal(objHwColumns.getCwcwcustomerchargeweight());
		List listIssueColumns = new ArrayList();
		//扣件标记
		String strFastenersmark = "N";
		//检查重量
		ChargeweightParameter objCWParameter = CorewaybillDemand.transferToCWParameter(objHwColumns, 
				listCorewaybillpieces);
		Chargeweight objChargeweight = new Chargeweight();
		ChargeweightResult objCWResult = objChargeweight.calculate(objCWParameter);
		BigDecimal iCheckWeight = new BigDecimal(objCWResult.getChargeweight());
		BigDecimal iGrossWeight = new BigDecimal(objHwColumns.getCwcwgrossweight());
		//客户重量与检查重量相等
		if(iCusWeight.compareTo(iCheckWeight) == 0) {
			objHwColumns.setCwcwchargeweight(iCheckWeight);
			// objHwColumns.setCwcwgrossweight(iCusWeight);			
			objHwColumns.setHwhwweightcheckkind("P");
		}else{
			objHwColumns.setHwhwweightcheckkind("N");
			//更改系统重量为客户重量
			// objHwColumns.setCwcwchargeweight(iCusWeight);
			// objHwColumns.setCwcwgrossweight(iCusWeight);
			//保存原所有件实重以及长宽高信息
			List listCWBP = CorewaybillpiecesDemand.load(objHwColumns.getHwcwcode());
			Businesslog objBusinesslog = new Businesslog();
			objBusinesslog.addBusinessLog(objHwColumns.getHwcwcode(),strOperId,listCWBP);
			
			strFastenersmark = "N";
			//修改系统的重量为核查重量
			objHwColumns.setCwcwchargeweight(iCheckWeight);
			objHwColumns.setCwcwgrossweight(iGrossWeight);
			// 清空渠道	
			if (!SystempropertyDemand.getEnterprise().startsWith("SBD")) {
				objHwColumns.setSchnchncode("null");
			}
			//记录快件问题
			if (isHold) {
				IssueColumns objIssueColumns = new IssueColumns();
				objIssueColumns.setCwcwcode(Long.parseLong(objHwColumns.getHwcwcode()));
				objIssueColumns.setIsutisutcode("110");
				objIssueColumns.setIhsihscode("CH");
				objIssueColumns.setIsuiscontent(objHwColumns.getCwcwcustomerewbcode()+"的运单，我司收货重量为"+iCheckWeight+"，贵司重量为"+iCusWeight+"，重量不符，请确认重量差异。谢谢。");
				listIssueColumns.add(objIssueColumns);
				strFastenersmark = "Y";
			}
		}
		//保存核查信息
		objHwColumns.setHwhwweightcheckdate(DateFormatUtility.getSysdate());
		objHwColumns.setHwhwopidweightcheck(Long.parseLong(strOperId));
		//保存
		SaveWaybillTransaction objSignInTrans = new SaveWaybillTransaction();
		objSignInTrans.setCheckweight(objHwColumns, 
				listCorewaybillpieces, 
				listIssueColumns,
				strWeightCheckBWCode,
				strOperId);
		objSignInTrans.execute();
		// 超长超重
		Specialtype objSpecialtype = new Specialtype();
		objSpecialtype.addOverLengthSpecialtype(listCorewaybillpieces,
				strOperId,
				objHwColumns.getHwcwcode());		
		
        String strResult = objHwColumns.getHwhwweightcheckkind().concat(strFastenersmark);
        // 重量不符则重新计费
        //if (objHwColumns.getHwhwweightcheckkind().equals("N")) {
			// 自动计费
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
