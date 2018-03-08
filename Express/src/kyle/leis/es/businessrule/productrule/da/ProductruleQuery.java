package kyle.leis.es.businessrule.productrule.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ProductruleQuery extends HGeneralQuery {
	
	public ProductruleQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.productrule.da.ProductruleColumns(pr.brId,pr.prInsurancesign,pr.prDoxtransfergw,pr.prSimpleinputsign,pr.prCollectsign,pr.prAllcussignoutbyoriginwbsign,br.brName,br.brEname,br.brStartdate,br.brEnddate,cop.opId,cop.opName,br.brCreatedate,mop.opId,mop.opName,br.brModifydate,br.brRemark,pk.pkCode,pk.pkName,pk.pkSname,pk.pkSename,ck.ckCode,ck.ckName,brk.brkCode,brk.brkName,ss.ssCode,ss.ssName) FROM TbrProductrule as pr inner join pr.tbrBusinessrule as br inner join br.tdiBusinessrulekind as brk inner join br.tdiOperatorByBrOpIdCreate as cop inner join br.tdiOperatorByBrOpIdModifier as mop inner join pr.tdiProductkind as pk inner join pr.tdiCurrencykind as ck inner join br.tdiSimplestatus as ss";
	    m_strWhereClause = "brk.brkCode = 'A01'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "pr.brId = ~~", "pk.pkCode = '~~'", "br.brCreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= br.brCreatedate", "br.brModifydate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= br.brModifydate", "br.brEnddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= br.brStartdate", "ss.ssCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
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

	public void setPkcode(String pkCode) {
		this.setField(1, pkCode);
	}

	public String getPkcode() {
		return this.getField(1);
	}

	public void setStartcreatedate(String StartCreatedate) {
		this.setField(2, StartCreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(2);
	}

	public void setEndcreatedate(String EndCreatedate) {
		this.setField(3, EndCreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(3);
	}

	public void setStartmodifydate(String StartModifydate) {
		this.setField(4, StartModifydate);
	}

	public String getStartmodifydate() {
		return this.getField(4);
	}

	public void setEndmodifydate(String EndModifydate) {
		this.setField(5, EndModifydate);
	}

	public String getEndmodifydate() {
		return this.getField(5);
	}

	public void setValiddate1(String validDate1) {
		this.setField(6, validDate1);
	}

	public String getValiddate1() {
		return this.getField(6);
	}

	public void setValiddate2(String validDate2) {
		this.setField(7, validDate2);
	}

	public String getValiddate2() {
		return this.getField(7);
	}

	public void setSscode(String ssCode) {
		this.setField(8, ssCode);
	}

	public String getSscode() {
		return this.getField(8);
	}

}
