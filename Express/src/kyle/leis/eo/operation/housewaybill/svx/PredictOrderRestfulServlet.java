package kyle.leis.eo.operation.housewaybill.svx;

import java.util.HashMap;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.housewaybill.bl.PredictOrderEX;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderColumnsEX;
import kyle.leis.fs.authoritys.user.bl.User;
import kyle.leis.fs.authoritys.user.da.UserColumns;


@Path("/PredictOrder")
public class PredictOrderRestfulServlet {
	@SuppressWarnings({ "unchecked" })
	@POST
    @Path("/{id}")
    @Produces({ "application/json; charset=UTF-8;"})
    public Response FinanceQuery(@PathParam("id") String Id,
    			@FormParam("username") String username,
    			@FormParam("password")   String password,
    			@FormParam("strPredictOrderJson") String strPredictOrderJson) throws Exception {      
		HashMap hm=new HashMap();	
	    if("PredictOrderCreate".equals(Id)){
	    	if (StringUtility.isNull(username)|| StringUtility.isNull(password)) {	   
				 hm.put("errorinfo", "-1");
				 hm.put("errcode", "用户名或密码不能为空");			    		
	             return Response.ok(hm).build();
    			}
	    	
	    	UserColumns objUserColumns = null;
	    	User objUser = new User();
    		objUserColumns = objUser.login(username, password);
    		if (objUserColumns == null || 
    			StringUtility.isNull(objUserColumns.getCococode())) {	   
				 hm.put("errorinfo", "-1");
				 hm.put("errcode", "用户名或密码错误，请修改后再提交");			    		
	             return Response.ok(hm).build();
    		}
    		if (StringUtility.isNull(strPredictOrderJson)) { 		  
				 hm.put("errorinfo", "-1");
				 hm.put("errcode", "运单数据不能为空，请输入运单数据");			    		
	             return Response.ok(hm).build();
    		}
	 
	      	PredictOrderColumnsEX objPredictOrderColumnsEX = null;
	      	PredictOrderJsonNew perdictJson = new PredictOrderJsonNew(strPredictOrderJson);
	        objPredictOrderColumnsEX = perdictJson.getPredictOrderEX();
	        // 保存
	        PredictOrderEX objPredictOrderEX = new PredictOrderEX(true);
	        InputAllQReturn objIAQR = objPredictOrderEX.save(objUserColumns.getCococode(),  //2162  1064
	        			objPredictOrderColumnsEX, 
	        			objUserColumns.getOpopid(), 
	        			true,
	        			false);
	        
	        PromptUtilityCollection objPUC = objIAQR.getPromptUtilityCollection();
	        if (objPUC != null && !objPUC.canGo(false)) {	
	        		System.out.println( objPUC.toString());
	    			hm.put("errorinfo", "-1");
	    			hm.put("errcode", objPUC.toString());			    		
	    			return Response.ok(hm).build();      	   
	        }
	        List listlistHouseWayBill = objIAQR.getHWBResults();
	        if (listlistHouseWayBill == null || listlistHouseWayBill.size() < 1) {	   
	    			hm.put("errorinfo", "-1");
	    			hm.put("errcode", "无返回值");			    		
	    			return Response.ok(hm).build();      
	        }      	
	        	        
	        	ForinputallColumns objFIAColumns = (ForinputallColumns)listlistHouseWayBill.get(0);
	        	// 将返回信息写回前台	   
    			hm.put("AWB", objFIAColumns.getCwserverewbcode());	
    			hm.put("OrderID", objFIAColumns.getCwcode());	
    			return Response.ok(hm).build();      
        }   
	    hm.put("errorinfo", "-1");
		hm.put("errcode", "请求失败，请确认地址是否正确后重新请求");			    		
		return Response.ok(hm).build();     		
	}	
}
