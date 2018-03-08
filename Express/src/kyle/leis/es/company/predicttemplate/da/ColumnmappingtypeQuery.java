package kyle.leis.es.company.predicttemplate.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class ColumnmappingtypeQuery extends JGeneralQuery {
	
	public ColumnmappingtypeQuery(){
	    m_strSelectClause = "SELECT cmt.cmt_code,cmt.cmt_name,cmt.cmt_ename FROM t_di_columnmappingtype cmt";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new ColumnmappingtypeColumns();
	}
	

}
