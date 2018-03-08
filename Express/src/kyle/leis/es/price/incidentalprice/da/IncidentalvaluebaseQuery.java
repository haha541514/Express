package kyle.leis.es.price.incidentalprice.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class IncidentalvaluebaseQuery extends HGeneralQuery {
	
	public IncidentalvaluebaseQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.incidentalprice.da.IncidentalvaluebaseColumns(ivb.comp_id.epCode,ivb.comp_id.ipvId,ivb.comp_id.fkCode,fk.fkCode,fk.fkName) FROM TepIncidentalvaluebase as ivb inner join ivb.tdiFeekind as fk";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "ivb.comp_id.epCode = ~~", "ivb.comp_id.ipvId = ~~", "ivb.comp_id.fkCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setEpcode(String epCode) {
		this.setField(0, epCode);
	}

	public String getEpcode() {
		return this.getField(0);
	}

	public void setIpvid(String ipvId) {
		this.setField(1, ipvId);
	}

	public String getIpvid() {
		return this.getField(1);
	}

	public void setFkcode(String fkCode) {
		this.setField(2, fkCode);
	}

	public String getFkcode() {
		return this.getField(2);
	}

}
