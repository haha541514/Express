package kyle.leis.fs.dictionary.customscargo.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TdiCustomscargo;
import net.sf.hibernate.Session;

public class DeleteCustomscargoTranscation extends AbstractTransaction{
	public String m_ccCode;
	
	public void setParam(String ccCode){
		m_ccCode=ccCode;
	}

	public void transaction(Session objSession) throws Exception {
		// TODO Auto-generated method stub
			if (StringUtility.isNull(m_ccCode)) return;
			
			//System.out.println(m_ccCode.get(i).toString()+"fefwerw"+Long.parseLong(m_ccCode.get(i).toString()));
			
			TdiCustomscargo objTdiCustomscargo = (TdiCustomscargo)objSession.load(TdiCustomscargo.class, 
					m_ccCode);	
			objSession.delete(objTdiCustomscargo);
	}

}
