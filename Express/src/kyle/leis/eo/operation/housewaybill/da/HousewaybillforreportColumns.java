package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class HousewaybillforreportColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public HousewaybillforreportColumns() {
		m_astrColumns = new String[98];
	}
	
	public HousewaybillforreportColumns(String hwCw_code, 
            String hwHw_signindate, String hwHw_op_id_signin, 
            String siopOp_name, String hwHw_op_id_weighting, 
            String hwHw_signoutdate, String hwHw_op_id_signout, 
            String soopOp_name, String hwHw_recorddate, 
            String hwHw_op_id_record, String rcopOp_name, 
            String hwHw_op_id_packing, String hwHw_insurancevalue, 
            String hwHw_consigneepostcode, String cwCw_postcode_destination, 
            String cwCw_pieces, String cwCw_grossweight, 
            String cwCw_chargeweight, String cwCw_transferpieces, 
            String cwCw_transfergrossweight, String cwCw_transferchargeweight, 
            String cwCw_serverchargeweight, String cwCw_customerewbcode, 
            String cwCw_serverewbcode, String cwCw_ewbcode, 
            String cwCw_transfervolumeweight, String cwsCws_code, 
            String cwsCws_name, String eeEe_code, 
            String eeEe_sname, String eeEe_esname, 
            String pkPk_code, String pkPk_name, 
            String pkPk_sname, String pkPk_sename, 
            String ddtDt_code, String ddtDt_hubcode, 
            String ddtDt_name, String sdtDt_code, 
            String sdtDt_hubcode, String sdtDt_name, 
            String cddtDt_code, String cddtDt_hubcode, 
            String cddtDt_name, String odtDt_code, 
            String odtDt_hubcode, String odtDt_name, 
            String pmPm_code, String pmPm_name, 
            String schnChn_code, String schnChn_name, 
            String schnChn_sname, String cchnChn_code, 
            String cchnChn_name, String cchnChn_sname, 
            String ctCt_code, String ctCt_name, 
            String scoCo_code, String scoCo_name, 
            String scoCo_sname, String scoCo_labelcode, 
            String ccoCo_code, String ccoCo_name, 
            String ccoCo_sname, String ccoCo_labelcode, 
            String abwBw_code, String abwBw_labelcode, 
            String abwAdd_date, String dbwBw_code, 
            String dbwBw_labelcode, String dbwAdd_date, 
            String cwCw_customerchargeweight, String ihsIhs_code, 
            String ihsIhs_name, String cwCw_volumerate, 
            String cwCw_transfervolumerate, String hwHw_remark, 
            String cctCt_code, String cctCt_name, 
            String csopOp_id, String csopOp_name, 
            String Baglabelcode, String ssopOp_id, 
            String ssopOp_name, String cwZnv_name, 
            String Cobalanceamount, String Volumeweight, 
            String hwHw_op_id_weightcheck, String hwopOp_name, 
            String hwHw_weightcheckdate, 
            String Hw_weightcheckkind, String wcbwBw_labelcode, 
            String cwCw_billcounts, String cwCw_bagcounts,
            String dunopOp_id, String dunopOp_name,
            String specialtype, String Piecelhw){
		m_astrColumns = new String[98];
		setHwcw_code(hwCw_code);
		setHwhw_signindate(hwHw_signindate);
		setHwhw_op_id_signin(hwHw_op_id_signin);
		setSiopop_name(siopOp_name);
		setHwhw_op_id_weighting(hwHw_op_id_weighting);
		setHwhw_signoutdate(hwHw_signoutdate);
		setHwhw_op_id_signout(hwHw_op_id_signout);
		setSoopop_name(soopOp_name);
		setHwhw_recorddate(hwHw_recorddate);
		setHwhw_op_id_record(hwHw_op_id_record);
		setRcopop_name(rcopOp_name);
		setHwhw_op_id_packing(hwHw_op_id_packing);
		setHwhw_insurancevalue(hwHw_insurancevalue);
		setHwhw_consigneepostcode(hwHw_consigneepostcode);
		setCwcw_postcode_destination(cwCw_postcode_destination);
		setCwcw_pieces(cwCw_pieces);
		setCwcw_grossweight(cwCw_grossweight);
		setCwcw_chargeweight(cwCw_chargeweight);
		setCwcw_transferpieces(cwCw_transferpieces);
		setCwcw_transfergrossweight(cwCw_transfergrossweight);
		setCwcw_transferchargeweight(cwCw_transferchargeweight);
		setCwcw_serverchargeweight(cwCw_serverchargeweight);
		setCwcw_customerewbcode(cwCw_customerewbcode);
		setCwcw_serverewbcode(cwCw_serverewbcode);
		setCwcw_ewbcode(cwCw_ewbcode);
		setCwcw_transfervolumeweight(cwCw_transfervolumeweight);
		setCwscws_code(cwsCws_code);
		setCwscws_name(cwsCws_name);
		setEeee_code(eeEe_code);
		setEeee_sname(eeEe_sname);
		setEeee_esname(eeEe_esname);
		setPkpk_code(pkPk_code);
		setPkpk_name(pkPk_name);
		setPkpk_sname(pkPk_sname);
		setPkpk_sename(pkPk_sename);
		setDdtdt_code(ddtDt_code);
		setDdtdt_hubcode(ddtDt_hubcode);
		setDdtdt_name(ddtDt_name);
		setSdtdt_code(sdtDt_code);
		setSdtdt_hubcode(sdtDt_hubcode);
		setSdtdt_name(sdtDt_name);
		setCddtdt_code(cddtDt_code);
		setCddtdt_hubcode(cddtDt_hubcode);
		setCddtdt_name(cddtDt_name);
		setOdtdt_code(odtDt_code);
		setOdtdt_hubcode(odtDt_hubcode);
		setOdtdt_name(odtDt_name);
		setPmpm_code(pmPm_code);
		setPmpm_name(pmPm_name);
		setSchnchn_code(schnChn_code);
		setSchnchn_name(schnChn_name);
		setSchnchn_sname(schnChn_sname);
		setCchnchn_code(cchnChn_code);
		setCchnchn_name(cchnChn_name);
		setCchnchn_sname(cchnChn_sname);
		setCtct_code(ctCt_code);
		setCtct_name(ctCt_name);
		setScoco_code(scoCo_code);
		setScoco_name(scoCo_name);
		setScoco_sname(scoCo_sname);
		setScoco_labelcode(scoCo_labelcode);
		setCcoco_code(ccoCo_code);
		setCcoco_name(ccoCo_name);
		setCcoco_sname(ccoCo_sname);
		setCcoco_labelcode(ccoCo_labelcode);
		setAbwbw_code(abwBw_code);
		setAbwbw_labelcode(abwBw_labelcode);
		setAbwadd_date(abwAdd_date);
		setDbwbw_code(dbwBw_code);
		setDbwbw_labelcode(dbwBw_labelcode);
		setDbwadd_date(dbwAdd_date);
		setCwcw_customerchargeweight(cwCw_customerchargeweight);
		setIhsihs_code(ihsIhs_code);
		setIhsihs_name(ihsIhs_name);
		setCwcw_volumerate(cwCw_volumerate);
		setCwcw_transfervolumerate(cwCw_transfervolumerate);
		setHwhw_remark(hwHw_remark);
		setCctct_code(cctCt_code);
		setCctct_name(cctCt_name);
		setCsopop_id(csopOp_id);
		setCsopop_name(csopOp_name);
		setBaglabelcode(Baglabelcode);
		setSsopop_id(ssopOp_id);
		setSsopop_name(ssopOp_name);
		setCwznv_name(cwZnv_name);
		setCobalanceamount(Cobalanceamount);
		setVolumeweight(Volumeweight);
		setHwhw_op_id_weightcheck(hwHw_op_id_weightcheck);
		setHwopop_name(hwopOp_name);
		setHwhw_weightcheckdate(hwHw_weightcheckdate);
		setHw_weightcheckkind(Hw_weightcheckkind);
		setWcbwbw_labelcode(wcbwBw_labelcode);
		setCwcw_billcounts(cwCw_billcounts);
		setCwcw_bagcounts(cwCw_bagcounts);
		setDunopop_id(dunopOp_id);
		setDunopop_name(dunopOp_name);
		setSpecialtype(specialtype);
		setPiecelhw(Piecelhw);
	}

	public void setHwcw_code(String hwCw_code) {
		this.setField(0, hwCw_code);
	}

	public String getHwcw_code() {
		return this.getField(0);
	}

	public void setHwhw_signindate(String hwHw_signindate) {
		this.setField(1, hwHw_signindate);
	}

	public String getHwhw_signindate() {
		return this.getField(1);
	}

	public void setHwhw_op_id_signin(String hwHw_op_id_signin) {
		this.setField(2, hwHw_op_id_signin);
	}

	public String getHwhw_op_id_signin() {
		return this.getField(2);
	}

	public void setSiopop_name(String siopOp_name) {
		this.setField(3, siopOp_name);
	}

	public String getSiopop_name() {
		return this.getField(3);
	}

	public void setHwhw_op_id_weighting(String hwHw_op_id_weighting) {
		this.setField(4, hwHw_op_id_weighting);
	}

	public String getHwhw_op_id_weighting() {
		return this.getField(4);
	}

	public void setHwhw_signoutdate(String hwHw_signoutdate) {
		this.setField(5, hwHw_signoutdate);
	}

	public String getHwhw_signoutdate() {
		return this.getField(5);
	}

	public void setHwhw_op_id_signout(String hwHw_op_id_signout) {
		this.setField(6, hwHw_op_id_signout);
	}

	public String getHwhw_op_id_signout() {
		return this.getField(6);
	}

	public void setSoopop_name(String soopOp_name) {
		this.setField(7, soopOp_name);
	}

	public String getSoopop_name() {
		return this.getField(7);
	}

	public void setHwhw_recorddate(String hwHw_recorddate) {
		this.setField(8, hwHw_recorddate);
	}

	public String getHwhw_recorddate() {
		return this.getField(8);
	}

	public void setHwhw_op_id_record(String hwHw_op_id_record) {
		this.setField(9, hwHw_op_id_record);
	}

	public String getHwhw_op_id_record() {
		return this.getField(9);
	}

	public void setRcopop_name(String rcopOp_name) {
		this.setField(10, rcopOp_name);
	}

	public String getRcopop_name() {
		return this.getField(10);
	}

	public void setHwhw_op_id_packing(String hwHw_op_id_packing) {
		this.setField(11, hwHw_op_id_packing);
	}

	public String getHwhw_op_id_packing() {
		return this.getField(11);
	}

	public void setHwhw_insurancevalue(String hwHw_insurancevalue) {
		this.setField(12, hwHw_insurancevalue);
	}

	public String getHwhw_insurancevalue() {
		return this.getField(12);
	}

	public void setHwhw_consigneepostcode(String hwHw_consigneepostcode) {
		this.setField(13, hwHw_consigneepostcode);
	}

	public String getHwhw_consigneepostcode() {
		return this.getField(13);
	}

	public void setCwcw_postcode_destination(String cwCw_postcode_destination) {
		this.setField(14, cwCw_postcode_destination);
	}

	public String getCwcw_postcode_destination() {
		return this.getField(14);
	}

	public void setCwcw_pieces(String cwCw_pieces) {
		this.setField(15, cwCw_pieces);
	}

	public String getCwcw_pieces() {
		return this.getField(15);
	}

	public void setCwcw_grossweight(String cwCw_grossweight) {
		this.setField(16, cwCw_grossweight);
	}

	public String getCwcw_grossweight() {
		return this.getField(16);
	}

	public void setCwcw_chargeweight(String cwCw_chargeweight) {
		this.setField(17, cwCw_chargeweight);
	}

	public String getCwcw_chargeweight() {
		return this.getField(17);
	}

	public void setCwcw_transferpieces(String cwCw_transferpieces) {
		this.setField(18, cwCw_transferpieces);
	}

	public String getCwcw_transferpieces() {
		return this.getField(18);
	}

	public void setCwcw_transfergrossweight(String cwCw_transfergrossweight) {
		this.setField(19, cwCw_transfergrossweight);
	}

	public String getCwcw_transfergrossweight() {
		return this.getField(19);
	}

	public void setCwcw_transferchargeweight(String cwCw_transferchargeweight) {
		this.setField(20, cwCw_transferchargeweight);
	}

	public String getCwcw_transferchargeweight() {
		return this.getField(20);
	}

	public void setCwcw_serverchargeweight(String cwCw_serverchargeweight) {
		this.setField(21, cwCw_serverchargeweight);
	}

	public String getCwcw_serverchargeweight() {
		return this.getField(21);
	}

	public void setCwcw_customerewbcode(String cwCw_customerewbcode) {
		this.setField(22, cwCw_customerewbcode);
	}

	public String getCwcw_customerewbcode() {
		return this.getField(22);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(23, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(23);
	}

	public void setCwcw_ewbcode(String cwCw_ewbcode) {
		this.setField(24, cwCw_ewbcode);
	}

	public String getCwcw_ewbcode() {
		return this.getField(24);
	}

	public void setCwcw_transfervolumeweight(String cwCw_transfervolumeweight) {
		this.setField(25, cwCw_transfervolumeweight);
	}

	public String getCwcw_transfervolumeweight() {
		return this.getField(25);
	}

	public void setCwscws_code(String cwsCws_code) {
		this.setField(26, cwsCws_code);
	}

	public String getCwscws_code() {
		return this.getField(26);
	}

	public void setCwscws_name(String cwsCws_name) {
		this.setField(27, cwsCws_name);
	}

	public String getCwscws_name() {
		return this.getField(27);
	}

	public void setEeee_code(String eeEe_code) {
		this.setField(28, eeEe_code);
	}

	public String getEeee_code() {
		return this.getField(28);
	}

	public void setEeee_sname(String eeEe_sname) {
		this.setField(29, eeEe_sname);
	}

	public String getEeee_sname() {
		return this.getField(29);
	}

	public void setEeee_esname(String eeEe_esname) {
		this.setField(30, eeEe_esname);
	}

	public String getEeee_esname() {
		return this.getField(30);
	}

	public void setPkpk_code(String pkPk_code) {
		this.setField(31, pkPk_code);
	}

	public String getPkpk_code() {
		return this.getField(31);
	}

	public void setPkpk_name(String pkPk_name) {
		this.setField(32, pkPk_name);
	}

	public String getPkpk_name() {
		return this.getField(32);
	}

	public void setPkpk_sname(String pkPk_sname) {
		this.setField(33, pkPk_sname);
	}

	public String getPkpk_sname() {
		return this.getField(33);
	}

	public void setPkpk_sename(String pkPk_sename) {
		this.setField(34, pkPk_sename);
	}

	public String getPkpk_sename() {
		return this.getField(34);
	}

	public void setDdtdt_code(String ddtDt_code) {
		this.setField(35, ddtDt_code);
	}

	public String getDdtdt_code() {
		return this.getField(35);
	}

	public void setDdtdt_hubcode(String ddtDt_hubcode) {
		this.setField(36, ddtDt_hubcode);
	}

	public String getDdtdt_hubcode() {
		return this.getField(36);
	}

	public void setDdtdt_name(String ddtDt_name) {
		this.setField(37, ddtDt_name);
	}

	public String getDdtdt_name() {
		return this.getField(37);
	}

	public void setSdtdt_code(String sdtDt_code) {
		this.setField(38, sdtDt_code);
	}

	public String getSdtdt_code() {
		return this.getField(38);
	}

	public void setSdtdt_hubcode(String sdtDt_hubcode) {
		this.setField(39, sdtDt_hubcode);
	}

	public String getSdtdt_hubcode() {
		return this.getField(39);
	}

	public void setSdtdt_name(String sdtDt_name) {
		this.setField(40, sdtDt_name);
	}

	public String getSdtdt_name() {
		return this.getField(40);
	}

	public void setCddtdt_code(String cddtDt_code) {
		this.setField(41, cddtDt_code);
	}

	public String getCddtdt_code() {
		return this.getField(41);
	}

	public void setCddtdt_hubcode(String cddtDt_hubcode) {
		this.setField(42, cddtDt_hubcode);
	}

	public String getCddtdt_hubcode() {
		return this.getField(42);
	}

	public void setCddtdt_name(String cddtDt_name) {
		this.setField(43, cddtDt_name);
	}

	public String getCddtdt_name() {
		return this.getField(43);
	}

	public void setOdtdt_code(String odtDt_code) {
		this.setField(44, odtDt_code);
	}

	public String getOdtdt_code() {
		return this.getField(44);
	}

	public void setOdtdt_hubcode(String odtDt_hubcode) {
		this.setField(45, odtDt_hubcode);
	}

	public String getOdtdt_hubcode() {
		return this.getField(45);
	}

	public void setOdtdt_name(String odtDt_name) {
		this.setField(46, odtDt_name);
	}

	public String getOdtdt_name() {
		return this.getField(46);
	}

	public void setPmpm_code(String pmPm_code) {
		this.setField(47, pmPm_code);
	}

	public String getPmpm_code() {
		return this.getField(47);
	}

	public void setPmpm_name(String pmPm_name) {
		this.setField(48, pmPm_name);
	}

	public String getPmpm_name() {
		return this.getField(48);
	}

	public void setSchnchn_code(String schnChn_code) {
		this.setField(49, schnChn_code);
	}

	public String getSchnchn_code() {
		return this.getField(49);
	}

	public void setSchnchn_name(String schnChn_name) {
		this.setField(50, schnChn_name);
	}

	public String getSchnchn_name() {
		return this.getField(50);
	}

	public void setSchnchn_sname(String schnChn_sname) {
		this.setField(51, schnChn_sname);
	}

	public String getSchnchn_sname() {
		return this.getField(51);
	}

	public void setCchnchn_code(String cchnChn_code) {
		this.setField(52, cchnChn_code);
	}

	public String getCchnchn_code() {
		return this.getField(52);
	}

	public void setCchnchn_name(String cchnChn_name) {
		this.setField(53, cchnChn_name);
	}

	public String getCchnchn_name() {
		return this.getField(53);
	}

	public void setCchnchn_sname(String cchnChn_sname) {
		this.setField(54, cchnChn_sname);
	}

	public String getCchnchn_sname() {
		return this.getField(54);
	}

	public void setCtct_code(String ctCt_code) {
		this.setField(55, ctCt_code);
	}

	public String getCtct_code() {
		return this.getField(55);
	}

	public void setCtct_name(String ctCt_name) {
		this.setField(56, ctCt_name);
	}

	public String getCtct_name() {
		return this.getField(56);
	}

	public void setScoco_code(String scoCo_code) {
		this.setField(57, scoCo_code);
	}

	public String getScoco_code() {
		return this.getField(57);
	}

	public void setScoco_name(String scoCo_name) {
		this.setField(58, scoCo_name);
	}

	public String getScoco_name() {
		return this.getField(58);
	}

	public void setScoco_sname(String scoCo_sname) {
		this.setField(59, scoCo_sname);
	}

	public String getScoco_sname() {
		return this.getField(59);
	}

	public void setScoco_labelcode(String scoCo_labelcode) {
		this.setField(60, scoCo_labelcode);
	}

	public String getScoco_labelcode() {
		return this.getField(60);
	}

	public void setCcoco_code(String ccoCo_code) {
		this.setField(61, ccoCo_code);
	}

	public String getCcoco_code() {
		return this.getField(61);
	}

	public void setCcoco_name(String ccoCo_name) {
		this.setField(62, ccoCo_name);
	}

	public String getCcoco_name() {
		return this.getField(62);
	}

	public void setCcoco_sname(String ccoCo_sname) {
		this.setField(63, ccoCo_sname);
	}

	public String getCcoco_sname() {
		return this.getField(63);
	}

	public void setCcoco_labelcode(String ccoCo_labelcode) {
		this.setField(64, ccoCo_labelcode);
	}

	public String getCcoco_labelcode() {
		return this.getField(64);
	}

	public void setAbwbw_code(String abwBw_code) {
		this.setField(65, abwBw_code);
	}

	public String getAbwbw_code() {
		return this.getField(65);
	}

	public void setAbwbw_labelcode(String abwBw_labelcode) {
		this.setField(66, abwBw_labelcode);
	}

	public String getAbwbw_labelcode() {
		return this.getField(66);
	}

	public void setAbwadd_date(String abwAdd_date) {
		this.setField(67, abwAdd_date);
	}

	public String getAbwadd_date() {
		return this.getField(67);
	}

	public void setDbwbw_code(String dbwBw_code) {
		this.setField(68, dbwBw_code);
	}

	public String getDbwbw_code() {
		return this.getField(68);
	}

	public void setDbwbw_labelcode(String dbwBw_labelcode) {
		this.setField(69, dbwBw_labelcode);
	}

	public String getDbwbw_labelcode() {
		return this.getField(69);
	}

	public void setDbwadd_date(String dbwAdd_date) {
		this.setField(70, dbwAdd_date);
	}

	public String getDbwadd_date() {
		return this.getField(70);
	}

	public void setCwcw_customerchargeweight(String cwCw_customerchargeweight) {
		this.setField(71, cwCw_customerchargeweight);
	}

	public String getCwcw_customerchargeweight() {
		return this.getField(71);
	}

	public void setIhsihs_code(String ihsIhs_code) {
		this.setField(72, ihsIhs_code);
	}

	public String getIhsihs_code() {
		return this.getField(72);
	}

	public void setIhsihs_name(String ihsIhs_name) {
		this.setField(73, ihsIhs_name);
	}

	public String getIhsihs_name() {
		return this.getField(73);
	}

	public void setCwcw_volumerate(String cwCw_volumerate) {
		this.setField(74, cwCw_volumerate);
	}

	public String getCwcw_volumerate() {
		return this.getField(74);
	}

	public void setCwcw_transfervolumerate(String cwCw_transfervolumerate) {
		this.setField(75, cwCw_transfervolumerate);
	}

	public String getCwcw_transfervolumerate() {
		return this.getField(75);
	}

	public void setHwhw_remark(String hwHw_remark) {
		this.setField(76, hwHw_remark);
	}

	public String getHwhw_remark() {
		return this.getField(76);
	}

	public void setCctct_code(String cctCt_code) {
		this.setField(77, cctCt_code);
	}

	public String getCctct_code() {
		return this.getField(77);
	}

	public void setCctct_name(String cctCt_name) {
		this.setField(78, cctCt_name);
	}

	public String getCctct_name() {
		return this.getField(78);
	}

	public void setCsopop_id(String csopOp_id) {
		this.setField(79, csopOp_id);
	}

	public String getCsopop_id() {
		return this.getField(79);
	}

	public void setCsopop_name(String csopOp_name) {
		this.setField(80, csopOp_name);
	}

	public String getCsopop_name() {
		return this.getField(80);
	}

	public void setBaglabelcode(String Baglabelcode) {
		this.setField(81, Baglabelcode);
	}

	public String getBaglabelcode() {
		return this.getField(81);
	}

	public void setSsopop_id(String ssopOp_id) {
		this.setField(82, ssopOp_id);
	}

	public String getSsopop_id() {
		return this.getField(82);
	}

	public void setSsopop_name(String ssopOp_name) {
		this.setField(83, ssopOp_name);
	}

	public String getSsopop_name() {
		return this.getField(83);
	}

	public void setCwznv_name(String cwZnv_name) {
		this.setField(84, cwZnv_name);
	}

	public String getCwznv_name() {
		return this.getField(84);
	}

	public void setCobalanceamount(String Cobalanceamount) {
		this.setField(85, Cobalanceamount);
	}

	public String getCobalanceamount() {
		return this.getField(85);
	}

	public void setVolumeweight(String Volumeweight) {
		this.setField(86, Volumeweight);
	}

	public String getVolumeweight() {
		return this.getField(86);
	}

	public void setHwhw_op_id_weightcheck(String hwHw_op_id_weightcheck) {
		this.setField(87, hwHw_op_id_weightcheck);
	}

	public String getHwhw_op_id_weightcheck() {
		return this.getField(87);
	}

	public void setHwopop_name(String hwopOp_name) {
		this.setField(88, hwopOp_name);
	}

	public String getHwopop_name() {
		return this.getField(88);
	}

	public void setHwhw_weightcheckdate(String hwHw_weightcheckdate) {
		this.setField(89, hwHw_weightcheckdate);
	}

	public String getHwhw_weightcheckdate() {
		return this.getField(89);
	}

	public void setHw_weightcheckkind(String Hw_weightcheckkind) {
		this.setField(90, Hw_weightcheckkind);
	}

	public String getHw_weightcheckkind() {
		return this.getField(90);
	}

	public void setWcbwbw_labelcode(String wcbwBw_labelcode) {
		this.setField(91, wcbwBw_labelcode);
	}

	public String getWcbwbw_labelcode() {
		return this.getField(91);
	}

	public void setCwcw_billcounts(String cwCw_billcounts) {
		this.setField(92, cwCw_billcounts);
	}

	public String getCwcw_billcounts() {
		return this.getField(92);
	}

	public void setCwcw_bagcounts(String cwCw_bagcounts) {
		this.setField(93, cwCw_bagcounts);
	}

	public String getCwcw_bagcounts() {
		return this.getField(93);
	}
	
	public void setDunopop_id(String dunopOp_id) {
		this.setField(94, dunopOp_id);
	}

	public String getDunopop_id() {
		return this.getField(94);
	}

	public void setDunopop_name(String dunopOp_name) {
		this.setField(95, dunopOp_name);
	}

	public String getDunopop_name() {
		return this.getField(95);
	}	
	
	public void setSpecialtype(String specialType) {
		this.setField(96, specialType);
	}

	public String getSpecialtype() {
		return this.getField(96);
	}		
	
	public void setPiecelhw(String Piecelhw) {
		this.setField(97, Piecelhw);
	}

	public String getPiecelhw() {
		return this.getField(97);
	}	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
