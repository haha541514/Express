package kyle.leis.eo.operation.corewaybillcode.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CorewaybillcodeQuery extends HGeneralQuery {
	
	public CorewaybillcodeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.corewaybillcode.da.CorewaybillcodeColumns(cwbc.comp_id.cwbcId,cwbc.comp_id.cwCode,cwbc.cwbcCustomerewbcode) FROM TopCorewaybillcode as cwbc";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cwbc.comp_id.cwCode = ~~", "cwbc.comp_id.cwbcId = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setCwbcid(String cwbcId) {
		this.setField(1, cwbcId);
	}

	public String getCwbcid() {
		return this.getField(1);
	}

}
