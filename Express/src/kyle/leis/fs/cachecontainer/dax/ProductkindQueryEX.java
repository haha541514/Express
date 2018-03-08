package kyle.leis.fs.cachecontainer.dax;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ProductkindQueryEX  extends HGeneralQuery {
	
	public ProductkindQueryEX(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.dax.ProductkindColumnsEX(pk.pkCode, pk.pkName, pk.pkEname, pk.pkSname, pk.pkSename, pk.pkDescription, ss.ssCode, pk.pkSigninrestrictsign, pk.pkShowserverewbcode, pk.pkBillingbybatchwaysign, pk.pkSigninneedpostcode, pk.pkSiprintselflabelcodesign, pk.pkWebstructurecode) FROM TdiProductkind as pk inner join pk.tdiSimplestatus as ss";
	    m_strWhereClause = "ss.ssCode = 'ON'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "pk.pkSaletrialsign = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setPksaletrialsign(String Pksaletrialsign) {
		this.setField(0, Pksaletrialsign);
	}

	public String getPksaletrialsign() {
		return this.getField(0);
	}

}
