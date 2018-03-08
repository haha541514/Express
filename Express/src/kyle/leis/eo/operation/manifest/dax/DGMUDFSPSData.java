package kyle.leis.eo.operation.manifest.dax;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallCondition;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillCondition;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.es.company.channel.da.ChannelColumns;
import kyle.leis.es.company.channel.da.ChanneladdressColumns;
import kyle.leis.es.company.channel.dax.ChannelDemand;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class DGMUDFSPSData extends ADGMSPSData {
	
	public String build(BatchwaybillColumns objBWColumns, ChannelColumns objChannelColumns) throws Exception {
		StringBuffer sbSPSText = new StringBuffer();
		ChanneladdressColumns objCAColumns = ChannelDemand.loadChanneladdress(objBWColumns.getChnchncode());
		// 获得运单信息
		HousewaybillCondition objHWBCondition = new HousewaybillCondition();
		objHWBCondition.setDbwlabelcode(objBWColumns.getBwbwlabelcode());
		List listResults = HousewaybillDemand.query(objHWBCondition);
		//HousewaybillColumns housewaybillColumns = (HousewaybillColumns) listResults.get(0);
		
		// 开始导出数据
		Date objAdddate = DateFormatUtility.getStandardDate(objBWColumns.getBwadddate());
//		String strManifestID = objChannelColumns.getChnchnpaymentaccount() 
//								+ DateFormatUtility.getDateString(objAdddate, "yyyyMMddHHmmss");
		/******************************RcdHeader部分***********************************/
		sbSPSText.append("RcdHeader|");
		sbSPSText.append("1.3|");
		sbSPSText.append(/*strManifestID + */"|");
		sbSPSText.append(DateFormatUtility.getDateString(objAdddate, "yyyy-MM-dd") + "|");
		sbSPSText.append(DateFormatUtility.getDateString(objAdddate, "HH:mm:ss") + "|");
		sbSPSText.append("|"); // bol_consignment_note
		sbSPSText.append(listResults.size() + "|");//票数
		sbSPSText.append("USERS|V1.20|LEIS\r\n");
		/************************************RcdPickupHeader部分*****************************/
		sbSPSText.append("RcdPickupHeader|");
//		sbSPSText.append(DateFormatUtility.getDateString(objAdddate, "yyyy-MM-dd") + "|");
//		sbSPSText.append(DateFormatUtility.getDateString(objAdddate, "HH:mm:ss") + "|");
//		sbSPSText.append(objFIAColumns.getHwconsigneecompany() + "|");
//		sbSPSText.append("|");// attention 
//		String[] astrAddress = parseAddress(objFIAColumns.getHwconsigneeaddress1() + 
//				objFIAColumns.getHwconsigneeaddress2() + objFIAColumns.getHwconsigneeaddress3(), 2, 39);
//		sbSPSText.append(astrAddress[0] + "|");
//		sbSPSText.append(astrAddress[1] + "|");
//		sbSPSText.append(objFIAColumns.getHwConsigneecity() + "|");
//		sbSPSText.append(DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity(), 
//				"", DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()),
//				objFIAColumns.getHwconsigneepostcode()) + "|"); // 州
//		sbSPSText.append(objFIAColumns.getHwconsigneepostcode() + "|");
//		sbSPSText.append(objFIAColumns.getHwconsigneetelephone() + "|");
//		sbSPSText.append("");// pickupemail
		sbSPSText.append("||||||||||");
		sbSPSText.append("\r\n");
		/*********************************************RcdShipDetail****************************/		
//		String[] shpAddress = parseAddress(objFIAColumns.getHwshipperaddress1() + 
//				objFIAColumns.getHwshipperaddress2() + objFIAColumns.getHwshipperaddress3(), 3, 49);
//		BigDecimal objSumChargeweight = new BigDecimal("0");
		for (int i = 0; i < listResults.size(); i++) {
			HousewaybillColumns objHousewaybillColumns = (HousewaybillColumns)listResults.get(i);
			//收/发件人信息
			if (objHousewaybillColumns.getCwcwcustomerewbcode().equals("2007245434"))
				objHousewaybillColumns.setCwcwcustomerewbcode("2007245434");
			ForinputallColumns objFIAColumns = HousewaybillDemand.load(objHousewaybillColumns.getHwcwcode());
			//String[] conAddress = parseAddress(StringUtility.buildFromByte(objFIAColumns.getHwconsigneeaddressex(), "utf-8"), 3, 49);
			String[] conAddress = null;
			if (StringUtility.isNull(objFIAColumns.getHwconsigneeaddressex())) {
				conAddress = parseAddress(objFIAColumns.getHwconsigneeaddress1() + 
				objFIAColumns.getHwconsigneeaddress2() + objFIAColumns.getHwconsigneeaddress3(), 3, 39);
			} else {
				conAddress = parseAddress(StringUtility.buildFromByte(objFIAColumns.getHwconsigneeaddressex(), "utf-8"), 3, 39);
			}			
			
			sbSPSText.append("RcdShipDetail|");
			sbSPSText.append(objChannelColumns.getChnchnpaymentaccount() + "|");
			sbSPSText.append(objCAColumns.getChnachnaprocessingaddress1() + "|");
			// 16盎司以上为83，以下为82
//			String strChargeweight = objHousewaybillColumns.getCwcwserverchargeweight();
//			BigDecimal objStandardRate = new BigDecimal("35.2739619");
//			BigDecimal objChargeweight = objStandardRate.multiply(new BigDecimal(strChargeweight)).divide(new BigDecimal("1"), 2, 4);
//			objSumChargeweight = objSumChargeweight.add(objChargeweight);
			String strProduct = "";
//			String customerLabel = objChannelColumns.getLflfcode();
//			if ("C_DGMP".equals(customerLabel)) {
//				sbSPSText.append(strProduct + "|");
//				sbSPSText.append("PKD|");
//				sbSPSText.append("|");
//			} else if("C_DGMG".equals(customerLabel)){
//				sbSPSText.append(strProduct + "|");
//				sbSPSText.append("PPS|");
//				sbSPSText.append("|");
//			} else {
//				throw new RuntimeException("错误的客户标签[" + customerLabel + "]!");
//			}
			sbSPSText.append(strProduct + "|");
			sbSPSText.append("PLT|");
			sbSPSText.append("|");
			sbSPSText.append(objHousewaybillColumns.getCwcwcustomerewbcode() + "|");
			sbSPSText.append("|||||||");
			sbSPSText.append(objHousewaybillColumns.getCwcwserverewbcode() + "|");// packageid
			sbSPSText.append("|");// dsp tracking number（转单号，服务商号）
			String weight = new BigDecimal(objHousewaybillColumns.getCwcwserverchargeweight())
								.multiply(new BigDecimal("1000")).toString();
			sbSPSText.append(weight +  "|"); // WEIGHT
			sbSPSText.append("G|");// WEIGHT UNIT
			sbSPSText.append("|||||||||");
			sbSPSText.append("CM|"); // dim_uom
//			List<?> corewaybillpieces = CorewaybillpiecesDemand.load(objHousewaybillColumns.getHwcwcode());
//			if (corewaybillpieces.isEmpty()) {
//				sbSPSText.append("||||");
//			} else {
//				//ContainerRef1，袋号
//				sbSPSText.append(((CorewaybillpiecesColumns)corewaybillpieces.get(0)).getCpcpbaglabelcode() + "||||");
//			}
			sbSPSText.append("||||");
			// 发票信息
			ForinputallCondition objFInputAllC = new ForinputallCondition();
			objFInputAllC.setCustomerewbcode(objHousewaybillColumns.getCwcwcustomerewbcode());
			InputAllQReturn objIAQR = HousewaybillDemand.queryInput(objFInputAllC);
			List listCargo = objIAQR.getCargoInfoResults();
			String strDeclaredvalue = "0.00";
			String strDeclaredcurrency = "USD";
			String strDeclaredcontent = "DOC";
			Pattern pt = Pattern.compile("[^a-zA-Z_0-9]");
			if (listCargo != null && listCargo.size() > 0) {
				BigDecimal objDeclaredvalue = new BigDecimal("0");
				strDeclaredcontent = "";
				for (int j = 0; j < listCargo.size(); j++) {
					CargoinfoColumns objCIC = (CargoinfoColumns) listCargo.get(j);
					objDeclaredvalue = objDeclaredvalue.add(new BigDecimal(objCIC
							.getCicitotalprice()));
					strDeclaredcurrency = objCIC.getCkckcode();
					strDeclaredcontent = strDeclaredcontent + pt.matcher(objCIC.getCiciename()).replaceAll("")
							+ ",";
				}
				if (!StringUtility.isNull(strDeclaredcontent)
						&& strDeclaredcontent.length() > 90)
					strDeclaredcontent = strDeclaredcontent.substring(0, 89);
				strDeclaredvalue = objDeclaredvalue.divide(new BigDecimal("1"), 2,
						2).toString();
			}
			sbSPSText.append(strDeclaredcontent + "|");//Package Description
			sbSPSText.append(strDeclaredcontent + "||");// Package Description - Export
			sbSPSText.append(strDeclaredvalue + "|DDP|||");
			sbSPSText.append(strDeclaredcurrency + "|");// ISO Currency Code
			sbSPSText.append("|||||||||");
			sbSPSText.append("WS|");//Workshare Indicator
			sbSPSText.append("|||||\r\n");
			/**********************************RcdAddress***********************************/
			// 收件人地址
			sbSPSText.append("RcdAddress|");
			sbSPSText.append("CON|");
			sbSPSText.append(objFIAColumns.getHwconsigneecompany() + "|");
			//sbSPSText.append(StringUtility.buildFromByte(objFIAColumns.getHwconsigneenameex(), "utf-8") +  "|");
			if (StringUtility.isNull(objFIAColumns.getHwconsigneenameex())) {
				sbSPSText.append(objFIAColumns.getHwconsigneename() +  "|");
			} else {
				sbSPSText.append(StringUtility.buildFromByte(objFIAColumns.getHwconsigneenameex(), "utf-8") +  "|");
			}
			sbSPSText.append(conAddress[0] + "|");
			sbSPSText.append(conAddress[1] + "|");
			sbSPSText.append(conAddress[2] + "|");
			sbSPSText.append(objFIAColumns.getHwConsigneecity() + "|");
			sbSPSText.append(DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity(), 
					"", DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()),
					objFIAColumns.getHwconsigneepostcode()) + "|"); //州
			sbSPSText.append(objFIAColumns.getHwconsigneepostcode() + "|");
			sbSPSText.append(DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()) + "|");
			sbSPSText.append(DistrictDemand.getCountryEnameByCity(objFIAColumns.getDtcode()) +  "|");
			sbSPSText.append(objFIAColumns.getHwconsigneetelephone() + "|");
			sbSPSText.append("|\r\n");
			// 发件人地址
