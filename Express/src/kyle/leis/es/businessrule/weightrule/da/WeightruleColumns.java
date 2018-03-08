package kyle.leis.es.businessrule.weightrule.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class WeightruleColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WeightruleColumns() {
		m_astrColumns = new String[31];
	}
	
	public WeightruleColumns(Long wrBrid, 
            String brBrname, String brBrename, 
            Date brBrstartdate, Date brBrenddate, 
            Long copOpid, String copOpname, 
            Date brBrcreatedate, Long mopOpid, 
            String mopOpname, Date brBrmodifydate, 
            String brBrremark, String brkBrkcode, 
            String brkBrkname, String ssSscode, 
            String ssSsname, Long wrkWrkid, 
            String wrkWrkname, String utUtcode, 
            String utUtname, String pkPkcode, 
            String pkPksename, String pdPdcode, 
            String pdPdname, String swkSwkcode, 
            String swkSwkname, String swkSwkename, 
            String wrWrpelenghtformula, String wrWrpeweightformula, 
            BigDecimal wrWrpeactualweight, String tswkSwkcode){
		m_astrColumns = new String[31];
		setWrbrid(wrBrid);
		setBrbrname(brBrname);
		setBrbrename(brBrename);
		setBrbrstartdate(brBrstartdate);
		setBrbrenddate(brBrenddate);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setBrbrcreatedate(brBrcreatedate);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setBrbrmodifydate(brBrmodifydate);
		setBrbrremark(brBrremark);
		setBrkbrkcode(brkBrkcode);
		setBrkbrkname(brkBrkname);
		setSssscode(ssSscode);
		setSsssname(ssSsname);
		setWrkwrkid(wrkWrkid);
		setWrkwrkname(wrkWrkname);
		setUtutcode(utUtcode);
		setUtutname(utUtname);
		setPkpkcode(pkPkcode);
		setPkpksename(pkPksename);
		setPdpdcode(pdPdcode);
		setPdpdname(pdPdname);
		setSwkswkcode(swkSwkcode);
		setSwkswkname(swkSwkname);
		setSwkswkename(swkSwkename);
		setWrwrpelenghtformula(wrWrpelenghtformula);
		setWrwrpeweightformula(wrWrpeweightformula);
		setWrwrpeactualweight(wrWrpeactualweight);
		setTswkswkcode(tswkSwkcode);
	}

	public void setWrbrid(Long wrBrid) {
		this.setField(0, wrBrid);
	}

	public String getWrbrid() {
		return this.getField(0);
	}

	public void setBrbrname(String brBrname) {
		this.setField(1, brBrname);
	}

	public String getBrbrname() {
		return this.getField(1);
	}

	public void setBrbrename(String brBrename) {
		this.setField(2, brBrename);
	}

	public String getBrbrename() {
		return this.getField(2);
	}

	public void setBrbrstartdate(Date brBrstartdate) {
		this.setField(3, brBrstartdate);
	}

	public String getBrbrstartdate() {
		return this.getField(3);
	}

	public void setBrbrenddate(Date brBrenddate) {
		this.setField(4, brBrenddate);
	}

	public String getBrbrenddate() {
		return this.getField(4);
	}

	public void setCopopid(Long copOpid) {
		this.setField(5, copOpid);
	}

	public String getCopopid() {
		return this.getField(5);
	}

	public void setCopopname(String copOpname) {
		this.setField(6, copOpname);
	}

	public String getCopopname() {
		return this.getField(6);
	}

	public void setBrbrcreatedate(Date brBrcreatedate) {
		this.setField(7, brBrcreatedate);
	}

	public String getBrbrcreatedate() {
		return this.getField(7);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(8, mopOpid);
	}

	public String getMopopid() {
		return this.getField(8);
	}

	public void setMopopname(String mopOpname) {
		this.setField(9, mopOpname);
	}

	public String getMopopname() {
		return this.getField(9);
	}

	public void setBrbrmodifydate(Date brBrmodifydate) {
		this.setField(10, brBrmodifydate);
	}

	public String getBrbrmodifydate() {
		return this.getField(10);
	}

	public void setBrbrremark(String brBrremark) {
		this.setField(11, brBrremark);
	}

	public String getBrbrremark() {
		return this.getField(11);
	}

	public void setBrkbrkcode(String brkBrkcode) {
		this.setField(12, brkBrkcode);
	}

	public String getBrkbrkcode() {
		return this.getField(12);
	}

	public void setBrkbrkname(String brkBrkname) {
		this.setField(13, brkBrkname);
	}

	public String getBrkbrkname() {
		return this.getField(13);
	}

	public void setSssscode(String ssSscode) {
		this.setField(14, ssSscode);
	}

	public String getSssscode() {
		return this.getField(14);
	}

	public void setSsssname(String ssSsname) {
		this.setField(15, ssSsname);
	}

	public String getSsssname() {
		return this.getField(15);
	}

	public void setWrkwrkid(Long wrkWrkid) {
		this.setField(16, wrkWrkid);
	}

	public String getWrkwrkid() {
		return this.getField(16);
	}

	public void setWrkwrkname(String wrkWrkname) {
		this.setField(17, wrkWrkname);
	}

	public String getWrkwrkname() {
		return this.getField(17);
	}

	public void setUtutcode(String utUtcode) {
		this.setField(18, utUtcode);
	}

	public String getUtutcode() {
		return this.getField(18);
	}

	public void setUtutname(String utUtname) {
		this.setField(19, utUtname);
	}

	public String getUtutname() {
		return this.getField(19);
	}

	public void setPkpkcode(String pkPkcode) {
		this.setField(20, pkPkcode);
	}

	public String getPkpkcode() {
		return this.getField(20);
	}

	public void setPkpksename(String pkPksename) {
		this.setField(21, pkPksename);
	}

	public String getPkpksename() {
		return this.getField(21);
	}

	public void setPdpdcode(String pdPdcode) {
		this.setField(22, pdPdcode);
	}

	public String getPdpdcode() {
		return this.getField(22);
	}

	public void setPdpdname(String pdPdname) {
		this.setField(23, pdPdname);
	}

	public String getPdpdname() {
		return this.getField(23);
	}

	public void setSwkswkcode(String swkSwkcode) {
		this.setField(24, swkSwkcode);
	}

	public String getSwkswkcode() {
		return this.getField(24);
	}

	public void setSwkswkname(String swkSwkname) {
		this.setField(25, swkSwkname);
	}

	public String getSwkswkname() {
		return this.getField(25);
	}

	public void setSwkswkename(String swkSwkename) {
		this.setField(26, swkSwkename);
	}

	public String getSwkswkename() {
		return this.getField(26);
	}

	public void setWrwrpelenghtformula(String wrWrpelenghtformula) {
		this.setField(27, wrWrpelenghtformula);
	}

	public String getWrwrpelenghtformula() {
		return this.getField(27);
	}

	public void setWrwrpeweightformula(String wrWrpeweightformula) {
		this.setField(28, wrWrpeweightformula);
	}

	public String getWrwrpeweightformula() {
		return this.getField(28);
	}

	public void setWrwrpeactualweight(BigDecimal wrWrpeactualweight) {
		this.setField(29, wrWrpeactualweight);
	}

	public String getWrwrpeactualweight() {
		return this.getField(29);
	}

	public void setTswkswkcode(String tswkSwkcode) {
		this.setField(30, tswkSwkcode);
	}

	public String getTswkswkcode() {
		return this.getField(30);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
