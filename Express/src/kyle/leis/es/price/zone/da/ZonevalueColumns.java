package kyle.leis.es.price.zone.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ZonevalueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ZonevalueColumns() {
		
	}
	
	public ZonevalueColumns(Long znvcomp_idZnvid, 
            String znvZnvname, String znvZnvename, 
            String znvZnvstructurecode, Long znZncode, 
            String znZnname){
		m_astrColumns = new String[6];
		setZnvcomp_idznvid(znvcomp_idZnvid);
		setZnvznvname(znvZnvname);
		setZnvznvename(znvZnvename);
		setZnvznvstructurecode(znvZnvstructurecode);
		setZnzncode(znZncode);
		setZnznname(znZnname);
	}

	public void setZnvcomp_idznvid(Long znvcomp_idZnvid) {
		this.setField(0, znvcomp_idZnvid);
	}

	public String getZnvcomp_idznvid() {
		return this.getField(0);
	}

	public void setZnvznvname(String znvZnvname) {
		this.setField(1, znvZnvname);
	}

	public String getZnvznvname() {
		return this.getField(1);
	}

	public void setZnvznvename(String znvZnvename) {
		this.setField(2, znvZnvename);
	}

	public String getZnvznvename() {
		return this.getField(2);
	}

	public void setZnvznvstructurecode(String znvZnvstructurecode) {
		this.setField(3, znvZnvstructurecode);
	}

	public String getZnvznvstructurecode() {
		return this.getField(3);
	}

	public void setZnzncode(Long znZncode) {
		this.setField(4, znZncode);
	}

	public String getZnzncode() {
		return this.getField(4);
	}

	public void setZnznname(String znZnname) {
		this.setField(5, znZnname);
	}

	public String getZnznname() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
