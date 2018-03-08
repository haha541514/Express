package kyle.leis.eo.billing.calculate.feecalculate.dax;

import java.util.ArrayList;

import kyle.leis.es.price.freightprice.da.FreightpriceColumns;

public class FeeCalculateResult {
	private String m_strUtcode;
	private String m_strFkcode;
	private String m_strReversesign;
	private String m_strMinimumvalue;
	private String m_strMaxvalue;
	private String m_strBasevalue;
	private String m_strPricevalue;
	private String m_strCkcode;
	private String m_strCurrencyrate;
	private String m_strRemark;
	private String m_strEpcode;
	private String m_strEpvid;
	private String m_strPdcode;
	private String m_strUnitnumber;
	private String m_strCommissionrate;
	private String m_strChargeweight;
	private String m_strEstcode;
	private String m_strStoreChannelcode;
	private String m_strFreightpriceRemark;
	private String m_strFreightcalcExp;
	private String m_strCarryweight;
	private ArrayList<String> m_alBaseFkcode;
	private FreightpriceColumns m_objFPriceColumns;
	
	public String getUtcode() {
		return m_strUtcode;
	}
	
	public void setUtcode(String strUtcode) {
		m_strUtcode = strUtcode;
	}
	
	public String getFkcode() {
		return m_strFkcode;
	}
	
	public void setFkcode(String strFkcode) {
		m_strFkcode = strFkcode;
	}
	
	public String getReversesign() {
		return m_strReversesign;
	}
	
	public void setReversesign(String strReversesign) {
		m_strReversesign = strReversesign;
	}
	
	public String getMinimumvalue() {
		return m_strMinimumvalue;
	}
	
	public void setMinimumvalue(String strMinimumvalue) {
		m_strMinimumvalue = strMinimumvalue;
	}	
	
	public String getMaxvalue() {
		return m_strMaxvalue;
	}
	
	public void setMaxvalue(String strMaxvalue) {
		m_strMaxvalue = strMaxvalue;
	}		
	
	public String getBasevalue() {
		return m_strBasevalue;
	}
	
	public void setBasevalue(String strBasevalue) {
		m_strBasevalue = strBasevalue;
	}		
	
	public String getPricevalue() {
		return m_strPricevalue;
	}
	
	public void setPricevalue(String strPricevalue) {
		m_strPricevalue = strPricevalue;
	}
	
	public String getCkcode() {
		return m_strCkcode;
	}
	
	public void setCkcode(String strCkcode) {
		m_strCkcode = strCkcode;
	}

	public String getCurrencyrate() {
		return m_strCurrencyrate;
	}
	
	public void setCurrencyrate(String strCurrencyrate) {
		m_strCurrencyrate = strCurrencyrate;
	}	
	
	public String getRemark() {
		return m_strRemark;
	}
	
	public void setRemark(String strRemark) {
		m_strRemark = strRemark;
	}
	
	public String getEpcode() {
		return m_strEpcode;
	}
	
	public void setEpcode(String strEpcode) {
		m_strEpcode = strEpcode;
	}
	
	public String getEpvid() {
		return m_strEpvid;
	}
	
	public void setEpvid(String strEpvid) {
		m_strEpvid = strEpvid;
	}
	
	public String getPdcode() {
		return m_strPdcode;
	}
	
	public void setPdcode(String strPdcode) {
		m_strPdcode = strPdcode;
	}	
	
	public String getUnitnumber() {
		return m_strUnitnumber;
	}
	
	public void setUnitnumber(String strUnitnumber) {
		m_strUnitnumber = strUnitnumber;
	}
	
	public String getCommissionrate() {
		return m_strCommissionrate;
	}
	
	public void setCommissionirate(String strCommissionrate) {
		m_strCommissionrate = strCommissionrate;
	}
	
	public String getChargeweight() {
		return m_strChargeweight;
	}
	
	public void setChargeweight(String strChargeweight) {
		m_strChargeweight = strChargeweight;
	}
	
	public String getEstcode() {
		return m_strEstcode;
	}
	
	public void setEstcode(String strEstcode) {
		m_strEstcode = strEstcode;
	}
	
	public ArrayList<String> getBaseFkcode() {
		return m_alBaseFkcode;
	}
	
	public void setBaseFkcode(ArrayList<String> alBaseFkcode) {
		m_alBaseFkcode = alBaseFkcode;
	}
	
	public String getStorechannelcode() {
		return m_strStoreChannelcode;
	}
	
	public void setStorechannelcode(String strStoreChannelcode) {
		m_strStoreChannelcode = strStoreChannelcode;
	}
	
	public String getFreightpriceRemark() {
		return m_strFreightpriceRemark;
	}
	
	public void setFreightpriceRemark(String strFreightpriceRemark) {
		m_strFreightpriceRemark = strFreightpriceRemark;
	}	
	
	public String getFreightcalcExp() {
		return m_strFreightcalcExp;
	}
	
	public void setFreightcalcExp(String strFreightcalcExp) {
		m_strFreightcalcExp = strFreightcalcExp;
	}	
	
	public String getCarryweigh() {
		return m_strCarryweight;
	}
	
	public void setCarryweigh(String strCarryweigh) {
		m_strCarryweight = strCarryweigh;
	}	
	
	public FreightpriceColumns getFreightpriceColumns() {
		return m_objFPriceColumns;
	}
	
	public void setFreightpriceColumns(FreightpriceColumns objFPriceColumns) {
		m_objFPriceColumns = objFPriceColumns;
	}
	
	public FeeCalculateResult copy() {
		FeeCalculateResult objFCResult = new FeeCalculateResult();
		objFCResult.setBaseFkcode(this.getBaseFkcode());
		objFCResult.setBasevalue(this.getBasevalue());
		objFCResult.setChargeweight(this.getChargeweight());
		objFCResult.setCkcode(this.getCkcode());
		objFCResult.setCommissionirate(this.getCommissionrate());
		objFCResult.setCurrencyrate(this.getCurrencyrate());
		objFCResult.setEpcode(this.getEpcode());
		objFCResult.setEpvid(this.getEpvid());
		objFCResult.setEstcode(this.getEstcode());
		objFCResult.setFkcode(this.getFkcode());
		objFCResult.setMinimumvalue(this.getMinimumvalue());
		objFCResult.setMaxvalue(this.getMaxvalue());
		objFCResult.setPdcode(this.getPdcode());
		objFCResult.setPricevalue(this.getPricevalue());
		objFCResult.setRemark(this.getRemark());
		objFCResult.setReversesign(this.getReversesign());
		objFCResult.setUnitnumber(this.getUnitnumber());
		objFCResult.setUtcode(this.getUtcode());
		objFCResult.setStorechannelcode(this.getStorechannelcode());
		objFCResult.setFreightpriceRemark(this.getFreightpriceRemark());
		objFCResult.setFreightcalcExp(this.getFreightcalcExp());
		objFCResult.setFreightpriceColumns(this.getFreightpriceColumns());
		objFCResult.setCarryweigh(this.getCarryweigh());
		return objFCResult;
	}
	
}
