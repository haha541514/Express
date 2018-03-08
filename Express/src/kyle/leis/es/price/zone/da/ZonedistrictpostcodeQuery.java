package kyle.leis.es.price.zone.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ZonedistrictpostcodeQuery extends HGeneralQuery {
	
	public ZonedistrictpostcodeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.zone.da.ZonedistrictpostcodeColumns(zndp.zdpStartpostcode,zndp.zdpEndpostcode,znvd.comp_id.znvId,znvd.comp_id.dtCode,znv.comp_id.znvId,znv.znvName,znv.znvEname) FROM TepZonedistrictpostcode as zndp inner join zndp.tepZonevaluedistrict as znvd inner join znvd.tepZonevalue as znv inner join znv.tepZone as zn";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "znvd.comp_id.znvId = ~~", "znvd.comp_id.dtCode = '~~'", "zn.znCode = ~~", "'~~' > = zndp.zdpStartpostcode", "zndp.zdpEndpostcode >= '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
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

	public void setDtcode(String dtCode) {
		this.setField(1, dtCode);
	}

	public String getDtcode() {
		return this.getField(1);
	}

	public void setZncode(String znCode) {
		this.setField(2, znCode);
	}

	public String getZncode() {
		return this.getField(2);
	}

	public void setValidpostcode2(String ValidPostcode2) {
		this.setField(3, ValidPostcode2);
	}

	public String getValidpostcode2() {
		return this.getField(3);
	}

	public void setValidpostcode1(String ValidPostcode1) {
		this.setField(4, ValidPostcode1);
	}

	public String getValidpostcode1() {
		return this.getField(4);
	}

}
