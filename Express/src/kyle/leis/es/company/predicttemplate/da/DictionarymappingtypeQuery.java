package kyle.leis.es.company.predicttemplate.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class DictionarymappingtypeQuery extends JGeneralQuery {
	
	public DictionarymappingtypeQuery(){
	    m_strSelectClause = "SELECT dmk.dmk_code,dmk.dmk_name,dmk.dmk_ename FROM t_di_dictionarymappingkind dmk";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new DictionarymappingtypeColumns();
	}
	

}
