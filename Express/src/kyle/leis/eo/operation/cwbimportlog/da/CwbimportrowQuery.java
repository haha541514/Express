package kyle.leis.eo.operation.cwbimportlog.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CwbimportrowQuery extends HGeneralQuery {
	
	public CwbimportrowQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.cwbimportlog.da.CwbimportrowColumns(cwbr.comp_id.cwbrId,cwbr.topCwbimportlog.cwlId,cwbr.cwbrSuccesssign,cwbr.cwbrOperatetype,cwbr.cwbrRemark) FROM TopCwbimportrow as cwbr";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cwbr.comp_id.cwbrId = ~~", "cwbr.topCwbimportlog.cwlId = ~~", "cwbr.cwbrSuccesssign = '~~'", "cwbr.cwbrOperatetype = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCwbrid(String cwbrId) {
		this.setField(0, cwbrId);
	}

	public String getCwbrid() {
		return this.getField(0);
	}

	public void setCwlid(String cwlId) {
		this.setField(1, cwlId);
	}

	public String getCwlid() {
		return this.getField(1);
	}

	public void setSuccesssign(String successsign) {
		this.setField(2, successsign);
	}

	public String getSuccesssign() {
		return this.getField(2);
	}

	public void setOperatetype(String operatetype) {
		this.setField(3, operatetype);
	}

	public String getOperatetype() {
		return this.getField(3);
	}

}
