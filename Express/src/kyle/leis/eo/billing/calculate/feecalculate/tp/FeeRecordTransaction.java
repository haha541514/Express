package kyle.leis.eo.billing.calculate.feecalculate.tp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateDemand;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateResult;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.payable.dax.PayableDemand;
import kyle.leis.eo.billing.payable.tp.SavePayableTrans;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.eo.billing.receivable.tp.SaveReceivableTrans;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;

public class FeeRecordTransaction extends AbstractTransaction {
	private HashMap<String, FeeCalculateResult> m_hmFeeResult;
	private HousewaybillColumns m_objHwbColumns;
	private List m_listOriginRvColumns;
	private List<ReceivableColumns> m_listOriginBalanceRvColumns;
	private List m_listOriginPayColumns;
	private String m_strBrkcode;
	private String m_strRemark;
	
	public void setParam(HashMap<String, FeeCalculateResult> hmFeeResult,
			HousewaybillColumns objHwbColumns,
			String strBrkcode,
			String strRemark) throws Exception {
		m_hmFeeResult = hmFeeResult;
		m_strBrkcode = strBrkcode;
		m_objHwbColumns = objHwbColumns;
		m_strRemark = strRemark;
		// 查询出原始费用
		String strCwcode = m_objHwbColumns.getHwcwcode();
		if (!StringUtility.isNull(strCwcode)) {
			m_listOriginRvColumns = ReceivableDemand.loadAll(strCwcode);
			m_listOriginBalanceRvColumns = new ArrayList<ReceivableColumns>();
			if (m_listOriginRvColumns != null && m_listOriginRvColumns.size() > 0) {
				for (int i = 0; i < m_listOriginRvColumns.size(); i++) {
					ReceivableColumns objReceivableColumns = (ReceivableColumns)m_listOriginRvColumns.get(i);
					if (objReceivableColumns != null && objReceivableColumns.getBkbkcode().equals("A0101"))
						m_listOriginBalanceRvColumns.add(objReceivableColumns);
				}
			}
			m_listOriginPayColumns = PayableDemand.load(strCwcode, strBrkcode);
		}
	}
	
	public List getOriginBalanceRvColumns() {
		return m_listOriginBalanceRvColumns;
	}
	
	public List getOriginPayColumns() {
		return m_listOriginPayColumns;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_hmFeeResult == null || m_hmFeeResult.size() < 1)
			return;
		List<ReceivableColumns> listReceivable = new ArrayList<ReceivableColumns>();
		List<PayableColumns> listPayable = new ArrayList<PayableColumns>();
		Iterator<String> objFeekind = m_hmFeeResult.keySet().iterator();
		while(objFeekind.hasNext()) {
			String strFkcode = objFeekind.next();
			FeeCalculateResult objClacFeeResult = m_hmFeeResult.get(strFkcode);
			// 费用为0则不计入
			if (StringUtility.isNull(objClacFeeResult.getPricevalue()) ||
					new BigDecimal(objClacFeeResult.getPricevalue()).compareTo(new BigDecimal("0")) == 0)
				continue;
			if (objClacFeeResult.getPdcode().equals(IExpressPriceBasicData.PRICEDOMAIN_SALES)) {
				// 销售则记录至应收
				ReceivableColumns objReceivableColumns = FeeCalculateDemand.transferToRecColumns(objClacFeeResult, 
						m_objHwbColumns, 
						m_strBrkcode);
				if (!StringUtility.isNull(m_strRemark))
					objReceivableColumns.setRvrvremark(m_strRemark);
				listReceivable.add(objReceivableColumns);
			} else if (objClacFeeResult.getPdcode().equals(IExpressPriceBasicData.PRICEDOMAIN_COSTS) ||
					objClacFeeResult.getPdcode().equals(IExpressPriceBasicData.PRICEDOMAIN_MIDDLE)) {
				// 采购记录至应付
				PayableColumns objPayableColumns = FeeCalculateDemand.transferToPayableColumns(objClacFeeResult,
						m_objHwbColumns,
						m_strBrkcode);
				if (!StringUtility.isNull(m_strRemark))
					objPayableColumns.setPypyremark(m_strRemark);				
				listPayable.add(objPayableColumns);
			}
		}
		// 记录应收费用
		if (listReceivable != null && listReceivable.size() > 0) {
			SaveReceivableTrans objSaveReceivableTrans = new SaveReceivableTrans();
			objSaveReceivableTrans.setParam(listReceivable, 
					m_objHwbColumns.getHwcwcode(), 
					"0",
					true,
					m_listOriginRvColumns);
			objSaveReceivableTrans.transaction(objSession);
		}
		// 记录应付费用
		if (listPayable != null && listPayable.size() > 0) {
			SavePayableTrans objSavePayableTrans = new SavePayableTrans();
			objSavePayableTrans.setParam(listPayable, 
					m_objHwbColumns.getHwcwcode(), 
					"0",
					true,
					m_listOriginPayColumns);
			objSavePayableTrans.transaction(objSession);
		}	
	}
}
