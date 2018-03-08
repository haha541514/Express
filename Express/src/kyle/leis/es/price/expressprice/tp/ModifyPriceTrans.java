package kyle.leis.es.price.expressprice.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiPricestatus;
import kyle.leis.hi.TepExpressprice;

public class ModifyPriceTrans extends AbstractTransaction {
	private String m_strEpcode;
	private String m_strPscode;
	private String m_strStartDate;
	private String m_strEndDate;
	private String m_strOperId;
	/**
	 * 修改价格表状态
	 * @param strEpcode
	 * @param strPscode
	 */
	public void setModifyStatusParam(String strEpcode, 
			String strPscode, 
			String strOperId) {
		m_strEpcode = strEpcode;
		m_strPscode = strPscode;
		m_strOperId = strOperId;
	}
	
	/**
	 * 修改价格表日期
	 * @param strEpcode
	 * @param strSavingStartDate
	 * @param strSavingEndDate
	 */
	public void setModifyDateParam(String strEpcode, 
			String strStartDate, 
			String strEndDate, 
			String strOperId) {
		m_strEpcode = strEpcode;
		m_strStartDate = strStartDate;
		m_strEndDate = strEndDate;
		m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) 
	throws Exception {
		if (StringUtility.isNull(m_strEpcode)) return;
		// 查询价格表
		TepExpressprice objTepExpressprice = (TepExpressprice)objSession.load(TepExpressprice.class, 
				Long.parseLong(m_strEpcode));
		// 修改价格表状态
		if (!StringUtility.isNull(m_strPscode)) {
			TdiPricestatus objTdiPricestatus = (TdiPricestatus)objSession.load(TdiPricestatus.class, 
					m_strPscode);
			objTepExpressprice.setTdiPricestatus(objTdiPricestatus);
		}
		// 修改日期
		if (!StringUtility.isNull(m_strStartDate))
			objTepExpressprice.setEpStartdate(DateFormatUtility.getStandardDate(m_strStartDate));
		if (!StringUtility.isNull(m_strEndDate))
			objTepExpressprice.setEpEnddate(DateFormatUtility.getStandardDate(m_strEndDate));
		// 操作员
		if (!StringUtility.isNull(m_strOperId)) {
			TdiOperator objTOP = (TdiOperator)objSession.load(TdiOperator.class, 
					Long.parseLong(m_strOperId));
			objTepExpressprice.setTdiOperatorByEpOpIdModify(objTOP);
			objTepExpressprice.setEpModifydate(DateFormatUtility.getSysdate());
		}
		objSession.save(objTepExpressprice);
	}
}
