package kyle.leis.eo.billing.payable.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class PayableQuery extends HGeneralQuery {
	
	
	public PayableQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.billing.payable.da.PayableColumns(py.pyId,py.pyCreatedate,py.pyModifydate,py.brId,py.pyUnitprice,py.pyUnitnumber,py.pyAuditdate,py.pyCurrencyrate,py.pyTotal,py.pyActualtotal,py.epCode,py.epvId,py.pyOccurdate,py.pyCommissionrate,py.pyIdReference,py.pyRemark,ck.ckCode,ck.ckName,chn.chnCode,chn.chnName,chn.chnSname,chn.chnSename,mop.opId,mop.opName,cop.opId,cop.opName,aop.opId,aop.opName,fk.fkCode,fk.fkName,co.coCode,co.coName,co.coSname,co.coSename,cw.cwCode,bk.bkCode,bk.bkName,fs.fsCode,fs.fsName,spy.spyId) FROM TblPayable as py left join py.tfiServerpayable as spy inner join py.tdiCurrencykind as ck inner join py.tchnChannel as chn inner join py.tdiOperatorByPyOpIdModifier as mop inner join py.tdiOperatorByPyOpIdCreator as cop left join py.tdiOperatorByPyOpIdAuditor as aop inner join py.tdiFeekind as fk inner join py.tcoCorporation as co inner join py.topCorewaybill as cw inner join py.tdiBillingkind as bk inner join py.tdiFeestatus as fs";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cwCode = ~~", "py.pyId = ~~", "cw.cwCustomerewbcode = '~~'", "cw.cwServerewbcode = '~~'", "cw.cwEwbcode = '~~'", "co.coCode = '~~'", "py.pyOccurdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= py.pyOccurdate", "fs.fsCode IN (~~)", "fk.fkCode IN (~~)", "bk.bkCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setPyid(String pyId) {
		this.setField(1, pyId);
	}

	public String getPyid() {
		return this.getField(1);
	}

	public void setCwcustomerewbcode(String cwCustomerewbcode) {
		this.setField(2, cwCustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(2);
	}

	public void setCwserverewbcode(String cwServerewbcode) {
		this.setField(3, cwServerewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(3);
	}

	public void setCwewbcode(String cwEwbcode) {
		this.setField(4, cwEwbcode);
	}

	public String getCwewbcode() {
		return this.getField(4);
	}

	public void setCocode(String coCode) {
		this.setField(5, coCode);
	}

	public String getCocode() {
		return this.getField(5);
	}

	public void setStartpyoccurdate(String StartPyOccurdate) {
		this.setField(6, StartPyOccurdate);
	}

	public String getStartpyoccurdate() {
		return this.getField(6);
	}

	public void setEndpyoccurdate(String EndPyOccurdate) {
		this.setField(7, EndPyOccurdate);
	}

	public String getEndpyoccurdate() {
		return this.getField(7);
	}

	public void setFscode(String fsCode) {
		this.setField(8, fsCode);
	}

	public String getFscode() {
		return this.getField(8);
	}

	public void setFkcode(String fkCode) {
		this.setField(9, fkCode);
	}

	public String getFkcode() {
		return this.getField(9);
	}

	public void setBkcode(String bkCode) {
		this.setField(10, bkCode);
	}

	public String getBkcode() {
		return this.getField(10);
	}	
	
	
}
