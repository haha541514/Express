package kyle.leis.eo.finance.purchaseorder.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class WaybillpayableQuery extends JGeneralQuery {
	
	public WaybillpayableQuery(){
	    m_strSelectClause = "SELECT powb.po_id,powb.cw_code ,py.py_id FROM t_fi_purchaseorder po,t_fi_purchaseorderwaybill powb,t_bl_payable py";
	    m_strWhereClause = "po.po_id = powb.po_id and powb.cw_code = py.cw_code and po.ss_code = 'ON'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "po.po_id = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new WaybillpayableColumns();
	}
	
	public void setPoid(String poId) {
		this.setField(0, poId);
	}

	public String getPoid() {
		return this.getField(0);
	}

}
