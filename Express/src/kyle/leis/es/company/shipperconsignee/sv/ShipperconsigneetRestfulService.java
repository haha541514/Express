package kyle.leis.es.company.shipperconsignee.sv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeColumns;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeCondition;
import kyle.leis.es.company.shipperconsignee.dax.ShipperconsigneeColumnsexRestful;
import kyle.leis.es.company.shipperconsignee.dax.ShipperconsigneeDemand;

@Path("/Shipper")
public class ShipperconsigneetRestfulService {
	@SuppressWarnings({ "static-access", "unchecked" })
	@GET
    @Path("/{id}")
    @Produces({ "application/json; charset=UTF-8;"})
    public Response ShipperQuery(@PathParam("id") String Id,
    			@QueryParam("cocode") String cocode) throws Exception {      
	    if("ShipperQuery".equals(Id)){
	    	ShipperconsigneeDemand pd=new ShipperconsigneeDemand();
	    	ShipperconsigneeCondition sc=new ShipperconsigneeCondition();
	    	sc.setCmcocode(cocode);
	    	
	    	List<ShipperconsigneeColumns> pdls=pd.query(sc);
	    	
    	
	    	List<ShipperconsigneeColumnsexRestful> listPredictex = new ArrayList<ShipperconsigneeColumnsexRestful>();
	    	for(ShipperconsigneeColumns pc:pdls){ 
	    		ShipperconsigneeColumnsexRestful Predictex = new ShipperconsigneeColumnsexRestful();
	    		Predictex.setSclabelcode(pc.getScsclabelcode());
	    		Predictex.setScname(pc.getScscname());
	    		Predictex.setSccompanyname(pc.getScsccompanyname());
	    		Predictex.setScaddress1(pc.getScscaddress1());
	    		Predictex.setScaddress2(pc.getScscaddress2());
	    		Predictex.setScaddress3(pc.getScscaddress3());
	    		Predictex.setScpostcode(pc.getScscpostcode());
	    		Predictex.setSccitycode(pc.getScsccitycode());
	    		Predictex.setSctelephone(pc.getScsctelephone());
	    		Predictex.setScfax(pc.getScscfax());
	    		
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
