package kyle.leis.eo.operation.predictwaybill.svx;


import java.util.ArrayList;
import java.util.HashMap;


import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillCondition;
import kyle.leis.eo.operation.predictwaybill.dax.PredictwaybillColumnsexRestful;
import kyle.leis.eo.operation.predictwaybill.dax.PredictwaybillDemand;


@Path("/order")
public class PredictwaybillRestfulService {
	@SuppressWarnings({ "static-access", "unchecked" })
	@GET
    @Path("/{id}")
    @Produces({ "application/json; charset=UTF-8;"})
    public Response query(@PathParam("id") String Id,
    			@QueryParam("startcreatedate") String StartCreateDate,
    			@QueryParam("endcreatedate")   String EndCreateDate,
    			@QueryParam("pkcode")   String pk_code,
    			@QueryParam("cocode") String SetCo_code_customer,
    			@QueryParam("dtcode")   String dt_code) throws Exception {      
	    if("PredictwaybillQuery".equals(Id)){
	    	PredictwaybillDemand pd=new PredictwaybillDemand();
	    	PredictwaybillCondition objPWCondition=new PredictwaybillCondition();	
	    	
	    	objPWCondition.setEndcreatedate(EndCreateDate);
	    	objPWCondition.setStartcreatedate(StartCreateDate);
	    	objPWCondition.setPk_code(pk_code);
	    	objPWCondition.setCo_code_customer(SetCo_code_customer);
	    	objPWCondition.setDt_code(dt_code);
	    	objPWCondition.setPwbs_code("CTS, CHP, CHU");
	    	
	    	
	    	List<PredictwaybillColumns> pdls=pd.query(objPWCondition);
	    	
	    	List<PredictwaybillColumnsexRestful> listPredictex = new ArrayList<PredictwaybillColumnsexRestful>();
	    	for(PredictwaybillColumns pc:pdls){ 
	    		PredictwaybillColumnsexRestful Predictex = new PredictwaybillColumnsexRestful();
	    		
	    		Predictex.setConsigneename(pc.getPwbpwb_consigneename());
	    		Predictex.setConsigneetel(pc.getPwbpwb_consigneetel());
	    		Predictex.setConsigneeaddress(pc.getPwbpwb_consigneeaddress1());
	    		Predictex.setConsigneecity(pc.getPwbpwb_consigneecity());
	    		Predictex.setConsigneepostcode(pc.getPwbpwb_consigneepostcode());
	    		Predictex.setCargoename(pc.getPwbpwb_cargoename());
	    		Predictex.setChargeweight(pc.getPwbpwb_chargeweight());
	    		Predictex.setTransactionid(pc.getPwbpwb_transactionid());
	    		Predictex.setOrderid(pc.getPwbpwb_orderid());
	    		Predictex.setPwbsname(pc.getPwbspwbs_name());
	    		Predictex.setPksname(pc.getPkpk_sname());
	    		Predictex.setServerewbcode(pc.getPwbpwb_serverewbcode());
		    		
		    	listPredictex.add(Predictex);
	    	}	    	
	    	
	    	
          return Response.ok(listPredictex).build();
        }else{
        	    HashMap hm=new HashMap();		   
			    hm.put("errorinfo", "-1");
			    hm.put("errcode", "∑√Œ  ß∞‹");
			    		
          return Response.ok(hm).build();
        }	
    }   
	 
	
	
	
	
}