package kyle.leis.es.systemproperty.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class SystempropertyQuery extends HGeneralQuery {
	
	public SystempropertyQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.systemproperty.da.SystempropertyColumns(sp.comp_id.spCode,sp.spName,sp.spValue,ee.eeCode,ee.eeName,ee.eeEname) FROM TesSystemproperty as sp inner join sp.tdiEnterpriseelement as ee";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "sp.comp_id.spCode = '~~'", "sp.spName like '%~~%'", "ee.eeCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setSpcode(String spCode) {
		this.setField(0, spCode);
	}

	public String getSpcode() {
		return this.getField(0);
	}

	public void setSpname(String spName) {
		this.setField(1, spName);
	}

	public String getSpname() {
		return this.getField(1);
	}

	public void setEecode(String eeCode) {
		this.setField(2, eeCode);
	}

	public String getEecode() {
		return this.getField(2);
	}

}
