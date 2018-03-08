package kyle.leis.eo.operation.corewaybill.dax;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TntaddressColumns;
import kyle.leis.fs.dictionary.dictionarys.dax.DictionaryDemand;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.hi.TchnChannel;

public class RequestTNTXML extends RequestXMLEX {

	@Override
	public String buildRequestXML(ForinputallColumns objFIAColumns,
			List listCargo,
			List listPieces,
			PromptUtilityCollection objPUCollection)
			throws Exception {
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objFIAColumns.getChncode_Cwspchn());
		
		StringBuilder xmlStr = new StringBuilder();
		xmlStr.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?>");
		xmlStr.append("<!DOCTYPE ESHIPPER SYSTEM 'D:\\application-data\\inetpub\\iConnections\\ShipperDTD2.0\\eshipperin2.dtd'>");
		xmlStr.append("<ESHIPPER>");
		xmlStr.append("<LOGIN>");
		xmlStr.append("<COMPANY>" + objTchnChannel.getChnRegistername() + "</COMPANY>");
		xmlStr.append("<PASSWORD>" + objTchnChannel.getChnRegisterpassword() + "</PASSWORD>");
		xmlStr.append("<APPID>IN</APPID>");
		xmlStr.append("<APPVERSION>2.2</APPVERSION>");
		xmlStr.append("</LOGIN>");
		
		xmlStr.append("<CONSIGNMENTBATCH>");
		
		String termsOfPayment = "S";// 付款类型
		String strPaymentaccount = "";
		if (!objTchnChannel.getChnMasteraccount().equals(
				objTchnChannel.getChnPaymentaccount())) {
			termsOfPayment = "R";
			strPaymentaccount = objTchnChannel.getChnPaymentaccount();
		}
		// 发件人信息
		senderInfo(objFIAColumns, xmlStr, objTchnChannel.getChnMasteraccount());
		// 收件人信息
		consigneeInfo(objFIAColumns, xmlStr, strPaymentaccount);
		
		xmlStr.append("<CUSTOMERREF>" + objFIAColumns.getCwcustomerewbcode() + "</CUSTOMERREF>");
		xmlStr.append("<CONTYPE>N</CONTYPE>");//货物类型

