package kyle.leis.eo.billing.receivable.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SumcorundocheckoutQuery extends JGeneralQuery {
	
	public SumcorundocheckoutQuery(){
	    m_strSelectClause = "SELECT nvl(sum(round(rv.rv_actualtotal * rv.rv_currencyrate,2)),0) as sumRvTotal FROM t_bl_receivable rv,t_op_corewaybill cw,t_op_housewaybill hw";
	    m_strWhereClause = "cw.cw_code = rv.cw_code and cw.cw_code = hw.cw_code and rv.fs_code != 'E' and cw.cws_code NOT IN ('EL', 'CEL') and cw.bw_code_departure is null";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "rv.co_code = '~~'", "hw.hw_signindate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.hw_signindate", "cw.ihs_code in (~~)" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SumcorundocheckoutColumns();
	}
	
	public void setCocode(String coCode) {
		this.setField(0, coCode);
	}

	public String getCocode() {
		return this.getField(0);
	}

	public void setStartsignindate(String StartSignindate) {
		this.setField(1, StartSignindate);
	}

	public String getStartsignindate() {
		return this.getField(1);
	}

	public void setEndsignindate(String EndSignindate) {
		this.setField(2, EndSignindate);
	}

	public String getEndsignindate() {
		return this.getField(2);
	}

	public void setInihscode(String Inihscode) {
		this.setField(3, Inihscode);
	}

	public String getInihscode() {
		return this.getField(3);
	}

}
