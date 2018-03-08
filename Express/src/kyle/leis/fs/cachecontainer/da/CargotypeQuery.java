package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CargotypeQuery extends HGeneralQuery {
	
	public CargotypeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.CargotypeColumns(ct.ctCode, ct.ctName, ct.ctEname, ct.ctSename, ct.ctVisiblesign) FROM TdiCargotype as ct inner join ct.tdiSimplestatus as ss";
	    m_strWhereClause = "ss.ssCode = 'ON'";
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
