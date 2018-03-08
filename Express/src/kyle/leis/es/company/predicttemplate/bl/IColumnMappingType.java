package kyle.leis.es.company.predicttemplate.bl;

import kyle.common.dbaccess.query.GeneralColumns;

public interface IColumnMappingType {
	
	public void setValue(String strMappingColumnEname, 
			GeneralColumns generalColumns,
			String strValue);
	
}
