package kyle.leis.eo.operation.predictwaybill.dax;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;

public class PredictwaybillColumnsEX extends PredictwaybillColumns {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getPwbpwb_consigneename() {
		String strOriginname = super.getPwbpwb_consigneename();
		if (StringUtility.isNull(super.getPwbpwb_consigneenameex()))
			return strOriginname;
		else {
			return StringUtility.buildFromByte(super.getPwbpwb_consigneenameex(), "utf-8");
		}
	}
	
	public String getPwbpwb_consigneeaddress1() {
		String str = super.getPwbpwb_consigneeaddress1();
		if (StringUtility.isNull(super.getPwbpwb_consigneeaddressex()))
			return str;
		else {
			return StringUtility.buildFromByte(super.getPwbpwb_consigneeaddressex(), "utf-8");
		}
	}
	
	public String getPwbpwb_consigneeaddress2() {
		String str = super.getPwbpwb_consigneeaddress2();
		if (StringUtility.isNull(super.getPwbpwb_consigneeaddressex()))
			return str;
		else
			return ".";
	}	
	
	public String getPwbpwb_consigneecity() {
		String str = super.getPwbpwb_consigneecityex();
		if (StringUtility.isNull(super.getPwbpwb_consigneecityex()))
			return str;
		else
			return StringUtility.buildFromByte(super.getPwbpwb_consigneecityex(), "utf-8");
	}	
	
}
