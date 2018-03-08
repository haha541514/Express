package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CountrypostcodecountQuery extends JGeneralQuery {
	
	public CountrypostcodecountQuery(){
	    m_strSelectClause = "SELECT count(0) as c FROM t_di_dhldistrict dd";
	    m_strWhereClause = "dd.dd_startpostcode is not null";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "dd.dd_nationcode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CountrypostcodecountColumns();
	}
	
	public void setDd_nationcode(String dd_nationcode) {
		this.setField(0, dd_nationcode);
	}

	public String getDd_nationcode() {
		return this.getField(0);
	}

}
