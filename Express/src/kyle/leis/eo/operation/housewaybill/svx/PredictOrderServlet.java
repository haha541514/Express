package kyle.leis.eo.operation.housewaybill.svx;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kyle.common.connectors.servlet.ActionServletConstant;
import kyle.common.util.jlang.IntervalTime;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.operation.housewaybill.bl.PredictOrderEX;
import kyle.leis.eo.operation.housewaybill.bl.PredictOrderSignOutThread;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderColumnsEX;
import kyle.leis.eo.operation.predictwaybill.bl.Predictwaybill;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.authoritys.user.bl.User;
import kyle.leis.fs.authoritys.user.da.UserColumns;
import kyle.leis.fs.servletutil.ResultsXML;

public class PredictOrderServlet extends HttpServlet {
	private static final long serialVersionUID = -7734960730041654259L;
	private static Logger s_objLogger = Logger.getLogger(PredictOrderServlet.class.getName());
	
	public void init(ServletConfig objSCF) throws ServletException {
		ServletContext sc = objSCF.getServletContext();
		String webInfPath = sc.getRealPath("WEB-INF/web.xml");
		ActionServletConstant objASConstant = ActionServletConstant.getInstance();
    	objASConstant.setRealPath(webInfPath);
	}
	
	public void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
	throws IOException, ServletException {
		process(request, response);
	}
	
	
	public void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
	throws IOException, ServletException {
		process(request, response);
	}
	
	
    protected void process(HttpServletRequest request, 
    		HttpServletResponse response) 
    throws IOException, ServletException {
    	
    	request.setCharacterEncoding("UTF-8");
    	System.out.println(request.getCharacterEncoding());
    	
        String strUsername = request.getParameter("username");
        String strPassword = request.getParameter("password");
        String strPredictOrderXML = request.getParameter("shipmentrequest");
        if (StringUtility.isNull(strPredictOrderXML)) {
        	strPredictOrderXML = request.getParameter("shipment");
        }
        
        if(!StringUtility.isNull(strPredictOrderXML)){
        	strPredictOrderXML = strPredictOrderXML.replaceAll("\u200c|\r|\n|\f", "");
        }
        
        String shipment = request.getParameter("shipment");
        IntervalTime objIT = new IntervalTime(strUsername + "开始调用接口");
        // strPredictOrderXML = CharsetConvert.GBToUnicode(strPredictOrderXML);
        UserColumns objUserColumns = null;
        try {
        	// 检查是否合法的用户跟密码
    		// ...提示信息
    		if (StringUtility.isNull(strUsername)
    				|| StringUtility.isNull(strPassword)) {
    			String strResults = ResultsXML.doResults("E_Login_001",
    					"用户名或密码不能为空");
    			writeBack(response, strResults);
    			return;
    		}
    		User objUser = new User();
    		objUserColumns = objUser.login(strUsername, strPassword);
    		if (objUserColumns == null || 
    				StringUtility.isNull(objUserColumns.getCococode())) {
    			String strResults = ResultsXML.doResults("E_Login_001",
				"用户名或密码错误");
    			writeBack(response, strResults);
    			s_objLogger.warning(objIT.toString() + "--验证错误");
    			return;    			
    		}
        	// 解析运单数据
    		PredictOrderColumnsEX objPredictOrderColumnsEX = null;
    		if (SystempropertyDemand.getEnterprise().startsWith("QQYX")) {
	        	PredictOrderJson perdictJson = new PredictOrderJson(shipment);
	        	objPredictOrderColumnsEX = perdictJson.getPredictOrderEX();
    		} else {
	    		PredictOrderXML objPredictOrderXML = new PredictOrderXML(strPredictOrderXML);
	    		objPredictOrderColumnsEX = objPredictOrderXML.parse();
    		}
        	if (SystempropertyDemand.getEnterprise().startsWith("SLYIM")) {
        		// transfer...()转换类型
	    		PredictwaybillColumns pwc = PredictOrderXML.transformWFDC(objPredictOrderColumnsEX.getWaybillforpredict());
	    		List listPCIC = PredictOrderXML.transformListCIC(objPredictOrderColumnsEX.getListCargoInfo());        		
        		pwc.setCoco_code(objUserColumns.getCococode());
        		String strOperID = objUserColumns.getOpopid();
        		Predictwaybill pw = new Predictwaybill(true);
        		SavedResultUtility objSU = pw.save(pwc, listPCIC, strOperID);
        		PromptUtilityCollection pu = objSU.getPromptUtilityCollection();
        		if (pu != null && !pu.canGo(false)) {
        			writeBack(response, ResultsXML.doResults("E_Save_001", pu.toString()));
        			s_objLogger.warning(objIT.toString() + "--暂存失败");
        			return;
    			}
        		objSU = pw.uploadFinance(pwc.getPwbpwb_code(), strOperID);
        		pu = objSU.getPromptUtilityCollection();
        		if (pu != null && !pu.canGo(false)) {
        			// 删除
        			pw.withdraw(pwc.getPwbpwb_code(), strOperID, false);
        			writeBack(response, ResultsXML.doResults("E_Save_001", pu.toString()));
        			s_objLogger.warning(objIT.toString() + "--上传失败");
        			return;
    			} 
        		PredictwaybillColumns pc = (PredictwaybillColumns)objSU.getColumns();
        		if (pc != null && 
        				!StringUtility.isNull(pc.getPwbcw_code())) {
        			ForinputallColumns fc = HousewaybillDemand.load(pc.getPwbcw_code());
        			// 开启线程出货
            		new PredictOrderSignOutThread(fc.getCwserverewbcode(), strOperID).start();
        			String strFeedBack = doResults(fc);
        			s_objLogger.warning(objIT.toString() + "--上传成功");
    	        	// 将返回信息写回前台
    	        	writeBack(response, strFeedBack); 
    	        	return;
        		} else {
        			s_objLogger.warning(objIT.toString() + "--返回信息为空");
        			writeBack(response, ResultsXML.doResults("E_Save_002", "保存失败,返回的信息为空"));
        			return;        			
        		}
        	} else {
	        	// 保存
	        	PredictOrderEX objPredictOrderEX = new PredictOrderEX(true);
	        	InputAllQReturn objIAQR = objPredictOrderEX.save(objUserColumns.getCococode(), 
	        			objPredictOrderColumnsEX, 
	        			objUserColumns.getOpopid(), 
	        			true,
	        			false);
	        	String strFeedBack = doResults(objIAQR);
	        	// 将返回信息写回前台
	        	writeBack(response, strFeedBack);
        	}
        } catch (Exception ex) {
        	ex.printStackTrace();
        	String strFeedBack = ResultsXML.doResults("E_Others_001", ex.getMessage());
        	writeBack(response, strFeedBack);
        }
    }	
    
