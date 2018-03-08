package kyle.leis.fs.cachecontainer.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.cachecontainer.da.CachecontainerColumns;
import kyle.leis.fs.cachecontainer.dax.CacheContainerDemand;
import kyle.leis.hi.TfsCachecontainer;
import kyle.leis.hi.TfsCachecontainerPK;
import net.sf.hibernate.Session;

public class SaveCacheDateTransaction extends AbstractTransaction {

	private String m_strNewCc_code;
	private CachecontainerColumns m_objCachecontainerCol;
	
	public String getNewCc_code()
	{
		return this.m_strNewCc_code;
	}
	
	public void setParams(CachecontainerColumns objCachecontainerCol)
	{
		this.m_objCachecontainerCol = objCachecontainerCol;
	}
	
	public void transaction(Session objSession) throws Exception {
		if(m_objCachecontainerCol == null) return;
		
		TfsCachecontainer objTfsCachecontainer;
		TfsCachecontainerPK objTCCPK = new TfsCachecontainerPK();
		objTCCPK.setIskCode("LEDIS");
		objTCCPK.setCcCode(m_objCachecontainerCol.getCccccode());
		if(CacheContainerDemand.queryByCccode(m_objCachecontainerCol.getCccccode()) == null)
		{
			objTfsCachecontainer = new TfsCachecontainer();
			objTfsCachecontainer.setComp_id(objTCCPK);
		}
		else {
			objTfsCachecontainer = (TfsCachecontainer)objSession.load(TfsCachecontainer.class, objTCCPK);
		}
		
		CacheContainerDemand.setCacheByColumns(m_objCachecontainerCol, objTfsCachecontainer);
		objSession.save(objTfsCachecontainer);
		this.m_strNewCc_code = objTfsCachecontainer.getComp_id().getCcCode();
	}

}
