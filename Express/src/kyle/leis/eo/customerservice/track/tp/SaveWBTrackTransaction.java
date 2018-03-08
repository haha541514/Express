package kyle.leis.eo.customerservice.track.tp;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.customerservice.track.da.WaybillbatchtrackColumns;
import kyle.leis.eo.customerservice.track.da.WaybilltrackColumns;
import kyle.leis.eo.customerservice.track.dax.TrackDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TcsWaybilltrack;
import kyle.leis.hi.TdiOperator;

public class SaveWBTrackTransaction extends AbstractTransaction {
	private List<WaybilltrackColumns> m_listWBTColumns;
	private String m_strOperId;
	private boolean m_isDelOriginTrack;
	private SaveBatchTrackTransaction m_objSBTTrans;
	private TcsWaybilltrack m_objTcsWaybilltrack;
	
	public void setParam(List<WaybilltrackColumns> listWBTColumns,
			String strOperId,
			boolean isDelOriginTrack) throws Exception {
		m_listWBTColumns = listWBTColumns;
		m_strOperId = strOperId;
		m_isDelOriginTrack = isDelOriginTrack;
		
		if (listWBTColumns == null || listWBTColumns.size() < 1)
			return;
		WaybilltrackColumns objLastWBTColumns = m_listWBTColumns.get(listWBTColumns.size() - 1);
		String strCwcode = objLastWBTColumns.getWbbtcwcode();
		if (StringUtility.isNull(strCwcode)) return;
		// ���ι켣
		WaybillbatchtrackColumns objWBBTColumns = TrackDemand.loadBatchTrack(strCwcode);
		boolean isInsetBatchTrack = false;
		if (objWBBTColumns == null) {
			objWBBTColumns = new WaybillbatchtrackColumns();
			isInsetBatchTrack = true;
		}
		objWBBTColumns.setWbbtcwcode(Long.parseLong(strCwcode));
		objWBBTColumns.setWbbtwbbtlatesttrackdesc(objLastWBTColumns.getWbtwbtdescription());
		objWBBTColumns.setWbtswbtscode(objLastWBTColumns.getWbtswbtscode());
		
		if (!StringUtility.isNull(objLastWBTColumns.getWbtwbtoccurdate()))
			objWBBTColumns.setWbbtwbbtlatesttrackdate(DateFormatUtility.getStandardDate(objLastWBTColumns.getWbtwbtoccurdate()));
		
		m_objSBTTrans = new SaveBatchTrackTransaction();
		m_objSBTTrans.setParam(objWBBTColumns, isInsetBatchTrack);		
	}
	
	public void setParam(WaybilltrackColumns objWBTColumns,
			String strOperId,
			boolean isDelOriginTrack) throws Exception {
		List<WaybilltrackColumns> listWBTColumns = new ArrayList<WaybilltrackColumns>();
		listWBTColumns.add(objWBTColumns);
		setParam(listWBTColumns, strOperId, isDelOriginTrack);
	}
	
	public TcsWaybilltrack getSavedWayBillTrack() {
		return m_objTcsWaybilltrack;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listWBTColumns == null || m_listWBTColumns.size() < 1)
			return;
		String strCwcode = m_listWBTColumns.get(0).getWbbtcwcode();
		if (StringUtility.isNull(strCwcode)) return;
		// ɾ��ԭ�켣
		if (m_isDelOriginTrack) {
			execute(objSession, "delete from T_CS_WAYBILLTRACK where CW_CODE = " + 
					strCwcode);			
		}
		// ����
		if (m_objSBTTrans == null) return; 
		// �������ι켣���¼
		m_objSBTTrans.transaction(objSession);
		// ���ù켣����
		for (int i = 0; i < m_listWBTColumns.size(); i++) {
			WaybilltrackColumns objWBTColumns = m_listWBTColumns.get(i);
			String strWbtid = objWBTColumns.getWbtwbtid();
			TcsWaybilltrack objTcsWaybilltrack;
			if (StringUtility.isNull(strWbtid)) {
				objTcsWaybilltrack = new TcsWaybilltrack();
				TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
				objTcsWaybilltrack.setTdiOperatorByOpIdCreator(objTdiOperator);
				objTcsWaybilltrack.setWbtCreatedate(DateFormatUtility.getSysdate());
			} else {
				objTcsWaybilltrack = (TcsWaybilltrack)objSession.load(TcsWaybilltrack.class, 
						Long.parseLong(strWbtid));
			}
			TrackDemand.setTrackByColumns(objTcsWaybilltrack,
					objWBTColumns,
					m_strOperId,
					objSession);
			// ����켣��
			objTcsWaybilltrack.setTcsWaybillbatchtrack(m_objSBTTrans.getSavedBatchTrack());
			objSession.save(objTcsWaybilltrack);
			m_objTcsWaybilltrack = objTcsWaybilltrack;
		}
	}
}
