package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PricedomainColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PricedomainColumns() {
		
	}
	
	public PricedomainColumns(String pdPdcode, 
            String pdPdname, String pdPdename){
		m_astrColumns = new String[3];
		setPdpdcode(pdPdcode);
		setPdpdname(pdPdname);
		setPdpdename(pdPdename);
	}

	public void setPdpdcode(String pdPdcode) {
		this.setField(0, pdPdcode);
	}

	public String getPdpdcode() {
		return this.getField(0);
	}

	public void setPdpdname(String pdPdname) {
		this.setField(1, pdPdname);
	}

	public String getPdpdname() {
		return this.getField(1);
	}

	public void setPdpdename(String pdPdename) {
		this.setField(2, pdPdename);
	}

	public String getPdpdename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
