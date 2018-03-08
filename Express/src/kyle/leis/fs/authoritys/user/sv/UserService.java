package kyle.leis.fs.authoritys.user.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.prompt.PromptUtility;
import kyle.leis.fs.authoritys.user.bl.User;
import kyle.leis.fs.authoritys.user.da.UserColumns;
import kyle.leis.fs.authoritys.user.da.UserCondition;
import kyle.leis.fs.authoritys.user.dax.UserDemand;
import kyle.leis.fs.dictionary.operator.bl.Operator;

public class UserService extends AService {
	
	/*public String login(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		String strOperCode = (String)objPD.getParameter(0, String.class);
		String strOperPassword = (String)objPD.getParameter(1, String.class);
		String strIsk_code = (String)objPD.getParameter(2, String.class);
		//SystemcertificationColumns objSystemcertificationCol = (SystemcertificationColumns)objPD.getParameter(3, SystemcertificationColumns.class);
		
		Operator objOperator = new Operator();
		//OperatorLoginReturn objOLR = objOperator.login(strOperCode, strOperPassword, strIsk_code,objSystemcertificationCol);
		OperatorLoginReturn objOLR = objOperator.login(strOperCode, strOperPassword, strIsk_code);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objOLR.getLoginReturn());
		objEncode.addParameter(objOLR.getOperatorColumns());
		objEncode.addParameter(objOLR.getRoleMenus());
		return objEncode.toString();
	}*/
	
	public String modifyPassword(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		
		String strOperId = (String)objPD.getParameter(0, String.class);
		String strOldPassword = (String)objPD.getParameter(1, String.class);
		String strNewPassword = (String)objPD.getParameter(2, String.class);
		
		Operator objOperator = new Operator();
		PromptUtility objPUtility = objOperator.modifyPassword(strOperId, 
				strOldPassword, 
				strNewPassword);
		if (objPUtility == null)
			return "";
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objPUtility.toStringArray());
		return objEncode.toString();
	}
	
	public String queryByOperCode(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		String strOperCode = (String)objPD.getParameter(0, String.class);
		
		UserColumns objUserColumns = UserDemand.queryByOperCode(strOperCode);
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(objUserColumns);
		return objEncoder.toString();
	}
	public String query(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		UserCondition objUserCondition = (UserCondition)objPD.getParameter(0, UserCondition.class);
		List objList = UserDemand.query(objUserCondition);
		
		Encoder encode = new Encoder();
		encode.addParameter(objList);
		return encode.toString();
	}
	
	
	//modify
	public String save(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		String strOperId = (String)objPD.getParameter(0, String.class);
		UserColumns objUserColumns = (UserColumns)objPD.getParameter(1, UserColumns.class);
		
		User objUser = new User();
		UserColumns objUserReturn = objUser.save(strOperId, objUserColumns);
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objUserReturn);
		return objEncode.toString();
	}
	
	public String modifyStatus(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		String strOperId = (String) objPD.getParameter(0, String.class);
		String strOsCode = (String) objPD.getParameter(1, String.class);
		
		User objUser = new User();
		objUser.modifyStatus(strOperId, strOsCode);
		return "";
	}
	
	
	public String delete(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		String strOperId = (String)objPD.getParameter(0, String.class);
		User objUser = new User();
		objUser.delete(strOperId);
		return "";
	}
	
}
