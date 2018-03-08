package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class WaybilltrackstatusQuery extends HGeneralQuery {
	
	public WaybilltrackstatusQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.WaybilltrackstatusColumns(wbts.wbtsCode,wbts.wbtsName,wbts.wbtsEname,wbts.wbtsAbnormalsign,wbtp.wbtpCode,wbtp.wbtpName) FROM TdiWaybilltrackstatus as wbts inner join wbts.tdiWaybilltransferphase as wbtp inner join wbts.tdiSimplestatus as ss";
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
