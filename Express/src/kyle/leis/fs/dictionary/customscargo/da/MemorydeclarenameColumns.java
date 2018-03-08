package kyle.leis.fs.dictionary.customscargo.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class MemorydeclarenameColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public MemorydeclarenameColumns() {
		m_astrColumns = new String[4];
	}
	
	public MemorydeclarenameColumns(Long mdnMdncode, 
            String mdnMdnlabelcode, String mdnMdnename, 
            String mdnMdnname){
		m_astrColumns = new String[4];
		setMdnmdncode(mdnMdncode);
		setMdnmdnlabelcode(mdnMdnlabelcode);
		setMdnmdnename(mdnMdnename);
		setMdnmdnname(mdnMdnname);
	}

	public void setMdnmdncode(Long mdnMdncode) {
		this.setField(0, mdnMdncode);
	}

	public String getMdnmdncode() {
		return this.getField(0);
	}

	public void setMdnmdnlabelcode(String mdnMdnlabelcode) {
		this.setField(1, mdnMdnlabelcode);
	}

	public String getMdnmdnlabelcode() {
		return this.getField(1);
	}

	public void setMdnmdnename(String mdnMdnename) {
		this.setField(2, mdnMdnename);
	}

	public String getMdnmdnename() {
		return this.getField(2);
	}

	public void setMdnmdnname(String mdnMdnname) {
		this.setField(3, mdnMdnname);
	}

	public String getMdnmdnname() {
		return this.getField(3);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
