package kyle.leis.eo.operation.corewaybill.blx;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.customerservice.track.bl.Track;
import kyle.leis.eo.operation.housewaybill.bl.PredictOrderEX;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictColumns;

public class CoreWaybillPrintThread extends Thread {

	private String[] m_astrCwcode; 
	private String m_opId;
	private WaybillforpredictColumns[] m_awayBills	;
	
	public CoreWaybillPrintThread(String[] astrCwcode, 
			String opId, 
			WaybillforpredictColumns[] awayBills) {
		m_astrCwcode = astrCwcode;
		m_opId = opId;
		m_awayBills = awayBills;
	}
	
	public void run() {
		try {
			if (m_astrCwcode == null || m_astrCwcode.length < 1)
				return;
			if (StringUtility.isNull(m_opId))
				m_opId = "0";
			// ÐÞ¸Ä×´Ì¬
			for (int j = 0; j < m_astrCwcode.length; j++) {
				WaybillforpredictColumns objWFPColumns = m_awayBills[j];
				if ("CTS".equals(objWFPColumns.getCwscws_code()) ||
						"CHD".equals(objWFPColumns.getCwscws_code())) {
					new PredictOrderEX().modifyCorewaybillStatus(
							m_astrCwcode[j], "CHP", m_opId);
					// Ôö¼Ó¹ì¼£
					Track objTrack = new Track();
					objTrack.addSingleTrack(m_astrCwcode[j], 
							"719", 
							"TS",
							m_opId, 
							DateFormatUtility.getStandardSysdate());
				}
			
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}	
	
}
