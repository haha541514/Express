package kyle.leis.eo.customerservice.issue.sv;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import kyle.leis.eo.customerservice.issue.da.IssueColumns;
import kyle.leis.eo.customerservice.issue.da.IssueCondition;
import kyle.leis.eo.customerservice.issue.dax.IssueDemand;
import kyle.leis.eo.customerservice.issue.dax.IssueColumnsexRestful;


@Path("/Issue")
public class IssueQueryRestfulService {
	@SuppressWarnings({ "static-access", "unchecked" })
	@GET
    @Path("/{id}")
    @Produces({ "application/json; charset=UTF-8;"})
    public Response ProblemQuery(@PathParam("id") String Id,
    			@QueryParam("startcreatedate") String StartCreateDate,
    			@QueryParam("endcreatedate")   String EndCreateDate,
    			@QueryParam("cocode")   String cocode,
    			@QueryParam("Isuscode")   String Isuscode,
    			@QueryParam("Notisuscode") String Notisuscode,
    			@QueryParam("Isutcode")   String Isutcode,
    			@QueryParam("Cwcustomerewbcode")   String Cwcustomerewbcode) throws Exception {      
	    if("ProblemQuery".equals(Id)){
	    	IssueDemand pd=new IssueDemand();
	    	IssueCondition isd=new IssueCondition();
	    	
	    	isd.setStartmodifydate(StartCreateDate);
	    	isd.setEndmodifydate(EndCreateDate);
	    	isd.setCocode(cocode);
	    	isd.setIsuscode(Isuscode);
	    	isd.setNotisuscode(Notisuscode);
	    	isd.setIsutcode(Isutcode);
	    	isd.setCwcustomerewbcode(Cwcustomerewbcode);
	    	
	    	
	    	
	    	List<IssueColumns> pdls=pd.query(isd);
	    	
    	
	    	List<IssueColumnsexRestful> listPredictex = new ArrayList<IssueColumnsexRestful>();
	    	for(IssueColumns pc:pdls){ 
	    		IssueColumnsexRestful Predictex = new IssueColumnsexRestful();
	    		
	    		Predictex.setIsucreatedate(pc.getIsuisucreatedate());
	    		Predictex.setIsusname(pc.getIsusisusname());
	    		Predictex.setIscontent(pc.getIsuiscontent());
	    		Predictex.setCwcustomerewbcode(pc.getCwcwcustomerewbcode());
	    		Predictex.setIsutname(pc.getIsutisutname());
	    		Predictex.setCwchargeweight(pc.getCwcwchargeweight());
	    		
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
