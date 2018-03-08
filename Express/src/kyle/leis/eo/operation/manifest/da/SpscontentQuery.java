package kyle.leis.eo.operation.manifest.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class SpscontentQuery extends HGeneralQuery {
	
	public SpscontentQuery(){
	    m_strSelectClause = "select new kyle.leis.eo.operation.manifest.da.SpscontentColumns(mfv.cwSpscontent) FROM TopManifestvalue as mfv";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "mfv.comp_id.mfCode = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	public String getSQL() {
		return this.buildQuerySQL();
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	} 

}
