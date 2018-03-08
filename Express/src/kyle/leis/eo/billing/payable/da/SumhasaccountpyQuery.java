package kyle.leis.eo.billing.payable.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SumhasaccountpyQuery extends JGeneralQuery {
	
	public SumhasaccountpyQuery(){
	    m_strSelectClause = "SELECT nvl(sum(round(py.py_currencyrate * py.py_actualtotal,2)),0) as sumPYTotal FROM T_BL_PAYABLE py";
	    m_strWhereClause = "py.bk_code = 'A0201'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "py.co_code = '~~'", "py.br_id = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SumhasaccountpyColumns();
	}
	
	public void setCocode(String coCode) {
		this.setField(0, coCode);
	}

	public String getCocode() {
		return this.getField(0);
	}

	public void setBrid(String brId) {
		this.setField(1, brId);
	}

	public String getBrid() {
		return this.getField(1);
	}

}
