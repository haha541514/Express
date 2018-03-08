package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class AirwaycompanyQuery extends JGeneralQuery {
	
	public AirwaycompanyQuery(){
	    m_strSelectClause = "SELECT ac.ac_id,ac.ac_hubcode,ac.ac_threehubcode,ac.ac_ename,ac.ac_cname FROM t_di_airwaycompany ac";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "ac.ac_hubcode = '~~'", "ac.ac_threehubcode = '~~'", "ac.ac_ename = '~~'", "ac.ac_cname = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new AirwaycompanyColumns();
	}
	
	public void setAchubcode(String achubcode) {
		this.setField(0, achubcode);
	}

	public String getAchubcode() {
		return this.getField(0);
	}

	public void setActhreehubcode(String acthreehubcode) {
		this.setField(1, acthreehubcode);
	}

	public String getActhreehubcode() {
		return this.getField(1);
	}

	public void setAcename(String acename) {
		this.setField(2, acename);
	}

	public String getAcename() {
		return this.getField(2);
	}

	public void setAccname(String accname) {
		this.setField(3, accname);
	}

	public String getAccname() {
		return this.getField(3);
	}

}
