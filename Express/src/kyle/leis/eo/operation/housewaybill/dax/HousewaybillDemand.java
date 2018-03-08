package kyle.leis.eo.operation.housewaybill.dax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybill.dax.ICorewaybillBasicData;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.CorewaybillforchangeewbQuery;
import kyle.leis.eo.operation.housewaybill.da.Dhlconnect4Condition;
import kyle.leis.eo.operation.housewaybill.da.Dhlconnect4Query;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallCondition;
import kyle.leis.eo.operation.housewaybill.da.ForinputallQuery;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillQuery;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillforcustomsCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillforcustomsQuery;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillforklexCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillforklexQuery;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillformanifestCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillformanifestQuery;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillforreportCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillforreportQuery;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillfortarazzColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillfortarazzCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillfortarazzQuery;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillremarkColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillremarkQuery;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.da.LabeldataColumns;
import kyle.leis.eo.operation.housewaybill.da.LabeldataQuery;
import kyle.leis.eo.operation.housewaybill.da.LatestcreatedateColumns;
import kyle.leis.eo.operation.housewaybill.da.LatestcreatedateQuery;
import kyle.leis.eo.operation.housewaybill.da.SimplewaybillforpackageColumns;
import kyle.leis.eo.operation.housewaybill.da.SimplewaybillforpackageQuery;
import kyle.leis.eo.operation.housewaybill.da.SobagstatisticCondition;
import kyle.leis.eo.operation.housewaybill.da.SumweightcheckweightCondition;
import kyle.leis.eo.operation.housewaybill.da.SumweightcheckweightQuery;
import kyle.leis.eo.operation.housewaybill.da.WaybillforauditColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforauditCondition;
import kyle.leis.eo.operation.housewaybill.da.WaybillforauditQuery;
import kyle.leis.eo.operation.housewaybill.da.WaybillforbillCondition;
import kyle.leis.eo.operation.housewaybill.da.WaybillforbillQuery;
import kyle.leis.eo.operation.housewaybill.da.WaybillfordcustomerCondition;
import kyle.leis.eo.operation.housewaybill.da.WaybillfordcustomerQuery;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpackageColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpackageCondition;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpackageQuery;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictCondition;
import kyle.leis.eo.operation.housewaybill.da.WaybillforprintorderCondition;
import kyle.leis.eo.operation.housewaybill.da.WaybillforprintorderQuery;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpurchaseCondition;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpurchaseQuery;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;

public class HousewaybillDemand {
	public static InputAllQReturn queryInput(ForinputallCondition objFInputAllC,
			String strEestructurecode) throws Exception {
		InputAllQReturn objIAQR = queryInput(objFInputAllC);
		if (objIAQR == null) return objIAQR;
		
		List listHouseWayBill = objIAQR.getHWBResults();
		if (listHouseWayBill == null || listHouseWayBill.size() < 1)
			return objIAQR;
		
		List<ForinputallColumns> listReturn = new ArrayList<ForinputallColumns>();
		ForinputallColumns objFIAColumns = (ForinputallColumns)listHouseWayBill.get(0);
		String strHwstructurecode = EnterpriseelementDemand.getEestructurecode(objFIAColumns.getEecode());
		if (strHwstructurecode.startsWith(strEestructurecode))
			listReturn.add(objFIAColumns);
		objIAQR.setHWBResults(listReturn);
		
		return objIAQR;
	}
	
	
	
	public static InputAllQReturn queryInput(ForinputallCondition objFInputAllC) throws Exception {
		InputAllQReturn objIAQR = new InputAllQReturn();
		// 查询运单信息
		if (!StringUtility.isNull(objFInputAllC.getNotincwscode()) &&
				objFInputAllC.getNotincwscode().equals("CEL")) {
			// 其他条件为空,这里其实可以增加Condition为空的方法，但由于涉及到common的修改，所以延后
			// 暂时这样实现
			if (StringUtility.isNull(objFInputAllC.getCustomerewbcode()) &&
					StringUtility.isNull(objFInputAllC.getCwcode()) &&
					StringUtility.isNull(objFInputAllC.getServerewbcode()) &&
					StringUtility.isNull(objFInputAllC.getEwbcode())) {
				objFInputAllC.setEwbcode("1a??");
			}
		}
		ForinputallQuery objFInputAllQ = new ForinputallQuery();
		objFInputAllQ.setCondition(objFInputAllC);
		List listHouseWayBill = objFInputAllQ.getResults();
		objIAQR.setHWBResults(listHouseWayBill);
		if (listHouseWayBill == null || listHouseWayBill.size() < 1) 
			return objIAQR;
		// 查询货物详细信息
		ForinputallColumns objFIAColumns = (ForinputallColumns)listHouseWayBill.get(0);
		objIAQR.setPieces(CorewaybillpiecesDemand.load(objFIAColumns.getCwcode()));
		if (objFIAColumns.getCtcode().equals("DOX")) return objIAQR;
		
		List listCargoInfo = CargoInfoDemand.queryByCwCode(objFIAColumns.getCwcode());
		objIAQR.setCargoInfoResults(listCargoInfo);
		
		return objIAQR;
	}
	
