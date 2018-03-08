package kyle.leis.eo.operation.housewaybill.sv;

import java.util.ArrayList;
import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import kyle.leis.eo.customerservice.issue.da.IssueColumns;
import kyle.leis.eo.customerservice.track.bl.Track;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.corewaybill.bl.Corewaybill;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybill.dax.ModifyChargeweightColumns;
import kyle.leis.eo.operation.corewaybill.dax.ModifyCustomerWaybillcodeColumns;
import kyle.leis.eo.operation.corewaybill.dax.ModifyHawbWaybillcodeColumns;
import kyle.leis.eo.operation.corewaybill.dax.ModifyServerChargeweightColumns;
import kyle.leis.eo.operation.corewaybill.dax.ModifyServerWaybillcodeColumns;
import kyle.leis.eo.operation.corewaybill.sv.CorewaybillDelegate;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.housewaybill.bl.BatchHousewaybill;
import kyle.leis.eo.operation.housewaybill.bl.Housewaybill;
import kyle.leis.eo.operation.housewaybill.bl.Input;
import kyle.leis.eo.operation.housewaybill.bl.SignIn;
import kyle.leis.eo.operation.housewaybill.bl.SignOut;
import kyle.leis.eo.operation.housewaybill.da.Dhlconnect4Condition;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillforcustomsCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillforklexCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillformanifestCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillforreportCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillfortarazzCondition;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.da.PredictwaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.SobagstatisticCondition;
import kyle.leis.eo.operation.housewaybill.da.SumweightcheckweightCondition;
import kyle.leis.eo.operation.housewaybill.da.WaybillforauditCondition;
import kyle.leis.eo.operation.housewaybill.da.WaybillforbillCondition;
import kyle.leis.eo.operation.housewaybill.da.WaybillfordcustomerCondition;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpackageColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpackageCondition;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictCondition;
import kyle.leis.eo.operation.housewaybill.dax.DHLConnectImportColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;

