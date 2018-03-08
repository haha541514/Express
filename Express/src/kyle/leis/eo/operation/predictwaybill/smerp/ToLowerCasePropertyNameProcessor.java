package kyle.leis.eo.operation.predictwaybill.smerp;

import net.sf.json.processors.PropertyNameProcessor;

/**
 * 首字母转小写，反序列化时使用
 * @author Administrator
 *
 */
public class ToLowerCasePropertyNameProcessor implements PropertyNameProcessor {

	@SuppressWarnings("unchecked")
	public String processPropertyName(Class arg0, String elementName) {
		//处理第二个字母为大写的情况
		if (elementName.length() > 1) {
			String secondChar = elementName.substring(1, 2);
			if (secondChar.toUpperCase().equals(secondChar)) {
				return elementName;
			}
		}
		return elementName.substring(0, 1).toLowerCase() + elementName.substring(1);
	}

}
