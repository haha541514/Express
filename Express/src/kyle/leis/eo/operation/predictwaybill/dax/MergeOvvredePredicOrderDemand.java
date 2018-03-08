package kyle.leis.eo.operation.predictwaybill.dax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.ObjectGenerator;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtility;
import kyle.leis.eo.billing.calculate.chargeweight.bl.Chargeweight;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightParameter;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightResult;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateParameter;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.channelsearch.bl.ChannelSearch;
import kyle.leis.eo.operation.channelsearch.dax.ChannelSearchResult;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillColumns;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.housewaybill.da.BuildewbcodeseqColumns;
import kyle.leis.eo.operation.housewaybill.da.BuildewbcodeseqQuery;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictCondition;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderColumnsEX;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeColumns;
import kyle.leis.es.company.shipperconsignee.dax.ShipperconsigneeDemand;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TemplatecolumnColumns;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.fs.dictionary.productkind.da.ProductkindColumns;
import kyle.leis.fs.dictionary.productkind.dax.ProductkindDemand;
import kyle.leis.hi.TdiProductkind;

public class MergeOvvredePredicOrderDemand {
	public static WaybillforpredictColumns loadExistsRecord(String strCocode, 
			PredictwaybillColumns objWFPC) throws Exception {
//		WaybillforpredictColumns objWFPC = objPOCEX.getWaybillforpredict();
		WaybillforpredictCondition objWFPCondition = new WaybillforpredictCondition();
		objWFPCondition.setCocodecustomer(strCocode);
		objWFPCondition.setCwcustomerewbcode(objWFPC.getPwbpwb_orderid());
		objWFPCondition.setIncwscode("CTS,CHD,CHP");
		List listResults = HousewaybillDemand.queryForPredict(objWFPCondition);
		if (listResults == null || listResults.size() < 1) {
			objWFPCondition.setCocodecustomer(strCocode);
			objWFPCondition.setCwcustomerewbcode("");
			objWFPCondition.setIncwscode("CTS,CHD,CHP");
			objWFPCondition.setHwconsigneename(objWFPC.getPwbpwb_consigneename());
			listResults = HousewaybillDemand.queryForPredict(objWFPCondition);
			if (listResults == null || listResults.size() < 1) {			
				return null;
			}
		}
		return ((WaybillforpredictColumns)listResults.get(0));		
	}
	
	public static void merge(PredictwaybillColumns pwbColumns,
			List<CargoinfoColumns> listCargoInfo) throws Exception {
	 // ��Ȼ���ڵ�Ԥ����CargoListΪ�գ�����Ϊ���ٸĶ�����ĳ��򣬴�PredictwaybillColumns
	//  ��ȡ��һ��CargoinfoColumns�����ŵ�CargoList����
		List<CargoinfoColumns> listSourceCargoInfo = new ArrayList<CargoinfoColumns>();
		BigDecimal objPieces = new BigDecimal(pwbColumns.getPwbpwb_cargopieces());
		BigDecimal objUnitprice = new BigDecimal(pwbColumns.getPwbpwb_cargoamount());
		BigDecimal objTotalprice = objUnitprice.multiply(objPieces).divide(new BigDecimal("1"), 2, 4);
		CargoinfoColumns cargoinfoColumns = new CargoinfoColumns();
		cargoinfoColumns.setCiciname(pwbColumns.getPwbpwb_cargoename());
		cargoinfoColumns.setCiciename(pwbColumns.getPwbpwb_cargoename());
		cargoinfoColumns.setCiciunitprice(objUnitprice);
		cargoinfoColumns.setCicipieces(objPieces.intValue());
		cargoinfoColumns.setCicitotalprice(objTotalprice);
		cargoinfoColumns.setCkckcode(pwbColumns.getPwbck_code());
		cargoinfoColumns.setCiciattacheinfo(pwbColumns.getPwbpwb_customremark());//attacheinfo==�����Ϣ=CustomRemark=�Զ���ǩ��
		listSourceCargoInfo.add(cargoinfoColumns);
//		List<CargoinfoColumns> listDestCargoInfo = listCargoInfo;
		merge(listSourceCargoInfo, listCargoInfo);
	} 
	public static void merge(List<CargoinfoColumns> listSourceCargoInfo,
			List<CargoinfoColumns> listDestCargoInfo) {
		if (listSourceCargoInfo == null || listSourceCargoInfo.size() < 1)
			return;
		if (listDestCargoInfo == null || listDestCargoInfo.size() < 1) {
			listDestCargoInfo = listSourceCargoInfo;
			return;
		}
		for (CargoinfoColumns objCargoinfoColumns : listSourceCargoInfo) {
			merge(objCargoinfoColumns, listDestCargoInfo);
		}
	}	
	
