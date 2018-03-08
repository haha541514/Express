package kyle.fetcher.track.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ChanneltrackmappingQuery extends HGeneralQuery {
	
	public ChanneltrackmappingQuery(){
	    m_strSelectClause = "SELECT new kyle.fetcher.track.da.ChanneltrackmappingColumns(ctm.ctmId,ctm.ctmSourcetrackdesc,ctm.ctmCreatedate,ctm.ctmModifydate,chn.chnCode,chn.chnSname,cop.opId,cop.opName,mop.opId,mop.opName,wbts.wbtsCode,wbts.wbtsName,ss.ssCode,ss.ssName) FROM TbrChanneltrackmapping as ctm inner join ctm.tchnChannel as chn inner join ctm.tdiOperatorByOpIdCreator as cop inner join ctm.tdiOperatorByOpIdModifier as mop inner join ctm.tdiWaybilltrackstatus as wbts inner join ctm.tdiSimplestatus as ss";
	    m_strWhereClause = "ss.ssCode = 'ON'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "ctm.ctmId = ~~", "ctm.ctmSourcetrackdesc like '%~~%'", "chn.chnCode = '~~'", "wbts.wbtsCode = '~~'", "upper(ctm.ctmSourcetrackdesc) = upper('~~')" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCtmid(String ctmId) {
		this.setField(0, ctmId);
	}

	public String getCtmid() {
		return this.getField(0);
	}

	public void setCtmsourcetrackdesc(String ctmSourcetrackdesc) {
		this.setField(1, ctmSourcetrackdesc);
	}

	public String getCtmsourcetrackdesc() {
		return this.getField(1);
	}

	public void setChncode(String chnCode) {
		this.setField(2, chnCode);
	}

	public String getChncode() {
		return this.getField(2);
	}

	public void setWbtscode(String wbtsCode) {
		this.setField(3, wbtsCode);
	}

	public String getWbtscode() {
		return this.getField(3);
	}
	
	public void setCtmeqsourcetrackdesc(String ctmSourcetrackdesc) {
		this.setField(4, ctmSourcetrackdesc);
	}

	public String getCtmeqsourcetrackdesc() {
		return this.getField(4);
	}	

}
