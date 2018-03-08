package kyle.leis.eo.finance.serverbillrecord.tp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.currency.bl.Currency;
import kyle.leis.eo.finance.serverbillrecord.da.ServerpayableColumns;
import kyle.leis.eo.finance.serverbillrecord.dax.ServerBillRecordDemand;
import kyle.leis.hi.TfiServerbillrecord;
import kyle.leis.hi.TfiServerpayable;
import kyle.leis.hi.TfiServerwaybill;

public class AddDetailTransation extends AbstractTransaction {
	private TfiServerbillrecord m_objTSBRecord; 
	private List m_listSavePayable;
	
	public void setParam(TfiServerbillrecord objSBRecord,
			List listSavePayable) {
		m_objTSBRecord = objSBRecord;
		m_listSavePayable = listSavePayable;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objTSBRecord == null) return;
		// ���������ϸ
		if (m_listSavePayable == null || m_listSavePayable.size() < 1)
			return;
		BigDecimal objTotalAmount = new BigDecimal("0");
		// �˵��Ŷ�Ӧ�ķ������˵���¼
		HashMap<String, TfiServerwaybill> hmServerEwbWaybill = new HashMap<String, TfiServerwaybill>();
		// ��Ҫ����ͳ����Ϣ�ķ������˵�
		List<TfiServerwaybill> listModifyWaybill = new ArrayList<TfiServerwaybill>();
		for (int i = 0; i < m_listSavePayable.size(); i++) {
			ServerpayableColumns objSPYColumns = (ServerpayableColumns)m_listSavePayable.get(i);
			/* debug
			if (objSPYColumns.getSwbswbserverewbcode().equals("9967578272") ||
					objSPYColumns.getSwbswbserverewbcode().equals("9970645344") ||
					objSPYColumns.getSwbswbserverewbcode().equals("9965299562") ||
					objSPYColumns.getSwbswbserverewbcode().equals("9969605085")) {
				objSPYColumns.setSwbswbserverewbcode(objSPYColumns.getSwbswbserverewbcode());
			}
			*/
			// ���Ƚ��з�������ӳ��
			ServerBillRecordDemand.setServerPayableFkcode(objSPYColumns);
			// ����Ѿ����ڴ˷��������ظ���������
			boolean isExistsPayable = ServerBillRecordDemand.isExistsPayable(objSPYColumns.getSwbswbserverewbcode(), 
					objSPYColumns.getFkfkcode(), 
					objSPYColumns.getChnchncode(),
					m_objTSBRecord.getSbrLabelcode());
			if (isExistsPayable) continue;
			// ���û���
			Currency objCurrency = new Currency();
			String strCurrencyrate = objCurrency.getCurrencyrate("",  "A", 
					DateFormatUtility.getStandardDate(m_objTSBRecord.getSbrOccurdate()), 
					"1",
					objSPYColumns.getCkckcode());
			if (StringUtility.isNull(strCurrencyrate))
				throw (new Exception("�޷���÷����̱��ֶ�Ӧ�Ļ��ʣ�����Ϊ��" + 
						objSPYColumns.getCkckcode()));
			objSPYColumns.setSpyspycurrencyrate(new BigDecimal(strCurrencyrate));
			objSPYColumns.setChnchncode(m_objTSBRecord.getTchnChannel().getChnCode());
			// �ȱ���������˵�
			// 1�����ϵͳ�в����ڴ˷����̵��������������������˵�����
			// 2������Ѿ���������Ҫ�����˵��ķ���ͳ����Ϣ
			// 3����Ҫ����ķ����̷�����ϸ�п��ܴ����ظ��ķ����̵���
			String strServerWBCode = objSPYColumns.getSwbswbserverewbcode();
			TfiServerwaybill objTfiServerwaybill;
			if (hmServerEwbWaybill.containsKey(strServerWBCode)) {
				// �˵��ظ�����Ҫ�����˵�ͳ����Ϣ
				objTfiServerwaybill = hmServerEwbWaybill.get(strServerWBCode);
				ServerBillRecordDemand.resetWaybillFee(objTfiServerwaybill, objSPYColumns);
				listModifyWaybill.add(objTfiServerwaybill);
			} else {
				// �˵����ظ�
				// ���ϵͳ�в����ڸ÷������˵����½��������޸ĸ��˵���ͳ����Ϣ
				AddServerwaybillTrans objAddSWBTrans = new AddServerwaybillTrans();
				objAddSWBTrans.setParam(objSPYColumns);
				objAddSWBTrans.transaction(objSession);
				objTfiServerwaybill = objAddSWBTrans.getSavedServerwaybill();
				if (!objAddSWBTrans.isInsertWaybill())
					listModifyWaybill.add(objTfiServerwaybill);
				// �����˵������б����ظ�
				hmServerEwbWaybill.put(strServerWBCode, objTfiServerwaybill);
			}
			// ���������ϸ
			TfiServerpayable objTfiServerpayable = new TfiServerpayable();
			// ���÷��õ�����
			ServerBillRecordDemand.setServerPayableByColumns(objSPYColumns,
					objTfiServerpayable,
					m_objTSBRecord.getSbrOccurdate());
			// ���÷������˵�
			objSPYColumns.setSwbswbreferencecode(objTfiServerwaybill.getSwbReferencecode());
			objTfiServerpayable.setTfiServerbillrecord(m_objTSBRecord);
			objTfiServerpayable.setTfiServerwaybill(objTfiServerwaybill);
			objSession.save(objTfiServerpayable);
			// ͳ�Ʒ����Ա�����˵��ϵ��ܷ���
			String strCurrencyRate = objSPYColumns.getSpyspycurrencyrate();
			BigDecimal objColumnsTotalCharge = new BigDecimal(objSPYColumns.getSpyspytotalcharge()).multiply(new BigDecimal(strCurrencyRate));
			objColumnsTotalCharge = objColumnsTotalCharge.divide(new BigDecimal("1"), 2, 4);			
			objTotalAmount = objTotalAmount.add(objColumnsTotalCharge);
		}
		// �޸��˵���ͳ����Ϣ
		if (listModifyWaybill != null && listModifyWaybill.size() > 0) {
			for (int i = 0; i < listModifyWaybill.size(); i++) {
				TfiServerwaybill objTfiServerwaybill = listModifyWaybill.get(i);
				objSession.update(objTfiServerwaybill);
			}
		}
		// ���ڴ��ڷ��úϲ���ͬһ���˵������������ڱ�����ϸ��ͳһ�޸��˵����
		/*
		BigDecimal objOriginSBRTotal = m_objTSBRecord.getSbrTotal();
		if (objOriginSBRTotal == null)
			objOriginSBRTotal = new BigDecimal("0");
		m_objTSBRecord.setSbrTotal(objOriginSBRTotal.add(objTotalAmount));
		objSession.update(m_objTSBRecord);
		*/
	}
}
