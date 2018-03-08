package kyle.leis.ds.report.finance.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FeegroupbycwColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FeegroupbycwColumns() {
		m_astrColumns = new String[16];
	}
	
	public FeegroupbycwColumns(String cwCo_code_supplier, 
            String cwChn_code_supplier, String Billcount, 
            String Sumpieces, String Sumchargeweight, 
            String Sumserverchargeweight, String Rvtotal, 
            String Pytotal, String chnChn_sname, 
            String coCo_sname, String coCo_labelcode,
            String Rmbpytotal, String Hkdpytotal,
            String eeEe_sname, String Usdpytotal,
            String Eurpytotal){
		m_astrColumns = new String[16];
		setCwco_code_supplier(cwCo_code_supplier);
		setCwchn_code_supplier(cwChn_code_supplier);
		setBillcount(Billcount);
		setSumpieces(Sumpieces);
		setSumchargeweight(Sumchargeweight);
		setSumserverchargeweight(Sumserverchargeweight);
		setRvtotal(Rvtotal);
		setPytotal(Pytotal);
		setChnchn_sname(chnChn_sname);
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

	public void setCwchn_code_supplier(String cwChn_code_supplier) {
		this.setField(1, cwChn_code_supplier);
	}

	public String getCwchn_code_supplier() {
		return this.getField(1);
	}

	public void setBillcount(String Billcount) {
		this.setField(2, Billcount);
	}

	public String getBillcount() {
		return this.getField(2);
	}
	
	public void setSumpieces(String Sumpieces) {
		this.setField(3, Sumpieces);
	}

	public String getSumpieces() {
		return this.getField(3);
	}

	public void setSumchargeweight(String Sumchargeweight) {
		this.setField(4, Sumchargeweight);
	}

	public String getSumchargeweight() {
		return this.getField(4);
	}

	public void setSumserverchargeweight(String Sumserverchargeweight) {
		this.setField(5, Sumserverchargeweight);
	}

	public String getSumserverchargeweight() {
		return this.getField(5);
	}


	public void setRvtotal(String Rvtotal) {
		this.setField(6, Rvtotal);
	}

	public String getRvtotal() {
		return this.getField(6);
	}

	public void setPytotal(String Pytotal) {
		this.setField(7, Pytotal);
	}

	public String getPytotal() {
		return this.getField(7);
	}
	
	public void setChnchn_sname(String chnChn_sname) {
		this.setField(8, chnChn_sname);
	}

	public String getChnchn_sname() {
		return this.getField(8);
	}

	public void setCoco_sname(String coCo_sname) {
		this.setField(9, coCo_sname);
	}

	public String getCoco_sname() {
		return this.getField(9);
	}


	public void setCoco_labelcode(String coCo_labelcode) {
		this.setField(10, coCo_labelcode);
	}

	public String getCoco_labelcode() {
		return this.getField(10);
	}
	
	public void setRmbpytotal(String Rmbpytotal) {
		this.setField(11, Rmbpytotal);
	}

	public String getRmbpytotal() {
		return this.getField(11);
	}	
	
	public void setHkdpytotal(String Hkdpytotal) {
		this.setField(12, Hkdpytotal);
	}

	public String getHkdpytotal() {
		return this.getField(12);
	}	
	
	public void setEeee_sname(String eeEe_sname) {
		this.setField(13, eeEe_sname);
	}

	public String getEeee_sname() {
		return this.getField(13);
	}	
	
	public void setUsdpytotal(String Hkdpytotal) {
		this.setField(14, Hkdpytotal);
	}

	public String getUsdpytotal() {
		return this.getField(14);
	}		
	
	public void setEurpytotal(String Hkdpytotal) {
		this.setField(15, Hkdpytotal);
	}

	public String getEurpytotal() {
		return this.getField(15);
	}		
	
	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
