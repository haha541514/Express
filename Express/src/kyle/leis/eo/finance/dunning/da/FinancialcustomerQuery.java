package kyle.leis.eo.finance.dunning.da;


import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class FinancialcustomerQuery extends JGeneralQuery {
	
	public FinancialcustomerQuery(){
	    m_strSelectClause = "SELECT co.co_code, co.co_labelcode,co.co_sname,pk.pk_name,cu.cm_arrearallowsignout,cu.co_code FROM t_co_corporation co,t_co_customer cu,T_BR_CusSignoutByOriginWB cs,T_BR_ProductRule pr,T_DI_ProductKind pk";
	    m_strWhereClause = "co.co_code=cu.co_code and cu.co_code=cs.co_code and cs.br_id=pr.br_id and pr.pk_code=pk.pk_code";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "co.co_labelcode='~~'", "co.co_sname='~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new FinancialcustomerColumns();
	}
	
	public void setCocolablecode(String cocoLablecode) {
		this.setField(0, cocoLablecode);
	}

	public String getCocolablecode() {
		return this.getField(0);
	}

	public void setCocosname(String cocoSname) {
		this.setField(1, cocoSname);
	}

	public String getCocosname() {
		return this.getField(1);
	}

}
