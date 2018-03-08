package kyle.leis.eo.finance.financeauditlogtype.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class TdifinanceauditlogtypeQuery extends HGeneralQuery {
	
	public TdifinanceauditlogtypeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.finance.financeauditlogtype.da.TdifinanceauditlogtypeColumns(fal.faltCode,fal.faltContent) FROM TdiFinanceauditlogtype as fal";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "fal.faltCode = '~~'", "fal.faltContent = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setFaltcode(String faltCode) {
		this.setField(0, faltCode);
	}

	public String getFaltcode() {
		return this.getField(0);
	}

	public void setFaltcontent(String faltContent) {
		this.setField(1, faltContent);
	}

	public String getFaltcontent() {
		return this.getField(1);
	}

}
