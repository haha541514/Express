package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class LabelformatQuery extends HGeneralQuery {
	
	public LabelformatQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.LabelformatColumns(lf.lfCode,lf.lfName,lf.lfEname) FROM TdiLabelformat as lf";
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
