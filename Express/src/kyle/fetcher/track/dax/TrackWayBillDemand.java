package kyle.fetcher.track.dax;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.fetcher.track.da.ChanneltrackmappingColumns;
import kyle.fetcher.track.da.ChanneltrackmappingQuery;
import kyle.fetcher.track.da.ExisttrackQuery;
import kyle.fetcher.track.da.MaxtrackdateColumns;
import kyle.fetcher.track.da.MaxtrackdateQuery;
import kyle.fetcher.track.da.PoddatevalidateColumns;
import kyle.fetcher.track.da.PoddatevalidateQuery;
import kyle.fetcher.track.da.TrackstatusColumns;
import kyle.fetcher.track.da.TrackstatusQuery;
import kyle.fetcher.track.da.WaybillforfetcherColumns;
import kyle.fetcher.track.da.WaybillforfetcherQuery;

/**
 * User: Kyle 
 * Date: 2010-2-9 
 * Time: 11:42:58
 */
public class TrackWayBillDemand {
	static Logger s_objLogger = Logger.getLogger(TrackWayBillDemand.class.getName());

	public static List<WaybillforfetcherColumns> getWayBillForFetcher(
			String strAgentServe, 
			String strDateFrom, 
			String strDateTo,
			String strCHNCodeServe, 
			String strSsgCode,
			String strWPARCode) {
		return getWayBillForFetcher(strAgentServe, 
				strDateFrom, 
				strDateTo,
				null, 
				null, 
				strCHNCodeServe, 
				strSsgCode,
				strWPARCode);
	}

	public static List<WaybillforfetcherColumns> getWayBillForFetcher(
			String strCwcode) {
		return getWayBillForFetcher(null, 
				null, 
				null, 
				strCwcode, 
				null,
				null, 
				null,
				null);
	}

	public static List<WaybillforfetcherColumns> getWayBillByCwcode(
			String strServeHAWBCode) {
		return getWayBillForFetcher(null, 
				null, 
				null, 
				null, 
				strServeHAWBCode,
				null,
				null, 
				null);
	}

	private static List<WaybillforfetcherColumns> getWayBillForFetcher(
			String strAgentServe, 
			String strDateFrom, 
			String strDateTo,
			String strCwcode, 
			String strServeHAWBCode, 
			String strCHNCodeServe,
			String strSsgCode,
			String strWPARCode) {
		WaybillforfetcherQuery objWFFQuery = new WaybillforfetcherQuery();
		try {
			/*
			 * 展示Query的面向对象用法，此用法适合人工界面编程，只需要从后台获得 数据内容
			 */
			// 设置查询条件,请自行补充查询条件具体值
			objWFFQuery.setCocodesupplier(strAgentServe);
			// objWFFQuery.setStartarrivaldate("2010-03-06 10:51:11");
			if (StringUtility.isNull(strDateFrom))
				strDateFrom = "2010-03-06 10:51:11";
			objWFFQuery.setStartarrivaldate(strDateFrom);
			objWFFQuery.setEndarrivaldate(strDateTo);
			objWFFQuery.setCwcode(strCwcode);
			// objWFFQuery.setCwcode("4096");
			objWFFQuery.setCwserverewbcode(strServeHAWBCode);
			// objWFFQuery.setCwserverewbcode("2268080485");
			objWFFQuery.setWpacode(strWPARCode);
			if (strCHNCodeServe != null)
				objWFFQuery.setChncodesupplier(strCHNCodeServe);
			objWFFQuery.setSsgcode(strSsgCode);
			// 获得数据
			List objList = objWFFQuery.getResults();
			List<WaybillforfetcherColumns> listResults = new ArrayList<WaybillforfetcherColumns>();
			for (int i = 0; i < objList.size(); i++)
				listResults.add((WaybillforfetcherColumns) objList.get(i));
			return listResults;
		} catch (Exception ex) {
			s_objLogger.warning("Get trace hawb failed! " + ex);
			return null;
		}
	}

	public String getMaxTrackDate(String strCwcode) {
		MaxtrackdateColumns objMaxtrackdateColumns;
		MaxtrackdateQuery objMTD;
		objMTD = new MaxtrackdateQuery();
		try {
			objMTD.setCwcode(strCwcode);
			List objList = objMTD.getResults();
			if (objList == null || objList.size() < 1) 
				return null;
			objMaxtrackdateColumns = (MaxtrackdateColumns) objList.get(0);
			return objMaxtrackdateColumns.getMaxoccurdate();
		} catch (Exception ex) {
			s_objLogger.warning("Get business " + strCwcode
					+ " max track date failed! " + ex);
			return null;
		}
	}

	public static boolean existTrack(String strCwcode, 
			String strCocode,
			String strWbtdescription, 
			String strWbtlocation,
			String strWbtOccurdate) {
		try {
			ExisttrackQuery objExisttrackQuery = new ExisttrackQuery();
			objExisttrackQuery.setCwcode(strCwcode);
			objExisttrackQuery.setCocode(strCocode);
			objExisttrackQuery.setWbtdescription(strWbtdescription);
			// 查询语句不在把地址为空的记录，反复添加到详细轨迹中。
			if (StringUtility.isNull(strWbtlocation)) { 
				// 这个语句的作用是不把地址放到查询语句中。
				objExisttrackQuery.setWbtlocation(strWbtlocation);
			}
			objExisttrackQuery.setWbtoccurdate(strWbtOccurdate);
			List objList = objExisttrackQuery.getResults();
			if (objList != null && objList.size() > 0)
				return true;
			else 
				return false;
		} catch (Exception ex) {
			s_objLogger.warning("Search track failed! " + ex);
			return true;
		}
	}

