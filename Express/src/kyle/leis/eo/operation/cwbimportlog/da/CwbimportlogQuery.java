package kyle.leis.eo.operation.cwbimportlog.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CwbimportlogQuery extends HGeneralQuery {
	
	public CwbimportlogQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.cwbimportlog.da.CwbimportlogColumns(toc.cwlId,toc.tdiOperator.opId,toc.cwlCreatedate,toc.cwlTotalrecords,toc.cwlUnsuccessfulrecords,toc.cwlFilepath,toc.cwlRemark,toc.cwlStatus,toc.potId) FROM TopCwbimportlog as toc";
	    m_strWhereClause = "";
	    m_strOrderByClause = "toc.cwlCreatedate desc";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "toc.cwlId = ~~", "toc.tdiOperator.opId = ~~", "toc.cwlCreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= toc.cwlCreatedate ", "toc.cwlTotalrecords = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCwlid(String cwlId) {
		this.setField(0, cwlId);
	}

	public String getCwlid() {
		return this.getField(0);
	}

	public void setOpid(String opId) {
		this.setField(1, opId);
	}

	public String getOpid() {
		return this.getField(1);
	}

	public void setBegindate(String begindate) {
		this.setField(2, begindate);
	}

	public String getBegindate() {
		return this.getField(2);
	}

	public void setEnddate(String enddate) {
		this.setField(3, enddate);
	}

	public String getEnddate() {
		return this.getField(3);
	}

	public void setTotalrecords(String totalrecords) {
		this.setField(4, totalrecords);
	}

	public String getTotalrecords() {
		return this.getField(4);
	}

}
