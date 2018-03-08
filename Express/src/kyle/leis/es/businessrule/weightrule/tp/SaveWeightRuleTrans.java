package kyle.leis.es.businessrule.weightrule.tp;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.businessrules.dax.IBusinessruleBasicData;
import kyle.leis.es.businessrule.weightrule.da.WeightruleColumns;
import kyle.leis.es.businessrule.weightrule.dax.LoadWeighruleResult;
import kyle.leis.es.businessrule.weightrule.dax.WeightRuleDemand;
import kyle.leis.es.businessrule.weightrule.dax.WeightruleDate;
import kyle.leis.es.ruledate.ARuleDate;
import kyle.leis.es.ruledate.RuleCheckReturn;
import kyle.leis.hi.TbrBusinessrule;
import kyle.leis.hi.TbrWeightrule;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;

public class SaveWeightRuleTrans extends AbstractTransaction {
	private ModifyWeightruleDateTrans m_objMWRDateTrans;
	private WeightruleColumns m_objWeightruleColumns;
	private List m_listWRVColumns;
	private List m_listVWRVColumns;
	private List m_listCWRVColumns;
	private String m_strOperId;
	
	private Long m_lNewBrid;
	
	public ArrayList<RuleCheckReturn> setConflictParam(LoadWeighruleResult objLoadResult,
			String strOperId,
			String strChangedStartDate,
			String strChangedEndDate,
			boolean isCheckDateConflic) throws Exception {
		WeightruleColumns objWeightruleColumns = objLoadResult.getWeightruleColumns();
		objWeightruleColumns.setWrbrid(null);
		objWeightruleColumns.setBrbrstartdate(DateFormatUtility.getStandardDate(strChangedStartDate));
		objWeightruleColumns.setBrbrenddate(DateFormatUtility.getStandardDate(strChangedEndDate));    	
    	
    	return setParam(objWeightruleColumns,
    			objLoadResult.getWeightruleValue(),
    			objLoadResult.getVolumeweightrulevalue(),
    			objLoadResult.getCarryweightrulevalue(),
    			strOperId,
    			isCheckDateConflic);		
	}
	
	public ArrayList<RuleCheckReturn> setParam(WeightruleColumns objWeightruleColumns,
			List listWRVColumns,
			List listVWRVColumns,
			List listCWRVColumns,
			String strOperId,
			boolean isCheckDateConflict) throws Exception {
		m_objWeightruleColumns = objWeightruleColumns;
		m_listWRVColumns = listWRVColumns;
		m_listVWRVColumns = listVWRVColumns;
		m_listCWRVColumns = listCWRVColumns;
		m_strOperId = strOperId;
		// 检查日期是否冲突
		if (isCheckDateConflict) {
			ARuleDate objRuleDate = new WeightruleDate();
			ArrayList<RuleCheckReturn> alRuleCheckReturn = objRuleDate.checkRuleDate(objWeightruleColumns);
			if (alRuleCheckReturn != null && alRuleCheckReturn.size() > 0) {
				m_objMWRDateTrans = new ModifyWeightruleDateTrans();
				m_objMWRDateTrans.setParam(alRuleCheckReturn);
			}
			return alRuleCheckReturn;
		}
		return null;		
	}
	
	public Long getNewBrid() {
		return m_lNewBrid;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objWeightruleColumns == null) return;
		
		TbrWeightrule objTbrWeightrule = null;
		TbrBusinessrule objTbrBusinessrule = null;
		String strBrid = m_objWeightruleColumns.getWrbrid();
		// 新增和修改
		if (StringUtility.isNull(strBrid)) {
			objTbrWeightrule = new TbrWeightrule();
			objTbrBusinessrule = new TbrBusinessrule();
			// 创建人创建时间
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
			objTbrWeightrule = (TbrWeightrule)objSession.load(TbrWeightrule.class, 
					Long.parseLong(strBrid));
			objTbrBusinessrule = (TbrBusinessrule)objSession.load(TbrBusinessrule.class, 
					Long.parseLong(strBrid));
		}
		// 设置规则
		WeightRuleDemand.setBusinessRuleByColumns(objTbrBusinessrule, 
				m_objWeightruleColumns,
				m_strOperId, 
				objSession);
		objSession.save(objTbrBusinessrule);
		m_lNewBrid = objTbrBusinessrule.getBrId();
		// 设置重量规则
		WeightRuleDemand.setWeightRuleByColumns(objTbrWeightrule, 
				m_objWeightruleColumns, 
				m_strOperId, 
				objSession);
		// 保存重量规则
		objTbrWeightrule.setBrId(m_lNewBrid);
		objTbrWeightrule.setTbrBusinessrule(objTbrBusinessrule);
		objSession.save(objTbrWeightrule);
		// 保存重量规则值
		SaveWeightRuleValueTrans objSWRVTrans = new SaveWeightRuleValueTrans();
		objSWRVTrans.setParam(objTbrWeightrule, 
				m_listWRVColumns, 
				m_listVWRVColumns, 
				m_listCWRVColumns);
		objSWRVTrans.transaction(objSession);
		// 修改日期冲突的规则表
		if (m_objMWRDateTrans != null)
			m_objMWRDateTrans.transaction(objSession);				
	}
}
