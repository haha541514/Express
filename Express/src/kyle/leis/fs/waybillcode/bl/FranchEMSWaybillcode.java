package kyle.leis.fs.waybillcode.bl;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.fs.waybillcode.da.WaybillcodeColumns;
import kyle.leis.fs.waybillcode.da.WaybillcodekindColumns;

public class FranchEMSWaybillcode  extends AWaybillcode {
	
	public FranchEMSWaybillcode(WaybillcodekindColumns objWBCKColumns) {
		super(objWBCKColumns);
	}	
	
	public String buildLabelcode(String strCode, 
			String strPrefix, 
			String strSuffix) {
		String strLabelcode = strCode;
		
	    if (strLabelcode.length() < 4) {
	      for (int i = 0; i < 4 - strCode.length(); i++) {
	    	  strLabelcode = "0" + strLabelcode;
	      }
	    }
	    // 加上日期
	    strLabelcode = DateFormatUtility.getDateString(DateFormatUtility.getSysdate(), "yyMMdd") + strLabelcode;
		if (!StringUtility.isNull(strPrefix))
			strLabelcode = strPrefix + strLabelcode;
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
