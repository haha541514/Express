package kyle.leis.eo.operation.predictwaybill.smerp;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import kyle.common.util.jlang.DateUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictCondition;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictQuery;
import kyle.leis.eo.operation.housewaybill.dax.WaybillforpredictQueryEX;
import kyle.leis.eo.operation.predictwaybill.bl.Predictwaybill;
import kyle.leis.eo.operation.predictwaybill.da.PredictcargoinfoColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.eo.operation.predictwaybill.smerp.assembler.SmErpAssembler;
import kyle.leis.eo.operation.predictwaybill.smerp.util.MD5Util;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.ApiEntry;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.DetailOrder;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.ErrorData;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.Order;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.PrintRequest;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.PrintResponse;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.PrintResult;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.Product;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.ProductKind;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.PushRequest;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.PushResponse;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.Receiver;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.Result;
import kyle.leis.es.price.expressprice.bl.ExpressPrice;
import kyle.leis.fs.cachecontainer.dax.ProductkindColumnsEX;
import kyle.leis.fs.cachecontainer.dax.ProductkindQueryEX;
import kyle.leis.fs.dictionary.operator.da.OperatorColumns;
import kyle.leis.fs.dictionary.operator.da.OperatorCondition;
import kyle.leis.fs.dictionary.operator.da.OperatorQuery;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.io.FileUtils;
import sun.misc.BASE64Encoder;


public class SmErpInterface {
	protected static final Logger LOGGER = Logger.getLogger(SmErpInterface.class.getName());
	//和SmPritInterface是分开的.
	/** 接收订单 **/
	public static final String API_PUSH = "push";
	/** 货代介绍 **/
	public static final String API_DESCRIPTION = "description";
	/** 货代渠道 **/
	public static final String API_CHANNEL = "channel";
	
	public static final String ACTION_PARAM_NAME = "action";
											
	private static final String API_TOKEN = "EC55DE27E3FA4C2483FF208A24F3B00F";
	/**打印标签*/
	public static final String API_PRINTLABLE = "print";
	/**LabelUrl*/
	public static final String PRINT_PDF = "http://www.1001000.com/PrintPDFLableServlet.xsv";

	private final String url;
	
	public SmErpInterface() {
		url = null;
	}
	
	public SmErpInterface(String url) {
		this.url = url;
	}
	
	public String apiEntry(String action, String request) throws Exception{
		if (API_PUSH.equalsIgnoreCase(action)) {
			LOGGER.info("----------------SmErpInterface------------------------");
			LOGGER.info(request);
			return push(request);
		} else if (API_DESCRIPTION.equalsIgnoreCase(action)) {
			return des();
		} else if (API_CHANNEL.equalsIgnoreCase(action)) {
			return channel();
		} else if (API_PRINTLABLE.equalsIgnoreCase(action)) {
			return printLable(request);
		} else {
			return apiEntry();
		}
	}
	/**将URl转为JSON格式*/
	private String apiEntry() {
		ApiEntry apiEntry = new ApiEntry();
		apiEntry.setPushUrl(url + "?" + ACTION_PARAM_NAME + "=" + API_PUSH);
		apiEntry.setDescriptionUrl(url + "?" + ACTION_PARAM_NAME + "=" + API_DESCRIPTION);
		apiEntry.setChannelUrl(url + "?" + ACTION_PARAM_NAME + "=" + API_CHANNEL);

		apiEntry.setLabelUrl(url + "?" + ACTION_PARAM_NAME + "=" + API_PRINTLABLE);
		return toJsonStr(apiEntry);
	}

	private String channel() {
		return toJsonStr(getProduct("1"), Arrays.asList(new Class<?>[]{ProductKind.class}));
	}

