package kyle.leis.es.company.channel.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class ChannelColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ChannelColumns() {
		m_astrColumns = new String[50];
	}
	
	public ChannelColumns(String chnChncode, 
            Date chnChncreatedate, Date chnChnmodifydate, 
            String chnCstcode, String chnChnname, 
            String chnChnename, String chnChnsname, 
            String chnChnsename, String chnChnmanifestseriesnumber, 
            String Eraccount, String chnChnpaymentaccount, 
            String csCscode, String csCsname, 
            Long mopOpid, String mopOpname, 
            Long copOpid, String copOpname, 
            String eeEecode, String eeEesname, 
            String eeEeesname, String coCocode, 
            String coCosname, String coCosename, 
            BigDecimal chnChnmaxdtransferweight, String chnChnwpxspsmappingname, 
            String sotSotcode, String sotSotname, 
            String ssgSsgcode, String ssgSsgname, 
            String ssgSsgename, String sbckBckcode, 
            String sbckBckname, String sbckBckename, 
            String mbckBckcode, String mbckBckname, 
            String mbckBckename, String lfLfcode, 
            String lfLfname, String clfClfcode, 
            String clfClfname, BigDecimal chnChnmaxdtransfercharge, 
            BigDecimal chnChnmaxmtransfercharge, String ckCkcode, 
            String ckCkname, String estEstcode, 
            String estEstname, String chnChnlabelprintprefix, 
            String chnChnsobypiecessign, BigDecimal chnChnmaxoverdeclarevalue, 
            String chnChnchawbprefix){
		m_astrColumns = new String[50];
		setChnchncode(chnChncode);
		setChnchncreatedate(chnChncreatedate);
		setChnchnmodifydate(chnChnmodifydate);
		setChncstcode(chnCstcode);
		setChnchnname(chnChnname);
		setChnchnename(chnChnename);
		setChnchnsname(chnChnsname);
		setChnchnsename(chnChnsename);
		setChnchnmanifestseriesnumber(chnChnmanifestseriesnumber);
		setEraccount(Eraccount);
		setChnchnpaymentaccount(chnChnpaymentaccount);
		setCscscode(csCscode);
		setCscsname(csCsname);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setEeeecode(eeEecode);
		setEeeesname(eeEesname);
		setEeeeesname(eeEeesname);
		setCococode(coCocode);
		setCocosname(coCosname);
		setCocosename(coCosename);
		setChnchnmaxdtransferweight(chnChnmaxdtransferweight);
		setChnchnwpxspsmappingname(chnChnwpxspsmappingname);
		setSotsotcode(sotSotcode);
		setSotsotname(sotSotname);
		setSsgssgcode(ssgSsgcode);
		setSsgssgname(ssgSsgname);
		setSsgssgename(ssgSsgename);
		setSbckbckcode(sbckBckcode);
		setSbckbckname(sbckBckname);
		setSbckbckename(sbckBckename);
		setMbckbckcode(mbckBckcode);
		setMbckbckname(mbckBckname);
		setMbckbckename(mbckBckename);
		setLflfcode(lfLfcode);
		setLflfname(lfLfname);
		setClfclfcode(clfClfcode);
		setClfclfname(clfClfname);
		setChnchnmaxdtransfercharge(chnChnmaxdtransfercharge);
		setChnchnmaxmtransfercharge(chnChnmaxmtransfercharge);
		setCkckcode(ckCkcode);
		setCkckname(ckCkname);
		setEstestcode(estEstcode);
		setEstestname(estEstname);
		setChnchnlabelprintprefix(chnChnlabelprintprefix);
		setChnchnsobypiecessign(chnChnsobypiecessign);
		setChnchnmaxoverdeclarevalue(chnChnmaxoverdeclarevalue);
		setChnchnchawbprefix(chnChnchawbprefix);
	}

	public void setChnchncode(String chnChncode) {
		this.setField(0, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(0);
	}

	public void setChnchncreatedate(Date chnChncreatedate) {
		this.setField(1, chnChncreatedate);
	}

	public String getChnchncreatedate() {
		return this.getField(1);
	}

	public void setChnchnmodifydate(Date chnChnmodifydate) {
		this.setField(2, chnChnmodifydate);
	}

	public String getChnchnmodifydate() {
		return this.getField(2);
	}

	public void setChncstcode(String chnCstcode) {
		this.setField(3, chnCstcode);
	}

	public String getChncstcode() {
		return this.getField(3);
	}

	public void setChnchnname(String chnChnname) {
		this.setField(4, chnChnname);
	}

	public String getChnchnname() {
		return this.getField(4);
	}

	public void setChnchnename(String chnChnename) {
		this.setField(5, chnChnename);
	}

	public String getChnchnename() {
		return this.getField(5);
	}

	public void setChnchnsname(String chnChnsname) {
		this.setField(6, chnChnsname);
	}

	public String getChnchnsname() {
		return this.getField(6);
	}

	public void setChnchnsename(String chnChnsename) {
		this.setField(7, chnChnsename);
	}

	public String getChnchnsename() {
		return this.getField(7);
	}

	public void setChnchnmanifestseriesnumber(String chnChnmanifestseriesnumber) {
		this.setField(8, chnChnmanifestseriesnumber);
	}

	public String getChnchnmanifestseriesnumber() {
		return this.getField(8);
	}

	public void setEraccount(String Eraccount) {
		this.setField(9, Eraccount);
	}

	public String getEraccount() {
		return this.getField(9);
	}

	public void setChnchnpaymentaccount(String chnChnpaymentaccount) {
		this.setField(10, chnChnpaymentaccount);
	}

	public String getChnchnpaymentaccount() {
		return this.getField(10);
	}

	public void setCscscode(String csCscode) {
		this.setField(11, csCscode);
	}

	public String getCscscode() {
		return this.getField(11);
	}

	public void setCscsname(String csCsname) {
		this.setField(12, csCsname);
	}

	public String getCscsname() {
		return this.getField(12);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(13, mopOpid);
	}

	public String getMopopid() {
		return this.getField(13);
	}

	public void setMopopname(String mopOpname) {
		this.setField(14, mopOpname);
	}

	public String getMopopname() {
		return this.getField(14);
	}

	public void setCopopid(Long copOpid) {
		this.setField(15, copOpid);
	}

	public String getCopopid() {
		return this.getField(15);
	}

	public void setCopopname(String copOpname) {
		this.setField(16, copOpname);
	}

	public String getCopopname() {
		return this.getField(16);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(17, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(17);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(18, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(18);
	}

	public void setEeeeesname(String eeEeesname) {
		this.setField(19, eeEeesname);
	}

	public String getEeeeesname() {
		return this.getField(19);
	}

	public void setCococode(String coCocode) {
		this.setField(20, coCocode);
	}

	public String getCococode() {
		return this.getField(20);
	}

	public void setCocosname(String coCosname) {
		this.setField(21, coCosname);
	}

	public String getCocosname() {
		return this.getField(21);
	}

	public void setCocosename(String coCosename) {
		this.setField(22, coCosename);
	}

	public String getCocosename() {
		return this.getField(22);
	}

	public void setChnchnmaxdtransferweight(BigDecimal chnChnmaxdtransferweight) {
		this.setField(23, chnChnmaxdtransferweight);
	}

	public String getChnchnmaxdtransferweight() {
		return this.getField(23);
	}

	public void setChnchnwpxspsmappingname(String chnChnwpxspsmappingname) {
		this.setField(24, chnChnwpxspsmappingname);
	}

	public String getChnchnwpxspsmappingname() {
		return this.getField(24);
	}

	public void setSotsotcode(String sotSotcode) {
		this.setField(25, sotSotcode);
	}

	public String getSotsotcode() {
		return this.getField(25);
	}

	public void setSotsotname(String sotSotname) {
		this.setField(26, sotSotname);
	}

	public String getSotsotname() {
		return this.getField(26);
	}

	public void setSsgssgcode(String ssgSsgcode) {
		this.setField(27, ssgSsgcode);
	}

	public String getSsgssgcode() {
		return this.getField(27);
	}

	public void setSsgssgname(String ssgSsgname) {
		this.setField(28, ssgSsgname);
	}

	public String getSsgssgname() {
		return this.getField(28);
	}

	public void setSsgssgename(String ssgSsgename) {
		this.setField(29, ssgSsgename);
	}

	public String getSsgssgename() {
		return this.getField(29);
	}

	public void setSbckbckcode(String sbckBckcode) {
		this.setField(30, sbckBckcode);
	}

	public String getSbckbckcode() {
		return this.getField(30);
	}

	public void setSbckbckname(String sbckBckname) {
		this.setField(31, sbckBckname);
	}

	public String getSbckbckname() {
		return this.getField(31);
	}

	public void setSbckbckename(String sbckBckename) {
		this.setField(32, sbckBckename);
	}

	public String getSbckbckename() {
		return this.getField(32);
	}

	public void setMbckbckcode(String mbckBckcode) {
		this.setField(33, mbckBckcode);
	}

	public String getMbckbckcode() {
		return this.getField(33);
	}

	public void setMbckbckname(String mbckBckname) {
		this.setField(34, mbckBckname);
	}

	public String getMbckbckname() {
		return this.getField(34);
	}

	public void setMbckbckename(String mbckBckename) {
		this.setField(35, mbckBckename);
	}

	public String getMbckbckename() {
		return this.getField(35);
	}

	public void setLflfcode(String lfLfcode) {
		this.setField(36, lfLfcode);
	}

	public String getLflfcode() {
		return this.getField(36);
	}

	public void setLflfname(String lfLfname) {
		this.setField(37, lfLfname);
	}

	public String getLflfname() {
		return this.getField(37);
	}

	public void setClfclfcode(String clfClfcode) {
		this.setField(38, clfClfcode);
	}

	public String getClfclfcode() {
		return this.getField(38);
	}

	public void setClfclfname(String clfClfname) {
		this.setField(39, clfClfname);
	}

	public String getClfclfname() {
		return this.getField(39);
	}

	public void setChnchnmaxdtransfercharge(BigDecimal chnChnmaxdtransfercharge) {
		this.setField(40, chnChnmaxdtransfercharge);
	}

	public String getChnchnmaxdtransfercharge() {
		return this.getField(40);
	}

	public void setChnchnmaxmtransfercharge(BigDecimal chnChnmaxmtransfercharge) {
		this.setField(41, chnChnmaxmtransfercharge);
	}

	public String getChnchnmaxmtransfercharge() {
		return this.getField(41);
	}

	public void setCkckcode(String ckCkcode) {
		this.setField(42, ckCkcode);
	}

	public String getCkckcode() {
		return this.getField(42);
	}

	public void setCkckname(String ckCkname) {
		this.setField(43, ckCkname);
	}

	public String getCkckname() {
		return this.getField(43);
	}

	public void setEstestcode(String estEstcode) {
		this.setField(44, estEstcode);
	}

	public String getEstestcode() {
		return this.getField(44);
	}

	public void setEstestname(String estEstname) {
		this.setField(45, estEstname);
	}

	public String getEstestname() {
		return this.getField(45);
	}

	public void setChnchnlabelprintprefix(String chnChnlabelprintprefix) {
		this.setField(46, chnChnlabelprintprefix);
	}

	public String getChnchnlabelprintprefix() {
		return this.getField(46);
	}

	public void setChnchnsobypiecessign(String chnChnsobypiecessign) {
		this.setField(47, chnChnsobypiecessign);
	}

	public String getChnchnsobypiecessign() {
		return this.getField(47);
	}

	public void setChnchnmaxoverdeclarevalue(BigDecimal chnChnmaxoverdeclarevalue) {
		this.setField(48, chnChnmaxoverdeclarevalue);
	}

	public String getChnchnmaxoverdeclarevalue() {
		return this.getField(48);
	}

	public void setChnchnchawbprefix(String chnChnchawbprefix) {
		this.setField(49, chnChnchawbprefix);
	}

	public String getChnchnchawbprefix() {
		return this.getField(49);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
