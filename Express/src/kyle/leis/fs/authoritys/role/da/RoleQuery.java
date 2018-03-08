package kyle.leis.fs.authoritys.role.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class RoleQuery extends HGeneralQuery {
	
	public RoleQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.authoritys.role.da.RoleColumns(rl.rlCode, rl.rlName, rl.rlEname, rl.rlAdministratorsign, rl.rlOpIdCreator, rl.rlCreatedate, rl.rlOpIdModifier, rl.rlModifydate, rl.rlStructurecode, rl.rlRemark, isk.iskName, isk.iskEname, isk.iskCode) FROM TfsRole as rl inner join rl.tdiInfomationsystemkind as isk";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "rl.rlCode = '~~'", "rl.rlName = '~~'", "rl.rlAdministratorsign = '~~'", "rl.rlOpIdCreator = ~~", "rl.rlCreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= rl.rlCreatedate ", "rl.rlOpIdModifier = ~~", "rl.rlModifydate >= to_date('~~','yyyy-mm-dd:hh24:mi:ss')", "to_date('~~','yyyy-mm-dd:hh24:mi:ss') >= rl.rlModifydate", "rl.rlStructurecode = '~~'", "isk.iskCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
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

	public void setRlname(String rlName) {
		this.setField(1, rlName);
	}

	public String getRlname() {
		return this.getField(1);
	}

	public void setRladministratorsign(String rlAdministratorsign) {
		this.setField(2, rlAdministratorsign);
	}

	public String getRladministratorsign() {
		return this.getField(2);
	}

	public void setRlopidcreator(String rlOpIdCreator) {
		this.setField(3, rlOpIdCreator);
	}

	public String getRlopidcreator() {
		return this.getField(3);
	}

	public void setStartrlcreatedate(String startRlCreatedate) {
		this.setField(4, startRlCreatedate);
	}

	public String getStartrlcreatedate() {
		return this.getField(4);
	}

	public void setEndrlcreatedate(String endRlCreatedate) {
		this.setField(5, endRlCreatedate);
	}

	public String getEndrlcreatedate() {
		return this.getField(5);
	}

	public void setRlopidmodifier(String rlOpIdModifier) {
		this.setField(6, rlOpIdModifier);
	}

	public String getRlopidmodifier() {
		return this.getField(6);
	}

	public void setStartrlmodifydate(String startRlModifydate) {
		this.setField(7, startRlModifydate);
	}

	public String getStartrlmodifydate() {
		return this.getField(7);
	}

	public void setEndrlmodifydate(String endRlModifydate) {
		this.setField(8, endRlModifydate);
	}

	public String getEndrlmodifydate() {
		return this.getField(8);
	}

	public void setRlstructurecode(String rlStructurecode) {
		this.setField(9, rlStructurecode);
	}

	public String getRlstructurecode() {
		return this.getField(9);
	}

	public void setIsk_code(String isk_code) {
		this.setField(10, isk_code);
	}

	public String getIsk_code() {
		return this.getField(10);
	}

}
