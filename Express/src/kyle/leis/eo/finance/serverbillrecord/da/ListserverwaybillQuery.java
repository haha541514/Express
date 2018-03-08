package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class ListserverwaybillQuery extends JGeneralQuery {
	
	public ListserverwaybillQuery(){
	    m_strSelectClause = "SELECT distinct swb.swb_code  FROM t_fi_serverpayable spy,t_fi_serverwaybill swb";
	    m_strWhereClause = "spy.swb_code = swb.swb_code";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "spy.sbr_id = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new ListserverwaybillColumns();
	}
	
	public void setSbrid(String sbrId) {
		this.setField(0, sbrId);
	}

	public String getSbrid() {
		return this.getField(0);
	}

}
