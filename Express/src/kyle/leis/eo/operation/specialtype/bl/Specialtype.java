package kyle.leis.eo.operation.specialtype.bl;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.feecalculate.blx.AutoFeeCalculateThread;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.customerservice.issue.bl.Issue;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillColumns;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.specialtype.dax.ISpecialtypeBasicData;
import kyle.leis.eo.operation.specialtype.dax.SpecialtypeDemand;
import kyle.leis.eo.operation.specialtype.tp.DeleteSpecialtypeTrans;
import kyle.leis.eo.operation.specialtype.tp.SaveSingleSpecialtypeTrans;
import kyle.leis.eo.operation.specialtype.tp.SaveSpecialtypeTrans;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.businesslog.bl.Businesslog;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiDistrictDC;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiDistrict;

public class Specialtype {
	/**
	 * 保存某项特殊类型
	 * @param strCwcode
	 * @param strEstcode
	 * @param strOperId
	 * @param strRemark
	 * @return
	 * @throws Exception
	 */
	public List addSpecialtype(String strCwcode, 
			String strEstcode, 
			String strOperId, 
			String strRemark,
			boolean isRecalFee) throws Exception {
		SaveSingleSpecialtypeTrans objSSSTTrans = new SaveSingleSpecialtypeTrans();
		objSSSTTrans.setParam(strCwcode, 
				strEstcode, 
				strOperId, 
				strRemark);
		objSSSTTrans.execute();
		// 计费
		if (isRecalFee) {
			AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(strCwcode,
					IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
					"",
					true);			
			objAFCThread.start();
		}
		return SpecialtypeDemand.load(strCwcode);
	}
	
	public void addRestrictedElevatedRisk(String strCwcode,
			String strDtcode,
			String strSsgcode,
			String strOperId) throws Exception {
		if (!StringUtility.isNull(strSsgcode) &&
				strSsgcode.startsWith("DHL") &&
				!strSsgcode.equals("DHL-GlobeMail") &&
				!strSsgcode.equals("DHL-USGlobeMail") &&				
				!StringUtility.isNull(strDtcode)) {
			TdiDistrict td = TdiDistrictDC.loadByKey(strDtcode);
			String strIssueRemark = "";
			if ("Y".equals(td.getDtRestrictedsign())) {
				addSpecialtype(strCwcode, 
						ISpecialtypeBasicData.SPECIALTYPE_RESTRICTEDDT, 
						strOperId, 
						"限运地区", 
						false);
				strIssueRemark = "目的国家为" + td.getDtName() + "是限运目的地，需要加收限运目的地附加费而扣件";
			} else {
				// 删除
				delete(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_RESTRICTEDDT);
			}
			if ("Y".equals(td.getDtElevatedrisksign())) {
				addSpecialtype(strCwcode, 
						ISpecialtypeBasicData.SPECIALTYPE_ELEVATEDRISK, 
						strOperId, 
						"高风险地区", 
						false);
				if (!StringUtility.isNull(strIssueRemark)) {
					strIssueRemark = "目的国家为" + td.getDtName() + "是限运目的地和高风险地区，需要加收限运目的地附加费和高风险地区而扣件";
				} else { 
					strIssueRemark = "目的国家为" + td.getDtName() + "是高风险地区，需要加收高风险地区附加费而扣件";
				}
			} else {
				// 删除
				delete(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_ELEVATEDRISK);
			}
			if (!StringUtility.isNull(strIssueRemark)) { 
				Issue objIssue= new Issue();
				objIssue.addHoldIssue(strCwcode, String.valueOf(502), strIssueRemark, strOperId);
			}	
			return;
		}
		// 都不是，则删除限运地区和高风险地区的特殊类型
		delete(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_ELEVATEDRISK);
		delete(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_RESTRICTEDDT);
	}	
	
	
	
	/**
	 * 超值报关
	 * @param strCwcode
	 * @param strEstcode
	 * @param strOperId
	 * @param strRemark
	 * @throws Exception
	 */
	public void addOverDeclare(String strCwcode, 
			String strTotalDeclare,
			String strCkcode,
			String strOperId,
			String strChncode) throws Exception {
		if (StringUtility.isNull(strTotalDeclare) || StringUtility.isNull(strCkcode))
			return;
		BigDecimal objStandardDeclarevalue = new BigDecimal("128");
		if (!StringUtility.isNull(strChncode)) {
			TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
			if (objTchnChannel.getChnMaxoverdeclarevalue() != null &&
					objTchnChannel.getChnMaxoverdeclarevalue().compareTo(new BigDecimal("0")) > 0)
					objStandardDeclarevalue = objTchnChannel.getChnMaxoverdeclarevalue();
		}
		if (strCkcode.equals("USD") &&	
				new BigDecimal(strTotalDeclare).compareTo(objStandardDeclarevalue) >= 0) {
			boolean blIsNotCustomer = SpecialtypeDemand.isExistEst(ISpecialtypeBasicData.SPECIALTYPE_OVER_DECLARE);
			if (blIsNotCustomer) {
				SaveSingleSpecialtypeTrans objSSSTTrans = new SaveSingleSpecialtypeTrans();
				objSSSTTrans.setParam(strCwcode, 
						ISpecialtypeBasicData.SPECIALTYPE_OVER_DECLARE, 
						strOperId, 
						"系统自动记录超值报关");
				objSSSTTrans.execute();
			}
		}
	}	
	
	
	
