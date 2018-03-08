package kyle.leis.es.businessrule.operationprompt.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class OptchannelQuery extends HGeneralQuery {
	
	public OptchannelQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.operationprompt.da.OptchannelColumns(optchn.comp_id.brId,optchn.optnCssign,chn.chnCode,chn.chnName,chn.chnSname,co.coCode) FROM TbrOptchannel as optchn inner join optchn.tchnChannel as chn inner join chn.tcoCorporation as co";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "optchn.comp_id.brId in (~~)", "chn.chnCode = '~~'", "optchn.optnCssign = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setChncode(String chnCode) {
		this.setField(1, chnCode);
	}

	public String getChncode() {
		return this.getField(1);
	}

	public void setOptncssign(String optnCssign) {
		this.setField(2, optnCssign);
	}

	public String getOptncssign() {
		return this.getField(2);
	}

}
