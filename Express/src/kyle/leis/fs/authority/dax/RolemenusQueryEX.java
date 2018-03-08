package kyle.leis.fs.authority.dax;

import kyle.leis.fs.authority.da.RolemenusQuery;

public class RolemenusQueryEX extends RolemenusQuery {
	public RolemenusQueryEX(String strIskcode){
		m_strWhereClause = "gm.tdiInfomationsystemkind.iskCode = '" + strIskcode + "'";
	}
}
