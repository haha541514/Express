package test;

import kyle.common.dbaccess.query.GeneralCondition;

public class DeptCondition extends GeneralCondition {
	public DeptCondition() {
		m_astrConditions = new String[4];
	}
	
	public void setDeptno(String strDeptno) {
		this.setField(0, strDeptno);
	}
	
	public String getDeptno() {
		return this.getField(0);
	}
	
	public void setDname(String strDname) {
		this.setField(1, strDname);
	}
	
	public String getDname() {
		return this.getField(1);
	}
	
	public void setLoc(String strLoc) {
		this.setField(2, strLoc);
		
	}
	
	public String getLoc() {
		return this.getField(2);
	}	
	
	public void setJob(String strJob) {
		this.setField(3, strJob);
	}
	
	public String getJob() {
		return this.getField(3);
	}	
	
}
