package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class ChargeweightdifferenceQuery extends JGeneralQuery {
	
	public ChargeweightdifferenceQuery(){
	    m_strSelectClause = "SELECT hwb.HW_OWNINPUTCWAUDITSIGN,(select count(1) from t_bl_receivable rv where rv.cw_code = cw.cw_code and fk_code = 'A0101' and rv.fs_code='B') as status,(select nvl(sum(round(rv.rv_actualtotal*rv.RV_CURRENCYRATE, 2)),0) from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.fs_code != 'E') as rvtotal,(select nvl(sum(round(py.py_actualtotal*py.PY_CURRENCYRATE, 2)),0) from t_bl_payable py where py.cw_code = cw.cw_code and py.fs_code != 'E' and py.bk_code = 'A0201') as pytotal,swb.SWB_CODE,swb.CHN_Code,chn.chn_sname,swb.SWB_ServerEwbcode,swb.SWB_CustomerEwbcode,swb.SWB_Pieces,swb.SWB_Chargeweight,swb.SWB_TotalFreightCharge,swb.SWB_TotalSurcharge,swb.SWB_TotalIncidentalCharge,cw.cw_transferpieces,cw.cw_serverchargeweight,cw.cw_chargeweight,cw.cw_code,co.co_code,co.co_sname,co.co_labelcode,bw.add_date, pk.pk_code, pk.pk_name FROM T_FI_SERVERWAYBILL swb, T_OP_COREWAYBILL cw, T_OP_BATCHWAYBILL bw, T_CHN_CHANNEL chn, T_CO_CORPORATION co, T_DI_PRODUCTKIND pk,t_co_customer cus,t_op_housewaybill hwb";
	    m_strWhereClause = "hwb.cw_code=cw.cw_code and cw.CO_CODE_CUSTOMER=cus.co_code and swb.swb_referencecode = cw.cw_code and cw.bw_code_arrival = bw.bw_code and swb.chn_code = chn.chn_code and cw.co_code_customer = co.co_code and cw.pk_code = pk.pk_code and swb.SWB_Chargeweight != cw.cw_chargeweight";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "swb.SWB_CODE = ~~", "swb.SWB_ServerEwbcode in (~~)", "bw.add_date >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.add_date", "co.co_code = '~~'", "pk.pk_code = '~~'", "cus.ct_code = '~~'", "nvl(hwb.HW_OWNINPUTCWAUDITSIGN,'N')='~~'", "cw.cw_customerewbcode in (~~)" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new ChargeweightdifferenceColumns();
	}
	
	public void setSwbcode(String swbCode) {
		this.setField(0, swbCode);
	}

	public String getSwbcode() {
		return this.getField(0);
	}

	public void setSwbserverewbcode(String swbServerEwbcode) {
		this.setField(1, swbServerEwbcode);
	}

	public String getSwbserverewbcode() {
		return this.getField(1);
	}

	public void setStartadddate(String StartAddDate) {
		this.setField(2, StartAddDate);
	}

	public String getStartadddate() {
		return this.getField(2);
	}

	public void setEndadddate(String EndAddDate) {
		this.setField(3, EndAddDate);
	}

	public String getEndadddate() {
		return this.getField(3);
	}

	public void setCocode(String coCode) {
		this.setField(4, coCode);
	}

	public String getCocode() {
		return this.getField(4);
	}

	public void setPkcode(String pkCode) {
		this.setField(5, pkCode);
	}

	public String getPkcode() {
		return this.getField(5);
	}

	public void setCtcode(String ctCode) {
		this.setField(6, ctCode);
	}

	public String getCtcode() {
		return this.getField(6);
	}

	public void setHwowninputcwauditsign(String hwOwnInputcwauditSign) {
		this.setField(7, hwOwnInputcwauditSign);
	}

	public String getHwowninputcwauditsign() {
		return this.getField(7);
	}

	public void setCustomerewbcode(String customerewbcode) {
		this.setField(8, customerewbcode);
	}

	public String getCustomerewbcode() {
		return this.getField(8);
	}

}
