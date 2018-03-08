package kyle.leis.fs.dictionary.dictionarys.dax;

import kyle.leis.fs.dictionary.dictionarys.da.TemplatecolumnQuery;

public class TemplatecolumnQueryEX extends TemplatecolumnQuery {
	public TemplatecolumnQueryEX(){
	    m_strWhereClause = "tc.tcId >= '1111' and tc.tcId < '1199'";	
	}
	
	public TemplatecolumnQueryEX(boolean isIMPAPI){
		if (isIMPAPI)
			m_strWhereClause = "to_number(tc.tcId) < 1111";	
	}	
	
}
