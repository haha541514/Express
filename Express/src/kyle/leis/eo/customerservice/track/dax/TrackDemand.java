package kyle.leis.eo.customerservice.track.dax;

import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.customerservice.track.da.LatestsystrackColumns;
import kyle.leis.eo.customerservice.track.da.LatestsystrackQuery;
import kyle.leis.eo.customerservice.track.da.WaybillbatchtrackColumns;
import kyle.leis.eo.customerservice.track.da.WaybillbatchtrackCondition;
import kyle.leis.eo.customerservice.track.da.WaybillbatchtrackQuery;
import kyle.leis.eo.customerservice.track.da.WaybillfortrackCondition;
import kyle.leis.eo.customerservice.track.da.WaybillfortrackQuery;
import kyle.leis.eo.customerservice.track.da.WaybillfortrackclientCondition;
import kyle.leis.eo.customerservice.track.da.WaybillfortrackclientQuery;
import kyle.leis.eo.customerservice.track.da.WaybilltrackColumns;
import kyle.leis.eo.customerservice.track.da.WaybilltrackCondition;
import kyle.leis.eo.customerservice.track.da.WaybilltrackQuery;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillColumns;
import kyle.leis.eo.operation.corewaybill.dax.SimplecorewaybillQueryEx;
import kyle.leis.fs.dictionary.dictionarys.da.TdiDistrictDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiWaybilltrackstatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TfsWebpageaccessDC;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TcsWaybillbatchtrack;
import kyle.leis.hi.TcsWaybilltrack;
import kyle.leis.hi.TdiDistrict;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiWaybilltrackstatus;
import kyle.leis.hi.TfsWebpageaccess;
import net.sf.hibernate.Session;

public class TrackDemand {
	public static List queryWaybillfortrack(WaybillfortrackCondition objWBFTCondition) 
	throws Exception {
		WaybillfortrackQuery objWBFTQuery = new WaybillfortrackQuery();
		if(!StringUtility.isNull(objWBFTCondition.getEecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objWBFTCondition.getEecode());
			objWBFTCondition.setEestructurecode(strEestructurecode);
			objWBFTCondition.setEecode("");
		}		
		objWBFTQuery.setCondition(objWBFTCondition);
		return objWBFTQuery.getResults();
	}
	
	public static List queryWaybillfortrackclient(WaybillfortrackclientCondition objWBFTCondition) 
	throws Exception {
		WaybillfortrackclientQuery objWBFTQuery = new WaybillfortrackclientQuery();
		if(!StringUtility.isNull(objWBFTCondition.getEecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objWBFTCondition.getEecode());
			objWBFTCondition.setEestructurecode(strEestructurecode);
			objWBFTCondition.setEecode("");
		}		
		objWBFTQuery.setCondition(objWBFTCondition);
		return objWBFTQuery.getResults();
	}	
	
	
	public static List queryTrack(WaybilltrackCondition objWBTCondition)
	throws Exception {
		WaybilltrackQuery objWBTQuery = new WaybilltrackQuery();
		objWBTQuery.setCondition(objWBTCondition);
		return objWBTQuery.getResults();
	}
	
	public static List loadTracks(String strCwcode) throws Exception {
		if (StringUtility.isNull(strCwcode))
			return null;
		WaybilltrackQuery objWBTQuery = new WaybilltrackQuery();
		objWBTQuery.setCwcode(strCwcode);
		return objWBTQuery.getResults();		
	}
	
	public static List queryBatchTrack(WaybillbatchtrackCondition objWBTCondition) 
	throws Exception {
		WaybillbatchtrackQuery objWBTQuery = new WaybillbatchtrackQuery();
		objWBTQuery.setCondition(objWBTCondition);
		return objWBTQuery.getResults();
	}
	
	public static String getLatestsystrack(String strCwcode) throws Exception {
		LatestsystrackQuery objLSTQ = new LatestsystrackQuery();
		objLSTQ.setCwcode(strCwcode);
		List listResult = objLSTQ.getResults();
		if (listResult == null || listResult.size() <1)
			return "";
		return ((LatestsystrackColumns)listResult.get(0)).getLatestsystrack();
	}
	
	
	public static WaybillbatchtrackColumns loadBatchTrack(String strCwcode) 
	throws Exception {
		if (StringUtility.isNull(strCwcode))
			return null;
		WaybillbatchtrackQuery objWBTQuery = new WaybillbatchtrackQuery();
		objWBTQuery.setCwcode(strCwcode);
		List objList = objWBTQuery.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (WaybillbatchtrackColumns)objList.get(0);
	}
	
	public static boolean isTrackInArrays(WaybilltrackColumns objWBTColumns,
			String[] astrWbtid) {
		if (astrWbtid == null || astrWbtid.length < 1)
			return false;
		for (int i = 0; i < astrWbtid.length; i++) {
			if (objWBTColumns.getWbtwbtid().equals(astrWbtid[i]))
				return true;
		}
		return false;
	}
	
