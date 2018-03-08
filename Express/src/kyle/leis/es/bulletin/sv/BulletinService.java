package kyle.leis.es.bulletin.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.bulletin.bl.Bulletin;
import kyle.leis.es.bulletin.da.BulletinColumns;
import kyle.leis.es.bulletin.da.BulletinCondition;
import kyle.leis.es.bulletin.dax.BulletinDemand;

public class BulletinService extends AService {
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		BulletinCondition objBLCondition = (BulletinCondition)objPD.getParameter(0, 
				BulletinCondition.class);
		List objList = BulletinDemand.query(objBLCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		BulletinColumns objBulletinColumns = (BulletinColumns)objPD.getParameter(0, 
				BulletinColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		Bulletin objBulletin = new Bulletin();
		objBulletin.save(objBulletinColumns, strOperId);
		
		return "";
	}
	
	public String delete(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strBlid = (String)objPD.getParameter(0, String.class);
		
		Bulletin objBulletin = new Bulletin();
		objBulletin.delete(strBlid);
		
		return "";
	}
}
