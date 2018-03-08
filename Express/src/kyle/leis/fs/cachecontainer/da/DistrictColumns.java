package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class DistrictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public DistrictColumns() {
		
	}
	
	public DistrictColumns(String dtDtcode, 
            String dtDthubcode, String dtDtname, 
            String dtDtename, String dtDtgrade, 
            String dtkDkcode, String dtcountryDtcode, 
            String dtcountryDthubcode, String dtcountryDtname, 
            String dtcountryDtename, String dtDtstartcitysign){
		m_astrColumns = new String[11];
		setDtdtcode(dtDtcode);
		setDtdthubcode(dtDthubcode);
		setDtdtname(dtDtname);
		setDtdtename(dtDtename);
		setDtdtgrade(dtDtgrade);
		setDtkdkcode(dtkDkcode);
		setDtcountrydtcode(dtcountryDtcode);
		setDtcountrydthubcode(dtcountryDthubcode);
		setDtcountrydtname(dtcountryDtname);
		setDtcountrydtename(dtcountryDtename);
		setDtdtstartcitysign(dtDtstartcitysign);
	}

	public void setDtdtcode(String dtDtcode) {
		this.setField(0, dtDtcode);
	}

	public String getDtdtcode() {
		return this.getField(0);
	}

	public void setDtdthubcode(String dtDthubcode) {
		this.setField(1, dtDthubcode);
	}

	public String getDtdthubcode() {
		return this.getField(1);
	}

	public void setDtdtname(String dtDtname) {
		this.setField(2, dtDtname);
	}

	public String getDtdtname() {
		return this.getField(2);
	}

	public void setDtdtename(String dtDtename) {
		this.setField(3, dtDtename);
	}

	public String getDtdtename() {
		return this.getField(3);
	}

	public void setDtdtgrade(String dtDtgrade) {
		this.setField(4, dtDtgrade);
	}

	public String getDtdtgrade() {
		return this.getField(4);
	}

	public void setDtkdkcode(String dtkDkcode) {
		this.setField(5, dtkDkcode);
	}

	public String getDtkdkcode() {
		return this.getField(5);
	}

	public void setDtcountrydtcode(String dtcountryDtcode) {
		this.setField(6, dtcountryDtcode);
	}

	public String getDtcountrydtcode() {
		return this.getField(6);
	}

	public void setDtcountrydthubcode(String dtcountryDthubcode) {
		this.setField(7, dtcountryDthubcode);
	}

	public String getDtcountrydthubcode() {
		return this.getField(7);
	}

	public void setDtcountrydtname(String dtcountryDtname) {
		this.setField(8, dtcountryDtname);
	}

	public String getDtcountrydtname() {
		return this.getField(8);
	}

	public void setDtcountrydtename(String dtcountryDtename) {
		this.setField(9, dtcountryDtename);
	}

	public String getDtcountrydtename() {
		return this.getField(9);
	}

	public void setDtdtstartcitysign(String dtDtstartcitysign) {
		this.setField(10, dtDtstartcitysign);
	}

	public String getDtdtstartcitysign() {
		return this.getField(10);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
