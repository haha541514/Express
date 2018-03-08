package kyle.leis.eo.operation.cwbimportlog.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CwbimportdataQuery extends HGeneralQuery {
	
	public CwbimportdataQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.cwbimportlog.da.CwbimportdataColumns(cwbd.comp_id.cwbrId,cwbd.topCwbimportrow.topCwbimportlog.cwlId,cwbd.comp_id.cwbdColumnname,cwbd.cwbdValue) FROM TopCwbimportdata as cwbd";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cwbd.comp_id.cwbrId = ~~", "cwbd.topCwbimportrow.topCwbimportlog.cwlId = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
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

}
