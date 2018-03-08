package kyle.leis.fs.dictionary.dictionarys.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class EparceldistrictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public EparceldistrictColumns() {
		m_astrColumns = new String[3];
	}
	
	public EparceldistrictColumns(String epdEpd_hubcode, 
            String epdEpd_startpostcode, String epdEpd_endtpostcode){
		m_astrColumns = new String[3];
		setEpdepd_hubcode(epdEpd_hubcode);
		setEpdepd_startpostcode(epdEpd_startpostcode);
		setEpdepd_endtpostcode(epdEpd_endtpostcode);
	}

	public void setEpdepd_hubcode(String epdEpd_hubcode) {
		this.setField(0, epdEpd_hubcode);
	}

	public String getEpdepd_hubcode() {
		return this.getField(0);
	}

	public void setEpdepd_startpostcode(String epdEpd_startpostcode) {
		this.setField(1, epdEpd_startpostcode);
	}

	public String getEpdepd_startpostcode() {
		return this.getField(1);
	}

	public void setEpdepd_endtpostcode(String epdEpd_endtpostcode) {
		this.setField(2, epdEpd_endtpostcode);
	}

	public String getEpdepd_endtpostcode() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
