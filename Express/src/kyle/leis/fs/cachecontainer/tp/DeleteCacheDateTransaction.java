package kyle.leis.fs.cachecontainer.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;

public class DeleteCacheDateTransaction extends AbstractTransaction {

	private String [] m_arrayCccode;
	
	public void setParame(String[] arrayCccode)
	{
		this.m_arrayCccode = arrayCccode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if(m_arrayCccode.length <1) return;
		for(int i=0;i<m_arrayCccode.length;i++)
		{
			objSession.delete("from TfsCachecontainer cc where cc.ccCode = '"
					+ m_arrayCccode[i]
					+ "'");
		}
	}

}
