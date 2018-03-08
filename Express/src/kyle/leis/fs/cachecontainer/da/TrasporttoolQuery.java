package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class TrasporttoolQuery extends HGeneralQuery {
	
	public TrasporttoolQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.TrasporttoolColumns(tt.ttCode,tt.ttLabelcode,tt.ttDeparturetime,tt.ttArrivaltime,tt.ttDrivername,ttchn.chnSname,ttco.coSname) FROM TdiTrasporttool as tt left join tt.tchnChannel as ttchn left join ttchn.tcoCorporation as ttco";
	    m_strWhereClause = "";
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
