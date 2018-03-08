package kyle.leis.fs.authoritys.user.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CocodeseqQuery extends JGeneralQuery {
	
	public CocodeseqQuery(){
	    m_strSelectClause = "SELECT s_corportion_code.nextval as Cocodeseq FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CocodeseqColumns();
	}
	

}
