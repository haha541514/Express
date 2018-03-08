package kyle.leis.eo.billing.receivable.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SumcorreceivableQuery extends JGeneralQuery {
	
	public SumcorreceivableQuery(){
	    m_strSelectClause = "SELECT nvl(sum(round(rv.rv_actualtotal * rv.rv_currencyrate,2)),0) as sumRvTotal FROM t_bl_receivable rv,t_op_corewaybill cw";
	    m_strWhereClause = "cw.cw_code = rv.cw_code and rv.fs_code != 'E' and cw.cws_code NOT IN ('EL', 'CEL') and rv.bk_code = 'A0101'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "rv.co_code = '~~'", "to_char(rv.rv_occurdate,'yyyy-mm') = '~~'", "to_char(rv.rv_occurdate,'yyyy') = '~~'", "to_char(rv.rv_occurdate,'yyyy-mm-dd') = '~~'", "rv.rv_occurdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= rv.rv_occurdate" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SumcorreceivableColumns();
	}
	
	public void setCocode(String coCode) {
		this.setField(0, coCode);
	}

	public String getCocode() {
		return this.getField(0);
	}

	public void setRvoccurdate(String rvoccurdate) {
		this.setField(1, rvoccurdate);
	}

	public String getRvoccurdate() {
		return this.getField(1);
	}

	public void setYrvoccurdate(String yrvoccurdate) {
		this.setField(2, yrvoccurdate);
	}

	public String getYrvoccurdate() {
		return this.getField(2);
	}

	public void setDrvoccurdate(String drvoccurdate) {
		this.setField(3, drvoccurdate);
	}

	public String getDrvoccurdate() {
		return this.getField(3);
	}

	public void setStartrvoccurdate(String StartRvOccurdate) {
		this.setField(4, StartRvOccurdate);
	}

	public String getStartrvoccurdate() {
		return this.getField(4);
	}

	public void setEndrvoccurdate(String EndRvOccurdate) {
		this.setField(5, EndRvOccurdate);
	}

	public String getEndrvoccurdate() {
		return this.getField(5);
	}

}
