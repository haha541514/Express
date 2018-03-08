package kyle.leis.es.company.predicttemplate.bl.cmt;

import kyle.common.dbaccess.query.GeneralColumns;
import kyle.common.util.jlang.ObjectGenerator;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.predicttemplate.bl.IColumnMappingType;

public class AddColumnmappingtype implements IColumnMappingType {

	public void setValue(String strMappingColumnEname, 
			GeneralColumns generalColumns,
			String strValue) {
		try {
			String strOriginValue = ObjectGenerator.process("get" + strMappingColumnEname, 
					generalColumns, 
					null);
			if (!StringUtility.isNull(strOriginValue))
				strValue = strOriginValue + " " + strValue;
			ObjectGenerator.process("set" + strMappingColumnEname, 
					generalColumns, 
					strValue);	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
