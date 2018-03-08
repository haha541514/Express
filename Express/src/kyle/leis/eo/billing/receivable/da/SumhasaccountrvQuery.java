package kyle.leis.eo.billing.receivable.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SumhasaccountrvQuery extends JGeneralQuery {
	
	public SumhasaccountrvQuery(){
	    m_strSelectClause = "SELECT nvl(sum(round(rv.rv_actualtotal * rv.rv_currencyrate,2)),0) as sumRvTotal FROM t_bl_receivable rv";
	    m_strWhereClause = "rv.bk_code = 'A0101'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "rv.co_code = '~~'", "rv.br_id = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SumhasaccountrvColumns();
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
