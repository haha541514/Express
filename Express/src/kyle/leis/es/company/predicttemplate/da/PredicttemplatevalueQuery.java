package kyle.leis.es.company.predicttemplate.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class PredicttemplatevalueQuery extends HGeneralQuery {
	
	public PredicttemplatevalueQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.company.predicttemplate.da.PredicttemplatevalueColumns(potv.comp_id.potvId,potv.comp_id.potId,potv.potvColumnname,dmk.dmkCode,dmk.dmkName,cmt.cmtCode,cmt.cmtName,tc.tcId,tc.tcColumnindex,tc.tcColumnname,tc.tcColumnename,tc.tcColumngroup,tc.tcColumntype) FROM TcoPredictordertemplatevalue as potv left join potv.tdiDictionarymappingkind as dmk left join potv.tdiColumnmappingtype as cmt left join potv.tdiTemplatecolumn as tc";
	    m_strWhereClause = "";
	    m_strOrderByClause = "potv.comp_id.potvId asc";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "potv.comp_id.potId = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setPotid(String potId) {
		this.setField(0, potId);
	}

	public String getPotid() {
		return this.getField(0);
	}

}
