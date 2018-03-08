package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class DistrictandcountryQuery extends JGeneralQuery {
	
	public DistrictandcountryQuery(){
	    m_strSelectClause = "SELECT dt.dt_code,dt.dt_hubcode,dt.dt_name FROM t_di_district dt,t_di_district cdt";
	    m_strWhereClause = "dt.dt_countcode = cdt.dt_code";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cdt.dt_hubcode = '~~'", "dt.dt_ename = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new DistrictandcountryColumns();
	}
	
	public void setCountryhubcode(String countryhubCode) {
		this.setField(0, countryhubCode);
	}

	public String getCountryhubcode() {
		return this.getField(0);
	}

	public void setCityname(String cityName) {
		this.setField(1, cityName);
	}

	public String getCityname() {
		return this.getField(1);
	}

}
