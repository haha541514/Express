package kyle.leis.fs.businesslog.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.businesslog.bl.Businesslog;
import kyle.leis.fs.businesslog.da.BusinesslogColumns;
import kyle.leis.fs.businesslog.da.BusinesslogCondition;
import kyle.leis.fs.businesslog.dax.BusinesslogDemand;

public class BusinesslogService extends AService {
	/*
	 * �ṩ�Ĳ�ѯ����
	 */
	public String queryCondition(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);	
		BusinesslogCondition objBusinessCondition = (BusinesslogCondition)objPD.getParameter(0, 
				BusinesslogCondition.class);
		List listResults = BusinesslogDemand.queryCondition(objBusinessCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		//System.out.println("���ܺ������           "+objEncode.toString());
		return objEncode.toString();
	}
	
	/*
	 * �ṩ�ı��淽��(���ص���ֵ)
	 */
	/*public String save(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD, 1, this);
		
		BusinesslogColumns objBusinesslogCol= (BusinesslogColumns)objPD.getParameter(0, BusinesslogColumns.class);
		Businesslog objBusinesslog = new Businesslog();
		BusinesslogQueryReturn objBusinesslogQueryReturn = objBusinesslog.save(objBusinesslogCol);
		
		return objBusinesslogQueryReturn.toString();
	}*/
	
	/*
	 * �ṩ�ı��淽��(���ؼ�)
	 */
	public String save(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD, 1, this);
		
		BusinesslogColumns objBusinesslogCol= (BusinesslogColumns)objPD.getParameter(0, BusinesslogColumns.class);
		Businesslog objBusinesslog = new Businesslog();
		List listBusinesslogColumns = objBusinesslog.save(objBusinesslogCol);
		Encoder code = new Encoder();
		code.addParameter(listBusinesslogColumns);
		return code.toString();
	}
	
	public String saveSimple(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD, 3, this);
		
		String strBusinessobjectcode = (String)objPD.getParameter(0,String.class);
		String strOperId = (String)objPD.getParameter(1, String.class); 
		String strRemark = (String)objPD.getParameter(2, String.class);
		
		Businesslog objBusinesslog = new Businesslog();
		objBusinesslog.addBusinessLog(strBusinessobjectcode, strOperId, strRemark);
		
		return "";
	}	
}
