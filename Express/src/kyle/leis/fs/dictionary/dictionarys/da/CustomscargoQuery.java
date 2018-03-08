package kyle.leis.fs.dictionary.dictionarys.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CustomscargoQuery extends JGeneralQuery {
	
	public CustomscargoQuery(){
	    m_strSelectClause = "SELECT cc.cc_ename,cc.cc_name,cc.cc_hscode, cc.cc_unittype FROM t_di_customscargo cc";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cc.cc_ename = '~~'", "cc.cc_name = '~~'", "cc.cc_hscode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CustomscargoColumns();
	}
	
	public void setCcename(String ccEname) {
		this.setField(0, ccEname);
	}

	public String getCcename() {
		return this.getField(0);
	}

	public void setCcname(String ccName) {
		this.setField(1, ccName);
	}

	public String getCcname() {
		return this.getField(1);
	}

	public void setCchscode(String ccHscode) {
		this.setField(2, ccHscode);
	}

	public String getCchscode() {
		return this.getField(2);
	}

}
