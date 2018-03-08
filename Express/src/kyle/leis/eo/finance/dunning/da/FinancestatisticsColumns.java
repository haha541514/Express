package kyle.leis.eo.finance.dunning.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class FinancestatisticsColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FinancestatisticsColumns() {
		m_astrColumns = new String[23];
	}
	
	public FinancestatisticsColumns(String fscomp_idCocode, 
            BigDecimal fsFsreceivableamount, BigDecimal fsFsreceivableso, 
            BigDecimal fsFsreceivableunsohold, BigDecimal fsFspayableso, 
            BigDecimal fsFspayableunsohold, BigDecimal fsFspayableamount, 
            BigDecimal fsFsbalanceamount, String coCocode, 
            String coConame, String coCosname, 
            String coCosename, String coColabelcode, 
            BigDecimal cmCmcreditlimit, String ckCkcode, 
            String ckCkname, BigDecimal cmCmtemporarycreditlimit, 
            Date cmCmtclstartdate, Date cmCmtclenddate, 
            Date fsFslatesthawbcreatedate, BigDecimal fsFsbybalanceamount, 
            String eeEesname, BigDecimal cmCmholdhwrate){
		m_astrColumns = new String[23];
		setFscomp_idcocode(fscomp_idCocode);
		setFsfsreceivableamount(fsFsreceivableamount);
		setFsfsreceivableso(fsFsreceivableso);
		setFsfsreceivableunsohold(fsFsreceivableunsohold);
		setFsfspayableso(fsFspayableso);
		setFsfspayableunsohold(fsFspayableunsohold);
		setFsfspayableamount(fsFspayableamount);
		setFsfsbalanceamount(fsFsbalanceamount);
		setCococode(coCocode);
		setCoconame(coConame);
		setCocosname(coCosname);
		setCocosename(coCosename);
		setCocolabelcode(coColabelcode);
		setCmcmcreditlimit(cmCmcreditlimit);
		setCkckcode(ckCkcode);
		setCkckname(ckCkname);
		setCmcmtemporarycreditlimit(cmCmtemporarycreditlimit);
		setCmcmtclstartdate(cmCmtclstartdate);
		setCmcmtclenddate(cmCmtclenddate);
		setFsfslatesthawbcreatedate(fsFslatesthawbcreatedate);
		setFsfsbybalanceamount(fsFsbybalanceamount);
		setEeeesname(eeEesname);
		setCmcmholdhwrate(cmCmholdhwrate);
	}

	public void setFscomp_idcocode(String fscomp_idCocode) {
		this.setField(0, fscomp_idCocode);
	}

	public String getFscomp_idcocode() {
		return this.getField(0);
	}

	public void setFsfsreceivableamount(BigDecimal fsFsreceivableamount) {
		this.setField(1, fsFsreceivableamount);
	}

	public String getFsfsreceivableamount() {
		return this.getField(1);
	}

	public void setFsfsreceivableso(BigDecimal fsFsreceivableso) {
		this.setField(2, fsFsreceivableso);
	}

	public String getFsfsreceivableso() {
		return this.getField(2);
	}

	public void setFsfsreceivableunsohold(BigDecimal fsFsreceivableunsohold) {
		this.setField(3, fsFsreceivableunsohold);
	}

	public String getFsfsreceivableunsohold() {
		return this.getField(3);
	}

	public void setFsfspayableso(BigDecimal fsFspayableso) {
		this.setField(4, fsFspayableso);
	}

	public String getFsfspayableso() {
		return this.getField(4);
	}

	public void setFsfspayableunsohold(BigDecimal fsFspayableunsohold) {
		this.setField(5, fsFspayableunsohold);
	}

	public String getFsfspayableunsohold() {
		return this.getField(5);
	}

	public void setFsfspayableamount(BigDecimal fsFspayableamount) {
		this.setField(6, fsFspayableamount);
	}

	public String getFsfspayableamount() {
		return this.getField(6);
	}

	public void setFsfsbalanceamount(BigDecimal fsFsbalanceamount) {
		this.setField(7, fsFsbalanceamount);
	}

	public String getFsfsbalanceamount() {
		return this.getField(7);
	}

	public void setCococode(String coCocode) {
		this.setField(8, coCocode);
	}

	public String getCococode() {
		return this.getField(8);
	}

	public void setCoconame(String coConame) {
		this.setField(9, coConame);
	}

	public String getCoconame() {
		return this.getField(9);
	}

	public void setCocosname(String coCosname) {
		this.setField(10, coCosname);
	}

	public String getCocosname() {
		return this.getField(10);
	}

	public void setCocosename(String coCosename) {
		this.setField(11, coCosename);
	}

	public String getCocosename() {
		return this.getField(11);
	}

	public void setCocolabelcode(String coColabelcode) {
		this.setField(12, coColabelcode);
	}

	public String getCocolabelcode() {
		return this.getField(12);
	}

	public void setCmcmcreditlimit(BigDecimal cmCmcreditlimit) {
		this.setField(13, cmCmcreditlimit);
	}

	public String getCmcmcreditlimit() {
		return this.getField(13);
	}

	public void setCkckcode(String ckCkcode) {
		this.setField(14, ckCkcode);
	}

	public String getCkckcode() {
		return this.getField(14);
	}

	public void setCkckname(String ckCkname) {
		this.setField(15, ckCkname);
	}

	public String getCkckname() {
		return this.getField(15);
	}

	public void setCmcmtemporarycreditlimit(BigDecimal cmCmtemporarycreditlimit) {
		this.setField(16, cmCmtemporarycreditlimit);
	}

	public String getCmcmtemporarycreditlimit() {
		return this.getField(16);
	}

	public void setCmcmtclstartdate(Date cmCmtclstartdate) {
		this.setField(17, cmCmtclstartdate);
	}

	public String getCmcmtclstartdate() {
		return this.getField(17);
	}

	public void setCmcmtclenddate(Date cmCmtclenddate) {
		this.setField(18, cmCmtclenddate);
	}

	public String getCmcmtclenddate() {
		return this.getField(18);
	}

	public void setFsfslatesthawbcreatedate(Date fsFslatesthawbcreatedate) {
		this.setField(19, fsFslatesthawbcreatedate);
	}

	public String getFsfslatesthawbcreatedate() {
		return this.getField(19);
	}

	public void setFsfsbybalanceamount(BigDecimal fsFsbybalanceamount) {
		this.setField(20, fsFsbybalanceamount);
	}

	public String getFsfsbybalanceamount() {
		return this.getField(20);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(21, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(21);
	}

	public void setCmcmholdhwrate(BigDecimal cmCmholdhwrate) {
		this.setField(22, cmCmholdhwrate);
	}

	public String getCmcmholdhwrate() {
		return this.getField(22);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
