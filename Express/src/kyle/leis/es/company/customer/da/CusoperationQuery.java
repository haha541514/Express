package kyle.leis.es.company.customer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CusoperationQuery extends HGeneralQuery {
	
	public CusoperationQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.company.customer.da.CusoperationColumns(cm.coCode,ot.otCode) FROM TcoCustomersorestrict as cr inner join cr.tcoCustomer as cm inner join cr.tdiOperationtache as ot";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cm.coCode='~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCmcocode(String cmcocode) {
		this.setField(0, cmcocode);
	}

	public String getCmcocode() {
		return this.getField(0);
	}

}
