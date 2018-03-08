package kyle.leis.eo.operation.manifest.test;

import java.text.DecimalFormat;
import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybill.dax.ICorewaybillBasicData;
import kyle.leis.eo.operation.corewaybillcode.dax.CorewaybillcodeDemand;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.manifest.dax.DGMSPSDataFactory;
import kyle.leis.eo.operation.manifest.dax.DHLSPSData;
import kyle.leis.eo.operation.manifest.dax.ManifestDemand;
import kyle.leis.eo.operation.manifest.sv.ManifestService;

public class ManifestTest {
	private static ManifestService s_objManifestService = new ManifestService();
	public static void main(String[] args) {
		try {
			// queryCreageManifestData();
			// queryManifest();
			// createManifest();
			// querySpsContent();
			// createManifest();
			// loadResult();
			querySPSData();
			//rebuildCustomerHAWBCode();
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	public static void rebuildCustomerHAWBCode() {
		String strChnCHAWBPrefix = "";
		String strCustomerHAWBCode = "0123456789abcdefg";
		System.out.println(CorewaybillcodeDemand.rebuildCustomerHAWBCode(strChnCHAWBPrefix,
				strCustomerHAWBCode));
	}
	
	public static void querySPSData() throws Exception {
		/*
		HousewaybillColumns objHWColumns = HousewaybillDemand.load("5616829701A", ICorewaybillBasicData.EWBCODE_TYPE_CUSTOMER);
		DHLSPSData objDHLSPSData = new DHLSPSData();
		System.out.println(objDHLSPSData.build(objHWColumns.getHwcwcode()));
		*/
		
		String strEwbcode = "01993126509999989125F162343201000930209|4203037|9247609364|8008151125165859";
		String strDeliveryPointIdentifier = "";
		if (strEwbcode.indexOf("|") >= 0) {
			String[] astrEwbcode = strEwbcode.split("\\|");
			if (astrEwbcode != null && astrEwbcode.length >= 4) {
				strDeliveryPointIdentifier = astrEwbcode[2];
				if (!StringUtility.isNull(strDeliveryPointIdentifier) && strDeliveryPointIdentifier.length() >= 10)
					strDeliveryPointIdentifier = strDeliveryPointIdentifier.substring(2);
			}
		}
		System.out.println(strDeliveryPointIdentifier);
		
		double pieces = 23333.23344;
		System.out.println(new DecimalFormat("#").format(pieces));
		
		/*
		String strDbwLabelcode = "BQCSZX-HKDGM-UK专线-160317A";
		DGMSPSDataFactory objDGMSPSDataFactory = new DGMSPSDataFactory();
		String strResults = objDGMSPSDataFactory.buildDGMSPSData(strDbwLabelcode);	
		System.out.println(strResults);
		*/
		
	}
	
	public static void queryCreageManifestData() throws Exception {
		String str = "";
		Decoder objPD = new Decoder(str);
		System.out.println(s_objManifestService.queryCreateManifestData(objPD));
	}
	
	public static void queryManifest() throws Exception {
		String str = "~`ON~`2010-01-10 19:03:00~`2010-01-10 19:03:00~`~@~#";
		Decoder objPD = new Decoder(str);
		System.out.println(s_objManifestService.queryManifest(objPD));
	}	
	
	public static void createManifest() throws Exception {
		String str = "1~`~@~#由周兵导出清单数据~`~@~#1~`~@~#";
		Decoder objPD = new Decoder(str);
		System.out.println(s_objManifestService.createManifest(objPD));
	}
	
	public static void querySpsContent()  throws Exception {
		ManifestDemand objManifestDemand = new ManifestDemand();
		List objList = objManifestDemand.querySpsContent("1");
		System.out.println(objList);
	}
	
	public static void loadResult() throws Exception {
		String str = "11~`~@~#";
		Decoder objPD = new Decoder(str);
		System.out.println(s_objManifestService.loadResults(objPD));		
	}
	
}
