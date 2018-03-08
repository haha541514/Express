package kyle.leis.fs.waybillcode.bl;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.fs.waybillcode.da.WaybillcodeColumns;
import kyle.leis.fs.waybillcode.da.WaybillcodekindColumns;

public class DHLChildWaybillcode extends AWaybillcode {
	public DHLChildWaybillcode(WaybillcodekindColumns objWBCKColumns) {
		super(objWBCKColumns);
	}
	
	public String buildLabelcode(String strCode, String strPrefix, String strSuffix) {
		String strLabelcode = strCode;
		if (!StringUtility.isNull(strPrefix))
			strLabelcode = strPrefix + strLabelcode;
		if (!StringUtility.isNull(strSuffix))
			strLabelcode = strLabelcode + strSuffix;
		return strLabelcode;
	}

	public PromptUtilityCollection checkLabelcode(String strLabelcode) {
		PromptUtilityCollection objPUCollection = super.checkCodeLength(strLabelcode);
		return objPUCollection;
	}

	@Override
	protected PromptUtilityCollection checkRegister(WaybillcodeColumns objWaybillcodeColumns) {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		// 开始号效验
		objPUCollection = checkLabelcode(objWaybillcodeColumns.getBcbcstartcode());
		if(objPUCollection != null && !objPUCollection.canGo(true))
			return objPUCollection;
		// 结束号效验
		objPUCollection = checkLabelcode(objWaybillcodeColumns.getBcbcendcode());
		if(objPUCollection != null && !objPUCollection.canGo(true))
			return objPUCollection;
		return objPUCollection;
	}

}
