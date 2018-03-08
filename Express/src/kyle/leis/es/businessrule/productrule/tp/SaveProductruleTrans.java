package kyle.leis.es.businessrule.productrule.tp;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.productrule.da.ProductruleColumns;
import kyle.leis.es.businessrule.productrule.dax.LoadProductruleResult;
import kyle.leis.es.businessrule.productrule.dax.ProductruleDemand;
import kyle.leis.es.businessrule.productrule.dax.ProductruleDate;
import kyle.leis.es.ruledate.ARuleDate;
import kyle.leis.es.ruledate.RuleCheckReturn;
import kyle.leis.hi.TbrBusinessrule;
import kyle.leis.hi.TbrProductrule;
import kyle.leis.hi.TdiOperator;

public class SaveProductruleTrans extends AbstractTransaction {
	private ModifyProductruleDateTrans m_objMPRDTrans;
	private ProductruleColumns m_objProductruleColumns;
	private List m_listChnPRColumns;
	private List m_listDistrictPRColumns;
	private List m_listCorporationPRColumns;
	private String m_strOperId;
	
	private Long m_lNewBrid;
	
	public ArrayList<RuleCheckReturn> setConflictParam(LoadProductruleResult objLoadPResult,
			String strOperId,
			String strChangedStartDate,
			String strChangedEndDate,
			boolean isCheckDateConflic) throws Exception {
		ProductruleColumns objPRColumns = objLoadPResult.getProductruleColumns();
		objPRColumns.setPrbrid(null);
		objPRColumns.setBrbrstartdate(DateFormatUtility.getStandardDate(strChangedStartDate));
		objPRColumns.setBrbrenddate(DateFormatUtility.getStandardDate(strChangedEndDate));    	
    	
    	return setParam(objPRColumns,
    			objLoadPResult.getChnPRColumns(),
    			objLoadPResult.getDistrictPRColumns(),   
    			objLoadPResult.getCorporationPRColumns(),
    			strOperId,
    			isCheckDateConflic);
	}
	
	public ArrayList<RuleCheckReturn> setParam(ProductruleColumns objProductruleColumns,
			List listChnPRColumns,
			List listDistrictPRColumns,
			List listCorporationPRColumns,
			String strOperId, 
			boolean isCheckDateConflict) throws Exception {
		m_objProductruleColumns = objProductruleColumns;
		m_listChnPRColumns = listChnPRColumns;
		m_listDistrictPRColumns = listDistrictPRColumns;
		m_listCorporationPRColumns = listCorporationPRColumns;
		m_strOperId = strOperId;
		// 检查日期是否冲突
		if (isCheckDateConflict) {
			ARuleDate objRuleDate = new ProductruleDate();
			ArrayList<RuleCheckReturn> alRuleCheckReturn = objRuleDate.checkRuleDate(objProductruleColumns);
			if (alRuleCheckReturn != null && alRuleCheckReturn.size() > 0) {
				m_objMPRDTrans = new ModifyProductruleDateTrans();
				m_objMPRDTrans.setParam(alRuleCheckReturn);
			}
			return alRuleCheckReturn;
		}
		return null;
	}
	
	public Long getNewBrid() {
		return m_lNewBrid;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objProductruleColumns == null) return;
		
		TbrBusinessrule objTbrBusinessrule;
		TbrProductrule objTbrProductrule;
		// 新增或修改
		if (StringUtility.isNull(m_objProductruleColumns.getPrbrid())) {
			objTbrBusinessrule = new TbrBusinessrule();
			objTbrProductrule = new TbrProductrule();
			
			if (!StringUtility.isNull(m_strOperId)) {
				TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, 
						Long.parseLong(m_strOperId));
				objTbrBusinessrule.setTdiOperatorByBrOpIdCreate(objTdiOperator);
				objTbrBusinessrule.setBrCreatedate(DateFormatUtility.getSysdate());
			}
		} else {
			objTbrBusinessrule = (TbrBusinessrule)objSession.load(TbrBusinessrule.class, 
					Long.parseLong(m_objProductruleColumns.getPrbrid()));
			objTbrProductrule = (TbrProductrule)objSession.load(TbrProductrule.class, 
					Long.parseLong(m_objProductruleColumns.getPrbrid()));
		}
		ProductruleDemand.setBusinessruleByColumns(objTbrBusinessrule, 
				m_objProductruleColumns, 
				m_strOperId, 
				objSession);
		ProductruleDemand.setProductruleByColumns(objTbrProductrule, 
				m_objProductruleColumns,
				objSession);
		// 保存规则基表
		objSession.save(objTbrBusinessrule);
		m_lNewBrid = objTbrBusinessrule.getBrId();
		// 保存产品规则
		objTbrProductrule.setBrId(objTbrBusinessrule.getBrId());
		objTbrProductrule.setTbrBusinessrule(objTbrBusinessrule);
		objSession.save(objTbrProductrule);
		// 保存值
		SaveProductrulevalueTrans objSavePRVTrans = new SaveProductrulevalueTrans();
		objSavePRVTrans.setParam(m_listChnPRColumns, 
				m_listDistrictPRColumns, 
				m_listCorporationPRColumns,
				objTbrProductrule);
		objSavePRVTrans.transaction(objSession);
		// 修改日期冲突的规则表
		if (m_objMPRDTrans != null)
			m_objMPRDTrans.transaction(objSession);		
	}

}
