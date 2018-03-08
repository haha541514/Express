package kyle.fetcher.track.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class MaxtrackdateColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public MaxtrackdateColumns() {
		m_astrColumns = new String[1];
	}
	
	public MaxtrackdateColumns(String Maxoccurdate){
		m_astrColumns = new String[1];
		setMaxoccurdate(Maxoccurdate);
	}

	public void setMaxoccurdate(String Maxoccurdate) {
		this.setField(0, Maxoccurdate);
	}

	public String getMaxoccurdate() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
