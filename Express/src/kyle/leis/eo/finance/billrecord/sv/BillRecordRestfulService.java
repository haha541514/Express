package kyle.leis.eo.finance.billrecord.sv;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import kyle.leis.eo.finance.billrecord.da.BillrecordColumns;
import kyle.leis.eo.finance.billrecord.da.BillrecordCondition;
import kyle.leis.eo.finance.billrecord.dax.BillRecordDemand;
import kyle.leis.eo.finance.billrecord.dax.BillrecordColumnsexRestful;
import kyle.leis.eo.finance.dunning.dax.DunningDemand;


@Path("/bill")
public class BillRecordRestfulService {
	@SuppressWarnings({ "static-access", "unchecked" })
	@GET
    @Path("/{id}")
    @Produces({ "application/json; charset=UTF-8;"})
    public Response FinanceQuery(@PathParam("id") String Id,
    			@QueryParam("startcreatedate") String StartCreateDate,
    			@QueryParam("endcreatedate")   String EndCreateDate,
    			@QueryParam("cocode")   String cocode,
    			@QueryParam("brlablecode") String brlablecode) throws Exception {      
	    if("FinanceQuery".equals(Id)){
	    	BillRecordDemand bd=new BillRecordDemand();
	    	BillrecordCondition bct=new BillrecordCondition();
	    	
	    	bct.setStartoccurdate(StartCreateDate);
	    	bct.setEndoccurdate(EndCreateDate);
	    	bct.setCocode(cocode);
	    	bct.setBrlablecode(brlablecode);
	    	
	    	
	    	List<BillrecordColumns> pdls=bd.query(bct);
	    	
    	
	    	List<BillrecordColumnsexRestful> listPredictex = new ArrayList<BillrecordColumnsexRestful>();
	    	for(BillrecordColumns pc:pdls){ 
	    		BillrecordColumnsexRestful Predictex = new BillrecordColumnsexRestful();
	    		Predictex.setBrcreatedate(pc.getBrbrcreatedate());
	    		Predictex.setBroccurdate(pc.getBrbroccurdate());
	    		Predictex.setBrremark(pc.getBrbrremark());
	    		Predictex.setBrtotal(pc.getBrbrtotal());
	    		Predictex.setCkcode(pc.getCkckcode());
	    		Predictex.setBrscode(pc.getBrsbrscode());
	    		
		    	listPredictex.add(Predictex);
	    	}	    	
	    	if(listPredictex.size()<1){
	    		  HashMap hm=new HashMap();		   
				    hm.put("errorinfo", "-1");
				    hm.put("errcode", "该条件下暂时无数据");
				    		
	              return Response.ok(hm).build();
	    	
	    	}
	    	
	    	return Response.ok(listPredictex).build();
        }else{
        	  HashMap hm=new HashMap();		   
			    hm.put("errorinfo", "-1");
			    hm.put("errcode", "访问失败");
			    		
              return Response.ok(hm).build();
        }	
	}
}
