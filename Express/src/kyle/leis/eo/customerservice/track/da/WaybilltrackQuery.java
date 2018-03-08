package kyle.leis.eo.customerservice.track.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class WaybilltrackQuery extends HGeneralQuery {
	
	public WaybilltrackQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.customerservice.track.da.WaybilltrackColumns(wbt.wbtId,wbt.wbtDescription,wbt.wbtOrigindescription,wbt.wbtLocation,wbt.wbtOccurdate,wbt.wbtCreatedate,wbt.wbtModifydate,wbt.wbtOpensign,wbbt.cwCode,dt.dtCode,dt.dtHubcode,mop.opId,mop.opName,cop.opId,cop.opName,wbts.wbtsCode,wbts.wbtsName,wbts.wbtsAbnormalsign,wbtp.wbtpCode,wbtp.wbtpName,co.coCode,co.coSname,co.coLabelcode,wbt.wbtFrom,wbts.wbtsEname) FROM TcsWaybilltrack as wbt inner join wbt.tcsWaybillbatchtrack as wbbt left join wbt.tdiDistrict as dt inner join wbt.tdiOperatorByOpIdModifier as mop inner join wbt.tdiOperatorByOpIdCreator as cop left join wbt.tdiWaybilltrackstatus as wbts left join wbts.tdiWaybilltransferphase as wbtp left join wbt.tcoCorporation as co";
	    m_strWhereClause = "";
	    m_strOrderByClause = "wbt.wbtOccurdate desc";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "wbt.wbtId = ~~", "wbbt.cwCode in (~~)", "wbbt.cwCode = ~~", "dt.dtCode = '~~'", "wbts.wbtsCode = '~~'", "wbt.wbtOpensign = '~~'", "wbt.wbtOccurdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= wbt.wbtOccurdate", "wbt.wbtDescription = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setWbtid(String wbtId) {
		this.setField(0, wbtId);
	}

	public String getWbtid() {
		return this.getField(0);
	}

	public void setCwcode(String cwCode) {
		this.setField(1, cwCode);
	}

	public String getCwcode() {
		return this.getField(1);
	}

	public void setIscwcode(String isCwCode) {
		this.setField(2, isCwCode);
	}

	public String getIscwcode() {
		return this.getField(2);
	}

	public void setDtcode(String dtCode) {
		this.setField(3, dtCode);
	}

	public String getDtcode() {
		return this.getField(3);
	}

	public void setWbtscode(String wbtsCode) {
		this.setField(4, wbtsCode);
	}

	public String getWbtscode() {
		return this.getField(4);
	}

	public void setWbtopensign(String wbtOpensign) {
		this.setField(5, wbtOpensign);
	}

	public String getWbtopensign() {
		return this.getField(5);
	}

	public void setStartoccurdate(String StartOccurdate) {
		this.setField(6, StartOccurdate);
	}

	public String getStartoccurdate() {
		return this.getField(6);
	}

	public void setEndoccurdate(String EndOccurdate) {
		this.setField(7, EndOccurdate);
	}

	public String getEndoccurdate() {
		return this.getField(7);
	}
	
	public void setWbtdescription(String Wbtdescription) {
		this.setField(8, Wbtdescription);
	}

	public String getWbtdescription() {
		return this.getField(8);
	}	
}
