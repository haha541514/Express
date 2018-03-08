package kyle.leis.eo.operation.transportwaybill.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybilltraceColumns;
import kyle.leis.eo.operation.transportwaybill.dax.TransportWaybillDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiTransportwaybillstatusDC;
import kyle.leis.hi.TdiDistrict;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiTransportwaybillstatus;
import kyle.leis.hi.TopTransportwaybill;
import kyle.leis.hi.TopTransportwaybilltrace;
import kyle.leis.hi.TopTransportwaybilltracePK;

public class ModifyStatusTrans extends AbstractTransaction {
	private List<TransportwaybilltraceColumns> m_listTWTColumns;
	private String m_strOperId;
	
	public void setParam(List<TransportwaybilltraceColumns> listTWTColumns,
			String strOperId) {
		m_listTWTColumns = listTWTColumns;
		m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		// ������״̬���ٱ�
		if (m_listTWTColumns == null || m_listTWTColumns.size() < 1)
			return;
		// ��ɾ��
		execute(objSession, "delete from t_op_transportwaybilltrace twbt where twbt.twb_id = " +
				m_listTWTColumns.get(0).getTwbtcomp_idtwbid());	
		// ����
		TopTransportwaybill objTTW = null;
		TdiTransportwaybillstatus objTTWBS = null;
		for (int i = 0; i < m_listTWTColumns.size(); i++) {
			TransportwaybilltraceColumns objTWTColumns = m_listTWTColumns.get(i);
			TopTransportwaybilltrace objTTWT = new TopTransportwaybilltrace();
			// ����
			TopTransportwaybilltracePK objTTWPK = new TopTransportwaybilltracePK();
			objTTWPK.setTwbId(Long.parseLong(objTWTColumns.getTwbtcomp_idtwbid()));
			objTTWPK.setTwbsCode(objTWTColumns.getTwbtcomp_idtwbscode());			
			objTTWT.setComp_id(objTTWPK);

			objTTWT.setTwbtOccurdate(DateFormatUtility.getStandardDate(objTWTColumns.getTwbttwbtoccurdate()));
			if (!StringUtility.isNull(objTWTColumns.getDtdtcode())) {
				TdiDistrict objTdiDistrict = (TdiDistrict)objSession.load(TdiDistrict.class, 
						objTWTColumns.getDtdtcode());
				objTTWT.setTdiDistrict(objTdiDistrict);
			}
			TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
			objTTWT.setTdiOperator(objTdiOperator);
			objTTWT.setTwbtCreatedate(DateFormatUtility.getSysdate());
			
			objTTWBS = TdiTransportwaybillstatusDC.loadByKey(objTWTColumns.getTwbtcomp_idtwbscode());
			objTTWT.setTdiTransportwaybillstatus(objTTWBS);
			// ��������
			objTTW = (TopTransportwaybill)objSession.load(TopTransportwaybill.class,
					Long.parseLong(objTWTColumns.getTwbtcomp_idtwbid()));
			objTTWT.setTopTransportwaybill(objTTW);
			// ������ٱ�����
			objSession.save(objTTWT);
		}
		// ������������״̬
		TransportwaybilltraceColumns objLatestTWBTColumns = TransportWaybillDemand.getLatestTWBTrace(m_listTWTColumns);
		if (objLatestTWBTColumns == null) return;
		objTTWBS = TdiTransportwaybillstatusDC.loadByKey(objLatestTWBTColumns.getTwbtcomp_idtwbscode());
		// ������������״̬
		objTTW.setTdiTransportwaybillstatus(objTTWBS);
		objSession.save(objTTW);
	}

}
