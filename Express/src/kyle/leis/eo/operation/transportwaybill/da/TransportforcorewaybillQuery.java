package kyle.leis.eo.operation.transportwaybill.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class TransportforcorewaybillQuery extends HGeneralQuery {
	
	public TransportforcorewaybillQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.transportwaybill.da.TransportforcorewaybillColumns(twbv.twbvBaglabelcode,bw.bwCode,twb.twbLabelcode,tt.ttLabelcode,tt.ttDrivername, twbv.comp_id.twbId) FROM TopTransportwaybillvalue as twbv inner join twbv.topTransportwaybill as twb left join twbv.topBatchwaybill as bw inner join twb.tdiTrasporttool as tt";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "bw.bwCode = '~~'", "twbv.twbvBaglabelcode = '~~'", "twbv.twbvBaglabelcode is '~~'", "twbv.topCorewaybill.cwCode = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBwcode(String bwCode) {
		this.setField(0, bwCode);
	}

	public String getBwcode() {
		return this.getField(0);
	}

	public void setTwbvbaglabelcode(String twbvBaglabelcode) {
		this.setField(1, twbvBaglabelcode);
	}

	public String getTwbvbaglabelcode() {
		return this.getField(1);
	}

	public void setNulltwbvbaglabelcode(String nulltwbvBaglabelcode) {
		this.setField(2, nulltwbvBaglabelcode);
	}

	public String getNulltwbvbaglabelcode() {	
		return this.getField(2);
	}
	
	public void setCwcode(String cwCode) {
		this.setField(3, cwCode);
	}

	public String getCwcode() {	
		return this.getField(3);
	}	
	
}
