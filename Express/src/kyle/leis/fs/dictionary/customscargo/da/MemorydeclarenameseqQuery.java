package kyle.leis.fs.dictionary.customscargo.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class MemorydeclarenameseqQuery extends JGeneralQuery {
	
	public MemorydeclarenameseqQuery(){
	    m_strSelectClause = "SELECT S_MDN_CODE.nextval as MemorydeclarenameSeq FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new MemorydeclarenameseqColumns();
	}
	

}
