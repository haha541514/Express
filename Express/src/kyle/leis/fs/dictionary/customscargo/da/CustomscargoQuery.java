package kyle.leis.fs.dictionary.customscargo.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CustomscargoQuery extends HGeneralQuery {
	
	public CustomscargoQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.customscargo.da.CustomscargoColumns(cc.ccCode,cc.ccEname,cc.ccName,cc.ccHscode,cc.ccModifydate,cc.ccOpIdModifier,cc.ccUnittype) FROM TdiCustomscargo as cc";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cc.ccEname like '%~~%'", "cc.ccName like '%~~%'", "cc.ccHscode='~~'", "cc.ccCode ='~~'", "cc.ccEname='~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setCcename(String ccEname) {
		this.setField(0, ccEname);
	}

	public String getCcename() {
		return this.getField(0);
	}

	public void setCcname(String ccName) {
		this.setField(1, ccName);
	}

	public String getCcname() {
		return this.getField(1);
	}

	public void setCchscode(String ccHscode) {
		this.setField(2, ccHscode);
	}

	public String getCchscode() {
		return this.getField(2);
	}

	public void setCccodes(String ccCodes) {
		this.setField(3, ccCodes);
	}

	public String getCccodes() {
		return this.getField(3);
	}

	public void setCheckename(String checkEname) {
		this.setField(4, checkEname);
	}

	public String getCheckename() {
		return this.getField(4);
	}

}
