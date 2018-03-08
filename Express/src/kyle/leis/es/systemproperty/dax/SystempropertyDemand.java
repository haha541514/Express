package kyle.leis.es.systemproperty.dax;

import java.util.List;

import kyle.common.connectors.conf.ServerConfig;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.systemproperty.da.PreictmappingColumns;
import kyle.leis.es.systemproperty.da.PreictmappingQuery;
import kyle.leis.es.systemproperty.da.SystempropertyColumns;
import kyle.leis.es.systemproperty.da.SystempropertyCondition;
import kyle.leis.es.systemproperty.da.SystempropertyQuery;

public class SystempropertyDemand {

	public static List query(SystempropertyCondition objSPCondition) throws Exception{
		SystempropertyQuery objSPQuery = new SystempropertyQuery();
		objSPCondition.setEecode("");
		objSPQuery.setCondition(objSPCondition);
		return objSPQuery.getResults();
	}
	
	public static boolean getSignOutCheckFinance() throws Exception{
		SystempropertyCondition objSPCondition = new SystempropertyCondition();
		objSPCondition.setSpname("SignOutCheckFinance");
		List listResults = query(objSPCondition);
		if (listResults == null || listResults.size() < 1)
			return true;
		String strSpsvalue = ((SystempropertyColumns)(listResults.get(0))).getSpspvalue();
		if (StringUtility.isNull(strSpsvalue) || strSpsvalue.equals("Y"))
			return true;
		return false;
	}	
	
	
	public static String getEnterprise() throws Exception{
		SystempropertyQuery objSPQuery = new SystempropertyQuery();
		objSPQuery.setUseCachesign(true);
		objSPQuery.setSpname("Enterprise");
		List list = objSPQuery.getResults();
		if (list == null || list.size() < 1)
			return "";
		return ((SystempropertyColumns)list.get(0)).getSpspvalue();
	}	
	
	public static String getMaxPiecesSOByChildLabelcode() throws Exception{
		SystempropertyQuery objSPQuery = new SystempropertyQuery();
		objSPQuery.setUseCachesign(true);
		objSPQuery.setSpname("MaxPiecesSOByChild");
		List list = objSPQuery.getResults();
		if (list == null || list.size() < 1)
			return "";
		return ((SystempropertyColumns)list.get(0)).getSpspvalue();
	}		
	
	public static String getSIPrintChildLabelSign() throws Exception {
		SystempropertyQuery objSPQuery = new SystempropertyQuery();
		objSPQuery.setUseCachesign(true);
		objSPQuery.setSpname("SIPrintChildLabel");
		List list = objSPQuery.getResults();
		if (list == null || list.size() < 1)
			return "";
		return ((SystempropertyColumns)list.get(0)).getSpspvalue();
	}		
	
	public static String getWaybillFilePath() throws Exception {
		// 优先访问配置文件
		ServerConfig objServerConfig = ServerConfig.getInstance();
		if (objServerConfig.getWaybillcodeConfig() != null &&
				!StringUtility.isNull(objServerConfig.getWaybillcodeConfig().getWaybillFilePath()))
			return objServerConfig.getWaybillcodeConfig().getWaybillFilePath();
		
		SystempropertyQuery objSPQuery = new SystempropertyQuery();
		objSPQuery.setUseCachesign(true);
		objSPQuery.setSpname("WaybillFilePath");
		List list = objSPQuery.getResults();
		if (list == null || list.size() < 1)
			return "";
		return ((SystempropertyColumns)list.get(0)).getSpspvalue();
	}	
	
	public static String getWaybillPrintURL() throws Exception {
		ServerConfig objServerConfig = ServerConfig.getInstance();
		if (objServerConfig.getWaybillcodeConfig() != null &&
				!StringUtility.isNull(objServerConfig.getWaybillcodeConfig().getWaybillPrintURL()))
			return objServerConfig.getWaybillcodeConfig().getWaybillPrintURL();		
		
		SystempropertyQuery objSPQuery = new SystempropertyQuery();
		objSPQuery.setUseCachesign(true);
		objSPQuery.setSpname("WaybillPrintURL");
		List list = objSPQuery.getResults();
		if (list == null || list.size() < 1)
			return "";
		return ((SystempropertyColumns)list.get(0)).getSpspvalue();
	}		
	
	public static String getPreictmapping(String strOriginValue,
			String strPtccode,
			String strDmkcode) throws Exception {
		PreictmappingQuery objPreictmappingQuery = new PreictmappingQuery();
		objPreictmappingQuery.setDmkcode(strDmkcode);
		objPreictmappingQuery.setOriginvalue(strOriginValue);
		objPreictmappingQuery.setPtccode(strPtccode);
		List listResults = objPreictmappingQuery.getResults();
		if (listResults == null || listResults.size() < 1)
			return "";
		return ((PreictmappingColumns)listResults.get(0)).getPm_standardvalue();
	}
	
}
