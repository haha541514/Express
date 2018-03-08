package kyle.leis.es.company.predicttemplate.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ColumnstemplateColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ColumnstemplateColumns() {
		m_astrColumns = new String[6];
	}
	
	public ColumnstemplateColumns(String pPotv_id, 
            String pPot_id, String pPotv_columnname, 
            String cCmt_name, String tTc_id, 
            String tTc_columnname){
		m_astrColumns = new String[6];
		setPpotv_id(pPotv_id);
		setPpot_id(pPot_id);
		setPpotv_columnname(pPotv_columnname);
		setCcmt_name(cCmt_name);
		setTtc_id(tTc_id);
		setTtc_columnname(tTc_columnname);
	}

	public void setPpotv_id(String pPotv_id) {
		this.setField(0, pPotv_id);
	}

	public String getPpotv_id() {
		return this.getField(0);
	}

	public void setPpot_id(String pPot_id) {
		this.setField(1, pPot_id);
	}

	public String getPpot_id() {
		return this.getField(1);
	}

	public void setPpotv_columnname(String pPotv_columnname) {
		this.setField(2, pPotv_columnname);
	}

	public String getPpotv_columnname() {
		return this.getField(2);
	}

	public void setCcmt_name(String cCmt_name) {
		this.setField(3, cCmt_name);
	}

	public String getCcmt_name() {
		return this.getField(3);
	}

	public void setTtc_id(String tTc_id) {
		this.setField(4, tTc_id);
	}

	public String getTtc_id() {
		return this.getField(4);
	}

	public void setTtc_columnname(String tTc_columnname) {
		this.setField(5, tTc_columnname);
	}

	public String getTtc_columnname() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
