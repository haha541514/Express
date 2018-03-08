package kyle.leis.eo.operation.housewaybill.bl;

import java.util.Date;
import java.util.logging.Logger;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.prompt.SavedResultUtility;

/**
 * 异步出货
 * @author Administrator
 *
 */
public class PredictOrderSignOutThread extends Thread {
	protected static final Logger LOGGER = Logger.getLogger(PredictOrderSignOutThread.class.getName());
	
	private String strCwServerewbcode;
	private String strOperId;
	
	public PredictOrderSignOutThread(String strCwServerewbcode, String strOperId) {
		this.strCwServerewbcode = strCwServerewbcode;
		this.strOperId = strOperId;
	}

	@Override
	public void run() {
		String strBagLabelcode = DateFormatUtility.getDateString(new Date(), "yyyyMMdd");
		SignOut signOut = new SignOut();
		try {
			SavedResultUtility result = signOut.save("", strCwServerewbcode, strBagLabelcode, 
					strOperId, true, true, "DHLS");
			if (result.getPromptUtilityCollection().canGo(true)) {
				LOGGER.info("出货成功!");
			} else {
				LOGGER.severe(result.getPromptUtilityCollection().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
