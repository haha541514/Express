package kyle.leis.es.price.incidentalprice.dax;

import java.util.List;

import kyle.common.connectors.util.Encoder;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceColumns;

public class LoadIncidentalResult {
	private IncidentalpriceColumns m_objIPriceColumns;
	private List m_listIPValueColumns;
	private List m_listIPVBaseColumns;
	private List m_listIPVChannelColumns;
	private List m_listEnterprise;
	
	public IncidentalpriceColumns getIPriceColumns() {
		return m_objIPriceColumns;
	}
	
	public void setIPriceColumns(IncidentalpriceColumns objIPriceColumns) {
		m_objIPriceColumns = objIPriceColumns;
	}
	
	public List getIPValueColumns() {
		return m_listIPValueColumns;
	}
	
	public void setIPValueColumns(List listIPValueColumns) {
		m_listIPValueColumns = listIPValueColumns;
	}
	
	public List getIPVBaseColumns() {
		return m_listIPVBaseColumns;
	}
	
	public void setIPVBaseColumns(List listIPVBaseColumns) {
		m_listIPVBaseColumns = listIPVBaseColumns;
	}
	
	public List getIPVChannelColumns() {
		return m_listIPVChannelColumns;
	}
	
	public void setIPVChannelColumns(List listIPVChannelColumns) {
		m_listIPVChannelColumns = listIPVChannelColumns;
	}	
	
	public List getEnterprise() {
		return m_listEnterprise;
	}
	
	public void setEnterprise(List listEnterprise) {
		m_listEnterprise = listEnterprise;
	}	
	
	public String toString() {
		Encoder objEncode = new Encoder();
		objEncode.addParameter(m_objIPriceColumns);
		objEncode.addParameter(m_listIPValueColumns);
		objEncode.addParameter(m_listIPVBaseColumns);
		objEncode.addParameter(m_listIPVChannelColumns);
		objEncode.addParameter(m_listEnterprise);
		return objEncode.toString();
	}

}
