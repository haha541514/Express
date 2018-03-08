package kyle.leis.eo.finance.serverbillrecord.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.serverbillrecord.da.ServerbillrecordColumns;
import kyle.leis.eo.finance.serverbillrecord.dax.ServerBillRecordDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TfiServerbillrecord;

public class SaveTransaction extends AbstractTransaction {
	private ServerbillrecordColumns m_objSBRColumns;
	private List m_listSaveBilldetail;
	private String m_strOperId;
	private String m_strNewSbrId;
	
	public void setParam(ServerbillrecordColumns objSBRColumns,
			List listSaveBilldetail,
			String strOperId) {
		m_objSBRColumns = objSBRColumns;
		m_listSaveBilldetail = listSaveBilldetail;
		m_strOperId = strOperId;
	}
	
	public String getSavedSbrId() {
		return m_strNewSbrId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objSBRColumns == null) return;
		// �����˵����˵���ϸ
		String strSbrId = m_objSBRColumns.getSbrsbrid();
		String strSbrLabelcode = m_objSBRColumns.getSbrsbrlabelcode();
		TfiServerbillrecord objTSBR = null;
		if (StringUtility.isNull(strSbrId)) {
			// ���������������
			// 1��ϵͳ�в������˵���ţ���ֱ�������˵���ϸ
			// 2��ϵͳ���Ѿ����ڴ��˵���ţ����޸�ԭ�˵��ܽ��ϲ���Ʊ���ķ��á������ȡ�
			ServerbillrecordColumns objSBRColumns = ServerBillRecordDemand.loadByLabelcode(strSbrLabelcode,
					m_objSBRColumns.getChnchncode());
			if (objSBRColumns == null || 
					StringUtility.isNull(objSBRColumns.getSbrsbrid())) {
				// ����
				objTSBR = new TfiServerbillrecord();
				objTSBR.setSbrCreatedate(DateFormatUtility.getSysdate());
				TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
				objTSBR.setTdiOperatorByOpIdCreator(objTdiOperator);
			} else {
				// �ϲ��˵���ϸ
				objTSBR = (TfiServerbillrecord)objSession.load(TfiServerbillrecord.class, 
						Long.parseLong(objSBRColumns.getSbrsbrid()));
			}
		} else {
			// �޸��˵�����ɾ��ԭ�˵���ϸ��������
			objTSBR = (TfiServerbillrecord)objSession.load(TfiServerbillrecord.class, 
					Long.parseLong(strSbrId));
		}
		// ����������˵�
		ServerBillRecordDemand.setBillRecordByColumns(m_objSBRColumns,
				objTSBR,
				m_strOperId,
				objSession);
		objSession.save(objTSBR);
		m_strNewSbrId = String.valueOf(objTSBR.getSbrId());
		// ����������˵���ϸ
		AddDetailTransation objAddTrans = new AddDetailTransation();
		objAddTrans.setParam(objTSBR, m_listSaveBilldetail);
		objAddTrans.transaction(objSession);
	}
}
