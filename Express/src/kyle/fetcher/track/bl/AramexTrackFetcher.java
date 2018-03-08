package kyle.fetcher.track.bl;

import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import kyle.common.explorer.HttpExplorer;
import kyle.common.util.jlang.DateUtility;
import kyle.common.util.jlang.NumberUtility;
import kyle.common.util.logger.LoggerConfig;
import kyle.common.util.render.DateRender;
import kyle.common.util.render.HashtableRender;
import kyle.common.util.render.StringRender;
import kyle.common.util.textformat.bl.FText;
import kyle.common.util.textformat.bl.TextFormat;
import kyle.common.util.textformat.bl.TextFormatRule;
import kyle.common.webpageaccess.bl.FTextChecker;
import kyle.common.webpageaccess.bl.IFTextDepositor;
import kyle.common.webpageaccess.bl.IFetcher;
import kyle.common.webpageaccess.ca.WebPageAccessConfig;
import kyle.common.webpageaccess.rule.bl.WebPageAccessRule;
import kyle.fetcher.track.bl.SingleTrackFetcher;
import kyle.fetcher.track.dax.ITrackBasic;

public class AramexTrackFetcher extends SingleTrackFetcher {

	public void preDealClue(WebPageAccessRule objWPA, TextFormatRule objTFR,
			IFTextDepositor objFTD,
			Hashtable<String, String> htSubmitParameter,
			Hashtable<String, String> htDepositParameter)  {
		String strServerEwbcode = htSubmitParameter.get(ITrackBasic.SUBMIT_HAWBCODE);
		String strAddress = "http://www.aramex.com/track-results-multiple.aspx"; 
		String strParameter = "ShipmentNumber=" + strServerEwbcode;
		try {
			HttpExplorer objHttpExplorer = new HttpExplorer();
			String strPageContent = objHttpExplorer.accessPage("GET", 
					strAddress, 
					strParameter, 
					"utf-8", 
					htSubmitParameter);
			int beginIndex = strPageContent.indexOf("ctl00_ctl00_MainContent_InnerMainContent_hdnParams");
			int endIndex = strPageContent.indexOf("ctl00$ctl00$MainContent$InnerMainContent$hdnSelectedAccount");
			if (beginIndex < 0 || endIndex < 0)
				return;
			
			String AWBID = strPageContent.substring(beginIndex, endIndex);
			beginIndex = AWBID.indexOf("value=")+"value=".length()+1;
			endIndex = AWBID.indexOf(" />")-1;
			AWBID = AWBID.substring(beginIndex, endIndex).trim();
			AWBID = AWBID.split("\\u007C")[0];
			htSubmitParameter.put(IFetcher.PARAMETER_PAGE_STATE, AWBID);
		} catch (Exception ex) {
			
		}
	}
	protected int loadClue(WebPageAccessRule objWPA, 
			TextFormatRule objTFR,
			IFTextDepositor objFTD, 
			Hashtable<String, String> htFormParameter,
			Hashtable<String, String> htDepositParameter, 
			boolean blAlert) throws IOException {
			return loadSinglePageClue(objWPA, objTFR, objFTD, htFormParameter,
				htDepositParameter, blAlert);
	}

	private int loadSinglePageClue(WebPageAccessRule objWPA, TextFormatRule objTFR,IFTextDepositor objFTD, Hashtable<String, String> htFormParameter,Hashtable<String, String> htDepositParameter, boolean blAlert) throws IOException {
		int iRecordCount = 0;
		String strPageContent;
		// s_objLogger.info("Load one clue begin...");
		infoClueStart(objWPA);
		long lClueStart = System.currentTimeMillis();
		// 获取页面内容
		// strAccessDesc = objWPA.accessFirstPage(htFormParameter);
		String s="\"";
		strPageContent = getContent(objWPA.getFirstAddress(),"{\"AWBID\":"+s+htFormParameter.get(IFetcher.PARAMETER_PAGE_STATE)+s+",\"ProductGroup\": \"EXP,DOM\",\"Language\": \"en\",\"IsLoggedIn\": \"False\",\"SignatureInnerHtml\": \"(view attached signature)\",\"SelectedAccount\":\"\",\"SearchMode\": \"0\",\"SearchCriteria\": \"\"}")+"addend0";
		displayPageContent(strPageContent);
		FText objFText = TextFormat.parseText(strPageContent, objTFR);
		if (blAlert)
			alertCheck(objFText, strPageContent);
		// 存储格式化文本信息
		iRecordCount = deposit(objFTD, objFText, objTFR, htDepositParameter);
		infoClueEnd(lClueStart, 1, iRecordCount);
		sleepInterval(objWPA.getClueInterval());
		return iRecordCount;
	}
	public static String getContent(String strAddress,String strParameter){
		HttpClient httpClient = new HttpClient();
    	httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
        PostMethod postMethod = new PostMethod(strAddress);
        postMethod.addRequestHeader("Content-Type","application/json;charset=utf-8");
        postMethod.addRequestHeader("Referer","http://www.aramex.com/express/track-results.aspx");
        postMethod.setRequestBody(strParameter);
        String wbepageContent=null;
        try {
			httpClient.executeMethod(postMethod);
		    wbepageContent= postMethod.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			postMethod.releaseConnection();
		}
        
		return  wbepageContent;
	}
	private void alertCheck(FText objFText, 
			String strAccessDesc) {
		FTextChecker objFTextChecker = new FTextChecker();
		// String strCheck = objFTextChecker.checkFFText(objFText);
		objFTextChecker.checkFFText(objFText);
		/*
		 * if ( strCheck != null ) { // 如果strCheck不为空，说明格式有问题，需要预警。 AlertConfig
		 * objAC = AlertConfig.getInstance(); String strFrom = objAC.getFrom();
		 * String strTo = objAC.getTo(); String strCC = objAC.getCC(); String
		 * strBCC = objAC.getBCC(); String strTitle = objAC.getTitle(); String
		 * strContent = strAccessDesc + strCheck;
		 * 
		 * try { SyncMessage objSM = new SyncMessage();
		 * 
		 * objSM.sendHtmlEmail(strFrom, strTo, strCC, strBCC, strTitle,
		 * strContent); } catch(ServiceException ex) {
		 * s_objLogger.warning("发送邮件失败！" + ex.getMessage()); } }
		 */
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
  
	private void infoClueStart(WebPageAccessRule objWPA) {
		StringBuffer sbInfo = new StringBuffer();

		sbInfo.append("Load Clue Start. First Address:");
		sbInfo.append(objWPA.getFirstAddress());

		if (!objWPA.isSinglePageAccess()) {
			sbInfo.append(" Next Address:");
			sbInfo.append(objWPA.getNextAddress());
		}
	}
	private void displayPageContent(String strPageContent) {
		if (WebPageAccessConfig.getPromptLevel() == LoggerConfig.DEBUG) {
			long lStart = System.currentTimeMillis();
			StringRender.toScreen(strPageContent);
			DateRender.toScreen("Display Page Time:", lStart);
		}
	}
}
