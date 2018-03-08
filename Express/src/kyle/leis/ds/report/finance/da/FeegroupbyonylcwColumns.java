package kyle.leis.ds.report.finance.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FeegroupbyonylcwColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FeegroupbyonylcwColumns() {
		m_astrColumns = new String[14];
	}
	
	public FeegroupbyonylcwColumns(String cwCo_code_supplier, 
            String Billcount, String Sumpieces,
            String Sumchargeweight, String Sumserverchargeweight, 
            String Rvtotal,String Pytotal,
            String coCo_sname,String coCo_labelcode,
            String Rmbpytotal, String Hkdpytotal,
            String eeEe_sname, String Usdpytotal,
            String Eurpytotal){
		m_astrColumns = new String[14];
		setCwco_code_supplier(cwCo_code_supplier);
		setBillcount(Billcount);
		setSumpieces(Sumpieces);
		setSumchargeweight(Sumchargeweight);
		setSumserverchargeweight(Sumserverchargeweight);
		setRvtotal(Rvtotal);
		setPytotal(Pytotal);
		setCoco_sname(coCo_sname);
		setCoco_labelcode(coCo_labelcode);
		setRmbpytotal(Rmbpytotal);
		setHkdpytotal(Hkdpytotal);
		setEeee_sname(eeEe_sname);		
		setUsdpytotal(Usdpytotal);
		setEurpytotal(Eurpytotal);
	}

	public void setCwco_code_supplier(String cwCo_code_supplier) {
		this.setField(0, cwCo_code_supplier);
	}

	public String getCwco_code_supplier() {
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


	public void setCoco_sname(String coCo_sname) {
		this.setField(7, coCo_sname);
	}

	public String getCoco_sname() {
		return this.getField(7);
	}

	public void setCoco_labelcode(String coCo_labelcode) {
		this.setField(8, coCo_labelcode);
	}

	public String getCoco_labelcode() {
		return this.getField(8);
	}
	
	public void setRmbpytotal(String Rmbpytotal) {
		this.setField(9, Rmbpytotal);
	}

	public String getRmbpytotal() {
		return this.getField(9);
	}	
	
	public void setHkdpytotal(String Hkdpytotal) {
		this.setField(10, Hkdpytotal);
	}

	public String getHkdpytotal() {
		return this.getField(10);
	}		
	
	public void setEeee_sname(String eeEe_sname) {
		this.setField(11, eeEe_sname);
	}

	public String getEeee_sname() {
		return this.getField(11);
	}	
	
	public void setUsdpytotal(String Hkdpytotal) {
		this.setField(12, Hkdpytotal);
	}

	public String getUsdpytotal() {
		return this.getField(12);
	}		
	
	public void setEurpytotal(String Hkdpytotal) {
		this.setField(13, Hkdpytotal);
	}

	public String getEurpytotal() {
		return this.getField(13);
	}		
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
