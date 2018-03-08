package kyle.leis.es.company.shipperconsignee.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ShipperconsigneeseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ShipperconsigneeseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public ShipperconsigneeseqColumns(String Shipperconsigneeseq){
		m_astrColumns = new String[1];
		setShipperconsigneeseq(Shipperconsigneeseq);
	}

	public void setShipperconsigneeseq(String Shipperconsigneeseq) {
		this.setField(0, Shipperconsigneeseq);
	}

	public String getShipperconsigneeseq() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
