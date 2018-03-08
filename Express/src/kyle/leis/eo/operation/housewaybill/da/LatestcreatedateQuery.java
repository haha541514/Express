package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class LatestcreatedateQuery extends JGeneralQuery {
	
	public LatestcreatedateQuery(){
	    m_strSelectClause = "SELECT max(cw.cw_createdate) as LatestCreatedate FROM t_op_corewaybill cw";
	    m_strWhereClause = "cw.cws_code in ('SI','IP','SO')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.co_code_customer =  ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new LatestcreatedateColumns();
	}
	
	public void setCo_code_customer(String co_code_customer) {
		this.setField(0, co_code_customer);
	}

	public String getCo_code_customer() {
		return this.getField(0);
	}

}
