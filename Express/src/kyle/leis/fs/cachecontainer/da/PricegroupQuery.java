package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class PricegroupQuery extends HGeneralQuery {
	
	public PricegroupQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.PricegroupColumns(pg.pgCode, pg.pgName, pg.pgEname) FROM TdiPricegroup as pg";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "pg.pgCommonsign = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setPgcommonsign(String Pgcommonsign) {
		this.setField(0, Pgcommonsign);
	}

	public String getPgcommonsign() {
		return this.getField(0);
	}

}
