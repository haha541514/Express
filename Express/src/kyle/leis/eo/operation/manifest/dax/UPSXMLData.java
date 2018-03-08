package kyle.leis.eo.operation.manifest.dax;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class UPSXMLData {
	

	public String build(String strCwcode) throws Exception {
		ForinputallColumns objFIAColumns = HousewaybillDemand.load(strCwcode);
		List listCargoInfo = CargoInfoDemand.queryByCwCode(strCwcode);
		List listCWPieces = CorewaybillpiecesDemand.load(strCwcode);
		if (objFIAColumns == null) return "";
		if (listCWPieces == null || 
				listCWPieces.size() < 1) 
			return objFIAColumns.getCwserverewbcode() + "件数信息为空，无法生成Xml数据";
		return build(objFIAColumns, listCargoInfo, listCWPieces);
		}
		
	
	private String build(ForinputallColumns objFIAColumns ,
			List listCargoInfo,
			List listCWPieces
			) throws Exception {
        StringBuffer sb = new StringBuffer();
		CargoinfoColumns cargo = (CargoinfoColumns) listCargoInfo.get(0);
		CorewaybillpiecesColumns cwb = (CorewaybillpiecesColumns) listCWPieces.get(0);
		
		/**注释部分为不明意义字段*/
		sb.append("<OpenShipments xmlns=\"x-schema:OpenShipments.xdr\">\n")
		  .append("<OpenShipment ShipmentOption=\"\" ProcessStatus=\"\">\n")
		  
		  .append("<Shipper>\n")
	   // .append("<UpsAccountNumber>").append("").append("</UpsAccountNumber>\n")
		  .append("</Shipper>\n")
		  
		  .append("<ShipTo>\n")
		  .append("<CustomerID>").append("").append("</CustomerID>\n")
		  .append("<CompanyOrName>").append(objFIAColumns.getHwshippercompany()).append("</CompanyOrName>\n")
		  .append("<Attention>").append(transferColumnsData(objFIAColumns.getHwelectricmark())).append("</Attention>\n")
		  .append("<Address1>").append(objFIAColumns.getHwshipperaddress1()).append("</Address1>\n")
		  .append("<Address2>").append(objFIAColumns.getHwshipperaddress2()).append("</Address2>\n")
		  .append("<Address3>").append(objFIAColumns.getHwshipperaddress3()).append("</Address3>\n")
		  .append("<CityOrTown>").append(objFIAColumns.getDthubcode_Cwodt()).append("</CityOrTown>\n")
		  .append("<CountryTerritory>").append(objFIAColumns.getDthubcode()).append("</CountryTerritory>\n")
		  .append("<PostalCode>").append(objFIAColumns.getHwshipperpostcode()).append("</PostalCode>\n")
      //  .append("<StateProvinceCounty>").append(objFIAColumns.getHwConsigneecity()).append("</StateProvinceCounty>\n")
		  .append("<Telephone>").append(objFIAColumns.getHwconsigneetelephone()).append("</Telephone>\n")
		  .append("</ShipTo>\n");
		  
	   sb.append("<ThirdParty>\n")
	     .append("<CustomerID>").append("").append("</CustomerID>\n")
	     .append("<CompanyName>").append(objFIAColumns.getHwshippercompany()).append("</CompanyName>\n")
	     .append("<CompanyOrName>").append(objFIAColumns.getHwshippercompany()).append("</CompanyOrName>\n")
	     .append("<ContactPerson>").append(objFIAColumns.getHwshippername()).append("</ContactPerson>\n")
	     .append("<Attention>").append(transferColumnsData(objFIAColumns.getHwelectricmark())).append("</Attention>")
	  // .append("<AddressLine1>").append(objFIAColumns.getHwshipperaddress1()).append("</AddressLine1>\n")
	     .append("<Address1>").append(objFIAColumns.getHwshipperaddress1()).append("</Address1>\n")
	  // .append("<AddressLine2>").append(objFIAColumns.getHwshipperaddress1()).append("</AddressLine2>\n")
	     .append("<Address2>").append(objFIAColumns.getHwshipperaddress2()).append("</Address2>\n")
	 //  .append("<AddressLine3>").append("</AddressLine3>\n")
	     .append("<Address3>").append(objFIAColumns.getHwshipperaddress3()).append("</Address3>\n")
	     .append("<City>").append(objFIAColumns.getDthubcode_Cwodt()).append("</City>\n")
	     .append("<CityOrTown>").append(objFIAColumns.getDthubcode_Cwodt()).append("</CityOrTown>\n")
	     .append("<CountryCode>").append(objFIAColumns.getDthubcode()).append("</CountryCode>\n")
	     .append("<CountryTerritory>").append(objFIAColumns.getDthubcode()).append("</CountryTerritory>\n")
	     .append("<PostalCode>").append(objFIAColumns.getHwshipperpostcode()).append("</PostalCode>\n")
	     .append("<StateOrProvince>").append(objFIAColumns.getHwConsigneecity()).append("</StateOrProvince>\n")
	  // .append("<StateProvinceCounty>").append("</StateProvinceCounty>\n")
	     .append("<Residential>").append(objFIAColumns.getHwConsigneecity()).append("</Residential>\n")
	 //  .append("<ResidentialIndicator>").append("</ResidentialIndicator>\n")
	     .append("<CustomerIDNumber>").append("</CustomerIDNumber>\n")
	     .append("<Phone>").append(objFIAColumns.getHwshippertelephone()).append("</Phone>\n")
	     .append("<Telephone>").append(objFIAColumns.getHwconsigneetelephone()).append("</Telephone>\n")
	  // .append("<UpsAccountNumber>").append("</UpsAccountNumber>\n")
         .append("</ThirdParty>\n");
         
		 sb.append("<ThirdPartyReceiver>\n")	   
			    .append("<CustomerID>").append("</CustomerID>\n")
			    .append("<CompanyOrName>").append(objFIAColumns.getHwconsigneecompany()).append("</CompanyOrName>\n")
			    .append("<Attention>").append(transferColumnsData(objFIAColumns.getHwelectricmark())).append("</Attention>\n")
			    .append("<Address1>").append(objFIAColumns.getHwconsigneeaddress1()).append("</Address1>\n")
			    .append("<Address2>").append(objFIAColumns.getHwconsigneeaddress2()).append("</Address2>\n")
			    .append("<Address3>").append(objFIAColumns.getHwconsigneeaddress3()).append("</Address3>\n")
			    .append("<CityOrTown>").append(objFIAColumns.getHwConsigneecity()).append("</CityOrTown>\n")
			    .append("<CountryTerritory>").append(DistrictDemand.getCountryEnameByCity(objFIAColumns.getDtcode())).append("</CountryTerritory>\n")
			    .append("<PostalCode>").append(objFIAColumns.getDtcode()).append("</PostalCode>\n")
			    .append("<StateProvinceCounty>").append(objFIAColumns.getHwConsigneecity()).append("</StateProvinceCounty>\n")
			    .append("<Telephone>").append(objFIAColumns.getHwconsigneetelephone()).append("</Telephone>\n")
			    .append("<FaxNumber>").append(transferColumnsData(objFIAColumns.getHwconsigneefax())).append("</FaxNumber>\n")
			//  .append("<UpsAccountNumber>").append("</UpsAccountNumber>\n")
			    .append("</ThirdPartyReceiver>\n")
			
				.append("<ShipmentInformation>\n")
			//	.append("<ServiceType>")SV.append("</ServiceType>\n")
			//	.append("<PackageType>").append(objFIAColumns.getCtcode()).append("</PackageType>\n")
				.append("<NumberOfPackages>").append(objFIAColumns.getCwpieces()).append("</NumberOfPackages>\n")
				.append("<ShipmentActualWeight>").append(objFIAColumns.getCwtransferchargeweight()).append("</ShipmentActualWeight>\n")
			//	.append("<DescriptionOfGoods>").append("</DescriptionOfGoods>\n")
			//	.append("<Reference1>").append("</Reference1>\n")
			//	.append("<Reference2>").append("</Reference2>\n")
			//	.append("<BillTransportationTo>").append("</BillTransportationTo>\n")
			//	.append("<BillDutyTaxTo>").append("</BillDutyTaxTo>\n")
				.append("</ShipmentInformation>\n")
				
				.append("<InternationalDocumentation>\n")
				.append("<InvoiceCurrencyCode>").append(cargo.getCkckcode()).append("</InvoiceCurrencyCode>\n")
			//	.append("<InvoiceReasonForExport>").append("</InvoiceReasonForExport>\n")
				.append("</InternationalDocumentation>\n")
						
				.append("<Goods>\n")
				.append("<PartNumber>").append(cwb.getCpcplabelcode()).append("</PartNumber>\n")
				.append("<DescriptionOfGood>").append(cargo.getCiciename()).append("</DescriptionOfGood>\n")
			//	.append("<Inv-NAFTA-TariffCode>").append("").append("</Inv-NAFTA-TariffCode>\n")
				.append("<Inv-NAFTA-CO-CountryTerritoryOfOrigin>").append(objFIAColumns.getDthubcode_Cwodt()).append("</Inv-NAFTA-CO-CountryTerritoryOfOrigin>\n")
				.append("<InvoiceUnits>").append(cargo.getCicipieces()).append(cargo.getCiciunitprice()).append("</InvoiceUnits>\n")
				.append("<InvoiceUnitOfMeasure>"+"PCS").append("</InvoiceUnitOfMeasure>\n") //暂未找到字段
				.append("<Invoice-SED-UnitPrice>").append(cargo.getCiciunitprice()).append("</Invoice-SED-UnitPrice>\n")
				.append("<InvoiceCurrencyCode>").append(cargo.getCkckcode()).append("</InvoiceCurrencyCode>\n")
				.append("</Goods>\n")
                
				.append("<Goods>\n")
				.append("<PartNumber>").append(cwb.getCpcplabelcode()).append("</PartNumber>\n")
				.append("<DescriptionOfGood>").append(cargo.getCiciename()).append("</DescriptionOfGood>\n")
			//	.append("<Inv-NAFTA-TariffCode>").append("</Inv-NAFTA-TariffCode>\n")
				.append("<Inv-NAFTA-CO-CountryTerritoryOfOrigin>").append(objFIAColumns.getDthubcode_Cwodt()).append("</Inv-NAFTA-CO-CountryTerritoryOfOrigin>\n")
				.append("<InvoiceUnits>").append(cargo.getCicipieces()).append("</InvoiceUnits>\n")
				.append("<InvoiceUnitOfMeasure>"+"PCS").append("</InvoiceUnitOfMeasure>\n")
				.append("<Invoice-SED-UnitPrice>").append(cargo.getCiciunitprice()).append("</Invoice-SED-UnitPrice>\n")
				.append("<InvoiceCurrencyCode>").append(cargo.getCkckcode()).append("</InvoiceCurrencyCode>\n")
				.append("</Goods>\n")
				
				.append("<Goods>")
				.append("<PartNumber>").append(cwb.getCpcplabelcode()).append("</PartNumber>\n")
				.append("<DescriptionOfGood>").append(cargo.getCiciename()).append("</DescriptionOfGood>\n")
			//	.append("<Inv-NAFTA-TariffCode>").append("</Inv-NAFTA-TariffCode>\n")
				.append("<Inv-NAFTA-CO-CountryTerritoryOfOrigin>").append(objFIAColumns.getDthubcode_Cwodt()).append("</Inv-NAFTA-CO-CountryTerritoryOfOrigin>\n")
				.append("<InvoiceUnits>").append(cargo.getCicipieces()).append("</InvoiceUnits>\n")
				.append("<InvoiceUnitOfMeasure>"+"PCS").append("</InvoiceUnitOfMeasure>\n")
				.append("<Invoice-SED-UnitPrice>").append(cargo.getCiciunitprice()).append("</Invoice-SED-UnitPrice>\n")
				
				.append("<InvoiceCurrencyCode>").append(cargo.getCkckcode()).append("</InvoiceCurrencyCode>\n")
				.append("</Goods>\n")
		        .append("</OpenShipment>\n")
                .append("</OpenShipments>"); 
		return sb.toString();  
		
	}

		public void stringToXML(String str) throws IOException {
			   SAXReader saxReader = new SAXReader();
			   Document document;
			   try {
			    document =  saxReader.read(new ByteArrayInputStream(str.getBytes()));   
			    OutputFormat format = OutputFormat.createPrettyPrint();
			    /** 指定XML字符集编码 */
			    format.setEncoding("windows-1252");
			    /** 将document中的内容写入文件中 */
			    XMLWriter writer = new XMLWriter(new FileWriter(new File("UPS_ThirdParty.xml")),format);
			    writer.write(document); 
			    System.out.println("success");   //成功路径为：\express 刷新下即可
			    writer.close();
			   } catch (DocumentException e) {
			    e.printStackTrace();
			   }

			}

	
	public static void main(String[ ]args){
		UPSXMLData xd = new UPSXMLData();
		try {
			xd.stringToXML(xd.build("4777"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String transferColumnsData(String strColumnData) {
		if (StringUtility.isNull(strColumnData))
			return " ";
		return strColumnData;
	}
	
	
}
