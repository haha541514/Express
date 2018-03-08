package kyle.leis.eo.billing.receivable.dax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.currency.bl.Currency;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import kyle.leis.eo.billing.receivable.da.ReceivableCondition;
import kyle.leis.eo.billing.receivable.da.ReceivableQuery;
import kyle.leis.eo.billing.receivable.da.ReceivableforbillColumns;
import kyle.leis.eo.billing.receivable.da.ReceivableforbillCondition;
import kyle.leis.eo.billing.receivable.da.ReceivableforbillQuery;
import kyle.leis.eo.billing.receivable.da.ReceivableforfaxCondition;
import kyle.leis.eo.billing.receivable.da.ReceivableforfaxQuery;
import kyle.leis.eo.billing.receivable.da.SimplereceivableColumns;
import kyle.leis.eo.billing.receivable.da.SimplereceivableCondition;
import kyle.leis.eo.billing.receivable.da.SimplereceivableQuery;
import kyle.leis.eo.billing.receivable.da.SumaccountingonlyColumns;
import kyle.leis.eo.billing.receivable.da.SumaccountingonlyQuery;
import kyle.leis.eo.billing.receivable.da.SumcorreceivableColumns;
import kyle.leis.eo.billing.receivable.da.SumcorreceivableQuery;
import kyle.leis.eo.billing.receivable.da.SumcorundocheckoutColumns;
import kyle.leis.eo.billing.receivable.da.SumcorundocheckoutQuery;
import kyle.leis.eo.billing.receivable.da.SumhasaccountrvColumns;
import kyle.leis.eo.billing.receivable.da.SumhasaccountrvQuery;
import kyle.leis.eo.billing.receivable.da.SumhasaccountrvQueryEX;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TdiBillingkindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCurrencykindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeekindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeestatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.feekind.da.FeekindColumns;
import kyle.leis.fs.dictionary.feekind.da.FeekindCondition;
import kyle.leis.fs.dictionary.feekind.dax.FeekindDemand;
import kyle.leis.hi.TblReceivable;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TdiBillingkind;
import kyle.leis.hi.TdiCurrencykind;
import kyle.leis.hi.TdiFeekind;
import kyle.leis.hi.TdiFeestatus;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.ThiReceivable;
import kyle.leis.hi.TopCorewaybill;

public class ReceivableDemand {
	public static List query(ReceivableCondition objReceivableCondition) 
	throws Exception {
		ReceivableQuery objReceivableQuery = new ReceivableQuery();
		objReceivableQuery.setCondition(objReceivableCondition);
		return objReceivableQuery.getResults();
	}
	
	public static List queryForfax(ReceivableforfaxCondition objRFFCondition) 
	throws Exception {
		ReceivableforfaxQuery objRFFQuery = new ReceivableforfaxQuery();
		objRFFQuery.setCondition(objRFFCondition);
		return objRFFQuery.getResults();
	}	
	
	
	public static List load(String strCwcode) throws Exception {
		ReceivableCondition objReceivableCondition = new ReceivableCondition();
		objReceivableCondition.setCwcode(strCwcode);
		return query(objReceivableCondition);
	}
	
	public static List loadAll(String strCwcode) throws Exception {
		ReceivableCondition objReceivableCondition = new ReceivableCondition();
		objReceivableCondition.setCwcode(strCwcode);
		
		ReceivableQueryEX objReceivableQuery = new ReceivableQueryEX();
		objReceivableQuery.setCondition(objReceivableCondition);
		return objReceivableQuery.getResults();		
	}	
	
	
	/**
	 * 是否存在运费
	 * @param strCwcode
	 * @return
	 * @throws Exception
	 */
	public static boolean isExistsFreight(String strCwcode) throws Exception {
		ReceivableCondition objRvCondition = new ReceivableCondition();
		objRvCondition.setCwcode(strCwcode);
		objRvCondition.setFkcode(IFeeCalculateBasicData.FEEKIND_DETAIL_FREIGHT);
		objRvCondition.setFscode("C,B,W");
		
		ReceivableQuery objReceivableQuery = new ReceivableQuery();
		objReceivableQuery.setCondition(objRvCondition);
		List objList = objReceivableQuery.getResults();
		if (objList == null || objList.size() < 1) return false;
		return true;
	}
	
