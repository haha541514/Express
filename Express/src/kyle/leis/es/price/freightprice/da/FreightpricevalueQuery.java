package kyle.leis.es.price.freightprice.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class FreightpricevalueQuery extends HGeneralQuery {
	
	public FreightpricevalueQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.freightprice.da.FreightpricevalueColumns(fpv.comp_id.epCode,fpv.comp_id.fpvId,fpv.znvId,fpv.fpvWeightgrade,fpv.fpvWeightunit,fpv.fpvCarryweight,fpv.fpvPricevalue,fvt.fvtCode,fvt.fvtName) FROM TepFreightpricevalue as fpv inner join fpv.tdiFreightvaluetype as fvt";
	    m_strWhereClause = "";
	    m_strOrderByClause = "fpv.fpvWeightgrade";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "fpv.comp_id.epCode = ~~", "fpv.comp_id.fpvId = ~~" };
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

	public void setFpvid(String fpvId) {
		this.setField(1, fpvId);
	}

	public String getFpvid() {
		return this.getField(1);
	}

}
