package kyle.leis.eo.billing.payable.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class PayableforbillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PayableforbillColumns() {
		m_astrColumns = new String[91];
	}
	
	public PayableforbillColumns(Long pyPyid, 
            Date pyPycreatedate, Date pyPymodifydate, 
            Long pyBrid, BigDecimal pyPyunitprice, 
            BigDecimal pyPyunitnumber, Date pyPyauditdate, 
            BigDecimal pyPycurrencyrate, BigDecimal pyPytotal, 
            BigDecimal pyPyactualtotal, Long pyEpcode, 
            Integer pyEpvid, Date pyPyoccurdate, 
            BigDecimal pyPycommissionrate, Long pyPyidreference, 
            String pyPyremark, String ckCkcode, 
            String ckCkname, String chnChncode, 
            String chnChnname, String chnChnsname, 
            String chnChnsename, Long mopOpid, 
            String mopOpname, Long copOpid, 
            String copOpname, Long aopOpid, 
            String aopOpname, String fkFkcode, 
            String fkFkname, String coCocode, 
            String coConame, String coCosname, 
            String coCosename, Long cwCwcode, 
            String cwCwpostcodedestination, int cwCwpieces, 
            BigDecimal cwCwgrossweight, BigDecimal cwCwchargeweight, 
            BigDecimal cwCwtransferpieces, BigDecimal cwCwtransfergrossweight, 
            BigDecimal cwCwtransferchargeweight, BigDecimal cwCwserverchargeweight, 
            String cwCwcustomerewbcode, String cwCwserverewbcode, 
            String cwCwewbcode, BigDecimal cwCwtransfervolumeweight, 
            Date hwHwsignindate, String cwsCwscode, 
            String cwsCwsname, String pkPkcode, 
            String pkPkname, String pkPksname, 
            String pkPksename, String ddtDtcode, 
            String ddtDthubcode, String ddtDtname, 
            String cddtDtcode, String cddtDthubcode, 
            String cddtDtname, String sdtDtcode, 
            String sdtDthubcode, String odtDtcode, 
            String odtDthubcode, String odtDtname, 
            String pmPmcode, String pmPmname, 
            String schnChncode, String schnChnname, 
            String schnChnsname, String ctCtcode, 
            String ctCtname, String scoCocode, 
            String scoConame, String scoCosname, 
            String ccoCocode, String ccoConame, 
            String ccoCosname, Long abwBwcode, 
            String abwBwlabelcode, Long dbwBwcode, 
            String dbwBwlabelcode, String bkBkcode, 
            String bkBkname, String fsFscode, 
            String fsFsname, String pkPkshowserverewbcode, 
            Integer cwCwbillcounts, Integer cwCwbagcounts, 
            String cwCwbatchwaybillsign, String cwZnvname){
		m_astrColumns = new String[91];
		setPypyid(pyPyid);
		setPypycreatedate(pyPycreatedate);
		setPypymodifydate(pyPymodifydate);
		setPybrid(pyBrid);
		setPypyunitprice(pyPyunitprice);
		setPypyunitnumber(pyPyunitnumber);
		setPypyauditdate(pyPyauditdate);
		setPypycurrencyrate(pyPycurrencyrate);
		setPypytotal(pyPytotal);
		setPypyactualtotal(pyPyactualtotal);
		setPyepcode(pyEpcode);
		setPyepvid(pyEpvid);
		setPypyoccurdate(pyPyoccurdate);
		setPypycommissionrate(pyPycommissionrate);
		setPypyidreference(pyPyidreference);
		setPypyremark(pyPyremark);
		setCkckcode(ckCkcode);
		setCkckname(ckCkname);
		setChnchncode(chnChncode);
		setChnchnname(chnChnname);
		setChnchnsname(chnChnsname);
		setChnchnsename(chnChnsename);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setAopopid(aopOpid);
		setAopopname(aopOpname);
		setFkfkcode(fkFkcode);
		setFkfkname(fkFkname);
		setCococode(coCocode);
		setCoconame(coConame);
		setCocosname(coCosname);
		setCocosename(coCosename);
		setCwcwcode(cwCwcode);
		setCwcwpostcodedestination(cwCwpostcodedestination);
		setCwcwpieces(cwCwpieces);
		setCwcwgrossweight(cwCwgrossweight);
		setCwcwchargeweight(cwCwchargeweight);
		setCwcwtransferpieces(cwCwtransferpieces);
		setCwcwtransfergrossweight(cwCwtransfergrossweight);
		setCwcwtransferchargeweight(cwCwtransferchargeweight);
		setCwcwserverchargeweight(cwCwserverchargeweight);
		setCwcwcustomerewbcode(cwCwcustomerewbcode);
		setCwcwserverewbcode(cwCwserverewbcode);
		setCwcwewbcode(cwCwewbcode);
		setCwcwtransfervolumeweight(cwCwtransfervolumeweight);
		setHwhwsignindate(hwHwsignindate);
		setCwscwscode(cwsCwscode);
		setCwscwsname(cwsCwsname);
		setPkpkcode(pkPkcode);
		setPkpkname(pkPkname);
		setPkpksname(pkPksname);
		setPkpksename(pkPksename);
		setDdtdtcode(ddtDtcode);
		setDdtdthubcode(ddtDthubcode);
		setDdtdtname(ddtDtname);
		setCddtdtcode(cddtDtcode);
		setCddtdthubcode(cddtDthubcode);
		setCddtdtname(cddtDtname);
		setSdtdtcode(sdtDtcode);
		setSdtdthubcode(sdtDthubcode);
		setOdtdtcode(odtDtcode);
		setOdtdthubcode(odtDthubcode);
		setOdtdtname(odtDtname);
		setPmpmcode(pmPmcode);
		setPmpmname(pmPmname);
		setSchnchncode(schnChncode);
		setSchnchnname(schnChnname);
		setSchnchnsname(schnChnsname);
		setCtctcode(ctCtcode);
		setCtctname(ctCtname);
		setScococode(scoCocode);
		setScoconame(scoConame);
		setScocosname(scoCosname);
		setCcococode(ccoCocode);
		setCcoconame(ccoConame);
		setCcocosname(ccoCosname);
		setAbwbwcode(abwBwcode);
		setAbwbwlabelcode(abwBwlabelcode);
		setDbwbwcode(dbwBwcode);
		setDbwbwlabelcode(dbwBwlabelcode);
		setBkbkcode(bkBkcode);
		setBkbkname(bkBkname);
		setFsfscode(fsFscode);
		setFsfsname(fsFsname);
		setPkpkshowserverewbcode(pkPkshowserverewbcode);
		setCwcwbillcounts(cwCwbillcounts);
		setCwcwbagcounts(cwCwbagcounts);
		setCwcwbatchwaybillsign(cwCwbatchwaybillsign);
		setCwznvname(cwZnvname);
	}

	public void setPypyid(Long pyPyid) {
		this.setField(0, pyPyid);
	}

	public String getPypyid() {
		return this.getField(0);
	}

	public void setPypycreatedate(Date pyPycreatedate) {
		this.setField(1, pyPycreatedate);
	}

	public String getPypycreatedate() {
		return this.getField(1);
	}

	public void setPypymodifydate(Date pyPymodifydate) {
		this.setField(2, pyPymodifydate);
	}

	public String getPypymodifydate() {
		return this.getField(2);
	}

	public void setPybrid(Long pyBrid) {
		this.setField(3, pyBrid);
	}

	public String getPybrid() {
		return this.getField(3);
	}

	public void setPypyunitprice(BigDecimal pyPyunitprice) {
		this.setField(4, pyPyunitprice);
	}

	public String getPypyunitprice() {
		return this.getField(4);
	}

	public void setPypyunitnumber(BigDecimal pyPyunitnumber) {
		this.setField(5, pyPyunitnumber);
	}

	public String getPypyunitnumber() {
		return this.getField(5);
	}

	public void setPypyauditdate(Date pyPyauditdate) {
		this.setField(6, pyPyauditdate);
	}

	public String getPypyauditdate() {
		return this.getField(6);
	}

	public void setPypycurrencyrate(BigDecimal pyPycurrencyrate) {
		this.setField(7, pyPycurrencyrate);
	}

	public String getPypycurrencyrate() {
		return this.getField(7);
	}

	public void setPypytotal(BigDecimal pyPytotal) {
		this.setField(8, pyPytotal);
	}

	public String getPypytotal() {
		return this.getField(8);
	}

	public void setPypyactualtotal(BigDecimal pyPyactualtotal) {
		this.setField(9, pyPyactualtotal);
	}

	public String getPypyactualtotal() {
		return this.getField(9);
	}

	public void setPyepcode(Long pyEpcode) {
		this.setField(10, pyEpcode);
	}

	public String getPyepcode() {
		return this.getField(10);
	}

	public void setPyepvid(Integer pyEpvid) {
		this.setField(11, pyEpvid);
	}

	public String getPyepvid() {
		return this.getField(11);
	}

	public void setPypyoccurdate(Date pyPyoccurdate) {
		this.setField(12, pyPyoccurdate);
	}

	public String getPypyoccurdate() {
		return this.getField(12);
	}

	public void setPypycommissionrate(BigDecimal pyPycommissionrate) {
		this.setField(13, pyPycommissionrate);
	}

	public String getPypycommissionrate() {
		return this.getField(13);
	}

	public void setPypyidreference(Long pyPyidreference) {
		this.setField(14, pyPyidreference);
	}

	public String getPypyidreference() {
		return this.getField(14);
	}

	public void setPypyremark(String pyPyremark) {
		this.setField(15, pyPyremark);
	}

	public String getPypyremark() {
		return this.getField(15);
	}

	public void setCkckcode(String ckCkcode) {
		this.setField(16, ckCkcode);
	}

	public String getCkckcode() {
		return this.getField(16);
	}

	public void setCkckname(String ckCkname) {
		this.setField(17, ckCkname);
	}

	public String getCkckname() {
		return this.getField(17);
	}

	public void setChnchncode(String chnChncode) {
		this.setField(18, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(18);
	}

	public void setChnchnname(String chnChnname) {
		this.setField(19, chnChnname);
	}

	public String getChnchnname() {
		return this.getField(19);
	}

	public void setChnchnsname(String chnChnsname) {
		this.setField(20, chnChnsname);
	}

	public String getChnchnsname() {
		return this.getField(20);
	}

	public void setChnchnsename(String chnChnsename) {
		this.setField(21, chnChnsename);
	}

	public String getChnchnsename() {
		return this.getField(21);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(22, mopOpid);
	}

	public String getMopopid() {
		return this.getField(22);
	}

	public void setMopopname(String mopOpname) {
		this.setField(23, mopOpname);
	}

	public String getMopopname() {
		return this.getField(23);
	}

	public void setCopopid(Long copOpid) {
		this.setField(24, copOpid);
	}

	public String getCopopid() {
		return this.getField(24);
	}

	public void setCopopname(String copOpname) {
		this.setField(25, copOpname);
	}

	public String getCopopname() {
		return this.getField(25);
	}

	public void setAopopid(Long aopOpid) {
		this.setField(26, aopOpid);
	}

	public String getAopopid() {
		return this.getField(26);
	}

	public void setAopopname(String aopOpname) {
		this.setField(27, aopOpname);
	}

	public String getAopopname() {
		return this.getField(27);
	}

	public void setFkfkcode(String fkFkcode) {
		this.setField(28, fkFkcode);
	}

	public String getFkfkcode() {
		return this.getField(28);
	}

	public void setFkfkname(String fkFkname) {
		this.setField(29, fkFkname);
	}

	public String getFkfkname() {
		return this.getField(29);
	}

	public void setCococode(String coCocode) {
		this.setField(30, coCocode);
	}

	public String getCococode() {
		return this.getField(30);
	}

	public void setCoconame(String coConame) {
		this.setField(31, coConame);
	}

	public String getCoconame() {
		return this.getField(31);
	}

	public void setCocosname(String coCosname) {
		this.setField(32, coCosname);
	}

	public String getCocosname() {
		return this.getField(32);
	}

	public void setCocosename(String coCosename) {
		this.setField(33, coCosename);
	}

	public String getCocosename() {
		return this.getField(33);
	}

	public void setCwcwcode(Long cwCwcode) {
		this.setField(34, cwCwcode);
	}

	public String getCwcwcode() {
		return this.getField(34);
	}

	public void setCwcwpostcodedestination(String cwCwpostcodedestination) {
		this.setField(35, cwCwpostcodedestination);
	}

	public String getCwcwpostcodedestination() {
		return this.getField(35);
	}

	public void setCwcwpieces(int cwCwpieces) {
		this.setField(36, cwCwpieces);
	}

	public String getCwcwpieces() {
		return this.getField(36);
	}

	public void setCwcwgrossweight(BigDecimal cwCwgrossweight) {
		this.setField(37, cwCwgrossweight);
	}

	public String getCwcwgrossweight() {
		return this.getField(37);
	}

	public void setCwcwchargeweight(BigDecimal cwCwchargeweight) {
		this.setField(38, cwCwchargeweight);
	}

	public String getCwcwchargeweight() {
		return this.getField(38);
	}

	public void setCwcwtransferpieces(BigDecimal cwCwtransferpieces) {
		this.setField(39, cwCwtransferpieces);
	}

	public String getCwcwtransferpieces() {
		return this.getField(39);
	}

	public void setCwcwtransfergrossweight(BigDecimal cwCwtransfergrossweight) {
		this.setField(40, cwCwtransfergrossweight);
	}

	public String getCwcwtransfergrossweight() {
		return this.getField(40);
	}

	public void setCwcwtransferchargeweight(BigDecimal cwCwtransferchargeweight) {
		this.setField(41, cwCwtransferchargeweight);
	}

	public String getCwcwtransferchargeweight() {
		return this.getField(41);
	}

	public void setCwcwserverchargeweight(BigDecimal cwCwserverchargeweight) {
		this.setField(42, cwCwserverchargeweight);
	}

	public String getCwcwserverchargeweight() {
		return this.getField(42);
	}

	public void setCwcwcustomerewbcode(String cwCwcustomerewbcode) {
		this.setField(43, cwCwcustomerewbcode);
	}

	public String getCwcwcustomerewbcode() {
		return this.getField(43);
	}

	public void setCwcwserverewbcode(String cwCwserverewbcode) {
		this.setField(44, cwCwserverewbcode);
	}

	public String getCwcwserverewbcode() {
		return this.getField(44);
	}

	public void setCwcwewbcode(String cwCwewbcode) {
		this.setField(45, cwCwewbcode);
	}

	public String getCwcwewbcode() {
		return this.getField(45);
	}

	public void setCwcwtransfervolumeweight(BigDecimal cwCwtransfervolumeweight) {
		this.setField(46, cwCwtransfervolumeweight);
	}

	public String getCwcwtransfervolumeweight() {
		return this.getField(46);
	}

	public void setHwhwsignindate(Date hwHwsignindate) {
		this.setField(47, hwHwsignindate);
	}

	public String getHwhwsignindate() {
		return this.getField(47);
	}

	public void setCwscwscode(String cwsCwscode) {
		this.setField(48, cwsCwscode);
	}

	public String getCwscwscode() {
		return this.getField(48);
	}

	public void setCwscwsname(String cwsCwsname) {
		this.setField(49, cwsCwsname);
	}

	public String getCwscwsname() {
		return this.getField(49);
	}

	public void setPkpkcode(String pkPkcode) {
		this.setField(50, pkPkcode);
	}

	public String getPkpkcode() {
		return this.getField(50);
	}

	public void setPkpkname(String pkPkname) {
		this.setField(51, pkPkname);
	}

	public String getPkpkname() {
		return this.getField(51);
	}

	public void setPkpksname(String pkPksname) {
		this.setField(52, pkPksname);
	}

	public String getPkpksname() {
		return this.getField(52);
	}

	public void setPkpksename(String pkPksename) {
		this.setField(53, pkPksename);
	}

	public String getPkpksename() {
		return this.getField(53);
	}

	public void setDdtdtcode(String ddtDtcode) {
		this.setField(54, ddtDtcode);
	}

	public String getDdtdtcode() {
		return this.getField(54);
	}

	public void setDdtdthubcode(String ddtDthubcode) {
		this.setField(55, ddtDthubcode);
	}

	public String getDdtdthubcode() {
		return this.getField(55);
	}

	public void setDdtdtname(String ddtDtname) {
		this.setField(56, ddtDtname);
	}

	public String getDdtdtname() {
		return this.getField(56);
	}

	public void setCddtdtcode(String cddtDtcode) {
		this.setField(57, cddtDtcode);
	}

	public String getCddtdtcode() {
		return this.getField(57);
	}

	public void setCddtdthubcode(String cddtDthubcode) {
		this.setField(58, cddtDthubcode);
	}

	public String getCddtdthubcode() {
		return this.getField(58);
	}

	public void setCddtdtname(String cddtDtname) {
		this.setField(59, cddtDtname);
	}

	public String getCddtdtname() {
		return this.getField(59);
	}

	public void setSdtdtcode(String sdtDtcode) {
		this.setField(60, sdtDtcode);
	}

	public String getSdtdtcode() {
		return this.getField(60);
	}

	public void setSdtdthubcode(String sdtDthubcode) {
		this.setField(61, sdtDthubcode);
	}

	public String getSdtdthubcode() {
		return this.getField(61);
	}

	public void setOdtdtcode(String odtDtcode) {
		this.setField(62, odtDtcode);
	}

	public String getOdtdtcode() {
		return this.getField(62);
	}

	public void setOdtdthubcode(String odtDthubcode) {
		this.setField(63, odtDthubcode);
	}

	public String getOdtdthubcode() {
		return this.getField(63);
	}

	public void setOdtdtname(String odtDtname) {
		this.setField(64, odtDtname);
	}

	public String getOdtdtname() {
		return this.getField(64);
	}

	public void setPmpmcode(String pmPmcode) {
		this.setField(65, pmPmcode);
	}

	public String getPmpmcode() {
		return this.getField(65);
	}

	public void setPmpmname(String pmPmname) {
		this.setField(66, pmPmname);
	}

	public String getPmpmname() {
		return this.getField(66);
	}

	public void setSchnchncode(String schnChncode) {
		this.setField(67, schnChncode);
	}

	public String getSchnchncode() {
		return this.getField(67);
	}

	public void setSchnchnname(String schnChnname) {
		this.setField(68, schnChnname);
	}

	public String getSchnchnname() {
		return this.getField(68);
	}

	public void setSchnchnsname(String schnChnsname) {
		this.setField(69, schnChnsname);
	}

	public String getSchnchnsname() {
		return this.getField(69);
	}

	public void setCtctcode(String ctCtcode) {
		this.setField(70, ctCtcode);
	}

	public String getCtctcode() {
		return this.getField(70);
	}

	public void setCtctname(String ctCtname) {
		this.setField(71, ctCtname);
	}

	public String getCtctname() {
		return this.getField(71);
	}

	public void setScococode(String scoCocode) {
		this.setField(72, scoCocode);
	}

	public String getScococode() {
		return this.getField(72);
	}

	public void setScoconame(String scoConame) {
		this.setField(73, scoConame);
	}

	public String getScoconame() {
		return this.getField(73);
	}

	public void setScocosname(String scoCosname) {
		this.setField(74, scoCosname);
	}

	public String getScocosname() {
		return this.getField(74);
	}

	public void setCcococode(String ccoCocode) {
		this.setField(75, ccoCocode);
	}

	public String getCcococode() {
		return this.getField(75);
	}

	public void setCcoconame(String ccoConame) {
		this.setField(76, ccoConame);
	}

	public String getCcoconame() {
		return this.getField(76);
	}

	public void setCcocosname(String ccoCosname) {
		this.setField(77, ccoCosname);
	}

	public String getCcocosname() {
		return this.getField(77);
	}

	public void setAbwbwcode(Long abwBwcode) {
		this.setField(78, abwBwcode);
	}

	public String getAbwbwcode() {
		return this.getField(78);
	}

	public void setAbwbwlabelcode(String abwBwlabelcode) {
		this.setField(79, abwBwlabelcode);
	}

	public String getAbwbwlabelcode() {
		return this.getField(79);
	}

	public void setDbwbwcode(Long dbwBwcode) {
		this.setField(80, dbwBwcode);
	}

	public String getDbwbwcode() {
		return this.getField(80);
	}

	public void setDbwbwlabelcode(String dbwBwlabelcode) {
		this.setField(81, dbwBwlabelcode);
	}

	public String getDbwbwlabelcode() {
		return this.getField(81);
	}

	public void setBkbkcode(String bkBkcode) {
		this.setField(82, bkBkcode);
	}

	public String getBkbkcode() {
		return this.getField(82);
	}

	public void setBkbkname(String bkBkname) {
		this.setField(83, bkBkname);
	}

	public String getBkbkname() {
		return this.getField(83);
	}

	public void setFsfscode(String fsFscode) {
		this.setField(84, fsFscode);
	}

	public String getFsfscode() {
		return this.getField(84);
	}

	public void setFsfsname(String fsFsname) {
		this.setField(85, fsFsname);
	}

	public String getFsfsname() {
		return this.getField(85);
	}

	public void setPkpkshowserverewbcode(String pkPkshowserverewbcode) {
		this.setField(86, pkPkshowserverewbcode);
	}

	public String getPkpkshowserverewbcode() {
		return this.getField(86);
	}

	public void setCwcwbillcounts(Integer cwCwbillcounts) {
		this.setField(87, cwCwbillcounts);
	}

	public String getCwcwbillcounts() {
		return this.getField(87);
	}

	public void setCwcwbagcounts(Integer cwCwbagcounts) {
		this.setField(88, cwCwbagcounts);
	}

	public String getCwcwbagcounts() {
		return this.getField(88);
	}

	public void setCwcwbatchwaybillsign(String cwCwbatchwaybillsign) {
		this.setField(89, cwCwbatchwaybillsign);
	}

	public String getCwcwbatchwaybillsign() {
		return this.getField(89);
	}

	public void setCwznvname(String cwZnvname) {
		this.setField(90, cwZnvname);
	}

	public String getCwznvname() {
		return this.getField(90);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
