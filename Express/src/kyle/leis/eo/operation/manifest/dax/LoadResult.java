package kyle.leis.eo.operation.manifest.dax;

import java.util.List;

import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.operation.manifest.da.ManifestColumns;

public class LoadResult {
	private ManifestColumns m_objManifestColumns;
	private List m_listManifestvalue;
	
	public ManifestColumns getManifestColumns() {
		return m_objManifestColumns;
	}
	
	public void setManifestColumns(ManifestColumns objManifestColumns) {
		m_objManifestColumns = objManifestColumns;
	}
	
	public List getManifestvalue() {
		return m_listManifestvalue;
	}
	
	public void setManifestvalue(List listManifestvalue) {
		m_listManifestvalue = listManifestvalue;
	}
	
	public String toString() {
		Encoder objEncode = new Encoder();
		objEncode.addParameter(m_objManifestColumns);
		objEncode.addParameter(m_listManifestvalue);
		return objEncode.toString();			
	}
	
}
