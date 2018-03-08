package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class UnittypeQuery extends HGeneralQuery {
	
	public UnittypeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.UnittypeColumns(ut.utCode, ut.utName, ut.utEname) FROM TdiUnittype as ut";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
