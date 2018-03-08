package kyle.leis.eo.finance.billrecord.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class AllbillrecordidQuery extends JGeneralQuery {
	
	public AllbillrecordidQuery(){
	    m_strSelectClause = "SELECT FUN_GET_CUSTOMERBRID(cw.cw_code) as customerBrid, FUN_GET_PAYBRID(cw.cw_code) as payBrid ,sbr.sbr_labelcode FROM T_OP_COREWAYBILL cw ,T_FI_SERVERWAYBILL swb,t_fi_serverpayable spy,t_fi_serverbillrecord sbr";
	    m_strWhereClause = "swb.swb_referencecode(+) = cw.cw_code and spy.swb_code(+) = swb.swb_code and spy.sbr_id = sbr.sbr_id(+)";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cw_code = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new AllbillrecordidColumns();
	}
	
	public void setCwcwcode(String cwcwcode) {
		this.setField(0, cwcwcode);
	}

	public String getCwcwcode() {
		return this.getField(0);
	}

}
