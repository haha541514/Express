package kyle.leis.ds.report.finance.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class FeegroupbyrvQuery extends JGeneralQuery {
	
	public FeegroupbyrvQuery(){
	    m_strSelectClause = "SELECT rv.rv_occurdate,rv.ck_code,cw.cw_customerewbcode,cw.cw_serverewbcode,cdt.dt_name,pk.pk_name,co.co_name, fk.fk_name,rv.rv_unitprice,rv.rv_unitnumber,rv.rv_actualtotal,rv.rv_currencyrate,sco.co_name,ee.ee_sname FROM t_bl_receivable rv,t_op_corewaybill cw,t_co_corporation co,t_co_corporation sco,t_di_feekind fk,t_di_productkind pk,t_di_district dt,t_di_district cdt,T_DI_ENTERPRISEELEMENT ee";
	    m_strWhereClause = "rv.cw_code = cw.cw_code and rv.co_code = co.co_code and cw.ee_code = ee.ee_code and cw.co_code_supplier = sco.co_code(+) and rv.fk_code = fk.fk_code and cw.pk_code = pk.pk_code and cw.dt_code_destination = dt.dt_code and dt.dt_countcode = cdt.dt_code and rv.fs_code != 'E' ";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "rv.fk_code in (~~)", "rv.co_code = ~~", "rv.rv_occurdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') > rv.rv_occurdate", "cw.pk_code in (~~)", "(cw.cw_customerewbcode in (~~) OR cw.cw_serverewbcode in (~~))", "cw.co_code_supplier = ~~", "ee.EE_Structurecode like '~~%'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 2, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new FeegroupbyrvColumns();
	}
	
	public void setFkcode(String fkcode) {
		this.setField(0, fkcode);
	}

	public String getFkcode() {
		return this.getField(0);
	}

	public void setCocode(String cocode) {
		this.setField(1, cocode);
	}

	public String getCocode() {
		return this.getField(1);
	}

	public void setStartrvoccurdate(String startrvoccurdate) {
		this.setField(2, startrvoccurdate);
	}

	public String getStartrvoccurdate() {
		return this.getField(2);
	}

	public void setEndrvoccurdate(String Endrvoccurdate) {
		this.setField(3, Endrvoccurdate);
	}

	public String getEndrvoccurdate() {
		return this.getField(3);
	}

	public void setPkcode(String pkcode) {
		this.setField(4, pkcode);
	}

	public String getPkcode() {
		return this.getField(4);
	}

	public void setIncustomerewbcode(String InCustomerewbcode) {
		this.setField(5, InCustomerewbcode);
	}

	public String getIncustomerewbcode() {
		return this.getField(5);
	}
	public void setInserverewbcode(String InServerewbcode) {
		this.setField(6, InServerewbcode);
	}

	public String getInserverewbcode() {
		return this.getField(6);
	}

	public void setCocodesupplier(String cocodesupplier) {
		this.setField(7, cocodesupplier);
	}

	public String getCocodesupplier() {
		return this.getField(7);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(8, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(8);
	}

}
