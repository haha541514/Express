package kyle.leis.fs.waybillcode.bl;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.fs.waybillcode.da.WaybillcodeColumns;
import kyle.leis.fs.waybillcode.da.WaybillcodekindColumns;
import kyle.leis.fs.waybillcode.dax.IWaybillcodeBasicData;

public class DHLCoreWaybillcode extends AWaybillcode {
	public DHLCoreWaybillcode(WaybillcodekindColumns objWBCKColumns) {
		super(objWBCKColumns);
		m_iMaxLength = IWaybillcodeBasicData.MAX_DHL_COREWAYBILLCODE_LENGTH;
		m_iMinLength = IWaybillcodeBasicData.MAX_DHL_COREWAYBILLCODE_LENGTH;			
	}	
	
	public DHLCoreWaybillcode() {
		m_iMaxLength = IWaybillcodeBasicData.MAX_DHL_COREWAYBILLCODE_LENGTH;
		m_iMinLength = IWaybillcodeBasicData.MAX_DHL_COREWAYBILLCODE_LENGTH;		
	}	
	
	public String buildLabelcode(String strCode, String strPrefix, String strSuffix) {
		String strLabelcode = strCode;
		strLabelcode = strLabelcode + StringUtility.getMod(strLabelcode, 7);
		if (!StringUtility.isNull(strPrefix))
			strLabelcode = strPrefix + strLabelcode;
		if (!StringUtility.isNull(strSuffix))
			strLabelcode = strLabelcode + strSuffix;
		return strLabelcode;
	}

	public PromptUtilityCollection checkLabelcode(String strLabelcode) {
		PromptUtilityCollection objPUCollection = super.checkCodeLength(strLabelcode);
		if (objPUCollection.canGo(false)) {
			// 检查是否模7
			if (!StringUtility.checkMod(strLabelcode, 7))
				objPUCollection.add("E_WBC_002", "DHL主单必须模7", "DHLCoreWaybillcode.checkLabelcode");
		}
		return objPUCollection;
	}
	
	
	
	protected PromptUtilityCollection checkRegister(WaybillcodeColumns objWaybillcodeColumns) {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		/*
		PromptUtilityCollection objPUCollectionS = checkLabelcode(objWaybillcodeColumns.getBcbcstartcode());
		PromptUtilityCollection objCPUCollectionE = checkLabelcode(objWaybillcodeColumns.getBcbcendcode());
		if(!objPUCollectionS.canGo(false))
			objPUCollection.addAll(objPUCollectionS);
		else if(!objCPUCollectionE.canGo(false))
			objPUCollection.addAll(objCPUCollectionE);
		*/
		return objPUCollection;
	}
	
}
