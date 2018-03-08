package kyle.leis.eo.operation.channelsearch.dax;

import java.math.BigDecimal;

import kyle.common.util.jlang.StringUtility;

public class ChannelSearchResult {
	private String m_strChncode;
	private String m_strLevelweight;
	private String m_strChargeweight;
	private String m_strCarriedweight;
	private String m_strGrossweight;
	private String m_strVolumeweight;
	private String m_strFreightvalue;
	private String m_strSurchargevalue;
	private String m_strIncidentalvalue;
	private String m_strAdjustivevalue;
	private String m_strOptimalsign = "N";
	private String m_strRelativevalue;
	private String m_strCalcCWExpression;
	private String m_strVolumerate;
	private String m_strFreightpriceRemark;
	private String m_strFreightcalcExp;
	private String m_strResultchargeweight;
	
	private String m_strExpressStartdate;
	private String m_strExpressEnddate;
	private String m_strEpcode;
	private String m_strZoneName;
	private String m_strZonevalueName;	
	private String m_strServerweightrulekind;
	
	private String[] m_astrValues;
	
	public ChannelSearchResult() {
		m_astrValues = new String[22];
	}
	
	public String getChncode() {
		return m_strChncode;
	}
	
	public void setChncode(String strChncode) {
		m_strChncode = strChncode;
	}
	
	public String getFreightvalue() {
		return m_strFreightvalue;
	}
	
	public void setFreightvalue(String strFreightvalue) {
		m_strFreightvalue = strFreightvalue;
	}	
	
	public String getSurchargevalue() {
		return m_strSurchargevalue;
	}
	
	public void setSurchargevalue(String strSurchargevalue) {
		m_strSurchargevalue = strSurchargevalue;
	}
	
	public String getIncidentalvalue() {
		return m_strIncidentalvalue;
	}
	
	public void setIncidentalvalue(String strIncidentalvalue) {
		m_strIncidentalvalue = strIncidentalvalue;
	}		
	
	public String getAdjustivevalue() {
		if (StringUtility.isNull(m_strAdjustivevalue))
			return "0";
		return m_strAdjustivevalue;
	}
	
	public void setAdjustivevalue(String strAdjustivevalue) {
		m_strAdjustivevalue = strAdjustivevalue;
	}	
	
	
	public String getTotalpricevalue() {
		BigDecimal objTotalpricevalue = new BigDecimal("0");
		if (!StringUtility.isNull(m_strFreightvalue))
			objTotalpricevalue = objTotalpricevalue.add(new BigDecimal(m_strFreightvalue));
		if (!StringUtility.isNull(m_strSurchargevalue))
			objTotalpricevalue = objTotalpricevalue.add(new BigDecimal(m_strSurchargevalue));
		if (!StringUtility.isNull(m_strIncidentalvalue))
			objTotalpricevalue = objTotalpricevalue.add(new BigDecimal(m_strIncidentalvalue));
		if (!StringUtility.isNull(m_strAdjustivevalue))
			objTotalpricevalue = objTotalpricevalue.add(new BigDecimal(m_strAdjustivevalue));		
		return objTotalpricevalue.toString();
	}
	
	
	public String getChargeweight() {
		return m_strChargeweight;
	}
	
	public void setChargeweight(String strChargeweight) {
		m_strChargeweight = strChargeweight;
	}
	
	public String getLevelweight() {
		return m_strLevelweight;
	}
	
	public void setLevelweight(String strLevelweight) {
		m_strLevelweight = strLevelweight;
	}
	
	public String getResultchargeweight() {
		return m_strResultchargeweight;
	}
	
	public void setResultchargeweight(String strResultchargeweight) {
		m_strResultchargeweight = strResultchargeweight;
	}
	
	public void setGrossweight(String strGrossweight) {
		m_strGrossweight = strGrossweight;
	}	
	
