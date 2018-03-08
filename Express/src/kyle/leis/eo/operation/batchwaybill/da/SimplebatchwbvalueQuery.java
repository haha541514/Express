package kyle.leis.eo.operation.batchwaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SimplebatchwbvalueQuery extends JGeneralQuery {
	
	public SimplebatchwbvalueQuery(){
	    m_strSelectClause = "SELECT bwv.bw_code,bwv.cw_code,bwv.bwbv_serialno,bwv.bwbv_baglabelcode,bwv.bwbv_zonename,bwv.bwbv_id,cw.cw_serverchargeweight,cw.cw_billcounts,cw.cw_bagcounts,pk.pk_name,pk.pk_code,cw.cw_ewbcode,dt.dt_hubcode,bwv.bwbv_issuecontent FROM t_op_batchwaybillvalue bwv,t_op_corewaybill cw,t_op_housewaybill hw,t_op_batchwaybill bw, t_di_productkind pk, t_di_district dt";
	    m_strWhereClause = "bwv.cw_code = cw.cw_code and cw.cw_code = hw.cw_code and bw.bw_code = bwv.bw_code and cw.pk_code = pk.pk_code and cw.dt_code_signin = dt.dt_code and cw.cws_code != 'EL'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "bw.add_Date >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.add_Date", "cw.co_code_customer = '~~'", "cw.chn_code_supplier = '~~'", "bwv.bwbv_id = ~~", "pk.pk_code = '~~'", "cw.cw_code = ~~", "bw.bw_code = ~~", "bw.bw_labelcode = '~~'", "bw.adt_code = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SimplebatchwbvalueColumns();
	}
	
	public void setStartadddate(String StartAddDate) {
		this.setField(0, StartAddDate);
	}

	public String getStartadddate() {
		return this.getField(0);
	}

	public void setEndadddate(String EndAddDate) {
		this.setField(1, EndAddDate);
	}

	public String getEndadddate() {
		return this.getField(1);
	}

	public void setCocodecustomer(String cocodecustomer) {
		this.setField(2, cocodecustomer);
	}

	public String getCocodecustomer() {
		return this.getField(2);
	}

	public void setChncodesupplier(String chncodesupplier) {
		this.setField(3, chncodesupplier);
	}

	public String getChncodesupplier() {
		return this.getField(3);
	}

	public void setBwbvid(String bwbvid) {
		this.setField(4, bwbvid);
	}

	public String getBwbvid() {
		return this.getField(4);
	}

	public void setPkcode(String pkcode) {
		this.setField(5, pkcode);
	}

	public String getPkcode() {
		return this.getField(5);
	}

	public void setCwcode(String cwcode) {
		this.setField(6, cwcode);
	}

	public String getCwcode() {
		return this.getField(6);
	}

	public void setBwcode(String bwcode) {
		this.setField(7, bwcode);
	}

	public String getBwcode() {
		return this.getField(7);
	}

	public void setBwlabelcode(String bwlabelcode) {
		this.setField(8, bwlabelcode);
	}

	public String getBwlabelcode() {
		return this.getField(8);
	}

	public void setAdtcode(String adtcode) {
		this.setField(9, adtcode);
	}

	public String getAdtcode() {
		return this.getField(9);
	}

}
