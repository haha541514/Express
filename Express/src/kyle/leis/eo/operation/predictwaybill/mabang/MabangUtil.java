package kyle.leis.eo.operation.predictwaybill.mabang;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.expressprice.bl.ExpressPrice;
import kyle.leis.fs.cachecontainer.dax.ProductkindColumnsEX;
import kyle.leis.fs.cachecontainer.dax.ProductkindQueryEX;

public class MabangUtil {
	public static String getParams(String startdate,String enddate){
		StringBuffer sbStrParams = new StringBuffer();
		sbStrParams.append("{");
		if(!StringUtility.isNull(startdate)){
			sbStrParams.append("\"dateFrom\":\""+startdate+"\",");
		}

		if(!StringUtility.isNull(enddate)){
			sbStrParams.append("\"dateTo\":\""+enddate+"\",");
		}

//		if(!(params.getStatus()==null||"".equals(params.getStatus()))){
//			sbStrParams.append("\"status\":\""+params.getStatus()+"\",");
//		}
//		else{
//			sbStrParams.append("\"status\":\"-1\",");
//		}
//		if(!(params.getPage()==null||"".equals(params.getPage()))){
//			sbStrParams.append("\"page\":\""+params.getPage()+"\",");
//		}
//		else{
//			sbStrParams.append("\"page\":\"1\",");
//		}
//		if(!(params.getRowsPerPage()==null||"".equals(params.getRowsPerPage()))){
//			sbStrParams.append("\"rowsPerPage\":\""+params.getRowsPerPage()+"\",");
//		}
//		else{
//			sbStrParams.append("\"rowsPerPage\":\"100\",");
//		}
//		if(!(params.getMyNotify()==null||"".equals(params.getMyNotify()))){
			sbStrParams.append("\"myNotify\":\"1\"");
//		}
//		if(!(params.getCode()==null||"".equals(params.getCode()))){
//			sbStrParams.append("\"code\":\""+params.getCode()+"\",");
//		}
//		if(!(params.getCodes()==null||"".equals(params.getCodes()))){
//			sbStrParams.append("\"codes\":\""+params.getCodes()+"\",");
//		}
//		sbStrParams.deleteCharAt(sbStrParams.length()-1);
		sbStrParams.append("}");
		return sbStrParams.toString();
	}
	public static String getParamsServlet(String codes){
		StringBuffer sbStrParams = new StringBuffer();
		sbStrParams.append("{");
		sbStrParams.append("\"myNotify\":\"1\",");
			sbStrParams.append("\"codes\":\""+codes+"\"");
			
		sbStrParams.append("}");
		return sbStrParams.toString();
	}
	public static String getAcceptParams(ERPEntity erp){
		StringBuffer sbStrParams = new StringBuffer();
		sbStrParams.append("{");
		sbStrParams.append("\"code\":\""+erp.getCode()+"\",");
		sbStrParams.append("\"changeStatus\":\"accept\",");
		sbStrParams.append("\"supplierInnerCode\":\""+erp.getServiceNumber()+"\",");
		sbStrParams.append("\"labelPDFUrl\":{\"b10_10\":{\"ac\":\"" +
			"http://www.1001000.cc/PrintPDFLableServlet.xsv?serverewbcode=X%3E"+erp.getServiceNumber()+"%3CX\"}}");
		sbStrParams.append("}");
		return sbStrParams.toString();
	}
	public static String getExceptionParams(ERPEntity erp){
		StringBuffer sbStrParams = new StringBuffer();
		sbStrParams.append("{");
		sbStrParams.append("\"code\":\""+erp.getCode()+"\",");
		sbStrParams.append("\"changeStatus\":\"exception\",");
		sbStrParams.append("\"processMessage\":\""+erp.getErrorMessage()+"\"");
		sbStrParams.append("}");
		String params = UTF2GBK.GBK2Unicode(sbStrParams.toString());
		return params;
	}
	public static String getUrl(ApiLinkEntity al){
		int time =(int)(System.currentTimeMillis()/1000);
		String base =Base64.encode(al.getJsonParams().getBytes());
		String sign = MabangUtil.md5("api="+al.getApi()+"&apiAccountId="+al.getApiAccountId()+"&encodeParams="+base+"&timestamp="+time+al.getApiKey());
		String url =al.getMainUrl()+"?api="+al.getApi()+"&apiAccountId="+al.getApiAccountId()+"&encodeParams="+base+"&timestamp="+time+"&sign="+sign;
		return url;
	}
	public static String md5(String str) {
		StringBuffer sb = new StringBuffer(32);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(str.getBytes("UTF-8"));
			for (int i = 0; i < array.length ;i++){
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));   
			}
		} catch (Exception e) {
			System.out.println("Can not encode the string '" + str +  "' to MD5!");   
			e.printStackTrace();
			return null;   
		}
		return sb.toString();
	}
	public static String value2Json(String s)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			switch (c)
			{
			case '\"': sb.append("\\\"");break;
			case '\\': sb.append("\\\\");break;
			case '/': sb.append("\\/");break;
			case '\b': sb.append("\\b");break;
			case '\f': sb.append("\\f");break;
			case '\n': sb.append("\\n");break;
			case '\r': sb.append("\\r");break;
			case '\t': sb.append("\\t");break;
			default: sb.append(c);
			}
		}
		return sb.toString();
	}
	
	//当前用户拥有的产品列表
	public static List<ProductkindColumnsEX> getAllProduct(String strCocode ) throws Exception{
		
		ProductkindQueryEX objPKQ = new ProductkindQueryEX();
		objPKQ.setUseCachesign(true);
		List<ProductkindColumnsEX> listResults = (List<ProductkindColumnsEX>)objPKQ.getResults();
		
		if(StringUtility.isNull(strCocode)){
			return null;
		}
		String strDtcode = "719";
		String strEecode = "1";
		ExpressPrice e = new ExpressPrice();
		HashSet<String> hashset = e.searchProductKind(strCocode, strDtcode, strEecode);
		List<ProductkindColumnsEX> list = new ArrayList<ProductkindColumnsEX>();
		Iterator<ProductkindColumnsEX> iterator = listResults.iterator();
		while(iterator.hasNext()){
			ProductkindColumnsEX objPkc = (ProductkindColumnsEX)iterator.next(); 
			Iterator<String> hashIter =hashset.iterator();
			while(hashIter.hasNext()){
				String s = hashIter.next();
				if(s.toString().equals((objPkc).getPkcode().toString())){
					list.add(objPkc);	
				}	
			}		
	    }
		Collections.sort(list);
		return list;
	}
}