	public String getGrossweight() {
		if (!StringUtility.isNull(m_strCalcCWExpression) &&
				m_strCalcCWExpression.trim().toLowerCase().equals("gw"))
			return m_strLevelweight;
		if (StringUtility.isNull(m_strCalcCWExpression))
			return m_strLevelweight;
		return m_strGrossweight;
	}
	
	public void setVolumeweight(String strVolumeweight) {
		m_strVolumeweight = strVolumeweight;
	}
	
	public String getVolumeweight() {
		if (!StringUtility.isNull(m_strCalcCWExpression) &&
				m_strCalcCWExpression.trim().toLowerCase().equals("gw"))
			return m_strLevelweight;
		return m_strVolumeweight;		
	}
	
	public void setCalcCWExpression(String strCalcCWExpression) {
		m_strCalcCWExpression = strCalcCWExpression;
	}	
	
	public String getOptimalsign() {
		return m_strOptimalsign;
	}	
	
	public void setOptimalsign(String strOptimalsign) {
		m_strOptimalsign = strOptimalsign;
	}
	
	public String getRelativevalue() {
		return m_strRelativevalue;
	}	

	public void setRelativevalue(String strRelativevalue) {
		m_strRelativevalue = strRelativevalue;
	}
	
	public String getVolumerate() {
		return m_strVolumerate;
	}
	
	public void setVolumerate(String strVolumerate) {
		m_strVolumerate = strVolumerate;
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
	
	public String getExpressstartdate() {
		return m_strExpressStartdate;
	}
	
	public void setExpressstartdate(String strExpressStartdate) {
		m_strExpressStartdate = strExpressStartdate;
	}	
	
	public String getExpressenddate() {
		return m_strExpressEnddate;
	}
	
	public void setExpressenddate(String strExpressEnddate) {
		m_strExpressEnddate = strExpressEnddate;
	}
	
	public String getFreightprice() {
		return m_strEpcode;
	}
	
	public void setFreightprice(String strEpcode) {
		m_strEpcode = strEpcode;
	}	
	
	public String getZonename() {
		return m_strZoneName;
	}
	
	public void setZonename(String strZoneName) {
		m_strZoneName = strZoneName;
	}	
	
	public String getZonevaluename() {
		return m_strZonevalueName;
	}
	
	public void setZonevaluename(String strZonevalueName) {
		m_strZonevalueName = strZonevalueName;
	}	
	
	public void setServerweightrulekind(String strServerweightrulekind) {
		m_strServerweightrulekind = strServerweightrulekind;
	}
	
	public String getServerweightrulekind() {
		return m_strServerweightrulekind;
	}	
	
	public void setCarriedweight(String strCarriedweight) {
		m_strCarriedweight = strCarriedweight;
	}
	
	public String getCarriedweight() {
		return m_strCarriedweight;
	}	
	
	
	public String[] toStringArray() {
		
		m_astrValues[0] = this.getChargeweight();
		m_astrValues[1] = this.getChncode();
		m_astrValues[2] = this.getFreightvalue();
		m_astrValues[3] = this.getGrossweight();
		m_astrValues[4] = this.getIncidentalvalue();
		m_astrValues[5] = this.getLevelweight();
		m_astrValues[6] = this.getOptimalsign();
		m_astrValues[7] = this.getRelativevalue();
		m_astrValues[8] = this.getSurchargevalue();
		m_astrValues[9] = this.getTotalpricevalue();
		m_astrValues[10] = this.getVolumeweight();
		m_astrValues[11] = this.getVolumerate();
		m_astrValues[12] = this.getFreightpriceRemark();
		m_astrValues[13] = this.getFreightcalcExp();		
		
		m_astrValues[14] = this.getExpressstartdate();
		m_astrValues[15] = this.getExpressenddate();
		m_astrValues[16] = this.getFreightprice();		
		m_astrValues[17] = this.getZonename();		
		m_astrValues[18] = this.getZonevaluename();		
		m_astrValues[19] = this.getAdjustivevalue();
		
		m_astrValues[20] = this.getServerweightrulekind();
		m_astrValues[21] = this.getCarriedweight();
		
		return m_astrValues;
	}
}
