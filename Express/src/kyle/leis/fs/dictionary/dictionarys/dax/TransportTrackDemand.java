package kyle.leis.fs.dictionary.dictionarys.dax;

import java.util.List;

import kyle.leis.fs.dictionary.dictionarys.da.TransporttrackmappingColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TransporttrackmappingQuery;

public class TransportTrackDemand {
	public static String getTrackStatus(String strTwbscode) throws Exception {
		TransporttrackmappingQuery objTTMQuery = new TransporttrackmappingQuery();
		objTTMQuery.setUseCachesign(true);
		List objList = objTTMQuery.getResults();
		if (objList == null || objList.size() < 1)
			return "";
		for (int i = 0; i < objList.size(); i++) {
			TransporttrackmappingColumns objTTMColumns = (TransporttrackmappingColumns)objList.get(i);
			if (objTTMColumns.getTtmcomp_idtwbscode().equals(strTwbscode))
				return objTTMColumns.getTtmcomp_idwbtscode();
		}
		return "";
	}
}
