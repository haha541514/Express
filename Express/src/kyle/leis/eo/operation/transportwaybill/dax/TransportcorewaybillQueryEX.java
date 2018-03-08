package kyle.leis.eo.operation.transportwaybill.dax;

import kyle.leis.eo.operation.transportwaybill.da.TransportcorewaybillQuery;

public class TransportcorewaybillQueryEX extends TransportcorewaybillQuery {
	public TransportcorewaybillQueryEX(){
		m_strWhereClause = "twbv.cw_code = cw.cw_code and cw.bw_code_departure = bw.bw_code and twbv.cw_code is not null and twbv.twbv_baglabelcode is null and cw.cws_code NOT IN ('EL', 'CEL')";	
	}
}
