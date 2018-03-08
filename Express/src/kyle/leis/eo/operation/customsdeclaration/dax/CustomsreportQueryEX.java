package kyle.leis.eo.operation.customsdeclaration.dax;

import kyle.leis.eo.operation.customsdeclaration.da.CustomsreportQuery;

public class CustomsreportQueryEX extends CustomsreportQuery {
	
	public CustomsreportQueryEX(){
	    m_strSelectClause = "SELECT FUN_GET_CARGOHSCODE(cw.cw_code) as cargono,FUN_GET_CARGOINFOCNAME(cw.cw_code) as CargoInfo,FUN_GET_CARGOINFO(cw.cw_code) as CargoInfo,FUN_GET_TOTALCARGOVALUE(cw.cw_code) as TotalCargoInfo,cw.cw_grossweight,cw.cw_pieces,hw.hw_consigneecompany,hw.hw_consigneetelephone,cw.cw_customerewbcode,cw.cw_serverewbcode,hw.hw_signoutdate,FUN_GET_TOTALCARGOVALUE(cw.cw_code) as TotalCargoInfo,'',FUN_GET_CARGOHSCODE(cw.cw_code) as cargono FROM t_op_housewaybill hw,t_op_corewaybill cw,t_op_batchwaybill bw";
	    m_strWhereClause = "hw.cw_code = cw.cw_code and cw.bw_code_departure = bw.bw_code(+) and cw.cws_code NOT IN ('EL', 'CEL')";
	}

}
