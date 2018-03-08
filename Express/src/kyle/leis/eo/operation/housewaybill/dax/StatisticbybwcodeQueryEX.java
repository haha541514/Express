package kyle.leis.eo.operation.housewaybill.dax;

import kyle.common.dbaccess.query.IColumns;
import kyle.leis.eo.operation.housewaybill.da.StatisticbybwcodeColumns;
import kyle.leis.eo.operation.housewaybill.da.StatisticbybwcodeQuery;

public class StatisticbybwcodeQueryEX extends StatisticbybwcodeQuery {
	public StatisticbybwcodeQueryEX() {
		this.m_strSelectClause = "SELECT count(distinct cw.cw_code) as pieces FROM t_op_corewaybill cw, t_op_housewaybill hw";
		this.m_strWhereClause = "cw.cw_code = hw.cw_code(+)";
		this.m_strOrderByClause = "";
		this.m_strGroupByClause = "";
		this.m_astrConditionWords = new String[] {
				"hw.hw_signindate >= to_date('~~', 'yyyy-mm-dd,hh24:mi:ss')",
				"to_date('~~','yyyy-mm-dd,hh24:mi:ss') >= hw.hw_signindate",
				"cw.BW_CODE_ARRIVAL = '~~'", "cw.CO_CODE_CUSTOMER = '~~'" };
		this.m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };
	}

	public IColumns createColumns() {
		return new StatisticbybwcodeColumns();
	}

	public void setSigninstartdate(String signinStartDate) {
		setField(0, signinStartDate);
	}

	public String getSigninstartdate() {
		return getField(0);
	}

	public void setSigninenddate(String signinEndDate) {
		setField(1, signinEndDate);
	}

	public String getSigninenddate() {
		return getField(1);
	}

	public void setBw_code_arrival(String BW_CODE_ARRIVAL) {
		setField(2, BW_CODE_ARRIVAL);
	}

	public String getBw_code_arrival() {
		return getField(2);
	}

	public void setCo_code_customer(String CO_CODE_CUSTOMER) {
		setField(3, CO_CODE_CUSTOMER);
	}

	public String getCo_code_customer() {
		return getField(3);
	}
}
