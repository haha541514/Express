package kyle.leis.fs.dictionary.dictionarys.dax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.CustomscargoCondition;
import kyle.leis.fs.dictionary.dictionarys.da.CustomscargoQuery;
import kyle.leis.fs.dictionary.dictionarys.da.EparceldistrictColumns;
import kyle.leis.fs.dictionary.dictionarys.da.EparceldistrictQuery;
import kyle.leis.fs.dictionary.dictionarys.da.ServerfeekindmappingColumns;
import kyle.leis.fs.dictionary.dictionarys.da.ServerfeekindmappingQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TemplatecolumnColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TemplatecolumnQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TntaddressColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TntaddressQuery;

public class DictionaryDemand {
	
	private static Map<String, TemplatecolumnColumns> s_hmStandardTemplate;
	
	public static String getDHLFkcodeByServerFkname(String strServerFkname) throws Exception {
		return getFkcodeByServerFkname(strServerFkname, "DHL");
	}
	
	public static String getFkcodeByServerFkname(String strServerFkname,
			String strFsmKind) throws Exception {
		// 为空则表示运费
		if (StringUtility.isNull(strServerFkname))
			strServerFkname = "Freight Charge";
		ServerfeekindmappingQuery objSFKMQuery = new ServerfeekindmappingQuery();
		objSFKMQuery.setUseCachesign(true);
		objSFKMQuery.setSfkmserverbillkind(strFsmKind);
		List objList = objSFKMQuery.getResults();
		if (objList == null || objList.size() < 1)
			return "";
		for (int i = 0; i < objList.size(); i++) {
			ServerfeekindmappingColumns objSFKMColumns = (ServerfeekindmappingColumns)objList.get(i);
			if (objSFKMColumns.getSfmsfkmorigindesc().equals(strServerFkname))
				return objSFKMColumns.getFkfkcode();
		}
		return "";
	}
	
	public static List queryCustomsCargo(CustomscargoCondition objCCondition) throws Exception {
		CustomscargoQuery objCCQuery = new CustomscargoQuery();
		String strCcename = objCCondition.getCcename();
		if (!StringUtility.isNull(strCcename) && strCcename.indexOf("'") > 0)
			objCCondition.setCcename(strCcename.replaceAll("'", "''")); 
		objCCQuery.setCondition(objCCondition);
		return objCCQuery.getResults();
	}
	
	public static List queryStandardTemplate() throws Exception {
		TemplatecolumnQuery objTCQ = new TemplatecolumnQuery();
		objTCQ.setUseCachesign(true);
		return objTCQ.getResults();
	}
	
	public static List queryStandardTemplate(boolean isIMPAPI) throws Exception {
		TemplatecolumnQueryEX objTCQ = new TemplatecolumnQueryEX(isIMPAPI);
		objTCQ.setUseCachesign(true);
		return objTCQ.getResults();
	}	
	
	public static String getEparcelDistrict(String strPostcode) throws Exception {
		EparceldistrictQuery objEPDQuery = new EparceldistrictQuery();
		objEPDQuery.setStartpostcode(strPostcode);
		objEPDQuery.setEndpostcode(strPostcode);
		List list = objEPDQuery.getResults();
		
		if (list == null || list.size() < 1)
			return "";
		return ((EparceldistrictColumns)list.get(0)).getEpdepd_hubcode();
	}
	
	public static List queryPwStandardTemplate() throws Exception {
		TemplatecolumnQueryEX objTCQ = new TemplatecolumnQueryEX();
		objTCQ.setUseCachesign(true);
		return objTCQ.getResults();
	}	
	
	public static Map getStandardTemplateMap() throws Exception {
		if (s_hmStandardTemplate != null && s_hmStandardTemplate.size() > 0)
			return s_hmStandardTemplate;
		
		s_hmStandardTemplate = new HashMap<String, TemplatecolumnColumns>();
		List listResults = queryStandardTemplate();
		if (listResults == null ||listResults.size() < 1)
			return s_hmStandardTemplate;
		for (int i = 0; i < listResults.size(); i++) {
			TemplatecolumnColumns objTCC = (TemplatecolumnColumns)listResults.get(i);
			s_hmStandardTemplate.put(objTCC.getTctcid(), objTCC);
		}
		return s_hmStandardTemplate;
	}
	
	public static TntaddressColumns loadTNTCountryAddress(String strCountryHubcode) throws Exception {
		TntaddressQuery objTAQ = new TntaddressQuery();
		objTAQ.setCountryhubcode(strCountryHubcode);
		List listResults = objTAQ.getResults();
		if (listResults == null || listResults.size() < 1)
			return null;
		return (TntaddressColumns)listResults.get(0);
	}
	
}
