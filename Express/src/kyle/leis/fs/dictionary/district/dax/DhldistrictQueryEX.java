package kyle.leis.fs.dictionary.district.dax;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.district.da.DhldistrictQuery;

public class DhldistrictQueryEX extends DhldistrictQuery {
	public DhldistrictQueryEX(String ddCityName, String ddHubCode){
		m_strWhereClause = " 1 = 1 ";
		if (!StringUtility.isNull(ddCityName)) {
			m_strWhereClause += " and dhl.DD_CITYNAME like '" + ddCityName + "%' "; 
		}
		if (!StringUtility.isNull(ddHubCode)) {
			m_strWhereClause += " and dhl.DD_HUBCODE like '" + ddHubCode + "%' ";
		}
	}
	/**
	 * 
	 * @param ddCityName 英文名
	 * @param ddHubCode 机场代码
	 * @param ddCityCName 中文名
	 */
	public DhldistrictQueryEX(String ddCityName, String ddHubCode, String ddCityCName){
		m_strSelectClause = "SELECT dhl.dd_cityname,dhl.dd_hubcode,dhl.dd_startpostcode,dhl.dd_endtpostcode, dhl.dd_citycname FROM t_di_dhldistrict dhl";
		m_strWhereClause = " 1 = 1 ";
		if (!StringUtility.isNull(ddCityName)) {
			m_strWhereClause += " and dhl.DD_CITYNAME like '" + ddCityName + "%' "; 
		}
		if (!StringUtility.isNull(ddHubCode)) {
			m_strWhereClause += " and dhl.DD_HUBCODE like '" + ddHubCode + "%' ";
		}
		if (!StringUtility.isNull(ddCityCName)) {
			m_strWhereClause += " and dhl.DD_CITYCNAME like '" + ddCityCName + "%' ";
		}
	}
	
	@Override
	public IColumns createColumns() {
		return new DhldistrictColumnsEX();
	}
}
