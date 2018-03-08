package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ExpressspecialtypeQuery extends HGeneralQuery {
	
	public ExpressspecialtypeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.ExpressspecialtypeColumns(est.estCode,est.estName,est.estEname,est.estStructurecode,est.estEndsign,est.estExcludesign) FROM TdiExpressspecialtype as est inner join est.tdiSimplestatus as ss";
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
