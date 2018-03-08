package kyle.leis.es.price.zone.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ZonedistrictpostcodeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ZonedistrictpostcodeColumns() {
		
	}
	
	public ZonedistrictpostcodeColumns(String zndpZdpstartpostcode, 
            String zndpZdpendpostcode, Long znvdcomp_idZnvid, 
            String znvdcomp_idDtcode, Long znvcomp_idZnvid, 
            String znvZnvname, String znvZnvename){
		m_astrColumns = new String[7];
		setZndpzdpstartpostcode(zndpZdpstartpostcode);
		setZndpzdpendpostcode(zndpZdpendpostcode);
		setZnvdcomp_idznvid(znvdcomp_idZnvid);
		setZnvdcomp_iddtcode(znvdcomp_idDtcode);
		setZnvcomp_idznvid(znvcomp_idZnvid);
		setZnvznvname(znvZnvname);
		setZnvznvename(znvZnvename);
	}

	public void setZndpzdpstartpostcode(String zndpZdpstartpostcode) {
		this.setField(0, zndpZdpstartpostcode);
	}

	public String getZndpzdpstartpostcode() {
		return this.getField(0);
	}

	public void setZndpzdpendpostcode(String zndpZdpendpostcode) {
		this.setField(1, zndpZdpendpostcode);
	}

	public String getZndpzdpendpostcode() {
		return this.getField(1);
	}

	public void setZnvdcomp_idznvid(Long znvdcomp_idZnvid) {
		this.setField(2, znvdcomp_idZnvid);
	}

	public String getZnvdcomp_idznvid() {
		return this.getField(2);
	}

	public void setZnvdcomp_iddtcode(String znvdcomp_idDtcode) {
		this.setField(3, znvdcomp_idDtcode);
	}

	public String getZnvdcomp_iddtcode() {
		return this.getField(3);
	}

	public void setZnvcomp_idznvid(Long znvcomp_idZnvid) {
		this.setField(4, znvcomp_idZnvid);
	}

	public String getZnvcomp_idznvid() {
		return this.getField(4);
	}

	public void setZnvznvname(String znvZnvname) {
		this.setField(5, znvZnvname);
	}

	public String getZnvznvname() {
		return this.getField(5);
	}

	public void setZnvznvename(String znvZnvename) {
		this.setField(6, znvZnvename);
	}

	public String getZnvznvename() {
		return this.getField(6);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
