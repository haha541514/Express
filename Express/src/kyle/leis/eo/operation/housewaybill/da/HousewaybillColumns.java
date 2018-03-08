package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class HousewaybillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public HousewaybillColumns() {
		m_astrColumns = new String[104];
	}
	
	public HousewaybillColumns(Long hwCwcode, 
            Date hwHwsignindate, Long hwHwopidsignin, 
            Long hwHwopidweighting, Date hwHwsignoutdate, 
            Long hwHwopidsignout, Date hwHwrecorddate, 
            Long hwHwopidrecord, Long hwHwopidpacking, 
            BigDecimal hwHwinsurancevalue, String hwHwconsigneepostcode, 
            String cwCwpostcodedestination, int cwCwpieces, 
            BigDecimal cwCwgrossweight, BigDecimal cwCwchargeweight, 
            BigDecimal cwCwtransferpieces, BigDecimal cwCwtransfergrossweight, 
            BigDecimal cwCwtransferchargeweight, BigDecimal cwCwserverchargeweight, 
            String cwCwcustomerewbcode, String cwCwserverewbcode, 
            String cwCwewbcode, BigDecimal cwCwtransfervolumeweight, 
            String cwsCwscode, String cwsCwsname, 
            String eeEecode, String eeEesname, 
            String eeEeesname, String pkPkcode, 
            String pkPkname, String pkPksname, 
            String pkPksename, String ddtDtcode, 
            String ddtDthubcode, String ddtDtname, 
            String sdtDtcode, String sdtDthubcode, 
            String sdtDtname, String cddtDtcode, 
            String cddtDthubcode, String cddtDtname, 
            String odtDtcode, String odtDthubcode, 
            String odtDtname, String pmPmcode, 
            String pmPmname, String schnChncode, 
            String schnChnname, String schnChnsname, 
            String cchnChncode, String cchnChnname, 
            String cchnChnsname, String ctCtcode, 
            String ctCtname, String scoCocode, 
            String scoConame, String scoCosname, 
            String scoColabelcode, String ccoCocode, 
            String ccoConame, String ccoCosname, 
            String ccoColabelcode, Long abwBwcode, 
            String abwBwlabelcode, Date abwAdddate, 
            Long dbwBwcode, String dbwBwlabelcode, 
            Date dbwAdddate, BigDecimal cwCwcustomerchargeweight, 
            String ihsIhscode, String ihsIhsname, 
            int cwCwvolumerate, int cwCwtransfervolumerate, 
            String hwHwremark, String cctCtcode, 
            String cctCtname, Long csopOpid, 
            String csopOpname, String ctCtename, 
            String ctCtsename, String cwsCwsename, 
            String ddtDtename, String sdtDtename, 
            String cddtDtename, String odtDtename, 
            String cwZnvname, String serverwbckBckcode, 
            String hwHwserverewbchangedsign, Integer hwHwlabelprinttimes, 
            String subwbckBckcode, String Eraccount, 
            String hwHwconsigneecity, Long hwHwopidweightcheck, 
            String hwHwweightcheckkind, Date hwHwweightcheckdate, 
            String wcbwBwlabelcode, Integer cwCwbillcounts, 
            Integer cwCwbagcounts, Long abwvBwbvid, 
            Long dbwvBwbvid, String cwCwbatchwaybillsign, 
            String hwHwarrearsignout, String hwHwsewbchangedbywebsign, 
            String hwHwpaymentaccount){
		m_astrColumns = new String[104];
		setHwcwcode(hwCwcode);
		setHwhwsignindate(hwHwsignindate);
		setHwhwopidsignin(hwHwopidsignin);
		setHwhwopidweighting(hwHwopidweighting);
		setHwhwsignoutdate(hwHwsignoutdate);
		setHwhwopidsignout(hwHwopidsignout);
		setHwhwrecorddate(hwHwrecorddate);
		setHwhwopidrecord(hwHwopidrecord);
		setHwhwopidpacking(hwHwopidpacking);
		setHwhwinsurancevalue(hwHwinsurancevalue);
		setHwhwconsigneepostcode(hwHwconsigneepostcode);
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
		setCwscwscode(cwsCwscode);
		setCwscwsname(cwsCwsname);
		setEeeecode(eeEecode);
		setEeeesname(eeEesname);
		setEeeeesname(eeEeesname);
		setPkpkcode(pkPkcode);
		setPkpkname(pkPkname);
		setPkpksname(pkPksname);
		setPkpksename(pkPksename);
		setDdtdtcode(ddtDtcode);
		setDdtdthubcode(ddtDthubcode);
		setDdtdtname(ddtDtname);
		setSdtdtcode(sdtDtcode);
		setSdtdthubcode(sdtDthubcode);
		setSdtdtname(sdtDtname);
		setCddtdtcode(cddtDtcode);
		setCddtdthubcode(cddtDthubcode);
		setCddtdtname(cddtDtname);
		setOdtdtcode(odtDtcode);
		setOdtdthubcode(odtDthubcode);
		setOdtdtname(odtDtname);
		setPmpmcode(pmPmcode);
		setPmpmname(pmPmname);
		setSchnchncode(schnChncode);
		setSchnchnname(schnChnname);
		setSchnchnsname(schnChnsname);
		setCchnchncode(cchnChncode);
		setCchnchnname(cchnChnname);
		setCchnchnsname(cchnChnsname);
		setCtctcode(ctCtcode);
		setCtctname(ctCtname);
		setScococode(scoCocode);
		setScoconame(scoConame);
		setScocosname(scoCosname);
		setScocolabelcode(scoColabelcode);
		setCcococode(ccoCocode);
		setCcoconame(ccoConame);
		setCcocosname(ccoCosname);
		setCcocolabelcode(ccoColabelcode);
		setAbwbwcode(abwBwcode);
		setAbwbwlabelcode(abwBwlabelcode);
		setAbwadddate(abwAdddate);
		setDbwbwcode(dbwBwcode);
		setDbwbwlabelcode(dbwBwlabelcode);
		setDbwadddate(dbwAdddate);
		setCwcwcustomerchargeweight(cwCwcustomerchargeweight);
		setIhsihscode(ihsIhscode);
		setIhsihsname(ihsIhsname);
		setCwcwvolumerate(cwCwvolumerate);
		setCwcwtransfervolumerate(cwCwtransfervolumerate);
		setHwhwremark(hwHwremark);
		setCctctcode(cctCtcode);
		setCctctname(cctCtname);
		setCsopopid(csopOpid);
		setCsopopname(csopOpname);
		setCtctename(ctCtename);
		setCtctsename(ctCtsename);
		setCwscwsename(cwsCwsename);
		setDdtdtename(ddtDtename);
		setSdtdtename(sdtDtename);
		setCddtdtename(cddtDtename);
		setOdtdtename(odtDtename);
		setCwznvname(cwZnvname);
		setServerwbckbckcode(serverwbckBckcode);
		setHwhwserverewbchangedsign(hwHwserverewbchangedsign);
		setHwhwlabelprinttimes(hwHwlabelprinttimes);
		setSubwbckbckcode(subwbckBckcode);
		setEraccount(Eraccount);
		setHwhwconsigneecity(hwHwconsigneecity);
		setHwhwopidweightcheck(hwHwopidweightcheck);
		setHwhwweightcheckkind(hwHwweightcheckkind);
		setHwhwweightcheckdate(hwHwweightcheckdate);
		setWcbwbwlabelcode(wcbwBwlabelcode);
		setCwcwbillcounts(cwCwbillcounts);
		setCwcwbagcounts(cwCwbagcounts);
		setAbwvbwbvid(abwvBwbvid);
		setDbwvbwbvid(dbwvBwbvid);
		setCwcwbatchwaybillsign(cwCwbatchwaybillsign);
		setHwhwarrearsignout(hwHwarrearsignout);
		setHwhwsewbchangedbywebsign(hwHwsewbchangedbywebsign);
		setHwhwpaymentaccount(hwHwpaymentaccount);
	}

	public void setHwcwcode(Long hwCwcode) {
		this.setField(0, hwCwcode);
	}

	public String getHwcwcode() {
		return this.getField(0);
	}

	public void setHwhwsignindate(Date hwHwsignindate) {
		this.setField(1, hwHwsignindate);
	}

	public String getHwhwsignindate() {
		return this.getField(1);
	}

	public void setHwhwopidsignin(Long hwHwopidsignin) {
		this.setField(2, hwHwopidsignin);
	}

	public String getHwhwopidsignin() {
		return this.getField(2);
	}

	public void setHwhwopidweighting(Long hwHwopidweighting) {
		this.setField(3, hwHwopidweighting);
	}

	public String getHwhwopidweighting() {
		return this.getField(3);
	}

	public void setHwhwsignoutdate(Date hwHwsignoutdate) {
		this.setField(4, hwHwsignoutdate);
	}

	public String getHwhwsignoutdate() {
		return this.getField(4);
	}

	public void setHwhwopidsignout(Long hwHwopidsignout) {
		this.setField(5, hwHwopidsignout);
	}

	public String getHwhwopidsignout() {
		return this.getField(5);
	}

	public void setHwhwrecorddate(Date hwHwrecorddate) {
		this.setField(6, hwHwrecorddate);
	}

	public String getHwhwrecorddate() {
		return this.getField(6);
	}

	public void setHwhwopidrecord(Long hwHwopidrecord) {
		this.setField(7, hwHwopidrecord);
	}

	public String getHwhwopidrecord() {
		return this.getField(7);
	}

	public void setHwhwopidpacking(Long hwHwopidpacking) {
		this.setField(8, hwHwopidpacking);
	}

	public String getHwhwopidpacking() {
		return this.getField(8);
	}

	public void setHwhwinsurancevalue(BigDecimal hwHwinsurancevalue) {
		this.setField(9, hwHwinsurancevalue);
	}

	public String getHwhwinsurancevalue() {
		return this.getField(9);
	}

	public void setHwhwconsigneepostcode(String hwHwconsigneepostcode) {
		this.setField(10, hwHwconsigneepostcode);
	}

	public String getHwhwconsigneepostcode() {
		return this.getField(10);
	}

	public void setCwcwpostcodedestination(String cwCwpostcodedestination) {
		this.setField(11, cwCwpostcodedestination);
	}

	public String getCwcwpostcodedestination() {
		return this.getField(11);
	}

	public void setCwcwpieces(int cwCwpieces) {
		this.setField(12, cwCwpieces);
	}

	public String getCwcwpieces() {
		return this.getField(12);
	}

	public void setCwcwgrossweight(BigDecimal cwCwgrossweight) {
		this.setField(13, cwCwgrossweight);
	}

	public String getCwcwgrossweight() {
		return this.getField(13);
	}

	public void setCwcwchargeweight(BigDecimal cwCwchargeweight) {
		this.setField(14, cwCwchargeweight);
	}

	public String getCwcwchargeweight() {
		return this.getField(14);
	}

	public void setCwcwtransferpieces(BigDecimal cwCwtransferpieces) {
		this.setField(15, cwCwtransferpieces);
	}

	public String getCwcwtransferpieces() {
		return this.getField(15);
	}

	public void setCwcwtransfergrossweight(BigDecimal cwCwtransfergrossweight) {
		this.setField(16, cwCwtransfergrossweight);
	}

	public String getCwcwtransfergrossweight() {
		return this.getField(16);
	}

	public void setCwcwtransferchargeweight(BigDecimal cwCwtransferchargeweight) {
		this.setField(17, cwCwtransferchargeweight);
	}

	public String getCwcwtransferchargeweight() {
		return this.getField(17);
	}

	public void setCwcwserverchargeweight(BigDecimal cwCwserverchargeweight) {
		this.setField(18, cwCwserverchargeweight);
	}

	public String getCwcwserverchargeweight() {
		return this.getField(18);
	}

	public void setCwcwcustomerewbcode(String cwCwcustomerewbcode) {
		this.setField(19, cwCwcustomerewbcode);
	}

	public String getCwcwcustomerewbcode() {
		return this.getField(19);
	}

	public void setCwcwserverewbcode(String cwCwserverewbcode) {
		this.setField(20, cwCwserverewbcode);
	}

	public String getCwcwserverewbcode() {
		return this.getField(20);
	}

	public void setCwcwewbcode(String cwCwewbcode) {
		this.setField(21, cwCwewbcode);
	}

	public String getCwcwewbcode() {
		return this.getField(21);
	}

	public void setCwcwtransfervolumeweight(BigDecimal cwCwtransfervolumeweight) {
		this.setField(22, cwCwtransfervolumeweight);
	}

	public String getCwcwtransfervolumeweight() {
		return this.getField(22);
	}

	public void setCwscwscode(String cwsCwscode) {
		this.setField(23, cwsCwscode);
	}

	public String getCwscwscode() {
		return this.getField(23);
	}

	public void setCwscwsname(String cwsCwsname) {
		this.setField(24, cwsCwsname);
	}

	public String getCwscwsname() {
		return this.getField(24);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(25, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(25);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(26, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(26);
	}

	public void setEeeeesname(String eeEeesname) {
		this.setField(27, eeEeesname);
	}

	public String getEeeeesname() {
		return this.getField(27);
	}

	public void setPkpkcode(String pkPkcode) {
		this.setField(28, pkPkcode);
	}

	public String getPkpkcode() {
		return this.getField(28);
	}

	public void setPkpkname(String pkPkname) {
		this.setField(29, pkPkname);
	}

	public String getPkpkname() {
		return this.getField(29);
	}

	public void setPkpksname(String pkPksname) {
		this.setField(30, pkPksname);
	}

	public String getPkpksname() {
		return this.getField(30);
	}

	public void setPkpksename(String pkPksename) {
		this.setField(31, pkPksename);
	}

	public String getPkpksename() {
		return this.getField(31);
	}

	public void setDdtdtcode(String ddtDtcode) {
		this.setField(32, ddtDtcode);
	}

	public String getDdtdtcode() {
		return this.getField(32);
	}

	public void setDdtdthubcode(String ddtDthubcode) {
		this.setField(33, ddtDthubcode);
	}

	public String getDdtdthubcode() {
		return this.getField(33);
	}

	public void setDdtdtname(String ddtDtname) {
		this.setField(34, ddtDtname);
	}

	public String getDdtdtname() {
		return this.getField(34);
	}

	public void setSdtdtcode(String sdtDtcode) {
		this.setField(35, sdtDtcode);
	}

	public String getSdtdtcode() {
		return this.getField(35);
	}

	public void setSdtdthubcode(String sdtDthubcode) {
		this.setField(36, sdtDthubcode);
	}

	public String getSdtdthubcode() {
		return this.getField(36);
	}

	public void setSdtdtname(String sdtDtname) {
		this.setField(37, sdtDtname);
	}

	public String getSdtdtname() {
		return this.getField(37);
	}

	public void setCddtdtcode(String cddtDtcode) {
		this.setField(38, cddtDtcode);
	}

	public String getCddtdtcode() {
		return this.getField(38);
	}

	public void setCddtdthubcode(String cddtDthubcode) {
		this.setField(39, cddtDthubcode);
	}

	public String getCddtdthubcode() {
		return this.getField(39);
	}

	public void setCddtdtname(String cddtDtname) {
		this.setField(40, cddtDtname);
	}

	public String getCddtdtname() {
		return this.getField(40);
	}

	public void setOdtdtcode(String odtDtcode) {
		this.setField(41, odtDtcode);
	}

	public String getOdtdtcode() {
		return this.getField(41);
	}

	public void setOdtdthubcode(String odtDthubcode) {
		this.setField(42, odtDthubcode);
	}

	public String getOdtdthubcode() {
		return this.getField(42);
	}

	public void setOdtdtname(String odtDtname) {
		this.setField(43, odtDtname);
	}

	public String getOdtdtname() {
		return this.getField(43);
	}

	public void setPmpmcode(String pmPmcode) {
		this.setField(44, pmPmcode);
	}

	public String getPmpmcode() {
		return this.getField(44);
	}

	public void setPmpmname(String pmPmname) {
		this.setField(45, pmPmname);
	}

	public String getPmpmname() {
		return this.getField(45);
	}

	public void setSchnchncode(String schnChncode) {
		this.setField(46, schnChncode);
	}

	public String getSchnchncode() {
		return this.getField(46);
	}

	public void setSchnchnname(String schnChnname) {
		this.setField(47, schnChnname);
	}

	public String getSchnchnname() {
		return this.getField(47);
	}

	public void setSchnchnsname(String schnChnsname) {
		this.setField(48, schnChnsname);
	}

	public String getSchnchnsname() {
		return this.getField(48);
	}

	public void setCchnchncode(String cchnChncode) {
		this.setField(49, cchnChncode);
	}

	public String getCchnchncode() {
		return this.getField(49);
	}

	public void setCchnchnname(String cchnChnname) {
		this.setField(50, cchnChnname);
	}

	public String getCchnchnname() {
		return this.getField(50);
	}

	public void setCchnchnsname(String cchnChnsname) {
		this.setField(51, cchnChnsname);
	}

	public String getCchnchnsname() {
		return this.getField(51);
	}

	public void setCtctcode(String ctCtcode) {
		this.setField(52, ctCtcode);
	}

	public String getCtctcode() {
		return this.getField(52);
	}

	public void setCtctname(String ctCtname) {
		this.setField(53, ctCtname);
	}

	public String getCtctname() {
		return this.getField(53);
	}

	public void setScococode(String scoCocode) {
		this.setField(54, scoCocode);
	}

	public String getScococode() {
		return this.getField(54);
	}

	public void setScoconame(String scoConame) {
		this.setField(55, scoConame);
	}

	public String getScoconame() {
		return this.getField(55);
	}

	public void setScocosname(String scoCosname) {
		this.setField(56, scoCosname);
	}

	public String getScocosname() {
		return this.getField(56);
	}

	public void setScocolabelcode(String scoColabelcode) {
		this.setField(57, scoColabelcode);
	}

	public String getScocolabelcode() {
		return this.getField(57);
	}

	public void setCcococode(String ccoCocode) {
		this.setField(58, ccoCocode);
	}

	public String getCcococode() {
		return this.getField(58);
	}

	public void setCcoconame(String ccoConame) {
		this.setField(59, ccoConame);
	}

	public String getCcoconame() {
		return this.getField(59);
	}

	public void setCcocosname(String ccoCosname) {
		this.setField(60, ccoCosname);
	}

	public String getCcocosname() {
		return this.getField(60);
	}

	public void setCcocolabelcode(String ccoColabelcode) {
		this.setField(61, ccoColabelcode);
	}

	public String getCcocolabelcode() {
		return this.getField(61);
	}

	public void setAbwbwcode(Long abwBwcode) {
		this.setField(62, abwBwcode);
	}

	public String getAbwbwcode() {
		return this.getField(62);
	}

	public void setAbwbwlabelcode(String abwBwlabelcode) {
		this.setField(63, abwBwlabelcode);
	}

	public String getAbwbwlabelcode() {
		return this.getField(63);
	}

	public void setAbwadddate(Date abwAdddate) {
		this.setField(64, abwAdddate);
	}

	public String getAbwadddate() {
		return this.getField(64);
	}

	public void setDbwbwcode(Long dbwBwcode) {
		this.setField(65, dbwBwcode);
	}

	public String getDbwbwcode() {
		return this.getField(65);
	}

	public void setDbwbwlabelcode(String dbwBwlabelcode) {
		this.setField(66, dbwBwlabelcode);
	}

	public String getDbwbwlabelcode() {
		return this.getField(66);
	}

	public void setDbwadddate(Date dbwAdddate) {
		this.setField(67, dbwAdddate);
	}

	public String getDbwadddate() {
		return this.getField(67);
	}

	public void setCwcwcustomerchargeweight(BigDecimal cwCwcustomerchargeweight) {
		this.setField(68, cwCwcustomerchargeweight);
	}

	public String getCwcwcustomerchargeweight() {
		return this.getField(68);
	}

	public void setIhsihscode(String ihsIhscode) {
		this.setField(69, ihsIhscode);
	}

	public String getIhsihscode() {
		return this.getField(69);
	}

	public void setIhsihsname(String ihsIhsname) {
		this.setField(70, ihsIhsname);
	}

	public String getIhsihsname() {
		return this.getField(70);
	}

	public void setCwcwvolumerate(int cwCwvolumerate) {
		this.setField(71, cwCwvolumerate);
	}

	public String getCwcwvolumerate() {
		return this.getField(71);
	}

	public void setCwcwtransfervolumerate(int cwCwtransfervolumerate) {
		this.setField(72, cwCwtransfervolumerate);
	}

	public String getCwcwtransfervolumerate() {
		return this.getField(72);
	}

	public void setHwhwremark(String hwHwremark) {
		this.setField(73, hwHwremark);
	}

	public String getHwhwremark() {
		return this.getField(73);
	}

	public void setCctctcode(String cctCtcode) {
		this.setField(74, cctCtcode);
	}

	public String getCctctcode() {
		return this.getField(74);
	}

	public void setCctctname(String cctCtname) {
		this.setField(75, cctCtname);
	}

	public String getCctctname() {
		return this.getField(75);
	}

	public void setCsopopid(Long csopOpid) {
		this.setField(76, csopOpid);
	}

	public String getCsopopid() {
		return this.getField(76);
	}

	public void setCsopopname(String csopOpname) {
		this.setField(77, csopOpname);
	}

	public String getCsopopname() {
		return this.getField(77);
	}

	public void setCtctename(String ctCtename) {
		this.setField(78, ctCtename);
	}

	public String getCtctename() {
		return this.getField(78);
	}

	public void setCtctsename(String ctCtsename) {
		this.setField(79, ctCtsename);
	}

	public String getCtctsename() {
		return this.getField(79);
	}

	public void setCwscwsename(String cwsCwsename) {
		this.setField(80, cwsCwsename);
	}

	public String getCwscwsename() {
		return this.getField(80);
	}

	public void setDdtdtename(String ddtDtename) {
		this.setField(81, ddtDtename);
	}

	public String getDdtdtename() {
		return this.getField(81);
	}

	public void setSdtdtename(String sdtDtename) {
		this.setField(82, sdtDtename);
	}

	public String getSdtdtename() {
		return this.getField(82);
	}

	public void setCddtdtename(String cddtDtename) {
		this.setField(83, cddtDtename);
	}

	public String getCddtdtename() {
		return this.getField(83);
	}

	public void setOdtdtename(String odtDtename) {
		this.setField(84, odtDtename);
	}

	public String getOdtdtename() {
		return this.getField(84);
	}

	public void setCwznvname(String cwZnvname) {
		this.setField(85, cwZnvname);
	}

	public String getCwznvname() {
		return this.getField(85);
	}

	public void setServerwbckbckcode(String serverwbckBckcode) {
		this.setField(86, serverwbckBckcode);
	}

	public String getServerwbckbckcode() {
		return this.getField(86);
	}

	public void setHwhwserverewbchangedsign(String hwHwserverewbchangedsign) {
		this.setField(87, hwHwserverewbchangedsign);
	}

	public String getHwhwserverewbchangedsign() {
		return this.getField(87);
	}

	public void setHwhwlabelprinttimes(Integer hwHwlabelprinttimes) {
		this.setField(88, hwHwlabelprinttimes);
	}

	public String getHwhwlabelprinttimes() {
		return this.getField(88);
	}

	public void setSubwbckbckcode(String subwbckBckcode) {
		this.setField(89, subwbckBckcode);
	}

	public String getSubwbckbckcode() {
		return this.getField(89);
	}

	public void setEraccount(String Eraccount) {
		this.setField(90, Eraccount);
	}

	public String getEraccount() {
		return this.getField(90);
	}

	public void setHwhwconsigneecity(String hwHwconsigneecity) {
		this.setField(91, hwHwconsigneecity);
	}

	public String getHwhwconsigneecity() {
		return this.getField(91);
	}

	public void setHwhwopidweightcheck(Long hwHwopidweightcheck) {
		this.setField(92, hwHwopidweightcheck);
	}

	public String getHwhwopidweightcheck() {
		return this.getField(92);
	}

	public void setHwhwweightcheckkind(String hwHwweightcheckkind) {
		this.setField(93, hwHwweightcheckkind);
	}

	public String getHwhwweightcheckkind() {
		return this.getField(93);
	}

	public void setHwhwweightcheckdate(Date hwHwweightcheckdate) {
		this.setField(94, hwHwweightcheckdate);
	}

	public String getHwhwweightcheckdate() {
		return this.getField(94);
	}

	public void setWcbwbwlabelcode(String wcbwBwlabelcode) {
		this.setField(95, wcbwBwlabelcode);
	}

	public String getWcbwbwlabelcode() {
		return this.getField(95);
	}

	public void setCwcwbillcounts(Integer cwCwbillcounts) {
		this.setField(96, cwCwbillcounts);
	}

	public String getCwcwbillcounts() {
		return this.getField(96);
	}

	public void setCwcwbagcounts(Integer cwCwbagcounts) {
		this.setField(97, cwCwbagcounts);
	}

	public String getCwcwbagcounts() {
		return this.getField(97);
	}

	public void setAbwvbwbvid(Long abwvBwbvid) {
		this.setField(98, abwvBwbvid);
	}

	public String getAbwvbwbvid() {
		return this.getField(98);
	}

	public void setDbwvbwbvid(Long dbwvBwbvid) {
		this.setField(99, dbwvBwbvid);
	}

	public String getDbwvbwbvid() {
		return this.getField(99);
	}

	public void setCwcwbatchwaybillsign(String cwCwbatchwaybillsign) {
		this.setField(100, cwCwbatchwaybillsign);
	}

	public String getCwcwbatchwaybillsign() {
		return this.getField(100);
	}

	public void setHwhwarrearsignout(String hwHwarrearsignout) {
		this.setField(101, hwHwarrearsignout);
	}

	public String getHwhwarrearsignout() {
		return this.getField(101);
	}

	public void setHwhwsewbchangedbywebsign(String hwHwsewbchangedbywebsign) {
		this.setField(102, hwHwsewbchangedbywebsign);
	}

	public String getHwhwsewbchangedbywebsign() {
		return this.getField(102);
	}

	public void setHwhwpaymentaccount(String hwHwpaymentaccount) {
		this.setField(103, hwHwpaymentaccount);
	}

	public String getHwhwpaymentaccount() {
		return this.getField(103);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
