package kyle.leis.fs.dictionary.dictionarys.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class TemplatecolumnColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TemplatecolumnColumns() {
		m_astrColumns = new String[9];
	}
	
	public TemplatecolumnColumns(String tcTcid, 
            int tcTccolumnindex, String tcTccolumnname, 
            String tcTccolumnename, String tcTccolumngroup, 
            String tcTccolumntype, String tcTcallownullsign, 
            int tcTcmaxlength, int tcTcminlength){
		m_astrColumns = new String[9];
		setTctcid(tcTcid);
		setTctccolumnindex(tcTccolumnindex);
		setTctccolumnname(tcTccolumnname);
		setTctccolumnename(tcTccolumnename);
		setTctccolumngroup(tcTccolumngroup);
		setTctccolumntype(tcTccolumntype);
		setTctcallownullsign(tcTcallownullsign);
		setTctcmaxlength(tcTcmaxlength);
		setTctcminlength(tcTcminlength);
	}

	public void setTctcid(String tcTcid) {
		this.setField(0, tcTcid);
	}

	public String getTctcid() {
		return this.getField(0);
	}

	public void setTctccolumnindex(int tcTccolumnindex) {
		this.setField(1, tcTccolumnindex);
	}

	public String getTctccolumnindex() {
		return this.getField(1);
	}

	public void setTctccolumnname(String tcTccolumnname) {
		this.setField(2, tcTccolumnname);
	}

	public String getTctccolumnname() {
		return this.getField(2);
	}

	public void setTctccolumnename(String tcTccolumnename) {
		this.setField(3, tcTccolumnename);
	}

	public String getTctccolumnename() {
		return this.getField(3);
	}

	public void setTctccolumngroup(String tcTccolumngroup) {
		this.setField(4, tcTccolumngroup);
	}

	public String getTctccolumngroup() {
		return this.getField(4);
	}

	public void setTctccolumntype(String tcTccolumntype) {
		this.setField(5, tcTccolumntype);
	}

	public String getTctccolumntype() {
		return this.getField(5);
	}

	public void setTctcallownullsign(String tcTcallownullsign) {
		this.setField(6, tcTcallownullsign);
	}

	public String getTctcallownullsign() {
		return this.getField(6);
	}

	public void setTctcmaxlength(int tcTcmaxlength) {
		this.setField(7, tcTcmaxlength);
	}

	public String getTctcmaxlength() {
		return this.getField(7);
	}

	public void setTctcminlength(int tcTcminlength) {
		this.setField(8, tcTcminlength);
	}

	public String getTctcminlength() {
		return this.getField(8);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
