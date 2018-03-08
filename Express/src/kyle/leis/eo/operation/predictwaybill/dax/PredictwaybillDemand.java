package kyle.leis.eo.operation.predictwaybill.dax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateParameter;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.channelsearch.bl.ChannelSearch;
import kyle.leis.eo.operation.channelsearch.dax.ChannelSearchResult;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderDemand;
import kyle.leis.eo.operation.predictwaybill.da.PredictcargoinfoColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictcargoinfoQuery;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillCondition;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillQuery;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillforprintCondition;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillforprintQuery;
import kyle.leis.eo.operation.predictwaybill.da.ToppredictwaybillTR;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeColumns;
import kyle.leis.es.company.shipperconsignee.dax.ShipperconsigneeDemand;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.fs.dictionary.productkind.da.ProductkindColumns;
import kyle.leis.fs.dictionary.productkind.dax.ProductkindDemand;

public class PredictwaybillDemand {
	public static List query(PredictwaybillCondition objPWCondition) throws Exception {
		PredictwaybillQuery objPWQuery = new PredictwaybillQuery();
		objPWQuery.setCondition(objPWCondition);
		return objPWQuery.getResults();
	}
	
	public static List queryByCMStructurecode(PredictwaybillCondition objPWCondition) throws Exception {
		if (!StringUtility.isNull(objPWCondition.getCo_code_customer())) {
			PredictwaybillQueryEX objPWQuery = new PredictwaybillQueryEX(objPWCondition.getCo_code_customer());
			objPWQuery.setCondition(objPWCondition);
			return objPWQuery.getResults();
		} else {
			return query(objPWCondition);
		}
	}	
	
	public static PredictwaybillColumns loadBycwcode(String strCwcode) throws Exception {
		PredictwaybillCondition objPWCondition = new PredictwaybillCondition();
		objPWCondition.setCwcode(strCwcode);
		List listResults = query(objPWCondition);
		if (listResults == null || listResults.size() < 1)
			return null;
		return (PredictwaybillColumns)listResults.get(0);
	}		
	
	
	public static PredictwaybillColumns load(String strPwbcode) throws Exception {
		PredictwaybillCondition objPWCondition = new PredictwaybillCondition();
		objPWCondition.setPwb_code(strPwbcode);
		List listResults = query(objPWCondition);
		if (listResults == null || listResults.size() < 1)
			return null;
		return (PredictwaybillColumns)listResults.get(0);
	}	
	
	public static List loadCargoinfo(String strPwbcode) throws Exception {
		PredictcargoinfoQuery objPCIQuery = new PredictcargoinfoQuery();
		objPCIQuery.setPwbcode(strPwbcode);
		return objPCIQuery.getResults();
	}
	
	
	public static List queryForPrint(PredictwaybillforprintCondition objPWFPCondition) 
	throws Exception {
		PredictwaybillforprintQuery objPWFPQuery = new PredictwaybillforprintQuery();
		objPWFPQuery.setCondition(objPWFPCondition);
		return objPWFPQuery.getResults();
	}	
	
