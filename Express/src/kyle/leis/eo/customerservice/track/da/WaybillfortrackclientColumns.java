package kyle.leis.eo.customerservice.track.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillfortrackclientColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillfortrackclientColumns() {
		m_astrColumns = new String[59];
	}
	
	public WaybillfortrackclientColumns(String cwCw_code, 
            String cwCw_pieces, String cwCw_chargeweight, 
            String cwCw_serverchargeweight, String cwCw_customerewbcode, 
            String cwCw_serverewbcode, String cwCw_ewbcode, 
            String cwsCws_code, String cwsCws_name, 
            String pkPk_code, String pkPk_sname, 
            String ddtDt_code, String ddtDt_hubcode, 
            String cddtDt_code, String cddtDt_hubcode, 
            String odtDt_code, String odtDt_hubcode, 
            String pmPm_code, String pmPm_name, 
            String schnChn_code, String schnChn_sname, 
            String eeEe_code, String eeEe_sname, 
            String ctCt_code, String ctCt_name, 
            String ihsIhs_code, String ihsIhs_name, 
            String scoCo_code, String scoCo_sname, 
            String scoCo_labelcode, String ccoCo_code, 
            String ccoCo_sname, String ccoCo_labelcode, 
            String abwBw_code, String abwBw_labelcode, 
            String abwAdd_date, String dbwBw_code, 
            String dbwBw_labelcode, String dbwAdd_date, 
            String wbbtWbbt_latesttrackdesc, String wbbtWbbt_latestcslogdesc, 
            String wbbtWbbt_cslogcreatedate, String wbbtWbbt_signforuser, 
            String wbbtWbbt_signfordate, String wbtsWbts_code, 
            String wbtsWbts_name, String csopOp_id, 
            String csopOp_name, String wbtsWbts_ename, 
            String ddtDt_ename, String cddtDt_ename, 
            String odtDt_ename, String sopOp_id, 
            String sopOp_name, String wbbtWbbt_latesttrackdate, 
            String cctCt_code, String cctCt_name, 
            String Baglabelcode, String wbbtWbbt_latesttrackfetchdate){
		m_astrColumns = new String[59];
		setCwcw_code(cwCw_code);
		setCwcw_pieces(cwCw_pieces);
		setCwcw_chargeweight(cwCw_chargeweight);
		setCwcw_serverchargeweight(cwCw_serverchargeweight);
		setCwcw_customerewbcode(cwCw_customerewbcode);
		setCwcw_serverewbcode(cwCw_serverewbcode);
		setCwcw_ewbcode(cwCw_ewbcode);
		setCwscws_code(cwsCws_code);
		setCwscws_name(cwsCws_name);
		setPkpk_code(pkPk_code);
		setPkpk_sname(pkPk_sname);
		setDdtdt_code(ddtDt_code);
		setDdtdt_hubcode(ddtDt_hubcode);
		setCddtdt_code(cddtDt_code);
		setCddtdt_hubcode(cddtDt_hubcode);
		setOdtdt_code(odtDt_code);
		setOdtdt_hubcode(odtDt_hubcode);
		setPmpm_code(pmPm_code);
		setPmpm_name(pmPm_name);
		setSchnchn_code(schnChn_code);
		setSchnchn_sname(schnChn_sname);
		setEeee_code(eeEe_code);
		setEeee_sname(eeEe_sname);
		setCtct_code(ctCt_code);
		setCtct_name(ctCt_name);
		setIhsihs_code(ihsIhs_code);
		setIhsihs_name(ihsIhs_name);
		setScoco_code(scoCo_code);
		setScoco_sname(scoCo_sname);
		setScoco_labelcode(scoCo_labelcode);
		setCcoco_code(ccoCo_code);
		setCcoco_sname(ccoCo_sname);
		setCcoco_labelcode(ccoCo_labelcode);
		setAbwbw_code(abwBw_code);
		setAbwbw_labelcode(abwBw_labelcode);
		setAbwadd_date(abwAdd_date);
		setDbwbw_code(dbwBw_code);
		setDbwbw_labelcode(dbwBw_labelcode);
		setDbwadd_date(dbwAdd_date);
		setWbbtwbbt_latesttrackdesc(wbbtWbbt_latesttrackdesc);
		setWbbtwbbt_latestcslogdesc(wbbtWbbt_latestcslogdesc);
		setWbbtwbbt_cslogcreatedate(wbbtWbbt_cslogcreatedate);
		setWbbtwbbt_signforuser(wbbtWbbt_signforuser);
		setWbbtwbbt_signfordate(wbbtWbbt_signfordate);
		setWbtswbts_code(wbtsWbts_code);
		setWbtswbts_name(wbtsWbts_name);
		setCsopop_id(csopOp_id);
		setCsopop_name(csopOp_name);
		setWbtswbts_ename(wbtsWbts_ename);
		setDdtdt_ename(ddtDt_ename);
		setCddtdt_ename(cddtDt_ename);
		setOdtdt_ename(odtDt_ename);
		setSopop_id(sopOp_id);
		setSopop_name(sopOp_name);
		setWbbtwbbt_latesttrackdate(wbbtWbbt_latesttrackdate);
		setCctct_code(cctCt_code);
		setCctct_name(cctCt_name);
		setBaglabelcode(Baglabelcode);
		setWbbtwbbt_latesttrackfetchdate(wbbtWbbt_latesttrackfetchdate);
	}

	public void setCwcw_code(String cwCw_code) {
		this.setField(0, cwCw_code);
	}

	public String getCwcw_code() {
		return this.getField(0);
	}

	public void setCwcw_pieces(String cwCw_pieces) {
		this.setField(1, cwCw_pieces);
	}

	public String getCwcw_pieces() {
		return this.getField(1);
	}

	public void setCwcw_chargeweight(String cwCw_chargeweight) {
		this.setField(2, cwCw_chargeweight);
	}

	public String getCwcw_chargeweight() {
		return this.getField(2);
	}

	public void setCwcw_serverchargeweight(String cwCw_serverchargeweight) {
		this.setField(3, cwCw_serverchargeweight);
	}

	public String getCwcw_serverchargeweight() {
		return this.getField(3);
	}

	public void setCwcw_customerewbcode(String cwCw_customerewbcode) {
		this.setField(4, cwCw_customerewbcode);
	}

	public String getCwcw_customerewbcode() {
		return this.getField(4);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(5, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(5);
	}

	public void setCwcw_ewbcode(String cwCw_ewbcode) {
		this.setField(6, cwCw_ewbcode);
	}

	public String getCwcw_ewbcode() {
		return this.getField(6);
	}

	public void setCwscws_code(String cwsCws_code) {
		this.setField(7, cwsCws_code);
	}

	public String getCwscws_code() {
		return this.getField(7);
	}

	public void setCwscws_name(String cwsCws_name) {
		this.setField(8, cwsCws_name);
	}

	public String getCwscws_name() {
		return this.getField(8);
	}

	public void setPkpk_code(String pkPk_code) {
		this.setField(9, pkPk_code);
	}

	public String getPkpk_code() {
		return this.getField(9);
	}

	public void setPkpk_sname(String pkPk_sname) {
		this.setField(10, pkPk_sname);
	}

	public String getPkpk_sname() {
		return this.getField(10);
	}

	public void setDdtdt_code(String ddtDt_code) {
		this.setField(11, ddtDt_code);
	}

	public String getDdtdt_code() {
		return this.getField(11);
	}

	public void setDdtdt_hubcode(String ddtDt_hubcode) {
		this.setField(12, ddtDt_hubcode);
	}

	public String getDdtdt_hubcode() {
		return this.getField(12);
	}

	public void setCddtdt_code(String cddtDt_code) {
		this.setField(13, cddtDt_code);
	}

	public String getCddtdt_code() {
		return this.getField(13);
	}

	public void setCddtdt_hubcode(String cddtDt_hubcode) {
		this.setField(14, cddtDt_hubcode);
	}

	public String getCddtdt_hubcode() {
		return this.getField(14);
	}

	public void setOdtdt_code(String odtDt_code) {
		this.setField(15, odtDt_code);
	}

	public String getOdtdt_code() {
		return this.getField(15);
	}

	public void setOdtdt_hubcode(String odtDt_hubcode) {
		this.setField(16, odtDt_hubcode);
	}

	public String getOdtdt_hubcode() {
		return this.getField(16);
	}

	public void setPmpm_code(String pmPm_code) {
		this.setField(17, pmPm_code);
	}

	public String getPmpm_code() {
		return this.getField(17);
	}

	public void setPmpm_name(String pmPm_name) {
		this.setField(18, pmPm_name);
	}

	public String getPmpm_name() {
		return this.getField(18);
	}

	public void setSchnchn_code(String schnChn_code) {
		this.setField(19, schnChn_code);
	}

	public String getSchnchn_code() {
		return this.getField(19);
	}

	public void setSchnchn_sname(String schnChn_sname) {
		this.setField(20, schnChn_sname);
	}

	public String getSchnchn_sname() {
		return this.getField(20);
	}

	public void setEeee_code(String eeEe_code) {
		this.setField(21, eeEe_code);
	}

	public String getEeee_code() {
		return this.getField(21);
	}

	public void setEeee_sname(String eeEe_sname) {
		this.setField(22, eeEe_sname);
	}

	public String getEeee_sname() {
		return this.getField(22);
	}

	public void setCtct_code(String ctCt_code) {
		this.setField(23, ctCt_code);
	}

	public String getCtct_code() {
		return this.getField(23);
	}

	public void setCtct_name(String ctCt_name) {
		this.setField(24, ctCt_name);
	}

	public String getCtct_name() {
		return this.getField(24);
	}

	public void setIhsihs_code(String ihsIhs_code) {
		this.setField(25, ihsIhs_code);
	}

	public String getIhsihs_code() {
		return this.getField(25);
	}

	public void setIhsihs_name(String ihsIhs_name) {
		this.setField(26, ihsIhs_name);
	}

	public String getIhsihs_name() {
		return this.getField(26);
	}

	public void setScoco_code(String scoCo_code) {
		this.setField(27, scoCo_code);
	}

	public String getScoco_code() {
		return this.getField(27);
	}

	public void setScoco_sname(String scoCo_sname) {
		this.setField(28, scoCo_sname);
	}

	public String getScoco_sname() {
		return this.getField(28);
	}

	public void setScoco_labelcode(String scoCo_labelcode) {
		this.setField(29, scoCo_labelcode);
	}

	public String getScoco_labelcode() {
		return this.getField(29);
	}

	public void setCcoco_code(String ccoCo_code) {
		this.setField(30, ccoCo_code);
	}

	public String getCcoco_code() {
		return this.getField(30);
	}

	public void setCcoco_sname(String ccoCo_sname) {
		this.setField(31, ccoCo_sname);
	}

	public String getCcoco_sname() {
		return this.getField(31);
	}

	public void setCcoco_labelcode(String ccoCo_labelcode) {
		this.setField(32, ccoCo_labelcode);
	}

	public String getCcoco_labelcode() {
		return this.getField(32);
	}

	public void setAbwbw_code(String abwBw_code) {
		this.setField(33, abwBw_code);
	}

	public String getAbwbw_code() {
		return this.getField(33);
	}

	public void setAbwbw_labelcode(String abwBw_labelcode) {
		this.setField(34, abwBw_labelcode);
	}

	public String getAbwbw_labelcode() {
		return this.getField(34);
	}

	public void setAbwadd_date(String abwAdd_date) {
		this.setField(35, abwAdd_date);
	}

	public String getAbwadd_date() {
		return this.getField(35);
	}

	public void setDbwbw_code(String dbwBw_code) {
		this.setField(36, dbwBw_code);
	}

	public String getDbwbw_code() {
		return this.getField(36);
	}

	public void setDbwbw_labelcode(String dbwBw_labelcode) {
		this.setField(37, dbwBw_labelcode);
	}

	public String getDbwbw_labelcode() {
		return this.getField(37);
	}

	public void setDbwadd_date(String dbwAdd_date) {
		this.setField(38, dbwAdd_date);
	}

	public String getDbwadd_date() {
		return this.getField(38);
	}

	public void setWbbtwbbt_latesttrackdesc(String wbbtWbbt_latesttrackdesc) {
		this.setField(39, wbbtWbbt_latesttrackdesc);
	}

	public String getWbbtwbbt_latesttrackdesc() {
		return this.getField(39);
	}

	public void setWbbtwbbt_latestcslogdesc(String wbbtWbbt_latestcslogdesc) {
		this.setField(40, wbbtWbbt_latestcslogdesc);
	}

	public String getWbbtwbbt_latestcslogdesc() {
		return this.getField(40);
	}

	public void setWbbtwbbt_cslogcreatedate(String wbbtWbbt_cslogcreatedate) {
		this.setField(41, wbbtWbbt_cslogcreatedate);
	}

	public String getWbbtwbbt_cslogcreatedate() {
		return this.getField(41);
	}

	public void setWbbtwbbt_signforuser(String wbbtWbbt_signforuser) {
		this.setField(42, wbbtWbbt_signforuser);
	}

	public String getWbbtwbbt_signforuser() {
		return this.getField(42);
	}

	public void setWbbtwbbt_signfordate(String wbbtWbbt_signfordate) {
		this.setField(43, wbbtWbbt_signfordate);
	}

	public String getWbbtwbbt_signfordate() {
		return this.getField(43);
	}

	public void setWbtswbts_code(String wbtsWbts_code) {
		this.setField(44, wbtsWbts_code);
	}

	public String getWbtswbts_code() {
		return this.getField(44);
	}

	public void setWbtswbts_name(String wbtsWbts_name) {
		this.setField(45, wbtsWbts_name);
	}

	public String getWbtswbts_name() {
		return this.getField(45);
	}

	public void setCsopop_id(String csopOp_id) {
		this.setField(46, csopOp_id);
	}

	public String getCsopop_id() {
		return this.getField(46);
	}

	public void setCsopop_name(String csopOp_name) {
		this.setField(47, csopOp_name);
	}

	public String getCsopop_name() {
		return this.getField(47);
	}

	public void setWbtswbts_ename(String wbtsWbts_ename) {
		this.setField(48, wbtsWbts_ename);
	}

	public String getWbtswbts_ename() {
		return this.getField(48);
	}

	public void setDdtdt_ename(String ddtDt_ename) {
		this.setField(49, ddtDt_ename);
	}

	public String getDdtdt_ename() {
		return this.getField(49);
	}

	public void setCddtdt_ename(String cddtDt_ename) {
		this.setField(50, cddtDt_ename);
	}

	public String getCddtdt_ename() {
		return this.getField(50);
	}

	public void setOdtdt_ename(String odtDt_ename) {
		this.setField(51, odtDt_ename);
	}

	public String getOdtdt_ename() {
		return this.getField(51);
	}

	public void setSopop_id(String sopOp_id) {
		this.setField(52, sopOp_id);
	}

	public String getSopop_id() {
		return this.getField(52);
	}

	public void setSopop_name(String sopOp_name) {
		this.setField(53, sopOp_name);
	}

	public String getSopop_name() {
		return this.getField(53);
	}

	public void setWbbtwbbt_latesttrackdate(String wbbtWbbt_latesttrackdate) {
		this.setField(54, wbbtWbbt_latesttrackdate);
	}

	public String getWbbtwbbt_latesttrackdate() {
		return this.getField(54);
	}

	public void setCctct_code(String cctCt_code) {
		this.setField(55, cctCt_code);
	}

	public String getCctct_code() {
		return this.getField(55);
	}

	public void setCctct_name(String cctCt_name) {
		this.setField(56, cctCt_name);
	}

	public String getCctct_name() {
		return this.getField(56);
	}

	public void setBaglabelcode(String Baglabelcode) {
		this.setField(57, Baglabelcode);
	}

	public String getBaglabelcode() {
		return this.getField(57);
	}

	public void setWbbtwbbt_latesttrackfetchdate(String wbbtWbbt_latesttrackfetchdate) {
		this.setField(58, wbbtWbbt_latesttrackfetchdate);
	}

	public String getWbbtwbbt_latesttrackfetchdate() {
		return this.getField(58);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
