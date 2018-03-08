package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ChannelQuery extends HGeneralQuery {
	
	public ChannelQuery(){
	    m_strSelectClause = "select new kyle.leis.fs.cachecontainer.da.ChannelColumns(chn.chnCode, chn.chnName, chn.chnSname, chn.chnSename, co.coCode, chn.chnWpxspsmappingname, sot.sotCode, chs.csCode, chs.csName) FROM TchnChannel as chn inner join chn.tcoCorporation as co left join chn.tdiSignouttype as sot inner join chn.tdiChannelstatus as chs";
	    m_strWhereClause = "cstCode = 'S' and chs.csCode NOT IN ('EL', 'CEL')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	} 

}
