package kyle.leis.es.smsservice.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SmsidseqQuery extends JGeneralQuery {
	
	public SmsidseqQuery(){
	    m_strSelectClause = "SELECT S_SMS_ID.nextval as SmsIdseq FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SmsidseqColumns();
	}
	

}
