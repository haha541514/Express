package kyle.leis.eo.billing.receivable.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ReceivableQuery extends HGeneralQuery {
	
	public ReceivableQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.billing.receivable.da.ReceivableColumns(rv.rvId,rv.rvCreatedate,rv.rvModifydate,rv.rvAuditdate,rv.brId,rv.rvUnitprice,rv.rvUnitnumber,rv.rvCurrencyrate,rv.rvTotal,rv.rvActualtotal,rv.epCode,rv.epvId,rv.rvOccurdate,rv.rvCommissionrate,rv.rvIdReference,rv.rvRemark,ck.ckCode,ck.ckName,chn.chnCode,chn.chnName,chn.chnSname,chn.chnSename,mop.opId,mop.opName,cop.opId,cop.opName,aop.opId,aop.opName,fk.fkCode,fk.fkName,co.coCode,co.coName,co.coSname,co.coLabelcode,cw.cwCode,bk.bkCode,bk.bkName,fs.fsCode,fs.fsName) FROM TblReceivable as rv inner join rv.tdiCurrencykind as ck left join rv.tchnChannel as chn inner join rv.tdiOperatorByRvOpIdModifier as mop inner join rv.tdiOperatorByRvOpIdCreator as cop left join rv.tdiOperatorByRvOpIdAuditor as aop inner join rv.tdiFeekind as fk inner join rv.tcoCorporation as co inner join rv.topCorewaybill as cw inner join rv.tdiBillingkind as bk inner join rv.tdiFeestatus as fs";
	    m_strWhereClause = "bk.bkCode = 'A0101'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cwCode = ~~", "rv.rvId = ~~", "cw.cwCustomerewbcode = '~~'", "cw.cwServerewbcode = '~~'", "cw.cwEwbcode = '~~'", "rv.rvOccurdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= rv.rvOccurdate", "fs.fsCode IN (~~)", "fk.fkCode IN (~~)" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
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

	public void setRvid(String rvId) {
		this.setField(1, rvId);
	}

	public String getRvid() {
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

	public void setStartoccurdate(String StartOccurdate) {
		this.setField(5, StartOccurdate);
	}

	public String getStartoccurdate() {
		return this.getField(5);
	}

	public void setEndoccurdate(String EndOccurdate) {
		this.setField(6, EndOccurdate);
	}

	public String getEndoccurdate() {
		return this.getField(6);
	}

	public void setFscode(String fsCode) {
		this.setField(7, fsCode);
	}

	public String getFscode() {
		return this.getField(7);
	}

	public void setFkcode(String fkCode) {
		this.setField(8, fkCode);
	}

	public String getFkcode() {
		return this.getField(8);
	}

}