	private String des() {
		return "<div>百千诚国际物流有限公司隶属于百千诚集团，在物流产业方面拥有获得国家行政机关批准的国际快递、国际空运、报关报检、道路运输等相关运营资质；坚持合法经营、相互信赖、互利共赢、服务社会的原则；拥有精通业务、善于管理、经验丰富、不断创新的管理团队。</div>" +
				"<div>服务热线：0755-22222232</div>";
	}
	
	
	
	
	private String push(String request){
		PushResponse response = new PushResponse();
		// 数据验证
		if (StringUtility.isNull(request)) {
			response.setSuccess(false);
			response.setMessage("No data error!");
			return toJsonStr(response);
		}
		PushRequest pushRequest = null;
		try {
			pushRequest = buildPushRequest(request);
		} catch (JSONException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage("Invalid json data format!");
			return toJsonStr(response);
		}
		// 身份验证
		String apiToken = pushRequest.getApiToken();
		if (!API_TOKEN.equals(apiToken)) {
			response.setSuccess(false);
			response.setMessage("ApiToken is invalid!");
			return toJsonStr(response);
		}
		String opCode = pushRequest.getApiUserName();
		OperatorQuery operatorQuery = new OperatorQuery();
		OperatorCondition condition = new OperatorCondition();
		condition.setOpcode(opCode);
		operatorQuery.setCondition(condition);
		List<?> operatorColumns = null;
		try {
			operatorColumns = operatorQuery.getResults();
			if (operatorColumns.isEmpty()) {
				response.setSuccess(false);
				response.setMessage("ApiUserName is invalid!");
				return toJsonStr(response);
			}
		} catch (Exception e) {
			response.setSuccess(false);
			response.setMessage(e.getMessage());
			return toJsonStr(response);
		}
		// 上传运单
		OperatorColumns operator = (OperatorColumns) operatorColumns.get(0);
		List<Order> orderList = pushRequest.getOrderList();
		Predictwaybill predictwaybill = new Predictwaybill();
		for (Order order : orderList) {
			Result result = new Result();
			result.setPlatformOrderID(order.getPlatformOrderID());
			SavedResultUtility checkResultUtility = checkOrderAndSetPredictwaybill(order, operator.getCocode());
			PromptUtilityCollection utilityCollection = checkResultUtility.getPromptUtilityCollection();
			if (!utilityCollection.canGo(false)) {
				result.setSuccess(false);
				result.setErrorMessage(utilityCollection.toString());
				response.addResult(result);
				continue;
			}
			// 保存预报
			SavedResultUtility resultUtility = predictwaybill.save((PredictwaybillColumns) checkResultUtility.getColumns(), 
					checkResultUtility.getColumnsCollection(), operator.getOpid());
			if (resultUtility.getPromptUtilityCollection().canGo(false)) {
				PredictwaybillColumns savedColumns = (PredictwaybillColumns) resultUtility.getColumns();
				String pwbCode = savedColumns.getPwbpwb_code();
				// 申报
				SavedResultUtility ufResult = predictwaybill.upload(pwbCode, operator.getOpid());//1375133,Opid101380
				if (ufResult.getPromptUtilityCollection().canGo(false)) {
					result.setSuccess(true);
					PredictwaybillColumns ufColumns = (PredictwaybillColumns) ufResult.getColumns();
					result.setTrackingNumber(ufColumns.getPwbpwb_serverewbcode());
					result.setFreightNumber(pwbCode);
					
					String returnLableUrl = url+"?action=print";//速脉根据这个URL来获取PDF,传递的值是json，放在request中
					result.setLabelUrl(returnLableUrl);
					
				} else {
					// 撤销预报
					predictwaybill.withdraw(pwbCode, operator.getOpid(), false);
					result.setSuccess(false);
					result.setErrorMessage(ufResult.getPromptUtilityCollection().toString());
				}
			} else {
				result.setSuccess(false);
				result.setErrorMessage(resultUtility.getPromptUtilityCollection().toString());
			}
			response.addResult(result);
		}
		response.setSuccess(true);
		return toJsonStr(response, Arrays.asList(new Class<?>[]{Result.class}));
	}
	
	
	
	
	private SavedResultUtility checkOrderAndSetPredictwaybill(Order order, String coCode){
		SavedResultUtility resultUtility = new SavedResultUtility();
		PromptUtilityCollection collection = new PromptUtilityCollection();
		resultUtility.setPromptUtilityCollection(collection);
		if (StringUtility.isNull(order.getChannel())) {
			collection.add("E_001", "渠道不能为空", "SmErpInterface.checkOrderAndSetPredictwaybill");
			return resultUtility;
		}
		PredictwaybillColumns predictwaybillColumns = SmErpAssembler.toPredictwaybill(order);
		predictwaybillColumns.setPkpk_code(order.getChannel());
		predictwaybillColumns.setCoco_code(coCode);
		List<Product> productList = order.getProductList();
		List<PredictcargoinfoColumns> listCargoinfo = new ArrayList<PredictcargoinfoColumns>();
		for (Product product : productList) {
			if (StringUtility.isNull(product.getCustomsName())) {
				collection.add("E_001", "产品品名不能为空", "SmErpInterface.checkOrderAndSetPredictwaybill");
				return resultUtility;
			}
			Pattern pt = Pattern.compile("[^a-zA-Z 0-9%]");
			if (StringUtility.isNull(pt.matcher(product.getCustomsName()).replaceAll(""))) {
				collection.add("E_001", "产品英文品名不能有特殊字符（包括中文）", "SmErpInterface.checkOrderAndSetPredictwaybill");
				return resultUtility;
			}
			product.setCurrencyType(order.getCurrencyType());
			PredictcargoinfoColumns predictcargoinfo = SmErpAssembler.toPredictcargoinfo(product);
			listCargoinfo.add(predictcargoinfo);
		}
		resultUtility.setColumns(predictwaybillColumns);
		resultUtility.setColumnsCollection(listCargoinfo);
		return resultUtility;
	}
	
