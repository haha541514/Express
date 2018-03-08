package kyle.leis.fs.dictionary.waybilltrackstatus.dax;

import kyle.leis.fs.cachecontainer.da.WaybilltrackstatusQuery;

public class WaybilltrackstatusQueryEX extends WaybilltrackstatusQuery {
	public WaybilltrackstatusQueryEX(String wbtsCode){
		m_strWhereClause = "wbts.wbtsCode = '" + wbtsCode + "' and ss.ssCode = 'ON' ";
	}
}
