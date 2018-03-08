package kyle.leis.eo.customerservice.track.dax;

import java.math.BigDecimal;
import java.util.Date;

import kyle.leis.eo.customerservice.track.da.WaybillfortrackColumns;

public class WaybillfortrackColumnsEX extends WaybillfortrackColumns {
	private static final long serialVersionUID = 1L;
	
	public WaybillfortrackColumnsEX(){
		m_astrColumns = new String[56];
	}
	
	public WaybillfortrackColumnsEX(Long cwCwcode, 
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
            String cddtDtname){
		m_astrColumns = new String[56];
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
		setCddtdtname(cddtDtname);
	}
	
	public void setCddtdtname(String cddtDtname){
		setField(55, cddtDtname);
	}
	
	public String getCddtdtname(){
		return getField(55);
	}

}
