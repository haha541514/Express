package kyle.leis.es.price.freightprice.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class FreightpriceQuery extends HGeneralQuery {
	
	public FreightpriceQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.freightprice.da.FreightpriceColumns(fp.epCode,fp.fpCommissionrate,ep.epCreatedate,ep.epModifydate,ep.epStartdate,ep.epEnddate,ep.epRemark,ep.epWithdrawsign,ee.eeCode,ee.eeName, ee.eeEsname,ps.psCode,ps.psName,epk.epkCode,epk.epkName,cop.opId,cop.opName,mop.opId,mop.opName,pk.pkCode,pk.pkName,pk.pkSname,dt.dtCode,dt.dtHubcode,dt.dtName,ck.ckCode,ck.ckName,pd.pdCode,pd.pdName,pm.pmCode,pm.pmName,ut.utCode,ut.utName,ct.ctCode,ct.ctName,zn.znCode,zn.znName,zn.znKeywords,co.coCode,co.coName,co.coSname,pg.pgCode,pg.pgName,chn.chnCode,chn.chnName,chn.chnSname) FROM TepFreightprice as fp inner join fp.tepExpressprice as ep inner join ep.tdiEnterpriseelement as ee inner join ep.tdiPricestatus as ps inner join ep.tdiExpresspricekind as epk inner join ep.tdiOperatorByEpOpIdCreate as cop inner join ep.tdiOperatorByEpOpIdModify as mop left join fp.tdiProductkind as pk inner join fp.tdiDistrict as dt inner join fp.tdiCurrencykind as ck inner join fp.tdiPricedomain as pd inner join fp.tdiPaymentmode as pm inner join fp.tdiUnittype as ut inner join fp.tdiCargotype as ct inner join fp.tepZone as zn left join fp.tcoCorporation as co left join fp.tdiPricegroup as pg left join fp.tchnChannel as chn";
	    m_strWhereClause = "epk.epkCode = 'A01'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "fp.epCode = '~~'", "ep.epEnddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= ep.epStartdate", "ps.psCode = '~~'", "cop.opId = ~~", "mop.opId = ~~", "pk.pkCode = '~~'", "dt.dtCode = '~~'", "ck.ckCode = '~~'", "pd.pdCode = '~~'", "pm.pmCode = '~~'", "ut.utCode = '~~'", "ct.ctCode = '~~'", "zn.znCode = '~~'", "zn.znKeywords like '~~%'", "co.coCode = '~~'", "pg.pgCode = '~~'", "chn.chnCode = '~~'", "epk.epkCode = '~~'", "ee.eeCode = '~~'", "co.coCode is ~~", "exists (select epe.comp_id.epCode from TepEnterprise as epe where epe.comp_id.epCode = fp.epCode and epe.comp_id.eeCode = '~~')" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
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

	public void setCkcode(String ckCode) {
		this.setField(8, ckCode);
	}

	public String getCkcode() {
		return this.getField(8);
	}

	public void setPdcode(String pdCode) {
		this.setField(9, pdCode);
	}

	public String getPdcode() {
		return this.getField(9);
	}

	public void setPmcode(String pmCode) {
		this.setField(10, pmCode);
	}

	public String getPmcode() {
		return this.getField(10);
	}

	public void setUtcode(String utCode) {
		this.setField(11, utCode);
	}

	public String getUtcode() {
		return this.getField(11);
	}

	public void setCtcode(String ctCode) {
		this.setField(12, ctCode);
	}

	public String getCtcode() {
		return this.getField(12);
	}

	public void setZncode(String znCode) {
		this.setField(13, znCode);
	}

	public String getZncode() {
		return this.getField(13);
	}

	public void setZnkeywords(String znKeywords) {
		this.setField(14, znKeywords);
	}

	public String getZnkeywords() {
		return this.getField(14);
	}

	public void setCocode(String coCode) {
		this.setField(15, coCode);
	}

	public String getCocode() {
		return this.getField(15);
	}

	public void setPgcode(String pgCode) {
		this.setField(16, pgCode);
	}

	public String getPgcode() {
		return this.getField(16);
	}

	public void setChncode(String chnCode) {
		this.setField(17, chnCode);
	}

	public String getChncode() {
		return this.getField(17);
	}

	public void setEpkcode(String epkCode) {
		this.setField(18, epkCode);
	}

	public String getEpkcode() {
		return this.getField(18);
	}

	public void setEecode(String eeCode) {
		this.setField(19, eeCode);
	}

	public String getEecode() {
		return this.getField(19);
	}

	public void setIsnullsign(String IsNullSign) {
		this.setField(20, IsNullSign);
	}

	public String getIsnullsign() {
		return this.getField(20);
	}

	public void setEpecode(String epeCode) {
		this.setField(21, epeCode);
	}

	public String getEpecode() {
		return this.getField(21);
	}

}
