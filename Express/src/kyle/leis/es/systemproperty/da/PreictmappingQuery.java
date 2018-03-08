package kyle.leis.es.systemproperty.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class PreictmappingQuery extends JGeneralQuery {
	
	public PreictmappingQuery(){
	    m_strSelectClause = "SELECT PM_OriginValue,PM_StandardValue FROM T_ES_PreictMapping pm";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "DMK_Code = '~~'", "PTC_Code = '~~'", "PM_OriginValue = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new PreictmappingColumns();
	}
	
	public void setDmkcode(String dmkcode) {
		this.setField(0, dmkcode);
	}

	public String getDmkcode() {
		return this.getField(0);
	}

	public void setPtccode(String ptccode) {
		this.setField(1, ptccode);
	}

	public String getPtccode() {
		return this.getField(1);
	}

	public void setOriginvalue(String originvalue) {
		this.setField(2, originvalue);
	}

	public String getOriginvalue() {
		return this.getField(2);
	}

}
