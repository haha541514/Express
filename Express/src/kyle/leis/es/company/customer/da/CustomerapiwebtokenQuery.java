package kyle.leis.es.company.customer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CustomerapiwebtokenQuery extends HGeneralQuery {
	
	public CustomerapiwebtokenQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.company.customer.da.CustomerapiwebtokenColumns(cawt.cawtId, co.coCode, cawt.cawtUsername, cawt.cawtToken, capwt.capwtCode, capwt.capwtName, capwt.capwtEname, cawt.cawtPassword) FROM TcoCustomerapiwebtoken as cawt inner join cawt.tcoCustomer as co inner join cawt.tdiCustomerapiwebtype as capwt";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cawt.cawtId = ~~", "co.coCode = '~~'", "capwt.capwtCode = '~~'", "cawt.cawtUsername = '~~'", "cawt.cawtToken = '~~'", "cawt.cawtPassword = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCawtcawtid(String cawtCawtId) {
		this.setField(0, cawtCawtId);
	}

	public String getCawtcawtid() {
		return this.getField(0);
	}

	public void setCococode(String coCoCode) {
		this.setField(1, coCoCode);
	}

	public String getCococode() {
		return this.getField(1);
	}

	public void setCapwtcapwtcode(String capwtCapwtCode) {
		this.setField(2, capwtCapwtCode);
	}

	public String getCapwtcapwtcode() {
		return this.getField(2);
	}

	public void setCawtcawtusername(String cawtCawtUsername) {
		this.setField(3, cawtCawtUsername);
	}

	public String getCawtcawtusername() {
		return this.getField(3);
	}

	public void setCawtcawttoken(String cawtCawtToken) {
		this.setField(4, cawtCawtToken);
	}

	public String getCawtcawttoken() {
		return this.getField(4);
	}
	
	public void setCawtcawtpassword(String cawtCawtPassword) {
		this.setField(5, cawtCawtPassword);
	}

	public String getCawtcawtpassword() {
		return this.getField(5);
	}

}
