package kyle.leis.es.businessrule.channeltrackmapping.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ChanneltrackmappingQuery extends HGeneralQuery {
	
	public ChanneltrackmappingQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.channeltrackmapping.da.ChanneltrackmappingColumns(ctm.ctmId,ctm.ctmSourcetrackdesc,ctm.ctmCreatedate,ctm.ctmModifydate,chn.chnCode,chn.chnSname,chn.chnSename,cop.opId,cop.opName,mop.opId,mop.opName,wts.wbtsCode,wts.wbtsName,wts.wbtsEname,ss.ssCode,ss.ssName) FROM TbrChanneltrackmapping as ctm left join ctm.tchnChannel as chn inner join ctm.tdiOperatorByOpIdCreator as cop inner join ctm.tdiOperatorByOpIdModifier as mop left join ctm.tdiWaybilltrackstatus as wts inner join ctm.tdiSimplestatus as ss";
	    m_strWhereClause = "ss.ssCode = 'ON'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "ctm.ctmSourcetrackdesc like '%~~%'", "cop.opId = ~~", "mop.opId = ~~", "ctm.ctmId = ~~", "ctm.ctmModifydate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= ctm.ctmModifydate", "wts.wbtsCode is ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCtmsourcetrackdesc(String ctmSourcetrackdesc) {
		this.setField(0, ctmSourcetrackdesc);
	}

	public String getCtmsourcetrackdesc() {
		return this.getField(0);
	}

	public void setCopid(String copid) {
		this.setField(1, copid);
	}

	public String getCopid() {
		return this.getField(1);
	}

	public void setMopid(String mopid) {
		this.setField(2, mopid);
	}

	public String getMopid() {
		return this.getField(2);
	}

	public void setCtmid(String ctmid) {
		this.setField(3, ctmid);
	}

	public String getCtmid() {
		return this.getField(3);
	}

	public void setStartdate(String startdate) {
		this.setField(4, startdate);
	}

	public String getStartdate() {
		return this.getField(4);
	}

	public void setEnddate(String enddate) {
		this.setField(5, enddate);
	}

	public String getEnddate() {
		return this.getField(5);
	}

	public void setIsnullwbtscode(String isNullWbtscode) {
		this.setField(6, isNullWbtscode);
	}

	public String getIsnullwbtscode() {
		return this.getField(6);
	}

}
