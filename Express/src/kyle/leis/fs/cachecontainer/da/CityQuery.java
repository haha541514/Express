package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CityQuery extends HGeneralQuery {
	
	public CityQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.CityColumns(ct.ctCode,ct.ctName,ct.ctStartpostcode,ct.ctEndpostcode,st.stCode) FROM TdiCity as ct left join ct.tdiState as st";
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
