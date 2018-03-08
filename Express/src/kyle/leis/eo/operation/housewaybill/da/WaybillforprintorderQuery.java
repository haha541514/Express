package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class WaybillforprintorderQuery extends JGeneralQuery {
	
	public WaybillforprintorderQuery(){
	    m_strSelectClause = "SELECT cw.cw_code,hw.hw_shippername,co.co_labelcode,hw.hw_shippercompany,hw.hw_shipperaddress1,hw.hw_shipperaddress2,hw.hw_shipperaddress3,hw.hw_consigneename,hw.hw_consigneecompany,hw.hw_consigneeaddress1,hw.hw_consigneeaddress2,hw.hw_consigneeaddress3,cdt.dt_name,cdt.dt_ename,hw.hw_consigneetelephone,cw.cw_customerewbcode,pk.pk_sename FROM t_op_corewaybill cw,t_op_housewaybill hw,t_co_corporation co,t_di_district dt,t_di_district cdt,t_di_productkind pk";
	    m_strWhereClause = "cw.cw_code = hw.cw_code and cw.co_code_customer = co.co_code and cw.dt_code_origin = dt.dt_code and dt.dt_countcode = cdt.dt_code and cw.pk_code = pk.pk_code and cw.cws_code NOT IN ('EL', 'CEL', 'CTS', 'CHP')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cw_code in (~~)" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new WaybillforprintorderColumns();
	}
	
	public void setIncwcode(String Incwcode) {
		this.setField(0, Incwcode);
	}

	public String getIncwcode() {
		return this.getField(0);
	}

}
