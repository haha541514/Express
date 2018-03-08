package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CachecontainerColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CachecontainerColumns() {
		m_astrColumns = new String[7];
	}
	
	public CachecontainerColumns(String ccCccode, 
            String ccCcname, String ccCcbatchnumber, 
            String ccCctotalsign, String ccCcsql, 
            String iskIskcode, String iskIskname){
		m_astrColumns = new String[7];
		setCccccode(ccCccode);
		setCcccname(ccCcname);
		setCcccbatchnumber(ccCcbatchnumber);
		setCccctotalsign(ccCctotalsign);
		setCcccsql(ccCcsql);
		setIskiskcode(iskIskcode);
		setIskiskname(iskIskname);
	}

	public void setCccccode(String ccCccode) {
		this.setField(0, ccCccode);
	}

	public String getCccccode() {
		return this.getField(0);
	}

	public void setCcccname(String ccCcname) {
		this.setField(1, ccCcname);
	}

	public String getCcccname() {
		return this.getField(1);
	}

	public void setCcccbatchnumber(String ccCcbatchnumber) {
		this.setField(2, ccCcbatchnumber);
	}

	public String getCcccbatchnumber() {
		return this.getField(2);
	}

	public void setCccctotalsign(String ccCctotalsign) {
		this.setField(3, ccCctotalsign);
	}

	public String getCccctotalsign() {
		return this.getField(3);
	}

	public void setCcccsql(String ccCcsql) {
		this.setField(4, ccCcsql);
	}

	public String getCcccsql() {
		return this.getField(4);
	}

	public void setIskiskcode(String iskIskcode) {
		this.setField(5, iskIskcode);
	}

	public String getIskiskcode() {
		return this.getField(5);
	}

	public void setIskiskname(String iskIskname) {
		this.setField(6, iskIskname);
	}

	public String getIskiskname() {
		return this.getField(6);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
