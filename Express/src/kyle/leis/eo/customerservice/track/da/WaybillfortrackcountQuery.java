package kyle.leis.eo.customerservice.track.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class WaybillfortrackcountQuery extends JGeneralQuery {
	
	public WaybillfortrackcountQuery(){
	    m_strSelectClause = "SELECT count(1) as pieces FROM t_op_corewaybill cw, t_cs_waybillbatchtrack wbbt";
	    m_strWhereClause = "cw.cw_code = wbbt.cw_code";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.co_code_customer = '~~'", "cw.cw_createdate >= to_date('~~', 'yyyy-mm-dd,hh24:mi:ss')", "to_date('~~', 'yyyy-mm-dd,hh24:mi:ss') >= cw.cw_createdate", "wbbt.wbts_code in (~~)", "(wbbt.wbts_code is null or wbbt.wbts_code not in (~~))" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new WaybillfortrackcountColumns();
	}
	
	public void setCwcocode(String cwCoCode) {
		this.setField(0, cwCoCode);
	}

	public String getCwcocode() {
		return this.getField(0);
	}

	public void setStartcwcreatedate(String startCwcreatedate) {
		this.setField(1, startCwcreatedate);
	}

	public String getStartcwcreatedate() {
		return this.getField(1);
	}

	public void setEndcwcreatedate(String endCwcreatedate) {
		this.setField(2, endCwcreatedate);
	}

	public String getEndcwcreatedate() {
		return this.getField(2);
	}

	public void setWbtscode(String wbtsCode) {
		this.setField(3, wbtsCode);
	}

	public String getWbtscode() {
		return this.getField(3);
	}

	public void setNotwbtscode(String notWbtsCode) {
		this.setField(4, notWbtsCode);
	}

	public String getNotwbtscode() {
		return this.getField(4);
	}

}