	/**
	 * 是否存在草稿费用
	 * @param strCwcode
	 * @return
	 * @throws Exception
	 */
	public static boolean isExistsDraftFee(String strCwcode) throws Exception {
		ReceivableCondition objRvCondition = new ReceivableCondition();
		objRvCondition.setCwcode(strCwcode);
		objRvCondition.setFscode("D");
		
		ReceivableQuery objReceivableQuery = new ReceivableQuery();
		objReceivableQuery.setCondition(objRvCondition);
		List objList = objReceivableQuery.getResults();
		if (objList == null || objList.size() < 1) return false;
		return true;
	}	
	
	
	/**
	 * 是否存在不能修改的费用
	 * @param strCwcode
	 * @return
	 * @throws Exception
	 */
	public static boolean isExistsUnModifyFee(String strCwcode) throws Exception {
		SimplereceivableCondition objSRVCondition = new SimplereceivableCondition();
		objSRVCondition.setCw_code(strCwcode);
		objSRVCondition.setNotinfscode("E");
		
		SimplereceivableQuery objSRVQuery = new SimplereceivableQuery();
		objSRVQuery.setCondition(objSRVCondition);
		List objList = objSRVQuery.getResults();
		if (objList == null || objList.size() < 1) return false;
		for (int i = 0; i < objList.size(); i++) {
			SimplereceivableColumns objSRColumns = (SimplereceivableColumns)objList.get(i);
			String strFscode = objSRColumns.getRvfs_code();
			String strFkcode = objSRColumns.getRvfk_code();
			String strCocode = objSRColumns.getRvco_code();
			if (strFscode.equals(IReceivableBasicData.FEE_STATUS_BILL) || 
					strFscode.equals(IReceivableBasicData.FEE_STATUS_WRITEOFF)) {
				BigDecimal objSumFeeTotal = sumFeeTotal(objList, strFkcode, strCocode);
				if (objSumFeeTotal.compareTo(new BigDecimal("0")) != 0)
					return true;
			}
		}
		return false;
	}
	
