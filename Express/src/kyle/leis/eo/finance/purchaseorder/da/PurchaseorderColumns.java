package kyle.leis.eo.finance.purchaseorder.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PurchaseorderColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PurchaseorderColumns() {
		m_astrColumns = new String[10];
	}
	
	public PurchaseorderColumns(String poPo_id, 
            String poSs_code, String ssSs_name, 
            String poPo_op_id_creator, String poPo_op_id_modifier, 
            String poPo_createdate, String poPo_modifydate, 
            String poPo_occurdate, String creator, 
            String modifier){
		m_astrColumns = new String[10];
		setPopo_id(poPo_id);
		setPoss_code(poSs_code);
		setSsss_name(ssSs_name);
		setPopo_op_id_creator(poPo_op_id_creator);
		setPopo_op_id_modifier(poPo_op_id_modifier);
		setPopo_createdate(poPo_createdate);
		setPopo_modifydate(poPo_modifydate);
		setPopo_occurdate(poPo_occurdate);
		setCreator(creator);
		setModifier(modifier);
	}

	public void setPopo_id(String poPo_id) {
		this.setField(0, poPo_id);
	}

	public String getPopo_id() {
		return this.getField(0);
	}

	public void setPoss_code(String poSs_code) {
		this.setField(1, poSs_code);
	}

	public String getPoss_code() {
		return this.getField(1);
	}

	public void setSsss_name(String ssSs_name) {
		this.setField(2, ssSs_name);
	}

	public String getSsss_name() {
		return this.getField(2);
	}

	public void setPopo_op_id_creator(String poPo_op_id_creator) {
		this.setField(3, poPo_op_id_creator);
	}

	public String getPopo_op_id_creator() {
		return this.getField(3);
	}

	public void setPopo_op_id_modifier(String poPo_op_id_modifier) {
		this.setField(4, poPo_op_id_modifier);
	}

	public String getPopo_op_id_modifier() {
		return this.getField(4);
	}

	public void setPopo_createdate(String poPo_createdate) {
		this.setField(5, poPo_createdate);
	}

	public String getPopo_createdate() {
		return this.getField(5);
	}

	public void setPopo_modifydate(String poPo_modifydate) {
		this.setField(6, poPo_modifydate);
	}

	public String getPopo_modifydate() {
		return this.getField(6);
	}

	public void setPopo_occurdate(String poPo_occurdate) {
		this.setField(7, poPo_occurdate);
	}

	public String getPopo_occurdate() {
		return this.getField(7);
	}

	public void setCreator(String creator) {
		this.setField(8, creator);
	}

	public String getCreator() {
		return this.getField(8);
	}

	public void setModifier(String modifier) {
		this.setField(9, modifier);
	}

	public String getModifier() {
		return this.getField(9);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
