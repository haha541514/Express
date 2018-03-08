package kyle.leis.es.price.currency.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CurrencyQuery extends HGeneralQuery {
	
	public CurrencyQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.currency.da.CurrencyColumns(cu.epCode,ep.epCreatedate,ep.epModifydate,ep.epStartdate,ep.epEnddate,ep.epRemark,ep.epWithdrawsign,epk.epkCode,epk.epkName,cop.opId,cop.opName,mop.opId,mop.opName,ee.eeCode,ee.eeSname,ps.psCode,ps.psName,pk.pkCode,pk.pkSname,co.coCode,co.coSname,pg.pgCode,pg.pgName) FROM TepCurrency as cu inner join cu.tepExpressprice as ep inner join ep.tdiExpresspricekind as epk inner join ep.tdiOperatorByEpOpIdCreate as cop inner join ep.tdiOperatorByEpOpIdModify as mop inner join ep.tdiEnterpriseelement as ee inner join ep.tdiPricestatus as ps inner join cu.tdiProductkind as pk left join cu.tcoCorporation as co left join cu.tdiPricegroup as pg";
	    m_strWhereClause = "epk.epkCode = 'A07'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cu.epCode = '~~'", "ep.epEnddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= ep.epStartdate", "ps.psCode = '~~'", "cop.opId = ~~", "mop.opId = ~~", "pk.pkCode = '~~'", "co.coCode = '~~'", "pg.pgCode = '~~'", "ee.eeCode = '~~'", "pg.pgCode is ~~", "co.coCode is ~~", "ps.psCode not in (~~)" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
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

	public void setCocode(String coCode) {
		this.setField(7, coCode);
	}

	public String getCocode() {
		return this.getField(7);
	}

	public void setPgcode(String pgCode) {
		this.setField(8, pgCode);
	}

	public String getPgcode() {
		return this.getField(8);
	}

	public void setEecode(String eeCode) {
		this.setField(9, eeCode);
	}

	public String getEecode() {
		return this.getField(9);
	}

	public void setIspgcode(String ispgCode) {
		this.setField(10, ispgCode);
	}

	public String getIspgcode() {
		return this.getField(10);
	}

	public void setIscocode(String iscoCode) {
		this.setField(11, iscoCode);
	}

	public String getIscocode() {
		return this.getField(11);
	}

	public void setNotinpscode(String NotInPsCode) {
		this.setField(12, NotInPsCode);
	}

	public String getNotinpscode() {
		return this.getField(12);
	}

}
