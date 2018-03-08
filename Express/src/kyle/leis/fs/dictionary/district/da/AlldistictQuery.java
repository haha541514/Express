package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class AlldistictQuery extends JGeneralQuery {
	
	public AlldistictQuery(){
	    m_strSelectClause = "SELECT dt.dt_code,dt.dt_hubcode,dt.dt_name,dt.dt_ename,dt.dt_statename FROM t_di_district dt";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "dt.dk_code = '~~'", "dt.dt_statename = '~~'", "dt.dt_name = '~~'", "dt.dt_startpostcode = '~~'", "dt.dt_startpostcode like '~~%'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new AlldistictColumns();
	}
	
	public void setDkcode(String dkcode) {
		this.setField(0, dkcode);
	}

	public String getDkcode() {
		return this.getField(0);
	}

	public void setStatename(String statename) {
		this.setField(1, statename);
	}

	public String getStatename() {
		return this.getField(1);
	}

	public void setDtname(String dtname) {
		this.setField(2, dtname);
	}

	public String getDtname() {
		return this.getField(2);
	}

	public void setPostcode(String postcode) {
		this.setField(3, postcode);
	}

	public String getPostcode() {
		return this.getField(3);
	}
	
	public void setLikepostcode(String postcode) {
		this.setField(4, postcode);
	}

	public String getLikepostcode() {
		return this.getField(4);
	}	
}
