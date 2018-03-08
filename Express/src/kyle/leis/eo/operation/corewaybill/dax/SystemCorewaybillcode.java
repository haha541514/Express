package kyle.leis.eo.operation.corewaybill.dax;

import java.util.ArrayList;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillColumns;
import kyle.leis.fs.waybillcode.bl.AWaybillcode;
import kyle.leis.fs.waybillcode.bl.IComplexPrefix;
import kyle.leis.fs.waybillcode.bl.WaybillcodeFactory;
import kyle.leis.fs.waybillcode.dax.IWaybillcodeBasicData;

public class SystemCorewaybillcode {
	
	public static synchronized ArrayList<String> getDHLChildLabelcode(String strBCK_Code,
			String strOperId, 
			int iPieces) throws Exception {
		// AWaybillcode objWaybillcode = WaybillcodeFactory.getWaybillcode("DHLCC");
		AWaybillcode objWaybillcode = WaybillcodeFactory.getWaybillcode(strBCK_Code);
		return objWaybillcode.getUsedLabelcode(strOperId, iPieces);
	}
	
	public static String getDHLMainLabelcode(String strBCK_Code, 
			String strOperId,
			IComplexPrefix objComplexPrefix) throws Exception {
		while (true) {
			String strServerEwbcode = getDHLMainLabelcodeOnly(strBCK_Code, strOperId, objComplexPrefix);
			if (StringUtility.isNull(strServerEwbcode))
				return null;
			// 效验是否重复
			SimplecorewaybillColumns objSCWColumns = CorewaybillDemand.loadCWByEwbcode(strServerEwbcode, 
					ICorewaybillBasicData.EWBCODE_TYPE_SERVER);
			if (objSCWColumns == null || StringUtility.isNull(objSCWColumns.getCwcw_code()))
				return strServerEwbcode;
		}
	}
	
	private static synchronized String getDHLMainLabelcodeOnly(String strBCK_Code,
			String strOperId,
			IComplexPrefix objComplexPrefix) throws Exception {
		// AWaybillcode objWaybillcode = WaybillcodeFactory.getWaybillcode(IWaybillcodeBasicData.BCK_DHLMASTER);
		AWaybillcode objWaybillcode = WaybillcodeFactory.getWaybillcode(strBCK_Code);
		objWaybillcode.setComplexPrefix(objComplexPrefix);
		ArrayList<String> alLabelcode = objWaybillcode.getUsedLabelcode(strOperId, 1);
		if (alLabelcode == null || alLabelcode.size() < 1)
			return null;
		String strServerEwbcode = alLabelcode.get(0);
		return strServerEwbcode;
	}
	
	public static synchronized String getCustomsLabelcode(String strOperId) throws Exception {
		AWaybillcode objWaybillcode = WaybillcodeFactory.getWaybillcode(IWaybillcodeBasicData.BCK_CUSTOMSINCREMENT);
		ArrayList<String> alLabelcode = objWaybillcode.getUsedLabelcode(strOperId, 1);
		if (alLabelcode == null || alLabelcode.size() < 1)
			return null;
		String strServerEwbcode = alLabelcode.get(0);
		return strServerEwbcode;
	}
}
