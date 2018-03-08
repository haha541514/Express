package kyle.leis.eo.operation.predictwaybill.baseorderimport;

import java.io.StringWriter;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.*;

// ���ɵ�json��ʽ�� �ֶΰ�����ʱ���˳��
@XmlAccessorOrder(XmlAccessOrder.UNDEFINED)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "message")
public class RestfulParameter {
	
	private String msgCode;
    private String msgValue;

    public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

	public String getMsgValue() {
		return msgValue;
	}

	public void setMsgValue(String msgValue) {
		this.msgValue = msgValue;
	}

	
    
    // setter,getter�������Լ��޲��вҹ��췽�� ʡ�ԣ�
   public String toXml() {
        StringWriter sw = new StringWriter();
        JAXB.marshal(this, sw);
        return sw.toString();
    }
}