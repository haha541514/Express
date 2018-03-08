package kyle.leis.eo.billing.payable.tp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.payable.dax.PayableDemand;
import kyle.leis.eo.billing.receivable.dax.IReceivableBasicData;
import kyle.leis.hi.TblPayable;
import kyle.leis.hi.ThiPayable;

public class DeleltePayableTrans extends AbstractTransaction {
	private List<PayableColumns> m_listPayable;
	private String m_strOperId;
	private String m_strCwcode;
	
	public String getOriginDeleteCwcode() {
		return m_strCwcode;
	}
	
	/**
	 * ɾ��ϵͳ����ķ���
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void setParam(String strCwcode, 
			String strOperId) throws Exception {
		m_strOperId = strOperId;
		// ��ѯӦ�շ���
		List listPayable = PayableDemand.load(strCwcode, "A0201");
		if (listPayable == null || listPayable.size() < 1)
			return;
		m_listPayable = new ArrayList<PayableColumns>();
		// ɾ��ϵͳ��¼��Ӧ������
		for (int i = 0; i < listPayable.size(); i++) {
			PayableColumns objPayableColumns = (PayableColumns)listPayable.get(i);
			String strModifyOperId = objPayableColumns.getMopopid();
			if (strModifyOperId.equals(IReceivableBasicData.OPERID_SYSTEM))
				m_listPayable.add(objPayableColumns);
		}
	}
	
	/**
	 * ɾ�����з���
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void setDelAllParam(String strCwcode,
			String strOperId) throws Exception {
		m_strOperId = strOperId;
		// ��ѯӦ�շ���
		List listPayable = PayableDemand.load(strCwcode, "A0201");
		if (listPayable == null || listPayable.size() < 1)
			return;
		m_listPayable = new ArrayList<PayableColumns>();
		// ɾ��ϵͳ��¼��Ӧ������
		for (int i = 0; i < listPayable.size(); i++) {
			PayableColumns objPayableColumns = (PayableColumns)listPayable.get(i);
			m_listPayable.add(objPayableColumns);
		}
	}
	
	/**
	 * ɾ��ϵͳ�Զ�������ӷ�
	 * @param listOriginPyColumns
	 */
	public List<PayableColumns> setAutoCalcParam(List listOriginPyColumns) {
		if (listOriginPyColumns == null || listOriginPyColumns.size() < 1)
			return null;
		m_listPayable = new ArrayList<PayableColumns>();
		List<PayableColumns> listRetainedOriginPy= new ArrayList<PayableColumns>();
		
		for (int i = 0; i < listOriginPyColumns.size(); i++) {
			PayableColumns objPayableColumns = (PayableColumns)listOriginPyColumns.get(i);
			String strModifyOperId = objPayableColumns.getMopopid();
			String strFscode = objPayableColumns.getFsfscode();
			String strFkcode = objPayableColumns.getFkfkcode();
			String strSpyId = objPayableColumns.getSpyspyid();
			// ɾ��ȷ��״̬��ϵͳ��¼��δ��Ӧ�������˵����õ��ӷ�
			if (strFscode.equals(IReceivableBasicData.FEE_STATUS_CONFIRM) &&
					strModifyOperId.equals(IReceivableBasicData.OPERID_SYSTEM) &&
					(strFkcode.startsWith(IFeeCalculateBasicData.FEEKIND_INCIDENTAL) || strFkcode.equals(IFeeCalculateBasicData.FEEKIND_SURCHARGE_OIL)) &&
					StringUtility.isNull(strSpyId))
				m_listPayable.add(objPayableColumns);
			else
				listRetainedOriginPy.add(objPayableColumns);
		}
		m_strOperId = IReceivableBasicData.OPERID_SYSTEM;
		return listRetainedOriginPy;
	}
	
