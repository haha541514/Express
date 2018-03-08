package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CountryQuery extends JGeneralQuery {
	
	public CountryQuery(){
	    m_strSelectClause = "SELECT dt.dt_code,dt.dt_hubcode,dt.dt_name,dt.dt_ename FROM t_di_district dt";
	    m_strWhereClause = "dt.dt_code = dt.dt_countcode";
	    m_strOrderByClause = "dt.dt_ename";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "dt.dk_code = '~~'", "dt.dt_hubcode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CountryColumns();
	}
	
	public void setDkcode(String dkcode) {
		this.setField(0, dkcode);
	}

	public String getDkcode() {
		return this.getField(0);
	}

	public void setDthubcode(String dthubcode) {
		this.setField(1, dthubcode);
	}

	public String getDthubcode() {
		return this.getField(1);
	}

}
