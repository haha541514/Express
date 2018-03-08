package kyle.leis.eo.finance.purchaseorder.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class PurchasepayableQuery extends JGeneralQuery {
	
	public PurchasepayableQuery(){
	    m_strSelectClause = "SELECT py.py_id,py.cw_code,py.bk_code,py.fk_code,py.py_commissionrate FROM t_bl_payable py";
	    m_strWhereClause = " py.bk_code = 'A0202'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "py.cw_code = ~~", "py.fk_code = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new PurchasepayableColumns();
	}
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setFkcode(String fkCode) {
		this.setField(1, fkCode);
	}

	public String getFkcode() {
		return this.getField(1);
	}

}
