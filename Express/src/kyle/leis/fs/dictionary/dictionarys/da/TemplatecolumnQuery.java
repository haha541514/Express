package kyle.leis.fs.dictionary.dictionarys.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class TemplatecolumnQuery extends HGeneralQuery {
	
	public TemplatecolumnQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.dictionarys.da.TemplatecolumnColumns(tc.tcId,tc.tcColumnindex,tc.tcColumnname,tc.tcColumnename,tc.tcColumngroup,tc.tcColumntype,tc.tcAllownullsign,tc.tcMaxlength,tc.tcMinlength) FROM TdiTemplatecolumn as tc";
	    m_strWhereClause = "tc.tcId < 1111";
	    m_strOrderByClause = "tc.tcColumnindex asc";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "sfm.sfkmServerbillkind = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setSfkmserverbillkind(String sfkmServerbillkind) {
		this.setField(0, sfkmServerbillkind);
	}

	public String getSfkmserverbillkind() {
		return this.getField(0);
	}

}
