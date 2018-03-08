package kyle.leis.es.price.adjustiveprice.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class AdjustivepricevalueQuery extends HGeneralQuery {
	
	public AdjustivepricevalueQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.adjustiveprice.da.AdjustivepricevalueColumns(apv.comp_id.epCode,apv.comp_id.apvId,apv.apvWeekday,apv.apvPricevalue,ct.ctCode,ct.ctName,dt.dtCode,dt.dtName,ut.utCode, ut.utName) FROM TepAdjustivepricevalue as apv inner join apv.tdiCargotype as ct inner join apv.tdiDistrict as dt inner join apv.tdiUnittype as ut";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "apv.comp_id.epCode = ~~", "apv.comp_id.apvId = ~~" };
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

	public void setApvid(String apvId) {
		this.setField(1, apvId);
	}

	public String getApvid() {
		return this.getField(1);
	}

}
