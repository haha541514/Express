package kyle.leis.eo.billing.payable.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PayableforbillCondition extends GeneralCondition {
	public PayableforbillCondition() {
		m_astrConditions = new String[42];
	}	
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setCwcustomerewbcode(String cwCustomerewbcode) {
		this.setField(1, cwCustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(1);
	}

	public void setCwserverewbcode(String cwServerewbcode) {
		this.setField(2, cwServerewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(2);
	}

	public void setCwewbcode(String cwEwbcode) {
		this.setField(3, cwEwbcode);
	}

	public String getCwewbcode() {
		return this.getField(3);
	}

	public void setCocode(String coCode) {
		this.setField(4, coCode);
	}

	public String getCocode() {
		return this.getField(4);
	}

	public void setStartpyoccurdate(String StartPyOccurdate) {
		this.setField(5, StartPyOccurdate);
	}

	public String getStartpyoccurdate() {
		return this.getField(5);
	}

	public void setEndpyoccurdate(String EndPyOccurdate) {
		this.setField(6, EndPyOccurdate);
	}

	public String getEndpyoccurdate() {
		return this.getField(6);
	}

	public void setFscode(String fsCode) {
		this.setField(7, fsCode);
	}

	public String getFscode() {
		return this.getField(7);
	}

	public void setStartchargeweight(String StartChargeweight) {
		this.setField(8, StartChargeweight);
	}

	public String getStartchargeweight() {
		return this.getField(8);
	}

	public void setEndchargeweight(String EndChargeweight) {
		this.setField(9, EndChargeweight);
	}

	public String getEndchargeweight() {
		return this.getField(9);
	}

	public void setStartserverchargeweight(String StartServerchargeweight) {
		this.setField(10, StartServerchargeweight);
	}

	public String getStartserverchargeweight() {
		return this.getField(10);
	}

	public void setEndserverchargeweight(String EndServerchargeweight) {
		this.setField(11, EndServerchargeweight);
	}

	public String getEndserverchargeweight() {
		return this.getField(11);
	}

	public void setCwscode(String cwsCode) {
		this.setField(12, cwsCode);
	}

	public String getCwscode() {
		return this.getField(12);
	}

	public void setNotcwscode(String NotcwsCode) {
		this.setField(13, NotcwsCode);
	}

	public String getNotcwscode() {
		return this.getField(13);
	}

	public void setPkcode(String pkCode) {
		this.setField(14, pkCode);
	}

	public String getPkcode() {
		return this.getField(14);
	}

	public void setDesdtcode(String DesDtCode) {
		this.setField(15, DesDtCode);
	}

	public String getDesdtcode() {
		return this.getField(15);
	}

	public void setDescountrycode(String DesCountryCode) {
		this.setField(16, DesCountryCode);
	}

	public String getDescountrycode() {
		return this.getField(16);
	}

	public void setOrigindtcode(String OriginDtCode) {
		this.setField(17, OriginDtCode);
	}

	public String getOrigindtcode() {
		return this.getField(17);
	}

	public void setPmcode(String pmCode) {
		this.setField(18, pmCode);
	}

	public String getPmcode() {
		return this.getField(18);
	}

	public void setChncode(String chnCode) {
		this.setField(19, chnCode);
	}

	public String getChncode() {
		return this.getField(19);
	}

	public void setCtcode(String ctCode) {
		this.setField(20, ctCode);
	}

	public String getCtcode() {
		return this.getField(20);
	}

	public void setScocode(String scoCode) {
		this.setField(21, scoCode);
	}

	public String getScocode() {
		return this.getField(21);
	}

	public void setCcocode(String ccoCode) {
		this.setField(22, ccoCode);
	}

	public String getCcocode() {
		return this.getField(22);
	}

	public void setAbwlabelcode(String abwLabelcode) {
		this.setField(23, abwLabelcode);
	}

	public String getAbwlabelcode() {
		return this.getField(23);
	}

	public void setDbwlabelcode(String dbwLabelcode) {
		this.setField(24, dbwLabelcode);
	}

	public String getDbwlabelcode() {
		return this.getField(24);
	}

	public void setBrid(String brId) {
		this.setField(25, brId);
	}

	public String getBrid() {
		return this.getField(25);
	}

	public void setInbrid(String InbrId) {
		this.setField(26, InbrId);
	}

	public String getInbrid() {
		return this.getField(26);
	}

	public void setSbrid(String sbrId) {
		this.setField(27, sbrId);
	}

	public String getSbrid() {
		return this.getField(27);
	}

	public void setSbrlabelcode(String sbrLabelcode) {
		this.setField(28, sbrLabelcode);
	}

	public String getSbrlabelcode() {
		return this.getField(28);
	}

	public void setCwswequalsign(String CWSWEqualSign) {
		this.setField(29, CWSWEqualSign);
	}

	public String getCwswequalsign() {
		return this.getField(29);
	}

	public void setBegincarryoversign(String BeginCarryoversign) {
		this.setField(30, BeginCarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(30);
	}

	public void setEndcarryoversigin(String EndCarryoversigin) {
		this.setField(31, EndCarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(31);
	}

	public void setIncustomerewbcode(String InCustomerewbcode) {
		this.setField(32, InCustomerewbcode);
	}

	public String getIncustomerewbcode() {
		return this.getField(32);
	}
	public void setInserverewbcode(String InServerewbcode) {
		this.setField(33, InServerewbcode);
	}

	public String getInserverewbcode() {
		return this.getField(33);
	}

	public void setCkcode(String ckCode) {
		this.setField(34, ckCode);
	}

	public String getCkcode() {
		return this.getField(34);
	}

	public void setHwowninputcwauditsign(String hwOwninputcwauditsign) {
		this.setField(35, hwOwninputcwauditsign);
	}

	public String getHwowninputcwauditsign() {
		return this.getField(35);
	}

	public void setStarthwsignindate(String StartHwSignindate) {
		this.setField(36, StartHwSignindate);
	}

	public String getStarthwsignindate() {
		return this.getField(36);
	}

	public void setEndhwsignindate(String EndHwSignindate) {
		this.setField(37, EndHwSignindate);
	}

	public String getEndhwsignindate() {
		return this.getField(37);
	}

	public void setFkcode(String fkcode) {
		this.setField(38, fkcode);
	}

	public String getFkcode() {
		return this.getField(38);
	}

	public void setBkcode(String bkcode) {
		this.setField(39, bkcode);
	}

	public String getBkcode() {
		return this.getField(39);
	}

	public void setPyhasaccount(String PyhasAccount) {
		this.setField(40, PyhasAccount);
	}

	public String getPyhasaccount() {
		return this.getField(40);
	}

}
