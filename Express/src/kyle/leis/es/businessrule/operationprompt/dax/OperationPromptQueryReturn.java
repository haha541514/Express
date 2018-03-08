package kyle.leis.es.businessrule.operationprompt.dax;

import java.util.List;

import kyle.common.connectors.util.Encoder;
import kyle.leis.es.businessrule.operationprompt.da.OperationpromptColumns;

public class OperationPromptQueryReturn {
	private OperationpromptColumns m_objOPTColumns;
	private List m_listCustomer;
	private List m_listSupplier;
//	private List m_listCsChannel;
	private List m_listSpChannel;
	private List m_listExpressSpecialType;
	private List m_listOperationTache;
	private List m_listOriginDistrict;
	private List m_listDepartureDistrict;
	
	
	public OperationpromptColumns getM_objOPTColumns() {
		return m_objOPTColumns;
	}


	public void setM_objOPTColumns(OperationpromptColumns columns) {
		m_objOPTColumns = columns;
	}



	public List getM_listCustomer() {
		return m_listCustomer;
	}



	public void setM_listCustomer(List customer) {
		m_listCustomer = customer;
	}



	public List getM_listSupplier() {
		return m_listSupplier;
	}



	public void setM_listSupplier(List supplier) {
		m_listSupplier = supplier;
	}



	/*public List getM_listCsChannel() {
		return m_listCsChannel;
	}



	public void setM_listCsChannel(List csChannel) {
		m_listCsChannel = csChannel;
	}
*/


	public List getM_listSpChannel() {
		return m_listSpChannel;
	}



	public void setM_listSpChannel(List spChannel) {
		m_listSpChannel = spChannel;
	}



	public List getM_listExpressSpecialType() {
		return m_listExpressSpecialType;
	}



	public void setM_listExpressSpecialType(List expressSpecialType) {
		m_listExpressSpecialType = expressSpecialType;
	}



	public List getM_listOperationTache() {
		return m_listOperationTache;
	}



	public void setM_listOperationTache(List operationTache) {
		m_listOperationTache = operationTache;
	}



	public List getM_listOriginDistrict() {
		return m_listOriginDistrict;
	}



	public void setM_listOriginDistrict(List originDistrict) {
		m_listOriginDistrict = originDistrict;
	}



	public List getM_listDepartureDistrict() {
		return m_listDepartureDistrict;
	}



	public void setM_listDepartureDistrict(List departureDistrict) {
		m_listDepartureDistrict = departureDistrict;
	}



	public String toString()
	{
		Encoder objEncode = new Encoder();
		objEncode.addParameter(m_objOPTColumns);
		objEncode.addParameter(m_listCustomer);
		objEncode.addParameter(m_listSupplier);
//		objEncode.addParameter(m_listCsChannel);
		objEncode.addParameter(m_listSpChannel);
		objEncode.addParameter(m_listExpressSpecialType);
		objEncode.addParameter(m_listOperationTache);
		objEncode.addParameter(m_listOriginDistrict);
		objEncode.addParameter(m_listDepartureDistrict);
		return objEncode.toString();
	}

}
