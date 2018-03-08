package kyle.leis.eo.finance.cashrecord.bl;

import java.math.BigDecimal;

import kyle.common.externalsv.recharge.IKuaiAfterRecharge;
import kyle.common.externalsv.recharge.KuaiRechargeParam;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.leis.eo.finance.cashrecord.da.CashrecordColumns;

public class KuaiAlipayAfterRecharge implements IKuaiAfterRecharge {

	public void saveRechargeRecord(KuaiRechargeParam objRechargeParam) throws Exception {
		CashrecordColumns objCashrecordColumns = buildCashrecordColumns(objRechargeParam);
		CashRecord objCashRecord = new CashRecord();
		objCashRecord.save(objCashrecordColumns, objRechargeParam.getstrOperId());
	}
	
	private CashrecordColumns buildCashrecordColumns(KuaiRechargeParam objRechargeParam) {
		CashrecordColumns objCashrecordColumns = new CashrecordColumns();
		
		objCashrecordColumns.setCkckcode("RMB");
		objCashrecordColumns.setCococode(objRechargeParam.getstrCocode());
		// objCashrecordColumns.setCrcrlabelcode(objRechargeParam.getBusinessNumber());
		objCashrecordColumns.setCrcroccurdate(DateFormatUtility.getSysdate());
		objCashrecordColumns.setCrcrreceiptlabelcode(objRechargeParam.getMerchant_id());
		objCashrecordColumns.setCrcrremark(objRechargeParam.getMerchant_param());
		objCashrecordColumns.setCrcrtotal(objRechargeParam.getAmount());
		objCashrecordColumns.setCrkcrkcode("RA");
		objCashrecordColumns.setCrscrscode("C");
		objCashrecordColumns.setEeeecode("1");
		objCashrecordColumns.setPtptcode("APY");
		objCashrecordColumns.setCrcrcurrencyrate(new BigDecimal("1"));
		
		return objCashrecordColumns;
	}

}
