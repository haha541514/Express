package kyle.leis.es.businessrule.weightrule.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class WeightrulevalueQuery extends HGeneralQuery {
	
	public WeightrulevalueQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.weightrule.da.WeightrulevalueColumns(wrv.comp_id.brId,wrv.comp_id.brvId,wrv.znId,wrv.znvId,wrv.znvCondition,wrv.znvExpression) FROM TbrWeightrulevalue as wrv inner join wrv.tbrWeightrule as wr";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "wrv.comp_id.brId = ~~", "wrv.comp_id.brvId = ~~", "cpr.znId = ~~", "cpr.znvId = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setBrvid(String brvId) {
		this.setField(1, brvId);
	}

	public String getBrvid() {
		return this.getField(1);
	}

	public void setZnid(String znId) {
		this.setField(2, znId);
	}

	public String getZnid() {
		return this.getField(2);
	}

	public void setZnvid(String znvId) {
		this.setField(3, znvId);
	}

	public String getZnvid() {
		return this.getField(3);
	}

}
