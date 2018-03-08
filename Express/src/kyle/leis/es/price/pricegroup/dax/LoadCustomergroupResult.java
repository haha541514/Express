package kyle.leis.es.price.pricegroup.dax;

import java.util.List;

import kyle.common.connectors.util.Encoder;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupColumns;

public class LoadCustomergroupResult {
	private CustomerpricegroupColumns m_objCPGColumns;
	private List m_listCPGValueColumns;
	
	public CustomerpricegroupColumns getCPGColumns() {
		return m_objCPGColumns;
	}
	
	public void setCPGColumns(CustomerpricegroupColumns objCPGColumns) {
		m_objCPGColumns = objCPGColumns;
	}
	
	public List getCPGValueColumns() {
		return m_listCPGValueColumns;
	}
	
	public void setCPGValueColumns(List listCPGValueColumns) {
		m_listCPGValueColumns = listCPGValueColumns;
	}
	
	public String toString() {
		Encoder objEncode = new Encoder();
		objEncode.addParameter(m_objCPGColumns);
		objEncode.addParameter(m_listCPGValueColumns);
		return objEncode.toString();
	}
}
