package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class PortQuery extends JGeneralQuery {
	
	public PortQuery(){
	    m_strSelectClause = "SELECT p.pt_code,p.pt_ename,p.pt_cname FROM t_di_port p";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "p.pt_ename = '~~'", "p.pt_cname = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new PortColumns();
	}
	
	public void setPtename(String ptename) {
		this.setField(0, ptename);
	}

	public String getPtename() {
		return this.getField(0);
	}

	public void setPtcname(String ptcname) {
		this.setField(1, ptcname);
	}

	public String getPtcname() {
		return this.getField(1);
	}

}
