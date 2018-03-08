package kyle.leis.fs.dictionary.issuetype.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class TdiissuetypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TdiissuetypeColumns() {
		m_astrColumns = new String[8];
	}
	
	public TdiissuetypeColumns(String isutIsutcode, 
            String isutIsutname, String isutIsutename, 
            String isutIsutgroup, String isutIsutcustomervisiblesign, 
            String isutIsutnoticeinfo, String ssSscode, 
            String ssSsname){
		m_astrColumns = new String[8];
		setIsutisutcode(isutIsutcode);
		setIsutisutname(isutIsutname);
		setIsutisutename(isutIsutename);
		setIsutisutgroup(isutIsutgroup);
		setIsutisutcustomervisiblesign(isutIsutcustomervisiblesign);
		setIsutisutnoticeinfo(isutIsutnoticeinfo);
		setSssscode(ssSscode);
		setSsssname(ssSsname);
	}

	public void setIsutisutcode(String isutIsutcode) {
		this.setField(0, isutIsutcode);
	}

	public String getIsutisutcode() {
		return this.getField(0);
	}

	public void setIsutisutname(String isutIsutname) {
		this.setField(1, isutIsutname);
	}

	public String getIsutisutname() {
		return this.getField(1);
	}

	public void setIsutisutename(String isutIsutename) {
		this.setField(2, isutIsutename);
	}

	public String getIsutisutename() {
		return this.getField(2);
	}

	public void setIsutisutgroup(String isutIsutgroup) {
		this.setField(3, isutIsutgroup);
	}

	public String getIsutisutgroup() {
		return this.getField(3);
	}

	public void setIsutisutcustomervisiblesign(String isutIsutcustomervisiblesign) {
		this.setField(4, isutIsutcustomervisiblesign);
	}

	public String getIsutisutcustomervisiblesign() {
		return this.getField(4);
	}

	public void setIsutisutnoticeinfo(String isutIsutnoticeinfo) {
		this.setField(5, isutIsutnoticeinfo);
	}

	public String getIsutisutnoticeinfo() {
		return this.getField(5);
	}

	public void setSssscode(String ssSscode) {
		this.setField(6, ssSscode);
	}

	public String getSssscode() {
		return this.getField(6);
	}

	public void setSsssname(String ssSsname) {
		this.setField(7, ssSsname);
	}

	public String getSsssname() {
		return this.getField(7);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
