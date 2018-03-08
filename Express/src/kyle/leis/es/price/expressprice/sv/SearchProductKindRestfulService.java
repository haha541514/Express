package kyle.leis.es.price.expressprice.sv;

import java.util.ArrayList;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import kyle.leis.es.price.expressprice.bl.ExpressPrice;
import kyle.leis.es.price.expressprice.dax.SearchProductKindRestfulParam;

@Path("/Product")
public class SearchProductKindRestfulService {
	
	@GET
    @Path("/{id}")
    @Produces({ "application/json; charset=UTF-8;"})
    public Response ProductQuery(@PathParam("id") String Id,		
    			@QueryParam("cocode")   String cocode) throws Exception {    
		
		List<SearchProductKindRestfulParam> ls=new ArrayList<SearchProductKindRestfulParam>();
		HashSet<String> pdls=null;
	    if("ProductQuery".equals(Id)){
	    	ExpressPrice ep=new ExpressPrice();
	    
	    	pdls=ep.searchProductKind(cocode, "", "");
	    	Iterator<String> iterator=pdls.iterator();
			while(iterator.hasNext()){	
				String product=iterator.next();		
				SearchProductKindRestfulParam sp=new SearchProductKindRestfulParam();
				sp.setPkcode(product);
				ls.add(sp);
    	
			}
	    	
	    	if(pdls.size()<1){
	    		 return Response.ok("该条件下暂时无数据").build();
	    	}else{
	    		return Response.ok(ls).build();}	
	    
	    } else{
         System.out.println("error!!!!");
         return Response.ok("访问出错").build();
        }	
	}
}
