package kyle.leis.eo.billing.incidentalfee.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SumincidentalfeeQuery extends JGeneralQuery {
	
	public SumincidentalfeeQuery(){
	    m_strSelectClause = "SELECT NVL(SUM(ROUND(decode(iff.bk_code, 'A0101', 1, 'A0201', -1, 'A01', 1, 'A02', -1, 0) * iff.if_currencyrate * iff.if_actualtotal, 2)),0) as incidentalFeeTotal FROM T_BL_INCIDENTALFEE iff";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "iff.co_code = '~~'", "iff.br_id = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SumincidentalfeeColumns();
	}
	
	public void setCocode(String coCode) {
		this.setField(0, coCode);
	}

	public String getCocode() {
		return this.getField(0);
	}

	public void setBrid(String brId) {
		this.setField(1, brId);
	}

	public String getBrid() {
		return this.getField(1);
	}

}
