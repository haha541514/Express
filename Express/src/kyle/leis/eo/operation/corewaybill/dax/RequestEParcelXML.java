package kyle.leis.eo.operation.corewaybill.dax;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeColumns;
import kyle.leis.es.company.shipperconsignee.dax.ShipperconsigneeDemand;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class RequestEParcelXML extends RequestXMLEX{

	@Override
	public String buildRequestXML(ForinputallColumns objFIAColumns,
			List listCargo,
			List listPieces,
			PromptUtilityCollection objPUCollection)
			throws Exception {
		StringBuilder xmlStr = new StringBuilder();
		xmlStr.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:gen=\"http://www.auspost.com.au/Schema/ProductandServiceFulfilment/LodgementManagement/generateLabel:v1\">");
		xmlStr.append("<soap:Header/>");
		xmlStr.append("<soap:Body>");
		xmlStr.append("<gen:get>");
		xmlStr.append("<Generate>");
		// InterfaceHeader
		xmlStr.append("<InterfaceHeader>");
		xmlStr.append("<InterfaceName>LabelPrintingService</InterfaceName>");
		xmlStr.append("<InterfaceVersion>0.1</InterfaceVersion>");
		xmlStr.append("<MessageType>Request</MessageType>");
		xmlStr.append("<BusinessReferenceID>" + DateFormatUtility.getDateString(new Date(), "yyyyMMddHHmmssSSS") + "</BusinessReferenceID>");
		xmlStr.append("<SourceSystemID>L7</SourceSystemID>");
		xmlStr.append("<SourceInformation>MySystem</SourceInformation>");
		xmlStr.append("<Timestamp>" + DateFormatUtility.getDateString(new Date(), "yyyy-MM-dd'T'HH:mm:ss") + "</Timestamp>");
		xmlStr.append("</InterfaceHeader>");
		// ServiceHeader
		xmlStr.append("<ServiceHeader>");
		xmlStr.append("<RequestType>PDF</RequestType>");
		xmlStr.append("<RequesterId>" + objFIAColumns.getCwcustomerewbcode() + "</RequesterId>");
//		xmlStr.append("<LabelMessage/>");
		xmlStr.append("</ServiceHeader>");
		// LabelGroup
		xmlStr.append("<LabelGroup>");
		xmlStr.append("<Layout>THERMAL LABEL-1PP</Layout>");
		xmlStr.append("<Branding>true</Branding>");
//		xmlStr.append("<LeftOffset>7</LeftOffset>");
//		xmlStr.append("<TopOffset>7</TopOffset>");
		xmlStr.append("<Label>");
		xmlStr.append("<TemplateName>EPARCEL</TemplateName>");
//		xmlStr.append("<InternationalPrintList>LABEL</InternationalPrintList>");
		// xmlStr.append("<ArticleId>" + objFIAColumns.getCwcustomerewbcode() + "</ArticleId>");
		xmlStr.append("<Barcode/>");
		String dthubCode = DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getDtcode());
		String MLID = null;
		if ("SYD".equals(dthubCode)) {
			MLID = "25G";
		} else if ("MEL".equals(dthubCode)) {
			MLID = "25F";
		} else if ("PER".equals(dthubCode)) {
			MLID = "25J";
		} else if ("BNE".equals(dthubCode)) {
			MLID = "25H";
		}
		if (StringUtility.isNull(MLID)) {
			return "E_001_目的地只能是SYD、MEL、PER、BEN中的一个";
		}
		xmlStr.append("<Source>" + MLID +"</Source>"); // MLID
