package kyle.leis.es.company.customer.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.es.company.customer.da.CusoperationColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperationtacheDC;
import kyle.leis.hi.TcoCustomer;
import kyle.leis.hi.TcoCustomersorestrict;
import kyle.leis.hi.TcoCustomersorestrictPK;
import kyle.leis.hi.TdiOperationtache;

public class SaveCusSORestrictTran  extends AbstractTransaction{
	private List m_operationList;
	private TcoCustomer m_TcoCustomer;
	
	public void setParam(List operationList,
			TcoCustomer objTcoCustomer){
		this.m_operationList = operationList;
		m_TcoCustomer = objTcoCustomer;
	}
	
	public void transaction(Session objSession) throws Exception {
		// ÏÈÉ¾³ý
		objSession.delete(" from TcoCustomersorestrict as csor where csor.comp_id.coCode = '" + 
				m_TcoCustomer.getCoCode() + "'");	
		if (m_operationList == null) 
			return;
		// ÐÂÔö
		for (int i = 0; i < m_operationList.size(); i++) {
			CusoperationColumns objCusoperationColumns  = (CusoperationColumns)m_operationList.get(i);            
			
			TcoCustomersorestrict objTcoCustomersorestrict = new TcoCustomersorestrict();
			
			TdiOperationtache objTdiOperationtache = TdiOperationtacheDC.loadByKey(objCusoperationColumns.getOtotcode());
			objTcoCustomersorestrict.setTdiOperationtache(objTdiOperationtache);			
			objTcoCustomersorestrict.setTcoCustomer(m_TcoCustomer);
			
			TcoCustomersorestrictPK objTCRPK = new TcoCustomersorestrictPK();
			objTCRPK.setCoCode(m_TcoCustomer.getCoCode());
			objTCRPK.setOtCode(objCusoperationColumns.getOtotcode());
			objTcoCustomersorestrict.setComp_id(objTCRPK);
			
			objSession.save(objTcoCustomersorestrict);
		}
	}
}
