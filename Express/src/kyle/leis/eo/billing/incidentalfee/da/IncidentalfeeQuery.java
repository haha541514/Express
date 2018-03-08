package kyle.leis.eo.billing.incidentalfee.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class IncidentalfeeQuery extends HGeneralQuery {
	
	public IncidentalfeeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.billing.incidentalfee.da.IncidentalfeeColumns(if.ifId, if.ifCurrencyrate, if.ifTotal, if.ifActualtotal, if.ifOccurdate, if.ifCreatedate, if.ifModifydate, if.ifConfirmdate, if.ifIdReference, if.ifRemark, ck.ckCode, ck.ckName, ck.ckEname, br.brId, chn.chnCode, chn.chnName, chn.chnEname, chn.chnSname, chn.chnSename, fs.fsCode, fs.fsName, fs.fsEname, confop.opId, confop.opCode, confop.opName, cop.opId, cop.opCode, cop.opName, mop.opId, mop.opCode, mop.opName, fk.fkCode, fk.fkName, co.coCode, co.coName, co.coSname, co.coSename, bk.bkCode, bk.bkName) FROM TblIncidentalfee as if inner join if.tdiCurrencykind as ck left join if.tfiBillrecord as br left join if.tchnChannel as chn inner join if.tdiFeestatus as fs left join if.tdiOperatorByOpIdConfirm as confop inner join if.tdiOperatorByOpIdCreate as cop inner join if.tdiOperatorByOpIdModify as mop inner join if.tdiFeekind as fk inner join if.tcoCorporation as co inner join if.tdiBillingkind as bk";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "if.ifId = ~~", "br.brId = ~~", "chn.chnCode = '~~'", "ck.ckCode = '~~'", "co.coCode = '~~'", "co.coSename = '~~'", "cop.opCode = '~~'", "mop.opCode = '~~'", "confop.opCode = '~~'", "if.ifOccurdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= if.ifOccurdate", "if.ifCreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= if.ifCreatedate", "if.ifModifydate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= if.ifModifydate", "fs.fsCode IN (~~)", "fk.fkCode IN (~~)", "bk.bkCode = '~~'", "br.brId in (~~)" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setIfid(String ifId) {
		this.setField(0, ifId);
	}

	public String getIfid() {
		return this.getField(0);
	}

	public void setBrid(String brId) {
		this.setField(1, brId);
	}

	public String getBrid() {
		return this.getField(1);
	}

	public void setChncode(String chnCode) {
		this.setField(2, chnCode);
	}

	public String getChncode() {
		return this.getField(2);
	}

	public void setCkcode(String ckCode) {
		this.setField(3, ckCode);
	}

	public String getCkcode() {
		return this.getField(3);
	}

	public void setCocode(String coCode) {
		this.setField(4, coCode);
	}

	public String getCocode() {
		return this.getField(4);
	}

	public void setCosename(String coSename) {
		this.setField(5, coSename);
	}

	public String getCosename() {
		return this.getField(5);
	}

	public void setCopcode(String copCode) {
		this.setField(6, copCode);
	}

	public String getCopcode() {
		return this.getField(6);
	}

	public void setMopcode(String mopCode) {
		this.setField(7, mopCode);
	}

	public String getMopcode() {
		return this.getField(7);
	}

	public void setConfopcode(String confopCode) {
		this.setField(8, confopCode);
	}

	public String getConfopcode() {
		return this.getField(8);
	}

	public void setStartoccurdate(String StartOccurdate) {
		this.setField(9, StartOccurdate);
	}

	public String getStartoccurdate() {
		return this.getField(9);
	}

	public void setEndoccurdate(String EndOccurdate) {
		this.setField(10, EndOccurdate);
	}

	public String getEndoccurdate() {
		return this.getField(10);
	}

	public void setStartcreatedate(String StartCreatedate) {
		this.setField(11, StartCreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(11);
	}

	public void setEndcreatedate(String EndCreatedate) {
		this.setField(12, EndCreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(12);
	}

	public void setStartmodifydate(String StartModifydate) {
		this.setField(13, StartModifydate);
	}

	public String getStartmodifydate() {
		return this.getField(13);
	}

	public void setEndmodifydate(String EndModifydate) {
		this.setField(14, EndModifydate);
	}

	public String getEndmodifydate() {
		return this.getField(14);
	}

	public void setFscode(String fsCode) {
		this.setField(15, fsCode);
	}

	public String getFscode() {
		return this.getField(15);
	}

	public void setFkcode(String fkCode) {
		this.setField(16, fkCode);
	}

	public String getFkcode() {
		return this.getField(16);
	}

	public void setBkcode(String bkCode) {
		this.setField(17, bkCode);
	}

	public String getBkcode() {
		return this.getField(17);
	}

	public void setInBrid(String InbrId) {
		this.setField(18, InbrId);
	}

	public String getInBrid() {
		return this.getField(18);
	}	
	
}
