package kyle.leis.es.smsservice.bl;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * Œ¢–≈œ˚œ¢
 * 
 * @author Administrator
 * 
 */
public abstract class WeChatSms implements Sms {

	public String getContent() {
		Map<String, Object> conMap = new HashMap<String, Object>();
		conMap.put("action", getAction());
		conMap.put("data", getData());
		return JSONObject.fromObject(conMap).toString();
	}

	protected abstract String getAction();

	protected abstract Map<String, Object> getData();
}
