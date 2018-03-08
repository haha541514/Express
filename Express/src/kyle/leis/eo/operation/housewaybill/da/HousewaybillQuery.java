package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class HousewaybillQuery extends HGeneralQuery {
	
	public HousewaybillQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns(hw.cwCode,hw.hwSignindate,hw.hwOpIdSignin,hw.hwOpIdWeighting,hw.hwSignoutdate,hw.hwOpIdSignout,hw.hwRecorddate,hw.hwOpIdRecord,hw.hwOpIdPacking,hw.hwInsurancevalue,hw.hwConsigneepostcode,cw.cwPostcodeDestination,cw.cwPieces,cw.cwGrossweight,cw.cwChargeweight,cw.cwTransferpieces,cw.cwTransfergrossweight,cw.cwTransferchargeweight,cw.cwServerchargeweight,cw.cwCustomerewbcode,cw.cwServerewbcode,cw.cwEwbcode,cw.cwTransfervolumeweight,cws.cwsCode,cws.cwsName,ee.eeCode,ee.eeSname,ee.eeEsname,pk.pkCode,pk.pkName,pk.pkSname,pk.pkSename,ddt.dtCode,ddt.dtHubcode,ddt.dtName,sdt.dtCode,sdt.dtHubcode,sdt.dtName,cddt.dtCode,cddt.dtHubcode,cddt.dtName,odt.dtCode,odt.dtHubcode,odt.dtName,pm.pmCode,pm.pmName,schn.chnCode,schn.chnName,schn.chnSname,cchn.chnCode,cchn.chnName,cchn.chnSname,ct.ctCode,ct.ctName,sco.coCode,sco.coName,sco.coSname,sco.coLabelcode,cco.coCode,cco.coName,cco.coSname,cco.coLabelcode,abw.bwCode,abw.bwLabelcode,abw.addDate,dbw.bwCode,dbw.bwLabelcode,dbw.addDate,cw.cwCustomerchargeweight,ihs.ihsCode,ihs.ihsName,cw.cwVolumerate,cw.cwTransfervolumerate,hw.hwRemark,cct.ctCode,cct.ctName,csop.opId,csop.opName,ct.ctEname,ct.ctSename,cws.cwsEname,ddt.dtEname,sdt.dtEname,cddt.dtEname,odt.dtEname,cw.znvName,serverwbck.bckCode,hw.hwServerewbchangedsign,hw.hwLabelprinttimes, subwbck.bckCode,hw.hwChannelmasteraccount,hw.hwConsigneecity,hw.hwOpIdWeightcheck,hw.hwWeightcheckkind,hw.hwWeightcheckdate,wcbw.bwLabelcode,cw.cwBillcounts,cw.cwBagcounts,abwv.bwbvId,dbwv.bwbvId, cw.cwBatchwaybillsign, hw.hwArrearsignout, hw.hwSewbchangedbywebsign,hw.hwPaymentaccount) FROM TopHousewaybill as hw inner join hw.topCorewaybill as cw inner join cw.tdiCorewaybillstatus as cws inner join cw.tdiEnterpriseelement as ee inner join cw.tdiProductkind as pk left join cw.tdiDistrictByDtCodeDestination as ddt left join ddt.tdiDistrict as cddt left join cw.tdiDistrictByDtCodeSignin as sdt inner join cw.tdiDistrictByDtCodeOrigin as odt inner join cw.tdiPaymentmode as pm left join cw.tchnChannelByChnCodeSupplier as schn left join cw.tchnChannelByChnCodeCustomer as cchn inner join cw.tdiCargotype as ct left join cw.tcoCorporationByCoCodeSupplier as sco left join cw.tcoCorporationByCoCodeCustomer as cco left join cco.tcoCustomer as cm left join cm.tdiCustomertype as cct left join cm.tdiOperatorByCmOpIdCservice as csop left join cw.topBatchwaybillByBwCodeArrival as abw left join cw.topBatchwaybillByBwCodeDeparture as dbw left join cw.topBatchwaybillByBwCodeWeightcheck as wcbw left join cw.tdiIssueholdstatus as ihs left join hw.tdiWaybillcodekindByHwServerewbkind as serverwbck left join hw.tdiWaybillcodekindByHwSubchildewbkind as subwbck left join cw.topBatchwaybillvalueByBwbvIdArrival as abwv left join cw.topBatchwaybillvalueByBwbvIdDeparture as dbwv";
	    m_strWhereClause = "";
	    m_strOrderByClause = "abw.addDate desc";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cwCustomerewbcode = '~~'", "exists (select cwc.comp_id.cwCode from TopCorewaybillcode cwc where cwc.comp_id.cwCode = hw.cwCode and cwc.cwbcCustomerewbcode = '~~')", "cw.cwServerewbcode = '~~'", "cw.cwEwbcode = '~~'", "hw.hwSignindate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.hwSignindate", "hw.hwSignoutdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.hwSignoutdate", "hw.hwRecorddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.hwRecorddate", "cw.cwChargeweight >= ~~", "~~ >= cw.cwChargeweight", "cw.cwServerchargeweight >= ~~", "~~ >= cw.cwServerchargeweight", "cws.cwsCode in (~~)", "cws.cwsCode not in (~~)", "pk.pkCode = '~~'", "ddt.dtCode = '~~'", "cddt.dtCode = '~~'", "odt.dtCode = '~~'", "pm.pmCode = '~~'", "schn.chnCode = '~~'", "ct.ctCode = '~~'", "sco.coCode = '~~'", "cco.coCode = '~~'", "abw.bwLabelcode = '~~'", "dbw.bwLabelcode = '~~'", "wcbw.bwLabelcode = '~~'", "hw.cwCode = ~~", "ee.eeCode = '~~'", "csop.opId = ~~", "ihs.ihsCode = '~~'", "exists (select cp.comp_id.cpId from TopCorewaybillpiece cp where cp.comp_id.cwCode = hw.cwCode and cp.cpBaglabelcode = '~~')", "abw.addDate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= abw.addDate", "abwv.bwbvId = ~~", "dbwv.bwbvId = ~~", "abwv.topCorewaybill.cwEwbcode = ~~", "dbwv.topCorewaybill.cwEwbcode = ~~", "cw.cwBatchwaybillsign = '~~'", "cct.ctCode= '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCustomerewbcode(String Customerewbcode) {
		this.setField(0, Customerewbcode);
	}

	public String getCustomerewbcode() {
		return this.getField(0);
	}

	public void setExistsorderid(String existsorderid) {
		this.setField(1, existsorderid);
	}

	public String getExistsorderid() {
		return this.getField(1);
	}

	public void setServerewbcode(String Serverewbcode) {
		this.setField(2, Serverewbcode);
	}

	public String getServerewbcode() {
		return this.getField(2);
	}

	public void setEwbcode(String Ewbcode) {
		this.setField(3, Ewbcode);
	}

	public String getEwbcode() {
		return this.getField(3);
	}

	public void setStartsignindate(String StartSignindate) {
		this.setField(4, StartSignindate);
	}

	public String getStartsignindate() {
		return this.getField(4);
	}

	public void setEndsignindate(String EndSignindate) {
		this.setField(5, EndSignindate);
	}

	public String getEndsignindate() {
		return this.getField(5);
	}

	public void setStartsignoutdate(String StartSignoutdate) {
		this.setField(6, StartSignoutdate);
	}

	public String getStartsignoutdate() {
		return this.getField(6);
	}

	public void setEndsignoutdate(String EndSignoutdate) {
		this.setField(7, EndSignoutdate);
	}

	public String getEndsignoutdate() {
		return this.getField(7);
	}

	public void setStartrecorddate(String StartRecorddate) {
		this.setField(8, StartRecorddate);
	}

	public String getStartrecorddate() {
		return this.getField(8);
	}

	public void setEndrecorddate(String EndRecorddate) {
		this.setField(9, EndRecorddate);
	}

	public String getEndrecorddate() {
		return this.getField(9);
	}

	public void setStartchargeweight(String StartChargeweight) {
		this.setField(10, StartChargeweight);
	}

	public String getStartchargeweight() {
		return this.getField(10);
	}

	public void setEndchargeweight(String EndChargeweight) {
		this.setField(11, EndChargeweight);
	}

	public String getEndchargeweight() {
		return this.getField(11);
	}

	public void setStartserverchargeweight(String StartServerchargeweight) {
		this.setField(12, StartServerchargeweight);
	}

	public String getStartserverchargeweight() {
		return this.getField(12);
	}

	public void setEndserverchargeweight(String EndServerchargeweight) {
		this.setField(13, EndServerchargeweight);
	}

	public String getEndserverchargeweight() {
		return this.getField(13);
	}

	public void setCwscode(String cwsCode) {
		this.setField(14, cwsCode);
	}

	public String getCwscode() {
		return this.getField(14);
	}

	public void setNotcwscode(String NotcwsCode) {
		this.setField(15, NotcwsCode);
	}

	public String getNotcwscode() {
		return this.getField(15);
	}

	public void setPkcode(String pkCode) {
		this.setField(16, pkCode);
	}

	public String getPkcode() {
		return this.getField(16);
	}

	public void setDesdtcode(String DesDtCode) {
		this.setField(17, DesDtCode);
	}

	public String getDesdtcode() {
		return this.getField(17);
	}

	public void setDescountrycode(String DesCountryCode) {
		this.setField(18, DesCountryCode);
	}

	public String getDescountrycode() {
		return this.getField(18);
	}

	public void setOrigindtcode(String OriginDtCode) {
		this.setField(19, OriginDtCode);
	}

	public String getOrigindtcode() {
		return this.getField(19);
	}

	public void setPmcode(String pmCode) {
		this.setField(20, pmCode);
	}

	public String getPmcode() {
		return this.getField(20);
	}

	public void setChncode(String chnCode) {
		this.setField(21, chnCode);
	}

	public String getChncode() {
		return this.getField(21);
	}

	public void setCtcode(String ctCode) {
		this.setField(22, ctCode);
	}

	public String getCtcode() {
		return this.getField(22);
	}

	public void setScocode(String scoCode) {
		this.setField(23, scoCode);
	}

	public String getScocode() {
		return this.getField(23);
	}

	public void setCcocode(String ccoCode) {
		this.setField(24, ccoCode);
	}

	public String getCcocode() {
		return this.getField(24);
	}

	public void setAbwlabelcode(String abwLabelcode) {
		this.setField(25, abwLabelcode);
	}

	public String getAbwlabelcode() {
		return this.getField(25);
	}

	public void setDbwlabelcode(String dbwLabelcode) {
		this.setField(26, dbwLabelcode);
	}

	public String getDbwlabelcode() {
		return this.getField(26);
	}

	public void setWcbwlabelcode(String wcbwLabelcode) {
		this.setField(27, wcbwLabelcode);
	}

	public String getWcbwlabelcode() {
		return this.getField(27);
	}

	public void setCwcode(String cwCode) {
		this.setField(28, cwCode);
	}

	public String getCwcode() {
		return this.getField(28);
	}

	public void setEecode(String eeCode) {
		this.setField(29, eeCode);
	}

	public String getEecode() {
		return this.getField(29);
	}

	public void setCsopid(String csopId) {
		this.setField(30, csopId);
	}

	public String getCsopid() {
		return this.getField(30);
	}

	public void setIhscode(String ihsCode) {
		this.setField(31, ihsCode);
	}

	public String getIhscode() {
		return this.getField(31);
	}

	public void setCpbaglabelcode(String cpBaglabelcode) {
		this.setField(32, cpBaglabelcode);
	}

	public String getCpbaglabelcode() {
		return this.getField(32);
	}

	public void setStartarrivedate(String StartArrivedate) {
		this.setField(33, StartArrivedate);
	}

	public String getStartarrivedate() {
		return this.getField(33);
	}

	public void setEndarrivedate(String EndArrivedate) {
		this.setField(34, EndArrivedate);
	}

	public String getEndarrivedate() {
		return this.getField(34);
	}

	public void setAbwvid(String abwvID) {
		this.setField(35, abwvID);
	}

	public String getAbwvid() {
		return this.getField(35);
	}

	public void setDbwvid(String dbwvID) {
		this.setField(36, dbwvID);
	}

	public String getDbwvid() {
		return this.getField(36);
	}

	public void setAbwvcwewbcode(String abwvCwewbcode) {
		this.setField(37, abwvCwewbcode);
	}

	public String getAbwvcwewbcode() {
		return this.getField(37);
	}

	public void setDbwvcwewbcode(String dbwvCwewbcode) {
		this.setField(38, dbwvCwewbcode);
	}

	public String getDbwvcwewbcode() {
		return this.getField(38);
	}

	public void setCwbatchwaybillsign(String cwBatchwaybillsign) {
		this.setField(39, cwBatchwaybillsign);
	}

	public String getCwbatchwaybillsign() {
		return this.getField(39);
	}
	public void setCctcode(String cctCode) {
		this.setField(40, cctCode);
	}

	public String getCctcode() {
		return this.getField(40);
	}

}
