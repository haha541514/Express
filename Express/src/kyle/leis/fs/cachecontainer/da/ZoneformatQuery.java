package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ZoneformatQuery extends HGeneralQuery {
	
	public ZoneformatQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.ZoneformatColumns(zf.zfCode,zf.zfName,zf.zfEname) FROM TdiZoneformat as zf";
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