	public static ForinputallColumns load(String strCwcode) throws Exception {
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setCwcode(strCwcode);
		
		ForinputallQuery objFInputAllQ = new ForinputallQuery();
		objFInputAllQ.setCondition(objFInputAllC);
		List objList = objFInputAllQ.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (ForinputallColumns)objList.get(0);
	}
	
	public static List query(HousewaybillCondition objHWBCondition) throws Exception {
		HousewaybillQuery objHWBQuery = new HousewaybillQuery();
		String strCustomerEwbcode = objHWBCondition.getCustomerewbcode();
		
		if (!StringUtility.isNull(strCustomerEwbcode))
			objHWBCondition.setExistsorderid("");
		
		objHWBQuery.setCondition(objHWBCondition);
		
		List objList = objHWBQuery.getResults();
		
		if (!StringUtility.isNull(strCustomerEwbcode)) {
			objHWBQuery = new HousewaybillQuery();
			objHWBCondition.setCustomerewbcode("");
			objHWBCondition.setExistsorderid(strCustomerEwbcode);
			objHWBQuery.setCondition(objHWBCondition);
			List listResults = objHWBQuery.getResults();
			objList = addNoExists(objList, listResults);
		}
		return objList;
	}
	
	private static List addNoExists(List listSource,
			List listDestination) {
		if (listSource == null || listSource.size() < 1)
			return listDestination;
		if (listDestination == null || listDestination.size() < 1)
			return listSource;
		List<HousewaybillColumns> listResults = new ArrayList<HousewaybillColumns>();
		for (int i = 0; i < listSource.size(); i++) {
			listResults.add((HousewaybillColumns)listSource.get(i));
		}
		for (int i = 0; i < listDestination.size(); i++) {
			HousewaybillColumns objHWC = (HousewaybillColumns)listDestination.get(i);
			boolean isRepeat = false;
			for (int j = 0; j < listSource.size(); j++) {
				HousewaybillColumns objHWCSource = (HousewaybillColumns)listSource.get(j);
				if (objHWC.getHwcwcode().equals(objHWCSource.getHwcwcode())) {
					isRepeat = true;
					break;
				}
			}
			if (!isRepeat)
				listResults.add(objHWC);
		}
		return listResults;
	}
	
	
	private static List addPredictNoExists(List listSource,
			List listDestination) {
		if (listSource == null || listSource.size() < 1)
			return listDestination;
		if (listDestination == null || listDestination.size() < 1)
			return listSource;
		List<WaybillforpredictColumns> listResults = new ArrayList<WaybillforpredictColumns>();
		for (int i = 0; i < listSource.size(); i++) {
			listResults.add((WaybillforpredictColumns)listSource.get(i));
		}
		for (int i = 0; i < listDestination.size(); i++) {
			WaybillforpredictColumns objHWC = (WaybillforpredictColumns)listDestination.get(i);
			boolean isRepeat = false;
			for (int j = 0; j < listSource.size(); j++) {
				WaybillforpredictColumns objHWCSource = (WaybillforpredictColumns)listSource.get(j);
				if (objHWC.getCwcw_code().equals(objHWCSource.getCwcw_code())) {
					isRepeat = true;
					break;
				}
			}
			if (!isRepeat)
				listResults.add(objHWC);
		}
		return listResults;
	}
	
	public static List queryForPredict(WaybillforpredictCondition objWFPCondition) throws Exception {
		String strSystemPE = SystempropertyDemand.getEnterprise();
		
		WaybillforpredictQueryEX objWFPQuery = new WaybillforpredictQueryEX();
		boolean isSystemIM = false;
		String strCustomer = "";
		if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("SLYIM")) {
			objWFPQuery = new WaybillforpredictQueryIMEXCNULL();
			isSystemIM = true;
			if (!StringUtility.isNull(objWFPCondition.getCocodecustomer())) {
				objWFPQuery = new WaybillforpredictQueryIMEX(objWFPCondition.getCocodecustomer());
				strCustomer = objWFPCondition.getCocodecustomer();
				objWFPCondition.setCocodecustomer("");
				objWFPCondition.setLikecocode(strCustomer);
				//isSystemIM = true;
			}
		}
		