	// 查找本次取轨迹以前，是否已经通过其它的轨迹规则取到轨迹。
	public static boolean getPodDateValidate(String strCwcode, 
			String strPodDate) {
		PoddatevalidateQuery objPDVQ = new PoddatevalidateQuery();
		boolean blValidate = false;
		int intValidateRows;
		// 设置初始值
		objPDVQ.setCwcode(strCwcode);
		objPDVQ.setStartarrivaldate(strPodDate.replaceAll("''", "'"));
		objPDVQ.setEndarrivaldate(DateFormatUtility.getStandardSysdate());
		// 判断POD时间的有效性。POD时间应该在我司签出后且在当前系统时间前。
		try {
			List objList = objPDVQ.getResults();
			if (objList != null && objList.size() == 1) {
				PoddatevalidateColumns objPDVDColumns = (PoddatevalidateColumns)objList.get(0);
				intValidateRows = Integer.parseInt(objPDVDColumns.getValidaterow());
				if (intValidateRows > 0)
					blValidate = true;
				else
					blValidate = false;
			} else {
				blValidate = false;
			}
		} catch (Exception ex) {
			s_objLogger.warning("Get trace hawb failed! " + ex);
			blValidate = false;
		}
		return blValidate;
	}

	public static String mapTrackStatus(String strChncode,
			String strCtmsourcetrackdesc) {
		try {
			ChanneltrackmappingQuery objCTMQuery = new ChanneltrackmappingQuery();
			// objCTMQuery.setChncode(strChncode);
			objCTMQuery.setCtmeqsourcetrackdesc(strCtmsourcetrackdesc);
			List objList = objCTMQuery.getResults();
			if (objList == null || objList.size() < 1) 
				return null;
			ChanneltrackmappingColumns objCTMColumns = (ChanneltrackmappingColumns)objList.get(0);
			return objCTMColumns.getWbtswbtscode();
		} catch (Exception ex) {
			s_objLogger.warning("Get track status code failed! " + ex);
			return null;
		}
	}

	// 查找指定的运单轨迹是否存在于轨迹对照表中。
	public static boolean existMapTrack(String strChncode,
			String strCtmsourcetrackdesc) {
		String strMappingTrackDesc = mapTrackStatus(strChncode, strCtmsourcetrackdesc);
		if (StringUtility.isNull(strMappingTrackDesc))
			return false;
		return true;
	}

	// 查找指定的运单中，是否已经处于完成状态。
	public static boolean existWayBillFinishStatus(String strCwcode) {
		TrackstatusQuery objTrackstatusQuery = new TrackstatusQuery();
		boolean blExistHawbFinish = false;
		String strWbtscode = "";
		String strSignForuser = "";
		try {
			// 设置查询条件,请自行补充查询条件具体值
			objTrackstatusQuery.setCwcode(strCwcode);
			// 获得数据
			List objList = objTrackstatusQuery.getResults();
			// 显示所有数据
			if (objList == null || objList.size() < 1)
				return false;
			TrackstatusColumns objTSColumns = (TrackstatusColumns)objList.get(0);
			strWbtscode = objTSColumns.getWbbtwbts_code();
			strSignForuser = objTSColumns.getWbbtwbbt_signforuser();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (StringUtility.isNull(strWbtscode))
			blExistHawbFinish = false;
		// 签收且签收人不为空
		else if ((strWbtscode.startsWith("OK") || strWbtscode.equals("SF")) &&
				!StringUtility.isNull(strSignForuser))
			blExistHawbFinish = true;
		else if (strWbtscode.equals("CC") || strWbtscode.equals("RE"))
			blExistHawbFinish = true;
		return blExistHawbFinish;
	}

	// 查找本次取轨迹以前，是否已经通过其它的轨迹规则取到轨迹。
	public static boolean existDifferTrackWebSite(String strCwcode,
			String strWPARCode) {
		TrackstatusQuery objTrackstatusQuery = new TrackstatusQuery();
		String strWebSite = "";
		boolean blExistDifferTrackWebSite = false;
		try {
			// 设置查询条件,请自行补充查询条件具体值
			objTrackstatusQuery.setCwcode(strCwcode);
			// 获得数据
			List objList = objTrackstatusQuery.getResults();
			if (objList == null || objList.size() < 1)
				return false;	
			TrackstatusColumns objTSColumns = (TrackstatusColumns)objList.get(0);
			strWebSite = objTSColumns.getWbbtwpa_code();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (StringUtility.isNull(strWebSite) || (strWebSite.equals(strWPARCode))) {
			blExistDifferTrackWebSite = false;
		} else {
			blExistDifferTrackWebSite = true;
		}
		return blExistDifferTrackWebSite;
	}

}
