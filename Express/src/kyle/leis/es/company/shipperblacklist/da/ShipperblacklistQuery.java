package kyle.leis.es.company.shipperblacklist.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ShipperblacklistQuery extends HGeneralQuery {
	
	public ShipperblacklistQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.company.shipperblacklist.da.ShipperblacklistColumns(sbl.sblCode, sbl.sblCompanyname, sbl.sblCreatedate, sbl.sblModifydate, opc.opId, opc.opCode, opc.opName, opc.opEname, opm.opId, opm.opCode, opm.opName, opm.opEname, ssg.ssgCode, ssg.ssgName, ssg.ssgEname) FROM TcoShipperblacklist as sbl inner join sbl.tdiOperatorByOpIdCreator as opc inner join sbl.tdiOperatorByOpIdModifier as opm inner join sbl.tdiServerstructuregroup as ssg";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "sbl.sblCode = ~~", "sbl.sblCompanyname like '%~~%'", "FUN_COMMON_ALLTRIM(sbl.sblCompanyname) = FUN_COMMON_ALLTRIM('~~')", "'~~' like ssg.ssgCode || '%'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setSblcode(String sblCode) {
		this.setField(0, sblCode);
	}

	public String getSblcode() {
		return this.getField(0);
	}

	public void setSbllikecompanyname(String sblLikeCompanyname) {
		this.setField(1, sblLikeCompanyname);
	}

	public String getSbllikecompanyname() {
		return this.getField(1);
	}

	public void setSblcompanyname(String sblCompanyname) {
		this.setField(2, sblCompanyname);
	}

	public String getSblcompanyname() {
		return this.getField(2);
	}

	public void setSsgcode(String ssgCode) {
		this.setField(3, ssgCode);
	}

	public String getSsgcode() {
		return this.getField(3);
	}

}
