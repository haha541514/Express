package kyle.leis.eo.billing.calculate.feecalculate.dax;

import java.util.List;

import kyle.common.dbaccess.query.GeneralColumns;
import kyle.common.util.jlang.StringUtility;

public class FeeCalculateParameter extends GeneralColumns {
	private List m_listSpecialtype;
	
	public FeeCalculateParameter() {
		m_astrColumns = new String[14];
	}
	
	public String getPostcode() {
		return this.getField(0);
	}
	
	public void setPostcode(String strPostcode) {
		this.setField(0, strPostcode);
	}
	
	public String getGrossWeight() {
		return this.getField(1);
	}
	
	public void setGrossWeight(String strGrossWeight) {
		this.setField(1, strGrossWeight);
	}
	
	public String getVolumeWeight() {
		return this.getField(2);
	}
	
	public void setVolumeWeight(String strVolumeWeight) {
		this.setField(2, strVolumeWeight);
	}
	
	public String getChargeWeight() {
		return this.getField(3);
	}
	
	public void setChargeWeight(String strChargeWeight) {
		this.setField(3, strChargeWeight);
	}

	public String getPieces() {
		return this.getField(4);
	}
	
	public void setPieces(String strPieces) {
		this.setField(4, strPieces);
	}
	
	public String getCtcode() {
		return this.getField(5);
	}
	
	public void setCtcode(String strCtcode) {
		this.setField(5, strCtcode);
	}
	
	public String getPmcode() {
		return this.getField(6);
	}
	
	public void setPmcode(String strPmcode) {
		this.setField(6, strPmcode);
	}
	
	public String getCwcode() {
		return this.getField(7);
	}
	
	public void setCwcode(String strCwcode) {
		this.setField(7, strCwcode);
	}
	
	public String getDtcode() {
		return this.getField(8);
	}
	
	public void setDtcode(String strDtcode) {
		this.setField(8, strDtcode);
	}
	
	public String getBkcode() {
		return this.getField(9);
	}
	
	public void setBkcode(String strBkcode) {
		this.setField(9, strBkcode);
	}	
	
	public String getOriginVolumerate() {
		return this.getField(10); 
	}
	
	public void setOriginVolumerate(String strVolumerate) {
		this.setField(10, strVolumerate); 
	}
	
	public String getBillcounts() {
		return this.getField(11); 
	}
	
	public void setBillcounts(String strBillcounts) {
		this.setField(11, strBillcounts); 
	}	
	
	public List getSpecialtypeRecords() {
		return m_listSpecialtype;
	}
	
	public void setSpecialtypeRecords(List listSpecialtype) {
		m_listSpecialtype = listSpecialtype;
	}
	
	public String getTotalDeclarevalue() {
		if (StringUtility.isNull(this.getField(12)))
			return "0";
		else
			return this.getField(12); 
	}
	
	public void setTotalDeclarevalue(String strTotalDeclarevalue) {
		this.setField(12, strTotalDeclarevalue); 
	}
	
	public String getDeclareCurrencyRate() {
		if (StringUtility.isNull(this.getField(13)))
			return "0";
		else
			return this.getField(13); 
	}
	
	public void setDeclareCurrencyRate(String strDeclareCurrencyRat) {
		this.setField(13, strDeclareCurrencyRat); 
	}	
	
}
