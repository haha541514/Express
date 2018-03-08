package kyle.leis.eo.customerservice.issue.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.customerservice.issue.da.IssueactionColumns;
import kyle.leis.eo.customerservice.issue.dax.IssueDemand;
import kyle.leis.hi.TcsIssue;
import kyle.leis.hi.TcsIssueaction;
import kyle.leis.hi.TcsIssueactionPK;
import kyle.leis.hi.TdiActionkind;
import kyle.leis.hi.TdiOperator;

public class SaveIssueactionTrans extends AbstractTransaction {
	private List m_listIssueaction;
	private TcsIssue m_objTcsIssue;
	private String m_strOperId;
	
	public void setParam(List listIssueaction,
			TcsIssue objTcsIssue,
			String strOperId) {
		m_objTcsIssue = objTcsIssue;
		m_listIssueaction = listIssueaction;
		m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listIssueaction == null || m_listIssueaction.size() < 1)
			return;
		
		TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, 
				Long.parseLong(m_strOperId));
		int iMaxIsaid = Integer.parseInt(IssueDemand.getMaxIsuactionId(String.valueOf(m_objTcsIssue.getIsuId())));
		for (int i = 0; i < m_listIssueaction.size(); i++) {
			IssueactionColumns objIssueactionColumns = (IssueactionColumns)m_listIssueaction.get(i);
			TcsIssueaction objTcsIssueaction = new TcsIssueaction();
			
			TcsIssueactionPK objTIAPK = new TcsIssueactionPK();
			objTIAPK.setIsaId(++iMaxIsaid);
			objTIAPK.setIsuId(m_objTcsIssue.getIsuId());
			
			objTcsIssueaction.setComp_id(objTIAPK);
			objTcsIssueaction.setIsaContent(objIssueactionColumns.getIsaisacontent());
			objTcsIssueaction.setIsaCreatedate(DateFormatUtility.getSysdate());
			objTcsIssueaction.setTcsIssue(m_objTcsIssue);
			
			if (!StringUtility.isNull(objIssueactionColumns.getAkakcode())) {
				TdiActionkind objTdiActionkind = (TdiActionkind)objSession.load(TdiActionkind.class, 
						objIssueactionColumns.getAkakcode());
				objTcsIssueaction.setTdiActionkind(objTdiActionkind);
			}
			objTcsIssueaction.setTdiOperator(objTdiOperator);
			objSession.save(objTcsIssueaction);
		}
	}
}
