package kyle.leis.eo.billing.receivable.tp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import kyle.leis.eo.billing.receivable.dax.IReceivableBasicData;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.hi.TblReceivable;
import kyle.leis.hi.ThiReceivable;

public class DelelteReceivableTrans extends AbstractTransaction {
	private List<ReceivableColumns> m_listReceivable;
	private String m_strOperId;
	private String m_strCwcode;
	
	public String getDeleteOriginCwcode() {
		return m_strCwcode;
	}
	
	/**
	 * ɾ��ϵͳ��¼�ķ���
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void setParam(String strCwcode,
			String strOperId) throws Exception {
		m_strOperId = strOperId;
		// ��ѯӦ�շ���
		if (StringUtility.isNull(strCwcode)) return;
		List listReceivable = ReceivableDemand.load(strCwcode);
		if (listReceivable == null || listReceivable.size() < 1)
			return;
		m_listReceivable = new ArrayList<ReceivableColumns>();
		for (int i = 0; i < listReceivable.size(); i++) {
			ReceivableColumns objRvColumns = (ReceivableColumns)listReceivable.get(i);
			String strModifyOperId = objRvColumns.getMopopid();
			String strReference = objRvColumns.getRvrvidreference();
			// ����ɾ����������ķ���
			if (strModifyOperId.equals(IReceivableBasicData.OPERID_SYSTEM) &&
					StringUtility.isNull(strReference))
				m_listReceivable.add(objRvColumns);
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
		if (StringUtility.isNull(strCwcode)) return;
		List listReceivable = ReceivableDemand.loadAll(strCwcode);
		if (listReceivable == null || listReceivable.size() < 1)
			return;
		m_listReceivable = new ArrayList<ReceivableColumns>();
		for (int i = 0; i < listReceivable.size(); i++) {
			ReceivableColumns objRvColumns = (ReceivableColumns)listReceivable.get(i);
			m_listReceivable.add(objRvColumns);
		}
	}
	
	/**
	 * ɾ��ϵͳ������ӷѲ����Ѿ�ɾ���ķ��ôӼ��������
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */	
	public List<ReceivableColumns> setAutoCalcParam(List listOriginRvColumns) {
		if (listOriginRvColumns == null || listOriginRvColumns.size() < 1)
			return null;
		m_listReceivable = new ArrayList<ReceivableColumns>();
		List<ReceivableColumns> listRetainedOriginRv = new ArrayList<ReceivableColumns>();
		
		for (int i = 0; i < listOriginRvColumns.size(); i++) {
			ReceivableColumns objRvColumns = (ReceivableColumns)listOriginRvColumns.get(i);
			String strModifyOperId = objRvColumns.getMopopid();
			String strFscode = objRvColumns.getFsfscode();
			String strFkcode = objRvColumns.getFkfkcode();
			// ɾ��ȷ��״̬��ϵͳ��¼���ӷ�
			if (strFscode.equals(IReceivableBasicData.FEE_STATUS_CONFIRM) &&
					strModifyOperId.equals(IReceivableBasicData.OPERID_SYSTEM) &&
					(strFkcode.startsWith(IFeeCalculateBasicData.FEEKIND_INCIDENTAL) ||
							strFkcode.equals(IFeeCalculateBasicData.FEEKIND_SURCHARGE_OIL)))
				m_listReceivable.add(objRvColumns);
			else
				listRetainedOriginRv.add(objRvColumns);
		}
		m_strOperId = IReceivableBasicData.OPERID_SYSTEM;
		return listRetainedOriginRv;
	}
	
	public void setParam(ReceivableColumns objDeleteRvColumns, 
			String strOperId) throws Exception {
		m_listReceivable = new ArrayList<ReceivableColumns>();
		m_listReceivable.add(objDeleteRvColumns);
		m_strOperId = strOperId;
	}
	
	public void setParam(String[] astrRvid, 
			String strOperId) throws Exception {
		if (astrRvid == null || astrRvid.length < 1)
			return;
		m_strOperId = strOperId;
		m_listReceivable = new ArrayList<ReceivableColumns>();
		for (int i = 0; i < astrRvid.length; i++) {
			ReceivableColumns obj = ReceivableDemand.loadByRvid(astrRvid[i]);
			m_strCwcode = obj.getCwcwcode();
			m_listReceivable.add(obj);
		}
	}
	
	public List<ReceivableColumns> getOriginReceivable() {
		return m_listReceivable;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listReceivable == null || m_listReceivable.size() < 1)
			return;
		for (int i = 0; i < m_listReceivable.size(); i++) {
			ReceivableColumns objRvColumns = (ReceivableColumns)m_listReceivable.get(i);
			if (objRvColumns == null) continue;
			String strFscode = objRvColumns.getFsfscode();
			String strRvid = objRvColumns.getRvrvid();	 
			// ��ɾ����������ķ���
			if (!StringUtility.isNull(objRvColumns.getRvrvidreference()))
				continue;
			// �ݸ塢Ԥ����ȷ�����ü�¼����ʷ���ɾ��ԭ����
			if (strFscode.equals(IReceivableBasicData.FEE_STATUS_PRELERT) ||
					strFscode.equals(IReceivableBasicData.FEE_STATUS_DRAFT) ||
					strFscode.equals(IReceivableBasicData.FEE_STATUS_CONFIRM) || 
					strFscode.equals(IReceivableBasicData.FEE_STATUS_ELIMINATE)) {
				// �Ƚ�ԭʼ����������ʷ���ݿ�
				TblReceivable objOriginReceivable = (TblReceivable)objSession.load(TblReceivable.class, 
						Long.parseLong(strRvid)); 
				ThiReceivable objThiReceivable = ReceivableDemand.createHiReceivable(objOriginReceivable,
						m_strOperId, 
						objSession);
				objSession.save(objThiReceivable);
				// ɾ��֮
				objSession.delete(" from TblReceivable as rv where rv.rvId = " + strRvid);
			}
			// ���˻����
			else if (strFscode.equals(IReceivableBasicData.FEE_STATUS_BILL) ||
					 strFscode.equals(IReceivableBasicData.FEE_STATUS_WRITEOFF)) {
				// �������������
				ReceivableColumns objFlushRecColumns = ReceivableDemand.loadByRvid(strRvid);
				objFlushRecColumns.setRvrvidreference(Long.parseLong(strRvid));
				objFlushRecColumns.setRvrvactualtotal(new BigDecimal("-1").multiply(new BigDecimal(objFlushRecColumns.getRvrvactualtotal())));
				objFlushRecColumns.setRvrvtotal(new BigDecimal("-1").multiply(new BigDecimal(objFlushRecColumns.getRvrvtotal())));
				objFlushRecColumns.setRvrvremark("������������");
				objFlushRecColumns.setFsfscode("C");
				
				AddReceivableTrans objAddReceivableTrans = new AddReceivableTrans();
				objAddReceivableTrans.setParam(objFlushRecColumns, "0", true);
				objAddReceivableTrans.transaction(objSession);
				Long lNewRvid = objAddReceivableTrans.getNewRvid();
				// �޸�ԭ���õ�ָ��
				TblReceivable objOriginTblReceivable = (TblReceivable)objSession.load(TblReceivable.class, 
						Long.parseLong(strRvid));
				objOriginTblReceivable.setRvIdReference(lNewRvid);
				objSession.update(objOriginTblReceivable);				
			}
		}
	}
}
