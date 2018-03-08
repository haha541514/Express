package kyle.leis.eo.operation.batchwaybill.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class BatchwaybillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public BatchwaybillColumns() {
		m_astrColumns = new String[40];
	}
	
	public BatchwaybillColumns(Long bwBwcode, 
            Date bwBwcreatedate, Date bwBwmodifydate, 
            Date bwBwcompletedate, Date bwBwauditdate, 
            Date bwBwapprovedate, Date bwAdddate, 
            String bwBwremark, BigDecimal bwBwtotalgrossweight, 
            Integer bwBwtotalpieces, String bwBwlabelcode, 
            String bwBwbatchnumber, String bwsBwscode, 
            String bwsBwsname, String chnChncode, 
            String chnChnname, String chnChnsname, 
            String chnChnsename, Long copOpid, 
            String copOpname, Long apopOpid, 
            String apopOpname, Long coopOpid, 
            String coopOpname, Long mopOpid, 
            String mopOpname, Long auopOpid, 
            String auopOpname, String eeEecode, 
            String eeEesname, String eeEeesname, 
            String adtAdtcode, String adtAdtname, 
            String coCocode, String coConame, 
            String coCosname, String coCosename, 
            String bwBwcontainerid,String cctCtcode, 
            String cctCtname){
		m_astrColumns = new String[40];
		setBwbwcode(bwBwcode);
		setBwbwcreatedate(bwBwcreatedate);
		setBwbwmodifydate(bwBwmodifydate);
		setBwbwcompletedate(bwBwcompletedate);
		setBwbwauditdate(bwBwauditdate);
		setBwbwapprovedate(bwBwapprovedate);
		setBwadddate(bwAdddate);
		setBwbwremark(bwBwremark);
		setBwbwtotalgrossweight(bwBwtotalgrossweight);
		setBwbwtotalpieces(bwBwtotalpieces);
		setBwbwlabelcode(bwBwlabelcode);
		setBwbwbatchnumber(bwBwbatchnumber);
		setBwsbwscode(bwsBwscode);
		setBwsbwsname(bwsBwsname);
		setChnchncode(chnChncode);
		setChnchnname(chnChnname);
		setChnchnsname(chnChnsname);
		setChnchnsename(chnChnsename);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setApopopid(apopOpid);
		setApopopname(apopOpname);
		setCoopopid(coopOpid);
		setCoopopname(coopOpname);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setAuopopid(auopOpid);
		setAuopopname(auopOpname);
		setEeeecode(eeEecode);
		setEeeesname(eeEesname);
		setEeeeesname(eeEeesname);
		setAdtadtcode(adtAdtcode);
		setAdtadtname(adtAdtname);
		setCococode(coCocode);
		setCoconame(coConame);
		setCocosname(coCosname);
		setCocosename(coCosename);
		setBwbwcontainerid(bwBwcontainerid);
		setCctctcode(cctCtcode);
		setCctctname(cctCtname);
	}

	public void setBwbwcode(Long bwBwcode) {
		this.setField(0, bwBwcode);
	}

	public String getBwbwcode() {
		return this.getField(0);
	}

	public void setBwbwcreatedate(Date bwBwcreatedate) {
		this.setField(1, bwBwcreatedate);
	}

	public String getBwbwcreatedate() {
		return this.getField(1);
	}

	public void setBwbwmodifydate(Date bwBwmodifydate) {
		this.setField(2, bwBwmodifydate);
	}

	public String getBwbwmodifydate() {
		return this.getField(2);
	}

	public void setBwbwcompletedate(Date bwBwcompletedate) {
		this.setField(3, bwBwcompletedate);
	}

	public String getBwbwcompletedate() {
		return this.getField(3);
	}

	public void setBwbwauditdate(Date bwBwauditdate) {
		this.setField(4, bwBwauditdate);
	}

	public String getBwbwauditdate() {
		return this.getField(4);
	}

	public void setBwbwapprovedate(Date bwBwapprovedate) {
		this.setField(5, bwBwapprovedate);
	}

	public String getBwbwapprovedate() {
		return this.getField(5);
	}

	public void setBwadddate(Date bwAdddate) {
		this.setField(6, bwAdddate);
	}

	public String getBwadddate() {
		return this.getField(6);
	}

	public void setBwbwremark(String bwBwremark) {
		this.setField(7, bwBwremark);
	}

	public String getBwbwremark() {
		return this.getField(7);
	}

	public void setBwbwtotalgrossweight(BigDecimal bwBwtotalgrossweight) {
		this.setField(8, bwBwtotalgrossweight);
	}

	public String getBwbwtotalgrossweight() {
		return this.getField(8);
	}

	public void setBwbwtotalpieces(Integer bwBwtotalpieces) {
		this.setField(9, bwBwtotalpieces);
	}

	public String getBwbwtotalpieces() {
		return this.getField(9);
	}

	public void setBwbwlabelcode(String bwBwlabelcode) {
		this.setField(10, bwBwlabelcode);
	}

	public String getBwbwlabelcode() {
		return this.getField(10);
	}

	public void setBwbwbatchnumber(String bwBwbatchnumber) {
		this.setField(11, bwBwbatchnumber);
	}

	public String getBwbwbatchnumber() {
		return this.getField(11);
	}

	public void setBwsbwscode(String bwsBwscode) {
		this.setField(12, bwsBwscode);
	}

	public String getBwsbwscode() {
		return this.getField(12);
	}

	public void setBwsbwsname(String bwsBwsname) {
		this.setField(13, bwsBwsname);
	}

	public String getBwsbwsname() {
		return this.getField(13);
	}

	public void setChnchncode(String chnChncode) {
		this.setField(14, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(14);
	}

	public void setChnchnname(String chnChnname) {
		this.setField(15, chnChnname);
	}

	public String getChnchnname() {
		return this.getField(15);
	}

	public void setChnchnsname(String chnChnsname) {
		this.setField(16, chnChnsname);
	}

	public String getChnchnsname() {
		return this.getField(16);
	}

	public void setChnchnsename(String chnChnsename) {
		this.setField(17, chnChnsename);
	}

	public String getChnchnsename() {
		return this.getField(17);
	}

	public void setCopopid(Long copOpid) {
		this.setField(18, copOpid);
	}

	public String getCopopid() {
		return this.getField(18);
	}

	public void setCopopname(String copOpname) {
		this.setField(19, copOpname);
	}

	public String getCopopname() {
		return this.getField(19);
	}

	public void setApopopid(Long apopOpid) {
		this.setField(20, apopOpid);
	}

	public String getApopopid() {
		return this.getField(20);
	}

	public void setApopopname(String apopOpname) {
		this.setField(21, apopOpname);
	}

	public String getApopopname() {
		return this.getField(21);
	}

	public void setCoopopid(Long coopOpid) {
		this.setField(22, coopOpid);
	}

	public String getCoopopid() {
		return this.getField(22);
	}

	public void setCoopopname(String coopOpname) {
		this.setField(23, coopOpname);
	}

	public String getCoopopname() {
		return this.getField(23);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(24, mopOpid);
	}

	public String getMopopid() {
		return this.getField(24);
	}

	public void setMopopname(String mopOpname) {
		this.setField(25, mopOpname);
	}

	public String getMopopname() {
		return this.getField(25);
	}

	public void setAuopopid(Long auopOpid) {
		this.setField(26, auopOpid);
	}

	public String getAuopopid() {
		return this.getField(26);
	}

	public void setAuopopname(String auopOpname) {
		this.setField(27, auopOpname);
	}

	public String getAuopopname() {
		return this.getField(27);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(28, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(28);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(29, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(29);
	}

	public void setEeeeesname(String eeEeesname) {
		this.setField(30, eeEeesname);
	}

	public String getEeeeesname() {
		return this.getField(30);
	}

	public void setAdtadtcode(String adtAdtcode) {
		this.setField(31, adtAdtcode);
	}

	public String getAdtadtcode() {
		return this.getField(31);
	}

	public void setAdtadtname(String adtAdtname) {
		this.setField(32, adtAdtname);
	}

	public String getAdtadtname() {
		return this.getField(32);
	}

	public void setCococode(String coCocode) {
		this.setField(33, coCocode);
	}

	public String getCococode() {
		return this.getField(33);
	}

	public void setCoconame(String coConame) {
		this.setField(34, coConame);
	}

	public String getCoconame() {
		return this.getField(34);
	}

	public void setCocosname(String coCosname) {
		this.setField(35, coCosname);
	}

	public String getCocosname() {
		return this.getField(35);
	}

	public void setCocosename(String coCosename) {
		this.setField(36, coCosename);
	}

	public String getCocosename() {
		return this.getField(36);
	}

	public void setBwbwcontainerid(String bwBwcontainerid) {
		this.setField(37, bwBwcontainerid);
	}

	public String getBwbwcontainerid() {
		return this.getField(37);
	}
	public void setCctctcode(String cctCtcode) {
		this.setField(38, cctCtcode);
	}

	public String getCctctcode() {
		return this.getField(38);
	}

	public void setCctctname(String cctCtname) {
		this.setField(39, cctCtname);
	}

	public String getCctctname() {
		return this.getField(39);
	}
	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
