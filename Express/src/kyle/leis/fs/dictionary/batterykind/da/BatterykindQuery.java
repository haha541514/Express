package kyle.leis.fs.dictionary.batterykind.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class BatterykindQuery extends HGeneralQuery {
	
	public BatterykindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.batterykind.da.BatterykindColumns(bk.bkCode,bk.bkName,bk.bkEname,   si.ssCode,si.ssName,   ck.cgkCode,ck.cgkName) FROM TdiBatterykind as bk inner join bk.tdiCargokind as ck inner join bk.tdiSimplestatus as si";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { " bk.bkCode  = '~~'", " bk.bkName = '~~'", " bk.bkEname  = '~~'", " si.ssCode = '~~'", " si.ssName = '~~'", " ck.cgkCode = '~~'", " ck.cgkName = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBkcode(String bkCode) {
		this.setField(0, bkCode);
	}

	public String getBkcode() {
		return this.getField(0);
	}

	public void setBkname(String bkName) {
		this.setField(1, bkName);
	}

	public String getBkname() {
		return this.getField(1);
	}

	public void setBkename(String bkEname) {
		this.setField(2, bkEname);
	}

	public String getBkename() {
		return this.getField(2);
	}

	public void setSscode(String ssCode) {
		this.setField(3, ssCode);
	}

	public String getSscode() {
		return this.getField(3);
	}

	public void setSsname(String ssName) {
		this.setField(4, ssName);
	}

	public String getSsname() {
		return this.getField(4);
	}

	public void setCgkcode(String cgkCode) {
		this.setField(5, cgkCode);
	}

	public String getCgkcode() {
		return this.getField(5);
	}

	public void setCgkname(String cgkName) {
		this.setField(6, cgkName);
	}

	public String getCgkname() {
		return this.getField(6);
	}

}
