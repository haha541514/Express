package kyle.leis.eo.billing.receivable.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class ReceivableColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ReceivableColumns() {
		m_astrColumns = new String[39];
	}
	
	public ReceivableColumns(Long rvRvid, 
            Date rvRvcreatedate, Date rvRvmodifydate, 
            Date rvRvauditdate, Long rvBrid, 
            BigDecimal rvRvunitprice, BigDecimal rvRvunitnumber, 
            BigDecimal rvRvcurrencyrate, BigDecimal rvRvtotal, 
            BigDecimal rvRvactualtotal, Long rvEpcode, 
            Integer rvEpvid, Date rvRvoccurdate, 
            BigDecimal rvRvcommissionrate, Long rvRvidreference, 
            String rvRvremark, String ckCkcode, 
            String ckCkname, String chnChncode, 
            String chnChnname, String chnChnsname, 
            String chnChnsename, Long mopOpid, 
            String mopOpname, Long copOpid, 
            String copOpname, Long aopOpid, 
            String aopOpname, String fkFkcode, 
            String fkFkname, String coCocode, 
            String coConame, String coCosname, 
            String coColabelcode, Long cwCwcode, 
            String bkBkcode, String bkBkname, 
            String fsFscode, String fsFsname){
		m_astrColumns = new String[39];
		setRvrvid(rvRvid);
		setRvrvcreatedate(rvRvcreatedate);
		setRvrvmodifydate(rvRvmodifydate);
		setRvrvauditdate(rvRvauditdate);
		setRvbrid(rvBrid);
		setRvrvunitprice(rvRvunitprice);
		setRvrvunitnumber(rvRvunitnumber);
		setRvrvcurrencyrate(rvRvcurrencyrate);
		setRvrvtotal(rvRvtotal);
		setRvrvactualtotal(rvRvactualtotal);
		setRvepcode(rvEpcode);
		setRvepvid(rvEpvid);
		setRvrvoccurdate(rvRvoccurdate);
		setRvrvcommissionrate(rvRvcommissionrate);
		setRvrvidreference(rvRvidreference);
		setRvrvremark(rvRvremark);
		setCkckcode(ckCkcode);
		setCkckname(ckCkname);
		setChnchncode(chnChncode);
		setChnchnname(chnChnname);
		setChnchnsname(chnChnsname);
		setChnchnsename(chnChnsename);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setAopopid(aopOpid);
		setAopopname(aopOpname);
		setFkfkcode(fkFkcode);
		setFkfkname(fkFkname);
		setCococode(coCocode);
		setCoconame(coConame);
		setCocosname(coCosname);
		setCocolabelcode(coColabelcode);
		setCwcwcode(cwCwcode);
		setBkbkcode(bkBkcode);
		setBkbkname(bkBkname);
		setFsfscode(fsFscode);
		setFsfsname(fsFsname);
	}

	public void setRvrvid(Long rvRvid) {
		this.setField(0, rvRvid);
	}

	public String getRvrvid() {
		return this.getField(0);
	}

	public void setRvrvcreatedate(Date rvRvcreatedate) {
		this.setField(1, rvRvcreatedate);
	}

	public String getRvrvcreatedate() {
		return this.getField(1);
	}

	public void setRvrvmodifydate(Date rvRvmodifydate) {
		this.setField(2, rvRvmodifydate);
	}

	public String getRvrvmodifydate() {
		return this.getField(2);
	}

	public void setRvrvauditdate(Date rvRvauditdate) {
		this.setField(3, rvRvauditdate);
	}

	public String getRvrvauditdate() {
		return this.getField(3);
	}

	public void setRvbrid(Long rvBrid) {
		this.setField(4, rvBrid);
	}

	public String getRvbrid() {
		return this.getField(4);
	}

	public void setRvrvunitprice(BigDecimal rvRvunitprice) {
		this.setField(5, rvRvunitprice);
	}

	public String getRvrvunitprice() {
		return this.getField(5);
	}

	public void setRvrvunitnumber(BigDecimal rvRvunitnumber) {
		this.setField(6, rvRvunitnumber);
	}

	public String getRvrvunitnumber() {
		return this.getField(6);
	}

	public void setRvrvcurrencyrate(BigDecimal rvRvcurrencyrate) {
		this.setField(7, rvRvcurrencyrate);
	}

	public String getRvrvcurrencyrate() {
		return this.getField(7);
	}

	public void setRvrvtotal(BigDecimal rvRvtotal) {
		this.setField(8, rvRvtotal);
	}

	public String getRvrvtotal() {
		return this.getField(8);
	}

	public void setRvrvactualtotal(BigDecimal rvRvactualtotal) {
		this.setField(9, rvRvactualtotal);
	}

	public String getRvrvactualtotal() {
		return this.getField(9);
	}

	public void setRvepcode(Long rvEpcode) {
		this.setField(10, rvEpcode);
	}

	public String getRvepcode() {
		return this.getField(10);
	}

	public void setRvepvid(Integer rvEpvid) {
		this.setField(11, rvEpvid);
	}

	public String getRvepvid() {
		return this.getField(11);
	}

	public void setRvrvoccurdate(Date rvRvoccurdate) {
		this.setField(12, rvRvoccurdate);
	}

	public String getRvrvoccurdate() {
		return this.getField(12);
	}

	public void setRvrvcommissionrate(BigDecimal rvRvcommissionrate) {
		this.setField(13, rvRvcommissionrate);
	}

	public String getRvrvcommissionrate() {
		return this.getField(13);
	}

	public void setRvrvidreference(Long rvRvidreference) {
		this.setField(14, rvRvidreference);
	}

	public String getRvrvidreference() {
		return this.getField(14);
	}

	public void setRvrvremark(String rvRvremark) {
		this.setField(15, rvRvremark);
	}

	public String getRvrvremark() {
		return this.getField(15);
	}

	public void setCkckcode(String ckCkcode) {
		this.setField(16, ckCkcode);
	}

	public String getCkckcode() {
		return this.getField(16);
	}

	public void setCkckname(String ckCkname) {
		this.setField(17, ckCkname);
	}

	public String getCkckname() {
		return this.getField(17);
	}

	public void setChnchncode(String chnChncode) {
		this.setField(18, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(18);
	}

	public void setChnchnname(String chnChnname) {
		this.setField(19, chnChnname);
	}

	public String getChnchnname() {
		return this.getField(19);
	}

	public void setChnchnsname(String chnChnsname) {
		this.setField(20, chnChnsname);
	}

	public String getChnchnsname() {
		return this.getField(20);
	}

	public void setChnchnsename(String chnChnsename) {
		this.setField(21, chnChnsename);
	}

	public String getChnchnsename() {
		return this.getField(21);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(22, mopOpid);
	}

	public String getMopopid() {
		return this.getField(22);
	}

	public void setMopopname(String mopOpname) {
		this.setField(23, mopOpname);
	}

	public String getMopopname() {
		return this.getField(23);
	}

	public void setCopopid(Long copOpid) {
		this.setField(24, copOpid);
	}

	public String getCopopid() {
		return this.getField(24);
	}

	public void setCopopname(String copOpname) {
		this.setField(25, copOpname);
	}

	public String getCopopname() {
		return this.getField(25);
	}

	public void setAopopid(Long aopOpid) {
		this.setField(26, aopOpid);
	}

	public String getAopopid() {
		return this.getField(26);
	}

	public void setAopopname(String aopOpname) {
		this.setField(27, aopOpname);
	}

	public String getAopopname() {
		return this.getField(27);
	}

	public void setFkfkcode(String fkFkcode) {
		this.setField(28, fkFkcode);
	}

	public String getFkfkcode() {
		return this.getField(28);
	}

	public void setFkfkname(String fkFkname) {
		this.setField(29, fkFkname);
	}

	public String getFkfkname() {
		return this.getField(29);
	}

	public void setCococode(String coCocode) {
		this.setField(30, coCocode);
	}

	public String getCococode() {
		return this.getField(30);
	}

	public void setCoconame(String coConame) {
		this.setField(31, coConame);
	}

	public String getCoconame() {
		return this.getField(31);
	}

	public void setCocosname(String coCosname) {
		this.setField(32, coCosname);
	}

	public String getCocosname() {
		return this.getField(32);
	}

	public void setCocolabelcode(String coColabelcode) {
		this.setField(33, coColabelcode);
	}

	public String getCocolabelcode() {
		return this.getField(33);
	}

	public void setCwcwcode(Long cwCwcode) {
		this.setField(34, cwCwcode);
	}

	public String getCwcwcode() {
		return this.getField(34);
	}

	public void setBkbkcode(String bkBkcode) {
		this.setField(35, bkBkcode);
	}

	public String getBkbkcode() {
		return this.getField(35);
	}

	public void setBkbkname(String bkBkname) {
		this.setField(36, bkBkname);
	}

	public String getBkbkname() {
		return this.getField(36);
	}

	public void setFsfscode(String fsFscode) {
		this.setField(37, fsFscode);
	}

	public String getFsfscode() {
		return this.getField(37);
	}

	public void setFsfsname(String fsFsname) {
		this.setField(38, fsFsname);
	}

	public String getFsfsname() {
		return this.getField(38);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
