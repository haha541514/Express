package kyle.leis.es.price.zone.dax;

import java.util.List;

import kyle.common.connectors.util.Encoder;
import kyle.leis.es.price.zone.da.ZoneColumns;

public class ZoneQueryReturn {
	private ZoneColumns m_objZoneColumns; 
	private List m_listZoneValue;
	private List m_listZoneValueDistrict;
	private List m_listZoneDistrictPostcode;

	public ZoneColumns getZoneColumns() {
		return m_objZoneColumns;
	}
	
	public void setZoneColumns(ZoneColumns objZoneColumns) {
		m_objZoneColumns = objZoneColumns;
	}	
	
	public List getZoneValue() {
		return m_listZoneValue;
	}
	
	public void setZoneValue(List listZoneValue) {
		m_listZoneValue = listZoneValue;
	}
	
	public List getZoneValueDistrict() {
		return m_listZoneValueDistrict;
	}
	
	public void setZoneValueDistrict(List listZoneValueDistrict) {
		m_listZoneValueDistrict = listZoneValueDistrict;
	}
	
	public List getZoneDistrictPostcode() {
		return m_listZoneDistrictPostcode;
	}
	
	public void setZoneDistrictPostcode(List listZoneDistrictPostcode) {
		m_listZoneDistrictPostcode = listZoneDistrictPostcode;
	}
	
	public String toString() {
		Encoder objEncode = new Encoder();
		objEncode.addParameter(m_objZoneColumns);
		objEncode.addParameter(m_listZoneValue);
		objEncode.addParameter(m_listZoneValueDistrict);
		objEncode.addParameter(m_listZoneDistrictPostcode);
		return objEncode.toString();
	}
	
}
