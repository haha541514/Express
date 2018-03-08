package kyle.leis.es.businessrule.weightrulekind.dax;

import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.weightrulekind.da.WeightrulekindColumns;
import kyle.leis.es.businessrule.weightrulekind.da.WeightrulekindCondition;
import kyle.leis.es.businessrule.weightrulekind.da.WeightrulekindQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPricedomainDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.hi.TbrWeightrulekind;
import kyle.leis.hi.TdiPricedomain;
import kyle.leis.hi.TdiProductkind;
import kyle.leis.hi.TdiSimplestatus;

public class WeightRuleKindDemand {
	
	public static List query(WeightrulekindCondition objWRKCondition) 
	throws Exception {
		WeightrulekindQuery objWRKQuery = new WeightrulekindQuery();
		objWRKQuery.setCondition(objWRKCondition);
		return objWRKQuery.getResults();
	}
	
	public static WeightrulekindColumns load(String strWrkid) 
	throws Exception {
		WeightrulekindCondition objWRKCondition = new WeightrulekindCondition();
		objWRKCondition.setWrkid(strWrkid);
		List objList = query(objWRKCondition);
		
		if (objList == null || objList.size() < 1) return null;
		return (WeightrulekindColumns)objList.get(0);
	}
	
	public static void setWeightrulekindByColumns(TbrWeightrulekind objTbrWeightrulekind,
			WeightrulekindColumns objWRKColumns,
			String strOperId,
			Session objSession) throws Exception {
		if (!StringUtility.isNull(objWRKColumns.getPdpdcode())) {
			TdiPricedomain objTdiPricedomain = TdiPricedomainDC.loadByKey(objWRKColumns.getPdpdcode());
			objTbrWeightrulekind.setTdiPricedomain(objTdiPricedomain);
		}
		if (!StringUtility.isNull(objWRKColumns.getSssscode())) {
			TdiSimplestatus objTdiSimplestatus = TdiSimplestatusDC.loadByKey(objWRKColumns.getSssscode());
			objTbrWeightrulekind.setTdiSimplestatus(objTdiSimplestatus);
		}
		if (!StringUtility.isNull(objWRKColumns.getPkpkcode())) {
			TdiProductkind objTdiProductkind = TdiProductkindDC.loadByKey(objWRKColumns.getPkpkcode());
			objTbrWeightrulekind.setTdiProductkind(objTdiProductkind);
		}		
		
		objTbrWeightrulekind.setWrkDefaultsign(objWRKColumns.getWrkwrkdefaultsign());
		objTbrWeightrulekind.setWrkEname(objWRKColumns.getWrkwrkename());
		objTbrWeightrulekind.setWrkName(objWRKColumns.getWrkwrkname());
	}
	
}
