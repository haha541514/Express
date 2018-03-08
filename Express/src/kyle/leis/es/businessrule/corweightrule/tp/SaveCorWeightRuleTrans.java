package kyle.leis.es.businessrule.corweightrule.tp;

import java.util.ArrayList;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.businessrules.dax.IBusinessruleBasicData;
import kyle.leis.es.businessrule.corweightrule.da.CorweightruleColumns;
import kyle.leis.es.businessrule.corweightrule.dax.CorWeightRuleDate;
import kyle.leis.es.businessrule.corweightrule.dax.CorWeightRuleDemand;
import kyle.leis.es.ruledate.ARuleDate;
import kyle.leis.es.ruledate.RuleCheckReturn;
import kyle.leis.hi.TbrBusinessrule;
import kyle.leis.hi.TbrCorweightrule;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;

public class SaveCorWeightRuleTrans extends AbstractTransaction {
	private ModifyCWRuleDateTrans m_objMCWRDateTrans;
	private CorweightruleColumns m_objCorweightruleColumns;
	private String m_strOperId;
	
	private Long m_lNewBrid;
	
	public ArrayList<RuleCheckReturn> setConflictParam(CorweightruleColumns objCWRColumns,
			String strOperId,
			String strChangedStartDate,
			String strChangedEndDate,
			boolean isCheckDateConflic) throws Exception {
		objCWRColumns.setCwrbrid(null);
		objCWRColumns.setBrbrstartdate(DateFormatUtility.getStandardDate(strChangedStartDate));
		objCWRColumns.setBrbrenddate(DateFormatUtility.getStandardDate(strChangedEndDate));    	
    	
    	return setParam(objCWRColumns, strOperId, isCheckDateConflic);
	}
	
	public ArrayList<RuleCheckReturn> setParam(CorweightruleColumns objCorweightruleColumns,
			String strOperId,
			boolean isCheckDateConflict) throws Exception {
		m_objCorweightruleColumns = objCorweightruleColumns;
		m_strOperId = strOperId;
		// 检查日期是否冲突
		if (isCheckDateConflict) {
			ARuleDate objRuleDate = new CorWeightRuleDate();
			ArrayList<RuleCheckReturn> alRuleCheckReturn = objRuleDate.checkRuleDate(objCorweightruleColumns);
			if (alRuleCheckReturn != null && alRuleCheckReturn.size() > 0) {
				m_objMCWRDateTrans = new ModifyCWRuleDateTrans();
				m_objMCWRDateTrans.setParam(alRuleCheckReturn);
			}
			return alRuleCheckReturn;
		}
		return null;		
	}
	
	public Long getNewBrid() {
		return m_lNewBrid;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objCorweightruleColumns == null)
			return;
		TbrBusinessrule objTbrBusinessrule = null;
		TbrCorweightrule objTbrCorweightrule = null;
		String strBrid = m_objCorweightruleColumns.getCwrbrid();
		// 新增或修改
		if (StringUtility.isNull(strBrid)) {
			objTbrBusinessrule = new TbrBusinessrule();
			objTbrCorweightrule = new TbrCorweightrule();
			objTbrBusinessrule.setBrCreatedate(DateFormatUtility.getSysdate());
			
			if (!StringUtility.isNull(m_strOperId)) {
				TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, 
						Long.parseLong(m_strOperId));
				objTbrBusinessrule.setTdiOperatorByBrOpIdCreate(objTdiOperator);
				objTbrBusinessrule.setBrCreatedate(DateFormatUtility.getSysdate());
				// 设置新建状态
				TdiSimplestatus objTdiSimplestatus = (TdiSimplestatus)objSession.load(TdiSimplestatus.class, 
						IBusinessruleBasicData.SIMPLESTATUS_NEW);
				objTbrBusinessrule.setTdiSimplestatus(objTdiSimplestatus);
			}
		} else {
			objTbrBusinessrule = (TbrBusinessrule)objSession.load(TbrBusinessrule.class, 
					Long.parseLong(strBrid));
			objTbrCorweightrule = (TbrCorweightrule)objSession.load(TbrCorweightrule.class, 
					Long.parseLong(strBrid));		
		}
		CorWeightRuleDemand.setBusinessruleByColumns(objTbrBusinessrule, 
				m_objCorweightruleColumns, 
				m_strOperId, 
				objSession);
		CorWeightRuleDemand.setCorweightruleByColumns(objTbrCorweightrule, 
				m_objCorweightruleColumns, 
				objSession);
		// 保存规则
		objSession.save(objTbrBusinessrule);
		m_lNewBrid = objTbrBusinessrule.getBrId();
		// 保存公司重量规则
		objTbrCorweightrule.setBrId(m_lNewBrid);
		objTbrCorweightrule.setTbrBusinessrule(objTbrBusinessrule);
		objSession.save(objTbrCorweightrule);
		// 修改日期冲突的规则表
		if (m_objMCWRDateTrans != null)
			m_objMCWRDateTrans.transaction(objSession);				
	}

}
