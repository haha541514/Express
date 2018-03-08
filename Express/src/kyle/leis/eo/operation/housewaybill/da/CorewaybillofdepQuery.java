package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CorewaybillofdepQuery extends JGeneralQuery {
	
	public CorewaybillofdepQuery(){
	    m_strSelectClause = "SELECT cw.cw_serverewbcode FROM t_op_corewaybill cw";
	    m_strWhereClause = "cw.cws_code != 'EL'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.bw_code_departure = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CorewaybillofdepColumns();
	}
	
	public void setBwcodedeparture(String bwcodedeparture) {
		this.setField(0, bwcodedeparture);
	}

	public String getBwcodedeparture() {
		return this.getField(0);
	}

}
