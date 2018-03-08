package kyle.leis.fs.dictionary.enterpriseelement.dax;

import java.util.List;

import kyle.leis.fs.dictionary.enterpriseelement.da.DistributioncenterCondition;
import kyle.leis.fs.dictionary.enterpriseelement.da.DistributioncenterQuery;

public class DistributioncenterDemand {

	public static List query(DistributioncenterCondition objDistributioncenterCon) throws Exception
	{
		DistributioncenterQuery objDistributioncenterQue = new DistributioncenterQuery();
		objDistributioncenterQue.setCondition(objDistributioncenterCon);
		return objDistributioncenterQue.getResults();
	}
}
