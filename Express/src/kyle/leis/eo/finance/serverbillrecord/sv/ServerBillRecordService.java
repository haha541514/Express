package kyle.leis.eo.finance.serverbillrecord.sv;


import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.finance.billrecord.da.BillrecordColumns;
import kyle.leis.eo.finance.serverbillrecord.bl.ServerBillRecord;
import kyle.leis.eo.finance.serverbillrecord.da.ChargeweightdifferenceColumns;
import kyle.leis.eo.finance.serverbillrecord.da.ChargeweightdifferenceCondition;
import kyle.leis.eo.finance.serverbillrecord.da.DifferencedetailColumns;
import kyle.leis.eo.finance.serverbillrecord.da.DifferenceincidentalsCondition;
import kyle.leis.eo.finance.serverbillrecord.da.ServerbillrecordColumns;
import kyle.leis.eo.finance.serverbillrecord.da.ServerbillrecordCondition;
import kyle.leis.eo.finance.serverbillrecord.da.ServerpayableColumns;
import kyle.leis.eo.finance.serverbillrecord.da.ServerpayableCondition;
import kyle.leis.eo.finance.serverbillrecord.dax.ServerBillRecordDemand;


public class ServerBillRecordService extends AService {
	/**
	 * 查询服务商账单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		ServerbillrecordCondition objSBRCondition = (ServerbillrecordCondition)objPD.getParameter(0, 
				ServerbillrecordCondition.class);
		List objList = ServerBillRecordDemand.query(objSBRCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}	
	
	/**
	 * 装载明细记录
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String loadPayable(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strSbrid = (String)objPD.getParameter(0, String.class);
		List objList = ServerBillRecordDemand.loadPayable(strSbrid);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	/**
	 * 查询服务商账单中的应付费用
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryPayable(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		ServerpayableCondition objSPYCondition = (ServerpayableCondition)objPD.getParameter(0, 
				ServerpayableCondition.class);
		List objList = ServerBillRecordDemand.queryPayable(objSPYCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	/**
	 * 查询应收重量差
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryChargeweightDifference(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		ChargeweightdifferenceCondition objCWDCondition = (ChargeweightdifferenceCondition)objPD.getParameter(0, 
				ChargeweightdifferenceCondition.class);
		boolean isHasNoServerBillRecord = Boolean.parseBoolean((String)objPD.getParameter(1, String.class));
		//boolean isHasNoServerBillRecord = false;
		List listCWDifference = ServerBillRecordDemand.queryCWDifference(objCWDCondition, isHasNoServerBillRecord);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listCWDifference);
		return objEncode.toString();
	}	
	
	
	/**
	 * 获得服务商重量差
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryServerCWDifference(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strSbrid = (String)objPD.getParameter(0, String.class);
		List listCWDifference = ServerBillRecordDemand.queryServerCWDifference(strSbrid);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listCWDifference);
		return objEncode.toString();
	}
	
	
	/**
	 * 获得费用差
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryChargeDifference(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strSbrid = (String)objPD.getParameter(0, String.class);
		List listChargeDifference = ServerBillRecordDemand.queryChargeDifference(strSbrid, false);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listChargeDifference);
		return objEncode.toString();
	}
	
	/**
	 * 保存
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		
		ServerbillrecordColumns objSBRColumns = (ServerbillrecordColumns)objPD.getParameter(0, 
				ServerbillrecordColumns.class);
		List listSaveBilldetail = objPD.getParameterList(1, ServerpayableColumns.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		
		ServerBillRecord objServerBillRecord = new ServerBillRecord();
		ServerbillrecordColumns objSavedBRColumns = objServerBillRecord.save(objSBRColumns, 
				listSaveBilldetail, 
				strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objSavedBRColumns);
		return objEncode.toString();	
	}
	
	/**
	 * 用账单上的服务商重量修改系统中的服务商重量
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String acceptServerChargeweight(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		List listSCWDifference = objPD.getParameterList(0, 
				DifferencedetailColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		ServerBillRecord objServerBillRecord = new ServerBillRecord();
		objServerBillRecord.acceptServerChargeweight(listSCWDifference, 
				strOperId);
		return "";
	}
	
	/**
	 * 用账单上的重量修改收货重量
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String acceptChargeweight(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		List listCWDifference = objPD.getParameterList(0, 
				ChargeweightdifferenceColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		ServerBillRecord objServerBillRecord = new ServerBillRecord();
		objServerBillRecord.acceptChargeweight(listCWDifference, 
				strOperId);
		return "";
	}	
	
	public String confirm(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		
		String strSbrId = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		boolean isModifyStatus = Boolean.parseBoolean((String)objPD.getParameter(2, String.class));
		
		ServerBillRecord objServerBillRecord = new ServerBillRecord();
		BillrecordColumns objBRColumns = objServerBillRecord.confirm(strSbrId, strOperId, isModifyStatus);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objBRColumns);
		return objEncode.toString();	
	}	
	
	
	/**
	 * 接受服务商费用
	 * 集合为单票件的费用
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String acceptSeverCharge(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		List listServerPayable = objPD.getParameterList(0, ServerpayableColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		ServerBillRecord objServerBillRecord = new ServerBillRecord();
		objServerBillRecord.acceptSeverCharge(listServerPayable, strOperId);
		return "";
	}	
	
	public String simpleAcceptSeverCharge(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		ServerBillRecord objServerBillRecord = new ServerBillRecord();
		objServerBillRecord.acceptSeverCharge(strCwcode, strOperId);
		return "";
	}	
	
	
	/**
	 * 删除服务商账单
	 */
	
	public String delete(Decoder objPD) throws Exception {
		checkParameterCount(objPD,1,this);
		
		String strSbrId = (String)objPD.getParameter(0, String.class);
		
		ServerBillRecord objServerBillRecord = new ServerBillRecord();
		objServerBillRecord.delete(strSbrId);
		
		return "";
	}
	
	public String modifyOwninputaudit(Decoder objPD) throws Exception{
		checkParameterCount(objPD,2,this);
		String cwCode = (String)objPD.getParameter(0, String.class);
		String ownInputAudit=(String)objPD.getParameter(1, String.class);
		ServerBillRecord objServerBillRecord=new ServerBillRecord();
		objServerBillRecord.modifyOwninputaudit(cwCode,ownInputAudit);
		return "";
	}
	/**
	 * 查询漏收的杂费
	 */
	public String getDifferenceincidentals(Decoder objPD) throws Exception{
		checkParameterCount(objPD,1,this);
		DifferenceincidentalsCondition objDifferenceincidentalsCondition = (DifferenceincidentalsCondition)objPD.getParameter(0, DifferenceincidentalsCondition.class);
		ServerBillRecordDemand objServerBillRecordDemand=new ServerBillRecordDemand();
		List incidentalsList=objServerBillRecordDemand.getDifferenceincidentals(objDifferenceincidentalsCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(incidentalsList);
		return objEncode.toString();
		
	}
	 

}
