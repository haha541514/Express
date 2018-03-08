package kyle.leis.es.company.predicttemplate.bl.cmt;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.predicttemplate.bl.IColumnMappingType;

public class ColumnmappingtypeFactory {
	
	public static IColumnMappingType createMappingtype(String strDmkcode) {
		if (StringUtility.isNull(strDmkcode))
			return new CopyColumnmappingtype();
		if (strDmkcode.equals("AD"))
			return new AddColumnmappingtype();
		if (strDmkcode.equals("CP"))
			return new CopyColumnmappingtype();
		else
			return new CopyColumnmappingtype();
	}
}
