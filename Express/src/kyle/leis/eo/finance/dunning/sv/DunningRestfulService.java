package kyle.leis.eo.finance.dunning.sv;

import java.util.ArrayList;




import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import kyle.leis.eo.finance.dunning.dax.DunningColumnsexRestful;
import kyle.leis.eo.finance.dunning.dax.DunningDemand;
import kyle.leis.eo.finance.dunning.dax.FinanceReportResults;


@Path("/Report")
public class DunningRestfulService {
	@SuppressWarnings({ "static-access", "unchecked" })
	@GET
    @Path("/{id}")
    @Produces({ "application/json; charset=UTF-8;"})
    public Response FinanceQuery(@PathParam("id") String Id,
    			@QueryParam("startdate") String startdate,
    			@QueryParam("enddate")   String enddate,
    			@QueryParam("cocode")   String cocode,
    			@QueryParam("ckcode") String ckcode) throws Exception {      
	    if("QueryFinanceReport".equals(Id)){
	    	DunningDemand dd=new DunningDemand();
	    	
	    	
	    	
	    	List<FinanceReportResults> pdls=dd.queryFinanceReport(cocode, startdate, enddate, "", ckcode);
	    	
    	
	    	List<DunningColumnsexRestful> listPredictex = new ArrayList<DunningColumnsexRestful>();
	    	for(FinanceReportResults pc:pdls){ 
	    		DunningColumnsexRestful Predictex = new DunningColumnsexRestful();
	    		Predictex.setOccurDate(pc.getOccurDate());
	    		Predictex.setRemark(pc.getRemark());
	    		Predictex.setBillTotal(pc.getBillTotal());
	    		Predictex.setCashTotal(pc.getCashTotal());
	    		Predictex.setBalanceTotal(pc.getBalanceTotal());
	    		
		    	listPredictex.add(Predictex);
	    	}	    	
	    	if(listPredictex.size()<1){
	    		 return Response.ok("该条件下暂时无数据").build();
	    	}
	    	
          return Response.ok(listPredictex).build();
        }else{
         System.out.println("error!!!!");
         return Response.ok("访问出错").build();
        }	
	}
}
