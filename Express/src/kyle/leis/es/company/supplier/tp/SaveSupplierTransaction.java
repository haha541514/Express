package kyle.leis.es.company.supplier.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.companys.dax.CompanysDemand;
import kyle.leis.es.company.customer.dax.CustomerDemand;
import kyle.leis.es.company.supplier.da.SupplierColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TcoSupplier;
import kyle.leis.hi.TdiOperator;
import net.sf.hibernate.Session;

public class SaveSupplierTransaction extends AbstractTransaction {
	private SupplierColumns m_objSupplierColumns;
	private String m_strOperId;
	private String m_strSavedCocode;
	
	public void setParam(SupplierColumns objSupplierColumns,
			String strOperId) {
		m_objSupplierColumns = objSupplierColumns;
		m_strOperId = strOperId;
	}
	
	public String getSavedCocode() {
		return m_strSavedCocode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objSupplierColumns == null) return;
		// 保存
		TcoCorporation objTcoCorporation;
		TcoSupplier objTcoSupplier;
		String strCocode = m_objSupplierColumns.getSpcocode();
		// 新建
		if (StringUtility.isNull(strCocode)) {
			objTcoCorporation = new TcoCorporation();
			objTcoSupplier = new TcoSupplier();
			TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
			objTcoCorporation.setTdiOperatorByCoOpIdCreate(objTdiOperator);
			objTcoCorporation.setCoCreatedate(DateFormatUtility.getSysdate());
			objTcoCorporation.setCoCode(CompanysDemand.getNewCorporationcode());
		} else {
			objTcoCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					strCocode);
			objTcoSupplier = (TcoSupplier)objSession.load(TcoSupplier.class, 
					strCocode);
		}
		// 设置公司表属性
		CompanysDemand.setCompanyBySuppier(objTcoCorporation, 
				m_objSupplierColumns, 
				m_strOperId);
		// 设置客户表属性
		CustomerDemand.setSupplierByColumns(objTcoSupplier, m_objSupplierColumns);
		// 保存
		objSession.save(objTcoCorporation);
		objTcoSupplier.setCoCode(objTcoCorporation.getCoCode());
		objTcoSupplier.setTcoCorporation(objTcoCorporation);
		objSession.save(objTcoSupplier);
		
		m_strSavedCocode = objTcoCorporation.getCoCode();
	}
}
