package kyle.leis.es.company.channel.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ChanneladdressQuery extends HGeneralQuery {
	
	public ChanneladdressQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.company.channel.da.ChanneladdressColumns(chna.chnCode,chna.chnaAddress1,chna.chnaAddress2,chna.chnaAddress3,chna.chnaProcessingaddress1,chna.chnaProcessingaddress2,chna.chnaProcessingaddress3,chna.chnaMid) FROM TchnAddress as chna";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "chna.chnCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setChncode(String chnCode) {
		this.setField(0, chnCode);
	}

	public String getChncode() {
		return this.getField(0);
	}

}
