package kyle.leis.fs.dictionary.dictionarys.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ServerfeekindmappingQuery extends HGeneralQuery {
	
	public ServerfeekindmappingQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.dictionarys.da.ServerfeekindmappingColumns(sfm.sfkmCode,sfm.sfkmServerbillkind,sfm.sfkmOrigindesc,fk.fkCode) FROM TdiServerfeekindmapping as sfm inner join sfm.tdiFeekind as fk";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "sfm.sfkmServerbillkind = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setSfkmserverbillkind(String sfkmServerbillkind) {
		this.setField(0, sfkmServerbillkind);
	}

	public String getSfkmserverbillkind() {
		return this.getField(0);
	}

}
