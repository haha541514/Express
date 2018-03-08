package kyle.leis.fs.dictionary.customscargo.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CustomscargoseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CustomscargoseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public CustomscargoseqColumns(String Customscargoseq){
		m_astrColumns = new String[1];
		setCustomscargoseq(Customscargoseq);
	}

	public void setCustomscargoseq(String Customscargoseq) {
		this.setField(0, Customscargoseq);
	}

	public String getCustomscargoseq() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
