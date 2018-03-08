package kyle.leis.es.businessrule.operationprompt.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class OptorigindistrictQuery extends HGeneralQuery {
	
	public OptorigindistrictQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.operationprompt.da.OptorigindistrictColumns(optodt.comp_id.brId,odt.dtCode,odt.dtName) FROM TbrOptorigindistrict as optodt inner join optodt.tdiDistrict as odt";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "optodt.comp_id.brId in (~~)", "odt.dtCode = '~~'" };
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
