package kyle.leis.es.company.customer.tp;

import java.math.BigDecimal;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.companys.dax.CompanysDemand;
import kyle.leis.es.company.customer.da.CustomerColumns;
import kyle.leis.es.company.customer.dax.CustomerDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TcoCustomer;
import kyle.leis.hi.TdiOperator;

public class SaveCustomerTransaction extends AbstractTransaction {
	private CustomerColumns m_objCustomerColumns;
	private List m_listCustomerSORestrict;
	private String m_strOperId;
	private String m_strSavedCocode;
	
	public void setCustomerParam(CustomerColumns objCustomerColumns,
			List listCustomerSORestrict,
			String strOperId) {
		m_objCustomerColumns = objCustomerColumns;
		m_listCustomerSORestrict = listCustomerSORestrict;
		m_strOperId = strOperId;
	}
	
	public String getSavedCocode() {
		return m_strSavedCocode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objCustomerColumns == null) return;
		// 保存
		TcoCorporation objTcoCorporation;
		TcoCustomer objTcoCustomer;
		String strCocode = m_objCustomerColumns.getCmcocode();
		// 新建
		if (StringUtility.isNull(strCocode)) {
			objTcoCorporation = new TcoCorporation();
			objTcoCustomer = new TcoCustomer();
			TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
			objTcoCorporation.setTdiOperatorByCoOpIdCreate(objTdiOperator);
			objTcoCorporation.setCoCreatedate(DateFormatUtility.getSysdate());
			objTcoCorporation.setCoCode(CompanysDemand.getNewCorporationcode());
			objTcoCustomer.setCmCreditlimit(new BigDecimal("0"));
		} else {
			objTcoCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					strCocode);
			objTcoCustomer = (TcoCustomer)objSession.load(TcoCustomer.class, 
					strCocode);
		}
		// 设置公司表属性
		CompanysDemand.setCompanyByCustomer(objTcoCorporation, 
				m_objCustomerColumns, 
				m_strOperId);
		// 设置客户表属性
		CustomerDemand.setCustomerByColumns(objTcoCustomer, m_objCustomerColumns);
		// 保存
		objSession.save(objTcoCorporation);
		objTcoCustomer.setCoCode(objTcoCorporation.getCoCode());
		objTcoCustomer.setTcoCorporation(objTcoCorporation);
		objSession.save(objTcoCustomer);
		// 保存出货限制
		SaveCusSORestrictTran objSCSORT = new SaveCusSORestrictTran();
		objSCSORT.setParam(m_listCustomerSORestrict, objTcoCustomer);
		objSCSORT.transaction(objSession);
		
		m_strSavedCocode = objTcoCorporation.getCoCode();
	}
}
