package kyle.leis.es.businessrule.operationprompt.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class OptoperationtacheQuery extends HGeneralQuery {
	
	public OptoperationtacheQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.operationprompt.da.OptoperationtacheColumns(optot.comp_id.brId,ot.otCode,ot.otName) FROM TbrOptoperationtache as optot inner join optot.tdiOperationtache as ot";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "optot.comp_id.brId in (~~)", "ot.otCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setOtcode(String otCode) {
		this.setField(1, otCode);
	}

	public String getOtcode() {
		return this.getField(1);
	}

}
