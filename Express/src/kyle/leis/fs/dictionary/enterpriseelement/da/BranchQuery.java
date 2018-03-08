package kyle.leis.fs.dictionary.enterpriseelement.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class BranchQuery extends HGeneralQuery {
	
	public BranchQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.enterpriseelement.da.BranchColumns(br.eeCode, br.brOpIdManager, br.brOpIdCustomerservice, br.brOpIdDunner, br.brOpIdSaler, ee.eeCode, ee.eeLevel, ee.eeStructurecode, ee.eeName, ee.eeEname, ee.eeSname, ee.eeEsname, ee.eeAddress, ee.eeEaddress, ee.eePostcode, ee.eeEmail, ee.eeTelephone, ee.eeFax, ee.eeOpIdCreator, ee.eeCreatedate, ee.eeOpIdModifier, ee.eeModifydate, dt.dtCode, dt.dtName, dt.dtEname, dt.dtStatecode, dt.dtStatename, dt.dtGrade, rg.rgCode, rg.rgName, rg.rgEname, eek.eekCode, eek.eekName, eek.eekEname, st.stCode,st.stName,ct.ctCode,ct.ctName) FROM TdiBranch as br inner join br.tdiEnterpriseelement as ee inner join ee.tdiDistrict as dt inner join ee.tdiRegion as rg inner join ee.tdiEnterpriseelementkind as eek left join ee.tdiState as st left join ee.tdiCity as ct";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "br.eeCode = '~~'", "ee.eeCode = '~~'", "br.brOpIdManager = ~~", "br.brOpIdCustomerservice = ~~", "br.brOpIdDunner = ~~", "br.brOpIdSaler = ~~", "eek.eekCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBreecode(String Breecode) {
		this.setField(0, Breecode);
	}

	public String getBreecode() {
		return this.getField(0);
	}

	public void setEeeecode(String Eeeecode) {
		this.setField(1, Eeeecode);
	}

	public String getEeeecode() {
		return this.getField(1);
	}

	public void setBropidmanager(String Bropidmanager) {
		this.setField(2, Bropidmanager);
	}

	public String getBropidmanager() {
		return this.getField(2);
	}

	public void setBropidcustomerservice(String Bropidcustomerservice) {
		this.setField(3, Bropidcustomerservice);
	}

	public String getBropidcustomerservice() {
		return this.getField(3);
	}

	public void setBropiddunner(String Bropiddunner) {
		this.setField(4, Bropiddunner);
	}

	public String getBropiddunner() {
		return this.getField(4);
	}

	public void setBropidsaler(String Bropidsaler) {
		this.setField(5, Bropidsaler);
	}

	public String getBropidsaler() {
		return this.getField(5);
	}

	public void setEekcode(String Eekcode) {
		this.setField(6, Eekcode);
	}

	public String getEekcode() {
		return this.getField(6);
	}

}
