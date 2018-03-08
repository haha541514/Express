package kyle.leis.eo.customerservice.issue.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class IssuecountQuery extends JGeneralQuery {
	
	public IssuecountQuery(){
	    m_strSelectClause = "SELECT count(1) as pieces FROM t_op_corewaybill cw, t_op_housewaybill hw";
	    m_strWhereClause = "cw.cw_code = hw.cw_code and cw.cws_code not in ('EL', 'CEL')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.co_code_customer = ~~", "hw.hw_signindate >= to_date('~~', 'yyyy-mm-dd,hh24:mi:ss')", "to_date('~~', 'yyyy-mm-dd,hh24:mi:ss') >= hw.hw_signindate", "exists (select isu.isu_id from t_cs_issue isu where isu.cw_code = cw.cw_code and isu.isus_code in (~~))", "exists (select isu.isu_id from t_cs_issue isu where isu.cw_code = cw.cw_code and isu.isus_code in ('AS', 'DL', 'CF') and to_char(isu.isut_code) in (~~))" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new IssuecountColumns();
	}
	
	public void setCwcocode(String cwCoCode) {
		this.setField(0, cwCoCode);
	}

	public String getCwcocode() {
		return this.getField(0);
	}

	public void setStarthwsignindate(String startHwSignindate) {
		this.setField(1, startHwSignindate);
	}

	public String getStarthwsignindate() {
		return this.getField(1);
	}

	public void setEndhwsignindate(String endHwSignindate) {
		this.setField(2, endHwSignindate);
	}

	public String getEndhwsignindate() {
		return this.getField(2);
	}

	public void setIsuscode(String isusCode) {
		this.setField(3, isusCode);
	}

	public String getIsuscode() {
		return this.getField(3);
	}

	public void setIsutcode(String isutCode) {
		this.setField(4, isutCode);
	}

	public String getIsutcode() {
		return this.getField(4);
	}

}
