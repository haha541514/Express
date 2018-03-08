package kyle.leis.eo.billing.calculate.feecalculate.dax;

import java.util.List;

public class SaleTrialCalculateParameter {
	private String m_strPkcode;
	private String m_strCocode;
	private String m_strCtcode;
	private String m_strPmcode;
	private String m_strOrginDtcode;
	private String m_strDestDtcode;
	private String m_strGrossweight;
	private String m_strPostcode;
	private List m_listPieces;
	
	public SaleTrialCalculateParameter() {
		
	}
	
	public SaleTrialCalculateParameter(String[] astrParameter) {
		if (astrParameter == null || astrParameter.length < 1)
			return;
		this.setPkcode(astrParameter[0]);
		this.setCocode(astrParameter[1]);
		this.setCtcode(astrParameter[2]);
		this.setPmcode(astrParameter[3]);
		this.setOrginDtcode(astrParameter[4]);
		this.setDestDtcode(astrParameter[5]);
		this.setGrossweight(astrParameter[6]);
		this.setPostcode(astrParameter[7]);
	}
	
	
	public void setPkcode(String strPkcode) {
		m_strPkcode = strPkcode;
	}
	
	public String getPkcode() {
		return m_strPkcode;
	}	
	
	public void setCocode(String strCocode) {
		m_strCocode = strCocode;
	}
	
	public String getCocode() {
		return m_strCocode;
	}	
	
	public void setCtcode(String strCtcode) {
		m_strCtcode = strCtcode;
	}
	
	public String getCtcode() {
		return m_strCtcode;
	}	
	
	public void setPmcode(String strPmcode) {
		m_strPmcode = strPmcode;
	}
	
	public String getPmcode() {
		return m_strPmcode;
	}	
	
	public void setOrginDtcode(String strOrginDtcode) {
		m_strOrginDtcode = strOrginDtcode;
	}
	
	public String getOrginDtcode() {
		return m_strOrginDtcode;
	}	
	
	public void setDestDtcode(String strDestDtcode) {
		m_strDestDtcode = strDestDtcode;
	}
	
	public String getDestDtcode() {
		return m_strDestDtcode;
	}		

	public void setGrossweight(String strGrossweight) {
		m_strGrossweight = strGrossweight;
	}
	
	public String getGrossweight() {
		return m_strGrossweight;
	}	
	
	public void setPostcode(String strPostcode) {
		m_strPostcode = strPostcode;
	}
	
	public String getPostcode() {
		return m_strPostcode;
	}	
	
	public void setPiecesList(List listPieces) {
		m_listPieces = listPieces;
	}
	
	public List getPiecesList() {
		return m_listPieces;
	}
}
