package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class VolumeweightQuery extends JGeneralQuery {
	
	public VolumeweightQuery(){
	    m_strSelectClause = "SELECT cw.cw_code,FUN_GET_VolumeWeight(cw.cw_code) as VolumeWeight FROM  t_op_corewaybill cw";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cw_code = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new VolumeweightColumns();
	}
	
	public void setCw_code(String cw_code) {
		this.setField(0, cw_code);
	}

	public String getCw_code() {
		return this.getField(0);
	}

}
