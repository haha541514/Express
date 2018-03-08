package kyle.leis.fs.waybillcode.bl;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.fs.waybillcode.da.WaybillcodeColumns;
import kyle.leis.fs.waybillcode.da.WaybillcodekindColumns;

public class DHLGlobeCoreWaybillcode extends AWaybillcode {
	
	public DHLGlobeCoreWaybillcode(WaybillcodekindColumns objWBCKColumns) {
		super(objWBCKColumns);
	}	
	
	public String buildLabelcode(String strCode, 
			String strPrefix, 
			String strSuffix) {
		String strLabelcode = strCode;
		
	    if (strLabelcode.length() < 12) {
	      for (int i = 0; i < 12 - strCode.length(); i++) {
	    	  strLabelcode = "0" + strLabelcode;
	      }
	    }
		if (!StringUtility.isNull(strPrefix))
			strLabelcode = strPrefix + strLabelcode;
		if (!StringUtility.isNull(strSuffix))
			strLabelcode = strLabelcode + strSuffix;
		return strLabelcode;		
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
