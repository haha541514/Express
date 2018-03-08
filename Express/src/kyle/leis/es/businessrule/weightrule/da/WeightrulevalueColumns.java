package kyle.leis.es.businessrule.weightrule.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WeightrulevalueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WeightrulevalueColumns() {
		
	}
	
	public WeightrulevalueColumns(Long wrvcomp_idBrid, 
            Integer wrvcomp_idBrvid, Long wrvZnid, 
            Integer wrvZnvid, String wrvZnvcondition, 
            String wrvZnvexpression){
		m_astrColumns = new String[6];
		setWrvcomp_idbrid(wrvcomp_idBrid);
		setWrvcomp_idbrvid(wrvcomp_idBrvid);
		setWrvznid(wrvZnid);
		setWrvznvid(wrvZnvid);
		setWrvznvcondition(wrvZnvcondition);
		setWrvznvexpression(wrvZnvexpression);
	}

	public void setWrvcomp_idbrid(Long wrvcomp_idBrid) {
		this.setField(0, wrvcomp_idBrid);
	}

	public String getWrvcomp_idbrid() {
		return this.getField(0);
	}

	public void setWrvcomp_idbrvid(Integer wrvcomp_idBrvid) {
		this.setField(1, wrvcomp_idBrvid);
	}

	public String getWrvcomp_idbrvid() {
		return this.getField(1);
	}

	public void setWrvznid(Long wrvZnid) {
		this.setField(2, wrvZnid);
	}

	public String getWrvznid() {
		return this.getField(2);
	}

	public void setWrvznvid(Integer wrvZnvid) {
		this.setField(3, wrvZnvid);
	}

	public String getWrvznvid() {
		return this.getField(3);
	}

	public void setWrvznvcondition(String wrvZnvcondition) {
		this.setField(4, wrvZnvcondition);
	}

	public String getWrvznvcondition() {
		return this.getField(4);
	}

	public void setWrvznvexpression(String wrvZnvexpression) {
		this.setField(5, wrvZnvexpression);
	}

	public String getWrvznvexpression() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
