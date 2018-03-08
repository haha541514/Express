package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CountserverpayableQuery extends JGeneralQuery {
	
	public CountserverpayableQuery(){
	    m_strSelectClause = "SELECT count(1) count FROM t_fi_serverpayable spy ,t_fi_serverwaybill swb";
	    m_strWhereClause = "spy.swb_code = swb.swb_code";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "swb.swb_code = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CountserverpayableColumns();
	}
	
	public void setSwbserverewbcode(String swbServerewbcode) {
		this.setField(0, swbServerewbcode);
	}

	public String getSwbserverewbcode() {
		return this.getField(0);
	}

}
