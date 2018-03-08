package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class FdxremotedistrictQuery extends JGeneralQuery {
	
	public FdxremotedistrictQuery(){
	    m_strSelectClause = "SELECT frd.FRD_CODE,frd.FRD_NATIONCODE,frd.FRD_NATIONNAME,frd.FRD_CITYNAME,frd.FRD_STARTPOSTCODE,frd.FRD_ENDPOSTCODE FROM T_DI_FEDEXREMOTEDISTRICT frd";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "frd.FRD_NATIONCODE = '~~'", "frd.FRD_CITYNAME = '~~'", "frd.FRD_ENDPOSTCODE >= '~~'", "'~~'>= frd.FRD_STARTPOSTCODE" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new FdxremotedistrictColumns();
	}
	
	public void setNationcode(String Nationcode) {
		this.setField(0, Nationcode);
	}

	public String getNationcode() {
		return this.getField(0);
	}

	public void setFrdcityname(String frdCityname) {
		this.setField(1, frdCityname);
	}

	public String getFrdcityname() {
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
