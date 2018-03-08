package kyle.leis.eo.operation.specialtype.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class SpecialtypeQuery extends HGeneralQuery {
	
	public SpecialtypeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.specialtype.da.SpecialtypeColumns(wbst.comp_id.cwCode,wbst.comp_id.estCode,wbst.wbstCreatedate,wbst.wbstRemark,est.estName,cop.opId,cop.opName) FROM TopWaybillspecialtype as wbst inner join wbst.tdiExpressspecialtype as est inner join wbst.tdiOperator as cop";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "wbst.comp_id.cwCode = ~~", "wbst.comp_id.estCode = '~~'" };
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

	public void setEstcode(String estCode) {
		this.setField(1, estCode);
	}

	public String getEstcode() {
		return this.getField(1);
	}

}
