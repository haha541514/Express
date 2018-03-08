package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class StatisticdhlremotedistrictQuery extends JGeneralQuery {
	
	public StatisticdhlremotedistrictQuery(){
	    m_strSelectClause = "SELECT drd.drd_nationname as nationname,count(drd.DRD_CODE) as counts FROM T_DI_DHLREMOTEDISTRICT drd";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "drd.drd_nationname";
	    m_astrConditionWords = new String[] { "drd.drd_nationcode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new StatisticdhlremotedistrictColumns();
	}
	
	public void setDrd_nationcode(String drd_nationcode) {
		this.setField(0, drd_nationcode);
	}

	public String getDrd_nationcode() {
		return this.getField(0);
	}

}
