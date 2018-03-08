package kyle.leis.fs.corewaybillauditlog.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CorewaybillauditlogQuery extends HGeneralQuery {
	
	public CorewaybillauditlogQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.corewaybillauditlog.da.CorewaybillauditlogColumns(cwal.cwalCreatedate,falt.faltCode,falt.faltContent,cw.cwCode,cop.opId,cop.opName) FROM TfsCorewaybillauditlog as cwal inner join cwal.tdiFinanceauditlogtype as falt inner join cwal.topCorewaybill as cw inner join cwal.tdiOperator as cop";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cwCode = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

}
