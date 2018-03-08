package kyle.leis.es.businessrule.manifestexportformat.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ManifestCusExportcolumnColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;
    
	public ManifestCusExportcolumnColumns(int size) {
		m_astrColumns = new String[size];
	}
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
