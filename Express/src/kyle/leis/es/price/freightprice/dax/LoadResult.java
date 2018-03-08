package kyle.leis.es.price.freightprice.dax;

import java.util.List;

import kyle.common.connectors.util.Encoder;

public class LoadResult {
	private List m_listFPColumns;
	private List m_listFPVColumns;
	private List m_listSVColumns;
	private List m_listSVBaseColumns;
	private List m_listEnterprise;
	
	public List getFreightPriceColumns() {
		return m_listFPColumns;
	}
	
	public void setFreightPriceColumns(List listFPColumns) {
		m_listFPColumns = listFPColumns;
	}
	
	public List getFreightValueColumns() {
		return m_listFPVColumns;
	}
	
	public void setFreightValueColumns(List listFPVColumns) {
		m_listFPVColumns = listFPVColumns;
	}
	
	public List getSurchargeValueColumns() {
		return m_listSVColumns;
	}
	
	public void setSurchargeValueColumns(List listSVColumns) {
		m_listSVColumns = listSVColumns;
	}
	
	public List getSurchargeBaseColumns() {
		return m_listSVBaseColumns;
	}
	
	public void setSurchargeBaseColumns(List listSVBaseColumns) {
		m_listSVBaseColumns = listSVBaseColumns;
	}
	
	public List getEnterprise() {
		return m_listEnterprise;
	}
	
	public void setEnterprise(List listEnterprise) {
		m_listEnterprise = listEnterprise;
	}	
	
	public String toString() {
		Encoder objEncode = new Encoder();
		
		objEncode.addParameter(m_listFPColumns);
		objEncode.addParameter(m_listFPVColumns);
		objEncode.addParameter(m_listSVColumns);
		objEncode.addParameter(m_listSVBaseColumns);
		objEncode.addParameter(m_listEnterprise);
		
		return objEncode.toString();
	}
}
