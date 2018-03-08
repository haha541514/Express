package kyle.leis.fs.businesslog.dax;

import java.util.List;

import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.businesslog.da.BusinesslogColumns;

public class BusinesslogQueryReturn {
	private List<BusinesslogColumns> m_objBusinesslogCol;

	
	public List<BusinesslogColumns> getM_objBusinesslogCol() {
		return m_objBusinesslogCol;
	}


	public void setM_objBusinesslogCol(List<BusinesslogColumns> businesslogCol) {
		m_objBusinesslogCol = businesslogCol;
	}



	public String toString()
	{
		Encoder encode = new Encoder();
		encode.addParameter(m_objBusinesslogCol);
		return encode.toString();
	}
}
