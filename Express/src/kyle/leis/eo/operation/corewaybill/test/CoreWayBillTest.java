package kyle.leis.eo.operation.corewaybill.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

import kyle.common.connectors.servlet.ActionServletConstant;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.FileUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.corewaybill.blx.CoreWayBillCheck;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybill.dax.RequestDHLXML;
import kyle.leis.eo.operation.corewaybill.dax.RequestEParcelXML;
import kyle.leis.eo.operation.corewaybill.dax.RequestXML;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallCondition;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;

public class CoreWayBillTest {
	public static void main(String[] args) {
		try {
			// testBalance();
			testDHLXML();
			// testPostcode();
			// createPdf("0017838026");
			// splitLengthString();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	public static void testPostcode() throws Exception {
		
		String strPostcode = "5330013";
		System.out.println(strPostcode.substring(0, 5));
		
		
		ActionServletConstant.getInstance().setRealPath("E:/jakarta-tomcat-5.5.9/webapps/Express/WEB-INF/");
		
		RequestDHLXML rdxml = new RequestDHLXML();
		//rdxml.getRequestModelContent();
		
		System.out.print(rdxml.autoRebuildDHLPostcode("JP", "5330013"));
		
	}
	
	public static void testDHLXML() throws Exception {
		RequestXML objRequestXML = new RequestEParcelXML();
		//RequestXML objRequestXML = new RequestDHLXML();
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setServerewbcode("25H2494696");
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		InputAllQReturn objIAQR = HousewaybillDemand.queryInput(objFInputAllC);
		String strRequestXML = objRequestXML.buildRequestXML((ForinputallColumns)objIAQR.getHWBResults().get(0), 
				objIAQR.getCargoInfoResults(), null,
				objPUCollection);		
		FileUtility.createFile("E:/", "25H2494696.xml", strRequestXML);
	}
	
	public static void createPdf(String mainCode) throws Exception{
		String base64Pdf = "";
		
		File file = new File("E:\\dhlresponse2.xml");;
		base64Pdf = FileUtility.readFile(file);
		base64Pdf = base64Pdf.substring(base64Pdf.indexOf("<OutputImage>") + "<OutputImage>".length(),
				base64Pdf.indexOf("</OutputImage>"));
		
		System.out.println(base64Pdf);
		
		byte[] pb = Base64.decodeBase64(base64Pdf.getBytes("UTF-8"));
		String subFile = DateFormatUtility.getCompactOnlyDateSysdate();
		file = new File("E:\\" + mainCode + ".jpg");
		// 输出PDF文件
		FileOutputStream out = new FileOutputStream(file);
		out.write(pb);
		out.close();
		
	}
	
	
	public static void testBalance() throws Exception {
		/*
		String strCwcode = "4536131";
		String strCocode = "12501";
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		CoreWayBillCheck objCoreWayBillCheck = new CoreWayBillCheck();
		objCoreWayBillCheck.checkFinanceForSignOut(strCwcode, strCocode, "0", "", "2014-12-01",
				false, objPUCollection);
		System.out.println(objPUCollection.toString());
		*/
		
		CorewaybillDemand.getCwcodeBySChildLabelcode("123");
	}
	
	public static void testModifyTransaction() throws Exception {
		String strCwcode = "5411";
		String strCocode = "48";
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		CoreWayBillCheck objCoreWayBillCheck = new CoreWayBillCheck();
		objCoreWayBillCheck.checkFinanceForSignOut(strCwcode, strCocode, "0", "", "",
				false,objPUCollection);
	}	
	
	public static void splitLengthString() throws Exception {
		String str = "12345678";
		System.out.println(str.substring(str.length() - 6));
		
		str = "信号接收器12";
		Pattern pt = Pattern.compile("[^a-zA-Z 0-9%]");
		System.out.println(pt.matcher(str).replaceAll(""));		
		
		/*
		ForinputallColumns objFIAColumns = HousewaybillDemand.load("1281030");
		
		String[] astrAddress = new String[4];
		String strAddress = objFIAColumns.getHwconsigneeaddress1() + " " +
		objFIAColumns.getHwconsigneeaddress2() + " " +
		objFIAColumns.getHwconsigneeaddress3();	
		for (int i = 0; i < 4; i++) {
			astrAddress[i] = ".";
			if (strAddress.length() > i * 39) {
				if (strAddress.length() > (i + 1) * 39)
					astrAddress[i] = strAddress.substring(i * 39, (i + 1) * 39);
				else
					astrAddress[i] = strAddress.substring(i * 39);
			}
		}
		
		System.out.println(astrAddress[0]);
		System.out.println(astrAddress[1]);
		System.out.println(astrAddress[2]);
		System.out.println(astrAddress[3]);
		
		System.out.println(StringUtility.substring(strAddress, 0, 60));
		System.out.println(StringUtility.substring(strAddress, 60, 120));
		System.out.println(StringUtility.substring(strAddress, 120, 180));		
		*/
	}
	
}
