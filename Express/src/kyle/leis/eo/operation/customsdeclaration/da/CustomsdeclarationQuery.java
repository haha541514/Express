package kyle.leis.eo.operation.customsdeclaration.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CustomsdeclarationQuery extends HGeneralQuery {
	
	public CustomsdeclarationQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.customsdeclaration.da.CustomsdeclarationColumns(cd.cdId,cd.cdLabelcode,cd.cdEname,cd.cdName,cd.cdGrossweight,cd.cdPieces,cd.cdUnitprice,cd.cdTotalprice,hw.cwCode,hw.hwCustomslabelprintsign,cd.cdAmount,cd.cdUnitname,cd.cdGoodslabelcode,cw.cwCustomerewbcode,cw.cwServerewbcode,cd.cdShipperaddress,cd.cdConsigneeaddress) FROM TopCustomsdeclaration as cd inner join cd.topHousewaybill as hw inner join hw.topCorewaybill as cw";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "hw.cwCode = ~~", "cd.cdLabelcode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setCdlabelcode(String cdLabelcode) {
		this.setField(1, cdLabelcode);
	}

	public String getCdlabelcode() {
		return this.getField(1);
	}

}
