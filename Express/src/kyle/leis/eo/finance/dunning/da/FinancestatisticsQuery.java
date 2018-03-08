package kyle.leis.eo.finance.dunning.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class FinancestatisticsQuery extends HGeneralQuery {
	
	public FinancestatisticsQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.finance.dunning.da.FinancestatisticsColumns(fs.comp_id.coCode,fs.fsReceivableamount,fs.fsReceivableso,fs.fsReceivableunsohold,fs.fsPayableso,fs.fsPayableunsohold,fs.fsPayableamount,fs.fsBalanceamount,co.coCode,co.coName,co.coSname,co.coSename,co.coLabelcode,cm.cmCreditlimit,ck.ckCode,ck.ckName,cm.cmTemporarycreditlimit,cm.cmTclstartdate,cm.cmTclenddate,fs.fsLatesthawbcreatedate,fs.fsBybalanceamount,ee.eeSname,cm.cmHoldhwrate) FROM TcoFinancialstatistic as fs inner join fs.tcoCorporation as co inner join co.tcoCustomer as cm inner join fs.tdiCurrencykind as ck inner join co.tdiEnterpriseelement as ee";
	    m_strWhereClause = "co.tdiCorporationstatus.csCode != 'E'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "co.coCode in (~~)", "co.coName like '%~~%'", "co.coSname like '%~~%'", "co.coLabelcode in (~~)", "(co.coCarryoversign='~~' OR co.coCarryoverdate >= sysdate)", "(co.coCarryoversign='~~' AND sysdate >= co.coCarryoverdate)", "fs.comp_id.fsCarryoverenterprise = '~~'", "cm.tdiCustomertype.ctCode = '~~'", "cm.tdiOperatorByCmOpIdDun.opId = ~~", "fs.fsLatesthawbcreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') > fs.fsLatesthawbcreatedate", "cm.tdiOperatorByCmOpIdSale.opId = ~~", "ee.eeStructurecode like '~~%'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
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

	public void setCosname(String coSname) {
		this.setField(2, coSname);
	}

	public String getCosname() {
		return this.getField(2);
	}

	public void setColabelcode(String coLabelcode) {
		this.setField(3, coLabelcode);
	}

	public String getColabelcode() {
		return this.getField(3);
	}

	public void setBegincarryoversign(String BeginCarryoversign) {
		this.setField(4, BeginCarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(4);
	}

	public void setEndcarryoversigin(String EndCarryoversigin) {
		this.setField(5, EndCarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(5);
	}

	public void setFscarryoverenterprise(String fsCarryoverenterprise) {
		this.setField(6, fsCarryoverenterprise);
	}

	public String getFscarryoverenterprise() {
		return this.getField(6);
	}

	public void setCtcode(String ctCode) {
		this.setField(7, ctCode);
	}

	public String getCtcode() {
		return this.getField(7);
	}

	public void setDunoperid(String DunOperId) {
		this.setField(8, DunOperId);
	}

	public String getDunoperid() {
		return this.getField(8);
	}

	public void setStartdate(String startdate) {
		this.setField(9, startdate);
	}

	public String getStartdate() {
		return this.getField(9);
	}

	public void setEnddate(String enddate) {
		this.setField(10, enddate);
	}

	public String getEnddate() {
		return this.getField(10);
	}

	public void setSaleoperid(String saleoperid) {
		this.setField(11, saleoperid);
	}

	public String getSaleoperid() {
		return this.getField(11);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(12, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(12);
	}

}
