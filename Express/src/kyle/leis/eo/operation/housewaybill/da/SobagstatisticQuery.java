package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SobagstatisticQuery extends JGeneralQuery {
	
	public SobagstatisticQuery(){
	    m_strSelectClause = "SELECT cw.cw_serverchargeweight,(select cwp.cp_baglabelcode from t_op_corewaybillpieces cwp where cwp.cw_code = cw.cw_code and rownum < 2) as bagno FROM t_op_corewaybill cw,t_op_batchwaybill bw";
	    m_strWhereClause = "cw.bw_code_departure = bw.bw_code";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "bw.add_date >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.add_date", "cw.chn_code_supplier = '~~'", "bw.bw_labelcode = '~~'", "exists (select cp.cp_id from t_op_corewaybillpieces cp where cp.cw_code = cw.cw_code and cp.cp_baglabelcode = '~~')", "exists (select * from t_op_transportwaybill tw,t_op_transportwaybillvalue twv,t_op_batchwaybill bw where tw.twb_id = twv.twb_id and twv.bw_code = bw.bw_code and bw.bw_code = cw.bw_code_departure and tw.twbs_code != 'E' and tw.twb_labelcode = '~~')" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SobagstatisticColumns();
	}
	
	public void setStartdate(String StartDate) {
		this.setField(0, StartDate);
	}

	public String getStartdate() {
		return this.getField(0);
	}

	public void setEnddate(String EndDate) {
		this.setField(1, EndDate);
	}

	public String getEnddate() {
		return this.getField(1);
	}

	public void setChncodesupplier(String chncodeSupplier) {
		this.setField(2, chncodeSupplier);
	}

	public String getChncodesupplier() {
		return this.getField(2);
	}

	public void setBwcodedeparture(String bwcodeDeparture) {
		this.setField(3, bwcodeDeparture);
	}

	public String getBwcodedeparture() {
		return this.getField(3);
	}

	public void setCpbaglabelcode(String cpbaglabelcode) {
		this.setField(4, cpbaglabelcode);
	}

	public String getCpbaglabelcode() {
		return this.getField(4);
	}

	public void setTwblabelcode(String twbLabelcode) {
		this.setField(5, twbLabelcode);
	}

	public String getTwblabelcode() {
		return this.getField(5);
	}

}
