package kyle.leis.fs.authoritys.gmenus.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class GmenusQuery extends HGeneralQuery {
	
	public GmenusQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.authoritys.gmenus.da.GmenusColumns(gm.gmCode, gm.gmName, gm.gmParameter, gm.gmLink, gm.gmIcon, gm.gmLevel, gm.gmStructurecode, gm.gmShortcutkey, gm.gmGroupcode, gm.gmMaxusecount, gm.gmShowtoolbar,gm.gmPinyinname, gos.gosCode, gos.gosName, gos.gosEname,isk.iskCode, isk.iskName, isk.iskEname) FROM TfsGuimenu as gm inner join gm.tdiGuiopenstyle as gos inner join gm.tdiInfomationsystemkind as isk";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "gm.gmName like '~~'", "gm.gmCode = '~~' ", "gm.gmStructurecode = '~~'", "gm.gmGroupcode = '~~'", "gos.gosCode = '~~'", "isk.iskCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setGmname(String gmName) {
		this.setField(0, gmName);
	}

	public String getGmname() {
		return this.getField(0);
	}

	public void setGmcode(String gmCode) {
		this.setField(1, gmCode);
	}

	public String getGmcode() {
		return this.getField(1);
	}

	public void setGmstructurecode(String gmStructurecode) {
		this.setField(2, gmStructurecode);
	}

	public String getGmstructurecode() {
		return this.getField(2);
	}

	public void setGmgroupcode(String gmGroupcode) {
		this.setField(3, gmGroupcode);
	}

	public String getGmgroupcode() {
		return this.getField(3);
	}

	public void setGoscode(String gosCode) {
		this.setField(4, gosCode);
	}

	public String getGoscode() {
		return this.getField(4);
	}

	public void setIskcode(String iskCode) {
		this.setField(5, iskCode);
	}

	public String getIskcode() {
		return this.getField(5);
	}

}
