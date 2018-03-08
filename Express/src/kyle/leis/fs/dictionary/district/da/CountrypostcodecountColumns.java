package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CountrypostcodecountColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CountrypostcodecountColumns() {
		m_astrColumns = new String[1];
	}
	
	public CountrypostcodecountColumns(String C){
		m_astrColumns = new String[1];
		setC(C);
	}

	public void setC(String C) {
		this.setField(0, C);
	}

	public String getC() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
