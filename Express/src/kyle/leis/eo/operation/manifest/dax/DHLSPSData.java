package kyle.leis.eo.operation.manifest.dax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.eo.operation.corewaybillcode.dax.CorewaybillcodeDemand;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.es.company.supplier.dax.SupplierDemand;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiEnterpriseelement;

public class DHLSPSData {
	public String build(String strCwcode) throws Exception {
		ForinputallColumns objFIAColumns = HousewaybillDemand.load(strCwcode);
		List listCargoInfo = CargoInfoDemand.queryByCwCode(strCwcode);
		List listCWPieces = CorewaybillpiecesDemand.load(strCwcode);
		
		if (objFIAColumns == null) return "";
		
		if (listCWPieces == null || 
				listCWPieces.size() < 1) 
			return objFIAColumns.getCwserverewbcode() + "件数信息为空，无法生成SPS数据";
		// SLY大陆DHL的客户运单号要截取后8位号码
		String strMBCK_Code = objFIAColumns.getServerwbckbckcode();
		String strChncode = objFIAColumns.getChncode_Cwspchn();
		if (StringUtility.isNull(strChncode))
			return "";
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
		String strSsgcode = "";
		if (objTchnChannel.getTdiServerstructuregroup() != null)
			strSsgcode = objTchnChannel.getTdiServerstructuregroup().getSsgCode();
		// 根据规则重新获得客户单号
		objFIAColumns.setCwcustomerewbcode(CorewaybillcodeDemand.rebuildCustomerHAWBCode(objTchnChannel.getChnChawbprefix(),
				objFIAColumns.getCwcustomerewbcode()));	
		// 其他规则
		if (!StringUtility.isNull(strSsgcode) && strSsgcode.equals("DHL_HKMY")) {
			String strCustomerEwbcode = objFIAColumns.getCwcustomerewbcode();
			if (strCustomerEwbcode.length() > 6)
				strCustomerEwbcode = strCustomerEwbcode.substring(strCustomerEwbcode.length() - 6,
						strCustomerEwbcode.length());
			strCustomerEwbcode = "SV11-Y-" + strCustomerEwbcode;
			objFIAColumns.setCwcustomerewbcode(strCustomerEwbcode);
		}
		if ((!StringUtility.isNull(strMBCK_Code) && 
				strMBCK_Code.equals("DHLMCN")) ||
				(!StringUtility.isNull(strSsgcode) && 
						(strSsgcode.equals("DHL_NC") ||
								strSsgcode.equals("DHL_CNMY")))) {
			/*
			String strCustomerEwbcode = objFIAColumns.getCwcustomerewbcode();
			if (strCustomerEwbcode.length() > 8)
				strCustomerEwbcode = strCustomerEwbcode.substring(strCustomerEwbcode.length() - 8,
						strCustomerEwbcode.length());
			objFIAColumns.setCwcustomerewbcode(strCustomerEwbcode);
			*/
			objFIAColumns.setCwcustomerewbcode("");
		}
		String strResult = "";
		try {
			strResult = build(objFIAColumns, listCargoInfo, listCWPieces);
		} catch (Exception ex) {
			strResult = objFIAColumns.getCwserverewbcode() + "生成数据时错误" + ex.getMessage();
		}
		return strResult;
	}
	
	private String build(ForinputallColumns objFIAColumns, 
			List listCargoInfo,
			List listCWPieces) throws Exception {
		if (objFIAColumns == null || 
				StringUtility.isNull(objFIAColumns.getCocode_Cwsp())) 
			return "";
		
		listCargoInfo = filterCargo(listCargoInfo);
		
		StringBuffer sbSPSText = new StringBuffer();
		// SPS Version ID 1
		sbSPSText.append("SPS 2.02|");
		// Back End Type 2
		sbSPSText.append("SPS|");
		// Reference Data version number 3
		sbSPSText.append(" |");
		// Meter Number 4
		sbSPSText.append(" |");
		// Opunit 5
		sbSPSText.append(" |");
		// Schedule Number 6
		sbSPSText.append("0|");
		// Consignment Number 7
		sbSPSText.append(" |");
		// Shipment Number ber 8
		sbSPSText.append(objFIAColumns.getCwserverewbcode() + "|");
		// Origin IATA 9
		String strShipperHubcode = DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getHwDtcodeshipper());
		if (strShipperHubcode.length() > 3)
			strShipperHubcode = strShipperHubcode.substring(0, 3);
		
