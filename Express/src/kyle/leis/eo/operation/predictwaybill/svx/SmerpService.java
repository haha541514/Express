package kyle.leis.eo.operation.predictwaybill.svx;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kyle.leis.eo.operation.predictwaybill.smerp.SmErpInterface;

import org.apache.commons.io.IOUtils;

public class SmerpService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "http://" + request.getServerName();
		if (request.getServerPort() != 80) {
			url += ":" + request.getServerPort();
		}
		url += request.getContextPath() + request.getServletPath();
		String action = request.getParameter(SmErpInterface.ACTION_PARAM_NAME);
		String pushRequest = null;
	
		if (SmErpInterface.API_PUSH.equals(action) || SmErpInterface.API_PRINTLABLE.equals(action)) {
			ServletInputStream inputStream = request.getInputStream();
			pushRequest = IOUtils.toString(inputStream, "UTF-8");
		}
		SmErpInterface smErp = new SmErpInterface(url);
		String result = null;
			
		try {
			result = smErp.apiEntry(action, pushRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setContentType("application/json;charset=UTF-8");
		if (SmErpInterface.API_DESCRIPTION.equals(action)) {
			response.setContentType("text/html;charset=UTF-8");
		}
		response.getWriter().write(result);
	}
}
