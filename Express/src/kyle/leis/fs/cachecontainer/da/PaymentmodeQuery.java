package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class PaymentmodeQuery extends HGeneralQuery {
	
	public PaymentmodeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.PaymentmodeColumns(pm.pmCode, pm.pmName, pm.pmEname, pm.pmSename, pm.pmVisiblesign, ss.ssCode) FROM TdiPaymentmode as pm inner join pm.tdiSimplestatus as ss";
	    m_strWhereClause = "ss.ssCode = 'ON'";
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
