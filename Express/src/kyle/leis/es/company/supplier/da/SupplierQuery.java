package kyle.leis.es.company.supplier.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class SupplierQuery extends HGeneralQuery {
	
	public SupplierQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.company.supplier.da.SupplierColumns(sp.coCode,sp.spAccount,sp.spManifestseriesnumber,co.coCreatedate,co.coModifydate,co.coAuditdate,co.opcodeConfirm,co.coConfirmdate,co.coName,co.coEname,co.coSname,co.coSename,co.coLabelcode,co.coAddress,co.coPostcode,co.coRemark,co.coWebsite,cst.cstCode,cst.cstName,aop.opName,aop.opId,mop.opName,mop.opId,cop.opName,cop.opId,ee.eeCode,ee.eeSname,cos.csCode,cos.csName,co.coCarryoversign,co.coCarryoverdate) FROM TcoSupplier as sp inner join sp.tcoCorporation as co inner join co.tdiCustomersuppliertype as cst left join co.tdiOperatorByCoOpIdAudit as aop inner join co.tdiOperatorByCoOpIdModify as mop inner join co.tdiOperatorByCoOpIdCreate as cop inner join co.tdiEnterpriseelement as ee inner join co.tdiCorporationstatus as cos";
	    m_strWhereClause = "cst.cstCode = 'S'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "sp.coCode = '~~'", "co.coName = '~~'", "co.coEname = '~~'", "co.coSname = '~~'", "co.coSename = '~~'", "co.coLabelcode = '~~'", "ct.ctCode = '~~'", "cos.csCode in (~~)", "cos.csCode not in (~~)", "(exists (select br.brId from TfiBillrecord br where br.tcoCorporation.coCode = sp.coCode and br.tdiBillrecordstatus.brsCode in (~~))  or exists (select cr.crId from TfiCashrecord cr where cr.tcoCorporation.coCode = sp.coCode and cr.tdiCashrecordstatus.crsCode in (~~)))", "(co.coCarryoversign='~~' OR co.coCarryoverdate >= sysdate)", "(co.coCarryoversign='~~' AND sysdate >= co.coCarryoverdate)" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCocode(String coCode) {
		this.setField(0, coCode);
	}

	public String getCocode() {
		return this.getField(0);
	}

	public void setConame(String coName) {
		this.setField(1, coName);
	}

	public String getConame() {
		return this.getField(1);
	}

	public void setCoename(String coEname) {
		this.setField(2, coEname);
	}

	public String getCoename() {
		return this.getField(2);
	}

	public void setCosname(String coSname) {
		this.setField(3, coSname);
	}

	public String getCosname() {
		return this.getField(3);
	}

	public void setCosename(String coSename) {
		this.setField(4, coSename);
	}

	public String getCosename() {
		return this.getField(4);
	}

	public void setColabelcode(String coLabelcode) {
		this.setField(5, coLabelcode);
	}

	public String getColabelcode() {
		return this.getField(5);
	}

	public void setCtcode(String ctCode) {
		this.setField(6, ctCode);
	}

	public String getCtcode() {
		return this.getField(6);
	}

	public void setIncscode(String InCscode) {
		this.setField(7, InCscode);
	}

	public String getIncscode() {
		return this.getField(7);
	}

	public void setNotincscode(String NotInCscode) {
		this.setField(8, NotInCscode);
	}

	public String getNotincscode() {
		return this.getField(8);
	}

	public void setBrscode(String brsCode) {
		this.setField(9, brsCode);
	}

	public String getBrscode() {
		return this.getField(9);
	}
	public void setCrscode(String crsCode) {
		this.setField(10, crsCode);
	}

	public String getCrscode() {
		return this.getField(10);
	}

	public void setBegincarryoversign(String BeginCarryoversign) {
		this.setField(11, BeginCarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(11);
	}

	public void setEndcarryoversigin(String EndCarryoversigin) {
		this.setField(11, EndCarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(11);
	}

}
