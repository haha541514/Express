package kyle.leis.es.businessrule.weightrule.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CalcweightvalueQuery extends JGeneralQuery {
	
	public IColumns createColumns() {
		return new CalcweightvalueColumns();
	}

}
