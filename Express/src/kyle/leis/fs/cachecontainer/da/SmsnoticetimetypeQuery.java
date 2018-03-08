package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class SmsnoticetimetypeQuery extends HGeneralQuery {
	
	public SmsnoticetimetypeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.SmsnoticetimetypeColumns(sntt.snttCode, sntt.snttName, sntt.snttEname, sntt.snttStarttime, sntt.snttEndtime, sntt.snttRestrictsign) FROM TdiSmsnoticetimetype as sntt";
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
