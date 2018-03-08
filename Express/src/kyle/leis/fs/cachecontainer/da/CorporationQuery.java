package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CorporationQuery extends HGeneralQuery {
	
	public CorporationQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.CorporationColumns(co.coCode, co.coSname, co.coSename, cst.cstCode, cs.csCode, co.coCarryoversign, co.coCarryoverdate, ee.eeStructurecode) FROM TcoCorporation as co inner join co.tdiCustomersuppliertype as cst inner join co.tdiCorporationstatus as cs left join co.tdiEnterpriseelement as ee";
	    m_strWhereClause = "cs.csCode = 'R'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