	/**
	 * 统计未出货的费用
	 * @param strCocode
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal sumUndoSignout(String strCocode) throws Exception {
		BigDecimal objSunUndoCheckout = new BigDecimal("0");
		SumcorundocheckoutQuery objSCUCQuery = new SumcorundocheckoutQuery();
		objSCUCQuery.setCocode(strCocode);
		List objList = objSCUCQuery.getResults();
		if (objList == null || objList.size() < 1)
			return objSunUndoCheckout;
		SumcorundocheckoutColumns objSCUColumns = (SumcorundocheckoutColumns)objList.get(0);
		if (StringUtility.isNull(objSCUColumns.getSumrvtotal()))
			return objSunUndoCheckout;
		return new BigDecimal(objSCUColumns.getSumrvtotal());
	}
	
	/**
	 * 统计旧扣件
	 * @param strCocode
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal sumYesHoldUndoSignout(String strCocode) throws Exception {
		BigDecimal objSunUndoCheckout = new BigDecimal("0");
		SumcorundocheckoutQuery objSCUCQuery = new SumcorundocheckoutQuery();
		objSCUCQuery.setCocode(strCocode);
		objSCUCQuery.setEndsignindate(DateFormatUtility.getStandardSysdate().substring(0, 10) + " 00:00:00");
		objSCUCQuery.setInihscode("CH,CR");
		
		List objList = objSCUCQuery.getResults();
		if (objList == null || objList.size() < 1)
			return objSunUndoCheckout;
		SumcorundocheckoutColumns objSCUColumns = (SumcorundocheckoutColumns)objList.get(0);
		if (StringUtility.isNull(objSCUColumns.getSumrvtotal()))
			return objSunUndoCheckout;
		return new BigDecimal(objSCUColumns.getSumrvtotal());
	}	
	
	/**
	 * 统计旧的所有未出
	 * @param strCocode
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal sumYesUndoSignout(String strCocode) throws Exception {
		BigDecimal objSunUndoCheckout = new BigDecimal("0");
		SumcorundocheckoutQuery objSCUCQuery = new SumcorundocheckoutQuery();
		objSCUCQuery.setCocode(strCocode);
		objSCUCQuery.setEndsignindate(DateFormatUtility.getStandardSysdate().substring(0, 10) + " 00:00:00");
		
		List objList = objSCUCQuery.getResults();
		if (objList == null || objList.size() < 1)
			return objSunUndoCheckout;
		SumcorundocheckoutColumns objSCUColumns = (SumcorundocheckoutColumns)objList.get(0);
		if (StringUtility.isNull(objSCUColumns.getSumrvtotal()))
			return objSunUndoCheckout;
		return new BigDecimal(objSCUColumns.getSumrvtotal());
	}	
	
	/**
	 * 统计当天所有未出
	 * @param strCocode
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal sumTodayUndoSignout(String strCocode) throws Exception {
		BigDecimal objSunUndoCheckout = new BigDecimal("0");
		SumcorundocheckoutQuery objSCUCQuery = new SumcorundocheckoutQuery();
		objSCUCQuery.setCocode(strCocode);
		objSCUCQuery.setStartsignindate(DateFormatUtility.getStandardSysdate().substring(0, 10) + " 00:00:00");
		objSCUCQuery.setEndsignindate(DateFormatUtility.getStandardSysdate().substring(0, 10) + " 23:59:59");
		
		List objList = objSCUCQuery.getResults();
		if (objList == null || objList.size() < 1)
			return objSunUndoCheckout;
		SumcorundocheckoutColumns objSCUColumns = (SumcorundocheckoutColumns)objList.get(0);
		if (StringUtility.isNull(objSCUColumns.getSumrvtotal()))
			return objSunUndoCheckout;
		return new BigDecimal(objSCUColumns.getSumrvtotal());
	}
	
	/**
	 * 统计当天扣件未出
	 * @param strCocode
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal sumTodayHoldUndoSignout(String strCocode) throws Exception {
		BigDecimal objSunUndoCheckout = new BigDecimal("0");
		SumcorundocheckoutQuery objSCUCQuery = new SumcorundocheckoutQuery();
		objSCUCQuery.setCocode(strCocode);
		objSCUCQuery.setStartsignindate(DateFormatUtility.getStandardSysdate().substring(0, 10) + " 00:00:00");
		objSCUCQuery.setEndsignindate(DateFormatUtility.getStandardSysdate().substring(0, 10) + " 23:59:59");
		objSCUCQuery.setInihscode("CH,CR");
		
		List objList = objSCUCQuery.getResults();
		if (objList == null || objList.size() < 1)
			return objSunUndoCheckout;
		SumcorundocheckoutColumns objSCUColumns = (SumcorundocheckoutColumns)objList.get(0);
		if (StringUtility.isNull(objSCUColumns.getSumrvtotal()))
			return objSunUndoCheckout;
		return new BigDecimal(objSCUColumns.getSumrvtotal());
	}	
	
	/**
	 * 统计所有扣件未出
	 * @param strCocode
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal sumHoldUndoSignout(String strCocode) throws Exception {
		BigDecimal objSunUndoCheckout = new BigDecimal("0");
		SumcorundocheckoutQuery objSCUCQuery = new SumcorundocheckoutQuery();
		objSCUCQuery.setCocode(strCocode);
		objSCUCQuery.setInihscode("CH,CR");
		
		List objList = objSCUCQuery.getResults();
		if (objList == null || objList.size() < 1)
			return objSunUndoCheckout;
		SumcorundocheckoutColumns objSCUColumns = (SumcorundocheckoutColumns)objList.get(0);
		if (StringUtility.isNull(objSCUColumns.getSumrvtotal()))
			return objSunUndoCheckout;
		return new BigDecimal(objSCUColumns.getSumrvtotal());
	}	
	
	/**
	 * 统计账单中运单的记账费用
	 * @param strCocode
	 * @param strBrid
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal sumAccountingonly(String strCocode,
			String strBrid) throws Exception {
		BigDecimal objAccountingonly = new BigDecimal("0");
		SumaccountingonlyQuery objSAOQuery = new SumaccountingonlyQuery();
		objSAOQuery.setCocode(strCocode);
		objSAOQuery.setBrid(strBrid);
		List objList = objSAOQuery.getResults();
		if (objList == null || objList.size() < 1)
			return objAccountingonly;
		SumaccountingonlyColumns objSAOColumns = (SumaccountingonlyColumns)objList.get(0);
		if (StringUtility.isNull(objSAOColumns.getSumrvtotal()))
			return objAccountingonly;
		return new BigDecimal(objSAOColumns.getSumrvtotal());
	}	
	
	public static String sumHasAccountingAmount(String strCocode,
			String strBrid) throws Exception {
		SumhasaccountrvQuery objSHARVQ = new SumhasaccountrvQuery();
		objSHARVQ.setBrid(strBrid);
		objSHARVQ.setCocode(strCocode);
		List listResults = objSHARVQ.getResults();
		if (listResults == null || listResults.size() < 1)
			return "0";
		return ((SumhasaccountrvColumns)listResults.get(0)).getSumrvtotal();
	}	
	
	public static String sumHasAccountingOriginAmount(String strCocode,
			String strBrid) throws Exception {
		SumhasaccountrvQueryEX objSHARVQ = new SumhasaccountrvQueryEX();
		objSHARVQ.setBrid(strBrid);
		objSHARVQ.setCocode(strCocode);
		List listResults = objSHARVQ.getResults();
		if (listResults == null || listResults.size() < 1)
			return "0";
		return ((SumhasaccountrvColumns)listResults.get(0)).getSumrvtotal();
	}		
	
	
	/**
	 * 查找正常费用
	 * @param strCwcode
	 * @return
	 * @throws Exception
	 */
	public static List loadSimpleNormalFee(String strCwcode) throws Exception {
		SimplereceivableCondition objSRVCondition = new SimplereceivableCondition();
		objSRVCondition.setCw_code(strCwcode);
		objSRVCondition.setNotinfscode("E");
		
		SimplereceivableQuery objSRVQuery = new SimplereceivableQuery();
		objSRVQuery.setCondition(objSRVCondition);
		return objSRVQuery.getResults();
	}
	
