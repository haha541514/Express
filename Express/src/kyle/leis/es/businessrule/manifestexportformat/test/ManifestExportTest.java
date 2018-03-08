package kyle.leis.es.businessrule.manifestexportformat.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.es.businessrule.manifestexportformat.sv.ManifestService;


public class ManifestExportTest {

	public static void main(String[] args) throws Exception {
		ManifestExportTest test = new ManifestExportTest();
		//test.queryManifestexportformat();
		//test.delete();
		test.saveManifestExoprt();
	}
	
	
	public void queryManifestexportformat() throws Exception{
		
		ManifestService service = new ManifestService();
		//~`~`~`~`~`~`ON~`~`~`~`~`~@~#
		//String quertStr = "88~`~`~`~`~`~`~`~`~`~`~`~@~#";
		String quertStr = "~`~`~`~`~`~`ON~`~`~`~`~`~@~#";
		Decoder objPD = new Decoder(quertStr);
		
		String objReturn = service.queryManifestExport(objPD);
		System.out.println(objReturn);
	}
	public void delete() throws Exception{
		ManifestService service = new ManifestService();
		
		String quertStr = "88~`~`~`~`~`~`~`~`~`~`~`~@~#";
		Decoder objPD = new Decoder(quertStr);
		
		String objReturn = service.deleteManifestExport(objPD);
		System.out.println(objReturn);
		
	}
	public void saveManifestExoprt() throws Exception{
		
		ManifestService service = new ManifestService();
		
		String quertStr = "185~`test004~`haha~`c:\\Leis\\≤‚ ‘ƒ£∞Â\\test.xsl~`2~`1~`~`~`xls~`1360~`~`1360~`~`ON~`~`~@~#";
		String quertStr2 ="80~`~@~#~`~@~#999862581149,2268088550,2268088675~`~@~#~`~`~`~`~`2010-03-09 10:00:00~`2010-03-09 11:59:59~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~@~#";
		Decoder objPD = new Decoder(quertStr2);	
		String mef_code = (String) objPD.getParameter(0, String.class);
		System.out.println(objPD.getParameter(0, String.class));
		//ManifestCusExportcolumnCondition con =  (ManifestCusExportcolumnCondition) objPD.getParameter(1, ManifestCusExportcolumnCondition.class);
		//ManifestDemand demand = new ManifestDemand();
		//String sql = demand.buildSql(mef_code);
		String list = (String) objPD.getParameter(2, String.class);
		System.out.println(list);
		String[] mscdode = list.split(",");
		for(String s:mscdode)
		System.out.println(s);
//		System.out.println(list.get(1));
//		System.out.println(list.get(2));
//		String[] capname_list =demand.queryManifestCaptionName(mef_code);
//		String[][] value_list  =demand.queryManifestValue(sql, con, capname_list.length);
//        String[] caplist = new String[capname_list.size()];
//         for(int i =0;i<capname_list.size();i++){
//        	 caplist[i]=capname_list.get(i);
//         }
//        int row = value_list.size();
//        int col = capname_list.size();
//        String[][] valuelist = new String[value_list.size()][capname_list.size()];
//        for(int i =0;i<row;i++){
//        		valuelist[i]=value_list.get(i);
//        }
//		System.out.println(capname_list.size());
//		System.out.println(value_list.size());
//		Encoder objEncoder = new Encoder();
//		List list = new ArrayList<String>();
////		list.add("3333");
//		objEncoder.addParameter(capname_list);
//    	objEncoder.addParameter(value_list);
//    	objEncoder.addParameter(valuelist);
//    	objEncoder.addParameter("222");
//    	objEncoder.addParameter("333");
//    	System.out.println(objEncoder.toString());
//		String objReturn = service.saveManifestExport(objPD);
//		System.out.println(objReturn);
		
		
	}
	
}
