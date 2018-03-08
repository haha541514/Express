package kyle.leis.es.businessrule.operationprompt.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class OperationpromptQuery extends HGeneralQuery {
	
	public OperationpromptQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.operationprompt.dax.OperationpromptColumnsEX(opt.brId,opt.ptCode,opt.optName,opt.optUniversalcustomersign,opt.optUniversalservesign,opt.optUniversalachannelsign,opt.optUniversalschannelsign,opt.optUniversaldeparturesign,opt.optUniversaldestinationsign,opt.optUniversaltachesign,opt.optVwgwformular,opt.optChargeweightbegin,opt.optChargeweightend,opt.optPiecegrossweightbegin,opt.optPiecegrossweightend,opt.optDeclarevaluebegin,opt.optDeclarevalueend,opt.optContent,br.brName,br.brEname,br.brStartdate,br.brEnddate,cop.opId,cop.opName,br.brCreatedate,mop.opId,mop.opName,br.brModifydate,br.brRemark,brk.brkCode,brk.brkName,ss.ssCode,ss.ssName,ct.ctCode,ct.ctName,pm.pmCode,pm.pmName,pk.pkCode,pk.pkName) FROM TbrOperationprompt as opt inner join opt.tbrBusinessrule as br inner join br.tdiOperatorByBrOpIdCreate as cop inner join br.tdiOperatorByBrOpIdModifier as mop inner join br.tdiBusinessrulekind as brk inner join br.tdiSimplestatus as ss inner join opt.tdiCargotype as ct inner join opt.tdiPaymentmode as pm inner join opt.tdiProductkind as pk";
	    m_strWhereClause = "brk.brkCode = 'A04'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "opt.brId = ~~", "br.brEnddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= br.brStartdate", "br.brName = '~~'", "br.brEname = '~~'", "ct.ctCode = '~~'", "ct.ctCode in (~~)", "ct.ctCode not in (~~)", "pm.pmCode = '~~'", "pm.pmCode in (~~)", "pm.pmCode not in (~~)", "pk.pkCode = '~~'", "pk.pkCode in (~~)", "pk.pkCode not in (~~)", "ss.ssCode in (~~)", "ss.ssCode not in (~~)", "opt.optChargeweightend >= ~~", "~~ >= opt.optChargeweightbegin", "opt.optPiecegrossweightend >= ~~", "~~ >= opt.optPiecegrossweightbegin", "opt.optDeclarevalueend >= ~~", "~~ >= opt.optDeclarevaluebegin" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
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

	public void setValiddate1(String validDate1) {
		this.setField(1, validDate1);
	}

	public String getValiddate1() {
		return this.getField(1);
	}

	public void setValiddate2(String validDate2) {
		this.setField(2, validDate2);
	}

	public String getValiddate2() {
		return this.getField(2);
	}

	public void setBrname(String brName) {
		this.setField(3, brName);
	}

	public String getBrname() {
		return this.getField(3);
	}

	public void setBrename(String brEname) {
		this.setField(4, brEname);
	}

	public String getBrename() {
		return this.getField(4);
	}

	public void setCtcode(String ctCode) {
		this.setField(5, ctCode);
	}

	public String getCtcode() {
		return this.getField(5);
	}

	public void setCtcode2(String ctCode2) {
		this.setField(6, ctCode2);
	}

	public String getCtcode2() {
		return this.getField(6);
	}

	public void setNotctcode(String NotCtCode) {
		this.setField(7, NotCtCode);
	}

	public String getNotctcode() {
		return this.getField(7);
	}

	public void setPmcode(String pmCode) {
		this.setField(8, pmCode);
	}

	public String getPmcode() {
		return this.getField(8);
	}

	public void setPmcode2(String pmCode2) {
		this.setField(9, pmCode2);
	}

	public String getPmcode2() {
		return this.getField(9);
	}

	public void setNotpmcode2(String NotPmCode2) {
		this.setField(10, NotPmCode2);
	}

	public String getNotpmcode2() {
		return this.getField(10);
	}

	public void setPkcode(String pkCode) {
		this.setField(11, pkCode);
	}

	public String getPkcode() {
		return this.getField(11);
	}

	public void setPkcode2(String pkCode2) {
		this.setField(12, pkCode2);
	}

	public String getPkcode2() {
		return this.getField(12);
	}

	public void setNotpkcode2(String NotPkCode2) {
		this.setField(13, NotPkCode2);
	}

	public String getNotpkcode2() {
		return this.getField(13);
	}

	public void setSscode(String ssCode) {
		this.setField(14, ssCode);
	}

	public String getSscode() {
		return this.getField(14);
	}

	public void setNotsscode(String NotssCode) {
		this.setField(15, NotssCode);
	}

	public String getNotsscode() {
		return this.getField(15);
	}

	public void setChargeweight(String chargeweight) {
		this.setField(16, chargeweight);
	}

	public String getChargeweight() {
		return this.getField(16);
	}

	public void setChargeweight2(String chargeweight2) {
		this.setField(17, chargeweight2);
	}

	public String getChargeweight2() {
		return this.getField(17);
	}

	public void setPiecegrossweight(String piecegrossweight) {
		this.setField(18, piecegrossweight);
	}

	public String getPiecegrossweight() {
		return this.getField(18);
	}

	public void setPiecegrossweight2(String piecegrossweight2) {
		this.setField(19, piecegrossweight2);
	}

	public String getPiecegrossweight2() {
		return this.getField(19);
	}

	public void setDeclarevalue(String declarevalue) {
		this.setField(20, declarevalue);
	}

	public String getDeclarevalue() {
		return this.getField(20);
	}

	public void setDeclarevalue2(String declarevalue2) {
		this.setField(21, declarevalue2);
	}

	public String getDeclarevalue2() {
		return this.getField(21);
	}

}
