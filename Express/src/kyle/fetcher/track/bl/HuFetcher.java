package kyle.fetcher.track.bl;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;

import kyle.common.util.jlang.DateUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.render.DateRender;
import kyle.common.util.render.HashtableRender;
import kyle.common.util.textformat.bl.FText;
import kyle.common.util.textformat.bl.TextFormat;
import kyle.common.util.textformat.bl.TextFormatRule;
import kyle.common.webpageaccess.bl.APageFetcher;
import kyle.common.webpageaccess.bl.IFTextDepositor;
import kyle.common.webpageaccess.rule.bl.WebPageAccessRule;
import kyle.fetcher.track.da.WaybillforfetcherColumns;
import kyle.fetcher.track.dax.ITrackBasic;
import kyle.fetcher.track.dax.TrackWayBillDemand;
import kyle.fetcher.track.dax.HTTPSSecureProtocolSocketFactory;
import kyle.leis.eo.customerservice.track.bl.Track;
import kyle.leis.eo.customerservice.track.dax.TrackDemand;

/**
 * User: Kyle Date: 2010-2-9 Time: 15:34:29
 */
public class HuFetcher extends APageFetcher {
	static Logger s_objLogger = Logger.getLogger(HuFetcher.class.getName());

	private String m_strSupplier;

	private String m_strChncode;

	private String m_strCheckOutBeginDate;

	private String m_strCheckOutEndDate;

	private String m_strBusiness;

	private String m_strFetchInterval;

	private String m_strHawbCodeType;

	private String m_strSsgCode;

	private List<WaybillforfetcherColumns> m_listWBFFColumns;

	private static String cookie;

	private static String url;

	private static String icewindow;

	private static String iceview;

	private static String viewstate;

	private static String p1;

	private static String p2;

	private static String p3;

	private static String p4;

	private static Cookie[] cookies;
	
	private static String m_hucwCode;

	// ��ʼ���������壬����Ϣץȡ������Ĳ����б��У�ȡԤ���õĲ�����
	// ������������Ҫ��ITrackBasic��Ԥ�ȶ��塣
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
		 * if (StringUtility.isNull(m_strSupplier) &&
		 * StringUtility.isNull(m_strBusiness)) { throw new
		 * Exception("Serve agent and Business is not available!" +
		 * " It will have too many hawb to trace in one thread!"); }
		 */
		if (StringUtility.isNull(strBeginDay)) {
			strBeginDay = "-90";
		}

		if (StringUtility.isNull(strEndDay)) {
			strEndDay = "0";
		}

		// m_strBusiness = "TEST123";

		m_strBusiness = "2368909874";
		
		m_hucwCode="RR340801524HU";

		iBeginDay = Integer.parseInt(strBeginDay);
		iEndDay = Integer.parseInt(strEndDay) + 1;

		m_strCheckOutBeginDate = DateUtility.getMoveDate(iBeginDay);
		m_strCheckOutEndDate = DateUtility.getMoveDate(iEndDay);

