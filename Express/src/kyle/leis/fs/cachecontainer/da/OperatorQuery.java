package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class OperatorQuery extends HGeneralQuery {
	
	public OperatorQuery(){
	    m_strSelectClause = "new kyle.leis.fs.cachecontainer.da.OperatorColumns(op.opId, op.opName, op.opEname, op.opSname, op.opCode, fc.fcCode, co.coCode, ops.osCode, ops.osName) FROM TdiOperator as op inner join op.tdiFunction as fc left join op.tcoCorporation as co left join op.tdiOperatorstatus as ops";
	    m_strWhereClause = "ops.osCode != 'DS'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] { };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	} 

}
