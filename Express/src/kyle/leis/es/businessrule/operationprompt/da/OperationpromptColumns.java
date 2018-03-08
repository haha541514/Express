package kyle.leis.es.businessrule.operationprompt.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class OperationpromptColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public OperationpromptColumns() {
		m_astrColumns = new String[39];
	}
	
	public OperationpromptColumns(Long optBrid, 
            String optPtcode, String optOptname, 
            String optOptuniversalcustomersign, String optOptuniversalservesign, 
            String optOptuniversalachannelsign, String optOptuniversalschannelsign, 
            String optOptuniversaldeparturesign, String optOptuniversaldestinationsign, 
            String optOptuniversaltachesign, String optOptvwgwformular, 
            BigDecimal optOptchargeweightbegin, BigDecimal optOptchargeweightend, 
            BigDecimal optOptpiecegrossweightbegin, BigDecimal optOptpiecegrossweightend, 
            BigDecimal optOptdeclarevaluebegin, BigDecimal optOptdeclarevalueend, 
            String optOptcontent, String brBrname, 
            String brBrename, Date brBrstartdate, 
            Date brBrenddate, Long copOpid, 
            String copOpname, Date brBrcreatedate, 
            Long mopOpid, String mopOpname, 
            Date brBrmodifydate, String brBrremark, 
            String brkBrkcode, String brkBrkname, 
            String ssSscode, String ssSsname, 
            String ctCtcode, String ctCtname, 
            String pmPmcode, String pmPmname, 
            String pkPkcode, String pkPkname){
		m_astrColumns = new String[39];
		setOptbrid(optBrid);
		setOptptcode(optPtcode);
		setOptoptname(optOptname);
		setOptoptuniversalcustomersign(optOptuniversalcustomersign);
		setOptoptuniversalservesign(optOptuniversalservesign);
		setOptoptuniversalachannelsign(optOptuniversalachannelsign);
		setOptoptuniversalschannelsign(optOptuniversalschannelsign);
		setOptoptuniversaldeparturesign(optOptuniversaldeparturesign);
		setOptoptuniversaldestinationsign(optOptuniversaldestinationsign);
		setOptoptuniversaltachesign(optOptuniversaltachesign);
		setOptoptvwgwformular(optOptvwgwformular);
		setOptoptchargeweightbegin(optOptchargeweightbegin);
		setOptoptchargeweightend(optOptchargeweightend);
		setOptoptpiecegrossweightbegin(optOptpiecegrossweightbegin);
		setOptoptpiecegrossweightend(optOptpiecegrossweightend);
		setOptoptdeclarevaluebegin(optOptdeclarevaluebegin);
		setOptoptdeclarevalueend(optOptdeclarevalueend);
		setOptoptcontent(optOptcontent);
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
		setCtctcode(ctCtcode);
		setCtctname(ctCtname);
		setPmpmcode(pmPmcode);
		setPmpmname(pmPmname);
		setPkpkcode(pkPkcode);
		setPkpkname(pkPkname);
	}

	public void setOptbrid(Long optBrid) {
		this.setField(0, optBrid);
	}

	public String getOptbrid() {
		return this.getField(0);
	}

	public void setOptptcode(String optPtcode) {
		this.setField(1, optPtcode);
	}

	public String getOptptcode() {
		return this.getField(1);
	}

	public void setOptoptname(String optOptname) {
		this.setField(2, optOptname);
	}

	public String getOptoptname() {
		return this.getField(2);
	}

	public void setOptoptuniversalcustomersign(String optOptuniversalcustomersign) {
		this.setField(3, optOptuniversalcustomersign);
	}

	public String getOptoptuniversalcustomersign() {
		return this.getField(3);
	}

	public void setOptoptuniversalservesign(String optOptuniversalservesign) {
		this.setField(4, optOptuniversalservesign);
	}

	public String getOptoptuniversalservesign() {
		return this.getField(4);
	}

	public void setOptoptuniversalachannelsign(String optOptuniversalachannelsign) {
		this.setField(5, optOptuniversalachannelsign);
	}

	public String getOptoptuniversalachannelsign() {
		return this.getField(5);
	}

	public void setOptoptuniversalschannelsign(String optOptuniversalschannelsign) {
		this.setField(6, optOptuniversalschannelsign);
	}

	public String getOptoptuniversalschannelsign() {
		return this.getField(6);
	}

	public void setOptoptuniversaldeparturesign(String optOptuniversaldeparturesign) {
		this.setField(7, optOptuniversaldeparturesign);
	}

	public String getOptoptuniversaldeparturesign() {
		return this.getField(7);
	}

	public void setOptoptuniversaldestinationsign(String optOptuniversaldestinationsign) {
		this.setField(8, optOptuniversaldestinationsign);
	}

	public String getOptoptuniversaldestinationsign() {
		return this.getField(8);
	}

	public void setOptoptuniversaltachesign(String optOptuniversaltachesign) {
		this.setField(9, optOptuniversaltachesign);
	}

	public String getOptoptuniversaltachesign() {
		return this.getField(9);
	}

	public void setOptoptvwgwformular(String optOptvwgwformular) {
		this.setField(10, optOptvwgwformular);
	}

	public String getOptoptvwgwformular() {
		return this.getField(10);
	}

	public void setOptoptchargeweightbegin(BigDecimal optOptchargeweightbegin) {
		this.setField(11, optOptchargeweightbegin);
	}

	public String getOptoptchargeweightbegin() {
		return this.getField(11);
	}

	public void setOptoptchargeweightend(BigDecimal optOptchargeweightend) {
		this.setField(12, optOptchargeweightend);
	}

	public String getOptoptchargeweightend() {
		return this.getField(12);
	}

	public void setOptoptpiecegrossweightbegin(BigDecimal optOptpiecegrossweightbegin) {
		this.setField(13, optOptpiecegrossweightbegin);
	}

	public String getOptoptpiecegrossweightbegin() {
		return this.getField(13);
	}

	public void setOptoptpiecegrossweightend(BigDecimal optOptpiecegrossweightend) {
		this.setField(14, optOptpiecegrossweightend);
	}

	public String getOptoptpiecegrossweightend() {
		return this.getField(14);
	}

	public void setOptoptdeclarevaluebegin(BigDecimal optOptdeclarevaluebegin) {
		this.setField(15, optOptdeclarevaluebegin);
	}

	public String getOptoptdeclarevaluebegin() {
		return this.getField(15);
	}

	public void setOptoptdeclarevalueend(BigDecimal optOptdeclarevalueend) {
		this.setField(16, optOptdeclarevalueend);
	}

	public String getOptoptdeclarevalueend() {
		return this.getField(16);
	}

	public void setOptoptcontent(String optOptcontent) {
		this.setField(17, optOptcontent);
	}

	public String getOptoptcontent() {
		return this.getField(17);
	}

	public void setBrbrname(String brBrname) {
		this.setField(18, brBrname);
	}

	public String getBrbrname() {
		return this.getField(18);
	}

	public void setBrbrename(String brBrename) {
		this.setField(19, brBrename);
	}

	public String getBrbrename() {
		return this.getField(19);
	}

	public void setBrbrstartdate(Date brBrstartdate) {
		this.setField(20, brBrstartdate);
	}

	public String getBrbrstartdate() {
		return this.getField(20);
	}

	public void setBrbrenddate(Date brBrenddate) {
		this.setField(21, brBrenddate);
	}

	public String getBrbrenddate() {
		return this.getField(21);
	}

	public void setCopopid(Long copOpid) {
		this.setField(22, copOpid);
	}

	public String getCopopid() {
		return this.getField(22);
	}

	public void setCopopname(String copOpname) {
		this.setField(23, copOpname);
	}

	public String getCopopname() {
		return this.getField(23);
	}

	public void setBrbrcreatedate(Date brBrcreatedate) {
		this.setField(24, brBrcreatedate);
	}

	public String getBrbrcreatedate() {
		return this.getField(24);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(25, mopOpid);
	}

	public String getMopopid() {
		return this.getField(25);
	}

	public void setMopopname(String mopOpname) {
		this.setField(26, mopOpname);
	}

	public String getMopopname() {
		return this.getField(26);
	}

	public void setBrbrmodifydate(Date brBrmodifydate) {
		this.setField(27, brBrmodifydate);
	}

	public String getBrbrmodifydate() {
		return this.getField(27);
	}

	public void setBrbrremark(String brBrremark) {
		this.setField(28, brBrremark);
	}

	public String getBrbrremark() {
		return this.getField(28);
	}

	public void setBrkbrkcode(String brkBrkcode) {
		this.setField(29, brkBrkcode);
	}

	public String getBrkbrkcode() {
		return this.getField(29);
	}

	public void setBrkbrkname(String brkBrkname) {
		this.setField(30, brkBrkname);
	}

	public String getBrkbrkname() {
		return this.getField(30);
	}

	public void setSssscode(String ssSscode) {
		this.setField(31, ssSscode);
	}

	public String getSssscode() {
		return this.getField(31);
	}

	public void setSsssname(String ssSsname) {
		this.setField(32, ssSsname);
	}

	public String getSsssname() {
		return this.getField(32);
	}

	public void setCtctcode(String ctCtcode) {
		this.setField(33, ctCtcode);
	}

	public String getCtctcode() {
		return this.getField(33);
	}

	public void setCtctname(String ctCtname) {
		this.setField(34, ctCtname);
	}

	public String getCtctname() {
		return this.getField(34);
	}

	public void setPmpmcode(String pmPmcode) {
		this.setField(35, pmPmcode);
	}

	public String getPmpmcode() {
		return this.getField(35);
	}

	public void setPmpmname(String pmPmname) {
		this.setField(36, pmPmname);
	}

	public String getPmpmname() {
		return this.getField(36);
	}

	public void setPkpkcode(String pkPkcode) {
		this.setField(37, pkPkcode);
	}

	public String getPkpkcode() {
		return this.getField(37);
	}

	public void setPkpkname(String pkPkname) {
		this.setField(38, pkPkname);
	}

	public String getPkpkname() {
		return this.getField(38);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