		// ������Ԥ�ȶ���ҵ����룬��ֻȡһƱ�˵���켣���Է�����ٲ�켣�쳣�����
		if (m_strBusiness != null)
			m_listWBFFColumns = TrackWayBillDemand
					.getWayBillByCwcode(m_strBusiness);
		else
			m_listWBFFColumns = TrackWayBillDemand.getWayBillForFetcher(
					m_strSupplier, m_strCheckOutBeginDate,
					m_strCheckOutEndDate, m_strChncode, m_strSsgCode,
					strTFR_Code);
	}

	public void fetch(WebPageAccessRule objWPA, TextFormatRule objTFR,
			IFTextDepositor objFTD, Hashtable<String, String> htFetcherParameter) {

		String strServerEwbcode, strCwcode, strChncode, strCocodeSupplier;
		long lFetchInterval;
		WaybillforfetcherColumns objWBFFColumns;
		Hashtable<String, String> htSubmitParameter;
		Hashtable<String, String> htDepositParameter;
		htFetcherParameter.put(ITrackBasic.FETCHER_WPARCODE, objWPA
				.getWparCode());
		try {
			initParameter(htFetcherParameter);
		} catch (Exception e) {
			s_objLogger.warning("Init fetch parameter failed! " + e);
		}

		htSubmitParameter = new Hashtable<String, String>();
		htDepositParameter = new Hashtable<String, String>();
		if (m_listWBFFColumns == null || m_listWBFFColumns.size() < 1) {
			try {
				// Thread.sleep(6000000);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				s_objLogger.warning("Thread sleep failed! " + e);
			}
			return;
		}
		boolean isFetched = false;

		// IntervalTime objIVT = new IntervalTime("Fetch start");

		for (int i = 0; i < m_listWBFFColumns.size(); i++) {
			/*
			 * if (i < Integer.parseInt(strFetcherstart) || (i >
			 * Integer.parseInt(strFetcherend) &&
			 * Integer.parseInt(strFetcherend) > 0)) continue;
			 */
			objWBFFColumns = m_listWBFFColumns.get(i);
			strServerEwbcode = getEwbCode(objWBFFColumns, m_strHawbCodeType);
			strCwcode = objWBFFColumns.getCwcw_code();
			strChncode = objWBFFColumns.getCwchn_code_supplier();
			strCocodeSupplier = objWBFFColumns.getCwco_code_supplier();
			if (strServerEwbcode == null) {
				continue;
			}
			// ���ȡ�켣��ʱ��
			String strLatestsystrack = "";
			try {
				strLatestsystrack = TrackDemand.getLatestsystrack(strCwcode);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			// Ϊ��ʱȡ����ʱ��Ϊ���µĹ켣ʱ��
			/*
			 * if (StringUtility.isNull(strLatestsystrack)) { float
			 * fIntervalHour =
			 * DateUtility.getIntervalHour(objWBFFColumns.getBwadd_date(),
			 * DateFormatUtility.getStandardSysdate()); if
			 * (objWPA.getNullInterval() > 0 && fIntervalHour <
			 * objWPA.getNullInterval()) continue; } else { float fIntervalHour
			 * = DateUtility.getIntervalHour(strLatestsystrack,
			 * DateFormatUtility.getStandardSysdate()); if
			 * (objWPA.getLatestInterval() > 0 && fIntervalHour <
			 * objWPA.getLatestInterval()) continue; //
			 * �ӳ���ʱ�䵽�����������15�죬��ÿ��6Сʱȡһ�ι켣 float fIntervalAddDate =
			 * DateUtility.getIntervalHour(objWBFFColumns.getBwadd_date(),
			 * DateFormatUtility.getStandardSysdate()); if (fIntervalAddDate >
			 * 15 * 24 && fIntervalHour < 6) continue; }
			 */
			if (StringUtility.isNull(strChncode)) {
				strChncode = m_strChncode;
			}
			htSubmitParameter
					.put(ITrackBasic.SUBMIT_HAWBCODE, strServerEwbcode);
			htSubmitParameter.put(ITrackBasic.SUBMIT_DATE_YEAR, objWBFFColumns
					.getBwadd_date().substring(0, 4));
			htSubmitParameter.put(ITrackBasic.SUBMIT_DATE_MONTH, objWBFFColumns
					.getBwadd_date().substring(5, 7));
			htSubmitParameter.put(ITrackBasic.SUBMIT_DATE_DAY, objWBFFColumns
					.getBwadd_date().substring(8, 10));
			htDepositParameter.put(ITrackBasic.DEPOSITOR_COMPANYID,
					strCocodeSupplier);
			htDepositParameter.put(ITrackBasic.DEPOSITOR_BUSINESSCODE,
					strCwcode);
			htDepositParameter.put(ITrackBasic.DEPOSITOR_CHECKINDATE,
					objWBFFColumns.getBwadd_date());
			htDepositParameter.put(ITrackBasic.FETCHER_WPARCODE, objWPA
					.getWparCode());
			htDepositParameter.put(ITrackBasic.DEPOSITOR_DESTINATION,
					objWBFFColumns.getCdt_ename());
			htDepositParameter.put("wbtFromCode", m_hucwCode);
			if (strChncode != null)
				htDepositParameter.put(ITrackBasic.DEPOSITOR_CHANNELCODE,
						strChncode);
			else
				s_objLogger.warning("Default channel is not set!");
			try {
				// Ԥ����ȡ�켣����
				preDealClue(objWPA, objTFR, objFTD, htSubmitParameter,
						htDepositParameter);
				// ȡ�켣
				// loadClue(objWPA,
				// objTFR,
				// objFTD,
				// htSubmitParameter,
				// htDepositParameter);
				// isFetched = true;

			} catch (Exception e) {
				e.printStackTrace();
				s_objLogger.warning("HAWB ERROR !" + e.toString()
						+ htSubmitParameter);
			}

			HttpClient client = new HttpClient();
			// �����������ȡ��Ϣ�ύ����
			goPage(client);
			//
			String strPageContent = getPageContent(client);
			//System.out.println(strPageContent);
			FText objFText = TextFormat.parseText(strPageContent, objTFR);

			if (objFText == null)
				continue;
			int iTotalRecord = 0;
			int iRecord = deposit(objFTD, objFText, objTFR, htDepositParameter);
			iTotalRecord += iRecord;
			sleepInterval(objWPA.getPageInterval());
			sleepInterval(objWPA.getClueInterval());
			isFetched = true;

			// �������ȡ�켣��ʱ��
			try {
				Track objTrack = new Track();
				objTrack.modifyLatestFetchdate(strCwcode, htFetcherParameter
						.toString());
			} catch (Exception ex) {
				ex.printStackTrace();
				s_objLogger.warning("UPDATE ERROR! HAWB ERROR !"
						+ ex.toString() + htSubmitParameter);
			}

			// �������õ�ȡ�켣���ʱ�䣬ֹͣȡ�켣����ȡ�켣ʱ��������С��100ms
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

		// s_objLogger.warning("---fetchinterval---" + objIVT.toString());

		// �����߳�δȡ�κι켣����Ϣ100����
		if (!isFetched) {
			try {
				// Thread.sleep(6000000);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				s_objLogger.warning("Thread sleep failed! " + e);
			}
		}
		System.out.println("��������:" + m_strChncode + "   ���Ʊ��:"
				+ m_listWBFFColumns.size());
	}

	/**
	 * ���ڿ���п�����ת���ţ���ץ�켣ʱת�������ȼ���ߣ� ����Ƿ����̵��ţ�����ڻ�ȡ�˵���ʱ�Ȼ��ת���ţ� ��ת����Ϊ��ʱ�ٻ�÷����̵��š�
	 * ������Ϊ����ͨ��ת����ȡ�켣ʱ������ȡ�������˵����롣
	 * 
	 * @param objTHItem
	 * @return
	 */
	private String getEwbCode(WaybillforfetcherColumns objWBFFColumns,
			String strEwbCodeType) {
		String strChannelHAWBCode, strServeHAWBCode, strHAWBCode;
		strChannelHAWBCode = objWBFFColumns.getCwcw_channelewbcode();
		strServeHAWBCode = objWBFFColumns.getCwcw_serverewbcode();
		strHAWBCode = strChannelHAWBCode;
		// �������ȡת�����룬�򲻹����Ƿ�Ϊ�գ�ǿ�ȷ��ء�����ת���������ȡ�
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

	// ��ȡTRACK����ǰ��Ԥ�ȴ�������Ĳ����� ������������ȡ�켣ʱ�̳С�
	public void preDealClue(WebPageAccessRule objWPA, TextFormatRule objTFR,
			IFTextDepositor objFTD,
			Hashtable<String, String> htSubmitParameter,
			Hashtable<String, String> htDepositParameter) {
	}

	// ��һ�η��ʽ���
	public static void goPage(HttpClient client) {
		// HttpClient client = new HttpClient();
		GetMethod getmethod = new GetMethod("https://posta.hu/tracking");
		try {
			getmethod
					.setRequestHeader("Accept",
							"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			getmethod.setRequestHeader("Accept-Encoding", "gzip,deflate,sdch");
			getmethod.setRequestHeader("Accept-Language",
					"zh-CN,zh;q=0.8,en;q=0.6,hu;q=0.4");
			getmethod.setRequestHeader("Connection", "keep-alive");
			getmethod.setRequestHeader("Host", "posta.hu");
			getmethod
					.setRequestHeader(
							"User-Agent",
							"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
			Protocol https = new Protocol("https",
					new HTTPSSecureProtocolSocketFactory(), 443);
			Protocol.registerProtocol("https", https);
			client.executeMethod(getmethod);
			String pageContent = getmethod.getResponseBodyAsString();
			// ��ȡ�´��ύ����
			cutForm(pageContent);
			Protocol.unregisterProtocol("https");
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(getmethod.getResponseHeader("Set-Cookie"));
		// System.out.println(getmethod.getResponseHeaders("Set-Cookie").length);
		url = "jsessionid"
				+ getmethod.getResponseHeaders("Set-Cookie")[1].getValue()
						.split(";")[0].replace("nyomkovetes_JSESSIONID", "");
		p1 = getmethod.getResponseHeaders("Set-Cookie")[0].getValue()
				.split(";")[0]
				+ ";";
		p2 = " "
				+ getmethod.getResponseHeaders("Set-Cookie")[1].getValue()
						.split(";")[0] + ";";
		p3 = " "
				+ getmethod.getResponseHeaders("Set-Cookie")[2].getValue()
						.split(";")[0] + ";";
		p4 = " "
				+ getmethod.getResponseHeaders("Set-Cookie")[3].getValue()
						.split(";")[0] + ";";
		cookie = p1 + p2 + p3 + p4;
		// cookie=p1+p2+p3+p4;

		System.out.println("cookie=" + cookie);
	}

	public static void cutForm(String pageCotent) {
		icewindow = pageCotent.substring(pageCotent.indexOf("ice.window") + 33,
				pageCotent.indexOf("ice.view") - 17);
		iceview = pageCotent.substring(pageCotent.indexOf("ice.view") + 31,
				pageCotent.indexOf("nyomkoveto_captureSubmit") - 27);
		viewstate = pageCotent.substring(pageCotent
				.indexOf("javax.faces.ViewState") + 57, pageCotent
				.indexOf("autocomplete") - 2);
		// System.out.println(cookie);

	}

	// �ύ���������ȡ���
	public static String getPageContent(HttpClient client) {
		PostMethod postMethod = new PostMethod(
				"https://posta.hu/mpecom-sa-nyomkoveto-ui/nyomkoveto.jsf");
		Protocol https = new Protocol("https",
				new HTTPSSecureProtocolSocketFactory(), 443);
		Protocol.registerProtocol("https", https);
		postMethod.setRequestHeader("Accept", "*/*");
		postMethod.setRequestHeader("Accept-Encoding", "gzip,deflate,sdch");
		postMethod.setRequestHeader("Accept-Language",
				"zh-CN,zh;q=0.8,en;q=0.6,hu;q=0.4");
		postMethod.setRequestHeader("Connection", "keep-alive");
		postMethod.setRequestHeader("Content-Length", "779");
		postMethod.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		postMethod.setRequestHeader("Cookie", cookie);
		postMethod.setRequestHeader("Faces-Request", "partial/ajax");
		postMethod.setRequestHeader("Host", "posta.hu");
		postMethod.setRequestHeader("Origin", "https://posta.hu");
		postMethod.setRequestHeader("Referer", "https://posta.hu/tracking");
		postMethod
				.setRequestHeader(
						"User-Agent",
						"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
		postMethod.setRequestBody(new NameValuePair[] {
				new NameValuePair("nyomkoveto", "nyomkoveto"),
				new NameValuePair("ice.window", icewindow),
				new NameValuePair("ice.view", iceview),
				new NameValuePair("nyomkoveto:documentnumber:input",
						m_hucwCode),
				new NameValuePair("icefacesCssUpdates", ""),
				new NameValuePair("javax.faces.ViewState", viewstate),
				new NameValuePair("javax.faces.source",
						"nyomkoveto:pushBtnActionList"),
				new NameValuePair("javax.faces.partial.event", "click"),
				new NameValuePair("javax.faces.partial.execute", "@all"),
				new NameValuePair("javax.faces.partial.render", "@all"),
				new NameValuePair("ice.window", icewindow),
				new NameValuePair("ice.window", iceview),
				new NameValuePair("ice.focus",
						"nyomkoveto:pushBtnActionList_span-button"),
				new NameValuePair("ice.event.target", ""),
				new NameValuePair("ice.event.captured",
						"nyomkoveto:pushBtnActionList"),
				new NameValuePair("ice.event.type", "onclick"),
				new NameValuePair("ice.event.alt", "false"),
				new NameValuePair("ice.event.ctrl", "false"),
				new NameValuePair("ice.event.shift", "false"),
				new NameValuePair("ice.event.meta", "false"),
				new NameValuePair("ice.event.x", "768"),
				new NameValuePair("ice.event.y", "798"),
				new NameValuePair("ice.event.left", "true"),
				new NameValuePair("ice.event.right", "false"),
				new NameValuePair("ice.submit.type", "ice.s"),
				new NameValuePair("ice.submit.serialization", "form"),
				new NameValuePair("javax.faces.partial.ajax", "true"), });
		String pageContent = "";
		// postMethod.addRequestHeader("","");
		try {
			client.getHttpConnectionManager().getParams().setConnectionTimeout(
					30000);
			client.executeMethod(postMethod);
			Protocol.unregisterProtocol("https");
			pageContent = postMethod.getResponseBodyAsString();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageContent;
	}

	private int deposit(IFTextDepositor objFTD, FText objFText,
			TextFormatRule objTFR, Hashtable<String, String> htDepositParameter) {
		// ��ʾ���洢���Ĳ���
		String strParameterText = HashtableRender.toText(htDepositParameter);
		// s_objLogger.info("Deposit Parameter:" + strParameterText);
		// �洢ҳ����Ϣ
		int iRecordCount = -1;
		long lStart = System.currentTimeMillis();
		if (objFTD != null)
			iRecordCount = objFTD.process(objFText, objTFR, htDepositParameter);
		else
			s_objLogger.warning("The depositor is null!");
		DateRender.toScreen("Deposit Time:", lStart);
		return iRecordCount;
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
}
