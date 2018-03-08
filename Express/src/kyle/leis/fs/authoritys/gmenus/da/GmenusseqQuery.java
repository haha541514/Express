package kyle.leis.fs.authoritys.gmenus.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class GmenusseqQuery extends JGeneralQuery {
	
	public GmenusseqQuery(){
	    m_strSelectClause = "SELECT s_Menu_Code.Nextval as GmenusSeq FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new GmenusseqColumns();
	}
	

}
