package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class DhldistrictlocationQuery extends JGeneralQuery {
	
	public DhldistrictlocationQuery(){
	    m_strSelectClause = "SELECT dhl.dd_cityname,dhl.dd_hubcode,dhl.dd_startpostcode,dhl.dd_endtpostcode,dhl.dd_locationcode FROM t_di_dhldistrict dhl";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "dhl.dd_nationcode = '~~'", "replace(dhl.dd_cityname,'^',',') = '~~'", "dhl.dd_hubcode = '~~'", "dhl.dd_startpostcode <= '~~'", "'~~' <= dhl.dd_endtpostcode" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new DhldistrictlocationColumns();
	}
	
	public void setDdnationcode(String ddnationcode) {
		this.setField(0, ddnationcode);
	}

	public String getDdnationcode() {
		return this.getField(0);
	}

	public void setDdcityname(String ddcityname) {
		this.setField(1, ddcityname);
	}

	public String getDdcityname() {
		return this.getField(1);
	}

	public void setDdhubcode(String ddhubcode) {
		this.setField(2, ddhubcode);
	}

	public String getDdhubcode() {
		return this.getField(2);
	}

	public void setStartpostcode(String Startpostcode) {
		this.setField(3, Startpostcode);
	}

	public String getStartpostcode() {
		return this.getField(3);
	}

	public void setEndpostcode(String Endpostcode) {
		this.setField(4, Endpostcode);
	}

	public String getEndpostcode() {
		return this.getField(4);
	}

}