    private String doResults(InputAllQReturn objobjIAQR) {
    	PromptUtilityCollection objPUC = objobjIAQR.getPromptUtilityCollection();
    	if (objPUC != null && !objPUC.canGo(false)) {
    		return ResultsXML.doResults("E_Save_001", objPUC.toString());
    	}
    	List listlistHouseWayBill = objobjIAQR.getHWBResults();
    	if (listlistHouseWayBill == null || listlistHouseWayBill.size() < 1) {
    		return ResultsXML.doResults("E_Save_002", "无返回值");
    	}
    	// 返回服务商运单号码
    	ForinputallColumns objFIAColumns = (ForinputallColumns)listlistHouseWayBill.get(0);
		return doResults(objFIAColumns);
    }
    
    private String doResults(ForinputallColumns objFIAColumns) {
    	StringBuffer sb = new StringBuffer();
    	// 返回服务商运单号码
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("\r\n");
		sb.append("<ShipmentResponse>");
		sb.append("	<AWB>" + objFIAColumns.getCwserverewbcode() + "</AWB>");
		try {
			if (SystempropertyDemand.getEnterprise().startsWith("SLYIM")) {
				sb.append("<BookingID>" + objFIAColumns.getHwbookingid() + "</BookingID>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		sb.append("\r\n");
		sb.append("</ShipmentResponse>");
		
    	return sb.toString();
    }    
    
    
    private void writeBack(HttpServletResponse response, String strMessage) {
    	PrintWriter objPW = null;
    	try {
    		response.setCharacterEncoding("utf-8");
    		objPW = response.getWriter();
    		objPW.print(strMessage);
    		strMessage = null;
    	} catch (IOException e) {
    		s_objLogger.warning("Get http response writer failed! " + e);
    	} finally {
    		objPW.close();
    	}
    }

}
