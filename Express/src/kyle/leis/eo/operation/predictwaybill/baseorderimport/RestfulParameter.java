package kyle.leis.eo.operation.predictwaybill.baseorderimport;

import java.io.StringWriter;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.*;

// 生成的json格式的 字段按定义时候的顺序
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

	
    
    // setter,getter方法，以及无参有惨构造方法 省略，
   public String toXml() {
        StringWriter sw = new StringWriter();
        JAXB.marshal(this, sw);
        return sw.toString();
    }
}