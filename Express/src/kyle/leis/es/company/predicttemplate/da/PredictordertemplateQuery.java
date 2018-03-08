package kyle.leis.es.company.predicttemplate.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class PredictordertemplateQuery extends JGeneralQuery {
	
	public PredictordertemplateQuery(){
	    m_strSelectClause = "SELECT pot.pot_id,pot.co_code,pot.op_id_creator,pot.op_id_modifier,pot.pot_name,pot.pot_remark,pot.pot_createdate,pot.pot_modifydate FROM t_co_predictordertemplate pot";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "pot.pot_Id = ~~", "pot.co_code = '~~'", "pot.co_code is ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new PredictordertemplateColumns();
	}
	
	public void setPotid(String potId) {
		this.setField(0, potId);
	}

	public String getPotid() {
		return this.getField(0);
	}

	public void setCocode(String cocode) {
		this.setField(1, cocode);
	}

	public String getCocode() {
		return this.getField(1);
	}

	public void setIscocode(String Iscocode) {
		this.setField(2, Iscocode);
	}

	public String getIscocode() {
		return this.getField(2);
	}

}
