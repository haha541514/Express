package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class UspsdistrictQuery extends JGeneralQuery {
	
	public UspsdistrictQuery(){
	    m_strSelectClause = "SELECT ddt.dd_statename FROM T_DI_DHLDISTRICT ddt";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "ddt.Dd_Hubcode = '~~'", "ddt.dd_cityname = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new UspsdistrictColumns();
	}
	
	public void setHubcode(String hubCode) {
		this.setField(0, hubCode);
	}

	public String getHubcode() {
		return this.getField(0);
	}

	public void setCityname(String cityName) {
		this.setField(1, cityName);
	}

	public String getCityname() {
		return this.getField(1);
	}

}
