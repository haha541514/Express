package kyle.leis.es.price.zone.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.price.zone.bl.Zone;
import kyle.leis.es.price.zone.da.ZoneColumns;
import kyle.leis.es.price.zone.da.ZoneCondition;
import kyle.leis.es.price.zone.da.ZonedistrictpostcodeColumns;
import kyle.leis.es.price.zone.da.ZonevalueColumns;
import kyle.leis.es.price.zone.da.ZonevaluedistrictColumns;
import kyle.leis.es.price.zone.dax.ZoneDemand;
import kyle.leis.es.price.zone.dax.ZoneQueryReturn;

public class ZoneService extends AService {
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		ZoneCondition objZoneCondition = (ZoneCondition)objPD.getParameter(0, ZoneCondition.class);
		List objList = ZoneDemand.queryZone(objZoneCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strZncode = (String)objPD.getParameter(0, String.class);
		ZoneQueryReturn objZoneQueryReturn = ZoneDemand.loadByZncode(strZncode);
		
		return objZoneQueryReturn.toString();
	}
	
	public String loadZonevalue(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strZncode = (String)objPD.getParameter(0, String.class);
		List objList = ZoneDemand.loadZoneValue(strZncode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 5, this);

		ZoneColumns objZoneCol = (ZoneColumns)objPD.getParameter(0, ZoneColumns.class);
		List listZnvalueCol = objPD.getParameterList(1, ZonevalueColumns.class);
		List listZnvdistrictCol = objPD.getParameterList(2, ZonevaluedistrictColumns.class);
		List listZndpostcodeCol = objPD.getParameterList(3, ZonedistrictpostcodeColumns.class);
		String strOperId = (String)objPD.getParameter(4, String.class);
		
		Zone objZone = new Zone();
		ZoneQueryReturn objZoneQueryReturn = objZone.save(objZoneCol, 
				listZnvalueCol, 
				listZnvdistrictCol, 
				listZndpostcodeCol, 
				strOperId);
		return objZoneQueryReturn.toString();
	}
	
	public String modifyStatus(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strZncode = (String)objPD.getParameter(0, String.class);
		String strSscode = (String)objPD.getParameter(1, String.class);
		
		Zone objZone = new Zone();
		objZone.modifyStatus(strZncode, strSscode);
		return "";
	}
}
