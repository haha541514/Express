package kyle.leis.eo.operation.corewaybillcode.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CorewaybillcodeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CorewaybillcodeColumns() {
		m_astrColumns = new String[3];
	}
	
	public CorewaybillcodeColumns(Integer cwbccomp_idCwbcid, 
            Long cwbccomp_idCwcode, String cwbcCwbccustomerewbcode){
		m_astrColumns = new String[3];
		setCwbccomp_idcwbcid(cwbccomp_idCwbcid);
		setCwbccomp_idcwcode(cwbccomp_idCwcode);
		setCwbccwbccustomerewbcode(cwbcCwbccustomerewbcode);
	}

	public void setCwbccomp_idcwbcid(Integer cwbccomp_idCwbcid) {
		this.setField(0, cwbccomp_idCwbcid);
	}

	public String getCwbccomp_idcwbcid() {
		return this.getField(0);
	}

	public void setCwbccomp_idcwcode(Long cwbccomp_idCwcode) {
		this.setField(1, cwbccomp_idCwcode);
	}

	public String getCwbccomp_idcwcode() {
		return this.getField(1);
	}

	public void setCwbccwbccustomerewbcode(String cwbcCwbccustomerewbcode) {
		this.setField(2, cwbcCwbccustomerewbcode);
	}

	public String getCwbccwbccustomerewbcode() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
