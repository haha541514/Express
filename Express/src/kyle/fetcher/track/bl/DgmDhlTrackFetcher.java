package kyle.fetcher.track.bl;

import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;


import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import kyle.common.explorer.HttpExplorer;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.DateUtility;
import kyle.common.util.jlang.NumberUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.render.DateRender;
import kyle.common.util.render.HashtableRender;
import kyle.common.util.textformat.bl.FText;
import kyle.common.util.textformat.bl.TextFormat;
import kyle.common.util.textformat.bl.TextFormatRule;
import kyle.common.webpageaccess.bl.APageFetcher;
import kyle.common.webpageaccess.bl.IFTextDepositor;
import kyle.common.webpageaccess.bl.IFetcher;
import kyle.common.webpageaccess.rule.bl.WebPageAccessRule;
import kyle.fetcher.track.da.WaybillforfetcherColumns;
import kyle.fetcher.track.dax.ITrackBasic;
import kyle.fetcher.track.dax.TrackWayBillDemand;
import kyle.leis.eo.customerservice.track.bl.Track;
import kyle.leis.eo.customerservice.track.dax.TrackDemand;

/**
 * User: Kyle 
 * Date: 2010-2-9 
 * Time: 15:34:29
 */
public class DgmDhlTrackFetcher extends APageFetcher {
	static Logger s_objLogger = Logger.getLogger(DgmDhlTrackFetcher.class.getName());

	private String m_strSupplier;

	private String m_strChncode;

	private String m_strCheckOutBeginDate;

	private String m_strCheckOutEndDate;

	private String m_strBusiness;

	private String m_strFetchInterval;

	private String m_strHawbCodeType; 
	
	private String m_strSsgCode; 
	
	private static Cookie[] cookies;
	
	private List<WaybillforfetcherColumns> m_listWBFFColumns;

	// 初始化参数定义，从信息抓取器定义的参数列表中，取预设置的参数。
	// 参数的名称需要在ITrackBasic中预先定义。
	private void initParameter(Hashtable<String, String> htParameter)
			throws Exception {
		int iBeginDay, iEndDay;
		String strBeginDay, strEndDay, strTFR_Code;

		m_strSupplier = htParameter.get(ITrackBasic.FETCHER_AGENT_SERVE);
		m_strChncode = htParameter.get(ITrackBasic.FETCHER_CHANNEL);
		strBeginDay = htParameter.get(ITrackBasic.FETCHER_BEGIN_DAY);  
		strEndDay = htParameter.get(ITrackBasic.FETCHER_END_DAY);      
		m_strBusiness = htParameter.get(ITrackBasic.FETCHER_BUSINESSCODE);
		m_strFetchInterval = htParameter.get(ITrackBasic.FETCHER_FETCHINTERVAL);
		m_strHawbCodeType = htParameter.get(ITrackBasic.FETCHER_HAWBCODETYPE);
		m_strSsgCode = htParameter.get(ITrackBasic.FETCHER_SSG_CODE); 
		strTFR_Code = htParameter.get(ITrackBasic.FETCHER_WPARCODE);   
		/*
		if (StringUtility.isNull(m_strSupplier)
				&& StringUtility.isNull(m_strBusiness)) {
			throw new Exception("Serve agent and Business is not available!"
					+ " It will have too many hawb to trace in one thread!");
		}
		*/
		if (StringUtility.isNull(strBeginDay)) {
			strBeginDay = "-90";
		}

		if (StringUtility.isNull(strEndDay)) {
			strEndDay = "0";
		}
		
//		m_strBusiness = "RX614770420DE"; 
		
		iBeginDay = Integer.parseInt(strBeginDay);  
		iEndDay = Integer.parseInt(strEndDay) + 1;  

		m_strCheckOutBeginDate = DateUtility.getMoveDate(iBeginDay);
		m_strCheckOutEndDate = DateUtility.getMoveDate(iEndDay);

		// 参数中预先定义业务代码，则只取一票运单查轨迹。以方便跟踪查轨迹异常情况。
		if (m_strBusiness != null)
			m_listWBFFColumns = TrackWayBillDemand.getWayBillByCwcode(m_strBusiness);
		else
			m_listWBFFColumns = TrackWayBillDemand.getWayBillForFetcher(m_strSupplier, 
					m_strCheckOutBeginDate,
					m_strCheckOutEndDate,
					m_strChncode, 
					m_strSsgCode,
					strTFR_Code);
	}

