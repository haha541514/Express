package kyle.leis.es.businessrule.operationprompt.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class OptcorporationQuery extends HGeneralQuery {
	
	public OptcorporationQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.operationprompt.da.OptcorporationColumns(optco.comp_id.brId,optco.optcCssign,co.coCode,co.coName,co.coSname,co.coLabelcode) FROM TbrOptcorporation as optco inner join optco.tcoCorporation as co";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "optco.comp_id.brId in (~~)", "co.coCode = '~~'", "optco.optcCssign = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
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

	public void setCocode(String coCode) {
		this.setField(1, coCode);
	}

	public String getCocode() {
		return this.getField(1);
	}

	public void setOptccssign(String optcCssign) {
		this.setField(2, optcCssign);
	}

	public String getOptccssign() {
		return this.getField(2);
	}

}
