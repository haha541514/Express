package kyle.leis.eo.finance.writeoff.tp;

import java.math.BigDecimal;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.billrecord.dax.BillRecordDemand;
import kyle.leis.eo.finance.billrecord.dax.SumBillTotalResult;
import kyle.leis.eo.finance.cashrecord.da.CashrecordColumns;
import kyle.leis.eo.finance.cashrecord.dax.CashRecordDemand;
import kyle.leis.eo.finance.cashrecord.dax.ICashRecordBasicData;
import kyle.leis.eo.finance.cashrecord.dax.SumCashTotalResult;
import kyle.leis.eo.finance.cashrecord.tp.SaveCashRecordTrans;
import kyle.leis.eo.finance.writeoff.da.WriteoffColumns;
import kyle.leis.eo.finance.writeoff.dax.WriteoffDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiBillrecordstatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCashrecordstatusDC;
import kyle.leis.hi.TdiBillrecordstatus;
import kyle.leis.hi.TdiCashrecordstatus;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TfiBillrecord;
import kyle.leis.hi.TfiCashrecord;
import kyle.leis.hi.TfiWriteoff;

public class SaveWriteOffTrans extends AbstractTransaction {
	private String m_strOperId;
	private WriteoffColumns m_objWriteoffColumns;
	private String[] m_astrCrId;
	private String[] m_astrBrId;
	private CashrecordColumns m_objFlushCrColumns;
	private Long m_lNewWoid;
	
	public void setParam(WriteoffColumns objWriteoffColumns,
			String[] astrCrId,
			String[] astrBrId,
			String strOperId) throws Exception {
		// 效验是否都为空
		if ((astrCrId == null || astrCrId.length < 1) &&
				(astrBrId == null || astrBrId.length < 1))
			return;
		// 统计收付款总额
		SumCashTotalResult objSCTResult = CashRecordDemand.sumCashTotal(astrCrId);
		BigDecimal objSumCrTotal = objSCTResult.getSumCrTotal();
		CashrecordColumns objLastCrColumns = objSCTResult.getCashrecordColumns();
		// 统计账单总额
		SumBillTotalResult objSBTResult = BillRecordDemand.sumBillRecordTotal(astrBrId);
		BigDecimal objSumBrTotal = objSBTResult.getSumBrTotal();
		// 差额不能大于10
		BigDecimal objDifference = objSumCrTotal.add(objSumBrTotal.multiply(new BigDecimal("-1")));
		if (objDifference.abs().compareTo(new BigDecimal("10")) > 0)
			return;
		// 冲销
		if (objDifference.compareTo(new BigDecimal("0")) != 0) {
			m_objFlushCrColumns = objLastCrColumns;
			m_objFlushCrColumns.setCrcrtotal(objDifference.abs());
			m_objFlushCrColumns.setCkckcode("RMB");
			m_objFlushCrColumns.setCrcrcurrencyrate(new BigDecimal("1"));
			// 支付方式改为核销尾款
			m_objFlushCrColumns.setPtptcode("WT");
			m_objFlushCrColumns.setCrcrid(null);
			m_objFlushCrColumns.setCrkcrkcode(ICashRecordBasicData.CRK_RECEIVABLE_ACCOUNT);
			m_objFlushCrColumns.setCrcrremark("核销时自动增加");
			//m_objFlushCrColumns.setCrkcrkcode("WA");
			if (objDifference.compareTo(new BigDecimal("0")) < 0)
				m_objFlushCrColumns.setCrkcrkcode(ICashRecordBasicData.CRK_PAYABLE_ACCOUNT);
		}
		m_astrCrId = astrCrId;
		m_astrBrId = astrBrId;
		m_strOperId = strOperId;
		m_objWriteoffColumns = objWriteoffColumns;
		m_objWriteoffColumns.setWowototal(objSumBrTotal);
	}
	
	public String getSavedWoId() {
		return String.valueOf(m_lNewWoid);
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objWriteoffColumns == null) return;
		// 保存核销记录
		TfiWriteoff objTfiWriteoff = new TfiWriteoff();
		WriteoffDemand.setWriteoffByColumns(objTfiWriteoff, 
				m_objWriteoffColumns,
				m_strOperId,
				objSession);
		if (!StringUtility.isNull(m_strOperId)) {
			TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, 
					Long.parseLong(m_strOperId));
			objTfiWriteoff.setTdiOperator(objTdiOperator);
			objTfiWriteoff.setWoCreatedate(DateFormatUtility.getSysdate());
		}
		objTfiWriteoff.setWoLabelcode(WriteoffDemand.getWoLabelcode(m_objWriteoffColumns));
		objSession.save(objTfiWriteoff);
		m_lNewWoid = objTfiWriteoff.getWoId();
		// 保存冲销收付款
		if (m_objFlushCrColumns != null) {
			SaveCashRecordTrans objSCRTrans = new SaveCashRecordTrans();
			objSCRTrans.setParam(m_objFlushCrColumns, m_strOperId);
			objSCRTrans.transaction(objSession);
			TfiCashrecord objWriteOffCashrecord = objSCRTrans.getSavedCashrecord();
			objWriteOffCashrecord.setTfiWriteoff(objTfiWriteoff);
			objSession.update(objWriteOffCashrecord);
		}		
		TdiCashrecordstatus objTCRStatus = TdiCashrecordstatusDC.loadByKey("W");
		// 核销收付款
		if (m_astrCrId != null && m_astrCrId.length > 0)
			for (int i = 0; i < m_astrCrId.length; i++) {
				TfiCashrecord objTfiCashrecord = (TfiCashrecord)objSession.load(TfiCashrecord.class, 
						Long.parseLong(m_astrCrId[i]));
				objTfiCashrecord.setTfiWriteoff(objTfiWriteoff);
				objTfiCashrecord.setTdiCashrecordstatus(objTCRStatus);
				objSession.update(objTfiCashrecord);
			}
		// 核销账单
		TdiBillrecordstatus objTBRStatus = TdiBillrecordstatusDC.loadByKey("W");
		if (m_astrBrId != null && m_astrBrId.length > 0)
			for (int i = 0; i < m_astrBrId.length; i++) {
				TfiBillrecord objTfiBillrecord = (TfiBillrecord)objSession.load(TfiBillrecord.class, 
						Long.parseLong(m_astrBrId[i]));
				objTfiBillrecord.setTfiWriteoff(objTfiWriteoff);
				objTfiBillrecord.setTdiBillrecordstatus(objTBRStatus);	
				objSession.update(objTfiWriteoff);
				// 修改费用状态
				execute(objSession, "UPDATE T_BL_RECEIVABLE rv " + 
						" SET FS_CODE = 'W'" +
						" WHERE rv.BR_ID = " + objTfiBillrecord.getBrId());
				// 修改应付费用
				execute(objSession, "UPDATE T_BL_PAYABLE py " + 
						" SET FS_CODE = 'W'" +
						" WHERE py.BR_ID = " + objTfiBillrecord.getBrId());
			}
	}
}
