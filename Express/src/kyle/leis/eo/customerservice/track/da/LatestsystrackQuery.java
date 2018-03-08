package kyle.leis.eo.customerservice.track.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class LatestsystrackQuery extends JGeneralQuery {
	
	public LatestsystrackQuery(){
	    m_strSelectClause = "SELECT FUN_GET_LatestSYSTrack(cw.cw_code) as LatestSYSTrack FROM T_OP_COREWAYBILL cw";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cw_code = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new LatestsystrackColumns();
	}
	
	public void setCwcode(String cwcode) {
		this.setField(0, cwcode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

}
