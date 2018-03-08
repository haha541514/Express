package kyle.leis.fs.dictionary.productkind.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class PkcargokindQuery extends HGeneralQuery {
	
	public PkcargokindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.dictionary.productkind.da.PkcargokindColumns(pkck.comp_id.pkCode,pkck.comp_id.cgkCode) FROM TdiPkcargokind as pkck inner join pkck.tdiCargokind as ck inner join pkck.tdiProductkind as pk";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "pkck.comp_id.pkCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setPkcode(String pkcode) {
		this.setField(0, pkcode);
	}

	public String getPkcode() {
		return this.getField(0);
	}

}
