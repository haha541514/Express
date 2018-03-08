package kyle.leis.eo.finance.cashrecord.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class CashrecordColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CashrecordColumns() {
		m_astrColumns = new String[34];
	}
	
	public CashrecordColumns(Long crCrid, 
            BigDecimal crCrcurrencyrate, BigDecimal crCrtotal, 
            String crCrlabelcode, Date crCroccurdate, 
            Date crCrcreatedate, Date crCrmodifydate, 
            Date crCrauditdate, String crCrreceiptlabelcode, 
            Integer crCrreceiptprinttime, String crCrremark, 
            String ptPtcode, String ptPtname, 
            String crsCrscode, String crsCrsname, 
            String ckCkcode, String ckCkname, 
            Long copOpid, String copOpname, 
            Long aopOpid, String aopOpname, 
            Long mopOpid, String mopOpname, 
            String eeEecode, String eeEename, 
            String eeEesname, String eeEeesname, 
            String coCocode, String coConame, 
            String coCosname, String coCosename, 
            String crkCrkcode, String crkCrkname, 
            Long woWoid){
		m_astrColumns = new String[34];
		setCrcrid(crCrid);
		setCrcrcurrencyrate(crCrcurrencyrate);
		setCrcrtotal(crCrtotal);
		setCrcrlabelcode(crCrlabelcode);
		setCrcroccurdate(crCroccurdate);
		setCrcrcreatedate(crCrcreatedate);
		setCrcrmodifydate(crCrmodifydate);
		setCrcrauditdate(crCrauditdate);
		setCrcrreceiptlabelcode(crCrreceiptlabelcode);
		setCrcrreceiptprinttime(crCrreceiptprinttime);
		setCrcrremark(crCrremark);
		setPtptcode(ptPtcode);
		setPtptname(ptPtname);
		setCrscrscode(crsCrscode);
		setCrscrsname(crsCrsname);
		setCkckcode(ckCkcode);
		setCkckname(ckCkname);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setAopopid(aopOpid);
		setAopopname(aopOpname);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setEeeecode(eeEecode);
		setEeeename(eeEename);
		setEeeesname(eeEesname);
		setEeeeesname(eeEeesname);
		setCococode(coCocode);
		setCoconame(coConame);
		setCocosname(coCosname);
		setCocosename(coCosename);
		setCrkcrkcode(crkCrkcode);
		setCrkcrkname(crkCrkname);
		setWowoid(woWoid);
	}

	public void setCrcrid(Long crCrid) {
		this.setField(0, crCrid);
	}

	public String getCrcrid() {
		return this.getField(0);
	}

	public void setCrcrcurrencyrate(BigDecimal crCrcurrencyrate) {
		this.setField(1, crCrcurrencyrate);
	}

	public String getCrcrcurrencyrate() {
		return this.getField(1);
	}

	public void setCrcrtotal(BigDecimal crCrtotal) {
		this.setField(2, crCrtotal);
	}

	public String getCrcrtotal() {
		return this.getField(2);
	}

	public void setCrcrlabelcode(String crCrlabelcode) {
		this.setField(3, crCrlabelcode);
	}

	public String getCrcrlabelcode() {
		return this.getField(3);
	}

	public void setCrcroccurdate(Date crCroccurdate) {
		this.setField(4, crCroccurdate);
	}

	public String getCrcroccurdate() {
		return this.getField(4);
	}

	public void setCrcrcreatedate(Date crCrcreatedate) {
		this.setField(5, crCrcreatedate);
	}

	public String getCrcrcreatedate() {
		return this.getField(5);
	}

	public void setCrcrmodifydate(Date crCrmodifydate) {
		this.setField(6, crCrmodifydate);
	}

	public String getCrcrmodifydate() {
		return this.getField(6);
	}

	public void setCrcrauditdate(Date crCrauditdate) {
		this.setField(7, crCrauditdate);
	}

	public String getCrcrauditdate() {
		return this.getField(7);
	}

	public void setCrcrreceiptlabelcode(String crCrreceiptlabelcode) {
		this.setField(8, crCrreceiptlabelcode);
	}

	public String getCrcrreceiptlabelcode() {
		return this.getField(8);
	}

	public void setCrcrreceiptprinttime(Integer crCrreceiptprinttime) {
		this.setField(9, crCrreceiptprinttime);
	}

	public String getCrcrreceiptprinttime() {
		return this.getField(9);
	}

	public void setCrcrremark(String crCrremark) {
		this.setField(10, crCrremark);
	}

	public String getCrcrremark() {
		return this.getField(10);
	}

	public void setPtptcode(String ptPtcode) {
		this.setField(11, ptPtcode);
	}

	public String getPtptcode() {
		return this.getField(11);
	}

	public void setPtptname(String ptPtname) {
		this.setField(12, ptPtname);
	}

	public String getPtptname() {
		return this.getField(12);
	}

	public void setCrscrscode(String crsCrscode) {
		this.setField(13, crsCrscode);
	}

	public String getCrscrscode() {
		return this.getField(13);
	}

	public void setCrscrsname(String crsCrsname) {
		this.setField(14, crsCrsname);
	}

	public String getCrscrsname() {
		return this.getField(14);
	}

	public void setCkckcode(String ckCkcode) {
		this.setField(15, ckCkcode);
	}

	public String getCkckcode() {
		return this.getField(15);
	}

	public void setCkckname(String ckCkname) {
		this.setField(16, ckCkname);
	}

	public String getCkckname() {
		return this.getField(16);
	}

	public void setCopopid(Long copOpid) {
		this.setField(17, copOpid);
	}

	public String getCopopid() {
		return this.getField(17);
	}

	public void setCopopname(String copOpname) {
		this.setField(18, copOpname);
	}

	public String getCopopname() {
		return this.getField(18);
	}

	public void setAopopid(Long aopOpid) {
		this.setField(19, aopOpid);
	}

	public String getAopopid() {
		return this.getField(19);
	}

	public void setAopopname(String aopOpname) {
		this.setField(20, aopOpname);
	}

	public String getAopopname() {
		return this.getField(20);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(21, mopOpid);
	}

	public String getMopopid() {
		return this.getField(21);
	}

	public void setMopopname(String mopOpname) {
		this.setField(22, mopOpname);
	}

	public String getMopopname() {
		return this.getField(22);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(23, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(23);
	}

	public void setEeeename(String eeEename) {
		this.setField(24, eeEename);
	}

	public String getEeeename() {
		return this.getField(24);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(25, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(25);
	}

	public void setEeeeesname(String eeEeesname) {
		this.setField(26, eeEeesname);
	}

	public String getEeeeesname() {
		return this.getField(26);
	}

	public void setCococode(String coCocode) {
		this.setField(27, coCocode);
	}

	public String getCococode() {
		return this.getField(27);
	}

	public void setCoconame(String coConame) {
		this.setField(28, coConame);
	}

	public String getCoconame() {
		return this.getField(28);
	}

	public void setCocosname(String coCosname) {
		this.setField(29, coCosname);
	}

	public String getCocosname() {
		return this.getField(29);
	}

	public void setCocosename(String coCosename) {
		this.setField(30, coCosename);
	}

	public String getCocosename() {
		return this.getField(30);
	}

	public void setCrkcrkcode(String crkCrkcode) {
		this.setField(31, crkCrkcode);
	}

	public String getCrkcrkcode() {
		return this.getField(31);
	}

	public void setCrkcrkname(String crkCrkname) {
		this.setField(32, crkCrkname);
	}

	public String getCrkcrkname() {
		return this.getField(32);
	}

	public void setWowoid(Long woWoid) {
		this.setField(33, woWoid);
	}

	public String getWowoid() {
		return this.getField(33);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
