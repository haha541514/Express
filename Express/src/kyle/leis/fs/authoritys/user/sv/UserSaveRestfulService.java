package kyle.leis.fs.authoritys.user.sv;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import kyle.leis.fs.authoritys.user.bl.User;
import kyle.leis.fs.authoritys.user.da.UserColumns;
import kyle.leis.fs.authoritys.user.dax.OperatorColumnsexRestful;
import kyle.leis.fs.dictionary.operator.da.OperatorColumns;
import kyle.leis.fs.dictionary.operator.da.OperatorCondition;
import kyle.leis.fs.dictionary.operator.da.OperatorQuery;


@Path("/Save")
public class UserSaveRestfulService {
	
	@SuppressWarnings("unchecked")
	@GET
    @Path("/{id}")
    @Produces({ "application/json; charset=UTF-8;"})
    public Response ShipperQuery(@PathParam("id") String Id,
    			@QueryParam("Opcode") String Opcode,
    			@QueryParam("Opname") String Opname,
    			@QueryParam("Word") String Word,
    			@QueryParam("Opemail") String opemail,
    			@QueryParam("Opaddress") String opaddress,
    			@QueryParam("Stcode") String stcode,
    			@QueryParam("Ctcode") String ctcode,
    			@QueryParam("Optelephone") String optelephone) throws Exception {      
	    if("UserSave".equals(Id)){
	    	
	    	Date date = new Date();//当前日期
	    	
	    	User user=new User();
	    	UserColumns objUserColumns=new UserColumns();  
	    	objUserColumns.setStstcode(stcode);
	    	objUserColumns.setCtctcode(ctcode);
	    	objUserColumns.setOpopcode(Opcode);
	    	objUserColumns.setOpopname(Opname);
	    	objUserColumns.setWord(Word);
	    	objUserColumns.setOpopemail(opemail);
	    	objUserColumns.setOpopaddress(opaddress);
	    	objUserColumns.setOpoptelephone(optelephone);
	    	objUserColumns.setOpopcreatedate(date);
	    	objUserColumns.setCococode("N");
	    	objUserColumns.setCocoename(null);
	    	objUserColumns.setCoconame(null);
	    	objUserColumns.setCocosename(null);
	    	objUserColumns.setCocosname(null);
	    	objUserColumns.setCurrentRownum(0);
	    	objUserColumns.setDpdpcode("OP");
	    	objUserColumns.setDpdpename(null);
	    	objUserColumns.setDpdpname(null);
	    	objUserColumns.setEeeecode("1");
	    	objUserColumns.setEeeeename(null);
	    	objUserColumns.setEeeeesname(null);
	    	objUserColumns.setEeeename(null);
	    	objUserColumns.setEeeesname(null);
	    	objUserColumns.setFcfccode("GOP");
	    	objUserColumns.setFcfcename(null);
	    	objUserColumns.setFcfcname(null);
	    	objUserColumns.setOpopconfirmdate(date);
	    	objUserColumns.setOpopdimissiondate(null);
	    	objUserColumns.setOpopename(null);
	    	objUserColumns.setOpopfaxnumber(null);
	    	objUserColumns.setOpopidcreator(1);
	    	objUserColumns.setOpopidmodifier(1);
	    	objUserColumns.setOpopid(null);
	    	objUserColumns.setOpopidnumber("101");
	    	objUserColumns.setOpopissuecontactpersonsign(null);
	    	objUserColumns.setOpopmobile("x");
	    	objUserColumns.setOpopmodifydate(date);
	    	objUserColumns.setOpopmsnname(null);
	    	objUserColumns.setOpopqqnumber(null);
	    	objUserColumns.setOpopsex("F");
	    	objUserColumns.setOpopsname("XXX");
	    	objUserColumns.setOsoscode("RS");
	    	objUserColumns.setOsosname(null);
	    	objUserColumns.setPspscode("CL");
	    	objUserColumns.setPspsename(null);
	    	objUserColumns.setPspsname(null);	    	
	    	user.save("0", objUserColumns);
	    	   
	    	OperatorQuery objOperatorQuery = new OperatorQuery();
			OperatorCondition objOperatorC = new OperatorCondition();
			objOperatorC.setOpcode(Opcode);
			objOperatorQuery.setCondition(objOperatorC);
			
			List<OperatorColumns> objList = objOperatorQuery.getResults();
			
			   List<OperatorColumnsexRestful> lst=new ArrayList<OperatorColumnsexRestful>();
			   for(OperatorColumns pc:objList){
				   OperatorColumnsexRestful op=new OperatorColumnsexRestful();
				   
				   op.setStcode(pc.getStcode());
				   op.setCtcode(pc.getCtcode());
				   op.setStname(pc.getStname());
				   op.setCtname(pc.getCtname());
				   op.setOpcode(pc.getOpcode());
				   op.setCocode(pc.getCocode());
				   op.setOscode(pc.getOscode());
				   op.setOpmobile(pc.getOpmobile());
				   op.setDpcode(pc.getDpcode());
				   op.setEecode(pc.getEecode());
				   op.setEekcode(pc.getEekcode());
				   op.setEestructurecode(pc.getEestructurecode());
				
				   lst.add(op);
			   } 
   	
           return Response.ok(lst).build();
        }else{
        	   HashMap hm=new HashMap();		   
			    hm.put("errorinfo", "-1");
			    hm.put("errcode", "注册失败");
			    		
            return Response.ok(hm).build();
        }	
    }   
}
