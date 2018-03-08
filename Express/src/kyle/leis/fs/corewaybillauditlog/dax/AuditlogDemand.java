package kyle.leis.fs.corewaybillauditlog.dax;

import java.util.List;

import kyle.leis.fs.corewaybillauditlog.da.CorewaybillauditlogQuery;

public class AuditlogDemand {
	public static List query(String strCwcode) throws Exception {
		CorewaybillauditlogQuery objCALQuery = new CorewaybillauditlogQuery();
		objCALQuery.setCwcode(strCwcode);
		return objCALQuery.getResults();
	}
}
