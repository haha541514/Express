package kyle.leis.eo.finance.serverbillrecord.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class ServerbillrecordColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ServerbillrecordColumns() {
		m_astrColumns = new String[22];
	}
	
	public ServerbillrecordColumns(Long sbrSbrid, 
            String sbrSbrlabelcode, BigDecimal sbrSbrtotal, 
            Date sbrSbroccurdate, Date sbrSbrcreatedate, 
            Date sbrSbrmodifydate, String sbrSbrremark, 
            String coCocode, String coCosname, 
            String chnChncode, String chnChnsname, 
            Long mopOpid, String mopOpname, 
            Long copOpid, String copOpname, 
            String eeEecode, String eeEesname, 
            String ssSscode, String ssSsname, 
            String ckCkcode, String ckCkname, 
            Long woWoid){
		m_astrColumns = new String[22];
		setSbrsbrid(sbrSbrid);
		setSbrsbrlabelcode(sbrSbrlabelcode);
		setSbrsbrtotal(sbrSbrtotal);
		setSbrsbroccurdate(sbrSbroccurdate);
		setSbrsbrcreatedate(sbrSbrcreatedate);
		setSbrsbrmodifydate(sbrSbrmodifydate);
		setSbrsbrremark(sbrSbrremark);
		setCococode(coCocode);
		setCocosname(coCosname);
		setChnchncode(chnChncode);
		setChnchnsname(chnChnsname);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setEeeecode(eeEecode);
		setEeeesname(eeEesname);
		setSssscode(ssSscode);
		setSsssname(ssSsname);
		setCkckcode(ckCkcode);
		setCkckname(ckCkname);
		setWowoid(woWoid);
	}

	public void setSbrsbrid(Long sbrSbrid) {
		this.setField(0, sbrSbrid);
	}

	public String getSbrsbrid() {
		return this.getField(0);
	}

	public void setSbrsbrlabelcode(String sbrSbrlabelcode) {
		this.setField(1, sbrSbrlabelcode);
	}

	public String getSbrsbrlabelcode() {
		return this.getField(1);
	}

	public void setSbrsbrtotal(BigDecimal sbrSbrtotal) {
		this.setField(2, sbrSbrtotal);
	}

	public String getSbrsbrtotal() {
		return this.getField(2);
	}

	public void setSbrsbroccurdate(Date sbrSbroccurdate) {
		this.setField(3, sbrSbroccurdate);
	}

	public String getSbrsbroccurdate() {
		return this.getField(3);
	}

	public void setSbrsbrcreatedate(Date sbrSbrcreatedate) {
		this.setField(4, sbrSbrcreatedate);
	}

	public String getSbrsbrcreatedate() {
		return this.getField(4);
	}

	public void setSbrsbrmodifydate(Date sbrSbrmodifydate) {
		this.setField(5, sbrSbrmodifydate);
	}

	public String getSbrsbrmodifydate() {
		return this.getField(5);
	}

	public void setSbrsbrremark(String sbrSbrremark) {
		this.setField(6, sbrSbrremark);
	}

	public String getSbrsbrremark() {
		return this.getField(6);
	}

	public void setCococode(String coCocode) {
		this.setField(7, coCocode);
	}

	public String getCococode() {
		return this.getField(7);
	}

	public void setCocosname(String coCosname) {
		this.setField(8, coCosname);
	}

	public String getCocosname() {
		return this.getField(8);
	}

	public void setChnchncode(String chnChncode) {
		this.setField(9, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(9);
	}

	public void setChnchnsname(String chnChnsname) {
		this.setField(10, chnChnsname);
	}

	public String getChnchnsname() {
		return this.getField(10);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(11, mopOpid);
	}

	public String getMopopid() {
		return this.getField(11);
	}

	public void setMopopname(String mopOpname) {
		this.setField(12, mopOpname);
	}

	public String getMopopname() {
		return this.getField(12);
	}

	public void setCopopid(Long copOpid) {
		this.setField(13, copOpid);
	}

	public String getCopopid() {
		return this.getField(13);
	}

	public void setCopopname(String copOpname) {
		this.setField(14, copOpname);
	}

	public String getCopopname() {
		return this.getField(14);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(15, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(15);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(16, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(16);
	}

	public void setSssscode(String ssSscode) {
		this.setField(17, ssSscode);
	}

	public String getSssscode() {
		return this.getField(17);
	}

	public void setSsssname(String ssSsname) {
		this.setField(18, ssSsname);
	}

	public String getSsssname() {
		return this.getField(18);
	}

	public void setCkckcode(String ckCkcode) {
		this.setField(19, ckCkcode);
	}

	public String getCkckcode() {
		return this.getField(19);
	}

	public void setCkckname(String ckCkname) {
		this.setField(20, ckCkname);
	}

	public String getCkckname() {
		return this.getField(20);
	}

	public void setWowoid(Long woWoid) {
		this.setField(21, woWoid);
	}

	public String getWowoid() {
		return this.getField(21);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
