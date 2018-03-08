package kyle.leis.eo.customerservice.track.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class WaybillfortrackclientQuery extends JGeneralQuery {
	
	public WaybillfortrackclientQuery(){
	    m_strSelectClause = "SELECT cw.cw_Code,cw.cw_Pieces,cw.cw_Chargeweight,cw.cw_Serverchargeweight,cw.cw_Customerewbcode,cw.cw_Serverewbcode,cw.cw_Ewbcode,cws.cws_Code,cws.cws_Name,pk.pk_Code,pk.pk_Sname,ddt.dt_Code,ddt.dt_Hubcode,cddt.dt_Code,cddt.dt_Hubcode,odt.dt_Code,odt.dt_Hubcode,pm.pm_Code,pm.pm_Name,schn.chn_Code,schn.chn_Sname,ee.ee_Code,ee.ee_Sname,ct.ct_Code,ct.ct_Name,ihs.ihs_Code,ihs.ihs_Name,sco.co_Code,sco.co_Sname,sco.co_Labelcode,cco.co_Code,cco.co_Sname,cco.co_Labelcode,abw.bw_Code,abw.bw_Labelcode,abw.add_Date,dbw.bw_Code,dbw.bw_Labelcode,dbw.add_Date,wbbt.wbbt_Latesttrackdesc,wbbt.wbbt_Latestcslogdesc,wbbt.wbbt_Cslogcreatedate,wbbt.wbbt_Signforuser,wbbt.wbbt_Signfordate,wbts.wbts_Code,wbts.wbts_Name,csop.op_Id,csop.op_Name,wbts.wbts_Ename,ddt.dt_Ename,cddt.dt_Ename,odt.dt_Ename,sop.op_Id,sop.op_Name,wbbt.wbbt_Latesttrackdate,cct.ct_Code,cct.ct_Name,(select cp.cp_Baglabelcode from t_op_corewaybillpieces cp where cp.cw_code = cw.cw_code and rownum < 2) as baglabelcode,wbbt.WBBT_LATESTTRACKFETCHDATE FROM T_op_Corewaybill cw,t_cs_Waybillbatchtrack wbbt, t_di_Waybilltrackstatus wbts,t_di_Corewaybillstatus cws,t_di_Productkind pk,t_di_District ddt,t_di_District cddt,t_di_District odt,t_di_Paymentmode pm,t_chn_Channel schn,t_di_Enterpriseelement ee,t_di_Cargotype ct,t_di_Issueholdstatus ihs, t_co_Corporation sco,t_co_Corporation cco,t_co_Customer cm,t_di_Operator csop,t_di_Operator sop,t_op_Batchwaybill abw, t_op_Batchwaybill dbw,t_di_Customertype cct";
	    m_strWhereClause = "cw.cw_code = wbbt.cw_code(+) and wbbt.wbts_code = wbts.wbts_code(+) and cw.cws_code = cws.cws_code and cw.pk_code = pk.pk_code and cw.dt_code_destination = ddt.dt_code and ddt.dt_code = cddt.dt_code and cw.dt_code_origin = odt.dt_code and cw.pm_code = pm.pm_code and cw.chn_code_supplier = schn.chn_code(+) and cw.ee_code = ee.ee_code and cw.ct_code = ct.ct_code and cw.ihs_code = ihs.ihs_code(+) and cw.co_code_supplier = sco.co_code(+) and cw.co_code_customer = cco.co_code and cco.co_code = cm.co_code and cm.cm_op_id_cservice = csop.op_id(+) and cm.cm_op_id_sale = sop.op_id(+) and cw.bw_code_arrival = abw.bw_code and cw.bw_code_departure = dbw.bw_code(+) and cm.ct_code = cct.ct_code and cws.cws_Code NOT IN ('EL', 'CEL')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cw_Code = ~~", "cw.cw_Serverewbcode in (~~)", "cw.cw_Customerewbcode in (~~)", "cws.cws_Code = '~~'", "pk.pk_Code = '~~'", "cddt.dt_Code = '~~'", "odt.dt_Code = '~~'", "pm.pm_Code = '~~'", "schn.chn_Code = '~~'", "ee.ee_Code = '~~'", "ct.ct_Code = '~~'", "sco.co_Code = '~~'", "cco.co_Code = '~~'", "cco.co_Code not in (~~)", "abw.bw_Code = '~~'", "dbw.bw_Code = '~~'", "wbts.wbts_Code in (~~)", "nvl(wbts.wbts_Code,'null') not in (~~)", "abw.bw_Labelcode = '~~'", "dbw.bw_Labelcode = '~~'", "nvl(dbw.bw_Labelcode,'null') != '~~'", "wbbt.wbbt_Latesttrackdesc like '%~~%'", "wbbt.wbbt_Latestcslogdesc like '%~~%'", "nvl(wbbt.wbbt_Signforuser,'null') != '~~'", "wbbt.wbbt_Signfordate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= wbbt.wbbt_Signfordate", "abw.add_Date >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= abw.add_Date", "dbw.add_Date >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= dbw.add_Date", "csop.op_Id = ~~", "pk.pk_Queryneedlogin = '~~'", "cw.cw_Ewbcode in (~~)", "exists (select cp.cw_Code from t_op_Corewaybillpieces cp where cp.cw_code = cw.cw_code and cp.cp_Baglabelcode = '~~')", "ee.ee_Structurecode like '~~%'", "cct.ct_Code= '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new WaybillfortrackclientColumns();
	}
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setCwserverewbcode(String cwServerewbcode) {
		this.setField(1, cwServerewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(1);
	}

	public void setCwcustomerewbcode(String cwCustomerewbcode) {
		this.setField(2, cwCustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(2);
	}

	public void setCwscode(String cwsCode) {
		this.setField(3, cwsCode);
	}

	public String getCwscode() {
		return this.getField(3);
	}

	public void setPkcode(String pkCode) {
		this.setField(4, pkCode);
	}

	public String getPkcode() {
		return this.getField(4);
	}

	public void setCountrydtcode(String countrydtcode) {
		this.setField(5, countrydtcode);
	}

	public String getCountrydtcode() {
		return this.getField(5);
	}

	public void setOrigindtcode(String origindtcode) {
		this.setField(6, origindtcode);
	}

	public String getOrigindtcode() {
		return this.getField(6);
	}

	public void setPmcode(String pmCode) {
		this.setField(7, pmCode);
	}

	public String getPmcode() {
		return this.getField(7);
	}

	public void setChncode(String chnCode) {
		this.setField(8, chnCode);
	}

	public String getChncode() {
		return this.getField(8);
	}

	public void setEecode(String eeCode) {
		this.setField(9, eeCode);
	}

	public String getEecode() {
		return this.getField(9);
	}

	public void setCtcode(String ctCode) {
		this.setField(10, ctCode);
	}

	public String getCtcode() {
		return this.getField(10);
	}

	public void setScocode(String scoCode) {
		this.setField(11, scoCode);
	}

	public String getScocode() {
		return this.getField(11);
	}

	public void setCcocode(String ccoCode) {
		this.setField(12, ccoCode);
	}

	public String getCcocode() {
		return this.getField(12);
	}

	public void setNotccocode(String NotCcoCode) {
		this.setField(13, NotCcoCode);
	}

	public String getNotccocode() {
		return this.getField(13);
	}

	public void setArrivalbwcode(String arrivalbwCode) {
		this.setField(14, arrivalbwCode);
	}

	public String getArrivalbwcode() {
		return this.getField(14);
	}

	public void setDeparturebwcode(String departurebwCode) {
		this.setField(15, departurebwCode);
	}

	public String getDeparturebwcode() {
		return this.getField(15);
	}

	public void setWbtscode(String wbtsCode) {
		this.setField(16, wbtsCode);
	}

	public String getWbtscode() {
		return this.getField(16);
	}

	public void setNotwbscode(String NotWbsCode) {
		this.setField(17, NotWbsCode);
	}

	public String getNotwbscode() {
		return this.getField(17);
	}

	public void setArrivallabelcode(String ArrivalLabelcode) {
		this.setField(18, ArrivalLabelcode);
	}

	public String getArrivallabelcode() {
		return this.getField(18);
	}

	public void setDeparturelabelcode(String DepartureLabelcode) {
		this.setField(19, DepartureLabelcode);
	}

	public String getDeparturelabelcode() {
		return this.getField(19);
	}

	public void setNotdeparturelabelcode(String NotDepartureLabelcode) {
		this.setField(20, NotDepartureLabelcode);
	}

	public String getNotdeparturelabelcode() {
		return this.getField(20);
	}

	public void setWbbtlatesttrackdesc(String wbbtLatesttrackdesc) {
		this.setField(21, wbbtLatesttrackdesc);
	}

	public String getWbbtlatesttrackdesc() {
		return this.getField(21);
	}

	public void setWbbtlatestcslogdesc(String wbbtLatestcslogdesc) {
		this.setField(22, wbbtLatestcslogdesc);
	}

	public String getWbbtlatestcslogdesc() {
		return this.getField(22);
	}

	public void setNotsignforuser(String NotSignforuser) {
		this.setField(23, NotSignforuser);
	}

	public String getNotsignforuser() {
		return this.getField(23);
	}

	public void setStartsignfordate(String StartSignfordate) {
		this.setField(24, StartSignfordate);
	}

	public String getStartsignfordate() {
		return this.getField(24);
	}

	public void setEndsignfordate(String EndSignfordate) {
		this.setField(25, EndSignfordate);
	}

	public String getEndsignfordate() {
		return this.getField(25);
	}

	public void setStartarrivaldate(String StartArrivalDate) {
		this.setField(26, StartArrivalDate);
	}

	public String getStartarrivaldate() {
		return this.getField(26);
	}

	public void setEndarrivaldate(String EndArrivalDate) {
		this.setField(27, EndArrivalDate);
	}

	public String getEndarrivaldate() {
		return this.getField(27);
	}

	public void setStartdeparturedate(String StartDepartureDate) {
		this.setField(28, StartDepartureDate);
	}

	public String getStartdeparturedate() {
		return this.getField(28);
	}

	public void setEnddeparturedate(String EndDepartureDate) {
		this.setField(29, EndDepartureDate);
	}

	public String getEnddeparturedate() {
		return this.getField(29);
	}

	public void setCsopid(String csopId) {
		this.setField(30, csopId);
	}

	public String getCsopid() {
		return this.getField(30);
	}

	public void setPkqueryneedlogin(String pkQueryneedlogin) {
		this.setField(31, pkQueryneedlogin);
	}

	public String getPkqueryneedlogin() {
		return this.getField(31);
	}

	public void setCwewbcode(String Cwewbcode) {
		this.setField(32, Cwewbcode);
	}

	public String getCwewbcode() {
		return this.getField(32);
	}

	public void setCpbaglabelcode(String cpBaglabelcode) {
		this.setField(33, cpBaglabelcode);
	}

	public String getCpbaglabelcode() {
		return this.getField(33);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(34, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(34);
	}

	public void setCctcode(String cctCode) {
		this.setField(35, cctCode);
	}

	public String getCctcode() {
		return this.getField(35);
	}

}
