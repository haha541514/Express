package kyle.leis.fs.dictionary.district.dax;

import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.district.da.DhlremotedistrictCondition;
import kyle.leis.fs.dictionary.district.da.DhlremotedistrictQuery;
import kyle.leis.fs.dictionary.district.da.FdxremotedistrictCondition;
import kyle.leis.fs.dictionary.district.da.FdxremotedistrictQuery;
import kyle.leis.fs.dictionary.district.da.StatisticdhlremotedistrictCondition;
import kyle.leis.fs.dictionary.district.da.StatisticdhlremotedistrictQuery;
import kyle.leis.fs.dictionary.district.da.TntremotedistrictCondition;
import kyle.leis.fs.dictionary.district.da.TntremotedistrictQuery;
import kyle.leis.fs.dictionary.district.da.UpsremotedistrictCondition;
import kyle.leis.fs.dictionary.district.da.UpsremotedistrictQuery;

public class DHLRemoteDistrictDemand {
	public static List query(DhlremotedistrictCondition objDRDCondition) throws Exception {
		DhlremotedistrictQuery objDRDQuery = new DhlremotedistrictQuery();
		objDRDQuery.setCondition(objDRDCondition);
		return objDRDQuery.getResults();
	}
	
	public static List queryDRDByCountryHubcode(String strCountryHubcode) throws Exception
	{
		StatisticdhlremotedistrictQuery objSDRDQuery = new StatisticdhlremotedistrictQuery();
		if(!StringUtility.isNull(strCountryHubcode))
		{
			StatisticdhlremotedistrictCondition objSDRDCondition = new StatisticdhlremotedistrictCondition();
			objSDRDCondition.setDrd_nationcode(strCountryHubcode);
			objSDRDQuery.setCondition(objSDRDCondition);
		}
		List objResult = objSDRDQuery.getResults();
		if(CollectionUtility.isNull(objResult)) 
			return null;
		return objResult;
	}
	
	public static List queryFedex(FdxremotedistrictCondition objFRDC) throws Exception {
		FdxremotedistrictQuery objFRDQ = new FdxremotedistrictQuery();
		objFRDQ.setCondition(objFRDC);
		return objFRDQ.getResults();
	}
	
	public static List queryTNT(TntremotedistrictCondition objFRDC) throws Exception {
		TntremotedistrictQuery objFRDQ = new TntremotedistrictQuery();
		objFRDQ.setCondition(objFRDC);
		return objFRDQ.getResults();
	}
	
	
	public static List queryUps(UpsremotedistrictCondition objURDC) throws Exception {
		UpsremotedistrictQuery objURDQ = new UpsremotedistrictQuery();
		objURDQ.setCondition(objURDC);
		return objURDQ.getResults();
	}
}
