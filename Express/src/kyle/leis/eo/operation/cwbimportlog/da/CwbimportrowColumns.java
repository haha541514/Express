package kyle.leis.eo.operation.cwbimportlog.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CwbimportrowColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CwbimportrowColumns() {
		m_astrColumns = new String[5];
	}
	
	public CwbimportrowColumns(Long cwbrcomp_idCwbrid, 
            Long cwbrtopcwbimportlogCwlid, String cwbrCwbrsuccesssign, 
            String cwbrCwbroperatetype, String cwbrCwbrremark){
		m_astrColumns = new String[5];
		setCwbrcomp_idcwbrid(cwbrcomp_idCwbrid);
		setCwbrtopcwbimportlogcwlid(cwbrtopcwbimportlogCwlid);
		setCwbrcwbrsuccesssign(cwbrCwbrsuccesssign);
		setCwbrcwbroperatetype(cwbrCwbroperatetype);
		setCwbrcwbrremark(cwbrCwbrremark);
	}

	public void setCwbrcomp_idcwbrid(Long cwbrcomp_idCwbrid) {
		this.setField(0, cwbrcomp_idCwbrid);
	}

	public String getCwbrcomp_idcwbrid() {
		return this.getField(0);
	}

	public void setCwbrtopcwbimportlogcwlid(Long cwbrtopcwbimportlogCwlid) {
		this.setField(1, cwbrtopcwbimportlogCwlid);
	}

	public String getCwbrtopcwbimportlogcwlid() {
		return this.getField(1);
	}

	public void setCwbrcwbrsuccesssign(String cwbrCwbrsuccesssign) {
		this.setField(2, cwbrCwbrsuccesssign);
	}

	public String getCwbrcwbrsuccesssign() {
		return this.getField(2);
	}

	public void setCwbrcwbroperatetype(String cwbrCwbroperatetype) {
		this.setField(3, cwbrCwbroperatetype);
	}

	public String getCwbrcwbroperatetype() {
		return this.getField(3);
	}

	public void setCwbrcwbrremark(String cwbrCwbrremark) {
		this.setField(4, cwbrCwbrremark);
	}

	public String getCwbrcwbrremark() {
		return this.getField(4);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
