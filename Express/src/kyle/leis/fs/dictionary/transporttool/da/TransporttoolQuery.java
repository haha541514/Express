package kyle.leis.fs.dictionary.transporttool.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class TransporttoolQuery extends HGeneralQuery {
	
	public TransporttoolQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.transporttool.da.TransporttoolColumns(tt.ttCode, tt.ttLabelcode, tt.ttDeparturetime, tt.ttArrivaltime, tt.ttDrivername, dtdp.dtCode, dtdp.dtName, dtdp.dtEname, dtar.dtCode, dtar.dtName, dtar.dtEname, twbk.twbkCode, twbk.twbkName, twbk.twbkEname, chn.chnCode, chn.chnName, chn.chnEname) FROM TdiTrasporttool as tt inner join tt.tdiDistrictByDtCodeDeparture as dtdp inner join tt.tdiDistrictByDtCodeArrival as dtar inner join tt.tdiTransportwaybillkind as twbk left join tt.tchnChannel as chn";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "tt.ttCode = '~~'", "tt.ttLabelcode = '~~'", "tt.ttDeparturetime >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= tt.ttDeparturetime", "tt.ttArrivaltime >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= tt.ttArrivaltime", "tt.ttDrivername = '~~'", "dtdp.dtCode = '~~'", "dtar.dtCode = '~~'", "twbk.twbkCode = '~~'", "chn.chnCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setTtcode(String ttCode) {
		this.setField(0, ttCode);
	}

	public String getTtcode() {
		return this.getField(0);
	}

	public void setTtlabelcode(String ttLabelcode) {
		this.setField(1, ttLabelcode);
	}

	public String getTtlabelcode() {
		return this.getField(1);
	}

	public void setDpstartdate(String dpStartDate) {
		this.setField(2, dpStartDate);
	}

	public String getDpstartdate() {
		return this.getField(2);
	}

	public void setDpenddate(String dpEndDate) {
		this.setField(3, dpEndDate);
	}

	public String getDpenddate() {
		return this.getField(3);
	}

	public void setArstartdate(String arStartDate) {
		this.setField(4, arStartDate);
	}

	public String getArstartdate() {
		return this.getField(4);
	}

	public void setArenddate(String arEndDate) {
		this.setField(5, arEndDate);
	}

	public String getArenddate() {
		return this.getField(5);
	}

	public void setTtdrivername(String ttDrivername) {
		this.setField(6, ttDrivername);
	}

	public String getTtdrivername() {
		return this.getField(6);
	}

	public void setDtdpcode(String dtdpCode) {
		this.setField(7, dtdpCode);
	}

	public String getDtdpcode() {
		return this.getField(7);
	}

	public void setDtarcode(String dtarCode) {
		this.setField(8, dtarCode);
	}

	public String getDtarcode() {
		return this.getField(8);
	}

	public void setTwbkcode(String twbkCode) {
		this.setField(9, twbkCode);
	}

	public String getTwbkcode() {
		return this.getField(9);
	}

	public void setChncode(String chnCode) {
		this.setField(10, chnCode);
	}

	public String getChncode() {
		return this.getField(10);
	}

}
