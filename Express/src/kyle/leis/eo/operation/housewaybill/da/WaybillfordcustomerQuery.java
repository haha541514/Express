package kyle.leis.eo.operation.housewaybill.da;


import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class WaybillfordcustomerQuery extends JGeneralQuery {
	
	public WaybillfordcustomerQuery(){
	    m_strSelectClause = "SELECT cw.cw_code,cw.cw_customerewbcode,cw.cw_chargeweight,pk.pk_code,pk.pk_sname,pm.pm_code,pm.pm_name,ct.ct_code,ct.ct_name,odt.dt_code,odt.dt_hubcode,sdt.dt_code,sdt.dt_hubcode,ddt.dt_code,ddt.dt_hubcode,cddt.dt_hubcode,bw.bw_labelcode,bw.add_date,(select nvl(sum(round(rv.rv_currencyrate * rv.rv_actualtotal, 2)),0) from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.fs_code = 'C') as rvtotal, cw.cw_ewbcode FROM t_op_corewaybill cw,t_op_batchwaybill bw, t_di_productkind pk,t_di_district odt,t_di_district ddt,t_di_district cddt,t_di_district sdt,t_di_paymentmode pm,t_di_cargotype ct,t_co_corporation co";
	    m_strWhereClause = "cw.bw_code_arrival = bw.bw_code and cw.pk_code = pk.pk_code and cw.dt_code_origin = odt.dt_code and cw.dt_code_destination = ddt.dt_code(+) and ddt.dt_countcode = cddt.dt_code(+) and cw.dt_code_signin = sdt.dt_code(+) and cw.pm_code = pm.pm_code and cw.ct_code = ct.ct_code and cw.cws_code NOT IN ('EL', 'CEL', 'CTS', 'CHP') and cw.co_code_customer = co.co_code   and exists (select rv.rv_id from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.fs_code = 'C')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cw_customerewbcode = '~~'", "bw.bw_labelcode = '~~'", "pk.pk_code = '~~'", "ct.ct_code = '~~'", "bw.add_date >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.add_date", "cw.co_code_customer = '~~'", "(co.CO_CarryoverSign = '~~' OR co.CO_CarryoverDate > cw.cw_createdate)", "(co.CO_CarryoverSign = '~~' AND cw.cw_createdate > co.CO_CarryoverDate)" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new WaybillfordcustomerColumns();
	}
	
	public void setCw_customerewbcode(String cw_customerewbcode) {
		this.setField(0, cw_customerewbcode);
	}

	public String getCw_customerewbcode() {
		return this.getField(0);
	}

	public void setBw_labelcode(String bw_labelcode) {
		this.setField(1, bw_labelcode);
	}

	public String getBw_labelcode() {
		return this.getField(1);
	}

	public void setPk_code(String pk_code) {
		this.setField(2, pk_code);
	}

	public String getPk_code() {
		return this.getField(2);
	}

	public void setCt_code(String ct_code) {
		this.setField(3, ct_code);
	}

	public String getCt_code() {
		return this.getField(3);
	}

	public void setStartadddate(String StartAdddate) {
		this.setField(4, StartAdddate);
	}

	public String getStartadddate() {
		return this.getField(4);
	}

	public void setEndadddate(String EndAdddate) {
		this.setField(5, EndAdddate);
	}

	public String getEndadddate() {
		return this.getField(5);
	}

	public void setCo_code_customer(String co_code_customer) {
		this.setField(6, co_code_customer);
	}

	public String getCo_code_customer() {
		return this.getField(6);
	}

	public void setBegincarryoversign(String BeginCarryoversign) {
		this.setField(7, BeginCarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(7);
	}

	public void setEndcarryoversigin(String EndCarryoversigin) {
		this.setField(8, EndCarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(8);
	}

}
