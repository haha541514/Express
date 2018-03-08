package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class PredictwaybillQuery extends HGeneralQuery {
	
	public PredictwaybillQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.housewaybill.da.PredictwaybillColumns(cw.cwCustomerewbcode,cw.cwGrossweight,cw.cwPieces,cw.cwCreatedate,cwpk.pkCode,cwpk.pkName,cwcocus.coCode,cwdtocode.dtCode,cwdtocode.dtName,cwhw.hwConsigneename,cwhw.hwConsigneepostcode,cwhw.hwConsigneetelephone,cwhw.hwConsigneeaddress1,cwhw.hwConsigneeaddress2,cwhw.hwConsigneeaddress3,cwhw.hwRemark,cw.cwTransferchargeweight, cw.cwPostcodeDestination, cw.cwChargeweight) FROM TopCorewaybill as cw inner join cw.tdiProductkind as cwpk inner join cw.tcoCorporationByCoCodeCustomer as cwcocus inner join cw.tdiDistrictByDtCodeOrigin as cwdtocode inner join cw.topHousewaybill as cwhw";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cwCustomerewbcode = '~~'", "cwpk.pkCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCwcustomerewbcode(String cwCustomerewbcode) {
		this.setField(0, cwCustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(0);
	}

	public void setPkcode(String pkCode) {
		this.setField(1, pkCode);
	}

	public String getPkcode() {
		return this.getField(1);
	}

}
