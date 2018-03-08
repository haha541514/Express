package kyle.leis.es.businessrule.channeltrackmapping.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.businessrule.channeltrackmapping.bl.Channeltrackmapping;
import kyle.leis.es.businessrule.channeltrackmapping.da.ChanneltrackmappingColumns;
import kyle.leis.es.businessrule.channeltrackmapping.da.ChanneltrackmappingCondition;
import kyle.leis.es.businessrule.channeltrackmapping.dax.ChanneltrackmappingDemand;

public class ChanneltrackmappingService extends AService {
	
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		ChanneltrackmappingCondition objCTMC = (ChanneltrackmappingCondition)objPD.getParameter(0, 
				ChanneltrackmappingCondition.class);
		List objList = ChanneltrackmappingDemand.query(objCTMC);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		ChanneltrackmappingColumns objCTMC = (ChanneltrackmappingColumns)objPD.getParameter(0, 
				ChanneltrackmappingColumns.class);
		String strOperID = (String)objPD.getParameter(1, String.class);		
		Channeltrackmapping objCTM = new Channeltrackmapping();
		objCTMC = objCTM.save(objCTMC, strOperID);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objCTMC);
		return objEncode.toString();
	}
	
	public String delete(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strCtmid = (String)objPD.getParameter(0, String.class);
		String strOperID = (String)objPD.getParameter(1, String.class);
		
		Channeltrackmapping objCTM = new Channeltrackmapping();
		objCTM.delete(strCtmid, strOperID);
		
		return "";
	}	
	
}
