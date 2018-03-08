package kyle.leis.fs.dictionary.customscargo.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class MemorydeclarenameQuery extends HGeneralQuery {
	
	public MemorydeclarenameQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.customscargo.da.MemorydeclarenameColumns(mdn.mdnCode,mdn.mdnLabelcode,mdn.mdnEname,mdn.mdnName) FROM TdiMemorydeclarename as mdn";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "mdn.mdnEname like '%~~%'", "mdn.mdnName like '%~~%'", "mdn.mdnLabelcode ='~~'", "mdn.mdnCode = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setMdnename(String mdnEname) {
		this.setField(0, mdnEname);
	}

	public String getMdnename() {
		return this.getField(0);
	}

	public void setMdnname(String mdnName) {
		this.setField(1, mdnName);
	}

	public String getMdnname() {
		return this.getField(1);
	}

	public void setMdnlabelcode(String mdnLabelcode) {
		this.setField(2, mdnLabelcode);
	}

	public String getMdnlabelcode() {
		return this.getField(2);
	}

	public void setMdncode(String mdnCode) {
		this.setField(3, mdnCode);
	}

	public String getMdncode() {
		return this.getField(3);
	}

}
