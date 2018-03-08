package kyle.leis.fs.waybillcode.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Vector;

import kyle.common.connectors.util.Decoder;
import kyle.common.util.jlang.FileUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.corewaybill.dax.HUEMSComplexPrefix;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.fs.waybillcode.bl.AWaybillcode;
import kyle.leis.fs.waybillcode.bl.DGMCoreWaybillcode;
import kyle.leis.fs.waybillcode.bl.DHLCoreWaybillcode;
import kyle.leis.fs.waybillcode.bl.HKPKWaybillcode;
import kyle.leis.fs.waybillcode.bl.HUCoreWaybillcode;
import kyle.leis.fs.waybillcode.bl.IComplexPrefix;
import kyle.leis.fs.waybillcode.bl.WaybillcodeFactory;
import kyle.leis.fs.waybillcode.sv.WaybillcodeService;

public class WaybillcodeTest {

	private static WaybillcodeService m_objWaybillcodeService = new WaybillcodeService();
	
	public static void main(String [] args)
	{
		try
		{
			// System.out.println(register());
            // System.out.println(getUsedLabelcode());
            // System.out.println(checkLabelcode());
			// System.out.println(getDHLDGMVerifyNo());
   		    // System.out.println(query());
			Vector v = new Vector();
			System.out.println(testHKWaybillcode());
			//System.out.println(getHUEMSVerifyNo());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String testHKWaybillcode() throws Exception {
		HKPKWaybillcode obj = new HKPKWaybillcode(null);
		StringBuffer sb = new StringBuffer();
		/*
		String[] astr = {
				"34072601",
				"34072602",
				"34072603",
				"34072604",
				"34072605",
				"34072606",
				"34072607",
				"34072611",
				};
		
		for (String str : astr) {
			sb.append(String.valueOf(obj.buildLabelcode(str, "RX", "DE")));
			sb.append("\r\n");
		}
		System.out.println(sb.toString());
		*/
		
		int iFile = 0;
		
		for (int i = 14463988; i <= 14513988; i++) {
			sb.append(String.valueOf(obj.buildLabelcode(String.valueOf(i), "LW", "DE")));
			sb.append("\r\n");
			if (i % 5000 == 0) {
				FileUtility.createFile("E:/waybill", i + ".txt", sb.toString());
				sb = new StringBuffer();
			}
			iFile = i;
		}
		
		/*
		for (int i = 862501; i <= 872500; i++) {
			sb.append(String.valueOf(obj.buildLabelcode(String.valueOf(i), "RL1005", "")));
			sb.append("\r\n");
			if (i % 5000 == 0) {
				FileUtility.createFile("E:/waybill", i + ".txt", sb.toString());
				sb = new StringBuffer();
			}
			iFile = i;
		}		
		*/
		FileUtility.createFile("E:/waybill", iFile + ".txt", sb.toString());
		
		
		
		char c = '\u00f1';
		String str = "\\u00f1";
		System.out.println("str:" + str + "c:" + c);
		
		return "";
		
	}
	
	public static String getDHLDGMVerifyNo() throws Exception {
		DGMCoreWaybillcode objDGMCoreWaybillcode = new DGMCoreWaybillcode(null);
		return objDGMCoreWaybillcode.getVerifyNumber("0107298", "4204978392748999963297");
		
	}	
	
	public static String getHUEMSVerifyNo() throws Exception {
		HUCoreWaybillcode obj = new HUCoreWaybillcode(null);
		
		ForinputallColumns objFIAColumns = new ForinputallColumns();
		objFIAColumns.setHwconsigneepostcode("01234");
		IComplexPrefix cp = new HUEMSComplexPrefix(objFIAColumns);
		
		obj.setComplexPrefix(cp);
		return obj.buildLabelcode("10001234", "RL", "");
	}		
	
	
	public static String checkLabelcode() throws Exception {
		AWaybillcode objWaybillcode = new DHLCoreWaybillcode();
		PromptUtilityCollection objPU = objWaybillcode.checkLabelcode("HF-0002021");
		if (objPU.canGo(false)) return "Success";
		else
			return objPU.getCollection().get(0).getDescribtion();
	}
	
	
	public static String register() throws Exception
	{
//		String str = "~`81~`641~`~`~`~`~`~`~`DHLMC~`~`~`~`~`~`~`~`RG~`~`~@~#1058~`~@~#true~`~@~#";
		String str = "21~`81~`151~`~`~`~`~`~`Remark~`DHLMC~`~`~`~`~`~`~`~`RG~`~`~@~#1058~`~@~#false~`~@~#";
		Decoder objPD = new Decoder(str);
		return m_objWaybillcodeService.register(objPD);
	}
	
	public static String getUsedLabelcode() throws Exception
	{
		AWaybillcode objDCWC = WaybillcodeFactory.getWaybillcode("SGDGMRE");
		ArrayList<String> al = objDCWC.getUsedLabelcode("0", 1);
		return "";
	}
	
	public static String query() throws Exception
	{
		String str = "1~`~`~`~`~`~`~`~`~`~@~#";
		Decoder objPD = new Decoder(str);
		return m_objWaybillcodeService.query(objPD);
	}
}