	private PushRequest buildPushRequest(String request) throws JSONException{
		JSONObject jsonObject = JSONObject.fromObject(request);
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("OrderList", Order.class);
		classMap.put("ProductList", Product.class);
		JsonConfig config = new JsonConfig();
		config.setRootClass(PushRequest.class);
		config.setClassMap(classMap);
		ToLowerCasePropertyNameProcessor propertyNameProcessor = new ToLowerCasePropertyNameProcessor();
		config.registerJavaPropertyNameProcessor(PushRequest.class, propertyNameProcessor);
		config.registerJavaPropertyNameProcessor(Order.class, propertyNameProcessor);
		config.registerJavaPropertyNameProcessor(Receiver.class, propertyNameProcessor);
		config.registerJavaPropertyNameProcessor(Product.class, propertyNameProcessor);
		return (PushRequest) JSONObject.toBean(jsonObject, config);
	}

	
	private String toJsonStr(Object object){
		return toJsonStr(object, null);
	}
	
	private String toJsonStr(Object object, List<Class<?>> classes){
		JsonConfig config = new JsonConfig();
		ToUpperCasePropertyNameProcessor pnp = new ToUpperCasePropertyNameProcessor();
		if (!(object instanceof Collection<?>)) {
			config.registerJsonPropertyNameProcessor(object.getClass(), pnp);
		}
		if (classes != null && !classes.isEmpty()) {
			for (Class<?> clazz : classes) {
				config.registerJsonPropertyNameProcessor(clazz, pnp);
			}
		}
		if (object instanceof Collection<?>) {
			JSONArray jsonArray = JSONArray.fromObject(object, config);
			return jsonArray.toString();
		}
		JSONObject js = JSONObject.fromObject(object, config);
		return js.toString();
	}
	
	@SuppressWarnings("unused")
	private String toJsonStr2(Object object, List<Class<?>> classes,List<Class<?>> classes2){
		JsonConfig config = new JsonConfig();
		ToUpperCasePropertyNameProcessor pnp = new ToUpperCasePropertyNameProcessor();
		if (!(object instanceof Collection<?>)) {
			config.registerJsonPropertyNameProcessor(object.getClass(), pnp);
		}
		if (classes != null && !classes.isEmpty()) {
			for (Class<?> clazz : classes) {
				config.registerJsonPropertyNameProcessor(clazz, pnp);
			}
		}
		if (classes2 != null && !classes2.isEmpty()) {
			for (Class<?> clazz : classes2) {
				config.registerJsonPropertyNameProcessor(clazz, pnp);
			}
		}
		
		if (object instanceof Collection<?>) {
			JSONArray jsonArray = JSONArray.fromObject(object, config);
			return jsonArray.toString();
		}
		JSONObject js = JSONObject.fromObject(object, config);
		return js.toString();
	}
	
