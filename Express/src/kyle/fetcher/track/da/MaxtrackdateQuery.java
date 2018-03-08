package kyle.fetcher.track.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class MaxtrackdateQuery extends JGeneralQuery {
	
	public MaxtrackdateQuery(){
	    m_strSelectClause = "SELECT max(wbt.wbt_occurdate) as MaxOccurdate FROM t_cs_waybilltrack wbt";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "wbt.cwCode = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new MaxtrackdateColumns();
	}
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

}
