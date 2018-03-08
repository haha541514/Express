package kyle.leis.es.businessrule.productrule.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ProductcswbQuery extends HGeneralQuery {
	
	public ProductcswbQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.productrule.da.ProductcswbColumns(pr.brId,pr.prAllcussignoutbyoriginwbsign,cswb.comp_id.brId,cswb.comp_id.coCode) FROM TbrCussignoutbyoriginwb as cswb inner join cswb.tbrProductrule as pr";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "pr.brId = ~~", "cswb.comp_id.coCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
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

	public void setCocode(String coCode) {
		this.setField(1, coCode);
	}

	public String getCocode() {
		return this.getField(1);
	}

}
