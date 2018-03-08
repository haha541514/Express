package kyle.leis.eo.customerservice.track.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WaybillfortrackclientCondition extends GeneralCondition {
	public WaybillfortrackclientCondition() {
		m_astrConditions = new String[37];
	}	
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setCwserverewbcode(String cwServerewbcode) {
		this.setField(1, cwServerewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(1);
	}

	public void setCwcustomerewbcode(String cwCustomerewbcode) {
		this.setField(2, cwCustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(2);
	}

	public void setCwscode(String cwsCode) {
		this.setField(3, cwsCode);
	}

	public String getCwscode() {
		return this.getField(3);
	}

	public void setPkcode(String pkCode) {
		this.setField(4, pkCode);
	}

	public String getPkcode() {
		return this.getField(4);
	}

	public void setCountrydtcode(String countrydtcode) {
		this.setField(5, countrydtcode);
	}

	public String getCountrydtcode() {
		return this.getField(5);
	}

	public void setOrigindtcode(String origindtcode) {
		this.setField(6, origindtcode);
	}

	public String getOrigindtcode() {
		return this.getField(6);
	}

	public void setPmcode(String pmCode) {
		this.setField(7, pmCode);
	}

	public String getPmcode() {
		return this.getField(7);
	}

	public void setChncode(String chnCode) {
		this.setField(8, chnCode);
	}

	public String getChncode() {
		return this.getField(8);
	}

	public void setEecode(String eeCode) {
		this.setField(9, eeCode);
	}

	public String getEecode() {
		return this.getField(9);
	}

	public void setCtcode(String ctCode) {
		this.setField(10, ctCode);
	}

	public String getCtcode() {
		return this.getField(10);
	}

	public void setScocode(String scoCode) {
		this.setField(11, scoCode);
	}

	public String getScocode() {
		return this.getField(11);
	}

	public void setCcocode(String ccoCode) {
		this.setField(12, ccoCode);
	}

	public String getCcocode() {
		return this.getField(12);
	}

	public void setNotccocode(String NotCcoCode) {
		this.setField(13, NotCcoCode);
	}

	public String getNotccocode() {
		return this.getField(13);
	}

	public void setArrivalbwcode(String arrivalbwCode) {
		this.setField(14, arrivalbwCode);
	}

	public String getArrivalbwcode() {
		return this.getField(14);
	}

	public void setDeparturebwcode(String departurebwCode) {
		this.setField(15, departurebwCode);
	}

	public String getDeparturebwcode() {
		return this.getField(15);
	}

	public void setWbtscode(String wbtsCode) {
		this.setField(16, wbtsCode);
	}

	public String getWbtscode() {
		return this.getField(16);
	}

	public void setNotwbscode(String NotWbsCode) {
		this.setField(17, NotWbsCode);
	}

	public String getNotwbscode() {
		return this.getField(17);
	}

	public void setArrivallabelcode(String ArrivalLabelcode) {
		this.setField(18, ArrivalLabelcode);
	}

	public String getArrivallabelcode() {
		return this.getField(18);
	}

	public void setDeparturelabelcode(String DepartureLabelcode) {
		this.setField(19, DepartureLabelcode);
	}

	public String getDeparturelabelcode() {
		return this.getField(19);
	}

	public void setNotdeparturelabelcode(String NotDepartureLabelcode) {
		this.setField(20, NotDepartureLabelcode);
	}

	public String getNotdeparturelabelcode() {
		return this.getField(20);
	}

	public void setWbbtlatesttrackdesc(String wbbtLatesttrackdesc) {
		this.setField(21, wbbtLatesttrackdesc);
	}

	public String getWbbtlatesttrackdesc() {
		return this.getField(21);
	}

	public void setWbbtlatestcslogdesc(String wbbtLatestcslogdesc) {
		this.setField(22, wbbtLatestcslogdesc);
	}

	public String getWbbtlatestcslogdesc() {
		return this.getField(22);
	}

	public void setNotsignforuser(String NotSignforuser) {
		this.setField(23, NotSignforuser);
	}

	public String getNotsignforuser() {
		return this.getField(23);
	}

	public void setStartsignfordate(String StartSignfordate) {
		this.setField(24, StartSignfordate);
	}

	public String getStartsignfordate() {
		return this.getField(24);
	}

	public void setEndsignfordate(String EndSignfordate) {
		this.setField(25, EndSignfordate);
	}

	public String getEndsignfordate() {
		return this.getField(25);
	}

	public void setStartarrivaldate(String StartArrivalDate) {
		this.setField(26, StartArrivalDate);
	}

	public String getStartarrivaldate() {
		return this.getField(26);
	}

	public void setEndarrivaldate(String EndArrivalDate) {
		this.setField(27, EndArrivalDate);
	}

	public String getEndarrivaldate() {
		return this.getField(27);
	}

	public void setStartdeparturedate(String StartDepartureDate) {
		this.setField(28, StartDepartureDate);
	}

	public String getStartdeparturedate() {
		return this.getField(28);
	}

	public void setEnddeparturedate(String EndDepartureDate) {
		this.setField(29, EndDepartureDate);
	}

	public String getEnddeparturedate() {
		return this.getField(29);
	}

	public void setCsopid(String csopId) {
		this.setField(30, csopId);
	}

	public String getCsopid() {
		return this.getField(30);
	}

	public void setPkqueryneedlogin(String pkQueryneedlogin) {
		this.setField(31, pkQueryneedlogin);
	}

	public String getPkqueryneedlogin() {
		return this.getField(31);
	}

	public void setCwewbcode(String Cwewbcode) {
		this.setField(32, Cwewbcode);
	}

	public String getCwewbcode() {
		return this.getField(32);
	}

	public void setCpbaglabelcode(String cpBaglabelcode) {
		this.setField(33, cpBaglabelcode);
	}

	public String getCpbaglabelcode() {
		return this.getField(33);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(34, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(34);
	}

	public void setCctcode(String cctCode) {
		this.setField(35, cctCode);
	}

	public String getCctcode() {
		return this.getField(35);
	}

}
