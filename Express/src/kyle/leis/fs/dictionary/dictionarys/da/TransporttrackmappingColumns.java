package kyle.leis.fs.dictionary.dictionarys.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class TransporttrackmappingColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TransporttrackmappingColumns() {
		m_astrColumns = new String[2];
	}
	
	public TransporttrackmappingColumns(String ttmcomp_idTwbscode, 
            String ttmcomp_idWbtscode){
		m_astrColumns = new String[2];
		setTtmcomp_idtwbscode(ttmcomp_idTwbscode);
		setTtmcomp_idwbtscode(ttmcomp_idWbtscode);
	}

	public void setTtmcomp_idtwbscode(String ttmcomp_idTwbscode) {
		this.setField(0, ttmcomp_idTwbscode);
	}

	public String getTtmcomp_idtwbscode() {
		return this.getField(0);
	}

	public void setTtmcomp_idwbtscode(String ttmcomp_idWbtscode) {
		this.setField(1, ttmcomp_idWbtscode);
	}

	public String getTtmcomp_idwbtscode() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
