package kyle.leis.fs.waybillcode.bl;

import java.math.BigDecimal;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.fs.waybillcode.da.WaybillcodeColumns;
import kyle.leis.fs.waybillcode.da.WaybillcodekindColumns;

public class DGMCoreWaybillcode extends AWaybillcode {

	public DGMCoreWaybillcode(WaybillcodekindColumns objWBCKColumns) {
		super(objWBCKColumns);
	}		
	
	@Override
	public String buildLabelcode(String strCode, 
			String strPrefix, 
			String strSuffix) {
		String strLabelcode = strCode;
		String strComplexPrefix = "";
		if (m_objComplexPrefix != null) {
			try {
				strComplexPrefix = m_objComplexPrefix.buildPrefix();
			} catch (Exception ex) { 
				ex.printStackTrace();
			}
		}		
		String strVerifyNumber = getVerifyNumber(strLabelcode, strComplexPrefix);
		if (!StringUtility.isNull(strComplexPrefix))
			strLabelcode = strComplexPrefix + strLabelcode;	
		
		strLabelcode = strLabelcode + strVerifyNumber;
		if (!StringUtility.isNull(strPrefix))
			strLabelcode = strPrefix + strLabelcode;
		if (!StringUtility.isNull(strSuffix))
			strLabelcode = strLabelcode + strSuffix;	
		return strLabelcode;
	}
	
	  public String getVerifyNumber(String strSeriesNo, String strComplexPrefix) {
		  String strLabelcode = strSeriesNo;
		  if (strLabelcode.length() < 11) {
		      for (int i = 0; i < 11 - strSeriesNo.length(); i++) {
		    	  strLabelcode = "0" + strLabelcode;
		      }
		  }
		  strSeriesNo = strLabelcode;
		  if (!StringUtility.isNull(strComplexPrefix))
			  strSeriesNo = strComplexPrefix + strSeriesNo;		
		  	  
		  strSeriesNo = strSeriesNo.substring(strSeriesNo.length() - 25);
		  char[] achar = strSeriesNo.toCharArray();
		  int iStep2 = 0;
		  int iStep3 = 0;
		  for (int i = strSeriesNo.length() - 1; i >= 0; i--) {
			  if (i % 2 == 0)
				  iStep2 = iStep2 + Integer.parseInt(String.valueOf(achar[i]));
			  if ((i + 1)% 2 == 0)
				  iStep3 = iStep3 + Integer.parseInt(String.valueOf(achar[i]));
		  }
		  iStep2 = iStep2 * 3 + iStep3;
		  // 得到除以10最近的整数
		  BigDecimal obj = new BigDecimal(iStep2).divide(new BigDecimal("10"), 0, 2);
		  BigDecimal objVerifyNumber = obj.multiply(new BigDecimal("10")).add(new BigDecimal(iStep2).multiply(new BigDecimal("-1")));
		  return objVerifyNumber.toString();
	  }
	
	@Override
	public PromptUtilityCollection checkLabelcode(String strLabelcode) {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		
		return objPUCollection;
	}

	@Override
	protected PromptUtilityCollection checkRegister(WaybillcodeColumns objWaybillcodeColumns) {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		return objPUCollection;
	}

}
