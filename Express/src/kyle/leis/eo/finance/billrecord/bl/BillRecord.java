package kyle.leis.eo.finance.billrecord.bl;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.billrecord.da.BillrecordColumns;
import kyle.leis.eo.finance.billrecord.dax.BillRecordDemand;
import kyle.leis.eo.finance.billrecord.tp.ModifyBillRecordTrans;
import kyle.leis.eo.finance.billrecord.tp.SaveBillRecordTrans;
import kyle.leis.eo.finance.billrecord.tp.UndoBillRecordTrans;

public class BillRecord {
	public BillrecordColumns save(BillrecordColumns objBillrecordColumns, 
			String strOperId,String isCheckBill) throws Exception {
		SaveBillRecordTrans objSaveBillRecordTrans = new SaveBillRecordTrans();
		objSaveBillRecordTrans.setParam(objBillrecordColumns, strOperId,isCheckBill);
		objSaveBillRecordTrans.execute();
		// 刷新
		Long lNewBrid = objSaveBillRecordTrans.getNewBrid();
		if (lNewBrid == null) return null;
		// 统计账单中运单所对应的记账费用
		BillRecordAmountTH objBRATH = new BillRecordAmountTH();
		objBRATH.setParam(objBillrecordColumns.getCococode(), 
				String.valueOf(lNewBrid),
				objBillrecordColumns.getBkbkcode());
		objBRATH.start();
		// 返回值		
		return BillRecordDemand.load(String.valueOf(lNewBrid));
	}
	
	/*public BillrecordColumns saveByFee(BillrecordColumns objBillrecordColumns,
			List listReceivable,
			List listPayable,
			String strOperId) throws Exception {
		SaveBillRecordTrans objSaveBillRecordTrans = new SaveBillRecordTrans();
		objSaveBillRecordTrans.setParam(objBillrecordColumns, 
				listReceivable, 
				listPayable, 
				strOperId);
		objSaveBillRecordTrans.execute();
		// 刷新
		Long lNewBrid = objSaveBillRecordTrans.getNewBrid();
		if (lNewBrid == null) return null;
		return BillRecordDemand.load(String.valueOf(lNewBrid));
	}	*/
	
	
	public BillrecordColumns saveByFee(BillrecordColumns objBillrecordColumns,
			List listReceivable,
			List listPayable,
			List listIncidentalfee,
			String strOperId,
			boolean isOriginCurrency) throws Exception {
		SaveBillRecordTrans objSaveBillRecordTrans = new SaveBillRecordTrans();
		objSaveBillRecordTrans.setParam(objBillrecordColumns, 
				listReceivable, 
				listPayable,
				listIncidentalfee,
				strOperId,
				isOriginCurrency);
		objSaveBillRecordTrans.execute();
		// 刷新
		Long lNewBrid = objSaveBillRecordTrans.getNewBrid();
		if (lNewBrid == null) return null;
		// 统计账单中运单所对应的记账费用
		BillRecordAmountTH objBRATH = new BillRecordAmountTH();
		objBRATH.setParam(objBillrecordColumns.getCococode(), 
				String.valueOf(lNewBrid),
				objBillrecordColumns.getBkbkcode());
		objBRATH.start();
		// 返回值
		return BillRecordDemand.load(String.valueOf(lNewBrid));
	}	
	
	
	public BillrecordColumns modifyBillRecordStatus(String strBrid, 
			String strOperId, 
			String strBrscode) throws Exception {
		if (StringUtility.isNull(strBrid)) return null;
		// 修改状态
		ModifyBillRecordTrans objModifyBillRecordTrans = new ModifyBillRecordTrans();
		objModifyBillRecordTrans.setModifyStatusParam(strBrid, 
				strOperId, 
				strBrscode);
		objModifyBillRecordTrans.execute();
		return BillRecordDemand.load(strBrid);
	}
	
	public BillrecordColumns modify(BillrecordColumns objBillrecordColumns, 
			String strOperId) throws Exception {
		ModifyBillRecordTrans objModifyBillRecordTrans = new ModifyBillRecordTrans();
		objModifyBillRecordTrans.setParam(objBillrecordColumns, strOperId);
		objModifyBillRecordTrans.execute();
		
		if (StringUtility.isNull(objBillrecordColumns.getBrbrid())) return null;
		return BillRecordDemand.load(objBillrecordColumns.getBrbrid());
	}
	
	public BillrecordColumns undo(String strBrid, 
			String strOperId) throws Exception {
		UndoBillRecordTrans objUndoBillRecordTrans = new UndoBillRecordTrans();
		
		objUndoBillRecordTrans.setParam(strBrid, strOperId);
		objUndoBillRecordTrans.execute();
		
		return BillRecordDemand.load(strBrid);
	}	
}
