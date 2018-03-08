package kyle.leis.es.businessrule.productrule.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class DistrictproductruleColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public DistrictproductruleColumns() {
		m_astrColumns = new String[5];
	}
	
	public DistrictproductruleColumns(Long dprcomp_idBrid, 
            String dprcomp_idDtcode, String dtDtcode, 
            String dtDthubcode, String dtDtname){
		m_astrColumns = new String[5];
		setDprcomp_idbrid(dprcomp_idBrid);
		setDprcomp_iddtcode(dprcomp_idDtcode);
		setDtdtcode(dtDtcode);
		setDtdthubcode(dtDthubcode);
		setDtdtname(dtDtname);
	}

	public void setDprcomp_idbrid(Long dprcomp_idBrid) {
		this.setField(0, dprcomp_idBrid);
	}

	public String getDprcomp_idbrid() {
		return this.getField(0);
	}

	public void setDprcomp_iddtcode(String dprcomp_idDtcode) {
		this.setField(1, dprcomp_idDtcode);
	}

	public String getDprcomp_iddtcode() {
		return this.getField(1);
	}

	public void setDtdtcode(String dtDtcode) {
		this.setField(2, dtDtcode);
	}

	public String getDtdtcode() {
		return this.getField(2);
	}

	public void setDtdthubcode(String dtDthubcode) {
		this.setField(3, dtDthubcode);
	}

	public String getDtdthubcode() {
		return this.getField(3);
	}

	public void setDtdtname(String dtDtname) {
		this.setField(4, dtDtname);
	}

	public String getDtdtname() {
		return this.getField(4);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
