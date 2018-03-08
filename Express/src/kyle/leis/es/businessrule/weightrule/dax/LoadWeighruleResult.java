package kyle.leis.es.businessrule.weightrule.dax;

import java.util.List;

import kyle.common.connectors.util.Encoder;
import kyle.leis.es.businessrule.weightrule.da.WeightruleColumns;

public class LoadWeighruleResult {
	private WeightruleColumns m_objWeightruleColumns;
	private List m_listWeightrulevalue;
	private List m_listVolumeweightrulevalue;
	private List m_listCarryweightrulevalue;
	
	public WeightruleColumns getWeightruleColumns() {
		return m_objWeightruleColumns;
	}
	
	public void setWeightruleColumns(WeightruleColumns objWeightruleColumns) {
		m_objWeightruleColumns = objWeightruleColumns;
	}
	
	public List getWeightruleValue() {
		return m_listWeightrulevalue;
	}
	
	public void setWeightruleValue(List listWeightrulevalue) {
		m_listWeightrulevalue = listWeightrulevalue;
	}
	
	public List getVolumeweightrulevalue() {
		return m_listVolumeweightrulevalue;
	}
	
	public void setVolumeweightrulevalue(List listVolumeweightrulevalue) {
		m_listVolumeweightrulevalue = listVolumeweightrulevalue;
	}
	
	public List getCarryweightrulevalue() {
		return m_listCarryweightrulevalue;
	}
	
	public void setCarryweightrulevalue(List listCarryweightrulevalue) {
		m_listCarryweightrulevalue = listCarryweightrulevalue;
	}
	
	public String toString() {
		Encoder objEncode = new Encoder();
		
		objEncode.addParameter(m_objWeightruleColumns);
		objEncode.addParameter(m_listWeightrulevalue);
		objEncode.addParameter(m_listVolumeweightrulevalue);
		objEncode.addParameter(m_listCarryweightrulevalue);
		
		return objEncode.toString();
	}
}
