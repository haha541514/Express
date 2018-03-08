package kyle.leis.es.price.currency.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CurrencyvalueQuery extends HGeneralQuery {
	
	public CurrencyvalueQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.currency.da.CurrencyvalueColumns(cv.comp_id.cvId,cv.comp_id.epCode,cv.cvCurrencyrate,ckb.ckCode,ckb.ckName,ckc.ckCode,ckc.ckName) FROM TepCurrencyvalue as cv inner join cv.tdiCurrencykindByCkCodeChange as ckc inner join cv.tdiCurrencykindByCkCodeBase as ckb";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cv.comp_id.epCode = ~~", "cv.comp_id.cvId = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setEpcode(String epCode) {
		this.setField(0, epCode);
	}

	public String getEpcode() {
		return this.getField(0);
	}

	public void setCvid(String cvId) {
		this.setField(1, cvId);
	}

	public String getCvid() {
		return this.getField(1);
	}

}
