package kyle.leis.eo.operation.housewaybill.svx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictCondition;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.fs.authoritys.user.bl.User;
import kyle.leis.fs.authoritys.user.da.UserColumns;
import net.sf.json.JSONObject;

public class PredictBookingIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String customerwbCodes = request.getParameter("customerwbCodes");
		Map<String, Object> result = new HashMap<String, Object>();
		if (StringUtility.isNull(username) || StringUtility.isNull(password) 
				|| StringUtility.isNull(customerwbCodes)) {
			result.put("Success", false);
			result.put("Message", "用户名、密码和客户单号都不能为空（username,password,customerwbCodes）");
			writeResult(response, result);
			return;
		}
		User objUser = new User();
		UserColumns objUserColumns = objUser.login(username, password);
		if (objUserColumns == null || StringUtility.isNull(objUserColumns.getCococode())) {
			result.put("Success", false);
			result.put("Message", "用户名或密码错误");
			writeResult(response, result);
			return;
		}
		WaybillforpredictCondition objWFPCondition = new WaybillforpredictCondition();
		objWFPCondition.setCocodecustomer(objUserColumns.getCococode());
		objWFPCondition.setCwcustomerewbcode(customerwbCodes);
		try {
			List<?> list = HousewaybillDemand.queryForPredict(objWFPCondition);
			result.put("Success", true);
			result.put("Message", "");
			List<Map<String, String>> data = new ArrayList<Map<String,String>>();
			for (Object object : list) {
				Map<String, String> map = new HashMap<String, String>();
				WaybillforpredictColumns wbp = (WaybillforpredictColumns) object;
				map.put("CWB", wbp.getCwcw_customerewbcode());
				map.put("AWB", wbp.getCwcw_serverewbcode());
				map.put("WEIGHT", wbp.getCwcw_chargeweight());
				map.put("SODATE", wbp.getHw_signindate());
				// BookingId放到buyerId里了
				map.put("BookingId", wbp.getHwhw_buyerid()); 
				
				data.add(map);
			}
			result.put("Data", data);
			writeResult(response, result);
		} catch (Exception e) {
			result.put("Success", false);
			result.put("Message", "查询出错：" + e.getMessage());
			writeResult(response, result);
			e.printStackTrace();
		}
	}
	
	private void writeResult(HttpServletResponse response, Object result) throws IOException{
		response.getWriter().write(JSONObject.fromObject(result).toString());
	}

}
