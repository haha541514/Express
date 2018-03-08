package kyle.leis.eo.operation.cwbimportlog.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CwbimportdataColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CwbimportdataColumns() {
		m_astrColumns = new String[4];
	}
	
	public CwbimportdataColumns(Long cwbdcomp_idCwbrid, 
            Long cwbdtopcwbimportrowtopcwbimportlogCwlid, String cwbdcomp_idCwbdcolumnname, 
            String cwbdCwbdvalue){
		m_astrColumns = new String[4];
		setCwbdcomp_idcwbrid(cwbdcomp_idCwbrid);
		setCwbdtopcwbimportrowtopcwbimportlogcwlid(cwbdtopcwbimportrowtopcwbimportlogCwlid);
		setCwbdcomp_idcwbdcolumnname(cwbdcomp_idCwbdcolumnname);
		setCwbdcwbdvalue(cwbdCwbdvalue);
	}

	public void setCwbdcomp_idcwbrid(Long cwbdcomp_idCwbrid) {
		this.setField(0, cwbdcomp_idCwbrid);
	}

	public String getCwbdcomp_idcwbrid() {
		return this.getField(0);
	}

	public void setCwbdtopcwbimportrowtopcwbimportlogcwlid(Long cwbdtopcwbimportrowtopcwbimportlogCwlid) {
		this.setField(1, cwbdtopcwbimportrowtopcwbimportlogCwlid);
	}

	public String getCwbdtopcwbimportrowtopcwbimportlogcwlid() {
		return this.getField(1);
	}

	public void setCwbdcomp_idcwbdcolumnname(String cwbdcomp_idCwbdcolumnname) {
		this.setField(2, cwbdcomp_idCwbdcolumnname);
	}

	public String getCwbdcomp_idcwbdcolumnname() {
		return this.getField(2);
	}

	public void setCwbdcwbdvalue(String cwbdCwbdvalue) {
		this.setField(3, cwbdCwbdvalue);
	}

	public String getCwbdcwbdvalue() {
		return this.getField(3);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
