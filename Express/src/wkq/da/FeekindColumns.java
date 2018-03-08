package wkq.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FeekindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FeekindColumns() {
		m_astrColumns = new String[11];
	}
	
	public FeekindColumns(String foFkcode, 
            String foFkname, String foFkename, 
            String foFkreferenceposition, String foFkmanualmodifysign, 
            String Sign, String foFkremark, 
            String foFkaccountingonlysign, String foFkdeclarevaluesign, 
            String siSscode, String exEstcode){
		m_astrColumns = new String[11];
		setFofkcode(foFkcode);
		setFofkname(foFkname);
		setFofkename(foFkename);
		setFofkreferenceposition(foFkreferenceposition);
		setFofkmanualmodifysign(foFkmanualmodifysign);
		setSign(Sign);
		setFofkremark(foFkremark);
		setFofkaccountingonlysign(foFkaccountingonlysign);
		setFofkdeclarevaluesign(foFkdeclarevaluesign);
		setSisscode(siSscode);
		setExestcode(exEstcode);
	}

	public void setFofkcode(String foFkcode) {
		this.setField(0, foFkcode);
	}

	public String getFofkcode() {
		return this.getField(0);
	}

	public void setFofkname(String foFkname) {
		this.setField(1, foFkname);
	}

	public String getFofkname() {
		return this.getField(1);
	}

	public void setFofkename(String foFkename) {
		this.setField(2, foFkename);
	}

	public String getFofkename() {
		return this.getField(2);
	}

	public void setFofkreferenceposition(String foFkreferenceposition) {
		this.setField(3, foFkreferenceposition);
	}

	public String getFofkreferenceposition() {
		return this.getField(3);
	}

	public void setFofkmanualmodifysign(String foFkmanualmodifysign) {
		this.setField(4, foFkmanualmodifysign);
	}

	public String getFofkmanualmodifysign() {
		return this.getField(4);
	}

	public void setSign(String Sign) {
		this.setField(5, Sign);
	}

	public String getSign() {
		return this.getField(5);
	}

	public void setFofkremark(String foFkremark) {
		this.setField(6, foFkremark);
	}

	public String getFofkremark() {
		return this.getField(6);
	}

	public void setFofkaccountingonlysign(String foFkaccountingonlysign) {
		this.setField(7, foFkaccountingonlysign);
	}

	public String getFofkaccountingonlysign() {
		return this.getField(7);
	}

	public void setFofkdeclarevaluesign(String foFkdeclarevaluesign) {
		this.setField(8, foFkdeclarevaluesign);
	}

	public String getFofkdeclarevaluesign() {
		return this.getField(8);
	}

	public void setSisscode(String siSscode) {
		this.setField(9, siSscode);
	}

	public String getSisscode() {
		return this.getField(9);
	}

	public void setExestcode(String exEstcode) {
		this.setField(10, exEstcode);
	}

	public String getExestcode() {
		return this.getField(10);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
