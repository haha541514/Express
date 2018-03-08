package kyle.leis.fs.waybillcode.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillcodeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillcodeColumns() {
		
	}
	
	public WaybillcodeColumns(Long bcBcid, 
            String bcBcstartcode, String bcBcendcode, 
            String bcBcprefix, String bcBcsuffix, 
            Date bcBccreatedate, Date bcBcmodifydate, 
            String bcBccurrentlabelcode, String bcBcremark, 
            String bckBckcode, String bckBckname, 
            String bckBckgroupcode, String bckBckbuildvaluesign, 
            Long copOpid, String copOpname, 
            Long mopOpid, String mopOpname, 
            String bcsCscode, String bcsCsname){
		m_astrColumns = new String[19];
		setBcbcid(bcBcid);
		setBcbcstartcode(bcBcstartcode);
		setBcbcendcode(bcBcendcode);
		setBcbcprefix(bcBcprefix);
		setBcbcsuffix(bcBcsuffix);
		setBcbccreatedate(bcBccreatedate);
		setBcbcmodifydate(bcBcmodifydate);
		setBcbccurrentlabelcode(bcBccurrentlabelcode);
		setBcbcremark(bcBcremark);
		setBckbckcode(bckBckcode);
		setBckbckname(bckBckname);
		setBckbckgroupcode(bckBckgroupcode);
		setBckbckbuildvaluesign(bckBckbuildvaluesign);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setBcscscode(bcsCscode);
		setBcscsname(bcsCsname);
	}

	public void setBcbcid(Long bcBcid) {
		this.setField(0, bcBcid);
	}

	public String getBcbcid() {
		return this.getField(0);
	}

	public void setBcbcstartcode(String bcBcstartcode) {
		this.setField(1, bcBcstartcode);
	}

	public String getBcbcstartcode() {
		return this.getField(1);
	}

	public void setBcbcendcode(String bcBcendcode) {
		this.setField(2, bcBcendcode);
	}

	public String getBcbcendcode() {
		return this.getField(2);
	}

	public void setBcbcprefix(String bcBcprefix) {
		this.setField(3, bcBcprefix);
	}

	public String getBcbcprefix() {
		return this.getField(3);
	}

	public void setBcbcsuffix(String bcBcsuffix) {
		this.setField(4, bcBcsuffix);
	}

	public String getBcbcsuffix() {
		return this.getField(4);
	}

	public void setBcbccreatedate(Date bcBccreatedate) {
		this.setField(5, bcBccreatedate);
	}

	public String getBcbccreatedate() {
		return this.getField(5);
	}

	public void setBcbcmodifydate(Date bcBcmodifydate) {
		this.setField(6, bcBcmodifydate);
	}

	public String getBcbcmodifydate() {
		return this.getField(6);
	}

	public void setBcbccurrentlabelcode(String bcBccurrentlabelcode) {
		this.setField(7, bcBccurrentlabelcode);
	}

	public String getBcbccurrentlabelcode() {
		return this.getField(7);
	}

	public void setBcbcremark(String bcBcremark) {
		this.setField(8, bcBcremark);
	}

	public String getBcbcremark() {
		return this.getField(8);
	}

	public void setBckbckcode(String bckBckcode) {
		this.setField(9, bckBckcode);
	}

	public String getBckbckcode() {
		return this.getField(9);
	}

	public void setBckbckname(String bckBckname) {
		this.setField(10, bckBckname);
	}

	public String getBckbckname() {
		return this.getField(10);
	}

	public void setBckbckgroupcode(String bckBckgroupcode) {
		this.setField(11, bckBckgroupcode);
	}

	public String getBckbckgroupcode() {
		return this.getField(11);
	}

	public void setBckbckbuildvaluesign(String bckBckbuildvaluesign) {
		this.setField(12, bckBckbuildvaluesign);
	}

	public String getBckbckbuildvaluesign() {
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

	public void setBcscscode(String bcsCscode) {
		this.setField(17, bcsCscode);
	}

	public String getBcscscode() {
		return this.getField(17);
	}

	public void setBcscsname(String bcsCsname) {
		this.setField(18, bcsCsname);
	}

	public String getBcscsname() {
		return this.getField(18);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
