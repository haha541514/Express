package kyle.leis.eo.finance.purchaseorder.tp;

import java.math.BigDecimal;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.purchaseorder.da.WaybillpayableColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.hi.TblPayable;
import kyle.leis.hi.TfiPurchaseorder;
@SuppressWarnings("unchecked")
public class DeletePurchaseOrderTrans extends AbstractTransaction{
	private String m_strPoId;
	private String m_strSscode;
	private List m_listPurchaseWaybill;

	public void setParam(String strPoId, 
			String strSscode,
			List listPurchaseWaybill) throws Exception {
		m_strPoId = strPoId;
		m_strSscode = strSscode;
		m_listPurchaseWaybill = listPurchaseWaybill;
	}

	public void transaction(Session objSession) throws Exception {
		if(StringUtility.isNull(m_strPoId))
			return;
		TfiPurchaseorder objTfiPurchaseorder = (TfiPurchaseorder) objSession.load(TfiPurchaseorder.class,
				Long.parseLong(m_strPoId));
		objTfiPurchaseorder.setTdiSimplestatus(TdiSimplestatusDC.loadByKey(m_strSscode));
		objSession.update(objTfiPurchaseorder);
		
		if(m_listPurchaseWaybill == null) return;
		for(int i = 0;i<m_listPurchaseWaybill.size();i++){
			WaybillpayableColumns objPurchasewaybillColumns = (WaybillpayableColumns) m_listPurchaseWaybill.get(i);
			TblPayable objTblPayable = (TblPayable) objSession.load(TblPayable.class,
					Long.parseLong(objPurchasewaybillColumns.getPypy_id()));                     
			String strCommissionrate = String.valueOf(objTblPayable.getPyPurchasecommissionrate());
			if(!StringUtility.isNull(strCommissionrate)){
				objTblPayable.setPyPurchasecommissionrate(new BigDecimal(0));
			}
			objSession.update(objTblPayable);
		}

	}
}
