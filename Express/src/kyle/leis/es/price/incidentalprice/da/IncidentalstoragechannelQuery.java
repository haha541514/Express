package kyle.leis.es.price.incidentalprice.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class IncidentalstoragechannelQuery extends HGeneralQuery {
	
	public IncidentalstoragechannelQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.incidentalprice.da.IncidentalstoragechannelColumns(isc.comp_id.epCode,isc.comp_id.ipvId,isc.comp_id.chnCode,chn.chnCode,chn.chnSname,chn.chnSename) FROM TepIncidentalstorgechannel as isc inner join isc.tchnChannel as chn";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "isc.comp_id.epCode = ~~", "isc.comp_id.ipvId = ~~", "isc.comp_id.chnCode = '~~'" };
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

	public void setChncode(String chnCode) {
		this.setField(2, chnCode);
	}

	public String getChncode() {
		return this.getField(2);
	}

}
