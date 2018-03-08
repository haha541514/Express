package kyle.leis.es.businessrule.weightrule.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class VolumeweightrulevalueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public VolumeweightrulevalueColumns() {
		
	}
	
	public VolumeweightrulevalueColumns(Integer vwrvcomp_idVwrvid, 
            Long vwrvcomp_idBrid, Long vwrvZnid, 
            Integer vwrvZnvid, int vwrvVwrvvalue){
		m_astrColumns = new String[5];
		setVwrvcomp_idvwrvid(vwrvcomp_idVwrvid);
		setVwrvcomp_idbrid(vwrvcomp_idBrid);
		setVwrvznid(vwrvZnid);
		setVwrvznvid(vwrvZnvid);
		setVwrvvwrvvalue(vwrvVwrvvalue);
	}

	public void setVwrvcomp_idvwrvid(Integer vwrvcomp_idVwrvid) {
		this.setField(0, vwrvcomp_idVwrvid);
	}

	public String getVwrvcomp_idvwrvid() {
		return this.getField(0);
	}

	public void setVwrvcomp_idbrid(Long vwrvcomp_idBrid) {
		this.setField(1, vwrvcomp_idBrid);
	}

	public String getVwrvcomp_idbrid() {
		return this.getField(1);
	}

	public void setVwrvznid(Long vwrvZnid) {
		this.setField(2, vwrvZnid);
	}

	public String getVwrvznid() {
		return this.getField(2);
	}

	public void setVwrvznvid(Integer vwrvZnvid) {
		this.setField(3, vwrvZnvid);
	}

	public String getVwrvznvid() {
		return this.getField(3);
	}

	public void setVwrvvwrvvalue(int vwrvVwrvvalue) {
		this.setField(4, vwrvVwrvvalue);
	}

	public String getVwrvvwrvvalue() {
		return this.getField(4);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
