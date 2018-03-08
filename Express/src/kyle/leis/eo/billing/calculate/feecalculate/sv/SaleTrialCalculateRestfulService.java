package kyle.leis.eo.billing.calculate.feecalculate.sv;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import net.sf.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import kyle.leis.eo.billing.calculate.feecalculate.bl.SaleTrialCalculate;
import kyle.leis.eo.billing.calculate.feecalculate.dax.CalculteParamRestful;
import kyle.leis.eo.billing.calculate.feecalculate.dax.SaleTrialCalculateParameter;
import kyle.leis.eo.billing.calculate.feecalculate.dax.SaleTrialCalculateResult;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;



@Path("/Calculate")
public class SaleTrialCalculateRestfulService {
	@SuppressWarnings("unchecked")
	@POST
    @Path("/{id}")
    @Produces({ "application/json; charset=UTF-8;"})
    public Response ShipperQuery(@PathParam("id") String Id,
    			@FormParam("Cocode") String strCocode,
    			@FormParam("Pkcode") String strPkcode,
    			@FormParam("Ctcode") String strCtcode,
    			@FormParam("Grossweight") String strGrossweight,
    			@FormParam("Pmcode") String strPmcode,
    			@FormParam("OrginDtcode") String strOrginDtcode,
    			@FormParam("DestDtcode") String strDestDtcode,
    			@FormParam("Postcode") String strPostcode,
    			@FormParam("Param") String Param) throws Exception {      
		
		    List lst=new ArrayList();
		    JSONArray jsonArray = JSONArray.fromObject(Param);	   
            for(int i=0;i<jsonArray.size();i++){
            String ar=jsonArray.getString(i).toString();		          
            JSONObject object = new JSONObject(ar);  
            
			String lenght2=(String)object.get("lenght");	
			String wide2=(String)object.get("wide");	
			String high2=(String)object.get("high");
			
			
			CorewaybillpiecesColumns cl=new CorewaybillpiecesColumns();		
			BigDecimal lenght=new BigDecimal(lenght2);
			BigDecimal wide=new BigDecimal(wide2);
			BigDecimal high=new BigDecimal(high2);
			cl.setCpcplength(lenght);
			cl.setCpcpwidth(wide);
			cl.setCpcpheight(high);
			
			lst.add(cl);
         }
		
	
	    if("CostCalculate".equals(Id)){
	    	SaleTrialCalculate stc=new SaleTrialCalculate();
	    	SaleTrialCalculateParameter  objSCParameter=new SaleTrialCalculateParameter();
	    	
	    	   objSCParameter.setCocode(strCocode);
	    	   objSCParameter.setPkcode(strPkcode);
	    	   objSCParameter.setCtcode(strCtcode);
	    	   objSCParameter.setGrossweight(strGrossweight);
	    	   objSCParameter.setPmcode(strPmcode);
	    	   objSCParameter.setOrginDtcode(strOrginDtcode);
	    	   objSCParameter.setDestDtcode(strDestDtcode);
	    	   objSCParameter.setPostcode(strPostcode);
	    	   objSCParameter.setPiecesList(lst);
	    	   
	    	   List<CalculteParamRestful> lstRest=new ArrayList<CalculteParamRestful>();
	    	   List<SaleTrialCalculateResult> listSTCResults= stc.calculate(objSCParameter);
	    	   for(SaleTrialCalculateResult pc:listSTCResults){
	    		   CalculteParamRestful pd=new CalculteParamRestful();
	    		   
	    		   pd.setPkremark(pc.getRemark(pc.getPkcode()));
	    		   pd.setChargeweight(pc.getChargeweight());
	    		   pd.setPkcode(pc.getPkcode());
	    		   pd.setZoneName(pc.getZonename());
	    		   pd.setGrossweight(pc.getGrossweight());
	    		   pd.setVolumeweight(pc.getVolumeweight());
	    		   pd.setFreightvalue(pc.getFreightvalue());
	    		   pd.setSurchargevalue(pc.getSurchargevalue());
	    		   pd.setIncidentalvalue(pc.getIncidentalvalue());
	    		   pd.setVolumerate(pc.getVolumerate());
	    		   pd.setFreightpriceRemark(pc.getFreightpriceRemark());
	    		   pd.setFreightcalcExp(pc.getFreightcalcExp());
	    		   pd.setExpressStartdate(pc.getExpressstartdate());
	    		   pd.setExpressEnddate(pc.getExpressenddate());
	    		   pd.setZoneName(pc.getZonename());
	    		   pd.setZonevalueName(pc.getZonevaluename());
	    		   pd.setIntroductionlink(pc.getIntroductionlink());
	    		  
	    		   lstRest.add(pd);
	    	   }
			  	
              return Response.ok(lstRest).build();
        }else{
        	   HashMap hm=new HashMap();		   
			    hm.put("errorinfo", "-1");
			    hm.put("errcode", " ‘À„ ß∞‹");
			    		
            return Response.ok(hm).build();
        }	
    }   
}