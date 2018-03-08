package kyle.fetcher.track.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class WaybillforfetcherQuery extends JGeneralQuery {
	
	public WaybillforfetcherQuery(){
	    m_strSelectClause = "SELECT cw.cw_code,cw.cw_serverewbcode,cw.cw_channelewbcode,cw.chn_code_supplier,wbbt.wbts_code,wbbt.wpa_code,bw.add_date,cw.co_code_supplier,ssg.ssg_code,cdt.dt_ename FROM T_OP_COREWAYBILL cw,T_OP_BATCHWAYBILL bw,T_CS_WAYBILLBATCHTRACK wbbt, t_chn_channel chn, T_DI_SERVERSTRUCTUREGROUP ssg, t_di_district dt, t_di_district cdt";
	    m_strWhereClause = "cw.bw_code_departure = bw.bw_code and cw.chn_code_supplier = chn.chn_code and chn.ssg_code = ssg.ssg_code and cw.dt_code_destination = dt.dt_code and dt.dt_countcode = cdt.dt_code and cw.cw_code = wbbt.cw_code(+) and cw.cws_code != 'EL' and wbbt.wbbt_signfordate is null and (wbbt.wbts_code is null or wbbt.wbts_code not in ('RE','CC', 'SF', 'OK'))";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.co_code_supplier in (~~)", "bw.add_date >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.add_date", "cw.cw_code = ~~", "cw.cw_serverewbcode = '~~'", "cw.chn_code_supplier in (~~)", "(wbbt.wpa_code is null or wbbt.wpa_code = '~~')", "ssg.ssg_code in (~~)" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new WaybillforfetcherColumns();
	}
	
	public void setCocodesupplier(String cocodesupplier) {
		this.setField(0, cocodesupplier);
	}

	public String getCocodesupplier() {
		return this.getField(0);
	}

	public void setStartarrivaldate(String StartArrivalDate) {
		this.setField(1, StartArrivalDate);
	}

	public String getStartarrivaldate() {
		return this.getField(1);
	}

	public void setEndarrivaldate(String EndArrivalDate) {
		this.setField(2, EndArrivalDate);
	}

	public String getEndarrivaldate() {
		return this.getField(2);
	}

	public void setCwcode(String cwcode) {
		this.setField(3, cwcode);
	}

	public String getCwcode() {
		return this.getField(3);
	}

	public void setCwserverewbcode(String cwserverewbcode) {
		this.setField(4, cwserverewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(4);
	}

	public void setChncodesupplier(String chncodesupplier) {
		this.setField(5, chncodesupplier);
	}

	public String getChncodesupplier() {
		return this.getField(5);
	}

	public void setWpacode(String wpacode) {
		this.setField(6, wpacode);
	}

	public String getWpacode() {
		return this.getField(6);
	}

	public void setSsgcode(String ssgcode) {
		this.setField(7, ssgcode);
	}

	public String getSsgcode() {
		return this.getField(7);
	}

}
