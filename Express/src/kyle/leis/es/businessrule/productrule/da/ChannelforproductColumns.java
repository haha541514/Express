package kyle.leis.es.businessrule.productrule.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ChannelforproductColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ChannelforproductColumns() {
		m_astrColumns = new String[3];
	}
	
	public ChannelforproductColumns(String chnChn_sname, 
            String pkPk_sname, String pkPk_name){
		m_astrColumns = new String[3];
		setChnchn_sname(chnChn_sname);
		setPkpk_sname(pkPk_sname);
		setPkpk_name(pkPk_name);
	}

	public void setChnchn_sname(String chnChn_sname) {
		this.setField(0, chnChn_sname);
	}

	public String getChnchn_sname() {
		return this.getField(0);
	}

	public void setPkpk_sname(String pkPk_sname) {
		this.setField(1, pkPk_sname);
	}

	public String getPkpk_sname() {
		return this.getField(1);
	}

	public void setPkpk_name(String pkPk_name) {
		this.setField(2, pkPk_name);
	}

	public String getPkpk_name() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
