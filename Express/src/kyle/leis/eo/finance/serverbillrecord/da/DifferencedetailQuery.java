package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class DifferencedetailQuery extends JGeneralQuery {
	
	public DifferencedetailQuery(){
	    m_strSelectClause = "SELECT swb.SWB_CODE,swb.CHN_Code,chn.chn_sname,swb.SWB_ServerEwbcode,swb.SWB_CustomerEwbcode,swb.SWB_Pieces,swb.SWB_Chargeweight,swb.SWB_TotalFreightCharge,swb.SWB_TotalSurcharge,swb.SWB_TotalIncidentalCharge,cw.cw_transferpieces,cw.cw_serverchargeweight,cw.cw_chargeweight,fun_get_serverfreightcharge(cw.cw_code) as freightcharge,fun_get_serveroilcharge(cw.cw_code) as oilcharge,fun_get_serverothercharge(cw.cw_code) as othercharge,cw.cw_code,cw.cw_transferchargeweight FROM T_FI_SERVERWAYBILL swb,T_OP_COREWAYBILL cw,T_CHN_CHANNEL chn";
	    m_strWhereClause = "swb.swb_referencecode = cw.cw_code(+) and swb.chn_code = chn.chn_code and swb.SWB_Chargeweight != nvl(cw.cw_serverchargeweight,0)";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "swb.SWB_CODE = ~~", "swb.SWB_ServerEwbcode = '~~'", "exists (select * from t_fi_serverpayable spy where spy.swb_code = swb.swb_code and spy.sbr_id = ~~)" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new DifferencedetailColumns();
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

	public void setSbrid(String sbrId) {
		this.setField(2, sbrId);
	}

	public String getSbrid() {
		return this.getField(2);
	}

}
