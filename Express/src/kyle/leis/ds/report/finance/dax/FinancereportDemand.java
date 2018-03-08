package kyle.leis.ds.report.finance.dax;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.ds.report.finance.da.CustomerselfhawbCondition;
import kyle.leis.ds.report.finance.da.CustomerselfhawbQuery;
import kyle.leis.ds.report.finance.da.FeegroupbycoColumns;
import kyle.leis.ds.report.finance.da.FeegroupbycoCondition;
import kyle.leis.ds.report.finance.da.FeegroupbycoQuery;
import kyle.leis.ds.report.finance.da.FeegroupbycwCondition;
import kyle.leis.ds.report.finance.da.FeegroupbycwQuery;
import kyle.leis.ds.report.finance.da.FeegroupbyonylcoColumns;
import kyle.leis.ds.report.finance.da.FeegroupbyonylcoCondition;
import kyle.leis.ds.report.finance.da.FeegroupbyonylcoQuery;
import kyle.leis.ds.report.finance.da.FeegroupbyonylcwCondition;
import kyle.leis.ds.report.finance.da.FeegroupbyonylcwQuery;
import kyle.leis.ds.report.finance.da.FeegroupbypyCondition;
import kyle.leis.ds.report.finance.da.FeegroupbypyQuery;
import kyle.leis.ds.report.finance.da.FeegroupbyrvCondition;
import kyle.leis.ds.report.finance.da.FeegroupbyrvQuery;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;

public class FinancereportDemand {
	
	public static List queryFeegroupbyCo(FeegroupbycoCondition objFGCCondition) 
	throws Exception {
		FeegroupbycoQuery objFGBCQuery;
		
		if (!StringUtility.isNull(objFGCCondition.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objFGCCondition.getEestructurecode());
			objFGCCondition.setEestructurecode(strEestructurecode);
		}			
		if (!StringUtility.isNull(objFGCCondition.getStartfeecreatedate())) {
			String strCreateStartFeeDate = objFGCCondition.getStartfeecreatedate();
			String strCreateEndFeeDate = objFGCCondition.getStartfeeenddate();
			String strStartAdDate = objFGCCondition.getStartadddate();
			// 查询运单开始日期跟截止日期内运单的费用总和
			objFGBCQuery = new FeegroupbycoQueryEX(objFGCCondition.getStartfeecreatedate(),
					objFGCCondition.getStartfeeenddate(),
					objFGCCondition.getStartadddate(),
					objFGCCondition.getEndadddate());
			objFGCCondition.setStartfeecreatedate("");
			objFGCCondition.setStartfeeenddate("");
			objFGBCQuery.setCondition(objFGCCondition);
			List listResults = objFGBCQuery.getResults();			
			// 查询运单开始日期以前的运单以及这些运单费用创建日期范围内的费用总和
			BeforeFeegroupbycoQueryEX objBFGBCQuery = new BeforeFeegroupbycoQueryEX(strCreateStartFeeDate,
					strCreateEndFeeDate);	
			objFGCCondition.setStartadddate("2001-01-01 00:00:00");
			objFGCCondition.setEndadddate(strStartAdDate);
			objFGCCondition.setStartfeecreatedate("");
			objFGCCondition.setStartfeeenddate("");
			objBFGBCQuery.setCondition(objFGCCondition);
			List listBeforeHAWBResults = objBFGBCQuery.getResults();	
			// 合并数据
			listResults = mergeFeegroupbyCo(listResults, listBeforeHAWBResults);
			
			return listResults;
		} else {
			objFGBCQuery = new FeegroupbycoQuery();
			objFGBCQuery.setCondition(objFGCCondition);
			return objFGBCQuery.getResults();
		}
	}
	
