package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SumweightcheckweightQuery extends JGeneralQuery {
	
	public SumweightcheckweightQuery(){
	    m_strSelectClause = "SELECT nvl(sum(cw.cw_chargeweight),0) as Sumchargeweight,nvl(sum(cw.cw_customerchargeweight),0) as Sumcmchargeweight,count(1) as billcounts FROM t_op_corewaybill cw";
	    m_strWhereClause = "cw.cws_code NOT IN ('EL', 'CEL', 'CTS', 'CHP')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "exists (select bw.bw_code from t_op_batchwaybill bw where bw.adt_code = 'W' and bw.bw_code = cw.bw_code_weightcheck and bw.bw_code = '~~')", "cw.bw_code_arrival = '~~'", "exists (select hw.cw_code from t_op_housewaybill hw where hw.cw_code = cw.cw_code and hw.hw_op_id_weightcheck is ~~)" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SumweightcheckweightColumns();
	}
	
	public void setBwcodeweightcheck(String bwcodeweightcheck) {
		this.setField(0, bwcodeweightcheck);
	}

	public String getBwcodeweightcheck() {
		return this.getField(0);
	}

	public void setBwcodearrival(String bwcodearrival) {
		this.setField(1, bwcodearrival);
	}

	public String getBwcodearrival() {
		return this.getField(1);
	}

	public void setIsnullsign(String Isnullsign) {
		this.setField(2, Isnullsign);
	}

	public String getIsnullsign() {
		return this.getField(2);
	}

}
