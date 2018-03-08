package kyle.leis.eo.finance.serverbillrecord.da;


import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class DifferenceincidentalsQuery extends JGeneralQuery {
	
	public DifferenceincidentalsQuery(){
	    m_strSelectClause = "SELECT cw.cw_code,spa.fk_code,co.co_name,pk.pk_name,cw.cw_chargeweight,swb.swb_serverewbcode,SWB_CustomerEwbcode,fk.fk_name,spa.ck_code,cw.cw_grossweight,spa.SPY_TOTALCHARGE,pk.pk_code FROM T_FI_SERVERWAYBILL swb,t_fi_serverpayable spa, T_OP_COREWAYBILL cw,T_CO_CORPORATION co, T_DI_PRODUCTKIND pk,t_di_feekind fk";
	    m_strWhereClause = "swb.swb_referencecode = cw.cw_code and cw.co_code_customer = co.co_code and cw.pk_code =pk.pk_code and swb.swb_code=spa.swb_code and spa.fk_code=fk.fk_code and not exists (select rv.fk_code from t_bl_receivable rv where  rv.cw_code = cw.cw_code and rv.fk_code=fk.fk_code)";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "swb.swb_serverewbcode='~~'", "swb.SWB_CustomerEwbcode='~~'", "spa.fk_code='~~'", "spa.SBR_ID='~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new DifferenceincidentalsColumns();
	}
	
	public void setSwbserverewbcode(String swbServerewbcode) {
		this.setField(0, swbServerewbcode);
	}

	public String getSwbserverewbcode() {
		return this.getField(0);
	}

	public void setSwbcustomerewbcode(String swbCustomerEwbcode) {
		this.setField(1, swbCustomerEwbcode);
	}

	public String getSwbcustomerewbcode() {
		return this.getField(1);
	}

	public void setFkcode(String fkCode) {
		this.setField(2, fkCode);
	}

	public String getFkcode() {
		return this.getField(2);
	}

	public void setSbrid(String sbrId) {
		this.setField(3, sbrId);
	}

	public String getSbrid() {
		return this.getField(3);
	}

}
