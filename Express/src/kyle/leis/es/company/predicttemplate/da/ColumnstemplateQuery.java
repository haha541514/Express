package kyle.leis.es.company.predicttemplate.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class ColumnstemplateQuery extends JGeneralQuery {
	
	public ColumnstemplateQuery(){
	    m_strSelectClause = "SELECT p.potv_id,p.pot_id,p.potv_columnname,c.cmt_name,t.tc_id,t.tc_columnname FROM t_co_predictordertemplatevalue p ,t_di_templatecolumn t,t_di_columnmappingtype c ";
	    m_strWhereClause = "p.tc_standardcolumn = t.tc_id and c.cmt_code = p.cmt_code";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "p.pot_id = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new ColumnstemplateColumns();
	}
	
	public void setPotid(String potId) {
		this.setField(0, potId);
	}

	public String getPotid() {
		return this.getField(0);
	}

}
