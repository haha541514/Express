package kyle.leis.fs.authority.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class MenusQuery extends HGeneralQuery {
	
	public MenusQuery(){
	    m_strSelectClause = "select new kyle.leis.fs.authority.da.MenusColumns(gm.gmCode, gm.gmName, gm.gmParameter, gm.gmLink, gm.gmIcon, gm.gmLevel, gm.gmStructurecode, gm.gmShortcutkey, gm.gmGroupcode, gm.gmMaxusecount, gm.gmShowtoolbar, gos.gosCode, gm.gmPinyinname) FROM TfsGuimenu as gm inner join gm.tdiGuiopenstyle as gos";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "gm.gmName like '~~'", "gm.tdiInfomationsystemkind.iskCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	} 
	
	public void setGmname(String strGmname) {
		this.setField(0, strGmname);
	}

	public String getGmname() {
		return this.getField(0);
	}
	
	public void setIskcode(String strIskcode) {
		this.setField(1, strIskcode);
	}

	public String getIskcode() {
		return this.getField(1);
	}	

}