	private static List mergeFeegroupbyCo(List listFeegroupbyCoResults,
			List listBeforeHAWBResults) {
		if (listFeegroupbyCoResults == null || listFeegroupbyCoResults.size() < 1)
			return listBeforeHAWBResults;
		if (listBeforeHAWBResults == null || listBeforeHAWBResults.size() < 1)
			return listFeegroupbyCoResults;		
		List<FeegroupbycoColumns> listResults = new ArrayList<FeegroupbycoColumns>();
		// 增加A集合存在的
		for (int i = 0; i < listFeegroupbyCoResults.size(); i++) {
			FeegroupbycoColumns objFeegroupbycoColumns = (FeegroupbycoColumns)listFeegroupbyCoResults.get(i);
			mergeFeegroupbyCoAmount(objFeegroupbycoColumns, listBeforeHAWBResults);
			listResults.add(objFeegroupbycoColumns);
		}
		// 增加A集合不存在而B集合存在的
		for (int i = 0; i < listBeforeHAWBResults.size(); i++) {
			FeegroupbycoColumns objFeegroupbycoColumns = (FeegroupbycoColumns)listBeforeHAWBResults.get(i);
			if (!isExistsFeegroupbyco(objFeegroupbycoColumns, listFeegroupbyCoResults))
				listResults.add(objFeegroupbycoColumns);
		}		
		return listResults;
	}
	
	private static boolean isExistsFeegroupbyco(FeegroupbycoColumns objBeforeHawbFeegroupbycoColumns,
			List listFeegroupbyCoResults) {
		for (int i = 0; i < listFeegroupbyCoResults.size(); i++) {
			FeegroupbycoColumns objFeegroupbycoColumns = (FeegroupbycoColumns)listFeegroupbyCoResults.get(i);
			if (objBeforeHawbFeegroupbycoColumns.getCoco_labelcode().equals(objFeegroupbycoColumns.getCoco_labelcode()) &&
					objBeforeHawbFeegroupbycoColumns.getPkpk_sname().equals(objFeegroupbycoColumns.getPkpk_sname()) &&
					objBeforeHawbFeegroupbycoColumns.getOpop_name().equals(objFeegroupbycoColumns.getOpop_name())) {
				return true;
			}
		}
		return false;
	}
	
	private static void mergeFeegroupbyCoAmount(FeegroupbycoColumns objFeegroupbycoColumns,
			List listBeforeHAWBResults) {	
		for (int i = 0; i < listBeforeHAWBResults.size(); i++) {
			FeegroupbycoColumns objBeforeHAWFeegroupbycoColumns = (FeegroupbycoColumns)listBeforeHAWBResults.get(i);
			if (objFeegroupbycoColumns.getCoco_labelcode().equals(objBeforeHAWFeegroupbycoColumns.getCoco_labelcode()) &&
					objFeegroupbycoColumns.getPkpk_sname().equals(objBeforeHAWFeegroupbycoColumns.getPkpk_sname()) &&
					objFeegroupbycoColumns.getOpop_name().equals(objBeforeHAWFeegroupbycoColumns.getOpop_name())) {
				String strTotal = addFeeAmount(objBeforeHAWFeegroupbycoColumns.getHkdrvtotal(),
						objFeegroupbycoColumns.getHkdrvtotal());
				objFeegroupbycoColumns.setHkdrvtotal(strTotal);
				
				strTotal = addFeeAmount(objBeforeHAWFeegroupbycoColumns.getPytotal(),
						objFeegroupbycoColumns.getPytotal());
				objFeegroupbycoColumns.setPytotal(strTotal);
				
				strTotal = addFeeAmount(objBeforeHAWFeegroupbycoColumns.getRmbrvtotal(),
						objFeegroupbycoColumns.getRmbrvtotal());
				objFeegroupbycoColumns.setRmbrvtotal(strTotal);
				
				strTotal = addFeeAmount(objBeforeHAWFeegroupbycoColumns.getRvtotal(),
						objFeegroupbycoColumns.getRvtotal());
				objFeegroupbycoColumns.setRvtotal(strTotal);	
				
				strTotal = addFeeAmount(objBeforeHAWFeegroupbycoColumns.getBillcount(),
						objFeegroupbycoColumns.getBillcount());
				objFeegroupbycoColumns.setBillcount(strTotal);	
				
				strTotal = addFeeAmount(objBeforeHAWFeegroupbycoColumns.getSumchargeweight(),
						objFeegroupbycoColumns.getSumchargeweight());
				objFeegroupbycoColumns.setSumchargeweight(strTotal);					
				
				strTotal = addFeeAmount(objBeforeHAWFeegroupbycoColumns.getSumpieces(),
						objFeegroupbycoColumns.getSumpieces());
				objFeegroupbycoColumns.setSumpieces(strTotal);					
				
				strTotal = addFeeAmount(objBeforeHAWFeegroupbycoColumns.getSumserverchargeweight(),
						objFeegroupbycoColumns.getSumserverchargeweight());
				objFeegroupbycoColumns.setSumserverchargeweight(strTotal);					
				
				break;
			}
		}
	}	
	
