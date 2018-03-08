package kyle.leis.eo.finance.billrecord.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class BillrecordQuery extends HGeneralQuery {
	
	public BillrecordQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.finance.billrecord.da.BillrecordColumns(br.brId,br.brCreatedate,br.brModifydate,br.brAuditdate,br.brOccurdate,br.brTotal,br.brLablecode,br.brRemark,br.brDownloadtimes,br.brPrinttimes,ck.ckCode,ck.ckName,chn.chnCode,chn.chnName,chn.chnSname,mop.opId,mop.opName,cop.opId,cop.opName,aop.opId,aop.opName,ee.eeCode,ee.eeName,ee.eeSname,ee.eeEsname,co.coCode,co.coName,co.coSname,co.coSename,brs.brsCode,brs.brsName,bk.bkCode,bk.bkName,sbr.sbrId,sbr.sbrLabelcode,br.brAgencyfeetotal) FROM TfiBillrecord as br inner join br.tdiCurrencykind as ck left join br.tchnChannel as chn inner join br.tdiOperatorByBrOpIdModifier as mop inner join br.tdiOperatorByBrOpIdCreator as cop left join br.tdiOperatorByBrOpIdAuditor as aop inner join br.tdiEnterpriseelement as ee inner join br.tcoCorporation as co inner join br.tdiBillrecordstatus as brs inner join br.tdiBillingkind as bk left join br.tfiWriteoff as wo left join br.tfiServerbillrecord as sbr";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "br.brId = ~~", "br.brLablecode = '~~'", "br.brOccurdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= br.brOccurdate", "brs.brsCode in (~~)", "brs.brsCode not in (~~)", "ee.eeCode = '~~'", "co.coCode = '~~'", "bk.bkCode = '~~'", "wo.woId = ~~", "(co.coCarryoversign='~~' OR co.coCarryoverdate >= br.brOccurdate)", "(co.coCarryoversign='~~' AND br.brOccurdate >= co.coCarryoverdate)", "ee.eeStructurecode like '~~%'", "sbr.sbrId = ~~", "sbr.sbrLabelcode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setBrlablecode(String brLablecode) {
		this.setField(1, brLablecode);
	}

	public String getBrlablecode() {
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

	public void setBrscode(String brsCode) {
		this.setField(4, brsCode);
	}

	public String getBrscode() {
		return this.getField(4);
	}

	public void setNotinbrscode(String NotInBrsCode) {
		this.setField(5, NotInBrsCode);
	}

	public String getNotinbrscode() {
		return this.getField(5);
	}

	public void setEecode(String eeCode) {
		this.setField(6, eeCode);
	}

	public String getEecode() {
		return this.getField(6);
	}

	public void setCocode(String coCode) {
		this.setField(7, coCode);
	}

	public String getCocode() {
		return this.getField(7);
	}

	public void setBkcode(String bkCode) {
		this.setField(8, bkCode);
	}

	public String getBkcode() {
		return this.getField(8);
	}

	public void setWoid(String woId) {
		this.setField(9, woId);
	}

	public String getWoid() {
		return this.getField(9);
	}

	public void setBegincarryoversign(String Begincarryoversign) {
		this.setField(10, Begincarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(10);
	}

	public void setEndcarryoversigin(String Endcarryoversigin) {
		this.setField(11, Endcarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(11);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(12, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(12);
	}

	public void setSbrid(String sbrId) {
		this.setField(13, sbrId);
	}

	public String getSbrid() {
		return this.getField(13);
	}

	public void setSbrlabelcode(String sbrLabelcode) {
		this.setField(14, sbrLabelcode);
	}

	public String getSbrlabelcode() {
		return this.getField(14);
	}

}
