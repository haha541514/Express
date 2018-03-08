package kyle.leis.es.price.zone.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ZonevaluedistrictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ZonevaluedistrictColumns() {
		
	}
	
	public ZonevaluedistrictColumns(Long znvcomp_idZnvid, 
            String znvZnvname, String znvZnvename, 
            String dtDtcode, String dtDthubcode, 
            String dtDtname, String dtDtename){
		m_astrColumns = new String[7];
		setZnvcomp_idznvid(znvcomp_idZnvid);
		setZnvznvname(znvZnvname);
		setZnvznvename(znvZnvename);
		setDtdtcode(dtDtcode);
		setDtdthubcode(dtDthubcode);
		setDtdtname(dtDtname);
		setDtdtename(dtDtename);
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

	public void setDtdtcode(String dtDtcode) {
		this.setField(3, dtDtcode);
	}

	public String getDtdtcode() {
		return this.getField(3);
	}

	public void setDtdthubcode(String dtDthubcode) {
		this.setField(4, dtDthubcode);
	}

	public String getDtdthubcode() {
		return this.getField(4);
	}

	public void setDtdtname(String dtDtname) {
		this.setField(5, dtDtname);
	}

	public String getDtdtname() {
		return this.getField(5);
	}

	public void setDtdtename(String dtDtename) {
		this.setField(6, dtDtename);
	}

	public String getDtdtename() {
		return this.getField(6);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