//		xmlStr.append("<OrderRef>" + objFIAColumns.getCwcustomerewbcode() + "</OrderRef>");
		String strCargoInfoename = "";
		if (listCargo != null && !listCargo.isEmpty()) {
			for (Object obj : listCargo) {
				CargoinfoColumns cargoInfo = (CargoinfoColumns) obj;
				strCargoInfoename = strCargoInfoename + ";" + cargoInfo.getCiciename();
			}
		}
		if (strCargoInfoename.startsWith(";")){
			strCargoInfoename = strCargoInfoename.substring(1);
		}
		if (strCargoInfoename.length() > 50){
			strCargoInfoename = strCargoInfoename.substring(0, 49);
		}
		xmlStr.append("<CustomerRef1>" + strCargoInfoename + "</CustomerRef1>");
		xmlStr.append("<CustomerRef2>" + objFIAColumns.getHwshippercompany() + "</CustomerRef2>");
		// xmlStr.append("<ConsignmentId>" + objFIAColumns.getCwcustomerewbcode() + "</ConsignmentId>");
		xmlStr.append("<ArticleCount>1</ArticleCount>");
		xmlStr.append("<TotalArticles>" + objFIAColumns.getCwpieces() + "</TotalArticles>");
		xmlStr.append("<Product>00093</Product>");
		xmlStr.append("<DeliverySignatureCapture>True</DeliverySignatureCapture>");
		xmlStr.append("<PickupSignatureCapture>True</PickupSignatureCapture>");
		//xmlStr.append("<PartialDeliveryAllowed>false</PartialDeliveryAllowed>");
		xmlStr.append("<PostagePaidIndicator>true</PostagePaidIndicator>");
		// 包裹特点
		xmlStr.append("<ParcelCharacteristics>");
		String strWeight = objFIAColumns.getTransfervolumeweight();
		if (StringUtility.isNull(objFIAColumns.getTransfervolumeweight()) ||
				new BigDecimal(objFIAColumns.getTransfervolumeweight()).equals(new BigDecimal("0")))
			strWeight = objFIAColumns.getCwtransfergrossweight();
		// 发票
		//String strDeclaredvalue = "0.00";
		String strDeclaredcontent = "DOC";
		if (listCargo != null && listCargo.size() > 0) {
			BigDecimal objDeclaredvalue = new BigDecimal("0");
			strDeclaredcontent = "";
			for (int i = 0; i < listCargo.size(); i++) {
				CargoinfoColumns objCIC = (CargoinfoColumns)listCargo.get(i);
				objDeclaredvalue = objDeclaredvalue.add(new BigDecimal(objCIC.getCicitotalprice()));
				strDeclaredcontent = strDeclaredcontent + objCIC.getCiciename() + ",";
			}
			if (!StringUtility.isNull(strDeclaredcontent) && 
					strDeclaredcontent.length() > 90)
				strDeclaredcontent = strDeclaredcontent.substring(0, 89);
			//strDeclaredvalue = objDeclaredvalue.divide(new BigDecimal("1"), 2, 2).toString();
		}
		xmlStr.append("<DeliveryInstructions>" + strDeclaredcontent + "</DeliveryInstructions>");
		xmlStr.append("<DangerousGoodsIndicator>false</DangerousGoodsIndicator>");
//		xmlStr.append("<PickupInstructions/>");
//		xmlStr.append("<DangerousGoodsIndicator>false</DangerousGoodsIndicator>");
//		xmlStr.append("<HeavyGoodIndicator>true</HeavyGoodIndicator>");
//		xmlStr.append("<Fragile>false</Fragile>");
        // xmlStr.append("<Contents>");
		// xmlStr.append("<ContentQuantity/>");
		// xmlStr.append("<ContentWeight/>");
		// xmlStr.append("<ContentUnitValue/>");
		// xmlStr.append("<TotalContentValue/>");
		// xmlStr.append("</Contents>");
