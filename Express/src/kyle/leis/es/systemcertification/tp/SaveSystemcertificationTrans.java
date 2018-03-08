package kyle.leis.es.systemcertification.tp;


import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.systemcertification.da.SystemcertificationColumns;
import kyle.leis.es.systemcertification.dax.SystemCertificationDemand;
import kyle.leis.hi.TesSystemcertification;
import net.sf.hibernate.Session;

public class SaveSystemcertificationTrans extends AbstractTransaction {

	private SystemcertificationColumns m_objSystemcertificationCol;
	private String m_strOperId;
	private String m_strNewscId;
	
	public String getNewscId()
	{
		return this.m_strNewscId;
	}
	
	public void setParam(String strSchdserialnumber,
			String strScmacaddress,
			String strScipaddress,
			String strOperId)
	{
		SystemcertificationColumns objSystemcertificationCol = new SystemcertificationColumns();
		objSystemcertificationCol.setScschdserialnumber(strSchdserialnumber);
		objSystemcertificationCol.setScscmacaddress(strScmacaddress);
		objSystemcertificationCol.setScscipaddress(strScipaddress);
		this.setParam(objSystemcertificationCol, strOperId);
	}
	
	public void setParam(SystemcertificationColumns objSystemcertificationCol,
			String strOperId)
	{
		this.m_objSystemcertificationCol = objSystemcertificationCol;
		this.m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if(m_objSystemcertificationCol == null || StringUtility.isNull(m_strOperId)) return;
		
		TesSystemcertification objTesSystemcertification = new TesSystemcertification();
		SystemCertificationDemand.setSystemcertificationByColumns(objTesSystemcertification, m_objSystemcertificationCol, m_strOperId,objSession);
		objSession.save(objTesSystemcertification);
		this.m_strNewscId = String.valueOf(objTesSystemcertification.getScId());
	}

}