	private static String addFeeAmount(String strTotal,
			String strAddedTotal) {
		if (StringUtility.isNull(strTotal))
			strTotal = "0";
		if (StringUtility.isNull(strAddedTotal))
			strAddedTotal = "0";
		BigDecimal obj = new BigDecimal(strTotal).add(new BigDecimal(strAddedTotal));
		return obj.toString();
	}
	
	public static List queryFeegroupOnlybyCo(FeegroupbyonylcoCondition objFGBOCondition) 
	throws Exception {
		
		if (!StringUtility.isNull(objFGBOCondition.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objFGBOCondition.getEestructurecode());
			objFGBOCondition.setEestructurecode(strEestructurecode);
		}		
		
		FeegroupbyonylcoQuery objFBOCQuery = new FeegroupbyonylcoQuery();
		if (!StringUtility.isNull(objFGBOCondition.getStartfeecreatedate())) {			
			String strCreateStartFeeDate = objFGBOCondition.getStartfeecreatedate();
			String strCreateEndFeeDate = objFGBOCondition.getStartfeeenddate();
			String strStartAdDate = objFGBOCondition.getStartadddate();					
			
			objFBOCQuery = new FeegroupbyonylcoQueryEX(objFGBOCondition.getStartfeecreatedate(),
					objFGBOCondition.getStartfeeenddate(),
					objFGBOCondition.getStartadddate(),
					objFGBOCondition.getEndadddate());
			
			objFGBOCondition.setStartfeecreatedate("");
			objFGBOCondition.setStartfeeenddate("");
			objFBOCQuery.setCondition(objFGBOCondition);
			List listResults = objFBOCQuery.getResults();			
			// 查询运单开始日期以前的运单以及这些运单费用创建日期范围内的费用总和
			BeforeFeegroupbyonylcoQueryEX objBFGBCQuery = new BeforeFeegroupbyonylcoQueryEX(strCreateStartFeeDate,
					strCreateEndFeeDate);	
			objFGBOCondition.setStartadddate("2001-01-01 00:00:00");
			objFGBOCondition.setEndadddate(strStartAdDate);
			objFGBOCondition.setStartfeecreatedate("");
			objFGBOCondition.setStartfeeenddate("");
			
			objBFGBCQuery.setCondition(objFGBOCondition);
			List listBeforeHAWBResults = objBFGBCQuery.getResults();	
			// 合并数据
			listResults = mergeFeegroupOnlybyCo(listResults, listBeforeHAWBResults);
			
			return listResults;				
			
		} else {
			objFBOCQuery.setCondition(objFGBOCondition);
			return objFBOCQuery.getResults();			
		}
	}	
	
