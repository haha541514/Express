package kyle.leis.fs.authoritys.userrole.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class UserroleQuery extends HGeneralQuery {
	
	public UserroleQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.authoritys.userrole.da.UserroleColumns(ul.comp_id.urUsercode,rl.rlCode, rl.rlName, rl.rlEname, rl.rlAdministratorsign, rl.rlOpIdCreator, rl.rlCreatedate, rl.rlOpIdModifier, rl.rlModifydate, rl.rlStructurecode, rl.rlRemark, isk.iskName, isk.iskEname, isk.iskCode) FROM TfsUserrole as ul inner join ul.tfsRole as rl inner join ul.tdiInfomationsystemkind as isk";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "isk.iskCode = '~~'", "ul.comp_id.urUsercode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setIsk_code(String isk_code) {
		this.setField(0, isk_code);
	}

	public String getIsk_code() {
		return this.getField(0);
	}

	public void setUrusercode(String urUsercode) {
		this.setField(1, urUsercode);
	}

	public String getUrusercode() {
		return this.getField(1);
	}

}
