package kyle.leis.eo.operation.transportwaybill.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TopBatchwaybill;
import kyle.leis.hi.TopCorewaybill;
import kyle.leis.hi.TopTransportwaybill;
import kyle.leis.hi.TopTransportwaybillvalue;
import kyle.leis.hi.TopTransportwaybillvaluePK;

public class AddTransportValueTrans extends AbstractTransaction {
	private String m_strTwbid;
	private TopTransportwaybill m_objTTransportwaybill;
	private String[] m_astrBwcode;
	private String[] m_astrBaglabelcode;
	private String[] m_astrCwcode;
	
	public void setParam(TopTransportwaybill objTTransportwaybill,
			String[] astrBwcode,
			String[] astrBaglabelcode,
			String[] astrCwcode) throws Exception
	{
		m_astrBaglabelcode = astrBaglabelcode;
		m_astrCwcode = astrCwcode;
		setParam(objTTransportwaybill,astrBwcode);
	}
	
	public void setParam(String strTwbid, String[] astrBwcode) {
		m_strTwbid = strTwbid;
		m_astrBwcode = astrBwcode;
	}
	
	public void setParam(TopTransportwaybill objTTransportwaybill, 
			String[] astrBwcode) throws Exception {
		m_objTTransportwaybill = objTTransportwaybill;
		m_astrBwcode = astrBwcode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (!StringUtility.isNull(m_strTwbid)) {
			m_objTTransportwaybill = (TopTransportwaybill)objSession.load(TopTransportwaybill.class, 
					Long.parseLong(m_strTwbid));			
		}
		m_strTwbid = String.valueOf(m_objTTransportwaybill.getTwbId());
		// охи╬ЁЩ
		execute(objSession, "delete from T_op_Transportwaybillvalue twbv where twbv.twb_id = " +
				m_strTwbid);
		if (m_astrBwcode == null || m_astrBwcode.length < 1) return;
		for (int i = 0; i < m_astrBwcode.length; i++) {
			TopTransportwaybillvalue objTTWBV = new TopTransportwaybillvalue();
			TopBatchwaybill objTopBatchwaybill = null;
			if (!StringUtility.isNull(m_astrBwcode[i])) {
				objTopBatchwaybill = (TopBatchwaybill)objSession.load(TopBatchwaybill.class, 
						Long.parseLong(m_astrBwcode[i]));
			}
			
			TopTransportwaybillvaluePK objTTWBVPK = new TopTransportwaybillvaluePK();
//			objTTWBVPK.setBwCode(Long.parseLong(m_astrBwcode[i]));
			objTTWBVPK.setTwbvId(Long.valueOf(i));
			objTTWBVPK.setTwbId(m_objTTransportwaybill.getTwbId());
			objTTWBV.setComp_id(objTTWBVPK);
			
			if(m_astrBaglabelcode.length > 0 && !StringUtility.isNull(m_astrBaglabelcode[i]))
				objTTWBV.setTwbvBaglabelcode(m_astrBaglabelcode[i]);
			
			if(m_astrCwcode != null && 
					m_astrCwcode.length > 0 && 
					!StringUtility.isNull(m_astrCwcode[i])) {
				TopCorewaybill objtopCorewaybill = (TopCorewaybill)objSession.load(TopCorewaybill.class, 
						Long.parseLong(m_astrCwcode[i]));
				objTTWBV.setTopCorewaybill(objtopCorewaybill);
			}
			
			objTTWBV.setTopBatchwaybill(objTopBatchwaybill);
			objTTWBV.setTopTransportwaybill(m_objTTransportwaybill);
			
			objSession.save(objTTWBV);
		}
	}

}
