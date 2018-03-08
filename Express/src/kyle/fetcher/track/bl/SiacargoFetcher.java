package kyle.fetcher.track.bl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import kyle.common.util.jlang.DateUtility;
import kyle.common.util.jlang.NumberUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.logger.LoggerConfig;
import kyle.common.util.render.DateRender;
import kyle.common.util.render.StringRender;
import kyle.common.util.textformat.bl.FText;
import kyle.common.util.textformat.bl.TextFormat;
import kyle.common.util.textformat.bl.TextFormatRule;
import kyle.common.webpageaccess.bl.FTextChecker;
import kyle.common.webpageaccess.bl.IFTextDepositor;
import kyle.common.webpageaccess.bl.IFetcher;
import kyle.common.webpageaccess.ca.WebPageAccessConfig;
import kyle.common.webpageaccess.rule.bl.WebPageAccessRule;
import kyle.fetcher.track.da.WaybillforfetcherColumns;
import kyle.fetcher.track.dax.ITrackBasic;
import kyle.leis.eo.customerservice.track.bl.Track;
import kyle.leis.eo.customerservice.track.dax.TrackDemand;
import kyle.leis.eo.operation.transportwaybill.da.TransportcorewaybillColumns;
import kyle.leis.eo.operation.transportwaybill.da.TransportcorewaybillCondition;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybillColumns;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybillCondition;
import kyle.leis.eo.operation.transportwaybill.dax.TransportWaybillDemand;

public class SiacargoFetcher extends SingleTrackFetcher {
	static Logger s_objLogger = Logger.getLogger(SingleTrackFetcher.class
			.getName());
	private String next_page;

	private String m_strSupplier;

	private String m_strChncode;

	private String m_strCheckOutBeginDate;

	private String m_strCheckOutEndDate;

	private String m_strBusiness;

	private String m_strFetchInterval;

	private String m_strHawbCodeType;

	private String m_strSsgCode;

	private List<WaybillforfetcherColumns> m_listWBFFColumns;

	private List<String> m_cwcodelist;

	private String m_twbid;

	private String m_EVENTVALIDATION = "%2FwEWEQKPpZj4AgLxz6%2FNBwLB48JgAtbmkbgNAtzM4PUKArv986IDAve1%2FooFAqCU1o0JApKfnKAPAoWruPgOAq2IurUJApr2qvYNApLXgoMNApLXjoMNApLXioMNAu%2BOvL8FAuqG4JoJP2f9FbRfZogpcVJX%2BcGdxPip9Is%3D";

