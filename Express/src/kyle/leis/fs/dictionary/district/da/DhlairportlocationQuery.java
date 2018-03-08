package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class DhlairportlocationQuery extends JGeneralQuery {
	
	public DhlairportlocationQuery(){
	    m_strSelectClause = "SELECT ap.ap_hubcode,ap.ap_ename,ap.ap_cname,dt.dt_name FROM t_di_airport ap,t_di_district dt";
	    m_strWhereClause = "ap.dt_code = dt.dt_code";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "ap.ap_hubcode = '~~'", "ap.ap_ename like '~~'", "ap.ap_cname like '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new DhlairportlocationColumns();
	}
	
	public void setAphubcode(String aphubcode) {
		this.setField(0, aphubcode);
	}

	public String getAphubcode() {
		return this.getField(0);
	}

	public void setApename(String apename) {
		this.setField(1, apename);
	}

	public String getApename() {
		return this.getField(1);
	}

	public void setApcname(String apcname) {
		this.setField(2, apcname);
	}

	public String getApcname() {
		return this.getField(2);
	}

}
