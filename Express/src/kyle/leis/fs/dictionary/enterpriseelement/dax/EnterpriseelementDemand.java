package kyle.leis.fs.dictionary.enterpriseelement.dax;

import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCityDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiDistrictDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementkindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiRegionDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiStateDC;
import kyle.leis.fs.dictionary.enterpriseelement.da.BranchColumns;
import kyle.leis.fs.dictionary.enterpriseelement.da.DistributioncenterColumns;
import kyle.leis.fs.dictionary.enterpriseelement.da.EecityQuery;
import kyle.leis.fs.dictionary.enterpriseelement.da.EnterpriseelementColumns;
import kyle.leis.fs.dictionary.enterpriseelement.da.EnterpriseelementCondition;
import kyle.leis.fs.dictionary.enterpriseelement.da.EnterpriseelementQuery;
import kyle.leis.hi.TdiEnterpriseelement;

public class EnterpriseelementDemand {
	public static List query(EnterpriseelementCondition objEnterpriseelementCon) throws Exception
	{
		EnterpriseelementQuery objEnterpriseelementQue = new EnterpriseelementQuery();
		objEnterpriseelementQue.setCondition(objEnterpriseelementCon);
		return objEnterpriseelementQue.getResults();
	}
	
	public static String getEestructurecode(String strEecode) throws Exception {
		EnterpriseelementQuery objEnterpriseelementQue = new EnterpriseelementQuery();
		objEnterpriseelementQue.setEecode(strEecode);
		objEnterpriseelementQue.setUseCachesign(true);
		List listResults = objEnterpriseelementQue.getResults();
		if (listResults == null || listResults.size() < 1)
			return "";
		return ((EnterpriseelementColumns)listResults.get(0)).getEeeestructurecode();
	}
	
	public static List queryEecity(String strEecode) throws Exception {
		EecityQuery objEecityQuery = new EecityQuery();
		objEecityQuery.setEecode(strEecode);
		return objEecityQuery.getResults();
	}	
	
	
	public static String getUpBranchEecode(String strEecode) throws Exception {
		EnterpriseelementQuery objEnterpriseelementQue = new EnterpriseelementQuery();
		objEnterpriseelementQue.setEecode(strEecode);
		objEnterpriseelementQue.setUseCachesign(true);
		List listResults = objEnterpriseelementQue.getResults();
		if (listResults == null || listResults.size() < 1)
			return "";
		// 如果要查找的为分公司则直接返回
		EnterpriseelementColumns objEeColumns = (EnterpriseelementColumns)listResults.get(0);	
		if (objEeColumns.getEekeekcode().equals("BR"))
			return objEeColumns.getEeeecode();
		// 如果是分拨中心则查找上一级的分公司
		EnterpriseelementQuery objEEQ = new EnterpriseelementQuery();
		objEEQ.setLikeeestructurecode(objEeColumns.getEeeestructurecode());
		objEEQ.setEekcode("BR");
		objEEQ.setUseCachesign(true);
		listResults = objEEQ.getResults();
		if (listResults == null || listResults.size() < 1)
			return "";
		String strUpBranchEecode = "";
		String strUpBranchEestructure = "";
		for (int i = 0; i < listResults.size(); i++) {
			objEeColumns = (EnterpriseelementColumns)listResults.get(i);
			if (objEeColumns.getEeeestructurecode().length() > strUpBranchEestructure.length()) {
				strUpBranchEestructure = objEeColumns.getEeeestructurecode();
				strUpBranchEecode = objEeColumns.getEeeecode();
			}
		}
		return strUpBranchEecode;
	}
	
	
	public static void setEnterpriseelementByCol(TdiEnterpriseelement objTdiEnterpriseelement,EnterpriseelementColumns objEnterpriseelementCol,String strOperId) throws Exception
	{
		if(!StringUtility.isNull(objEnterpriseelementCol.getEeeeaddress()))
			objTdiEnterpriseelement.setEeAddress(objEnterpriseelementCol.getEeeeaddress());
		objTdiEnterpriseelement.setEeEaddress(objEnterpriseelementCol.getEeeeeaddress());
		objTdiEnterpriseelement.setEeEmail(objEnterpriseelementCol.getEeeeemail());
		objTdiEnterpriseelement.setEeEname(objEnterpriseelementCol.getEeeeename());
		objTdiEnterpriseelement.setEeEsname(objEnterpriseelementCol.getEeeeesname());
		objTdiEnterpriseelement.setEeFax(objEnterpriseelementCol.getEeeefax());
		objTdiEnterpriseelement.setEeLevel(Integer.parseInt(objEnterpriseelementCol.getEeeelevel()));
		objTdiEnterpriseelement.setEeModifydate(DateFormatUtility.getSysdate());
		objTdiEnterpriseelement.setEeName(objEnterpriseelementCol.getEeeename());
		objTdiEnterpriseelement.setEeOpIdModifier(Long.parseLong(strOperId));
		if(!StringUtility.isNull(objEnterpriseelementCol.getEeeepostcode()))
			objTdiEnterpriseelement.setEePostcode(objEnterpriseelementCol.getEeeepostcode());
		objTdiEnterpriseelement.setEeSname(objEnterpriseelementCol.getEeeesname());
		objTdiEnterpriseelement.setEeStructurecode(objEnterpriseelementCol.getEeeestructurecode());
		objTdiEnterpriseelement.setEeTelephone(objEnterpriseelementCol.getEeeetelephone());
		
		objTdiEnterpriseelement.setTdiDistrict(TdiDistrictDC.loadByKey(objEnterpriseelementCol.getDtdtcode()));
		objTdiEnterpriseelement.setTdiEnterpriseelementkind(TdiEnterpriseelementkindDC.loadByKey(objEnterpriseelementCol.getEekeekcode()));
		objTdiEnterpriseelement.setTdiRegion(TdiRegionDC.loadByKey(objEnterpriseelementCol.getRgrgcode()));
	}
	
