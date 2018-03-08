package kyle.leis.fs.authoritys.gmenus.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class GmitemQuery extends HGeneralQuery {
	
	public GmitemQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.authoritys.gmenus.da.GmitemColumns(gmi.gmCode,gmi.gmiContent) FROM TfsGuimenuitem as gmi";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "gmi.gmCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setGmcode(String gmCode) {
		this.setField(0, gmCode);
	}

	public String getGmcode() {
		return this.getField(0);
	}

}