		xmlStr.append("<PAYMENTIND>" + termsOfPayment + "</PAYMENTIND>");
		xmlStr.append("<ITEMS>" + objFIAColumns.getCwpieces() + "</ITEMS>");
		xmlStr.append("<TOTALWEIGHT>" + objFIAColumns.getCwtransferchargeweight() + "</TOTALWEIGHT>");
		xmlStr.append("<TOTALVOLUME>0.1</TOTALVOLUME>");
		// 发票
		String strDeclaredvalue = "0.00";
		String strDeclaredcurrency = "USD";
		String strDeclaredcontent = "DOC";
		if (listCargo != null && listCargo.size() > 0) {
			BigDecimal objDeclaredvalue = new BigDecimal("0");
			strDeclaredcontent = "";
			for (int i = 0; i < listCargo.size(); i++) {
				CargoinfoColumns objCIC = (CargoinfoColumns) listCargo.get(i);
				objDeclaredvalue = objDeclaredvalue.add(new BigDecimal(objCIC
						.getCicitotalprice()));
				strDeclaredcurrency = objCIC.getCkckcode();
				strDeclaredcontent = strDeclaredcontent + objCIC.getCiciename()
						+ ",";
			}
			if (!StringUtility.isNull(strDeclaredcontent)
					&& strDeclaredcontent.length() > 90)
				strDeclaredcontent = strDeclaredcontent.substring(0, 89);
			strDeclaredvalue = objDeclaredvalue.divide(new BigDecimal("1"), 2,
					2).toString();
		}
		xmlStr.append("<CURRENCY>" + strDeclaredcurrency + "</CURRENCY>");
		xmlStr.append("<GOODSVALUE>" + strDeclaredvalue + "</GOODSVALUE>");
		xmlStr.append("<INSURANCEVALUE></INSURANCEVALUE>");
		xmlStr.append("<INSURANCECURRENCY></INSURANCECURRENCY>");
		xmlStr.append("<SERVICE>48N</SERVICE>");
		xmlStr.append("<OPTION></OPTION>");
		xmlStr.append("<DESCRIPTION>" + strDeclaredcontent + "</DESCRIPTION>");
		xmlStr.append("<DELIVERYINST>" + strDeclaredcontent + "</DELIVERYINST>");
		// 货物信息
		int iTransferPieces = Integer.parseInt(objFIAColumns.getCwpieces());
		String strWeight = objFIAColumns.getCwtransferchargeweight();
		String strUnittransferweight = new BigDecimal(strWeight).divide(
				new BigDecimal(objFIAColumns.getCwpieces()), 2, 4).toString();
		String invoiceValue = new BigDecimal(strDeclaredvalue).divide(
				new BigDecimal(objFIAColumns.getCwpieces()), 2, 4).toString();
		for (int i = 0; i < iTransferPieces; i++) {
			xmlStr.append("<PACKAGE>");
			xmlStr.append("<ITEMS>1</ITEMS>");
			xmlStr.append("<DESCRIPTION>" + strDeclaredcontent + "</DESCRIPTION>");
			xmlStr.append("<LENGTH>0.1</LENGTH>");
			xmlStr.append("<HEIGHT>0.1</HEIGHT>");
			xmlStr.append("<WIDTH>0.1</WIDTH>");
			xmlStr.append("<WEIGHT>" + strUnittransferweight + "</WEIGHT>");
			xmlStr.append("<ARTICLE>");
			xmlStr.append("<ITEMS>1</ITEMS>");
			xmlStr.append("<DESCRIPTION>" + strDeclaredcontent + "</DESCRIPTION>");
			xmlStr.append("<WEIGHT>" + strUnittransferweight + "</WEIGHT>");
			xmlStr.append("<INVOICEVALUE>" + invoiceValue + "</INVOICEVALUE>");
			xmlStr.append("<INVOICEDESC>" + strDeclaredcontent + "</INVOICEDESC>");
			xmlStr.append("<HTS></HTS>");
			xmlStr.append("<COUNTRY></COUNTRY>");
			xmlStr.append("</ARTICLE>");
			xmlStr.append("</PACKAGE>");
		}
		
