package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class DhldistrictQuery extends JGeneralQuery {
	
	public DhldistrictQuery(){
	    m_strSelectClause = "SELECT dhl.dd_cityname,dhl.dd_hubcode,dhl.dd_startpostcode,dhl.dd_endtpostcode FROM t_di_dhldistrict dhl";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "dhl.dd_nationcode = '~~'", "dhl.dd_endtpostcode >= '~~'", "'~~' >= dhl.dd_startpostcode" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new DhldistrictColumns();
	}
	
	public void setDdnationcode(String ddnationcode) {
		this.setField(0, ddnationcode);
	}

	public String getDdnationcode() {
		return this.getField(0);
	}

	public void setStartpostcode(String Startpostcode) {
		this.setField(1, Startpostcode);
	}

	public String getStartpostcode() {
		return this.getField(1);
	}

	public void setEndpostcode(String Endpostcode) {
		this.setField(2, Endpostcode);
	}

	public String getEndpostcode() {
		return this.getField(2);
	}

}
