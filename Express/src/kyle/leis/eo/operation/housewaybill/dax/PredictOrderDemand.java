package kyle.leis.eo.operation.housewaybill.dax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.chargeweight.bl.Chargeweight;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightParameter;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightResult;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateParameter;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.channelsearch.bl.ChannelSearch;
import kyle.leis.eo.operation.channelsearch.dax.ChannelSearchResult;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.housewaybill.da.BuildewbcodeseqColumns;
import kyle.leis.eo.operation.housewaybill.da.BuildewbcodeseqQuery;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictCondition;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeColumns;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeCondition;
import kyle.leis.es.company.shipperconsignee.dax.ShipperconsigneeDemand;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.fs.dictionary.productkind.da.ProductkindColumns;
import kyle.leis.fs.dictionary.productkind.dax.ProductkindDemand;
import kyle.leis.hi.TdiProductkind;

public class PredictOrderDemand {
	
	public static void merge(PredictOrderColumnsEX objSourcePOCEX,
			PredictOrderColumnsEX objDestPOCEX) {
		List<CargoinfoColumns> listSourceCargoInfo = objSourcePOCEX.getListCargoInfo();
		List<CargoinfoColumns> listDestCargoInfo = objDestPOCEX.getListCargoInfo();
		merge(listSourceCargoInfo, listDestCargoInfo);
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
	
	public static WaybillforpredictColumns loadExistsRecord(String strCocode, 
			PredictOrderColumnsEX objPOCEX) throws Exception {
		WaybillforpredictColumns objWFPC = objPOCEX.getWaybillforpredict();
		
		WaybillforpredictCondition objWFPCondition = new WaybillforpredictCondition();
		objWFPCondition.setCocodecustomer(strCocode);
		objWFPCondition.setCwcustomerewbcode(objWFPC.getCwcw_customerewbcode());
		objWFPCondition.setIncwscode("CTS,CHD,CHP");
		List listResults = HousewaybillDemand.queryForPredict(objWFPCondition);
		if (listResults == null || listResults.size() < 1) {
			objWFPCondition.setCocodecustomer(strCocode);
			objWFPCondition.setCwcustomerewbcode("");
			objWFPCondition.setIncwscode("CTS,CHD,CHP");
			objWFPCondition.setHwconsigneename(objWFPC.getHwhw_consigneename());
			listResults = HousewaybillDemand.queryForPredict(objWFPCondition);
			if (listResults == null || listResults.size() < 1) {			
				return null;
			}
		}
		return ((WaybillforpredictColumns)listResults.get(0));		
	}
	
	
	private static void merge(CargoinfoColumns objSourceCC,
			List<CargoinfoColumns> listDestCargoInfo) {
		boolean isRepeat = false;
		for (CargoinfoColumns objCargoinfoColumns : listDestCargoInfo) {
			// 源
			String strScCienme = StringUtility.replaceWhenNull(objSourceCC.getCiciename(),
					"");
			String strScAttacheinfo = StringUtility.replaceWhenNull(objSourceCC.getCiciattacheinfo(),
					"");
			// 目的
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
	
	public static void setDefaultinfo(String strCocode,
			List<PredictOrderColumnsEX> listWaybill) throws Exception {
		for (PredictOrderColumnsEX objPOCEX : listWaybill) {
			String strScname = objPOCEX.getWaybillforpredict().getHwhw_shippername();
			if (StringUtility.isNull(strScname)) {
				ShipperconsigneeColumns objSCColumns = ShipperconsigneeDemand.loadByCustomer(strCocode);//objSCColumns = null
				//判断表中是否有发件人
				if(objSCColumns == null ){
					return ;
				}else{
					setShipperInfo(objPOCEX, objSCColumns);
				}
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
			PredictOrderColumnsEX objPOCEX) throws Exception {
		WaybillforpredictColumns objWFPC = objPOCEX.getWaybillforpredict();
		ForinputallColumns objFIAColumns = new ForinputallColumns();
		
		if (!StringUtility.isNull(objWFPC.getHwhw_consigneecity()))
			objWFPC.setHwhw_consigneecity(objWFPC.getHwhw_consigneecity().toUpperCase());
		
		if (!StringUtility.isNull(objWFPC.getCwcw_code()))
			objFIAColumns.setCwcode(Long.parseLong(objWFPC.getCwcw_code()));		
		
		objFIAColumns.setCwcustomerewbcode(objWFPC.getCwcw_customerewbcode());	
		String strEwbcode = objWFPC.getCwcw_customerewbcode(); 
		if (StringUtility.isNull(objWFPC.getCwcw_code())) {
			String strPE = SystempropertyDemand.getEnterprise();
			if (!StringUtility.isNull(strPE) && strPE.startsWith("SLY"))
				strEwbcode = buildEwbcode();
			else
				strEwbcode = objWFPC.getCwcw_customerewbcode();
			
			objFIAColumns.setCwserverewbcode(objWFPC.getCwcw_customerewbcode());
			objFIAColumns.setCwewbcode(strEwbcode);
		} else {
			objFIAColumns.setCwserverewbcode(objWFPC.getCwcw_serverewbcode());
			objFIAColumns.setCwewbcode(objWFPC.getCwcw_ewbcode());			
		}
		
		objFIAColumns.setAdddate(DateFormatUtility.getSysdate());
		objFIAColumns.setCocode(strCocode);
		objFIAColumns.setCocode_Cwcus(strCocode);
		objFIAColumns.setEecode("1");
		objFIAColumns.setDtcode_Cwodt("719");
		// 设置产品
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
		// 这里似乎要处理JL的城市问题，暂时在XML中转换看后面是不是需要直接保存
		// 即如果有只能通过城市三字代码来计费而且需要用城市跟国家去找城市三字代码的这种情况
		// 这里就需要调整
		String strSignInDtcode = "";
		String strEe = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strEe) && strEe.startsWith("KLEX")) {
			String strCountryhubcode = objWFPC.getCwdt_code_signin();
			strSignInDtcode = DistrictDemand.getDtcodeByDtename(strCountryhubcode,
					objWFPC.getHwhw_consigneecity());
		}
		if (StringUtility.isNull(strSignInDtcode)) {
			strSignInDtcode = DistrictDemand.getDtcodeByHubcode(objWFPC.getCwdt_code_signin());
		}
		
		objFIAColumns.setSidtcode(strSignInDtcode);
		objFIAColumns.setDtcode(strSignInDtcode);
		if (!StringUtility.isNull(strEe) && strEe.startsWith("SBD")) {
			objFIAColumns.setDtcode(DistrictDemand.getNotCountryDtcodeByHubcode(DistrictDemand.getDHLHubcode(objWFPC.getCwdt_code_signin(), 
					objWFPC.getHwhw_consigneepostcode())));
		}
		if (!StringUtility.isNull(strEe) && strEe.startsWith("SLY")) {
			String strPkstructurecode = objPKC.getPkstructurecode();
			if (!StringUtility.isNull(strPkstructurecode) && strPkstructurecode.startsWith("DHL")) {
				objFIAColumns.setDtcode(DistrictDemand.getNotCountryDtcodeByHubcode(DistrictDemand.getDHLHubcode(objWFPC.getCwdt_code_signin(), 
						objWFPC.getHwhw_consigneepostcode())));
			}
		}		
		objFIAColumns.setHwDtcodeshipper("719");
		objFIAColumns.setCwscode("CTS");
		// 客户重量作为实重
		String strCw_grossweight = objWFPC.getCwcw_customerchargeweight();
		if (StringUtility.isNull(strCw_grossweight))
			strCw_grossweight = "0.5";
		// 获得计费重量
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
		
		//收件人信息
		objFIAColumns.setHwconsigneename(objWFPC.getHwhw_consigneename());
		// 解决乱码的问题
		objFIAColumns.setHwconsigneenameex(StringUtility.buildToByte(objWFPC.getHwhw_consigneename(), "utf-8", 512));
		
		objFIAColumns.setHwconsigneecompany(objWFPC.getHwhw_consigneecompany());
		objFIAColumns.setHwconsigneetelephone(objWFPC.getHwhw_consigneetelephone());
		objFIAColumns.setHwConsigneecity(objWFPC.getHwhw_consigneecity());
		objFIAColumns.setHwconsigneecityex(StringUtility.buildToByte(objWFPC.getHwhw_consigneecity(), "utf-8", 512));
		objFIAColumns.setHwpat_code(objWFPC.getHwpat_code());
		objFIAColumns.setHwcgk_code(objWFPC.getHwcgk_code());
		objFIAColumns.setHwbk_code(objWFPC.getHwbk_code());
		objFIAColumns.setHwdt_code(objWFPC.getHwdt_code());
		
		// 地址信息
		String strConsigneeaddress = objWFPC.getHwhw_consigneeaddress1();
		if (!StringUtility.isNull(objWFPC.getHwhw_consigneeaddress2()) &&
				!objWFPC.getHwhw_consigneeaddress2().equals("."))
			strConsigneeaddress = strConsigneeaddress + " " + objWFPC.getHwhw_consigneeaddress2();
		
		if (!StringUtility.isNull(objWFPC.getHwhw_consigneecity()) &&
				!objWFPC.getHwhw_consigneecity().equals("."))
			strConsigneeaddress = strConsigneeaddress + " " + objWFPC.getHwhw_consigneecity();
		
		if (!StringUtility.isNull(objWFPC.getHwhw_consigneeaddress3()) &&
				!objWFPC.getHwhw_consigneeaddress3().equals("."))
			strConsigneeaddress = strConsigneeaddress + " " + objWFPC.getHwhw_consigneeaddress3();
		// 解决乱码的问题
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
		objFIAColumns.setCwpostcodedestination(objWFPC.getHwhw_consigneepostcode());
		objFIAColumns.setHwconsigneepostcode(objWFPC.getHwhw_consigneepostcode());
		// 发件人信息
		String strShipperaddress = objWFPC.getHwhw_shipperaddress1();
		if (!StringUtility.isNull(objWFPC.getHwhw_shipperaddress2()) &&
				!objWFPC.getHwhw_shipperaddress2().equals("."))
			strShipperaddress = strShipperaddress + " " + objWFPC.getHwhw_shipperaddress2();
		if (!StringUtility.isNull(objWFPC.getHwhw_shipperaddress3()) &&
				!objWFPC.getHwhw_shipperaddress3().equals("."))
			strShipperaddress = strShipperaddress + " " + objWFPC.getHwhw_shipperaddress3();
		strAddress1 = StringUtility.substring(strShipperaddress, 0, 30);
		strAddress2 = StringUtility.substring(strShipperaddress, 30, 30);
		strAddress3 = StringUtility.substring(strShipperaddress, 60, 60);
		if (strAddress2.equals(strShipperaddress))
			strAddress2 = ".";
		if (strAddress3.equals(strShipperaddress))
			strAddress3 = ".";

		
		objFIAColumns.setHwshipperaddress1(strAddress1);
		objFIAColumns.setHwshipperaddress2(strAddress2);
		objFIAColumns.setHwshipperaddress3(strAddress3);
		
		objFIAColumns.setHwshippercompany(objWFPC.getHwhw_shippercompany());
		objFIAColumns.setHwshipperfax(objWFPC.getHwhw_shipperfax());
		objFIAColumns.setHwshippername(objWFPC.getHwhw_shippername());
		objFIAColumns.setHwshipperpostcode(objWFPC.getHwhw_shipperpostcode());
		objFIAColumns.setHwshippertelephone(objWFPC.getHwhw_shippertelephone());
		objFIAColumns.setHwremark(objWFPC.getHwhw_remark());
		objFIAColumns.setTransactionid(objWFPC.getHwhw_transactionid());
		objFIAColumns.setBuyerid(objWFPC.getHwhw_buyerid());
		//info
		if(!StringUtility.isNull(objWFPC.getCwcw_code())) {
			objFIAColumns.setCwcode(Long.parseLong(objWFPC.getCwcw_code()));
		}
		if(StringUtility.isNull(objWFPC.getHwhw_insurancevalue()))
			objWFPC.setHwhw_insurancevalue("0");
		objFIAColumns.setHwinsurancevalue(new BigDecimal(objWFPC.getHwhw_insurancevalue()));
		objFIAColumns.setIfyibcode(objWFPC.getIfyib_code());
		objFIAColumns.setItiptcode(objWFPC.getItipt_code());
		objFIAColumns.setHwhwDcustomssign(objWFPC.getHwhw_dcustomssign());
		
		return objFIAColumns;		
	}
	
	public static ChargeweightParameter transferToCWParameter(ForinputallColumns objFIC,
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
		// 获得件数详细信息
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
		// 获得计费重量
		String strSystemPE = SystempropertyDemand.getEnterprise();
		// sbd澳洲专线为了加快速度，指定唯一渠道
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
		objFIAColumns.setCwcustomerchargeweight(new BigDecimal(objCWResult.getChargeweight()));
		objFIAColumns.setCwserverchargeweight(new BigDecimal(objCWResult.getChargeweight()));
		objFIAColumns.setCwtransferchargeweight(new BigDecimal(objCWResult.getChargeweight()));
		objFIAColumns.setTransfervolumeweight(new BigDecimal("0"));
		// 获得服务渠道
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
	
	public static List buildCargoinfo(PredictOrderColumnsEX objPOCEX,
			ForinputallColumns objFIAColumns) {
		List<CargoinfoColumns> listCargoInfo = objPOCEX.getListCargoInfo();
		
		if (listCargoInfo == null || listCargoInfo.size() < 1) {
			objFIAColumns.setCtcode("ADOX");
			return listCargoInfo;
		}
		
		int i = 1;
		for (CargoinfoColumns objCargoinfoColumns : listCargoInfo) {
			// 品名中明确标明为文件时，需设置运单的货物类型为文件
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
	
	public static List buildPiecesinfo(PredictOrderColumnsEX objPOCEX) {
		// 预报数据中包含有件信息数据则直接返回
		if (objPOCEX.getListCorewaybillpieces() != null && 
				objPOCEX.getListCorewaybillpieces().size() > 0)
			return objPOCEX.getListCorewaybillpieces();
		// 否则默认只有1件
		WaybillforpredictColumns objWFPC = objPOCEX.getWaybillforpredict();
		int iPieces = 1;
		if (StringUtility.isNull(objWFPC.getCwcw_customerchargeweight()))
			objWFPC.setCwcw_customerchargeweight("0.5");
		List<CorewaybillpiecesColumns> listPiecesColumns = new ArrayList<CorewaybillpiecesColumns>();
		for (int i = 0; i < iPieces; i++) {
			CorewaybillpiecesColumns objCWPColumns = new CorewaybillpiecesColumns();
			objCWPColumns.setCpcomp_idcpid(i);
			//objCWPColumns.setCpcomp_idcwcode(Long.parseLong(objHWColumns.getHwcwcode()));
			if (i == 0) 
				objCWPColumns.setCpcpgrossweight(new BigDecimal(objWFPC.getCwcw_customerchargeweight()));
			else
				objCWPColumns.setCpcpgrossweight(new BigDecimal("0"));
			objCWPColumns.setCpcpheight(new BigDecimal("0"));
			objCWPColumns.setCpcpwidth(new BigDecimal("0"));
			objCWPColumns.setCpcplength(new BigDecimal("0"));
			listPiecesColumns.add(objCWPColumns);
		}
		return listPiecesColumns;
	}	
	
	/**
	 * 替换发件人信息
	 * @param chnCode
	 * @param objFIAColumns
	 * @throws Exception
	 */
	public static void replaceShipperInfoByChnChannel(String chnCode, 
			ForinputallColumns objFIAColumns) throws Exception{
		if (StringUtility.isNull(chnCode)) {
			return;
		}
		ShipperconsigneeCondition objSCCondition = new ShipperconsigneeCondition();
		objSCCondition.setChncode(chnCode);
		List<?> shipperInfos = ShipperconsigneeDemand.query(objSCCondition);
		if (shipperInfos.isEmpty()) {
			return;
		}
		ShipperconsigneeColumns shipperconsignee = (ShipperconsigneeColumns) shipperInfos.get(0);
		objFIAColumns.setHwshippername(shipperconsignee.getScscname());
		String companyname = shipperconsignee.getScsccompanyname();
		if (!"请输入正确的公司名，其他信息请勿修改".equals(companyname)) {
			objFIAColumns.setHwshippercompany(companyname);
		}
		objFIAColumns.setHwshipperaddress1(shipperconsignee.getScscaddress1());
		objFIAColumns.setHwshipperaddress2(shipperconsignee.getScscaddress2());
		objFIAColumns.setHwshipperaddress3(shipperconsignee.getScscaddress3());
		objFIAColumns.setHwshippertelephone(shipperconsignee.getScsctelephone());
		objFIAColumns.setHwshipperfax(shipperconsignee.getScscfax());
		objFIAColumns.setHwshipperpostcode(shipperconsignee.getScscpostcode());
		//objFIAColumns.set
		if (!StringUtility.isNull(shipperconsignee.getScsccitycode())) {
			String strDtcode = DistrictDemand.getNotCountryDtcodeByHubcode(shipperconsignee.getScsccitycode());
			if (!StringUtility.isNull(strDtcode)) {
				objFIAColumns.setHwDtcodeshipper(strDtcode);
			}
		}
	}
	
}
