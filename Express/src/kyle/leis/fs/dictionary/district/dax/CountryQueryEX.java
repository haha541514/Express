package kyle.leis.fs.dictionary.district.dax;

import kyle.common.dbaccess.query.IColumns;
import kyle.leis.fs.dictionary.district.da.CountryColumns;
import kyle.leis.fs.dictionary.district.da.CountryQuery;

public class CountryQueryEX extends CountryQuery{

	public CountryQueryEX(){
		m_strSelectClause = "SELECT dt.dt_code,dt.dt_hubcode,dt.dt_name,dt.dt_ename FROM t_di_district dt";
	    m_strWhereClause = "dt.dt_code = dt.dt_countcode";
	    m_strOrderByClause = "dt.dt_ename";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "dt.dt_name = '~~'", "dt.dt_hubcode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CountryColumns();
	}
	
	public void setDtname(String dtname) {
		this.setField(0, dtname);
	}

	public String getDtname() {
		return this.getField(0);
	}

	
	    
	public void setDthubcode(String dthubcode) {
		this.setField(2, dthubcode);
	}

	public String getDthubcode() {
		return this.getField(2);
	}
}
