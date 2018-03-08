package kyle.leis.es.company.customer.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CustomerforbillQuery extends JGeneralQuery {
	
	public CustomerforbillQuery(){
	    m_strSelectClause = "SELECT cm.co_code,cm.cm_creditlimit,cm.cm_op_id_dun,op.op_name,co.co_sname,co.co_labelcode,co.co_sename,(select sum(round(rv.rv_currencyrate*rv.rv_actualtotal,2)) from t_bl_receivable rv where rv.co_code = co.co_code and rv.fs_code = 'C') as SumConfirmFee,(select sum(round(rv.rv_currencyrate*rv.rv_actualtotal,2)) from t_bl_receivable rv where rv.co_code = co.co_code and rv.fs_code = 'D') as SumDraftFee,cmt.ct_code,cmt.ct_name,ee.ee_code,ee.ee_sname FROM t_co_corporation co,t_co_customer cm,t_di_operator op,T_DI_CustomerType cmt, T_DI_ENTERPRISEELEMENT ee";
	    m_strWhereClause = "cm.ct_code=cmt.ct_code and co.co_code = cm.co_code and cm.cm_op_id_dun = op.op_id(+) and co.ee_code = ee.ee_code and co.cst_code = 'C' ";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cm.co_code = '~~'", "exists (select * from t_bl_receivable rv where rv.co_code = co.co_code and rv.fs_code = 'C' and rv.rv_occurdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss') and to_date('~~','yyyy-mm-dd hh24:mi:ss') >= rv.rv_occurdate)", "cm.cm_op_id_dun = ~~", "cmt.ct_code='~~'", "ee.EE_Structurecode like '~~%'" };
	    m_aiConditionVariableCount = new int[] { 1, 2, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CustomerforbillColumns();
	}
	
	public void setCocode(String coCode) {
		this.setField(0, coCode);
	}

	public String getCocode() {
		return this.getField(0);
	}

	public void setStartdate(String startdate) {
		this.setField(1, startdate);
	}

	public String getStartdate() {
		return this.getField(1);
	}
	public void setEnddate(String enddate) {
		this.setField(2, enddate);
	}

	public String getEnddate() {
		return this.getField(2);
	}

	public void setOpiddunner(String opidDunner) {
		this.setField(3, opidDunner);
	}

	public String getOpiddunner() {
		return this.getField(3);
	}

	public void setCtcode(String ctCode) {
		this.setField(4, ctCode);
	}

	public String getCtcode() {
		return this.getField(4);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(5, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(5);
	}

}
