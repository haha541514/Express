package kyle.leis.es.businessrule.corweightrule.dax;

import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.businessrules.dax.IBusinessruleBasicData;
import kyle.leis.es.businessrule.corweightrule.da.CorweightruleColumns;
import kyle.leis.es.businessrule.corweightrule.da.CorweightruleCondition;
import kyle.leis.es.businessrule.corweightrule.da.CorweightruleQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiBusinessrulekindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.hi.TbrBusinessrule;
import kyle.leis.hi.TbrCorweightrule;
import kyle.leis.hi.TbrWeightrulekind;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TdiBusinessrulekind;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;

public class CorWeightRuleDemand {
	public static List query(CorweightruleCondition objCorWRCondition) 
	throws Exception {
		CorweightruleQuery objCorWRQuery = new CorweightruleQuery();
		objCorWRQuery.setCondition(objCorWRCondition);
		return objCorWRQuery.getResults();
	}
	
	public static CorweightruleColumns load(String strBrid) throws Exception {
		CorweightruleCondition objCorWRCondition = new CorweightruleCondition();
		objCorWRCondition.setBrid(strBrid);
		List objList = query(objCorWRCondition);
		if (objList == null || objList.size() < 1) return null;
		return (CorweightruleColumns)objList.get(0);
	}
	
	public static void setBusinessruleByColumns(TbrBusinessrule objTbrBusinessrule,
			CorweightruleColumns objCorweightruleColumns,
			String strOperId,
			Session objSession) throws Exception {
		if (StringUtility.isNull(objCorweightruleColumns.getBrbrename()))
			objTbrBusinessrule.setBrEname("corweightrule ename");
		else
			objTbrBusinessrule.setBrEname(objCorweightruleColumns.getBrbrename());
		
		objTbrBusinessrule.setBrStartdate(DateFormatUtility.getStandardDate(objCorweightruleColumns.getBrbrstartdate()));
		objTbrBusinessrule.setBrEnddate(DateFormatUtility.getStandardDate(objCorweightruleColumns.getBrbrenddate()));
		objTbrBusinessrule.setBrName(objCorweightruleColumns.getBrbrname());
		objTbrBusinessrule.setBrRemark(objCorweightruleColumns.getBrbrremark());
		
		TdiBusinessrulekind objTdiBusinessrulekind = TdiBusinessrulekindDC.loadByKey(IBusinessruleBasicData.BRK_CODE_WEIGHTRULE);
		objTbrBusinessrule.setTdiBusinessrulekind(objTdiBusinessrulekind);			
		
		if (!StringUtility.isNull(strOperId)) {
			TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(strOperId);
			objTbrBusinessrule.setTdiOperatorByBrOpIdModifier(objTdiOperator);
			objTbrBusinessrule.setBrModifydate(DateFormatUtility.getSysdate());
		}
		if (!StringUtility.isNull(objCorweightruleColumns.getSssscode())) {
			TdiSimplestatus objTdiSimplestatus = TdiSimplestatusDC.loadByKey(objCorweightruleColumns.getSssscode());
			objTbrBusinessrule.setTdiSimplestatus(objTdiSimplestatus);
		}
	}
	
	public static void setCorweightruleByColumns(TbrCorweightrule objTbrCorweightrule,
			CorweightruleColumns objCorweightruleColumns,
			Session objSession) throws Exception {
		if (!StringUtility.isNull(objCorweightruleColumns.getWrkwrkid())) {
			TbrWeightrulekind objTbrWeightrulekind = (TbrWeightrulekind)objSession.load(TbrWeightrulekind.class, 
					Long.parseLong(objCorweightruleColumns.getWrkwrkid()));
			objTbrCorweightrule.setTbrWeightrulekind(objTbrWeightrulekind);
		}
		if (!StringUtility.isNull(objCorweightruleColumns.getChnchncode())) {
			TchnChannel objTchnChannel = (TchnChannel)objSession.load(TchnChannel.class, 
					objCorweightruleColumns.getChnchncode());
			objTbrCorweightrule.setTchnChannel(objTchnChannel);
		}
		if (!StringUtility.isNull(objCorweightruleColumns.getCococode())) {
			TcoCorporation objTcoCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					objCorweightruleColumns.getCococode());
			objTbrCorweightrule.setTcoCorporation(objTcoCorporation);
		}
	}
}
