package kyle.leis.eo.finance.dunning.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.dunning.bl.Dunning;
import kyle.leis.eo.finance.dunning.da.FinancestatisticsCondition;
import kyle.leis.eo.finance.dunning.da.FinancestatisticssCondition;
import kyle.leis.eo.finance.dunning.da.FinancialcustomerCondition;
import kyle.leis.eo.finance.dunning.da.FinancialdetailsCondition;
import kyle.leis.eo.finance.dunning.dax.DunningDemand;
import kyle.leis.eo.finance.dunning.dax.FinanceReportResults;

public class DunningService extends AService {
	
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		FinancestatisticsCondition objFSCondition = (FinancestatisticsCondition)objPD.getParameter(0, 
				FinancestatisticsCondition.class);
		if (StringUtility.isNull(objFSCondition.getFscarryoverenterprise()))
			objFSCondition.setFscarryoverenterprise("ALL");
		List objList = DunningDemand.query(objFSCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	//获得系统时间
	public String getSysdate(Decoder objPD) throws Exception {
		String sysDate = DateFormatUtility.getSysdateString("yyyy-MM-dd HH:mm:ss");
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(sysDate);
		return objEncode.toString();
	}
	
	public String getCreditbalance(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strCocode = (String)objPD.getParameter(0, String.class);
		
		Dunning objDunning = new Dunning();
		String strCreditbalance = objDunning.getCreditbalance(strCocode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strCreditbalance);
		return objEncode.toString();		
	}
	
	public String querySupplier(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		FinancestatisticssCondition objFSSCondition = (FinancestatisticssCondition)objPD.getParameter(0, 
				FinancestatisticssCondition.class);
		List objList = DunningDemand.querySupplier(objFSSCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}	
	
	
	/**
	 * 查询往来账
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryFinanceReport(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 5, this);
		
		String strCocode = (String)objPD.getParameter(0, String.class);
		String strStartDate = (String)objPD.getParameter(1, String.class); 
		String strEndDate = (String)objPD.getParameter(2, String.class);
		String strCarryoversign = (String)objPD.getParameter(3, String.class);
		String strCkcode = (String)objPD.getParameter(4, String.class);
		
		List<FinanceReportResults> listFinanceReportResults = DunningDemand.queryFinanceReport(strCocode, 
				strStartDate, 
				strEndDate,
				strCarryoversign,
				strCkcode);
		return FinanceReportResults.toString(listFinanceReportResults);
	}
	/**
	 * 财务明细
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryFinancialDetails(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		FinancialdetailsCondition objFinancialdetailsCondition=(FinancialdetailsCondition) objPD.getParameter(0, FinancialdetailsCondition.class);
		
		List objList = DunningDemand.queryFinancialDetails(objFinancialdetailsCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	/**
	 * 客户查询
	 */
	public String queryFinancialcustomer(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		FinancialcustomerCondition objFinancialcustomerCondition=(FinancialcustomerCondition) objPD.getParameter(0, FinancialcustomerCondition.class);
		
		List objList = DunningDemand.queryFinancialCustomer(objFinancialcustomerCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String modifySignout(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		String cocode=(String) objPD.getParameter(0, String.class);
		String isAllow=(String) objPD.getParameter(1, String.class);
		
		DunningDemand objDemand=new DunningDemand();
		objDemand.ModifySingout(cocode, isAllow);
		
		return "";
	}
}