		String strCustomerEwbcode = objWFPCondition.getCwcustomerewbcode();
		if (!StringUtility.isNull(strCustomerEwbcode))
			objWFPCondition.setExistsorderid("");
		
		objWFPQuery.setCondition(objWFPCondition);
		List objList = objWFPQuery.getResults();
		
		if (!StringUtility.isNull(strCustomerEwbcode)) {
			if (isSystemIM)
				objWFPQuery = new WaybillforpredictQueryIMEX(strCustomer);
			else
				objWFPQuery = new WaybillforpredictQueryEX();
			objWFPCondition.setCwcustomerewbcode("");
			objWFPCondition.setExistsorderid(strCustomerEwbcode);
			objWFPQuery.setCondition(objWFPCondition);
			List listResults = objWFPQuery.getResults();
			objList = addPredictNoExists(objList, listResults);
		}
		return objList;
	}	

	//重写方法
	public static List queryForPredict(WaybillforpredictCondition objWFPCondition,
			String field,
			String sort) throws Exception {
		/*
		WaybillforpredictQueryParamer objWFPQuery=null;
		if(!StringUtility.isNull(objWFPCondition.getCwopidcreator())){
		    objWFPQuery = new WaybillforpredictQueryParamer(field, sort, Long.valueOf(objWFPCondition.getCwopidcreator()));
		}else{
			 objWFPQuery = new WaybillforpredictQueryParamer(field, sort);
		}
		*/
		WaybillforpredictQueryParamer objWFPQuery = new WaybillforpredictQueryParamer(field, sort);
		String strCustomerEwbcode = objWFPCondition.getCwcustomerewbcode();
		if (!StringUtility.isNull(strCustomerEwbcode))
			objWFPCondition.setExistsorderid("");
		
		objWFPQuery.setCondition(objWFPCondition);
		List objList = objWFPQuery.getResults();
		
		if (!StringUtility.isNull(strCustomerEwbcode)) {
			objWFPQuery = new WaybillforpredictQueryParamer(field, sort);
			objWFPCondition.setCwcustomerewbcode("");
			objWFPCondition.setExistsorderid(strCustomerEwbcode);
			objWFPQuery.setCondition(objWFPCondition);
			List listResults = objWFPQuery.getResults();
			objList = addPredictNoExists(objList, listResults);
		}
		return objList;
	}
	public static WaybillforpredictColumns loadForPredict(String strCwcode) throws Exception {
		if (StringUtility.isNull(strCwcode))
			return null;
		WaybillforpredictCondition objWFPCondition = new WaybillforpredictCondition();
		objWFPCondition.setCwcode(strCwcode);
		List listResults = queryForPredict(objWFPCondition);
		if(listResults == null || listResults.size() < 1)
			return null;
		return (WaybillforpredictColumns)listResults.get(0);
	}	
	
	
	public static List queryForprintorder(WaybillforprintorderCondition objWPOCondition) throws Exception {
		WaybillforprintorderQuery objWPOQuery = new WaybillforprintorderQuery();
		objWPOQuery.setCondition(objWPOCondition);
		return objWPOQuery.getResults();
	}	
	
	
	public static List queryForreport(HousewaybillforreportCondition objHWBFRCondition) throws Exception {
		HousewaybillforreportQuery objHWFRQuery = new HousewaybillforreportQuery();
		if (StringUtility.isNull(objHWBFRCondition.getBegincarryoversign()) && 
				StringUtility.isNull(objHWBFRCondition.getEndcarryoversigin()))
			objHWBFRCondition.setFscarryoverenterprise("ALL");
		if (!StringUtility.isNull(objHWBFRCondition.getBegincarryoversign()))
			objHWBFRCondition.setFscarryoverenterprise("SLY");
		if (!StringUtility.isNull(objHWBFRCondition.getEndcarryoversigin()))
			objHWBFRCondition.setFscarryoverenterprise("BAIQIAN");		
		
		if (!StringUtility.isNull(objHWBFRCondition.getIncustomerewbcode()) &&
				StringUtility.isNull(objHWBFRCondition.getInserverewbcode()))
			objHWBFRCondition.setInserverewbcode("TTTXXX");
		if (StringUtility.isNull(objHWBFRCondition.getIncustomerewbcode()) &&
				!StringUtility.isNull(objHWBFRCondition.getInserverewbcode()))
			objHWBFRCondition.setIncustomerewbcode("TTTXXX");
		
		if (!StringUtility.isNull(objHWBFRCondition.getStartsignindate()) &&
				StringUtility.isNull(objHWBFRCondition.getStartarrivedate())) {
			objHWBFRCondition.setStartarrivedate(objHWBFRCondition.getStartsignindate());
			objHWBFRCondition.setEndarrivedate(objHWBFRCondition.getEndsignindate());
			objHWBFRCondition.setStartsignindate("");
			objHWBFRCondition.setEndsignindate("");
		}
		
		if (!StringUtility.isNull(objHWBFRCondition.getEecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objHWBFRCondition.getEecode());
			objHWBFRCondition.setEestructurecode(strEestructurecode);
			objHWBFRCondition.setEecode(null);
		}	
		objHWFRQuery.setCondition(objHWBFRCondition);
		return objHWFRQuery.getResults();
	}	
	
	public static List queryForcustoms(HousewaybillforcustomsCondition objHWBFCCondition) throws Exception {
		HousewaybillforcustomsQuery objHWBFCQuery = new HousewaybillforcustomsQuery();
		if (!StringUtility.isNull(objHWBFCCondition.getIncustomerewbcode()) &&
				StringUtility.isNull(objHWBFCCondition.getInserverewbcode()))
			objHWBFCCondition.setInserverewbcode("TTTXXX");
		if (StringUtility.isNull(objHWBFCCondition.getIncustomerewbcode()) &&
				!StringUtility.isNull(objHWBFCCondition.getInserverewbcode()))
			objHWBFCCondition.setIncustomerewbcode("TTTXXX");
		if (!StringUtility.isNull(objHWBFCCondition.getEecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objHWBFCCondition.getEecode());
			objHWBFCCondition.setEestructurecode(strEestructurecode);
			objHWBFCCondition.setEecode(null);
		}		
		objHWBFCQuery.setCondition(objHWBFCCondition);
		return objHWBFCQuery.getResults();
	}	
	
	public static List queryFormanifest(HousewaybillformanifestCondition objHWBFMCondition) 
	throws Exception {
		HousewaybillformanifestQuery objHWBFQuery = new HousewaybillformanifestQuery();
		if (!StringUtility.isNull(objHWBFMCondition.getEecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objHWBFMCondition.getEecode());
			objHWBFMCondition.setEestructurecode(strEestructurecode);
			objHWBFMCondition.setEecode(null);
		}	
		objHWBFQuery.setCondition(objHWBFMCondition);
		return objHWBFQuery.getResults();
	}		
	
	public static List queryForKlex(HousewaybillforklexCondition objHFKC) 
	throws Exception {
		HousewaybillforklexQuery objHFKQuery = new HousewaybillforklexQuery();
		objHFKQuery.setCondition(objHFKC);
		return objHFKQuery.getResults();
	}		
	
	
	public static List queryForTarazz(HousewaybillfortarazzCondition objHWBFTCondition) 
	throws Exception {
		HousewaybillfortarazzQuery objHWBFTQuery = new HousewaybillfortarazzQuery();
		objHWBFTQuery.setCondition(objHWBFTCondition);
		List listResults = objHWBFTQuery.getResults();
		if (listResults == null || listResults.size() < 1) 
			return listResults;
		// 转换Tarazz的州名
		for (int i = 0; i < listResults.size(); i++) {
			HousewaybillfortarazzColumns objHFTColumns = (HousewaybillfortarazzColumns)listResults.get(i);
			String strCityname = objHFTColumns.getReceivercity();
			String strPostcode = objHFTColumns.getReceiverpostalcode();
			strPostcode = StringUtility.removePrefix(strPostcode, '0');
			
			String strStateCode = DistrictDemand.getTarazzStateCode(strCityname, strPostcode);
			objHFTColumns.setReceiverpostalcode(strPostcode);
			objHFTColumns.setReceivercitycode(strStateCode);
		}
		return listResults;
	}
	
	public static List queryForChangeEWB() throws Exception {
		CorewaybillforchangeewbQuery objCBFCQuery = new CorewaybillforchangeewbQuery();
		return objCBFCQuery.getResults();
	}
	
	public static String getLatestcreatedate(String strCocode) throws Exception {
		if (StringUtility.isNull(strCocode)) return "";
		
		LatestcreatedateQuery objLCDQuery = new LatestcreatedateQuery();
		objLCDQuery.setCo_code_customer(strCocode);
		List listResult = objLCDQuery.getResults();
		
		if (listResult == null || listResult.size() < 1) return "";
		return ((LatestcreatedateColumns)listResult.get(0)).getLatestcreatedate();
	}
	
	/**
	 * 查询确定状态的费用和运单
	 * @param objWFDCondition
	 * @return
	 * @throws Exception
	 */
	public static List queryForDirectCustomer(WaybillfordcustomerCondition objWFDCondition) 
	throws Exception {
		WaybillfordcustomerQuery objWFCQuery = new WaybillfordcustomerQuery();
		objWFCQuery.setCondition(objWFDCondition);
		return objWFCQuery.getResults();
	}
	
	public static List queryForBill(WaybillforbillCondition objWBFBCondition) 
	throws Exception {
		WaybillforbillQuery objWBFBQuery = new WaybillforbillQuery();
		if (!StringUtility.isNull(objWBFBCondition.getEecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objWBFBCondition.getEecode());
			objWBFBCondition.setEestructurecode(strEestructurecode);
			objWBFBCondition.setEecode(null);
		}	
		
		objWBFBQuery.setCondition(objWBFBCondition);
		return objWBFBQuery.getResults();
	}
	
	public static List queryForPurchase(WaybillforpurchaseCondition objWBFPCondition) 
	throws Exception {
		WaybillforpurchaseQuery objWBFPQuery = new WaybillforpurchaseQuery();
		objWBFPQuery.setCondition(objWBFPCondition);
		return objWBFPQuery.getResults();
	}
	
	public static List sumWeightcheckweight(String strBwcodeweightcheck) 
	throws Exception {
		SumweightcheckweightQuery objSCWQ = new SumweightcheckweightQuery();
		objSCWQ.setBwcodeweightcheck(strBwcodeweightcheck);
		return objSCWQ.getResults();
	}
	
	public static List sumWeightcheckweight(SumweightcheckweightCondition objSCWC) 
	throws Exception {
		SumweightcheckweightQuery objSCWQ = new SumweightcheckweightQuery();
		objSCWQ.setCondition(objSCWC);
		return objSCWQ.getResults();
	}	
	
	@SuppressWarnings("unchecked")
	public static List queryForAudit(WaybillforauditCondition objWFACondition) 
	throws Exception {
		
		if (!StringUtility.isNull(objWFACondition.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objWFACondition.getEestructurecode());
			objWFACondition.setEestructurecode(strEestructurecode);
		}			
		if (!StringUtility.isNull(objWFACondition.getExistsrv()) &&
				!StringUtility.isNull(objWFACondition.getFkcode())) {
			objWFACondition.setExistsrv(objWFACondition.getFkcode().replaceAll("'", ""));
			objWFACondition.setExistspy(objWFACondition.getFkcode().replaceAll("'", ""));
			objWFACondition.setFkcode("");
		}
		if (!StringUtility.isNull(objWFACondition.getNotexistsrv()) &&
				!StringUtility.isNull(objWFACondition.getFkcode())) {
			
			objWFACondition.setNotexistsrv(objWFACondition.getFkcode().replaceAll("'", ""));
			objWFACondition.setNotexistspy(objWFACondition.getFkcode().replaceAll("'", ""));
			objWFACondition.setFkcode("");
		}		
		
		if (StringUtility.isNull(objWFACondition.getStartfeecreatedate())) {
			WaybillforauditQuery objWFAQuery = new WaybillforauditQuery();
			objWFAQuery.setCondition(objWFACondition);
			return objWFAQuery.getResults();
		}
		//查询运单开始日期跟截止日期内运单的费用总和
		WaybillforauditQuery betweenQuery = new WaybillforauditQueryEX(objWFACondition.getStartadddate(), 
				objWFACondition.getEndadddate(), objWFACondition.getStartfeecreatedate(), objWFACondition.getStartfeeenddate());
		betweenQuery.setCondition(objWFACondition);
		List<WaybillforauditColumns> betweenColumns = betweenQuery.getResults();
		// 查询运单开始日期以前的运单以及这些运单费用创建日期范围内的费用总和
		String endAdddate = objWFACondition.getStartadddate();
		objWFACondition.setStartadddate("2000-01-01 00:00:00");
		objWFACondition.setEndadddate(endAdddate);
		WaybillforauditQuery beforeQuery = new WaybillforauditQueryBeforeEX(objWFACondition.getStartfeecreatedate(), 
				objWFACondition.getStartfeeenddate());
		beforeQuery.setCondition(objWFACondition);
		List<WaybillforauditColumns> beforeColumns = beforeQuery.getResults();
		//合并
		betweenColumns.addAll(beforeColumns);
		return betweenColumns;
	}
	
	public static HousewaybillColumns loadByCwcode(String strCwcode) throws Exception {
		if (StringUtility.isNull(strCwcode)) return null;
		
		HousewaybillCondition objHWBCondition = new HousewaybillCondition();
		objHWBCondition.setCwcode(strCwcode);
		
		List objList = query(objHWBCondition);
		if (objList == null || objList.size() != 1) return null;
		return (HousewaybillColumns)objList.get(0);		
	}
	
	public static HousewaybillColumns load(String strEwbcode,
			String strEwbcodeType) throws Exception {
		if (StringUtility.isNull(strEwbcode)) return null;
		
		HousewaybillCondition objHWBCondition = new HousewaybillCondition();
		if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_CUSTOMER))
			objHWBCondition.setCustomerewbcode(strEwbcode);
		else if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_SELF))
			objHWBCondition.setEwbcode(strEwbcode);
		else if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_SERVER))
			objHWBCondition.setServerewbcode(strEwbcode);
		List objList = query(objHWBCondition);
		if (objList == null || objList.size() != 1) return null;
		return (HousewaybillColumns)objList.get(0);
	}
	
	public static HousewaybillColumns loadNosignout(String strEwbcode,
			String strEwbcodeType) throws Exception {
		if (StringUtility.isNull(strEwbcode)) return null;
		
		HousewaybillCondition objHWBCondition = new HousewaybillCondition();
		if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_CUSTOMER))
			objHWBCondition.setCustomerewbcode(strEwbcode);
		else if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_SELF))
			objHWBCondition.setEwbcode(strEwbcode);
		else if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_SERVER))
			objHWBCondition.setServerewbcode(strEwbcode);
		objHWBCondition.setNotcwscode("SO");
		List objList = query(objHWBCondition);
		if (objList == null || objList.size() != 1) return null;
		return (HousewaybillColumns)objList.get(0);
	}
	
	public static HousewaybillColumns load(String strEwbcode,
			String strCwscode,
			String strEwbcodeType) throws Exception {
		if (StringUtility.isNull(strEwbcode)) return null;
		
		HousewaybillCondition objHWBCondition = new HousewaybillCondition();
		if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_CUSTOMER))
			objHWBCondition.setCustomerewbcode(strEwbcode);
		else if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_SELF))
			objHWBCondition.setEwbcode(strEwbcode);
		else if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_SERVER))
			objHWBCondition.setServerewbcode(strEwbcode);
		objHWBCondition.setCwscode(strCwscode);
		objHWBCondition.setNotcwscode("SO");
		List objList = query(objHWBCondition);
		if (objList == null || objList.size() != 1) return null;
		return (HousewaybillColumns)objList.get(0);
	}	

	@SuppressWarnings("unchecked")
	public static List loadNormalCollection(String strEwbcode,
			String strEwbcodeType,
			String strEestructurecode) throws Exception {
		List listResults = loadNormalCollection(strEwbcode, strEwbcodeType);
		if (listResults == null || listResults.size() < 1)
			return null;
		if (StringUtility.isNull(strEestructurecode))
			return listResults;
		List listReturn = new ArrayList();
		for (int i = 0; i< listResults.size(); i++) {
			HousewaybillColumns objHWColumns = (HousewaybillColumns)listResults.get(i);
			String strHwstructurecode = EnterpriseelementDemand.getEestructurecode(objHWColumns.getEeeecode());
			if (strHwstructurecode.startsWith(strEestructurecode))
				listReturn.add(objHWColumns);
		}
		return listReturn;
	}
	
	
	public static List loadNormalCollection(String strEwbcode,
			String strEwbcodeType) throws Exception {
		if (StringUtility.isNull(strEwbcode)) return null;
		
		HousewaybillCondition objHWBCondition = new HousewaybillCondition();
		if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_CUSTOMER))
			objHWBCondition.setCustomerewbcode(strEwbcode);
		else if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_SELF))
			objHWBCondition.setEwbcode(strEwbcode);
		else if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_SERVER))
			objHWBCondition.setServerewbcode(strEwbcode);
		else if (strEwbcodeType.equals(ICorewaybillBasicData.EWBCODE_TYPE_SERVERLABELCODE)) {
			String strCwcode = CorewaybillDemand.getCwcodeBySChildLabelcode(strEwbcode);
			objHWBCondition.setCwcode(strCwcode);
		}
		objHWBCondition.setNotcwscode("CEL,EL");
		List objList = query(objHWBCondition);
		if (objList == null || objList.size() < 1) return null;
		return objList;
	}
	
	public static List queryForPackage(WaybillforpackageCondition objWFPC) throws Exception {
		WaybillforpackageQuery objWFPQ = new WaybillforpackageQuery();
		objWFPQ.setCondition(objWFPC);
		return objWFPQ.getResults();
	}
	
	public static SimplewaybillforpackageColumns querySimpleForPackage(String strCwcode) throws Exception {
		SimplewaybillforpackageQuery objSWFPQ = new SimplewaybillforpackageQuery();
		objSWFPQ.setCwcode(strCwcode);
		List listResults = objSWFPQ.getResults();
		if (listResults == null || listResults.size() < 1)
			return null;
		return (SimplewaybillforpackageColumns)listResults.get(0);
	}	
	
	public static LabeldataColumns loadDHLGlobleMailPTST(String strPostcode,
			String strProduct) throws Exception {
		LabeldataQuery objLabeldataQuery = new LabeldataQuery();
		objLabeldataQuery.setProducts(strProduct);
		objLabeldataQuery.setCode(strPostcode);
		objLabeldataQuery.setUseCachesign(true);
		List listResults = objLabeldataQuery.getResults();
		if (listResults == null || listResults.size() < 1)
			return null;
		return (LabeldataColumns)listResults.get(0);
	}
	
	public static WaybillforpackageColumns loadForPackage(String strCwcode) throws Exception {
		WaybillforpackageCondition objWFPC = new WaybillforpackageCondition();
		objWFPC.setCwcode(strCwcode);
		List listResults = queryForPackage(objWFPC);
		if (listResults == null || listResults.size() < 1)
			return null;
		return (WaybillforpackageColumns)listResults.get(0);
	}	
	
	public static HousewaybillColumns buildHousewaybillByPackage(WaybillforpackageColumns objWFPColumns,
			String strAdtcode) {
		HousewaybillColumns objHwColumns = new HousewaybillColumns();
		if (strAdtcode.equals("A"))
			objHwColumns.setAbwbwcode(Long.parseLong(objWFPColumns.getBwbwcode()));
		else
			objHwColumns.setDbwbwcode(Long.parseLong(objWFPColumns.getBwbwcode()));
		
		objHwColumns.setCwcwbagcounts(Integer.parseInt(objWFPColumns.getCwcwbagcounts()));
		objHwColumns.setCwcwbillcounts(Integer.parseInt(objWFPColumns.getCwcwbillcounts()));
		objHwColumns.setCwcwgrossweight(new BigDecimal(objWFPColumns.getCwcwchargeweight()));
		objHwColumns.setCtctcode("AWPX");
		objHwColumns.setPmpmcode("APP");
		
		if (!StringUtility.isNull(objWFPColumns.getCwcwcode()))
			objHwColumns.setHwcwcode(Long.parseLong(objWFPColumns.getCwcwcode()));
		
		objHwColumns.setCwcwcustomerewbcode(objWFPColumns.getCwcwcustomerewbcode());
		objHwColumns.setCwcwpieces(Integer.parseInt(objWFPColumns.getCwcwpieces()));
		
		String strDtcode = objWFPColumns.getDdtdtcode();
		if (StringUtility.isNull(strDtcode))
			strDtcode = objWFPColumns.getDtsignindtcode();
		if (StringUtility.isNull(strDtcode))
			strDtcode = "1";
		objHwColumns.setDdtdtcode(strDtcode);
		objHwColumns.setSdtdtcode(strDtcode);
		objHwColumns.setEeeecode(objWFPColumns.getEeeecode());
		objHwColumns.setPkpkcode(objWFPColumns.getPkpkcode());
		
		return objHwColumns;
	}
	
	public static String getLabelConsignments(String strCwcode) throws Exception {
		HousewaybillremarkQuery objHRQ = new HousewaybillremarkQuery();
		objHRQ.setIncwcode(strCwcode);
		List listRecords = objHRQ.getResults();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < listRecords.size(); i++) {
			HousewaybillremarkColumns objHRC = (HousewaybillremarkColumns)listRecords.get(i);
			String strConsignment = objHRC.getHwhw_remark();
			if (!StringUtility.isNull(strConsignment) &&
					strConsignment.indexOf("consignments") >= 0) {
				int iIndex = strConsignment.indexOf("consignments") + "consignments=".length();
				strConsignment = strConsignment.substring(iIndex);
			}
			sb.append(strConsignment + ",");
		}
		String strLabelConsignments = sb.toString();
		if (strLabelConsignments.endsWith(","))
			strLabelConsignments = strLabelConsignments.substring(0, strLabelConsignments.length() - 1);
		return strLabelConsignments;
	}
	
	public static void buildInputToService(ForinputallColumns objFIAColumns, 
			List listWaybillPieces) throws Exception {
		objFIAColumns.setHwremark("");
		objFIAColumns.setCwcode(null);
		objFIAColumns.setCocode(SystempropertyDemand.getPreictmapping(objFIAColumns.getEecode(),
				"BQ",
				"ES"));
		objFIAColumns.setCocode_Cwcus(objFIAColumns.getCocode());
		objFIAColumns.setPk_code(SystempropertyDemand.getPreictmapping(objFIAColumns.getChncode_Cwspchn(),
				"BQ",
				"PT"));
		objFIAColumns.setEecode("2");
		objFIAColumns.setChncode("");
		objFIAColumns.setChncode_Cwcuschn("");
		objFIAColumns.setServerwbckbckcode("");
		objFIAColumns.setHwhwserverewbchangedsign("");	
		objFIAColumns.setHWSEWBChangedByWebSign("");
		objFIAColumns.setSubwbckbckcode("");		
		objFIAColumns.setBwcode_Cwabm(null);
		objFIAColumns.setBwcode(null);
		objFIAColumns.setBwcode_Cwdbm(null);
		objFIAColumns.setCwgrossweight(new BigDecimal(objFIAColumns.getCwserverchargeweight()));
		objFIAColumns.setCwchargeweight(new BigDecimal(objFIAColumns.getCwserverchargeweight()));
		if (listWaybillPieces != null && listWaybillPieces.size() > 0) {
			for (int i = 0; i < listWaybillPieces.size(); i++) {
				CorewaybillpiecesColumns objCWPColumns = (CorewaybillpiecesColumns)listWaybillPieces.get(i);
				if (i == 0) {
					objCWPColumns.setCpcpgrossweight(new BigDecimal(objFIAColumns.getCwserverchargeweight()));
				} else {
					objCWPColumns.setCpcpgrossweight(new BigDecimal("0"));
				}
				objCWPColumns.setCpcpheight(new BigDecimal("0"));
				objCWPColumns.setCpcpwidth(new BigDecimal("0"));
				objCWPColumns.setCpcplength(new BigDecimal("0"));
			}
		}
	}
	
	public static void buildSavedFromService(ForinputallColumns objFIAColumns,
			ForinputallColumns objInputFIAColumns) {
		objFIAColumns.setCwserverewbcode(objInputFIAColumns.getCwserverewbcode());
		objFIAColumns.setHwhwserverewbchangedsign(objInputFIAColumns.getHwhwserverewbchangedsign());	
		objFIAColumns.setHWSEWBChangedByWebSign(objInputFIAColumns.getHWSEWBChangedByWebSign());	
		objFIAColumns.setPaymentaccount(objInputFIAColumns.getPaymentaccount());
		objFIAColumns.setMasteraccount(objInputFIAColumns.getMasteraccount());
		objFIAColumns.setHwlabelprinttimes(0);
		if (!StringUtility.isNull(objFIAColumns.getHwremark()) && 
				objFIAColumns.getHwremark().length() > 50)
			objFIAColumns.setHwremark("");
		objFIAColumns.setHwremark(StringUtility.replaceWhenNull(objFIAColumns.getHwremark(), "") + ";成功上传数据" + objInputFIAColumns.getHwremark());
	}	
	
	@SuppressWarnings("unchecked")
	public static List queryDhlConnect(Dhlconnect4Condition objDhlconnect4Condition) 
	throws Exception{
		 Dhlconnect4Query objDhlconnect4Query=new Dhlconnect4Query();
		 objDhlconnect4Query.setCondition(objDhlconnect4Condition);
		 return objDhlconnect4Query.getResults();
	}
	
	public static List querySobagstatistic(SobagstatisticCondition objSBSCondition) throws Exception {
		SobagstatisticQueryEX objSQEX = new SobagstatisticQueryEX(objSBSCondition);
		return objSQEX.getResults();
	}
	
}
