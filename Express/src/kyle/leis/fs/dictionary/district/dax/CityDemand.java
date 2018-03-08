package kyle.leis.fs.dictionary.district.dax;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.util.jlang.CollectionUtility;
import kyle.leis.fs.dictionary.district.da.CityColumns;
import kyle.leis.fs.dictionary.district.da.CityCondition;
import kyle.leis.fs.dictionary.district.da.CityExportColumns;
import kyle.leis.fs.dictionary.district.da.CityQuery;
import kyle.leis.fs.dictionary.district.da.DicdistrictColumns;
import kyle.leis.fs.dictionary.district.da.StateColumns;

/**
 * 20160817 15:36 by wukaiquan
 * 
 * **/
public class CityDemand {

	
	
	
	/**
	 * 20160817 15:36 by wukaiquan option:×ª»»
	 * **/
	public static void setCityColumns(CityColumns columns,
			CityExportColumns objCityExportColumns,
			StateColumns objStateColumns, DicdistrictColumns objDistrictColumns) {

		columns.setStstcode(objStateColumns.getStstcode());
		columns.setDidtcode(objDistrictColumns.getDddtcode());
		columns.setCictsname(objCityExportColumns.getTdcsname());
		columns.setCictname(objCityExportColumns.getTdcctname());
		columns.setCictename(objCityExportColumns.getTdcctename());
		columns.setCictstartpostcode(objCityExportColumns
				.getTdcctstartpostcode());
		columns.setCictendpostcode(objCityExportColumns.getTdcctendpostcode());

	}

	public static List queryCity(CityCondition condtion) throws Exception {
		
		CityQuery query = new CityQuery();
		query.setCondition(condtion);
		List listReturn = query.getResults();
		
		if(CollectionUtility.isNull(listReturn) || listReturn.size() == 0){
			return null;
		}else{
			return listReturn;
		}
		
	}

}
