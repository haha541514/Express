package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class ChanneltransferweightQuery extends JGeneralQuery {
	
	public ChanneltransferweightQuery(){
	    m_strSelectClause = "SELECT nvl(sum(cw.cw_serverchargeweight),0) as SumServerChargeweight FROM t_op_corewaybill cw";
	    m_strWhereClause = "cw.cws_code NOT IN ('EL', 'CEL', 'CTS', 'CHP')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.chn_code_supplier = '~~'", "to_char(cw.cw_createdate,'yyyy-mm-dd') = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new ChanneltransferweightColumns();
	}
	
	public void setChncodesupplier(String chnCodeSupplier) {
		this.setField(0, chnCodeSupplier);
	}

	public String getChncodesupplier() {
		return this.getField(0);
	}

	public void setCreatedate(String CreateDate) {
		this.setField(1, CreateDate);
	}

	public String getCreatedate() {
		return this.getField(1);
	}

}
