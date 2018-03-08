package kyle.leis.eo.finance.cashrecord.bl;

import java.math.BigDecimal;

import kyle.common.externalsv.recharge.IAfterRecharge;
import kyle.common.externalsv.recharge.RechargeParam;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.leis.eo.finance.cashrecord.da.CashrecordColumns;

public class AlipayAfterRecharge implements IAfterRecharge {

	public void saveRechargeRecord(RechargeParam objRechargeParam) throws Exception {
		CashrecordColumns objCashrecordColumns = buildCashrecordColumns(objRechargeParam);
		CashRecord objCashRecord = new CashRecord();
		objCashRecord.save(objCashrecordColumns, objRechargeParam.getOperId());
	}
	
	private CashrecordColumns buildCashrecordColumns(RechargeParam objRechargeParam) {
		CashrecordColumns objCashrecordColumns = new CashrecordColumns();
		
		objCashrecordColumns.setCkckcode("RMB");
		objCashrecordColumns.setCococode(objRechargeParam.getCocode());
		// objCashrecordColumns.setCrcrlabelcode(objRechargeParam.getBusinessNumber());
		objCashrecordColumns.setCrcroccurdate(DateFormatUtility.getSysdate());
		objCashrecordColumns.setCrcrreceiptlabelcode(objRechargeParam.getBusinessNumber());
		objCashrecordColumns.setCrcrremark(objRechargeParam.getRemark());
		objCashrecordColumns.setCrcrtotal(objRechargeParam.getRechargeAmount());
		objCashrecordColumns.setCrkcrkcode("RA");
		objCashrecordColumns.setCrscrscode("C");
		objCashrecordColumns.setEeeecode("1");
		objCashrecordColumns.setPtptcode("APY");
		objCashrecordColumns.setCrcrcurrencyrate(new BigDecimal("1"));
		
		return objCashrecordColumns;
	}
}
