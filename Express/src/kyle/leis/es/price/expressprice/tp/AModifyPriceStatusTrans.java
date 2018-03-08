package kyle.leis.es.price.expressprice.tp;

import java.util.ArrayList;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.ruledate.RuleCheckReturn;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiPricestatus;
import kyle.leis.hi.TepExpressprice;

public abstract class AModifyPriceStatusTrans extends AbstractTransaction {
	private String m_strEpcode;
	private String m_strPscode;
	private String m_strOperId;
	protected AModifyPriceDateTrans m_objMPDateTrans;
	
	public ArrayList<RuleCheckReturn> setParam(String strEpcode, 
			String strPscode, 
			String strOperId, 
			boolean isCheckDateConflic) throws Exception {
		m_strEpcode = strEpcode;
		m_strPscode = strPscode;
		m_strOperId = strOperId;
		if (isCheckDateConflic)
			return checkPriceDateConflict(m_strEpcode, m_strPscode);
		return null;
	}
	
	public abstract ArrayList<RuleCheckReturn> checkPriceDateConflict(String strEpcode, String strPscode)
	throws Exception;
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strEpcode)) return;
		// 查询价格表
		TepExpressprice objTepExpressprice = (TepExpressprice)objSession.load(TepExpressprice.class, 
				Long.parseLong(m_strEpcode));
		// 修改价格表状态
		if (!StringUtility.isNull(m_strPscode)) {
			TdiPricestatus objTdiPricestatus = (TdiPricestatus)objSession.load(TdiPricestatus.class, 
					m_strPscode);
			objTepExpressprice.setTdiPricestatus(objTdiPricestatus);
			if (m_strPscode.equals("N"))
				objTepExpressprice.setEpWithdrawsign("Y");
		}
		// 
		if (!StringUtility.isNull(m_strOperId)) {
			TdiOperator objTOP = (TdiOperator)objSession.load(TdiOperator.class, 
					Long.parseLong(m_strOperId));
			objTepExpressprice.setTdiOperatorByEpOpIdModify(objTOP);
			objTepExpressprice.setEpModifydate(DateFormatUtility.getSysdate());
		}
		objSession.save(objTepExpressprice);
		// 修改日期冲突的价格表
		if (m_objMPDateTrans != null)
			m_objMPDateTrans.transaction(objSession);
	}

}
