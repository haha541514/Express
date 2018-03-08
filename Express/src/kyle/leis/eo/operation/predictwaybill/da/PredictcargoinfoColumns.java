package kyle.leis.eo.operation.predictwaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PredictcargoinfoColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PredictcargoinfoColumns() {
		m_astrColumns = new String[12];
	}
	
	public PredictcargoinfoColumns(String pciPci_id, 
            String pciPwb_code, String pciCk_code, 
            String pciPci_name, String pciPci_ename, 
            String pciPci_pieces, String pciPci_weight, 
            String pciPci_unitprice, String pciPci_totalprice, 
            String pciPci_hscode, String pciPci_attacheinfo, 
            String pciPci_remark){
		m_astrColumns = new String[12];
		setPcipci_id(pciPci_id);
		setPcipwb_code(pciPwb_code);
		setPcick_code(pciCk_code);
		setPcipci_name(pciPci_name);
		setPcipci_ename(pciPci_ename);
		setPcipci_pieces(pciPci_pieces);
		setPcipci_weight(pciPci_weight);
		setPcipci_unitprice(pciPci_unitprice);
		setPcipci_totalprice(pciPci_totalprice);
		setPcipci_hscode(pciPci_hscode);
		setPcipci_attacheinfo(pciPci_attacheinfo);
		setPcipci_remark(pciPci_remark);
	}

	public void setPcipci_id(String pciPci_id) {
		this.setField(0, pciPci_id);
	}

	public String getPcipci_id() {
		return this.getField(0);
	}

	public void setPcipwb_code(String pciPwb_code) {
		this.setField(1, pciPwb_code);
	}

	public String getPcipwb_code() {
		return this.getField(1);
	}

	public void setPcick_code(String pciCk_code) {
		this.setField(2, pciCk_code);
	}

	public String getPcick_code() {
		return this.getField(2);
	}

	public void setPcipci_name(String pciPci_name) {
		this.setField(3, pciPci_name);
	}

	public String getPcipci_name() {
		return this.getField(3);
	}

	public void setPcipci_ename(String pciPci_ename) {
		this.setField(4, pciPci_ename);
	}

	public String getPcipci_ename() {
		return this.getField(4);
	}

	public void setPcipci_pieces(String pciPci_pieces) {
		this.setField(5, pciPci_pieces);
	}

	public String getPcipci_pieces() {
		return this.getField(5);
	}

	public void setPcipci_weight(String pciPci_weight) {
		this.setField(6, pciPci_weight);
	}

	public String getPcipci_weight() {
		return this.getField(6);
	}

	public void setPcipci_unitprice(String pciPci_unitprice) {
		this.setField(7, pciPci_unitprice);
	}

	public String getPcipci_unitprice() {
		return this.getField(7);
	}

	public void setPcipci_totalprice(String pciPci_totalprice) {
		this.setField(8, pciPci_totalprice);
	}

	public String getPcipci_totalprice() {
		return this.getField(8);
	}

	public void setPcipci_hscode(String pciPci_hscode) {
		this.setField(9, pciPci_hscode);
	}

	public String getPcipci_hscode() {
		return this.getField(9);
	}

	public void setPcipci_attacheinfo(String pciPci_attacheinfo) {
		this.setField(10, pciPci_attacheinfo);
	}

	public String getPcipci_attacheinfo() {
		return this.getField(10);
	}

	public void setPcipci_remark(String pciPci_remark) {
		this.setField(11, pciPci_remark);
	}

	public String getPcipci_remark() {
		return this.getField(11);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
