package kyle.leis.eo.operation.corewaybill.dax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.WaybillcodeParam;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.corewaybillcode.dax.CorewaybillcodeDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.hi.TchnChannel;

public abstract class RequestXML extends ARequestXML {
	
	private static Map<String, List<PostcodeVerify>> mapPostVerify;
	
	private class PostcodeVerify {
		private String nationcode;
		private String verfiyrule;
		private String length;
		
		public void setNationcode(String nationcode) {
			this.nationcode = nationcode;
		}
		
		public String getNationcode() {
			return nationcode;
		}
		
		public void setVerfiyrule(String verfiyrule) {
			this.verfiyrule = verfiyrule;
		}
		
		public String getVerfiyrule() {
			return verfiyrule;
		}
		
		public void setLength(String length) {
			this.length = length;
		}
		
		public String getLength() {
			return length;
		}		
	}	
	
	public void iniDHLPostVerify() {
		// 读取邮编效验文件
		try {
			if (mapPostVerify == null || mapPostVerify.size() < 1) {
				String strDHLPostcodeVerify = getRequestModelContent("countrypc.txt");
				if (!StringUtility.isNull(strDHLPostcodeVerify)) {
					String[] astrNationalPost = strDHLPostcodeVerify.split("\n");
					mapPostVerify = new HashMap<String, List<PostcodeVerify>>();
					for (String strNationalPost : astrNationalPost) {
						String[] astr = strNationalPost.split("\\|");
						
						PostcodeVerify pv = new PostcodeVerify();
						pv.setNationcode(astr[0]);
						pv.setVerfiyrule(astr[1]);
						pv.setLength(astr[2]);
						List<PostcodeVerify> list;
						
						if (mapPostVerify.containsKey(astr[0])) {
							list = mapPostVerify.get(astr[0]);
							list.add(pv);
						} else {
							list = new ArrayList<PostcodeVerify>();
							list.add(pv);
							mapPostVerify.put(astr[0], list);
						}
					}
				}
			}
		} catch (Exception ex) {
			
		}		
		
	}
	
	public abstract String buildRequestXML(ForinputallColumns objFIAColumns,
			List listCargo,
			List listPieces,
			PromptUtilityCollection objPUCollection) throws Exception;
	
	public WaybillcodeParam buildRequest(ForinputallColumns objFIAColumns,
			List listCargo,
			List listPieces,
			PromptUtilityCollection objPUCollection) throws Exception {
		WaybillcodeParam param = new WaybillcodeParam();
		
		// 根据规则重新获得客户单号
		String strChncode = objFIAColumns.getChncode_Cwspchn();
		String strOldCwcustomerewbcode = objFIAColumns.getCwcustomerewbcode();
		try {
			// 重新获得客户单号
			if (!StringUtility.isNull(strChncode)) {
				TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);		
				objFIAColumns.setCwcustomerewbcode(CorewaybillcodeDemand.rebuildCustomerHAWBCode(objTchnChannel.getChnChawbprefix(),
						objFIAColumns.getCwcustomerewbcode()));	
			}
			param.setWaybillcodeParam(buildRequestXML(objFIAColumns,
					listCargo,
					listPieces,
					objPUCollection));
			return param;
		} finally {
			objFIAColumns.setCwcustomerewbcode(strOldCwcustomerewbcode);
		}
		
	}
	
	public String autoRebuildDHLPostcode(String strNationcode, String strPostcode) {
		// 初始化
		iniDHLPostVerify();
		
		if (mapPostVerify == null || mapPostVerify.size() < 1) {
			return "E_001_没有设置DHL的邮编效验规则";
		}		
		
		if (strNationcode.equals("GB")) {
			if (strPostcode.indexOf("-") > 0) {
				if (strPostcode.indexOf(" ") > 0)
					strPostcode = strPostcode.replace(" ", "");
				strPostcode = strPostcode.replace("-", " ");
			}
			return strPostcode;
		}
		
		String[] astrNeedVerifyNation = new String[] {"PL", "SE", "CZ", "GR", "SK", "JP"};
		boolean isNeedVerify = false;
		for (String strNeedVerifyNation : astrNeedVerifyNation) {
			if (strNationcode.equals(strNeedVerifyNation))
				isNeedVerify = true;
		}
		if (!isNeedVerify) {
			return strPostcode;
		}
		if (mapPostVerify.containsKey(strNationcode)) {
			List<PostcodeVerify> listPostcodeVerify = mapPostVerify.get(strNationcode);
			if (listPostcodeVerify != null && listPostcodeVerify.size() > 0) {
				for (PostcodeVerify pv : listPostcodeVerify) {
					if (pv.getVerfiyrule().indexOf(" ") > 0 || pv.getVerfiyrule().indexOf("-") > 0)  {
						String strReplaceVerfiyrule = pv.getVerfiyrule().replace(" ", "").replace("-", "");
						if (strPostcode.length() == strReplaceVerfiyrule.length()) {
							return autoAddPostcode(pv.getVerfiyrule(), strPostcode);
						}
					}
				}
			}
		}
		return strPostcode;
	}
	
	private String autoAddPostcode(String strVerifyrule, String strPostcode) {
		char[] achar = new char[strVerifyrule.length()];
		int iSpecialComma = 0;
		for (int i = 0; i < strVerifyrule.length(); i++) {
			char c = strVerifyrule.charAt(i);
			if (c == ' ' || c == '-') {
				achar[i] = c;
				iSpecialComma++;
			} else {
				achar[i] = strPostcode.charAt(i - iSpecialComma);
			}
		}
		return new String(achar);
	}	

}
