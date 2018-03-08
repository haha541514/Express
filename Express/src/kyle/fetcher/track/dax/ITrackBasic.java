package kyle.fetcher.track.dax;

/**
 * Created by IntelliJ IDEA.
 * User: Jordan
 * Date: 2006-4-1
 * Time: 10:37:09
 * To change this template use File | Settings | File Templates.
 */
public interface ITrackBasic
{
  public static final String FETCHER_AGENT_SERVE = "AgentServe";
  public static final String FETCHER_CHANNEL = "Channel";
  public static final String FETCHER_BEGIN_DAY = "BeginDay";
  public static final String FETCHER_END_DAY = "EndDay";
  public static final String FETCHER_BUSINESSCODE = "Business";
  public static final String FETCHER_FETCHINTERVAL = "FetchInterval";
  public static final String FETCHER_HAWBCODETYPE = "HawbCodeType";    
  public static final String FETCHER_WPARCODE = "WparCode";
  public static final String FETCHER_SSG_CODE = "SsgCode";
  public static final String FETCHER_START = "fetcherStart";
  public static final String FETCHER_END = "fetcherEnd";
  public static final String FETCHER_LATESTINTERVAL = "LatestInterval";
  
  public static final String SUBMIT_HAWBCODE = "hawbcode";
  public static final String SUBMIT_DATE_YEAR = "submityear";
  public static final String SUBMIT_DATE_MONTH = "submitmonth";
  public static final String SUBMIT_DATE_DAY = "submitday";
  public static final String DEPOSITOR_COMPANYID = "CompanyID";
  public static final String DEPOSITOR_BUSINESSCODE = "BusinessCode";
  public static final String DEPOSITOR_CHANNELCODE = "ChannelCode";
  public static final String DEPOSITOR_LOCATIONFILTER = "LocationFilter";
  public static final String DEPOSITOR_CHECKINDATE = "CheckInDate";     
  public static final String DEPOSITOR_DESTINATION = "Destination";
  
  public static final String FTB_POD = "POD";
  public static final String FTR_SIGNFOR = "签收";

  public static final String FTF_HAWB_CODE = "运单号";
  public static final String FTF_SIGNFOR_PERSON = "签收人";
  public static final String FTF_SIGNFOR_DATE = "签收日期";
  public static final String FTF_SIGN_STATUS = "签收状态";
  public static final String FTF_ORIGIN_AREA = "始发地";
   public static final String FTF_DESTINATION_AREA = "目的地";

  public static final String FTB_TRACK_REPORT = "轨迹报告";
  public static final String FTR_HAWB_CODE = "运单";
  //public static final String FTF_HAWB_CODE = "运单号";
  public static final String FTR_TRACK = "轨迹";
  public static final String FTF_DATE = "日期";
  public static final String FTF_TIME = "时间";
  public static final String FTF_DATETIME = "时间";
  
  public static final String FTB_PAGE = "翻页";
  public static final String FTR_PAGE = "翻页";
  public static final String FTF_PAGE = "翻页";

  public static final String FTB_PAGE_STATUS = "页状态";
  public static final String FTR_PAGE_STATUS = "页状态";
  public static final String FTF_PAGE_STATUS = "页状态";
  
  //public static final String FTF_REGION = "国家城市";
  public static final String FTF_NATION = "国家";
  public static final String FTF_PROVINCE = "省";
  public static final String FTF_SPOT = "地点";
  public static final String FTF_ORIGINDETAIL = "原始详情";
  public static final String FTF_DETAIL = "详情";
}
