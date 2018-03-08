package kyle.leis.es.systemcertification.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.jlang.CollectionUtility;
import kyle.leis.es.systemcertification.bl.Systemcertification;
import kyle.leis.es.systemcertification.da.SystemcertificationColumns;
import kyle.leis.es.systemcertification.da.SystemcertificationCondition;
import kyle.leis.es.systemcertification.dax.SystemCertificationDemand;

public class SystemcertificationService extends AService {
	
	/*
	 * 审核通过
	 */
	public String confrimSystemcertification(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		String strOperId =(String) objPD.getParameter(0, String.class);
		String strScId = (String) objPD.getParameter(1, String.class);
		
		Systemcertification objSystemcertification = new Systemcertification();
		SystemcertificationColumns objRetrunCol =  objSystemcertification.modifySystemcertificationStatus(strOperId,strScId,"ON");
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objRetrunCol);
		return objEncode.toString();
	}
	
	/*
	 * 认证失效
	 */
	public String eliminateSystemcertification(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		String strOperId = (String) objPD.getParameter(0, String.class);
		String strScId = (String) objPD.getParameter(1, String.class);
		Systemcertification objSystemcertification = new Systemcertification();
		SystemcertificationColumns objReturnCol =  objSystemcertification.modifySystemcertificationStatus(strOperId,strScId, "OFF");
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objReturnCol);
		return objEncode.toString();
	}
	
	/*
	 * 认证延长
	 */
	public String extendSystemcertification(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,3,this);
		
		String strOperId = (String) objPD.getParameter(0,String.class);
		String strScId = (String) objPD.getParameter(1, String.class);
		String strExtendDate = (String) objPD.getParameter(2, String.class);
		
		Systemcertification objSystemcertification = new Systemcertification();
		SystemcertificationColumns objReturnCol =  objSystemcertification.extendSystemcertification(strOperId,strScId, strExtendDate);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objReturnCol);
		return objEncode.toString();
	}
	
	/*
	 * 添加认证备注
	 */
	public String addRemark(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,3,this);
		
		String strOperId = (String) objPD.getParameter(0, String.class);
		String strScId = (String) objPD.getParameter(1, String.class);
		String strRemark = (String) objPD.getParameter(2, String.class);
		
		Systemcertification objSystemcertification = new Systemcertification();
		objSystemcertification.addRemark(strOperId, strScId, strRemark);
		return "";
	}
	
	public String modifyOwnEnterprise(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,3,this);
		
		String strOperId = (String) objPD.getParameter(0, String.class);
		String strScId = (String) objPD.getParameter(1, String.class);
		String strOwnEnterpriseSign = (String) objPD.getParameter(2, String.class);
		
		Systemcertification objSystemcertification = new Systemcertification();
		objSystemcertification.modifyOwnEnterprise(strOperId, strScId, strOwnEnterpriseSign);
		return "";
	}	
	
	
	/*
	 * 查询 
	 */
	public String query(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		SystemcertificationCondition objSystemcertificationCon = (SystemcertificationCondition) objPD.getParameter(0, SystemcertificationCondition.class);
		List objList = SystemCertificationDemand.query(objSystemcertificationCon);
		if(CollectionUtility.isNull(objList)) return "";
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
}