	/**
	 * 统计某项费用的总和
	 * @param objList
	 * @param strFkcode
	 * @param strCocode
	 * @return
	 */
	public static BigDecimal sumFeeTotal(List objList, 
			String strFkcode, 
			String strCocode) {
		BigDecimal objSumFeeTotal = new BigDecimal("0");
		if (objList == null || objList.size() < 1) return objSumFeeTotal;
		for (int i = 0; i < objList.size(); i++) {
			SimplereceivableColumns objSRColumns = (SimplereceivableColumns)objList.get(i);
			String strRFkcode = objSRColumns.getRvfk_code();
			String strRCocode = objSRColumns.getRvco_code();
			String strCurrencyRate = objSRColumns.getRvrv_currencyrate();
			String strActualTotal = objSRColumns.getRvrv_actualtotal();
			BigDecimal objActualTotal = new BigDecimal(strActualTotal).multiply(new BigDecimal(strCurrencyRate));
			objActualTotal = objActualTotal.divide(new BigDecimal("1"), 2, 4);
			if ((StringUtility.isNull(strFkcode) || strRFkcode.equals(strFkcode)) && 
					strRCocode.equals(strCocode))
				objSumFeeTotal = objSumFeeTotal.add(objActualTotal);
		}
		return objSumFeeTotal;
	}
	
	public static BigDecimal sumCorReceivable(String strCocode,
			String strDate) throws Exception {
		SumcorreceivableQuery objSCRQ = new SumcorreceivableQuery();
		objSCRQ.setCocode(strCocode);
		objSCRQ.setRvoccurdate(strDate);
		List listResults = objSCRQ.getResults();
		if (listResults == null || listResults.size() < 1)
			return new BigDecimal("0");
		SumcorreceivableColumns objSCRColumns = (SumcorreceivableColumns)listResults.get(0);
		String strRvtotal = objSCRColumns.getSumrvtotal();
		if (StringUtility.isNull(strRvtotal))
			return new BigDecimal("0");
		return new BigDecimal(strRvtotal);
	}	
	
	
	/**
	 * 是否全部核销
	 * @param strCwcode
	 * @return
	 * @throws Exception
	 */
	public static boolean isAllWriteOff(String strCwcode) throws Exception {
		SimplereceivableCondition objSRVCondition = new SimplereceivableCondition();
		objSRVCondition.setCw_code(strCwcode);
		objSRVCondition.setNotinfscode("E,W");
		
		SimplereceivableQuery objSRVQuery = new SimplereceivableQuery();
		objSRVQuery.setCondition(objSRVCondition);
		List objList = objSRVQuery.getResults();
		if (objList == null || objList.size() < 1) return true;
		return false;
	}
	
	/**
	 * 根据SimplereceivableColumns检查是否全核销
	 * @param objList
	 * @return
	 * @throws Exception
	 */
	public static boolean isAllWriteOff(List objList) throws Exception {
		if (objList == null || objList.size() < 1)
			return false;
		for (int i = 0; i < objList.size(); i++) {
			SimplereceivableColumns objSRColumns = (SimplereceivableColumns)objList.get(i);
			String strFscode = objSRColumns.getRvfs_code();
			if (!strFscode.equals("E") && !strFscode.equals("W"))
				return false;
		}
		return true;
	}
	
	public static ReceivableColumns loadByRvid(String strRvid) throws Exception {
		ReceivableQuery objReceivableQuery = new ReceivableQuery();
		objReceivableQuery.setRvid(strRvid);
		List objList = objReceivableQuery.getResults();
		if (objList == null || objList.size() < 1) return null;
		return (ReceivableColumns)objList.get(0);
	}
	
	public static List queryForBill(ReceivableforbillCondition objRForbillCondition) 
	throws Exception {
		ReceivableforbillQuery objRForBillQuery = new ReceivableforbillQuery();
		//objRForbillCondition.setFscode(IReceivableBasicData.FEE_STATUS_CONFIRM);
		
		if (!StringUtility.isNull(objRForbillCondition.getCcocode()))
			objRForbillCondition.setCocode(objRForbillCondition.getCcocode());
		if (!StringUtility.isNull(objRForbillCondition.getScocode()))
			objRForbillCondition.setCocode(objRForbillCondition.getScocode());
		if (!StringUtility.isNull(objRForbillCondition.getIncustomerewbcode()) &&
				StringUtility.isNull(objRForbillCondition.getInserverewbcode()))
			objRForbillCondition.setInserverewbcode("TTTXXX");
		if (StringUtility.isNull(objRForbillCondition.getIncustomerewbcode()) &&
				!StringUtility.isNull(objRForbillCondition.getInserverewbcode()))
			objRForbillCondition.setIncustomerewbcode("TTTXXX");
		objRForBillQuery.setCondition(objRForbillCondition);
		return objRForBillQuery.getResults();
	}
	
