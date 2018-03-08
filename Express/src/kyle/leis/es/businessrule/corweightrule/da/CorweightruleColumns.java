package kyle.leis.es.businessrule.corweightrule.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class CorweightruleColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CorweightruleColumns() {
		
	}
	
	public CorweightruleColumns(Long cwrBrid, 
            String brBrname, String brBrename, 
            Date brBrstartdate, Date brBrenddate, 
            Long copOpid, String copOpname, 
            Date brBrcreatedate, Long mopOpid, 
            String mopOpname, Date brBrmodifydate, 
            String brBrremark, String brkBrkcode, 
            String brkBrkname, String ssSscode, 
            String ssSsname, Long wrkWrkid, 
            String wrkWrkname, String pdPdcode, 
            String pdPdname, String chnChncode, 
            String chnChnname, String chnChnsname, 
            String chnChnsename, String coCocode, 
            String coConame, String coCosname, 
            String coCosename, String coColabelcode){
		m_astrColumns = new String[29];
		setCwrbrid(cwrBrid);
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
		setPdpdcode(pdPdcode);
		setPdpdname(pdPdname);
		setChnchncode(chnChncode);
		setChnchnname(chnChnname);
		setChnchnsname(chnChnsname);
		setChnchnsename(chnChnsename);
		setCococode(coCocode);
		setCoconame(coConame);
		setCocosname(coCosname);
		setCocosename(coCosename);
		setCocolabelcode(coColabelcode);
	}

	public void setCwrbrid(Long cwrBrid) {
		this.setField(0, cwrBrid);
	}

	public String getCwrbrid() {
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

	public void setPdpdcode(String pdPdcode) {
		this.setField(18, pdPdcode);
	}

	public String getPdpdcode() {
		return this.getField(18);
	}

	public void setPdpdname(String pdPdname) {
		this.setField(19, pdPdname);
	}

	public String getPdpdname() {
		return this.getField(19);
	}

	public void setChnchncode(String chnChncode) {
		this.setField(20, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(20);
	}

	public void setChnchnname(String chnChnname) {
		this.setField(21, chnChnname);
	}

	public String getChnchnname() {
		return this.getField(21);
	}

	public void setChnchnsname(String chnChnsname) {
		this.setField(22, chnChnsname);
	}

	public String getChnchnsname() {
		return this.getField(22);
	}

	public void setChnchnsename(String chnChnsename) {
		this.setField(23, chnChnsename);
	}

	public String getChnchnsename() {
		return this.getField(23);
	}

	public void setCococode(String coCocode) {
		this.setField(24, coCocode);
	}

	public String getCococode() {
		return this.getField(24);
	}

	public void setCoconame(String coConame) {
		this.setField(25, coConame);
	}

	public String getCoconame() {
		return this.getField(25);
	}

	public void setCocosname(String coCosname) {
		this.setField(26, coCosname);
	}

	public String getCocosname() {
		return this.getField(26);
	}

	public void setCocosename(String coCosename) {
		this.setField(27, coCosename);
	}

	public String getCocosename() {
		return this.getField(27);
	}

	public void setCocolabelcode(String coColabelcode) {
		this.setField(28, coColabelcode);
	}

	public String getCocolabelcode() {
		return this.getField(28);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
