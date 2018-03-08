package kyle.leis.fs.dictionary.feekind.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FeekindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FeekindColumns() {
		m_astrColumns = new String[12];
	}
	
	public FeekindColumns(String fkFkcode, 
            String fkFkname, String fkFkename, 
            String fkFkreferenceposition, String fkFkmanualmodifysign, 
            String Sign, String fkFkremark, 
            String estEstcode, String estEstname, 
            String estEstename, String ssSscode, 
            String ssSsname){
		m_astrColumns = new String[12];
		setFkfkcode(fkFkcode);
		setFkfkname(fkFkname);
		setFkfkename(fkFkename);
		setFkfkreferenceposition(fkFkreferenceposition);
		setFkfkmanualmodifysign(fkFkmanualmodifysign);
		setSign(Sign);
		setFkfkremark(fkFkremark);
		setEstestcode(estEstcode);
		setEstestname(estEstname);
		setEstestename(estEstename);
		setSssscode(ssSscode);
		setSsssname(ssSsname);
	}

	public void setFkfkcode(String fkFkcode) {
		this.setField(0, fkFkcode);
	}

	public String getFkfkcode() {
		return this.getField(0);
	}

	public void setFkfkname(String fkFkname) {
		this.setField(1, fkFkname);
	}

	public String getFkfkname() {
		return this.getField(1);
	}

	public void setFkfkename(String fkFkename) {
		this.setField(2, fkFkename);
	}

	public String getFkfkename() {
		return this.getField(2);
	}

	public void setFkfkreferenceposition(String fkFkreferenceposition) {
		this.setField(3, fkFkreferenceposition);
	}

	public String getFkfkreferenceposition() {
		return this.getField(3);
	}

	public void setFkfkmanualmodifysign(String fkFkmanualmodifysign) {
		this.setField(4, fkFkmanualmodifysign);
	}

	public String getFkfkmanualmodifysign() {
		return this.getField(4);
	}

	public void setSign(String Sign) {
		this.setField(5, Sign);
	}

	public String getSign() {
		return this.getField(5);
	}

	public void setFkfkremark(String fkFkremark) {
		this.setField(6, fkFkremark);
	}

	public String getFkfkremark() {
		return this.getField(6);
	}

	public void setEstestcode(String estEstcode) {
		this.setField(7, estEstcode);
	}

	public String getEstestcode() {
		return this.getField(7);
	}

	public void setEstestname(String estEstname) {
		this.setField(8, estEstname);
	}

	public String getEstestname() {
		return this.getField(8);
	}

	public void setEstestename(String estEstename) {
		this.setField(9, estEstename);
	}

	public String getEstestename() {
		return this.getField(9);
	}

	public void setSssscode(String ssSscode) {
		this.setField(10, ssSscode);
	}

	public String getSssscode() {
		return this.getField(10);
	}

	public void setSsssname(String ssSsname) {
		this.setField(11, ssSsname);
	}

	public String getSsssname() {
		return this.getField(11);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
