package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class HousewaybillforreportQuery extends JGeneralQuery {
	
	public HousewaybillforreportQuery(){
	    m_strSelectClause = "SELECT hw.CW_CODE,hw.HW_SIGNINDATE,hw.HW_OP_ID_SIGNIN,siop.OP_NAME, hw.HW_OP_ID_WEIGHTING,hw.HW_SIGNOUTDATE,hw.HW_OP_ID_SIGNOUT,soop.OP_NAME,hw.HW_RECORDDATE,hw.HW_OP_ID_RECORD,rcop.OP_NAME,hw.HW_OP_ID_PACKING,hw.HW_INSURANCEVALUE,hw.HW_CONSIGNEEPOSTCODE,cw.CW_POSTCODE_DESTINATION,cw.CW_PIECES,cw.CW_GROSSWEIGHT,cw.CW_CHARGEWEIGHT,cw.CW_TRANSFERPIECES,cw.CW_TRANSFERGROSSWEIGHT,cw.CW_TRANSFERCHARGEWEIGHT,cw.CW_SERVERCHARGEWEIGHT,cw.CW_CUSTOMEREWBCODE,cw.CW_SERVEREWBCODE,cw.CW_EWBCODE,cw.CW_TRANSFERVOLUMEWEIGHT,cws.CWS_CODE,cws.CWS_NAME,ee.EE_CODE,ee.EE_SNAME,ee.EE_ESNAME,pk.PK_CODE,pk.PK_NAME,pk.PK_SNAME,pk.PK_SENAME,ddt.DT_CODE,ddt.DT_HUBCODE,ddt.DT_NAME,sdt.DT_CODE,sdt.DT_HUBCODE,sdt.DT_NAME,cddt.DT_CODE,cddt.DT_HUBCODE,cddt.DT_NAME,odt.DT_CODE,odt.DT_HUBCODE,odt.DT_NAME,pm.PM_CODE,pm.PM_NAME,schn.CHN_CODE,schn.CHN_NAME,schn.CHN_SNAME,cchn.CHN_CODE,cchn.CHN_NAME,cchn.CHN_SNAME,ct.CT_CODE,ct.CT_NAME,sco.CO_CODE,sco.CO_NAME,sco.CO_SNAME,sco.CO_LABELCODE,cco.CO_CODE,cco.CO_NAME,cco.CO_SNAME,cco.CO_LABELCODE,abw.BW_CODE,abw.BW_LABELCODE,abw.ADD_DATE,dbw.BW_CODE,dbw.BW_LABELCODE,dbw.ADD_DATE,cw.CW_CUSTOMERCHARGEWEIGHT,ihs.IHS_CODE,ihs.IHS_NAME,cw.CW_VOLUMERATE,cw.CW_TRANSFERVOLUMERATE,hw.HW_REMARK,cct.CT_CODE,cct.CT_NAME,csop.OP_ID,csop.OP_NAME,(select cp.cp_Baglabelcode from t_op_corewaybillpieces cp where cp.cw_code = cw.cw_code and rownum < 2) as baglabelcode,ssop.OP_ID,ssop.OP_NAME,cw.znv_Name,cofs.fs_balanceamount + cm.cm_creditlimit as cobalanceamount, FUN_GET_VolumeWeight(hw.CW_CODE) as Volumeweight,hw.HW_OP_ID_WEIGHTCHECK,hwop.op_name,hw.HW_WEIGHTCHECKDATE,decode(nvl(hw.HW_WEIGHTCHECKKIND,'U'),'U','','P','','N','') as HW_WEIGHTCHECKKIND,wcbw.bw_labelcode,cw.cw_Billcounts,cw.cw_Bagcounts,dunop.OP_ID,dunop.OP_NAME,FUN_GET_SPECIALTYTYPE(hw.CW_CODE) as specialtype,FUN_GET_COREWAYBILLPIECESLWH(hw.CW_CODE) as piecelhw FROM T_OP_HOUSEWAYBILL hw,T_OP_COREWAYBILL cw,T_DI_COREWAYBILLSTATUS cws,T_DI_ENTERPRISEELEMENT ee,T_DI_PRODUCTKIND pk,T_DI_DISTRICT ddt,T_DI_DISTRICT cddt, T_DI_DISTRICT sdt, T_DI_DISTRICT odt,T_DI_PAYMENTMODE pm,T_CHN_CHANNEL schn,T_CHN_CHANNEL cchn, T_DI_CARGOTYPE ct,T_CO_CORPORATION sco, T_CO_CORPORATION cco, T_CO_CUSTOMER cm,t_co_financialstatistics cofs,T_DI_CUSTOMERTYPE cct,T_DI_OPERATOR csop,T_DI_OPERATOR ssop,T_DI_OPERATOR dunop,T_DI_OPERATOR siop,T_DI_OPERATOR soop,T_DI_OPERATOR rcop,T_DI_OPERATOR hwop,T_OP_BATCHWAYBILL abw,T_OP_BATCHWAYBILL dbw,T_OP_BATCHWAYBILL wcbw,T_DI_ISSUEHOLDSTATUS ihs";
	    m_strWhereClause = "hw.CW_CODE = cw.CW_CODE and cw.CWS_CODE = cws.CWS_CODE and cw.EE_CODE = ee.EE_CODE and cw.PK_CODE = pk.PK_CODE and cw.DT_CODE_DESTINATION = ddt.DT_CODE(+) and ddt.DT_COUNTCODE = cddt.DT_CODE(+) and cw.DT_CODE_SIGNIN = sdt.DT_CODE(+) and cw.DT_CODE_ORIGIN = odt.DT_CODE and cw.PM_CODE = pm.PM_CODE and cw.CHN_CODE_SUPPLIER = schn.CHN_CODE(+) and cw.CHN_CODE_CUSTOMER = cchn.CHN_CODE(+) and cw.CT_CODE = ct.CT_CODE and cw.CO_CODE_SUPPLIER = sco.CO_CODE(+) and cw.CO_CODE_CUSTOMER = cco.CO_CODE(+) and cco.CO_CODE = cm.CO_CODE(+) and cco.co_code = cofs.co_code(+) and cm.CT_CODE = cct.CT_CODE(+) and cm.CM_OP_ID_CSERVICE = csop.OP_ID(+) and cm.cm_op_id_sale = ssop.OP_ID(+) and cm.cm_op_id_dun = dunop.OP_ID(+) and hw.HW_OP_ID_SIGNIN = siop.OP_ID(+) and hw.HW_OP_ID_SIGNOUT = soop.OP_ID(+) and hw.HW_OP_ID_RECORD = rcop.OP_ID(+) and hw.hw_op_id_weightcheck = hwop.op_id(+) and cw.BW_CODE_ARRIVAL = abw.BW_CODE(+) and cw.BW_CODE_DEPARTURE = dbw.BW_CODE(+) and cw.bw_code_weightcheck = wcbw.bw_code(+) and cw.IHS_CODE = ihs.IHS_CODE(+) and cm.ct_code = cct.ct_code";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.CW_CUSTOMEREWBCODE = '~~'", "cw.CW_SERVEREWBCODE = '~~'", "cw.CW_EWBCODE = '~~'", "hw.hw_Signindate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.hw_Signindate", "hw.hw_Signoutdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.hw_Signoutdate", "hw.hw_Recorddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.hw_Recorddate", "cw.cw_Chargeweight >= ~~", "~~ >= cw.cw_Chargeweight", "cw.cw_Serverchargeweight >= ~~", "~~ >= cw.cw_Serverchargeweight", "cws.cws_Code in (~~)", "cws.cws_Code not in (~~)", "pk.pk_Code = '~~'", "ddt.dt_Code = '~~'", "cddt.dt_Code = '~~'", "odt.dt_Code = '~~'", "pm.pm_Code = '~~'", "schn.chn_Code = '~~'", "ct.ct_Code = '~~'", "sco.co_Code = '~~'", "cco.co_Code = '~~'", "abw.bw_Labelcode = '~~'", "dbw.bw_Labelcode = '~~'", "hw.cw_Code = ~~", "ee.ee_Code = '~~'", "csop.op_Id = ~~", "ssop.op_Id = ~~", "hw.hw_op_id_record = ~~", "hw.hw_op_id_signin = ~~", "ihs.ihs_Code = '~~'", "cw.znv_Name = '~~'", "exists (select cp.cp_Id from t_op_corewaybillpieces cp where cp.cw_Code = hw.cw_Code and cp.cp_Baglabelcode in (~~))", "exists (select wst.cw_Code from t_op_waybillspecialtype wst where wst.cw_Code = hw.cw_Code and wst.est_code in (~~))", "abw.ADD_DATE >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= abw.ADD_DATE", "exists (select twv.twb_id from t_op_transportwaybillvalue twv, t_op_transportwaybill tw,t_op_batchwaybill bw,t_op_corewaybillpieces cwp where twv.twb_id = tw.twb_id and twv.bw_code = bw.bw_code and twv.twbv_baglabelcode = cwp.cp_baglabelcode and bw.bw_code = cw.bw_code_departure and cw.cw_code = cwp.cw_code and tw.twb_labelcode = '~~')", "(cw.CW_CUSTOMEREWBCODE in (~~) OR cw.CW_SERVEREWBCODE in (~~))", "(cco.CO_CarryoverSign = '~~' OR cco.CO_CarryoverDate > cw.cw_createdate)", "(cco.CO_CarryoverSign = '~~' AND cw.cw_createdate > cco.CO_CarryoverDate)", "cofs.fs_carryoverenterprise = '~~'", "~~ >= cw.cw_chargeweight", "cw.cw_chargeweight >= ~~", "ee.ee_Structurecode like '~~%'", "hw.HW_OP_ID_WEIGHTCHECK = ~~", "hw.HW_WEIGHTCHECKDATE >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.HW_WEIGHTCHECKDATE", "nvl(hw.HW_WEIGHTCHECKKIND,'U') = '~~'", "wcbw.bw_labelcode = '~~'", "cw.cw_Batchwaybillsign = '~~'", "exists (select wbt.wbt_id from t_cs_waybilltrack wbt where wbt.cw_code = hw.cw_code and wbt.wbts_code = 'AFF' and wbt.wbt_district = '719' and wbt.wbt_createdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss') and to_date('~~','yyyy-mm-dd hh24:mi:ss') >= wbt.wbt_createdate)", "exists (select cp.cp_Id from t_op_corewaybillpieces cp where cp.cw_Code = hw.cw_Code and cp.cp_Sibaglabelcode = '~~')", "cct.ct_Code= '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1 };		
	}	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new HousewaybillforreportColumns();
	}
	
	public void setCustomerewbcode(String Customerewbcode) {
		this.setField(0, Customerewbcode);
	}

	public String getCustomerewbcode() {
		return this.getField(0);
	}

	public void setServerewbcode(String Serverewbcode) {
		this.setField(1, Serverewbcode);
	}

	public String getServerewbcode() {
		return this.getField(1);
	}

	public void setEwbcode(String Ewbcode) {
		this.setField(2, Ewbcode);
	}

	public String getEwbcode() {
		return this.getField(2);
	}

	public void setStartsignindate(String StartSignindate) {
		this.setField(3, StartSignindate);
	}

	public String getStartsignindate() {
		return this.getField(3);
	}

	public void setEndsignindate(String EndSignindate) {
		this.setField(4, EndSignindate);
	}

	public String getEndsignindate() {
		return this.getField(4);
	}

	public void setStartsignoutdate(String StartSignoutdate) {
		this.setField(5, StartSignoutdate);
	}

	public String getStartsignoutdate() {
		return this.getField(5);
	}

	public void setEndsignoutdate(String EndSignoutdate) {
		this.setField(6, EndSignoutdate);
	}

	public String getEndsignoutdate() {
		return this.getField(6);
	}

	public void setStartrecorddate(String StartRecorddate) {
		this.setField(7, StartRecorddate);
	}

	public String getStartrecorddate() {
		return this.getField(7);
	}

	public void setEndrecorddate(String EndRecorddate) {
		this.setField(8, EndRecorddate);
	}

	public String getEndrecorddate() {
		return this.getField(8);
	}

	public void setStartchargeweight(String StartChargeweight) {
		this.setField(9, StartChargeweight);
	}

	public String getStartchargeweight() {
		return this.getField(9);
	}

	public void setEndchargeweight(String EndChargeweight) {
		this.setField(10, EndChargeweight);
	}

	public String getEndchargeweight() {
		return this.getField(10);
	}

	public void setStartserverchargeweight(String StartServerchargeweight) {
		this.setField(11, StartServerchargeweight);
	}

	public String getStartserverchargeweight() {
		return this.getField(11);
	}

	public void setEndserverchargeweight(String EndServerchargeweight) {
		this.setField(12, EndServerchargeweight);
	}

	public String getEndserverchargeweight() {
		return this.getField(12);
	}

	public void setCwscode(String cwsCode) {
		this.setField(13, cwsCode);
	}

	public String getCwscode() {
		return this.getField(13);
	}

	public void setNotcwscode(String NotcwsCode) {
		this.setField(14, NotcwsCode);
	}

	public String getNotcwscode() {
		return this.getField(14);
	}

	public void setPkcode(String pkCode) {
		this.setField(15, pkCode);
	}

	public String getPkcode() {
		return this.getField(15);
	}

	public void setDesdtcode(String DesDtCode) {
		this.setField(16, DesDtCode);
	}

	public String getDesdtcode() {
		return this.getField(16);
	}

	public void setDescountrycode(String DesCountryCode) {
		this.setField(17, DesCountryCode);
	}

	public String getDescountrycode() {
		return this.getField(17);
	}

	public void setOrigindtcode(String OriginDtCode) {
		this.setField(18, OriginDtCode);
	}

	public String getOrigindtcode() {
		return this.getField(18);
	}

	public void setPmcode(String pmCode) {
		this.setField(19, pmCode);
	}

	public String getPmcode() {
		return this.getField(19);
	}

	public void setChncode(String chnCode) {
		this.setField(20, chnCode);
	}

	public String getChncode() {
		return this.getField(20);
	}

	public void setCtcode(String ctCode) {
		this.setField(21, ctCode);
	}

	public String getCtcode() {
		return this.getField(21);
	}

	public void setScocode(String scoCode) {
		this.setField(22, scoCode);
	}

	public String getScocode() {
		return this.getField(22);
	}

	public void setCcocode(String ccoCode) {
		this.setField(23, ccoCode);
	}

	public String getCcocode() {
		return this.getField(23);
	}

	public void setAbwlabelcode(String abwLabelcode) {
		this.setField(24, abwLabelcode);
	}

	public String getAbwlabelcode() {
		return this.getField(24);
	}

	public void setDbwlabelcode(String dbwLabelcode) {
		this.setField(25, dbwLabelcode);
	}

	public String getDbwlabelcode() {
		return this.getField(25);
	}

	public void setCwcode(String cwCode) {
		this.setField(26, cwCode);
	}

	public String getCwcode() {
		return this.getField(26);
	}

	public void setEecode(String eeCode) {
		this.setField(27, eeCode);
	}

	public String getEecode() {
		return this.getField(27);
	}

	public void setCsopid(String csopId) {
		this.setField(28, csopId);
	}

	public String getCsopid() {
		return this.getField(28);
	}

	public void setSsopid(String ssopId) {
		this.setField(29, ssopId);
	}

	public String getSsopid() {
		return this.getField(29);
	}

	public void setOpidrecord(String opIdRecord) {
		this.setField(30, opIdRecord);
	}

	public String getOpidrecord() {
		return this.getField(30);
	}

	public void setOpidsignin(String opIdSignin) {
		this.setField(31, opIdSignin);
	}

	public String getOpidsignin() {
		return this.getField(31);
	}

	public void setIhscode(String ihsCode) {
		this.setField(32, ihsCode);
	}

	public String getIhscode() {
		return this.getField(32);
	}

	public void setZnvname(String znvName) {
		this.setField(33, znvName);
	}

	public String getZnvname() {
		return this.getField(33);
	}

	public void setCpbaglabelcode(String cpBaglabelcode) {
		this.setField(34, cpBaglabelcode);
	}

	public String getCpbaglabelcode() {
		return this.getField(34);
	}

	public void setEstcode(String estCode) {
		this.setField(35, estCode);
	}

	public String getEstcode() {
		return this.getField(35);
	}

	public void setStartarrivedate(String StartArrivedate) {
		this.setField(36, StartArrivedate);
	}

	public String getStartarrivedate() {
		return this.getField(36);
	}

	public void setEndarrivedate(String EndArrivedate) {
		this.setField(37, EndArrivedate);
	}

	public String getEndarrivedate() {
		return this.getField(37);
	}

	public void setTwblabelcode(String twbLabelcode) {
		this.setField(38, twbLabelcode);
	}

	public String getTwblabelcode() {
		return this.getField(38);
	}

	public void setIncustomerewbcode(String InCustomerewbcode) {
		this.setField(39, InCustomerewbcode);
	}

	public String getIncustomerewbcode() {
		return this.getField(39);
	}
	public void setInserverewbcode(String InServerewbcode) {
		this.setField(40, InServerewbcode);
	}

	public String getInserverewbcode() {
		return this.getField(40);
	}

	public void setBegincarryoversign(String BeginCarryoversign) {
		this.setField(41, BeginCarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(41);
	}

	public void setEndcarryoversigin(String EndCarryoversigin) {
		this.setField(42, EndCarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(42);
	}

	public void setFscarryoverenterprise(String fscarryoverenterprise) {
		this.setField(43, fscarryoverenterprise);
	}

	public String getFscarryoverenterprise() {
		return this.getField(43);
	}

	public void setMaxweight(String MaxWeight) {
		this.setField(44, MaxWeight);
	}

	public String getMaxweight() {
		return this.getField(44);
	}

	public void setMinweight(String MinWeight) {
		this.setField(45, MinWeight);
	}

	public String getMinweight() {
		return this.getField(45);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(46, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(46);
	}

	public void setOpidweightcheck(String OpIdWeightCheck) {
		this.setField(47, OpIdWeightCheck);
	}

	public String getOpidweightcheck() {
		return this.getField(47);
	}

	public void setStartcheckdate(String StartCheckdate) {
		this.setField(48, StartCheckdate);
	}

	public String getStartcheckdate() {
		return this.getField(48);
	}

	public void setEndcheckdate(String EndCheckdate) {
		this.setField(49, EndCheckdate);
	}

	public String getEndcheckdate() {
		return this.getField(49);
	}

	public void setHw_weightcheckkind(String HW_WEIGHTCHECKKIND) {
		this.setField(50, HW_WEIGHTCHECKKIND);
	}

	public String getHw_weightcheckkind() {
		return this.getField(50);
	}

	public void setWcbwlabelcode(String wcbwlabelcode) {
		this.setField(51, wcbwlabelcode);
	}

	public String getWcbwlabelcode() {
		return this.getField(51);
	}

	public void setCwbatchwaybillsign(String cwBatchwaybillsign) {
		this.setField(52, cwBatchwaybillsign);
	}

	public String getCwbatchwaybillsign() {
		return this.getField(52);
	}

	public void setStartrecordtrackdate(String StartRecordtrackdate) {
		this.setField(53, StartRecordtrackdate);
	}

	public String getStartrecordtrackdate() {
		return this.getField(53);
	}
	public void setEndrecordtrackdate(String EndRecordtrackdate) {
		this.setField(54, EndRecordtrackdate);
	}

	public String getEndrecordtrackdate() {
		return this.getField(54);
	}

	public void setCpsibaglabelcode(String cpSibaglabelcode) {
		this.setField(55, cpSibaglabelcode);
	}

	public String getCpsibaglabelcode() {
		return this.getField(55);
	}
	public void setCctcode(String cctCode) {
		this.setField(56, cctCode);
	}

	public String getCctcode() {
		return this.getField(56);
	}
}
