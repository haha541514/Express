package kyle.leis.eo.finance.billrecord.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.billing.incidentalfee.da.IncidentalfeeColumns;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.payable.da.PayableforbillCondition;
import kyle.leis.eo.billing.payable.dax.PayableDemand;
import kyle.leis.eo.billing.payable.dax.PayableforbillCD;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import kyle.leis.eo.billing.receivable.da.ReceivableforbillCondition;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.eo.billing.receivable.dax.ReceivableforbillCD;
import kyle.leis.eo.finance.billrecord.bl.BillRecord;
import kyle.leis.eo.finance.billrecord.da.AllbillrecordidColumns;
import kyle.leis.eo.finance.billrecord.da.BillrecordColumns;
import kyle.leis.eo.finance.billrecord.da.BillrecordCondition;
import kyle.leis.eo.finance.billrecord.dax.BillRecordDemand;

public class BillRecordService extends AService {
	
	/**
	 * 查询账单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		BillrecordCondition objBillrecordCondition = (BillrecordCondition)objPD.getParameter(0, 
				BillrecordCondition.class);
		List objList = BillRecordDemand.query(objBillrecordCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	/**
	 * 查找账单费用
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryForBill(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		ReceivableforbillCondition objRvCondition = (ReceivableforbillCondition)objPD.getParameter(0, 
				ReceivableforbillCondition.class);
		// 应收费用
		List listReceivable = ReceivableDemand.queryForBill(objRvCondition);
		// 应付费用
		PayableforbillCD objPayableforbillCD = new PayableforbillCD();
		objPayableforbillCD.copyFromReceivable(objRvCondition);
		List listPayable = PayableDemand.queryForBill(objPayableforbillCD);
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listReceivable);
		objEncode.addParameter(listPayable);
		return objEncode.toString();
	}
	
	public String queryForServerBill(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		PayableforbillCondition objPForbillCondition = (PayableforbillCondition)objPD.getParameter(0, 
				PayableforbillCondition.class);
		// 应付费用
		List listPayable = PayableDemand.queryForBill(objPForbillCondition);
		// 应收费用
		ReceivableforbillCD objReceivableforbillCD = new ReceivableforbillCD();
		objReceivableforbillCD.copyFromPayable(objPForbillCondition);
		List listReceivable = ReceivableDemand.queryForBill(objReceivableforbillCD);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listReceivable);
		objEncode.addParameter(listPayable);
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

		BillrecordColumns objBillrecordColumns = (BillrecordColumns)objPD.getParameter(0, 
				BillrecordColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		String isCheckBill=(String)objPD.getParameter(2, String.class);
		
		BillRecord objBillRecord = new BillRecord();
		objBillrecordColumns = objBillRecord.save(objBillrecordColumns, strOperId,isCheckBill);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objBillrecordColumns);
		return objEncode.toString();
	}
	
	/**
	 * 按费用生成账单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	/*public String saveByFee(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);
		
		BillrecordColumns objBillrecordColumns = (BillrecordColumns)objPD.getParameter(0, 
				BillrecordColumns.class);
		List listReceivable = objPD.getParameterList(1, ReceivableColumns.class);
		List listPayable = objPD.getParameterList(2, PayableColumns.class);
		String strOperId = (String)objPD.getParameter(3, String.class);
		
		BillRecord objBillRecord = new BillRecord();
		objBillrecordColumns = objBillRecord.saveByFee(objBillrecordColumns, 
				listReceivable, listPayable, strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objBillrecordColumns);
		return objEncode.toString();
	}	*/
	
	public String saveByFee(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 5, this);
		
		BillrecordColumns objBillrecordColumns = (BillrecordColumns)objPD.getParameter(0, 
				BillrecordColumns.class);
		List listReceivable = objPD.getParameterList(1, ReceivableColumns.class);
		List listPayable = objPD.getParameterList(2, PayableColumns.class);
		List listIncidentalfee = objPD.getParameterList(3, IncidentalfeeColumns.class);
		String strOperId = (String)objPD.getParameter(4, String.class);
		
		String strBkcode = objBillrecordColumns.getBkbkcode();
		boolean isisOriginCurrency = false;
		if (strBkcode.startsWith("A02"))
			isisOriginCurrency = true;
		BillRecord objBillRecord = new BillRecord();
		objBillrecordColumns = objBillRecord.saveByFee(objBillrecordColumns, 
				listReceivable, listPayable, 
				listIncidentalfee, strOperId,
				isisOriginCurrency);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objBillrecordColumns);
		return objEncode.toString();
	}
	
	
	/**
	 * 修改账单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String modify(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		BillrecordColumns objBillrecordColumns = (BillrecordColumns)objPD.getParameter(0, 
				BillrecordColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		BillRecord objBillRecord = new BillRecord();
		objBillrecordColumns = objBillRecord.modify(objBillrecordColumns, strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objBillrecordColumns);
		return objEncode.toString();
	}
	
	/**
	 * 审核账单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String audit(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		String strBrid = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		BillRecord objBillRecord = new BillRecord();
		BillrecordColumns objBillrecordColumns = objBillRecord.modifyBillRecordStatus(strBrid, strOperId, "A");
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objBillrecordColumns);
		return objEncode.toString();		
	}
	
	/**
	 * 撤销账单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String undo(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		String strBrid = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		BillRecord objBillRecord = new BillRecord();
		BillrecordColumns objBillrecordColumns = objBillRecord.undo(strBrid, strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objBillrecordColumns);
		return objEncode.toString();		
	}
	
	/**
	 * 装载记录
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strBrid = (String)objPD.getParameter(0, String.class);
		BillrecordColumns objBillrecordColumns = BillRecordDemand.load(strBrid);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objBillrecordColumns);
		return objEncode.toString();
	}
	
	public String queryAllBillrecordID(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 1, this);
		String cwCode = (String)objPD.getParameter(0, String.class);
		
		BillRecordDemand objBillRecordDemand=new BillRecordDemand();
		AllbillrecordidColumns objAllbillrecord=objBillRecordDemand.queryAllBillrecordID(cwCode);
		
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objAllbillrecord);
		return objEncode.toString();
	}
}
