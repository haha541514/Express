package kyle.leis.es.company.customer.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SimplecustomerQuery extends JGeneralQuery {
	
	public SimplecustomerQuery(){
	    m_strSelectClause = "SELECT cm.co_code,cm.co_code_parent,cm.cm_structruecode,co.cs_code,co.co_name,co.co_sname,co.co_address,co.co_remark,cm.ct_code FROM t_co_customer cm,t_co_corporation co";
	    m_strWhereClause = "cm.co_code = co.co_code";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cm.co_code_parent = '~~'", "cm.co_code = '~~'", "cm.ct_code = '~~'", "co.co_name like '~~%'", "co.co_sname like '~~%'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SimplecustomerColumns();
	}
	
	public void setCocodeparent(String cocodeparent) {
		this.setField(0, cocodeparent);
	}

	public String getCocodeparent() {
		return this.getField(0);
	}

	public void setCocode(String cocode) {
		this.setField(1, cocode);
	}

	public String getCocode() {
		return this.getField(1);
	}

	public void setCtcode(String ctcode) {
		this.setField(2, ctcode);
	}

	public String getCtcode() {
		return this.getField(2);
	}

	public void setLikeconame(String likeconame) {
		this.setField(3, likeconame);
	}

	public String getLikeconame() {
		return this.getField(3);
	}

	public void setLikecosname(String likecosname) {
		this.setField(4, likecosname);
	}

	public String getLikecosname() {
		return this.getField(4);
	}

}