public class HousewaybillService extends AService {
	/**
	 * 录单查询
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryInputAll(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);	

		ForinputallCondition objFInputAllC = (ForinputallCondition)objPD.getParameter(0, ForinputallCondition.class);
		String strEestructurecode = (String)objPD.getParameter(1, String.class);
		InputAllQReturn objIAQR = HousewaybillDemand.queryInput(objFInputAllC, strEestructurecode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objIAQR.getHWBResults());
		objEncode.addParameter(objIAQR.getCargoInfoResults());
		return objEncode.toString();		
	}
	
	/**
	 * 普通查询
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);	
		
		HousewaybillCondition objHWBCondition = (HousewaybillCondition)objPD.getParameter(0, 
				HousewaybillCondition.class);
		if (StringUtility.isNull(objHWBCondition.getCwbatchwaybillsign()))
			objHWBCondition.setCwbatchwaybillsign("N");
		if (!StringUtility.isNull(objHWBCondition.getCwcode()))
			objHWBCondition.setCwbatchwaybillsign("");
		
		List objList = HousewaybillDemand.query(objHWBCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}
	
	/**
	 * 运单查询统计
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryForreport(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);	
		
		HousewaybillforreportCondition objHWBFRCondition = (HousewaybillforreportCondition)objPD.getParameter(0, 
				HousewaybillforreportCondition.class);
		List objList = HousewaybillDemand.queryForreport(objHWBFRCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}
	
	/**
	 * 超值报关
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryForcustoms(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);	
		
		HousewaybillforcustomsCondition objHWBFCCondition = (HousewaybillforcustomsCondition)objPD.getParameter(0, 
				HousewaybillforcustomsCondition.class);
		List objList = HousewaybillDemand.queryForcustoms(objHWBFCCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		
		return objEncode.toString();		
	}	
	
	
	/**
	 * 直客交款时查询运单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryForDirectCustomer(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);	
		
		WaybillfordcustomerCondition objWFDCondition = (WaybillfordcustomerCondition)objPD.getParameter(0, 
				WaybillfordcustomerCondition.class);
		List objList = HousewaybillDemand.queryForDirectCustomer(objWFDCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}	
	
	/**
	 * 调整费用中的运单查询
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryForBill(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);	
		
		WaybillforbillCondition objWBFBCondition = (WaybillforbillCondition)objPD.getParameter(0, 
				WaybillforbillCondition.class);
		List objList = HousewaybillDemand.queryForBill(objWBFBCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
		// return "";
	}
	
	public String queryForAudit(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);	
		
		WaybillforauditCondition objWFACondition = (WaybillforauditCondition)objPD.getParameter(0, 
				WaybillforauditCondition.class);
		List objList = HousewaybillDemand.queryForAudit(objWFACondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}
	
	public String queryForPredict(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);	
		
		WaybillforpredictCondition objWFPCondition = (WaybillforpredictCondition)objPD.getParameter(0, 
				WaybillforpredictCondition.class);
		List objList = HousewaybillDemand.queryForPredict(objWFPCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}
	
	public String queryFormanifest(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);	
		
		HousewaybillformanifestCondition objHFMCondition = (HousewaybillformanifestCondition)objPD.getParameter(0, 
				HousewaybillformanifestCondition.class);
		if (StringUtility.isNull(objHFMCondition.getCwbatchwaybillsign()))
			objHFMCondition.setCwbatchwaybillsign("N");
		if (!StringUtility.isNull(objHFMCondition.getCwcode()))
			objHFMCondition.setCwbatchwaybillsign("");		
		
		List objList = HousewaybillDemand.queryFormanifest(objHFMCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}		
	
	
	public String queryForklex(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);	
		
		HousewaybillforklexCondition objHFKC = (HousewaybillforklexCondition)objPD.getParameter(0, 
				HousewaybillforklexCondition.class);
		if (StringUtility.isNull(objHFKC.getCwbatchwaybillsign()))
			objHFKC.setCwbatchwaybillsign("N");
		if (!StringUtility.isNull(objHFKC.getCwcode()))
			objHFKC.setCwbatchwaybillsign("");		
		
		List objList = HousewaybillDemand.queryForKlex(objHFKC);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}		
	
	public String queryForPackage(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);	
		
		WaybillforpackageCondition objWFPC = (WaybillforpackageCondition)objPD.getParameter(0, 
				WaybillforpackageCondition.class);
		List objList = HousewaybillDemand.queryForPackage(objWFPC);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}	
	
	
	public String queryForTarazz(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);	
		
		HousewaybillfortarazzCondition objHWBFTCondition = (HousewaybillfortarazzCondition)objPD.getParameter(0, 
				HousewaybillfortarazzCondition.class);
		List objList = HousewaybillDemand.queryForTarazz(objHWBFTCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}	
	
	
	/**
	 * 装载正常件
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String loadNormalCollection(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		
		String strEwbcode = (String)objPD.getParameter(0, String.class);
		String strEwbcodeType = (String)objPD.getParameter(1, String.class);
		String strEecode = (String)objPD.getParameter(2, String.class);
		List objList = HousewaybillDemand.loadNormalCollection(strEwbcode, strEwbcodeType, strEecode);
		List<HousewaybillColumns> listResults = new ArrayList<HousewaybillColumns>();
		if (objList != null && objList.size() > 0) {
			for (int i = 0; i < objList.size(); i++) {
				HousewaybillColumns objHWC = (HousewaybillColumns)objList.get(i);
				if (objHWC.getCwscwscode().equals("CHD") ||
						objHWC.getCwscwscode().equals("CTS"))
					continue;
				listResults.add(objHWC);
			}
		}
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();		
	}
	
	/**
	 * 收货
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String signIn(Decoder objPD) throws Exception {
		//checkParameterCount(objPD, 6, this);	
		
		HousewaybillColumns objHwColumns = (HousewaybillColumns)objPD.getParameter(0, 
				HousewaybillColumns.class);
		List listCorewaybillpieces = objPD.getParameterList(1, CorewaybillpiecesColumns.class);
		List listIssueColumns = objPD.getParameterList(2, IssueColumns.class);
		String strOperId = (String)objPD.getParameter(3, String.class);
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(4, String.class));
		String strReceivableAmount = (String)objPD.getParameter(5, String.class);
		
		SignIn objSignIn = new SignIn();
		if (objPD.getParameterCount() > 6) {
			List listRvColumns = objPD.getParameterList(6, ReceivableColumns.class);
			SavedResultUtility objSRUtility = objSignIn.save(objHwColumns, 
					listCorewaybillpieces, 
					listIssueColumns, 
					strOperId, 
					strReceivableAmount,
					isIgnoreNotice,
					listRvColumns);
			return objSRUtility.toString();			
		} else {
			SavedResultUtility objSRUtility = objSignIn.save(objHwColumns, 
					listCorewaybillpieces, 
					listIssueColumns, 
					strOperId, 
					strReceivableAmount,
					isIgnoreNotice);
			return objSRUtility.toString();
		}
	}
	
	/**
	 * 小包业务保存
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String savePackage(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 5, this);	
		
		WaybillforpackageColumns objWFPColumns = (WaybillforpackageColumns)objPD.getParameter(0, 
				WaybillforpackageColumns.class);
		String strAdtcode = (String)objPD.getParameter(1, String.class);
		String strCwcode = (String)objPD.getParameter(2, String.class);
		String strOldBrvid = (String)objPD.getParameter(3, String.class);
		String strOperId = (String)objPD.getParameter(4, String.class);
		
		SignIn objSignIn = new SignIn();
		SavedResultUtility objSRUtility = objSignIn.save(objWFPColumns, 
				strAdtcode,
				strCwcode,
				strOldBrvid,
				strOperId);
		return objSRUtility.toString();
	}	
	
	
	/**
	 * 快件信息调整
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String modify(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 5, this);	
		
		HousewaybillColumns objHwColumns = (HousewaybillColumns)objPD.getParameter(0, 
				HousewaybillColumns.class);
		List listCorewaybillpieces = objPD.getParameterList(1, CorewaybillpiecesColumns.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		String strModifyRemark = (String)objPD.getParameter(3, String.class);
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(4, String.class));
		
		Housewaybill objHousewaybill = new Housewaybill();
		SavedResultUtility objSRUtility = objHousewaybill.modify(objHwColumns, 
				listCorewaybillpieces, 
				null, 
				strOperId,
				strModifyRemark,
				isIgnoreNotice);
		return objSRUtility.toString();
	}
	
	/**
	 * 修改件信息
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String modifyPieces(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);	
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		List listCorewaybillpieces = objPD.getParameterList(1, CorewaybillpiecesColumns.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		
		Housewaybill objHousewaybill = new Housewaybill();
		strCwcode = objHousewaybill.modifyPieces(strCwcode, 
				listCorewaybillpieces, 
				strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strCwcode);
		return objEncode.toString();
	}	
	
	
	/**
	 * 制单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String inputAll(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, 4, this);
		
		boolean isCheckRepeatCustomerewbcode = true;
		String strOperId = null;
		ForinputallColumns objFIAColumns = null;
		List<Object> listCargo = null;
		
		if (objPD.getParameterCount() == 3) {
			strOperId = (String)objPD.getParameter(0, String.class);
			objFIAColumns = (ForinputallColumns)objPD.getParameter(1, ForinputallColumns.class);		
			listCargo = objPD.getParameterList(2, CargoinfoColumns.class);
		} else if (objPD.getParameterCount() == 4) {
			strOperId = (String)objPD.getParameter(0, String.class);
			objFIAColumns = (ForinputallColumns)objPD.getParameter(1, ForinputallColumns.class);		
			listCargo = objPD.getParameterList(2, CargoinfoColumns.class);
			isCheckRepeatCustomerewbcode = Boolean.parseBoolean((String)objPD.getParameter(3, String.class));
		}
		if (objFIAColumns == null) return "";
		
		Input objHWBInput = new Input();
		InputAllQReturn objIAQR = objHWBInput.inputAll(strOperId, objFIAColumns, 
				listCargo, null, isCheckRepeatCustomerewbcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objIAQR.getHWBResults());
		objEncode.addParameter(objIAQR.getCargoInfoResults());
		if (objIAQR.getPromptUtilityCollection() != null) 
			objEncode.addParameter(objIAQR.getPromptUtilityCollection().toStringArray());
		return objEncode.toString();
	}
	
	public String inputAllForService(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);
			
		String strOperId = (String)objPD.getParameter(0, String.class);
		ForinputallColumns objFIAColumns = (ForinputallColumns)objPD.getParameter(1, ForinputallColumns.class);
		List listCargo = objPD.getParameterList(2, CargoinfoColumns.class);
		List listWaybillPieces = objPD.getParameterList(3, CorewaybillpiecesColumns.class);
		
		Input objHWBInput = new Input();
		InputAllQReturn objIAQR = objHWBInput.inputAllForService(strOperId, 
				objFIAColumns, 
				listCargo, 
				listWaybillPieces);
		Encoder objEncode = new Encoder();
		if (objIAQR.getPromptUtilityCollection() != null) 
			objEncode.addParameter(objIAQR.getPromptUtilityCollection().toStringArray());		
		objEncode.addParameter(objIAQR.getHWBResults());
		objEncode.addParameter(objIAQR.getPieces());
		return objEncode.toString();
	}
	
	/**
	 * 修改制单信息
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String modifyInputAll(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 6, this);	
		
		String strOperId = (String)objPD.getParameter(0, String.class);
		ForinputallColumns objFIAColumns = (ForinputallColumns)objPD.getParameter(1, ForinputallColumns.class);		
		List<Object> listCargo = objPD.getParameterList(2, CargoinfoColumns.class);
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(3, String.class));
		boolean isMainwaybillFromsystem = Boolean.parseBoolean((String)objPD.getParameter(4, String.class));
		boolean isReinputBywebservice = Boolean.parseBoolean((String)objPD.getParameter(5, String.class));
		
		Input objHWBInput = new Input();
		InputAllQReturn objIAQR = objHWBInput.modify(strOperId, 
				objFIAColumns, 
				listCargo, 
				isIgnoreNotice,
				isMainwaybillFromsystem,
				isReinputBywebservice);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objIAQR.getHWBResults());
		objEncode.addParameter(objIAQR.getCargoInfoResults());
		if (objIAQR.getPromptUtilityCollection() != null) 
			objEncode.addParameter(objIAQR.getPromptUtilityCollection().toStringArray());
		return objEncode.toString();
	}
	
	/**
	 * 出货
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String SignOut(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 9, this);	
		
		String strBwcodeDeparture = (String)objPD.getParameter(0, String.class);
		String strCwServerewbcode = (String)objPD.getParameter(1, String.class);
		String strBagLabelcode = (String)objPD.getParameter(2, String.class);
		String strOperId = (String)objPD.getParameter(3, String.class);
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(4, String.class));
		String strSotcode = (String)objPD.getParameter(5, String.class);
		boolean isArrearAllowSignout = Boolean.parseBoolean((String)objPD.getParameter(6, String.class));
		String strSelfLabelcode = (String)objPD.getParameter(7, String.class);
		boolean isSOByServerLabelcode = Boolean.parseBoolean((String)objPD.getParameter(8, String.class)); 
		SignOut objSignOut = new SignOut();
		SavedResultUtility objSRUtility = objSignOut.save(strBwcodeDeparture, 
				strCwServerewbcode, 
				strBagLabelcode, 
				strOperId, 
				isIgnoreNotice,
				isArrearAllowSignout,
				strSotcode,
				strSelfLabelcode,
				isSOByServerLabelcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objSRUtility.getPromptUtilityCollection().toStringArray());
		objEncode.addParameter(objSRUtility.getColumns());
		return objEncode.toString();
	}
	
	/**
	 * 撤销出货
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String undoSignOut(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);
		
		String strCwcode = (String)objPD.getParameter(0, String.class); 
		String strOperId = (String)objPD.getParameter(1, String.class);
		String strRemark = (String)objPD.getParameter(2, String.class);
		boolean bResetChangeEWBSign = Boolean.parseBoolean((String)objPD.getParameter(3, String.class));
		
		Housewaybill objHousewaybill = new Housewaybill();
		objHousewaybill.undoSignout(strCwcode, 
				strOperId, 
				strRemark,
				bResetChangeEWBSign);
		
		return "";
	}
	
	
	/**
	 * 获得体积重
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String getVolumeWeight(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);	
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		String strVolumeweight = CorewaybillDemand.getVolumeweight(strCwcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strVolumeweight);
		return objEncode.toString();	
	}
	
	/**
	 * 打印标签
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String printLabel(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);	
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		String strLabelPrintRemark = (String)objPD.getParameter(2, String.class);
		
		Housewaybill objHousewaybill = new Housewaybill();
		objHousewaybill.printLabel(strCwcode, strLabelPrintRemark, strOperId);
		
		return "";
	}
	
	/**
	 * 打印发票
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String printCargo(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);	
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		Housewaybill objHousewaybill = new Housewaybill();
		objHousewaybill.printCargo(strCwcode, strOperId);
		
		return "";
	}
	
	/**
	 * 修改服务渠道
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String modifyServerChannel(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);	
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		String strServerChannel = (String)objPD.getParameter(1, String.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		
		Housewaybill objHousewaybill = new Housewaybill();
		objHousewaybill.modifyServerChannel(strCwcode, strServerChannel, strOperId);
		
		return "";
	}	
	
	
	/**
	 * 作废运单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String eliminate(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);	
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		Housewaybill objHousewaybill = new Housewaybill();
		PromptUtilityCollection objPUC = objHousewaybill.eliminate(strCwcode, strOperId);

		Encoder objEncode = new Encoder();
		objEncode.addParameter(objPUC.toStringArray());
		return objEncode.toString();
	}
	
	/**
	 * 批量收货出货
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String batchSignInSignout(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 8, this);	
		
		PredictwaybillColumns objPWBColumns = (PredictwaybillColumns)objPD.getParameter(0, 
				PredictwaybillColumns.class);
		List listCargoInfo = objPD.getParameterList(1, CargoinfoColumns.class);
		String strChncode = (String)objPD.getParameter(2, String.class);
		String strBwcodeDeparture = (String)objPD.getParameter(3, String.class);
		String strOperId = (String)objPD.getParameter(4, String.class);
		boolean isAutoSignout = Boolean.parseBoolean((String)objPD.getParameter(5, String.class));
		boolean isPrealertSign = Boolean.parseBoolean((String)objPD.getParameter(6, String.class));
		String strEecode = (String)objPD.getParameter(7, String.class);
		
		BatchHousewaybill objBatchHousewaybill = new BatchHousewaybill();
		PromptUtilityCollection objPUC = objBatchHousewaybill.signInSignOut(objPWBColumns,
				listCargoInfo,
				strChncode, 
				strBwcodeDeparture, 
				strOperId,
				isAutoSignout,
				isPrealertSign,
				strEecode);
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objPUC.toStringArray());
		return objEncode.toString();		
	}
	
	/**
	 * 调整收货主单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String modifyArrivalBatchwaybill(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);	
		
		String[] astrCwcode = (String[])objPD.getParameterArray(0, String.class);
		String strBwcodeDest = (String)objPD.getParameter(1, String.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		
		Corewaybill objCorewaybill = new Corewaybill();
		objCorewaybill.modifyArrivalBatchwaybill(astrCwcode, strBwcodeDest, strOperId);
		return "";
	}	
	
	/**
	 * 修改出货主单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String modifyDepartureBatchwaybill(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);	
		
		String[] astrCwcode = (String[])objPD.getParameterArray(0, String.class);
		String strBwcodeDest = (String)objPD.getParameter(1, String.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		
		Corewaybill objCorewaybill = new Corewaybill();
		objCorewaybill.modifyDepartureBatchwaybill(astrCwcode, strBwcodeDest, strOperId);
		return "";
	}	
	
	
	public String simpleBQInput(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);	
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		String strNewServerEwbcode = (String)objPD.getParameter(1, String.class); 
		String strOperId = (String)objPD.getParameter(2, String.class);
		
		Corewaybill objCorewaybill = new Corewaybill();
		objCorewaybill.simpleBQInput(strCwcode, strNewServerEwbcode, strOperId);
		return "";
	}	
	
	
	public String modifyServerWaybillcode(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);	
		
		List listMSWCColumns = objPD.getParameterList(0, ModifyServerWaybillcodeColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		Corewaybill objCorewaybill = new Corewaybill();
		objCorewaybill.modifyServerWaybillcode(listMSWCColumns, strOperId);
		return "";
	}	
	
	public String modifyHawbWaybillcode(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);	
		
		List listMHWCColumns = objPD.getParameterList(0, ModifyHawbWaybillcodeColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		Corewaybill objCorewaybill = new Corewaybill();
		objCorewaybill.modifyHawbWaybillcode(listMHWCColumns, strOperId);
		return "";
	}	
	
	
	public String modifyCustomerWaybillcode(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);	
		
		List listMSWCColumns = objPD.getParameterList(0, ModifyCustomerWaybillcodeColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		Corewaybill objCorewaybill = new Corewaybill();
		objCorewaybill.modifyCustomerWaybillcode(listMSWCColumns, strOperId);
		return "";
	}		
	
	
	public String modifyTransactionID(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);	
		
		List listMSWCColumns = objPD.getParameterList(0, ModifyServerWaybillcodeColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		Corewaybill objCorewaybill = new Corewaybill();
		objCorewaybill.modifyTransactionID(listMSWCColumns, strOperId);
		return "";
	}	
	
	
	public String modifyServerchargeweight(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);	
		
		List listServerchargeweightColumns = objPD.getParameterList(0, ModifyServerChargeweightColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		Corewaybill objCorewaybill = new Corewaybill();
		objCorewaybill.modifyServerchargeweight(listServerchargeweightColumns, strOperId);
		return "";
	}
	
	public String modifyChargeweight(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);	
		
		List listModifyChargeweightColumns = objPD.getParameterList(0, ModifyChargeweightColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		Corewaybill objCorewaybill = new Corewaybill();
		objCorewaybill.modifyChargeweight(listModifyChargeweightColumns, strOperId);
		return "";
	}	

	
	
	/**
	 * 同步运单信息
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String syncHousewaybill(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);	
		
		ForinputallColumns objFIAColumns = (ForinputallColumns)objPD.getParameter(0, 
				ForinputallColumns.class);
		List listCargoInfo = objPD.getParameterList(1, CargoinfoColumns.class);
		List listCWPieces = objPD.getParameterList(2, CorewaybillpiecesColumns.class);
		String strOperId = (String)objPD.getParameter(3, String.class);
		
		BatchHousewaybill objBatchHousewaybill = new BatchHousewaybill();
		PromptUtilityCollection objPUC = objBatchHousewaybill.syncHousewaybill(objFIAColumns, 
				listCargoInfo, 
				listCWPieces, 
				strOperId);
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objPUC.toStringArray());
		return objEncode.toString();			
	}
	
	/**
	 * 制单并出货
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String simpleInputSignout(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 5, this);	
		
		ForinputallColumns objFIAColumns = (ForinputallColumns)objPD.getParameter(0, 
				ForinputallColumns.class);
		List listCargoInfo = objPD.getParameterList(1, CargoinfoColumns.class);
		boolean isAutoSignout = Boolean.parseBoolean((String)objPD.getParameter(2, String.class));
		String strOperId = (String)objPD.getParameter(3, String.class);
		boolean isArrearAllowSignout = Boolean.parseBoolean((String)objPD.getParameter(4, String.class));
		
		BatchHousewaybill objBatchHousewaybill = new BatchHousewaybill();
		PromptUtilityCollection objPUC = objBatchHousewaybill.simpleInputSignout(objFIAColumns, 
				listCargoInfo, 
				isAutoSignout,
				isArrearAllowSignout,
				strOperId);
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objPUC.toStringArray());
		return objEncode.toString();			
	}	
	
	/**
	 * 撤销退件
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String UndoBackprice(Decoder objPD) throws Exception {
		checkParameterCount(objPD,2,this);
		
		String strCwCode = (String)objPD.getParameter(0,String.class);		
		String strOperId = (String)objPD.getParameter(1,String.class);		
		Housewaybill objHousewaybill = new Housewaybill();
		objHousewaybill.undoBackprice(strCwCode,
				strOperId);
		
		return "";
	}
	
	/**
	 * 撤销成已打印状态
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String undoToCustomerPrint(Decoder objPD) throws Exception {
		checkParameterCount(objPD,2,this);
		
		String strCwCode = (String)objPD.getParameter(0,String.class);		
		String strOperId = (String)objPD.getParameter(1,String.class);	
		
		Housewaybill objHousewaybill = new Housewaybill();
		objHousewaybill.undoToCustomerPrint(strCwCode,
				strOperId);
		
		return "";
	}
	
	/**
	 * 核查重量
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String checkWeight(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 6, this);
		
		HousewaybillColumns objHwColumns = (HousewaybillColumns)objPD.getParameter(0,HousewaybillColumns.class);
		List listCorewaybillpieces = (List)objPD.getParameterList(1, CorewaybillpiecesColumns.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		String strEecode = (String)objPD.getParameter(3, String.class);
		boolean isHold = Boolean.parseBoolean((String)objPD.getParameter(4, String.class));
		String strWeightCheckBWCode = (String)objPD.getParameter(5, String.class);
		
		Track objTrack = new Track();
		String strDate = DateFormatUtility.getStandardSysdate();
		objTrack.addSingleTrack(objHwColumns.getHwcwcode(), 
				TdiEnterpriseelementDC.loadByKey(strEecode).getTdiDistrict().getDtCode(), 
				"AFF", 
				strOperId, 
				strDate);
		
		Housewaybill objHousewaybill = new Housewaybill();
		String strResult = objHousewaybill.checkWeight(objHwColumns,
				listCorewaybillpieces,
				strEecode,
				strOperId,
				isHold,
				strWeightCheckBWCode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strResult);
		return objEncode.toString();			
	}
	
	
	public String sumWeightcheckweight(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		SumweightcheckweightCondition objSCWC = (SumweightcheckweightCondition)objPD.getParameter(0, SumweightcheckweightCondition.class);
		List listResults = HousewaybillDemand.sumWeightcheckweight(objSCWC);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}
	
	public String getDHLGlobeWaybillcode(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strBCK_Code = (String)objPD.getParameter(0, String.class);
		CorewaybillDelegate cwd = new CorewaybillDelegate();
		List<String> listResults = cwd.getDHLChildLabelcode(strBCK_Code, "0", 1);
		
		String strDHLGlobeWaybillcode = "";
		if (listResults != null && listResults.size() > 0)
			strDHLGlobeWaybillcode = listResults.get(0);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strDHLGlobeWaybillcode);
		return objEncode.toString();
	}
	
	public String getLatestcreatedate(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strCocode = (String)objPD.getParameter(0, String.class);
		String strLatestcreatedate = HousewaybillDemand.getLatestcreatedate(strCocode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strLatestcreatedate);
		return objEncode.toString();
	}	
	
	public String getLabelConsignments(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		String strConsignments = HousewaybillDemand.getLabelConsignments(strCwcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strConsignments);
		return objEncode.toString();
	}
	
	public String queryForDHLConnect(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		Dhlconnect4Condition objDhlconnect4Condition = (Dhlconnect4Condition)objPD.getParameter(0, Dhlconnect4Condition.class);
		List listResults = HousewaybillDemand.queryDhlConnect(objDhlconnect4Condition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}	
	
	public String querySobagstatistic(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		SobagstatisticCondition objSBSCondition = (SobagstatisticCondition)objPD.getParameter(0, SobagstatisticCondition.class);
		List listResults = HousewaybillDemand.querySobagstatistic(objSBSCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}		
	
	
	public String saveCNDHLImportData(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		List listDHLConnectImport = objPD.getParameterList(0, DHLConnectImportColumns.class);
		String strOperID = (String)objPD.getParameter(1, String.class);
		
		Housewaybill objHousewaybill = new Housewaybill();
		String strResults = objHousewaybill.saveCNDHLImportData(listDHLConnectImport, strOperID);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strResults);
		return objEncode.toString();
	}	
	
	public String getEUBPrinturl(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		List listDHLConnectImport = objPD.getParameterList(0, DHLConnectImportColumns.class);
		String strOperID = (String)objPD.getParameter(1, String.class);
		
		Housewaybill objHousewaybill = new Housewaybill();
		String strResults = objHousewaybill.saveCNDHLImportData(listDHLConnectImport, strOperID);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strResults);
		return objEncode.toString();
	}	
}
