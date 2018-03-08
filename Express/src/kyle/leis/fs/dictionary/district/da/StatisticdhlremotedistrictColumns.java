package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class StatisticdhlremotedistrictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public StatisticdhlremotedistrictColumns() {
		m_astrColumns = new String[2];
	}
	
	public StatisticdhlremotedistrictColumns(String Nationname, 
            String Counts){
		m_astrColumns = new String[2];
		setNationname(Nationname);
		setCounts(Counts);
	}

	public void setNationname(String Nationname) {
		this.setField(0, Nationname);
	}

	public String getNationname() {
		return this.getField(0);
	}

	public void setCounts(String Counts) {
		this.setField(1, Counts);
	}

	public String getCounts() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
