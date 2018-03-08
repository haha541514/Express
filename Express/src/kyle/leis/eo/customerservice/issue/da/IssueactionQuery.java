package kyle.leis.eo.customerservice.issue.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class IssueactionQuery extends HGeneralQuery {
	
	public IssueactionQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.customerservice.issue.da.IssueactionColumns(isa.comp_id.isaId,isa.comp_id.isuId,isa.isaCreatedate,isa.isaContent,cop.opId,cop.opName,ak.akCode,ak.akName) FROM TcsIssueaction as isa inner join isa.tdiOperator as cop inner join isa.tdiActionkind as ak";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "isa.comp_id.isuId = ~~", "isa.comp_id.isaId = ~~", "ak.akCode in (~~)" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setIsuid(String isuId) {
		this.setField(0, isuId);
	}

	public String getIsuid() {
		return this.getField(0);
	}

	public void setIsaid(String isaId) {
		this.setField(1, isaId);
	}

	public String getIsaid() {
		return this.getField(1);
	}

	public void setAkcode(String akCode) {
		this.setField(2, akCode);
	}

	public String getAkcode() {
		return this.getField(2);
	}

}
