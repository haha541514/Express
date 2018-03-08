package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class DicdhldistrictQuery extends HGeneralQuery {
	
	public DicdhldistrictQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.district.da.DicdhldistrictColumns(di.ddCode,di.ddNationcode,di.ddNationname, di.ddStatecode,    di.ddStatename, di.ddCityname, di.ddHubcode,di.ddStartpostcode,   di.ddEndtpostcode, di.ddLocationcode, di.ddCitycname) FROM TdiDhldistrict  as di";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "di.ddCode = '~~'", "di.ddNationcode = '~~'", "di.ddNationname = '~~'", "di.ddStatecode = '~~'", "di.ddStatename = '~~'", "di.ddCityname = '~~'", "di.ddHubcode = '~~'", "di.ddStartpostcode = '~~'", "di.ddEndtpostcode = '~~'", "di.ddLocationcode = '~~'", "di.ddCitycname = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setDdcode(String ddCode) {
		this.setField(0, ddCode);
	}

	public String getDdcode() {
		return this.getField(0);
	}

	public void setDdnationcode(String ddNationcode) {
		this.setField(1, ddNationcode);
	}

	public String getDdnationcode() {
		return this.getField(1);
	}

	public void setDdnationname(String ddNationname) {
		this.setField(2, ddNationname);
	}

	public String getDdnationname() {
		return this.getField(2);
	}

	public void setDdstatecode(String ddStatecode) {
		this.setField(3, ddStatecode);
	}

	public String getDdstatecode() {
		return this.getField(3);
	}

	public void setDdstatename(String ddStatename) {
		this.setField(4, ddStatename);
	}

	public String getDdstatename() {
		return this.getField(4);
	}

	public void setDdcityname(String ddCityname) {
		this.setField(5, ddCityname);
	}

	public String getDdcityname() {
		return this.getField(5);
	}

	public void setDdhubcode(String ddHubcode) {
		this.setField(6, ddHubcode);
	}

	public String getDdhubcode() {
		return this.getField(6);
	}

	public void setDdstartpostcode(String ddStartpostcode) {
		this.setField(7, ddStartpostcode);
	}

	public String getDdstartpostcode() {
		return this.getField(7);
	}

	public void setDdendtpostcode(String ddEndtpostcode) {
		this.setField(8, ddEndtpostcode);
	}

	public String getDdendtpostcode() {
		return this.getField(8);
	}

	public void setDdlocationcode(String ddLocationcode) {
		this.setField(9, ddLocationcode);
	}

	public String getDdlocationcode() {
		return this.getField(9);
	}

	public void setDdcitycname(String ddCitycname) {
		this.setField(10, ddCitycname);
	}

	public String getDdcitycname() {
		return this.getField(10);
	}

}
