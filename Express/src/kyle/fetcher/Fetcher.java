package kyle.fetcher;

import java.util.logging.Logger;
import java.util.List;

import kyle.common.webpageaccess.bl.*;
import kyle.common.webpageaccess.ca.WebPageAccessConfig;
import kyle.common.webpageaccess.ca.WebPageAccessSourceConfig;
import kyle.common.webpageaccess.da.WebpageaccessColumns;

/**
 * User: Kyle 
 * Date: 2009-2-6 
 * Time: 9:17:19
 */
public class Fetcher {
	static Logger s_objLogger = Logger.getLogger(Fetcher.class.getName());

	public static void main(String[] args) {
		fetch();
	}
	
	public static void fetch() {
		WebPageAccessConfig.getInstance();
		Fetcher objFetcher = new Fetcher();
		objFetcher.loadAllInfo();		
	}
	
	private void loadAllInfo() {
		if (WebPageAccessConfig.getInfoCount() == 0)
			s_objLogger.warning("There are no load info in config!");
		int iInfoCount = WebPageAccessConfig.getInfoCount();
		for (int i = 0; i < iInfoCount; i++) {
			loadOneInfo(WebPageAccessConfig.getInfo(i));
		}
	}

	/**
	 * 根据信息类别开始装载相关信息 
	 * 1.获取此信息类别的信息装载规则 
	 * 2.每条信息装载规则对应一个线程进行相应的信息访问、解析信息并保存到数据库
	 * @param objInfo
	 */
	private void loadOneInfo(WebPageAccessSourceConfig objInfo) {
		List<WebpageaccessColumns> listILRTR;

		s_objLogger.info("Start load info " + objInfo.getType() + " "
				+ objInfo.getWebsite());

		listILRTR = WebPageAccessRuleDemand.getInfoLoadRule(objInfo.getType(),
				objInfo.getWebsite());
		
		if (listILRTR != null) {
			// 每个信息装载规则启动一个线程
			for (int i = 0; i < listILRTR.size(); i++) {
				WebpageaccessColumns objILRTR = listILRTR.get(i);
				WebPageAccess objILThread = new WebPageAccess(objILRTR);
				//if (objILRTR.getWpawpacode().equals("TCK_USPS6"))
				objILThread.run();
			}
		}
	}
}
