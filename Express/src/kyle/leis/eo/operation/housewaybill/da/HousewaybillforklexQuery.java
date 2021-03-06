package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class HousewaybillforklexQuery extends JGeneralQuery {
	
	public HousewaybillforklexQuery(){
	    m_strSelectClause = "SELECT cw.cw_code,cw.cw_grossweight,cw.cw_pieces,dbw.add_date,'NO' as cons1,FUN_GET_CWP_BAGLABELCODE(cw.cw_code) as cons2,FUN_GET_TransportLabelcode(dbw.bw_code) as mawblabelcode,cw.cw_serverewbcode,FUN_GET_Transporttool(dbw.bw_code) as route,FUN_GET_TransportDest(dbw.bw_code) as routedest,codt.dt_hubcode as origincountry,codt.dt_hubcode as exportcountry,odt.dt_hubcode as originlocation,codt.dt_hubcode as shippercountry,'NO' as shipperstate,shdt.dt_ename,hw.hw_shipperaddress1||hw.hw_shipperaddress2||hw.hw_shipperaddress3 as hw_shipperaddress1,''as shipperaddress2,hw.hw_shipperpostcode,hw.hw_shippercompany,'NO' as shipperweb,'NO' as shipperemail,hw.hw_shippername,hw.hw_shippertelephone,FUN_GET_TOTALCARGOINFO(cw.cw_code) as TotalCargoInfo,FUN_GET_TOTALCARGOCURRENCY(cw.cw_code) as Cargocurrency,cddt.dt_hubcode,ddt.dt_hubcode,cddt.dt_hubcode as ConsigneeCountry,'NO' as consigneestate,hw.hw_consigneecity,hw.hw_consigneeaddress1||hw.hw_consigneeaddress2||hw.hw_consigneeaddress3 as hw_consigneeaddress1, '' as consigneeaddress2,hw.hw_consigneepostcode,hw.hw_consigneecompany,hw.hw_consigneename,'NO' as consigneeemail,hw.hw_consigneetelephone,FUN_GET_CARGOINFO(cw.cw_code) as CargoInfoCName,'NO' as blankmark,FUN_GET_TOTALCARGOPCS(cw.cw_code) as TotalCargoPCS,cw.cw_serverchargeweight,cw.cw_serverchargeweight as chargeableweight,'KG' as weightcode,'NO' as hscode FROM T_OP_HOUSEWAYBILL hw,T_OP_COREWAYBILL cw,T_DI_COREWAYBILLSTATUS cws,T_DI_ENTERPRISEELEMENT ee,T_DI_PRODUCTKIND pk,T_DI_DISTRICT ddt,T_DI_DISTRICT cddt, T_DI_DISTRICT sdt, T_DI_DISTRICT odt,T_DI_DISTRICT codt,T_DI_DISTRICT shdt,T_DI_PAYMENTMODE pm,T_CHN_CHANNEL schn,T_CHN_CHANNEL cchn, T_DI_CARGOTYPE ct,T_CO_CORPORATION sco, T_CO_CORPORATION cco, T_CO_CUSTOMER cm,t_co_financialstatistics cofs,T_DI_CUSTOMERTYPE cct,T_DI_OPERATOR csop,T_DI_OPERATOR ssop,T_DI_OPERATOR siop,T_DI_OPERATOR soop,T_DI_OPERATOR rcop,T_OP_BATCHWAYBILL abw,T_OP_BATCHWAYBILL dbw,T_DI_ISSUEHOLDSTATUS ihs";
	    m_strWhereClause = "hw.CW_CODE = cw.CW_CODE and cw.CWS_CODE = cws.CWS_CODE and cw.EE_CODE = ee.EE_CODE and cw.PK_CODE = pk.PK_CODE and cw.pm_code = pm.pm_code and cw.DT_CODE_DESTINATION = ddt.DT_CODE(+) and ddt.DT_COUNTCODE = cddt.DT_CODE(+) and cw.DT_CODE_SIGNIN = sdt.DT_CODE(+) and cw.DT_CODE_ORIGIN = odt.DT_CODE and odt.Dt_Countcode = codt.dt_code and hw.dt_code_shipper = shdt.dt_code(+) and cw.CHN_CODE_SUPPLIER = schn.CHN_CODE(+) and cw.CHN_CODE_CUSTOMER = cchn.CHN_CODE(+) and cw.CT_CODE = ct.CT_CODE and cw.CO_CODE_SUPPLIER = sco.CO_CODE(+) and cw.CO_CODE_CUSTOMER = cco.CO_CODE(+) and cco.CO_CODE = cm.CO_CODE(+) and cco.co_code = cofs.co_code(+) and cm.CT_CODE = cct.CT_CODE(+) and cm.CM_OP_ID_CSERVICE = csop.OP_ID(+) and cm.cm_op_id_sale = ssop.OP_ID(+) and hw.HW_OP_ID_SIGNIN = siop.OP_ID(+) and hw.HW_OP_ID_SIGNOUT = soop.OP_ID(+) and hw.HW_OP_ID_RECORD = rcop.OP_ID(+) and cw.BW_CODE_ARRIVAL = abw.BW_CODE(+) and cw.BW_CODE_DEPARTURE = dbw.BW_CODE(+) and cw.IHS_CODE = ihs.IHS_CODE(+) and cofs.fs_carryoverenterprise = 'ALL'";
	    m_strOrderByClause = "mawblabelcode,cons2";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.CW_CUSTOMEREWBCODE = '~~'", "cw.CW_SERVEREWBCODE = '~~'", "cw.CW_EWBCODE = '~~'", "hw.hw_Signindate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.hw_Signindate", "hw.hw_Signoutdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.hw_Signoutdate", "hw.hw_Recorddate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.hw_Recorddate", "cw.cw_Chargeweight >= ~~", "~~ >= cw.cw_Chargeweight", "cw.cw_Serverchargeweight >= ~~", "~~ >= cw.cw_Serverchargeweight", "cws.cws_Code in (~~)", "cws.cws_Code not in (~~)", "pk.pk_Code = '~~'", "ddt.dt_Code = '~~'", "cddt.dt_Code = '~~'", "odt.dt_Code = '~~'", "pm.pm_Code = '~~'", "schn.chn_Code = '~~'", "ct.ct_Code = '~~'", "sco.co_Code = '~~'", "cco.co_Code = '~~'", "abw.bw_Labelcode = '~~'", "dbw.bw_Labelcode = '~~'", "hw.cw_Code = ~~", "ee.ee_Code = '~~'", "csop.op_Id = ~~", "ssop.op_Id = ~~", "ihs.ihs_Code = '~~'", "cw.znv_Name = '~~'", "exists (select cp.cp_Id from t_op_corewaybillpieces cp where cp.cw_Code = hw.cw_Code and cp.cp_Baglabelcode = '~~')", "exists (select wst.cw_Code from t_op_waybillspecialtype wst where wst.cw_Code = hw.cw_Code and wst.est_code in (~~))", "abw.ADD_DATE >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= abw.ADD_DATE", "exists (select twv.twb_id from t_op_transportwaybillvalue twv, t_op_transportwaybill tw,t_op_batchwaybill bw where twv.twb_id = tw.twb_id and twv.bw_code = bw.bw_code and bw.bw_code = cw.bw_code_departure and tw.twb_labelcode = '~~')", "cw.cw_batchwaybillsign = '~~'", "rcop.op_Id = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new HousewaybillforklexColumns();
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

	public void setIhscode(String ihsCode) {
		this.setField(30, ihsCode);
	}

	public String getIhscode() {
		return this.getField(30);
	}

	public void setZnvname(String znvName) {
		this.setField(31, znvName);
	}

	public String getZnvname() {
		return this.getField(31);
	}

	public void setCpbaglabelcode(String cpBaglabelcode) {
		this.setField(32, cpBaglabelcode);
	}

	public String getCpbaglabelcode() {
		return this.getField(32);
	}

	public void setEstcode(String estCode) {
		this.setField(33, estCode);
	}

	public String getEstcode() {
		return this.getField(33);
	}

	public void setStartarrivedate(String StartArrivedate) {
		this.setField(34, StartArrivedate);
	}

	public String getStartarrivedate() {
		return this.getField(34);
	}

	public void setEndarrivedate(String EndArrivedate) {
		this.setField(35, EndArrivedate);
	}

	public String getEndarrivedate() {
		return this.getField(35);
	}

	public void setTwblabelcode(String twbLabelcode) {
		this.setField(36, twbLabelcode);
	}

	public String getTwblabelcode() {
		return this.getField(36);
	}

	public void setCwbatchwaybillsign(String cwbatchwaybillsign) {
		this.setField(37, cwbatchwaybillsign);
	}

	public String getCwbatchwaybillsign() {
		return this.getField(37);
	}

	public void setRcopid(String rcopId) {
		this.setField(38, rcopId);
	}

	public String getRcopid() {
		return this.getField(38);
	}

}
