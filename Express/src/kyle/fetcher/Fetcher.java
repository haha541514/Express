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
	 * ������Ϣ���ʼװ�������Ϣ 
	 * 1.��ȡ����Ϣ������Ϣװ�ع��� 
	 * 2.ÿ����Ϣװ�ع����Ӧһ���߳̽�����Ӧ����Ϣ���ʡ�������Ϣ�����浽���ݿ�
	 * @param objInfo
	 */
	private void loadOneInfo(WebPageAccessSourceConfig objInfo) {
		List<WebpageaccessColumns> listILRTR;

		s_objLogger.info("Start load info " + objInfo.getType() + " "
				+ objInfo.getWebsite());

		listILRTR = WebPageAccessRuleDemand.getInfoLoadRule(objInfo.getType(),
				objInfo.getWebsite());
		
		if (listILRTR != null) {
			// ÿ����Ϣװ�ع�������һ���߳�
			for (int i = 0; i < listILRTR.size(); i++) {
				WebpageaccessColumns objILRTR = listILRTR.get(i);
				WebPageAccess objILThread = new WebPageAccess(objILRTR);
				//if (objILRTR.getWpawpacode().equals("TCK_USPS6"))
				objILThread.run();
			}
		}
	}
}
