package kyle.leis.es.businessrule.operationprompt.test;


import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.SingleColumnQuery;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.es.businessrule.operationprompt.bl.OperationPrompt;
import kyle.leis.es.businessrule.operationprompt.da.OperationpromptCondition;
import kyle.leis.es.businessrule.operationprompt.da.OperationpromptQuery;

public class Operationprompt {
	
	public static void main(String [] args)
	{
		try
		{
			testVerify();
//			testNull();
//			testExpress();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void testVerify() throws Exception
	{
		OperationPrompt optOperationPrompt = new OperationPrompt();
//		String strCwcode = "4762";//3905:Ò»¼þ 4762:8¼þ
//		String[] astr = optOperationPrompt.verifyOpt(strCwcode,"002");
		
		String strServerewbcode = "3446029300";//00694313760010100425   8153
		HousewaybillColumns objHWColumns = HousewaybillDemand.load(strServerewbcode, "S");
		List list = CorewaybillpiecesDemand.load(objHWColumns.getHwcwcode());
		
		String[] astr = optOperationPrompt.verify(objHWColumns, list, "002");
		
		/*
		String strValiddate = DateFormatUtility.getStandardSysdate().substring(0, 10);
		strValiddate = strValiddate + " 00:00:00";
		System.out.println(strValiddate);
		*/
		
		//String[] astr = optOperationPrompt.verifySignOutopt(strServerewbcode,"003");
		//System.out.println(astr.length);
	}
	
	public static void testNull() throws Exception
	{
		OperationpromptCondition objOptCondition = new OperationpromptCondition();
//		objOptCondition.setBrid(null);
//		objOptCondition.setCtcode("AWPX");
		objOptCondition.setCtcode(null);
		OperationpromptQuery objOptQuery = new OperationpromptQuery();
		objOptQuery.setCondition(objOptCondition);
		List objList = objOptQuery.getResults();
		System.out.println(objList.size());
	}
	
	public static void testExpress() throws Exception
	{
		String strResult = SingleColumnQuery.getColumnData("select 1 from dual where 2>3");
		System.out.println(strResult);
	}
}
