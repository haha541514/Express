package kyle.leis.eo.customerservice.track.dax;

import kyle.leis.eo.customerservice.track.da.WaybillfortrackQuery;

public class WaybillfortrackQueryEX extends WaybillfortrackQuery {
	/**
	 * 
	 * @param signStatusIsNull
	 *            Ç©ÊÕ×´Ì¬ÊÇ·ñÎª¿Õ
	 */
	public WaybillfortrackQueryEX(boolean signStatusIsNull) {
		m_strSelectClause = "SELECT new kyle.leis.eo.customerservice.track.dax.WaybillfortrackColumnsEX(cw.cwCode,cw.cwPieces,cw.cwChargeweight,cw.cwServerchargeweight,cw.cwCustomerewbcode,cw.cwServerewbcode,cw.cwEwbcode,cws.cwsCode,cws.cwsName,pk.pkCode,pk.pkSname,ddt.dtCode,ddt.dtHubcode,cddt.dtCode,cddt.dtHubcode,odt.dtCode,odt.dtHubcode,pm.pmCode,pm.pmName,schn.chnCode,schn.chnSname,ee.eeCode,ee.eeSname,ct.ctCode,ct.ctName,ihs.ihsCode,ihs.ihsName,sco.coCode,sco.coSname,sco.coLabelcode,cco.coCode,cco.coSname,cco.coLabelcode,abw.bwCode,abw.bwLabelcode,abw.addDate,dbw.bwCode,dbw.bwLabelcode,dbw.addDate,wbbt.wbbtLatesttrackdesc,wbbt.wbbtLatestcslogdesc,wbbt.wbbtCslogcreatedate,wbbt.wbbtSignforuser,wbbt.wbbtSignfordate,wbts.wbtsCode,wbts.wbtsName,csop.opId,csop.opName,wbts.wbtsEname,ddt.dtEname,cddt.dtEname,odt.dtEname,sop.opId,sop.opName,wbbt.wbbtLatesttrackdate,cddt.dtName) FROM TopCorewaybill as cw left join cw.tcsWaybillbatchtrack as wbbt left join wbbt.tdiWaybilltrackstatus as wbts inner join cw.tdiCorewaybillstatus as cws inner join cw.tdiProductkind as pk left join cw.tdiDistrictByDtCodeDestination as ddt left join ddt.tdiDistrict as cddt inner join cw.tdiDistrictByDtCodeOrigin as odt inner join cw.tdiPaymentmode as pm left join cw.tchnChannelByChnCodeSupplier as schn inner join cw.tdiEnterpriseelement as ee inner join cw.tdiCargotype as ct left join cw.tdiIssueholdstatus as ihs left join cw.tcoCorporationByCoCodeSupplier as sco inner join cw.tcoCorporationByCoCodeCustomer as cco inner join cco.tcoCustomer as cm left join cm.tdiOperatorByCmOpIdCservice as csop left join cm.tdiOperatorByCmOpIdSale as sop inner join cw.topBatchwaybillByBwCodeArrival as abw left join cw.topBatchwaybillByBwCodeDeparture as dbw";
		if (signStatusIsNull) {
			m_strWhereClause += " AND wbts.wbtsCode IS NULL";
		} else {
			m_strWhereClause += " AND wbts.wbtsCode IS NOT NULL";
		}
	}
}
