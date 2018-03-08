package kyle.leis.fs.businesslog.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.businesslog.da.BusinesslogColumns;
import kyle.leis.fs.businesslog.dax.BusinesslogDemand;
import kyle.leis.hi.TfsBusinesslog;
import net.sf.hibernate.Session;

public class SaveBusinesslogTransaction extends AbstractTransaction {

	private String m_strBlBusinesscode;
	public String getM_strBlBusinesscode() {
		return m_strBlBusinesscode;
	}

	private BusinesslogColumns m_businesslogCol;
	
	public void setParam(BusinesslogColumns businesslogCol)
	{
		this.m_businesslogCol = businesslogCol;
	}
	
	public void transaction(Session objSession) throws Exception {
		// TODO Auto-generated method stub
		if(m_businesslogCol==null) return;
		TfsBusinesslog objTFsBusinesslog = new TfsBusinesslog();
		BusinesslogDemand.setBusinesslogByColumns(objTFsBusinesslog, m_businesslogCol, objSession);
		objSession.save(objTFsBusinesslog);
		
		m_strBlBusinesscode = objTFsBusinesslog.getBlBusinesscode();
		
	}

}
