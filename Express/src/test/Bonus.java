package test;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="BONUS"
 *     
*/
public class Bonus implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7979351948072199731L;

	/** identifier field */
    private String ename;

    /** identifier field */
    private String job;

    /** identifier field */
    private BigDecimal sal;

    /** identifier field */
    private BigDecimal comm;

    /** full constructor */
    public Bonus(String ename, String job, BigDecimal sal, BigDecimal comm) {
        this.ename = ename;
        this.job = job;
        this.sal = sal;
        this.comm = comm;
    }

    /** default constructor */
    public Bonus() {
    }

    /** 
     *                @hibernate.property
     *                 column="ENAME"
     *             
     */
    public String getEname() {
        return this.ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    /** 
     *                @hibernate.property
     *                 column="JOB"
     *             
     */
    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    /** 
     *                @hibernate.property
     *                 column="SAL"
     *             
     */
    public BigDecimal getSal() {
        return this.sal;
    }

    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }

    /** 
     *                @hibernate.property
     *                 column="COMM"
     *             
     */
    public BigDecimal getComm() {
        return this.comm;
    }

    public void setComm(BigDecimal comm) {
        this.comm = comm;
    }

    public boolean equals(Object o) {
    	if (this == o)
    		return true;
    	
      	if (!(o instanceof Bonus))
      		return false;

      	Bonus bonus = (Bonus) o;
      	if (!this.comm.equals(bonus.getComm()))
      		return false;
      	
      	if (!this.ename.equals(bonus.getEname()))
      		return false;
      	
      	if (!this.job.equals(bonus.getJob()))
      		return false;
      	
        if (!this.sal.equals(bonus.getSal()))
        	return false;    	
      	
      	return true;    	
    }

    public int hashCode() {
    	
    	int result;
    	result = (comm == null ? 0 : comm.hashCode());
    	result = 29 * result + (ename == null ? 0 : ename.hashCode());

    	return result;
    }
    
    
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("ename", getEname())
            .append("job", getJob())
            .append("sal", getSal())
            .append("comm", getComm())
            .toString();
    }

}
