package kyle.leis.eo.operation.predictwaybill.smerp;

import net.sf.json.processors.PropertyNameProcessor;

/**
 * ����ĸת��д�����л�ʱʹ��
 * @author Administrator
 *
 */
public class ToUpperCasePropertyNameProcessor implements PropertyNameProcessor {

	@SuppressWarnings("unchecked")
	public String processPropertyName(Class clazz, String property) {
		try {
			String fieldName = property.substring(0, 1).toUpperCase() 
							+ property.substring(1);
			return clazz.getDeclaredField(fieldName).getName();
		} catch (Exception e) {
			return property;
		}
	}

}
