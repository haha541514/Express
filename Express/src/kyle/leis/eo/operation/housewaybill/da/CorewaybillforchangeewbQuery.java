package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CorewaybillforchangeewbQuery extends JGeneralQuery {
	
	public CorewaybillforchangeewbQuery(){
	    m_strSelectClause = "SELECT cw.cw_code FROM t_op_housewaybill hw, t_op_corewaybill cw, t_chn_channel chn, t_di_waybillcodekind wbck";
	    m_strWhereClause = "hw.cw_code = cw.cw_code and cw.chn_code_supplier = chn.chn_code and hw.hw_serverewbchangedsign = 'N' and hw.hw_recorddate > sysdate - 1 and chn.chn_mainbillcodekind = wbck.bck_code and wbck.bck_groupcode = 'DHLMC' and wbck.bck_fromwebservicesign = 'Y' and cw.cws_code NOT IN ('EL', 'CEL')";
	    m_strOrderByClause = "hw.hw_recorddate";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cw_code = ~~" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CorewaybillforchangeewbColumns();
	}
	
	public void setCw_code(String cw_code) {
		this.setField(0, cw_code);
	}

	public String getCw_code() {
		return this.getField(0);
	}

}
