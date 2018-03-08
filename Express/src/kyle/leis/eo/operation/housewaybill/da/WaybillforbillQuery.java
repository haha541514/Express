package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class WaybillforbillQuery extends JGeneralQuery {
	
	public WaybillforbillQuery(){
	    m_strSelectClause = "SELECT cw.cw_code,cw.cw_customerewbcode,cw.cw_ewbcode,cw.cw_serverewbcode,chn.chn_sname,co.co_code,co.co_labelcode,co.co_sname,cw.cw_grossweight,cw.cw_pieces,cw.cw_chargeweight,cw.cw_serverchargeweight,pk.pk_code,pk.pk_sname,pm.pm_code,pm.pm_name,ct.ct_code,ct.ct_name,odt.dt_code,odt.dt_hubcode,sdt.dt_code,sdt.dt_hubcode,ddt.dt_code,ddt.dt_hubcode,cddt.dt_code,cddt.dt_hubcode,bw.bw_labelcode,bw.add_date,dbw.bw_labelcode,dbw.add_date,cws.cws_code,cws.cws_name,(select nvl(sum(round(rv.rv_actualtotal, 2)),0) from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.fs_code != 'E' and rv.bk_code = 'A0101' and rv.ck_code = 'HKD') as rvtotal,(select nvl(sum(round(rv.rv_actualtotal, 2)),0) from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.bk_code = 'A0101' and rv.fs_code != 'E' and rv.ck_code = 'RMB') as rvrmbtotal,(select nvl(sum(round(py.py_actualtotal, 2)),0) from t_bl_payable py where py.cw_code = cw.cw_code and py.fs_code != 'E' and py.bk_code = 'A0201' and py.ck_code = 'HKD') as pytotal,(select nvl(sum(round(py.py_actualtotal, 2)),0) from t_bl_payable py where py.cw_code = cw.cw_code and py.fs_code != 'E' and py.bk_code = 'A0201' and py.ck_code = 'RMB') as pyrmbtotal,(select nvl(sum(round(py.py_currencyrate * py.py_actualtotal, 2)),0) from t_bl_payable py where py.cw_code = cw.cw_code and py.fs_code != 'E' and py.bk_code = 'A0201') as pyalltotal,sco.co_code,sco.co_labelcode,sco.co_sname,(select nvl(sum(round(rv.rv_actualtotal, 2)),0) from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.fs_code != 'E' and rv.ck_code = 'USD') as rvusdtotal,(select nvl(sum(round(py.py_actualtotal, 2)),0) from t_bl_payable py where py.cw_code = cw.cw_code and py.fs_code != 'E' and py.bk_code = 'A0201' and py.ck_code = 'USD') as pyusdtotal,ee.ee_code,ee.ee_sname,(select nvl(sum(round(py.py_actualtotal, 2)),0) from t_bl_payable py where py.cw_code = cw.cw_code and py.fs_code != 'E' and py.bk_code = 'A0201' and py.ck_code = 'EUR') as pyeurtotal,cct.CT_CODE,cct.CT_NAME FROM t_op_corewaybill cw,t_op_batchwaybill bw,t_op_batchwaybill dbw,t_co_corporation co,t_di_productkind pk,t_di_district odt,t_di_district ddt,t_di_district cddt,t_di_district sdt,t_di_paymentmode pm,t_di_cargotype ct,t_di_corewaybillstatus  cws,t_chn_channel chn, t_co_corporation sco,t_di_enterpriseelement ee,t_co_customer cm,T_DI_CUSTOMERTYPE cct";
	    m_strWhereClause = "cw.bw_code_arrival = bw.bw_code and cw.bw_code_departure = dbw.bw_code(+) and cw.chn_code_supplier = chn.chn_code(+) and cw.co_code_customer = co.co_code and co.co_code = cm.co_code(+) and cm.ct_code = cct.ct_code and cw.co_code_supplier = sco.co_code(+) and cw.pk_code = pk.pk_code and cw.dt_code_origin = odt.dt_code and cw.dt_code_destination = ddt.dt_code(+) and ddt.dt_countcode = cddt.dt_code(+) and cw.dt_code_signin = sdt.dt_code(+) and cw.pm_code = pm.pm_code and cw.ct_code = ct.ct_code and cws.cws_code = cw.cws_code and cw.ee_code = ee.ee_code and cw.cws_code NOT IN ('EL', 'CEL', 'CTS', 'CHP')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cw_customerewbcode in (~~)", "cw.cw_ewbcode in (~~)", "cw.cw_serverewbcode in (~~)", "cw.chn_code_supplier = '~~'", "cw.co_code_supplier = '~~'", "bw.bw_labelcode = '~~'", "dbw.bw_labelcode = '~~'", "cws.cws_code in (~~)", "pm.pm_code = '~~'", "pk.pk_code = '~~'", "ct.ct_code = '~~'", "odt.dt_code = '~~'", "ddt.dt_code = '~~'", "cddt.dt_code = '~~'", "cw.co_code_customer = '~~'", "bw.add_date >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.add_date", "dbw.add_date >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= dbw.add_date", "(co.CO_CarryoverSign = '~~' OR co.CO_CarryoverDate > cw.cw_createdate)", "(co.CO_CarryoverSign = '~~' AND cw.cw_createdate > co.CO_CarryoverDate)", "ee.ee_Code = '~~'", "ee.ee_Structurecode like '~~%'", "FUN_GET_TOTALCARGOVALUE(cw.cw_code) >= ~~", "~~ >= FUN_GET_TOTALCARGOVALUE(cw.cw_code)", "not exists (select * from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.fk_code = 'A0101' and rv.fs_code != 'E' and  '~~' = 'Y')", "cm.cm_op_id_sale = '~~'", "cct.ct_Code= '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new WaybillforbillColumns();
	}
	
	public void setCw_customerewbcode(String cw_customerewbcode) {
		this.setField(0, cw_customerewbcode);
	}

	public String getCw_customerewbcode() {
		return this.getField(0);
	}

	public void setCw_ewbcode(String cw_ewbcode) {
		this.setField(1, cw_ewbcode);
	}

	public String getCw_ewbcode() {
		return this.getField(1);
	}

	public void setCw_serverewbcode(String cw_serverewbcode) {
		this.setField(2, cw_serverewbcode);
	}

	public String getCw_serverewbcode() {
		return this.getField(2);
	}

	public void setChn_code_supplier(String chn_code_supplier) {
		this.setField(3, chn_code_supplier);
	}

	public String getChn_code_supplier() {
		return this.getField(3);
	}

	public void setCo_code_supplier(String co_code_supplier) {
		this.setField(4, co_code_supplier);
	}

	public String getCo_code_supplier() {
		return this.getField(4);
	}

	public void setAbw_labelcode(String abw_labelcode) {
		this.setField(5, abw_labelcode);
	}

	public String getAbw_labelcode() {
		return this.getField(5);
	}

	public void setDbw_labelcode(String dbw_labelcode) {
		this.setField(6, dbw_labelcode);
	}

	public String getDbw_labelcode() {
		return this.getField(6);
	}

	public void setCws_code(String cws_code) {
		this.setField(7, cws_code);
	}

	public String getCws_code() {
		return this.getField(7);
	}

	public void setPm_code(String pm_code) {
		this.setField(8, pm_code);
	}

	public String getPm_code() {
		return this.getField(8);
	}

	public void setPk_code(String pk_code) {
		this.setField(9, pk_code);
	}

	public String getPk_code() {
		return this.getField(9);
	}

	public void setCt_code(String ct_code) {
		this.setField(10, ct_code);
	}

	public String getCt_code() {
		return this.getField(10);
	}

	public void setOdt_code(String odt_code) {
		this.setField(11, odt_code);
	}

	public String getOdt_code() {
		return this.getField(11);
	}

	public void setDdt_code(String ddt_code) {
		this.setField(12, ddt_code);
	}

	public String getDdt_code() {
		return this.getField(12);
	}

	public void setCddt_code(String cddt_code) {
		this.setField(13, cddt_code);
	}

	public String getCddt_code() {
		return this.getField(13);
	}

	public void setCo_code_customer(String co_code_customer) {
		this.setField(14, co_code_customer);
	}

	public String getCo_code_customer() {
		return this.getField(14);
	}

	public void setStartadddate(String StartAdddate) {
		this.setField(15, StartAdddate);
	}

	public String getStartadddate() {
		return this.getField(15);
	}

	public void setEndadddate(String EndAdddate) {
		this.setField(16, EndAdddate);
	}

	public String getEndadddate() {
		return this.getField(16);
	}

	public void setStartdepdate(String StartDepdate) {
		this.setField(17, StartDepdate);
	}

	public String getStartdepdate() {
		return this.getField(17);
	}

	public void setEnddepdate(String EndDepdate) {
		this.setField(18, EndDepdate);
	}

	public String getEnddepdate() {
		return this.getField(18);
	}

	public void setBegincarryoversign(String BeginCarryoversign) {
		this.setField(19, BeginCarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(19);
	}

	public void setEndcarryoversigin(String EndCarryoversigin) {
		this.setField(20, EndCarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(20);
	}

	public void setEecode(String eeCode) {
		this.setField(21, eeCode);
	}

	public String getEecode() {
		return this.getField(21);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(22, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(22);
	}

	public void setStartdeclarevalue(String StartDeclarevalue) {
		this.setField(23, StartDeclarevalue);
	}

	public String getStartdeclarevalue() {
		return this.getField(23);
	}

	public void setEnddeclarevalue(String EndDeclarevalue) {
		this.setField(24, EndDeclarevalue);
	}

	public String getEnddeclarevalue() {
		return this.getField(24);
	}

	public void setNotcalcfeesign(String notcalcfeesign) {
		this.setField(25, notcalcfeesign);
	}

	public String getNotcalcfeesign() {
		return this.getField(25);
	}

	public void setOp_id_sale(String op_id_sale) {
		this.setField(26, op_id_sale);
	}

	public String getOp_id_sale() {
		return this.getField(26);
	}

}
