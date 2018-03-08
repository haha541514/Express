package kyle.leis.eo.operation.corewaybill.test;

import java.io.File;
import java.util.List;

import com.AWaybillcodeRequest;
import com.WaybillcodeParam;
import com.WaybillcodeResponse;
import com.dhl.DHLWayBillCode;
import com.dhl.DHLWebWayBillCode;
import com.eub.EUBWaybillCode;

import kyle.common.connectors.servlet.ActionServletConstant;
import kyle.common.util.jlang.FileUtility;
import kyle.common.util.prompt.PromptUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.corewaybill.dax.Corewaybillcode;
import kyle.leis.eo.operation.corewaybill.dax.RequestDHLWEB;
import kyle.leis.eo.operation.corewaybill.dax.RequestDHLXML;
import kyle.leis.eo.operation.corewaybill.dax.RequestEMSSoapXML;
import kyle.leis.eo.operation.corewaybill.dax.RequestEParcelXML;
import kyle.leis.eo.operation.corewaybill.dax.RequestEubXML;
import kyle.leis.eo.operation.corewaybill.dax.RequestUSPSSoapXML;
import kyle.leis.eo.operation.corewaybill.dax.RequestXML;
import kyle.leis.eo.operation.corewaybill.sv.CorewaybillDelegate;
import kyle.leis.eo.operation.corewaybill.task.ChangeServerEWBTask;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;

public class CorewaybillcodeTest {
	
	public static void getServerEwbcode() throws Exception {
		
		ActionServletConstant.getInstance().setRealPath("E:\\jakarta-tomcat-5.5.9\\webapps\\Express\\WEB-INF\\");
		CorewaybillDelegate cd = new CorewaybillDelegate();
		
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setCwcode("7597447");
		//objFInputAllC.setCustomerewbcode("");
		
		//System.out.println(System.getProperty("java.class.path"));
		
		InputAllQReturn objIAQR = HousewaybillDemand.queryInput(objFInputAllC);
		List listCargoInfo = objIAQR.getCargoInfoResults();
		ForinputallColumns objForinputallColumns = (ForinputallColumns)objIAQR.getHWBResults().get(0);
		
		HousewaybillColumns objHWBColumns = HousewaybillDemand.loadByCwcode(objForinputallColumns.getCwcode());
		List listWaybillpieces = CorewaybillpiecesDemand.load(objForinputallColumns.getCwcode());
		
		Corewaybillcode objCorewaybillcode = new Corewaybillcode();
		PromptUtilityCollection objPU = objCorewaybillcode.setWaybillcodeByService(objForinputallColumns, 
				listWaybillpieces, 
				listCargoInfo, 
				objHWBColumns, 
				"DHLSGMC", 
				"DHLMC_E",
				"DHLSGMC",
				"");
		
		System.out.println(objForinputallColumns.getCwcustomerewbcode());
		System.out.println(objForinputallColumns.getCwserverewbcode());
		
		if (objPU.getCollection() != null && objPU.getCollection().size() > 0)
			for (int i = 0; i < objPU.getCollection().size(); i++) {
				PromptUtility objPromptUtility = objPU.getCollection().get(i);
				System.out.println(objPromptUtility.getDescribtion());
			}		
	}
	
	public static void testDHLWeb() throws Exception {
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setCustomerewbcode("USPS001");
		//objFInputAllC.setCustomerewbcode("");
		
		InputAllQReturn objIAQR = HousewaybillDemand.queryInput(objFInputAllC);
		List listCargoInfo = objIAQR.getCargoInfoResults();
		ForinputallColumns objForinputallColumns = (ForinputallColumns)objIAQR.getHWBResults().get(0);
		
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		
		RequestXML objRequestXML = new RequestUSPSSoapXML();
		String strRequestXML = objRequestXML.buildRequestXML(objForinputallColumns, listCargoInfo, null,
				objPUCollection);
		AWaybillcodeRequest objWayBillCode = new DHLWebWayBillCode();
		WaybillcodeParam waybillcodeParam = new WaybillcodeParam();
		waybillcodeParam.setWaybillcodeParam(strRequestXML);
		WaybillcodeResponse objWaybillcodeResponse = objWayBillCode.getWaybillcode(waybillcodeParam);
		System.out.println(objWaybillcodeResponse.getLableurl());
	}
	
	public static void testEUB() throws Exception {
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setCustomerewbcode("20160412");
		InputAllQReturn objIAQR = HousewaybillDemand.queryInput(objFInputAllC);
		List listCargoInfo = objIAQR.getCargoInfoResults();
		ForinputallColumns objForinputallColumns = (ForinputallColumns)objIAQR.getHWBResults().get(0);
		
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		
		RequestXML objRequestXML = new RequestEubXML();
		String strRequestXML = objRequestXML.buildRequestXML(objForinputallColumns, listCargoInfo, null,
				objPUCollection);
		AWaybillcodeRequest objWayBillCode = new EUBWaybillCode();
		WaybillcodeParam waybillcodeParam = new WaybillcodeParam();
		waybillcodeParam.setWaybillcodeParam(strRequestXML);
		WaybillcodeResponse objWaybillcodeResponse = objWayBillCode.getWaybillcode(waybillcodeParam);
		
		System.out.println(objWaybillcodeResponse.getMainWaybillcode());
		System.out.println(objWaybillcodeResponse.getLableurl());
	}	
	
	
	public static void batchModifyServerEWB() {
		ChangeServerEWBTask objCSEWBTask = new ChangeServerEWBTask();
		objCSEWBTask.execute("");
	}
	
	public static void testEqual() throws Exception {
		String str1 = "";
		String str2 = null;
		System.out.println(str1.equals(str2));
		
		String str = FileUtility.readFile(new File("C:\\Documents and Settings\\Bing\\×ÀÃæ\\aa.xml"));
		RequestEMSSoapXML exml = new RequestEMSSoapXML();
		System.out.println(exml.formatXML(str));
	}
	
	
	
	public static void main(String[] args) {
		try {
			testDHLWeb();
			//testEUB();
			getServerEwbcode();
			// testEqual();
			// batchModifyServerEWB();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		
	}
}