	private String m_VIEWSTATE = "%2FwEPDwUJNjA4ODc2MjgzD2QWBAIBD2QWAgIKDxUFB1N1ZmZpeDEHU3VmZml4MgdTdWZmaXgzB1N1ZmZpeDQHU3VmZml4NWQCAw9kFgICAw9kFgJmD2QWBAIBDxYCHgVzdHlsZQUJZGlzcGxheTo7ZAIHDxYCHwAFDWRpc3BsYXk6bm9uZTtkZMxH7759wguFi4d80fLdFDA2mGpU";

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
				Thread.sleep(6000000);
				//Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				s_objLogger.warning("Thread sleep failed! " + e);
			}
			return;
		}
		String strFetcherstart = htFetcherParameter
				.get(ITrackBasic.FETCHER_START);
		String strFetcherend = htFetcherParameter.get(ITrackBasic.FETCHER_END);
		boolean isFetched = false;

		// IntervalTime objIVT = new IntervalTime("Fetch start");

		for (int i = 0; i < m_listWBFFColumns.size(); i++) {
			/*
			 * if (i < Integer.parseInt(strFetcherstart) || (i >
			 * Integer.parseInt(strFetcherend) &&
			 * Integer.parseInt(strFetcherend) > 0)) continue;
			 */

			objWBFFColumns = m_listWBFFColumns.get(i);
			// strServerEwbcode = getEwbCode(objWBFFColumns, m_strHawbCodeType);
			strCwcode = objWBFFColumns.getCwcw_code();
			strChncode = objWBFFColumns.getCwchn_code_supplier();
			strCocodeSupplier = objWBFFColumns.getCwco_code_supplier();
			// if (strServerEwbcode == null) {
			// continue;
			// }
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
			htSubmitParameter.put(ITrackBasic.SUBMIT_HAWBCODE, objWBFFColumns
					.getCwcw_channelewbcode());
			htSubmitParameter.put(ITrackBasic.SUBMIT_DATE_YEAR, objWBFFColumns
					.getBwadd_date().substring(0, 4));
			htSubmitParameter.put(ITrackBasic.SUBMIT_DATE_MONTH, objWBFFColumns
					.getBwadd_date().substring(5, 7));
			htSubmitParameter.put(ITrackBasic.SUBMIT_DATE_DAY, objWBFFColumns
					.getBwadd_date().substring(8, 10));
			// htDepositParameter.put(ITrackBasic.DEPOSITOR_COMPANYID,
			// strCocodeSupplier);
			htDepositParameter.put(ITrackBasic.DEPOSITOR_BUSINESSCODE,
					strCwcode);
			htDepositParameter.put(ITrackBasic.DEPOSITOR_CHECKINDATE,
					objWBFFColumns.getBwadd_date());
			htDepositParameter.put(ITrackBasic.FETCHER_WPARCODE, objWPA
					.getWparCode());
			// htDepositParameter.put(ITrackBasic.DEPOSITOR_DESTINATION,
			// objWBFFColumns.getCdt_ename());
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
				loadClue(objWPA, objTFR, objFTD, htSubmitParameter,
						htDepositParameter);
				isFetched = true;

			} catch (Exception e) {
				e.printStackTrace();
				s_objLogger.warning("HAWB ERROR !" + e.toString()
						+ htSubmitParameter);
			}
			// �������ȡ�켣��ʱ��
			try {
				Track objTrack = new Track();
				TransportcorewaybillCondition con = new TransportcorewaybillCondition();
				List<TransportcorewaybillColumns> list = null;
				con.setTwbid(strCwcode);
				list = TransportWaybillDemand.queryCorewaybill(con);
				for (int j = 0; j < list.size(); j++) {
					objTrack.modifyLatestFetchdate(list.get(j).getCwcw_code(),
							htFetcherParameter.toString());
				}
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
				Thread.sleep(6000000);
				//Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				s_objLogger.warning("Thread sleep failed! " + e);
			}
		}
		System.out.println("��������:" + m_strChncode + "��������Ʊ��:"
				+ m_listWBFFColumns.size());
	}

	// ��ʼ���������壬����Ϣץȡ������Ĳ����б��У�ȡԤ���õĲ�����
	// ������������Ҫ��ITrackBasic��Ԥ�ȶ��塣
	private void initParameter(Hashtable<String, String> htParameter)
			throws Exception {
		int iBeginDay, iEndDay;
		String strBeginDay, strEndDay, strTFR_Code;
		m_twbid = htParameter.get("TwbCode");
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

		m_strBusiness = "618-39725420";

		iBeginDay = Integer.parseInt(strBeginDay);
		iEndDay = Integer.parseInt(strEndDay) + 1;

		m_strCheckOutBeginDate = DateUtility.getMoveDate(iBeginDay);
		m_strCheckOutEndDate = DateUtility.getMoveDate(iEndDay);

		// ��ȡ����������Ϣ
		TransportwaybillCondition con = new TransportwaybillCondition();
		if (!StringUtility.isNull(m_strBusiness))
			con.setTwblabelcode(m_strBusiness);
		else {
			//con.setTwbid(m_twbid);
			con.setStartcreatedate(m_strCheckOutBeginDate);
			con.setEndcreatedate(m_strCheckOutEndDate);
			con.setTtcode(m_strChncode);
		}
		
		List<TransportwaybillColumns> twb_list = (List<TransportwaybillColumns>) TransportWaybillDemand
				.query(con);
		TransportcorewaybillCondition tswb_con;
		WaybillforfetcherColumns wbff_col;
		m_listWBFFColumns = new ArrayList<WaybillforfetcherColumns>();
		// ���������Ż�ȡ���ε���
		for (int i = 0; i < twb_list.size(); i++) {
			tswb_con = new TransportcorewaybillCondition();
			tswb_con.setTwbid(twb_list.get(i).getTwbtwbid());
			List<TransportcorewaybillColumns> tswb_list = TransportWaybillDemand
					.queryCorewaybill(tswb_con);
			wbff_col = new WaybillforfetcherColumns();
			wbff_col.setCwchn_code_supplier(twb_list.get(i).getTtchnchncode());
			wbff_col.setCwcw_channelewbcode(twb_list.get(i)
					.getTwbtwblabelcode());
			wbff_col.setBwadd_date(twb_list.get(i).getTwbtwbcreatedate());
			wbff_col.setCwcw_code(twb_list.get(i).getTwbtwbid());
			m_listWBFFColumns.add(wbff_col);
		}
	}


	private int loadMultiPageClue(WebPageAccessRule objWPA,
			TextFormatRule objTFR, IFTextDepositor objFTD,
			Hashtable<String, String> htFormParameter,
			Hashtable<String, String> htDepositParameter, boolean blAlert)
			throws IOException {
		int iTotalRecord = 0, iTotalPage = 1;
		String strAccessDesc, strPageContent = "";
		FText objFText;
		if (htFormParameter == null)
			htFormParameter = new Hashtable<String, String>();
		htFormParameter.put(IFetcher.PARAMETER_EXIST_RECORD, "");
		// s_objLogger.info("Load one clue begin...");
		infoClueStart(objWPA);
		long lClueStart = System.currentTimeMillis();
		// ��Щ��վSession�а�����������ˢ�����ؽ��ͻ���
		objWPA.refreshSession();
		do {
			long lPageStart = System.currentTimeMillis();
			if (iTotalPage == 1) { // ������ҳ
				strAccessDesc = objWPA.getAccessFirstDesc(htFormParameter);
				strPageContent = objWPA.accessFirstPage(htFormParameter);
			} else { // ������ҳ
				objWPA = new WebPageAccessRule("SCG");
				objWPA.setNextAddress(next_page);
				objTFR = new TextFormatRule("TFR_SCG");
				strAccessDesc = objWPA.getAccessFirstDesc(htFormParameter);
				// strPageContent = objWPA.accessNextPage(htFormParameter);
				String pram[] = htFormParameter.get("hawbcode").split("-");
				// GetMethod getmethod = new
				// GetMethod(next_page+"?ScriptManager1=" +
				// "UpdatePanel1%7CbtnQuery&__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE=%2FwEPDwUJNjA4ODc2MjgzD2QWBAIBD2QWAgIKDxUFB1N1ZmZpeDEHU3VmZml4MgdTdWZmaXgzB1N1ZmZpeDQHU3VmZml4NWQCAw9kFgICAw9kFgJmD2QWBAIBDxYCHgVzdHlsZQUJZGlzcGxheTo7ZAIHDxYCHwAFDWRpc3BsYXk6bm9uZTtkZMxH7759wguFi4d80fLdFDA2mGpU&__EVENTVALIDATION=%2FwEWEQKPpZj4AgLxz6%2FNBwLB48JgAtbmkbgNAtzM4PUKArv986IDAve1%2FooFAqCU1o0JApKfnKAPAoWruPgOAq2IurUJApr2qvYNApLXgoMNApLXjoMNApLXioMNAu%2BOvL8FAuqG4JoJP2f9FbRfZogpcVJX%2BcGdxPip9Is%3D&Prefix1="+
				// pram[0].trim()+"&Suffix1="+pram[1].trim()+"&hiddenTxt=&Email1=&Email2=&Email3=&hdTransformSource=FSUTop.xsl"
				// +
				// "&__ASYNCPOST=true&btnQuery=Submit");
				GetMethod getmethod = new GetMethod(
						next_page
								+ "?ScriptManager1="
								+ "UpdatePanel1%7CbtnQuery&__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE="
								+ m_VIEWSTATE
								+ "&__EVENTVALIDATION="
								+ m_EVENTVALIDATION
								+ "&Prefix1="
								+ pram[0].trim()
								+ "&Suffix1="
								+ pram[1].trim()
								+ "&hiddenTxt=&Email1=&Email2=&Email3=&hdTransformSource=FSUTop.xsl"
								+ "&__ASYNCPOST=true&btnQuery=Submit");
				HttpClient client = new HttpClient();
				client.executeMethod(getmethod);
				strPageContent = getmethod.getResponseBodyAsString();
			}
			release(objWPA);
			displayPageContent(strPageContent);
			objFText = TextFormat.parseText(strPageContent, objTFR);
			// �����ҪԤ������Ԥ�����
			if (blAlert)
				alertCheck(objFText, strAccessDesc);
			// ����ʽ���ı�Ϊ��ʱ����ҳ�����޷���÷�ҳ��Ϣ�������˳�ѭ��
			// ���ٷ�ҳ
			if (objFText == null)
				break;
			int iRecord = 0;
			if (iTotalPage != 1) {
				iRecord = deposit(objFTD, objFText, objTFR, htDepositParameter);
			} else {
				next_page = objFText.getFBlockList("��ҳ").get(0).getFRecordList(
						"��ҳ").get(0).getFieldValue("��ҳ");
			}
			iTotalRecord += iRecord;
			infoPageEnd(lPageStart, iTotalPage, iRecord);
			sleepInterval(objWPA.getPageInterval());
			iTotalPage++;
			// Ŀǰû�л�ҳ3�ε���վ��Ϊ�˽��UPS��վ�ظ�����״̬ҳ�����⣬���Ӵ�����
			if (iTotalPage > 3)
				break;
		} while (getNextParameter(htFormParameter, objFText, objWPA));
		infoClueEnd(lClueStart, iTotalPage - 1, iTotalRecord);
		sleepInterval(objWPA.getClueInterval());
		return iTotalRecord;
	}

	private int deposit(IFTextDepositor objFTD, FText objFText,
			TextFormatRule objTFR, Hashtable<String, String> htDepositParameter) {
		// ��ʾ���洢���Ĳ���
		// String strParameterText = HashtableRender.toText(htDepositParameter);
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

	private boolean getNextParameter(Hashtable<String, String> htParameter,
			FText objFText, WebPageAccessRule objWPA) {
		String strNextPage;
		String strPageState;

		strNextPage = objFText.getNextPage();
		if (strNextPage != null) {
			// ����ҳ�ֶβ�Ϊ��ʱ�����ݷ�ҳ��Ϣ������÷�ҳ��Ϣ��������true
			if (objWPA.isNextNumber()) {
				// �����ҳ����ҳ����Ϣ��������ҳ��
				htParameter.put(IFetcher.PARAMETER_PAGE_NUMBER, strNextPage);
			} else {
				// �����ҳ�����Ѿ����ڵļ�¼��Ϣ���������Ѵ��ڼ�¼
				String strExist = htParameter
						.get(IFetcher.PARAMETER_EXIST_RECORD);
				if (StringUtility.isNull(strExist))
					strExist = strNextPage;
				else
					strExist += "," + strNextPage;
				htParameter.put(IFetcher.PARAMETER_EXIST_RECORD, strExist);
			}
			// ���Ѿ���÷�ҳ��Ϣ���ٻ�ȡ״̬��Ϣ�������ҳ��ϢΪ��������
			// ���ط�ҳ�����ػ��״̬��Ϣ
			if (objWPA.isCheckState()) {
				// ���״̬��Ϣ����ĳЩ������վ��Ҫ������UPS
				strPageState = objFText.getPageState();
				if (strPageState != null)
					htParameter
							.put(IFetcher.PARAMETER_PAGE_STATE, strPageState);
				else
					s_objLogger
							.warning("Check page is true, but page state is null!");
			}
			return true;
		} else { // ��ҳ�ֶ�Ϊ�գ�����false
			return false;
		}
	}

	private void release(WebPageAccessRule objWPA) {
		// �ر�WebPageAccessor
		if (objWPA != null)
			objWPA.releaseConnection();
	}

	private void infoPageEnd(long lStart, int iPageSerial, int iRecordCount) {
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
		 * System.out.println(DateFormatUtility.getCompactSysdate() + " " +
		 * sbInfo.toString()); s_objLogger.info(sbInfo.toString());
		 */
	}

	private void alertCheck(FText objFText, String strAccessDesc) {
		FTextChecker objFTextChecker = new FTextChecker();
		// String strCheck = objFTextChecker.checkFFText(objFText);
		objFTextChecker.checkFFText(objFText);
		/*
		 * if ( strCheck != null ) { // ���strCheck��Ϊ�գ�˵����ʽ�����⣬��ҪԤ���� AlertConfig
		 * objAC = AlertConfig.getInstance(); String strFrom = objAC.getFrom();
		 * String strTo = objAC.getTo(); String strCC = objAC.getCC(); String
		 * strBCC = objAC.getBCC(); String strTitle = objAC.getTitle(); String
		 * strContent = strAccessDesc + strCheck;
		 * 
		 * try { SyncMessage objSM = new SyncMessage();
		 * 
		 * objSM.sendHtmlEmail(strFrom, strTo, strCC, strBCC, strTitle,
		 * strContent); } catch(ServiceException ex) {
		 * s_objLogger.warning("�����ʼ�ʧ�ܣ�" + ex.getMessage()); } }
		 */
	}

	private void infoClueStart(WebPageAccessRule objWPA) {
		StringBuffer sbInfo = new StringBuffer();

		sbInfo.append("Load Clue Start. First Address:");
		sbInfo.append(objWPA.getFirstAddress());

		if (!objWPA.isSinglePageAccess()) {
			sbInfo.append(" Next Address:");
			sbInfo.append(objWPA.getNextAddress());
		}
		/*
		 * System.out.println(DateFormatUtility.getCompactSysdate() + " " +
		 * sbInfo.toString()); s_objLogger.info(sbInfo.toString());
		 */
	}

	private void infoClueEnd(long lStart, int iPageCount, int iRecordCount) {
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
		 * System.out.println(DateFormatUtility.getCompactSysdate() + " " +
		 * sbInfo.toString()); s_objLogger.info(sbInfo.toString());
		 */
	}

	/**
	 * ���ڵ���ģʽʱ����Ļ����ʾ��ʽ��
	 * 
	 * @param strPageContent
	 */
	private void displayPageContent(String strPageContent) {
		if (WebPageAccessConfig.getPromptLevel() == LoggerConfig.DEBUG) {
			long lStart = System.currentTimeMillis();
			StringRender.toScreen(strPageContent);
			DateRender.toScreen("Display Page Time:", lStart);
		}
	}

	protected int loadClue(WebPageAccessRule objWPA, TextFormatRule objTFR,
			IFTextDepositor objFTD, Hashtable<String, String> htFormParameter,
			Hashtable<String, String> htDepositParameter, boolean blAlert)
			throws IOException {
		if (objWPA.isSinglePageAccess())
			return loadSinglePageClue(objWPA, objTFR, objFTD, htFormParameter,
					htDepositParameter, blAlert);
		else
			return loadMultiPageClue(objWPA, objTFR, objFTD, htFormParameter,
					htDepositParameter, blAlert);
	}

	private int loadSinglePageClue(WebPageAccessRule objWPA,
			TextFormatRule objTFR, IFTextDepositor objFTD,
			Hashtable<String, String> htFormParameter,
			Hashtable<String, String> htDepositParameter, boolean blAlert)
			throws IOException {
		int iRecordCount = 0;
		String strPageContent;
		System.out.println("���뵥ҳ�洦��");
		// s_objLogger.info("Load one clue begin...");
		infoClueStart(objWPA);
		long lClueStart = System.currentTimeMillis();
		// ĳЩ��վ��Session���м�������ˢ��session�ؽ��ͻ���
		objWPA.refreshSession();
		// ��ȡҳ������
		// strAccessDesc = objWPA.accessFirstPage(htFormParameter);
		strPageContent = objWPA.accessFirstPage(htFormParameter);
		// �ͷ������Դ
		release(objWPA);
		// ����ڵ���ģʽ����ʾ��ҳ����
		displayPageContent(strPageContent);
		// ����ҳ����Ϣ
		FText objFText = TextFormat.parseText(strPageContent, objTFR);
		// Ԥ�����
		if (blAlert)
			alertCheck(objFText, strPageContent);
		// �洢��ʽ���ı���Ϣ
		iRecordCount = deposit(objFTD, objFText, objTFR, htDepositParameter);
		infoClueEnd(lClueStart, 1, iRecordCount);
		sleepInterval(objWPA.getClueInterval());
		return iRecordCount;
	}
}
