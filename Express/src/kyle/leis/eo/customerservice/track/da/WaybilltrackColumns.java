package kyle.leis.eo.customerservice.track.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class WaybilltrackColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybilltrackColumns() {
		m_astrColumns = new String[25];
	}
	
	public WaybilltrackColumns(Long wbtWbtid, 
            String wbtWbtdescription, String wbtWbtorigindescription, 
            String wbtWbtlocation, Date wbtWbtoccurdate, 
            Date wbtWbtcreatedate, Date wbtWbtmodifydate, 
            String wbtWbtopensign, Long wbbtCwcode, 
            String dtDtcode, String dtDthubcode, 
            Long mopOpid, String mopOpname, 
            Long copOpid, String copOpname, 
            String wbtsWbtscode, String wbtsWbtsname, 
            String wbtsWbtsabnormalsign, String wbtpWbtpcode, 
            String wbtpWbtpname, String coCocode, 
            String coCosname, String coColabelcode, 
            String wbtWbtfrom, String wbtsWbtsename){
		m_astrColumns = new String[25];
		setWbtwbtid(wbtWbtid);
		setWbtwbtdescription(wbtWbtdescription);
		setWbtwbtorigindescription(wbtWbtorigindescription);
		setWbtwbtlocation(wbtWbtlocation);
		setWbtwbtoccurdate(wbtWbtoccurdate);
		setWbtwbtcreatedate(wbtWbtcreatedate);
		setWbtwbtmodifydate(wbtWbtmodifydate);
		setWbtwbtopensign(wbtWbtopensign);
		setWbbtcwcode(wbbtCwcode);
		setDtdtcode(dtDtcode);
		setDtdthubcode(dtDthubcode);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setWbtswbtscode(wbtsWbtscode);
		setWbtswbtsname(wbtsWbtsname);
		setWbtswbtsabnormalsign(wbtsWbtsabnormalsign);
		setWbtpwbtpcode(wbtpWbtpcode);
		setWbtpwbtpname(wbtpWbtpname);
		setCococode(coCocode);
		setCocosname(coCosname);
		setCocolabelcode(coColabelcode);
		setWbtwbtfrom(wbtWbtfrom);
		setWbtswbtsename(wbtsWbtsename);
	}

	public void setWbtwbtid(Long wbtWbtid) {
		this.setField(0, wbtWbtid);
	}

	public String getWbtwbtid() {
		return this.getField(0);
	}

	public void setWbtwbtdescription(String wbtWbtdescription) {
		this.setField(1, wbtWbtdescription);
	}

	public String getWbtwbtdescription() {
		return this.getField(1);
	}

	public void setWbtwbtorigindescription(String wbtWbtorigindescription) {
		this.setField(2, wbtWbtorigindescription);
	}

	public String getWbtwbtorigindescription() {
		return this.getField(2);
	}

	public void setWbtwbtlocation(String wbtWbtlocation) {
		this.setField(3, wbtWbtlocation);
	}

	public String getWbtwbtlocation() {
		return this.getField(3);
	}

	public void setWbtwbtoccurdate(Date wbtWbtoccurdate) {
		this.setField(4, wbtWbtoccurdate);
	}

	public String getWbtwbtoccurdate() {
		return this.getField(4);
	}

	public void setWbtwbtcreatedate(Date wbtWbtcreatedate) {
		this.setField(5, wbtWbtcreatedate);
	}

	public String getWbtwbtcreatedate() {
		return this.getField(5);
	}

	public void setWbtwbtmodifydate(Date wbtWbtmodifydate) {
		this.setField(6, wbtWbtmodifydate);
	}

	public String getWbtwbtmodifydate() {
		return this.getField(6);
	}

	public void setWbtwbtopensign(String wbtWbtopensign) {
		this.setField(7, wbtWbtopensign);
	}

	public String getWbtwbtopensign() {
		return this.getField(7);
	}

	public void setWbbtcwcode(Long wbbtCwcode) {
		this.setField(8, wbbtCwcode);
	}

	public String getWbbtcwcode() {
		return this.getField(8);
	}

	public void setDtdtcode(String dtDtcode) {
		this.setField(9, dtDtcode);
	}

	public String getDtdtcode() {
		return this.getField(9);
	}

	public void setDtdthubcode(String dtDthubcode) {
		this.setField(10, dtDthubcode);
	}

	public String getDtdthubcode() {
		return this.getField(10);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(11, mopOpid);
	}

	public String getMopopid() {
		return this.getField(11);
	}

	public void setMopopname(String mopOpname) {
		this.setField(12, mopOpname);
	}

	public String getMopopname() {
		return this.getField(12);
	}

	public void setCopopid(Long copOpid) {
		this.setField(13, copOpid);
	}

	public String getCopopid() {
		return this.getField(13);
	}

	public void setCopopname(String copOpname) {
		this.setField(14, copOpname);
	}

	public String getCopopname() {
		return this.getField(14);
	}

	public void setWbtswbtscode(String wbtsWbtscode) {
		this.setField(15, wbtsWbtscode);
	}

	public String getWbtswbtscode() {
		return this.getField(15);
	}

	public void setWbtswbtsname(String wbtsWbtsname) {
		this.setField(16, wbtsWbtsname);
	}

	public String getWbtswbtsname() {
		return this.getField(16);
	}

	public void setWbtswbtsabnormalsign(String wbtsWbtsabnormalsign) {
		this.setField(17, wbtsWbtsabnormalsign);
	}

	public String getWbtswbtsabnormalsign() {
		return this.getField(17);
	}

	public void setWbtpwbtpcode(String wbtpWbtpcode) {
		this.setField(18, wbtpWbtpcode);
	}

	public String getWbtpwbtpcode() {
		return this.getField(18);
	}

	public void setWbtpwbtpname(String wbtpWbtpname) {
		this.setField(19, wbtpWbtpname);
	}

	public String getWbtpwbtpname() {
		return this.getField(19);
	}

	public void setCococode(String coCocode) {
		this.setField(20, coCocode);
	}

	public String getCococode() {
		return this.getField(20);
	}

	public void setCocosname(String coCosname) {
		this.setField(21, coCosname);
	}

	public String getCocosname() {
		return this.getField(21);
	}

	public void setCocolabelcode(String coColabelcode) {
		this.setField(22, coColabelcode);
	}

	public String getCocolabelcode() {
		return this.getField(22);
	}

	public void setWbtwbtfrom(String wbtWbtfrom) {
		this.setField(23, wbtWbtfrom);
	}

	public String getWbtwbtfrom() {
		return this.getField(23);
	}

	public void setWbtswbtsename(String wbtsWbtsename) {
		this.setField(24, wbtsWbtsename);
	}

	public String getWbtswbtsename() {
		return this.getField(24);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
