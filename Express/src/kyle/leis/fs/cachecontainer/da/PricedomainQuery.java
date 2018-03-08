package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class PricedomainQuery extends HGeneralQuery {
	
	public PricedomainQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.PricedomainColumns(pd.pdCode, pd.pdName, pd.pdEname) FROM TdiPricedomain as pd";
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