	public static void transferBrColToEeCol(TdiEnterpriseelement objTdiEnterpriseelement,
			BranchColumns objBranchCol,
			String strOperId) throws Exception
	{
		if(!StringUtility.isNull(objBranchCol.getEeeeaddress()))
			objTdiEnterpriseelement.setEeAddress(objBranchCol.getEeeeaddress());
		objTdiEnterpriseelement.setEeEaddress(objBranchCol.getEeeeeaddress());
		objTdiEnterpriseelement.setEeEmail(objBranchCol.getEeeeemail());
		objTdiEnterpriseelement.setEeEname(objBranchCol.getEeeeename());
		objTdiEnterpriseelement.setEeEsname(objBranchCol.getEeeeesname());
		objTdiEnterpriseelement.setEeFax(objBranchCol.getEeeefax());
		objTdiEnterpriseelement.setEeLevel(Integer.parseInt(objBranchCol.getEeeelevel()));
		objTdiEnterpriseelement.setEeModifydate(DateFormatUtility.getSysdate());
		objTdiEnterpriseelement.setEeName(objBranchCol.getEeeename());
		objTdiEnterpriseelement.setEeOpIdModifier(Long.parseLong(strOperId));
		
		objTdiEnterpriseelement.setTdiState(null);
		objTdiEnterpriseelement.setTdiCity(null);
		objTdiEnterpriseelement.setTdiRegion(null);
		if (!StringUtility.isNull(objBranchCol.getStstcode())) {
			objTdiEnterpriseelement.setTdiState(TdiStateDC.loadByKey(objBranchCol.getStstcode()));
		}
		if (!StringUtility.isNull(objBranchCol.getCtctcode())) {
			objTdiEnterpriseelement.setTdiCity(TdiCityDC.loadByKey(objBranchCol.getCtctcode()));
		}		
		if (!StringUtility.isNull(objBranchCol.getRgrgcode())) {
			objTdiEnterpriseelement.setTdiRegion(TdiRegionDC.loadByKey(objBranchCol.getRgrgcode()));
		}			
		
		if(!StringUtility.isNull(objBranchCol.getEeeepostcode()))
			objTdiEnterpriseelement.setEePostcode(objBranchCol.getEeeepostcode());
		objTdiEnterpriseelement.setEeSname(objBranchCol.getEeeesname());
		objTdiEnterpriseelement.setEeStructurecode(objBranchCol.getEeeestructurecode());
		objTdiEnterpriseelement.setEeTelephone(objBranchCol.getEeeetelephone());
		
		objTdiEnterpriseelement.setTdiDistrict(TdiDistrictDC.loadByKey(objBranchCol.getDtdtcode()));
		objTdiEnterpriseelement.setTdiEnterpriseelementkind(TdiEnterpriseelementkindDC.loadByKey(objBranchCol.getEekeekcode()));
	}
	
