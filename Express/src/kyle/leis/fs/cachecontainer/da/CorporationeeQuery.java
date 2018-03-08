package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CorporationeeQuery extends HGeneralQuery {
	
	public CorporationeeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.CorporationeeColumns(co.coCode, co.coSname, co.coSename, cst.cstCode, cs.csCode) FROM TcoCorporation as co inner join co.tdiCustomersuppliertype as cst inner join co.tdiCorporationstatus as cs left join co.tdiEnterpriseelement as ee";
	    m_strWhereClause = "cs.csCode = 'R'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "ee.eeStructurecode like '~~%'", "co.ccBatchnumber > '~~'", "'~~' >= co.ccBatchnumber" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setEestructurecode(String eeStructurecode) {
		this.setField(0, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(0);
	}

	public void setStartbatchnumber(String startBatchnumber) {
		this.setField(1, startBatchnumber);
	}

	public String getStartbatchnumber() {
		return this.getField(1);
	}

	public void setEndbatchnumber(String EndBatchnumber) {
		this.setField(2, EndBatchnumber);
	}

	public String getEndbatchnumber() {
		return this.getField(2);
	}

}
