package kyle.fetcher.track.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class TrackstatusQuery extends JGeneralQuery {
	
	public TrackstatusQuery(){
	    m_strSelectClause = "SELECT wbbt.wbts_code,wbbt.wpa_code,wbbt.wbbt_signforuser,wbbt.wbbt_signfordate FROM T_CS_WAYBILLBATCHTRACK wbbt";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "wbbt.cw_code = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new TrackstatusColumns();
	}
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

}
