package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SumchargeweightQuery extends JGeneralQuery {
	
	public SumchargeweightQuery(){
	    m_strSelectClause = "SELECT nvl(sum(cw.cw_chargeweight), 0) as Sumchargeweight FROM t_op_corewaybill cw";
	    m_strWhereClause = "cw.cws_code NOT IN ('EL', 'CEL', 'CTS', 'CHP')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.BW_CODE_ARRIVAL = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SumchargeweightColumns();
	}
	
	public void setBw_code_arrival(String BW_CODE_ARRIVAL) {
		this.setField(0, BW_CODE_ARRIVAL);
	}

	public String getBw_code_arrival() {
		return this.getField(0);
	}

}
