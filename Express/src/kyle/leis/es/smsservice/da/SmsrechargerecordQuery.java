package kyle.leis.es.smsservice.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class SmsrechargerecordQuery extends HGeneralQuery {
	
	public SmsrechargerecordQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.smsservice.da.SmsrechargerecordColumns(srr.comp_id.srrId, srr.srrAmount, srr.srrCreatedate, srr.srrOpNameCreate, srr.srrRemark, ss.ssId, ss.ssStartdate, ss.ssEnddate, ss.ssCreatedate, ss.ssModifydate, ss.ssOpNameCreate, ss.ssOpNameModify, ss.ssBalanceamount, ss.ssUnitprice, ss.ssSendtotal, cs.coCode, csco.coName, csco.coEname, csco.coSname, csco.coSename) FROM TesSmsrechargerecord as srr inner join srr.tesSmsservice as ss inner join ss.tcoCustomer as cs inner join cs.tcoCorporation as csco";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "srr.comp_id.srrId = ~~", "ss.ssId = ~~", "cs.coCode = '~~'", "srr.srrCreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= srr.srrCreatedate" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setSrrid(String srrId) {
		this.setField(0, srrId);
	}

	public String getSrrid() {
		return this.getField(0);
	}

	public void setSsid(String ssId) {
		this.setField(1, ssId);
	}

	public String getSsid() {
		return this.getField(1);
	}

	public void setCocode(String coCode) {
		this.setField(2, coCode);
	}

	public String getCocode() {
		return this.getField(2);
	}

	public void setStartsrrcreatedate(String startsrrCreatedate) {
		this.setField(3, startsrrCreatedate);
	}

	public String getStartsrrcreatedate() {
		return this.getField(3);
	}

	public void setEndsrrcreatedate(String endsrrCreatedate) {
		this.setField(4, endsrrCreatedate);
	}

	public String getEndsrrcreatedate() {
		return this.getField(4);
	}

}
