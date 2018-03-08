package kyle.leis.fs.authoritys.rolegmenus.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.StringParser;
import kyle.leis.fs.authoritys.rolegmenus.dax.QueryByStructurecodeReturn;
import kyle.leis.fs.authoritys.rolegmenus.dax.RoleGmenusDemand;
import kyle.leis.fs.authoritys.rolegmenus.tp.SaveRoleGmenusTransaction;

public class RoleGmenus {

	public void save(String[] astrRole_code,String[] astrMenus_code,String strOperId) throws Exception
	{
		SaveRoleGmenusTransaction objSRGMTrans = new SaveRoleGmenusTransaction();
		objSRGMTrans.setParam(astrRole_code, astrMenus_code, strOperId);
		objSRGMTrans.execute();
		
		//
	}
	
	
	/*public QueryByStructurecodeReturn queryByStructurecode(String strStructurecodes,String strIsk_code) throws Exception
	{
		String [] astrStructurecode = strStructurecodes.split(",");
		List listAllExtends = new ArrayList();
		List listFather = new ArrayList();
		List listSelf = RoleGmenusDemand.queryByStructurecode(astrStructurecode[0],strIsk_code);
		QueryByStructurecodeReturn objQBSReturn = new QueryByStructurecodeReturn();
		if(strStructurecodes.length()>1)
		{
			for(int i=1;i<astrStructurecode.length;i++)
			{
				listFather = RoleGmenusDemand.queryByStructurecode(astrStructurecode[i], strIsk_code);
				for(int j=0;j<listFather.size();j++)
				{
					System.out.println("j:"+j);
					listAllExtends.add(listFather.get(j));
				}
			}
		}
		objQBSReturn.setM_listFatherRolegmenusColumns(listAllExtends);
		objQBSReturn.setM_listSelfRolegmenusColumns(listSelf);
		return objQBSReturn;
	}*/
	
	public QueryByStructurecodeReturn queryByStructurecode(String strStructurecode,String strIsk_code) throws Exception
	{
		List listSelf = new ArrayList();
		List listFather = new ArrayList();
		String strStructurecodes = StringParser.parseStructureList(strStructurecode,1,2);
		int iSubstrIndex = strStructurecodes.indexOf(",'"+strStructurecode+"'");
		if(iSubstrIndex != -1)
		{
			strStructurecodes = "("+strStructurecodes.substring(0,iSubstrIndex)+")";
			listFather = RoleGmenusDemand.queryByStructurecode(strStructurecodes, strIsk_code);
		}
		listSelf = RoleGmenusDemand.queryByStructurecode("'"+strStructurecode+"'",strIsk_code);
		QueryByStructurecodeReturn objQBSReturn = new QueryByStructurecodeReturn();
		objQBSReturn.setM_listFatherRolegmenusColumns(listFather);
		objQBSReturn.setM_listSelfRolegmenusColumns(listSelf);
		return objQBSReturn;
	}
}
