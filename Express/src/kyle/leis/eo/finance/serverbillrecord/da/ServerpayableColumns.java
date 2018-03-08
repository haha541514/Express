package kyle.leis.eo.finance.serverbillrecord.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class ServerpayableColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ServerpayableColumns() {
		m_astrColumns = new String[21];
	}
	
	public ServerpayableColumns(Long spySpyid, 
            String spySpyremark, String fkFkcode, 
            String fkFkname, BigDecimal spySpytotalcharge, 
            String ckCkcode, String ckCkname, 
            BigDecimal spySpycurrencyrate, Date spySpyoccurdate, 
            Long sbrSbrid, String sbrSbrlabelcode, 
            Long swbSwbcode, String swbSwbserverewbcode, 
            String swbSwbcustomerewbcode, int swbSwbpieces, 
            BigDecimal swbSwbchargeweight, Long swbSwbreferencecode, 
            String chnChncode, String chnChnsname, 
            String coCocode, String coCosname){
		m_astrColumns = new String[21];
		setSpyspyid(spySpyid);
		setSpyspyremark(spySpyremark);
		setFkfkcode(fkFkcode);
		setFkfkname(fkFkname);
		setSpyspytotalcharge(spySpytotalcharge);
		setCkckcode(ckCkcode);
		setCkckname(ckCkname);
		setSpyspycurrencyrate(spySpycurrencyrate);
		setSpyspyoccurdate(spySpyoccurdate);
		setSbrsbrid(sbrSbrid);
		setSbrsbrlabelcode(sbrSbrlabelcode);
		setSwbswbcode(swbSwbcode);
		setSwbswbserverewbcode(swbSwbserverewbcode);
		setSwbswbcustomerewbcode(swbSwbcustomerewbcode);
		setSwbswbpieces(swbSwbpieces);
		setSwbswbchargeweight(swbSwbchargeweight);
		setSwbswbreferencecode(swbSwbreferencecode);
		setChnchncode(chnChncode);
		setChnchnsname(chnChnsname);
		setCococode(coCocode);
		setCocosname(coCosname);
	}

	public void setSpyspyid(Long spySpyid) {
		this.setField(0, spySpyid);
	}

	public String getSpyspyid() {
		return this.getField(0);
	}

	public void setSpyspyremark(String spySpyremark) {
		this.setField(1, spySpyremark);
	}

	public String getSpyspyremark() {
		return this.getField(1);
	}

	public void setFkfkcode(String fkFkcode) {
		this.setField(2, fkFkcode);
	}

	public String getFkfkcode() {
		return this.getField(2);
	}

	public void setFkfkname(String fkFkname) {
		this.setField(3, fkFkname);
	}

	public String getFkfkname() {
		return this.getField(3);
	}

	public void setSpyspytotalcharge(BigDecimal spySpytotalcharge) {
		this.setField(4, spySpytotalcharge);
	}

	public String getSpyspytotalcharge() {
		return this.getField(4);
	}

	public void setCkckcode(String ckCkcode) {
		this.setField(5, ckCkcode);
	}

	public String getCkckcode() {
		return this.getField(5);
	}

	public void setCkckname(String ckCkname) {
		this.setField(6, ckCkname);
	}

	public String getCkckname() {
		return this.getField(6);
	}

	public void setSpyspycurrencyrate(BigDecimal spySpycurrencyrate) {
		this.setField(7, spySpycurrencyrate);
	}

	public String getSpyspycurrencyrate() {
		return this.getField(7);
	}

	public void setSpyspyoccurdate(Date spySpyoccurdate) {
		this.setField(8, spySpyoccurdate);
	}

	public String getSpyspyoccurdate() {
		return this.getField(8);
	}

	public void setSbrsbrid(Long sbrSbrid) {
		this.setField(9, sbrSbrid);
	}

	public String getSbrsbrid() {
		return this.getField(9);
	}

	public void setSbrsbrlabelcode(String sbrSbrlabelcode) {
		this.setField(10, sbrSbrlabelcode);
	}

	public String getSbrsbrlabelcode() {
		return this.getField(10);
	}

	public void setSwbswbcode(Long swbSwbcode) {
		this.setField(11, swbSwbcode);
	}

	public String getSwbswbcode() {
		return this.getField(11);
	}

	public void setSwbswbserverewbcode(String swbSwbserverewbcode) {
		this.setField(12, swbSwbserverewbcode);
	}

	public String getSwbswbserverewbcode() {
		return this.getField(12);
	}

	public void setSwbswbcustomerewbcode(String swbSwbcustomerewbcode) {
		this.setField(13, swbSwbcustomerewbcode);
	}

	public String getSwbswbcustomerewbcode() {
		return this.getField(13);
	}

	public void setSwbswbpieces(int swbSwbpieces) {
		this.setField(14, swbSwbpieces);
	}

	public String getSwbswbpieces() {
		return this.getField(14);
	}

	public void setSwbswbchargeweight(BigDecimal swbSwbchargeweight) {
		this.setField(15, swbSwbchargeweight);
	}

	public String getSwbswbchargeweight() {
		return this.getField(15);
	}

	public void setSwbswbreferencecode(Long swbSwbreferencecode) {
		this.setField(16, swbSwbreferencecode);
	}

	public String getSwbswbreferencecode() {
		return this.getField(16);
	}

	public void setChnchncode(String chnChncode) {
		this.setField(17, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(17);
	}

	public void setChnchnsname(String chnChnsname) {
		this.setField(18, chnChnsname);
	}

	public String getChnchnsname() {
		return this.getField(18);
	}

	public void setCococode(String coCocode) {
		this.setField(19, coCocode);
	}

	public String getCococode() {
		return this.getField(19);
	}

	public void setCocosname(String coCosname) {
		this.setField(20, coCosname);
	}

	public String getCocosname() {
		return this.getField(20);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
