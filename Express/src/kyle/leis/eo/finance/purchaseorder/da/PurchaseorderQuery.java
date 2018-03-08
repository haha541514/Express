package kyle.leis.eo.finance.purchaseorder.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class PurchaseorderQuery extends JGeneralQuery {
	
	public PurchaseorderQuery(){
	    m_strSelectClause = "SELECT po.po_id,po.ss_code,ss.ss_name,po.po_op_id_creator,po.po_op_id_modifier,po.po_createdate,po.po_modifydate,po.po_occurdate,opc.op_name  creator,opm.op_name modifier FROM t_fi_purchaseorder po,t_di_operator opc,t_di_operator opm,t_di_simplestatus ss";
	    m_strWhereClause = "po.po_op_id_creator = opc.op_id and po.po_op_id_modifier = opm.op_id and po.ss_code = ss.ss_code";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "po.po_id = ~~", "po.ss_code = '~~'", "po.po_occurdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= po.po_occurdate" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new PurchaseorderColumns();
	}
	
	public void setPoid(String poId) {
		this.setField(0, poId);
	}

	public String getPoid() {
		return this.getField(0);
	}

	public void setSscode(String ssCode) {
		this.setField(1, ssCode);
	}

	public String getSscode() {
		return this.getField(1);
	}

	public void setStartdate(String StartDate) {
		this.setField(2, StartDate);
	}

	public String getStartdate() {
		return this.getField(2);
	}

	public void setEnddate(String EndDate) {
		this.setField(3, EndDate);
	}

	public String getEnddate() {
		return this.getField(3);
	}

}
