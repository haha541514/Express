package kyle.leis.es.company.predicttemplate.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class PredictdicmappingQuery extends HGeneralQuery {
	
	public PredictdicmappingQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.company.predicttemplate.da.PredictdicmappingColumns(podm.podmId,podm.podmOriginvalue,podm.podmStandardvalue,dmk.dmkCode,dmk.dmkName,pot.potId,pot.potName) FROM TcoPreictorderdicmapping as podm inner join podm.tdiDictionarymappingkind as dmk left join podm.tcoPredictordertemplate as pot";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "pot.potId = ~~", "dmk.dmkCode = '~~'", "pot.potId is ~~", "lower(podm.podmOriginvalue) = lower('~~')" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setPotid(String potId) {
		this.setField(0, potId);
	}

	public String getPotid() {
		return this.getField(0);
	}

	public void setDmkcode(String dmkcode) {
		this.setField(1, dmkcode);
	}

	public String getDmkcode() {
		return this.getField(1);
	}

	public void setIspotid(String IspotId) {
		this.setField(2, IspotId);
	}

	public String getIspotid() {
		return this.getField(2);
	}

	public void setPodmoriginvalue(String podmOriginvalue) {
		this.setField(3, podmOriginvalue);
	}

	public String getPodmoriginvalue() {
		return this.getField(3);
	}

}
