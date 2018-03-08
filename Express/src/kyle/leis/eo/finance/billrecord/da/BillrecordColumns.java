package kyle.leis.eo.finance.billrecord.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class BillrecordColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public BillrecordColumns() {
		m_astrColumns = new String[36];
	}
	
	public BillrecordColumns(Long brBrid, 
            Date brBrcreatedate, Date brBrmodifydate, 
            Date brBrauditdate, Date brBroccurdate, 
            BigDecimal brBrtotal, String brBrlablecode, 
            String brBrremark, int brBrdownloadtimes, 
            int brBrprinttimes, String ckCkcode, 
            String ckCkname, String chnChncode, 
            String chnChnname, String chnChnsname, 
            Long mopOpid, String mopOpname, 
            Long copOpid, String copOpname, 
            Long aopOpid, String aopOpname, 
            String eeEecode, String eeEename, 
            String eeEesname, String eeEeesname, 
            String coCocode, String coConame, 
            String coCosname, String coCosename, 
            String brsBrscode, String brsBrsname, 
            String bkBkcode, String bkBkname, 
            Long sbrSbrid, String sbrSbrlabelcode, 
            BigDecimal brBragencyfeetotal){
		m_astrColumns = new String[36];
		setBrbrid(brBrid);
		setBrbrcreatedate(brBrcreatedate);
		setBrbrmodifydate(brBrmodifydate);
		setBrbrauditdate(brBrauditdate);
		setBrbroccurdate(brBroccurdate);
		setBrbrtotal(brBrtotal);
		setBrbrlablecode(brBrlablecode);
		setBrbrremark(brBrremark);
		setBrbrdownloadtimes(brBrdownloadtimes);
		setBrbrprinttimes(brBrprinttimes);
		setCkckcode(ckCkcode);
		setCkckname(ckCkname);
		setChnchncode(chnChncode);
		setChnchnname(chnChnname);
		setChnchnsname(chnChnsname);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setAopopid(aopOpid);
		setAopopname(aopOpname);
		setEeeecode(eeEecode);
		setEeeename(eeEename);
		setEeeesname(eeEesname);
		setEeeeesname(eeEeesname);
		setCococode(coCocode);
		setCoconame(coConame);
		setCocosname(coCosname);
		setCocosename(coCosename);
		setBrsbrscode(brsBrscode);
		setBrsbrsname(brsBrsname);
		setBkbkcode(bkBkcode);
		setBkbkname(bkBkname);
		setSbrsbrid(sbrSbrid);
		setSbrsbrlabelcode(sbrSbrlabelcode);
		setBrbragencyfeetotal(brBragencyfeetotal);
	}

	public void setBrbrid(Long brBrid) {
		this.setField(0, brBrid);
	}

	public String getBrbrid() {
		return this.getField(0);
	}

	public void setBrbrcreatedate(Date brBrcreatedate) {
		this.setField(1, brBrcreatedate);
	}

	public String getBrbrcreatedate() {
		return this.getField(1);
	}

	public void setBrbrmodifydate(Date brBrmodifydate) {
		this.setField(2, brBrmodifydate);
	}

	public String getBrbrmodifydate() {
		return this.getField(2);
	}

	public void setBrbrauditdate(Date brBrauditdate) {
		this.setField(3, brBrauditdate);
	}

	public String getBrbrauditdate() {
		return this.getField(3);
	}

	public void setBrbroccurdate(Date brBroccurdate) {
		this.setField(4, brBroccurdate);
	}

	public String getBrbroccurdate() {
		return this.getField(4);
	}

	public void setBrbrtotal(BigDecimal brBrtotal) {
		this.setField(5, brBrtotal);
	}

	public String getBrbrtotal() {
		return this.getField(5);
	}

	public void setBrbrlablecode(String brBrlablecode) {
		this.setField(6, brBrlablecode);
	}

	public String getBrbrlablecode() {
		return this.getField(6);
	}

	public void setBrbrremark(String brBrremark) {
		this.setField(7, brBrremark);
	}

	public String getBrbrremark() {
		return this.getField(7);
	}

	public void setBrbrdownloadtimes(int brBrdownloadtimes) {
		this.setField(8, brBrdownloadtimes);
	}

	public String getBrbrdownloadtimes() {
		return this.getField(8);
	}

	public void setBrbrprinttimes(int brBrprinttimes) {
		this.setField(9, brBrprinttimes);
	}

	public String getBrbrprinttimes() {
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

	public void setChnchncode(String chnChncode) {
		this.setField(12, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(12);
	}

	public void setChnchnname(String chnChnname) {
		this.setField(13, chnChnname);
	}

	public String getChnchnname() {
		return this.getField(13);
	}

	public void setChnchnsname(String chnChnsname) {
		this.setField(14, chnChnsname);
	}

	public String getChnchnsname() {
		return this.getField(14);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(15, mopOpid);
	}

	public String getMopopid() {
		return this.getField(15);
	}

	public void setMopopname(String mopOpname) {
		this.setField(16, mopOpname);
	}

	public String getMopopname() {
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

	public void setEeeecode(String eeEecode) {
		this.setField(21, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(21);
	}

	public void setEeeename(String eeEename) {
		this.setField(22, eeEename);
	}

	public String getEeeename() {
		return this.getField(22);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(23, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(23);
	}

	public void setEeeeesname(String eeEeesname) {
		this.setField(24, eeEeesname);
	}

	public String getEeeeesname() {
		return this.getField(24);
	}

	public void setCococode(String coCocode) {
		this.setField(25, coCocode);
	}

	public String getCococode() {
		return this.getField(25);
	}

	public void setCoconame(String coConame) {
		this.setField(26, coConame);
	}

	public String getCoconame() {
		return this.getField(26);
	}

	public void setCocosname(String coCosname) {
		this.setField(27, coCosname);
	}

	public String getCocosname() {
		return this.getField(27);
	}

	public void setCocosename(String coCosename) {
		this.setField(28, coCosename);
	}

	public String getCocosename() {
		return this.getField(28);
	}

	public void setBrsbrscode(String brsBrscode) {
		this.setField(29, brsBrscode);
	}

	public String getBrsbrscode() {
		return this.getField(29);
	}

	public void setBrsbrsname(String brsBrsname) {
		this.setField(30, brsBrsname);
	}

	public String getBrsbrsname() {
		return this.getField(30);
	}

	public void setBkbkcode(String bkBkcode) {
		this.setField(31, bkBkcode);
	}

	public String getBkbkcode() {
		return this.getField(31);
	}

	public void setBkbkname(String bkBkname) {
		this.setField(32, bkBkname);
	}

	public String getBkbkname() {
		return this.getField(32);
	}

	public void setSbrsbrid(Long sbrSbrid) {
		this.setField(33, sbrSbrid);
	}

	public String getSbrsbrid() {
		return this.getField(33);
	}

	public void setSbrsbrlabelcode(String sbrSbrlabelcode) {
		this.setField(34, sbrSbrlabelcode);
	}

	public String getSbrsbrlabelcode() {
		return this.getField(34);
	}

	public void setBrbragencyfeetotal(BigDecimal brBragencyfeetotal) {
		this.setField(35, brBragencyfeetotal);
	}

	public String getBrbragencyfeetotal() {
		return this.getField(35);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
