package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class DicdistrictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public DicdistrictColumns() {
		m_astrColumns = new String[26];
	}
	
	public DicdistrictColumns(String diDtcode, 
            String diDthubcode, String diDtname, 
            String diDtename, String diDtstatecode, 
            String diDtstatename, String diDtgrade, 
            String diDtstartpostcode, String diDtendpostcode, 
            String diDtopcodecreator, Date diDtcreatedate, 
            String diDtopcodemodifier, Date diDtmodifydate, 
            String diDtremark, String diDtstartcitysign, 
            String diDtelevatedrisksign, String diDtrestrictedsign, 
            String ddDtcode, String ddDtname, 
            String ddDthubcode, String ddDtename, 
            String ddDtopcodecreator, String ddDtopcodemodifier, 
            String dkDkcode, String dkDkname, 
            String dkDkename){
		m_astrColumns = new String[26];
		setDidtcode(diDtcode);
		setDidthubcode(diDthubcode);
		setDidtname(diDtname);
		setDidtename(diDtename);
		setDidtstatecode(diDtstatecode);
		setDidtstatename(diDtstatename);
		setDidtgrade(diDtgrade);
		setDidtstartpostcode(diDtstartpostcode);
		setDidtendpostcode(diDtendpostcode);
		setDidtopcodecreator(diDtopcodecreator);
		setDidtcreatedate(diDtcreatedate);
		setDidtopcodemodifier(diDtopcodemodifier);
		setDidtmodifydate(diDtmodifydate);
		setDidtremark(diDtremark);
		setDidtstartcitysign(diDtstartcitysign);
		setDidtelevatedrisksign(diDtelevatedrisksign);
		setDidtrestrictedsign(diDtrestrictedsign);
		setDddtcode(ddDtcode);
		setDddtname(ddDtname);
		setDddthubcode(ddDthubcode);
		setDddtename(ddDtename);
		setDddtopcodecreator(ddDtopcodecreator);
		setDddtopcodemodifier(ddDtopcodemodifier);
		setDkdkcode(dkDkcode);
		setDkdkname(dkDkname);
		setDkdkename(dkDkename);
	}

	public void setDidtcode(String diDtcode) {
		this.setField(0, diDtcode);
	}

	public String getDidtcode() {
		return this.getField(0);
	}

	public void setDidthubcode(String diDthubcode) {
		this.setField(1, diDthubcode);
	}

	public String getDidthubcode() {
		return this.getField(1);
	}

	public void setDidtname(String diDtname) {
		this.setField(2, diDtname);
	}

	public String getDidtname() {
		return this.getField(2);
	}

	public void setDidtename(String diDtename) {
		this.setField(3, diDtename);
	}

	public String getDidtename() {
		return this.getField(3);
	}

	public void setDidtstatecode(String diDtstatecode) {
		this.setField(4, diDtstatecode);
	}

	public String getDidtstatecode() {
		return this.getField(4);
	}

	public void setDidtstatename(String diDtstatename) {
		this.setField(5, diDtstatename);
	}

	public String getDidtstatename() {
		return this.getField(5);
	}

	public void setDidtgrade(String diDtgrade) {
		this.setField(6, diDtgrade);
	}

	public String getDidtgrade() {
		return this.getField(6);
	}

	public void setDidtstartpostcode(String diDtstartpostcode) {
		this.setField(7, diDtstartpostcode);
	}

	public String getDidtstartpostcode() {
		return this.getField(7);
	}

	public void setDidtendpostcode(String diDtendpostcode) {
		this.setField(8, diDtendpostcode);
	}

	public String getDidtendpostcode() {
		return this.getField(8);
	}

	public void setDidtopcodecreator(String diDtopcodecreator) {
		this.setField(9, diDtopcodecreator);
	}

	public String getDidtopcodecreator() {
		return this.getField(9);
	}

	public void setDidtcreatedate(Date diDtcreatedate) {
		this.setField(10, diDtcreatedate);
	}

	public String getDidtcreatedate() {
		return this.getField(10);
	}

	public void setDidtopcodemodifier(String diDtopcodemodifier) {
		this.setField(11, diDtopcodemodifier);
	}

	public String getDidtopcodemodifier() {
		return this.getField(11);
	}

	public void setDidtmodifydate(Date diDtmodifydate) {
		this.setField(12, diDtmodifydate);
	}

	public String getDidtmodifydate() {
		return this.getField(12);
	}

	public void setDidtremark(String diDtremark) {
		this.setField(13, diDtremark);
	}

	public String getDidtremark() {
		return this.getField(13);
	}

	public void setDidtstartcitysign(String diDtstartcitysign) {
		this.setField(14, diDtstartcitysign);
	}

	public String getDidtstartcitysign() {
		return this.getField(14);
	}

	public void setDidtelevatedrisksign(String diDtelevatedrisksign) {
		this.setField(15, diDtelevatedrisksign);
	}

	public String getDidtelevatedrisksign() {
		return this.getField(15);
	}

	public void setDidtrestrictedsign(String diDtrestrictedsign) {
		this.setField(16, diDtrestrictedsign);
	}

	public String getDidtrestrictedsign() {
		return this.getField(16);
	}

	public void setDddtcode(String ddDtcode) {
		this.setField(17, ddDtcode);
	}

	public String getDddtcode() {
		return this.getField(17);
	}

	public void setDddtname(String ddDtname) {
		this.setField(18, ddDtname);
	}

	public String getDddtname() {
		return this.getField(18);
	}

	public void setDddthubcode(String ddDthubcode) {
		this.setField(19, ddDthubcode);
	}

	public String getDddthubcode() {
		return this.getField(19);
	}

	public void setDddtename(String ddDtename) {
		this.setField(20, ddDtename);
	}

	public String getDddtename() {
		return this.getField(20);
	}

	public void setDddtopcodecreator(String ddDtopcodecreator) {
		this.setField(21, ddDtopcodecreator);
	}

	public String getDddtopcodecreator() {
		return this.getField(21);
	}

	public void setDddtopcodemodifier(String ddDtopcodemodifier) {
		this.setField(22, ddDtopcodemodifier);
	}

	public String getDddtopcodemodifier() {
		return this.getField(22);
	}

	public void setDkdkcode(String dkDkcode) {
		this.setField(23, dkDkcode);
	}

	public String getDkdkcode() {
		return this.getField(23);
	}

	public void setDkdkname(String dkDkname) {
		this.setField(24, dkDkname);
	}

	public String getDkdkname() {
		return this.getField(24);
	}

	public void setDkdkename(String dkDkename) {
		this.setField(25, dkDkename);
	}

	public String getDkdkename() {
		return this.getField(25);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
