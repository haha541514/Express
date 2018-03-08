package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ActionstatusmappingQuery extends HGeneralQuery {
	
	public ActionstatusmappingQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.ActionstatusmappingColumns(asm.asmCode,asm.asmBusinesskind,asm.asmOriginstatus,asm.asmFinalstatus,ak.akCode,ak.akName) FROM TfsActionstatusmapping as asm inner join asm.tdiActionkind as ak";
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
