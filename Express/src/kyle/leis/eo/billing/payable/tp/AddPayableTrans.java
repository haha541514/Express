package kyle.leis.eo.billing.payable.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.payable.dax.PayableDemand;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.hi.TblPayable;
import kyle.leis.hi.TdiOperator;
import net.sf.hibernate.Session;

public class AddPayableTrans extends AbstractTransaction {
	private PayableColumns m_objPayableColumns;
	private String m_strOperId;
	private Long m_lPyid;
	private boolean m_isUseSysdateForCreateDate;
	
	public void setParam(PayableColumns objPayableColumns, 
			String strOperId) {
		m_objPayableColumns = objPayableColumns;
		m_strOperId = strOperId;
	}
	
	public void setParam(PayableColumns objPayableColumns, 
			String strOperId,
			boolean isUseSysdateForCreateDate) {
		m_objPayableColumns = objPayableColumns;
		m_strOperId = strOperId;
		m_isUseSysdateForCreateDate = isUseSysdateForCreateDate;
	}	
	
	
	public Long getNewPyid() {
		return m_lPyid;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objPayableColumns == null) return;
		
		TblPayable objTblPayable = new TblPayable();
		PayableDemand.setPayableFromColumns(objTblPayable, 
				m_objPayableColumns, 
				m_strOperId, 
				objSession);
		String strSystemPE = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(m_strOperId)) {
			TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, Long.parseLong(m_strOperId));
			objTblPayable.setTdiOperatorByPyOpIdCreator(objTdiOperator);
			
			if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("WC"))
				objTblPayable.setPyCreatedate(objTblPayable.getPyOccurdate());
			else
				objTblPayable.setPyCreatedate(DateFormatUtility.getSysdate());
		}
		if (m_isUseSysdateForCreateDate && 
				!StringUtility.isNull(strSystemPE) && 
				strSystemPE.startsWith("WC"))
			objTblPayable.setPyCreatedate(DateFormatUtility.getSysdate());
		
		objSession.save(objTblPayable);
		m_lPyid = objTblPayable.getPyId();
	}

}