	private static void merge(CargoinfoColumns objSourceCC,
			List<CargoinfoColumns> listDestCargoInfo) {
		boolean isRepeat = false;
		for (CargoinfoColumns objCargoinfoColumns : listDestCargoInfo) {
			// Դ
			String strScCienme = StringUtility.replaceWhenNull(objSourceCC.getCiciename(),
					"");
			
			String strScAttacheinfo = StringUtility.replaceWhenNull(objSourceCC.getCiciattacheinfo(),
					"");
			// Ŀ��
			String strDestCienme = StringUtility.replaceWhenNull(objCargoinfoColumns.getCiciename(),
					"");
			String strDestAttacheinfo = StringUtility.replaceWhenNull(objCargoinfoColumns.getCiciattacheinfo(),
					"");
			if (strScCienme.equals(strDestCienme) &&
					strScAttacheinfo.equals(strDestAttacheinfo)) {
				int iSourcePieces = Integer.parseInt(objSourceCC.getCicipieces());
				int iPieces = Integer.parseInt(objCargoinfoColumns.getCicipieces());
				BigDecimal objUnitprice = new BigDecimal(objCargoinfoColumns.getCiciunitprice());
				BigDecimal objPieces = new BigDecimal(iSourcePieces + iPieces);
				BigDecimal objTotalprice = objUnitprice.multiply(objPieces).divide(new BigDecimal("1"), 2, 4);
				
				objCargoinfoColumns.setCicipieces(iSourcePieces + iPieces);
				objCargoinfoColumns.setCicitotalprice(objTotalprice);
				isRepeat = true;
			}
		}
		if (!isRepeat){
			/****************add by zzj********start*******/
			BigDecimal objUnitprice = new BigDecimal(objSourceCC.getCiciunitprice());
			BigDecimal objPieces = new BigDecimal(objSourceCC.getCicipieces());
			BigDecimal objTotalprice = objUnitprice.multiply(objPieces).divide(new BigDecimal("1"), 2, 4);
			objSourceCC.setCicitotalprice(objTotalprice);
			/****************add by zzj**********end******/
			listDestCargoInfo.add(objSourceCC);
		}
	}
	
	
	/**
	 * �����ݿ��м��ͻ��������Ƿ��ظ�
	 * @param strCocode
	 * @param objPOCEX
	 * @return
	 * @throws Exception
	 */
	private String checkRepeatCustomerEWB(String strCocode,
			PredictwaybillColumns objWFPC) throws Exception {
		
//		WaybillforpredictColumns objWFPC = objPOCEX.getWaybillforpredict();
		
		PromptUtility objPromptUtility = checkRepeatCustomerEWB(strCocode,
				objWFPC.getPwbpwb_orderid(),
				objWFPC.getPwbpwb_consigneename(),
				"");
		if (objPromptUtility == null)
			return "";
//		objPOCEX.setOpermode(objPromptUtility.getPromptCode());
		return objPromptUtility.getDescribtion();
	}
	
