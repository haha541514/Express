package kyle.leis.es.systemproperty.bl;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.systemproperty.da.SystempropertyCondition;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.es.systemproperty.tp.SaveSystemPropertyTransaction;

public class Systemproperty {

	public List add(List listSPColumns) throws Exception
	{
		SaveSystemPropertyTransaction objSaveSPTransaction = new SaveSystemPropertyTransaction();
		objSaveSPTransaction.setParam(listSPColumns);
		objSaveSPTransaction.execute();
		
		if(StringUtility.isNull(objSaveSPTransaction.getEecode())) return null;
		SystempropertyCondition objSPCondition = new SystempropertyCondition();
		objSPCondition.setEecode(objSaveSPTransaction.getEecode());
		return SystempropertyDemand.query(objSPCondition);
	}
}
