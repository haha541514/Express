package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class IssuetypeQuery extends HGeneralQuery {
	
	public IssuetypeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.IssuetypeColumns(isut.isutCode,isut.isutName,isut.isutEname,isut.isutNoticeinfo,isut.isutGroup,isut.isutCustomervisiblesign,ss.ssCode,ss.ssName) FROM TdiIssuetype as isut inner join isut.tdiSimplestatus as ss";
	    m_strWhereClause = "ss.ssCode = 'ON'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "isut.isutCode = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setIsutcode(String isutCode) {
		this.setField(0, isutCode);
	}

	public String getIsutcode() {
		return this.getField(0);
	}

}
