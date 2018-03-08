package kyle.leis.es.price.adjustiveprice.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class AdjustivepriceQuery extends HGeneralQuery {
	
	public AdjustivepriceQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.adjustiveprice.da.AdjustivepriceColumns(ap.epCode,ep.epCreatedate,ep.epModifydate,ep.epStartdate,ep.epEnddate,ep.epRemark,ep.epWithdrawsign,ee.eeCode,ee.eeName, ee.eeEsname,ps.psCode,ps.psName,epk.epkCode,epk.epkName,cop.opId,cop.opName,mop.opId,mop.opName,chn.chnCode,chn.chnName) FROM TepAdjustiveprice as ap inner join ap.tepExpressprice as ep inner join ep.tdiEnterpriseelement as ee inner join ep.tdiPricestatus as ps inner join ep.tdiExpresspricekind as epk inner join ep.tdiOperatorByEpOpIdCreate as cop inner join ep.tdiOperatorByEpOpIdModify as mop inner join ap.tchnChannel as chn";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "ap.epCode = ~~", "ep.epEnddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= ep.epStartdate", "ps.psCode = '~~'", "cop.opId = ~~", "mop.opId = ~~", "epk.epkCode = '~~'", "ee.eeCode = '~~'", "chn.chnCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setEpcode(String epCode) {
		this.setField(0, epCode);
	}

	public String getEpcode() {
		return this.getField(0);
	}

	public void setEpstartdate(String epStartdate) {
		this.setField(1, epStartdate);
	}

	public String getEpstartdate() {
		return this.getField(1);
	}

	public void setEpstartdate2(String epStartdate2) {
		this.setField(2, epStartdate2);
	}

	public String getEpstartdate2() {
		return this.getField(2);
	}

	public void setPscode(String psCode) {
		this.setField(3, psCode);
	}

	public String getPscode() {
		return this.getField(3);
	}

	public void setCopid(String copId) {
		this.setField(4, copId);
	}

	public String getCopid() {
		return this.getField(4);
	}

	public void setMopid(String mopId) {
		this.setField(5, mopId);
	}

	public String getMopid() {
		return this.getField(5);
	}

	public void setEpkcode(String epkCode) {
		this.setField(6, epkCode);
	}

	public String getEpkcode() {
		return this.getField(6);
	}

	public void setEecode(String eeCode) {
		this.setField(7, eeCode);
	}

	public String getEecode() {
		return this.getField(7);
	}

	public void setChncode(String chnCode) {
		this.setField(8, chnCode);
	}

	public String getChncode() {
		return this.getField(8);
	}

}
