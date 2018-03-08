package kyle.leis.es.company.shipperblacklist.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;

public class DeleteShipperbalcklistTrans extends AbstractTransaction {
	
	private String[] m_astrSblcode;
	
	public void setParam(String[] astrSblcode)
	{
		this.m_astrSblcode = astrSblcode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if(m_astrSblcode == null || m_astrSblcode.length<1) return;
		for(int i=0;i<m_astrSblcode.length;i++)
		{
			objSession.delete("from TcoShipperblacklist sbl where sbl.sblCode = "+ m_astrSblcode[i]);
		}
	}
}
