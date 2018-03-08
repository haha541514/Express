package kyle.leis.fs.dictionary.enterpriseelement.bl;

import java.util.List;

import kyle.leis.fs.dictionary.enterpriseelement.da.DistributioncenterColumns;
import kyle.leis.fs.dictionary.enterpriseelement.da.DistributioncenterCondition;
import kyle.leis.fs.dictionary.enterpriseelement.dax.DistributioncenterDemand;
import kyle.leis.fs.dictionary.enterpriseelement.tp.SaveDistributioncenterTrans;

public class Distributioncenter {

	public DistributioncenterColumns addDistributioncenter(DistributioncenterColumns objDistributioncenterCol,
			List listEECity,
			String strOperId) throws Exception
	{
		SaveDistributioncenterTrans objSaveDistributioncenterTrans = new SaveDistributioncenterTrans();
		objSaveDistributioncenterTrans.setParam(objDistributioncenterCol, 
				listEECity, 
				strOperId);
		objSaveDistributioncenterTrans.execute();
		
		DistributioncenterCondition objDistributioncenterCon = new DistributioncenterCondition();
		objDistributioncenterCon.setDceecode(objSaveDistributioncenterTrans.getNewEecode());
		return (DistributioncenterColumns)DistributioncenterDemand.query(objDistributioncenterCon).get(0);
	}
}
