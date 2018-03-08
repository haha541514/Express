package kyle.leis.es.price.zone.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ZonevaluedistrictQuery extends HGeneralQuery {
	
	public ZonevaluedistrictQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.zone.da.ZonevaluedistrictColumns(znv.comp_id.znvId,znv.znvName,znv.znvEname,dt.dtCode,dt.dtHubcode,dt.dtName,dt.dtEname) FROM TepZonevaluedistrict as znvd inner join znvd.tepZonevalue as znv inner join znvd.tdiDistrict as dt inner join znv.tepZone as zn";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "znv.comp_id.znvId = ~~", "zn.znCode = ~~", "dt.dtCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setZnvid(String znvId) {
		this.setField(0, znvId);
	}

	public String getZnvid() {
		return this.getField(0);
	}

	public void setZncode(String znCode) {
		this.setField(1, znCode);
	}

	public String getZncode() {
		return this.getField(1);
	}

	public void setDtcode(String dtCode) {
		this.setField(2, dtCode);
	}

	public String getDtcode() {
		return this.getField(2);
	}

}
