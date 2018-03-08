package kyle.leis.es.company.predicttemplate.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class PredicttemplateQuery extends HGeneralQuery {
	
	public PredicttemplateQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.company.predicttemplate.da.PredicttemplateColumns(pot.potId,pot.potName,pot.potRemark,pot.potCreatedate,pot.potModifydate,cop.opId,cop.opName,mop.opId,mop.opName,co.coCode,co.coSname) FROM TcoPredictordertemplate as pot inner join pot.tcoCustomer as cm inner join pot.tdiOperatorByOpIdCreator as cop inner join pot.tdiOperatorByOpIdModifier as mop inner join cm.tcoCorporation as co";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "pot.potId = ~~", "pot.potName like '%~~%'", "co.coCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setPotid(String potId) {
		this.setField(0, potId);
	}

	public String getPotid() {
		return this.getField(0);
	}

	public void setPotname(String potName) {
		this.setField(1, potName);
	}

	public String getPotname() {
		return this.getField(1);
	}

	public void setCocode(String coCode) {
		this.setField(2, coCode);
	}

	public String getCocode() {
		return this.getField(2);
	}

}
