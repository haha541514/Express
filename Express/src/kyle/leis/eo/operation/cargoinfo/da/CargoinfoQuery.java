package kyle.leis.eo.operation.cargoinfo.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CargoinfoQuery extends HGeneralQuery {
	
	public CargoinfoQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns(ci.comp_id.ciId, ci.comp_id.cwCode, ci.ciName, ci.ciEname, ci.ciPieces, ci.ciUnitprice, ci.ciTotalprice, ci.ciHscode, ck.ckCode, ci.ciAttacheinfo, ci.ciRemark, ci.ciWeight) FROM TopHwbcargoinfo as ci inner join ci.tdiCurrencykind as ck";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "ci.comp_id.cwCode = '~~'", "lower(ci.ciEname) like lower('~~')", "lower(ci.ciAttacheinfo) like lower('~~')" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
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

	public void setCiciename(String ciciEName) {
		this.setField(1, ciciEName);
	}

	public String getCiciename() {
		return this.getField(1);
	}

	public void setCiciattacheinfo(String ciciAttacheinfo) {
		this.setField(2, ciciAttacheinfo);
	}

	public String getCiciattacheinfo() {
		return this.getField(2);
	}

}