		xmlStr.append("</DETAILS>");
		xmlStr.append("</CONSIGNMENT>");
		xmlStr.append("</CONSIGNMENTBATCH>");
		// other
		xmlStr.append("<ACTIVITY>");
		xmlStr.append("<CREATE>");
		xmlStr.append("<CONREF>" + objFIAColumns.getCwcustomerewbcode() + "</CONREF>");
		xmlStr.append("</CREATE>");
		xmlStr.append("<RATE>");
		xmlStr.append("<CONREF>" + objFIAColumns.getCwcustomerewbcode() + "</CONREF>");
		xmlStr.append("</RATE>");
		xmlStr.append("<BOOK>");
		xmlStr.append("<CONREF>" + objFIAColumns.getCwcustomerewbcode() + "</CONREF>");
		xmlStr.append("</BOOK>");
		xmlStr.append("<SHIP>");
		xmlStr.append("<CONREF>" + objFIAColumns.getCwcustomerewbcode() + "</CONREF>");
		xmlStr.append("</SHIP>");		
		xmlStr.append("<PRINT>");
		xmlStr.append("<CONNOTE>");
		xmlStr.append("<CONREF>" + objFIAColumns.getCwcustomerewbcode() + "</CONREF>");
		xmlStr.append("</CONNOTE>");
		xmlStr.append("<LABEL>");
		xmlStr.append("<CONREF>" + objFIAColumns.getCwcustomerewbcode() + "</CONREF>");
		xmlStr.append("</LABEL>");
		xmlStr.append("<MANIFEST>");
		xmlStr.append("<CONREF>" + objFIAColumns.getCwcustomerewbcode() + "</CONREF>");
		xmlStr.append("</MANIFEST>");
		xmlStr.append("<INVOICE>");
		xmlStr.append("<CONREF>" + objFIAColumns.getCwcustomerewbcode() + "</CONREF>");
		xmlStr.append("</INVOICE>");
		xmlStr.append("</PRINT>");
		xmlStr.append("</ACTIVITY>");
		xmlStr.append("</ESHIPPER>");
		return xmlStr.toString();
	}

	/**
	 * 收件人
	 * @param objFIAColumns
	 * @param xmlStr
	 * @throws Exception
	 */
	private void consigneeInfo(ForinputallColumns objFIAColumns,
			StringBuilder xmlStr,
			String strPaymentaccount) throws Exception {
		xmlStr.append("<CONSIGNMENT>");
		xmlStr.append("<CONREF>" + objFIAColumns.getCwcustomerewbcode() + "</CONREF>");
		xmlStr.append("<DETAILS>");
		xmlStr.append("<RECEIVER>");
		
		String strCountryHubcode = DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode());
		TntaddressColumns objTntaddressColumns = DictionaryDemand.loadTNTCountryAddress(strCountryHubcode);
		if (objTntaddressColumns == null) {
			xmlStr.append("<COMPANYNAME>" + objFIAColumns.getHwconsigneecompany() + "</COMPANYNAME>");
			String[] rAddress = splitRAddress(objFIAColumns, 30, 3);
			for (int i = 0; i < rAddress.length; i++) {
				xmlStr.append("<STREETADDRESS" + (i + 1) + ">" 
						+ rAddress[i] + "</STREETADDRESS" + (i + 1) + ">");
			}
			xmlStr.append("<CITY>" + objFIAColumns.getHwConsigneecity() + "</CITY>");
			xmlStr.append("<PROVINCE></PROVINCE>");
			xmlStr.append("<POSTCODE>" + objFIAColumns.getHwconsigneepostcode() + "</POSTCODE>");
		} else {
			xmlStr.append("<COMPANYNAME>" + objTntaddressColumns.getTntca_companyname() + "</COMPANYNAME>");
			xmlStr.append("<STREETADDRESS1>" + objTntaddressColumns.getTntca_address1() + "</STREETADDRESS1>");
			xmlStr.append("<STREETADDRESS2>" + objTntaddressColumns.getTntca_address2() + "</STREETADDRESS2>");
			xmlStr.append("<STREETADDRESS3></STREETADDRESS3>");
			xmlStr.append("<CITY>" + objTntaddressColumns.getTntca_cityname() + "</CITY>");
			xmlStr.append("<PROVINCE></PROVINCE>");
			xmlStr.append("<POSTCODE>" + objTntaddressColumns.getTntca_postcode() + "</POSTCODE>");			
		}
		

		xmlStr.append("<COUNTRY>" + DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()) + "</COUNTRY>");
		xmlStr.append("<VAT></VAT>");
		xmlStr.append("<CONTACTNAME>" + objFIAColumns.getHwconsigneename() + "</CONTACTNAME>");
		
		String strConsigneeTel = "";
		if (objFIAColumns.getHwconsigneetelephone().indexOf("-") > 0) {
			String[] astrTel = objFIAColumns.getHwconsigneetelephone().split("-");
			if (astrTel != null && astrTel.length > 1) {
				strConsigneeTel = "<CONTACTDIALCODE>" + astrTel[0] + "</CONTACTDIALCODE>";
				strConsigneeTel = strConsigneeTel + "<CONTACTTELEPHONE>" + astrTel[1] + "</CONTACTTELEPHONE>";
			}
		}
		if (StringUtility.isNull(strConsigneeTel)) {
			strConsigneeTel = "<CONTACTDIALCODE>" + objFIAColumns.getHwshippertelephone() + "</CONTACTDIALCODE>";
			strConsigneeTel = strConsigneeTel + "<CONTACTTELEPHONE>" + objFIAColumns.getHwshippertelephone() + "</CONTACTTELEPHONE>";
		}		
		//xmlStr.append("<CONTACTDIALCODE>" + objFIAColumns.getHwconsigneetelephone() + "</CONTACTDIALCODE>");
		//xmlStr.append("<CONTACTTELEPHONE>" + objFIAColumns.getHwconsigneetelephone() + "</CONTACTTELEPHONE>");
		xmlStr.append(strConsigneeTel);
		xmlStr.append("<CONTACTEMAIL></CONTACTEMAIL>");
		if (!StringUtility.isNull(strPaymentaccount)) {
			xmlStr.append("<ACCOUNT>" + strPaymentaccount + "</ACCOUNT>");
			xmlStr.append("<ACCOUNTCOUNTRY>HK</ACCOUNTCOUNTRY>");
		}
		xmlStr.append("</RECEIVER>");
		xmlStr.append("<DELIVERY>");
		xmlStr.append("<COMPANYNAME>" + objFIAColumns.getHwconsigneecompany() + "</COMPANYNAME>");
		
		String[] rAddress = splitRAddress(objFIAColumns, 30, 3);		
		for (int i = 0; i < rAddress.length; i++) {
			xmlStr.append("<STREETADDRESS" + (i + 1) + ">" 
					+ rAddress[i] + "</STREETADDRESS" + (i + 1) + ">");
		}
		xmlStr.append("<CITY>" + objFIAColumns.getHwConsigneecity() + "</CITY>");
		xmlStr.append("<PROVINCE></PROVINCE>");
		xmlStr.append("<POSTCODE>" + objFIAColumns.getHwconsigneepostcode() + "</POSTCODE>");
		xmlStr.append("<COUNTRY>" + DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()) + "</COUNTRY>");
		xmlStr.append("<VAT></VAT>"); 
		xmlStr.append("<CONTACTNAME>" + objFIAColumns.getHwconsigneename() + "</CONTACTNAME>");
		// xmlStr.append("<CONTACTDIALCODE>" + objFIAColumns.getHwconsigneetelephone() + "</CONTACTDIALCODE>");
		// xmlStr.append("<CONTACTTELEPHONE>" + objFIAColumns.getHwconsigneetelephone() + "</CONTACTTELEPHONE>");
		xmlStr.append(strConsigneeTel);
		xmlStr.append("<CONTACTEMAIL></CONTACTEMAIL>");
		xmlStr.append("</DELIVERY>");
	}

	/**
	 * 发件人信息
	 * @param objFIAColumns
	 * @param xmlStr
	 * @throws Exception
	 */
	private void senderInfo(ForinputallColumns objFIAColumns,
			StringBuilder xmlStr,
			String strChnMasteraccount) throws Exception {
		xmlStr.append("<SENDER>");
		xmlStr.append("<COMPANYNAME>TF LOGISTICS LTD</COMPANYNAME>");
		String[] sAddress = splitSAddress(objFIAColumns, 30, 3);
		for (int i = 0; i < sAddress.length; i++) {
			xmlStr.append("<STREETADDRESS" + (i + 1) + ">"
					+ sAddress[i] + "</STREETADDRESS" + (i + 1) + ">");
		}
		xmlStr.append("<CITY>KWUN TONG</CITY>");
		xmlStr.append("<PROVINCE></PROVINCE>");
		xmlStr.append("<POSTCODE></POSTCODE>");
		xmlStr.append("<COUNTRY>HK</COUNTRY>");
		xmlStr.append("<ACCOUNT>" + strChnMasteraccount + "</ACCOUNT>");
		xmlStr.append("<VAT></VAT>");
		xmlStr.append("<CONTACTNAME>" + objFIAColumns.getHwshippername() + "</CONTACTNAME>");
		
		String strShipperTel = "";
		if (objFIAColumns.getHwshippertelephone().indexOf("-") > 0) {
			String[] astrTel = objFIAColumns.getHwshippertelephone().split("-");
			if (astrTel != null && astrTel.length > 1) {
				strShipperTel = "<CONTACTDIALCODE>" + astrTel[0] + "</CONTACTDIALCODE>";
				strShipperTel = strShipperTel + "<CONTACTTELEPHONE>" + astrTel[1] + "</CONTACTTELEPHONE>";
			}
		}
		if (StringUtility.isNull(strShipperTel)) {
			strShipperTel = "<CONTACTDIALCODE>" + objFIAColumns.getHwshippertelephone() + "</CONTACTDIALCODE>";
			strShipperTel = strShipperTel + "<CONTACTTELEPHONE>" + objFIAColumns.getHwshippertelephone() + "</CONTACTTELEPHONE>";
		}
		xmlStr.append(strShipperTel);
		xmlStr.append("<CONTACTEMAIL></CONTACTEMAIL>");
		// 取件人
		xmlStr.append("<COLLECTION>");
		xmlStr.append("<COLLECTIONADDRESS>");
		xmlStr.append("<COMPANYNAME>" + objFIAColumns.getHwshippercompany() + "</COMPANYNAME>");
		/*for (int i = 0; i < sAddress.length; i++) {
			xmlStr.append("<STREETADDRESS" + (i + 1) + ">"
					+ sAddress[i] + "</STREETADDRESS" + (i + 1) + ">");
		}*/
		xmlStr.append("<STREETADDRESS1>UNIT 103-106,1/F,AIRPORT F,</STREETADDRESS1>");
		xmlStr.append("<STREETADDRESS2>AIRPORT FREIGHT FORWARDING CTR,2</STREETADDRESS2>");
		xmlStr.append("<STREETADDRESS3>CHUN WAN ROAD CHEK LAP KOK</STREETADDRESS3>");
		xmlStr.append("<CITY>CHEK LAP KOK</CITY>");
		xmlStr.append("<PROVINCE></PROVINCE>");
		xmlStr.append("<POSTCODE></POSTCODE>");
		xmlStr.append("<COUNTRY>HK</COUNTRY>");
		xmlStr.append("<VAT></VAT>");
		xmlStr.append("<CONTACTNAME>ELAINE YUM</CONTACTNAME>");
		xmlStr.append("<CONTACTDIALCODE>852</CONTACTDIALCODE>");
		xmlStr.append("<CONTACTTELEPHONE>39710183</CONTACTTELEPHONE>");
		// xmlStr.append(strShipperTel);
		xmlStr.append("<CONTACTEMAIL></CONTACTEMAIL>");
		xmlStr.append("</COLLECTIONADDRESS>");
		xmlStr.append("<SHIPDATE>" + getShipperDate() + "</SHIPDATE>");
		xmlStr.append("<PREFCOLLECTTIME>");
		xmlStr.append("<FROM>09:00</FROM>");
		xmlStr.append("<TO>10:00</TO>");
		xmlStr.append("</PREFCOLLECTTIME>");
		xmlStr.append("<ALTCOLLECTTIME>");
		xmlStr.append("<FROM>11:00</FROM>");
		xmlStr.append("<TO>12:00</TO>");
		xmlStr.append("</ALTCOLLECTTIME>");
		xmlStr.append("<COLLINSTRUCTIONS>No pickup required</COLLINSTRUCTIONS>");
		xmlStr.append("</COLLECTION>");
		xmlStr.append("</SENDER>");
	}
	
	
	/**
	 * 取件日期
	 * @return
	 */
	public String getShipperDate(){
		Calendar calendar = Calendar.getInstance();
		switch (calendar.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SATURDAY:
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 2);
			break;
		case Calendar.SUNDAY:
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
			break;
		default:
			break;
		}
		return DateFormatUtility.getDateString(calendar.getTime(), "dd/MM/yyyy");
	}

}
