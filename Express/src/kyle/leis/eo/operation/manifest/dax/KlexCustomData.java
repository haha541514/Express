package kyle.leis.eo.operation.manifest.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillforklexColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillforklexCondition;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;

public class KlexCustomData {
	
	public String buildXMLData(HousewaybillforklexCondition objHFKC) throws Exception {
		List listResults = HousewaybillDemand.queryForKlex(objHFKC);
		if (listResults == null || listResults.size() < 1)
			return "";
		
		StringBuffer sbXMLData = new StringBuffer();
		sbXMLData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sbXMLData.append("\r\n");
		
		int iMAWBHawbCount = 0;
		int iBagHawbSeq = 1;
		StringBuffer sbBagXMLData = new StringBuffer();
		StringBuffer sbHawbXMLData = new StringBuffer();
		
		for (int i = 0; i < listResults.size(); i++) {
			HousewaybillforklexColumns objHWFKColumns = (HousewaybillforklexColumns)listResults.get(i);
			if (StringUtility.isNull(objHWFKColumns.getMawblabelcode()) ||
					StringUtility.isNull(objHWFKColumns.getCons2()))
				continue;
			
			iMAWBHawbCount++;
			HousewaybillforklexColumns objNextHWFKColumns = null;
			if (i < listResults.size() - 1)
				objNextHWFKColumns = (HousewaybillforklexColumns)listResults.get(i + 1);
			
			String strHawbXMLData = buildHAWBData(objHWFKColumns, iBagHawbSeq);
			
			// 上下不同的袋号
			if (objNextHWFKColumns == null || 
					!objNextHWFKColumns.getCons2().equals(objHWFKColumns.getCons2())) {
				// 增加袋跟运单的XML数据
				// 
				sbBagXMLData.append("  <CONS Number=\"" + objHWFKColumns.getCons2() + "\">");
				sbBagXMLData.append("\r\n");
				
				sbHawbXMLData.append(strHawbXMLData);
				/*
				if (StringUtility.isNull(sbHawbXMLData.toString())) {
					sbHawbXMLData.append(strHawbXMLData);
					// sbHawbXMLData.append("\r\n");
				}*/
				
				sbBagXMLData.append(sbHawbXMLData.toString());
				sbBagXMLData.append(" </CONS>");
				sbBagXMLData.append("\r\n");
				
				iBagHawbSeq = 1;
				sbHawbXMLData = new StringBuffer();
			} else {
				sbHawbXMLData.append(strHawbXMLData);
				// sbHawbXMLData.append("\r\n");
				iBagHawbSeq++;
			}
			// 上下不同的航空主单
			if (objNextHWFKColumns == null || 
					!objHWFKColumns.getMawblabelcode().equals(objNextHWFKColumns.getMawblabelcode())) {				
				if (StringUtility.isNull(sbBagXMLData.toString()) && 
						!StringUtility.isNull(sbHawbXMLData.toString())) {
					sbBagXMLData.append("  <CONS Number=\"" + objHWFKColumns.getCons2() + "\">");
					sbBagXMLData.append("\r\n");
					sbBagXMLData.append(sbHawbXMLData.toString());
					sbBagXMLData.append(" </CONS>");
					sbBagXMLData.append("\r\n");
					
					iBagHawbSeq = 1;
					sbHawbXMLData = new StringBuffer();
				}
				String strMAWBData = buildMAWBData(objHWFKColumns,
						iMAWBHawbCount);
				sbXMLData.append(strMAWBData);
				sbXMLData.append("\r\n");
				// 增加袋数据
				sbXMLData.append(sbBagXMLData.toString());
				sbXMLData.append("</MAWB>");
				sbXMLData.append("\r\n");				
				
				iMAWBHawbCount = 0;
				sbBagXMLData = new StringBuffer(); 
			}
		}
		return sbXMLData.toString();
	}
	
