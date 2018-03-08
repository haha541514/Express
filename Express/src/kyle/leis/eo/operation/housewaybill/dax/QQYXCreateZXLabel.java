package kyle.leis.eo.operation.housewaybill.dax;

import java.util.List;
import java.util.logging.Logger;

import kyle.common.explorer.HttpExplorer;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiWaybillcodekindDC;
import kyle.leis.fs.waybillcode.dax.IWaybillcodeBasicData;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiWaybillcodekind;


public class QQYXCreateZXLabel extends Thread {

	private static Logger s_objLogger = Logger.getLogger(QQYXCreateZXLabel.class.getName());
	private InputAllQReturn m_objIAQR;
	
	public QQYXCreateZXLabel(InputAllQReturn objIAQR) {
		m_objIAQR = objIAQR;
	}
	
	
	public void run() {
		try {
			List list = m_objIAQR.getHWBResults();
			if (list == null || list.size() < 1)
				return;
			ForinputallColumns objForColumns = (ForinputallColumns)list.get(0);
			if ("Y".equals(objForColumns.getHwhwserverewbchangedsign())) {
				TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objForColumns.getChncode_Cwspchn());
				if (objTchnChannel != null && objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind() != null) {
					TdiWaybillcodekind objTdiWaybillcodekind = TdiWaybillcodekindDC.loadByKey(objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind().getBckCode());
					if (objTdiWaybillcodekind.getBckGroupcode().equals(IWaybillcodeBasicData.BCK_EPARCEL)) {
						createLabel(objForColumns.getCwserverewbcode());
					}
				}
			}
			
		} catch(Exception ex) {
			s_objLogger.warning(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	private void createLabel(String strServerEwbcode) throws Exception {
		String strUrl = "http://www.qqyx.me/PrintPDFLableServlet.xsv";
		
		StringBuffer sbParameter = new StringBuffer();
		sbParameter.append("isCreate=Y");
		sbParameter.append("&serverewbcode=" + strServerEwbcode);
		
		HttpExplorer objHttpExplorer = new HttpExplorer();
		objHttpExplorer.getResponseStringByPostMethod(strUrl, 
				sbParameter.toString(), 
				"",
				"utf-8");		
	}
	
	public static void main(String[] args) {
		try {
			QQYXCreateZXLabel obj = new QQYXCreateZXLabel(null);
			obj.createLabel("25F162343201000930209");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
