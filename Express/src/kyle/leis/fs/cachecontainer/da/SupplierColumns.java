package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SupplierColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SupplierColumns() {
		
	}
	
	public SupplierColumns(String strCocode, String strSpmanifestseriesnumber){
		m_astrColumns = new String[2];
		setCocode(strCocode);
		setSpmanifestseriesnumber(strSpmanifestseriesnumber);
	}

	public void setCocode(String strCocode) {
		this.setField(0, strCocode);
	}

	public String getCocode() {
		return this.getField(0);
	}


	public void setSpmanifestseriesnumber(String strSpmanifestseriesnumber) {
		this.setField(1, strSpmanifestseriesnumber);
	}

	public String getSpmanifestseriesnumber() {
		return this.getField(1);
	}


	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
