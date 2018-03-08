package kyle.leis.eo.operation.corewaybillcode.dax;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybillcode.da.CorewaybillcodeColumns;
import kyle.leis.eo.operation.corewaybillcode.da.CorewaybillcodeCondition;
import kyle.leis.eo.operation.corewaybillcode.da.CorewaybillcodeQuery;

public class CorewaybillcodeDemand {
	
	public static List query(CorewaybillcodeCondition objCBCC) throws Exception {
		CorewaybillcodeQuery objCBCQ = new CorewaybillcodeQuery();
		objCBCQ.setCondition(objCBCC);
		return objCBCQ.getResults();
	}
	
	public static List load(String strCwcode) throws Exception {
		CorewaybillcodeCondition objCBCC = new CorewaybillcodeCondition();
		objCBCC.setCwcode(strCwcode);
		return query(objCBCC);
	}
	
	public static boolean isExistsCustomerewbcode(String strCustomerEwbcode,
			List listResults) {
		if (listResults == null || listResults.size() < 1)
			return false;
		for (int i = 0; i < listResults.size(); i++) {
			CorewaybillcodeColumns objCWBC = (CorewaybillcodeColumns)listResults.get(i);
			if (strCustomerEwbcode.equals(objCWBC.getCwbccwbccustomerewbcode()))
				return true;
		}
		return false;
	}
	
	public static String rebuildCustomerHAWBCode(String strChnCHAWBPrefix, 
			String strCustomerHAWBCode) {
		if (StringUtility.isNull(strChnCHAWBPrefix))
			return strCustomerHAWBCode;
		if (strChnCHAWBPrefix.indexOf("截取前") >= 0) {
            String strLength = strChnCHAWBPrefix.substring("截取前".length());
            if (strCustomerHAWBCode.length() > Integer.parseInt(strLength)) {
                return strCustomerHAWBCode.substring(0, Integer.parseInt(strLength));
            }
        } else if (strChnCHAWBPrefix.indexOf("截取后") >= 0) {
        	String strLength = strChnCHAWBPrefix.substring("截取后".length());
            if (strCustomerHAWBCode.length() > Integer.parseInt(strLength)) {
                return strCustomerHAWBCode.substring(strCustomerHAWBCode.length() - Integer.parseInt(strLength));
            }
        } else if (strChnCHAWBPrefix.indexOf("后缀") >= 0) {
        	String strSuffix = strChnCHAWBPrefix.substring("后缀".length());
            if (!StringUtility.isNull(strSuffix))
                return strCustomerHAWBCode + strSuffix;
        } else if (strChnCHAWBPrefix.indexOf("空") >= 0) {
        	strCustomerHAWBCode = "";
        } else {
        	strCustomerHAWBCode = strChnCHAWBPrefix + strCustomerHAWBCode;
        }
		return strCustomerHAWBCode;
	}
	
}
