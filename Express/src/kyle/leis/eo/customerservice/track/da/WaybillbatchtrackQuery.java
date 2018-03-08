package kyle.leis.eo.customerservice.track.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class WaybillbatchtrackQuery extends HGeneralQuery {
	
	public WaybillbatchtrackQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.eo.customerservice.track.da.WaybillbatchtrackColumns(wbbt.cwCode,wbbt.wbbtLatesttrackdesc,wbbt.wbbtLatestcslogdesc,wbbt.wbbtCslogcreatedate,wbbt.wbbtSignforuser,wbbt.wbbtSignfordate,wbts.wbtsCode,wbts.wbtsName,cw.cwChargeweight,cw.cwServerchargeweight,cw.cwCustomerewbcode,cw.cwServerewbcode,cw.cwEwbcode,wpa.wpaCode,wpa.wpaName,wbbt.wbbtLatesttrackdate) FROM TcsWaybillbatchtrack as wbbt inner join wbbt.topCorewaybill as cw left join wbbt.tdiWaybilltrackstatus as wbts left join wbbt.tfsWebpageaccess as wpa";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "wbbt.cwCode = ~~", "wbbt.wbbtLatesttrackdesc like '%~~%'", "wbbt.wbbtLatestcslogdesc like '%~~%'", "nvl(wbbt.wbbtSignforuser,'null') != '~~'", "wbbt.wbbtSignfordate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= wbbt.wbbtSignfordate" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setWbbtlatesttrackdesc(String wbbtLatesttrackdesc) {
		this.setField(1, wbbtLatesttrackdesc);
	}

	public String getWbbtlatesttrackdesc() {
		return this.getField(1);
	}

	public void setWbbtlatestcslogdesc(String wbbtLatestcslogdesc) {
		this.setField(2, wbbtLatestcslogdesc);
	}

	public String getWbbtlatestcslogdesc() {
		return this.getField(2);
	}

	public void setNotsignforuser(String NotSignforuser) {
		this.setField(3, NotSignforuser);
	}

	public String getNotsignforuser() {
		return this.getField(3);
	}

	public void setStartsignfordate(String StartSignfordate) {
		this.setField(4, StartSignfordate);
	}

	public String getStartsignfordate() {
		return this.getField(4);
	}

	public void setEndsignfordate(String EndSignfordate) {
		this.setField(5, EndSignfordate);
	}

	public String getEndsignfordate() {
		return this.getField(5);
	}

}
