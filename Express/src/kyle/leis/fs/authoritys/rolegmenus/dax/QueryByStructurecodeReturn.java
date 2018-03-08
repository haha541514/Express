package kyle.leis.fs.authoritys.rolegmenus.dax;

import java.util.List;

import kyle.common.connectors.util.Encoder;

public class QueryByStructurecodeReturn {

	private List m_listSelfRolegmenusColumns;
	private List m_listFatherRolegmenusColumns;
	

	public List getM_listSelfRolegmenusColumns() {
		return m_listSelfRolegmenusColumns;
	}

	public void setM_listSelfRolegmenusColumns(List selfRolegmenusColumns) {
		m_listSelfRolegmenusColumns = selfRolegmenusColumns;
	}

	public List getM_listFatherRolegmenusColumns() {
		return m_listFatherRolegmenusColumns;
	}

	public void setM_listFatherRolegmenusColumns(List fatherRolegmenusColumns) {
		m_listFatherRolegmenusColumns = fatherRolegmenusColumns;
	}

	public String toString()
	{
		Encoder objEncode = new Encoder();
		objEncode.addParameter(m_listSelfRolegmenusColumns);
		objEncode.addParameter(m_listFatherRolegmenusColumns);
		return objEncode.toString();
	}
}
