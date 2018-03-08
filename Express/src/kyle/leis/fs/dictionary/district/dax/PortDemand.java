package kyle.leis.fs.dictionary.district.dax;

import java.util.List;
import kyle.leis.fs.dictionary.district.da.PortCondition;
import kyle.leis.fs.dictionary.district.da.PortQuery;
public class PortDemand extends PortQuery{
	
	public static List queryPort(PortCondition objCondition)throws Exception{
		PortQuery objPortQuery = new PortQuery();
		objPortQuery.setCondition(objCondition);
		return objPortQuery.getResults();
	}
	
}
