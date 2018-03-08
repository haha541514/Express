package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class WaybillforpackageQuery extends HGeneralQuery {
	
	public WaybillforpackageQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.housewaybill.da.WaybillforpackageColumns(bw.bwCode,bw.bwLabelcode,cw.cwCode,cw.cwPieces,cw.cwPostcodeDestination,cw.cwGrossweight,cw.cwChargeweight,cw.cwTransferpieces,cw.cwTransfergrossweight,cw.cwTransferchargeweight,cw.cwServerchargeweight,cw.cwCustomerewbcode,cw.cwServerewbcode,cw.cwEwbcode,cw.cwOpIdCreator,cw.cwCreatedate,cw.cwOpIdModifier,cw.cwModifydate,cw.cwCustomerchargeweight,cw.znvName,cw.cwBillcounts,cw.cwBagcounts,pk.pkCode,pk.pkSname,pk.pkSename,sch.chnCode,sch.chnSname,sch.chnSename,cws.cwsCode,cws.cwsName,dtsignin.dtCode,dtsignin.dtHubcode,dtsignin.dtEname,ddt.dtCode,ddt.dtHubcode,ddt.dtEname,odt.dtCode,odt.dtHubcode,odt.dtEname,ee.eeCode,ee.eeSname,cco.coCode,cco.coSname,cco.coLabelcode,sco.coCode,sco.coSname,sco.coLabelcode,bwv.bwbvSerialno,bwv.bwbvBaglabelcode,bwv.bwbvZonename,bwv.bwbvIssuecontent,ihs.ihsCode,bwv.bwbvId) FROM TopBatchwaybillvalue as bwv inner join bwv.topBatchwaybill as bw inner join bwv.topCorewaybill as cw inner join cw.tdiProductkind as pk left join cw.tchnChannelByChnCodeSupplier as sch inner join cw.tdiCargotype as ct inner join cw.tdiCorewaybillstatus as cws left join cw.tdiDistrictByDtCodeSignin as dtsignin left join cw.tdiDistrictByDtCodeDestination as ddt inner join cw.tdiDistrictByDtCodeOrigin as odt inner join cw.tdiEnterpriseelement as ee left join cw.tcoCorporationByCoCodeCustomer as cco left join cw.tcoCorporationByCoCodeSupplier as sco left join cw.tdiIssueholdstatus as ihs";
	    m_strWhereClause = "cws.cwsCode != 'EL'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "bw.bwCode = ~~", "bw.bwLabelcode = '~~'", "cw.cwCustomerewbcode = '~~'", "cw.cwServerewbcode = '~~'", "cw.cwEwbcode = '~~'", "pk.pkCode = '~~'", "cw.znvName = '~~'", "sch.chnCode = '~~'", "cws.cwsCode in ('~~')", "ddt.dtCode = '~~'", "cco.coCode = '~~'", "sco.coCode = '~~'", "cw.cwCode = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
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

	public void setBwlabelcode(String bwLabelcode) {
		this.setField(1, bwLabelcode);
	}

	public String getBwlabelcode() {
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

	public void setPkcode(String pkCode) {
		this.setField(5, pkCode);
	}

	public String getPkcode() {
		return this.getField(5);
	}

	public void setZnvname(String znvName) {
		this.setField(6, znvName);
	}

	public String getZnvname() {
		return this.getField(6);
	}

	public void setSchncode(String schnCode) {
		this.setField(7, schnCode);
	}

	public String getSchncode() {
		return this.getField(7);
	}

	public void setIncwscode(String Incwscode) {
		this.setField(8, Incwscode);
	}

	public String getIncwscode() {
		return this.getField(8);
	}

	public void setDtcode(String dtCode) {
		this.setField(9, dtCode);
	}

	public String getDtcode() {
		return this.getField(9);
	}

	public void setCcocode(String ccoCode) {
		this.setField(10, ccoCode);
	}

	public String getCcocode() {
		return this.getField(10);
	}

	public void setScocode(String scoCode) {
		this.setField(11, scoCode);
	}

	public String getScocode() {
		return this.getField(11);
	}

	public void setCwcode(String cwCode) {
		this.setField(12, cwCode);
	}

	public String getCwcode() {
		return this.getField(12);
	}

}
