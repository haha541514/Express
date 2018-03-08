package kyle.leis.eo.operation.corewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SimplecorewaybillQuery extends JGeneralQuery {

	public SimplecorewaybillQuery() {
		m_strSelectClause = "SELECT cw.CW_CODE,cw.CWS_CODE,cw.CT_CODE,cw.PM_CODE,cw.PK_CODE,cw.IHS_CODE,cw.CO_CODE_CUSTOMER,cw.CO_CODE_SUPPLIER,cw.Chn_Code_Supplier,cw.cw_createdate, cw.Cw_Serverewbcode FROM t_op_corewaybill cw";
		m_strWhereClause = "";
		m_strOrderByClause = "";
		m_strGroupByClause = "";
		m_astrConditionWords = new String[] {
				"cw.CW_CODE = ~~",
				"cw.CW_CUSTOMEREWBCODE = '~~'",
				"cw.CW_SERVEREWBCODE = '~~'",
				"cw.CW_EWBCODE = '~~'",
				"cw.Chn_Code_Supplier = '~~'",
				"cw.co_code_customer = '~~'",
				"cw.cw_createdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')",
				"to_date('~~','yyyy-mm-dd hh24:mi:ss') >= cw.cw_createdate",
				"cw.cws_code not in (~~)",
				"exists (select cwp.cw_code from t_op_corewaybillpieces cwp where cwp.cw_code = cw.cw_code and cwp.cp_labelcode = '~~')" };
		m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	}

	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SimplecorewaybillColumns();
	}

	public void setCw_code(String CW_CODE) {
		this.setField(0, CW_CODE);
	}

	public String getCw_code() {
		return this.getField(0);
	}

	public void setCw_customerewbcode(String CW_CUSTOMEREWBCODE) {
		this.setField(1, CW_CUSTOMEREWBCODE);
	}

	public String getCw_customerewbcode() {
		return this.getField(1);
	}

	public void setCw_serverewbcode(String CW_SERVEREWBCODE) {
		this.setField(2, CW_SERVEREWBCODE);
	}

	public String getCw_serverewbcode() {
		return this.getField(2);
	}

	public void setCw_ewbcode(String CW_EWBCODE) {
		this.setField(3, CW_EWBCODE);
	}

	public String getCw_ewbcode() {
		return this.getField(3);
	}

	public void setChncodesupplier(String Chncodesupplier) {
		this.setField(4, Chncodesupplier);
	}

	public String getChncodesupplier() {
		return this.getField(4);
	}

	public void setCocodecustomer(String cocodecustomer) {
		this.setField(5, cocodecustomer);
	}

	public String getCocodecustomer() {
		return this.getField(5);
	}

	public void setStartcreatedate(String startcreatedate) {
		this.setField(6, startcreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(6);
	}

	public void setEndcreatedate(String endcreatedate) {
		this.setField(7, endcreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(7);
	}

	public void setNoincwscode(String Noincwscode) {
		this.setField(8, Noincwscode);
	}

	public String getNoincwscode() {
		return this.getField(8);
	}

	public void setSchildlabelcode(String schildlabelcode) {
		this.setField(9, schildlabelcode);
	}

	public String getSchildlabelcode() {
		return this.getField(9);
	}

}
