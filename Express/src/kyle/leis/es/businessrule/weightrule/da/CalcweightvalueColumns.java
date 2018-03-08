package kyle.leis.es.businessrule.weightrule.da;

import kyle.common.dbaccess.query.GeneralColumns;

public class CalcweightvalueColumns extends GeneralColumns {
	public CalcweightvalueColumns() {
		m_astrColumns = new String[1];
	}
	
	public String getWeightvalue() {
		return this.getField(0);
	}
	
	public void setWeightvalue(String strWeightvalue) {
		this.setField(0, strWeightvalue);
	}
}
