package test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="EMP"
 *     
*/
public class Emp implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1948730398206159081L;

	/** identifier field */
    private Integer empno;

    /** nullable persistent field */
    private String ename;

    /** nullable persistent field */
    private String job;

    /** nullable persistent field */
    private Integer mgr;

    /** nullable persistent field */
    private Date hiredate;

    /** nullable persistent field */
    private BigDecimal sal;

    /** nullable persistent field */
    private BigDecimal comm;

    /** persistent field */
    private test.Dept dept;

    /** full constructor */
    public Emp(String ename, String job, Integer mgr, Date hiredate, BigDecimal sal, BigDecimal comm, test.Dept dept) {
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.dept = dept;
    }

    /** default constructor */
    public Emp() {
    }

    /** minimal constructor */
    public Emp(test.Dept dept) {
        this.dept = dept;
    }

    /** 
     *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Integer"
     *             column="EMPNO"
     *         
     */
    public Integer getEmpno() {
        return this.empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    /** 
     *            @hibernate.property
     *             column="ENAME"
     *             length="10"
     *         
     */
    public String getEname() {
        return this.ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    /** 
     *            @hibernate.property
     *             column="JOB"
     *             length="9"
     *         
     */
    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    /** 
     *            @hibernate.property
     *             column="MGR"
     *             length="4"
     *         
     */
    public Integer getMgr() {
        return this.mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    /** 
     *            @hibernate.property
     *             column="HIREDATE"
     *             length="7"
     *         
     */
    public Date getHiredate() {
        return this.hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    /** 
     *            @hibernate.property
     *             column="SAL"
     *             length="7"
     *         
     */
    public BigDecimal getSal() {
        return this.sal;
    }

    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }

    /** 
     *            @hibernate.property
     *             column="COMM"
     *             length="7"
     *         
     */
    public BigDecimal getComm() {
        return this.comm;
    }

    public void setComm(BigDecimal comm) {
        this.comm = comm;
    }

    /** 
     *            @hibernate.many-to-one
     *             not-null="true"
     *            @hibernate.column name="DEPTNO"         
     *         
     */
    public test.Dept getDept() {
        return this.dept;
    }

    public void setDept(test.Dept dept) {
        this.dept = dept;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("empno", getEmpno())
            .toString();
    }

}
