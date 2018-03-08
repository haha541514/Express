package kyle.leis.eo.customerservice.track.svx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;

import kyle.leis.eo.customerservice.track.bl.Track;
import kyle.leis.eo.customerservice.track.dax.JsonResult;
import kyle.leis.eo.customerservice.track.dax.TrackDemand;
import kyle.leis.eo.customerservice.track.svx.TrackRequestParser.Event;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillColumns;

public class TrackServlet extends HttpServlet {
	private static Logger s_objLogger = Logger.getLogger(TrackServlet.class.getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public TrackServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/javascript");
		PrintWriter out = response.getWriter();
		
		String inputStr = getInput(request.getInputStream());
		s_objLogger.info(inputStr);
		TrackRequestParser requestParser = new TrackRequestParser();
		try {
			requestParser.parser(inputStr);
		} catch (JSONException e) {
			e.printStackTrace();
			write(out, JsonResult.returnFail("1", e.getMessage()));
			return;
		}
		
		SimplecorewaybillColumns columns = null;
		try {
			columns = TrackDemand.findCoreWayBillByBookId(requestParser.getBookId());
		} catch (Exception e1) {
			e1.printStackTrace();
			write(out, JsonResult.returnFail("5", "Internal server error!"));
			return;
		}
		if (columns == null) {
			write(out, JsonResult.returnFail("2", "Invalid booking Id!"));
			return;
		}
		String strCwcode = columns.getCwcw_code();
		
		List<Event> events = requestParser.getEvents();
		Track track = new Track();
		for (Event event : events) {
			try {
				track.addSingleTrack(strCwcode, null, null, 
						event.getMessage(), "0", event.getDate());
			} catch (Exception e) {
				e.printStackTrace();
				write(out, JsonResult.returnFail("3", "There was an error saving the Track!"));
				return;
			}
		}
		write(out, JsonResult.returnSuccess("Save success!"));
	}
	
	private String getInput(InputStream inputStream) throws IOException{
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = 
			new BufferedReader(new InputStreamReader(inputStream));
		String strLine;
		while ((strLine = reader.readLine()) != null) {
			sb.append(strLine + "\r\n");
		}
		reader.close();
		return sb.toString();
	}
	
	private void write(PrintWriter out, String content){
		out.write(content);
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