	public PromptUtility checkRepeatCustomerEWB(String strCocode,
			String strCustomerewbcode,
			String strConsigneename,
			String strConsigneecompany) throws Exception {
		PromptUtility objPromptUtility = null;
		SimplecorewaybillColumns objSCWC = CorewaybillDemand.loadSimpleCorewaybill(strCustomerewbcode, 
				strCocode, true);
		// �����������ͬ�Ķ��������ж��ռ����Ƿ�һ��
		if (objSCWC == null) {
			// Ҫ��
			WaybillforpredictCondition objWFPCondition = new WaybillforpredictCondition();
			objWFPCondition.setHwconsigneename(strConsigneename);
			objWFPCondition.setHwconsigneecompany(strConsigneecompany);
			objWFPCondition.setCocodecustomer(strCocode);
			objWFPCondition.setIncwscode("CTS");
			List listResults = HousewaybillDemand.queryForPredict(objWFPCondition);
			if (listResults != null && listResults.size() > 0) {
				objPromptUtility = new PromptUtility("SAVE|MERGE",
						"ϵͳ�Ѿ�������ͬ���ռ��˺��ռ��˹�˾��״̬Ϊ�ݴ�״̬",
						"");
				return objPromptUtility;
			}
			return objPromptUtility;
		}
		// �ͻ��Ѵ�ӡ��ǩ
		if (objSCWC.getCwcws_code().equals("CHP")) {
			objPromptUtility = new PromptUtility("OVVRIDE|HOLD",
					"ϵͳ�Ѿ�������ͬ�Ķ����Ų����Ѿ���ӡ��Label",
					"");
			return objPromptUtility;			
		}
		if (objSCWC.getCwcws_code().equals("CHD")) {
			objPromptUtility = new PromptUtility("OVVRIDE|MERGE",
					"ϵͳ�Ѿ�������ͬ�Ķ����Ų����Ѿ��걨",
					"");
			return objPromptUtility;				
		}		
		if (objSCWC.getCwcws_code().equals("SI") ||
				objSCWC.getCwcws_code().equals("IP") ||
				objSCWC.getCwcws_code().equals("SO")) {
			
			objPromptUtility = new PromptUtility("",
					"ϵͳ�Ѿ�������ͬ�Ķ����Ų�����˾��ȷ���ջ���ϵͳ���Ὣ�ⲿ�ֶ��������ϴ�",
					"");
			return objPromptUtility;				
		}
		if (objSCWC.getCwcws_code().equals("CTS")) {			
			String strOpermode = "OVVRIDE|MERGE";
			// Ҫ��
			WaybillforpredictColumns objExistsWFPC = HousewaybillDemand.loadForPredict(objSCWC.getCwcw_code());
			if (objExistsWFPC != null && 
					objExistsWFPC.getHwhw_consigneename() != null &&
					objExistsWFPC.getHwhw_consigneename().equals(strConsigneename) &&
					objExistsWFPC.getHwhw_consigneecompany() != null &&
					objExistsWFPC.getHwhw_consigneecompany().equals(strConsigneecompany)) {

				
				objPromptUtility = new PromptUtility(strOpermode,
						"ϵͳ�Ѿ�������ͬ�Ķ����Ŷ����ռ��������ռ��˹�˾Ҳ�ظ���ϵͳ�еĶ���Ϊ�Ƶ��ݴ�״̬",
						"");
				return objPromptUtility;					
			}
			else {
				objPromptUtility = new PromptUtility(strOpermode,
						"ϵͳ�Ѿ�������ͬ�Ķ����Ų���Ϊ�Ƶ��ݴ�״̬",
						"");
				return objPromptUtility;					
			}
		}
		return objPromptUtility;
	}	
	
	
	/**
	 * Ч��
	 * @param strCocode
	 * @param objPOCEX
	 * @param listStandardTemplate
	 * @return
	 * @throws Exception
	 */
	public String check(String strCocode,
			PredictwaybillColumns objPOCEX,
			List listStandardTemplate,
			boolean isOnlyCheckBase,
			String promptinfo) throws Exception {
		if (!checkBases(objPOCEX, listStandardTemplate,promptinfo)) {
			return "�޷�ͨ������Ч��";
		}
		String stPkcode = objPOCEX.getPkpk_code();
		if (StringUtility.isNull(stPkcode)) {
			return "�޷�ͨ������Ч�飬�߻�����Ϊ�ջ��޷�ӳ��";
		} else {
			// Ч���Ʒ
			List<ProductkindColumns> listProductKind = ProductkindDemand.getCanUseProduct(strCocode,
					"719",
					"1");
			boolean isContainPk = false;
			for (ProductkindColumns pkc : listProductKind) {
				// �����ϴ����������洫��pkcode��һ�£������ϴ���ʱ���õ��Ǽ�ƣ���������ֱ���õ���pkcode
				if (stPkcode.equals(pkc.getPkpkename()) || 
						stPkcode.equals(pkc.getPkpkcode())) {
					isContainPk = true;
					break;
				}
			}
			if (!isContainPk)
				return "�޷�ͨ������Ч�飬�߻�����������˾δ��ͨ���߻�����";
		}
		
		// Ч��ͻ����˵����루�����ţ�
		String strCustomerEwbcode = objPOCEX.getPwbpwb_orderid();
		if (StringUtility.isNull(strCustomerEwbcode))
			return "�ͻ��˵��Ż򶩵��Ų���Ϊ��";
		if (!isOnlyCheckBase)
			return checkRepeatCustomerEWB(strCocode, objPOCEX);
		else
			return "";
		
	}
	
	private boolean checkBases(PredictwaybillColumns objWFPC, 
			List listStandardTemplate,String promptinfo) throws Exception {
//		WaybillforpredictColumns objWFPC = objPOEX.getWaybillforpredict();
//		List<CargoinfoColumns> listCargoinfoColumns = objPOEX.getListCargoInfo();
		List<CargoinfoColumns> listCargoinfoColumns =new ArrayList<CargoinfoColumns>();
		
		BigDecimal objPieces = new BigDecimal(objWFPC.getPwbpwb_cargopieces());
		BigDecimal objUnitprice = new BigDecimal(objWFPC.getPwbpwb_cargoamount());
		BigDecimal objTotalprice = objUnitprice.multiply(objPieces).divide(new BigDecimal("1"), 2, 4);
		CargoinfoColumns cargoinfoColumns = new CargoinfoColumns();
		cargoinfoColumns.setCiciname(objWFPC.getPwbpwb_cargoename());
		cargoinfoColumns.setCiciename(objWFPC.getPwbpwb_cargoename());
		cargoinfoColumns.setCiciunitprice(objUnitprice);
		cargoinfoColumns.setCicipieces(objPieces.intValue());
		cargoinfoColumns.setCicitotalprice(objTotalprice);
		cargoinfoColumns.setCkckcode(objWFPC.getPwbck_code());
		cargoinfoColumns.setCiciattacheinfo(objWFPC.getPwbpwb_customremark());//attacheinfo==�����Ϣ=CustomRemark=�Զ���ǩ��
		listCargoinfoColumns.add(cargoinfoColumns);
		
		if (listCargoinfoColumns == null || listCargoinfoColumns.size() < 1) {
			promptinfo = "����Ҫ�л�����Ϣ";
			return false;
		}
		// Ч���Ƿ�Ϊ�պͳ���
		boolean isPassCheck = true;
		for (int i = 0; i < listStandardTemplate.size(); i++) {
			TemplatecolumnColumns objTC = (TemplatecolumnColumns)listStandardTemplate.get(i);
			String strColumnEname = objTC.getTctccolumnename();
			String strValue = "";
			if (objTC.getTctccolumntype().equals("W")) {
				strValue = ObjectGenerator.process("get" + strColumnEname, 
						objWFPC, 
						null);
				if (!checkBases(objTC, strValue, objWFPC,promptinfo))
					isPassCheck = false;				
			} else if (objTC.getTctccolumntype().equals("C") &&
					objTC.getTctccolumngroup().equals("1")) {
				for (int j = 0; j < listCargoinfoColumns.size(); j++) {
					CargoinfoColumns objCargoinfoColumns = listCargoinfoColumns.get(j);
					strValue = ObjectGenerator.process("get" + strColumnEname, 
							objCargoinfoColumns, 
							null);
					if (!checkBases(objTC, strValue, objWFPC,promptinfo))
						isPassCheck = false;					
				}
			}

		}
		return isPassCheck;
	}
	
