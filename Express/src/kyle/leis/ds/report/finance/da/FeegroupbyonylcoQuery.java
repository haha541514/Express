package kyle.leis.ds.report.finance.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class FeegroupbyonylcoQuery extends JGeneralQuery {
	
	public FeegroupbyonylcoQuery(){
	    m_strSelectClause = "SELECT co.co_sname,count(1) as billcount,nvl(sum(cw.cw_pieces),0) as sumpieces,nvl(sum(cw.cw_chargeweight),0) as sumchargeweight,nvl(sum(cw.cw_serverchargeweight),0) as sumserverchargeweight,nvl(sum((select sum(round(rv.rv_currencyrate * rv.rv_actualtotal,2)) from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.bk_code = 'A0101' and rv.fs_code in ('D','C','B','W'))),0) as rvTotal, nvl(sum((select sum(round(py.py_currencyrate * py.py_actualtotal,2)) from t_bl_payable py where py.cw_code = cw.cw_code and py.bk_code = 'A0201' and py.fs_code in ('D','C','B','W'))),0) as pyTotal,op.op_name,co.co_labelcode,nvl(sum((select sum(rv.rv_actualtotal) from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.bk_code = 'A0101' and rv.ck_code = 'RMB' and rv.fs_code in ('D','C','B','W'))),0) as RMBRvTotal,nvl(sum((select sum(rv.rv_actualtotal) from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.ck_code = 'HKD' and rv.bk_code = 'A0101' and rv.fs_code in ('D','C','B','W'))),0) as HKDRvTotal,ee.ee_sname FROM t_op_corewaybill cw,t_op_batchwaybill bw,t_co_corporation co,t_di_productkind pk, t_co_customer cm,t_di_operator op,t_co_supplier cs,t_chn_channel chn,T_DI_ENTERPRISEELEMENT ee";
	    m_strWhereClause = "cw.co_code_customer = co.co_code and cw.co_code_supplier = cs.co_code and cw.bw_code_arrival = bw.bw_code and cw.pk_code = pk.pk_code and cw.ee_code = ee.ee_code and co.co_code = cm.co_code and cm.cm_op_id_sale = op.op_id and cw.chn_code_supplier = chn.chn_code and cw.cws_code NOT IN ('EL', 'CEL', 'CTS', 'CHP')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "co.co_sname,op.op_name,co.co_labelcode,ee.ee_sname";
	    m_astrConditionWords = new String[] { "bw.add_date >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.add_date", "cw.pm_code = '~~'", "cw.ct_code = '~~'", "co.co_code = ~~", "cs.co_code = ~~", "pk.pk_code = '~~'", "chn.chn_code = '~~'", "cm.cm_op_id_sale = ~~", "(co.co_Carryoversign='~~' OR co.co_Carryoverdate >= cw.cw_createdate)", "(co.co_Carryoversign='~~' AND cw.cw_createdate >= co.co_Carryoverdate)", "exists (select rv.rv_id from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.rv_createdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss') and to_date('~~','yyyy-mm-dd hh24:mi:ss') >= rv.rv_createdate)", "ee.EE_Structurecode like '~~%'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new FeegroupbyonylcoColumns();
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
