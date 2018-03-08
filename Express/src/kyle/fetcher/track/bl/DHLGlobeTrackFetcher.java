package kyle.fetcher.track.bl;

import java.util.Hashtable;

import kyle.common.util.textformat.bl.TextFormatRule;
import kyle.common.webpageaccess.bl.IFTextDepositor;
import kyle.common.webpageaccess.rule.bl.WebPageAccessRule;
import kyle.fetcher.track.dax.ITrackBasic;

public class DHLGlobeTrackFetcher extends SingleTrackFetcher {
	public void preDealClue(WebPageAccessRule objWPA, TextFormatRule objTFR,
			IFTextDepositor objFTD,
			Hashtable<String, String> htSubmitParameter,
			Hashtable<String, String> htDepositParameter)  {
		try {
			String strCoutryEname = htDepositParameter.get(ITrackBasic.DEPOSITOR_DESTINATION);
			if (!("GERMANY").equals(strCoutryEname)) {
				String strServerEwbcode = htSubmitParameter.get(ITrackBasic.SUBMIT_HAWBCODE);
				strServerEwbcode = strServerEwbcode + "200";
				htSubmitParameter.put(ITrackBasic.SUBMIT_HAWBCODE, strServerEwbcode);
			}
		} catch (Exception ex) {
			
		}
	}
}