	/**
	 * 记录超长超重特殊类型
	 * @param listCorewaybillpieces
	 * @param strOperId
	 * @param strCwcode
	 * @throws Exception
	 */
	public void addOverLengthSpecialtype(List listCorewaybillpieces,
			String strOperId,
			String strCwcode) throws Exception {
		SimplecorewaybillColumns objSCWBColumns = CorewaybillDemand.loadSimpleCorewaybill(strCwcode);
		String strPkcode = objSCWBColumns.getCwpk_code();
		
		// 判断是否为非客户，判断逻辑：
		// 如果存在超长超重类型，则为非客户。
		// 因为客户里的类型为超长类型和超重类型两种，只有非客户才有超长超重类型
		boolean blIsNotCustomer = SpecialtypeDemand.isExistEst(ISpecialtypeBasicData.SPECIALTYPE_OVER_LW);
		
		if (strPkcode.startsWith("D")) {
			addDHLOverLengthSpecialtype(listCorewaybillpieces, strOperId, 
					strCwcode, blIsNotCustomer);
		} else if (strPkcode.startsWith("T")) {
			addTNTOverLengthSpecialtype(listCorewaybillpieces, strOperId, 
					strCwcode, blIsNotCustomer);
		} else if (strPkcode.startsWith("A07")) {
			// BA的DHL产品A07开头
			String strSystemEe = SystempropertyDemand.getEnterprise();
			if (strSystemEe.startsWith("QQYX")) {
				addDHLOverLengthSpecialtype(listCorewaybillpieces, strOperId, 
						strCwcode, blIsNotCustomer);				
			}
		}
		
		
	}
	
	private void addDHLOverLengthSpecialtype(List listCorewaybillpieces,
			String strOperId,
			String strCwcode,
			boolean blIsNotCustomer) throws Exception {
		BigDecimal objLength = new BigDecimal("0");
		BigDecimal objHeight = new BigDecimal("0");
		BigDecimal objWidth = new BigDecimal("0");
		BigDecimal objGrossweight = new BigDecimal("0");
		BigDecimal objStandardLength = new BigDecimal("120");
		BigDecimal objStandardGrossweight = new BigDecimal("70");
		//是否已记超长，false为没有记
		boolean blLengthMark = false;
		//是否已记超重，false为没有记
		boolean blGrossweightMark = false;
		
		for(CorewaybillpiecesColumns objCWBPColumns:(List<CorewaybillpiecesColumns>)listCorewaybillpieces)
		{
			Specialtype objSpecialtype = new Specialtype();
			objLength = new BigDecimal(objCWBPColumns.getCpcplength());
			objHeight = new BigDecimal(objCWBPColumns.getCpcpheight());
			objWidth = new BigDecimal(objCWBPColumns.getCpcpwidth());
			objGrossweight = new BigDecimal(objCWBPColumns.getCpcpgrossweight());
			//记超长
			if(!blLengthMark && 
					(objLength.compareTo(objStandardLength) >= 0 || 
							objHeight.compareTo(objStandardLength) >= 0 || 
							objWidth.compareTo(objStandardLength) >= 0 ))
			{
				//非客户
				if(blIsNotCustomer)
					objSpecialtype.addSpecialtype(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_OVER_LW, strOperId, "超长超重特殊类型", false);
				else
					objSpecialtype.addSpecialtype(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_OVER_L, strOperId, "超长超重特殊类型", false);
				blLengthMark = true;
				
				Issue objIssue= new Issue();
				objIssue.addHoldIssue(strCwcode, String.valueOf(212), "单件重量超过:+"+objStandardGrossweight+"或单边长度大于:"+objStandardLength+",需要确认超长超重附加费。", strOperId);				
			}
			//记超重
			if(objGrossweight.compareTo(objStandardGrossweight) >= 0)
			{
				//如果非客户并且没有记超长，超重时则记超重。有超长则不记超重,默认使用已记的超长。
				if(!blLengthMark && blIsNotCustomer)
					objSpecialtype.addSpecialtype(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_OVER_LW, strOperId, "超长超重特殊类型", false);
				//如果是客户，超长记超长，超重记超重。超长且超重，则默认使用已记的超长。(因为超长费和超重费都是一样的金额)
				else if(!blIsNotCustomer && !blLengthMark)
					objSpecialtype.addSpecialtype(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_OVER_W, strOperId, "超长超重特殊类型", false);
				
				Issue objIssue= new Issue();
				objIssue.addHoldIssue(strCwcode, String.valueOf(212), "单件重量超过:+"+objStandardGrossweight+"或单边长度大于:"+objStandardLength+",需要确认超长超重附加费。", strOperId);				
				blGrossweightMark = true;	
			}
			//只记录一件超长超重货物即可
			if(blLengthMark || blGrossweightMark)
				break;
		}		
	}
	
