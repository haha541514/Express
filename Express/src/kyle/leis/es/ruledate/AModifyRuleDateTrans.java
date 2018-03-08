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
	 * ���µĹ�������ڸ��Ǿɹ����ʱ������ԭ���۸�
	 * @param objSession
	 * @throws Exception
	 */
	protected abstract void eliminate(RuleCheckReturn objRuleCheckReturn,
			Session objSession) 
	throws Exception;
	
	/**
	 * ���µĹ����������ɹ�������ڽ���ʱ�����޸ľ͹�������Ч��ʧЧ����
	 * @param objSession
	 * @throws Exception
	 */
	protected abstract void modifyDate(RuleCheckReturn objRuleCheckReturn, 
			Session objSession) 
	throws Exception;
	
	/**
	 * ���µĹ�������ڰ����ھɹ����������ʱ���򴴽��µĹ����
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
			// �½�����
			if (objRuleCheckReturn.getAtcode().equals(RuleCheckReturn.ACTIONTYPE_NEW))
				createNewRule(objRuleCheckReturn, objSession);
			// �޸�����
			if (objRuleCheckReturn.getAtcode().equals(RuleCheckReturn.ACTIONTYPE_MODIFYDATE))
				modifyDate(objRuleCheckReturn, objSession);
			// ����
			if (objRuleCheckReturn.getAtcode().equals(RuleCheckReturn.ACTIONTYPE_ELIMINATE))
				eliminate(objRuleCheckReturn, objSession);
		}
	}
}
