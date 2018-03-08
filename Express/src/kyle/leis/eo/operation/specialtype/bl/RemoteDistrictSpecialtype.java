package kyle.leis.eo.operation.specialtype.bl;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.receivable.dax.IReceivableBasicData;
import kyle.leis.eo.customerservice.issue.bl.Issue;
import kyle.leis.eo.customerservice.issue.dax.IIssueBasicData;
import kyle.leis.eo.operation.specialtype.da.SpecialtypeColumns;
import kyle.leis.eo.operation.specialtype.dax.ISpecialtypeBasicData;
import kyle.leis.eo.operation.specialtype.dax.SpecialtypeDemand;
import kyle.leis.eo.operation.specialtype.tp.DeleteSpecialtypeTrans;
import kyle.leis.eo.operation.specialtype.tp.SaveSingleSpecialtypeTrans;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.district.bl.DHLRemoteDistrict;
import kyle.leis.fs.dictionary.district.bl.FDXRemoteDistrict;
import kyle.leis.fs.dictionary.district.bl.TNTRemoteDistrict;
import kyle.leis.fs.dictionary.district.bl.UPSRemoteDistrict;
import kyle.leis.fs.dictionary.district.dax.DHLRemoteVerifyResult;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiServerstructuregroup;

public class RemoteDistrictSpecialtype {
	public DHLRemoteVerifyResult verify(String strCwcode,
			String strCountryHubcode, 
			String strPostcode, 
			String strCityname,
			String strChncode) throws Exception {
		if (!StringUtility.isNull(strPostcode))
			strPostcode = strPostcode.toUpperCase().trim();
		
		if (!StringUtility.isNull(strPostcode) && strPostcode.indexOf(" ") >= 0) {
			
			strPostcode = strPostcode.substring(0, strPostcode.indexOf(" "));
			//strPostcode = strPostcode.replaceAll(" ", "");
			// 去掉前面的0
			while (true) {
				if (strPostcode.startsWith("0"))
					strPostcode = strPostcode.substring(1);
				else
					break;
			}
		}
		SpecialtypeColumns objSTColumns = SpecialtypeDemand.load(strCwcode, 
				ISpecialtypeBasicData.SPECIALTYPE_ODA);
		DHLRemoteVerifyResult objDHLRVResult = new DHLRemoteVerifyResult();
		// 如果已经记录ODA则不再检查
		if (objSTColumns != null && 
				!objSTColumns.getCopopid().equals(IReceivableBasicData.OPERID_SYSTEM)) {
			objDHLRVResult.setAutoVerifysign(false);
			objDHLRVResult.setDHLRemoteVerifyResult(true);
			return objDHLRVResult;
		}
		// 查询其渠道是否为DHL渠道
		String strSsgcode = "";
		if (!StringUtility.isNull(strChncode)) {
			TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
			if (objTchnChannel != null) {
				TdiServerstructuregroup objTSSG = objTchnChannel.getTdiServerstructuregroup();
				if (objTSSG == null)
					return  objDHLRVResult;
				strSsgcode = objTSSG.getSsgCode();
				if ((!StringUtility.isNull(strSsgcode) &&
						!strSsgcode.startsWith("DHL") && 
						!strSsgcode.startsWith("FDX")&&
						!strSsgcode.startsWith("TNT")&&
						!strSsgcode.startsWith("UPS")))
					return objDHLRVResult;
				// DHL小包不再效验
				if (strSsgcode.equals("DHL-GlobeMail") || 
						strSsgcode.equals("DHL-USGlobeMail") ||
						strSsgcode.equals("DHL-USPS"))
					return objDHLRVResult;
			}
		}
		
		
		// 检查是否为ODA
		if (strSsgcode.startsWith("DHL")) {
			DHLRemoteDistrict objDHLRemoteDistrict = new DHLRemoteDistrict();
			objDHLRVResult = objDHLRemoteDistrict.verify(strCountryHubcode, 
					strPostcode, 
					strCityname);
		} else if (strSsgcode.startsWith("UPS")){
			UPSRemoteDistrict objUPSRemoteDistrict = new UPSRemoteDistrict();
			objDHLRVResult = objUPSRemoteDistrict.verify(strCountryHubcode, 
					strPostcode, 
					strCityname);
			
		} else if (strSsgcode.startsWith("TNT")){
			TNTRemoteDistrict objTNTRemoteDistrict = new TNTRemoteDistrict();
			objDHLRVResult = objTNTRemoteDistrict.verify(strCountryHubcode, 
					strPostcode, 
					strCityname);
			
		} else {
			FDXRemoteDistrict objFDXRemoteDistrict = new FDXRemoteDistrict();
			objDHLRVResult = objFDXRemoteDistrict.verify(strCountryHubcode, 
					strPostcode, 
					strCityname);			
		}
		// 不是ODA
		// 如果原来是ODA且为系统自动记录则删除之
		if (!objDHLRVResult.getDHLRemoteVerifyResult()) {
			if (objSTColumns != null && 
					objSTColumns.getCopopid().equals(IReceivableBasicData.OPERID_SYSTEM)) {
				DeleteSpecialtypeTrans objDSTrans = new DeleteSpecialtypeTrans();
				objDSTrans.setParam(strCwcode, 
						ISpecialtypeBasicData.SPECIALTYPE_ODA);
				objDSTrans.execute();
				// 清空渠道
				objDHLRVResult.setChannelNullsign(true);
				// SaveHousewaybillTrans objSHBTrans = new SaveHousewaybillTrans();
				// objSHBTrans.setServerChannelNullParam(strCwcode, "0");
				// objSHBTrans.execute();
			}
			return objDHLRVResult;
		}
		// 效验结果是ODA，则记录之
		String strRemark = objDHLRVResult.getRemark();
		// 记录偏远
		SaveSingleSpecialtypeTrans objSSSTrans = new SaveSingleSpecialtypeTrans();
		objSSSTrans.setParam(strCwcode, 
				ISpecialtypeBasicData.SPECIALTYPE_ODA, 
				IReceivableBasicData.OPERID_SYSTEM, 
				strRemark);
		objSSSTrans.execute();
		// 记录问题
		Issue objIssue = new Issue();
		objIssue.addHoldIssue(strCwcode, 
				IIssueBasicData.ISSUE_TYPE_ODA, 
				strRemark, 
				"0");
		// 原来不是ODA，现在是ODA则清空服务渠道
		if (objSTColumns == null) {
			// 清空渠道
			objDHLRVResult.setChannelNullsign(true);
			//SaveHousewaybillTrans objSHBTrans = new SaveHousewaybillTrans();
			//objSHBTrans.setServerChannelNullParam(strCwcode, "0");
			//objSHBTrans.execute();			
		}
		return objDHLRVResult;
	}
}
