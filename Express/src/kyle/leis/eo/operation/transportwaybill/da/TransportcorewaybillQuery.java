package kyle.leis.eo.operation.transportwaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class TransportcorewaybillQuery extends JGeneralQuery {
	
	public TransportcorewaybillQuery(){
	    m_strSelectClause = "SELECT distinct twbv.twb_id,bw.bw_code,cw.cw_code,cw.cw_ewbcode,cw.cw_customerewbcode,cw.cw_serverewbcode,cw.cw_chargeweight,cw.ct_code,cw.pm_code,cw.cw_grossweight FROM t_op_transportwaybillvalue twbv,t_op_batchwaybill bw,t_op_corewaybill cw";
	    m_strWhereClause = "twbv.bw_code = bw.bw_code and bw.bw_code = cw.bw_code_departure and cw.cws_code NOT IN ('EL', 'CEL') AND twbv.cw_code is null";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "twbv.twb_id = ~~", "bw.bw_code = ~~", "cw.cw_code = ~~", "cw.cw_ewbcode = '~~'", "cw.cw_customerewbcode = '~~'", "cw.chn_code_supplier in ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new TransportcorewaybillColumns();
	}
	
	public void setTwbid(String twbId) {
		this.setField(0, twbId);
	}

	public String getTwbid() {
		return this.getField(0);
	}

	public void setBwcode(String bwCode) {
		this.setField(1, bwCode);
	}

	public String getBwcode() {
		return this.getField(1);
	}

	public void setCwcode(String cwCode) {
		this.setField(2, cwCode);
	}

	public String getCwcode() {
		return this.getField(2);
	}

	public void setCwewbcode(String cwEwbcode) {
		this.setField(3, cwEwbcode);
	}

	public String getCwewbcode() {
		return this.getField(3);
	}

	public void setCwcustomerewbcode(String cwCustomerewbcode) {
		this.setField(4, cwCustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(4);
	}

	public void setInchncodesupplier(String InChncodeSupplier) {
		this.setField(5, InChncodeSupplier);
	}

	public String getInchncodesupplier() {
		return this.getField(5);
	}

}
