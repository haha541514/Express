package kyle.leis.eo.operation.housewaybill.dax;

import kyle.common.dbaccess.query.IColumns;
import kyle.leis.eo.operation.housewaybill.da.SobagstatisticCondition;
import kyle.leis.eo.operation.housewaybill.da.SobagstatisticQuery;

public class SobagstatisticQueryEX extends SobagstatisticQuery {
	
	public SobagstatisticQueryEX(SobagstatisticCondition objSBSCondition){
		super.setCondition(objSBSCondition);
		m_strSelectClause = "select bagno,count(1),sum(cw_serverchargeweight) from (" + buildQuerySQL() + ")";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "bagno";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] { };				
	}
	
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SobagstatisticColumnsEX();
	}	
}
