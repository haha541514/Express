package kyle.leis.es.smsservice.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class SmsmessageQuery extends HGeneralQuery {
	
	public SmsmessageQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.smsservice.da.SmsmessageColumns(sms.smsId, sms.smsContent, sms.smsMobilenumber, sms.smsReceivecocode, sms.smsCreatedate, sms.smsStatus, cs.coCode, csco.coName, csco.coEname, csco.coSname, csco.coSename) FROM TesSmsmessage as sms left join sms.tcoCustomer as cs left join cs.tcoCorporation as csco";
	    m_strWhereClause = "";
	    m_strOrderByClause = "sms.smsCreatedate";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "sms.smsId = ~~", "cs.coCode = '~~'", "sms.smsMobilenumber = '~~'", "sms.smsReceivecocode = '~~'", "sms.smsStatus IN (~~)", "sms.smsCreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= sms.smsCreatedate" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setSmsid(String smsId) {
		this.setField(0, smsId);
	}

	public String getSmsid() {
		return this.getField(0);
	}

	public void setCocode(String coCode) {
		this.setField(1, coCode);
	}

	public String getCocode() {
		return this.getField(1);
	}

	public void setSmsmobilenumber(String smsMobilenumber) {
		this.setField(2, smsMobilenumber);
	}

	public String getSmsmobilenumber() {
		return this.getField(2);
	}

	public void setSmsreceivecocode(String smsReceivecocode) {
		this.setField(3, smsReceivecocode);
	}

	public String getSmsreceivecocode() {
		return this.getField(3);
	}

	public void setSmsstatus(String smsStatus) {
		this.setField(4, smsStatus);
	}

	public String getSmsstatus() {
		return this.getField(4);
	}

	public void setStartsmscreatedate(String startsmsCreatedate) {
		this.setField(5, startsmsCreatedate);
	}

	public String getStartsmscreatedate() {
		return this.getField(5);
	}

	public void setEndsmscreatedate(String endsmsCreatedate) {
		this.setField(6, endsmsCreatedate);
	}

	public String getEndsmscreatedate() {
		return this.getField(6);
	}

}
