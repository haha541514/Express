package kyle.leis.eo.operation.batchwaybill.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class BatchwaybillQuery extends HGeneralQuery {
	
	public BatchwaybillQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns(bw.bwCode,bw.bwCreatedate,bw.bwModifydate,bw.bwCompletedate,bw.bwAuditdate,bw.bwApprovedate,bw.addDate,bw.bwRemark,bw.bwTotalgrossweight,bw.bwTotalpieces,bw.bwLabelcode,bw.bwBatchnumber,bws.bwsCode,bws.bwsName,chn.chnCode,chn.chnName,chn.chnSname,chn.chnSename,cop.opId,cop.opName,apop.opId,apop.opName,coop.opId,coop.opName,mop.opId,mop.opName,auop.opId,auop.opName,ee.eeCode,ee.eeSname,ee.eeEsname,adt.adtCode,adt.adtName,co.coCode,co.coName,co.coSname,co.coSename,bw.bwContainerid,cct.ctCode,cct.ctName) FROM TopBatchwaybill as bw left join bw.tcoCorporation as co left join co.tcoCustomer as tcs left join tcs.tdiCustomertype as cct inner join bw.tdiBatchwaybillstatus as bws left join bw.tchnChannel as chn left join bw.tdiOperatorByBwOpIdComplete as coop left join bw.tdiOperatorByBwOpIdApprove as apop inner join bw.tdiOperatorByBwOpIdCreate as cop inner join bw.tdiOperatorByBwOpIdModify as mop left join bw.tdiOperatorByBwOpIdAudit as auop inner join bw.tdiEnterpriseelement as ee inner join bw.tdiArrivaldeparturetype as adt";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "bw.bwCode = ~~", "bw.bwCreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.bwCreatedate", "bw.bwModifydate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.bwModifydate", "bw.bwCompletedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.bwCompletedate", "bw.addDate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.addDate", "bw.bwAuditdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.bwAuditdate", "bw.bwApprovedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.bwApprovedate", "bw.bwLabelcode = '~~'", "bws.bwsCode = '~~'", "chn.chnCode = '~~'", "cop.opId = ~~", "apop.opId = ~~", "coop.opId = ~~", "mop.opId = ~~", "auop.opId = ~~", "ee.eeCode = '~~'", "adt.adtCode = '~~'", "co.coCode = '~~'", "ee.eeStructurecode like '~~%'", "cct.ctCode= '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBwcode(String bwCode) {
		this.setField(0, bwCode);
	}

	public String getBwcode() {
		return this.getField(0);
	}

	public void setStartcreatedate(String StartCreatedate) {
		this.setField(1, StartCreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(1);
	}

	public void setEndcreatedate(String EndCreatedate) {
		this.setField(2, EndCreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(2);
	}

	public void setStartmodifydate(String StartModifydate) {
		this.setField(3, StartModifydate);
	}

	public String getStartmodifydate() {
		return this.getField(3);
	}

	public void setEndmodifydate(String EndModifydate) {
		this.setField(4, EndModifydate);
	}

	public String getEndmodifydate() {
		return this.getField(4);
	}

	public void setStartcompletedate(String StartCompletedate) {
		this.setField(5, StartCompletedate);
	}

	public String getStartcompletedate() {
		return this.getField(5);
	}

	public void setEndcompletedate(String EndCompletedate) {
		this.setField(6, EndCompletedate);
	}

	public String getEndcompletedate() {
		return this.getField(6);
	}

	public void setStartadddate(String StartAddDate) {
		this.setField(7, StartAddDate);
	}

	public String getStartadddate() {
		return this.getField(7);
	}

	public void setEndadddate(String EndAddDate) {
		this.setField(8, EndAddDate);
	}

	public String getEndadddate() {
		return this.getField(8);
	}

	public void setStartauditdate(String StartAuditdate) {
		this.setField(9, StartAuditdate);
	}

	public String getStartauditdate() {
		return this.getField(9);
	}

	public void setEndauditdate(String EndAuditdate) {
		this.setField(10, EndAuditdate);
	}

	public String getEndauditdate() {
		return this.getField(10);
	}

	public void setStartapprovedate(String StartApprovedate) {
		this.setField(11, StartApprovedate);
	}

	public String getStartapprovedate() {
		return this.getField(11);
	}

	public void setEndapprovedate(String EndApprovedate) {
		this.setField(12, EndApprovedate);
	}

	public String getEndapprovedate() {
		return this.getField(12);
	}

	public void setBwlabelcode(String bwLabelcode) {
		this.setField(13, bwLabelcode);
	}

	public String getBwlabelcode() {
		return this.getField(13);
	}

	public void setBwscode(String bwsCode) {
		this.setField(14, bwsCode);
	}

	public String getBwscode() {
		return this.getField(14);
	}

	public void setChncode(String chnCode) {
		this.setField(15, chnCode);
	}

	public String getChncode() {
		return this.getField(15);
	}

	public void setCreateopid(String CreateOpId) {
		this.setField(16, CreateOpId);
	}

	public String getCreateopid() {
		return this.getField(16);
	}

	public void setApproveopid(String ApproveOpId) {
		this.setField(17, ApproveOpId);
	}

	public String getApproveopid() {
		return this.getField(17);
	}

	public void setCompleteopid(String CompleteOpId) {
		this.setField(18, CompleteOpId);
	}

	public String getCompleteopid() {
		return this.getField(18);
	}

	public void setModifyopid(String ModifyOpId) {
		this.setField(19, ModifyOpId);
	}

	public String getModifyopid() {
		return this.getField(19);
	}

	public void setAuditopid(String AuditOpId) {
		this.setField(20, AuditOpId);
	}

	public String getAuditopid() {
		return this.getField(20);
	}

	public void setEecode(String eeCode) {
		this.setField(21, eeCode);
	}

	public String getEecode() {
		return this.getField(21);
	}

	public void setAdtcode(String adtCode) {
		this.setField(22, adtCode);
	}

	public String getAdtcode() {
		return this.getField(22);
	}

	public void setCocode(String coCode) {
		this.setField(23, coCode);
	}

	public String getCocode() {
		return this.getField(23);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(24, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(24);
	}
	public void setCctcode(String cctCode) {
		this.setField(25, cctCode);
	}

	public String getCctcode() {
		return this.getField(25);
	}

}
