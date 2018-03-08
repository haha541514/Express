package kyle.leis.fs.dictionary.cargokind.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CargokindQuery extends HGeneralQuery {
	
	public CargokindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.cargokind.da.CargokindColumns(ck.cgkCode,ck.cgkName,ck.cgkEname,ck.cgkBatterysign,   si.ssCode,si.ssName) FROM TdiCargokind as ck inner join ck.tdiSimplestatus as si";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { " ck.cgkCode = '~~'", " ck.cgkName = '~~'", " ck.cgkEname = '~~'", " ck.cgkBatterysign = '~~'", " si.ssCode = '~~'", " si.ssName = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCgkcode(String cgkCode) {
		this.setField(0, cgkCode);
	}

	public String getCgkcode() {
		return this.getField(0);
	}

	public void setCgkname(String cgkName) {
		this.setField(1, cgkName);
	}

	public String getCgkname() {
		return this.getField(1);
	}

	public void setCgkename(String cgkEname) {
		this.setField(2, cgkEname);
	}

	public String getCgkename() {
		return this.getField(2);
	}

	public void setCgkbatterysign(String cgkBatterysign) {
		this.setField(3, cgkBatterysign);
	}

	public String getCgkbatterysign() {
		return this.getField(3);
	}

	public void setSscode(String ssCode) {
		this.setField(4, ssCode);
	}

	public String getSscode() {
		return this.getField(4);
	}

	public void setSsname(String ssName) {
		this.setField(5, ssName);
	}

	public String getSsname() {
		return this.getField(5);
	}

}
