package kyle.leis.fs.authority.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class RolemenusQuery extends HGeneralQuery {
	
	public RolemenusQuery(){
	    m_strSelectClause = "select distinct new kyle.leis.fs.authority.da.RolemenusColumns(gm.gmCode, gm.gmName, gm.gmParameter, gm.gmLink, gm.gmIcon, gm.gmLevel, gm.gmStructurecode, gm.gmShortcutkey, gm.gmGroupcode, gm.gmMaxusecount, gm.gmShowtoolbar, gos.gosCode, gm.gmPinyinname) FROM TfsRolemenu as rm inner join rm.tfsGuimenu as gm inner join rm.tfsRole as rl inner join gm.tdiGuiopenstyle as gos";
	    m_strWhereClause = "gm.tdiInfomationsystemkind.iskCode = 'LEDIS'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "rl.rlCode in (~~)", "rl.rlCode = (~~)" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	} 

}