//		xmlStr.append("<TotalConsignmentValue>" + strDeclaredvalue + "</TotalConsignmentValue>");
//		xmlStr.append("<ExportClearanceNumber></ExportClearanceNumber>"); // 出口清关号码
//		xmlStr.append("<Height>0</Height>");
//		xmlStr.append("<Length>0</Length>");
//		xmlStr.append("<Width>0</Width>");
		
		// 重量平均
		//BigDecimal obj = new BigDecimal(strWeight);
		//BigDecimal objDivideValue = obj.divide(new BigDecimal(objFIAColumns.getCwpieces()), 2, 1);		
		//xmlStr.append("<Weight>" + objDivideValue.toString() + "</Weight>");
		
		xmlStr.append("<Weight>" + getMaxGrossweight(objFIAColumns) + "</Weight>");
		xmlStr.append("</ParcelCharacteristics>");
		// 收件人信息
		xmlStr.append("<RecipientAddress>");
		xmlStr.append("<Name>" + objFIAColumns.getHwconsigneename() + "</Name>");
		xmlStr.append("<AddressLine>" + splitRAddress(objFIAColumns, 512, 1)[0] + "</AddressLine>");
		xmlStr.append("<Suburb>" + objFIAColumns.getHwConsigneecity() + "</Suburb>"); // 郊区
		xmlStr.append("<State>" + DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity(), 
				//DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getDtcode()),
				"",
				DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()),
				objFIAColumns.getHwconsigneepostcode()) + "</State>"); // 收件人州
		xmlStr.append("<Postcode>" + StringUtility.replaceWhenNull(objFIAColumns.getHwconsigneepostcode(), "").replace("-", "") + "</Postcode>");
		xmlStr.append("<CountryCode>" + DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()) + "</CountryCode>");
		xmlStr.append("<Phone>" + objFIAColumns.getHwconsigneetelephone() + "</Phone>");
		xmlStr.append("</RecipientAddress>");
		// 发件人信息
		// 
		ShipperconsigneeColumns objSCColumns = ShipperconsigneeDemand.load("EP_" + dthubCode);
		if (objSCColumns == null) {
			return "E_001_请先设置EP_" + dthubCode + "的发件人信息";
		}
		xmlStr.append("<SenderAddress>");
		xmlStr.append("<Name>" + objSCColumns.getScscname() + "</Name>");
		xmlStr.append(" <CompanyName>" + objSCColumns.getScsccompanyname() + "</CompanyName>");
		xmlStr.append("<AddressLine>" + objSCColumns.getScscaddress1() + "</AddressLine>");
		xmlStr.append("<Suburb>" + objSCColumns.getScscaddress2() + "</Suburb>"); // 郊区
		xmlStr.append("<State>" + objSCColumns.getScscaddress3() + "</State>");
		xmlStr.append("<Postcode>" + objSCColumns.getScscpostcode() + "</Postcode>");
		xmlStr.append("<CountryCode>" + objSCColumns.getScsccitycode() + "</CountryCode>");
		xmlStr.append("<Phone>" + objSCColumns.getScsctelephone() + "</Phone>");
		xmlStr.append("</SenderAddress>");
		xmlStr.append("</Label>");
		xmlStr.append("</LabelGroup>");
		xmlStr.append("</Generate>");
		xmlStr.append("</gen:get>");
		xmlStr.append("</soap:Body>");
		xmlStr.append("</soap:Envelope>");

		return xmlStr.toString();
	}
	
	private String getMaxGrossweight(ForinputallColumns objFIAColumns) throws Exception {
		List listCWPColumns = CorewaybillpiecesDemand.load(objFIAColumns.getCwcode());
		String strChargeweight = objFIAColumns.getCwgrossweight();
		
		if (listCWPColumns != null && listCWPColumns.size() >= 2) {
			strChargeweight = "0";
			for (int i = 0; i < listCWPColumns.size(); i++) {
				CorewaybillpiecesColumns corewaybill = (CorewaybillpiecesColumns)listCWPColumns.get(i);
				String strGrossweight = corewaybill.getCpcpgrossweight();
				if (new BigDecimal(strGrossweight).compareTo(new BigDecimal(strChargeweight)) > 0)
					strChargeweight = strGrossweight;
			}
		}
		BigDecimal obj = new BigDecimal(strChargeweight);
		obj = obj.divide(new BigDecimal("1.25"), 2, 1);
		if (obj.compareTo(new BigDecimal("0")) <= 0)
			return "0.5";
		return obj.toString();
	}
	
}
