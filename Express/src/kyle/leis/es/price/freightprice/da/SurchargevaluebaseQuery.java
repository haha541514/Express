package kyle.leis.es.price.freightprice.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class SurchargevaluebaseQuery extends HGeneralQuery {
	
	public SurchargevaluebaseQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.freightprice.da.SurchargevaluebaseColumns(svb.comp_id.fkCode,svb.comp_id.epCode,svb.comp_id.svId,fk.fkName) FROM TepSurchargevaluebase as svb inner join svb.tdiFeekind as fk";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "svb.comp_id.epCode = ~~", "svb.comp_id.svId = ~~", "svb.comp_id.fkCode = '~~'" };
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
