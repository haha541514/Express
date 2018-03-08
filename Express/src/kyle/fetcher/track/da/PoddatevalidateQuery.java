package kyle.fetcher.track.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class PoddatevalidateQuery extends JGeneralQuery {
	
	public PoddatevalidateQuery(){
	    m_strSelectClause = "SELECT count(1) as validateRow FROM T_CS_WAYBILLBATCHTRACK wbbt";
	    m_strWhereClause = "cw.bw_code_departure = bw.bw_code";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cw_code = ~~", "bw.add_date >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.add_date" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new PoddatevalidateColumns();
	}
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setStartarrivaldate(String StartArrivalDate) {
		this.setField(1, StartArrivalDate);
	}

	public String getStartarrivaldate() {
		return this.getField(1);
	}

	public void setEndarrivaldate(String EndArrivalDate) {
		this.setField(2, EndArrivalDate);
	}

	public String getEndarrivaldate() {
		return this.getField(2);
	}

}
