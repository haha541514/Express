package kyle.leis.fs.dictionary.dictionarys.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class TransporttrackmappingQuery extends HGeneralQuery {
	
	public TransporttrackmappingQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.dictionarys.da.TransporttrackmappingColumns(ttm.comp_id.twbsCode,ttm.comp_id.wbtsCode) FROM TdiTransporttrackmapping as ttm";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
