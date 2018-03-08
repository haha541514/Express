package kyle.leis.eo.operation.customsdeclaration.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CdaddressColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CdaddressColumns() {
		m_astrColumns = new String[3];
	}
	
	public CdaddressColumns(String cdscaCdsca_code, 
            String cdscaCdsca_shipperaddress, String cdscaCdsca_consigneeaddress){
		m_astrColumns = new String[3];
		setCdscacdsca_code(cdscaCdsca_code);
		setCdscacdsca_shipperaddress(cdscaCdsca_shipperaddress);
		setCdscacdsca_consigneeaddress(cdscaCdsca_consigneeaddress);
	}

	public void setCdscacdsca_code(String cdscaCdsca_code) {
		this.setField(0, cdscaCdsca_code);
	}

	public String getCdscacdsca_code() {
		return this.getField(0);
	}

	public void setCdscacdsca_shipperaddress(String cdscaCdsca_shipperaddress) {
		this.setField(1, cdscaCdsca_shipperaddress);
	}

	public String getCdscacdsca_shipperaddress() {
		return this.getField(1);
	}

	public void setCdscacdsca_consigneeaddress(String cdscaCdsca_consigneeaddress) {
		this.setField(2, cdscaCdsca_consigneeaddress);
	}

	public String getCdscacdsca_consigneeaddress() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
