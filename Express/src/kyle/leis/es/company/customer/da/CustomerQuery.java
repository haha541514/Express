package kyle.leis.es.company.customer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CustomerQuery extends HGeneralQuery {
	
	public CustomerQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.company.customer.da.CustomerColumns(cm.coCode,cm.cmInvoicesign,cm.cmOdanoticesign,cm.cmOdaholdsign,co.coCreatedate,co.coModifydate,co.coAuditdate,co.opcodeConfirm,co.coConfirmdate,co.coName,co.coEname,co.coSname,co.coSename,co.coLabelcode,co.coAddress,co.coPostcode,co.coRemark,co.coWebsite,cst.cstCode,cst.cstName,ct.ctCode,ct.ctName,csop.opId,csop.opName,ssop.opId,ssop.opName,dunop.opId,dunop.opName,cm.cmCreditlimit,aop.opName,aop.opId,mop.opName,mop.opId,cop.opName,cop.opId,ee.eeCode,ee.eeSname,cos.csCode,cos.csName,cm.cmPayablebankaccount,co.coCarryoversign,co.coCarryoverdate,cm.cmWebtrackneedlogin,cm.cmMaxreceivabletotal,cm.cmMaxrttype,cm.cmAllowprintlabelsign,cm.cmTemporarycreditlimit,cm.cmTclstartdate,cm.cmTclenddate,cm.cmPrintchildlabelsign,cm.cmStructruecode,cmparent.coCode,cm.cmHoldhwrate,cm.cmWebinputchangeswbsign,cm.cmArrearprintlabelsign) FROM TcoCustomer as cm inner join cm.tcoCorporation as co inner join co.tdiCustomersuppliertype as cst left join co.tdiOperatorByCoOpIdAudit as aop inner join co.tdiOperatorByCoOpIdModify as mop inner join co.tdiOperatorByCoOpIdCreate as cop inner join co.tdiEnterpriseelement as ee inner join co.tdiCorporationstatus as cos inner join cm.tdiCustomertype as ct left join cm.tdiOperatorByCmOpIdCservice as csop left join cm.tdiOperatorByCmOpIdSale as ssop left join cm.tdiOperatorByCmOpIdDun as dunop left join cm.tcoCustomer as cmparent";
	    m_strWhereClause = "cst.cstCode = 'C'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cm.coCode = '~~'", "co.coName = '~~'", "co.coEname = '~~'", "co.coSname = '~~'", "co.coSename = '~~'", "co.coLabelcode = '~~'", "ct.ctCode = '~~'", "csop.opId = ~~", "ssop.opId = ~~", "dunop.opId = ~~", "cos.csCode in (~~)", "cos.csCode not in (~~)", "(exists (select br.brId from TfiBillrecord br where br.tcoCorporation.coCode = cm.coCode and br.tdiBillrecordstatus.brsCode in (~~))  or exists (select cr.crId from TfiCashrecord cr where cr.tcoCorporation.coCode = cm.coCode and cr.tdiCashrecordstatus.crsCode in (~~)))", "(co.coCarryoversign='~~' OR co.coCarryoverdate >= sysdate)", "(co.coCarryoversign='~~' AND sysdate >= co.coCarryoverdate)", "cmparent.coCode = '~~'", "ee.eeStructurecode like '~~%'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1 };		
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

	public void setOpidservice(String opidService) {
		this.setField(7, opidService);
	}

	public String getOpidservice() {
		return this.getField(7);
	}

	public void setOpidsales(String opidSales) {
		this.setField(8, opidSales);
	}

	public String getOpidsales() {
		return this.getField(8);
	}

	public void setOpiddunner(String opidDunner) {
		this.setField(9, opidDunner);
	}

	public String getOpiddunner() {
		return this.getField(9);
	}

	public void setIncscode(String InCsCode) {
		this.setField(10, InCsCode);
	}

	public String getIncscode() {
		return this.getField(10);
	}

	public void setNotincscode(String NotInCsCode) {
		this.setField(11, NotInCsCode);
	}

	public String getNotincscode() {
		return this.getField(11);
	}

	public void setBrscode(String brsCode) {
		this.setField(12, brsCode);
	}

	public String getBrscode() {
		return this.getField(12);
	}
	public void setCrscode(String crsCode) {
		this.setField(13, crsCode);
	}

	public String getCrscode() {
		return this.getField(13);
	}

	public void setBegincarryoversign(String BeginCarryoversign) {
		this.setField(14, BeginCarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(14);
	}

	public void setEndcarryoversigin(String EndCarryoversigin) {
		this.setField(15, EndCarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(15);
	}

	public void setCmparent(String cmparent) {
		this.setField(16, cmparent);
	}

	public String getCmparent() {
		return this.getField(16);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(17, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(17);
	}

}
