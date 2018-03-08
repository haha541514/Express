package kyle.leis.eo.customerservice.issue.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class IssueQuery extends HGeneralQuery {
	
	public IssueQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.customerservice.issue.da.IssueColumns(isu.isuId,isu.isuCreatedate,isu.isuModifydate,isu.isContent,isus.isusCode,isus.isusName,isug.isugCode,isug.isugName,cop.opId,cop.opName,mop.opId,mop.opName,eop.opId,eop.opName,ihs.ihsCode,ihs.ihsName,isut.isutCode,isut.isutName,cw.cwCode,cw.cwChargeweight,cw.cwCustomerewbcode,cw.cwServerewbcode,cw.cwEwbcode,pm.pmCode,pm.pmName,ct.ctCode,ct.ctName,cco.coCode,cco.coSname,cco.coLabelcode,cwihs.ihsCode,cwihs.ihsName,ssop.opId,ssop.opName,csop.opId,csop.opName,ee.eeCode,ee.eeSname,cct.ctCode,cct.ctName) FROM TcsIssue as isu inner join isu.tdiIssuestatus as isus inner join isu.tdiIssuegrade as isug inner join isu.tdiOperatorByOpIdCreator as cop inner join isu.tdiOperatorByOpIdModifier as mop inner join isu.tdiOperatorByOpIdExecutor as eop left join isu.tdiIssueholdstatus as ihs inner join isu.tdiIssuetype as isut inner join isu.topCorewaybill as cw inner join cw.tdiPaymentmode as pm inner join cw.tdiCargotype as ct inner join cw.tcoCorporationByCoCodeCustomer as cco left join cw.tdiIssueholdstatus as cwihs inner join cw.tdiCorewaybillstatus as cws inner join cco.tcoCustomer as cm inner join cm.tdiOperatorByCmOpIdSale as ssop inner join cm.tdiOperatorByCmOpIdCservice as csop inner join cw.tdiEnterpriseelement as ee inner join cco.tcoCustomer as tct left join tct.tdiCustomertype as cct";
	    m_strWhereClause = "cws.cwsCode NOT IN ('EL', 'CEL')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "isu.isuId = ~~", "isus.isusCode in (~~)", "isus.isusCode not in (~~)", "isug.isugCode = '~~'", "isut.isutCode = '~~'", "cw.cwCustomerewbcode in (~~)", "cw.cwServerewbcode in (~~)", "cw.cwEwbcode = '~~'", "pm.pmCode = '~~'", "ct.ctCode = '~~'", "cco.coCode = '~~'", "cw.cwCode = ~~", "isu.isuModifydate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= isu.isuModifydate", "cwihs.ihsCode = '~~'", "ihs.ihsCode = '~~'", "ssop.opId = ~~", "csop.opId = ~~", "not exists(select tia.isaContent from TcsIssueaction tia where tia.comp_id.isuId = isu.isuId and tia.tdiActionkind.akCode = 'CWR' and '~~' = 'Y')", "exists(select tia.isaContent from TcsIssueaction tia where tia.comp_id.isuId = isu.isuId and tia.tdiActionkind.akCode = 'CWR' and '~~' = 'Y')", "ee.eeStructurecode like '~~%'", "cct.ctCode= '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	

	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setIsuid(String isuId) {
		this.setField(0, isuId);
	}

	public String getIsuid() {
		return this.getField(0);
	}

	public void setIsuscode(String isusCode) {
		this.setField(1, isusCode);
	}

	public String getIsuscode() {
		return this.getField(1);
	}

	public void setNotisuscode(String NotisusCode) {
		this.setField(2, NotisusCode);
	}

	public String getNotisuscode() {
		return this.getField(2);
	}

	public void setIsugcode(String isugCode) {
		this.setField(3, isugCode);
	}

	public String getIsugcode() {
		return this.getField(3);
	}

	public void setIsutcode(String isutCode) {
		this.setField(4, isutCode);
	}

	public String getIsutcode() {
		return this.getField(4);
	}

	public void setCwcustomerewbcode(String cwCustomerewbcode) {
		this.setField(5, cwCustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(5);
	}

	public void setCwserverewbcode(String cwServerewbcode) {
		this.setField(6, cwServerewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(6);
	}

	public void setCwewbcode(String cwEwbcode) {
		this.setField(7, cwEwbcode);
	}

	public String getCwewbcode() {
		return this.getField(7);
	}

	public void setPmcode(String pmCode) {
		this.setField(8, pmCode);
	}

	public String getPmcode() {
		return this.getField(8);
	}

	public void setCtcode(String ctCode) {
		this.setField(9, ctCode);
	}

	public String getCtcode() {
		return this.getField(9);
	}

	public void setCocode(String coCode) {
		this.setField(10, coCode);
	}

	public String getCocode() {
		return this.getField(10);
	}

	public void setCwcode(String cwCode) {
		this.setField(11, cwCode);
	}

	public String getCwcode() {
		return this.getField(11);
	}

	public void setStartmodifydate(String Startmodifydate) {
		this.setField(12, Startmodifydate);
	}

	public String getStartmodifydate() {
		return this.getField(12);
	}

	public void setEndmodifydate(String Endmodifydate) {
		this.setField(13, Endmodifydate);
	}

	public String getEndmodifydate() {
		return this.getField(13);
	}

	public void setCwihscode(String cwihsCode) {
		this.setField(14, cwihsCode);
	}

	public String getCwihscode() {
		return this.getField(14);
	}

	public void setIhscode(String ihsCode) {
		this.setField(15, ihsCode);
	}

	public String getIhscode() {
		return this.getField(15);
	}

	public void setOpidsales(String OpIdSales) {
		this.setField(16, OpIdSales);
	}

	public String getOpidsales() {
		return this.getField(16);
	}

	public void setOpidcustomerservice(String OpIdCustomerservice) {
		this.setField(17, OpIdCustomerservice);
	}

	public String getOpidcustomerservice() {
		return this.getField(17);
	}

	public void setTiaequalsign(String TIAEqualSign) {
		this.setField(18, TIAEqualSign);
	}

	public String getTiaequalsign() {
		return this.getField(18);
	}

	public void setTiaequalsignt(String TIAEqualSignT) {
		this.setField(19, TIAEqualSignT);
	}

	public String getTiaequalsignt() {
		return this.getField(19);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(20, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(20);
	}
	public void setCctcode(String cctCode) {
		this.setField(21, cctCode);
	}

	public String getCctcode() {
		return this.getField(21);
	}


}