	public static List<ReceivableColumns> transferBillRvToRvColumns(List listCwReceivable) {
		List<ReceivableColumns> listRFBColumns = new ArrayList<ReceivableColumns>();
		if (listCwReceivable == null || listCwReceivable.size() < 1) 
			return listRFBColumns;
		for (int i = 0; i < listCwReceivable.size(); i++) {
			ReceivableColumns objReceivableColumns = new ReceivableColumns();
			ReceivableforbillColumns objRFBColumns = (ReceivableforbillColumns)listCwReceivable.get(i);
			
			objReceivableColumns.setRvrvid(Long.parseLong(objRFBColumns.getRvrvid()));
			objReceivableColumns.setRvrvactualtotal(new BigDecimal(objRFBColumns.getRvrvactualtotal()));
			objReceivableColumns.setRvrvcurrencyrate(new BigDecimal(objRFBColumns.getRvrvcurrencyrate()));
			objReceivableColumns.setFkfkcode(objRFBColumns.getFkfkcode());
			
			listRFBColumns.add(objReceivableColumns);
		}
		return listRFBColumns;
	}
	
	public static List transferImportRvToColumns(HousewaybillColumns objHWColumns,
			List listRvColumnsforimport,
			String strOperid) throws Exception {
		List<ReceivableColumns> listRvColumns = new ArrayList<ReceivableColumns>();
		if (listRvColumnsforimport == null || listRvColumnsforimport.size() < 1) 
			return listRvColumns;
		for (int i = 0; i < listRvColumnsforimport.size(); i++) {
			ReceivableColumns objReceivableColumns = new ReceivableColumns();
			ReceivableColumnsForImport objRVFImport = (ReceivableColumnsForImport)listRvColumnsforimport.get(i);
			
			objReceivableColumns.setBkbkcode("A0101");
			objReceivableColumns.setCkckcode(objRVFImport.getCkcode());
			objReceivableColumns.setCococode(objHWColumns.getCcococode());
			objReceivableColumns.setCwcwcode(Long.parseLong(objHWColumns.getHwcwcode()));
			// 查找费用
			FeekindCondition objFeekindCondition = new FeekindCondition();
			objFeekindCondition.setFkname(objRVFImport.getFkname());
			List listFeekind = FeekindDemand.query(objFeekindCondition);
			if (listFeekind == null || listFeekind.size() < 1 || ((FeekindColumns)listFeekind.get(0)).getFkfkcode().length() < 5)
				throw (new Exception(objRVFImport.getFkname() + "的费用名称不存在"));
			
			objReceivableColumns.setFkfkcode(((FeekindColumns)listFeekind.get(0)).getFkfkcode());
			objReceivableColumns.setFsfscode("C");
			objReceivableColumns.setRvrvactualtotal(new BigDecimal(objRVFImport.getRvtotal()));
			// 取币种
			Currency objCurrency = new Currency();
			String strCurrencyrate = objCurrency.getCurrencyrate(objHWColumns.getCcococode(), 
					objHWColumns.getPkpkcode(), 
					objHWColumns.getAbwadddate(), 
					objHWColumns.getEeeecode(), 
					objRVFImport.getCkcode());
			if (StringUtility.isNull(strCurrencyrate) || 
					new BigDecimal(strCurrencyrate).compareTo(new BigDecimal("0")) <= 0)
				throw (new Exception(objRVFImport.getCkcode() + "的币种无法获得对应的汇率"));
			objReceivableColumns.setRvrvcurrencyrate(new BigDecimal(strCurrencyrate));
			objReceivableColumns.setRvrvoccurdate(DateFormatUtility.getStandardDate(objHWColumns.getAbwadddate()));
			objReceivableColumns.setRvrvremark("批量导入应收" + objRVFImport.getRvremark());
			objReceivableColumns.setRvrvtotal(new BigDecimal(objRVFImport.getRvtotal()));
			objReceivableColumns.setRvrvunitnumber(new BigDecimal(objHWColumns.getCwcwchargeweight()));
			objReceivableColumns.setRvrvunitprice(new BigDecimal(objRVFImport.getRvtotal()).divide(new BigDecimal(objHWColumns.getCwcwchargeweight()), 2, 4));
			objReceivableColumns.setMopopid(Long.parseLong(strOperid));
			objReceivableColumns.setCopopid(Long.parseLong(strOperid));
			
			listRvColumns.add(objReceivableColumns);
		}
		return listRvColumns;
	}
	
	
	public static ReceivableColumns getOriginReceivable(ReceivableColumns objRvColumns, 
			List listOriginRvColumns) throws Exception {
		if (listOriginRvColumns == null || listOriginRvColumns.size() < 1)
			return null;
		for (int i = 0; i < listOriginRvColumns.size(); i++) {
			ReceivableColumns objOriginRvColumns = (ReceivableColumns)listOriginRvColumns.get(i);
			// 修改则判断主键是否一致
			if (!StringUtility.isNull(objRvColumns.getRvrvid())) {
				if (objRvColumns.getRvrvid().equals(objOriginRvColumns.getRvrvid()))
					return objOriginRvColumns;
				continue;
			}
			// 公司、费用以及运单一致且无红冲蓝补标记
			/*
			if (objRvColumns.getCococode().equals(objOriginRvColumns.getCococode()) &&
					objRvColumns.getFkfkcode().equals(objOriginRvColumns.getFkfkcode()) &&
					objRvColumns.getCwcwcode().equals(objOriginRvColumns.getCwcwcode()) &&
					StringUtility.isNull(objOriginRvColumns.getRvrvidreference())) {
				// 如果有渠道则判断渠道是否一致
				if (!StringUtility.isNull(objRvColumns.getChnchncode()) &&
						!StringUtility.isNull(objOriginRvColumns.getChnchncode()) && 
						objRvColumns.getChnchncode().equals(objOriginRvColumns.getChnchncode()))
					return objOriginRvColumns;
				// 如果渠道同时为空，也认为一致
				if (StringUtility.isNull(objRvColumns.getChnchncode()) && 
						StringUtility.isNull(objOriginRvColumns.getChnchncode()))
						return objOriginRvColumns;
			}
			*/
			if (objRvColumns.getFkfkcode().equals(objOriginRvColumns.getFkfkcode()) &&
					objRvColumns.getCwcwcode().equals(objOriginRvColumns.getCwcwcode()) &&
					StringUtility.isNull(objOriginRvColumns.getRvrvidreference())) {
				return objOriginRvColumns;
			}			
		}
		return null;
	}
	
