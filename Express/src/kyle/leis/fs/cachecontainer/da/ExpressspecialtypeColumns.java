package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ExpressspecialtypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ExpressspecialtypeColumns() {
		m_astrColumns = new String[6];
	}
	
	public ExpressspecialtypeColumns(String estEstcode, 
            String estEstname, String estEstename, 
            String estEststructurecode, String estEstendsign, 
            String estEstexcludesign){
		m_astrColumns = new String[6];
		setEstestcode(estEstcode);
		setEstestname(estEstname);
		setEstestename(estEstename);
		setEsteststructurecode(estEststructurecode);
		setEstestendsign(estEstendsign);
		setEstestexcludesign(estEstexcludesign);
	}

	public void setEstestcode(String estEstcode) {
		this.setField(0, estEstcode);
	}

	public String getEstestcode() {
		return this.getField(0);
	}

	public void setEstestname(String estEstname) {
		this.setField(1, estEstname);
	}

	public String getEstestname() {
		return this.getField(1);
	}

	public void setEstestename(String estEstename) {
		this.setField(2, estEstename);
	}

	public String getEstestename() {
		return this.getField(2);
	}

	public void setEsteststructurecode(String estEststructurecode) {
		this.setField(3, estEststructurecode);
	}

	public String getEsteststructurecode() {
		return this.getField(3);
	}

	public void setEstestendsign(String estEstendsign) {
		this.setField(4, estEstendsign);
	}

	public String getEstestendsign() {
		return this.getField(4);
	}

	public void setEstestexcludesign(String estEstexcludesign) {
		this.setField(5, estEstexcludesign);
	}

	public String getEstestexcludesign() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
