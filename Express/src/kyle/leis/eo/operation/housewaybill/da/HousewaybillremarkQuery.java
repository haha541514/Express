package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class HousewaybillremarkQuery extends JGeneralQuery {
	
	public HousewaybillremarkQuery(){
	    m_strSelectClause = "SELECT hw.hw_remark FROM t_op_housewaybill hw";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "hw.cw_code in (~~)" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new HousewaybillremarkColumns();
	}
	
	public void setIncwcode(String Incwcode) {
		this.setField(0, Incwcode);
	}

	public String getIncwcode() {
		return this.getField(0);
	}

}
