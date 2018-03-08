package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class DhlremotedistrictQuery extends JGeneralQuery {
	
	public DhlremotedistrictQuery(){
	    m_strSelectClause = "SELECT drd.DRD_Code,drd.DRD_NationCode,drd.DRD_NationName,drd.DRD_StateName,drd.DRD_CityName,drd.DRD_PostCode FROM T_DI_DHLREMOTEDISTRICT drd";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "drd.DRD_NationCode = '~~'", "drd.DRD_CityName = '~~'", "drd.DRD_PostCode = '~~'", "drd.DRD_NationName = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new DhlremotedistrictColumns();
	}
	
	public void setDrdnationcode(String DrdNationcode) {
		this.setField(0, DrdNationcode);
	}

	public String getDrdnationcode() {
		return this.getField(0);
	}

	public void setDrdcityname(String DrdCityname) {
		this.setField(1, DrdCityname);
	}

	public String getDrdcityname() {
		return this.getField(1);
	}

	public void setDrdpostcode(String DrdPostcode) {
		this.setField(2, DrdPostcode);
	}

	public String getDrdpostcode() {
		return this.getField(2);
	}

	public void setDrdnationname(String DrdNationname) {
		this.setField(3, DrdNationname);
	}

	public String getDrdnationname() {
		return this.getField(3);
	}

}
