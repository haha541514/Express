package kyle.leis.eo.finance.cashrecord.tp;

import java.math.BigDecimal;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.cashrecord.da.CashrecordColumns;
import kyle.leis.eo.finance.cashrecord.dax.CashRecordDemand;
import kyle.leis.eo.finance.cashrecord.dax.ICashRecordBasicData;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TfiCashrecord;

public class SaveCashRecordTrans extends AbstractTransaction {
	private CashrecordColumns m_objCashrecordColumns;
	private TfiCashrecord m_objTfiCashrecord;
	private String m_strOriginCrtotal;
	private String m_strOperId;
	private Long m_lNewCrid;
	
	public void setParam(CashrecordColumns objCashrecordColumns, 
			String strOperId) {
		m_objCashrecordColumns = objCashrecordColumns;
		m_strOperId = strOperId;
	}
	
	public Long getNewCrid() {
		return m_lNewCrid;
	}
	
	public TfiCashrecord getSavedCashrecord() {
		return m_objTfiCashrecord;
	}
	
	public String getOriginCrtotal() {
		return m_strOriginCrtotal;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objCashrecordColumns == null) return;
		
		TfiCashrecord objTfiCashrecord = null;
		String strCrcrid = m_objCashrecordColumns.getCrcrid();
		boolean isInsert = false;
		// 新增
		if (StringUtility.isNull(strCrcrid)) {
			isInsert = true;
			objTfiCashrecord = new TfiCashrecord();
			// m_objCashrecordColumns.setCrscrscode("C");
			objTfiCashrecord.setCrCreatedate(DateFormatUtility.getSysdate());
			if (!StringUtility.isNull(m_strOperId)) {
				TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, 
						Long.parseLong(m_strOperId));
				objTfiCashrecord.setTdiOperatorByCrOpIdCreator(objTdiOperator);
			}
		} else {
			isInsert = false;
			// 修改
			objTfiCashrecord = (TfiCashrecord)objSession.load(TfiCashrecord.class, 
					Long.parseLong(strCrcrid));
			BigDecimal objOriginCrtotal = objTfiCashrecord.getCrTotal().multiply(objTfiCashrecord.getCrCurrencyrate());
			objOriginCrtotal = objOriginCrtotal.divide(new BigDecimal("1"), 2, 4);
			m_strOriginCrtotal = objOriginCrtotal.toString();
		}
		CashRecordDemand.setCashRecordByColumns(objTfiCashrecord,
				m_objCashrecordColumns,
				m_strOperId,
				objSession);
		objSession.save(objTfiCashrecord);
		// 收款、新增时修改收据编号
		boolean isUpdate = false;
		if (isInsert && 
				m_objCashrecordColumns.getCrkcrkcode().equals(ICashRecordBasicData.CRK_RECEIVABLE_ACCOUNT) &&
				StringUtility.isNull(m_objCashrecordColumns.getCrcrreceiptlabelcode())) {
			String strCrLabelcode = CashRecordDemand.buildLabelcode(objTfiCashrecord);
			objTfiCashrecord.setCrReceiptlabelcode(strCrLabelcode);
			isUpdate = true;
		}
		// 现金则将收据号做为水单号
		if (m_objCashrecordColumns.getPtptcode().equals(ICashRecordBasicData.PAYMENTTYPE_CASH)) {
			isUpdate = true;
			objTfiCashrecord.setCrLabelcode(objTfiCashrecord.getCrReceiptlabelcode());
		}
		if (isUpdate)
			objSession.update(objTfiCashrecord);
		
		m_lNewCrid = objTfiCashrecord.getCrId();
		m_objTfiCashrecord = objTfiCashrecord;
	}

}
