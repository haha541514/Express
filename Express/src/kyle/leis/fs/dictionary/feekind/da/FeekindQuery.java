package kyle.leis.fs.dictionary.feekind.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class FeekindQuery extends HGeneralQuery {
	
	public FeekindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.feekind.da.FeekindColumns(fk.fkCode, fk.fkName, fk.fkEname, fk.fkReferenceposition, fk.fkManualmodifysign, fk.fkBasesign, fk.fkRemark, est.estCode, est.estName, est.estEname, ss.ssCode, ss.ssName) FROM TdiFeekind as fk left join fk.tdiExpressspecialtype as est inner join fk.tdiSimplestatus as ss";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "fk.fkCode = '~~'", "fk.fkName = '~~'", "fk.fkManualmodifysign = '~~'", "fk.fkBasesign = '~~'", "est.estCode = '~~'", "est.estEname = '~~'", "ss.ssCode = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
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

	public void setFkename(String fkEname) {
		this.setField(1, fkEname);
	}

	public String getFkename() {
		return this.getField(1);
	}

	public void setFkmanualmodifysign(String fkManualmodifysign) {
		this.setField(2, fkManualmodifysign);
	}

	public String getFkmanualmodifysign() {
		return this.getField(2);
	}

	public void setFkbasesign(String fkBasesign) {
		this.setField(3, fkBasesign);
	}

	public String getFkbasesign() {
		return this.getField(3);
	}

	public void setEstcode(String estCode) {
		this.setField(4, estCode);
	}

	public String getEstcode() {
		return this.getField(4);
	}

	public void setEstename(String estEname) {
		this.setField(5, estEname);
	}

	public String getEstename() {
		return this.getField(5);
	}

	public void setSscode(String ssCode) {
		this.setField(6, ssCode);
	}

	public String getSscode() {
		return this.getField(6);
	}

}
