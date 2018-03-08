package kyle.leis.es.company.customer.sv;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.company.companys.sv.CompanysService;
import kyle.leis.es.company.customer.bl.Customer;
import kyle.leis.es.company.customer.da.CusoperationColumns;
import kyle.leis.es.company.customer.da.CustomerColumns;
import kyle.leis.es.company.customer.da.CustomerCondition;
import kyle.leis.es.company.customer.da.CustomerforbillCondition;
import kyle.leis.es.company.customer.dax.CustomerDemand;;

public class CustomerService extends CompanysService {
	/**
	 * 查询
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		CustomerCondition objCustomerCondition = (CustomerCondition)objPD.getParameter(0, 
				CustomerCondition.class);
		List objList = CustomerDemand.query(objCustomerCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}
	
	/**
	 * 统计
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryForBill(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		CustomerforbillCondition objCFBCondition = (CustomerforbillCondition)objPD.getParameter(0, 
				CustomerforbillCondition.class);
		List objList = CustomerDemand.queryForBill(objCFBCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}
	
	/**
	 * 修改额度
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String modifyCreditlimit(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);

		String strCocode = (String)objPD.getParameter(0, String.class);
		String strCreditLimit = (String)objPD.getParameter(1, String.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		String strHoldHWRate = (String)objPD.getParameter(3, String.class);
		
		Customer objCustomer = new Customer();
		objCustomer.modifyCreditlimit(strCocode, strCreditLimit, 
				strHoldHWRate, strOperId);
		
		return "";
	}
	
	/**
	 * 修改额度与临时额度
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String modifyTempAndCreditlimit(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 7, this);

		String strCocode = (String)objPD.getParameter(0, String.class);
		String strCreditLimit = (String)objPD.getParameter(1, String.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		String strTemporarycreditlimit = (String)objPD.getParameter(3, String.class);
		String strTclstartdate = (String)objPD.getParameter(4, String.class);
		String strTclenddate = (String)objPD.getParameter(5, String.class);
		String strHoldHWRate = (String)objPD.getParameter(6, String.class);
		
		Customer objCustomer = new Customer();
		objCustomer.modifyTempAndCreditlimit(strCocode, 
				strCreditLimit, 
				strOperId, 
				strTemporarycreditlimit, 
				strTclstartdate, 
				strTclenddate,
				strHoldHWRate);
		
		return "";
	}
	
	/**
	 * 保存
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);

		CustomerColumns objCustomerColumns = (CustomerColumns)objPD.getParameter(0, 
				CustomerColumns.class);
		List customerSORestrict = (List)objPD.getParameterList(1, CusoperationColumns.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		
		Customer objCustomer = new Customer();
		CustomerColumns objSavedCColumns = objCustomer.save(objCustomerColumns, customerSORestrict, strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objSavedCColumns);
		return objEncode.toString();	
	}
	
	public String batchModifyOperator(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);

		String[] astrCocode = objPD.getParameterArray(0, String.class);
		String strNewOperId = (String)objPD.getParameter(1, String.class);
		String strMkcode = (String)objPD.getParameter(2, String.class);
		String strActionOperId = (String)objPD.getParameter(3, String.class);
		
		Customer objCustomer = new Customer();
		objCustomer.batchModifyOperator(astrCocode, strNewOperId, 
				strMkcode, strActionOperId);
		
		return "";
	}
	
	public String queryCusSORestrict(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String cmCocode = (String) objPD.getParameter(0, String.class);
		List objList = CustomerDemand.queryCusSORestrict(cmCocode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();	
	}
}
