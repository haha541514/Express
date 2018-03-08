package kyle.leis.ds.report.finance.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.ds.report.finance.da.CustomerselfhawbCondition;
import kyle.leis.ds.report.finance.da.FeegroupbycoCondition;
import kyle.leis.ds.report.finance.da.FeegroupbycwCondition;
import kyle.leis.ds.report.finance.da.FeegroupbyonylcoCondition;
import kyle.leis.ds.report.finance.da.FeegroupbyonylcwCondition;
import kyle.leis.ds.report.finance.da.FeegroupbypyCondition;
import kyle.leis.ds.report.finance.da.FeegroupbyrvCondition;
import kyle.leis.ds.report.finance.dax.FinancereportDemand;

public class FinancereportService extends AService {
	
	public String queryFeegroupbyCo(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		FeegroupbycoCondition objFGCCondition = (FeegroupbycoCondition)objPD.getParameter(0, 
				FeegroupbycoCondition.class);
		List objList = FinancereportDemand.queryFeegroupbyCo(objFGCCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String queryFeegroupOnlybyCo(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		FeegroupbyonylcoCondition objFGBOCCondition = (FeegroupbyonylcoCondition)objPD.getParameter(0, 
				FeegroupbyonylcoCondition.class);
		List objList = FinancereportDemand.queryFeegroupOnlybyCo(objFGBOCCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}	
	
	public String queryFeegroupbyCw(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		FeegroupbycwCondition objFGCCondition = (FeegroupbycwCondition)objPD.getParameter(0, 
				FeegroupbycwCondition.class);
		List objList = FinancereportDemand.queryFeegroupbyCw(objFGCCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String queryFeegroupOnlybyCw(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		FeegroupbyonylcwCondition objFGBOCCondition = (FeegroupbyonylcwCondition)objPD.getParameter(0, 
				FeegroupbyonylcwCondition.class);
		List objList = FinancereportDemand.queryFeegroupOnlybyCw(objFGBOCCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}	
	
	public String queryFeegroupRv(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		FeegroupbyrvCondition objFGRVCondition = (FeegroupbyrvCondition)objPD.getParameter(0, 
				FeegroupbyrvCondition.class);
		List objList = FinancereportDemand.queryFeegroupRv(objFGRVCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();				
	}
	
	public String queryCustomerselfhawb(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		CustomerselfhawbCondition objCSHCondition = (CustomerselfhawbCondition)objPD.getParameter(0, 
				CustomerselfhawbCondition.class);
		List objList = FinancereportDemand.queryCustomerselfhawb(objCSHCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();				
	}	
	
	public String queryFeegroupPy(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		FeegroupbypyCondition objFGPYCondition = (FeegroupbypyCondition)objPD.getParameter(0, 
				FeegroupbypyCondition.class);
		List objList = FinancereportDemand.queryFeegroupPy(objFGPYCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();				
	}		
}
