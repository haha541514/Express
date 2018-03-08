package kyle.leis.fs.dictionary.enterpriseelement.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class DistributioncenterQuery extends HGeneralQuery {
	
	public DistributioncenterQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.enterpriseelement.da.DistributioncenterColumns(dc.eeCode, dcee.eeCode, dcee.eeLevel, dcee.eeStructurecode, dcee.eeName, dcee.eeEname, dcee.eeSname, dcee.eeEsname, dcee.eeAddress, dcee.eeEaddress, dcee.eePostcode, dcee.eeEmail, dcee.eeTelephone, dcee.eeFax, dcee.eeOpIdCreator, dcee.eeCreatedate, dcee.eeOpIdModifier, dcee.eeModifydate, dceedt.dtCode, dceedt.dtName, dceedt.dtEname, dceerg.rgCode, dceerg.rgName, dceerg.rgEname, dceeeek.eekCode, dceeeek.eekName, dceeeek.eekEname, dcdtb.dtCode, dcdtb.dtName, dcdtb.dtEname, dcbr.eeCode, dcbr.brOpIdManager, dcbr.brOpIdCustomerservice, dcbr.brOpIdDunner, dcbr.brOpIdSaler,st.stCode,st.stName,ct.ctCode,ct.ctName) FROM TdiDistributioncenter as dc inner join dc.tdiEnterpriseelement as dcee inner join dc.tdiDistrict as dcdtb left join dc.tdiBranch as dcbr inner join dcee.tdiDistrict as dceedt inner join dcee.tdiRegion as dceerg inner join dcee.tdiEnterpriseelementkind as dceeeek left join dcee.tdiState as st left join dcee.tdiCity as ct";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "dc.eeCode = '~~'", "dcbr.eeCode = '~~'", "dcdtb.dtCode = '~~'", "dceeeek.eekCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setDceecode(String Dceecode) {
		this.setField(0, Dceecode);
	}

	public String getDceecode() {
		return this.getField(0);
	}

	public void setDcbreecode(String Dcbreecode) {
		this.setField(1, Dcbreecode);
	}

	public String getDcbreecode() {
		return this.getField(1);
	}

	public void setDtbcode(String Dtbcode) {
		this.setField(2, Dtbcode);
	}

	public String getDtbcode() {
		return this.getField(2);
	}

	public void setEekcode(String Eekcode) {
		this.setField(3, Eekcode);
	}

	public String getEekcode() {
		return this.getField(3);
	}

}
