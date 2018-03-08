package kyle.leis.es.price.freightprice.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class SurchargevalueQuery extends HGeneralQuery {
	
	public SurchargevalueQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.freightprice.da.SurchargevalueColumns(sv.comp_id.epCode,sv.comp_id.svId,sv.znvId,sv.svReversesign,sv.svBasevalue,sv.svMinimumvalue,sv.svPricevalue,fk.fkCode,fk.fkName,ut.utCode,ut.utName, sv.svMaximumvalue) FROM TepSurchargevalue as sv inner join sv.tdiFeekind as fk inner join sv.tdiUnittype as ut";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "sv.comp_id.epCode = ~~", "sv.comp_id.svId = ~~", "fk.fkCode = '~~'" };
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

	public void setSvid(String svId) {
		this.setField(1, svId);
	}

	public String getSvid() {
		return this.getField(1);
	}

	public void setFkcode(String fkCode) {
		this.setField(2, fkCode);
	}

	public String getFkcode() {
		return this.getField(2);
	}

}
