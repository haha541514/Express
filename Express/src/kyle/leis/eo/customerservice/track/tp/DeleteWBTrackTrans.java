package kyle.leis.eo.customerservice.track.tp;

import java.util.ArrayList;
import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.customerservice.track.da.WaybilltrackColumns;
import kyle.leis.eo.customerservice.track.dax.TrackDemand;
import net.sf.hibernate.Session;

public class DeleteWBTrackTrans extends AbstractTransaction {
	private List<WaybilltrackColumns> m_listRetainedTrack;
	private String[] m_astrWbtid;
	private String m_strCwcode;
	
	public void setParam(String[] astrWbtid, 
			String strCwcode) throws Exception {
		m_astrWbtid = astrWbtid;
		m_strCwcode = strCwcode;
		
		List listOldWbtrack = TrackDemand.loadTracks(strCwcode);
		if (listOldWbtrack == null || listOldWbtrack.size() < 1)
			return;
		m_listRetainedTrack = new ArrayList<WaybilltrackColumns>();
		for (int i = 0; i < listOldWbtrack.size(); i++) {
			WaybilltrackColumns objWBTColumns = (WaybilltrackColumns)listOldWbtrack.get(i);
			if (TrackDemand.isTrackInArrays(objWBTColumns, m_astrWbtid))
				m_listRetainedTrack.add(objWBTColumns);
		}
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_astrWbtid == null || m_astrWbtid.length < 1)
			return;
		// É¾³ý¹ì¼£
		for (int i = 0; i < m_astrWbtid.length; i++) {
			execute(objSession, "delete from T_CS_WAYBILLTRACK where WBT_ID = " + 
					m_astrWbtid[i]);
		}
		// ¸üÐÂ×îÐÂ¹ì¼£×´Ì¬
		WaybilltrackColumns objLatestWBTColumns = TrackDemand.getLatestTrackColumns(m_listRetainedTrack);
		if (objLatestWBTColumns == null)
			execute(objSession, "delete from T_CS_WAYBILLBATCHTRACK where CW_CODE = " +
					m_strCwcode);
		else if(!StringUtility.isNull(objLatestWBTColumns.getWbtswbtscode()))
			execute(objSession, "update T_CS_WAYBILLBATCHTRACK wbbt " + 
					"set wbbt.WBTS_CODE = '" + 
					objLatestWBTColumns.getWbtswbtscode() + 
					"' where wbbt.CW_CODE = " + m_strCwcode);
	}

}
