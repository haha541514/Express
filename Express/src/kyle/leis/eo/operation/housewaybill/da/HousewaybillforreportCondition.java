package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class HousewaybillforreportCondition extends GeneralCondition {
	public HousewaybillforreportCondition() {
		m_astrConditions = new String[58];
	}	
	
	public void setCustomerewbcode(String Customerewbcode) {
		this.setField(0, Customerewbcode);
	}

	public String getCustomerewbcode() {
		return this.getField(0);
	}

	public void setServerewbcode(String Serverewbcode) {
		this.setField(1, Serverewbcode);
	}

	public String getServerewbcode() {
		return this.getField(1);
	}

	public void setEwbcode(String Ewbcode) {
		this.setField(2, Ewbcode);
	}

	public String getEwbcode() {
		return this.getField(2);
	}

	public void setStartsignindate(String StartSignindate) {
		this.setField(3, StartSignindate);
	}

	public String getStartsignindate() {
		return this.getField(3);
	}

	public void setEndsignindate(String EndSignindate) {
		this.setField(4, EndSignindate);
	}

	public String getEndsignindate() {
		return this.getField(4);
	}

	public void setStartsignoutdate(String StartSignoutdate) {
		this.setField(5, StartSignoutdate);
	}

	public String getStartsignoutdate() {
		return this.getField(5);
	}

	public void setEndsignoutdate(String EndSignoutdate) {
		this.setField(6, EndSignoutdate);
	}

	public String getEndsignoutdate() {
		return this.getField(6);
	}

	public void setStartrecorddate(String StartRecorddate) {
		this.setField(7, StartRecorddate);
	}

	public String getStartrecorddate() {
		return this.getField(7);
	}

	public void setEndrecorddate(String EndRecorddate) {
		this.setField(8, EndRecorddate);
	}

	public String getEndrecorddate() {
		return this.getField(8);
	}

	public void setStartchargeweight(String StartChargeweight) {
		this.setField(9, StartChargeweight);
	}

	public String getStartchargeweight() {
		return this.getField(9);
	}

	public void setEndchargeweight(String EndChargeweight) {
		this.setField(10, EndChargeweight);
	}

	public String getEndchargeweight() {
		return this.getField(10);
	}

	public void setStartserverchargeweight(String StartServerchargeweight) {
		this.setField(11, StartServerchargeweight);
	}

	public String getStartserverchargeweight() {
		return this.getField(11);
	}

	public void setEndserverchargeweight(String EndServerchargeweight) {
		this.setField(12, EndServerchargeweight);
	}

	public String getEndserverchargeweight() {
		return this.getField(12);
	}

	public void setCwscode(String cwsCode) {
		this.setField(13, cwsCode);
	}

	public String getCwscode() {
		return this.getField(13);
	}

	public void setNotcwscode(String NotcwsCode) {
		this.setField(14, NotcwsCode);
	}

	public String getNotcwscode() {
		return this.getField(14);
	}

	public void setPkcode(String pkCode) {
		this.setField(15, pkCode);
	}

	public String getPkcode() {
		return this.getField(15);
	}

	public void setDesdtcode(String DesDtCode) {
		this.setField(16, DesDtCode);
	}

	public String getDesdtcode() {
		return this.getField(16);
	}

	public void setDescountrycode(String DesCountryCode) {
		this.setField(17, DesCountryCode);
	}

	public String getDescountrycode() {
		return this.getField(17);
	}

	public void setOrigindtcode(String OriginDtCode) {
		this.setField(18, OriginDtCode);
	}

	public String getOrigindtcode() {
		return this.getField(18);
	}

	public void setPmcode(String pmCode) {
		this.setField(19, pmCode);
	}

	public String getPmcode() {
		return this.getField(19);
	}

	public void setChncode(String chnCode) {
		this.setField(20, chnCode);
	}

	public String getChncode() {
		return this.getField(20);
	}

	public void setCtcode(String ctCode) {
		this.setField(21, ctCode);
	}

	public String getCtcode() {
		return this.getField(21);
	}

	public void setScocode(String scoCode) {
		this.setField(22, scoCode);
	}

	public String getScocode() {
		return this.getField(22);
	}

	public void setCcocode(String ccoCode) {
		this.setField(23, ccoCode);
	}

	public String getCcocode() {
		return this.getField(23);
	}

	public void setAbwlabelcode(String abwLabelcode) {
		this.setField(24, abwLabelcode);
	}

	public String getAbwlabelcode() {
		return this.getField(24);
	}

	public void setDbwlabelcode(String dbwLabelcode) {
		this.setField(25, dbwLabelcode);
	}

	public String getDbwlabelcode() {
		return this.getField(25);
	}

	public void setCwcode(String cwCode) {
		this.setField(26, cwCode);
	}

	public String getCwcode() {
		return this.getField(26);
	}

	public void setEecode(String eeCode) {
		this.setField(27, eeCode);
	}

	public String getEecode() {
		return this.getField(27);
	}

	public void setCsopid(String csopId) {
		this.setField(28, csopId);
	}

	public String getCsopid() {
		return this.getField(28);
	}

	public void setSsopid(String ssopId) {
		this.setField(29, ssopId);
	}

	public String getSsopid() {
		return this.getField(29);
	}

	public void setOpidrecord(String opIdRecord) {
		this.setField(30, opIdRecord);
	}

	public String getOpidrecord() {
		return this.getField(30);
	}

	public void setOpidsignin(String opIdSignin) {
		this.setField(31, opIdSignin);
	}

	public String getOpidsignin() {
		return this.getField(31);
	}

	public void setIhscode(String ihsCode) {
		this.setField(32, ihsCode);
	}

	public String getIhscode() {
		return this.getField(32);
	}

	public void setZnvname(String znvName) {
		this.setField(33, znvName);
	}

	public String getZnvname() {
		return this.getField(33);
	}

	public void setCpbaglabelcode(String cpBaglabelcode) {
		this.setField(34, cpBaglabelcode);
	}

	public String getCpbaglabelcode() {
		return this.getField(34);
	}

	public void setEstcode(String estCode) {
		this.setField(35, estCode);
	}

	public String getEstcode() {
		return this.getField(35);
	}

	public void setStartarrivedate(String StartArrivedate) {
		this.setField(36, StartArrivedate);
	}

	public String getStartarrivedate() {
		return this.getField(36);
	}

	public void setEndarrivedate(String EndArrivedate) {
		this.setField(37, EndArrivedate);
	}

	public String getEndarrivedate() {
		return this.getField(37);
	}

	public void setTwblabelcode(String twbLabelcode) {
		this.setField(38, twbLabelcode);
	}

	public String getTwblabelcode() {
		return this.getField(38);
	}

	public void setIncustomerewbcode(String InCustomerewbcode) {
		this.setField(39, InCustomerewbcode);
	}

	public String getIncustomerewbcode() {
		return this.getField(39);
	}
	public void setInserverewbcode(String InServerewbcode) {
		this.setField(40, InServerewbcode);
	}

	public String getInserverewbcode() {
		return this.getField(40);
	}

	public void setBegincarryoversign(String BeginCarryoversign) {
		this.setField(41, BeginCarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(41);
	}

	public void setEndcarryoversigin(String EndCarryoversigin) {
		this.setField(42, EndCarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(42);
	}

	public void setFscarryoverenterprise(String fscarryoverenterprise) {
		this.setField(43, fscarryoverenterprise);
	}

	public String getFscarryoverenterprise() {
		return this.getField(43);
	}

	public void setMaxweight(String MaxWeight) {
		this.setField(44, MaxWeight);
	}

	public String getMaxweight() {
		return this.getField(44);
	}

	public void setMinweight(String MinWeight) {
		this.setField(45, MinWeight);
	}

	public String getMinweight() {
		return this.getField(45);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(46, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(46);
	}

	public void setOpidweightcheck(String OpIdWeightCheck) {
		this.setField(47, OpIdWeightCheck);
	}

	public String getOpidweightcheck() {
		return this.getField(47);
	}

	public void setStartcheckdate(String StartCheckdate) {
		this.setField(48, StartCheckdate);
	}

	public String getStartcheckdate() {
		return this.getField(48);
	}

	public void setEndcheckdate(String EndCheckdate) {
		this.setField(49, EndCheckdate);
	}

	public String getEndcheckdate() {
		return this.getField(49);
	}

	public void setHw_weightcheckkind(String HW_WEIGHTCHECKKIND) {
		this.setField(50, HW_WEIGHTCHECKKIND);
	}

	public String getHw_weightcheckkind() {
		return this.getField(50);
	}

	public void setWcbwlabelcode(String wcbwlabelcode) {
		this.setField(51, wcbwlabelcode);
	}

	public String getWcbwlabelcode() {
		return this.getField(51);
	}

	public void setCwbatchwaybillsign(String cwBatchwaybillsign) {
		this.setField(52, cwBatchwaybillsign);
	}

	public String getCwbatchwaybillsign() {
		return this.getField(52);
	}

	public void setStartrecordtrackdate(String StartRecordtrackdate) {
		this.setField(53, StartRecordtrackdate);
	}

	public String getStartrecordtrackdate() {
		return this.getField(53);
	}
	public void setEndrecordtrackdate(String EndRecordtrackdate) {
		this.setField(54, EndRecordtrackdate);
	}

	public String getEndrecordtrackdate() {
		return this.getField(54);
	}

	public void setCpsibaglabelcode(String cpSibaglabelcode) {
		this.setField(55, cpSibaglabelcode);
	}

	public String getCpsibaglabelcode() {
		return this.getField(55);
	}
	public void setCctcode(String cctCode) {
		this.setField(56, cctCode);
	}

	public String getCctcode() {
		return this.getField(56);
	}


}
