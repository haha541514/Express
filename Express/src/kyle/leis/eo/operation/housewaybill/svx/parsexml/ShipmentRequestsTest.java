package kyle.leis.eo.operation.housewaybill.svx.parsexml;

import java.io.File;
import java.util.List;

import kyle.common.util.jlang.FileUtility;
import kyle.leis.eo.operation.housewaybill.bl.PredictOrderEX;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderColumnsEX;
import kyle.leis.eo.operation.housewaybill.svx.PredictOrderXML;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;

public class ShipmentRequestsTest {
	public static void main(String[] args) {
		File objFile = new File("E:\\Kyle\\MyESProject\\Java\\kyle-express-1.1.0-src\\src\\kyle\\leis\\eo\\operation\\housewaybill\\svx\\CharsetNote");  //meng.xml
		String str = FileUtility.readFile(objFile, "utf-8");
		
		try {
	    	PredictOrderXML objPredictOrderXML = new PredictOrderXML(str);
	    	PredictOrderColumnsEX objPredictOrderColumnsEX = objPredictOrderXML.parse();
	    	
	    	System.out.println(objPredictOrderColumnsEX.getWaybillforpredict().getCwcw_pieces());
	    	/*
        	PredictOrderEX objPredictOrderEX = new PredictOrderEX(true);
        	InputAllQReturn objIAQR = objPredictOrderEX.save("1", 
        			objPredictOrderColumnsEX, 
        			"0", 
        			true,
        			false);	    	
	    	*/
    		//PredictwaybillColumns pwc = objPredictOrderXML.transformWFDC(objPredictOrderColumnsEX.getWaybillforpredict());
    		List listPCIC = objPredictOrderXML.transformListCIC(objPredictOrderColumnsEX.getListCargoInfo());	    	
	    	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		ShipmentRequests objShipmentRequests = new ShipmentRequests(str);
		System.out.println(objShipmentRequests.getServiceHeader().getUsercode());
		System.out.println(objShipmentRequests.getConsignee().getCity());
		System.out.println(objShipmentRequests.getShipper().getReferenceID());
		System.out.println(objShipmentRequests.getShipmentPieces().getConfig(0).getWidth());
		System.out.println(objShipmentRequests.getShipmentContents().getConfig(0).getDescription());
	}

}
