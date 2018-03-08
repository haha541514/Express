package kyle.leis.es.systemproperty.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class FilemanagerQuery extends JGeneralQuery {
	
	public FilemanagerQuery(){
	    m_strSelectClause = "SELECT fl.fl_name,fl.fl_path,fl.fl_createdate FROM T_ES_FILE fl";
	    m_strWhereClause = "";
	    m_strOrderByClause = "fl.fl_createdate desc";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "fl.fl_name='~~'", "fl.fl_createdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= fl.fl_createdate" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new FilemanagerColumns();
	}
	
	public void setName(String name) {
		this.setField(0, name);
	}

	public String getName() {
		return this.getField(0);
	}

	public void setCreatedate(String createdate) {
		this.setField(1, createdate);
	}

	public String getCreatedate() {
		return this.getField(1);
	}

	public void setEndcreatedate(String endcreatedate) {
		this.setField(2, endcreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(2);
	}

}
