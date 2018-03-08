package kyle.leis.eo.billing.receivable.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class ReceivableforfaxQuery extends JGeneralQuery {
	
	public ReceivableforfaxQuery(){
	    m_strSelectClause = "SELECT co.co_sname,co.co_labelcode,bw.bw_labelcode,cw.cw_ewbcode,cw.cw_customerewbcode,co.co_code,pk.pk_sname,ct.ct_name,cw.cw_pieces,cw.cw_grossweight,cw.cw_chargeweight, cdt.dt_name,fk.fk_name,rv.rv_currencyrate,rv.rv_actualtotal,round(rv.rv_actualtotal * rv.rv_currencyrate, 2) as sumrvactualtotal,ck.ck_name,rv.rv_occurdate FROM t_bl_receivable rv,t_op_corewaybill cw,t_op_batchwaybill bw,t_co_corporation co,t_di_feekind fk,t_di_productkind pk,t_di_cargotype ct,t_di_district dt,t_di_district cdt,t_di_currencykind ck";
	    m_strWhereClause = "rv.cw_code = cw.cw_code and cw.bw_code_arrival = bw.bw_code and rv.co_code = co.co_code and rv.fk_code = fk.fk_code and cw.pk_code = pk.pk_code and cw.ct_code = ct.ct_code and cw.dt_code_destination = dt.dt_code and dt.dt_countcode = cdt.dt_code and rv.ck_code = ck.ck_code and rv.fs_code != 'E' and cw.cws_code NOT IN ('EL', 'CEL')";
	    m_strOrderByClause = "co.co_code,cw.cw_ewbcode,cw.cw_createdate";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.co_code_supplier = '~~'", "rv.co_code = '~~'", "rv.fs_code in (~~)", "rv.fs_code not in (~~)", "rv.cw_code = ~~", "rv.br_id = ~~", "rv.br_id is ~~", "rv.fk_code IN (~~)", "rv.rv_Occurdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= rv.rv_Occurdate" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new ReceivableforfaxColumns();
	}
	
	public void setCo_code_supplier(String co_code_supplier) {
		this.setField(0, co_code_supplier);
	}

	public String getCo_code_supplier() {
		return this.getField(0);
	}

	public void setCocode(String coCode) {
		this.setField(1, coCode);
	}

	public String getCocode() {
		return this.getField(1);
	}

	public void setInfscode(String InfsCode) {
		this.setField(2, InfsCode);
	}

	public String getInfscode() {
		return this.getField(2);
	}

	public void setNotinfscode(String NotInfsCode) {
		this.setField(3, NotInfsCode);
	}

	public String getNotinfscode() {
		return this.getField(3);
	}

	public void setCw_code(String cw_code) {
		this.setField(4, cw_code);
	}

	public String getCw_code() {
		return this.getField(4);
	}

	public void setBrid(String brId) {
		this.setField(5, brId);
	}

	public String getBrid() {
		return this.getField(5);
	}

	public void setIsnullsign(String IsNullSign) {
		this.setField(6, IsNullSign);
	}

	public String getIsnullsign() {
		return this.getField(6);
	}

	public void setFkcode(String fkCode) {
		this.setField(7, fkCode);
	}

	public String getFkcode() {
		return this.getField(7);
	}

	public void setStartrvoccurdate(String StartRvOccurdate) {
		this.setField(8, StartRvOccurdate);
	}

	public String getStartrvoccurdate() {
		return this.getField(8);
	}

	public void setEndrvoccurdate(String EndRvOccurdate) {
		this.setField(9, EndRvOccurdate);
	}

	public String getEndrvoccurdate() {
		return this.getField(9);
	}

}
