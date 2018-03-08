package kyle.leis.eo.customerservice.track.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillfortrackColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillfortrackColumns() {
		m_astrColumns = new String[57];
	}
	
	public WaybillfortrackColumns(Long cwCwcode, 
            int cwCwpieces, BigDecimal cwCwchargeweight, 
            BigDecimal cwCwserverchargeweight, String cwCwcustomerewbcode, 
            String cwCwserverewbcode, String cwCwewbcode, 
            String cwsCwscode, String cwsCwsname, 
            String pkPkcode, String pkPksname, 
            String ddtDtcode, String ddtDthubcode, 
            String cddtDtcode, String cddtDthubcode, 
            String odtDtcode, String odtDthubcode, 
            String pmPmcode, String pmPmname, 
            String schnChncode, String schnChnsname, 
            String eeEecode, String eeEesname, 
            String ctCtcode, String ctCtname, 
            String ihsIhscode, String ihsIhsname, 
            String scoCocode, String scoCosname, 
            String scoColabelcode, String ccoCocode, 
            String ccoCosname, String ccoColabelcode, 
            Long abwBwcode, String abwBwlabelcode, 
            Date abwAdddate, Long dbwBwcode, 
            String dbwBwlabelcode, Date dbwAdddate, 
            String wbbtWbbtlatesttrackdesc, String wbbtWbbtlatestcslogdesc, 
            Date wbbtWbbtcslogcreatedate, String wbbtWbbtsignforuser, 
            Date wbbtWbbtsignfordate, String wbtsWbtscode, 
            String wbtsWbtsname, Long csopOpid, 
            String csopOpname, String wbtsWbtsename, 
            String ddtDtename, String cddtDtename, 
            String odtDtename, Long sopOpid, 
            String sopOpname, Date wbbtWbbtlatesttrackdate, 
            String cctCtcode, String cctCtname){
		m_astrColumns = new String[57];
		setCwcwcode(cwCwcode);
		setCwcwpieces(cwCwpieces);
		setCwcwchargeweight(cwCwchargeweight);
		setCwcwserverchargeweight(cwCwserverchargeweight);
		setCwcwcustomerewbcode(cwCwcustomerewbcode);
		setCwcwserverewbcode(cwCwserverewbcode);
		setCwcwewbcode(cwCwewbcode);
		setCwscwscode(cwsCwscode);
		setCwscwsname(cwsCwsname);
		setPkpkcode(pkPkcode);
		setPkpksname(pkPksname);
		setDdtdtcode(ddtDtcode);
		setDdtdthubcode(ddtDthubcode);
		setCddtdtcode(cddtDtcode);
		setCddtdthubcode(cddtDthubcode);
		setOdtdtcode(odtDtcode);
		setOdtdthubcode(odtDthubcode);
		setPmpmcode(pmPmcode);
		setPmpmname(pmPmname);
		setSchnchncode(schnChncode);
		setSchnchnsname(schnChnsname);
		setEeeecode(eeEecode);
		setEeeesname(eeEesname);
		setCtctcode(ctCtcode);
		setCtctname(ctCtname);
		setIhsihscode(ihsIhscode);
		setIhsihsname(ihsIhsname);
		setScococode(scoCocode);
		setScocosname(scoCosname);
		setScocolabelcode(scoColabelcode);
		setCcococode(ccoCocode);
		setCcocosname(ccoCosname);
		setCcocolabelcode(ccoColabelcode);
		setAbwbwcode(abwBwcode);
		setAbwbwlabelcode(abwBwlabelcode);
		setAbwadddate(abwAdddate);
		setDbwbwcode(dbwBwcode);
		setDbwbwlabelcode(dbwBwlabelcode);
		setDbwadddate(dbwAdddate);
		setWbbtwbbtlatesttrackdesc(wbbtWbbtlatesttrackdesc);
		setWbbtwbbtlatestcslogdesc(wbbtWbbtlatestcslogdesc);
		setWbbtwbbtcslogcreatedate(wbbtWbbtcslogcreatedate);
		setWbbtwbbtsignforuser(wbbtWbbtsignforuser);
		setWbbtwbbtsignfordate(wbbtWbbtsignfordate);
		setWbtswbtscode(wbtsWbtscode);
		setWbtswbtsname(wbtsWbtsname);
		setCsopopid(csopOpid);
		setCsopopname(csopOpname);
		setWbtswbtsename(wbtsWbtsename);
		setDdtdtename(ddtDtename);
		setCddtdtename(cddtDtename);
		setOdtdtename(odtDtename);
		setSopopid(sopOpid);
		setSopopname(sopOpname);
		setWbbtwbbtlatesttrackdate(wbbtWbbtlatesttrackdate);
		setCctctcode(cctCtcode);
		setCctctname(cctCtname);
	}

	public void setCwcwcode(Long cwCwcode) {
		this.setField(0, cwCwcode);
	}

	public String getCwcwcode() {
		return this.getField(0);
	}

	public void setCwcwpieces(int cwCwpieces) {
		this.setField(1, cwCwpieces);
	}

	public String getCwcwpieces() {
		return this.getField(1);
	}

	public void setCwcwchargeweight(BigDecimal cwCwchargeweight) {
		this.setField(2, cwCwchargeweight);
	}

	public String getCwcwchargeweight() {
		return this.getField(2);
	}

	public void setCwcwserverchargeweight(BigDecimal cwCwserverchargeweight) {
		this.setField(3, cwCwserverchargeweight);
	}

	public String getCwcwserverchargeweight() {
		return this.getField(3);
	}

	public void setCwcwcustomerewbcode(String cwCwcustomerewbcode) {
		this.setField(4, cwCwcustomerewbcode);
	}

	public String getCwcwcustomerewbcode() {
		return this.getField(4);
	}

	public void setCwcwserverewbcode(String cwCwserverewbcode) {
		this.setField(5, cwCwserverewbcode);
	}

	public String getCwcwserverewbcode() {
		return this.getField(5);
	}

	public void setCwcwewbcode(String cwCwewbcode) {
		this.setField(6, cwCwewbcode);
	}

	public String getCwcwewbcode() {
		return this.getField(6);
	}

	public void setCwscwscode(String cwsCwscode) {
		this.setField(7, cwsCwscode);
	}

	public String getCwscwscode() {
		return this.getField(7);
	}

	public void setCwscwsname(String cwsCwsname) {
		this.setField(8, cwsCwsname);
	}

	public String getCwscwsname() {
		return this.getField(8);
	}

	public void setPkpkcode(String pkPkcode) {
		this.setField(9, pkPkcode);
	}

	public String getPkpkcode() {
		return this.getField(9);
	}

	public void setPkpksname(String pkPksname) {
		this.setField(10, pkPksname);
	}

	public String getPkpksname() {
		return this.getField(10);
	}

	public void setDdtdtcode(String ddtDtcode) {
		this.setField(11, ddtDtcode);
	}

	public String getDdtdtcode() {
		return this.getField(11);
	}

	public void setDdtdthubcode(String ddtDthubcode) {
		this.setField(12, ddtDthubcode);
	}

	public String getDdtdthubcode() {
		return this.getField(12);
	}

	public void setCddtdtcode(String cddtDtcode) {
		this.setField(13, cddtDtcode);
	}

	public String getCddtdtcode() {
		return this.getField(13);
	}

	public void setCddtdthubcode(String cddtDthubcode) {
		this.setField(14, cddtDthubcode);
	}

	public String getCddtdthubcode() {
		return this.getField(14);
	}

	public void setOdtdtcode(String odtDtcode) {
		this.setField(15, odtDtcode);
	}

	public String getOdtdtcode() {
		return this.getField(15);
	}

	public void setOdtdthubcode(String odtDthubcode) {
		this.setField(16, odtDthubcode);
	}

	public String getOdtdthubcode() {
		return this.getField(16);
	}

	public void setPmpmcode(String pmPmcode) {
		this.setField(17, pmPmcode);
	}

	public String getPmpmcode() {
		return this.getField(17);
	}

	public void setPmpmname(String pmPmname) {
		this.setField(18, pmPmname);
	}

	public String getPmpmname() {
		return this.getField(18);
	}

	public void setSchnchncode(String schnChncode) {
		this.setField(19, schnChncode);
	}

	public String getSchnchncode() {
		return this.getField(19);
	}

	public void setSchnchnsname(String schnChnsname) {
		this.setField(20, schnChnsname);
	}

	public String getSchnchnsname() {
		return this.getField(20);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(21, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(21);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(22, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(22);
	}

	public void setCtctcode(String ctCtcode) {
		this.setField(23, ctCtcode);
	}

	public String getCtctcode() {
		return this.getField(23);
	}

	public void setCtctname(String ctCtname) {
		this.setField(24, ctCtname);
	}

	public String getCtctname() {
		return this.getField(24);
	}

	public void setIhsihscode(String ihsIhscode) {
		this.setField(25, ihsIhscode);
	}

	public String getIhsihscode() {
		return this.getField(25);
	}

	public void setIhsihsname(String ihsIhsname) {
		this.setField(26, ihsIhsname);
	}

	public String getIhsihsname() {
		return this.getField(26);
	}

	public void setScococode(String scoCocode) {
		this.setField(27, scoCocode);
	}

	public String getScococode() {
		return this.getField(27);
	}

	public void setScocosname(String scoCosname) {
		this.setField(28, scoCosname);
	}

	public String getScocosname() {
		return this.getField(28);
	}

	public void setScocolabelcode(String scoColabelcode) {
		this.setField(29, scoColabelcode);
	}

	public String getScocolabelcode() {
		return this.getField(29);
	}

	public void setCcococode(String ccoCocode) {
		this.setField(30, ccoCocode);
	}

	public String getCcococode() {
		return this.getField(30);
	}

	public void setCcocosname(String ccoCosname) {
		this.setField(31, ccoCosname);
	}

	public String getCcocosname() {
		return this.getField(31);
	}

	public void setCcocolabelcode(String ccoColabelcode) {
		this.setField(32, ccoColabelcode);
	}

	public String getCcocolabelcode() {
		return this.getField(32);
	}

	public void setAbwbwcode(Long abwBwcode) {
		this.setField(33, abwBwcode);
	}

	public String getAbwbwcode() {
		return this.getField(33);
	}

	public void setAbwbwlabelcode(String abwBwlabelcode) {
		this.setField(34, abwBwlabelcode);
	}

	public String getAbwbwlabelcode() {
		return this.getField(34);
	}

	public void setAbwadddate(Date abwAdddate) {
		this.setField(35, abwAdddate);
	}

	public String getAbwadddate() {
		return this.getField(35);
	}

	public void setDbwbwcode(Long dbwBwcode) {
		this.setField(36, dbwBwcode);
	}

	public String getDbwbwcode() {
		return this.getField(36);
	}

	public void setDbwbwlabelcode(String dbwBwlabelcode) {
		this.setField(37, dbwBwlabelcode);
	}

	public String getDbwbwlabelcode() {
		return this.getField(37);
	}

	public void setDbwadddate(Date dbwAdddate) {
		this.setField(38, dbwAdddate);
	}

	public String getDbwadddate() {
		return this.getField(38);
	}

	public void setWbbtwbbtlatesttrackdesc(String wbbtWbbtlatesttrackdesc) {
		this.setField(39, wbbtWbbtlatesttrackdesc);
	}

	public String getWbbtwbbtlatesttrackdesc() {
		return this.getField(39);
	}

	public void setWbbtwbbtlatestcslogdesc(String wbbtWbbtlatestcslogdesc) {
		this.setField(40, wbbtWbbtlatestcslogdesc);
	}

	public String getWbbtwbbtlatestcslogdesc() {
		return this.getField(40);
	}

	public void setWbbtwbbtcslogcreatedate(Date wbbtWbbtcslogcreatedate) {
		this.setField(41, wbbtWbbtcslogcreatedate);
	}

	public String getWbbtwbbtcslogcreatedate() {
		return this.getField(41);
	}

	public void setWbbtwbbtsignforuser(String wbbtWbbtsignforuser) {
		this.setField(42, wbbtWbbtsignforuser);
	}

	public String getWbbtwbbtsignforuser() {
		return this.getField(42);
	}

	public void setWbbtwbbtsignfordate(Date wbbtWbbtsignfordate) {
		this.setField(43, wbbtWbbtsignfordate);
	}

	public String getWbbtwbbtsignfordate() {
		return this.getField(43);
	}

	public void setWbtswbtscode(String wbtsWbtscode) {
		this.setField(44, wbtsWbtscode);
	}

	public String getWbtswbtscode() {
		return this.getField(44);
	}

	public void setWbtswbtsname(String wbtsWbtsname) {
		this.setField(45, wbtsWbtsname);
	}

	public String getWbtswbtsname() {
		return this.getField(45);
	}

	public void setCsopopid(Long csopOpid) {
		this.setField(46, csopOpid);
	}

	public String getCsopopid() {
		return this.getField(46);
	}

	public void setCsopopname(String csopOpname) {
		this.setField(47, csopOpname);
	}

	public String getCsopopname() {
		return this.getField(47);
	}

	public void setWbtswbtsename(String wbtsWbtsename) {
		this.setField(48, wbtsWbtsename);
	}

	public String getWbtswbtsename() {
		return this.getField(48);
	}

	public void setDdtdtename(String ddtDtename) {
		this.setField(49, ddtDtename);
	}

	public String getDdtdtename() {
		return this.getField(49);
	}

	public void setCddtdtename(String cddtDtename) {
		this.setField(50, cddtDtename);
	}

	public String getCddtdtename() {
		return this.getField(50);
	}

	public void setOdtdtename(String odtDtename) {
		this.setField(51, odtDtename);
	}

	public String getOdtdtename() {
		return this.getField(51);
	}

	public void setSopopid(Long sopOpid) {
		this.setField(52, sopOpid);
	}

	public String getSopopid() {
		return this.getField(52);
	}

	public void setSopopname(String sopOpname) {
		this.setField(53, sopOpname);
	}

	public String getSopopname() {
		return this.getField(53);
	}

	public void setWbbtwbbtlatesttrackdate(Date wbbtWbbtlatesttrackdate) {
		this.setField(54, wbbtWbbtlatesttrackdate);
	}

	public String getWbbtwbbtlatesttrackdate() {
		return this.getField(54);
	}

	public void setCctctcode(String cctCtcode) {
		this.setField(55, cctCtcode);
	}

	public String getCctctcode() {
		return this.getField(55);
	}

	public void setCctctname(String cctCtname) {
		this.setField(56, cctCtname);
	}

	public String getCctctname() {
		return this.getField(56);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
