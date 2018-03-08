package kyle.leis.fs.dictionary.expressspecialtype.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ExpressspecialtypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ExpressspecialtypeColumns() {
		m_astrColumns = new String[8];
	}
	
	public ExpressspecialtypeColumns(String estEstcode, 
            String ssSscode, String estEstname, 
            String estEstename, String estEststructurecode, 
            String estEstendsign, String estEstexcludesign, 
            String estEstpeculiarlychannelsign){
		m_astrColumns = new String[8];
		setEstestcode(estEstcode);
		setSssscode(ssSscode);
		setEstestname(estEstname);
		setEstestename(estEstename);
		setEsteststructurecode(estEststructurecode);
		setEstestendsign(estEstendsign);
		setEstestexcludesign(estEstexcludesign);
		setEstestpeculiarlychannelsign(estEstpeculiarlychannelsign);
	}

	public void setEstestcode(String estEstcode) {
		this.setField(0, estEstcode);
	}

	public String getEstestcode() {
		return this.getField(0);
	}

	public void setSssscode(String ssSscode) {
		this.setField(1, ssSscode);
	}

	public String getSssscode() {
		return this.getField(1);
	}

	public void setEstestname(String estEstname) {
		this.setField(2, estEstname);
	}

	public String getEstestname() {
		return this.getField(2);
	}

	public void setEstestename(String estEstename) {
		this.setField(3, estEstename);
	}

	public String getEstestename() {
		return this.getField(3);
	}

	public void setEsteststructurecode(String estEststructurecode) {
		this.setField(4, estEststructurecode);
	}

	public String getEsteststructurecode() {
		return this.getField(4);
	}

	public void setEstestendsign(String estEstendsign) {
		this.setField(5, estEstendsign);
	}

	public String getEstestendsign() {
		return this.getField(5);
	}

	public void setEstestexcludesign(String estEstexcludesign) {
		this.setField(6, estEstexcludesign);
	}

	public String getEstestexcludesign() {
		return this.getField(6);
	}

	public void setEstestpeculiarlychannelsign(String estEstpeculiarlychannelsign) {
		this.setField(7, estEstpeculiarlychannelsign);
	}

	public String getEstestpeculiarlychannelsign() {
		return this.getField(7);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
