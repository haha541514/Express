package kyle.leis.fs.authority.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class UserroleQuery extends HGeneralQuery {
	
	public UserroleQuery(){
	    m_strSelectClause = "select new kyle.leis.fs.authority.da.UserroleColumns(rl.rlCode, rl.rlName, rl.rlEname, rl.rlAdministratorsign, rl.rlOpIdCreator, rl.rlCreatedate, rl.rlOpIdModifier, rl.rlModifydate, rl.rlStructurecode, rl.rlRemark, ur.comp_id.urUsercode, isk.iskName, isk.iskEname, isk.iskCode) FROM TfsUserrole as ur inner join ur.tfsRole as rl inner join ur.tdiInfomationsystemkind as isk";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "isk.iskCode = '~~'", "ur.comp_id.urUsercode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	} 

}
