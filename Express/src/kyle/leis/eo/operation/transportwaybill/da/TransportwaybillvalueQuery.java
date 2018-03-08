package kyle.leis.eo.operation.transportwaybill.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class TransportwaybillvalueQuery extends HGeneralQuery {
	
	public TransportwaybillvalueQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.transportwaybill.da.TransportwaybillvalueColumns(twbv.comp_id.twbId,twbv.comp_id.twbvId,twbv.twbvBaglabelcode,bw.bwCode,bw.bwLabelcode,bw.addDate,co.coCode,co.coSname,co.coLabelcode,cw.cwCode,cw.cwServerewbcode,cw.cwCustomerewbcode,cw.cwEwbcode) FROM TopTransportwaybillvalue as twbv left join twbv.topBatchwaybill as bw left join bw.tcoCorporation as co left join twbv.topCorewaybill as cw";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "twbv.comp_id.twbId = ~~", "bw.bwCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
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

	public void setBwcode(String bwCode) {
		this.setField(1, bwCode);
	}

	public String getBwcode() {
		return this.getField(1);
	}

}
