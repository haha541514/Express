package kyle.leis.es.businessrule.manifest.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ManifeststandardQuery extends HGeneralQuery {
	
	public ManifeststandardQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.manifest.da.ManifeststandardColumns(msc.mscCode,msc.mscColumnname,msc.mscColumnename,msc.mscSqlcolumnname) FROM TdiManifeststandardcolumn as msc";
	    m_strWhereClause = "";
	    m_strOrderByClause = "msc.mscCode";
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