	public static BigDecimal sumActualTotal(List listReceivable, boolean isOriginCurrency) throws Exception {
		BigDecimal objSumActualTotal = new BigDecimal("0");
		if (listReceivable == null || listReceivable.size() < 1)
			return objSumActualTotal;
		for (int i = 0; i < listReceivable.size(); i++) {
			ReceivableColumns objReceivableColumns = (ReceivableColumns)listReceivable.get(i);
			String strFkAccountingonlysign = TdiFeekindDC.loadByKey(objReceivableColumns.getFkfkcode()).getFkAccountingonlysign();
			if (!StringUtility.isNull(strFkAccountingonlysign) && strFkAccountingonlysign.equals("Y"))
				continue;
			BigDecimal objActualTotal = new BigDecimal(objReceivableColumns.getRvrvactualtotal());
			BigDecimal objCurrencyRate = new BigDecimal(objReceivableColumns.getRvrvcurrencyrate());
			if (!isOriginCurrency) {
				objActualTotal = objActualTotal.multiply(objCurrencyRate).divide(new BigDecimal("1"), 2, 4);
			}
			objSumActualTotal = objSumActualTotal.add(objActualTotal);
		}
		return objSumActualTotal;
	}
	
	public static BigDecimal sumActualTotal2(List listReceivable, boolean isOriginCurrency) throws Exception {
		BigDecimal objSumActualTotal = new BigDecimal("0");
		if (listReceivable == null || listReceivable.size() < 1)
			return objSumActualTotal;
		for (int i = 0; i < listReceivable.size(); i++) {
			ReceivableforbillColumns objReceivableColumns = (ReceivableforbillColumns)listReceivable.get(i);
			String strFkAccountingonlysign = TdiFeekindDC.loadByKey(objReceivableColumns.getFkfkcode()).getFkAccountingonlysign();
			if (!StringUtility.isNull(strFkAccountingonlysign) && strFkAccountingonlysign.equals("Y"))
				continue;
			BigDecimal objActualTotal = new BigDecimal(objReceivableColumns.getRvrvactualtotal());
			BigDecimal objCurrencyRate = new BigDecimal(objReceivableColumns.getRvrvcurrencyrate());
			if (!isOriginCurrency) {
				objActualTotal = objActualTotal.multiply(objCurrencyRate).divide(new BigDecimal("1"), 2, 4);
			}
			objSumActualTotal = objSumActualTotal.add(objActualTotal);
		}
		return objSumActualTotal;
	}	
	
	public static void buildReceivalbe(HousewaybillColumns objHwcolumns,
			List listRvColumns,
			String strOperID) throws Exception {
		if (listRvColumns == null || listRvColumns.size() < 1)
			return;
		for (int i = 0; i < listRvColumns.size(); i++) {
			ReceivableColumns objReceivableColumns = (ReceivableColumns)listRvColumns.get(i);
			
			objReceivableColumns.setBkbkcode("A0101");
			objReceivableColumns.setCococode(objHwcolumns.getCcococode());
				
			objReceivableColumns.setCkckcode("RMB");
			objReceivableColumns.setCopopid(Long.parseLong(strOperID));
			objReceivableColumns.setCwcwcode(Long.parseLong(objHwcolumns.getHwcwcode()));
			objReceivableColumns.setFsfscode("C");
			objReceivableColumns.setMopopid(Long.parseLong(strOperID));
			objReceivableColumns.setRvrvcreatedate(DateFormatUtility.getSysdate());
			objReceivableColumns.setRvrvcurrencyrate(new BigDecimal("1"));
			objReceivableColumns.setRvrvmodifydate(DateFormatUtility.getSysdate());
			objReceivableColumns.setRvrvoccurdate(DateFormatUtility.getStandardDate(objHwcolumns.getAbwadddate()));
			objReceivableColumns.setRvrvremark("收货时手工增加费用");
			objReceivableColumns.setRvrvtotal(new BigDecimal(objReceivableColumns.getRvrvactualtotal()));
			objReceivableColumns.setRvrvcommissionrate(new BigDecimal("0"));
		}
	}
	
