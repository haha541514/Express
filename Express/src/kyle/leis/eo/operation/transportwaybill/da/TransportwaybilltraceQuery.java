package kyle.leis.eo.operation.transportwaybill.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class TransportwaybilltraceQuery extends HGeneralQuery {
	
	public TransportwaybilltraceQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.transportwaybill.da.TransportwaybilltraceColumns(twbt.comp_id.twbId,twbt.comp_id.twbsCode,twbt.twbtCreatedate,twbt.twbtOccurdate,twbs.twbsName,dt.dtCode,dt.dtHubcode,cop.opId,cop.opName) FROM TopTransportwaybilltrace as twbt inner join twbt.tdiTransportwaybillstatus as twbs inner join twbt.tdiDistrict as dt inner join twbt.tdiOperator as cop";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "twbt.comp_id.twbId = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setTwbid(String twbId) {
		this.setField(0, twbId);
	}

	public String getTwbid() {
		return this.getField(0);
	}

}
