package kyle.leis.es.company.predicttemplate.bl.cmt;

import kyle.common.dbaccess.query.GeneralColumns;
import kyle.common.util.jlang.ObjectGenerator;
import kyle.leis.es.company.predicttemplate.bl.IColumnMappingType;

public class CopyColumnmappingtype implements IColumnMappingType {

	public void setValue(String strMappingColumnEname, 
			GeneralColumns generalColumns,
			String strValue) {
		try {
			ObjectGenerator.process("set" + strMappingColumnEname, 
					generalColumns, 
					strValue);	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