	public static ReceivableColumns buildReceivalbe(HousewaybillColumns objHwcolumns,
			BigDecimal objUnitnumber,
			BigDecimal objFeeAmount,
			String strFkcode,
			String strCkcode,
			String strRemark,
			String strOperID) throws Exception {
		ReceivableColumns objReceivableColumns = new ReceivableColumns();
		
		objReceivableColumns.setBkbkcode("A0101");
		objReceivableColumns.setCococode(objHwcolumns.getCcococode());
			
		objReceivableColumns.setCkckcode(strCkcode);
		objReceivableColumns.setCopopid(Long.parseLong(strOperID));
		objReceivableColumns.setCwcwcode(Long.parseLong(objHwcolumns.getHwcwcode()));
		objReceivableColumns.setFkfkcode(strFkcode);
		objReceivableColumns.setFsfscode("C");
		objReceivableColumns.setMopopid(Long.parseLong(strOperID));
		objReceivableColumns.setRvrvactualtotal(objFeeAmount);
		objReceivableColumns.setRvrvcreatedate(DateFormatUtility.getSysdate());
		objReceivableColumns.setRvrvcurrencyrate(new BigDecimal("1"));
		objReceivableColumns.setRvrvmodifydate(DateFormatUtility.getSysdate());
		objReceivableColumns.setRvrvoccurdate(DateFormatUtility.getStandardDate(objHwcolumns.getAbwadddate()));
		objReceivableColumns.setRvrvremark(strRemark);
		objReceivableColumns.setRvrvtotal(objFeeAmount);
		objReceivableColumns.setRvrvunitnumber(objUnitnumber);
		objReceivableColumns.setRvrvcommissionrate(new BigDecimal("0"));
		
		if (objUnitnumber.compareTo(new BigDecimal("0")) == 0)
			objReceivableColumns.setRvrvunitprice(new BigDecimal("1"));
		else
			objReceivableColumns.setRvrvunitprice(objFeeAmount.divide(objUnitnumber, 2, 4));
		
		return objReceivableColumns;
	}	
	
	
	public static BigDecimal sumActualTotal(String strCwcode) throws Exception {
		List listReceivable = load(strCwcode);
		return sumActualTotal(listReceivable, false);
	}	
	
