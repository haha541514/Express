package kyle.leis.fs.dictionary.operator.sv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeColumns;
import kyle.leis.fs.authoritys.user.dax.OperatorColumnsexRestful;
import kyle.leis.fs.dictionary.operator.da.OperatorColumns;
import kyle.leis.fs.dictionary.operator.da.OperatorCondition;
import kyle.leis.fs.dictionary.operator.da.OperatorQuery;

@Path("/Login")
public class LoginInterfaceRestfulService {

	@SuppressWarnings("unchecked")
	@GET
    @Path("/{id}")
    @Produces({ "application/json; charset=UTF-8;"})
    public Response ProductQuery(@PathParam("id") String Id,		
    			@QueryParam("OperCode")   String strOperCode,
    			@QueryParam("OperPassword")   String strOperPassword) throws Exception {    
		
	    if("OperatorLogin".equals(Id)){
	    	OperatorQuery objOperatorQuery = new OperatorQuery();
			OperatorCondition objOperatorC = new OperatorCondition();
			objOperatorC.setOpcode(strOperCode);
			objOperatorQuery.setCondition(objOperatorC);
			List<OperatorColumns> objList = objOperatorQuery.getResults();
			
			   if (CollectionUtility.isNull(objList)) {				
				    HashMap hm=new HashMap();		   
				    hm.put("errorinfo", "-1");
				    hm.put("errcode", "用户名不存在");
				    		
	            return Response.ok(hm).build();
			    }
			   if (objList.get(0) instanceof OperatorColumns) {
				    OperatorColumns objOperatorColumns = (OperatorColumns)objList.get(0);
				    String strQueryPassword = objOperatorColumns.getSword();
			   if (StringUtility.isNull(strOperPassword) || StringUtility.isNull(strQueryPassword)) {
				    HashMap hm=new HashMap();		   
				    hm.put("errorinfo", "-1");
				    hm.put("errcode", "密码不能为空");
				    		
	            return Response.ok(hm).build();
			    }
				if (!strOperPassword.equals(strQueryPassword)) {
					    HashMap hm=new HashMap();		   
					    hm.put("errorinfo", "-1");
					    hm.put("errcode", "密码不正确");
					    		
		            return Response.ok(hm).build();
				}
			    }
			   List ls=new ArrayList();
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
			    HashMap hm=new HashMap();		   
			    hm.put("errorinfo", "null");
			    hm.put("errcode", "null");
			    		
			   ls.add(lst);
			   ls.add(hm);
		    return Response.ok(ls).build();
			  
	    } else{
	    	    HashMap hm=new HashMap();		   
			    hm.put("errorinfo", "-1");
			    hm.put("errcode", "登录失败");
			    		
         return Response.ok(hm).build();
        }	
	  
	}
}