	/**
	 * ɾ��ĳ�����
	 * @param objDeletePayColumns
	 * @param strOperId
	 * @throws Exception
	 */
	public void setParam(PayableColumns objDeletePayColumns, 
			String strOperId) throws Exception {
		m_listPayable = new ArrayList<PayableColumns>();
		m_listPayable.add(objDeletePayColumns);
		m_strOperId = strOperId;
	}
	
	/**
	 * ɾ�����ü���
	 * @param astrPyid
	 * @param strOperId
	 * @throws Exception
	 */
	public void setParam(String[] astrPyid, 
			String strOperId) throws Exception {
		if (astrPyid == null || astrPyid.length < 1)
			return;
		m_strOperId = strOperId;
		m_listPayable = new ArrayList<PayableColumns>();
		for (int i = 0; i < astrPyid.length; i++) {
			PayableColumns objPayableColumns = PayableDemand.loadByPyid(astrPyid[i]);
			m_strCwcode = objPayableColumns.getCwcwcode();
			m_listPayable.add(objPayableColumns);
		}
	}
	
	public List<PayableColumns> getOriginPayable() {
		return m_listPayable;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listPayable == null || m_listPayable.size() < 1)
			return;
		for (int i = 0; i < m_listPayable.size(); i++) {
			PayableColumns objPayableColumns = (PayableColumns)m_listPayable.get(i);
			if (objPayableColumns == null) continue;
			
			String strFscode = objPayableColumns.getFsfscode();
			String strPyid = objPayableColumns.getPypyid();
			// ��ɾ����������ķ���
			if (!StringUtility.isNull(objPayableColumns.getPypyidreference()))
				continue;
			// �ݸ塢Ԥ����ȷ�����ü�¼����ʷ���ɾ��ԭ����
			if (strFscode.equals(IReceivableBasicData.FEE_STATUS_PRELERT) ||
					strFscode.equals(IReceivableBasicData.FEE_STATUS_DRAFT) ||
					strFscode.equals(IReceivableBasicData.FEE_STATUS_CONFIRM) || 
					strFscode.equals(IReceivableBasicData.FEE_STATUS_ELIMINATE)) {
				// �Ƚ�ԭʼ����������ʷ���ݿ�
				TblPayable objOriginTblPayable = (TblPayable)objSession.load(TblPayable.class, 
						Long.parseLong(strPyid));
				ThiPayable objThiPayable = PayableDemand.createHiPayable(objOriginTblPayable, 
						m_strOperId, 
						objSession);
				objSession.save(objThiPayable);
				// ɾ��֮
				objSession.delete(" from TblPayable as py where py.pyId = " + strPyid);
			}
			// ���˻����
			else if (strFscode.equals(IReceivableBasicData.FEE_STATUS_BILL) ||
					 strFscode.equals(IReceivableBasicData.FEE_STATUS_WRITEOFF)) {
				// �������������
				PayableColumns objFlushPayableColumns = PayableDemand.loadByPyid(strPyid);
				objFlushPayableColumns.setPypyidreference(Long.parseLong(strPyid));
				objFlushPayableColumns.setPypyactualtotal(new BigDecimal("-1").multiply(new BigDecimal(objFlushPayableColumns.getPypyactualtotal())));
				objFlushPayableColumns.setPypytotal(new BigDecimal("-1").multiply(new BigDecimal(objFlushPayableColumns.getPypytotal())));
				objFlushPayableColumns.setFsfscode("C");
				
				AddPayableTrans objAddPayableTrans = new AddPayableTrans();
				objAddPayableTrans.setParam(objFlushPayableColumns, "0", true);
				objAddPayableTrans.transaction(objSession);
				Long lNewPyid = objAddPayableTrans.getNewPyid();
				// �޸�ԭ���õ�ָ��
				TblPayable objOriginTblPayable = (TblPayable)objSession.load(TblPayable.class, 
						Long.parseLong(strPyid));
				objOriginTblPayable.setPyIdReference(lNewPyid);
				objSession.update(objOriginTblPayable);				
			}
		}
	}
}
