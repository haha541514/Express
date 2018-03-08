package kyle.leis.eo.operation.corewaybill.bl;

import java.util.List;

import kyle.leis.eo.billing.calculate.feecalculate.blx.AutoFeeCalculateThread;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.operation.corewaybill.tp.ModifyArrivalBWTrans;
import kyle.leis.eo.operation.corewaybill.tp.ModifyChargeweightTrans;
import kyle.leis.eo.operation.corewaybill.tp.ModifyCustomerWaybillcodeTrans;
import kyle.leis.eo.operation.corewaybill.tp.ModifyDepartureBWTrans;
import kyle.leis.eo.operation.corewaybill.tp.ModifyHawbWaybillcodeTrans;
import kyle.leis.eo.operation.corewaybill.tp.ModifyTransactionIDTrans;
import kyle.leis.eo.operation.corewaybill.tp.SimpleBQInputTrans;
import kyle.leis.eo.operation.corewaybill.tp.ModifyServerCWByEwbcodeTrans;
import kyle.leis.eo.operation.corewaybill.tp.ModifyServerWaybillcodeTrans;

public class Corewaybill {
	public void modifyArrivalBatchwaybill(String[] astrCwcode, 
			String strBwcodeDest, 
			String strOperId) throws Exception {
		ModifyArrivalBWTrans objMABWTrans = new ModifyArrivalBWTrans();
		objMABWTrans.setParam(astrCwcode, strBwcodeDest, strOperId);
		objMABWTrans.execute();
		
		if (astrCwcode != null && astrCwcode.length > 0) {
			for (int i = 0; i < astrCwcode.length; i++) {
				AutoFeeCalculateThread objAFCT = new AutoFeeCalculateThread(astrCwcode[i],
						IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
						false);
				objAFCT.start();
			}
		}
	}
	
	public void modifyDepartureBatchwaybill(String[] astrCwcode, 
			String strBwcodeDest, 
			String strOperId) throws Exception {
		ModifyDepartureBWTrans objMDBWTrans = new ModifyDepartureBWTrans();
		objMDBWTrans.setParam(astrCwcode, strBwcodeDest, strOperId);
		objMDBWTrans.execute();
		if (astrCwcode != null && astrCwcode.length > 0) {
			for (int i = 0; i < astrCwcode.length; i++) {
				AutoFeeCalculateThread objAFCT = new AutoFeeCalculateThread(astrCwcode[i],
						IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW,
						false);
				objAFCT.start();
			}
		}
	}
	
	public void simpleBQInput(String strCwcode,
			String strNewServerEwbcode, 
			String strOperId) throws Exception {
		SimpleBQInputTrans objMSEWBCT = new SimpleBQInputTrans();
		objMSEWBCT.setParam(strCwcode, strNewServerEwbcode, strOperId);
		objMSEWBCT.execute();
	}
	
	public void modifyServerWaybillcode(List listMSWCColumns, 
			String strOperId) throws Exception {
		ModifyServerWaybillcodeTrans objMSWTrans = new ModifyServerWaybillcodeTrans();
		objMSWTrans.setParam(listMSWCColumns, strOperId);
		objMSWTrans.execute();
	}
	
	public void modifyCustomerWaybillcode(List listMCWCColumns, 
			String strOperId) throws Exception {
		ModifyCustomerWaybillcodeTrans objMCWTrans = new ModifyCustomerWaybillcodeTrans();
		objMCWTrans.setParam(listMCWCColumns, strOperId);
		objMCWTrans.execute();
	}	
	
	public void modifyHawbWaybillcode(List listMHWCColumns, 
			String strOperId) throws Exception {
		ModifyHawbWaybillcodeTrans objMHWTrans = new ModifyHawbWaybillcodeTrans();
		objMHWTrans.setParam(listMHWCColumns, strOperId);
		objMHWTrans.execute();
	}		
	
	
	public void modifyTransactionID(List listMSWCColumns, 
			String strOperId) throws Exception {
		ModifyTransactionIDTrans objMTIDTrans = new ModifyTransactionIDTrans();
		objMTIDTrans.setParam(listMSWCColumns);
		objMTIDTrans.execute();
	}	
	
	public void modifyServerchargeweight(List listServerchargeweightColumns, 
			String strOperId) throws Exception {
		ModifyServerCWByEwbcodeTrans objMSCWBETrans = new ModifyServerCWByEwbcodeTrans();
		objMSCWBETrans.setParam(listServerchargeweightColumns, strOperId);
		objMSCWBETrans.execute();
	}	
	
	public void modifyChargeweight(List listModifyChargeweightColumns, 
			String strOperId) throws Exception {
		ModifyChargeweightTrans objMCWBETrans = new ModifyChargeweightTrans();
		objMCWBETrans.setParam(listModifyChargeweightColumns, strOperId);
		objMCWBETrans.execute();
	}	
	
}