	private void addTNTOverLengthSpecialtype(List listCorewaybillpieces,
			String strOperId,
			String strCwcode,
			boolean blIsNotCustomer) throws Exception {
		BigDecimal objLength = new BigDecimal("0");
		BigDecimal objHeight = new BigDecimal("0");
		BigDecimal objWidth = new BigDecimal("0");
		BigDecimal objGrossweight = new BigDecimal("0");
		BigDecimal objStandardLength = new BigDecimal("120");
		BigDecimal objOGStandardLength = new BigDecimal("120");
		BigDecimal objStandardGrossweight = new BigDecimal("30");
		//是否已记超长，false为没有记
		boolean blLengthMark = false;
		
		for(CorewaybillpiecesColumns objCWBPColumns : (List<CorewaybillpiecesColumns>)listCorewaybillpieces) {
			Specialtype objSpecialtype = new Specialtype();
			objLength = new BigDecimal(objCWBPColumns.getCpcplength());
			objHeight = new BigDecimal(objCWBPColumns.getCpcpheight());
			objWidth = new BigDecimal(objCWBPColumns.getCpcpwidth());
			objGrossweight = new BigDecimal(objCWBPColumns.getCpcpgrossweight());
			String strRemark = "";
			//记超长
			if(objGrossweight.compareTo(objStandardGrossweight) < 0) {
				if (objLength.compareTo(objStandardLength) >= 0 || 
						objHeight.compareTo(objStandardLength) >= 0 || 
						objWidth.compareTo(objStandardLength) >= 0 ) {
						blLengthMark = true;
						strRemark = "单件重量小于：" + objStandardGrossweight + "，长度大于" + objStandardLength + "，需要加收超长费用。";
				}
			} else {
				if (objLength.compareTo(objOGStandardLength) >= 0 || 
						objHeight.compareTo(objOGStandardLength) >= 0 || 
						objWidth.compareTo(objOGStandardLength) >= 0 ) {
						blLengthMark = true;
						strRemark = "单件重量大于或等于：" + objStandardGrossweight + "，长度大于" + objOGStandardLength + "，需要加收超长费用。";
				}
			}
			if (blLengthMark) {
				//非客户
				if(blIsNotCustomer)
					objSpecialtype.addSpecialtype(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_OVER_LW, strOperId, "超长超重特殊类型", false);
				else
					objSpecialtype.addSpecialtype(strCwcode, ISpecialtypeBasicData.SPECIALTYPE_OVER_L, strOperId, "超长超重特殊类型", false);
				
				Issue objIssue= new Issue();
				objIssue.addHoldIssue(strCwcode, "212", strRemark, strOperId);
				
				break;
			}
		}		
	}	
	
	
	/**
	 * 删除特殊类型
	 * @param strCwcode
	 * @param strEstcode
	 * @throws Exception
	 */
	public void delete(String strCwcode, 
			String strEstcode) throws Exception {
		DeleteSpecialtypeTrans objDSTrans = new DeleteSpecialtypeTrans();
		objDSTrans.setParam(strCwcode, strEstcode);
		objDSTrans.execute();
	}
	
	/**
	 * 保存
	 * @param listSpecialtypes
	 * @param strOperId
	 * @param strCwcode
	 * @return
	 * @throws Exception
	 */
	public List save(List listSpecialtypes, 
			String strOperId,
			String strCwcode) throws Exception {
		// 记录日志
		List listOriginSpecialtype = SpecialtypeDemand.load(strCwcode);
		SaveSpecialtypeTrans objSSPTTrans = new SaveSpecialtypeTrans();
		objSSPTTrans.setParam(listSpecialtypes,
				strCwcode,
				strOperId,
				true);
		objSSPTTrans.execute();	
		// 记录备注
		List listSpecialtype =  SpecialtypeDemand.load(strCwcode);
		String strBusinessLog = "原有的特殊类型为：" + SpecialtypeDemand.getBusinessLog(listOriginSpecialtype);
		strBusinessLog = strBusinessLog + 
		"，新特殊类型为：" + SpecialtypeDemand.getBusinessLog(listSpecialtype);
		Businesslog objBusinesslog = new Businesslog();
		objBusinesslog.addBusinessLog(strCwcode, strOperId, strBusinessLog);
		// DDP自动扣件
		if (SpecialtypeDemand.isExistsSpecialtype(listSpecialtype, "A0201")) {
			Issue objIssue= new Issue();
			objIssue.addHoldIssue(strCwcode, 
					"313", 
					"此件运单注明发件人支付关税，请确认是否一定由发件人支付关税。确认由发件人支付关税时，贵司需支付一定金额的付税保证金，故请务必在确认后，及时与我司联系，处理相关事宜，谢谢。", 
					strOperId);		
		}
		// 计费
		AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(strCwcode,
				IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
				"",
				true);			
		objAFCThread.start();		
		// 返回值
		return listSpecialtype;
	}
}
