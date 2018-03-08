package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ForinputallQuery extends HGeneralQuery {
	
	public ForinputallQuery(){
	    m_strSelectClause = "select new kyle.leis.eo.operation.housewaybill.da.ForinputallColumns(bw.bwCode,bw.addDate,abwchn.chnCode,abwchn.chnSname,abwchn.chnSename,cwee.eeCode,cwee.eeSname,abwco.coCode,abwco.coSname,cw.cwCode,cw.cwPostcodeDestination,cw.cwPieces,cw.cwGrossweight,cw.cwChargeweight,cw.cwTransferpieces,cw.cwTransfergrossweight,cw.cwTransferchargeweight,cw.cwServerchargeweight,cw.cwCustomerewbcode,cw.cwServerewbcode,cw.cwChannelewbcode,cw.cwEwbcode,cw.cwOpIdCreator,cw.cwCreatedate,cwpm.pmCode,cwpm.pmName,cwct.ctCode,cwct.ctName,cwspchn.chnCode,cwspchn.chnSename,cwcuschn.chnCode,cwcuschn.chnSename,cwpk.pkCode,cwpk.pkSname,cwpk.pkSename,cwddt.dtCode,cwddt.dtHubcode,cwddt.dtEname,cwodt.dtCode,cwodt.dtHubcode,cwodt.dtEname,cwcs.cwsCode,cwcs.cwsName,cwdbm.bwCode,cwabm.bwCode,cwcus.coCode,cwcus.coSname,cwsp.coCode,cwsp.coSname,hw.hwShipperaccount,hw.hwShippername,hw.hwShippercompany,hw.hwShipperaddress1,hw.hwShipperaddress2,hw.hwShipperaddress3,hw.hwShipperpostcode,hw.hwShippertelephone,hw.hwShipperfax,hw.hwConsigneename,hw.hwConsigneecompany,hw.hwConsigneepostcode,hw.hwConsigneeaddress1,hw.hwConsigneeaddress2,hw.hwConsigneeaddress3,hw.hwConsigneetelephone,hw.hwConsigneeaccount,hw.hwConsigneefax,hw.hwSignindate,hw.hwOpIdSignin,hw.hwOpIdWeighting,hw.hwSignoutdate,hw.hwOpIdSignout,hw.hwRecorddate,hw.hwOpIdRecord,hw.hwOpIdPacking,hw.hwInsurancevalue, hw.hwInsurancesign, hw.hwConsigneecity, dtShipper.dtCode,cw.cwTransfervolumeweight,cw.cwVolumerate,cw.cwTransfervolumerate,ckinsurance.ckCode,hw.hwLabelprinttimes,cwct.ctEname,cwct.ctSename,cwcs.cwsEname,sidt.dtCode,sidt.dtHubcode,serverwbck.bckCode,hw.hwServerewbchangedsign,subwbck.bckCode, hw.hwLabelprintremark, hw.hwCargoprinttimes, hw.hwRemark, hw.hwTransactionid, hw.hwBuyerid, cw.cwCustomerchargeweight, hw.hwConsigneeaddressex, hw.hwConsigneenameex, cw.znvName, hw.hwElectricmark, hw.hwSewbchangedbywebsign, hw.hwBookingid, hw.hwConsigneecityex, hw.hwChannelmasteraccount, hw.hwPaymentaccount, dat.dtCode, bak.bkCode, pat.patCode, cgk.cgkCode, hw.hwDutypaidsign,hw.hwDcustomssign,it.iptCode,ify.ibCode) FROM TopCorewaybill as cw inner join cw.topHousewaybill as hw inner join cw.topBatchwaybillByBwCodeArrival as bw left join bw.tchnChannel as abwchn inner join cw.tdiEnterpriseelement as cwee inner join bw.tcoCorporation as abwco inner join cw.tdiPaymentmode as cwpm inner join cw.tdiCargotype as cwct left join cw.tchnChannelByChnCodeSupplier as cwspchn left join cw.tchnChannelByChnCodeCustomer as cwcuschn inner join cw.tdiProductkind as cwpk left join cw.tdiDistrictByDtCodeDestination as cwddt inner join cw.tdiDistrictByDtCodeOrigin as cwodt inner join cw.tdiCorewaybillstatus as cwcs left join  cw.topBatchwaybillByBwCodeDeparture as cwdbm left join cw.topBatchwaybillByBwCodeArrival as cwabm left join cw.tcoCorporationByCoCodeCustomer as cwcus left join cw.tcoCorporationByCoCodeSupplier as cwsp left join hw.tdiDistrict as dtShipper left join hw.tdiCurrencykind as ckinsurance left join cw.tdiDistrictByDtCodeSignin as sidt left join hw.tdiWaybillcodekindByHwServerewbkind as serverwbck left join hw.tdiWaybillcodekindByHwSubchildewbkind as subwbck left join hw.tdiDeliverytype as dat left join hw.tdiBatterykind as bak left join hw.tdiPackagetype as pat left join hw.tdiCargokind as cgk left join hw.tdiInvoiceprinttype as it left join hw.tdiInsurancebeneficiary as ify";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cwCustomerewbcode = '~~'", "cw.cwServerewbcode = '~~'", "cw.cwEwbcode = '~~'", "cw.cwCode = ~~", "cwcus.coCode = ~~", "cwcs.cwsCode not in (~~)" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	} 

}