	public void fetch(WebPageAccessRule objWPA, 
			TextFormatRule objTFR,
			IFTextDepositor objFTD, 
			Hashtable<String, String> htFetcherParameter) {
		
		String strServerEwbcode, strCwcode, strChncode, strCocodeSupplier;
		long lFetchInterval;
		WaybillforfetcherColumns objWBFFColumns;
		Hashtable<String, String> htSubmitParameter;
		Hashtable<String, String> htDepositParameter;
		htFetcherParameter.put(ITrackBasic.FETCHER_WPARCODE, objWPA.getWparCode());		
		try {
			initParameter(htFetcherParameter);
		} catch (Exception e) {
			s_objLogger.warning("Init fetch parameter failed! " + e);
		}

		htSubmitParameter = new Hashtable<String, String>();
		htDepositParameter = new Hashtable<String, String>();
//		if (m_listWBFFColumns == null || m_listWBFFColumns.size() < 1) {
//			try {
//				Thread.sleep(6000000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//				s_objLogger.warning("Thread sleep failed! " + e);
//			}
//			return;
//		}
		String strFetcherstart = htFetcherParameter.get(ITrackBasic.FETCHER_START); 
		String strFetcherend = htFetcherParameter.get(ITrackBasic.FETCHER_END);
		boolean isFetched = false;
		
		//IntervalTime objIVT = new IntervalTime("Fetch start");
		
		for (int i = 0; i < m_listWBFFColumns.size(); i++) {
			
//			if (i < Integer.parseInt(strFetcherstart) || 
//					(i > Integer.parseInt(strFetcherend) && 
//							Integer.parseInt(strFetcherend) > 0))
//				continue;
			
			objWBFFColumns = m_listWBFFColumns.get(i);
			strServerEwbcode = getEwbCode(objWBFFColumns, m_strHawbCodeType);
			strCwcode = objWBFFColumns.getCwcw_code();
			strChncode = objWBFFColumns.getCwchn_code_supplier();
			strCocodeSupplier = objWBFFColumns.getCwco_code_supplier();
			if (strServerEwbcode == null) {
				continue;
			}
			// 最近取轨迹的时间
			String strLatestsystrack = "";
			try {
				strLatestsystrack = TrackDemand.getLatestsystrack(strCwcode);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			// 为空时取出货时间为最新的轨迹时间
			if (StringUtility.isNull(strLatestsystrack)) {
				float fIntervalHour = DateUtility.getIntervalHour(objWBFFColumns.getBwadd_date(),
						DateFormatUtility.getStandardSysdate());
				if (objWPA.getNullInterval() > 0 &&
						fIntervalHour < objWPA.getNullInterval())
					continue;
			} else {
				float fIntervalHour = DateUtility.getIntervalHour(strLatestsystrack,
						DateFormatUtility.getStandardSysdate());				
				if (objWPA.getLatestInterval() > 0 && 
						fIntervalHour < objWPA.getLatestInterval())
					continue;	
				// 从出货时间到现在如果超过15天，则每隔6小时取一次轨迹		
				float fIntervalAddDate = DateUtility.getIntervalHour(objWBFFColumns.getBwadd_date(),
						DateFormatUtility.getStandardSysdate());
				if (fIntervalAddDate > 15 * 24 && fIntervalHour < 6)
					continue;				
			}
			
			if (StringUtility.isNull(strChncode)) {
				strChncode = m_strChncode;
			}
			htSubmitParameter.put(ITrackBasic.SUBMIT_HAWBCODE, strServerEwbcode);
			htSubmitParameter.put(ITrackBasic.SUBMIT_DATE_YEAR, objWBFFColumns.getBwadd_date().substring(0, 4));
			htSubmitParameter.put(ITrackBasic.SUBMIT_DATE_MONTH, objWBFFColumns.getBwadd_date().substring(5, 7));
			htSubmitParameter.put(ITrackBasic.SUBMIT_DATE_DAY, objWBFFColumns.getBwadd_date().substring(8, 10));
			
			htDepositParameter.put(ITrackBasic.DEPOSITOR_COMPANYID, strCocodeSupplier);
			htDepositParameter.put(ITrackBasic.DEPOSITOR_BUSINESSCODE, strCwcode);
			htDepositParameter.put(ITrackBasic.DEPOSITOR_CHECKINDATE, objWBFFColumns.getBwadd_date());
			htDepositParameter.put(ITrackBasic.FETCHER_WPARCODE, objWPA.getWparCode());
			htDepositParameter.put(ITrackBasic.DEPOSITOR_DESTINATION, objWBFFColumns.getCdt_ename());
			if (strChncode != null)
				htDepositParameter.put(ITrackBasic.DEPOSITOR_CHANNELCODE, strChncode);
			else
				s_objLogger.warning("Default channel is not set!");
			try {
				// 预处理取轨迹数据
				preDealClue(objWPA, 
						objTFR, 
						objFTD, 
						htSubmitParameter,
						htDepositParameter);
				// 取轨迹
//				loadClue(objWPA, 
//						objTFR, 
//						objFTD, 
//						htSubmitParameter,
//						htDepositParameter);
					int iTotalRecord = 0, iTotalPage = 1;
					long lClueStart = System.currentTimeMillis();
					// 有些网站Session中包含计数器，刷新是重建客户端
					objWPA.refreshSession();
					if(cookies!=null)
					cookies =null;
					String strAddress = "https://dhlecommerce.asia/Portal/Track"; 
					String strPageContent = getContent(strAddress,"");
					int beginIndex = strPageContent.indexOf("id=\"j_id1:javax.faces.ViewState:0\"");
					int endIndex = strPageContent.indexOf("<div class=\"nr-footer\" style=\"margin-top: 20px;\">");
					if (beginIndex < 0 || endIndex < 0)
						return;
					String AWBID = strPageContent.substring(beginIndex, endIndex);
					beginIndex = AWBID.indexOf("value=")+"value=".length()+1;
					endIndex = AWBID.indexOf("\" autocomplete");
					AWBID = AWBID.substring(beginIndex, endIndex).trim();
					
					String strServerEwb = htSubmitParameter.get(ITrackBasic.SUBMIT_HAWBCODE);
					String strParameter = "javax.faces.partial.ajax=true&javax.faces.source=trackItNowForm:searchSkuBtn&javax.faces.partial.execute=@all&javax.faces.partial.render=trackItNowForm:dataList+messages&" +
							"trackItNowForm:searchSkuBtn=trackItNowForm:searchSkuBtn&trackItNowForm=trackItNowForm&trackItNowForm:trackItNowSearchBox="+strServerEwb+"&javax.faces.ViewState="+AWBID;
					strAddress = strAddress+"?"+strParameter;
					//该网站需要访问两次才能拿到详细的数据
					getContent(strAddress,strParameter);
					strAddress= strAddress+"&trackItNowForm:j_idt34:1:j_idt35=trackItNowForm:j_idt34:1:j_idt35";
					long lPageStart = System.currentTimeMillis();
					String strPage = getContent(strAddress,strParameter);
					System.out.println(strPage);
					FText objFText= TextFormat.parseText(strPage, objTFR);
					if(objFText==null)
						continue;
					int iRecord = deposit(objFTD, objFText, objTFR, htDepositParameter);
					iTotalRecord += iRecord;
					infoPageEnd(lPageStart, iTotalPage, iRecord);
					sleepInterval(objWPA.getPageInterval());
					infoClueEnd(lClueStart, iTotalPage - 1, iTotalRecord);
					sleepInterval(objWPA.getClueInterval());
					
					isFetched = true;
			} catch (Exception e) {
				e.printStackTrace();
				s_objLogger.warning("HAWB ERROR !" + e.toString()
						+ htSubmitParameter);
			}
			// 更新最近取轨迹的时间
			try {
				Track objTrack = new Track();
				objTrack.modifyLatestFetchdate(strCwcode, htFetcherParameter.toString());
			} catch (Exception ex) {
				ex.printStackTrace();
				s_objLogger.warning("UPDATE ERROR! HAWB ERROR !" + ex.toString()
						+ htSubmitParameter);				
			}
			
			// 按照设置的取轨迹间隔时间，停止取轨迹。但取轨迹时间间隔不能小于100ms
			if (m_strFetchInterval != null) {
				lFetchInterval = Integer.parseInt(m_strFetchInterval);
				if (lFetchInterval < 100) {
					lFetchInterval = 10000;
				}
				try {
					Thread.sleep(lFetchInterval);
				} catch (InterruptedException e) {
					e.printStackTrace();
					s_objLogger.warning("Thread sleep failed! " + e);
				}
			}
		}
		
		//s_objLogger.warning("---fetchinterval---" + objIVT.toString());			
		
		// 整个线程未取任何轨迹则休息100分钟
//		if (!isFetched) {
//			try {
//				Thread.sleep(6000000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//				s_objLogger.warning("Thread sleep failed! " + e);
//			}			
//		}
		System.out.println("渠道代码:" + m_strChncode + "   快件票数:"
				+ m_listWBFFColumns.size());
	}

	/**
	 * 由于快件有可能有转单号，在抓轨迹时转单号优先级最高，
	 * 其次是服务商单号，因此在获取运单号时先获得转单号，
	 * 当转单号为空时再获得服务商单号。
	 * 当定义为必须通过转单号取轨迹时，则不再取服务商运单号码。
	 * @param objTHItem
	 * @return
	 */
	private String getEwbCode(WaybillforfetcherColumns objWBFFColumns,
			String strEwbCodeType) {
		String strChannelHAWBCode, strServeHAWBCode, strHAWBCode;
		strChannelHAWBCode = objWBFFColumns.getCwcw_channelewbcode();
		strServeHAWBCode = objWBFFColumns.getCwcw_serverewbcode();
		strHAWBCode = strChannelHAWBCode;
		// 如果必须取转单号码，则不管其是否为空，强迫返回。否则，转单号码优先。
		if (StringUtility.isNull(strEwbCodeType)) {
			strHAWBCode = strServeHAWBCode;

		} else {
			if (!strEwbCodeType.equals("CHANNEL")) {
				if (StringUtility.isNull(strHAWBCode)) {
					strHAWBCode = strServeHAWBCode;
				}
			}
		}
		return strHAWBCode;
	}

	// 在取TRACK数据前，预先处理给定的参数。 供各服务渠道取轨迹时继承。
	public void preDealClue(WebPageAccessRule objWPA, TextFormatRule objTFR,
			IFTextDepositor objFTD,
			Hashtable<String, String> htSubmitParameter,
			Hashtable<String, String> htDepositParameter) {
	}
	
	public static String getContent(String strAddress,String strServerEwbcode){
		HttpClient httpClient = new HttpClient();
		if(cookies!=null)
		httpClient.getState().addCookies(cookies);
    	httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
        PostMethod postMethod = new PostMethod(strAddress);
        postMethod.addRequestHeader("Content-Type","application/json;charset=utf-8");
//        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
        
//        if(!(strServerEwbcode==null ||"".equals(strServerEwbcode))){
//        NameValuePair[] postData = new NameValuePair[9];  
//        postData[0] = new NameValuePair("javax.faces.partial.ajax", "true");  
//        postData[1] = new NameValuePair("javax.faces.source", "trackItNowForm%3AsearchSkuBtn");  
//        postData[2] = new NameValuePair("javax.faces.partial.execute", "%40all"); 
//        postData[3] = new NameValuePair("javax.faces.partial.render", "trackItNowForm%3AdataList+messages"); 
//        postData[4] = new NameValuePair("trackItNowForm:searchSkuBtn", "trackItNowForm%3AsearchSkuBtn"); 
//        postData[5] = new NameValuePair("trackItNowForm", "trackItNowForm"); 
//        postData[6] = new NameValuePair("trackItNowForm:trackItNowSearchBox", ser); 
//        postData[7] = new NameValuePair("javax.faces.ViewState", awb); 
//        postData[8] = new NameValuePair("trackItNowForm:j_idt34:1:j_idt35", "trackItNowForm%3Aj_idt34%3A1%3Aj_idt35"); 
//        postMethod.setRequestBody(postData);
//        postMethod.setRequestBody(strServerEwbcode);
//        postMethod.setRequestEntity( new StringRequestEntity(strServerEwbcode));
//        }
        String wbepageContent=null;
        try {
			httpClient.executeMethod(postMethod);
		    wbepageContent= postMethod.getResponseBodyAsString();
			cookies=httpClient.getState().getCookies(); 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			postMethod.releaseConnection();
		}
//		try {  
//	           int statusCode = httpClient.executeMethod(postMethod);  
//	           if (statusCode == HttpStatus.SC_OK) {  
//	               byte[] responseBody = postMethod.getResponseBody();  
//	               if(cookies==null)
//	     			  cookies=httpClient.getState().getCookies(); 
//	               wbepageContent = new String(responseBody);  
//	               System.out.println(wbepageContent); 
//	           }  
//	       } catch (Exception e) {  
//	    	   e.printStackTrace();
//	       }finally{  
//	        postMethod.releaseConnection();  
//	    }  
        
		return  wbepageContent;
	}
	private int deposit(IFTextDepositor objFTD, 
			FText objFText,
			TextFormatRule objTFR, 
			Hashtable<String, String> htDepositParameter) {
		// 显示给存储器的参数
		String strParameterText = HashtableRender.toText(htDepositParameter);
		//s_objLogger.info("Deposit Parameter:" + strParameterText);
		// 存储页面信息
		int iRecordCount = -1;
		long lStart = System.currentTimeMillis();
		if (objFTD != null)
			iRecordCount = objFTD.process(objFText, objTFR, htDepositParameter);
		else
			s_objLogger.warning("The depositor is null!");
		DateRender.toScreen("Deposit Time:", lStart);
		return iRecordCount;
	}
	private void infoPageEnd(long lStart, 
			int iPageSerial, 
			int iRecordCount) {
		float fPageTime = DateUtility.getInterval(lStart);
		float fRecordTime = fPageTime / iRecordCount;
		// String strPageTime = DateUtility.transferSecond(fPageTime, 3);
		String strRecordTime = NumberUtility.toPrecision(fRecordTime, 3);

		StringBuffer sbInfo = new StringBuffer();
		sbInfo.append("Fetch Page " + iPageSerial + " Time:");
		sbInfo.append(fPageTime);
		sbInfo.append(" Total Record:");
		sbInfo.append(iRecordCount);
		sbInfo.append(" Everage Time:");
		sbInfo.append(strRecordTime);
		/*
		System.out.println(DateFormatUtility.getCompactSysdate() + " "
				+ sbInfo.toString());
		s_objLogger.info(sbInfo.toString());
		*/
	}
	private void sleepInterval(long lInterval) {
		if (lInterval > 0) {
			try {
				Thread.sleep(lInterval);
			} catch (InterruptedException ex) {
				s_objLogger.warning("Sleep interrupted!");
			}
		}
	}
	private void infoClueEnd(long lStart, 
			int iPageCount, 
			int iRecordCount) {
		float fClueTime = DateUtility.getInterval(lStart);
		float fPageTime = fClueTime / Math.max(iPageCount, 1);
		float fRecordTime = fClueTime / Math.max(iRecordCount, 1);
		String strPageTime = NumberUtility.toPrecision(fPageTime, 3);
		String strRecordTime = NumberUtility.toPrecision(fRecordTime, 3);

		StringBuffer sbInfo = new StringBuffer();
		sbInfo.append("Fetch Clue Time:");
		sbInfo.append(fClueTime);
		sbInfo.append("  Total Page:");
		sbInfo.append(iPageCount);
		sbInfo.append("  Total Record:");
		sbInfo.append(iRecordCount);
		sbInfo.append("  Page Time:");
		sbInfo.append(strPageTime);
		sbInfo.append("  Record Time:");
		sbInfo.append(strRecordTime);
		/*
		System.out.println(DateFormatUtility.getCompactSysdate() + " "
				+ sbInfo.toString());
		s_objLogger.info(sbInfo.toString());
		*/
	}
}
