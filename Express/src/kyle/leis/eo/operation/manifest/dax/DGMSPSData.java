package kyle.leis.eo.operation.manifest.dax;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillCondition;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.specialtype.da.SpecialtypeColumns;
import kyle.leis.eo.operation.specialtype.dax.SpecialtypeDemand;
import kyle.leis.es.company.channel.da.ChannelColumns;
import kyle.leis.es.company.channel.da.ChanneladdressColumns;
import kyle.leis.es.company.channel.dax.ChannelDemand;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class DGMSPSData extends ADGMSPSData {
	
	public String build(BatchwaybillColumns objBWColumns, ChannelColumns objChannelColumns) throws Exception {
		StringBuffer sbSPSText = new StringBuffer();
		ChanneladdressColumns objCAColumns = ChannelDemand.loadChanneladdress(objBWColumns.getChnchncode());
		// 获得运单信息
		HousewaybillCondition objHWBCondition = new HousewaybillCondition();
		objHWBCondition.setDbwlabelcode(objBWColumns.getBwbwlabelcode());
		List listResults = HousewaybillDemand.query(objHWBCondition);
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
		
		String strSystemPE = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("QQYX") &&
				!StringUtility.isNull(objBWColumns.getBwbwremark())) {
			sbSPSText.append(objBWColumns.getBwbwremark() + "|");
		} else {
			sbSPSText.append("|"); // bol_consignment_note
		}
		
		String customerLabel = objChannelColumns.getLflfcode();
		
		sbSPSText.append(listResults.size() + "|");//票数
		
		if ("C_DGMPGB".equals(customerLabel)) {
			sbSPSText.append("||\r\n");
		} else {
			sbSPSText.append("USERS|V1.20|LEIS\r\n");
		}
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
//			收/发件人信息
			ForinputallColumns objFIAColumns = HousewaybillDemand.load(objHousewaybillColumns.getHwcwcode());
			String[] conAddress = null;
			if (StringUtility.isNull(objFIAColumns.getHwconsigneeaddressex())) {
				conAddress = parseAddress(objFIAColumns.getHwconsigneeaddress1() + 
				objFIAColumns.getHwconsigneeaddress2() + objFIAColumns.getHwconsigneeaddress3(), 3, 49);
			} else {
				conAddress = parseAddress(StringUtility.buildFromByte(objFIAColumns.getHwconsigneeaddressex(), "utf-8"), 3, 49);
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
			
			if ("C_DGMP".equals(customerLabel)) {
				sbSPSText.append(strProduct + "|");
				sbSPSText.append("PKD|");
				sbSPSText.append("|");
			} else if("C_DGMG".equals(customerLabel)){
				sbSPSText.append(strProduct + "|");
				sbSPSText.append("PPS|");
				sbSPSText.append("|");
			} else if ("C_DGMPGB".equals(customerLabel)) {
				sbSPSText.append(strProduct + "|");
				sbSPSText.append("PLT|");
				sbSPSText.append("|");
			} else {
				throw new RuntimeException("错误的客户标签[" + customerLabel + "]!");
			}
			sbSPSText.append(objHousewaybillColumns.getCwcwcustomerewbcode() + "|");
			sbSPSText.append("|||||||");
			//packageid
			sbSPSText.append(objHousewaybillColumns.getCwcwserverewbcode() + "|");
			// dsp tracking number（转单号，服务商号）
			if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("QQYX") &&
					"C_DGMP".equals(objChannelColumns.getLflfcode()))
				sbSPSText.append("|");
			else if ("C_DGMPGB".equals(customerLabel))
				sbSPSText.append("|");
			else
				sbSPSText.append(objHousewaybillColumns.getCwcwserverewbcode() + "|");
			
			String weight = new BigDecimal(objHousewaybillColumns.getCwcwserverchargeweight())
								.multiply(new BigDecimal("1000")).toString();
			if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("QQYX")) {
				weight = new BigDecimal(weight).divide(new BigDecimal("1"), 2, 2).toString();
			}
			sbSPSText.append(weight +  "|"); // WEIGHT
			if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("QQYX"))
				sbSPSText.append("GM|");
			else
				sbSPSText.append("G|");
			// WEIGHT UNIT
			sbSPSText.append("|||||||||");
			sbSPSText.append("CM|"); // dim_uom
			List<?> corewaybillpieces = CorewaybillpiecesDemand.load(objHousewaybillColumns.getHwcwcode());
			if (corewaybillpieces.isEmpty() || "C_DGMPGB".equals(customerLabel)) {
				sbSPSText.append("||||");
			} else {
				//ContainerRef1，袋号
				sbSPSText.append(((CorewaybillpiecesColumns)corewaybillpieces.get(0)).getCpcpbaglabelcode() + "||||");
			}
			// 发票信息
			//ForinputallCondition objFInputAllC = new ForinputallCondition();
			//objFInputAllC.setCustomerewbcode(objHousewaybillColumns.getCwcwcustomerewbcode());
			List listCargo = CargoInfoDemand.queryByCwCode(objHousewaybillColumns.getHwcwcode());
			//InputAllQReturn objIAQR = HousewaybillDemand.queryInput(objFInputAllC);
			//List listCargo = objIAQR.getCargoInfoResults();
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
			
			if (strDeclaredcontent.length() < 3) {
				sbSPSText.append("错误,品名必须大于3位|");//Package Description
				sbSPSText.append("错误,品名必须大于3位||");// Package Description - Export
			} else {
				sbSPSText.append(StringUtility.splitMaxLength(strDeclaredcontent,30) + "|");//Package Description
				sbSPSText.append(StringUtility.splitMaxLength(strDeclaredcontent,30) + "||");// Package Description - Export
			}
			if ("USD".equals(strDeclaredcurrency) && 
					((new BigDecimal(strDeclaredvalue).compareTo(new BigDecimal("0")) <= 0) || 
							(new BigDecimal(strDeclaredvalue).compareTo(new BigDecimal("1000")) > 0))) {
				sbSPSText.append("错误,申报价值必须大于0不能小于1000美金|DDU|||");
			} else {
				if ("C_DGMPGB".equals(customerLabel)) {
					SpecialtypeColumns special = SpecialtypeDemand.load(objFIAColumns.getCwcode(), "A0201");
					if (special == null)
						sbSPSText.append(strDeclaredvalue + "|DDU|||");
					else
						sbSPSText.append(strDeclaredvalue + "|DDP|||");
				} else {
					sbSPSText.append(strDeclaredvalue + "|DDU|||");
				}
			}
			sbSPSText.append(strDeclaredcurrency + "|");// ISO Currency Code
			sbSPSText.append("|||||||||");
			if ("C_DGMPGB".equals(customerLabel))
				sbSPSText.append("|");
			else
				sbSPSText.append("WS|");//Workshare Indicator
			sbSPSText.append("|||||\r\n");
			/**********************************RcdAddress***********************************/
			// 收件人地址
			sbSPSText.append("RcdAddress|");
			sbSPSText.append("CON|");
			
			sbSPSText.append(StringUtility.splitMaxLength(StringUtility.replaceWhenNull(objFIAColumns.getHwconsigneecompany(), ""),30) + "|");
			if (StringUtility.isNull(objFIAColumns.getHwconsigneenameex())) {
				sbSPSText.append(StringUtility.splitMaxLength(objFIAColumns.getHwconsigneename(),30) +  "|");
			} else {
				sbSPSText.append(StringUtility.splitMaxLength(StringUtility.buildFromByte(objFIAColumns.getHwconsigneenameex(), "utf-8"),30) +  "|");
			}
			sbSPSText.append(conAddress[0] + "|");
			sbSPSText.append(conAddress[1] + "|");
			sbSPSText.append(StringUtility.splitMaxLength(conAddress[2],29) + "|");
			
			if (StringUtility.isNull(objFIAColumns.getHwConsigneecity())) {
				sbSPSText.append("错误,城市不能为空|");
			} else {
				sbSPSText.append(StringUtility.splitMaxLength(objFIAColumns.getHwConsigneecity(),30) + "|");
			}
			
			try {
				String strStatecode = DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity(), 
						"", DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()),
						objFIAColumns.getHwconsigneepostcode());
				sbSPSText.append(StringUtility.replaceWhenNull(strStatecode, "") + "|"); //州
			} catch (Exception ex) {
				sbSPSText.append("|"); //州
			}
			
			if (StringUtility.isNull(objFIAColumns.getHwconsigneepostcode())) {
				sbSPSText.append("错误,邮编不能为空|");
			} else if (objFIAColumns.getHwconsigneepostcode().length() < 4 && objFIAColumns.getHwconsigneepostcode().length() > 11) {
				sbSPSText.append("错误,邮编长度必须为4-11位|");
			} else {
				sbSPSText.append(objFIAColumns.getHwconsigneepostcode() + "|");
			}
			sbSPSText.append(DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()) + "|");
			sbSPSText.append(DistrictDemand.getCountryEnameByCity(objFIAColumns.getDtcode()) +  "|");
			
			if (StringUtility.isNull(objFIAColumns.getHwconsigneetelephone()))
				sbSPSText.append("错误,电话不能为空|");
			else
				sbSPSText.append(StringUtility.splitMaxLength(objFIAColumns.getHwconsigneetelephone(), 20) + "|");
			
			sbSPSText.append("|\r\n");
			// 发件人地址
			if ("C_DGMPGB".equals(customerLabel)) {
				
				String[] shpAddress = parseAddress(objFIAColumns.getHwshipperaddress1() + 
						objFIAColumns.getHwshipperaddress2() + 
						objFIAColumns.getHwshipperaddress3(), 
						3, 49);
				
				sbSPSText.append("RcdAddress|");
				sbSPSText.append("SHP|");
				sbSPSText.append(objFIAColumns.getHwshippercompany() + "|");
				sbSPSText.append(objFIAColumns.getHwshippername() +  "|");
				sbSPSText.append(shpAddress[0] + "|");
				sbSPSText.append(shpAddress[1] + "|");
				sbSPSText.append(StringUtility.splitMaxLength(shpAddress[2], 29) + "|");
					
				sbSPSText.append(DistrictDemand.getDtenameByDtcode(objFIAColumns.getHwDtcodeshipper()) + "|");
				sbSPSText.append("|"); //州
				sbSPSText.append(objFIAColumns.getHwshipperpostcode() + "|");
				sbSPSText.append(DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getHwDtcodeshipper()) + "|");
				sbSPSText.append(DistrictDemand.getCountryEnameByCity(objFIAColumns.getHwDtcodeshipper()) +  "|");
				sbSPSText.append(objFIAColumns.getHwshippertelephone() + "|");
				sbSPSText.append("|\r\n");
			}
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








