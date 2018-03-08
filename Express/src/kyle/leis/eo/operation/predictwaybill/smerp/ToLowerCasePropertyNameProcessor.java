package kyle.leis.eo.operation.predictwaybill.smerp;

import net.sf.json.processors.PropertyNameProcessor;

/**
 * ����ĸתСд�������л�ʱʹ��
 * @author Administrator
 *
 */
public class ToLowerCasePropertyNameProcessor implements PropertyNameProcessor {

	@SuppressWarnings("unchecked")
	public String processPropertyName(Class arg0, String elementName) {
		//����ڶ�����ĸΪ��д�����
		if (elementName.length() > 1) {
			String secondChar = elementName.substring(1, 2);
			if (secondChar.toUpperCase().equals(secondChar)) {
				return elementName;
			}
		}
		return elementName.substring(0, 1).toLowerCase() + elementName.substring(1);
	}

}
