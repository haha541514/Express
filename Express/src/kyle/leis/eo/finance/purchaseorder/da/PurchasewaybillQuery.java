package kyle.leis.eo.finance.purchaseorder.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class PurchasewaybillQuery extends JGeneralQuery {
	
	public PurchasewaybillQuery(){
	    m_strSelectClause = "SELECT powb.po_id,powb.cw_code FROM t_fi_purchaseorderwaybill powb";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "powb.po_id = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new PurchasewaybillColumns();
	}
	
	public void setPoid(String poId) {
		this.setField(0, poId);
	}

	public String getPoid() {
		return this.getField(0);
	}

}
