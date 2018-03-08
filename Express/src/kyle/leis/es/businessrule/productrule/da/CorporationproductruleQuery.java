package kyle.leis.es.businessrule.productrule.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CorporationproductruleQuery extends HGeneralQuery {
	
	public CorporationproductruleQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.productrule.da.CorporationproductruleColumns(cswb.comp_id.brId,cswb.comp_id.coCode,co.coCode,co.coLabelcode,co.coName,co.coEname,co.coSname,co.coSename) FROM TbrCussignoutbyoriginwb  as cswb inner join cswb.tcoCustomer as cc inner join cc.tcoCorporation  as co";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cswb.comp_id.brId = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

}
