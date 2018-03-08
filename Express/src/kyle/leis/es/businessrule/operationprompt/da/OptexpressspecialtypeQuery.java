package kyle.leis.es.businessrule.operationprompt.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class OptexpressspecialtypeQuery extends HGeneralQuery {
	
	public OptexpressspecialtypeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.operationprompt.da.OptexpressspecialtypeColumns(optest.comp_id.brId,est.estCode,est.estName) FROM TbrOptexpressspecialtype as optest inner join optest.tdiExpressspecialtype as est";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "optest.comp_id.brId = ~~", "est.estCode = '~~'" };
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

	public void setEstcode(String estCode) {
		this.setField(1, estCode);
	}

	public String getEstcode() {
		return this.getField(1);
	}

}
