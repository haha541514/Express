package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class DicdhldistrictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public DicdhldistrictColumns() {
		m_astrColumns = new String[11];
	}
	
	public DicdhldistrictColumns(String diDdcode, 
            String diDdnationcode, String diDdnationname, 
            String diDdstatecode, String diDdstatename, 
            String diDdcityname, String diDdhubcode, 
            String diDdstartpostcode, String diDdendtpostcode, 
            String diDdlocationcode, String diDdcitycname){
		m_astrColumns = new String[11];
		setDiddcode(diDdcode);
		setDiddnationcode(diDdnationcode);
		setDiddnationname(diDdnationname);
		setDiddstatecode(diDdstatecode);
		setDiddstatename(diDdstatename);
		setDiddcityname(diDdcityname);
		setDiddhubcode(diDdhubcode);
		setDiddstartpostcode(diDdstartpostcode);
		setDiddendtpostcode(diDdendtpostcode);
		setDiddlocationcode(diDdlocationcode);
		setDiddcitycname(diDdcitycname);
	}

	public void setDiddcode(String diDdcode) {
		this.setField(0, diDdcode);
	}

	public String getDiddcode() {
		return this.getField(0);
	}

	public void setDiddnationcode(String diDdnationcode) {
		this.setField(1, diDdnationcode);
	}

	public String getDiddnationcode() {
		return this.getField(1);
	}

	public void setDiddnationname(String diDdnationname) {
		this.setField(2, diDdnationname);
	}

	public String getDiddnationname() {
		return this.getField(2);
	}

	public void setDiddstatecode(String diDdstatecode) {
		this.setField(3, diDdstatecode);
	}

	public String getDiddstatecode() {
		return this.getField(3);
	}

	public void setDiddstatename(String diDdstatename) {
		this.setField(4, diDdstatename);
	}

	public String getDiddstatename() {
		return this.getField(4);
	}

	public void setDiddcityname(String diDdcityname) {
		this.setField(5, diDdcityname);
	}

	public String getDiddcityname() {
		return this.getField(5);
	}

	public void setDiddhubcode(String diDdhubcode) {
		this.setField(6, diDdhubcode);
	}

	public String getDiddhubcode() {
		return this.getField(6);
	}

	public void setDiddstartpostcode(String diDdstartpostcode) {
		this.setField(7, diDdstartpostcode);
	}

	public String getDiddstartpostcode() {
		return this.getField(7);
	}

	public void setDiddendtpostcode(String diDdendtpostcode) {
		this.setField(8, diDdendtpostcode);
	}

	public String getDiddendtpostcode() {
		return this.getField(8);
	}

	public void setDiddlocationcode(String diDdlocationcode) {
		this.setField(9, diDdlocationcode);
	}

	public String getDiddlocationcode() {
		return this.getField(9);
	}

	public void setDiddcitycname(String diDdcitycname) {
		this.setField(10, diDdcitycname);
	}

	public String getDiddcitycname() {
		return this.getField(10);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
