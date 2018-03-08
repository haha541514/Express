package kyle.leis.fs.dictionary.enterpriseelement.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class EecityQuery extends HGeneralQuery {
	
	public EecityQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.enterpriseelement.da.EecityColumns(eect.comp_id.eeCode,eect.comp_id.eecId,st.stCode,ct.ctCode) FROM TdiEecity as eect inner join eect.tdiEnterpriseelement as ee left join eect.tdiState as st left join eect.tdiCity as ct";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "eect.comp_id.eeCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setEecode(String eeCode) {
		this.setField(0, eeCode);
	}

	public String getEecode() {
		return this.getField(0);
	}

}
