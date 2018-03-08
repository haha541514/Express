package kyle.leis.es.smsservice.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class SmsserviceColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SmsserviceColumns() {
		m_astrColumns = new String[15];
	}
	
	public SmsserviceColumns(Long ssSsid, 
            Date ssSsstartdate, Date ssSsenddate, 
            Date ssSscreatedate, Date ssSsmodifydate, 
            String ssSsopnamecreate, String ssSsopnamemodify, 
            BigDecimal ssSsbalanceamount, BigDecimal ssSsunitprice, 
            long ssSssendtotal, String csCocode, 
            String cscoConame, String cscoCoename, 
            String cscoCosname, String cscoCosename){
		m_astrColumns = new String[15];
		setSsssid(ssSsid);
		setSsssstartdate(ssSsstartdate);
		setSsssenddate(ssSsenddate);
		setSssscreatedate(ssSscreatedate);
		setSsssmodifydate(ssSsmodifydate);
		setSsssopnamecreate(ssSsopnamecreate);
		setSsssopnamemodify(ssSsopnamemodify);
		setSsssbalanceamount(ssSsbalanceamount);
		setSsssunitprice(ssSsunitprice);
		setSssssendtotal(ssSssendtotal);
		setCscocode(csCocode);
		setCscoconame(cscoConame);
		setCscocoename(cscoCoename);
		setCscocosname(cscoCosname);
		setCscocosename(cscoCosename);
	}

	public void setSsssid(Long ssSsid) {
		this.setField(0, ssSsid);
	}

	public String getSsssid() {
		return this.getField(0);
	}

	public void setSsssstartdate(Date ssSsstartdate) {
		this.setField(1, ssSsstartdate);
	}

	public String getSsssstartdate() {
		return this.getField(1);
	}

	public void setSsssenddate(Date ssSsenddate) {
		this.setField(2, ssSsenddate);
	}

	public String getSsssenddate() {
		return this.getField(2);
	}

	public void setSssscreatedate(Date ssSscreatedate) {
		this.setField(3, ssSscreatedate);
	}

	public String getSssscreatedate() {
		return this.getField(3);
	}

	public void setSsssmodifydate(Date ssSsmodifydate) {
		this.setField(4, ssSsmodifydate);
	}

	public String getSsssmodifydate() {
		return this.getField(4);
	}

	public void setSsssopnamecreate(String ssSsopnamecreate) {
		this.setField(5, ssSsopnamecreate);
	}

	public String getSsssopnamecreate() {
		return this.getField(5);
	}

	public void setSsssopnamemodify(String ssSsopnamemodify) {
		this.setField(6, ssSsopnamemodify);
	}

	public String getSsssopnamemodify() {
		return this.getField(6);
	}

	public void setSsssbalanceamount(BigDecimal ssSsbalanceamount) {
		this.setField(7, ssSsbalanceamount);
	}

	public String getSsssbalanceamount() {
		return this.getField(7);
	}

	public void setSsssunitprice(BigDecimal ssSsunitprice) {
		this.setField(8, ssSsunitprice);
	}

	public String getSsssunitprice() {
		return this.getField(8);
	}

	public void setSssssendtotal(long ssSssendtotal) {
		this.setField(9, ssSssendtotal);
	}

	public String getSssssendtotal() {
		return this.getField(9);
	}

	public void setCscocode(String csCocode) {
		this.setField(10, csCocode);
	}

	public String getCscocode() {
		return this.getField(10);
	}

	public void setCscoconame(String cscoConame) {
		this.setField(11, cscoConame);
	}

	public String getCscoconame() {
		return this.getField(11);
	}

	public void setCscocoename(String cscoCoename) {
		this.setField(12, cscoCoename);
	}

	public String getCscocoename() {
		return this.getField(12);
	}

	public void setCscocosname(String cscoCosname) {
		this.setField(13, cscoCosname);
	}

	public String getCscocosname() {
		return this.getField(13);
	}

	public void setCscocosename(String cscoCosename) {
		this.setField(14, cscoCosename);
	}

	public String getCscocosename() {
		return this.getField(14);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
