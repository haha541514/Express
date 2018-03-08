package kyle.leis.eo.finance.purchaseorder.tp;


import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;

public class DeleteWaybillFeeTrans extends AbstractTransaction{

	private String m_strPoId;
	
	public void setParam(String strPoId) throws Exception {
		m_strPoId = strPoId;
	}
	public void transaction(Session objSession) throws Exception {

		if(StringUtility.isNull(m_strPoId))
			return;
		String strDeleteWaybillSql = "delete from t_fi_purchaseorderwaybill powb where powb.po_id =  " + m_strPoId;
		execute(objSession, strDeleteWaybillSql);		
		
		String strDeleteFeeSql = "delete from t_fi_purchaseorderfee pof where pof.po_id = " + m_strPoId;
		execute(objSession, strDeleteFeeSql);		
	}

}
