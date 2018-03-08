package kyle.leis.es.businessrule.productrule.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class BusinessproductruleQuery extends HGeneralQuery {
	
	public BusinessproductruleQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.productrule.da.BusinessproductruleColumns(br.brId,br.brName,br.brEname,br.brStartdate,br.brEnddate,pr.brId,pr.prAllcussignoutbyoriginwbsign) FROM TbrBusinessrule as br inner join br.tbrProductrule as pr";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= br.brStartdate", "br.brEnddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "pr.tdiProductkind.pkCode = '~~'", "br.tdiSimplestatus.ssCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setStartdate(String StartDate) {
		this.setField(0, StartDate);
	}

	public String getStartdate() {
		return this.getField(0);
	}

	public void setEnddate(String EndDate) {
		this.setField(1, EndDate);
	}

	public String getEnddate() {
		return this.getField(1);
	}

	public void setPkcode(String pkCode) {
		this.setField(2, pkCode);
	}

	public String getPkcode() {
		return this.getField(2);
	}

	public void setSscode(String ssCode) {
		this.setField(3, ssCode);
	}

	public String getSscode() {
		return this.getField(3);
	}

}
