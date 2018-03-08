package kyle.leis.fs.dictionary.district.tp;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;
import kyle.leis.fs.dictionary.district.da.CityColumns;

public class CityseqQuery extends JGeneralQuery{
    
	public CityseqQuery(){   
	    m_strSelectClause = "SELECT S_City_CODE.nextval as Eeseq FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CityColumns();
	}
}
