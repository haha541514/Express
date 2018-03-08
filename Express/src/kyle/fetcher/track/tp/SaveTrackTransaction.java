package kyle.fetcher.track.tp;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.fetcher.track.dax.TrackWayBillDemand;
import kyle.leis.eo.customerservice.track.da.WaybilltrackColumns;
import kyle.leis.eo.customerservice.track.tp.SaveWBTrackTransaction;
import kyle.leis.es.businessrule.channeltrackmapping.dax.ChanneltrackmappingDemand;

/**
 * User: Kyle
 * Date: 2009-2-9
 * Time: 13:20:59
 */
public class SaveTrackTransaction extends AbstractTransaction {
	private List<WaybilltrackColumns> m_listWBTColumns;
	private String m_strChncode;
	
	public void setTracks(List<WaybilltrackColumns> listWBTColumns, 
			String strChncode) {
		if (listWBTColumns == null || listWBTColumns.size() < 1)
			return;
		String strTrackDescription, strWbtscode;
		for (int i = 0; i < listWBTColumns.size(); i++) {
			WaybilltrackColumns objWBTColumns = listWBTColumns.get(i);
			//����켣��ϸΪ�գ�����ԭʼ�켣��ϸȡ����
			strTrackDescription = objWBTColumns.getWbtwbtdescription();
			if (StringUtility.isNull(strTrackDescription)) {
				strTrackDescription = objWBTColumns.getWbtwbtorigindescription();
				objWBTColumns.setWbtwbtdescription(strTrackDescription);
			}
			//����Ѿ����ڹ켣״̬�룬������ӳ����ҡ�
			strWbtscode = objWBTColumns.getWbtswbtscode();
			if (StringUtility.isNull(strWbtscode)) {
				strWbtscode = TrackWayBillDemand.mapTrackStatus(strChncode, 
						strTrackDescription);
				objWBTColumns.setWbtswbtscode(strWbtscode);
			}			
		}
		m_strChncode = strChncode;
		m_listWBTColumns = listWBTColumns;
	}
	
	public void setTrack(WaybilltrackColumns objWBTColumns, 
			String strChncode) {
		List<WaybilltrackColumns> listWBTColumns = new ArrayList<WaybilltrackColumns>();
		listWBTColumns.add(objWBTColumns);
		setTracks(listWBTColumns, strChncode);
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listWBTColumns == null || m_listWBTColumns.size() < 1) 
			return;
		SaveWBTrackTransaction objSWBTTrans = new SaveWBTrackTransaction();
		objSWBTTrans.setParam(m_listWBTColumns, "0", false);
		objSWBTTrans.transaction(objSession);
		// ��û�з���ɱ�׼�켣,�ҹ켣ӳ����в����ڴ�ԭʼ�켣ʱ��������¹켣���켣ӳ����С�
		for (int i = 0; i < m_listWBTColumns.size(); i++) {
			WaybilltrackColumns objWBTColumns = m_listWBTColumns.get(i);
			String strWbtscode = objWBTColumns.getWbtswbtscode();
			if (!StringUtility.isNull(strWbtscode)) 
				continue;
			String strTrackDescription = objWBTColumns.getWbtwbtdescription();
			if (TrackWayBillDemand.existMapTrack(m_strChncode, strTrackDescription) || 
					ChanneltrackmappingDemand.isExistsOrigintrack(strTrackDescription)) {
				continue;
			}
			SaveChannelTrackMapping objSCTMapping = new SaveChannelTrackMapping();
			objSCTMapping.setParam(m_strChncode, 
					objWBTColumns.getWbtwbtdescription());
			objSCTMapping.transaction(objSession);
		}
	}
}
