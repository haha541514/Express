package kyle.leis.fs.dictionary.district.dax;

import java.util.List;

import kyle.leis.fs.dictionary.district.da.AirwaycompanyCondition;
import kyle.leis.fs.dictionary.district.da.AirwaycompanyQuery;
import kyle.leis.fs.dictionary.district.da.PortCondition;
import kyle.leis.fs.dictionary.district.da.PortQuery;
public class AirwayCompanyDemand extends AirwaycompanyQuery{
	
	public static List queryAirwayCompany(AirwaycompanyCondition objacCondition)throws Exception{
		AirwaycompanyQuery objacQuery = new AirwaycompanyQuery();
		objacQuery.setCondition(objacCondition);
		return objacQuery.getResults();
	}
	
}