//			sbSPSText.append("RcdAddress|");
//			sbSPSText.append("SHP|");
//			sbSPSText.append(objFIAColumns.getHwshippercompany() + "|");
//			sbSPSText.append(objFIAColumns.getHwshippername() +  "|");
//			sbSPSText.append(shpAddress[0] + "|");
//			sbSPSText.append(shpAddress[1] + "|");
//			sbSPSText.append(shpAddress[2] + "|");
//			sbSPSText.append(DistrictDemand.getDtenameByDtcode(objFIAColumns.getHwDtcodeshipper()) + "|");
//			sbSPSText.append("|"); //州
//			sbSPSText.append(objFIAColumns.getHwshipperpostcode() + "|");
//			sbSPSText.append(DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getHwDtcodeshipper()) + "|");
//			sbSPSText.append(DistrictDemand.getCountryEnameByCity(objFIAColumns.getHwDtcodeshipper()) +  "|");
//			sbSPSText.append(objFIAColumns.getHwshippertelephone() + "|");
//			sbSPSText.append("|\r\n");
			/*********************************RcdCommodityDetail*************************/
			if (listCargo != null && !listCargo.isEmpty()) {
				for (Object object : listCargo) {
					CargoinfoColumns objCIC = (CargoinfoColumns) object;
					sbSPSText.append("RcdCommodityDetail|");
					sbSPSText.append("CN|"); //country Of Origin
					sbSPSText.append(objCIC.getCicipieces() + "|");
					sbSPSText.append("|");
					
					sbSPSText.append(pt.matcher(objCIC.getCiciename()).replaceAll("") + "|");
					sbSPSText.append(objCIC.getCiciunitprice() + "|");
					
					//if (StringUtility.isNull(objCIC.getCicihscode()))
						sbSPSText.append("1111111111|");
					//else
					//	sbSPSText.append(objCIC.getCicihscode() + "|");					
					
					sbSPSText.append("||||||||||||||||\r\n");
				}
			}
		}
		buildFile(objBWColumns.getBwbwlabelcode(),
				sbSPSText.toString());		
		return sbSPSText.toString();
	}

}








