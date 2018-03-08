package kyle.leis.eo.finance.cashrecord.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CashrecordfordunQuery extends JGeneralQuery {
	
	public CashrecordfordunQuery(){
	    m_strSelectClause = "SELECT cr.cr_id,cr.co_code,cr.cr_occurdate,cr.cr_remark,decode(cr.crk_code,'RA', 1, 'PA', -1) * round(cr.cr_total * cr.cr_currencyrate, 2) as crTotal FROM t_fi_cashrecord cr, t_co_corporation co";
	    m_strWhereClause = "cr.co_code = co.co_code and cr.crs_code != 'E'";
	    m_strOrderByClause = "cr.cr_occurdate";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cr.co_code = ~~", "cr.cr_occurdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= cr.cr_occurdate", "(co.co_Carryoversign='~~' OR co.co_Carryoverdate >= cr.cr_occurdate)", "(co.co_Carryoversign='~~' AND cr.cr_occurdate >= co.co_Carryoverdate)", "cr.ck_code = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CashrecordfordunColumns();
	}
	
	public void setCo_code(String co_code) {
		this.setField(0, co_code);
	}

	public String getCo_code() {
		return this.getField(0);
	}

	public void setStartoccurdate(String StartOccurdate) {
		this.setField(1, StartOccurdate);
	}

	public String getStartoccurdate() {
		return this.getField(1);
	}

	public void setEndoccurdate(String EndOccurdate) {
		this.setField(2, EndOccurdate);
	}

	public String getEndoccurdate() {
		return this.getField(2);
	}

	public void setBegincarryoversign(String Begincarryoversign) {
		this.setField(3, Begincarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(3);
	}

	public void setEndcarryoversigin(String Endcarryoversigin) {
		this.setField(4, Endcarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(4);
	}

	public void setCk_code(String ck_code) {
		this.setField(5, ck_code);
	}

	public String getCk_code() {
		return this.getField(5);
	}
}
