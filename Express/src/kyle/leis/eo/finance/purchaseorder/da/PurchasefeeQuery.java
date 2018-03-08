package kyle.leis.eo.finance.purchaseorder.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class PurchasefeeQuery extends JGeneralQuery {
	
	public PurchasefeeQuery(){
	    m_strSelectClause = "SELECT pof.po_id,pof.fk_code,pof.pof_commissionrate FROM t_fi_purchaseorderfee pof";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "pof.po_id = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new PurchasefeeColumns();
	}
	
	public void setPoid(String poId) {
		this.setField(0, poId);
	}

	public String getPoid() {
		return this.getField(0);
	}

}
