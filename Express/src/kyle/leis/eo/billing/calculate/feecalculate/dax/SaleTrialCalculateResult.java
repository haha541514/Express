package kyle.leis.eo.billing.calculate.feecalculate.dax;

import java.math.BigDecimal;


import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.hi.TdiProductkind;

public class SaleTrialCalculateResult {
	private String m_strPkcode;
	private String m_strChargeweight;
	private String m_strGrossweight;
	private String m_strVolumeweight;
	private String m_strFreightvalue;
	private String m_strSurchargevalue;
	private String m_strIncidentalvalue;
	private String m_strVolumerate;
	private String m_strFreightpriceRemark;
	private String m_strFreightcalcExp;
	private String m_strExpressStartdate;
	private String m_strExpressEnddate;
	private String m_strEpcode;
	private String m_strZoneName;
	private String m_strZonevalueName;
	private String m_strIntroductionlink;
	private String[] m_astrValues;
	
	
	public String getRemark(String pkcode) {
		try {
			TdiProductkind objTdiProductkind = TdiProductkindDC.loadByKey(pkcode);
			return objTdiProductkind.getPkRemark();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
	public String getIntroductionlink() {
		return m_strIntroductionlink;
	}

	public void setIntroductionlink(String strIntroductionlink) {
		m_strIntroductionlink = strIntroductionlink;
	}

	public SaleTrialCalculateResult() {
		m_astrValues = new String[17];
	}
	
	public String getPkcode() {
		try {
			TdiProductkind objTdiProductkind = TdiProductkindDC.loadByKey(m_strPkcode);
			return objTdiProductkind.getPkCode();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
	
	public void setPkcode(String strPkcode) {
		m_strPkcode = strPkcode;
	}
	
	public String getPkname() {
		try {
			TdiProductkind objTdiProductkind = TdiProductkindDC.loadByKey(m_strPkcode);
			return objTdiProductkind.getPkName();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
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
	
	public String getTotalpricevalue() {
		BigDecimal objTotalpricevalue = new BigDecimal("0");
		if (!StringUtility.isNull(m_strFreightvalue))
			objTotalpricevalue = objTotalpricevalue.add(new BigDecimal(m_strFreightvalue));
		if (!StringUtility.isNull(m_strSurchargevalue))
			objTotalpricevalue = objTotalpricevalue.add(new BigDecimal(m_strSurchargevalue));
		if (!StringUtility.isNull(m_strIncidentalvalue))
			objTotalpricevalue = objTotalpricevalue.add(new BigDecimal(m_strIncidentalvalue));
		return objTotalpricevalue.toString();
	}
	
	public String getChargeweight() {
		return m_strChargeweight;
	}
	
	public void setChargeweight(String strChargeweight) {
		m_strChargeweight = strChargeweight;
	}
	
	
	public void setGrossweight(String strGrossweight) {
		m_strGrossweight = strGrossweight;
	}	
	
	public String getGrossweight() {
		return m_strGrossweight;
	}
	
	public void setVolumeweight(String strVolumeweight) {
		m_strVolumeweight = strVolumeweight;
	}
	
	public String getVolumeweight() {
		return m_strVolumeweight;		
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
	
	public String[] toStringArray() {
		
		m_astrValues[0] = this.getChargeweight();
		m_astrValues[1] = this.getFreightvalue();
		m_astrValues[2] = this.getGrossweight();
		m_astrValues[3] = this.getIncidentalvalue();
		m_astrValues[4] = this.getSurchargevalue();
		m_astrValues[5] = this.getTotalpricevalue();
		m_astrValues[6] = this.getVolumeweight();
		m_astrValues[7] = this.getVolumerate();
		m_astrValues[8] = this.getFreightpriceRemark();
		m_astrValues[9] = this.getFreightcalcExp();		
		m_astrValues[10] = this.getPkcode();
		
		m_astrValues[11] = this.getExpressstartdate();
		m_astrValues[12] = this.getExpressenddate();
		m_astrValues[13] = this.getFreightprice();		
		m_astrValues[14] = this.getZonename();		
		m_astrValues[15] = this.getZonevaluename();
		m_astrValues[16] = this.getIntroductionlink();
		return m_astrValues;
	}	
}
