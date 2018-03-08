package kyle.leis.es.bulletin.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class WechatitemQuery extends HGeneralQuery {
	
	public WechatitemQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.bulletin.da.WechatitemColumns(wc.comp_id.wciId, bl.blId, wc.wciTitle, wc.wciDescription, wc.wciPicurl, wc.wciUrl, wc.wciContent) FROM TesWechatitem as wc inner join wc.tesBulletin as bl";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "wc.comp_id.wciId = '~~'", "bl.blId = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setWciid(String wciId) {
		this.setField(0, wciId);
	}

	public String getWciid() {
		return this.getField(0);
	}

	public void setBlid(String blId) {
		this.setField(1, blId);
	}

	public String getBlid() {
		return this.getField(1);
	}

}
