package kyle.leis.eo.operation.housewaybill.da;

import java.util.List;

import kyle.common.util.prompt.PromptUtilityCollection;


public class InputAllQReturn {
	private List m_listHouseWayBill;
	private List m_listCargoInfo;
	private List m_listPieces;
	private String m_strErrCustomerEWBCode;
	private PromptUtilityCollection m_objPUCollection;
	
	public void setErrCustomerEWBCode(String strErrCustomerEWBCode) {
		m_strErrCustomerEWBCode = strErrCustomerEWBCode;
	}
	
	public String getErrCustomerEWBCode() {
		return m_strErrCustomerEWBCode;
	}	
	
	public void setHWBResults(List listHouseWayBill) {
		m_listHouseWayBill = listHouseWayBill;
	}
	
	public List getHWBResults() {
		return m_listHouseWayBill;
	}
	
	public void setCargoInfoResults(List listCargoInfo) {
		m_listCargoInfo = listCargoInfo;
	}
	
	public List getCargoInfoResults() {
		return m_listCargoInfo;
	}
	
	public void setPieces(List listPieces) {
		m_listPieces = listPieces;
	}
	
	public List getPieces() {
		return m_listPieces;
	}	
	
	
	public void setPromptUtilityCollection(PromptUtilityCollection objPUCollection) {
		m_objPUCollection = objPUCollection;
	}
	
	public PromptUtilityCollection getPromptUtilityCollection() {
		return m_objPUCollection;
	}
}
