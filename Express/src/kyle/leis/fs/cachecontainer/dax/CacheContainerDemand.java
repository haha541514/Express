package kyle.leis.fs.cachecontainer.dax;

import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.leis.fs.cachecontainer.da.CachecontainerColumns;
import kyle.leis.fs.cachecontainer.da.CachecontainerCondition;
import kyle.leis.fs.cachecontainer.da.CachecontainerQuery;
import kyle.leis.fs.cachecontainer.da.CorporationeeQuery;
import kyle.leis.fs.cachecontainer.da.IssuetypeQuery;
import kyle.leis.fs.cachecontainer.da.PricegroupColumns;
import kyle.leis.fs.cachecontainer.da.PricegroupQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiInfomationsystemkindDC;
import kyle.leis.hi.TfsCachecontainer;

public class CacheContainerDemand {
	public static List queryIssuetype() throws Exception {
		IssuetypeQuery objIssuetypeQuery = new IssuetypeQuery();
		return objIssuetypeQuery.getResults();
	}
	
	/**
	 * 获得通用价格组
	 * @return
	 * @throws Exception
	 */
	public static String getCommonpricegroup() throws Exception {
		PricegroupQuery objPQuery = new PricegroupQuery();
		objPQuery.setUseCachesign(true);
		objPQuery.setPgcommonsign("Y");
		List objList = objPQuery.getResults();
		if (objList != null && objList.size() > 0)
			return ((PricegroupColumns)objList.get(0)).getPgpgcode();
		return null;
	}
	
	
	public static List query(CachecontainerCondition objCachecontainerCon) throws Exception
	{
		CachecontainerQuery objCachecontainerQue = new CachecontainerQuery();
		objCachecontainerQue.setCondition(objCachecontainerCon);
		return objCachecontainerQue.getResults();
	}
	
	public static List queryCorporationEE(String strEestructurecode) throws Exception {
		CorporationeeQuery objCEEQ = new CorporationeeQuery();
		objCEEQ.setUseCachesign(true);
		objCEEQ.setEestructurecode(strEestructurecode);
		return objCEEQ.getResults();
	}
	
	
	public static CachecontainerColumns queryByCccode(String strCccode) throws Exception
	{
		CachecontainerCondition objCachecontainerCon = new CachecontainerCondition();
		objCachecontainerCon.setCccode(strCccode);
		List objList = query(objCachecontainerCon); 
		if(!CollectionUtility.isNull(objList) && objList.size() == 1)
			return (CachecontainerColumns)objList.get(0);
		return null;
	}
	
	public static boolean isExist(String strCccode) throws Exception
	{
		CachecontainerCondition objCachecontainerCon = new CachecontainerCondition();
		objCachecontainerCon.setCccode(strCccode);
		if(query(objCachecontainerCon).size()>0)
			return true;
		return false;
	}
	public static void setCacheByColumns(CachecontainerColumns objCachecontainerCol,TfsCachecontainer objTfsCachecontainer) throws Exception
	{
		objTfsCachecontainer.setCcBatchnumber(objCachecontainerCol.getCcccbatchnumber());
		objTfsCachecontainer.setCcName(objCachecontainerCol.getCcccname());
		objTfsCachecontainer.setCcSql(objCachecontainerCol.getCcccsql());
		objTfsCachecontainer.setCcTotalsign(objCachecontainerCol.getCccctotalsign());
		objTfsCachecontainer.setTdiInfomationsystemkind(TdiInfomationsystemkindDC.loadByKey(objCachecontainerCol.getIskiskcode()));
	}
}
