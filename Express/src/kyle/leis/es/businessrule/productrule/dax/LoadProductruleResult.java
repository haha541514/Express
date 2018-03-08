package kyle.leis.es.businessrule.productrule.dax;

import java.util.List;

import kyle.common.connectors.util.Encoder;
import kyle.leis.es.businessrule.productrule.da.ProductruleColumns;

public class LoadProductruleResult {
	private List m_listCorporationPRColumns;	
	private List m_listDistrictPRColumns;
	private List m_listChnPRColumns;
	private ProductruleColumns m_objProductruleColumns;
	
	public List getCorporationPRColumns() {
		return m_listCorporationPRColumns;
	}

	public void setCorporationPRColumns(List listCorporationPRColumns) {
		m_listCorporationPRColumns = listCorporationPRColumns;
	}

	public List getDistrictPRColumns() {
		return m_listDistrictPRColumns;
	}
	
	public void setDistrictPRColumns(List listDistrictPRColumns) {
		m_listDistrictPRColumns = listDistrictPRColumns;
	}
	
	public List getChnPRColumns() {
		return m_listChnPRColumns;
	}
	
	public void setChnPRColumns(List listChnPRColumns) {
		m_listChnPRColumns = listChnPRColumns;
	}
	
	public ProductruleColumns getProductruleColumns() {
		return m_objProductruleColumns;
	}
	
	public void setProductruleColumns(ProductruleColumns objProductruleColumns) {
		m_objProductruleColumns = objProductruleColumns;
	}
	
	public String toString() {
		Encoder objEncode = new Encoder();		
		objEncode.addParameter(m_listDistrictPRColumns);
		objEncode.addParameter(m_listChnPRColumns);
		objEncode.addParameter(m_objProductruleColumns);
		objEncode.addParameter(m_listCorporationPRColumns);
		return objEncode.toString();
	}
}