	public static WaybilltrackColumns getLatestTrackColumns(List<WaybilltrackColumns> listWBTColumns) {
		if (listWBTColumns == null || listWBTColumns.size() < 1)
			return null;
		WaybilltrackColumns objLatestWBTColumns = listWBTColumns.get(0);
		if (listWBTColumns.size() == 1)
			return objLatestWBTColumns;
		for (int i = 1; i < listWBTColumns.size(); i++) {
			WaybilltrackColumns objWBTColumns = listWBTColumns.get(i);
			if (objWBTColumns.getWbtwbtoccurdate().compareTo(objLatestWBTColumns.getWbtwbtoccurdate()) > 0)
				objLatestWBTColumns = objWBTColumns;
		}
		return objLatestWBTColumns;
	}
	
	public static void setBatchtrackByColumns(TcsWaybillbatchtrack objWBBatchTrack,
			WaybillbatchtrackColumns objWBBTColumns) throws Exception {
		if (!StringUtility.isNull(objWBBTColumns.getWbbtcwcode()))
			objWBBatchTrack.setCwCode(Long.parseLong(objWBBTColumns.getWbbtcwcode()));
		if (!StringUtility.isNull(objWBBTColumns.getWbtswbtscode())) {
			TdiWaybilltrackstatus objTWBTStatus = TdiWaybilltrackstatusDC.loadByKey(objWBBTColumns.getWbtswbtscode());
			objWBBatchTrack.setTdiWaybilltrackstatus(objTWBTStatus);
		}
		if (!StringUtility.isNull(objWBBTColumns.getWpawpacode())) {
			TfsWebpageaccess objTWPA = TfsWebpageaccessDC.loadByKey(objWBBTColumns.getWpawpacode());
			objWBBatchTrack.setTfsWebpageaccess(objTWPA);
		}
			
		objWBBatchTrack.setWbbtLatestcslogdesc(objWBBTColumns.getWbbtwbbtlatestcslogdesc());
		if (!StringUtility.isNull(objWBBTColumns.getWbbtwbbtcslogcreatedate()))
			objWBBatchTrack.setWbbtCslogcreatedate(DateFormatUtility.getStandardDate(objWBBTColumns.getWbbtwbbtcslogcreatedate()));
			
		objWBBatchTrack.setWbbtLatesttrackdesc(objWBBTColumns.getWbbtwbbtlatesttrackdesc());
		if (!StringUtility.isNull(objWBBTColumns.getWbbtwbbtsignfordate()))
			objWBBatchTrack.setWbbtSignfordate(DateFormatUtility.getStandardDate(objWBBTColumns.getWbbtwbbtsignfordate()));
		if (!StringUtility.isNull(objWBBTColumns.getWbbtwbbtsignforuser()))
			objWBBatchTrack.setWbbtSignforuser(objWBBTColumns.getWbbtwbbtsignforuser());
		// 最新轨迹日期
		if (!StringUtility.isNull(objWBBTColumns.getWbbtwbbtlatesttrackdate()))
			objWBBatchTrack.setWbbtLatesttrackdate(DateFormatUtility.getStandardDate(objWBBTColumns.getWbbtwbbtlatesttrackdate()));

	}
	
	public static void setTrackByColumns(TcsWaybilltrack objTcsWaybilltrack,
			WaybilltrackColumns objWBTColumns,
			String strOperId,
			Session objSession) throws Exception {
		if (!StringUtility.isNull(objWBTColumns.getCococode())) {
			TcoCorporation objCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					objWBTColumns.getCococode());
			objTcsWaybilltrack.setTcoCorporation(objCorporation);
		}
		if (!StringUtility.isNull(objWBTColumns.getDtdtcode())) {
			TdiDistrict objTdiDistrict = TdiDistrictDC.loadByKey(objWBTColumns.getDtdtcode());
			objTcsWaybilltrack.setTdiDistrict(objTdiDistrict);
		}
		
		objTcsWaybilltrack.setWbtFrom(objWBTColumns.getWbtwbtfrom());
		TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(strOperId);	
		objTcsWaybilltrack.setTdiOperatorByOpIdModifier(objTdiOperator);
		objTcsWaybilltrack.setWbtModifydate(DateFormatUtility.getSysdate());
		
		if (!StringUtility.isNull(objWBTColumns.getWbtswbtscode())) {
			TdiWaybilltrackstatus objTWBTStatus = TdiWaybilltrackstatusDC.loadByKey(objWBTColumns.getWbtswbtscode());
			objTcsWaybilltrack.setTdiWaybilltrackstatus(objTWBTStatus);
		}
		objTcsWaybilltrack.setWbtDescription(objWBTColumns.getWbtwbtdescription());
		objTcsWaybilltrack.setWbtLocation(objWBTColumns.getWbtwbtlocation());
		
		if (!StringUtility.isNull(objWBTColumns.getWbtwbtoccurdate()))
			objTcsWaybilltrack.setWbtOccurdate(DateFormatUtility.getStandardDate(objWBTColumns.getWbtwbtoccurdate()));	
		String strOpenSign = objWBTColumns.getWbtwbtopensign();
		if (StringUtility.isNull(strOpenSign))
			strOpenSign = "Y";
		objTcsWaybilltrack.setWbtOpensign(strOpenSign);
	}
	
	public static SimplecorewaybillColumns findCoreWayBillByBookId(String bookId) 
			throws Exception{
		SimplecorewaybillQueryEx queryEx = new SimplecorewaybillQueryEx(bookId);
		List<?> list = queryEx.getResults();
		SimplecorewaybillColumns columns = null;
		if (!list.isEmpty()) {
			columns = (SimplecorewaybillColumns) list.get(0);
		}
		return columns;
	}
}
