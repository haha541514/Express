package kyle.leis.fs.authoritys.rolegmenus.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class RolegmenusQuery extends HGeneralQuery {
	
	public RolegmenusQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.authoritys.rolegmenus.da.RolegmenusColumns(rgm.comp_id.gmCode, rgm.comp_id.rlCode, rgm.rmOpIdCreator, rgm.rmCreatedate, rgm.rmOpIdModifier, rgm.rmModifydate, rl.rlStructurecode, rl.rlName, rl.rlEname, rl.rlAdministratorsign, gm.gmCode, gm.gmName, gm.gmParameter, gm.gmLink, gm.gmIcon, gm.gmLevel, gm.gmStructurecode, gm.gmShortcutkey, gm.gmGroupcode, gm.gmMaxusecount, gm.gmShowtoolbar, gos.gosCode, isk.iskCode, isk.iskName, isk.iskEname) FROM TfsRolemenu as rgm inner join rgm.tfsGuimenu as gm inner join gm.tdiGuiopenstyle as gos inner join rgm.tfsRole as rl inner join rl.tdiInfomationsystemkind as isk";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "rgm.comp_id.rlCode = '~~'", "rgm.comp_id.gmCode = '~~'", "rgm.rmOpIdCreator = ~~", "rgm.rmOpIdModifier = ~~", "rl.rlStructurecode in ~~", "isk.iskCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setRlcode(String rlCode) {
		this.setField(0, rlCode);
	}

	public String getRlcode() {
		return this.getField(0);
	}

	public void setGmcode(String gmCode) {
		this.setField(1, gmCode);
	}

	public String getGmcode() {
		return this.getField(1);
	}

	public void setRmopidcreator(String rmOpIdCreator) {
		this.setField(2, rmOpIdCreator);
	}

	public String getRmopidcreator() {
		return this.getField(2);
	}

	public void setRmopidmodifier(String rmOpIdModifier) {
		this.setField(3, rmOpIdModifier);
	}

	public String getRmopidmodifier() {
		return this.getField(3);
	}

	public void setRlstructurecode(String rlStructurecode) {
		this.setField(4, rlStructurecode);
	}

	public String getRlstructurecode() {
		return this.getField(4);
	}

	public void setIskcode(String iskCode) {
		this.setField(5, iskCode);
	}

	public String getIskcode() {
		return this.getField(5);
	}

}
