package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ChannelstatusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ChannelstatusColumns() {
		m_astrColumns = new String[3];
	}
	
	public ChannelstatusColumns(String csCscode, 
            String csCsname, String csCsename){
		m_astrColumns = new String[3];
		setCscscode(csCscode);
		setCscsname(csCsname);
		setCscsename(csCsename);
	}

	public void setCscscode(String csCscode) {
		this.setField(0, csCscode);
	}

	public String getCscscode() {
		return this.getField(0);
	}

	public void setCscsname(String csCsname) {
		this.setField(1, csCsname);
	}

	public String getCscsname() {
		return this.getField(1);
	}

	public void setCscsename(String csCsename) {
		this.setField(2, csCsename);
	}

	public String getCscsename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
