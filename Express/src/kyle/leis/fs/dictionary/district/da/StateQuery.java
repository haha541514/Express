package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class StateQuery extends HGeneralQuery {
	
	public StateQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.district.da.StateColumns(st.stCode, st.stSname, st.stName, st.stEname,     di.dtCode, di.dtHubcode,di.dtName, di.dtEname) FROM TdiState as st inner join st.tdiDistrict as di";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "st.stCode = '~~'", "st.stSname = '~~'", "st.stName = '~~'", "st.stEname = '~~'", "di.dtCode = '~~'", "di.dtName = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setStcode(String stCode) {
		this.setField(0, stCode);
	}

	public String getStcode() {
		return this.getField(0);
	}

	public void setStsname(String stSname) {
		this.setField(1, stSname);
	}

	public String getStsname() {
		return this.getField(1);
	}

	public void setStname(String stName) {
		this.setField(2, stName);
	}

	public String getStname() {
		return this.getField(2);
	}

	public void setStename(String stEname) {
		this.setField(3, stEname);
	}

	public String getStename() {
		return this.getField(3);
	}

	public void setDtcode(String dtCode) {
		this.setField(4, dtCode);
	}

	public String getDtcode() {
		return this.getField(4);
	}

	public void setDtname(String dtName) {
		this.setField(5, dtName);
	}

	public String getDtname() {
		return this.getField(5);
	}

}
