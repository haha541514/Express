package kyle.leis.eo.operation.predictwaybill.smerp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import kyle.leis.eo.operation.predictwaybill.smerp.vo.Order;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.Product;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.PushRequest;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.Receiver;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;

public class PushRequestTest {
	public static void main(String[] args) throws IOException {
		String path = PushRequestTest.class.getResource("pushRequest.txt").getPath();
		System.out.println(path);
		String json = FileUtils.readFileToString(new File(path), "GBK");
		JSONObject jsonObject = JSONObject.fromObject(json);
		
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
		
		Object object = JSONObject.toBean(jsonObject, config);
		if (object instanceof PushRequest) {
			PushRequest pushRequest = (PushRequest) object;
			System.out.println(pushRequest.getOrderList().size());
			System.out.println(pushRequest.getOrderList().get(0).getCustomsName());
			System.out.println(pushRequest.getOrderList().get(0).getReceiver().getCountry());
			System.out.println(pushRequest.getOrderList().get(0).getProductList().size());
			System.out.println(pushRequest.getOrderList().get(0).getProductList().get(0).getDisplayName());
		}
		
		
	}
}
