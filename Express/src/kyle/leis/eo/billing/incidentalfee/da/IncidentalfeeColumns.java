package kyle.leis.eo.billing.incidentalfee.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class IncidentalfeeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public IncidentalfeeColumns() {
		m_astrColumns = new String[39];
	}
	
	public IncidentalfeeColumns(Long ifIfid, 
            BigDecimal ifIfcurrencyrate, BigDecimal ifIftotal, 
            BigDecimal ifIfactualtotal, Date ifIfoccurdate, 
            Date ifIfcreatedate, Date ifIfmodifydate, 
            Date ifIfconfirmdate, Long ifIfidreference, 
            String ifIfremark, String ckCkcode, 
            String ckCkname, String ckCkename, 
            Long brBrid, String chnChncode, 
            String chnChnname, String chnChnename, 
            String chnChnsname, String chnChnsename, 
            String fsFscode, String fsFsname, 
            String fsFsename, Long confopOpid, 
            String confopOpcode, String confopOpname, 
            Long copOpid, String copOpcode, 
            String copOpname, Long mopOpid, 
            String mopOpcode, String mopOpname, 
            String fkFkcode, String fkFkname, 
            String coCocode, String coConame, 
            String coCosname, String coCosename, 
            String bkBkcode, String bkBkname){
		m_astrColumns = new String[39];
		setIfifid(ifIfid);
		setIfifcurrencyrate(ifIfcurrencyrate);
		setIfiftotal(ifIftotal);
		setIfifactualtotal(ifIfactualtotal);
		setIfifoccurdate(ifIfoccurdate);
		setIfifcreatedate(ifIfcreatedate);
		setIfifmodifydate(ifIfmodifydate);
		setIfifconfirmdate(ifIfconfirmdate);
		setIfifidreference(ifIfidreference);
		setIfifremark(ifIfremark);
		setCkckcode(ckCkcode);
		setCkckname(ckCkname);
		setCkckename(ckCkename);
		setBrbrid(brBrid);
		setChnchncode(chnChncode);
		setChnchnname(chnChnname);
		setChnchnename(chnChnename);
		setChnchnsname(chnChnsname);
		setChnchnsename(chnChnsename);
		setFsfscode(fsFscode);
		setFsfsname(fsFsname);
		setFsfsename(fsFsename);
		setConfopopid(confopOpid);
		setConfopopcode(confopOpcode);
		setConfopopname(confopOpname);
		setCopopid(copOpid);
		setCopopcode(copOpcode);
		setCopopname(copOpname);
		setMopopid(mopOpid);
		setMopopcode(mopOpcode);
		setMopopname(mopOpname);
		setFkfkcode(fkFkcode);
		setFkfkname(fkFkname);
		setCococode(coCocode);
		setCoconame(coConame);
		setCocosname(coCosname);
		setCocosename(coCosename);
		setBkbkcode(bkBkcode);
		setBkbkname(bkBkname);
	}

	public void setIfifid(Long ifIfid) {
		this.setField(0, ifIfid);
	}

	public String getIfifid() {
		return this.getField(0);
	}

	public void setIfifcurrencyrate(BigDecimal ifIfcurrencyrate) {
		this.setField(1, ifIfcurrencyrate);
	}

	public String getIfifcurrencyrate() {
		return this.getField(1);
	}

	public void setIfiftotal(BigDecimal ifIftotal) {
		this.setField(2, ifIftotal);
	}

	public String getIfiftotal() {
		return this.getField(2);
	}

	public void setIfifactualtotal(BigDecimal ifIfactualtotal) {
		this.setField(3, ifIfactualtotal);
	}

	public String getIfifactualtotal() {
		return this.getField(3);
	}

	public void setIfifoccurdate(Date ifIfoccurdate) {
		this.setField(4, ifIfoccurdate);
	}

	public String getIfifoccurdate() {
		return this.getField(4);
	}

	public void setIfifcreatedate(Date ifIfcreatedate) {
		this.setField(5, ifIfcreatedate);
	}

	public String getIfifcreatedate() {
		return this.getField(5);
	}

	public void setIfifmodifydate(Date ifIfmodifydate) {
		this.setField(6, ifIfmodifydate);
	}

	public String getIfifmodifydate() {
		return this.getField(6);
	}

	public void setIfifconfirmdate(Date ifIfconfirmdate) {
		this.setField(7, ifIfconfirmdate);
	}

	public String getIfifconfirmdate() {
		return this.getField(7);
	}

	public void setIfifidreference(Long ifIfidreference) {
		this.setField(8, ifIfidreference);
	}

	public String getIfifidreference() {
		return this.getField(8);
	}

	public void setIfifremark(String ifIfremark) {
		this.setField(9, ifIfremark);
	}

	public String getIfifremark() {
		return this.getField(9);
	}

	public void setCkckcode(String ckCkcode) {
		this.setField(10, ckCkcode);
	}

	public String getCkckcode() {
		return this.getField(10);
	}

	public void setCkckname(String ckCkname) {
		this.setField(11, ckCkname);
	}

	public String getCkckname() {
		return this.getField(11);
	}

	public void setCkckename(String ckCkename) {
		this.setField(12, ckCkename);
	}

	public String getCkckename() {
		return this.getField(12);
	}

	public void setBrbrid(Long brBrid) {
		this.setField(13, brBrid);
	}

	public String getBrbrid() {
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

	public void setChnchnename(String chnChnename) {
		this.setField(16, chnChnename);
	}

	public String getChnchnename() {
		return this.getField(16);
	}

	public void setChnchnsname(String chnChnsname) {
		this.setField(17, chnChnsname);
	}

	public String getChnchnsname() {
		return this.getField(17);
	}

	public void setChnchnsename(String chnChnsename) {
		this.setField(18, chnChnsename);
	}

	public String getChnchnsename() {
		return this.getField(18);
	}

	public void setFsfscode(String fsFscode) {
		this.setField(19, fsFscode);
	}

	public String getFsfscode() {
		return this.getField(19);
	}

	public void setFsfsname(String fsFsname) {
		this.setField(20, fsFsname);
	}

	public String getFsfsname() {
		return this.getField(20);
	}

	public void setFsfsename(String fsFsename) {
		this.setField(21, fsFsename);
	}

	public String getFsfsename() {
		return this.getField(21);
	}

	public void setConfopopid(Long confopOpid) {
		this.setField(22, confopOpid);
	}

	public String getConfopopid() {
		return this.getField(22);
	}

	public void setConfopopcode(String confopOpcode) {
		this.setField(23, confopOpcode);
	}

	public String getConfopopcode() {
		return this.getField(23);
	}

	public void setConfopopname(String confopOpname) {
		this.setField(24, confopOpname);
	}

	public String getConfopopname() {
		return this.getField(24);
	}

	public void setCopopid(Long copOpid) {
		this.setField(25, copOpid);
	}

	public String getCopopid() {
		return this.getField(25);
	}

	public void setCopopcode(String copOpcode) {
		this.setField(26, copOpcode);
	}

	public String getCopopcode() {
		return this.getField(26);
	}

	public void setCopopname(String copOpname) {
		this.setField(27, copOpname);
	}

	public String getCopopname() {
		return this.getField(27);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(28, mopOpid);
	}

	public String getMopopid() {
		return this.getField(28);
	}

	public void setMopopcode(String mopOpcode) {
		this.setField(29, mopOpcode);
	}

	public String getMopopcode() {
		return this.getField(29);
	}

	public void setMopopname(String mopOpname) {
		this.setField(30, mopOpname);
	}

	public String getMopopname() {
		return this.getField(30);
	}

	public void setFkfkcode(String fkFkcode) {
		this.setField(31, fkFkcode);
	}

	public String getFkfkcode() {
		return this.getField(31);
	}

	public void setFkfkname(String fkFkname) {
		this.setField(32, fkFkname);
	}

	public String getFkfkname() {
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

	public void setBkbkcode(String bkBkcode) {
		this.setField(37, bkBkcode);
	}

	public String getBkbkcode() {
		return this.getField(37);
	}

	public void setBkbkname(String bkBkname) {
		this.setField(38, bkBkname);
	}

	public String getBkbkname() {
		return this.getField(38);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