		sbSPSText.append(strShipperHubcode + "|");
		// Destination IATA 10
		if (StringUtility.isNull(objFIAColumns.getDthubcode()))
			sbSPSText.append(" |");
		else
			sbSPSText.append(objFIAColumns.getDthubcode() + "|");
		// Invoice Number 11
		String strManifestNumber = SupplierDemand.getManifestSeriesNumber(objFIAColumns.getCocode_Cwsp());
		if (StringUtility.isNull(strManifestNumber))
			sbSPSText.append("UNDEFINEFILENAME|");
		else
			sbSPSText.append(strManifestNumber + "|");
		// Shipper's Account Number 12
		TchnChannel objTCHN = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
		String strChnMasterAccount = objTCHN.getChnMasteraccount();
		String strPayAccount = objTCHN.getChnPaymentaccount();
		if (StringUtility.isNull(strChnMasterAccount)) 
			strChnMasterAccount = "000000000";
		sbSPSText.append(strChnMasterAccount + "|");
		// Shipper's Company Name 13
		sbSPSText.append(transferColumnsData(objFIAColumns.getHwshippercompany()) + "|");
		// Shipper's Contact Name 14
		sbSPSText.append(transferColumnsData(objFIAColumns.getHwshippername()) + "|");
		// Shipper's Address Line 1 15
		sbSPSText.append(cutColumnText(transferColumnsData(objFIAColumns.getHwshipperaddress1())) + "|");
		// Shipper's Address Line 2 15
		sbSPSText.append(cutColumnText(transferColumnsData(objFIAColumns.getHwshipperaddress2())) + "|");
		// Shipper's Address Line 3 15
		sbSPSText.append(cutColumnText(transferColumnsData(objFIAColumns.getHwshipperaddress3())) + "|");
		// Shipper's City 18 统一使用HONG KONG
		String strDtcodeshipperEname = DistrictDemand.getDtenameByDtcode(objFIAColumns.getHwDtcodeshipper());
		sbSPSText.append(strDtcodeshipperEname + "|");
		// Shipper's State 19
		sbSPSText.append(" |");
		// Shipper's Zip/Postal Code 20
		sbSPSText.append(transferColumnsData(objFIAColumns.getHwshipperpostcode()) + "|");
		// Shipper's Country Name 21
		sbSPSText.append(DistrictDemand.getCountryEnameByCity(objFIAColumns.getHwDtcodeshipper()) + "|");
		// Shipper's Country Code 22
		sbSPSText.append(DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getHwDtcodeshipper()) + "|");
		// Shipper's Reference    23
		sbSPSText.append(transferColumnsData(objFIAColumns.getCwcustomerewbcode()) + "|");
		// Shipper's Phone Number 24
		sbSPSText.append(transferColumnsData(objFIAColumns.getHwshippertelephone()) + "|");
		// Shipper's FAX Number 25
		sbSPSText.append(transferColumnsData(objFIAColumns.getHwshipperfax()) + "|");
		// Shipper's Telex Number 26
		sbSPSText.append(" |");
		// Shipper's Telex Answer-Back # 27
		sbSPSText.append(" |");
		// Shipper's Currency Code 28
		sbSPSText.append(" |");
		// Shipper's World Class Acct flag 29
		sbSPSText.append(" |");
		// Empty string Recipient Code 30
		sbSPSText.append(" |");
		// Recipient Account Number 31
		sbSPSText.append(" |");
		// Recipient Company Name 32
		sbSPSText.append(transferColumnsData(objFIAColumns.getHwconsigneecompany()) + "|");
		// Recipient Contact Name 33
		sbSPSText.append(transferColumnsData(objFIAColumns.getHwconsigneename()) + "|");
		// Recipient Address Line 1 34
		sbSPSText.append(cutColumnText(transferColumnsData(objFIAColumns.getHwconsigneeaddress1())) + "|");
		// Recipient Address Line 2 35
		sbSPSText.append(cutColumnText(transferColumnsData(objFIAColumns.getHwconsigneeaddress2())) + "|");
		// Recipient Address Line 3 36
		sbSPSText.append(cutColumnText(transferColumnsData(objFIAColumns.getHwconsigneeaddress3())) + "|");
		// Recipient City 37
		sbSPSText.append(transferColumnsData(objFIAColumns.getHwConsigneecity()) + "|");
		// Recipient's State 38  //洲/省
		sbSPSText.append(" |");
		// Recipient's Zip/Postal Code 39
		sbSPSText.append(transferColumnsData(splitBlankPostcode(DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()),
				objFIAColumns.getHwconsigneepostcode())) + "|");
		// Recipient's Country Name 40
		sbSPSText.append(DistrictDemand.getCountryEnameByCity(objFIAColumns.getDtcode()) + "|");
		// Recipient's Country Code 41
		sbSPSText.append(DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()) + "|");
		// Recipient's Phone Number 42
		sbSPSText.append(transferColumnsData(objFIAColumns.getHwconsigneetelephone()) + "|");
		// Recipient's FAX Number 43
		sbSPSText.append(transferColumnsData(objFIAColumns.getHwconsigneefax()) + "|");
		// Recipient's Telex Number 44
		sbSPSText.append(" |");
		// Recipient's Telex Answer-Back # 45
		sbSPSText.append(" |");
		// DHL's Recipient Table Ref. 46
		sbSPSText.append(" |");
		// Flag for Third Party Billing 47 
		// Y or N 暂时用 N
		if (!StringUtility.isNull(strChnMasterAccount) && 
				!StringUtility.isNull(strPayAccount) &&
				!strChnMasterAccount.equals(strPayAccount)) {
			sbSPSText.append("Y|");
			// Account Charge for shipment  48
			sbSPSText.append(strPayAccount + "|");			
		} else {
			sbSPSText.append("N|");
			// Account Charge for shipment  48
			sbSPSText.append(" |");
		}
		// Rate Table used   49
		sbSPSText.append(" |");
		// The external Product Code  2 characters for DPGM product 50 
		// 文件：D 包裹：P
		String strCtcode = objFIAColumns.getCtcode();
		strCtcode = transferCargoType(strCtcode.substring(1), objTCHN);
		if (strCtcode.equals("ESI"))
			sbSPSText.append("H|");
		else if (strCtcode.equals("DOX"))
			sbSPSText.append("D|");
		else 
			sbSPSText.append("P|");
		// The DHL internal Product Code 2 characters for DPGM product 51
		if (strCtcode.equals("ESI"))
			sbSPSText.append("H|");
		else if (strCtcode.equals("DOX"))
			sbSPSText.append("D|");
		else 
			sbSPSText.append("P|");		
		// Date SHIPMENT was entered 52
		sbSPSText.append(splitDateString(objFIAColumns.getHwsignindate()) + "|");
		// Time SHIPMENT was entered 53
		sbSPSText.append(splitTimeString(objFIAColumns.getEecode(),
				objFIAColumns.getHwDtcodeshipper(),
				objFIAColumns.getHwsignindate()) + "|");
		// Date SHIPMENT was complete 54
		if (StringUtility.isNull(objFIAColumns.getHwsignoutdate()))
			sbSPSText.append(splitDateString(objFIAColumns.getHwrecorddate()) + "|");
		else
			sbSPSText.append(splitDateString(objFIAColumns.getHwsignoutdate()) + "|");
		// Time SHIPMENT was complete 55
		if (StringUtility.isNull(objFIAColumns.getHwsignoutdate()))
			sbSPSText.append(splitTimeString(objFIAColumns.getHwrecorddate()) + "|");
		else
			sbSPSText.append(splitTimeString(objFIAColumns.getHwsignoutdate()) + "|");
		// Total pieces in shipment  56
		sbSPSText.append(transferColumnsData(objFIAColumns.getCwtransferpieces()) + "|");
		// One SHIPMENT# per piece flag 57 暂时用 Y
		sbSPSText.append("Y|");
		// Actual Total Shipment Weight 58 出货实重
		sbSPSText.append(transferWeight(objFIAColumns.getCwtransfergrossweight(), strCtcode) + "|");
		// Rounded (MFT) Total Ship. Wt.  59
		sbSPSText.append(transferWeight(objFIAColumns.getCwtransferchargeweight(), strCtcode) + "|");
		// Dimensional Weight Comp Factor 应该是重量系数 60
		sbSPSText.append("166|");
		// Total Dimensional Weight  61  中转体积重
		sbSPSText.append(transferWeight(objFIAColumns.getTransfervolumeweight(), strCtcode) + "|");
		// Total Declared Value      62
		String strSumCargoInfo = getSumCargoValue(listCargoInfo);
		if (strCtcode.equals("DOX"))
			strSumCargoInfo = "0";
		sbSPSText.append(strSumCargoInfo + "|");
		// Insurance Amount          63
		sbSPSText.append(transferColumnNumber(objFIAColumns.getHwinsurancevalue()) + "|");
		// Payment Method/Charge Type Empty string  64
		sbSPSText.append(" |");
		// PCheck Number Empty string 65
		sbSPSText.append(" |");
		// Credit Card Type Empty string 66
		sbSPSText.append(" |");
		// Credit Card Number Empty string 67
		sbSPSText.append(" |");
		// Credit Card Expiry Date Empty string 68
		sbSPSText.append(" |");
		// Amount Received Empty string 69
		sbSPSText.append(" |");
		// Special Instructions Code Empty string 70
		sbSPSText.append(" |");
		// Route Id Empty string 71
		sbSPSText.append(" |");
		// Cut-Off Code Empty string 72
		sbSPSText.append(" |");
		// Courier's Initials 73
		sbSPSText.append(" |");
		// Courier Pickup Date 74   出货日期
		if (StringUtility.isNull(objFIAColumns.getHwsignoutdate()))
			sbSPSText.append(splitDateString(objFIAColumns.getHwrecorddate()) + "|");
		else
			sbSPSText.append(splitDateString(objFIAColumns.getHwsignoutdate()) + "|");
		// Courier Pickup Time 75   出货时间
		if (StringUtility.isNull(objFIAColumns.getHwsignoutdate()))
			sbSPSText.append(splitDateString(objFIAColumns.getHwrecorddate()) + "|");
		else
			sbSPSText.append(splitTimeString(objFIAColumns.getHwsignoutdate()) + "|");
		// Customs Alert Date 76
		sbSPSText.append(" |");
		// Customs Alert Time 77
		sbSPSText.append(" |");
		// Label Printing Flag  'Y' or 'N' 78
		sbSPSText.append("Y|");
		// Pallet Flag  'Y' or 'N' 79 
		sbSPSText.append("N|");
		// COD Value COD Currency Code 80
		sbSPSText.append("0.00|");
		// Currency Code  81
		String strInsuranceCkcode = objFIAColumns.getHwinsurancecurrency();
		if (StringUtility.isNull(strInsuranceCkcode))
			strInsuranceCkcode = "";
		sbSPSText.append(strInsuranceCkcode + "|");
		// 品名和申报价值
		if (strCtcode.equals("DOX"))
			sbSPSText.append("1|DOC|");
		else
			sbSPSText.append(buildCargoNameInfo(listCargoInfo, objFIAColumns.getHwelectricmark()));
		// Number of Charge/Service entries 83
		sbSPSText.append("0|");
		// Number of Entries to Follow 84
		if (listCargoInfo == null || listCargoInfo.size() < 1)
			sbSPSText.append("1|");
		else
			sbSPSText.append(listCargoInfo.size() + "|");
		// Dutiable Flag 85 关税标志  包裹 Y 文件 N 
		if (strCtcode.equals("DOX"))
			sbSPSText.append("N|");
		else
			sbSPSText.append("Y|");
		// Shipper's Export License 86
		sbSPSText.append(" |");
		// Shipper's EIN/SSN or VAT # 87
		sbSPSText.append(" |");
		// Movement Certificate/IT/SAD 88
		sbSPSText.append(" |");
		// 06 Recipient Import License 89
		sbSPSText.append(" |");
		// Recipient EIN/SSN or VAT # 90
		sbSPSText.append(" |");
		// Terms of Trade   for ex DDU,DDP 91
		sbSPSText.append(" |");
		// Reason for Export Code 92
		if (strCtcode.equals("DOX"))
			sbSPSText.append(" |");
		else
			sbSPSText.append("P|");
		// 品名信息
		sbSPSText.append(buildCargoInfo(listCargoInfo, objFIAColumns, strCtcode));
		// Number of Entries to Follow
		sbSPSText.append("0|");
		// 件数信息
		sbSPSText.append(buildPiecesInfo(listCWPieces, strCtcode));
		return sbSPSText.toString();
	}
	
	private List<CargoinfoColumns> filterCargo(List listCargoInfo) {
		if (listCargoInfo == null || listCargoInfo.size() < 1)
			return null;
		List<CargoinfoColumns> listResult = new ArrayList<CargoinfoColumns>();
		for (int i = 0; i < listCargoInfo.size(); i++) {
			CargoinfoColumns objCargoinfoColumns = (CargoinfoColumns)listCargoInfo.get(i);
			if (objCargoinfoColumns.getCiciename().trim().startsWith("FREIGHT") ||
					objCargoinfoColumns.getCiciename().trim().endsWith("FREIGHT") ||
					objCargoinfoColumns.getCiciename().endsWith("CHARGE") || 
					objCargoinfoColumns.getCiciename().endsWith("FEE") || 
					objCargoinfoColumns.getCiciename().endsWith("COST"))
				continue;
			// 去掉特殊字符
			objCargoinfoColumns.setCiciename(objCargoinfoColumns.getCiciename().replaceAll("\r\n", ""));
			listResult.add(objCargoinfoColumns);
		}
		return listResult;
	}
	
	
	private String buildCargoNameInfo(List listCargoInfo,
			String strElectricRemark) {
		if (listCargoInfo == null || listCargoInfo.size() < 1)
			return "1|WPX";
		String strCiEname = "";
		/*
		StringBuffer sbAllCiEname = new StringBuffer();
		int iMaxCargosize = listCargoInfo.size();
		if (iMaxCargosize > 3)
			iMaxCargosize = 3;
		sbAllCiEname.append(iMaxCargosize + "|");
		
		for (int i = 0; i < iMaxCargosize; i++) {
			CargoinfoColumns objCargoinfoColumns = (CargoinfoColumns)listCargoInfo.get(i);
			strCiEname = "SAMPLE OF " + objCargoinfoColumns.getCiciename();
			if (!StringUtility.isNull(strElectricRemark) && 
					i == iMaxCargosize - 1)
				strCiEname = strCiEname + " (" + strElectricRemark + ")";
			if (strCiEname.length() > 29)
				strCiEname = strCiEname.substring(0, 29);
			sbAllCiEname.append(strCiEname + "|");
		}		
		return sbAllCiEname.toString();
		*/
		
		for (int i = 0; i < listCargoInfo.size(); i++) {
			CargoinfoColumns objCargoinfoColumns = (CargoinfoColumns)listCargoInfo.get(i);
			strCiEname = strCiEname + objCargoinfoColumns.getCiciename();			
			if (i < listCargoInfo.size() - 1)
				strCiEname = strCiEname + " AND ";
		}
		strCiEname = "SAMPLE OF " + strCiEname;
		if (!StringUtility.isNull(strElectricRemark))
			strCiEname = strCiEname + " (" + strElectricRemark + ")";
		if (strCiEname.endsWith(" AND "))
			strCiEname = strCiEname.substring(0, strCiEname.length() - 5);
		// 品名数量
		BigDecimal objCargoNumber = new BigDecimal(String.valueOf(strCiEname.length())).divide(new BigDecimal("29"), 0, 2);
		String strCargoNumber = objCargoNumber.toString();
		// 返回值
		StringBuffer sbAllCiEname = new StringBuffer();
		sbAllCiEname.append(strCargoNumber + "|");
		// 整合品名
		int iCargoNumber = Integer.parseInt(strCargoNumber);
		for (int i = 0; i < iCargoNumber; i++) {
			if (i < iCargoNumber - 1)
				sbAllCiEname.append(strCiEname.substring(i * 29, (i + 1) * 29) + "-|");
			else
				sbAllCiEname.append(strCiEname.substring(i * 29) + "|");
		}
		return sbAllCiEname.toString();
		
	}
	
	private String buildCargoInfo(List listCargoInfo, 
			ForinputallColumns objFIAColumns, 
			String strCtcode) {
		// 申报品名小于1或文件，则直接设置默认值，否则循环品名设置品名和申报价值
		if (listCargoInfo == null || listCargoInfo.size() < 1 || strCtcode.equals("DOX")) {
			return buildDoxCargoInfo(objFIAColumns, strCtcode);
		} 
		// 包裹则取品名单价、总价等
		StringBuffer sbSPSText = new StringBuffer(); 
		for (int i = 0; i < listCargoInfo.size(); i++) {	
			CargoinfoColumns objCargoinfoColumns = (CargoinfoColumns)listCargoInfo.get(i);
			/*
			if (objCargoinfoColumns.getCiciename().trim().startsWith("FREIGHT") ||
					objCargoinfoColumns.getCiciename().trim().endsWith("FREIGHT") ||
					objCargoinfoColumns.getCiciename().endsWith("CHARGE") || 
					objCargoinfoColumns.getCiciename().endsWith("FEE") || 
					objCargoinfoColumns.getCiciename().endsWith("COST"))
				continue;
			*/
			sbSPSText.append(objCargoinfoColumns.getCicipieces() + "|");
			sbSPSText.append(" |");
			// Unit Weight 3
			sbSPSText.append("0|");
			// eight unit of measure 4
			sbSPSText.append("KGS.|");
			// Number of decimals for weight 5	
			sbSPSText.append("2|");
			// Actual Dimensional Weight
			sbSPSText.append(transferWeight(objFIAColumns.getCwtransfergrossweight(), strCtcode) + "|");
			// Actual Dimensional Length
			sbSPSText.append("0|");
			// Actual Dimensional Height
			sbSPSText.append("0|");
			// Actual Dimensional Depth  9
			sbSPSText.append("0|");
			// Currency Code (USD, HKD, etc.)  10
			sbSPSText.append("USD|");
			// Number of Decimals for Price   11
			sbSPSText.append("2|");
			// Unit price  12
			sbSPSText.append(transferStandardColumnNumber(objCargoinfoColumns.getCiciunitprice()) + "|");
			// Country of Origin  13
			sbSPSText.append("CN|");
			// Description  14
			if (objCargoinfoColumns.getCiciename().length() > 10)
				sbSPSText.append("SAMPLE OF " + objCargoinfoColumns.getCiciename().substring(0, 10) + "|");
			else
				sbSPSText.append("SAMPLE OF " + objCargoinfoColumns.getCiciename() + "|");
			// 15 Harmonized/Commodity Code
			sbSPSText.append(" |");
			// 16 Manufacturer's Company Name
			sbSPSText.append(" |");			
			// 17 Manufacturer's Contact Name
			sbSPSText.append(" |");
			// 18 Manufacturer's Address 1
			sbSPSText.append(" |");
			// 19 Manufacturer's Address 2
			sbSPSText.append(" |");
			// 20 Manufacturer's Address 3
			sbSPSText.append(" |");
			// 21 Manufacturer's City
			sbSPSText.append(" |");			
			// 22 Manufacturer's State/Providence
			sbSPSText.append(" |");
			// 23 Manufacturer's Zip/Postal Code
			sbSPSText.append(" |");
			// 24 Manufacturer's Country Code
			sbSPSText.append("CN|");
			// 25
			sbSPSText.append(" |");
			// 26
			sbSPSText.append(" |");	
			// 27
			sbSPSText.append(" |");	
			// 28 Manufacturer's Telex Answer (THE END)
			sbSPSText.append(" |");				
		}
		return sbSPSText.toString();
	}
	
	private String buildDoxCargoInfo(ForinputallColumns objFIAColumns, 
			String strCtcode) {
		StringBuffer sbSPSText = new StringBuffer();
		sbSPSText.append("1|");
		sbSPSText.append(" |");
		// 实重
		sbSPSText.append(transferWeight(objFIAColumns.getCwtransfergrossweight(), strCtcode) + "|");
		// Currency Code 8
		sbSPSText.append("KGS.|");
		// Currency Code 9
		sbSPSText.append("2|");
		// 体积重
		sbSPSText.append(transferWeight(objFIAColumns.getTransfervolumeweight(), strCtcode) + "|");
		// Quantity Unit of Measure 2 0位小数
		sbSPSText.append("0|");
		// Quantity Unit of Measure 2
		sbSPSText.append("0|");
		// Actual Dimensional Depth  9
		sbSPSText.append("0|");
		// Currency Code (USD, HKD, etc.)  10
		sbSPSText.append("USD|");
		// Number of Decimals for Price   11 Invoice WPX
		sbSPSText.append("2|");
		// Unit price  12 
		sbSPSText.append("0.00|");
		// Country of Origin  13
		sbSPSText.append(" |");
		// Description  14
		sbSPSText.append(" |");
		// 15
		sbSPSText.append(" |");
		// 16
		sbSPSText.append(" |");			
		// 17
		sbSPSText.append(" |");			
		// 18
		sbSPSText.append(" |");
		// 19
		sbSPSText.append(" |");			
		// 20
		sbSPSText.append(" |");
		// 21
		sbSPSText.append(" |");			
		// 22
		sbSPSText.append(" |");
		// 23
		sbSPSText.append(" |");			
		// 24 Manufacturer's City  Alpha Numeric  Empty string
		sbSPSText.append(" |");
		// 25
		sbSPSText.append(" |");
		// 26
		sbSPSText.append(" |");			
		// 27
		sbSPSText.append(" |");
		// 28
		sbSPSText.append(" |");
		
		return sbSPSText.toString();
	}	
	
	private String buildPiecesInfo(List listCWPieces, String strCtcode) {
		if (listCWPieces == null || listCWPieces.size() < 1)
			return "0|";
		StringBuffer sbSPSText = new StringBuffer();
		sbSPSText.append(listCWPieces.size() + "|");
		for (int i = 0; i < listCWPieces.size(); i++) {
			CorewaybillpiecesColumns objCBPColumns = (CorewaybillpiecesColumns)listCWPieces.get(i);
			sbSPSText.append("JD01" + objCBPColumns.getCpcplabelcode() + "|");
			sbSPSText.append("0.00|");
			// Piece Volumetric Weight
			sbSPSText.append("0.00|");
			// Piece Volume
			sbSPSText.append("0.000|");
			// Piece Length
			sbSPSText.append("00000|");
			// Piece Width
			sbSPSText.append("00000|");
			// Piece Height
			sbSPSText.append("00000|");
			// Unit of Measurement
			sbSPSText.append("CM|");
			// Content Description
			sbSPSText.append(" |");
			// Package Type
			sbSPSText.append(strCtcode + "|");
			// Package Remarks
			sbSPSText.append(" |");
			// Number of shipper reference entries
			sbSPSText.append("0|");
		}
		return sbSPSText.toString();
	}
	
	private String transferColumnsData(String strColumnData) {
		if (StringUtility.isNull(strColumnData))
			return " ";
		return strColumnData;
	}
	
	private String transferColumnNumber(String strColumnNumber) {
		if (StringUtility.isNull(strColumnNumber))
			return "0";
		return strColumnNumber;
	}
	
	private String cutColumnText(String strColumnText) {
		if (strColumnText.length() > 30)
			strColumnText = strColumnText.substring(0, 30);
		return strColumnText;
	}
	
	
	private String transferCargoType(String strCtcode, TchnChannel objTCHN) {
		if (strCtcode.equals("DOX")) return strCtcode;
		// 包裹映射的名称
		String strWPXMapping = objTCHN.getChnWpxspsmappingname();
		if (StringUtility.isNull(strWPXMapping))
			return strCtcode;
		else
			return strWPXMapping;
	}
	
	private String transferWeight(String strWeight, String strCtcode) {
		if (StringUtility.isNull(strWeight))
			return "0.00";
		if (strCtcode.equals("DOX"))
			return "0.50";
		BigDecimal objWeight = new BigDecimal(strWeight);
		objWeight = objWeight.divide(new BigDecimal("1"), 2, 4);
		return objWeight.toString();
	}
	
	private String transferStandardColumnNumber(String strColumnNumber) {
		if (StringUtility.isNull(strColumnNumber))
			return "0.00";
		BigDecimal objWeight = new BigDecimal(strColumnNumber);
		objWeight = objWeight.divide(new BigDecimal("1"), 2, 4);
		return objWeight.toString();
	}
	
	public String getSumCargoValue(List listCargoInfo) {
		if (listCargoInfo == null || listCargoInfo.size() < 1)
			return "0";
		BigDecimal objSumCargovalue = new BigDecimal("0");
		for (int i = 0; i < listCargoInfo.size(); i++) {
			CargoinfoColumns objCargoinfoColumns = (CargoinfoColumns)listCargoInfo.get(i);
			/*
			if (objCargoinfoColumns.getCiciename().trim().startsWith("FREIGHT") ||
					objCargoinfoColumns.getCiciename().trim().endsWith("FREIGHT") ||
					objCargoinfoColumns.getCiciename().endsWith("CHARGE") || 
					objCargoinfoColumns.getCiciename().endsWith("FEE") || 
					objCargoinfoColumns.getCiciename().endsWith("COST"))
				continue;			
			*/
			objSumCargovalue = objSumCargovalue.add(new BigDecimal(objCargoinfoColumns.getCicitotalprice()));
		}
		return objSumCargovalue.toString();
	}
	
	private String splitDateString(String strDateTime) {
		String strDate = strDateTime.substring(0, 10);
		return strDate.replace("-", "");
	}
	
	private String splitBlankPostcode(String strConsigneeCoutryhubcode,
			String strPostcode) throws Exception {
		String strSystemPE = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("QQYX"))
			return strPostcode;
			
		if (!StringUtility.isNull(strPostcode) && 
				strPostcode.indexOf(" ") > 0 &&
				strConsigneeCoutryhubcode.equals("GB")) {
			return strPostcode.replaceAll(" ", "");
		}
		return strPostcode;
	}
	
	
	private String splitTimeString(String strEecode,
			String strDtcodeShipper,
			String strDateTime) throws Exception {
		TdiEnterpriseelement objTEE = TdiEnterpriseelementDC.loadByKey(strEecode);
		String strDate = strDateTime.substring(11, 16);
		strDate = strDate.replace(":", "");
		
		if (objTEE.getEeEsname().indexOf("LSD") >= 0 ||
				(objTEE.getEeEsname().indexOf("BA") >= 0 &&
						!"1119".equals(strDtcodeShipper))) {
		/*
		if (objTEE.getEeEsname().indexOf("LSD") >= 0) {
		*/
			if (strDate.substring(0, 2).compareTo("06") > 0)
				strDate = "03" + strDate.substring(2);
		}
		return strDate;
	}
	
	private String splitTimeString(String strDateTime) {
		String strDate = strDateTime.substring(11, 16);
		return strDate.replace(":", "");
	}
	
	
	public static void main(String[] args) {
		String str = "2012-01-02 20:23:00";
		String strDate = str.substring(11, 16);
		strDate = strDate.replace(":", "");
		if (strDate.substring(0, 2).compareTo("06") >= 0)
			strDate = "05" + strDate.substring(2);
		System.out.println(strDate);
		try {
			DHLSPSData objDHL = new DHLSPSData();
			str = objDHL.build("798899");
			System.out.println(str);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		
		/*
		String strSysdate = DateFormatUtility.getStandardSysdate();
		DHLSPSData objDHL = new DHLSPSData();
		System.out.println(objDHL.splitDateString(strSysdate));
		System.out.println(objDHL.splitTimeString(strSysdate));
		*/
	}
	
}
