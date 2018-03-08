package kyle.leis.fs.cachecontainer.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.leis.es.price.expressprice.bl.ExpressPrice;
import kyle.leis.fs.cachecontainer.bl.CacheContainer;
import kyle.leis.fs.cachecontainer.dax.ProductkindColumnsEX;
import kyle.leis.fs.cachecontainer.dax.ProductkindQueryEX;
import kyle.leis.fs.cachecontainer.sv.CacheContainerService;

public class CacheContainerTest {
	public static void main(String[] args) {
		try {
			//getCacheContainer();
//			getTotalCacheData();
			
			//System.out.println(addCacheDate());
			queryProductkind();
			
		}catch(Exception objEX) {
			System.out.println(objEX.getMessage());
		}
	}
	
	public static void getCacheContainer() throws Exception {
		CacheContainer objCacheContainer = new CacheContainer(); 
		List objList = objCacheContainer.getCacheContainer("LEDIS");
		System.out.println(objList);
	}
	
	public static void getTotalCacheData() throws Exception {
		/*
		CacheContainer objCacheContainer = new CacheContainer(); 
		List objList = objCacheContainer.getTotalCacheData("Operator", "1900");
		System.out.println(objList);*/
		String str = "District~`~@~#1900~`~@~#";
		Decoder objPD = new Decoder(str);
		CacheContainerService objCCS = new CacheContainerService();
		objCCS.getTotalCacheData(objPD);
	}
	
	public static String addCacheDate() throws Exception
	{
		String str = "InformationSystemKind~`InformationSystemKind~`20100916~`N~`SELECT new kyle.leis.fs.cachecontainer.da.InfomationsystemkindColumns(isk.iskCode,isk.iskName,isk.iskEname) FROM TdiInfomationsystemkind as isk~`LEDIS~`~`~@~#";
		Decoder objPD = new Decoder(str);
		CacheContainerService objCSS = new CacheContainerService();
		return objCSS.addCacheDate(objPD);
	}
	
	public static void queryProductkind() throws Exception {
		ProductkindQueryEX objPKQ = new ProductkindQueryEX();
		objPKQ.setUseCachesign(true);
		List<ProductkindColumnsEX> listResults = (List<ProductkindColumnsEX>)objPKQ.getResults();
		
		String strCocode = "1";
		String strDtcode = "719";
		String strEecode = "1";
		ExpressPrice e = new ExpressPrice();
		HashSet<String> hashset = e.searchProductKind(strCocode, strDtcode, strEecode);
		
		List<ProductkindColumnsEX> list = new ArrayList<ProductkindColumnsEX>();
		Iterator<ProductkindColumnsEX> iterator = listResults.iterator();
		while(iterator.hasNext()){
			ProductkindColumnsEX objPkc = (ProductkindColumnsEX)iterator.next(); 
			//System.out.println((objPkc).getPkcode()+"====");
			Iterator<String> hashIter =hashset.iterator();
			while(hashIter.hasNext()){
				String s = hashIter.next();
				if(s.toString().equals((objPkc).getPkcode().toString())){
					list.add(objPkc);	
				}	
			}		
	    }
		Collections.sort(list);
		for (ProductkindColumnsEX ex : list) {
			System.out.println(ex.getPkename());
		}
	}	
}
