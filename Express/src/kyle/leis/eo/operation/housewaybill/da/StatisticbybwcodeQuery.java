package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class StatisticbybwcodeQuery extends JGeneralQuery {
	
	public StatisticbybwcodeQuery(){
	    m_strSelectClause = "SELECT count(cw.cw_code) as pieces FROM t_op_corewaybill cw";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.BW_CODE_ARRIVAL = ~~", "cw.CO_CODE_CUSTOMER = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new StatisticbybwcodeColumns();
	}
	
	public void setBw_code_arrival(String BW_CODE_ARRIVAL) {
		this.setField(0, BW_CODE_ARRIVAL);
	}

	public String getBw_code_arrival() {
		return this.getField(0);
	}

	public void setCo_code_customer(String CO_CODE_CUSTOMER) {
		this.setField(1, CO_CODE_CUSTOMER);
	}

	public String getCo_code_customer() {
		return this.getField(1);
	}

}