	public static void setReceivalbeFromColumns(TblReceivable objTblReceivable, 
			ReceivableColumns objRvColumns, 
			String strOperId, 
			Session objSession) throws Exception {
		if (!StringUtility.isNull(objRvColumns.getRvepcode()))
			objTblReceivable.setEpCode(Long.parseLong(objRvColumns.getRvepcode()));
		if (!StringUtility.isNull(objRvColumns.getRvepvid()))
			objTblReceivable.setEpvId(Integer.parseInt(objRvColumns.getRvepvid()));
		if (!StringUtility.isNull(objRvColumns.getRvrvidreference()))
			objTblReceivable.setRvIdReference(Long.parseLong(objRvColumns.getRvrvidreference()));
		
		objTblReceivable.setRvActualtotal(new BigDecimal(objRvColumns.getRvrvactualtotal()));
		objTblReceivable.setRvTotal(new BigDecimal(objRvColumns.getRvrvtotal()));
		objTblReceivable.setRvUnitnumber(new BigDecimal(objRvColumns.getRvrvunitnumber()));
		objTblReceivable.setRvUnitprice(new BigDecimal(objRvColumns.getRvrvunitprice()));
		
		if (!StringUtility.isNull(objRvColumns.getRvrvauditdate()))
			objTblReceivable.setRvAuditdate(DateFormatUtility.getStandardDate(objRvColumns.getRvrvauditdate()));
		
		String strCommissionrate = objRvColumns.getRvrvcommissionrate();
		if (StringUtility.isNull(strCommissionrate))
			strCommissionrate = "0";	
		objTblReceivable.setRvCommissionrate(new BigDecimal(strCommissionrate));
		objTblReceivable.setRvCurrencyrate(new BigDecimal(objRvColumns.getRvrvcurrencyrate()));
		
		objTblReceivable.setRvModifydate(DateFormatUtility.getSysdate());
		objTblReceivable.setRvOccurdate(DateFormatUtility.getStandardDate(objRvColumns.getRvrvoccurdate()));
		objTblReceivable.setRvRemark(objRvColumns.getRvrvremark());
		
		if (!StringUtility.isNull(objRvColumns.getChnchncode())) {
			TchnChannel objTchnChannel = (TchnChannel)objSession.load(TchnChannel.class, 
					objRvColumns.getChnchncode());
			objTblReceivable.setTchnChannel(objTchnChannel);
		}
		if (!StringUtility.isNull(objRvColumns.getCococode())) {
			TcoCorporation objTcoCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					objRvColumns.getCococode());
			objTblReceivable.setTcoCorporation(objTcoCorporation);
		}
		if (!StringUtility.isNull(objRvColumns.getBkbkcode())) {
			TdiBillingkind objTdiBillingkind = TdiBillingkindDC.loadByKey(objRvColumns.getBkbkcode());
			objTblReceivable.setTdiBillingkind(objTdiBillingkind);
		}		
		if (!StringUtility.isNull(objRvColumns.getCkckcode())) {
			TdiCurrencykind objTdiCurrencykind = TdiCurrencykindDC.loadByKey(objRvColumns.getCkckcode());
			objTblReceivable.setTdiCurrencykind(objTdiCurrencykind);
		}
		if (!StringUtility.isNull(objRvColumns.getFkfkcode())) {
			TdiFeekind objTdiFeekind = TdiFeekindDC.loadByKey(objRvColumns.getFkfkcode());
			objTblReceivable.setTdiFeekind(objTdiFeekind);
		}
		if (!StringUtility.isNull(objRvColumns.getFsfscode())) {
			TdiFeestatus objTdiFeestatus = TdiFeestatusDC.loadByKey(objRvColumns.getFsfscode());
			objTblReceivable.setTdiFeestatus(objTdiFeestatus);
		}		
		if (!StringUtility.isNull(strOperId)) {
			TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(strOperId);
			objTblReceivable.setTdiOperatorByRvOpIdModifier(objTdiOperator);
		}
		if (!StringUtility.isNull(objRvColumns.getCwcwcode())) {
			TopCorewaybill objTopCorewaybill = (TopCorewaybill)objSession.load(TopCorewaybill.class, 
					Long.parseLong(objRvColumns.getCwcwcode()));
			objTblReceivable.setTopCorewaybill(objTopCorewaybill);
		}
	}
	
	/**
	 * 将应收费用复制为历史费用
	 * @param objTblReceivable
	 * @param objSession
	 */
	public static ThiReceivable createHiReceivable(TblReceivable objTblReceivable,
			String strOperId,
			Session objSession) {
		ThiReceivable objThiReceivable = new ThiReceivable();
		
		objThiReceivable.setEpCode(objTblReceivable.getEpCode());
		objThiReceivable.setEpvId(objTblReceivable.getEpvId());
		objThiReceivable.setRvActualtotal(objTblReceivable.getRvActualtotal());
		objThiReceivable.setRvAuditdate(objTblReceivable.getRvAuditdate());
		objThiReceivable.setRvCommissionrate(objTblReceivable.getRvCommissionrate());
		objThiReceivable.setRvCreatedate(objTblReceivable.getRvCreatedate());
		objThiReceivable.setRvCurrencyrate(objTblReceivable.getRvCurrencyrate());
		objThiReceivable.setRvId(objTblReceivable.getRvId());
		objThiReceivable.setRvIdReference(objTblReceivable.getRvIdReference());
		objThiReceivable.setRvOccurdate(objTblReceivable.getRvOccurdate());
		
		if (objTblReceivable.getTdiOperatorByRvOpIdAuditor() != null)
			objThiReceivable.setRvOpIdAuditor(objTblReceivable.getTdiOperatorByRvOpIdAuditor().getOpId());
		if (objTblReceivable.getTdiOperatorByRvOpIdCreator() != null)
			objThiReceivable.setRvOpIdCreator(objTblReceivable.getTdiOperatorByRvOpIdCreator().getOpId());
		
		objThiReceivable.setRvRemark(objTblReceivable.getRvRemark());
		objThiReceivable.setRvTotal(objTblReceivable.getRvTotal());
		objThiReceivable.setRvUnitnumber(objTblReceivable.getRvUnitnumber());
		objThiReceivable.setRvUnitprice(objTblReceivable.getRvUnitprice());
		
		objThiReceivable.setRvOpIdModifier(Long.parseLong(strOperId));
		objThiReceivable.setRvModifydate(DateFormatUtility.getSysdate());		
		
		// 
		objThiReceivable.setTchnChannel(objTblReceivable.getTchnChannel());
		objThiReceivable.setTcoCorporation(objTblReceivable.getTcoCorporation());
		objThiReceivable.setTdiBillingkind(objTblReceivable.getTdiBillingkind());
		objThiReceivable.setTdiCurrencykind(objTblReceivable.getTdiCurrencykind());
		objThiReceivable.setTdiFeekind(objTblReceivable.getTdiFeekind());
		objThiReceivable.setTdiFeestatus(objTblReceivable.getTdiFeestatus());
		//objThiReceivable.setTfiBillrecord(objTblReceivable.getBrId());
		objThiReceivable.setTopCorewaybill(objTblReceivable.getTopCorewaybill());
		
		return objThiReceivable;
	}
}
