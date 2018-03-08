package kyle.leis.ds.report.finance.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FeegroupbyonylcoColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FeegroupbyonylcoColumns() {
		m_astrColumns = new String[12];
	}
	
	public FeegroupbyonylcoColumns(String coCo_sname, 
			String Billcount, String Sumpieces, 
			String Sumchargeweight, String Sumserverchargeweight, 
			String Rvtotal, String Pytotal,
			String opOp_name,String coCo_labelcode,
			String Rmbrvtotal, String Hkdrvtotal,
			String eeEe_sname){
		m_astrColumns = new String[12];
		setCoco_sname(coCo_sname);
		setBillcount(Billcount);
		setSumpieces(Sumpieces);
		setSumchargeweight(Sumchargeweight);
		setSumserverchargeweight(Sumserverchargeweight);
		setRvtotal(Rvtotal);
		setPytotal(Pytotal);
		setOpop_name(opOp_name);
		setCoco_labelcode(coCo_labelcode);
		setRmbrvtotal(Rmbrvtotal);
		setHkdrvtotal(Hkdrvtotal);
		setEeee_sname(eeEe_sname);		
	}

	public void setCoco_sname(String coCo_sname) {
		this.setField(0, coCo_sname);
	}

	public String getCoco_sname() {
		return this.getField(0);
	}

	public void setBillcount(String Billcount) {
		this.setField(1, Billcount);
	}

	public String getBillcount() {
		return this.getField(1);
	}
	
	public void setSumpieces(String Sumpieces) {
		this.setField(2, Sumpieces);
	}
	
	public String getSumpieces() {
		return this.getField(2);
	}

	public void setSumchargeweight(String Sumchargeweight) {
		this.setField(3, Sumchargeweight);
	}

	public String getSumchargeweight() {
		return this.getField(3);
	}

	public void setSumserverchargeweight(String Sumserverchargeweight) {
		this.setField(4, Sumserverchargeweight);
	}

	public String getSumserverchargeweight() {
		return this.getField(4);
	}

	public void setRvtotal(String Rvtotal) {
		this.setField(5, Rvtotal);
	}

	public String getRvtotal() {
		return this.getField(5);
	}

	public void setPytotal(String Pytotal) {
		this.setField(6, Pytotal);
	}

	public String getPytotal() {
		return this.getField(6);
	}

	public void setOpop_name(String opOp_name) {
		this.setField(7, opOp_name);
	}

	public String getOpop_name() {
		return this.getField(7);
	}
	

	public void setCoco_labelcode(String coCo_labelcode) {
		this.setField(8, coCo_labelcode);
	}

	public String getCoco_labelcode() {
		return this.getField(8);
	}
	
	public void setRmbrvtotal(String Rmbrvtotal) {
		this.setField(9, Rmbrvtotal);
	}

	public String getRmbrvtotal() {
		return this.getField(9);
	}	
	
	public void setHkdrvtotal(String Hkdrvtotal) {
		this.setField(10, Hkdrvtotal);
	}

	public String getHkdrvtotal() {
		return this.getField(10);
	}	
	
	public void setEeee_sname(String eeEe_sname) {
		this.setField(11, eeEe_sname);
	}

	public String getEeee_sname() {
		return this.getField(11);
	}	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
