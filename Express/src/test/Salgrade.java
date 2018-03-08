package test;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="SALGRADE"
 *     
*/
public class Salgrade implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3036274185036533345L;

	/** identifier field */
    private BigDecimal grade;

    /** identifier field */
    private BigDecimal losal;

    /** identifier field */
    private BigDecimal hisal;

    /** full constructor */
    public Salgrade(BigDecimal grade, BigDecimal losal, BigDecimal hisal) {
        this.grade = grade;
        this.losal = losal;
        this.hisal = hisal;
    }

    /** default constructor */
    public Salgrade() {
    }

    /** 
     *                @hibernate.property
     *                 column="GRADE"
     *                 length="22"
     *             
     */
    public BigDecimal getGrade() {
        return this.grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    /** 
     *                @hibernate.property
     *                 column="LOSAL"
     *                 length="22"
     *             
     */
    public BigDecimal getLosal() {
        return this.losal;
    }

    public void setLosal(BigDecimal losal) {
        this.losal = losal;
    }

    /** 
     *                @hibernate.property
     *                 column="HISAL"
     *                 length="22"
     *             
     */
    public BigDecimal getHisal() {
        return this.hisal;
    }

    public void setHisal(BigDecimal hisal) {
        this.hisal = hisal;
    }

    
    
    public boolean equals(Object o) {
    	if (this == o)
    		return true;
    	
      	if (!(o instanceof Salgrade))
      		return false;

      	Salgrade salgrade = (Salgrade) o;
      	if (!this.grade.equals(salgrade.getGrade()))
      		return false;
      	
      	if (!this.hisal.equals(salgrade.getHisal()))
      		return false;
      	
      	if (!this.losal.equals(salgrade.getLosal()))
      		return false;
      	 	
      	
      	return true;    	
    }

    public int hashCode() {
    	
    	int result;
    	result = (grade == null ? 0 : grade.hashCode());
    	result = 29 * result + (hisal == null ? 0 : hisal.hashCode());

    	return result;
    }    
    
    
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("grade", getGrade())
            .append("losal", getLosal())
            .append("hisal", getHisal())
            .toString();
    }

}
