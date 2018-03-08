package kyle.leis.es.workbill.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class WorkbillactionQuery extends HGeneralQuery {
	
	public WorkbillactionQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.workbill.da.WorkbillactionColumns(wba.wbaId,wba.wbaCreatedate, wba.wbaContent,op.opName,ak.akName,op.opId,ak.akCode,wb.wbId) FROM TesWorkbillaction as wba inner join wba.tdiOperator as op inner join wba.tesWorkbill as wb inner join wba.tdiActionkind as ak";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "wba.wbaId='~~'", "wb.wbId='~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setWbaid(String wbaId) {
		this.setField(0, wbaId);
	}

	public String getWbaid() {
		return this.getField(0);
	}

	public void setWbid(String wbId) {
		this.setField(1, wbId);
	}

	public String getWbid() {
		return this.getField(1);
	}

}
