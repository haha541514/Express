package kyle.leis.eo.operation.manifest.dax;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import kyle.leis.es.company.channel.da.ChannelColumns;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeColumns;
import kyle.leis.es.company.shipperconsignee.dax.ShipperconsigneeDemand;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class EparcelSPSData extends ADGMSPSData {

	@Override
	public String build(BatchwaybillColumns objBWColumns,
			ChannelColumns objChannelColumns) throws Exception {
		
		StringBuffer sbSPSText = new StringBuffer();
//		ChanneladdressColumns objCAColumns = ChannelDemand.loadChanneladdress(objBWColumns.getChnchncode());
		// 获得运单信息
		HousewaybillCondition objHWBCondition = new HousewaybillCondition();
		objHWBCondition.setDbwlabelcode(objBWColumns.getBwbwlabelcode());
		List listResults = HousewaybillDemand.query(objHWBCondition);
		
		
		// 开始导出数据
//		Date objAdddate = DateFormatUtility.getStandardDate(objBWColumns.getBwadddate());
		Date dete =new Date();
		
		
		/******************************SendPCMSManifest部分***********************************/
		
		sbSPSText.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		sbSPSText.append("<PCMS xmlns=\"http://www.auspost.com.au/xml/pcms\">\n");
		
		sbSPSText.append("<SendPCMSManifest>\n");
		
		/******************************SendPCMSManifest.<header>部分***********************************/

		sbSPSText.append("<header>\n");
		sbSPSText.append("<TransactionDateTime>"+DateFormatUtility.getDateString(dete, "yyyy-MM-dd")+"T"+DateFormatUtility.getDateString(dete, "HH:mm:ss")+"</TransactionDateTime>\n");
		sbSPSText.append("<TransactionId>"+"1"+"</TransactionId>\n");
		sbSPSText.append("<TransactionSequence>"+"1"+"</TransactionSequence>\n");
		sbSPSText.append("<ApplicationId>"+"MERCHANT"+"</ApplicationId>\n");
		sbSPSText.append("</header>\n");
		
		/******************************SendPCMSManifest.<body>部分***********************************/
		sbSPSText.append("<body>\n");
		Map<String, String> hm = new HashMap<String, String>();		
		String strMerchantLocationId = "";		
		String strMerchantAccount = "";
		final  String strSYD="25G";
		final  String strMEL="25F";
		final  String strPER="25J";
		final  String strBNE="25H";
		
		//String strTwbtwblabelcode = "";
		String strManifestNumber = objBWColumns.getBwbwcode();
		if (strManifestNumber.length() > 10)
			strManifestNumber = strManifestNumber.substring(strManifestNumber.length() - 10);
		
		for (int i = 0; i < listResults.size(); i++) {
		HousewaybillColumns objHousewaybillColumns = (HousewaybillColumns)listResults.get(i);
		ForinputallColumns objFIAColumns = HousewaybillDemand.load(objHousewaybillColumns.getHwcwcode());
		List listCWPColumns = CorewaybillpiecesDemand.load(objHousewaybillColumns.getHwcwcode());
		
		List<CargoinfoColumns> listCargoInfo = CargoInfoDemand.queryByCwCode(objHousewaybillColumns.getHwcwcode());
		/*
		TransportforcorewaybillColumns tfcwcolumns = TransportWaybill.loadByCwcode(objHousewaybillColumns.getHwcwcode());
		if (tfcwcolumns != null) {
			strTwbtwblabelcode = tfcwcolumns.getTwbtwblabelcode();
			if (strTwbtwblabelcode.indexOf("-") >= 0)
				strTwbtwblabelcode = strTwbtwblabelcode.substring(strTwbtwblabelcode.indexOf("-") + 1);
			if (strTwbtwblabelcode.length() > 10)
				strTwbtwblabelcode = strTwbtwblabelcode.substring(0, 10);
		}
		*/
		StringBuffer sbSPSManifestText = new StringBuffer();
		String dthubCode = DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getDtcode());
		
		if(dthubCode.equals("SYD")){
			strMerchantLocationId = strSYD;
			strMerchantAccount = "7543764";
		}else if(dthubCode.equals("MEL")){
			strMerchantLocationId = strMEL;
			strMerchantAccount = "7543340";		
		}else if(dthubCode.equals("PER")){
			strMerchantLocationId = strPER;
			strMerchantAccount = "7543780";
		}else if(dthubCode.equals("BNE")){
			strMerchantLocationId = strBNE;
			strMerchantAccount = "7543798";
		}
		
		String[] strCw=parseAddress(objHousewaybillColumns.getCwcwserverewbcode(),1,10);
		
		sbSPSManifestText.append("<PCMSConsignment>\n");
		sbSPSManifestText.append("<ConsignmentNumber>"+strCw[0]+"</ConsignmentNumber>\n");
		sbSPSManifestText.append("<ChargeCode>"+"7D55"+"</ChargeCode>\n");
		
		/******************************<body>.AdditionalServices部分***********************************/

//		sbSPSText.append("<AdditionalServices>\n");
//		sbSPSText.append("<ServiceCodeGroup>\n");
//		sbSPSText.append("<ServiceCodes>\n");
//		sbSPSText.append("<ServiceCode>"+"109"+"</ServiceCode>\n");
//		sbSPSText.append("</ServiceCodes>\n");
//		
//		sbSPSText.append("<ServiceCodes>\n");
//		sbSPSText.append("<ServiceCode>"+"104"+"</ServiceCode>\n");
//		sbSPSText.append("<ServiceQualifier>\n");
//		sbSPSText.append("<ServiceAttributeName>"+"DEL_DATE"+"</ServiceAttributeName>\n");
//		sbSPSText.append("<Date>"+"2011-07-17"+"</Date>\n");
//		sbSPSText.append("</ServiceQualifier>\n");
//		sbSPSText.append("</ServiceCodes>\n");
//		
//		sbSPSText.append("<ServiceCodes>\n");
//		sbSPSText.append("<ServiceCode>"+"105"+"</ServiceCode>\n");
//		sbSPSText.append("<ServiceQualifier>\n");
//		sbSPSText.append("<ServiceAttributeName>"+"DEL_TIME"+"</ServiceAttributeName>\n");
//		
//		sbSPSText.append("<TimePeriod>\n");
//		sbSPSText.append("<StartTime>"+"07:00:00"+"</StartTime>\n");
//		sbSPSText.append("</StartTime>"+"12:00:00"+"</EndTime>\n");
//		sbSPSText.append("</TimePeriod>\n");
//		sbSPSText.append("<TimePeriod>\n");
//		sbSPSText.append("<StartTime>"+"12:00:00"+"</StartTime>\n");
//		sbSPSText.append("</StartTime>"+"17:00:00"+"</EndTime>\n");
//		sbSPSText.append("</TimePeriod>\n");
//		
//		sbSPSText.append("</ServiceQualifier>\n");
//		sbSPSText.append("</ServiceCodes>\n");
//		sbSPSText.append("</ServiceCodeGroup>\n");
//		sbSPSText.append("</AdditionalServices>\n");
		
		double pieces = 0;
		double unit = 0;
		double total = 0;
		String strCargoInfoename = "";
		for (CargoinfoColumns cargoInfo : listCargoInfo) {
			pieces  += Double.valueOf(cargoInfo.getCicipieces());
			unit += Double.valueOf(cargoInfo.getCiciunitprice());
			total += Double.valueOf(cargoInfo.getCicitotalprice());
			strCargoInfoename = strCargoInfoename + ";" + cargoInfo.getCiciename();
		}
		if (strCargoInfoename.startsWith(";"))
			strCargoInfoename = strCargoInfoename.substring(1);
		if (strCargoInfoename.length() > 50)
			strCargoInfoename = strCargoInfoename.substring(0, 49);
		
		pieces = pieces/listCWPColumns.size();
		unit = unit/listCWPColumns.size();
		total = total/listCWPColumns.size();		
		
		sbSPSManifestText.append("<InternalChargebackAccount>"+"Finance"+"</InternalChargebackAccount>\n");
		sbSPSManifestText.append("<ReferenceNo1>"+strCargoInfoename+"</ReferenceNo1>\n");
		sbSPSManifestText.append("<ReferenceNo2>"+objFIAColumns.getHwshippercompany()+"</ReferenceNo2>\n");
		//收件人信息
		sbSPSManifestText.append("<DeliveryName>"+objFIAColumns.getHwconsigneename()+"</DeliveryName>\n");
		sbSPSManifestText.append("<DeliveryCompanyName>"+objFIAColumns.getHwconsigneecompany()+"</DeliveryCompanyName>\n");
		sbSPSManifestText.append("<EmailNotification>"+"N"+"</EmailNotification>\n");
		
		//sbSPSManifestText.append("<BusinessPartnerID>"+""+"</BusinessPartnerID>\n");
		
		String[] deliveryAddress1=parseAddress(objFIAColumns.getHwconsigneeaddress1(),1,40);
		String[] deliveryAddress2=parseAddress(objFIAColumns.getHwconsigneeaddress2(),1,40);
		String[] deliveryAddress3=parseAddress(objFIAColumns.getHwconsigneeaddress3(),1,40);
		sbSPSManifestText.append("<DeliveryAddressLine1>"+deliveryAddress1[0]+"</DeliveryAddressLine1>\n");
		sbSPSManifestText.append("<DeliveryAddressLine2>"+deliveryAddress2[0]+"</DeliveryAddressLine2>\n");
		sbSPSManifestText.append("<DeliveryAddressLine3>"+deliveryAddress3[0]+"</DeliveryAddressLine3>\n");
		sbSPSManifestText.append("<DeliveryAddressLine4>"+""+"</DeliveryAddressLine4>\n");
		sbSPSManifestText.append("<DeliveryPhoneNumber>"+objFIAColumns.getHwconsigneetelephone()+"</DeliveryPhoneNumber>\n");
		sbSPSManifestText.append("<DeliveryEmailAddress>"+""+"</DeliveryEmailAddress>\n");
		sbSPSManifestText.append("<DeliverySuburb>"+objFIAColumns.getHwConsigneecity()+"</DeliverySuburb>\n");
		sbSPSManifestText.append("<DeliveryStateCode>"+DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity(), 
				"", DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()),
				objFIAColumns.getHwconsigneepostcode())+"</DeliveryStateCode>\n");
		sbSPSManifestText.append("<DeliveryPostcode>"+objFIAColumns.getHwconsigneepostcode()+"</DeliveryPostcode>\n");
		
		//sbSPSManifestText.append("<DeliveryPointIdentifier>"+objFIAColumns.getCwcustomerewbcode()+"</DeliveryPointIdentifier>\n");
		String strEwbcode = objFIAColumns.getCwewbcode();
		String strDeliveryPointIdentifier = "";
		if (strEwbcode.indexOf("|") >= 0) {
			String[] astrEwbcode = strEwbcode.split("\\|");
			if (astrEwbcode != null && astrEwbcode.length >= 4) {
				strDeliveryPointIdentifier = astrEwbcode[2];
				if (!StringUtility.isNull(strDeliveryPointIdentifier) && strDeliveryPointIdentifier.length() >= 10)
					strDeliveryPointIdentifier = strDeliveryPointIdentifier.substring(2);
			}
		}
		sbSPSManifestText.append("<DeliveryPointIdentifier>"+strDeliveryPointIdentifier+"</DeliveryPointIdentifier>\n");
		
		sbSPSManifestText.append("<DeliveryCountryCode>"+DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode())+"</DeliveryCountryCode>\n");
		sbSPSManifestText.append("<DeliveryInstructions>"+""+"</DeliveryInstructions>\n");
		sbSPSManifestText.append("<IsInternationalDelivery>"+"false"+"</IsInternationalDelivery>\n");
		//发件人信息
		ShipperconsigneeColumns objSCColumns = ShipperconsigneeDemand.load("EP_" + dthubCode);
		if (objSCColumns == null) {
			sbSPSManifestText = new StringBuffer();
			sbSPSManifestText.append("E_001_请先设置EP_" + dthubCode + "的发件人信息");
			return sbSPSManifestText.toString();
		}
		sbSPSManifestText.append("<ReturnName>"+objSCColumns.getScscname()+"</ReturnName>\n");
		//String[] ReturnAddress1=parseAddress(objFIAColumns.getHwshipperaddress1(),1,40);
		//String[] ReturnAddress2=parseAddress(objFIAColumns.getHwshipperaddress2(),1,40);
		//String[] ReturnAddress3=parseAddress(objFIAColumns.getHwshipperaddress3(),1,40);
		sbSPSManifestText.append("<ReturnAddressLine1>"+objSCColumns.getScscaddress1()+"</ReturnAddressLine1>\n");
		sbSPSManifestText.append("<ReturnAddressLine2>.</ReturnAddressLine2>\n");
		sbSPSManifestText.append("<ReturnAddressLine3>.</ReturnAddressLine3>\n");
		sbSPSManifestText.append("<ReturnAddressLine4>.</ReturnAddressLine4>\n");
		sbSPSManifestText.append("<ReturnSuburb>"+objSCColumns.getScscaddress2()+"</ReturnSuburb>\n");
		sbSPSManifestText.append("<ReturnStateCode>"+objSCColumns.getScscaddress3()+"</ReturnStateCode>\n");
		sbSPSManifestText.append("<ReturnPostcode>"+objSCColumns.getScscpostcode()+"</ReturnPostcode>\n");
		sbSPSManifestText.append("<ReturnCountryCode>"+objSCColumns.getScsccitycode()+"</ReturnCountryCode>\n");
		Date objCreatedDate = DateFormatUtility.getStandardDate(objFIAColumns.getHwrecorddate());
		sbSPSManifestText.append("<CreatedDateTime>"+DateFormatUtility.getDateString(objCreatedDate, "yyyy-MM-dd")+"T"+DateFormatUtility.getDateString(objCreatedDate, "HH:mm:ss")+"</CreatedDateTime>\n");

		sbSPSManifestText.append("<PostChargeToAccount>"+ strMerchantAccount +"</PostChargeToAccount>\n");

		sbSPSManifestText.append("<IsSignatureRequired>"+"Y"+"</IsSignatureRequired>\n");
		sbSPSManifestText.append("<CTCAmount>"+"0.00"+"</CTCAmount>\n");
		sbSPSManifestText.append("<DeliverPartConsignment>"+"N"+"</DeliverPartConsignment>\n");
		sbSPSManifestText.append("<ContainsDangerousGoods>"+"false"+"</ContainsDangerousGoods>\n");
		sbSPSManifestText.append("<ProfileId>"+"PT_01"+"</ProfileId>\n");
		

		for (int j = 0; j < listCWPColumns.size(); j++) {
			CorewaybillpiecesColumns corewaybill = (CorewaybillpiecesColumns)listCWPColumns.get(j);
			String ArticleNumber="";
			String serverewb = objFIAColumns.getCwserverewbcode();
			String labelcode = corewaybill.getCpcplabelcode();
			if(!StringUtility.isNull(labelcode)){
			String[] arrStr = labelcode.split("\\|");
			String str ="";
			for(int c = 0;c < arrStr.length;c++){
				if(arrStr[c].contains(serverewb)){
					str = arrStr[c];
					break;
				}
			}
			ArticleNumber = str.substring(str.indexOf(serverewb));
			}
			sbSPSManifestText.append("<PCMSDomesticArticle>\n");
			sbSPSManifestText.append("<ArticleNumber>"+ArticleNumber+"</ArticleNumber>\n");
			sbSPSManifestText.append("<BarcodeArticleNumber>"+corewaybill.getCpcplabelcode()+"</BarcodeArticleNumber>\n");
			BigDecimal l1 = null;
			BigDecimal w1 = null;
			BigDecimal h1 = null;
			BigDecimal blStandard = new BigDecimal("8");
	        BigDecimal divisor = new BigDecimal(1);
	        // 长
			if(!StringUtility.isNull(corewaybill.getCpcplength())){
				l1 = new BigDecimal(corewaybill.getCpcplength());
				if (l1.compareTo(blStandard) > 0)
					l1 = l1.add(blStandard.multiply(new BigDecimal("-1")));
				else
					l1 = blStandard;
			}else{
				l1 = blStandard;
			}
			//l1 = new BigDecimal("10");
	        BigDecimal length = l1.divide(divisor,0,BigDecimal.ROUND_HALF_EVEN);
			sbSPSManifestText.append("<Length>"+length+"</Length>\n");

			// 宽
			if(!StringUtility.isNull(corewaybill.getCpcpwidth())){
				w1 = new BigDecimal(corewaybill.getCpcpwidth());
				if (w1.compareTo(blStandard) > 0)
					w1 = w1.add(blStandard.multiply(new BigDecimal("-1")));
				else
					w1 = blStandard;
			}else{
				w1 = blStandard;
			}
			//w1 = new BigDecimal("10");
	        BigDecimal width = w1.divide(divisor,0, BigDecimal.ROUND_HALF_EVEN);
			sbSPSManifestText.append("<Width>"+width+"</Width>\n");
			// 高
			if(!StringUtility.isNull(corewaybill.getCpcpheight())){
				h1 = new BigDecimal(corewaybill.getCpcpheight());
				if (h1.compareTo(blStandard) > 0)
					h1 = h1.add(blStandard.multiply(new BigDecimal("-1")));
				else
					h1 = blStandard;				
			}else{
				h1 = blStandard;
			}
			//h1 = new BigDecimal("10");
	        BigDecimal height = h1.divide(divisor,0,BigDecimal.ROUND_HALF_EVEN);
			sbSPSManifestText.append("<Height>"+height+"</Height>\n");
			
			//int pars =Integer.parseInt(objHousewaybillColumns.getCwcwpieces()); 
			//double bi =new  BigDecimal(objHousewaybillColumns.getCwcwserverchargeweight()).doubleValue();
			//double weight = bi/pars;
			
			DecimalFormat df = new DecimalFormat("#.00");
			BigDecimal objWeight = new BigDecimal(corewaybill.getCpcpgrossweight());
			objWeight = objWeight.divide(new BigDecimal("1.25"), 2, 1);				
			
			sbSPSManifestText.append("<ActualWeight>" + objWeight.toString() + "</ActualWeight>\n");
			sbSPSManifestText.append("<CubicWeight>" + calcVolume(length, width, height) + "</CubicWeight>\n");
	
			sbSPSManifestText.append("<ArticleDescription>"+listCargoInfo.get(0).getCiciename()+"</ArticleDescription>\n");
			sbSPSManifestText.append("<IsTransitCoverRequired>"+"N"+"</IsTransitCoverRequired>\n");
			sbSPSManifestText.append("<TransitCoverAmount>"+"0"+"</TransitCoverAmount>\n");
			sbSPSManifestText.append("<ContentsItem>\n");
			sbSPSManifestText.append("<GoodsDescription>"+listCargoInfo.get(0).getCiciename()+"</GoodsDescription>\n");
			sbSPSManifestText.append("<Weight>" + objWeight.toString() + "</Weight>\n");
			sbSPSManifestText.append("<Quantity>" + new DecimalFormat("#").format(pieces)+"</Quantity>\n");
			
			sbSPSManifestText.append("<UnitValue>"+df.format(unit)+"</UnitValue>\n");
			sbSPSManifestText.append("<Value>"+df.format(total)+"</Value>\n");
			sbSPSManifestText.append("</ContentsItem>\n");
			sbSPSManifestText.append("</PCMSDomesticArticle>\n");
		}
		
		sbSPSManifestText.append("</PCMSConsignment>\n");
		
		if(hm.containsKey(strMerchantLocationId)){
			String strSps = (String)hm.get(strMerchantLocationId);
			strSps = strSps + sbSPSManifestText.toString();
			hm.put(strMerchantLocationId, strSps);
		} else {
			hm.put(strMerchantLocationId, sbSPSManifestText.toString());
		}
		
		}
		
		String mlid="";
		String manifestNumber ="";
		if(hm.containsKey(strSYD)){
			sbSPSText.append("<PCMSManifest>\n");
			sbSPSText.append("<MerchantLocationId>" + strSYD + "</MerchantLocationId>\n");
			sbSPSText.append("<ManifestNumber>" + strManifestNumber + "</ManifestNumber>\n");
			sbSPSText.append("<DateSubmitted>"+DateFormatUtility.getDateString(dete, "yyyy-MM-dd")+"T"+DateFormatUtility.getDateString(dete, "HH:mm:ss")+"</DateSubmitted>\n");
			sbSPSText.append("<DateLodged>"+DateFormatUtility.getDateString(dete, "yyyy-MM-dd")+"T"+DateFormatUtility.getDateString(dete, "HH:mm:ss")+"</DateLodged>\n");
			sbSPSText.append(hm.get(strSYD));
			sbSPSText.append("</PCMSManifest>\n");
			mlid = mlid+strSYD;
			manifestNumber = strManifestNumber;
		}
		if(hm.containsKey(strMEL)){	
			sbSPSText.append("<PCMSManifest>\n");
			sbSPSText.append("<MerchantLocationId>" + strMEL + "</MerchantLocationId>\n");
			sbSPSText.append("<ManifestNumber>" + strManifestNumber + "</ManifestNumber>\n");
			sbSPSText.append("<DateSubmitted>"+DateFormatUtility.getDateString(dete, "yyyy-MM-dd")+"T"+DateFormatUtility.getDateString(dete, "HH:mm:ss")+"</DateSubmitted>\n");
			sbSPSText.append("<DateLodged>"+DateFormatUtility.getDateString(dete, "yyyy-MM-dd")+"T"+DateFormatUtility.getDateString(dete, "HH:mm:ss")+"</DateLodged>\n");
			sbSPSText.append(hm.get(strMEL));
			sbSPSText.append("</PCMSManifest>\n");
			mlid = mlid+strMEL;
			manifestNumber = strManifestNumber;
		}
		if(hm.containsKey(strPER)){	
			sbSPSText.append("<PCMSManifest>\n");
			sbSPSText.append("<MerchantLocationId>" + strPER + "</MerchantLocationId>\n");
			sbSPSText.append("<ManifestNumber>" + strManifestNumber + "</ManifestNumber>\n");
			sbSPSText.append("<DateSubmitted>"+DateFormatUtility.getDateString(dete, "yyyy-MM-dd")+"T"+DateFormatUtility.getDateString(dete, "HH:mm:ss")+"</DateSubmitted>\n");
			sbSPSText.append("<DateLodged>"+DateFormatUtility.getDateString(dete, "yyyy-MM-dd")+"T"+DateFormatUtility.getDateString(dete, "HH:mm:ss")+"</DateLodged>\n");
			sbSPSText.append(hm.get(strPER));
			sbSPSText.append("</PCMSManifest>\n");
			mlid = mlid+strPER;
			manifestNumber = strManifestNumber;
		}
		if(hm.containsKey(strBNE)){	
			sbSPSText.append("<PCMSManifest>\n");
			sbSPSText.append("<MerchantLocationId>" + strBNE + "</MerchantLocationId>\n");
			sbSPSText.append("<ManifestNumber>" + strManifestNumber + "</ManifestNumber>\n");
			sbSPSText.append("<DateSubmitted>"+DateFormatUtility.getDateString(dete, "yyyy-MM-dd")+"T"+DateFormatUtility.getDateString(dete, "HH:mm:ss")+"</DateSubmitted>\n");
			sbSPSText.append("<DateLodged>"+DateFormatUtility.getDateString(dete, "yyyy-MM-dd")+"T"+DateFormatUtility.getDateString(dete, "HH:mm:ss")+"</DateLodged>\n");
			sbSPSText.append(hm.get(strBNE));
			sbSPSText.append("</PCMSManifest>\n");
			mlid = mlid+strBNE;
			manifestNumber = strManifestNumber;
		}

		sbSPSText.append("</body>\n");
		sbSPSText.append("</SendPCMSManifest>\n");
		sbSPSText.append("</PCMS>\n");
		
		String fileName = mlid+"_"+manifestNumber+"_"+"soaptest1";
		buildFile(fileName,
				sbSPSText.toString());
		return sbSPSText.toString();
	}
	
	private String calcVolume(BigDecimal length, BigDecimal width, BigDecimal height) {
		BigDecimal volume = length.multiply(width).multiply(height);
		volume = volume.divide(new BigDecimal("4000"), 2, BigDecimal.ROUND_HALF_UP);
		volume = volume.divide(new BigDecimal("0.5"), 0, BigDecimal.ROUND_CEILING);
		volume = volume.multiply(new BigDecimal("0.5"));
		return volume.toString();
	}
}
