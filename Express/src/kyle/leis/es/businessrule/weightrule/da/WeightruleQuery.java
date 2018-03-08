package kyle.leis.es.businessrule.weightrule.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class WeightruleQuery extends HGeneralQuery {
	
	public WeightruleQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.weightrule.da.WeightruleColumns(wr.brId,br.brName,br.brEname,br.brStartdate,br.brEnddate,cop.opId,cop.opName,br.brCreatedate,mop.opId,mop.opName,br.brModifydate,br.brRemark,brk.brkCode,brk.brkName,ss.ssCode,ss.ssName,wrk.wrkId,wrk.wrkName,ut.utCode,ut.utName,pk.pkCode,pk.pkSename,pd.pdCode,pd.pdName,swk.swkCode,swk.swkName,swk.swkEname,wr.wrPelenghtformula,wr.wrPeweightformula,wr.wrPeactualweight,tswk.swkCode) FROM TbrWeightrule as wr inner join wr.tbrBusinessrule as br inner join br.tdiBusinessrulekind as brk inner join br.tdiSimplestatus as ss inner join br.tdiOperatorByBrOpIdCreate as cop inner join br.tdiOperatorByBrOpIdModifier as mop inner join wr.tbrWeightrulekind as wrk inner join wr.tdiUnittype as ut left join wrk.tdiProductkind as pk inner join wrk.tdiPricedomain as pd left join wr.tdiServerweightkindBySwkCode as swk left join wr.tdiServerweightkindBySwkCodeTransfer as tswk";
	    m_strWhereClause = "brk.brkCode = 'A02'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "wr.brId = ~~", "br.brName like '%~~%'", "br.brEname like '%~~%'", "wrk.wrkName like '%~~%'", "wrk.wrkId = ~~", "swk.swkName like '%~~%'", "swk.swkCode = ~~", "br.brCreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= br.brCreatedate", "br.brModifydate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= br.brModifydate", "br.brEnddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= br.brStartdate", "ss.ssCode = '~~'", "ut.utCode = '~~'", "pk.pkCode = '~~'", "pd.pdCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
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

	public void setWrkid(String wrkId) {
		this.setField(4, wrkId);
	}

	public String getWrkid() {
		return this.getField(4);
	}

	public void setSwkname(String swkName) {
		this.setField(5, swkName);
	}

	public String getSwkname() {
		return this.getField(5);
	}

	public void setSwkcode(String swkCode) {
		this.setField(6, swkCode);
	}

	public String getSwkcode() {
		return this.getField(6);
	}

	public void setStartcreatedate(String StartCreatedate) {
		this.setField(7, StartCreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(7);
	}

	public void setEndcreatedate(String EndCreatedate) {
		this.setField(8, EndCreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(8);
	}

	public void setStartmodifydate(String StartModifydate) {
		this.setField(9, StartModifydate);
	}

	public String getStartmodifydate() {
		return this.getField(9);
	}

	public void setEndmodifydate(String EndModifydate) {
		this.setField(10, EndModifydate);
	}

	public String getEndmodifydate() {
		return this.getField(10);
	}

	public void setValiddate1(String validDate1) {
		this.setField(11, validDate1);
	}

	public String getValiddate1() {
		return this.getField(11);
	}

	public void setValiddate2(String validDate2) {
		this.setField(12, validDate2);
	}

	public String getValiddate2() {
		return this.getField(12);
	}

	public void setSscode(String ssCode) {
		this.setField(13, ssCode);
	}

	public String getSscode() {
		return this.getField(13);
	}

	public void setUtcode(String utCode) {
		this.setField(14, utCode);
	}

	public String getUtcode() {
		return this.getField(14);
	}

	public void setPkcode(String pkCode) {
		this.setField(15, pkCode);
	}

	public String getPkcode() {
		return this.getField(15);
	}

	public void setPdcode(String pdCode) {
		this.setField(16, pdCode);
	}

	public String getPdcode() {
		return this.getField(16);
	}

}
