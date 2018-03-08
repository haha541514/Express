package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class TarazzdistrictQuery extends JGeneralQuery {
	
	public TarazzdistrictQuery(){
	    m_strSelectClause = "SELECT td.TD_POSTCODE,td.TD_LOCALITYNAME,td.TD_STATECODE FROM T_DI_TARAZZDISTRICT td";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "td.TD_POSTCODE = '~~'", "td.TD_LOCALITYNAME = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new TarazzdistrictColumns();
	}
	
	public void setTdpostcode(String tdpostcode) {
		this.setField(0, tdpostcode);
	}

	public String getTdpostcode() {
		return this.getField(0);
	}

	public void setLocalityname(String localityname) {
		this.setField(1, localityname);
	}

	public String getLocalityname() {
		return this.getField(1);
	}

}
