package kyle.leis.eo.operation.corewaybill.sv;

import java.util.ArrayList;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybill.dax.DGMComplexPrefix;
import kyle.leis.eo.operation.corewaybill.dax.HUEMSComplexPrefix;
import kyle.leis.eo.operation.corewaybill.dax.SystemCorewaybillcode;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.fs.waybillcode.bl.IComplexPrefix;
import kyle.leis.fs.waybillcode.dax.IWaybillcodeBasicData;

public class CorewaybillService extends AService {
	
	public String getDHLChildLabelcode(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		
		String strBCK_Code = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		int strPieces = Integer.parseInt((String)objPD.getParameter(2, String.class));
		
		ArrayList<String> al = SystemCorewaybillcode.getDHLChildLabelcode(strBCK_Code, strOperId, strPieces);
		if (al == null || al.size() < 1)
			return "";
		
		String[] astrLabelcode = new String[al.size()];
		for (int i = 0; i < al.size(); i++) {
			astrLabelcode[i] = al.get(i);
		}
		Encoder objEncode = new Encoder();
		objEncode.addParameter(astrLabelcode);
		
		return objEncode.toString();
	}	
	
	public String getDHLMainLabelcode(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);
		
		String strBCK_Code = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		String strComplexPrefixType = (String)objPD.getParameter(2, String.class);
		ForinputallColumns objFIAColumns = (ForinputallColumns)objPD.getParameter(3, ForinputallColumns.class);
		IComplexPrefix objComplexPrefix = null;
		if (!StringUtility.isNull(strComplexPrefixType) && 
				strComplexPrefixType.equals(IWaybillcodeBasicData.COMPLEX_PREFIX_DGM)) {
			objComplexPrefix = new DGMComplexPrefix(objFIAColumns);
		}
		else if (!StringUtility.isNull(strComplexPrefixType) && 
				strComplexPrefixType.equals(IWaybillcodeBasicData.COMPLEX_PREFIX_HUEMS)) {
			objComplexPrefix = new HUEMSComplexPrefix(objFIAColumns);
		}
		
		String strLabelcode = SystemCorewaybillcode.getDHLMainLabelcode(strBCK_Code, strOperId, objComplexPrefix);

		Encoder objEncode = new Encoder();
		objEncode.addParameter(strLabelcode);
		
		return objEncode.toString();
	}		
	
	public String getCustomsLabelcode(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		String strCustomsLabelcode = SystemCorewaybillcode.getCustomsLabelcode(strOperId);

		Encoder objEncode = new Encoder();
		objEncode.addParameter(strCustomsLabelcode);
		
		return objEncode.toString();
	}	
	
}
