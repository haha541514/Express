package kyle.leis.fs.dictionary.dictionarys.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class TntaddressQuery extends JGeneralQuery {
	
	public TntaddressQuery(){
	    m_strSelectClause = "SELECT TNTCA_Companyname,TNTCA_Address1,TNTCA_Address2,TNTCA_Postcode,TNTCA_Cityname FROM T_DI_TNTCOUNTRYADDRESS tntca";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "tntca.TNTCA_CountryHubcode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new TntaddressColumns();
	}
	
	public void setCountryhubcode(String countryhubcode) {
		this.setField(0, countryhubcode);
	}

	public String getCountryhubcode() {
		return this.getField(0);
	}

}
