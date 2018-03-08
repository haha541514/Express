package kyle.leis.es.ruledate;

import java.util.ArrayList;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;

public abstract class AModifyRuleDateTrans extends AbstractTransaction {
	private ArrayList<RuleCheckReturn> m_listRuleCheckReturn;
	
	public void setParam(ArrayList<RuleCheckReturn> listRuleCheckReturn) {
		m_listRuleCheckReturn = listRuleCheckReturn;
	}
	
	/**
	 * 当新的规则表日期覆盖旧规则表时则作废原来价格
	 * @param objSession
	 * @throws Exception
	 */
	protected abstract void eliminate(RuleCheckReturn objRuleCheckReturn,
			Session objSession) 
	throws Exception;
	
	/**
	 * 当新的规则表日期与旧规则表日期交叉时，则修改就规则表的生效和失效日期
	 * @param objSession
	 * @throws Exception
	 */
	protected abstract void modifyDate(RuleCheckReturn objRuleCheckReturn, 
			Session objSession) 
	throws Exception;
	
	/**
	 * 当新的规则表日期包含在旧规则表日期中时，则创建新的规则表
	 * @param objSession
	 * @throws Exception
	 */
	protected abstract void createNewRule(RuleCheckReturn objRuleCheckReturn, 
			Session objSession) 
	throws Exception;
	
	public void transaction(Session objSession) throws Exception {
		if (m_listRuleCheckReturn == null || m_listRuleCheckReturn.size() < 1) 
			return;
		for (int i = 0; i < m_listRuleCheckReturn.size(); i++) {
			RuleCheckReturn objRuleCheckReturn = m_listRuleCheckReturn.get(i);
			// 新建规则
			if (objRuleCheckReturn.getAtcode().equals(RuleCheckReturn.ACTIONTYPE_NEW))
				createNewRule(objRuleCheckReturn, objSession);
			// 修改日期
			if (objRuleCheckReturn.getAtcode().equals(RuleCheckReturn.ACTIONTYPE_MODIFYDATE))
				modifyDate(objRuleCheckReturn, objSession);
			// 作废
			if (objRuleCheckReturn.getAtcode().equals(RuleCheckReturn.ACTIONTYPE_ELIMINATE))
				eliminate(objRuleCheckReturn, objSession);
		}
	}
}
