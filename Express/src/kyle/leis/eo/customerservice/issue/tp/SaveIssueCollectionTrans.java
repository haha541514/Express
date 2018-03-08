package kyle.leis.eo.customerservice.issue.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.customerservice.issue.da.IssueColumns;
import kyle.leis.eo.customerservice.issue.dax.IIssueBasicData;

public class SaveIssueCollectionTrans extends AbstractTransaction {
	private List m_listIssueCollection;
	private String m_strOperId;
	
	public void setParam(List listIssueCollection, 
			String strOperId) {
		m_strOperId = strOperId;
		m_listIssueCollection = listIssueCollection;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listIssueCollection == null || m_listIssueCollection.size() < 1)
			return;
		for (int i = 0; i < m_listIssueCollection.size(); i++) {
			IssueColumns objIssueColumns = (IssueColumns)m_listIssueCollection.get(i);
			SaveIssueTrans objSaveIssueTrans = new SaveIssueTrans();
			// 扣件问题
			if (objIssueColumns.getIhsihscode().equals(IIssueBasicData.HOLD_STATUS_CONFIRM)) {
				objSaveIssueTrans.setHoldIssueParam(objIssueColumns.getCwcwcode(), 
						objIssueColumns.getIsutisutcode(), 
						objIssueColumns.getIsuiscontent(), 
						m_strOperId);
			}
			// 其他问题
			else {
				objSaveIssueTrans.setAddParam(objIssueColumns, m_strOperId);
			}
			objSaveIssueTrans.transaction(objSession);
		}
	}
}