	private boolean checkBases(TemplatecolumnColumns objTC,
			String strActualValue,
			PredictwaybillColumns objPOEX,String promptinfo) {
		if (objTC.getTctcallownullsign().equals("N") &&
				StringUtility.isNull(strActualValue)) {
			
			String strPromptInfo = objTC.getTctccolumnname() + "����Ϊ��";
			if (objTC.getTctccolumnename().equals("Cwdt_code_signin") ||
					objTC.getTctccolumnename().equals("Pkpk_code"))
				strPromptInfo = strPromptInfo + "�����޷�ӳ��";
			
			promptinfo=strPromptInfo;
			return false;
		}
		if ("Cwcw_customerewbcode".equals(objTC.getTctccolumnename()) &&
				strActualValue.indexOf(",") > 0) {
			String[] astr = strActualValue.split(",");
			for (int i = 0; i < astr.length; i++) {
				if (!checkLength(astr[0],
						objTC,
						objPOEX,
						promptinfo))
					return false;
			}
		} else {
			if (!checkLength(strActualValue,
				objTC,
				objPOEX,
				promptinfo))
				return false;		
		}
		return true;
	}
	
	private boolean checkLength(String strActualValue,
			TemplatecolumnColumns objTC,
			PredictwaybillColumns objPOEX,
			String promptinfo) {
		if (!StringUtility.isNull(strActualValue)) {
			int iMaxLength = Integer.parseInt(objTC.getTctcmaxlength());
			int iMinLength = Integer.parseInt(objTC.getTctcminlength());
			if (strActualValue.length() > iMaxLength) {
				promptinfo=(objTC.getTctccolumnname() + "��󳤶Ȳ��ܴ���" + iMaxLength);
				return false;
			}
			if (strActualValue.length() < iMinLength) {
				promptinfo = (objTC.getTctccolumnname() + "��С���Ȳ���С��" + iMinLength);
				return false;
			}				
		}
		return true;		
	}
	
	
	
	public static List rebuildWaybillpieces(List listWaybillPieces,
			String strCustomerChargeweight) {
		List<CorewaybillpiecesColumns> list = new ArrayList<CorewaybillpiecesColumns>();
		if (listWaybillPieces == null || listWaybillPieces.size() < 1)
			return list;
		for (int i = 0; i < listWaybillPieces.size(); i++) {
			CorewaybillpiecesColumns objCBPC = (CorewaybillpiecesColumns)listWaybillPieces.get(i);
			if (i == 0) {
				objCBPC.setCpcpgrossweight(new BigDecimal(strCustomerChargeweight));
			} else {
				objCBPC.setCpcpgrossweight(new BigDecimal("0"));
			}
			list.add(objCBPC);
		}
		return list;
	}	
	



	public static void setDefaultinfo(String strCocode,
			List<PredictOrderColumnsEX> listWaybill) throws Exception {
		for (PredictOrderColumnsEX objPOCEX : listWaybill) {
			String strScname = objPOCEX.getWaybillforpredict().getHwhw_shippername();
			if (StringUtility.isNull(strScname)) {
				ShipperconsigneeColumns objSCColumns = ShipperconsigneeDemand.loadByCustomer(strCocode);
				setShipperInfo(objPOCEX, objSCColumns);
			}
			if (StringUtility.isNull(objPOCEX.getWaybillforpredict().getHwhw_consigneecompany())) {
				objPOCEX.getWaybillforpredict().setHwhw_consigneecompany(".");
			}			
		}
	}
	
