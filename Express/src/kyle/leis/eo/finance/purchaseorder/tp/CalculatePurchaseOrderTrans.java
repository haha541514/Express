package kyle.leis.eo.finance.purchaseorder.tp;

import java.math.BigDecimal;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.purchaseorder.da.PurchasefeeColumns;
import kyle.leis.eo.finance.purchaseorder.da.PurchasepayableColumns;
import kyle.leis.eo.finance.purchaseorder.da.PurchasewaybillColumns;
import kyle.leis.eo.finance.purchaseorder.dax.PurchaseOrderDemand;
import kyle.leis.hi.TblPayable;

@SuppressWarnings("unchecked")
public class CalculatePurchaseOrderTrans extends AbstractTransaction {

	private List m_listPurchaseOrderWaybill;
	private List m_listPurchaseOrderFee;

	public void setParam(List listPurchaseOrderWaybill,
			List listPurchaseOrderFee) throws Exception {
		m_listPurchaseOrderWaybill = listPurchaseOrderWaybill;
		m_listPurchaseOrderFee = listPurchaseOrderFee;
	}

	public void transaction(Session objSession) throws Exception {

		if (m_listPurchaseOrderWaybill == null|| m_listPurchaseOrderWaybill.size() == 0)
			return;
		if (m_listPurchaseOrderFee == null|| m_listPurchaseOrderFee.size() == 0)
			return;
		for (int i = 0; i < m_listPurchaseOrderWaybill.size(); i++) {
			PurchasewaybillColumns objWaybliiColumns = (PurchasewaybillColumns) m_listPurchaseOrderWaybill.get(i);
			String strCwcode = objWaybliiColumns.getPowbcw_code();
			for (int j = 0; j < m_listPurchaseOrderFee.size(); j++) {
				PurchasefeeColumns objFeeColumns = (PurchasefeeColumns) m_listPurchaseOrderFee.get(j);
				String strFkcode = objFeeColumns.getPoffk_code();
				PurchasepayableColumns objColumns = PurchaseOrderDemand.queryPurchasePayable(strCwcode, strFkcode);
				if (objColumns == null)
					continue;
				TblPayable objTblPayable = (TblPayable) objSession.load(TblPayable.class,Long.parseLong(objColumns.getPypy_id()));				
				if(StringUtility.isNull(objFeeColumns.getPofpof_commissionrate())){
					objTblPayable.setPyPurchasecommissionrate(new BigDecimal(0));
				}
				else{
					objTblPayable.setPyPurchasecommissionrate(new BigDecimal(objFeeColumns.getPofpof_commissionrate()));				
				}				
				objSession.save(objTblPayable);

			}
		}
	}
}
