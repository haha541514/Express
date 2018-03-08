package kyle.leis.fs.authoritys.user.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class UserQuery extends HGeneralQuery {
	
	public UserQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.authoritys.user.da.UserColumns(op.opId, op.opCode, op.opName, op.opSex, op.opPassword, op.opEname, op.opSname, op.opIdnumber, op.opEmail, op.opIdCreator, op.opCreatedate, op.opIdModifier, op.opModifydate, op.opAddress, op.opTelephone, op.opMobile, op.opConfirmdate, op.opDimissiondate, op.opMsnname, op.opQqnumber, op.opFaxnumber, ps.psCode, ps.psName, ps.psEname, ee.eeCode, ee.eeName, ee.eeEname, ee.eeSname, ee.eeEsname, fc.fcCode, fc.fcName, fc.fcEname, co.coCode, co.coName, co.coEname, co.coSname, co.coSename, dp.dpCode, dp.dpName, dp.dpEname, os.osCode, os.osName, op.opIssuecontactpersonsign,st.stCode,st.stName,ct.ctCode,ct.ctName) FROM TdiOperator as op inner join op.tdiPosition as ps inner join op.tdiEnterpriseelement as ee inner join op.tdiFunction as fc left join op.tcoCorporation as co inner join op.tdiDepartment as dp left join op.tdiOperatorstatus as os left join op.tdiState as st left join op.tdiCity as ct";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "op.opId = ~~", "op.opCode = '~~'", "op.opPassword = '~~'", "op.opEname = '~~'", "op.opIdnumber = '~~'", "ee.eeCode = '~~'", "co.coCode = '~~'", "dp.dpCode = '~~'", "fc.fcCode = '~~'", "ps.psCode = '~~'", "op.opCreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= op.opCreatedate", "op.opDimissiondate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= op.opDimissiondate", "os.osCode = '~~'", "co.coCode is ~~", "co.coCode is not ~~", "op.opQqnumber = '~~'", "op.opIssuecontactpersonsign = '~~'", "op.opName = '~~'", "fc.fcName = '~~'", "dp.dpName = '~~'","st.stCode = '~~'","ct.ctCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ,1,1};		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setOpid(String opId) {
		this.setField(0, opId);
	}

	public String getOpid() {
		return this.getField(0);
	}

	public void setOpcode(String opCode) {
		this.setField(1, opCode);
	}

	public String getOpcode() {
		return this.getField(1);
	}

	public void setOppassword(String opPassword) {
		this.setField(2, opPassword);
	}

	public String getOppassword() {
		return this.getField(2);
	}

	public void setOp_ename(String op_Ename) {
		this.setField(3, op_Ename);
	}

	public String getOp_ename() {
		return this.getField(3);
	}

	public void setOpidnumber(String opIdnumber) {
		this.setField(4, opIdnumber);
	}

	public String getOpidnumber() {
		return this.getField(4);
	}

	public void setEecode(String eeCode) {
		this.setField(5, eeCode);
	}

	public String getEecode() {
		return this.getField(5);
	}

	public void setCocode(String coCode) {
		this.setField(6, coCode);
	}

	public String getCocode() {
		return this.getField(6);
	}

	public void setDpcode(String dpCode) {
		this.setField(7, dpCode);
	}

	public String getDpcode() {
		return this.getField(7);
	}

	public void setFccode(String fcCode) {
		this.setField(8, fcCode);
	}

	public String getFccode() {
		return this.getField(8);
	}

	public void setPscode(String psCode) {
		this.setField(9, psCode);
	}

	public String getPscode() {
		return this.getField(9);
	}

	public void setStartcreatedate(String startCreatedate) {
		this.setField(10, startCreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(10);
	}

	public void setEndcreatedate(String endCreatedate) {
		this.setField(11, endCreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(11);
	}

	public void setStartdimissiondate(String startDimissiondate) {
		this.setField(12, startDimissiondate);
	}

	public String getStartdimissiondate() {
		return this.getField(12);
	}

	public void setEnddimissiondate(String endDimissiondate) {
		this.setField(13, endDimissiondate);
	}

	public String getEnddimissiondate() {
		return this.getField(13);
	}

	public void setOscode(String osCode) {
		this.setField(14, osCode);
	}

	public String getOscode() {
		return this.getField(14);
	}

	public void setIsnull(String isNull) {
		this.setField(15, isNull);
	}

	public String getIsnull() {
		return this.getField(15);
	}

	public void setIsnotnull(String isNotNull) {
		this.setField(16, isNotNull);
	}

	public String getIsnotnull() {
		return this.getField(16);
	}

	public void setOpqqnumber(String opQqnumber) {
		this.setField(17, opQqnumber);
	}

	public String getOpqqnumber() {
		return this.getField(17);
	}

	public void setOpissuecontactpersonsign(String opIssuecontactpersonsign) {
		this.setField(18, opIssuecontactpersonsign);
	}

	public String getOpissuecontactpersonsign() {
		return this.getField(18);
	}

	public void setOpname(String opName) {
		this.setField(19, opName);
	}

	public String getOpname() {
		return this.getField(19);
	}

	public void setFcname(String fcName) {
		this.setField(20, fcName);
	}

	public String getFcname() {
		return this.getField(20);
	}

	public void setDpname(String dpName) {
		this.setField(21, dpName);
	}

	public String getDpname() {
		return this.getField(21);
	}
	public void setStcode(String Stcode) {
		this.setField(22, Stcode);
	}

	public String getStcode() {
		return this.getField(22);
	}
	public void setCtcode(String Ctcode) {
		this.setField(23, Ctcode);
	}

	public String getCtcode() {
		return this.getField(23);
	}
}
