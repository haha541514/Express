package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SumserverpayableQuery extends JGeneralQuery {
	
	public SumserverpayableQuery(){
	    m_strSelectClause = "SELECT nvl(sum(round(spy.SPY_TOTALCHARGE,2)),0) as totalcharge FROM T_FI_SERVERPAYABLE spy";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "spy.SBR_ID = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SumserverpayableColumns();
	}
	
	public void setSprid(String sprId) {
		this.setField(0, sprId);
	}

	public String getSprid() {
		return this.getField(0);
	}

}
