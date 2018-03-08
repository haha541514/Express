package kyle.leis.es.smsservice.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class SmsreceiveruleQuery extends HGeneralQuery {
	
	public SmsreceiveruleQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.smsservice.da.SmsreceiveruleColumns(smsr.comp_id.snkCode, sr.opId, sr.opCode, sr.opEname, sr.opSname, sr.opName, snk.snkName, snk.snkEname, sntt.snttCode, sntt.snttName, sntt.snttEname, sntt.snttStarttime, sntt.snttEndtime, sntt.snttRestrictsign, mnk.mnkCode, mnk.mnkName, sree.eeSname,sr.opMobile,srco.coCode,srco.coName) FROM TcoSmsreceiverule as smsr inner join smsr.tdiOperator as sr inner join smsr.tdiSmsnoticekind as snk inner join smsr.tdiSmsnoticetimetype as sntt inner join smsr.tdiMessagenoticekind as mnk inner join sr.tdiEnterpriseelement as sree inner join sr.tcoCorporation as srco";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "smsr.comp_id.snkCode = '~~'", "sr.opId = ~~", "sr.opMobile = '~~'", "srco.coCode = '~~'", "sntt.snttCode = '~~'", "sntt.snttRestrictsign = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setSnkcode(String snkCode) {
		this.setField(0, snkCode);
	}

	public String getSnkcode() {
		return this.getField(0);
	}

	public void setOpid(String opId) {
		this.setField(1, opId);
	}

	public String getOpid() {
		return this.getField(1);
	}

	public void setOpmobile(String Opmobile) {
		this.setField(2, Opmobile);
	}

	public String getOpmobile() {
		return this.getField(2);
	}

	public void setCocode(String Cocode) {
		this.setField(3, Cocode);
	}

	public String getCocode() {
		return this.getField(3);
	}

	public void setSnttcode(String snttCode) {
		this.setField(4, snttCode);
	}

	public String getSnttcode() {
		return this.getField(4);
	}

	public void setSnttrestrictsign(String snttRestrictsign) {
		this.setField(5, snttRestrictsign);
	}

	public String getSnttrestrictsign() {
		return this.getField(5);
	}

}
