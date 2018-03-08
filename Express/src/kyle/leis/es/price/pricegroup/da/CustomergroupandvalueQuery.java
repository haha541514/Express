package kyle.leis.es.price.pricegroup.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CustomergroupandvalueQuery extends HGeneralQuery {
	
	public CustomergroupandvalueQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.pricegroup.da.CustomergroupandvalueColumns(cpg.epCode,ep.epCreatedate,ep.epModifydate,ep.epStartdate,ep.epEnddate,ep.epRemark,ep.epWithdrawsign,ps.psCode,ps.psName,epk.epkCode,epk.epkName,cop.opId,cop.opName,mop.opId,mop.opName,ee.eeCode,ee.eeSname,ee.eeEsname,co.coCode,co.coSname,co.coSename,co.coLabelcode,cst.cstCode,cst.cstName,pgk.pgkCode,pgk.pgkName,pk.pkCode,pk.pkSname,pk.pkSename,pg.pgCode,pg.pgName,fk.fkCode,fk.fkName) FROM TepCustomerpricegroupvalue as cpgv inner join cpgv.tdiPricegroup as pg inner join cpgv.tdiFeekind as fk inner join cpgv.tepCustomerpricegroup as cpg inner join cpg.tepExpressprice as ep inner join ep.tdiPricestatus as ps inner join ep.tdiExpresspricekind as epk inner join ep.tdiOperatorByEpOpIdCreate as cop inner join ep.tdiOperatorByEpOpIdModify as mop inner join ep.tdiEnterpriseelement as ee inner join cpg.tcoCorporation as co left join cpg.tdiPricegroupkind as pgk inner join cpg.tdiProductkind as pk inner join co.tdiCustomersuppliertype as cst";
	    m_strWhereClause = "epk.epkCode = 'A05'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "ep.epCode = ~~", "ep.ep.epEnddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= epStartdate", "ep.epCreatedate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= epCreatedate", "ep.epModifydate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= ep.epModifydate", "ps.psCode = '~~'", "cop.opId = ~~", "mop.opId = ~~", "pk.pkCode = '~~'", "pgk.pgkCode = '~~'", "co.coCode = '~~'", "epk.epkCode = '~~'", "ee.eeCode = '~~'", "pg.pgCode = '~~'", "fk.fkCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
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

	public void setEpcreatedatestart(String epCreateDateStart) {
		this.setField(3, epCreateDateStart);
	}

	public String getEpcreatedatestart() {
		return this.getField(3);
	}

	public void setEpcreatedateend(String epCreateDateEnd) {
		this.setField(4, epCreateDateEnd);
	}

	public String getEpcreatedateend() {
		return this.getField(4);
	}

	public void setEpmodifydatestart(String epModifyDateStart) {
		this.setField(5, epModifyDateStart);
	}

	public String getEpmodifydatestart() {
		return this.getField(5);
	}

	public void setEpmodifydateend(String epModifyDateEnd) {
		this.setField(6, epModifyDateEnd);
	}

	public String getEpmodifydateend() {
		return this.getField(6);
	}

	public void setPscode(String psCode) {
		this.setField(7, psCode);
	}

	public String getPscode() {
		return this.getField(7);
	}

	public void setCopid(String copId) {
		this.setField(8, copId);
	}

	public String getCopid() {
		return this.getField(8);
	}

	public void setMopid(String mopId) {
		this.setField(9, mopId);
	}

	public String getMopid() {
		return this.getField(9);
	}

	public void setPkcode(String pkCode) {
		this.setField(10, pkCode);
	}

	public String getPkcode() {
		return this.getField(10);
	}

	public void setPgkcode(String pgkCode) {
		this.setField(11, pgkCode);
	}

	public String getPgkcode() {
		return this.getField(11);
	}

	public void setCocode(String coCode) {
		this.setField(12, coCode);
	}

	public String getCocode() {
		return this.getField(12);
	}

	public void setEpkcode(String epkCode) {
		this.setField(13, epkCode);
	}

	public String getEpkcode() {
		return this.getField(13);
	}

	public void setEecode(String eeCode) {
		this.setField(14, eeCode);
	}

	public String getEecode() {
		return this.getField(14);
	}

	public void setPgcode(String pgCode) {
		this.setField(15, pgCode);
	}

	public String getPgcode() {
		return this.getField(15);
	}

	public void setFkcode(String fkCode) {
		this.setField(16, fkCode);
	}

	public String getFkcode() {
		return this.getField(16);
	}

}
