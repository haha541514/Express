package kyle.leis.es.businessrule.manifest.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class MefseqQuery extends JGeneralQuery {
	
	public MefseqQuery(){
	    m_strSelectClause = "SELECT S_MEF_CODE.Nextval as SmefSeq FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new MefseqColumns();
	}
	

}
