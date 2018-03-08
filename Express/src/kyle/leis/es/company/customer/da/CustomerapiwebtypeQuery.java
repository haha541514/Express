package kyle.leis.es.company.customer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CustomerapiwebtypeQuery extends HGeneralQuery {
	
	public CustomerapiwebtypeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.company.customer.da.CustomerapiwebtypeColumns(capwt.capwtCode, capwt.capwtName, capwt.capwtEname) FROM TdiCustomerapiwebtype as capwt";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "capwt.capwtCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCapwtcapwtcode(String capwtCapwtCode) {
		this.setField(0, capwtCapwtCode);
	}

	public String getCapwtcapwtcode() {
		return this.getField(0);
	}

}