	private String buildMAWBData(HousewaybillforklexColumns objHWFKColumns,
			int iMAWBHawbCount) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<MAWB Number=\"");
		sb.append(objHWFKColumns.getMawblabelcode());
		sb.append("\"");
		
		sb.append(" Route=\"");
		sb.append(objHWFKColumns.getRoute());
		sb.append("\"");
		//
		sb.append(" RouteDate=\"");
		sb.append(objHWFKColumns.getDbwadd_date().substring(0, 10));
		sb.append("\"");		
		
		sb.append(" RouteLegDestination=\"");
		sb.append(splitActualCity(objHWFKColumns.getRoutedest()));
		sb.append("\"");		
		
		sb.append(" OriginLocation=\"");
		sb.append(objHWFKColumns.getOriginlocation());
		sb.append("\"");
		
		sb.append(" DestinationLocation=\"");
		sb.append(splitActualCity(objHWFKColumns.getRoutedest()));
		sb.append("\"");		
		
		sb.append(" HAWBCount=\"");
		sb.append(iMAWBHawbCount);
		sb.append("\">");	
		
		return sb.toString();
	}
	
	private String buildHAWBData(HousewaybillforklexColumns objHWFKColumns,
			int iBagHawbCount) throws Exception {
		StringBuffer sb = new StringBuffer();
		
		sb.append("    <HAWB>\r\n");
		sb.append("      <ID>" + iBagHawbCount + "</ID>\r\n");
		sb.append("      <Number>" + objHWFKColumns.getCwcw_serverewbcode() + "</Number>\r\n");
		sb.append("      <OriginCountry>CN</OriginCountry>\r\n");
		sb.append("      <ExportCountry>CN</ExportCountry>\r\n");
		sb.append("      <DestinationCountry>" + splitActualCity(objHWFKColumns.getCddtdt_hubcode()) + "</DestinationCountry>\r\n");
		sb.append("      <ShipperCompany>" + objHWFKColumns.getHwhw_shippercompany() + "</ShipperCompany>\r\n");
		sb.append("      <ShipperrName>" + objHWFKColumns.getHwhw_shippername() + "</ShipperrName>\r\n");
		sb.append("      <ShipperCountry>CN</ShipperCountry>\r\n");
		sb.append("      <ShipperState />\r\n");
		sb.append("      <ShipperCity>" + objHWFKColumns.getShdtdt_ename() + "</ShipperCity>\r\n");
		sb.append("      <ShipperPostal>" + objHWFKColumns.getHwhw_shipperpostcode() + "</ShipperPostal>\r\n");
		sb.append("      <ShipperAddress1>" + objHWFKColumns.getHw_shipperaddress1() + "</ShipperAddress1>\r\n");
		sb.append("      <ShipperAddress2 />\r\n");
		sb.append("      <ShipperPhone>" + objHWFKColumns.getHwhw_shippertelephone() + "</ShipperPhone>\r\n");
		sb.append("      <ShipperEmail />\r\n");
		sb.append("      <ShipperWeb />\r\n");
		sb.append("      <ConsigneeCompany>" + objHWFKColumns.getHwhw_consigneecompany() + "</ConsigneeCompany>\r\n");
		sb.append("      <ConsigneeName>" + objHWFKColumns.getHwhw_consigneename() + "</ConsigneeName>\r\n");
		sb.append("      <ConsigneeCountry>" + splitActualCity(objHWFKColumns.getCddtdt_hubcode()) + "</ConsigneeCountry>\r\n");
		sb.append("      <ConsigneeState />\r\n");
		sb.append("      <ConsigneeCity>" + objHWFKColumns.getHwhw_consigneecity() + "</ConsigneeCity>\r\n");
		sb.append("      <ConsigneePostal>" + objHWFKColumns.getHwhw_consigneepostcode() + "</ConsigneePostal>\r\n");
		sb.append("      <ConsigneeAddress1>" + objHWFKColumns.getHw_consigneeaddress1() + "</ConsigneeAddress1>\r\n");
		sb.append("      <ConsigneeAddress2 />\r\n");
		sb.append("      <ConsigneePhone>" + objHWFKColumns.getHwhw_consigneetelephone() + "</ConsigneePhone>\r\n");
		sb.append("      <ConsigneeEmail />\r\n");
		sb.append("      <ShipmentDSK>" + objHWFKColumns.getCargoinfocname() + "</ShipmentDSK>\r\n");
		//
		sb.append("      <Pieces>" + objHWFKColumns.getCwcw_pieces() + "</Pieces>\r\n");
		//
		sb.append("      <WGT>" + formatNumber(objHWFKColumns.getCwcw_grossweight()) + "</WGT>\r\n");
		
		sb.append("      <ChargeableWeight>" + formatNumber(objHWFKColumns.getCwcw_serverchargeweight()) + "</ChargeableWeight>\r\n");
		sb.append("      <WeightCode>KG</WeightCode>\r\n");
		
		String strLength = "0";
		String strWidth = "0";
		String strHeight = "0";
		List listCargoInfo = CorewaybillpiecesDemand.load(objHWFKColumns.getCwcw_code());
		if (listCargoInfo != null && listCargoInfo.size() > 0) {
			CorewaybillpiecesColumns objCBPColumns = (CorewaybillpiecesColumns)listCargoInfo.get(0);
			strLength = objCBPColumns.getCpcplength();
			strWidth = objCBPColumns.getCpcpwidth();
			strHeight = objCBPColumns.getCpcpheight();
		}
		
		sb.append("      <SizeA>" + strLength + "</SizeA>\r\n");
		sb.append("      <SizeB>" + strWidth + "</SizeB>\r\n");
		sb.append("      <SizeC>" + strHeight + "</SizeC>\r\n");
		sb.append("      <OriginAmount>" + objHWFKColumns.getTotalcargoinfo() + "</OriginAmount>\r\n");
		sb.append("      <OriginCurrency>" + objHWFKColumns.getCargocurrency() + "</OriginCurrency>\r\n");
		sb.append("    </HAWB>\r\n");
		
		return sb.toString();
	}	
	
	private String splitActualCity(String strCity) {
		if (strCity.indexOf("-") < 0)
			return strCity;
		return strCity.substring(1);
	}
	
	public String formatNumber(String strNumber) {
		BigDecimal obj = new BigDecimal(strNumber);
		return obj.toString();
	}
	
	public static void main(String[] args) {
		KlexCustomData objKCD = new KlexCustomData();
		System.out.println(objKCD.formatNumber(".5"));
	}
}