	private static List mergeFeegroupOnlybyCo(List listFeegroupOnlybyCoResults,
			List listBeforeHAWBResults) {
		if (listFeegroupOnlybyCoResults == null || listFeegroupOnlybyCoResults.size() < 1)
			return listBeforeHAWBResults;
		if (listBeforeHAWBResults == null || listBeforeHAWBResults.size() < 1)
			return listFeegroupOnlybyCoResults;	
		List<FeegroupbyonylcoColumns> listResults = new ArrayList<FeegroupbyonylcoColumns>();
		for (int i = 0; i < listFeegroupOnlybyCoResults.size(); i++) {
			FeegroupbyonylcoColumns objFeegroupbyonylcoColumns = (FeegroupbyonylcoColumns)listFeegroupOnlybyCoResults.get(i);
			mergeFeegroupOnlybyCoAmount(objFeegroupbyonylcoColumns, listBeforeHAWBResults);
			listResults.add(objFeegroupbyonylcoColumns);
		}
		// 增加A集合不存在而B集合存在的
		for (int i = 0; i < listBeforeHAWBResults.size(); i++) {
			FeegroupbyonylcoColumns objBeforeHAWFeegroupbycoColumns = (FeegroupbyonylcoColumns)listBeforeHAWBResults.get(i);
			if (!isExistsFeegroupOnlybyCo(objBeforeHAWFeegroupbycoColumns, listFeegroupOnlybyCoResults))
				listResults.add(objBeforeHAWFeegroupbycoColumns);		
		}
		return listResults;
	}	
	
	private static boolean isExistsFeegroupOnlybyCo(FeegroupbyonylcoColumns objBeforeHAWFeegroupbycoColumns,
			List listFeegroupOnlybyCoResults) {
		for (int i = 0; i < listFeegroupOnlybyCoResults.size(); i++) {
			FeegroupbyonylcoColumns objFeegroupbycoColumns = (FeegroupbyonylcoColumns)listFeegroupOnlybyCoResults.get(i);
			if (objFeegroupbycoColumns.getCoco_labelcode().equals(objBeforeHAWFeegroupbycoColumns.getCoco_labelcode()) &&
					objFeegroupbycoColumns.getOpop_name().equals(objBeforeHAWFeegroupbycoColumns.getOpop_name())) {
				return true;
			}
		}
		return false;
	}
		
	private static void mergeFeegroupOnlybyCoAmount(FeegroupbyonylcoColumns objFeegroupbyonylcoColumns,
			List listBeforeHAWBResults) {	
		for (int i = 0; i < listBeforeHAWBResults.size(); i++) {
			FeegroupbyonylcoColumns objBeforeHAWFeegroupbycoColumns = (FeegroupbyonylcoColumns)listBeforeHAWBResults.get(i);
			if (objFeegroupbyonylcoColumns.getCoco_labelcode().equals(objBeforeHAWFeegroupbycoColumns.getCoco_labelcode()) &&
					objFeegroupbyonylcoColumns.getOpop_name().equals(objBeforeHAWFeegroupbycoColumns.getOpop_name())) {
				String strTotal = addFeeAmount(objBeforeHAWFeegroupbycoColumns.getHkdrvtotal(),
						objFeegroupbyonylcoColumns.getHkdrvtotal());
				objFeegroupbyonylcoColumns.setHkdrvtotal(strTotal);
				
				strTotal = addFeeAmount(objBeforeHAWFeegroupbycoColumns.getPytotal(),
						objFeegroupbyonylcoColumns.getPytotal());
				objFeegroupbyonylcoColumns.setPytotal(strTotal);
				
				strTotal = addFeeAmount(objBeforeHAWFeegroupbycoColumns.getRmbrvtotal(),
						objFeegroupbyonylcoColumns.getRmbrvtotal());
				objFeegroupbyonylcoColumns.setRmbrvtotal(strTotal);
				
				strTotal = addFeeAmount(objBeforeHAWFeegroupbycoColumns.getRvtotal(),
						objFeegroupbyonylcoColumns.getRvtotal());
				objFeegroupbyonylcoColumns.setRvtotal(strTotal);	
				
				strTotal = addFeeAmount(objBeforeHAWFeegroupbycoColumns.getBillcount(),
						objFeegroupbyonylcoColumns.getBillcount());
				objFeegroupbyonylcoColumns.setBillcount(strTotal);				
				
				strTotal = addFeeAmount(objBeforeHAWFeegroupbycoColumns.getSumchargeweight(),
						objFeegroupbyonylcoColumns.getSumchargeweight());
				objFeegroupbyonylcoColumns.setSumchargeweight(strTotal);						
				
				strTotal = addFeeAmount(objBeforeHAWFeegroupbycoColumns.getSumpieces(),
						objFeegroupbyonylcoColumns.getSumpieces());
				objFeegroupbyonylcoColumns.setSumpieces(strTotal);						
				
				strTotal = addFeeAmount(objBeforeHAWFeegroupbycoColumns.getSumserverchargeweight(),
						objFeegroupbyonylcoColumns.getSumserverchargeweight());
				objFeegroupbyonylcoColumns.setSumserverchargeweight(strTotal);						
				
				break;
			}
		}
	}	
	
	
	public static List queryFeegroupbyCw(FeegroupbycwCondition objFGCCondition) 
	throws Exception {
		if (!StringUtility.isNull(objFGCCondition.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objFGCCondition.getEestructurecode());
			objFGCCondition.setEestructurecode(strEestructurecode);
		}			
		
		FeegroupbycwQuery objFGBCQuery = new FeegroupbycwQuery();
		if (!StringUtility.isNull(objFGCCondition.getStartfeecreatedate()))
			objFGBCQuery = new FeegroupbycwQueryEX(objFGCCondition.getStartfeecreatedate(),
					objFGCCondition.getStartfeeenddate());	
		
		objFGBCQuery.setCondition(objFGCCondition);
		return objFGBCQuery.getResults();
	}
	
