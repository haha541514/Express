package kyle.leis.eo.operation.customsdeclaration.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CustomsreportQuery extends JGeneralQuery {
	
	public CustomsreportQuery(){
	    m_strSelectClause = "SELECT cd.cd_labelcode,cd.cd_name,cd.cd_ename,cd.cd_totalprice,cd.cd_grossweight,cd.cd_pieces,hw.hw_consigneecompany,hw.hw_consigneetelephone,cw.cw_ewbcode,cw.cw_serverewbcode,hw.hw_signoutdate,cd.cd_amount,cd.cd_unitname,cd.cd_goodslabelcode FROM T_OP_CUSTOMSDECLARATION cd,t_op_housewaybill hw,t_op_corewaybill cw,t_op_batchwaybill bw";
	    m_strWhereClause = "cd.cw_code(+) = hw.cw_code and hw.cw_code = cw.cw_code and cw.bw_code_departure = bw.bw_code(+) and cw.cws_code NOT IN ('EL', 'CEL')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "hw.hw_signoutdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.hw_signoutdate", "cw.cw_code = ~~", "cw.cw_ewbcode = '~~'", "cw.cw_serverewbcode = '~~'", "cw.chn_code_supplier = '~~'", "bw.bw_labelcode = '~~'", "cw.ct_code = '~~'", "cd.cd_labelcode = '~~'", "exists (select cp.cp_id from t_op_corewaybillpieces cp where cp.cw_code = hw.cw_code and cp.cp_baglabelcode = '~~')", "exists (select * from t_op_transportwaybill tw,t_op_transportwaybillvalue twv,t_op_batchwaybill bw where tw.twb_id = twv.twb_id and twv.bw_code = bw.bw_code and bw.bw_code = cw.bw_code_departure and tw.twbs_code != 'E' and tw.twb_labelcode = '~~')", "cw.co_code_customer = '~~'", "FUN_GET_ESSTRUCTURECODE(cw.ee_code) like '~~%'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CustomsreportColumns();
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

	public void setCwcode(String cwcode) {
		this.setField(2, cwcode);
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

	public void setCwserverewbcode(String cwServerewbcode) {
		this.setField(4, cwServerewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(4);
	}

	public void setChncodesupplier(String chncodeSupplier) {
		this.setField(5, chncodeSupplier);
	}

	public String getChncodesupplier() {
		return this.getField(5);
	}

	public void setBwcodedeparture(String bwcodeDeparture) {
		this.setField(6, bwcodeDeparture);
	}

	public String getBwcodedeparture() {
		return this.getField(6);
	}

	public void setCtcode(String ctCode) {
		this.setField(7, ctCode);
	}

	public String getCtcode() {
		return this.getField(7);
	}

	public void setCdlabelcode(String cdLabelcode) {
		this.setField(8, cdLabelcode);
	}

	public String getCdlabelcode() {
		return this.getField(8);
	}

	public void setCpbaglabelcode(String cpBaglabelcode) {
		this.setField(9, cpBaglabelcode);
	}

	public String getCpbaglabelcode() {
		return this.getField(9);
	}

	public void setTwblabelcode(String twbLabelcode) {
		this.setField(10, twbLabelcode);
	}

	public String getTwblabelcode() {
		return this.getField(10);
	}

	public void setCocodecustomer(String coCodecustomer) {
		this.setField(11, coCodecustomer);
	}

	public String getCocodecustomer() {
		return this.getField(11);
	}

	public void setEestructurecode(String eestructurecode) {
		this.setField(12, eestructurecode);
	}

	public String getEestructurecode() {
		return this.getField(12);
	}

}
