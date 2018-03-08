package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class IssuetypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public IssuetypeColumns() {
		m_astrColumns = new String[8];
	}
	
	public IssuetypeColumns(String isutIsutcode, 
            String isutIsutname, String isutIsutename, 
            String isutIsutnoticeinfo, String isutIsutgroup, 
            String isutIsutcustomervisiblesign, String ssSscode, 
            String ssSsname){
		m_astrColumns = new String[8];
		setIsutisutcode(isutIsutcode);
		setIsutisutname(isutIsutname);
		setIsutisutename(isutIsutename);
		setIsutisutnoticeinfo(isutIsutnoticeinfo);
		setIsutisutgroup(isutIsutgroup);
		setIsutisutcustomervisiblesign(isutIsutcustomervisiblesign);
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

	public void setIsutisutnoticeinfo(String isutIsutnoticeinfo) {
		this.setField(3, isutIsutnoticeinfo);
	}

	public String getIsutisutnoticeinfo() {
		return this.getField(3);
	}

	public void setIsutisutgroup(String isutIsutgroup) {
		this.setField(4, isutIsutgroup);
	}

	public String getIsutisutgroup() {
		return this.getField(4);
	}

	public void setIsutisutcustomervisiblesign(String isutIsutcustomervisiblesign) {
		this.setField(5, isutIsutcustomervisiblesign);
	}

	public String getIsutisutcustomervisiblesign() {
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
