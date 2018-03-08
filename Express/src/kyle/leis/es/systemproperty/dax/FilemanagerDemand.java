package kyle.leis.es.systemproperty.dax;


import java.util.List;

import kyle.leis.es.systemproperty.da.FilemanagerColumns;
import kyle.leis.es.systemproperty.da.FilemanagerCondition;
import kyle.leis.es.systemproperty.da.FilemanagerQuery;
import kyle.leis.es.systemproperty.tp.SaveFileManagerTransaction;


public class FilemanagerDemand {
	@SuppressWarnings("unchecked")
	public static List query(FilemanagerCondition objFMCondition) throws Exception{
		FilemanagerQuery objSPQuery = new FilemanagerQuery();
		objSPQuery.setCondition(objFMCondition);
		return objSPQuery.getResults();
	}
	public static void add(FilemanagerColumns objFMColumns) throws Exception{
		   SaveFileManagerTransaction objSFMT=new SaveFileManagerTransaction();
		   objSFMT.setParam(objFMColumns);
		   objSFMT.execute();
	}
}
