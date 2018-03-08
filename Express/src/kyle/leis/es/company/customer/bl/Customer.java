package kyle.leis.es.company.customer.bl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.customer.da.CustomerColumns;
import kyle.leis.es.company.customer.da.SimplecustomerColumns;
import kyle.leis.es.company.customer.dax.CustomerDemand;
import kyle.leis.es.company.customer.tp.BatchModifyOperatorTrans;
import kyle.leis.es.company.customer.tp.SaveCreditlimitTrans;
import kyle.leis.es.company.customer.tp.SaveCustomerTransaction;
import kyle.leis.es.company.customer.tp.SaveTempAndcreditlimitTrans;
import kyle.leis.fs.businesslog.bl.Businesslog;

public class Customer {
	public void modifyCreditlimit(String strCocode, 
			String strCreditLimit,
			String strHoldHWRate,
			String strOperId) throws Exception {
		SaveCreditlimitTrans objSCLTrans = new SaveCreditlimitTrans();
		objSCLTrans.setParam(strCocode, strCreditLimit, strHoldHWRate);
		objSCLTrans.execute();
		// 记录备注
		String strOldOldCreditlimit = objSCLTrans.getOldCreditlimit();
		Businesslog objBusinesslog = new Businesslog();
		objBusinesslog.addBusinessLog(strCocode, strOperId, 
				"FI", 
				"原信用额度为：" + strOldOldCreditlimit + "，新信用额度为：" + strCreditLimit + 
				",原扣货比例" + objSCLTrans.getOldHoldHWRate() + ", 新扣货比例" + strHoldHWRate);
	}
	
	//修改额度与临时额度
	public void modifyTempAndCreditlimit(String strCocode, 
			String strCreditLimit,
			String strOperId,
			String strTemporarycreditlimit,
			String strTclstartdate,
			String strTclenddate,
			String strHoldHWRate) throws Exception {
		Date tclstartdate = DateFormatUtility.getStringDate(strTclstartdate, "yyyy-MM-dd HH:mm:ss");
		Date tclenddate = DateFormatUtility.getStringDate(strTclenddate, "yyyy-MM-dd HH:mm:ss");
		SaveTempAndcreditlimitTrans objSTACTrans = new SaveTempAndcreditlimitTrans();
		objSTACTrans.setParam(strCocode, strCreditLimit,
				strTemporarycreditlimit,tclstartdate,
				tclenddate, strHoldHWRate);
		objSTACTrans.execute();
		// 记录备注
		String strOldOldCreditlimit = objSTACTrans.getOldCreditlimit();
		if (StringUtility.isNull(strOldOldCreditlimit))
			strOldOldCreditlimit = "0";
		
		String strOldOldTemporarycreditlimit = objSTACTrans.getM_strOldTemporarycreditlimit();
		if (StringUtility.isNull(strOldOldTemporarycreditlimit))
			strOldOldTemporarycreditlimit = "0";
		
		Businesslog objBusinesslog = new Businesslog();
		objBusinesslog.addBusinessLog(strCocode, strOperId, 
				"FI", "原信用额度为：" + strOldOldCreditlimit + "，新信用额度为：" + strCreditLimit
				+",原临时信用额度为："+strOldOldTemporarycreditlimit+"，新临时信用额度为："+strTemporarycreditlimit + 
				",原扣货比例" + objSTACTrans.getOldHoldHWRate() + ", 新扣货比例" + strHoldHWRate);
	}
	
	public CustomerColumns save(CustomerColumns objCustomerColumns,
			List listCustomerSORestrict,
			String strOperId) throws Exception {
		SaveCustomerTransaction objSCTrans = new SaveCustomerTransaction();
		objSCTrans.setCustomerParam(objCustomerColumns, listCustomerSORestrict, strOperId);
		objSCTrans.execute();
		// 刷新缓冲
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();
		// 返回值
		String strCocode = objSCTrans.getSavedCocode();
		return CustomerDemand.load(strCocode);
	}
	
	/**
	 * 保存客户，简易版\网页版
	 * @param objSavingSimplecustomerColumns
	 * @return
	 * @throws Exception
	 */
	public SimplecustomerColumns save(SimplecustomerColumns objSavingSCColumns,
			String strOperId) throws Exception {
		CustomerColumns objCustomerColumns = buildCustomerColumns(objSavingSCColumns);
		
		SaveCustomerTransaction objSCTrans = new SaveCustomerTransaction();
		objSCTrans.setCustomerParam(objCustomerColumns, null, strOperId);
		objSCTrans.execute();
		// 刷新缓冲
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();
		// 返回值
		String strCocode = objSCTrans.getSavedCocode();		
		return CustomerDemand.loadSimpleCustomer(strCocode);
	}
	
	private CustomerColumns buildCustomerColumns(SimplecustomerColumns objSavingSCColumns) throws Exception {
		CustomerColumns objCustomerColumns = new CustomerColumns();
		
		objCustomerColumns.setCmcmallowprintlabelsign("N");
		objCustomerColumns.setCmcmcreditlimit(new BigDecimal("0"));
		objCustomerColumns.setCmcminvoicesign("N");
		objCustomerColumns.setCmcmodaholdsign("N");
		objCustomerColumns.setCmcmodanoticesign("N");
		objCustomerColumns.setCocoaddress(objSavingSCColumns.getCoco_address());
		objCustomerColumns.setCocoename(objSavingSCColumns.getCoco_sname());
		objCustomerColumns.setCocolabelcode(objSavingSCColumns.getCoco_sname());
		objCustomerColumns.setCoconame(objSavingSCColumns.getCoco_name());
		objCustomerColumns.setCocopostcode(".");
		objCustomerColumns.setCocoremark(objSavingSCColumns.getCoco_remark());
		objCustomerColumns.setCocosename(objSavingSCColumns.getCoco_sname());
		objCustomerColumns.setCocosname(objSavingSCColumns.getCoco_sname());
		objCustomerColumns.setCocowebsite(".");
		objCustomerColumns.setCstcstcode("C");
		objCustomerColumns.setCtctcode(objSavingSCColumns.getCmct_code());
		objCustomerColumns.setEeeecode("1");
		objCustomerColumns.setCoscscode("N");
		objCustomerColumns.setCmcocode(objSavingSCColumns.getCmco_code());
		objCustomerColumns.setCmcmstructruecode(objSavingSCColumns.getCmcm_structruecode());
		objCustomerColumns.setCmparentcocode(objSavingSCColumns.getCmco_code_parent());
		
		return objCustomerColumns;
	}
	
	
	
	/**
	 * 批量修改操作员
	 * @param astrCocode
	 * @param strNewOperId
	 * @param strMkcode
	 * @param strActionOperId
	 * @throws Exception
	 */
	public void batchModifyOperator(String[] astrCocode,
			String strNewOperId,
			String strMkcode, 
			String strActionOperId) throws Exception {
		BatchModifyOperatorTrans objBMOTrans = new BatchModifyOperatorTrans();
		objBMOTrans.setParam(astrCocode, strNewOperId, 
				strMkcode, strActionOperId);
		objBMOTrans.execute();
	}
}
