package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillforpackageColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillforpackageColumns() {
		m_astrColumns = new String[53];
	}
	
	public WaybillforpackageColumns(Long bwBwcode, 
            String bwBwlabelcode, Long cwCwcode, 
            int cwCwpieces, String cwCwpostcodedestination, 
            BigDecimal cwCwgrossweight, BigDecimal cwCwchargeweight, 
            BigDecimal cwCwtransferpieces, BigDecimal cwCwtransfergrossweight, 
            BigDecimal cwCwtransferchargeweight, BigDecimal cwCwserverchargeweight, 
            String cwCwcustomerewbcode, String cwCwserverewbcode, 
            String cwCwewbcode, BigDecimal cwCwopidcreator, 
            Date cwCwcreatedate, BigDecimal cwCwopidmodifier, 
            Date cwCwmodifydate, BigDecimal cwCwcustomerchargeweight, 
            String cwZnvname, Integer cwCwbillcounts, 
            Integer cwCwbagcounts, String pkPkcode, 
            String pkPksname, String pkPksename, 
            String schChncode, String schChnsname, 
            String schChnsename, String cwsCwscode, 
            String cwsCwsname, String dtsigninDtcode, 
            String dtsigninDthubcode, String dtsigninDtename, 
            String ddtDtcode, String ddtDthubcode, 
            String ddtDtename, String odtDtcode, 
            String odtDthubcode, String odtDtename, 
            String eeEecode, String eeEesname, 
            String ccoCocode, String ccoCosname, 
            String ccoColabelcode, String scoCocode, 
            String scoCosname, String scoColabelcode, 
            String bwvBwbvserialno, String bwvBwbvbaglabelcode, 
            String bwvBwbvzonename, String bwvBwbvissuecontent, 
            String ihsIhscode, Long bwvBwbvid){
		m_astrColumns = new String[53];
		setBwbwcode(bwBwcode);
		setBwbwlabelcode(bwBwlabelcode);
		setCwcwcode(cwCwcode);
		setCwcwpieces(cwCwpieces);
		setCwcwpostcodedestination(cwCwpostcodedestination);
		setCwcwgrossweight(cwCwgrossweight);
		setCwcwchargeweight(cwCwchargeweight);
		setCwcwtransferpieces(cwCwtransferpieces);
		setCwcwtransfergrossweight(cwCwtransfergrossweight);
		setCwcwtransferchargeweight(cwCwtransferchargeweight);
		setCwcwserverchargeweight(cwCwserverchargeweight);
		setCwcwcustomerewbcode(cwCwcustomerewbcode);
		setCwcwserverewbcode(cwCwserverewbcode);
		setCwcwewbcode(cwCwewbcode);
		setCwcwopidcreator(cwCwopidcreator);
		setCwcwcreatedate(cwCwcreatedate);
		setCwcwopidmodifier(cwCwopidmodifier);
		setCwcwmodifydate(cwCwmodifydate);
		setCwcwcustomerchargeweight(cwCwcustomerchargeweight);
		setCwznvname(cwZnvname);
		setCwcwbillcounts(cwCwbillcounts);
		setCwcwbagcounts(cwCwbagcounts);
		setPkpkcode(pkPkcode);
		setPkpksname(pkPksname);
		setPkpksename(pkPksename);
		setSchchncode(schChncode);
		setSchchnsname(schChnsname);
		setSchchnsename(schChnsename);
		setCwscwscode(cwsCwscode);
		setCwscwsname(cwsCwsname);
		setDtsignindtcode(dtsigninDtcode);
		setDtsignindthubcode(dtsigninDthubcode);
		setDtsignindtename(dtsigninDtename);
		setDdtdtcode(ddtDtcode);
		setDdtdthubcode(ddtDthubcode);
		setDdtdtename(ddtDtename);
		setOdtdtcode(odtDtcode);
		setOdtdthubcode(odtDthubcode);
		setOdtdtename(odtDtename);
		setEeeecode(eeEecode);
		setEeeesname(eeEesname);
		setCcococode(ccoCocode);
		setCcocosname(ccoCosname);
		setCcocolabelcode(ccoColabelcode);
		setScococode(scoCocode);
		setScocosname(scoCosname);
		setScocolabelcode(scoColabelcode);
		setBwvbwbvserialno(bwvBwbvserialno);
		setBwvbwbvbaglabelcode(bwvBwbvbaglabelcode);
		setBwvbwbvzonename(bwvBwbvzonename);
		setBwvbwbvissuecontent(bwvBwbvissuecontent);
		setIhsihscode(ihsIhscode);
		setBwvbwbvid(bwvBwbvid);
	}

	public void setBwbwcode(Long bwBwcode) {
		this.setField(0, bwBwcode);
	}

	public String getBwbwcode() {
		return this.getField(0);
	}

	public void setBwbwlabelcode(String bwBwlabelcode) {
		this.setField(1, bwBwlabelcode);
	}

	public String getBwbwlabelcode() {
		return this.getField(1);
	}

	public void setCwcwcode(Long cwCwcode) {
		this.setField(2, cwCwcode);
	}

	public String getCwcwcode() {
		return this.getField(2);
	}

	public void setCwcwpieces(int cwCwpieces) {
		this.setField(3, cwCwpieces);
	}

	public String getCwcwpieces() {
		return this.getField(3);
	}

	public void setCwcwpostcodedestination(String cwCwpostcodedestination) {
		this.setField(4, cwCwpostcodedestination);
	}

	public String getCwcwpostcodedestination() {
		return this.getField(4);
	}

	public void setCwcwgrossweight(BigDecimal cwCwgrossweight) {
		this.setField(5, cwCwgrossweight);
	}

	public String getCwcwgrossweight() {
		return this.getField(5);
	}

	public void setCwcwchargeweight(BigDecimal cwCwchargeweight) {
		this.setField(6, cwCwchargeweight);
	}

	public String getCwcwchargeweight() {
		return this.getField(6);
	}

	public void setCwcwtransferpieces(BigDecimal cwCwtransferpieces) {
		this.setField(7, cwCwtransferpieces);
	}

	public String getCwcwtransferpieces() {
		return this.getField(7);
	}

	public void setCwcwtransfergrossweight(BigDecimal cwCwtransfergrossweight) {
		this.setField(8, cwCwtransfergrossweight);
	}

	public String getCwcwtransfergrossweight() {
		return this.getField(8);
	}

	public void setCwcwtransferchargeweight(BigDecimal cwCwtransferchargeweight) {
		this.setField(9, cwCwtransferchargeweight);
	}

	public String getCwcwtransferchargeweight() {
		return this.getField(9);
	}

	public void setCwcwserverchargeweight(BigDecimal cwCwserverchargeweight) {
		this.setField(10, cwCwserverchargeweight);
	}

	public String getCwcwserverchargeweight() {
		return this.getField(10);
	}

	public void setCwcwcustomerewbcode(String cwCwcustomerewbcode) {
		this.setField(11, cwCwcustomerewbcode);
	}

	public String getCwcwcustomerewbcode() {
		return this.getField(11);
	}

	public void setCwcwserverewbcode(String cwCwserverewbcode) {
		this.setField(12, cwCwserverewbcode);
	}

	public String getCwcwserverewbcode() {
		return this.getField(12);
	}

	public void setCwcwewbcode(String cwCwewbcode) {
		this.setField(13, cwCwewbcode);
	}

	public String getCwcwewbcode() {
		return this.getField(13);
	}

	public void setCwcwopidcreator(BigDecimal cwCwopidcreator) {
		this.setField(14, cwCwopidcreator);
	}

	public String getCwcwopidcreator() {
		return this.getField(14);
	}

	public void setCwcwcreatedate(Date cwCwcreatedate) {
		this.setField(15, cwCwcreatedate);
	}

	public String getCwcwcreatedate() {
		return this.getField(15);
	}

	public void setCwcwopidmodifier(BigDecimal cwCwopidmodifier) {
		this.setField(16, cwCwopidmodifier);
	}

	public String getCwcwopidmodifier() {
		return this.getField(16);
	}

	public void setCwcwmodifydate(Date cwCwmodifydate) {
		this.setField(17, cwCwmodifydate);
	}

	public String getCwcwmodifydate() {
		return this.getField(17);
	}

	public void setCwcwcustomerchargeweight(BigDecimal cwCwcustomerchargeweight) {
		this.setField(18, cwCwcustomerchargeweight);
	}

	public String getCwcwcustomerchargeweight() {
		return this.getField(18);
	}

	public void setCwznvname(String cwZnvname) {
		this.setField(19, cwZnvname);
	}

	public String getCwznvname() {
		return this.getField(19);
	}

	public void setCwcwbillcounts(Integer cwCwbillcounts) {
		this.setField(20, cwCwbillcounts);
	}

	public String getCwcwbillcounts() {
		return this.getField(20);
	}

	public void setCwcwbagcounts(Integer cwCwbagcounts) {
		this.setField(21, cwCwbagcounts);
	}

	public String getCwcwbagcounts() {
		return this.getField(21);
	}

	public void setPkpkcode(String pkPkcode) {
		this.setField(22, pkPkcode);
	}

	public String getPkpkcode() {
		return this.getField(22);
	}

	public void setPkpksname(String pkPksname) {
		this.setField(23, pkPksname);
	}

	public String getPkpksname() {
		return this.getField(23);
	}

	public void setPkpksename(String pkPksename) {
		this.setField(24, pkPksename);
	}

	public String getPkpksename() {
		return this.getField(24);
	}

	public void setSchchncode(String schChncode) {
		this.setField(25, schChncode);
	}

	public String getSchchncode() {
		return this.getField(25);
	}

	public void setSchchnsname(String schChnsname) {
		this.setField(26, schChnsname);
	}

	public String getSchchnsname() {
		return this.getField(26);
	}

	public void setSchchnsename(String schChnsename) {
		this.setField(27, schChnsename);
	}

	public String getSchchnsename() {
		return this.getField(27);
	}

	public void setCwscwscode(String cwsCwscode) {
		this.setField(28, cwsCwscode);
	}

	public String getCwscwscode() {
		return this.getField(28);
	}

	public void setCwscwsname(String cwsCwsname) {
		this.setField(29, cwsCwsname);
	}

	public String getCwscwsname() {
		return this.getField(29);
	}

	public void setDtsignindtcode(String dtsigninDtcode) {
		this.setField(30, dtsigninDtcode);
	}

	public String getDtsignindtcode() {
		return this.getField(30);
	}

	public void setDtsignindthubcode(String dtsigninDthubcode) {
		this.setField(31, dtsigninDthubcode);
	}

	public String getDtsignindthubcode() {
		return this.getField(31);
	}

	public void setDtsignindtename(String dtsigninDtename) {
		this.setField(32, dtsigninDtename);
	}

	public String getDtsignindtename() {
		return this.getField(32);
	}

	public void setDdtdtcode(String ddtDtcode) {
		this.setField(33, ddtDtcode);
	}

	public String getDdtdtcode() {
		return this.getField(33);
	}

	public void setDdtdthubcode(String ddtDthubcode) {
		this.setField(34, ddtDthubcode);
	}

	public String getDdtdthubcode() {
		return this.getField(34);
	}

	public void setDdtdtename(String ddtDtename) {
		this.setField(35, ddtDtename);
	}

	public String getDdtdtename() {
		return this.getField(35);
	}

	public void setOdtdtcode(String odtDtcode) {
		this.setField(36, odtDtcode);
	}

	public String getOdtdtcode() {
		return this.getField(36);
	}

	public void setOdtdthubcode(String odtDthubcode) {
		this.setField(37, odtDthubcode);
	}

	public String getOdtdthubcode() {
		return this.getField(37);
	}

	public void setOdtdtename(String odtDtename) {
		this.setField(38, odtDtename);
	}

	public String getOdtdtename() {
		return this.getField(38);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(39, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(39);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(40, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(40);
	}

	public void setCcococode(String ccoCocode) {
		this.setField(41, ccoCocode);
	}

	public String getCcococode() {
		return this.getField(41);
	}

	public void setCcocosname(String ccoCosname) {
		this.setField(42, ccoCosname);
	}

	public String getCcocosname() {
		return this.getField(42);
	}

	public void setCcocolabelcode(String ccoColabelcode) {
		this.setField(43, ccoColabelcode);
	}

	public String getCcocolabelcode() {
		return this.getField(43);
	}

	public void setScococode(String scoCocode) {
		this.setField(44, scoCocode);
	}

	public String getScococode() {
		return this.getField(44);
	}

	public void setScocosname(String scoCosname) {
		this.setField(45, scoCosname);
	}

	public String getScocosname() {
		return this.getField(45);
	}

	public void setScocolabelcode(String scoColabelcode) {
		this.setField(46, scoColabelcode);
	}

	public String getScocolabelcode() {
		return this.getField(46);
	}

	public void setBwvbwbvserialno(String bwvBwbvserialno) {
		this.setField(47, bwvBwbvserialno);
	}

	public String getBwvbwbvserialno() {
		return this.getField(47);
	}

	public void setBwvbwbvbaglabelcode(String bwvBwbvbaglabelcode) {
		this.setField(48, bwvBwbvbaglabelcode);
	}

	public String getBwvbwbvbaglabelcode() {
		return this.getField(48);
	}

	public void setBwvbwbvzonename(String bwvBwbvzonename) {
		this.setField(49, bwvBwbvzonename);
	}

	public String getBwvbwbvzonename() {
		return this.getField(49);
	}

	public void setBwvbwbvissuecontent(String bwvBwbvissuecontent) {
		this.setField(50, bwvBwbvissuecontent);
	}

	public String getBwvbwbvissuecontent() {
		return this.getField(50);
	}

	public void setIhsihscode(String ihsIhscode) {
		this.setField(51, ihsIhscode);
	}

	public String getIhsihscode() {
		return this.getField(51);
	}

	public void setBwvbwbvid(Long bwvBwbvid) {
		this.setField(52, bwvBwbvid);
	}

	public String getBwvbwbvid() {
		return this.getField(52);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
