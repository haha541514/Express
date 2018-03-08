package kyle.leis.eo.finance.purchaseorder.tp;

import java.math.BigDecimal;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.purchaseorder.da.PurchasefeeColumns;
import kyle.leis.eo.finance.purchaseorder.da.PurchaseorderColumns;
import kyle.leis.eo.finance.purchaseorder.da.PurchasewaybillColumns;
import kyle.leis.eo.finance.purchaseorder.dax.PurchaseOrderDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TfiPurchaseorder;
import kyle.leis.hi.TfiPurchaseorderfee;
import kyle.leis.hi.TfiPurchaseorderfeePK;
import kyle.leis.hi.TfiPurchaseorderwaybill;
import kyle.leis.hi.TfiPurchaseorderwaybillPK;
@SuppressWarnings("unchecked")	
public class SavePurchaseOrderTrans extends AbstractTransaction{

	private PurchaseorderColumns m_objPurchaseorderColumns;
	private List m_listPurchaseOrderWaybill;
	private List m_listPurchaseOrderFee;
	private String m_strOperId;
	private Long m_lPoId;

	public void setParam(PurchaseorderColumns objPurchaseorderColumns,
			List listPurchaseOrderWaybill,
			List listPurchaseOrderFee,
			String strOperId){
		m_objPurchaseorderColumns = objPurchaseorderColumns;
		m_listPurchaseOrderWaybill = listPurchaseOrderWaybill;
		m_listPurchaseOrderFee = listPurchaseOrderFee;
		m_strOperId = strOperId;
	}

	public Long getPoId() {
		return m_lPoId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if(m_objPurchaseorderColumns == null)
			return;
		//保存PurchaseOrder
		TfiPurchaseorder objTfiPurchaseorder = null;
		TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
		if(StringUtility.isNull(m_objPurchaseorderColumns.getPopo_id())){
			objTfiPurchaseorder = new TfiPurchaseorder();
			objTfiPurchaseorder.setTdiOperatorByPoOpIdCreator(objTdiOperator);
			objTfiPurchaseorder.setPoCreatedate(DateFormatUtility.getSysdate());
		}else{
			objTfiPurchaseorder = (TfiPurchaseorder) objSession.load(TfiPurchaseorder.class,
					Long.parseLong(m_objPurchaseorderColumns.getPopo_id()));			
		}
		objTfiPurchaseorder.setTdiOperatorByPoOpIdModifier(objTdiOperator);
		objTfiPurchaseorder.setPoModifydate(DateFormatUtility.getSysdate());		
		PurchaseOrderDemand.setPurchaseOrderByColumns(objTfiPurchaseorder, m_objPurchaseorderColumns);	
		objSession.save(objTfiPurchaseorder);
		m_lPoId = objTfiPurchaseorder.getPoId();
		//保存PurchaseOrderWaybill
		if(m_listPurchaseOrderWaybill == null || m_listPurchaseOrderWaybill.size() == 0)
			return;
		for(int i = 0;i<m_listPurchaseOrderWaybill.size();i++){
			PurchasewaybillColumns objTPOWBColumns = (PurchasewaybillColumns) m_listPurchaseOrderWaybill.get(i);
			TfiPurchaseorderwaybill objTPOWB = new  TfiPurchaseorderwaybill();
			TfiPurchaseorderwaybillPK objTPOWBPK = new TfiPurchaseorderwaybillPK();
			objTPOWBPK.setPoId(m_lPoId);
			objTPOWBPK.setCwCode(Long.parseLong(objTPOWBColumns.getPowbcw_code()));
			System.out.println(objTPOWBColumns.getPowbcw_code());
			objTPOWB.setComp_id(objTPOWBPK);
			objTPOWB.setTfiPurchaseorder(objTfiPurchaseorder);
			objSession.save(objTPOWB);						
		}
		//保存PurchaseOrderFee
		if(m_listPurchaseOrderFee == null || m_listPurchaseOrderFee.size() == 0)
			return;
		for(int j = 0;j<m_listPurchaseOrderFee.size();j++){
			PurchasefeeColumns objTPOWBColumns = (PurchasefeeColumns) m_listPurchaseOrderFee.get(j);
			TfiPurchaseorderfee objTPOF = new  TfiPurchaseorderfee();
			TfiPurchaseorderfeePK objTPOFPK = new TfiPurchaseorderfeePK();
			objTPOFPK.setPoId(m_lPoId);
			objTPOFPK.setFkCode(objTPOWBColumns.getPoffk_code());
			objTPOF.setComp_id(objTPOFPK);
			System.out.println(objTPOWBColumns.getPofpof_commissionrate());
			System.out.println(objTPOWBColumns.getPoffk_code());
			if(StringUtility.isNull(objTPOWBColumns.getPofpof_commissionrate())){
				objTPOF.setPofCommissionrate(new BigDecimal(0));
			}else{
				objTPOF.setPofCommissionrate(new BigDecimal(objTPOWBColumns.getPofpof_commissionrate()));
			}			
			objTPOF.setTfiPurchaseorder(objTfiPurchaseorder);
			objSession.save(objTPOF);
		}	
		//保存Payable中的commissionrate
		CalculatePurchaseOrderTrans objCPOTrans = new CalculatePurchaseOrderTrans();
		objCPOTrans.setParam(m_listPurchaseOrderWaybill,
				m_listPurchaseOrderFee);
		objCPOTrans.transaction(objSession);
	}
}
