package kyle.leis.eo.billing.receivable.dax;

import java.io.Serializable;

import kyle.common.dbaccess.query.GeneralColumns;

public class ReceivableColumnsForImport extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ReceivableColumnsForImport() {
		m_astrColumns = new String[5];
	}
	
	public void setCwcustomerewbcode(String strCwcustomerewbcode) {
		this.setField(0, strCwcustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(0);
	}

	public void setFkname(String strFkname) {
		this.setField(1, strFkname);
	}

	public String getFkname() {
		return this.getField(1);
	}	
	
	public void setCkcode(String strCkcode) {
		this.setField(2, strCkcode);
	}

	public String getCkcode() {
		return this.getField(2);
	}	
	
	public void setRvtotal(String strRvtotal) {
		this.setField(3, strRvtotal);
	}

	public String getRvtotal() {
		return this.getField(3);
	}		
	
	public void setRvremark(String strRvremark) {
		this.setField(4, strRvremark);
	}

	public String getRvremark() {
		return this.getField(4);
	}		
}
