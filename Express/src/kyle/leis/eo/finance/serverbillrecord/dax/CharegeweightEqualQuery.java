package kyle.leis.eo.finance.serverbillrecord.dax;

import kyle.leis.eo.finance.serverbillrecord.da.DifferencedetailQuery;

public class CharegeweightEqualQuery extends DifferencedetailQuery {
	public CharegeweightEqualQuery() {
		super();
		m_strWhereClause = "swb.swb_referencecode = cw.cw_code(+) and swb.chn_code = chn.chn_code and swb.SWB_Chargeweight = nvl(cw.cw_serverchargeweight,0)";
	}

}
