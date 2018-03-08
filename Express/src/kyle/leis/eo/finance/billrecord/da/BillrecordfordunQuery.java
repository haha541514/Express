package kyle.leis.eo.finance.billrecord.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class BillrecordfordunQuery extends JGeneralQuery {
	
	public BillrecordfordunQuery(){
	    m_strSelectClause = "SELECT br.br_id,br.co_code,br.br_occurdate,br.br_remark,decode(br.Bk_Code,'A01', 1, 'A02', 1) * br.br_total as brTotal FROM t_fi_billrecord br,t_co_corporation co";
	    m_strWhereClause = "br.co_code = co.co_code and br.brs_code != 'E'";
	    m_strOrderByClause = "br.br_occurdate";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "br.co_code = ~~", "br.br_occurdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= br.br_occurdate", "(co.co_Carryoversign='~~' OR co.co_Carryoverdate >= br.br_Occurdate)", "(co.co_Carryoversign='~~' AND br.br_Occurdate >= co.co_Carryoverdate)", "br.ck_code = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new BillrecordfordunColumns();
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
