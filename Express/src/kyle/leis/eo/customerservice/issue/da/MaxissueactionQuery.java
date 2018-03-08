package kyle.leis.eo.customerservice.issue.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class MaxissueactionQuery extends JGeneralQuery {
	
	public MaxissueactionQuery(){
	    m_strSelectClause = "SELECT max(ISA_ID) as maxIsaid FROM t_cs_issueaction isa";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "isa.ISU_ID = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new MaxissueactionColumns();
	}
	
	public void setIsuid(String isuId) {
		this.setField(0, isuId);
	}

	public String getIsuid() {
		return this.getField(0);
	}

}
