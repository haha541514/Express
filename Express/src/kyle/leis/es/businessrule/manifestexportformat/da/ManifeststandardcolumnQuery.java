package kyle.leis.es.businessrule.manifestexportformat.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ManifeststandardcolumnQuery extends HGeneralQuery {
	
	public ManifeststandardcolumnQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.es.businessrule.manifestexportformat.da.ManifeststandardcolumnColumns( msc.mscCode,  msc.mscColumnname, msc.mscColumnename, msc.mscSqlcolumnname) FROM TdiManifeststandardcolumn as msc";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { " msc.mscCode ='~~'", " msc.mscColumnname='~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setMsccode(String mscCode) {
		this.setField(0, mscCode);
	}

	public String getMsccode() {
		return this.getField(0);
	}

	public void setMsccolumnname(String mscColumnname) {
		this.setField(1, mscColumnname);
	}

	public String getMsccolumnname() {
		return this.getField(1);
	}

}
