package kyle.leis.es.businessrule.operationprompt.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class OptdeparturedistrictQuery extends HGeneralQuery {
	
	public OptdeparturedistrictQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.operationprompt.da.OptdeparturedistrictColumns(optddt.comp_id.brId,ddt.dtCode,ddt.dtName) FROM TbrOptdeparturedistrict as optddt inner join optddt.tdiDistrict as ddt";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "optddt.comp_id.brId in (~~)", "(ddt.dtCode = '~~' or ddt.dtCode = '~~')" };
	    m_aiConditionVariableCount = new int[] { 1, 2 };		
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
	
	public void setCountrydtcode(String dtCode) {
		this.setField(2, dtCode);
	}

	public String getCountrydtcode() {
		return this.getField(2);
	}	
	
}
