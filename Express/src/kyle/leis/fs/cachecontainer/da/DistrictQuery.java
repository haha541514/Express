package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class DistrictQuery extends HGeneralQuery {
	
	public DistrictQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.DistrictColumns(dt.dtCode, dt.dtHubcode, dt.dtName, dt.dtEname, dt.dtGrade, dtk.dkCode, dtcountry.dtCode, dtcountry.dtHubcode, dtcountry.dtName, dtcountry.dtEname,dt.dtStartcitysign) FROM TdiDistrict as dt inner join dt.tdiDistrict as dtcountry inner join dt.tdiDistrictkind as dtk";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "dtk.dkCode = '~~'", "dt.dtCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setDkcode(String Dkcode) {
		this.setField(0, Dkcode);
	}

	public String getDkcode() {
		return this.getField(0);
	}

	public void setDtcode(String dtCode) {
		this.setField(1, dtCode);
	}

	public String getDtcode() {
		return this.getField(1);
	}

}
