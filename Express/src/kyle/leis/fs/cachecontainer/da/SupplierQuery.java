package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class SupplierQuery extends HGeneralQuery {
	
	public SupplierQuery(){
	    m_strSelectClause = "select new kyle.leis.fs.cachecontainer.da.SupplierColumns(sp.coCode, sp.spManifestseriesnumber) FROM TcoSupplier as sp";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "sp.coCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	} 

}
