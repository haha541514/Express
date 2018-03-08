package kyle.leis.eo.finance.billrecord.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class AllbillrecordidColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public AllbillrecordidColumns() {
		m_astrColumns = new String[3];
	}
	
	public AllbillrecordidColumns(String Customerbrid, 
            String Paybrid, String sbrSbr_labelcode){
		m_astrColumns = new String[3];
		setCustomerbrid(Customerbrid);
		setPaybrid(Paybrid);
		setSbrsbr_labelcode(sbrSbr_labelcode);
	}

	public void setCustomerbrid(String Customerbrid) {
		this.setField(0, Customerbrid);
	}

	public String getCustomerbrid() {
		return this.getField(0);
	}

	public void setPaybrid(String Paybrid) {
		this.setField(1, Paybrid);
	}

	public String getPaybrid() {
		return this.getField(1);
	}

	public void setSbrsbr_labelcode(String sbrSbr_labelcode) {
		this.setField(2, sbrSbr_labelcode);
	}

	public String getSbrsbr_labelcode() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
