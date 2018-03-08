package kyle.leis.eo.operation.predictwaybill.ebay;

import java.io.Writer;
import java.util.logging.Logger;

import kyle.leis.eo.operation.predictwaybill.ebay.vo.EbayRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public abstract class AEbayService implements IEbayService{
	protected static final Logger LOGGER = Logger.getLogger(AEbayService.class.getName());
	
	static final String URL = "https://api.ebay.com/ws/api.dll";
//	static final String URL = "https://api.sandbox.ebay.com/ws/api.dll"; // ≤‚ ‘
	
	private static final String COMPATIBILITY_LEVEL = "943";
	private static final String DEV_NAME = "868edaee-8f03-45a9-8f76-7541a5397209";
	
	private static final String APP_NAME = "Xins102f1-b62d-42e6-83bd-18a8a0915f3";
//	private static final String APP_NAME = "Xins7baf3-47df-4280-9322-2796212f8cd"; // ≤‚ ‘
	
	private static final String CERT_NAME = "596f0e82-5a44-48f1-91aa-17f447ab0964";
//	private static final String CERT_NAME = "9652f794-98f2-44b3-b973-2d7ebb733dbf"; // ≤‚ ‘
	
	private static final String SITEID = "0"; // United States
	
	private static final String XML_DECLARE = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n";
	
	private HttpClient client;
	private PostMethod method;
	
	public AEbayService(String callName) {
		this(URL, callName);
	}
	
	public AEbayService(String URL, String callName) {
		client = new HttpClient();
		method = new PostMethod(URL);
		method.setRequestHeader("X-EBAY-API-COMPATIBILITY-LEVEL", COMPATIBILITY_LEVEL);
		method.setRequestHeader("X-EBAY-API-DEV-NAME", DEV_NAME);
		method.setRequestHeader("X-EBAY-API-APP-NAME", APP_NAME);
		method.setRequestHeader("X-EBAY-API-CERT-NAME", CERT_NAME);
		method.setRequestHeader("X-EBAY-API-SITEID", SITEID);
		method.setRequestHeader("X-EBAY-API-CALL-NAME", callName);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getResponse(EbayRequest request) throws Exception {
		try {
			XStream requestStream = getRequestXStream();
			configToXMLRequest(requestStream);
			String xmlRequest = XML_DECLARE + requestStream.toXML(request);
			LOGGER.info("----------xmlRequest------------");
			LOGGER.info(xmlRequest);
			RequestEntity requestEntity = new StringRequestEntity(xmlRequest, "text/xml", "UTF-8");
			method.setRequestEntity(requestEntity);
			client.executeMethod(method);
			String xmlResponse = method.getResponseBodyAsString();
			LOGGER.info("----------xmlResponse------------");
			LOGGER.info(xmlResponse);
			XStream responseStream = new XStream();
			responseStream.ignoreUnknownElements();
			configToResponse(responseStream);
			return (T) responseStream.fromXML(xmlResponse);
		} finally {
			method.releaseConnection();
		}
	}
	
	private XStream getRequestXStream(){
		XStream stream = new XStream(new XppDriver() {
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new PrettyPrintWriter(out) {

					public void startNode(String name) {
						// »•µÙ∞¸√˚
						if (name.indexOf(".") > -1) {
							name = name.substring(name.lastIndexOf(".") + 1);
						}
						super.startNode(name);
					};
				};
			}
		});
		stream.autodetectAnnotations(true);
		return stream;
	}
	
	protected abstract void configToXMLRequest(XStream stream);
	
	protected abstract void configToResponse(XStream stream);
}
