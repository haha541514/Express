package kyle.leis.es.businessrule.productrule.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class DistrictproductruleQuery extends HGeneralQuery {
	
	public DistrictproductruleQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.productrule.da.DistrictproductruleColumns(dpr.comp_id.brId,dpr.comp_id.dtCode,dt.dtCode,dt.dtHubcode,dt.dtName) FROM TbrDistrictproductrule as dpr inner join dpr.tdiDistrict as dt inner join dpr.tbrProductrule as pr";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "dpr.comp_id.brId = ~~", "dpr.comp_id.dtCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setDtcode(String dtCode) {
		this.setField(1, dtCode);
	}

	public String getDtcode() {
		return this.getField(1);
	}

}
