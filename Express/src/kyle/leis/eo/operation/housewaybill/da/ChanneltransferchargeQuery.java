package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class ChanneltransferchargeQuery extends JGeneralQuery {
	
	public ChanneltransferchargeQuery(){
	    m_strSelectClause = "SELECT nvl(sum(round(py.py_currencyrate * py.py_actualtotal, 2)),0) as SumServerCharge FROM t_bl_payable py";
	    m_strWhereClause = "py.bk_code = 'A0201' and py.fs_code not in ('P','D','E')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "py.chn_code = '~~'", "to_char(py.py_occurdate,'yyyy-mm-dd') = '~~'", "to_char(py.py_occurdate,'yyyy-mm') = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new ChanneltransferchargeColumns();
	}
	
	public void setChncodesupplier(String chnCodeSupplier) {
		this.setField(0, chnCodeSupplier);
	}

	public String getChncodesupplier() {
		return this.getField(0);
	}

	public void setDayoccurdate(String dayoccurdate) {
		this.setField(1, dayoccurdate);
	}

	public String getDayoccurdate() {
		return this.getField(1);
	}

	public void setMonthoccurdate(String monthoccurdate) {
		this.setField(2, monthoccurdate);
	}

	public String getMonthoccurdate() {
		return this.getField(2);
	}

}
