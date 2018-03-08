package kyle.fetcher.track.bl;

import java.util.Hashtable;

import kyle.common.explorer.HttpExplorer;
import kyle.common.util.textformat.bl.TextFormatRule;
import kyle.common.webpageaccess.bl.IFTextDepositor;
import kyle.common.webpageaccess.bl.IFetcher;
import kyle.common.webpageaccess.rule.bl.WebPageAccessRule;
import kyle.fetcher.track.dax.ITrackBasic;

public class USDHLGlobeTrackFetcher extends SingleTrackFetcher {

	public void preDealClue(WebPageAccessRule objWPA, TextFormatRule objTFR,
			IFTextDepositor objFTD,
			Hashtable<String, String> htSubmitParameter,
			Hashtable<String, String> htDepositParameter)  {
		String strServerEwbcode = htSubmitParameter.get(ITrackBasic.SUBMIT_HAWBCODE);
		String strAddress = "https://www.globalmail.dhl.com/web/portal-asia/traceit"; 
		String strParameter = "customerReference=" + strServerEwbcode;
		try {
			HttpExplorer objHttpExplorer = new HttpExplorer();
			String strPageContent = objHttpExplorer.accessPage("GET", 
					strAddress, 
					strParameter, 
					"utf-8", 
					htSubmitParameter);
			int beginIndex = strPageContent.indexOf("</thead> <tbody> <tr> <td>");
			int endIndex = strPageContent.indexOf("</td>");
			if (beginIndex < 0 || endIndex < 0)
				return;
			
			String AWBID = strPageContent.substring(beginIndex, endIndex);
			AWBID = AWBID.substring("</thead> <tbody> <tr> <td>".length());
			htSubmitParameter.put(IFetcher.PARAMETER_PAGE_STATE, AWBID);
		} catch (Exception ex) {
			
		}
	}

}
