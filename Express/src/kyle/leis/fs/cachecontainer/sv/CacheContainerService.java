package kyle.leis.fs.cachecontainer.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.cachecontainer.bl.CacheContainer;
import kyle.leis.fs.cachecontainer.da.CachecontainerColumns;
import kyle.leis.fs.cachecontainer.da.CachecontainerCondition;
import kyle.leis.fs.cachecontainer.dax.CacheContainerDemand;
import kyle.leis.fs.cachecontainer.dax.IncrementCacheDataResult;
import kyle.leis.fs.dictionary.dictionarys.da.CustomscargoCondition;
import kyle.leis.fs.dictionary.dictionarys.dax.DictionaryDemand;

public class CacheContainerService extends AService {
	public String getCacheContainer(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strIsk_code = (String)objPD.getParameter(0, String.class);
		CacheContainer objCacheContainer = new CacheContainer();
		List listCacheContainer = objCacheContainer.getCacheContainer(strIsk_code);
		// 编码
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listCacheContainer);
		return objEncode.toString();
	}

	public String getTotalCacheData(Decoder objPD) throws Exception {
		int iParameter = objPD.getParameterCount();
		
		String strCC_Code = (String)objPD.getParameter(0, String.class);
		String strCC_BatchNumber = (String)objPD.getParameter(1, String.class);
		String strIskcode = "LEDIS";
		if (iParameter == 3)
			strIskcode = (String)objPD.getParameter(2, String.class);
		
		
		CacheContainer objCacheContainer = new CacheContainer();
		List listCacheContainer = objCacheContainer.getTotalCacheData(strCC_Code, strCC_BatchNumber, strIskcode);
		// 编码
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listCacheContainer);
		return objEncode.toString();
	}
	
	/**
	 * 增量缓冲
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String getIncrementCacheData(Decoder objPD) throws Exception {
		int iParameter = objPD.getParameterCount();
		
		String strCC_Code = (String)objPD.getParameter(0, String.class);
		String strCC_BatchNumber = (String)objPD.getParameter(1, String.class);
		String strIskcode = "LEDIS";
		if (iParameter == 3)
			strIskcode = (String)objPD.getParameter(2, String.class);
		
		CacheContainer objCacheContainer = new CacheContainer();
		IncrementCacheDataResult objICDResult = objCacheContainer.getIncrementCacheData(strCC_Code, 
				strCC_BatchNumber,
				strIskcode);
		if (objICDResult == null) return "";
		// 编码
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objICDResult.getCacheData());
		objEncode.addParameter(objICDResult.getServerBatchNumber());
		return objEncode.toString();
	}	
	
	/**
	 * 获得问题类型
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryIssuetype(Decoder objPD) throws Exception {
		List objList = CacheContainerDemand.queryIssuetype();
		// 编码
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}
	
	/**
	 * 查询海关货物名称以及编码
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryCustomsCargo(Decoder objPD) throws Exception {
		CustomscargoCondition objCCondition = (CustomscargoCondition)objPD.getParameter(0, 
				CustomscargoCondition.class);
		List objList = DictionaryDemand.queryCustomsCargo(objCCondition);
		// 编码
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}
	
	/**
	 * 查询缓存数据
	 */
	public String query(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		CachecontainerCondition objCachecontainerCon = (CachecontainerCondition)objPD.getParameter(0, CachecontainerCondition.class);
		List objList = CacheContainerDemand.query(objCachecontainerCon);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	/**
	 * 添加(修改)缓存数据
	 */
	public String addCacheDate(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		CachecontainerColumns objCachecontainerCol = (CachecontainerColumns) objPD.getParameter(0, CachecontainerColumns.class);
		CacheContainer objCacheContainer = new CacheContainer();
		CachecontainerColumns objReturn = objCacheContainer.addCacheDate(objCachecontainerCol);
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objReturn);
		return objEncode.toString();
	}
	
	/**
	 * 删除缓存数据
	 */
	public String deleteCacheDate(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		String [] arrayCccode = (String []) objPD.getParameterArray(0, String.class);
		CacheContainer objCacheContainer = new CacheContainer();
		objCacheContainer.deleteCacheDate(arrayCccode);
		return "";
	}
	
	public String queryCorporationEE(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strEestructurecode = (String)objPD.getParameter(0, String.class);
		
		List listResults = CacheContainerDemand.queryCorporationEE(strEestructurecode);
		// 编码
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}
}
