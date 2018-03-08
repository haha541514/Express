package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class XinshdistrictQuery extends JGeneralQuery {
	
	public XinshdistrictQuery(){
	    m_strSelectClause = "SELECT distinct dt.dt_statecode as dt_statecode FROM t_di_district dt";
	    m_strWhereClause = "dt.dt_countcode = 1 and dt.dt_hubcode like 'CN%' and dt.dt_code != 1";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new XinshdistrictColumns();
	}
	

}
