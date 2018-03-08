package wkq.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class FeekindQuery extends HGeneralQuery {
	
	public FeekindQuery(){
	    m_strSelectClause = "SELECT new wkq.da.FeekindColumns(fo.fkCode,fo.fkName,fo.fkEname,fo.fkReferenceposition,fo.fkManualmodifysign,   fo.fkBasesign,fo.fkRemark,fo.fkAccountingonlysign,   fo.fkDeclarevaluesign,si.ssCode,ex.estCode) FROM TdiFeekind as fo inner join fo.tdiSimplestatus as si inner join fo.tdiExpressspecialtype as ex";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "fo.fkCode = '~~'", "si.ssCode = '~~'", "fo.fkName = '~~'", "fo.fkEname = '~~'", "fo.fkReferenceposition = '~~'", "fo.fkManualmodifysign = '~~'", "fo.fkBasesign = '~~'", "fo.fkRemark = '~~'", "ex.estCode = '~~'", "fo.fkAccountingonlysign = '~~'", "fo.fkDeclarevaluesign = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
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
