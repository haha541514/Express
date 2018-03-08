package kyle.leis.es.smsservice.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class SmsrechargerecordColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SmsrechargerecordColumns() {
		m_astrColumns = new String[20];
	}
	
	public SmsrechargerecordColumns(Long srrcomp_idSrrid, 
            BigDecimal srrSrramount, Date srrSrrcreatedate, 
            String srrSrropnamecreate, String srrSrrremark, 
            Long ssSsid, Date ssSsstartdate, 
            Date ssSsenddate, Date ssSscreatedate, 
            Date ssSsmodifydate, String ssSsopnamecreate, 
            String ssSsopnamemodify, BigDecimal ssSsbalanceamount, 
            BigDecimal ssSsunitprice, long ssSssendtotal, 
            String csCocode, String cscoConame, 
            String cscoCoename, String cscoCosname, 
            String cscoCosename){
		m_astrColumns = new String[20];
		setSrrcomp_idsrrid(srrcomp_idSrrid);
		setSrrsrramount(srrSrramount);
		setSrrsrrcreatedate(srrSrrcreatedate);
		setSrrsrropnamecreate(srrSrropnamecreate);
		setSrrsrrremark(srrSrrremark);
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

	public void setSrrcomp_idsrrid(Long srrcomp_idSrrid) {
		this.setField(0, srrcomp_idSrrid);
	}

	public String getSrrcomp_idsrrid() {
		return this.getField(0);
	}

	public void setSrrsrramount(BigDecimal srrSrramount) {
		this.setField(1, srrSrramount);
	}

	public String getSrrsrramount() {
		return this.getField(1);
	}

	public void setSrrsrrcreatedate(Date srrSrrcreatedate) {
		this.setField(2, srrSrrcreatedate);
	}

	public String getSrrsrrcreatedate() {
		return this.getField(2);
	}

	public void setSrrsrropnamecreate(String srrSrropnamecreate) {
		this.setField(3, srrSrropnamecreate);
	}

	public String getSrrsrropnamecreate() {
		return this.getField(3);
	}

	public void setSrrsrrremark(String srrSrrremark) {
		this.setField(4, srrSrrremark);
	}

	public String getSrrsrrremark() {
		return this.getField(4);
	}

	public void setSsssid(Long ssSsid) {
		this.setField(5, ssSsid);
	}

	public String getSsssid() {
		return this.getField(5);
	}

	public void setSsssstartdate(Date ssSsstartdate) {
		this.setField(6, ssSsstartdate);
	}

	public String getSsssstartdate() {
		return this.getField(6);
	}

	public void setSsssenddate(Date ssSsenddate) {
		this.setField(7, ssSsenddate);
	}

	public String getSsssenddate() {
		return this.getField(7);
	}

	public void setSssscreatedate(Date ssSscreatedate) {
		this.setField(8, ssSscreatedate);
	}

	public String getSssscreatedate() {
		return this.getField(8);
	}

	public void setSsssmodifydate(Date ssSsmodifydate) {
		this.setField(9, ssSsmodifydate);
	}

	public String getSsssmodifydate() {
		return this.getField(9);
	}

	public void setSsssopnamecreate(String ssSsopnamecreate) {
		this.setField(10, ssSsopnamecreate);
	}

	public String getSsssopnamecreate() {
		return this.getField(10);
	}

	public void setSsssopnamemodify(String ssSsopnamemodify) {
		this.setField(11, ssSsopnamemodify);
	}

	public String getSsssopnamemodify() {
		return this.getField(11);
	}

	public void setSsssbalanceamount(BigDecimal ssSsbalanceamount) {
		this.setField(12, ssSsbalanceamount);
	}

	public String getSsssbalanceamount() {
		return this.getField(12);
	}

	public void setSsssunitprice(BigDecimal ssSsunitprice) {
		this.setField(13, ssSsunitprice);
	}

	public String getSsssunitprice() {
		return this.getField(13);
	}

	public void setSssssendtotal(long ssSssendtotal) {
		this.setField(14, ssSssendtotal);
	}

	public String getSssssendtotal() {
		return this.getField(14);
	}

	public void setCscocode(String csCocode) {
		this.setField(15, csCocode);
	}

	public String getCscocode() {
		return this.getField(15);
	}

	public void setCscoconame(String cscoConame) {
		this.setField(16, cscoConame);
	}

	public String getCscoconame() {
		return this.getField(16);
	}

	public void setCscocoename(String cscoCoename) {
		this.setField(17, cscoCoename);
	}

	public String getCscocoename() {
		return this.getField(17);
	}

	public void setCscocosname(String cscoCosname) {
		this.setField(18, cscoCosname);
	}

	public String getCscocosname() {
		return this.getField(18);
	}

	public void setCscocosename(String cscoCosename) {
		this.setField(19, cscoCosename);
	}

	public String getCscocosename() {
		return this.getField(19);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
