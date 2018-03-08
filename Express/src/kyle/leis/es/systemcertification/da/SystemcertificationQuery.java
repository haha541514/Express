package kyle.leis.es.systemcertification.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class SystemcertificationQuery extends HGeneralQuery {
	
	public SystemcertificationQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.systemcertification.da.SystemcertificationColumns(sc.scId, sc.scHdserialnumber, sc.scMacaddress, sc.scIpaddress, sc.scStartdate, sc.scEnddate, sc.scConfirmdate, sc.scApplydate, sc.scModifydate, ss.ssCode, ss.ssName, opap.opId, opap.opCode, opap.opName, opco.opId, opco.opCode, opco.opName, opmo.opId, opmo.opCode, opmo.opName, sc.scRemark, sc.scOwnenterprisesign) FROM TesSystemcertification as sc inner join sc.tdiSimplestatus as ss inner join sc.tdiOperatorByScOpIdApply as opap left join sc.tdiOperatorByScOpIdConfirm as opco inner join sc.tdiOperatorByScOpIdModify as opmo";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "sc.scId = ~~", "sc.scHdserialnumber = '~~'", "sc.scMacaddress = '~~'", "sc.scIpaddress = '~~'", "sc.scStartdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= sc.scStartdate", "sc.scEnddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= sc.scEnddate", "sc.scConfirmdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= sc.scConfirmdate", "sc.scApplydate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= sc.scApplydate", "sc.scModifydate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= sc.scModifydate", "ss.ssCode = '~~'", "ss.ssCode not in (~~)", "opap.opId = ~~", "opap.opCode = ~~", "opco.opId = ~~", "opco.opCode = ~~", "opmo.opId = ~~", "opmo.opCode = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setScid(String scId) {
		this.setField(0, scId);
	}

	public String getScid() {
		return this.getField(0);
	}

	public void setSchdserialnumber(String scHdserialnumber) {
		this.setField(1, scHdserialnumber);
	}

	public String getSchdserialnumber() {
		return this.getField(1);
	}

	public void setScmacaddress(String scMacaddress) {
		this.setField(2, scMacaddress);
	}

	public String getScmacaddress() {
		return this.getField(2);
	}

	public void setScipaddress(String scIpaddress) {
		this.setField(3, scIpaddress);
	}

	public String getScipaddress() {
		return this.getField(3);
	}

	public void setStartscstartdate(String startscStartdate) {
		this.setField(4, startscStartdate);
	}

	public String getStartscstartdate() {
		return this.getField(4);
	}

	public void setEndscstartdate(String endscStartdate) {
		this.setField(5, endscStartdate);
	}

	public String getEndscstartdate() {
		return this.getField(5);
	}

	public void setStartscenddate(String startscEnddate) {
		this.setField(6, startscEnddate);
	}

	public String getStartscenddate() {
		return this.getField(6);
	}

	public void setEndscenddate(String endscEnddate) {
		this.setField(7, endscEnddate);
	}

	public String getEndscenddate() {
		return this.getField(7);
	}

	public void setStartscconfirmdate(String startscConfirmdate) {
		this.setField(8, startscConfirmdate);
	}

	public String getStartscconfirmdate() {
		return this.getField(8);
	}

	public void setEndscconfirmdate(String endscConfirmdate) {
		this.setField(9, endscConfirmdate);
	}

	public String getEndscconfirmdate() {
		return this.getField(9);
	}

	public void setStartscapplydate(String startscApplydate) {
		this.setField(10, startscApplydate);
	}

	public String getStartscapplydate() {
		return this.getField(10);
	}

	public void setEndscapplydate(String endscApplydate) {
		this.setField(11, endscApplydate);
	}

	public String getEndscapplydate() {
		return this.getField(11);
	}

	public void setStartscmodifydate(String startscModifydate) {
		this.setField(12, startscModifydate);
	}

	public String getStartscmodifydate() {
		return this.getField(12);
	}

	public void setEndscmodifydate(String endscModifydate) {
		this.setField(13, endscModifydate);
	}

	public String getEndscmodifydate() {
		return this.getField(13);
	}

	public void setSscode(String ssCode) {
		this.setField(14, ssCode);
	}

	public String getSscode() {
		return this.getField(14);
	}

	public void setNotinsscode(String notInssCode) {
		this.setField(15, notInssCode);
	}

	public String getNotinsscode() {
		return this.getField(15);
	}

	public void setOpapopid(String opapopId) {
		this.setField(16, opapopId);
	}

	public String getOpapopid() {
		return this.getField(16);
	}

	public void setOpapopcode(String opapopCode) {
		this.setField(17, opapopCode);
	}

	public String getOpapopcode() {
		return this.getField(17);
	}

	public void setOpcoopid(String opcoopId) {
		this.setField(18, opcoopId);
	}

	public String getOpcoopid() {
		return this.getField(18);
	}

	public void setOpcoopcode(String opcoopCode) {
		this.setField(19, opcoopCode);
	}

	public String getOpcoopcode() {
		return this.getField(19);
	}

	public void setOpmoopid(String opmoopId) {
		this.setField(20, opmoopId);
	}

	public String getOpmoopid() {
		return this.getField(20);
	}

	public void setOpmoopcode(String opmoopCode) {
		this.setField(21, opmoopCode);
	}

	public String getOpmoopcode() {
		return this.getField(21);
	}

}
