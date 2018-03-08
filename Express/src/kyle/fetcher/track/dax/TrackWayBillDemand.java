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
			 * չʾQuery����������÷������÷��ʺ��˹������̣�ֻ��Ҫ�Ӻ�̨��� ��������
			 */
			// ���ò�ѯ����,�����в����ѯ��������ֵ
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
			// �������
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
			// ��ѯ��䲻�ڰѵ�ַΪ�յļ�¼��������ӵ���ϸ�켣�С�
			if (StringUtility.isNull(strWbtlocation)) { 
				// ������������ǲ��ѵ�ַ�ŵ���ѯ����С�
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

	// ���ұ���ȡ�켣��ǰ���Ƿ��Ѿ�ͨ�������Ĺ켣����ȡ���켣��
	public static boolean getPodDateValidate(String strCwcode, 
			String strPodDate) {
		PoddatevalidateQuery objPDVQ = new PoddatevalidateQuery();
		boolean blValidate = false;
		int intValidateRows;
		// ���ó�ʼֵ
		objPDVQ.setCwcode(strCwcode);
		objPDVQ.setStartarrivaldate(strPodDate.replaceAll("''", "'"));
		objPDVQ.setEndarrivaldate(DateFormatUtility.getStandardSysdate());
		// �ж�PODʱ�����Ч�ԡ�PODʱ��Ӧ������˾ǩ�������ڵ�ǰϵͳʱ��ǰ��
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

	// ����ָ�����˵��켣�Ƿ�����ڹ켣���ձ��С�
	public static boolean existMapTrack(String strChncode,
			String strCtmsourcetrackdesc) {
		String strMappingTrackDesc = mapTrackStatus(strChncode, strCtmsourcetrackdesc);
		if (StringUtility.isNull(strMappingTrackDesc))
			return false;
		return true;
	}

	// ����ָ�����˵��У��Ƿ��Ѿ��������״̬��
	public static boolean existWayBillFinishStatus(String strCwcode) {
		TrackstatusQuery objTrackstatusQuery = new TrackstatusQuery();
		boolean blExistHawbFinish = false;
		String strWbtscode = "";
		String strSignForuser = "";
		try {
			// ���ò�ѯ����,�����в����ѯ��������ֵ
			objTrackstatusQuery.setCwcode(strCwcode);
			// �������
			List objList = objTrackstatusQuery.getResults();
			// ��ʾ��������
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
		// ǩ����ǩ���˲�Ϊ��
		else if ((strWbtscode.startsWith("OK") || strWbtscode.equals("SF")) &&
				!StringUtility.isNull(strSignForuser))
			blExistHawbFinish = true;
		else if (strWbtscode.equals("CC") || strWbtscode.equals("RE"))
			blExistHawbFinish = true;
		return blExistHawbFinish;
	}

	// ���ұ���ȡ�켣��ǰ���Ƿ��Ѿ�ͨ�������Ĺ켣����ȡ���켣��
	public static boolean existDifferTrackWebSite(String strCwcode,
			String strWPARCode) {
		TrackstatusQuery objTrackstatusQuery = new TrackstatusQuery();
		String strWebSite = "";
		boolean blExistDifferTrackWebSite = false;
		try {
			// ���ò�ѯ����,�����в����ѯ��������ֵ
			objTrackstatusQuery.setCwcode(strCwcode);
			// �������
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
