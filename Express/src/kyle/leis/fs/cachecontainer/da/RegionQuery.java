package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class RegionQuery extends HGeneralQuery {
	
	public RegionQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.RegionColumns(rg.rgCode, rg.rgName, rg.rgEname) FROM TdiRegion as rg";
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
