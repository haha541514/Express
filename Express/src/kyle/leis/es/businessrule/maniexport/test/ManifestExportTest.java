package kyle.leis.es.businessrule.maniexport.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.es.businessrule.maniexport.sv.ManifestService;


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
		Decoder objPD = new Decoder(quertStr);
		
		String objReturn = service.saveManifestExport(objPD);
		System.out.println(objReturn);
		
		
	}
	
}
