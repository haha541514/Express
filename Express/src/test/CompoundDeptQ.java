package test;

import java.io.Serializable;

import kyle.common.dbaccess.query.GeneralColumns;
import kyle.common.dbaccess.query.IColumns;

import org.apache.commons.lang.builder.ToStringBuilder;

public class CompoundDeptQ extends GeneralColumns implements Serializable {

	public CompoundDeptQ() {
		
	}
	
	public CompoundDeptQ(String dname, String ename, String job){
		m_astrColumns = new String[3];
		setDname(dname);
		setEname(ename);
		setJob(job);
	}
	
	public CompoundDeptQ(Long lKey) {
		
	}
	
	public void setDname(String dname){
		this.setField(0, dname);
	}

	public void setEname(String ename){
		this.setField(1, ename);
	}	
	
	public void setJob(String job){
		this.setField(2, job);
	}		
	

	public String getDname(){
		return this.getField(0);
	}

	public String getEname(){
		return this.getField(1);
	}	
	
	public String getJob(){
		return this.getField(2);
	}
	
    public String toString() {
        return new ToStringBuilder(this)
            .append("Dname", getDname())
            .toString();
    }	
	
}
