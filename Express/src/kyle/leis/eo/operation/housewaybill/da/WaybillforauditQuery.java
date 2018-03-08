package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class WaybillforauditQuery extends JGeneralQuery {
	
	public WaybillforauditQuery(){
	    m_strSelectClause = "SELECT cw.cw_code,cw.cw_customerewbcode,cw.cw_ewbcode,cw.cw_serverewbcode,co.co_code,co.co_labelcode,co.co_sname,cw.cw_grossweight,cw.cw_pieces,  cw.cw_chargeweight,cw.cw_serverchargeweight,pk.pk_code,pk.pk_sname,pm.pm_code,pm.pm_name,ct.ct_code,ct.ct_name,odt.dt_code,odt.dt_hubcode,sdt.dt_code,  sdt.dt_hubcode,ddt.dt_code,ddt.dt_hubcode,cddt.dt_code,cddt.dt_hubcode,bw.bw_labelcode,bw.add_date,dbw.bw_labelcode,dbw.add_date,cws.cws_code,cws.cws_name,  (select nvl(sum(round(rv.rv_currencyrate * rv.rv_actualtotal, 2)),0) from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.bk_code = 'A0101' and rv.fs_code != 'E') as rvtotal, (select nvl(sum(round(py.py_currencyrate * py.py_actualtotal, 2)), 0) from t_bl_payable py where py.cw_code = cw.cw_code and py.bk_code = 'A0201' and py.fs_code != 'E') as pytotal,fus.fas_code,fus.fas_name,fun_get_reconciliation(cw.cw_code) as reconciliationsign,cw.znv_Name,(select nvl(sum(round(py.py_currencyrate * py.py_actualtotal, 2)), 0) from t_bl_payable py where py.cw_code = cw.cw_code and py.bk_code = 'A0202' and py.fs_code != 'E') as pyrvtotal,sop.op_id,sop.op_name,ee.ee_code,ee.ee_sname,cct.CT_CODE,cct.CT_NAME FROM t_op_corewaybill cw,t_op_batchwaybill bw,t_op_batchwaybill dbw,t_co_corporation co,t_co_customer cm,t_di_productkind pk,t_di_district odt,t_di_district ddt,t_di_district cddt,t_di_district sdt,t_di_paymentmode pm,t_di_cargotype ct,t_di_corewaybillstatus cws,t_di_financeauditstatus fus,t_di_operator sop, t_di_enterpriseelement ee,T_DI_CUSTOMERTYPE cct";
	    m_strWhereClause = "cw.bw_code_arrival = bw.bw_code and cw.ee_code = ee.ee_code and cw.bw_code_departure = dbw.bw_code(+) and cw.co_code_customer = co.co_code and co.co_code = cm.co_code and cm.cm_op_id_sale = sop.op_id and cw.pk_code = pk.pk_code and cw.dt_code_origin = odt.dt_code and cw.dt_code_destination = ddt.dt_code(+) and ddt.dt_countcode = cddt.dt_code(+) and cw.dt_code_signin = sdt.dt_code(+) and cw.pm_code = pm.pm_code and cw.ct_code = ct.ct_code and cws.cws_code = cw.cws_code and cw.fas_code = fus.fas_code and cw.cws_code NOT IN ('EL', 'CEL', 'CTS', 'CHP') and cm.ct_code = cct.ct_code"; 
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cw_customerewbcode in (~~)", "cw.cw_ewbcode in (~~)", "cw.cw_serverewbcode in (~~)", "bw.bw_labelcode = '~~'", "dbw.bw_labelcode = '~~'", "cws.cws_code in (~~)", "pm.pm_code = '~~'", "pk.pk_code = '~~'", "ct.ct_code = '~~'", "odt.dt_code = '~~'", "ddt.dt_code = '~~'", "cddt.dt_code = '~~'", "fus.fas_code = '~~'", "cw.co_code_customer in (~~)", "bw.add_date >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.add_date", "dbw.add_date >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= dbw.add_date", "(co.CO_CarryoverSign = '~~' OR co.CO_CarryoverDate > cw.cw_createdate)", "(co.CO_CarryoverSign = '~~' AND cw.cw_createdate > co.CO_CarryoverDate)", "exists (select cw.cw_code,rv.fk_code from t_bl_receivable rv where cw.cw_code = rv.cw_code and rv.fk_code in(~~))", "fun_get_grossprofit(cw.cw_code) = '~~'", "('Y' = '~~' AND (cw.cw_chargeweight - cw.cw_serverchargeweight) != 0)", "fun_get_reconciliation(cw.cw_code) = '~~'", "fun_get_reconciliation(cw.cw_code) = '~~'", "cw.chn_code_supplier = '~~'", "cw.znv_Name = '~~'", "~~ >= cw.cw_chargeweight", "cw.cw_chargeweight >= ~~", "exists (select rv.rv_id,cw.cw_code from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.fs_code in (~~))", "exists (select py.py_id,cw.cw_code from t_bl_payable py where py.cw_code = cw.cw_code and py.bk_code = 'A0201' and py.fs_code in ('B','W') and py.br_id = ~~) ", "cw.co_code_supplier = '~~'", "exists (select cal.cw_code from t_fs_corewaybillauditlog cal where cal.cw_code = cw.cw_code and cal.falt_code = '~~')", "sop.op_id = ~~", "exists (select rv.rv_id from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.rv_createdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss') and to_date('~~','yyyy-mm-dd hh24:mi:ss') >= rv.rv_createdate)", "ee.ee_structurecode like '~~%'", "(exists (select rv.rv_id from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.bk_code = 'A0101' and rv.fs_code != 'E' and rv.fk_code in (~~)) and not exists (select py.py_id from t_bl_payable py where py.cw_code = cw.cw_code and py.bk_code = 'A0201' and py.fs_code != 'E' and py.fk_code in (~~)))", "(not exists (select rv.rv_id from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.bk_code = 'A0101' and rv.fs_code != 'E' and rv.fk_code in (~~)) and exists (select py.py_id from t_bl_payable py where py.cw_code = cw.cw_code and py.bk_code = 'A0201' and py.fs_code != 'E' and py.fk_code in (~~)))", "cct.ct_Code= '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new WaybillforauditColumns();
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

	public void setAbw_labelcode(String abw_labelcode) {
		this.setField(3, abw_labelcode);
	}

	public String getAbw_labelcode() {
		return this.getField(3);
	}

	public void setDbw_labelcode(String dbw_labelcode) {
		this.setField(4, dbw_labelcode);
	}

	public String getDbw_labelcode() {
		return this.getField(4);
	}

	public void setCws_code(String cws_code) {
		this.setField(5, cws_code);
	}

	public String getCws_code() {
		return this.getField(5);
	}

	public void setPm_code(String pm_code) {
		this.setField(6, pm_code);
	}

	public String getPm_code() {
		return this.getField(6);
	}

	public void setPk_code(String pk_code) {
		this.setField(7, pk_code);
	}

	public String getPk_code() {
		return this.getField(7);
	}

	public void setCt_code(String ct_code) {
		this.setField(8, ct_code);
	}

	public String getCt_code() {
		return this.getField(8);
	}

	public void setOdt_code(String odt_code) {
		this.setField(9, odt_code);
	}

	public String getOdt_code() {
		return this.getField(9);
	}

	public void setDdt_code(String ddt_code) {
		this.setField(10, ddt_code);
	}

	public String getDdt_code() {
		return this.getField(10);
	}

	public void setCddt_code(String cddt_code) {
		this.setField(11, cddt_code);
	}

	public String getCddt_code() {
		return this.getField(11);
	}

	public void setFas_code(String fas_code) {
		this.setField(12, fas_code);
	}

	public String getFas_code() {
		return this.getField(12);
	}

	public void setCo_code_customer(String co_code_customer) {
		this.setField(13, co_code_customer);
	}

	public String getCo_code_customer() {
		return this.getField(13);
	}

	public void setStartadddate(String StartAdddate) {
		this.setField(14, StartAdddate);
	}

	public String getStartadddate() {
		return this.getField(14);
	}

	public void setEndadddate(String EndAdddate) {
		this.setField(15, EndAdddate);
	}

	public String getEndadddate() {
		return this.getField(15);
	}

	public void setStartdepdate(String StartDepdate) {
		this.setField(16, StartDepdate);
	}

	public String getStartdepdate() {
		return this.getField(16);
	}

	public void setEnddepdate(String EndDepdate) {
		this.setField(17, EndDepdate);
	}

	public String getEnddepdate() {
		return this.getField(17);
	}

	public void setBegincarryoversign(String BeginCarryoversign) {
		this.setField(18, BeginCarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(18);
	}

	public void setEndcarryoversigin(String EndCarryoversigin) {
		this.setField(19, EndCarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(19);
	}

	public void setFkcode(String fkCode) {
		this.setField(20, fkCode);
	}

	public String getFkcode() {
		return this.getField(20);
	}

	public void setGross_profit(String gross_profit) {
		this.setField(21, gross_profit);
	}

	public String getGross_profit() {
		return this.getField(21);
	}

	public void setWeight_variation(String weight_variation) {
		this.setField(22, weight_variation);
	}

	public String getWeight_variation() {
		return this.getField(22);
	}

	public void setReconciliation_pass(String reconciliation_pass) {
		this.setField(23, reconciliation_pass);
	}

	public String getReconciliation_pass() {
		return this.getField(23);
	}

	public void setReconciliation_not_pass(String reconciliation_not_pass) {
		this.setField(24, reconciliation_not_pass);
	}

	public String getReconciliation_not_pass() {
		return this.getField(24);
	}

	public void setChn_code_supplier(String chn_code_supplier) {
		this.setField(25, chn_code_supplier);
	}

	public String getChn_code_supplier() {
		return this.getField(25);
	}

	public void setZnvname(String znvName) {
		this.setField(26, znvName);
	}

	public String getZnvname() {
		return this.getField(26);
	}

	public void setMaxweight(String MaxWeight) {
		this.setField(27, MaxWeight);
	}

	public String getMaxweight() {
		return this.getField(27);
	}

	public void setMinweight(String MinWeight) {
		this.setField(28, MinWeight);
	}

	public String getMinweight() {
		return this.getField(28);
	}

	public void setFscode(String fsCode) {
		this.setField(29, fsCode);
	}

	public String getFscode() {
		return this.getField(29);
	}

	public void setServerbrid(String serverbrid) {
		this.setField(30, serverbrid);
	}

	public String getServerbrid() {
		return this.getField(30);
	}

	public void setCo_code_supplier(String co_code_supplier) {
		this.setField(31, co_code_supplier);
	}

	public String getCo_code_supplier() {
		return this.getField(31);
	}

	public void setFalt_code(String falt_code) {
		this.setField(32, falt_code);
	}

	public String getFalt_code() {
		return this.getField(32);
	}

	public void setOp_id_sale(String op_id_sale) {
		this.setField(33, op_id_sale);
	}

	public String getOp_id_sale() {
		return this.getField(33);
	}

	public void setStartfeecreatedate(String StartFeeCreatedate) {
		this.setField(34, StartFeeCreatedate);
	}

	public String getStartfeecreatedate() {
		return this.getField(34);
	}
	public void setStartfeeenddate(String StartFeeEnddate) {
		this.setField(35, StartFeeEnddate);
	}

	public String getStartfeeenddate() {
		return this.getField(35);
	}

	public void setEestructurecode(String eestructurecode) {
		this.setField(36, eestructurecode);
	}

	public String getEestructurecode() {
		return this.getField(36);
	}

	public void setExistsrv(String existsRv) {
		this.setField(37, existsRv);
	}

	public String getExistsrv() {
		return this.getField(37);
	}
	public void setExistspy(String existsPy) {
		this.setField(38, existsPy);
	}

	public String getExistspy() {
		return this.getField(38);
	}

	public void setNotexistsrv(String notexistsRv) {
		this.setField(39, notexistsRv);
	}

	public String getNotexistsrv() {
		return this.getField(39);
	}
	public void setNotexistspy(String notexistsPy) {
		this.setField(40, notexistsPy);
	}

	public String getNotexistspy() {
		return this.getField(40);
	}

	public void setCctcode(String cctCode) {
		this.setField(41, cctCode);
	}

	public String getCctcode() {
		return this.getField(41);
	}

}
