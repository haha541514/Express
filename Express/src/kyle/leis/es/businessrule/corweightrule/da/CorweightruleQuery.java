package kyle.leis.es.businessrule.corweightrule.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CorweightruleQuery extends HGeneralQuery {
	
	public CorweightruleQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.corweightrule.da.CorweightruleColumns(cwr.brId,br.brName,br.brEname,br.brStartdate,br.brEnddate,cop.opId,cop.opName,br.brCreatedate,mop.opId,mop.opName,br.brModifydate,br.brRemark,brk.brkCode,brk.brkName,ss.ssCode,ss.ssName,wrk.wrkId,wrk.wrkName,pd.pdCode,pd.pdName,chn.chnCode,chn.chnName,chn.chnSname,chn.chnSename,co.coCode,co.coName,co.coSname,co.coSename,co.coLabelcode) FROM TbrCorweightrule as cwr inner join cwr.tbrBusinessrule as br inner join br.tdiOperatorByBrOpIdCreate as cop inner join br.tdiOperatorByBrOpIdModifier as mop inner join br.tdiBusinessrulekind as brk inner join br.tdiSimplestatus as ss inner join cwr.tbrWeightrulekind as wrk inner join wrk.tdiPricedomain as pd left join cwr.tchnChannel as chn left join cwr.tcoCorporation as co";
	    m_strWhereClause = "brk.brkCode = 'A02'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cwr.brId = ~~", "br.brName like '%~~%'", "br.brEname like '%~~%'", "wrk.wrkName like '%~~%'", "br.brCreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= br.brCreatedate", "br.brModifydate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= br.brModifydate", "br.brEnddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= br.brStartdate", "ss.ssCode = '~~'", "chn.chnCode = '~~'", "co.coCode = '~~'", "pd.pdCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setBrname(String brName) {
		this.setField(1, brName);
	}

	public String getBrname() {
		return this.getField(1);
	}

	public void setBrename(String brEname) {
		this.setField(2, brEname);
	}

	public String getBrename() {
		return this.getField(2);
	}

	public void setWrkname(String wrkName) {
		this.setField(3, wrkName);
	}

	public String getWrkname() {
		return this.getField(3);
	}

	public void setStartcreatedate(String StartCreatedate) {
		this.setField(4, StartCreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(4);
	}

	public void setEndcreatedate(String EndCreatedate) {
		this.setField(5, EndCreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(5);
	}

	public void setStartmodifydate(String StartModifydate) {
		this.setField(6, StartModifydate);
	}

	public String getStartmodifydate() {
		return this.getField(6);
	}

	public void setEndmodifydate(String EndModifydate) {
		this.setField(7, EndModifydate);
	}

	public String getEndmodifydate() {
		return this.getField(7);
	}

	public void setValiddate1(String validDate1) {
		this.setField(8, validDate1);
	}

	public String getValiddate1() {
		return this.getField(8);
	}

	public void setValiddate2(String validDate2) {
		this.setField(9, validDate2);
	}

	public String getValiddate2() {
		return this.getField(9);
	}

	public void setSscode(String ssCode) {
		this.setField(10, ssCode);
	}

	public String getSscode() {
		return this.getField(10);
	}

	public void setChncode(String chnCode) {
		this.setField(11, chnCode);
	}

	public String getChncode() {
		return this.getField(11);
	}

	public void setCocode(String coCode) {
		this.setField(12, coCode);
	}

	public String getCocode() {
		return this.getField(12);
	}

	public void setPdcode(String pdCode) {
		this.setField(13, pdCode);
	}

	public String getPdcode() {
		return this.getField(13);
	}

}