	public static void setShipperInfo(PredictOrderColumnsEX objPOCEX,
			ShipperconsigneeColumns objSCColumns) {
		objPOCEX.getWaybillforpredict().setHwhw_shipperaddress1(objSCColumns.getScscaddress1());
		objPOCEX.getWaybillforpredict().setHwhw_shipperaddress2(objSCColumns.getScscaddress2());
		objPOCEX.getWaybillforpredict().setHwhw_shipperaddress3(objSCColumns.getScscaddress3());
		objPOCEX.getWaybillforpredict().setHwhw_shippercompany(objSCColumns.getScsccompanyname());
		objPOCEX.getWaybillforpredict().setHwhw_shipperfax(objSCColumns.getScscfax());
		objPOCEX.getWaybillforpredict().setHwhw_shippername(objSCColumns.getScscname());
		objPOCEX.getWaybillforpredict().setHwhw_shipperpostcode(objSCColumns.getScscpostcode());
		objPOCEX.getWaybillforpredict().setHwhw_shippertelephone(objSCColumns.getScsctelephone());							
	}
	
	
	public static ForinputallColumns buildForinputAll(String strCocode,
			PredictwaybillColumns objWFPC) throws Exception {
//		WaybillforpredictColumns objWFPC = objPOCEX.getWaybillforpredict();
		ForinputallColumns objFIAColumns = new ForinputallColumns();
		ShipperconsigneeColumns objSCColumns = ShipperconsigneeDemand.loadByCustomer(strCocode);

		
		if (!StringUtility.isNull(objWFPC.getPwbpwb_consigneecity()))
			objWFPC.setPwbpwb_consigneecity(objWFPC.getPwbpwb_consigneecity().toUpperCase());
		
		if (!StringUtility.isNull(objWFPC.getPwbcw_code()))
			objFIAColumns.setCwcode(Long.parseLong(objWFPC.getPwbcw_code()));		
		
		objFIAColumns.setCwcustomerewbcode(objWFPC.getPwbpwb_orderid());	
		String strEwbcode = objWFPC.getPwbpwb_orderid(); 
		if (StringUtility.isNull(objWFPC.getPwbcw_code())) {
			String strPE = SystempropertyDemand.getEnterprise();
			if (!StringUtility.isNull(strPE) && strPE.startsWith("SLY"))
				strEwbcode = buildEwbcode();
			else
				strEwbcode = objWFPC.getPwbpwb_orderid();
			
			objFIAColumns.setCwserverewbcode(objWFPC.getPwbpwb_orderid());
			objFIAColumns.setCwewbcode(strEwbcode);
		} else {
			objFIAColumns.setCwserverewbcode(objWFPC.getPwbpwb_serverewbcode());
			objFIAColumns.setCwewbcode(objSCColumns.getCmcocode());			
		}
		
		objFIAColumns.setAdddate(DateFormatUtility.getSysdate());
		objFIAColumns.setCocode(strCocode);
		objFIAColumns.setCocode_Cwcus(strCocode);
		objFIAColumns.setEecode("1");
		objFIAColumns.setDtcode_Cwodt("719");
		// ���ò�Ʒ
		String strPkcode = "";
		ProductkindColumns objPKC = ProductkindDemand.queryBypkEname(objWFPC.getPkpk_code());
		if (objPKC != null)
			strPkcode = objPKC.getPkpkcode();
		if (StringUtility.isNull(strPkcode)) {
			objPKC = ProductkindDemand.queryBypkCode(objWFPC.getPkpk_code());
			if (objPKC != null)
				strPkcode = objPKC.getPkpkcode();
		}		
		if (StringUtility.isNull(strPkcode)) {
			strPkcode = "Y0001";
		}
		objFIAColumns.setPk_code(strPkcode);
		// �����ƺ�Ҫ����JL�ĳ������⣬��ʱ��XML��ת���������ǲ�����Ҫֱ�ӱ���
		// �������ֻ��ͨ���������ִ������ƷѶ�����Ҫ�ó��и�����ȥ�ҳ������ִ�����������
		// �������Ҫ����
		String strSignInDtcode = "";
		String strEe = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strEe) && strEe.startsWith("KLEX")) {
			String strCountryhubcode = objWFPC.getDtdt_code();
			strSignInDtcode = DistrictDemand.getDtcodeByDtename(strCountryhubcode,
					objWFPC.getPwbpwb_consigneecity());
		}
		if (StringUtility.isNull(strSignInDtcode)) {
			strSignInDtcode = DistrictDemand.getDtcodeByHubcode(objWFPC.getDtdt_code());
		}
		
		objFIAColumns.setSidtcode(strSignInDtcode);
		objFIAColumns.setDtcode(strSignInDtcode);
		if (!StringUtility.isNull(strEe) && strEe.startsWith("SBD")) {
			objFIAColumns.setDtcode(DistrictDemand.getNotCountryDtcodeByHubcode(DistrictDemand.getDHLHubcode(objWFPC.getDtdt_code(), 
					objWFPC.getPwbpwb_consigneepostcode())));
		}
		objFIAColumns.setHwDtcodeshipper("719");
		objFIAColumns.setCwscode("CHD");
		// �ͻ�������Ϊʵ��
		String strCw_grossweight = objWFPC.getPwbpwb_chargeweight();
		if (StringUtility.isNull(strCw_grossweight))
			strCw_grossweight = "0.5";
		// ��üƷ�����
		BigDecimal objFIACGrossweight = new BigDecimal(strCw_grossweight);
		objFIAColumns.setCwgrossweight(objFIACGrossweight);
		objFIAColumns.setCwcustomerchargeweight(objFIACGrossweight);
		objFIAColumns.setCwchargeweight(objFIACGrossweight);
		objFIAColumns.setCwserverchargeweight(objFIACGrossweight);
		objFIAColumns.setCwtransferchargeweight(objFIACGrossweight);
		objFIAColumns.setCwtransfergrossweight(objFIACGrossweight);
		objFIAColumns.setTransfervolumeweight(new BigDecimal("0"));
		
		if(objFIAColumns.getCtcode() == null || objFIAColumns.getCtcode().equals(""))
			objFIAColumns.setCtcode("AWPX");
		objFIAColumns.setTransfervolumerate(Integer.parseInt("5000"));
		objFIAColumns.setPmcode("APP");
		objFIAColumns.setCwpieces(1);
		
		//��������Ϣ
		objFIAColumns.setHwconsigneename(objWFPC.getPwbpwb_consigneename());
		// ������������
		objFIAColumns.setHwconsigneenameex(StringUtility.buildToByte(objWFPC.getPwbpwb_consigneename(), "utf-8", 512));
		
		objFIAColumns.setHwconsigneecompany("");
		objFIAColumns.setHwconsigneetelephone(objWFPC.getPwbpwb_consigneetel());
		objFIAColumns.setHwConsigneecity(objWFPC.getPwbpwb_consigneecity());
		// ��ַ��Ϣ
		String strConsigneeaddress = objWFPC.getPwbpwb_consigneeaddress1();
		if (!StringUtility.isNull(objWFPC.getPwbpwb_consigneeaddress2()) &&
				!objWFPC.getPwbpwb_consigneeaddress2().equals("."))
			strConsigneeaddress = strConsigneeaddress + " " + objWFPC.getPwbpwb_consigneeaddress2();
		
		if (!StringUtility.isNull(objWFPC.getPwbpwb_consigneecity()) &&
				!objWFPC.getPwbpwb_consigneecity().equals("."))
			strConsigneeaddress = strConsigneeaddress + " " + objWFPC.getPwbpwb_consigneecity();
		
		if (!StringUtility.isNull(objWFPC.getPwbpwb_consigneeaddressex()) &&
				!objWFPC.getPwbpwb_consigneeaddressex().equals("."))
			strConsigneeaddress = strConsigneeaddress + " " + objWFPC.getPwbpwb_consigneeaddressex();
		// ������������
		objFIAColumns.setHwconsigneeaddressex(StringUtility.buildToByte(strConsigneeaddress, "utf-8", 512));
		
		String strAddress1 = StringUtility.substring(strConsigneeaddress, 0, 60);
		String strAddress2 = StringUtility.substring(strConsigneeaddress, 60, 120);
		String strAddress3 = StringUtility.substring(strConsigneeaddress, 120, 180);
		
		if (strAddress2.equals(strConsigneeaddress))
			strAddress2 = ".";
		if (strAddress3.equals(strConsigneeaddress))
			strAddress3 = ".";
		objFIAColumns.setHwconsigneeaddress1(strAddress1);
		objFIAColumns.setHwconsigneeaddress2(strAddress2);
		objFIAColumns.setHwconsigneeaddress3(strAddress3);
		objFIAColumns.setCwpostcodedestination(objWFPC.getPwbpwb_consigneepostcode());
		objFIAColumns.setHwconsigneepostcode(objWFPC.getPwbpwb_consigneepostcode());
		// �ռ�����Ϣ
		String strShipperaddress = objSCColumns.getScscaddress1();
		strAddress1 = StringUtility.substring(strShipperaddress, 0, 30);
		strAddress2 = StringUtility.substring(strShipperaddress, 31, 60);
		strAddress3 = StringUtility.substring(strShipperaddress, 61, 90);
		if (strAddress2.equals(strShipperaddress))
			strAddress2 = ".";
		if (strAddress3.equals(strShipperaddress))
			strAddress3 = ".";

		
		objFIAColumns.setHwshipperaddress1(strAddress1);
		objFIAColumns.setHwshipperaddress2(strAddress2);
		objFIAColumns.setHwshipperaddress3(strAddress3);
		
		objFIAColumns.setHwshippercompany(objSCColumns.getScsccompanyname());
		objFIAColumns.setHwshipperfax(objSCColumns.getScscfax());
		objFIAColumns.setHwshippername(objSCColumns.getScscname());
		objFIAColumns.setHwshipperpostcode(objSCColumns.getScsccitycode());
		objFIAColumns.setHwshippertelephone(objSCColumns.getScsctelephone());
		objFIAColumns.setHwremark(objWFPC.getPwbpwb_customremark());
		objFIAColumns.setTransactionid(objWFPC.getPwbpwb_transactionid());
		objFIAColumns.setBuyerid(objWFPC.getPwbpwb_buyerid());
		
		return objFIAColumns;		
	}
	
	private static ChargeweightParameter transferToCWParameter(ForinputallColumns objFIC,
			List listWaybillPieces) 
	throws Exception {
		ChargeweightParameter objCWParameter = new ChargeweightParameter();
		
		objCWParameter.setDtcode(objFIC.getSidtcode());
		objCWParameter.setCocode(objFIC.getCocode());
		objCWParameter.setGrossWeight(objFIC.getCwgrossweight());
		objCWParameter.setPdcode(IExpressPriceBasicData.PRICEDOMAIN_SALES);
		objCWParameter.setPkcode(objFIC.getPk_code());
		objCWParameter.setPostcode(objFIC.getCwgrossweight());
		objCWParameter.setSearchDate(DateFormatUtility.getStandardSysdate());
		// ��ü�����ϸ��Ϣ
		objCWParameter.setWaybillpiecesCollection(listWaybillPieces);
		
		return objCWParameter;
	}	
	
	public static FreightpriceCondition transferToFPCondition(ForinputallColumns objFIC) 
	throws Exception {
		FreightpriceCondition objFPCondition = new FreightpriceCondition();
		
		objFPCondition.setCtcode(objFIC.getCtcode());
		objFPCondition.setDtcode(objFIC.getDtcode_Cwodt());
		objFPCondition.setEecode(objFIC.getEecode());
        objFPCondition.setEpstartdate(DateFormatUtility.getStandardSysdate());
        objFPCondition.setEpstartdate2(DateFormatUtility.getStandardSysdate());

        objFPCondition.setPdcode("A02");
        objFPCondition.setPkcode(objFIC.getPk_code());
        objFPCondition.setPmcode(objFIC.getPmcode());
        objFPCondition.setPscode("R");
		
		return objFPCondition;
	}	
	
	public static FeeCalculateParameter transferToFeeCalParam(ForinputallColumns objFIC) 
	throws Exception {
		FeeCalculateParameter objFCP = new FeeCalculateParameter();
		
		objFCP.setBkcode("A0201");
		objFCP.setCtcode(objFIC.getCtcode());
		objFCP.setDtcode(objFIC.getSidtcode());
		objFCP.setGrossWeight(objFIC.getCwchargeweight());
		objFCP.setPieces(objFIC.getCwpieces());
		objFCP.setPmcode(objFIC.getPmcode());
		objFCP.setPostcode(objFIC.getCwpostcodedestination());
		objFCP.setVolumeWeight("0");
		objFCP.setOriginVolumerate("6000");
		
		return objFCP;
	}	
	
	public static void buildSBDAUChargeweightAndChannel(ForinputallColumns objFIAColumns,
			String strChncode) throws Exception {
		objFIAColumns.setChncode_Cwspchn(strChncode);
		objFIAColumns.setCwchargeweight(new BigDecimal(objFIAColumns.getCwgrossweight()));
		objFIAColumns.setCwserverchargeweight(new BigDecimal(objFIAColumns.getCwgrossweight()));
		objFIAColumns.setCwtransferchargeweight(new BigDecimal(objFIAColumns.getCwgrossweight()));
		objFIAColumns.setCwtransfergrossweight(new BigDecimal(objFIAColumns.getCwgrossweight()));
		objFIAColumns.setTransfervolumeweight(new BigDecimal("0"));
	}
	
	
	public static void buildChargeweightAndChannel(ForinputallColumns objFIAColumns,
			List listWaybillPieces) throws Exception {
		// ��üƷ�����
		String strSystemPE = SystempropertyDemand.getEnterprise();
		// sbd����ר��Ϊ�˼ӿ��ٶȣ�ָ��Ψһ����
		if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("SBD")) {
			if (!StringUtility.isNull(objFIAColumns.getPk_code())) {
				TdiProductkind objTPK = TdiProductkindDC.loadByKey(objFIAColumns.getPk_code());
				if (objTPK.getTchnChannel() != null) {
					buildSBDAUChargeweightAndChannel(objFIAColumns, 
							objTPK.getTchnChannel().getChnCode());
					return;
				}
			}
		}
		ChargeweightParameter objCWParameter = transferToCWParameter(objFIAColumns,
				listWaybillPieces);
		Chargeweight objChargeweight = new Chargeweight();
		ChargeweightResult objCWResult = objChargeweight.calculate(objCWParameter);

		objFIAColumns.setCwchargeweight(new BigDecimal(objCWResult.getChargeweight()));
		objFIAColumns.setCwserverchargeweight(new BigDecimal(objCWResult.getChargeweight()));
		objFIAColumns.setCwtransferchargeweight(new BigDecimal(objCWResult.getChargeweight()));
		objFIAColumns.setTransfervolumeweight(new BigDecimal("0"));
		// ��÷�������
		FreightpriceCondition objFPCondition = transferToFPCondition(objFIAColumns);
		FeeCalculateParameter objCalcFeeParameter = transferToFeeCalParam(objFIAColumns);
		ChannelSearch objChannelSearch = new ChannelSearch();
		List<ChannelSearchResult> listResults = objChannelSearch.searchChannels(objFPCondition, objCalcFeeParameter);
		if (listResults == null || listResults.size() < 1)
			return;
		for (int i = 0; i < listResults.size(); i++) {
			ChannelSearchResult objCSR = listResults.get(i);
			if (objCSR.getOptimalsign().equals("Y")) {
				objFIAColumns.setChncode_Cwspchn(objCSR.getChncode());
				objFIAColumns.setCwserverchargeweight(new BigDecimal(objCSR.getChargeweight()));
				objFIAColumns.setCwtransferchargeweight(new BigDecimal(objCSR.getChargeweight()));
				objFIAColumns.setCwtransfergrossweight(new BigDecimal(objCSR.getGrossweight()));
				objFIAColumns.setTransfervolumeweight(new BigDecimal("0"));
			}
		}
	}
	
	
	
	public static String buildEwbcode() throws Exception {
		BuildewbcodeseqQuery objBESQ = new BuildewbcodeseqQuery();
		List listResults = objBESQ.getResults();
		if (listResults == null || listResults.size() < 1)
			return "";
		BuildewbcodeseqColumns objBESC = (BuildewbcodeseqColumns)listResults.get(0);
		
		return DateFormatUtility.getCompactOnlyDateSysdate().substring(2) + StringUtility.buildLength(objBESC.getBuildewbseq(), 6);
	}
	
	public static List buildCargoinfo(PredictwaybillColumns objPOCEX,
			ForinputallColumns objFIAColumns) {
		List<CargoinfoColumns> listCargoInfo =new ArrayList<CargoinfoColumns>();
		
		BigDecimal objPieces = new BigDecimal(objPOCEX.getPwbpwb_cargopieces());
		BigDecimal objUnitprice = new BigDecimal(objPOCEX.getPwbpwb_cargoamount());
		BigDecimal objTotalprice = objUnitprice.multiply(objPieces).divide(new BigDecimal("1"), 2, 4);
		CargoinfoColumns cargoinfoColumns = new CargoinfoColumns();
		cargoinfoColumns.setCiciname(objPOCEX.getPwbpwb_cargoename());
		cargoinfoColumns.setCiciename(objPOCEX.getPwbpwb_cargoename());
		cargoinfoColumns.setCiciunitprice(objUnitprice);
		cargoinfoColumns.setCicipieces(objPieces.intValue());
		cargoinfoColumns.setCicitotalprice(objTotalprice);
		cargoinfoColumns.setCkckcode(objPOCEX.getPwbck_code());
		cargoinfoColumns.setCiciattacheinfo(objPOCEX.getPwbpwb_customremark());//attacheinfo==�����Ϣ=CustomRemark=�Զ���ǩ��
		listCargoInfo.add(cargoinfoColumns);
		
		
		if (listCargoInfo == null || listCargoInfo.size() < 1) {
			objFIAColumns.setCtcode("ADOX");
			return listCargoInfo;
		}
		
		int i = 1;
		for (CargoinfoColumns objCargoinfoColumns : listCargoInfo) {
			// Ʒ������ȷ����Ϊ�ļ�ʱ���������˵��Ļ�������Ϊ�ļ�
			if (objCargoinfoColumns.getCiciename().indexOf("DOC") >= 0)
				objFIAColumns.setCtcode("ADOX");
			String strCipieces = objCargoinfoColumns.getCicipieces();
			String strCiunitprice = objCargoinfoColumns.getCiciunitprice();
			if (StringUtility.isNull(strCipieces))
				strCipieces = "1";
			if (StringUtility.isNull(strCiunitprice))
				strCiunitprice = "0";
			objCargoinfoColumns.setCicomp_idciid(i);
			i++;
			objCargoinfoColumns.setCicitotalprice(new BigDecimal(strCipieces).multiply(new BigDecimal(strCiunitprice)));
	   }
		return listCargoInfo;
	}	
	
	public static List buildPiecesinfo(PredictwaybillColumns objWFPC) {
		// Ԥ�������а����м���Ϣ������ֱ�ӷ���
//		if (objPOCEX.getListCorewaybillpieces() != null && 
//				objPOCEX.getListCorewaybillpieces().size() > 0)
//			return objPOCEX.getListCorewaybillpieces();
		// ����Ĭ��ֻ��1��
//		WaybillforpredictColumns objWFPC = objPOCEX.getWaybillforpredict();
		int iPieces = 1;
		if (StringUtility.isNull(objWFPC.getPwbpwb_chargeweight()))
			objWFPC.setPwbpwb_chargeweight("0.5");
		List<CorewaybillpiecesColumns> listPiecesColumns = new ArrayList<CorewaybillpiecesColumns>();
		for (int i = 0; i < iPieces; i++) {
			CorewaybillpiecesColumns objCWPColumns = new CorewaybillpiecesColumns();
			objCWPColumns.setCpcomp_idcpid(i);
			//objCWPColumns.setCpcomp_idcwcode(Long.parseLong(objHWColumns.getHwcwcode()));
			if (i == 0) 
				objCWPColumns.setCpcpgrossweight(new BigDecimal(objWFPC.getPwbpwb_chargeweight()));
			else
				objCWPColumns.setCpcpgrossweight(new BigDecimal("0"));
			objCWPColumns.setCpcpheight(new BigDecimal("0"));
			objCWPColumns.setCpcpwidth(new BigDecimal("0"));
			objCWPColumns.setCpcplength(new BigDecimal("0"));
			listPiecesColumns.add(objCWPColumns);
		}
		return listPiecesColumns;
	}	
	
	
	
}