	public static ForinputallColumns buildForinputAll(PredictwaybillColumns objPredictwaybillColumns) throws Exception {
		ForinputallColumns objFIAColumns = new ForinputallColumns();
		
		if (!StringUtility.isNull(objPredictwaybillColumns.getPwbpwb_consigneecity()))
			objPredictwaybillColumns.setPwbpwb_consigneecity(objPredictwaybillColumns.getPwbpwb_consigneecity().toUpperCase());	
		
		objFIAColumns.setCwcustomerewbcode(objPredictwaybillColumns.getPwbpwb_orderid());	
		String strEwbcode = objFIAColumns.getCwcustomerewbcode(); 
		// SLY取公司单号比较特殊
		String strPE = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strPE) && strPE.startsWith("SLY"))
			strEwbcode = PredictOrderDemand.buildEwbcode();
		objFIAColumns.setCwserverewbcode(objFIAColumns.getCwcustomerewbcode());
		objFIAColumns.setCwewbcode(strEwbcode);
		
		objFIAColumns.setAdddate(DateFormatUtility.getSysdate());
		objFIAColumns.setCocode(objPredictwaybillColumns.getCoco_code());
		objFIAColumns.setCocode_Cwcus(objPredictwaybillColumns.getCoco_code());
		objFIAColumns.setEecode("1");
		objFIAColumns.setDtcode_Cwodt("719");
		// 设置产品
		objFIAColumns.setPk_code(objPredictwaybillColumns.getPkpk_code());
		String strEe = SystempropertyDemand.getEnterprise();
		objFIAColumns.setSidtcode(objPredictwaybillColumns.getDtdt_code());
		objFIAColumns.setDtcode(objPredictwaybillColumns.getDtdt_code());
		if (!StringUtility.isNull(strEe) && strEe.startsWith("SBD")) {
			objFIAColumns.setDtcode(DistrictDemand.getNotCountryDtcodeByHubcode(DistrictDemand.getDHLHubcode(DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getSidtcode()), 
					objPredictwaybillColumns.getPwbpwb_consigneepostcode())));
		}
		if (!StringUtility.isNull(strEe) && strEe.startsWith("SLY")) {
			ProductkindColumns objPKC = ProductkindDemand.queryBypkCode(objPredictwaybillColumns.getPkpk_code());
			String strPkstructurecode = objPKC.getPkstructurecode();
			if (!StringUtility.isNull(strPkstructurecode) && strPkstructurecode.startsWith("DHL")) {
				objFIAColumns.setDtcode(DistrictDemand.getNotCountryDtcodeByHubcode(DistrictDemand.getDHLHubcode(DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getSidtcode()), 
						objPredictwaybillColumns.getPwbpwb_consigneepostcode())));
			}
		}			
		objFIAColumns.setHwDtcodeshipper("719");
		objFIAColumns.setCwscode("CHD");
		// 客户重量作为实重
		String strCw_grossweight = objPredictwaybillColumns.getPwbpwb_chargeweight();
		if (StringUtility.isNull(strCw_grossweight))
			strCw_grossweight = "0.5";
		// 获得计费重量
		BigDecimal objFIACGrossweight = new BigDecimal(strCw_grossweight);
		objFIAColumns.setCwgrossweight(objFIACGrossweight);
		//objFIAColumns.setCwcustomerchargeweight(objFIACGrossweight);
		objFIAColumns.setCwchargeweight(objFIACGrossweight);
		objFIAColumns.setCwserverchargeweight(objFIACGrossweight);
		objFIAColumns.setCwtransferchargeweight(objFIACGrossweight);
		objFIAColumns.setCwtransfergrossweight(objFIACGrossweight);
		objFIAColumns.setTransfervolumeweight(new BigDecimal("0"));
		
		if(objFIAColumns.getCtcode() == null || objFIAColumns.getCtcode().equals(""))
			objFIAColumns.setCtcode("AWPX");
		objFIAColumns.setTransfervolumerate(Integer.parseInt("5000"));
		objFIAColumns.setPmcode("APP");
		// 件数
		if (!StringUtility.isNull(objPredictwaybillColumns.getPwbpwb_pieces()) &&
				new BigDecimal(objPredictwaybillColumns.getPwbpwb_pieces()).compareTo(new BigDecimal("1")) >= 0)
			objFIAColumns.setCwpieces(Integer.parseInt(objPredictwaybillColumns.getPwbpwb_pieces()));
		else
			objFIAColumns.setCwpieces(1);
		objPredictwaybillColumns.setPwbpwb_pieces(objFIAColumns.getCwpieces());
		//发件人信息
		objFIAColumns.setHwconsigneename(objPredictwaybillColumns.getPwbpwb_consigneename());
		// 解决乱码的问题
		objFIAColumns.setHwconsigneenameex(objPredictwaybillColumns.getPwbpwb_consigneenameex());
		
		objFIAColumns.setHwconsigneecompany(".");
		objFIAColumns.setHwconsigneetelephone(objPredictwaybillColumns.getPwbpwb_consigneetel());
		objFIAColumns.setHwConsigneecity(objPredictwaybillColumns.getPwbpwb_consigneecity());
		objFIAColumns.setHwconsigneecityex(objPredictwaybillColumns.getPwbpwb_consigneecityex());
		// 地址信息
		String strConsigneeaddress = objPredictwaybillColumns.getPwbpwb_consigneeaddress1();
		if (!StringUtility.isNull(objPredictwaybillColumns.getPwbpwb_consigneeaddress2()) &&
				!objPredictwaybillColumns.getPwbpwb_consigneeaddress2().equals("."))
			strConsigneeaddress = strConsigneeaddress + " " + objPredictwaybillColumns.getPwbpwb_consigneeaddress2();
		// 解决乱码的问题
		objFIAColumns.setHwconsigneeaddressex(objPredictwaybillColumns.getPwbpwb_consigneeaddressex());
		
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
		objFIAColumns.setCwpostcodedestination(objPredictwaybillColumns.getPwbpwb_consigneepostcode());
		objFIAColumns.setHwconsigneepostcode(objPredictwaybillColumns.getPwbpwb_consigneepostcode());
		// 发件人信息
		ShipperconsigneeColumns objSCColumns = ShipperconsigneeDemand.loadByCustomer(objPredictwaybillColumns.getCoco_code());
		if (objSCColumns != null) {
			objFIAColumns.setHwshipperaddress1(objSCColumns.getScscaddress1());
			objFIAColumns.setHwshipperaddress2(objSCColumns.getScscaddress2());
			objFIAColumns.setHwshipperaddress3(objSCColumns.getScscaddress3());
			
			objFIAColumns.setHwshippercompany(objSCColumns.getScsccompanyname());
			objFIAColumns.setHwshipperfax(objSCColumns.getScscfax());
			objFIAColumns.setHwshippername(objSCColumns.getScscname());
			objFIAColumns.setHwshipperpostcode(objSCColumns.getScscpostcode());
			objFIAColumns.setHwshippertelephone(objSCColumns.getScsctelephone());
		} else {
			objFIAColumns.setHwshipperaddress1(".");
			objFIAColumns.setHwshipperaddress2(".");
			objFIAColumns.setHwshipperaddress3(".");
			
			objFIAColumns.setHwshippercompany(".");
			objFIAColumns.setHwshipperfax(".");
			objFIAColumns.setHwshippername(".");
			objFIAColumns.setHwshipperpostcode("");
			objFIAColumns.setHwshippertelephone(".");
		}
		

		objFIAColumns.setHwremark(objPredictwaybillColumns.getPwbpwb_customremark());
		objFIAColumns.setTransactionid(objPredictwaybillColumns.getPwbpwb_transactionid());
		objFIAColumns.setBuyerid(".");
		
		return objFIAColumns;		
	}
	
	public static List buildCargoinfo(PredictwaybillColumns objPredictwaybillColumns,
			List listPwbCargoinfo,
			ForinputallColumns objFIAColumns) {
		List<CargoinfoColumns> listCargoInfo = new ArrayList<CargoinfoColumns>();
		

		if (listPwbCargoinfo != null && listPwbCargoinfo.size() > 0) {
			for (int i = 0; i < listPwbCargoinfo.size(); i++) {
				CargoinfoColumns objCargoinfoColumns = new CargoinfoColumns();
				
				PredictcargoinfoColumns objPCIColumns = (PredictcargoinfoColumns)listPwbCargoinfo.get(i);
				String strCargoname = objPredictwaybillColumns.getPwbpwb_cargoename();
				if (StringUtility.isNull(strCargoname) || strCargoname.indexOf("DOC") >= 0) {
					objFIAColumns.setCtcode("ADOX");
					return listCargoInfo;
				}		
				objCargoinfoColumns.setCiciename(objPCIColumns.getPcipci_ename());
				objCargoinfoColumns.setCicipieces(Integer.parseInt(objPCIColumns.getPcipci_pieces()));
				objCargoinfoColumns.setCiciname(strCargoname);
				objCargoinfoColumns.setCiciunitprice(new BigDecimal(objPCIColumns.getPcipci_unitprice()));
				objCargoinfoColumns.setCiciweight(new BigDecimal(objPCIColumns.getPcipci_weight()));
				objCargoinfoColumns.setCicomp_idciid(i + 1);
				objCargoinfoColumns.setCiciattacheinfo(objPCIColumns.getPcipci_remark());
				objCargoinfoColumns.setCicitotalprice(new BigDecimal(objPCIColumns.getPcipci_totalprice()));
				objCargoinfoColumns.setCkckcode(objPCIColumns.getPcick_code());
				
				listCargoInfo.add(objCargoinfoColumns);
			}
		}
		return listCargoInfo;
	}	
	
	public static List buildCargoinfo(PredictwaybillColumns objPredictwaybillColumns,
			ForinputallColumns objFIAColumns) {
		List<CargoinfoColumns> listCargoInfo = new ArrayList<CargoinfoColumns>();
		
		String strCargoname = objPredictwaybillColumns.getPwbpwb_cargoename();
		if (StringUtility.isNull(strCargoname)) {
			objFIAColumns.setCtcode("ADOX");
			return listCargoInfo;
		}

		// 品名中明确标明为文件时，需设置运单的货物类型为文件
		CargoinfoColumns objCargoinfoColumns = new CargoinfoColumns();
		if (strCargoname.indexOf("DOC") >= 0)
			objFIAColumns.setCtcode("ADOX");
		
		String strCipieces = objPredictwaybillColumns.getPwbpwb_cargopieces();
		String strCiunitprice = objPredictwaybillColumns.getPwbpwb_cargoamount();
		if (StringUtility.isNull(strCipieces))
			strCipieces = "1";
		if (StringUtility.isNull(strCiunitprice))
			strCiunitprice = "0";
		// 品名为英文|中文格式
		String[] astr = new String[2];
		if (strCargoname.indexOf("|") > 0) {
			astr[0] = strCargoname.substring(0, strCargoname.indexOf("|"));
			astr[1] = strCargoname.substring(strCargoname.indexOf("|") + 1);
		} else {
			astr[0] = strCargoname;
			astr[1] = strCargoname;
		}
		objCargoinfoColumns.setCiciename(astr[0]);
		objCargoinfoColumns.setCicipieces(Integer.parseInt(strCipieces));
		objCargoinfoColumns.setCiciname(astr[1]);
		objCargoinfoColumns.setCiciunitprice(new BigDecimal(strCiunitprice));
		objCargoinfoColumns.setCicomp_idciid(1);
		objCargoinfoColumns.setCiciattacheinfo(objPredictwaybillColumns.getPwbpwb_customremark());
		objCargoinfoColumns.setCicitotalprice(new BigDecimal(strCipieces).multiply(new BigDecimal(strCiunitprice)));
		if (!StringUtility.isNull(objPredictwaybillColumns.getPwbck_code()))
			objCargoinfoColumns.setCkckcode(objPredictwaybillColumns.getPwbck_code());
		
		listCargoInfo.add(objCargoinfoColumns);
		return listCargoInfo;
	}	
	
	
	public static List buildPiecesinfo(PredictwaybillColumns objPredictwaybillColumns) {
		// 预报数据中包含有件信息数据则直接返回
		int iPieces = Integer.parseInt(objPredictwaybillColumns.getPwbpwb_pieces());
		if (StringUtility.isNull(objPredictwaybillColumns.getPwbpwb_chargeweight()))
			objPredictwaybillColumns.setPwbpwb_chargeweight("0.5");
		List<CorewaybillpiecesColumns> listPiecesColumns = new ArrayList<CorewaybillpiecesColumns>();
		for (int i = 0; i < iPieces; i++) {
			CorewaybillpiecesColumns objCWPColumns = new CorewaybillpiecesColumns();
			objCWPColumns.setCpcomp_idcpid(i);
			if (i == 0) 
				objCWPColumns.setCpcpgrossweight(new BigDecimal(objPredictwaybillColumns.getPwbpwb_chargeweight()));
			else
				objCWPColumns.setCpcpgrossweight(new BigDecimal("0"));
			objCWPColumns.setCpcpheight(new BigDecimal("0"));
			objCWPColumns.setCpcpwidth(new BigDecimal("0"));
			objCWPColumns.setCpcplength(new BigDecimal("0"));
			listPiecesColumns.add(objCWPColumns);
		}
		return listPiecesColumns;
	}		
	
	public static void buildChargeweightAndChannel(ForinputallColumns objFIAColumns,
			PredictwaybillColumns objPredictwaybillColumns) throws Exception {
		// 获得计费重量
		objFIAColumns.setChncode(objPredictwaybillColumns.getChnchn_code());
		objFIAColumns.setChncode_Cwspchn(objPredictwaybillColumns.getChnchn_code());
		objFIAColumns.setCwchargeweight(new BigDecimal(objPredictwaybillColumns.getPwbpwb_chargeweight()));
		objFIAColumns.setCwserverchargeweight(new BigDecimal(objPredictwaybillColumns.getPwbpwb_chargeweight()));
		objFIAColumns.setCwtransferchargeweight(new BigDecimal(objPredictwaybillColumns.getPwbpwb_chargeweight()));
		objFIAColumns.setTransfervolumeweight(new BigDecimal("0"));
		// 获取渠道
		if (StringUtility.isNull(objFIAColumns.getChncode())) {
			// 获得服务渠道
			FreightpriceCondition objFPCondition = PredictOrderDemand.transferToFPCondition(objFIAColumns);
			FeeCalculateParameter objCalcFeeParameter = PredictOrderDemand.transferToFeeCalParam(objFIAColumns);
			ChannelSearch objChannelSearch = new ChannelSearch();
			List<ChannelSearchResult> listResults = objChannelSearch.searchChannels(objFPCondition, objCalcFeeParameter);
			if (listResults == null || listResults.size() < 1)
				return;
			for (int i = 0; i < listResults.size(); i++) {
				ChannelSearchResult objCSR = listResults.get(i);
				if (objCSR.getOptimalsign().equals("Y")) {
					objFIAColumns.setChncode(objCSR.getChncode());
					objFIAColumns.setChncode_Cwspchn(objCSR.getChncode());
					objPredictwaybillColumns.setChnchn_code(objCSR.getChncode());
					objFIAColumns.setCwserverchargeweight(new BigDecimal(objCSR.getChargeweight()));
					objFIAColumns.setCwtransferchargeweight(new BigDecimal(objCSR.getChargeweight()));
					objFIAColumns.setCwtransfergrossweight(new BigDecimal(objCSR.getGrossweight()));
					objFIAColumns.setTransfervolumeweight(new BigDecimal("0"));
				}
			}			
		}
	}	
	
	
	public static void setPredictwaybill(PredictwaybillColumns objPredictwaybillColumns,
			ToppredictwaybillTR objToppredictwaybillTR,
			Session objSession) throws Exception {
		// 价值
		if (StringUtility.isNull(objPredictwaybillColumns.getPwbpwb_cargoamount()))
			objToppredictwaybillTR.setPwb_cargoamount("0");
		else
			objToppredictwaybillTR.setPwb_cargoamount(objPredictwaybillColumns.getPwbpwb_cargoamount());
		
		objToppredictwaybillTR.setPwb_cargoename(objPredictwaybillColumns.getPwbpwb_cargoename());
		// 数量
		if (StringUtility.isNull(objPredictwaybillColumns.getPwbpwb_cargopieces()))
			objToppredictwaybillTR.setPwb_cargopieces("1");
		else
			objToppredictwaybillTR.setPwb_cargopieces(objPredictwaybillColumns.getPwbpwb_cargopieces());
		// 重量
		if (StringUtility.isNull(objPredictwaybillColumns.getPwbpwb_chargeweight()))
			objToppredictwaybillTR.setPwb_chargeweight("0");
		else
			objToppredictwaybillTR.setPwb_chargeweight(objPredictwaybillColumns.getPwbpwb_chargeweight());
		
		objToppredictwaybillTR.setPwb_consigneeaddress1(objPredictwaybillColumns.getPwbpwb_consigneeaddress1());
		objToppredictwaybillTR.setPwb_consigneeaddress2(objPredictwaybillColumns.getPwbpwb_consigneeaddress2());
		objToppredictwaybillTR.setPwb_consigneecity(objPredictwaybillColumns.getPwbpwb_consigneecity());
		objToppredictwaybillTR.setPwb_consigneename(objPredictwaybillColumns.getPwbpwb_consigneename());
		objToppredictwaybillTR.setPwb_consigneepostcode(objPredictwaybillColumns.getPwbpwb_consigneepostcode());
		objToppredictwaybillTR.setPwb_consigneestate(objPredictwaybillColumns.getPwbpwb_consigneestate());
		objToppredictwaybillTR.setPwb_consigneetel(objPredictwaybillColumns.getPwbpwb_consigneetel());
		objToppredictwaybillTR.setPwb_customremark(objPredictwaybillColumns.getPwbpwb_customremark());
		objToppredictwaybillTR.setPwb_orderid(objPredictwaybillColumns.getPwbpwb_orderid());
		objToppredictwaybillTR.setPwb_serverewbcode(objPredictwaybillColumns.getPwbpwb_serverewbcode());
		objToppredictwaybillTR.setPwb_transactionid(objPredictwaybillColumns.getPwbpwb_transactionid());
		objToppredictwaybillTR.setPwb_consigneenameex(objPredictwaybillColumns.getPwbpwb_consigneenameex());
		objToppredictwaybillTR.setPwb_consigneecityex(objPredictwaybillColumns.getPwbpwb_consigneecityex());
		objToppredictwaybillTR.setPwb_consigneeaddressex(objPredictwaybillColumns.getPwbpwb_consigneeaddressex());
		objToppredictwaybillTR.setPwb_buyerid(objPredictwaybillColumns.getPwbpwb_buyerid());
		// 件数
		if (StringUtility.isNull(objPredictwaybillColumns.getPwbpwb_pieces()))
			objToppredictwaybillTR.setPwb_pieces("1");
		else
			objToppredictwaybillTR.setPwb_pieces(objPredictwaybillColumns.getPwbpwb_pieces());
		
		if (!StringUtility.isNull(objPredictwaybillColumns.getChnchn_code()))
			objToppredictwaybillTR.setChn_code(objPredictwaybillColumns.getChnchn_code());
		if (!StringUtility.isNull(objPredictwaybillColumns.getCoco_code()))
			objToppredictwaybillTR.setCo_code_customer(objPredictwaybillColumns.getCoco_code());
		if (!StringUtility.isNull(objPredictwaybillColumns.getPkpk_code()))
			objToppredictwaybillTR.setPk_code(objPredictwaybillColumns.getPkpk_code());
		if (!StringUtility.isNull(objPredictwaybillColumns.getDtdt_code()))
			objToppredictwaybillTR.setPwb_destination(objPredictwaybillColumns.getDtdt_code());		
		if (!StringUtility.isNull(objPredictwaybillColumns.getPwbck_code()))
			objToppredictwaybillTR.setCk_code(objPredictwaybillColumns.getPwbck_code());		
		
		objToppredictwaybillTR.setPwb_dutypaidsign(objPredictwaybillColumns.getPwbpwb_dutypaidsign());
		if ("YES".equals(objPredictwaybillColumns.getPwbpwb_dutypaidsign())) {
			objToppredictwaybillTR.setPwb_dutypaidsign("Y");
		} else if ("NO".equals(objPredictwaybillColumns.getPwbpwb_dutypaidsign())) {
			objToppredictwaybillTR.setPwb_dutypaidsign("N");
		}		
		
		String strPwbscode = "CTS";
		if (!StringUtility.isNull(objPredictwaybillColumns.getPwbspwbs_code())) {
			strPwbscode = objPredictwaybillColumns.getPwbspwbs_code();
		}
		objToppredictwaybillTR.setPwbs_code(strPwbscode);
	}
	
}
