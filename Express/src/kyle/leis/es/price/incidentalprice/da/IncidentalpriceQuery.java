package kyle.leis.es.price.incidentalprice.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class IncidentalpriceQuery extends HGeneralQuery {
	
	public IncidentalpriceQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.incidentalprice.da.IncidentalpriceColumns(ep.epCode,ep.epCreatedate,ep.epModifydate,ep.epStartdate,ep.epEnddate,ep.epRemark,ep.epWithdrawsign,ps.psCode,ps.psName,epk.epkCode,epk.epkName,cop.opId,cop.opName,mop.opId,mop.opName,ee.eeCode,ee.eeSname,ee.eeEsname,co.coCode,co.coSname,co.coLabelcode,pg.pgCode,pg.pgName,pk.pkCode,pk.pkName,pk.pkSename,chn.chnCode,chn.chnSname,chn.chnSename,dt.dtCode,dt.dtHubcode,pd.pdCode,pd.pdName) FROM TepIncidentalprice as ip inner join ip.tepExpressprice as ep inner join ep.tdiPricestatus as ps inner join ep.tdiExpresspricekind as epk inner join ep.tdiOperatorByEpOpIdCreate as cop inner join ep.tdiOperatorByEpOpIdModify as mop inner join ep.tdiEnterpriseelement as ee left join ip.tcoCorporation as co left join ip.tdiPricegroup as pg left join ip.tdiProductkind as pk left join ip.tchnChannel as chn inner join ip.tdiDistrict as dt inner join ip.tdiPricedomain as pd";
	    m_strWhereClause = "epk.epkCode = 'A02'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "ep.epCode = ~~", "ep.epEnddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= ep.epStartdate", "ps.psCode = '~~'", "cop.opId = ~~", "mop.opId = ~~", "pk.pkCode = '~~'", "dt.dtCode = '~~'", "pd.pdCode = '~~'", "co.coCode = '~~'", "pg.pgCode = '~~'", "chn.chnCode = '~~'", "epk.epkCode = '~~'", "ee.eeCode = '~~'", "exists (select epe.comp_id.epCode from TepEnterprise as epe where epe.comp_id.epCode = ip.epCode and epe.comp_id.eeCode = '~~')" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setEpcode(String epCode) {
		this.setField(0, epCode);
	}

	public String getEpcode() {
		return this.getField(0);
	}

	public void setEpstartdate(String epStartdate) {
		this.setField(1, epStartdate);
	}

	public String getEpstartdate() {
		return this.getField(1);
	}

	public void setEpstartdate2(String epStartdate2) {
		this.setField(2, epStartdate2);
	}

	public String getEpstartdate2() {
		return this.getField(2);
	}

	public void setPscode(String psCode) {
		this.setField(3, psCode);
	}

	public String getPscode() {
		return this.getField(3);
	}

	public void setCopid(String copId) {
		this.setField(4, copId);
	}

	public String getCopid() {
		return this.getField(4);
	}

	public void setMopid(String mopId) {
		this.setField(5, mopId);
	}

	public String getMopid() {
		return this.getField(5);
	}

	public void setPkcode(String pkCode) {
		this.setField(6, pkCode);
	}

	public String getPkcode() {
		return this.getField(6);
	}

	public void setDtcode(String dtCode) {
		this.setField(7, dtCode);
	}

	public String getDtcode() {
		return this.getField(7);
	}

	public void setPdcode(String pdCode) {
		this.setField(8, pdCode);
	}

	public String getPdcode() {
		return this.getField(8);
	}

	public void setCocode(String coCode) {
		this.setField(9, coCode);
	}

	public String getCocode() {
		return this.getField(9);
	}

	public void setPgcode(String pgCode) {
		this.setField(10, pgCode);
	}

	public String getPgcode() {
		return this.getField(10);
	}

	public void setChncode(String chnCode) {
		this.setField(11, chnCode);
	}

	public String getChncode() {
		return this.getField(11);
	}

	public void setEpkcode(String epkCode) {
		this.setField(12, epkCode);
	}

	public String getEpkcode() {
		return this.getField(12);
	}

	public void setEecode(String eeCode) {
		this.setField(13, eeCode);
	}

	public String getEecode() {
		return this.getField(13);
	}

	public void setEpecode(String epeCode) {
		this.setField(14, epeCode);
	}

	public String getEpecode() {
		return this.getField(14);
	}

}
