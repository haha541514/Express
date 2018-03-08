package kyle.leis.eo.customerservice.track.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillbatchtrackColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillbatchtrackColumns() {
		m_astrColumns = new String[16];
	}
	
	public WaybillbatchtrackColumns(Long wbbtCwcode, 
            String wbbtWbbtlatesttrackdesc, String wbbtWbbtlatestcslogdesc, 
            Date wbbtWbbtcslogcreatedate, String wbbtWbbtsignforuser, 
            Date wbbtWbbtsignfordate, String wbtsWbtscode, 
            String wbtsWbtsname, BigDecimal cwCwchargeweight, 
            BigDecimal cwCwserverchargeweight, String cwCwcustomerewbcode, 
            String cwCwserverewbcode, String cwCwewbcode, 
            String wpaWpacode, String wpaWpaname, 
            Date wbbtWbbtlatesttrackdate){
		m_astrColumns = new String[16];
		setWbbtcwcode(wbbtCwcode);
		setWbbtwbbtlatesttrackdesc(wbbtWbbtlatesttrackdesc);
		setWbbtwbbtlatestcslogdesc(wbbtWbbtlatestcslogdesc);
		setWbbtwbbtcslogcreatedate(wbbtWbbtcslogcreatedate);
		setWbbtwbbtsignforuser(wbbtWbbtsignforuser);
		setWbbtwbbtsignfordate(wbbtWbbtsignfordate);
		setWbtswbtscode(wbtsWbtscode);
		setWbtswbtsname(wbtsWbtsname);
		setCwcwchargeweight(cwCwchargeweight);
		setCwcwserverchargeweight(cwCwserverchargeweight);
		setCwcwcustomerewbcode(cwCwcustomerewbcode);
		setCwcwserverewbcode(cwCwserverewbcode);
		setCwcwewbcode(cwCwewbcode);
		setWpawpacode(wpaWpacode);
		setWpawpaname(wpaWpaname);
		setWbbtwbbtlatesttrackdate(wbbtWbbtlatesttrackdate);
	}

	public void setWbbtcwcode(Long wbbtCwcode) {
		this.setField(0, wbbtCwcode);
	}

	public String getWbbtcwcode() {
		return this.getField(0);
	}

	public void setWbbtwbbtlatesttrackdesc(String wbbtWbbtlatesttrackdesc) {
		this.setField(1, wbbtWbbtlatesttrackdesc);
	}

	public String getWbbtwbbtlatesttrackdesc() {
		return this.getField(1);
	}

	public void setWbbtwbbtlatestcslogdesc(String wbbtWbbtlatestcslogdesc) {
		this.setField(2, wbbtWbbtlatestcslogdesc);
	}

	public String getWbbtwbbtlatestcslogdesc() {
		return this.getField(2);
	}

	public void setWbbtwbbtcslogcreatedate(Date wbbtWbbtcslogcreatedate) {
		this.setField(3, wbbtWbbtcslogcreatedate);
	}

	public String getWbbtwbbtcslogcreatedate() {
		return this.getField(3);
	}

	public void setWbbtwbbtsignforuser(String wbbtWbbtsignforuser) {
		this.setField(4, wbbtWbbtsignforuser);
	}

	public String getWbbtwbbtsignforuser() {
		return this.getField(4);
	}

	public void setWbbtwbbtsignfordate(Date wbbtWbbtsignfordate) {
		this.setField(5, wbbtWbbtsignfordate);
	}

	public String getWbbtwbbtsignfordate() {
		return this.getField(5);
	}

	public void setWbtswbtscode(String wbtsWbtscode) {
		this.setField(6, wbtsWbtscode);
	}

	public String getWbtswbtscode() {
		return this.getField(6);
	}

	public void setWbtswbtsname(String wbtsWbtsname) {
		this.setField(7, wbtsWbtsname);
	}

	public String getWbtswbtsname() {
		return this.getField(7);
	}

	public void setCwcwchargeweight(BigDecimal cwCwchargeweight) {
		this.setField(8, cwCwchargeweight);
	}

	public String getCwcwchargeweight() {
		return this.getField(8);
	}

	public void setCwcwserverchargeweight(BigDecimal cwCwserverchargeweight) {
		this.setField(9, cwCwserverchargeweight);
	}

	public String getCwcwserverchargeweight() {
		return this.getField(9);
	}

	public void setCwcwcustomerewbcode(String cwCwcustomerewbcode) {
		this.setField(10, cwCwcustomerewbcode);
	}

	public String getCwcwcustomerewbcode() {
		return this.getField(10);
	}

	public void setCwcwserverewbcode(String cwCwserverewbcode) {
		this.setField(11, cwCwserverewbcode);
	}

	public String getCwcwserverewbcode() {
		return this.getField(11);
	}

	public void setCwcwewbcode(String cwCwewbcode) {
		this.setField(12, cwCwewbcode);
	}

	public String getCwcwewbcode() {
		return this.getField(12);
	}

	public void setWpawpacode(String wpaWpacode) {
		this.setField(13, wpaWpacode);
	}

	public String getWpawpacode() {
		return this.getField(13);
	}

	public void setWpawpaname(String wpaWpaname) {
		this.setField(14, wpaWpaname);
	}

	public String getWpawpaname() {
		return this.getField(14);
	}

	public void setWbbtwbbtlatesttrackdate(Date wbbtWbbtlatesttrackdate) {
		this.setField(15, wbbtWbbtlatesttrackdate);
	}

	public String getWbbtwbbtlatesttrackdate() {
		return this.getField(15);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
