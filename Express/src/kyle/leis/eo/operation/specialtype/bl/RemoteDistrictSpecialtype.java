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
			// ȥ��ǰ���0
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
		// ����Ѿ���¼ODA���ټ��
		if (objSTColumns != null && 
				!objSTColumns.getCopopid().equals(IReceivableBasicData.OPERID_SYSTEM)) {
			objDHLRVResult.setAutoVerifysign(false);
			objDHLRVResult.setDHLRemoteVerifyResult(true);
			return objDHLRVResult;
		}
		// ��ѯ�������Ƿ�ΪDHL����
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
				// DHLС������Ч��
				if (strSsgcode.equals("DHL-GlobeMail") || 
						strSsgcode.equals("DHL-USGlobeMail") ||
						strSsgcode.equals("DHL-USPS"))
					return objDHLRVResult;
			}
		}
		
		
		// ����Ƿ�ΪODA
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
		// ����ODA
		// ���ԭ����ODA��Ϊϵͳ�Զ���¼��ɾ��֮
		if (!objDHLRVResult.getDHLRemoteVerifyResult()) {
			if (objSTColumns != null && 
					objSTColumns.getCopopid().equals(IReceivableBasicData.OPERID_SYSTEM)) {
				DeleteSpecialtypeTrans objDSTrans = new DeleteSpecialtypeTrans();
				objDSTrans.setParam(strCwcode, 
						ISpecialtypeBasicData.SPECIALTYPE_ODA);
				objDSTrans.execute();
				// �������
				objDHLRVResult.setChannelNullsign(true);
				// SaveHousewaybillTrans objSHBTrans = new SaveHousewaybillTrans();
				// objSHBTrans.setServerChannelNullParam(strCwcode, "0");
				// objSHBTrans.execute();
			}
			return objDHLRVResult;
		}
		// Ч������ODA�����¼֮
		String strRemark = objDHLRVResult.getRemark();
		// ��¼ƫԶ
		SaveSingleSpecialtypeTrans objSSSTrans = new SaveSingleSpecialtypeTrans();
		objSSSTrans.setParam(strCwcode, 
				ISpecialtypeBasicData.SPECIALTYPE_ODA, 
				IReceivableBasicData.OPERID_SYSTEM, 
				strRemark);
		objSSSTrans.execute();
		// ��¼����
		Issue objIssue = new Issue();
		objIssue.addHoldIssue(strCwcode, 
				IIssueBasicData.ISSUE_TYPE_ODA, 
				strRemark, 
				"0");
		// ԭ������ODA��������ODA����շ�������
		if (objSTColumns == null) {
			// �������
			objDHLRVResult.setChannelNullsign(true);
			//SaveHousewaybillTrans objSHBTrans = new SaveHousewaybillTrans();
			//objSHBTrans.setServerChannelNullParam(strCwcode, "0");
			//objSHBTrans.execute();			
		}
		return objDHLRVResult;
	}
}
