package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class UpsremotedistrictQuery extends JGeneralQuery{
	public UpsremotedistrictQuery(){
	    m_strSelectClause = "SELECT urd.URD_CODE,urd.URD_NATIONCODE,urd.URD_NATIONNAME,urd.URD_CITYNAME,urd.URD_STARTPOSTCODE,urd.URD_ENDPOSTCODE FROM T_DI_UPSREMOTEDISTRICT urd";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "urd.URD_NATIONCODE = '~~'", "urd.URD_CITYNAME = '~~'", "urd.URD_ENDPOSTCODE >= '~~'", "'~~'>= urd.URD_STARTPOSTCODE" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new UpsremotedistrictColumns();
	}
	
	public void setNationcode(String Nationcode) {
		this.setField(0, Nationcode);
	}

	public String getNationcode() {
		return this.getField(0);
	}

	public void setUrdcityname(String urdCityname) {
		this.setField(1, urdCityname);
	}

	public String getUrdcityname() {
		return this.getField(1);
	}

	public void setPostcode(String Postcode) {
		this.setField(2, Postcode);
	}

	public String getPostcode() {
		return this.getField(2);
	}

	public void setPostcode2(String Postcode2) {
		this.setField(3, Postcode2);
	}

	public String getPostcode2() {
		return this.getField(3);
	}

}
