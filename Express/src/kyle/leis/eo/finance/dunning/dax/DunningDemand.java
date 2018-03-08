package kyle.leis.eo.finance.dunning.dax;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.billrecord.dax.BillRecordDemand;
import kyle.leis.eo.finance.cashrecord.dax.CashRecordDemand;
import kyle.leis.eo.finance.dunning.bl.Dunning;
import kyle.leis.eo.finance.dunning.da.FinancestatisticsColumns;
import kyle.leis.eo.finance.dunning.da.FinancestatisticsCondition;
import kyle.leis.eo.finance.dunning.da.FinancestatisticsQuery;
import kyle.leis.eo.finance.dunning.da.FinancestatisticssCondition;
import kyle.leis.eo.finance.dunning.da.FinancestatisticssQuery;
import kyle.leis.eo.finance.dunning.da.FinancialcustomerColumns;
import kyle.leis.eo.finance.dunning.da.FinancialcustomerCondition;
import kyle.leis.eo.finance.dunning.da.FinancialcustomerQuery;
import kyle.leis.eo.finance.dunning.da.FinancialdetailsColumns;
import kyle.leis.eo.finance.dunning.da.FinancialdetailsCondition;
import kyle.leis.eo.finance.dunning.da.FinancialdetailsQuery;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;

public class DunningDemand {
	public static List query(FinancestatisticsCondition objFSCondition) 
	throws Exception {
		if(!StringUtility.isNull(objFSCondition.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objFSCondition.getEestructurecode());
			objFSCondition.setEestructurecode(strEestructurecode);
		}
		FinancestatisticsQuery objFSQuery = new FinancestatisticsQuery();
		objFSQuery.setCondition(objFSCondition);
		return objFSQuery.getResults();
	}
	
	public static List querySupplier(FinancestatisticssCondition objFSSCondition) 
	throws Exception {
		if(!StringUtility.isNull(objFSSCondition.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objFSSCondition.getEestructurecode());
			objFSSCondition.setEestructurecode(strEestructurecode);
		}
		FinancestatisticssQuery objFSSQuery = new FinancestatisticssQuery();
		objFSSQuery.setCondition(objFSSCondition);
		return objFSSQuery.getResults();
	}
	
	public static FinancestatisticsColumns load(String strCocode) 
	throws Exception {
		FinancestatisticsQuery objFSQuery = new FinancestatisticsQuery();
		objFSQuery.setCocode(strCocode);
		objFSQuery.setFscarryoverenterprise("ALL");
		List objList = objFSQuery.getResults();
		if (objList == null || objList.size() < 1) return null;
		return (FinancestatisticsColumns)objList.get(0);
	}
	
	public static List<FinanceReportResults> queryFinanceReport(String strCocode,
			String strStartDate,
			String strEndDate,
			String strCarryoversign,
			String strCkcode) throws Exception {
		List listBillRecords = BillRecordDemand.queryForDun(strCocode, 
				strStartDate, strEndDate, strCarryoversign, strCkcode);
		List listCashRecords = CashRecordDemand.queryForDun(strCocode, 
				strStartDate, strEndDate, strCarryoversign, strCkcode);	
		DunningFinanceReport objDunningFReport = new DunningFinanceReport();
		return objDunningFReport.transferFinanceReport(listBillRecords, 
				listCashRecords, 
				strStartDate, 
				strEndDate);
	}
	public static List<FinancialdetailsColumns> queryFinancialDetails(FinancialdetailsCondition objFinancialdetailsCondition) throws Exception{
		FinancialdetailsQuery objFinancialdetailsQuery=new FinancialdetailsQuery();
		objFinancialdetailsQuery.setCondition(objFinancialdetailsCondition);
		return objFinancialdetailsQuery.getResults();
		
	}
	
	public void ModifySingout(String strCocode,String isAllow) throws Exception{
		Dunning objDunning=new Dunning();
		objDunning.modifySignout(strCocode, isAllow);
	}
	
	public static List<FinancialcustomerColumns> queryFinancialCustomer(FinancialcustomerCondition objFinancialcustomerCondition ) throws Exception{
		FinancialcustomerQuery objFinancialcustomerQuery=new FinancialcustomerQuery();
		objFinancialcustomerQuery.setCondition(objFinancialcustomerCondition);
		return objFinancialcustomerQuery.getResults();
		
	}
}
