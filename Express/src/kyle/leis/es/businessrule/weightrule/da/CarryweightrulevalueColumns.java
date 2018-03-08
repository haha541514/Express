package kyle.leis.es.businessrule.weightrule.da;

import java.io.Serializable;
import java.math.BigDecimal;

import kyle.common.dbaccess.query.GeneralColumns;

public class CarryweightrulevalueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CarryweightrulevalueColumns() {
		
	}
	
	public CarryweightrulevalueColumns(Integer cwrvcomp_idCwrvid, 
            Long cwrvcomp_idBrid, BigDecimal cwrvCwrvweightgrade, 
            BigDecimal cwrvCwrvvalue){
		m_astrColumns = new String[4];
		setCwrvcomp_idcwrvid(cwrvcomp_idCwrvid);
		setCwrvcomp_idbrid(cwrvcomp_idBrid);
		setCwrvcwrvweightgrade(cwrvCwrvweightgrade);
		setCwrvcwrvvalue(cwrvCwrvvalue);
	}

	public void setCwrvcomp_idcwrvid(Integer cwrvcomp_idCwrvid) {
		this.setField(0, cwrvcomp_idCwrvid);
	}

	public String getCwrvcomp_idcwrvid() {
		return this.getField(0);
	}

	public void setCwrvcomp_idbrid(Long cwrvcomp_idBrid) {
		this.setField(1, cwrvcomp_idBrid);
	}

	public String getCwrvcomp_idbrid() {
		return this.getField(1);
	}

	public void setCwrvcwrvweightgrade(BigDecimal cwrvCwrvweightgrade) {
		this.setField(2, cwrvCwrvweightgrade);
	}

	public String getCwrvcwrvweightgrade() {
		return this.getField(2);
	}

	public void setCwrvcwrvvalue(BigDecimal cwrvCwrvvalue) {
		this.setField(3, cwrvCwrvvalue);
	}

	public String getCwrvcwrvvalue() {
		return this.getField(3);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
