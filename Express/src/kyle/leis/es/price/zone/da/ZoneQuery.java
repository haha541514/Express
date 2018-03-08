package kyle.leis.es.price.zone.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ZoneQuery extends HGeneralQuery {
	
	public ZoneQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.price.zone.da.ZoneColumns(zn.znCode,zn.znName,zn.znEname,zn.znCreatedate,zn.znModifydate,zn.znRemark,zn.znKeywords,pk.pkCode,pk.pkSname,pk.pkSename,ss.ssCode,ss.ssName,cop.opId,cop.opName,mop.opId,mop.opName,zf.zfCode,zf.zfName) FROM TepZone as zn left join zn.tdiZoneformat as zf inner join zn.tdiProductkind as pk inner join zn.tdiSimplestatus as ss inner join zn.tdiOperatorByZnOpIdCreate as cop inner join zn.tdiOperatorByZnOpIdModify as mop ";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "zn.znCode = ~~", "zn.znName like '~~%'", "zn.znEname like '~~%'", "pk.pkCode = '~~'", "ss.ssCode = '~~'", "zn.znKeywords like '~~%'", "zn.znRemark like '~~%'", "zn.znModifydate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= zn.znModifydate" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setZncode(String znCode) {
		this.setField(0, znCode);
	}

	public String getZncode() {
		return this.getField(0);
	}

	public void setZnname(String znName) {
		this.setField(1, znName);
	}

	public String getZnname() {
		return this.getField(1);
	}

	public void setZnename(String znEname) {
		this.setField(2, znEname);
	}

	public String getZnename() {
		return this.getField(2);
	}

	public void setPkcode(String pkCode) {
		this.setField(3, pkCode);
	}

	public String getPkcode() {
		return this.getField(3);
	}

	public void setSscode(String ssCode) {
		this.setField(4, ssCode);
	}

	public String getSscode() {
		return this.getField(4);
	}

	public void setZnkeywords(String znKeywords) {
		this.setField(5, znKeywords);
	}

	public String getZnkeywords() {
		return this.getField(5);
	}

	public void setZnremark(String znRemark) {
		this.setField(6, znRemark);
	}

	public String getZnremark() {
		return this.getField(6);
	}

	public void setModifystartdate(String modifyStartdate) {
		this.setField(7, modifyStartdate);
	}

	public String getModifystartdate() {
		return this.getField(7);
	}

	public void setModifyenddate(String modifyEnddate) {
		this.setField(8, modifyEnddate);
	}

	public String getModifyenddate() {
		return this.getField(8);
	}

}
