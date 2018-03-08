package kyle.leis.eo.operation.corewaybill.dax;

import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillQuery;

public class SimplecorewaybillQueryEx extends SimplecorewaybillQuery {
	public SimplecorewaybillQueryEx(String bookId){
		m_strSelectClause = "SELECT cw.CW_CODE,cw.CWS_CODE,cw.CT_CODE,cw.PM_CODE,cw.PK_CODE,cw.IHS_CODE,cw.CO_CODE_CUSTOMER,cw.CO_CODE_SUPPLIER,cw.Chn_Code_Supplier,cw.cw_createdate, cw.Cw_Serverewbcode FROM t_op_corewaybill cw, t_op_housewaybill hw ";
		m_strWhereClause = " cw.cw_code = hw.cw_code and hw.hw_bookingid = '" + bookId + "'";
	}
}
