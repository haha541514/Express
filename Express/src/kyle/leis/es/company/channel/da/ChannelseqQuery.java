package kyle.leis.es.company.channel.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class ChannelseqQuery extends JGeneralQuery {
	
	public ChannelseqQuery(){
	    m_strSelectClause = "SELECT S_CHN_Code.nextval as ChannelSeq FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new ChannelseqColumns();
	}
	

}
