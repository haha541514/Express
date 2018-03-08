package kyle.leis.eo.finance.dunning.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class FinancialdetailsQuery extends JGeneralQuery {
	
	public FinancialdetailsQuery(){
	    m_strSelectClause = "SELECT cw.cw_customerewbcode ,cw.cw_serverewbcode,cw.cw_ewbcode,cw.cw_pieces,cw.cw_grossweight,hw.hw_signoutdate,op.op_name,co.CO_LABELCODE, co.CO_SNAME,cp.co_name,chn.chn_name,bw.add_date,(select sum(ra.rv_actualtotal*ra.rv_currencyrate) from t_bl_receivable ra where ra.cw_code=cw.cw_code and ra.fs_code!='E' and ra.fs_code!='D') as rvTotal,co.co_code,cp.CO_LABELCODE,cp.co_sname,chn.chn_sname FROM t_op_corewaybill cw,t_co_corporation co,t_co_corporation cp,T_CHN_Channel chn,t_op_housewaybill hw,t_op_batchwaybill bw,t_op_batchwaybillvalue bwv,t_di_operator op";
	    m_strWhereClause = "co.co_code=cw.co_code_supplier and cp.co_code=cw.co_code_customer and cw.chn_code_supplier=chn.chn_code  and cw.cw_code=bwv.cw_code and bwv.bw_code=bw.bw_code and hw.hw_op_id_signout=op.op_id and cws_code!='EL' and hw.hw_arrearsignout='Y'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cp.co_labelcode='~~'", "cp.co_sname='~~'", "hw.hw_signoutdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.hw_signoutdate" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new FinancialdetailsColumns();
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

	public void setStartsignoutdate(String StartSignoutdate) {
		this.setField(2, StartSignoutdate);
	}

	public String getStartsignoutdate() {
		return this.getField(2);
	}

	public void setEndsignoutdate(String EndSignoutdate) {
		this.setField(3, EndSignoutdate);
	}

	public String getEndsignoutdate() {
		return this.getField(3);
	}

}
