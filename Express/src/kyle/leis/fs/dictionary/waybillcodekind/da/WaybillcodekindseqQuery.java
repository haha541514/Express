package kyle.leis.fs.dictionary.waybillcodekind.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class WaybillcodekindseqQuery extends JGeneralQuery {
	
	public WaybillcodekindseqQuery(){
	    m_strSelectClause = "SELECT s_bck_code.nextval bckCode FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new WaybillcodekindseqColumns();
	}
	

}
