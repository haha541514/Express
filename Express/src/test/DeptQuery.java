package test;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class DeptQuery extends HGeneralQuery {
	
	public DeptQuery(){
		
	    m_strSelectClause = "select new test.CompoundDeptQ(a.dname,b.ename,b.job) from Dept as a inner join a.emps as b";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {"a.deptno = ~~" , "a.dname = '~~'", "a.loc = '~~'", "b.job = '~~'"};
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	} 

}