	public static List queryFeegroupOnlybyCw(FeegroupbyonylcwCondition objFGBOCondition) 
	throws Exception {
		if (!StringUtility.isNull(objFGBOCondition.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objFGBOCondition.getEestructurecode());
			objFGBOCondition.setEestructurecode(strEestructurecode);
		}			
		
		FeegroupbyonylcwQuery objFBOCQuery = new FeegroupbyonylcwQuery();
		if (!StringUtility.isNull(objFGBOCondition.getStartfeecreatedate()))
			objFBOCQuery = new FeegroupbyonylcwQueryEX(objFGBOCondition.getStartfeecreatedate(),
					objFGBOCondition.getStartfeeenddate());		
		objFBOCQuery.setCondition(objFGBOCondition);
		return objFBOCQuery.getResults();
	}	
	
	public static List queryCustomerselfhawb(CustomerselfhawbCondition objCSHCondition) 
	throws Exception {
		
		if (!StringUtility.isNull(objCSHCondition.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objCSHCondition.getEestructurecode());
			objCSHCondition.setEestructurecode(strEestructurecode);
		}			
		
		CustomerselfhawbQuery objCSHQuery = new CustomerselfhawbQuery();
		objCSHQuery.setCondition(objCSHCondition);
		return objCSHQuery.getResults();
	}		
	
	
	public static List queryFeegroupRv(FeegroupbyrvCondition objFGRVCondition) 
	throws Exception {
		if (!StringUtility.isNull(objFGRVCondition.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objFGRVCondition.getEestructurecode());
			objFGRVCondition.setEestructurecode(strEestructurecode);
		}			
		
		FeegroupbyrvQuery objFGRVQuery = new FeegroupbyrvQuery();
		objFGRVQuery.setCondition(objFGRVCondition);
		return objFGRVQuery.getResults();
	}	
	
	public static List queryFeegroupPy(FeegroupbypyCondition objFGPYCondition) 
	throws Exception {
		if (!StringUtility.isNull(objFGPYCondition.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objFGPYCondition.getEestructurecode());
			objFGPYCondition.setEestructurecode(strEestructurecode);
		}		
		FeegroupbypyQuery objFGPYQuery = new FeegroupbypyQuery();
		objFGPYQuery.setCondition(objFGPYCondition);
		return objFGPYQuery.getResults();
	}		
}
