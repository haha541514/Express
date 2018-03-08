package kyle.leis.eo.operation.customsdeclaration.da;

import java.io.Serializable;
import java.math.BigDecimal;

import kyle.common.dbaccess.query.GeneralColumns;

public class CustomsdeclarationColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CustomsdeclarationColumns() {
		m_astrColumns = new String[17];
	}
	
	public CustomsdeclarationColumns(Long cdCdid, 
            String cdCdlabelcode, String cdCdename, 
            String cdCdname, BigDecimal cdCdgrossweight, 
            int cdCdpieces, BigDecimal cdCdunitprice, 
            BigDecimal cdCdtotalprice, Long hwCwcode, 
            String hwHwcustomslabelprintsign, BigDecimal cdCdamount, 
            String cdCdunitname, String cdCdgoodslabelcode, 
            String cwCwcustomerewbcode, String cwCwserverewbcode, 
            String cdCdshipperaddress, String cdCdconsigneeaddress){
		m_astrColumns = new String[17];
		setCdcdid(cdCdid);
		setCdcdlabelcode(cdCdlabelcode);
		setCdcdename(cdCdename);
		setCdcdname(cdCdname);
		setCdcdgrossweight(cdCdgrossweight);
		setCdcdpieces(cdCdpieces);
		setCdcdunitprice(cdCdunitprice);
		setCdcdtotalprice(cdCdtotalprice);
		setHwcwcode(hwCwcode);
		setHwhwcustomslabelprintsign(hwHwcustomslabelprintsign);
		setCdcdamount(cdCdamount);
		setCdcdunitname(cdCdunitname);
		setCdcdgoodslabelcode(cdCdgoodslabelcode);
		setCwcwcustomerewbcode(cwCwcustomerewbcode);
		setCwcwserverewbcode(cwCwserverewbcode);
		setCdcdshipperaddress(cdCdshipperaddress);
		setCdcdconsigneeaddress(cdCdconsigneeaddress);
	}

	public void setCdcdid(Long cdCdid) {
		this.setField(0, cdCdid);
	}

	public String getCdcdid() {
		return this.getField(0);
	}

	public void setCdcdlabelcode(String cdCdlabelcode) {
		this.setField(1, cdCdlabelcode);
	}

	public String getCdcdlabelcode() {
		return this.getField(1);
	}

	public void setCdcdename(String cdCdename) {
		this.setField(2, cdCdename);
	}

	public String getCdcdename() {
		return this.getField(2);
	}

	public void setCdcdname(String cdCdname) {
		this.setField(3, cdCdname);
	}

	public String getCdcdname() {
		return this.getField(3);
	}

	public void setCdcdgrossweight(BigDecimal cdCdgrossweight) {
		this.setField(4, cdCdgrossweight);
	}

	public String getCdcdgrossweight() {
		return this.getField(4);
	}

	public void setCdcdpieces(int cdCdpieces) {
		this.setField(5, cdCdpieces);
	}

	public String getCdcdpieces() {
		return this.getField(5);
	}

	public void setCdcdunitprice(BigDecimal cdCdunitprice) {
		this.setField(6, cdCdunitprice);
	}

	public String getCdcdunitprice() {
		return this.getField(6);
	}

	public void setCdcdtotalprice(BigDecimal cdCdtotalprice) {
		this.setField(7, cdCdtotalprice);
	}

	public String getCdcdtotalprice() {
		return this.getField(7);
	}

	public void setHwcwcode(Long hwCwcode) {
		this.setField(8, hwCwcode);
	}

	public String getHwcwcode() {
		return this.getField(8);
	}

	public void setHwhwcustomslabelprintsign(String hwHwcustomslabelprintsign) {
		this.setField(9, hwHwcustomslabelprintsign);
	}

	public String getHwhwcustomslabelprintsign() {
		return this.getField(9);
	}

	public void setCdcdamount(BigDecimal cdCdamount) {
		this.setField(10, cdCdamount);
	}

	public String getCdcdamount() {
		return this.getField(10);
	}

	public void setCdcdunitname(String cdCdunitname) {
		this.setField(11, cdCdunitname);
	}

	public String getCdcdunitname() {
		return this.getField(11);
	}

	public void setCdcdgoodslabelcode(String cdCdgoodslabelcode) {
		this.setField(12, cdCdgoodslabelcode);
	}

	public String getCdcdgoodslabelcode() {
		return this.getField(12);
	}

	public void setCwcwcustomerewbcode(String cwCwcustomerewbcode) {
		this.setField(13, cwCwcustomerewbcode);
	}

	public String getCwcwcustomerewbcode() {
		return this.getField(13);
	}

	public void setCwcwserverewbcode(String cwCwserverewbcode) {
		this.setField(14, cwCwserverewbcode);
	}

	public String getCwcwserverewbcode() {
		return this.getField(14);
	}

	public void setCdcdshipperaddress(String cdCdshipperaddress) {
		this.setField(15, cdCdshipperaddress);
	}

	public String getCdcdshipperaddress() {
		return this.getField(15);
	}

	public void setCdcdconsigneeaddress(String cdCdconsigneeaddress) {
		this.setField(16, cdCdconsigneeaddress);
	}

	public String getCdcdconsigneeaddress() {
		return this.getField(16);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
