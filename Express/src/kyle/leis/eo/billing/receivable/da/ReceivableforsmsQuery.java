package kyle.leis.eo.billing.receivable.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class ReceivableforsmsQuery extends JGeneralQuery {
	
	public ReceivableforsmsQuery(){
	    m_strSelectClause = "SELECT sum(rv.rv_actualtotal) as rvrv_actualtotal FROM t_bl_receivable rv,t_op_corewaybill cw";
	    m_strWhereClause = "rv.cw_code = cw.cw_code and rv.bk_code = 'A0101'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.bw_code_arrival = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new ReceivableforsmsColumns();
	}
	
	public void setBw_code_arrival(String bw_code_arrival) {
		this.setField(0, bw_code_arrival);
	}

	public String getBw_code_arrival() {
		return this.getField(0);
	}

}
