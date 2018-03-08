package kyle.leis.eo.operation.corewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SimplecorewaybillcountQuery extends JGeneralQuery {
	
	public SimplecorewaybillcountQuery(){
	    m_strSelectClause = "SELECT count(1) as libcount FROM t_op_corewaybill cw";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.co_code_customer = '~~'", "cw.cw_createdate >= to_date('~~', 'yyyy-mm-dd hh24:mi:ss')and to_date('~~', 'yyyy-mm-dd hh24:mi:ss') >= cw.cw_createdate" };
	    m_aiConditionVariableCount = new int[] { 1, 2 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SimplecorewaybillcountColumns();
	}
	
	public void setCocodecustomer(String cocodecustomer) {
		this.setField(0, cocodecustomer);
	}

	public String getCocodecustomer() {
		return this.getField(0);
	}

	public void setStartime(String startime) {
		this.setField(1, startime);
	}

	public String getStartime() {
		return this.getField(1);
	}
	public void setEndtime(String endtime) {
		this.setField(2, endtime);
	}

	public String getEndtime() {
		return this.getField(2);
	}

}
