package kyle.leis.fs.cachecontainer.bl;

import java.util.List;

import kyle.common.dbaccess.session.HSessionFactory;
import kyle.leis.fs.cachecontainer.da.CachecontainerColumns;
import kyle.leis.fs.cachecontainer.da.CachecontainerCondition;
import kyle.leis.fs.cachecontainer.da.CachecontainerQuery;
import kyle.leis.fs.cachecontainer.dax.CacheContainerDemand;
import kyle.leis.fs.cachecontainer.dax.IncrementCacheDataResult;
import kyle.leis.fs.cachecontainer.tp.DeleteCacheDateTransaction;
import kyle.leis.fs.cachecontainer.tp.SaveCacheDateTransaction;
import kyle.leis.hi.TfsCachecontainer;
import kyle.leis.hi.TfsCachecontainerPK;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

public class CacheContainer {
	/**
	 * 获得缓冲容器
	 * @return
	 * @throws Exception
	 */
	public List getCacheContainer(String strIsk_code) throws Exception {
		// 获得缓冲容器
		CachecontainerQuery objCachecontainerQ = new CachecontainerQuery();
		CachecontainerCondition obCachecontainerC = new CachecontainerCondition();
		obCachecontainerC.setIskcode(strIsk_code);
		objCachecontainerQ.setCondition(obCachecontainerC);
		return objCachecontainerQ.getResults();
	}
	/**
	 * 完全更新
	 * @param strCC_Code
	 * @param strCC_BatchNumber
	 * @return
	 * @throws Exception
	 */
	public List getTotalCacheData(String strCC_Code, 
			String strCC_BatchNumber,
			String strIskcode) throws Exception {
		// 获得缓冲容器的批次号
		Session objSession = HSessionFactory.getSession();
		TfsCachecontainerPK objTCCPK = new TfsCachecontainerPK();
		objTCCPK.setCcCode(strCC_Code);
		objTCCPK.setIskCode(strIskcode);
		TfsCachecontainer objCacheContainer = (TfsCachecontainer)objSession.load(TfsCachecontainer.class, objTCCPK);
		if (objCacheContainer == null) return null;
		String strServerBatchNumber = objCacheContainer.getCcBatchnumber();
		// 批次不一致则刷新缓冲容器的数据
		if (!strCC_BatchNumber.equals(strServerBatchNumber)) {
			Query objHQuery = objSession.createQuery(objCacheContainer.getCcSql());
			return objHQuery.list();
		}
		return null;
	}
	
	
	public IncrementCacheDataResult getIncrementCacheData(String strCC_Code, 
			String strCC_BatchNumber,
			String strIskcode) throws Exception {
		// 获得缓冲容器的批次号
		IncrementCacheDataResult objICDResult = new IncrementCacheDataResult();
		Session objSession = HSessionFactory.getSession();
		TfsCachecontainerPK objTCCPK = new TfsCachecontainerPK();
		objTCCPK.setCcCode(strCC_Code);
		objTCCPK.setIskCode(strIskcode);
		TfsCachecontainer objCacheContainer = (TfsCachecontainer)objSession.load(TfsCachecontainer.class, objTCCPK);
		if (objCacheContainer == null) return null;
		String strServerBatchNumber = objCacheContainer.getCcBatchnumber();
		// 批次不一致则刷新缓冲容器的数据
		if (strCC_BatchNumber.compareTo(strServerBatchNumber) < 0) {
			Query objHQuery = objSession.createQuery(objCacheContainer.getCcSql());
			objHQuery.setString(0, strCC_BatchNumber);
			objHQuery.setString(1, strServerBatchNumber);
			
			objICDResult.setCacheData(objHQuery.list());
			objICDResult.setServerBatchNumber(strServerBatchNumber);
			
			return objICDResult;
		}
		return null;
	}
	
	public CachecontainerColumns addCacheDate(CachecontainerColumns objCachecontainerCol) throws Exception
	{
		SaveCacheDateTransaction objSaveCacheDateTrans = new SaveCacheDateTransaction();
		objSaveCacheDateTrans.setParams(objCachecontainerCol);
		objSaveCacheDateTrans.execute();
		return CacheContainerDemand.queryByCccode(objSaveCacheDateTrans.getNewCc_code());
	}
	
	public void deleteCacheDate(String [] arrayCccode) throws Exception
	{
		DeleteCacheDateTransaction objDeleteCacheDateTrans = new DeleteCacheDateTransaction();
		objDeleteCacheDateTrans.setParame(arrayCccode);
		objDeleteCacheDateTrans.execute();
	}
}
