package kyle.leis.es.price.zone.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class ZonezfcodeQuery extends JGeneralQuery {
	
	public ZonezfcodeQuery(){
	    m_strSelectClause = "SELECT zn_code FROM  t_ep_zone";
	    m_strWhereClause = "";
	    m_strOrderByClause = "zn_modifydate desc";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "zf_code='~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new ZonezfcodeColumns();
	}
	
	public void setZnzfcode(String znzfCode) {
		this.setField(0, znzfCode);
	}

	public String getZnzfcode() {
		return this.getField(0);
	}

}
