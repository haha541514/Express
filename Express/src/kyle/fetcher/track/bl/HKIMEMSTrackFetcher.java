package kyle.fetcher.track.bl;

import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

import com.ems.HKEMSInterface;
import com.ems.HKEMSInterface.HKEMSResult;
import com.ems.HKEMSInterface.HKEMSWaybillResponse;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.DateUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.common.util.textformat.bl.FText;
import kyle.common.util.textformat.bl.TextFormatRule;
import kyle.common.webpageaccess.bl.APageFetcher;
import kyle.common.webpageaccess.bl.IFTextDepositor;
import kyle.common.webpageaccess.rule.bl.WebPageAccessRule;
import kyle.fetcher.track.da.WaybillforfetcherColumns;
import kyle.fetcher.track.dax.ITrackBasic;
import kyle.fetcher.track.dax.TrackWayBillDemand;

public class HKIMEMSTrackFetcher extends APageFetcher {
	static Logger s_objLogger = Logger.getLogger(SingleTrackFetcher.class
			.getName());

	private String m_strSupplier;

	private String m_strChncode;

	private String m_strCheckOutBeginDate;

	private String m_strCheckOutEndDate;

	private String m_strBusiness;

	private String m_strFetchInterval;

	private String m_strHawbCodeType;

	private String m_strSsgCode;

	private List<WaybillforfetcherColumns> m_listWBFFColumns;

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
		
		// m_strBusiness = "006698244162"; // 2728634381

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
			String strSysdate = DateFormatUtility.getStandardSysdate();
			while (true) {
				if (strSysdate.substring(11,13).compareTo("20") < 0 &&
					strSysdate.substring(11,13).compareTo("08") >= 0) {
					Thread.sleep(600000);
					strSysdate = DateFormatUtility.getStandardSysdate();
				}
				else
					break;
			}
			initParameter(htFetcherParameter);
		} catch (Exception e) {
			s_objLogger.warning("Init fetch parameter failed! " + e);
		}

		htSubmitParameter = new Hashtable<String, String>();
		htDepositParameter = new Hashtable<String, String>();
		if (m_listWBFFColumns == null || m_listWBFFColumns.size() < 1) {
			try {
				Thread.sleep(6000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				s_objLogger.warning("Thread sleep failed! " + e);
			}
			return;
		}
		HKEMSInterface hkems = new HKEMSInterface();
		for (int i = 0; i < m_listWBFFColumns.size(); i++) {
			objWBFFColumns = m_listWBFFColumns.get(i);
			strServerEwbcode = getEwbCode(objWBFFColumns, m_strHawbCodeType);
			strCwcode = objWBFFColumns.getCwcw_code();
			strChncode = objWBFFColumns.getCwchn_code_supplier();
			strCocodeSupplier = objWBFFColumns.getCwco_code_supplier();
			if (strServerEwbcode == null) {
				continue;
			}
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
			if (strChncode != null)
				htDepositParameter.put(ITrackBasic.DEPOSITOR_CHANNELCODE,
						strChncode);
			else
				s_objLogger.warning("Default channel is not set!");

			try {
				// ���ýӿ�
				HKEMSWaybillResponse HKresponse = hkems
						.tracking(strServerEwbcode);
				if (HKresponse.getExecuteResult()) {
					FText objFText = buildByInterface(HKresponse.getResults());
					// �洢��ʽ���ı���Ϣ
					objFTD.process(objFText, objTFR, htDepositParameter);
				}
			} catch (Exception e) {
				e.printStackTrace();
				s_objLogger.warning("HAWB ERROR !" + e.toString()
						+ htSubmitParameter);
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
		System.out.println("��������:" + m_strChncode + "   ���Ʊ��:"
				+ m_listWBFFColumns.size());
	}

	private FText buildByInterface(List<HKEMSResult> HKresponse) {
		FText objFText = new FText();
		FBlock currentformatBlock = new FBlock();
		String time;
		for (HKEMSResult obj : HKresponse) {
			FRecord frecord = new FRecord();
			String strDatetime = obj.getEntryDatetime();
			if (!StringUtility.isNull(strDatetime) && strDatetime.length() >= 19) {
				strDatetime = strDatetime.substring(0, 19);
				strDatetime = strDatetime.replace("T", "");
				time = "to_date('"
						+ strDatetime
						+ "', 'yyyy-mm-dd hh24:mi:ss', 'NLS_DATE_LANGUAGE=American')";
				frecord.putField("ʱ��", time);
				frecord.putField("�ص�", obj.getReuslt());
				frecord.putField("����", obj.getDescription());
				currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK, frecord);
				currentformatBlock.addFRecord(ITrackBasic.FTB_TRACK_REPORT, frecord);
			}
		}
		objFText.put(ITrackBasic.FTR_TRACK, currentformatBlock);
		objFText.put(ITrackBasic.FTB_TRACK_REPORT, currentformatBlock);
		return objFText;
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

}