	/**
	 * 当前用户拥有的产品列表
	 * @param cocode
	 * @return
	 * @throws Exception
	 */
	private List<ProductKind> getProduct(String cocode) {
		List<ProductKind> productKinds = new ArrayList<ProductKind>();
		try{
			ProductkindQueryEX objPKQ = new ProductkindQueryEX();
			objPKQ.setUseCachesign(true);
			List<?> listResults = objPKQ.getResults();
			List<?> objProductkindColumns = Collections.unmodifiableList(listResults);
			
			if(StringUtility.isNull(cocode)) {
				cocode = "338";
			}
			String strDtcode = "719";
			String strEecode = "1";
			ExpressPrice expressPrice = new ExpressPrice();
			HashSet<String> pkCodeSet = expressPrice.searchProductKind(cocode, strDtcode, strEecode);
			for (Object object : objProductkindColumns) {
				ProductkindColumnsEX productkindColumns = (ProductkindColumnsEX) object;
				if (pkCodeSet.contains(productkindColumns.getPkcode())) {
					ProductKind productKind = new ProductKind(productkindColumns.getPkcode(), 
							productkindColumns.getPkname());
					productKinds.add(productKind);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productKinds;
	}
	private String printLable(String request) throws Exception {
		PrintResponse response = new PrintResponse();
		PrintResult result = null;
		PrintRequest printRequest = null;
		List<ErrorData> errorList = new ArrayList<ErrorData>();
	
		if (StringUtility.isNull(request)) {
			response.setSuccess(false);
			response.setMessage("No data error!");
			return toJsonStr(response);
		}
		try {
			printRequest = buildPrintLableRequest(request);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setMessage("Invalid json data format!");
			return toJsonStr(response);
		}

		String apiToken = printRequest.getToken();
		if (!API_TOKEN.equals(apiToken)) {
			response.setSuccess(false);
			response.setMessage("ApiToken is invalid!");
			return toJsonStr(response);
		}
		int orderSize = printRequest.getDetail().size();
		String cwservercode = "";
		String serverewbcode = "";
		String errorServerCode = "";
		for (int index = 0; index < orderSize; index++) {
				result = new PrintResult();
				
			errorServerCode = printRequest.getDetail().get(index).getReferenceid();
			//查询服务商单号
			WaybillforpredictCondition objWFPCondition = new WaybillforpredictCondition();
			objWFPCondition.setCwcustomerewbcode(errorServerCode);
			objWFPCondition.setStartcreatedate(DateUtility.getMoveDate(-10));//设置打印时间
			WaybillforpredictQuery objWFPQuery = new WaybillforpredictQueryEX();
			objWFPQuery.setCondition(objWFPCondition);
			List list = objWFPQuery.getResults();
			if (list == null || list.size() < 1) {
				ErrorData objErrordata = new ErrorData();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				objErrordata.setDateTime(sdf.format(new java.util.Date()));
				objErrordata.setErrorCode("time limit exceeded");
				objErrordata.setRequestUri("http://www.1001000.com/PrintPDFLableServlet.xsv?sign=s&serverewbcode="
						+ errorServerCode);
				objErrordata.setMessage("time limit exceeded");
				objErrordata.setTicketId(errorServerCode);
				
				errorList.add(objErrordata);
				//response.setErrorData(errorList);
				
				response.setErrorData(objErrordata);
				response.setMessage("print failed");
				response.setSuccess(false);
				return toJsonStr(response, Arrays.asList(new Class<?>[] { ErrorData.class }));
			}
			
			WaybillforpredictColumns columns = (WaybillforpredictColumns) list.get(0);
			serverewbcode = "x>" + columns.getCwcw_serverewbcode() + "<x";

			String path = PRINT_PDF;//这里其实使用传的URL，然后加上Servlet的，这里写死了。
			URL url = new URL(path);
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setConnectTimeout(30000);
			httpConn.setReadTimeout(30000);
			httpConn.setDoOutput(true);
			OutputStream os = httpConn.getOutputStream();
			String param = new String();
			
			param = "sign=s" + "&" + "serverewbcode=" + serverewbcode;
			os.write(param.getBytes());
			os.flush();
			os.close();
			httpConn.getResponseCode();
			URL urlResult = httpConn.getURL();			
			String responseResult = urlResult.toString();
			if (("/PrintPDFLableServlet.xsv").equals(responseResult
					.substring(responseResult.lastIndexOf("/")))) {
				ErrorData objErrordata = new ErrorData();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				objErrordata.setDateTime(sdf.format(new java.util.Date()));
				objErrordata.setErrorCode("PrintLable Error");
				objErrordata
						.setRequestUri("http://www.1001000.com/PrintPDFLableServlet.xsv?sign=s&serverewbcode="
								+ serverewbcode);
				objErrordata.setMessage("PrintLable Error");
				objErrordata.setTicketId(errorServerCode);
				
				errorList.add(objErrordata);
				//response.setErrorData(errorList);
				response.setErrorData(objErrordata);
				response.setMessage("print failed");
				response.setSuccess(false);
				return toJsonStr(response, Arrays.asList(new Class<?>[] { ErrorData.class }));
			}else if (responseResult.indexOf("</script>") > 0) {
				ErrorData errordata = new ErrorData();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				errordata.setDateTime(sdf.format(new java.util.Date()));
				errordata.setErrorCode("PrintLable Error");
				errordata
						.setRequestUri("http://www.1001000.com/PrintPDFLableServlet.xsv?sign=s&serverewbcode="
								+ serverewbcode);
				errordata.setMessage("PrintLable Error");
				errordata.setTicketId(errorServerCode);
				String jsonStr = toJsonStr(errordata);
				
				errorList.add(errordata);
				
				//response.setErrorData(errorList);
				
				response.setMessage("print failed");
				response.setSuccess(false);
				return toJsonStr(response, Arrays.asList(new Class<?>[] { ErrorData.class }));
		
			}
			String pdfFileName = responseResult.substring(responseResult
					.lastIndexOf("/") + 1);
			
			ClassLoader classLoader = Thread.currentThread()
					.getContextClassLoader();
			if (classLoader == null) {
				classLoader = ClassLoader.getSystemClassLoader();
			}
			java.net.URL url2 = classLoader.getResource("");			
			String ROOT_CLASS_PATH = url2.getPath() + "/";
			File rootFile = new File(ROOT_CLASS_PATH);
			String WEB_INFO_DIRECTORY_PATH = rootFile.getParent() + "/";
			File webInfoDir = new File(WEB_INFO_DIRECTORY_PATH);
			String SERVLET_CONTEXT_PATH = webInfoDir.getParent() + "/";
		
			String path3 = SERVLET_CONTEXT_PATH  + "PDFFile/" + pdfFileName;
			System.out.println("-------PDFFile:"+path3);
			path3 = path3.replaceAll("%20", " ");
			byte[] buffer = null;
			try {

				File file = new File(path3);//一些PDF系统文件下是没有的,PrintPDFServlet返回的是一个URL
				InputStream fis = new FileInputStream(file);
				ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
				long length = file.length();
				byte[] bytes = new byte[(int) length];
				int len = 0;
				while ((len = fis.read(bytes)) != -1) {
					bos.write(bytes, 0, len);
				}
				fis.close();
				bos.close();
				buffer = bos.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}

			result = new PrintResult();
			result.setFileName(pdfFileName);
			result.setFileType("pdf");
			//result.setBytes(buffer);
			String strBase64 = new BASE64Encoder().encode(buffer);//用jdk自带的sun
			result.setBase64Bytes(strBase64);
			result.setMD5(MD5Util.md5Encode(pdfFileName));

			response.setSuccess(true);
			response.setMessage("print Success");
			//response.addResult(result);
			response.setResult(result);
		}

		return toJsonStr(response, Arrays.asList(new Class<?>[] { PrintResult.class }));
		
	}


	private PrintRequest buildPrintLableRequest(String request) {
		JSONObject jsonObject = JSONObject.fromObject(request);
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("detail", DetailOrder.class);
		JsonConfig config = new JsonConfig();
		config.setRootClass(PrintRequest.class);
		config.setClassMap(classMap);
		ToLowerCasePropertyNameProcessor propertyNameProcessor = new ToLowerCasePropertyNameProcessor();
		config.registerJavaPropertyNameProcessor(PrintRequest.class,
				propertyNameProcessor);
		config.registerJavaPropertyNameProcessor(DetailOrder.class,
				propertyNameProcessor);
		return (PrintRequest) JSONObject.toBean(jsonObject, config);
	}
	public static void main(String[] args) throws Exception {
		//SmErpInterface sm = new SmErpInterface("http://www.1001000.com");//url = http://www.1001000.com,应该不是这个，被这个坑了
		SmErpInterface sm = new SmErpInterface("http://211.154.135.204/newbqwl/SmerpService.xsv");
			
		String result = null;
		result = sm.apiEntry(null, null);//http://www.1001000.com?action=channel可以访问
		System.out.println(result);
		//{"ChannelUrl":"http://211.154.135.204/newbqwl/SmerpService.xsv?action=channel","DescriptionUrl":"http://211.154.135.204/newbqwl/SmerpService.xsv?action=description","LableUrl":"http://211.154.135.204/newbqwl/SmerpService.xsv?action=print","PushUrl":"http://211.154.135.204/newbqwl/SmerpService.xsv?action=push"}

		String path = SmErpInterface.class.getResource("pushRequest2.txt").getPath();
		String request = FileUtils.readFileToString(new File(path), "UTF-8");
		result = sm.apiEntry(SmErpInterface.API_PUSH, request);
		System.out.println(result);
		
		result = sm.apiEntry(SmErpInterface.API_CHANNEL, null);
		System.out.println(result);
		
		 //description，描述
		result = sm.apiEntry(SmErpInterface.API_DESCRIPTION, null);
		System.out.println(result);
		
		
		String Lablepath = SmErpInterface.class.getResource("printLable.txt").getPath();
		request = FileUtils.readFileToString(new File(Lablepath), "UTF-8");
		result = sm.apiEntry(SmErpInterface.API_PRINTLABLE, null);
		System.out.println(result);

		
	}
	
}
