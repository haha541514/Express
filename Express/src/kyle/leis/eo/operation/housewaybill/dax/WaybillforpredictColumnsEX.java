package kyle.leis.eo.operation.housewaybill.dax;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictColumns;

public class WaybillforpredictColumnsEX extends WaybillforpredictColumns {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getHwhw_consigneename() {
		String strOriginname = super.getHwhw_consigneename();
		if (StringUtility.isNull(super.getHwhw_consigneenameex()))
			return strOriginname;
		else {
			return StringUtility.buildFromByte(super.getHwhw_consigneenameex(), "utf-8");
		}
	}

	public String getHwhw_consigneecity() {
		//return this.getField(20);
		String strOriginname = super.getHwhw_consigneecity();
		if (StringUtility.isNull(super.getHwhw_consigneecityex()))
			return strOriginname;
		else {
			return StringUtility.buildFromByte(super.getHwhw_consigneecityex(), "utf-8");
		}		
	}	
	
	
	public String getHwhw_consigneeaddress1() {
		String str = super.getHwhw_consigneeaddress1();
		if (StringUtility.isNull(super.getHwhw_consigneeaddressex()))
			return str;
		else {
			return StringUtility.buildFromByte(super.getHwhw_consigneeaddressex(), "utf-8");
		}
	}
	
	public String getHwhw_consigneeaddress2() {
		String str = super.getHwhw_consigneeaddress2();
		if (StringUtility.isNull(super.getHwhw_consigneeaddressex()))
			return str;
		else
			return ".";
	}
	
	public String getHwhw_consigneeaddress3() {
		String str = super.getHwhw_consigneeaddress3();
		if (StringUtility.isNull(super.getHwhw_consigneeaddressex()))
			return str;
		else
			return ".";
	}	
	
	
	
}
