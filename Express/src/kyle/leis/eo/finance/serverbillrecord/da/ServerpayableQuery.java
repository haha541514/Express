package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ServerpayableQuery extends HGeneralQuery {
	
	public ServerpayableQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.finance.serverbillrecord.da.ServerpayableColumns(spy.spyId,spy.spyRemark,fk.fkCode,fk.fkName,spy.spyTotalcharge,ck.ckCode,ck.ckName,spy.spyCurrencyrate,spy.spyOccurdate,sbr.sbrId,sbr.sbrLabelcode,swb.swbCode,swb.swbServerewbcode,swb.swbCustomerewbcode,swb.swbPieces,swb.swbChargeweight,swb.swbReferencecode,chn.chnCode,chn.chnSname,co.coCode,co.coSname) FROM TfiServerpayable as spy inner join spy.tdiFeekind as fk inner join spy.tdiCurrencykind as ck inner join spy.tfiServerbillrecord as sbr inner join spy.tfiServerwaybill as swb inner join swb.tchnChannel as chn inner join chn.tcoCorporation as co";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "spy.spyId = ~~", "fk.fkCode = '~~'", "sbr.sbrId = ~~", "swb.swbCode = ~~", "swb.swbServerewbcode = '~~'", "swb.swbCustomerewbcode = '~~'", "swb.swbReferencecode = ~~", "chn.chnCode = '~~'", "sbr.sbrLabelcode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setSpyid(String spyId) {
		this.setField(0, spyId);
	}

	public String getSpyid() {
		return this.getField(0);
	}

	public void setFkcode(String fkCode) {
		this.setField(1, fkCode);
	}

	public String getFkcode() {
		return this.getField(1);
	}

	public void setSbrid(String sbrId) {
		this.setField(2, sbrId);
	}

	public String getSbrid() {
		return this.getField(2);
	}

	public void setSwbcode(String swbCode) {
		this.setField(3, swbCode);
	}

	public String getSwbcode() {
		return this.getField(3);
	}

	public void setSbdserverewbcode(String sbdServerewbcode) {
		this.setField(4, sbdServerewbcode);
	}

	public String getSbdserverewbcode() {
		return this.getField(4);
	}

	public void setSbdcustomerewbcode(String sbdCustomerewbcode) {
		this.setField(5, sbdCustomerewbcode);
	}

	public String getSbdcustomerewbcode() {
		return this.getField(5);
	}

	public void setSbdreferencecode(String sbdReferencecode) {
		this.setField(6, sbdReferencecode);
	}

	public String getSbdreferencecode() {
		return this.getField(6);
	}

	public void setChncode(String chnCode) {
		this.setField(7, chnCode);
	}

	public String getChncode() {
		return this.getField(7);
	}

	public void setSbrlabelcode(String sbrLabelcode) {
		this.setField(8, sbrLabelcode);
	}

	public String getSbrlabelcode() {
		return this.getField(8);
	}

}
