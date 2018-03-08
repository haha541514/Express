package kyle.leis.es.businessrule.weightrule.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CarryweightrulevalueQuery extends HGeneralQuery {
	
	public CarryweightrulevalueQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.weightrule.da.CarryweightrulevalueColumns(cwrv.comp_id.cwrvId,cwrv.comp_id.brId,cwrv.cwrvWeightgrade,cwrv.cwrvValue) FROM TbrCarryweightrulevalue as cwrv inner join cwrv.tbrWeightrule as wr";
	    m_strWhereClause = "";
	    m_strOrderByClause = "cwrv.cwrvWeightgrade";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cwrv.comp_id.cwrvId = ~~", "cwrv.comp_id.brId = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCwrvid(String cwrvId) {
		this.setField(0, cwrvId);
	}

	public String getCwrvid() {
		return this.getField(0);
	}

	public void setBrid(String brId) {
		this.setField(1, brId);
	}

	public String getBrid() {
		return this.getField(1);
	}

}