	public static void transferDcColToEeCol(TdiEnterpriseelement objTdiEnterpriseelement,
			DistributioncenterColumns objDistributioncenterCol,
			String strOperId) throws Exception
	{
		if(!StringUtility.isNull(objDistributioncenterCol.getDceeeeeaddress()))
			objTdiEnterpriseelement.setEeEaddress(objDistributioncenterCol.getDceeeeeaddress());
		objTdiEnterpriseelement.setEeAddress(objDistributioncenterCol.getDceeeeaddress());
		objTdiEnterpriseelement.setEeEmail(objDistributioncenterCol.getDceeeeemail());
		objTdiEnterpriseelement.setEeEname(objDistributioncenterCol.getDceeeeename());
		objTdiEnterpriseelement.setEeEsname(objDistributioncenterCol.getDceeeeesname());
		objTdiEnterpriseelement.setEeFax(objDistributioncenterCol.getDceeeefax());
		objTdiEnterpriseelement.setEeLevel(Integer.parseInt(objDistributioncenterCol.getDceeeelevel()));
		objTdiEnterpriseelement.setEeModifydate(DateFormatUtility.getSysdate());
		objTdiEnterpriseelement.setEeName(objDistributioncenterCol.getDceeeename());
		objTdiEnterpriseelement.setEeOpIdModifier(Long.parseLong(strOperId));
		objTdiEnterpriseelement.setTdiState(null);
		objTdiEnterpriseelement.setTdiCity(null);
		objTdiEnterpriseelement.setTdiRegion(null);
		if (!StringUtility.isNull(objDistributioncenterCol.getStstcode())) {
			objTdiEnterpriseelement.setTdiState(TdiStateDC.loadByKey(objDistributioncenterCol.getStstcode()));
		}
		if (!StringUtility.isNull(objDistributioncenterCol.getCtctcode())) {
			objTdiEnterpriseelement.setTdiCity(TdiCityDC.loadByKey(objDistributioncenterCol.getCtctcode()));
		}		
		if (!StringUtility.isNull(objDistributioncenterCol.getDceergrgcode())) {
			objTdiEnterpriseelement.setTdiRegion(TdiRegionDC.loadByKey(objDistributioncenterCol.getDceergrgcode()));
		}			
		
		if(!StringUtility.isNull(objDistributioncenterCol.getDceeeepostcode()))
			objTdiEnterpriseelement.setEePostcode(objDistributioncenterCol.getDceeeepostcode());
		objTdiEnterpriseelement.setEeSname(objDistributioncenterCol.getDceeeesname());
		objTdiEnterpriseelement.setEeStructurecode(objDistributioncenterCol.getDceeeestructurecode());
		objTdiEnterpriseelement.setEeTelephone(objDistributioncenterCol.getDceeeetelephone());
		
		objTdiEnterpriseelement.setTdiDistrict(TdiDistrictDC.loadByKey(objDistributioncenterCol.getDceedtdtcode()));
		objTdiEnterpriseelement.setTdiEnterpriseelementkind(TdiEnterpriseelementkindDC.loadByKey(objDistributioncenterCol.getDceeeekeekcode()));
	}
}
