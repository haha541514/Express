package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class BuildewbcodeseqQuery extends JGeneralQuery {
	
	public BuildewbcodeseqQuery(){
	    m_strSelectClause = "SELECT s_build_ewbcode.nextval as Buildewbcode FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new BuildewbcodeseqColumns();
	}
	

}
