package kyle.leis.eo.operation.predictwaybill.da;

import kyle.common.dbaccess.table.ITableRecord;
import kyle.common.dbaccess.table.TableRecord;

/**
 * ±í¼ÇÂ¼Àà
 */
public class ToppredictcargoinfoTR extends TableRecord {

	public ToppredictcargoinfoTR() {
		super(ToppredictcargoinfoTD.getDatabaseInstance());
	}

	public ITableRecord createInstance() {
		return new ToppredictcargoinfoTR();
	}

	public void setPci_id(String PCI_ID) {
		this.setFieldValue(0, PCI_ID);
	}

	public String getPci_id() {
		return this.getFieldValue(0);
	}

	public void setPci_idCondition(String PCI_ID) {
		this.setConditionValue(0, PCI_ID);
	}

	public void setPwb_code(String PWB_CODE) {
		this.setFieldValue(1, PWB_CODE);
	}

	public String getPwb_code() {
		return this.getFieldValue(1);
	}

	public void setPwb_codeCondition(String PWB_CODE) {
		this.setConditionValue(1, PWB_CODE);
	}

	public void setCk_code(String CK_CODE) {
		this.setFieldValue(2, CK_CODE);
	}

	public String getCk_code() {
		return this.getFieldValue(2);
	}

	public void setCk_codeCondition(String CK_CODE) {
		this.setConditionValue(2, CK_CODE);
	}

	public void setPci_name(String PCI_NAME) {
		this.setFieldValue(3, PCI_NAME);
	}

	public String getPci_name() {
		return this.getFieldValue(3);
	}

	public void setPci_nameCondition(String PCI_NAME) {
		this.setConditionValue(3, PCI_NAME);
	}

	public void setPci_ename(String PCI_ENAME) {
		this.setFieldValue(4, PCI_ENAME);
	}

	public String getPci_ename() {
		return this.getFieldValue(4);
	}

	public void setPci_enameCondition(String PCI_ENAME) {
		this.setConditionValue(4, PCI_ENAME);
	}

	public void setPci_pieces(String PCI_PIECES) {
		this.setFieldValue(5, PCI_PIECES);
	}

	public String getPci_pieces() {
		return this.getFieldValue(5);
	}

	public void setPci_piecesCondition(String PCI_PIECES) {
		this.setConditionValue(5, PCI_PIECES);
	}

	public void setPci_weight(String PCI_WEIGHT) {
		this.setFieldValue(6, PCI_WEIGHT);
	}

	public String getPci_weight() {
		return this.getFieldValue(6);
	}

	public void setPci_weightCondition(String PCI_WEIGHT) {
		this.setConditionValue(6, PCI_WEIGHT);
	}

	public void setPci_unitprice(String PCI_UNITPRICE) {
		this.setFieldValue(7, PCI_UNITPRICE);
	}

	public String getPci_unitprice() {
		return this.getFieldValue(7);
	}

	public void setPci_unitpriceCondition(String PCI_UNITPRICE) {
		this.setConditionValue(7, PCI_UNITPRICE);
	}

	public void setPci_totalprice(String PCI_TOTALPRICE) {
		this.setFieldValue(8, PCI_TOTALPRICE);
	}

	public String getPci_totalprice() {
		return this.getFieldValue(8);
	}

	public void setPci_totalpriceCondition(String PCI_TOTALPRICE) {
		this.setConditionValue(8, PCI_TOTALPRICE);
	}

	public void setPci_hscode(String PCI_HSCODE) {
		this.setFieldValue(9, PCI_HSCODE);
	}

	public String getPci_hscode() {
		return this.getFieldValue(9);
	}

	public void setPci_hscodeCondition(String PCI_HSCODE) {
		this.setConditionValue(9, PCI_HSCODE);
	}

	public void setPci_attacheinfo(String PCI_ATTACHEINFO) {
		this.setFieldValue(10, PCI_ATTACHEINFO);
	}

	public String getPci_attacheinfo() {
		return this.getFieldValue(10);
	}

	public void setPci_attacheinfoCondition(String PCI_ATTACHEINFO) {
		this.setConditionValue(10, PCI_ATTACHEINFO);
	}

	public void setPci_remark(String PCI_REMARK) {
		this.setFieldValue(11, PCI_REMARK);
	}

	public String getPci_remark() {
		return this.getFieldValue(11);
	}

	public void setPci_remarkCondition(String PCI_REMARK) {
		this.setConditionValue(11, PCI_REMARK);
	}


}
