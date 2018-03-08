package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class BillrecordstatusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public BillrecordstatusColumns() {
		
	}
	
	public BillrecordstatusColumns(String brsBrscode, 
            String brsBrsname, String brsBrsename){
		m_astrColumns = new String[3];
		setBrsbrscode(brsBrscode);
		setBrsbrsname(brsBrsname);
		setBrsbrsename(brsBrsename);
	}

	public void setBrsbrscode(String brsBrscode) {
		this.setField(0, brsBrscode);
	}

	public String getBrsbrscode() {
		return this.getField(0);
	}

	public void setBrsbrsname(String brsBrsname) {
		this.setField(1, brsBrsname);
	}

	public String getBrsbrsname() {
		return this.getField(1);
	}

	public void setBrsbrsename(String brsBrsename) {
		this.setField(2, brsBrsename);
	}

	public String getBrsbrsename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
