package kyle.leis.es.businessrule.productrule.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ChannelproductruleQuery extends HGeneralQuery {
	
	public ChannelproductruleQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.productrule.da.ChannelproductruleColumns(cpr.comp_id.cprId,cpr.comp_id.brId,cpr.znId,cpr.znvId,chn.chnCode,chn.chnName,chn.chnSname,chn.chnSename) FROM TbrChannelproductrule as cpr inner join cpr.tchnChannel as chn inner join cpr.tbrProductrule as pr";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cpr.comp_id.cprId = ~~", "cpr.comp_id.brId = '~~'", "cpr.znId = ~~", "cpr.znvId = ~~", "chn.chnCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCprid(String cprId) {
		this.setField(0, cprId);
	}

	public String getCprid() {
		return this.getField(0);
	}

	public void setBrid(String brId) {
		this.setField(1, brId);
	}

	public String getBrid() {
		return this.getField(1);
	}

	public void setZnid(String znId) {
		this.setField(2, znId);
	}

	public String getZnid() {
		return this.getField(2);
	}

	public void setZnvid(String znvId) {
		this.setField(3, znvId);
	}

	public String getZnvid() {
		return this.getField(3);
	}

	public void setChncode(String chnCode) {
		this.setField(4, chnCode);
	}

	public String getChncode() {
		return this.getField(4);
	}

}
