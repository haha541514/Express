package kyle.leis.es.businessrule.weightrule.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.businessrules.dax.IBusinessruleBasicData;
import kyle.leis.es.businessrule.weightrule.da.CarryweightrulevalueQuery;
import kyle.leis.es.businessrule.weightrule.da.VolumeweightrulevalueQuery;
import kyle.leis.es.businessrule.weightrule.da.WeightruleColumns;
import kyle.leis.es.businessrule.weightrule.da.WeightruleCondition;
import kyle.leis.es.businessrule.weightrule.da.WeightruleQuery;
import kyle.leis.es.businessrule.weightrule.da.WeightrulevalueQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiBusinessrulekindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiUnittypeDC;
import kyle.leis.hi.TbrBusinessrule;
import kyle.leis.hi.TbrWeightrule;
import kyle.leis.hi.TbrWeightrulekind;
import kyle.leis.hi.TdiBusinessrulekind;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiServerweightkind;
import kyle.leis.hi.TdiSimplestatus;
import kyle.leis.hi.TdiUnittype;
import net.sf.hibernate.Session;

public class WeightRuleDemand {
	public static List query(WeightruleCondition objWeightruleCondition) 
	throws Exception {
		WeightruleQuery objWeightruleQuery = new WeightruleQuery();
		objWeightruleQuery.setCondition(objWeightruleCondition);
		return objWeightruleQuery.getResults();
	}
	
	public static WeightruleColumns loadWeightRule(String strBrid) throws Exception {
		WeightruleCondition objWeightruleCondition = new WeightruleCondition();
		objWeightruleCondition.setBrid(strBrid);
		List objList = query(objWeightruleCondition);
		if (objList == null || objList.size() < 1) return null;
		return (WeightruleColumns)objList.get(0);
	}
	
	public static List loadWeightrulevalue(String strBrid) throws Exception {
		WeightrulevalueQuery objWRVQuery = new WeightrulevalueQuery();
		objWRVQuery.setBrid(strBrid);
		return objWRVQuery.getResults();
	}
	
	public static List loadCarryweightrulevalue(String strBrid) throws Exception {
		CarryweightrulevalueQuery objCWRVQuery = new CarryweightrulevalueQuery();
		objCWRVQuery.setBrid(strBrid);
		return objCWRVQuery.getResults();
	}
	
	public static List loadVolumeweightrulevalue(String strBrid) throws Exception {
		VolumeweightrulevalueQuery objVWRVQuery = new VolumeweightrulevalueQuery();
		objVWRVQuery.setBrid(strBrid);
		return objVWRVQuery.getResults();
	}
	
	public static LoadWeighruleResult loadResults(String strBrid) throws Exception {
		LoadWeighruleResult objLoadWeighruleResult = new LoadWeighruleResult();
		
		objLoadWeighruleResult.setWeightruleColumns(loadWeightRule(strBrid));
		objLoadWeighruleResult.setCarryweightrulevalue(loadCarryweightrulevalue(strBrid));
		objLoadWeighruleResult.setVolumeweightrulevalue(loadVolumeweightrulevalue(strBrid));
		objLoadWeighruleResult.setWeightruleValue(loadWeightrulevalue(strBrid));
		
		return objLoadWeighruleResult;
	}
	
	public static void setBusinessRuleByColumns(TbrBusinessrule objTbrBusinessrule,
			WeightruleColumns objWeightruleColumns,
			String strOperId,
			Session objSession) throws Exception {
		objTbrBusinessrule.setBrEname(objWeightruleColumns.getBrbrename());
		objTbrBusinessrule.setBrStartdate(DateFormatUtility.getStandardDate(objWeightruleColumns.getBrbrstartdate()));
		objTbrBusinessrule.setBrEnddate(DateFormatUtility.getStandardDate(objWeightruleColumns.getBrbrenddate()));
		objTbrBusinessrule.setBrName(objWeightruleColumns.getBrbrname());
		objTbrBusinessrule.setBrRemark(objWeightruleColumns.getBrbrremark());		
		
		TdiBusinessrulekind objTdiBusinessrulekind = TdiBusinessrulekindDC.loadByKey(IBusinessruleBasicData.BRK_CODE_WEIGHTRULE);
		objTbrBusinessrule.setTdiBusinessrulekind(objTdiBusinessrulekind);		
		
		if (!StringUtility.isNull(strOperId)) {
			TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(strOperId);
			objTbrBusinessrule.setTdiOperatorByBrOpIdModifier(objTdiOperator);
			objTbrBusinessrule.setBrModifydate(DateFormatUtility.getSysdate());
		}
		
		if (!StringUtility.isNull(objWeightruleColumns.getSssscode())) {
			TdiSimplestatus objTdiSimplestatus = TdiSimplestatusDC.loadByKey(objWeightruleColumns.getSssscode());
			objTbrBusinessrule.setTdiSimplestatus(objTdiSimplestatus);
		}
	}
	
	public static void setWeightRuleByColumns(TbrWeightrule objTbrWeightrule,
			WeightruleColumns objWeightruleColumns,
			String strOperId,
			Session objSession) throws Exception {
		if(!StringUtility.isNull(objWeightruleColumns.getWrwrpeactualweight())){
			objTbrWeightrule.setWrPeactualweight(new BigDecimal(objWeightruleColumns.getWrwrpeactualweight()));
		}
			objTbrWeightrule.setWrPelenghtformula(objWeightruleColumns.getWrwrpelenghtformula());
			objTbrWeightrule.setWrPeweightformula(objWeightruleColumns.getWrwrpeweightformula());
		
		if (!StringUtility.isNull(objWeightruleColumns.getWrkwrkid())) {
			TbrWeightrulekind objTbrWeightrulekind = (TbrWeightrulekind)objSession.load(TbrWeightrulekind.class, 
					Long.parseLong(objWeightruleColumns.getWrkwrkid()));
			objTbrWeightrule.setTbrWeightrulekind(objTbrWeightrulekind);
		}
		if (!StringUtility.isNull(objWeightruleColumns.getSwkswkcode())) {
			TdiServerweightkind objTdiServerweightkind = (TdiServerweightkind)objSession.load(TdiServerweightkind.class, 
					objWeightruleColumns.getSwkswkcode());
			objTbrWeightrule.setTdiServerweightkindBySwkCode(objTdiServerweightkind);
		}
		else {
			objTbrWeightrule.setTdiServerweightkindBySwkCode(null);
		}
		// 选渠道的重量类型
		if (!StringUtility.isNull(objWeightruleColumns.getTswkswkcode())) {
			TdiServerweightkind objTdiServerweightkind = (TdiServerweightkind)objSession.load(TdiServerweightkind.class, 
					objWeightruleColumns.getTswkswkcode());
			objTbrWeightrule.setTdiServerweightkindBySwkCodeTransfer(objTdiServerweightkind);
		}
		else {
			objTbrWeightrule.setTdiServerweightkindBySwkCodeTransfer(null);
		}		
		
		if (!StringUtility.isNull(objWeightruleColumns.getUtutcode())) {
			TdiUnittype objTdiUnittype = TdiUnittypeDC.loadByKey(objWeightruleColumns.getUtutcode());
			objTbrWeightrule.setTdiUnittype(objTdiUnittype);
		}
	}
}
