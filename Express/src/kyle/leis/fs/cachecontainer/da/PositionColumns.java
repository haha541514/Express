package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PositionColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PositionColumns() {
		m_astrColumns = new String[3];
	}
	
	public PositionColumns(String psPscode, 
            String psPsname, String psPsename){
		m_astrColumns = new String[3];
		setPspscode(psPscode);
		setPspsname(psPsname);
		setPspsename(psPsename);
	}

	public void setPspscode(String psPscode) {
		this.setField(0, psPscode);
	}

	public String getPspscode() {
		return this.getField(0);
	}

	public void setPspsname(String psPsname) {
		this.setField(1, psPsname);
	}

	public String getPspsname() {
		return this.getField(1);
	}

	public void setPspsename(String psPsename) {
		this.setField(2, psPsename);
	}

	public String getPspsename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
