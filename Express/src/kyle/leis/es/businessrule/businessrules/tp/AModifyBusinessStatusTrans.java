package kyle.leis.es.businessrule.businessrules.tp;

import java.util.ArrayList;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.ruledate.RuleCheckReturn;
import kyle.leis.hi.TbrBusinessrule;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;

public abstract class AModifyBusinessStatusTrans extends AbstractTransaction {
	private String m_strBrid;
	private String m_strSscode;
	private String m_strOperId;
	protected AModifyBRDateTrans m_objMPDateTrans;
	
	public ArrayList<RuleCheckReturn> setParam(String strBrid, 
			String strSscode, 
			String strOperId, 
			boolean isCheckDateConflic) throws Exception {
		m_strBrid = strBrid;
		m_strSscode = strSscode;
		m_strOperId = strOperId;
		if (isCheckDateConflic)
			return checkBRDateConflict(m_strBrid, m_strSscode);
		return null;
	}
	
	public abstract ArrayList<RuleCheckReturn> checkBRDateConflict(String strBrid, 
			String strSscode) throws Exception;
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strBrid)) return;
		// 查询规则表
		TbrBusinessrule objTbrBusinessrule = (TbrBusinessrule)objSession.load(TbrBusinessrule.class, 
				Long.parseLong(m_strBrid));
		// 修改规则表状态
		if (!StringUtility.isNull(m_strSscode)) {
			TdiSimplestatus objTdiSimplestatus = (TdiSimplestatus)objSession.load(TdiSimplestatus.class, 
					m_strSscode);
			objTbrBusinessrule.setTdiSimplestatus(objTdiSimplestatus);
		}
		// 修改人和修改时间
		if (!StringUtility.isNull(m_strOperId)) {
			TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, 
					Long.parseLong(m_strOperId));
			objTbrBusinessrule.setTdiOperatorByBrOpIdModifier(objTdiOperator);
			objTbrBusinessrule.setBrModifydate(DateFormatUtility.getSysdate());
		}
		objSession.save(objTbrBusinessrule);
		// 修改日期冲突的规则表
		if (m_objMPDateTrans != null)
			m_objMPDateTrans.transaction(objSession);
	}
}
