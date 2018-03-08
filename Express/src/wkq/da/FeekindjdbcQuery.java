package wkq.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class FeekindjdbcQuery extends JGeneralQuery {
	
	public FeekindjdbcQuery(){
	    m_strSelectClause = "SELECT fo.fk_code,fo.fk_name,fo.fk_ename,fo.fk_referenceposition,fo.fk_manualmodifysign,fo.fk_basesign,   fo.fk_remark,fo.fk_accountingonlysign,fo.fk_declarevaluesign,   si.ss_code,   ex.est_code FROM t_di_feekind  fo,t_di_simplestatus  si,t_di_expressspecialtype ex";
	    m_strWhereClause = "fo.ss_code = si.ss_code and ex.est_code = fo.est_code";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "fo.fk_code = '~~'", "si.ss_code = '~~'", "fo.fk_name = '~~'", "fo.fk_ename = '~~'", "fo.fk_referenceposition = '~~'", "fo.fk_manualmodifysign = '~~'", "fo.fk_basesign = '~~'", "fo.fk_remark = '~~'", "ex.est_code = '~~'", "fo.fk_accountingonlysign = '~~'", "fo.fk_declarevaluesign = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new FeekindjdbcColumns();
	}
	
	public void setFkcode(String fkCode) {
		this.setField(0, fkCode);
	}

	public String getFkcode() {
		return this.getField(0);
	}

	public void setSscode(String ssCode) {
		this.setField(1, ssCode);
	}

	public String getSscode() {
		return this.getField(1);
	}

	public void setFkname(String fkName) {
		this.setField(2, fkName);
	}

	public String getFkname() {
		return this.getField(2);
	}

	public void setFkename(String fkEname) {
		this.setField(3, fkEname);
	}

	public String getFkename() {
		return this.getField(3);
	}

	public void setFkreferenceposition(String fkReferenceposition) {
		this.setField(4, fkReferenceposition);
	}

	public String getFkreferenceposition() {
		return this.getField(4);
	}

	public void setFkmanualmodifysign(String fkManualmodifysign) {
		this.setField(5, fkManualmodifysign);
	}

	public String getFkmanualmodifysign() {
		return this.getField(5);
	}

	public void setFkbasesign(String fkBasesign) {
		this.setField(6, fkBasesign);
	}

	public String getFkbasesign() {
		return this.getField(6);
	}

	public void setFkremark(String fkRemark) {
		this.setField(7, fkRemark);
	}

	public String getFkremark() {
		return this.getField(7);
	}

	public void setEstcode(String estCode) {
		this.setField(8, estCode);
	}

	public String getEstcode() {
		return this.getField(8);
	}

	public void setFkaccountingonlysign(String fkAccountingonlysign) {
		this.setField(9, fkAccountingonlysign);
	}

	public String getFkaccountingonlysign() {
		return this.getField(9);
	}

	public void setFkdeclarevaluesign(String fkDeclarevaluesign) {
		this.setField(10, fkDeclarevaluesign);
	}

	public String getFkdeclarevaluesign() {
		return this.getField(10);
	}

}
