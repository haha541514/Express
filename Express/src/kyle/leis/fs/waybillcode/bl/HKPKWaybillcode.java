package kyle.leis.fs.waybillcode.bl;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.fs.waybillcode.da.WaybillcodeColumns;
import kyle.leis.fs.waybillcode.da.WaybillcodekindColumns;

public class HKPKWaybillcode extends AWaybillcode {
	
	public HKPKWaybillcode(WaybillcodekindColumns objWBCKColumns) {
		super(objWBCKColumns);
	}	
	
	public String buildLabelcode(String strCode, 
			String strPrefix, 
			String strSuffix) {
		String strLabelcode = strCode;
		
	    if (strLabelcode.length() < 8) {
	      for (int i = 0; i < 8 - strCode.length(); i++) {
	    	  strLabelcode = "0" + strLabelcode;
	      }
	    }
		strLabelcode = strLabelcode + getVerifyNumber(strLabelcode);
		
		if (!StringUtility.isNull(strPrefix))
			strLabelcode = strPrefix + strLabelcode;
		if (!StringUtility.isNull(strSuffix))
			strLabelcode = strLabelcode + strSuffix;	
		return strLabelcode;
	}
	
	  public int getVerifyNumber(String strSeriesNo) {
	    int iVerifyCode = 8 * Integer.parseInt(strSeriesNo.substring(0, 1)) +
	        6 * Integer.parseInt(strSeriesNo.substring(1, 2)) +
	        4 * Integer.parseInt(strSeriesNo.substring(2, 3)) +
	        2 * Integer.parseInt(strSeriesNo.substring(3, 4)) +
	        3 * Integer.parseInt(strSeriesNo.substring(4, 5)) +
	        5 * Integer.parseInt(strSeriesNo.substring(5, 6)) +
	        9 * Integer.parseInt(strSeriesNo.substring(6, 7)) +
	        7 * Integer.parseInt(strSeriesNo.substring(7));

	    iVerifyCode = 11 - iVerifyCode % 11;
	    if (iVerifyCode == 11) iVerifyCode = 5;
	    else if (iVerifyCode == 10) iVerifyCode = 0;
	    return iVerifyCode;
	  }	
	
	public PromptUtilityCollection checkLabelcode(String strLabelcode) {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		
		return objPUCollection;
	}
	
	protected PromptUtilityCollection checkRegister(WaybillcodeColumns objWaybillcodeColumns) {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		
		return objPUCollection;
	}

}
