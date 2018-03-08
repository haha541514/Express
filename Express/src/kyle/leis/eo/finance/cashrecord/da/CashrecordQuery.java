package kyle.leis.eo.finance.cashrecord.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CashrecordQuery extends HGeneralQuery {
	
	public CashrecordQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.finance.cashrecord.da.CashrecordColumns(cr.crId,cr.crCurrencyrate,cr.crTotal,cr.crLabelcode,cr.crOccurdate,cr.crCreatedate,cr.crModifydate,cr.crAuditdate,cr.crReceiptlabelcode,cr.crReceiptprinttime,cr.crRemark,pt.ptCode,pt.ptName,crs.crsCode,crs.crsName,ck.ckCode,ck.ckName,cop.opId,cop.opName,aop.opId,aop.opName,mop.opId,mop.opName,ee.eeCode,ee.eeName,ee.eeSname,ee.eeEsname,co.coCode,co.coName,co.coSname,co.coSename,crk.crkCode,crk.crkName,wo.woId) FROM TfiCashrecord as cr inner join cr.tdiPaymenttype as pt inner join cr.tdiCashrecordstatus as crs inner join cr.tdiCurrencykind as ck inner join cr.tdiOperatorByCrOpIdCreator as cop left join cr.tdiOperatorByCrOpIdAuditor as aop inner join cr.tdiOperatorByCrOpIdModifier as mop inner join cr.tdiEnterpriseelement as ee inner join cr.tcoCorporation as co left join co.tcoCustomer as cm inner join cr.tdiCashrecordkind as crk left join cr.tfiWriteoff as wo";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cr.crId = ~~", "cr.crLabelcode = '~~'", "cr.crOccurdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= cr.crOccurdate", "crs.crsCode in (~~)", "crs.crsCode not in (~~)", "cr.crReceiptlabelcode = '~~'", "cr.crRemark like '%~~%'", "pt.ptCode = '~~'", "ck.ckCode = '~~'", "ee.eeCode = '~~'", "co.coCode in (~~)", "crk.crkCode = '~~'", "wo.woId = ~~", "(co.coCarryoversign='~~' OR co.coCarryoverdate >= cr.crOccurdate)", "(co.coCarryoversign='~~' AND cr.crOccurdate >= co.coCarryoverdate)", "ee.eeStructurecode like '~~%'", "cm.tdiOperatorByCmOpIdSale.opId = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCrid(String crId) {
		this.setField(0, crId);
	}

	public String getCrid() {
		return this.getField(0);
	}

	public void setCrlabelcode(String crLabelcode) {
		this.setField(1, crLabelcode);
	}

	public String getCrlabelcode() {
		return this.getField(1);
	}

	public void setStartoccurdate(String StartOccurdate) {
		this.setField(2, StartOccurdate);
	}

	public String getStartoccurdate() {
		return this.getField(2);
	}

	public void setEndoccurdate(String EndOccurdate) {
		this.setField(3, EndOccurdate);
	}

	public String getEndoccurdate() {
		return this.getField(3);
	}

	public void setCrscode(String crsCode) {
		this.setField(4, crsCode);
	}

	public String getCrscode() {
		return this.getField(4);
	}

	public void setNotincrscode(String NotInCrsCode) {
		this.setField(5, NotInCrsCode);
	}

	public String getNotincrscode() {
		return this.getField(5);
	}

	public void setCrreceiptlabelcode(String crReceiptlabelcode) {
		this.setField(6, crReceiptlabelcode);
	}

	public String getCrreceiptlabelcode() {
		return this.getField(6);
	}

	public void setCrremark(String crRemark) {
		this.setField(7, crRemark);
	}

	public String getCrremark() {
		return this.getField(7);
	}

	public void setPtcode(String ptCode) {
		this.setField(8, ptCode);
	}

	public String getPtcode() {
		return this.getField(8);
	}

	public void setCkcode(String ckCode) {
		this.setField(9, ckCode);
	}

	public String getCkcode() {
		return this.getField(9);
	}

	public void setEecode(String eeCode) {
		this.setField(10, eeCode);
	}

	public String getEecode() {
		return this.getField(10);
	}

	public void setCocode(String coCode) {
		this.setField(11, coCode);
	}

	public String getCocode() {
		return this.getField(11);
	}

	public void setCrkcode(String crkCode) {
		this.setField(12, crkCode);
	}

	public String getCrkcode() {
		return this.getField(12);
	}

	public void setWoid(String woId) {
		this.setField(13, woId);
	}

	public String getWoid() {
		return this.getField(13);
	}

	public void setBegincarryoversign(String Begincarryoversign) {
		this.setField(14, Begincarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(14);
	}

	public void setEndcarryoversigin(String Endcarryoversigin) {
		this.setField(15, Endcarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(15);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(16, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(16);
	}

	public void setOpidsale(String OpIDSale) {
		this.setField(17, OpIDSale);
	}

	public String getOpidsale() {
		return this.getField(17);
	}

}
