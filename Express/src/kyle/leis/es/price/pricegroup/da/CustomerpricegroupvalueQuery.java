package kyle.leis.es.price.pricegroup.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CustomerpricegroupvalueQuery extends HGeneralQuery {
	
	public CustomerpricegroupvalueQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.pricegroup.da.CustomerpricegroupvalueColumns(cpgv.comp_id.pgCode,cpgv.comp_id.epCode,cpgv.comp_id.fkCode,pg.pgCode,pg.pgName,fk.fkCode,fk.fkName) FROM TepCustomerpricegroupvalue as cpgv inner join cpgv.tdiPricegroup as pg inner join cpgv.tdiFeekind as fk";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cpgv.comp_id.epCode = ~~", "cpgv.comp_id.fkCode = '~~'", "cpgv.comp_id.pgCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
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

	public void setFkcode(String fkCode) {
		this.setField(1, fkCode);
	}

	public String getFkcode() {
		return this.getField(1);
	}

	public void setPgcode(String pgCode) {
		this.setField(2, pgCode);
	}

	public String getPgcode() {
		return this.getField(2);
	}

}
