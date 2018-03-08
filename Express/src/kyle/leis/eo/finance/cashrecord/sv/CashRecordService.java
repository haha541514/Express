package kyle.leis.eo.finance.cashrecord.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.finance.cashrecord.bl.CashRecord;
import kyle.leis.eo.finance.cashrecord.da.CashrecordColumns;
import kyle.leis.eo.finance.cashrecord.da.CashrecordCondition;
import kyle.leis.eo.finance.cashrecord.dax.CashRecordDemand;

public class CashRecordService extends AService {
	/**
	 * 查询
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		CashrecordCondition objCrCondition = (CashrecordCondition)objPD.getParameter(0, 
				CashrecordCondition.class);
		List objList = CashRecordDemand.query(objCrCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	/**
	 * 根据主键装载记录
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strCrId = (String)objPD.getParameter(0, String.class);
		CashrecordColumns objCashrecordColumns = CashRecordDemand.load(strCrId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objCashrecordColumns);
		return objEncode.toString();
	}
	
	/**
	 * 保存，主键为空时新增否则修改
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		CashrecordColumns objCashrecordColumns = (CashrecordColumns)objPD.getParameter(0, 
				CashrecordColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		CashRecord objCashRecord = new CashRecord();
		objCashrecordColumns = objCashRecord.save(objCashrecordColumns, strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objCashrecordColumns);
		return objEncode.toString();
	}
	
	/**
	 * 直客收款
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String saveDirectCustomerCash(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);

		CashrecordColumns objCashrecordColumns = (CashrecordColumns)objPD.getParameter(0, 
				CashrecordColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		String[] astrCwCode = objPD.getParameterArray(2, String.class);
		
		CashRecord objCashRecord = new CashRecord();
		objCashrecordColumns = objCashRecord.saveDirectCustomerCash(objCashrecordColumns, 
				strOperId, 
				astrCwCode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objCashrecordColumns);
		return objEncode.toString();
	}	
	
	/**
	 * 服务商账单付款
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String saveByServerBillrecord(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);

		CashrecordColumns objCashrecordColumns = (CashrecordColumns)objPD.getParameter(0, 
				CashrecordColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		String[] astrSWBCode = objPD.getParameterArray(2, String.class);
		
		CashRecord objCashRecord = new CashRecord();
		objCashrecordColumns = objCashRecord.saveByServerBillrecord(objCashrecordColumns, 
				strOperId, 
				astrSWBCode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objCashrecordColumns);
		return objEncode.toString();
	}	
	
	
	/**
	 * 确定
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String confirm(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strCrid = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		CashRecord objCashRecord = new CashRecord();
		CashrecordColumns objCashrecordColumns = objCashRecord.modifyStatus(strCrid, "C", strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objCashrecordColumns);
		return objEncode.toString();
	}
	
	/**
	 * 作废
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String eliminate(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strCrid = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		CashRecord objCashRecord = new CashRecord();
		CashrecordColumns objCashrecordColumns = objCashRecord.modifyStatus(strCrid, "E", strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objCashrecordColumns);
		return objEncode.toString();
	}
	
	/**
	 * 审核
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String audit(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strCrid = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		CashRecord objCashRecord = new CashRecord();
		CashrecordColumns objCashrecordColumns = objCashRecord.modifyStatus(strCrid, "A", strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objCashrecordColumns);
		return objEncode.toString();
	}	
	
	/**
	 * 审批
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String release(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strCrid = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		CashRecord objCashRecord = new CashRecord();
		CashrecordColumns objCashrecordColumns = objCashRecord.modifyStatus(strCrid, "R", strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objCashrecordColumns);
		return objEncode.toString();
	}	
	
	public String withdraw(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strCrid = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		CashRecord objCashRecord = new CashRecord();
		CashrecordColumns objCashrecordColumns = objCashRecord.modifyStatus(strCrid, "N", strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objCashrecordColumns);
		return objEncode.toString();
	}	
	
}
