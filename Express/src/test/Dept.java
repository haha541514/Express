package test;

import java.io.Serializable;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="DEPT"
 *     
*/
public class Dept implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer deptno;

    /** nullable persistent field */
    private String dname;

    /** nullable persistent field */
    private String loc;

    /** persistent field */
    private Set emps;

    /** full constructor */
    public Dept(String dname, String loc, Set emps) {
        this.dname = dname;
        this.loc = loc;
        this.emps = emps;
    }

    /** default constructor */
    public Dept() {
    }

    /** minimal constructor */
    public Dept(Set emps) {
        this.emps = emps;
    }

    /** 
     *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Integer"
     *             column="DEPTNO"
     *         
     */
    public Integer getDeptno() {
        return this.deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    /** 
     *            @hibernate.property
     *             column="DNAME"
     *             length="14"
     *         
     */
    public String getDname() {
        return this.dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    /** 
     *            @hibernate.property
     *             column="LOC"
     *             length="13"
     *         
     */
    public String getLoc() {
        return this.loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    /** 
     *            @hibernate.set
     *             lazy="true"
     *             inverse="true"
     *             cascade="none"
     *            @hibernate.collection-key
     *             column="DEPTNO"
     *            @hibernate.collection-one-to-many
     *             class="kyle.test.Emp"
     *         
     */
    public Set getEmps() {
        return this.emps;
    }

    public void setEmps(Set emps) {
        this.emps = emps;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("deptno", getDeptno())
            .toString();
    }

}
