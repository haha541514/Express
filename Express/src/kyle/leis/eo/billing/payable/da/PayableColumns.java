package kyle.leis.eo.billing.payable.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class PayableColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PayableColumns() {
		m_astrColumns = new String[40];
	}
	
	public PayableColumns(Long pyPyid, 
            Date pyPycreatedate, Date pyPymodifydate, 
            Long pyBrid, BigDecimal pyPyunitprice, 
            BigDecimal pyPyunitnumber, Date pyPyauditdate, 
            BigDecimal pyPycurrencyrate, BigDecimal pyPytotal, 
            BigDecimal pyPyactualtotal, Long pyEpcode, 
            Integer pyEpvid, Date pyPyoccurdate, 
            BigDecimal pyPycommissionrate, Long pyPyidreference, 
            String pyPyremark, String ckCkcode, 
            String ckCkname, String chnChncode, 
            String chnChnname, String chnChnsname, 
            String chnChnsename, Long mopOpid, 
            String mopOpname, Long copOpid, 
            String copOpname, Long aopOpid, 
            String aopOpname, String fkFkcode, 
            String fkFkname, String coCocode, 
            String coConame, String coCosname, 
            String coCosename, Long cwCwcode, 
            String bkBkcode, String bkBkname, 
            String fsFscode, String fsFsname, 
            Long spySpyid){
		m_astrColumns = new String[40];
		setPypyid(pyPyid);
		setPypycreatedate(pyPycreatedate);
		setPypymodifydate(pyPymodifydate);
		setPybrid(pyBrid);
		setPypyunitprice(pyPyunitprice);
		setPypyunitnumber(pyPyunitnumber);
		setPypyauditdate(pyPyauditdate);
		setPypycurrencyrate(pyPycurrencyrate);
		setPypytotal(pyPytotal);
		setPypyactualtotal(pyPyactualtotal);
		setPyepcode(pyEpcode);
		setPyepvid(pyEpvid);
		setPypyoccurdate(pyPyoccurdate);
		setPypycommissionrate(pyPycommissionrate);
		setPypyidreference(pyPyidreference);
		setPypyremark(pyPyremark);
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
		setCocosename(coCosename);
		setCwcwcode(cwCwcode);
		setBkbkcode(bkBkcode);
		setBkbkname(bkBkname);
		setFsfscode(fsFscode);
		setFsfsname(fsFsname);
		setSpyspyid(spySpyid);
	}

	public void setPypyid(Long pyPyid) {
		this.setField(0, pyPyid);
	}

	public String getPypyid() {
		return this.getField(0);
	}

	public void setPypycreatedate(Date pyPycreatedate) {
		this.setField(1, pyPycreatedate);
	}

	public String getPypycreatedate() {
		return this.getField(1);
	}

	public void setPypymodifydate(Date pyPymodifydate) {
		this.setField(2, pyPymodifydate);
	}

	public String getPypymodifydate() {
		return this.getField(2);
	}

	public void setPybrid(Long pyBrid) {
		this.setField(3, pyBrid);
	}

	public String getPybrid() {
		return this.getField(3);
	}

	public void setPypyunitprice(BigDecimal pyPyunitprice) {
		this.setField(4, pyPyunitprice);
	}

	public String getPypyunitprice() {
		return this.getField(4);
	}

	public void setPypyunitnumber(BigDecimal pyPyunitnumber) {
		this.setField(5, pyPyunitnumber);
	}

	public String getPypyunitnumber() {
		return this.getField(5);
	}

	public void setPypyauditdate(Date pyPyauditdate) {
		this.setField(6, pyPyauditdate);
	}

	public String getPypyauditdate() {
		return this.getField(6);
	}

	public void setPypycurrencyrate(BigDecimal pyPycurrencyrate) {
		this.setField(7, pyPycurrencyrate);
	}

	public String getPypycurrencyrate() {
		return this.getField(7);
	}

	public void setPypytotal(BigDecimal pyPytotal) {
		this.setField(8, pyPytotal);
	}

	public String getPypytotal() {
		return this.getField(8);
	}

	public void setPypyactualtotal(BigDecimal pyPyactualtotal) {
		this.setField(9, pyPyactualtotal);
	}

	public String getPypyactualtotal() {
		return this.getField(9);
	}

	public void setPyepcode(Long pyEpcode) {
		this.setField(10, pyEpcode);
	}

	public String getPyepcode() {
		return this.getField(10);
	}

	public void setPyepvid(Integer pyEpvid) {
		this.setField(11, pyEpvid);
	}

	public String getPyepvid() {
		return this.getField(11);
	}

	public void setPypyoccurdate(Date pyPyoccurdate) {
		this.setField(12, pyPyoccurdate);
	}

	public String getPypyoccurdate() {
		return this.getField(12);
	}

	public void setPypycommissionrate(BigDecimal pyPycommissionrate) {
		this.setField(13, pyPycommissionrate);
	}

	public String getPypycommissionrate() {
		return this.getField(13);
	}

	public void setPypyidreference(Long pyPyidreference) {
		this.setField(14, pyPyidreference);
	}

	public String getPypyidreference() {
		return this.getField(14);
	}

	public void setPypyremark(String pyPyremark) {
		this.setField(15, pyPyremark);
	}

	public String getPypyremark() {
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

	public void setCocosename(String coCosename) {
		this.setField(33, coCosename);
	}

	public String getCocosename() {
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

	public void setSpyspyid(Long spySpyid) {
		this.setField(39, spySpyid);
	}

	public String getSpyspyid() {
		return this.getField(39);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
