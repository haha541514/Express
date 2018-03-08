package kyle.leis.eo.customerservice.track.da;

import java.io.Serializable;

import kyle.common.dbaccess.query.GeneralColumns;

public class SimplebillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SimplebillColumns() {
		m_astrColumns = new String[3];
	}
	
	public SimplebillColumns(String swbSwb_code,String swbSwb_customerewbcode){
		m_astrColumns = new String[3];
		setSwbswb_code(swbSwb_code);
		setSwbswb_customerewbcode(swbSwb_customerewbcode);
	}
    public void setSwbswb_code(String swbSwb_code){
    	this.setField(0, swbSwb_code);
    }
    public String getSwbswb_code(){
    	return this.getField(0);
    }
	public void setSwbswb_customerewbcode(String swbSwb_customerewbcode) {
		this.setField(1, swbSwb_customerewbcode);
	}

	public String getSwbswb_customerewbcode() {
		return this.getField(1);
	}

    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
