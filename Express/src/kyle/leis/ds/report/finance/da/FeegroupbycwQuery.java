package kyle.leis.ds.report.finance.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class FeegroupbycwQuery extends JGeneralQuery {
	
	public FeegroupbycwQuery(){
	    m_strSelectClause = "SELECT cw.co_code_supplier,cw.chn_code_supplier,count(1) as billcount,nvl(sum(cw.cw_pieces),0) as sumpieces,nvl(sum(cw.cw_chargeweight),0) as sumchargeweight,nvl(sum(cw.cw_serverchargeweight),0) as sumserverchargeweight,nvl(sum((select sum(round(rv.rv_currencyrate * rv.rv_actualtotal,2)) from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.fs_code in ('D','C','B','W'))),0) as rvTotal, nvl(sum((select sum(round(py.py_currencyrate * py.py_actualtotal,2)) from t_bl_payable py where py.cw_code = cw.cw_code and py.bk_code = 'A0201' and py.fs_code in ('D','C','B','W'))),0) as pyTotal,chn.chn_sname,co.co_sname,co.co_labelcode,nvl(sum((select sum(py.py_actualtotal) from t_bl_payable py where py.cw_code = cw.cw_code and py.bk_code = 'A0201' and py.ck_code = 'RMB' and py.fs_code in ('D','C','B','W'))),0) as RMBPyTotal,nvl(sum((select sum(py.py_actualtotal) from t_bl_payable py where py.cw_code = cw.cw_code and py.bk_code = 'A0201' and py.ck_code = 'HKD' and py.fs_code in ('D','C','B','W'))),0) as HKDPyTotal,ee.ee_sname,nvl(sum((select sum(py.py_actualtotal) from t_bl_payable py where py.cw_code = cw.cw_code and py.bk_code = 'A0201' and py.ck_code = 'USD' and py.fs_code in ('D','C','B','W'))),0) as USDPyTotal,nvl(sum((select sum(py.py_actualtotal) from t_bl_payable py where py.cw_code = cw.cw_code and py.bk_code = 'A0201' and py.ck_code = 'EUR' and py.fs_code in ('D','C','B','W'))),0) as EURPyTotal FROM t_op_corewaybill cw,t_op_batchwaybill bw,t_co_corporation co,t_di_productkind pk, t_co_customer cm,t_co_supplier cs,t_chn_channel chn,T_DI_ENTERPRISEELEMENT ee";
	    m_strWhereClause = "cw.co_code_supplier = co.co_code and cw.co_code_customer = cm.co_code and cw.bw_code_arrival = bw.bw_code and cw.pk_code = pk.pk_code and cw.ee_code = ee.ee_code and co.co_code = cs.co_code and cw.chn_code_supplier = chn.chn_code and cw.cws_code NOT IN ('EL', 'CEL', 'CTS', 'CHP')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "cw.co_code_supplier,cw.chn_code_supplier,co.co_sname,chn.chn_sname,co.co_labelcode,ee.ee_sname";
	    m_astrConditionWords = new String[] { "bw.add_date >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.add_date", "cw.pm_code = '~~'", "cw.ct_code = '~~'", "co.co_code = ~~", "cs.co_code = ~~", "pk.pk_code = '~~'", "chn.chn_code = '~~'", "cm.cm_op_id_sale = ~~", "(co.co_Carryoversign='~~' OR co.co_Carryoverdate >= cw.cw_createdate)", "(co.co_Carryoversign='~~' AND cw.cw_createdate >= co.co_Carryoverdate)", "exists (select rv.rv_id from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.rv_createdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss') and to_date('~~','yyyy-mm-dd hh24:mi:ss') >= rv.rv_createdate)", "ee.EE_Structurecode like '~~%'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new FeegroupbycwColumns();
	}
	
	public void setStartadddate(String startadddate) {
		this.setField(0, startadddate);
	}

	public String getStartadddate() {
		return this.getField(0);
	}

	public void setEndadddate(String Endadddate) {
		this.setField(1, Endadddate);
	}

	public String getEndadddate() {
		return this.getField(1);
	}

	public void setPmcode(String pmcode) {
		this.setField(2, pmcode);
	}

	public String getPmcode() {
		return this.getField(2);
	}

	public void setCtcode(String ctcode) {
		this.setField(3, ctcode);
	}

	public String getCtcode() {
		return this.getField(3);
	}

	public void setCocode(String cocode) {
		this.setField(4, cocode);
	}

	public String getCocode() {
		return this.getField(4);
	}

	public void setSpcode(String spcode) {
		this.setField(5, spcode);
	}

	public String getSpcode() {
		return this.getField(5);
	}

	public void setPkcode(String pkcode) {
		this.setField(6, pkcode);
	}

	public String getPkcode() {
		return this.getField(6);
	}

	public void setChncode(String chncode) {
		this.setField(7, chncode);
	}

	public String getChncode() {
		return this.getField(7);
	}

	public void setCmopidsale(String cmOpidsale) {
		this.setField(8, cmOpidsale);
	}

	public String getCmopidsale() {
		return this.getField(8);
	}

	public void setBegincarryoversign(String BeginCarryoversign) {
		this.setField(9, BeginCarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(9);
	}

	public void setEndcarryoversigin(String EndCarryoversigin) {
		this.setField(10, EndCarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(10);
	}

	public void setStartfeecreatedate(String StartFeeCreatedate) {
		this.setField(11, StartFeeCreatedate);
	}

	public String getStartfeecreatedate() {
		return this.getField(11);
	}
	public void setStartfeeenddate(String StartFeeEnddate) {
		this.setField(12, StartFeeEnddate);
	}

	public String getStartfeeenddate() {
		return this.getField(12);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(13, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(13);
	}

}
