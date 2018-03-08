package kyle.leis.eo.operation.corewaybill.dax;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.corewaybill.dax.RequestXMLEX;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.hi.TchnChannel;

public class RequestUPSWebCanada extends RequestXMLEX {


	@SuppressWarnings("unchecked")
	@Override
	public String buildRequestXML(ForinputallColumns objFIAColumns, 
			List listCargo, 
			List listPieces,
			PromptUtilityCollection objPUCollection) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("isSelectedDistro=false");
		builder.append("ActionOriginPair=Next___CreateAShipment");
		builder.append("&TC_TIME_STAMP=" + new Date().getTime());
		builder.append("&loc=en_CA");
		builder.append("&RedirectHref=");
		builder.append("&app-context=/cship");
		builder.append("&uri=create");
		
		/*****************收件人信息******************************/
		builder.append("&consigneeOption.addressBookId=");
		builder.append("&shipTo=");
		builder.append("&isSurePostOnlyAddress=false");
		builder.append("&consigneeAddress.name=" + objFIAColumns.getHwconsigneecompany());
		builder.append("&consigneeAddress.contactName=" + objFIAColumns.getHwconsigneename());
		builder.append("&consigneeAddress.country=" + DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()));
		String[] rAdds = splitRAddress(objFIAColumns, 35, 3);
		builder.append("&consigneeAddress.street=" + rAdds[0]);
		builder.append("&consigneeAddress.addr2=" + (rAdds.length > 1 ? rAdds[1] : ""));
		builder.append("&consigneeAddress.addr3=" + (rAdds.length > 2 ? rAdds[2] : ""));
		builder.append("&consigneeAddress.city=" + objFIAColumns.getHwConsigneecity());
		builder.append("&consigneeAddress.state=" + DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity(), 
				DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getDtcode()), 
				DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()),
				objFIAColumns.getHwconsigneepostcode()));
		builder.append("&consigneeAddress.postalCode=" + objFIAColumns.getHwconsigneepostcode());
		builder.append("&consigneeAddress.telephone=" + objFIAColumns.getHwconsigneetelephone());
		builder.append("&consigneeAddress.extension=");
		builder.append("&consigneeAddress.email=");
		builder.append("&ADLModuleBean.adlSelected=false");
		builder.append("&shipToLocale=en_CA");
		builder.append("&shipToRIFClient=UIS");
		builder.append("&consigneeOption.collapsed=false");
		builder.append("&consigneeOption.saveOption= ");
		builder.append("&ADLModuleBean.receiverADLNotification.adlNotifyType=03");
		builder.append("&alternateDeliveryLocation_PhoneCountryCode_hidden=CA");
		builder.append("&ADLModuleBean.receiverADLNotification.adlNotificationLanguage=ENG_CA");
		builder.append("&ADLModuleBean.shipperADLNotification.adlNotifyType=03");
		builder.append("&shipperAlternateDeliveryLocation_PhoneCountryCode=1");
		builder.append("&shipperAlternateDeliveryLocation_PhoneCountryCode_hidden=CA");
		builder.append("&ADLModuleBean.shipperADLNotification.adlNotificationLanguage=ENG_CA ");
		/****************收件人信息*******************************/
		
		/******************发件人信息*********************/
		builder.append("&shipFromAddress.contactName=" + objFIAColumns.getHwshippername());
		builder.append("&login=");
		builder.append("&shipFromAddress.name=" + objFIAColumns.getHwshippercompany());
		String[] sAdds = splitSAddress(objFIAColumns, 35, 3);
		builder.append("&shipFromAddress.street=" + sAdds[0]);
		builder.append("&shipFromAddress.addr2=" + (sAdds.length > 1 ? sAdds[1] : ""));
		builder.append("&shipFromAddress.addr3=" + (sAdds.length > 2 ? sAdds[2] : ""));
		builder.append("&shipFromAddress.postalCode=" + objFIAColumns.getHwshipperpostcode());
		builder.append("&shipFromAddress.city=" + DistrictDemand.getDtenameByDtcode(objFIAColumns.getHwDtcodeshipper()));
		builder.append("&shipFromAddress.state=ON");
		builder.append("&shipFromAddress.country=" + DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getHwDtcodeshipper()));
		builder.append("&shipFromAddress.telephone=" + objFIAColumns.getHwshippertelephone());
		builder.append("&shipFromAddress.extension=");
		builder.append("&shipFromAddress.email=");
		builder.append("&shipFromAddress.residential=false");
		builder.append("&shipFromOption.addressBookId=00");
		/******************发件人信息*********************/
		
		/******************退件地址*********************/
		builder.append("&shipperAddress.contactName=" + objFIAColumns.getHwshippername());
		builder.append("&shipperOption.addressBookId=00");
		builder.append("&shipperAddress.country=CA");
		builder.append("&shipperSameAsShipFrom=true"); // 与发件人地址相同
		builder.append("&isSurePostOnlyAddress=");
		builder.append("&usprLocale=en_CA");
		builder.append("&usprRIFClient=UIS");
		builder.append("&shipperOption.collapsed=true");
		builder.append("&shipperOption.saveOption= ");
		builder.append("&shipperOption.nickName=");
		/******************退件地址*********************/
		
		/******************包裹**************************/
		String strWeight = objFIAColumns.getTransfervolumeweight();
		if (StringUtility.isNull(strWeight) || new BigDecimal(strWeight).equals(BigDecimal.ZERO)) {
			strWeight = objFIAColumns.getCwtransfergrossweight();
		}
		String strUnittransferweight = new BigDecimal(strWeight)
				.divide(new BigDecimal(objFIAColumns.getCwpieces()), 2, 4).toString();
		int iTransferPieces = Integer.parseInt(objFIAColumns.getCwpieces());
		// 申报价值和描述
		String strDeclaredvalue = "0";
		String strDeclaredcontent = "DOC";
		if (listCargo != null && listCargo.size() > 0) {
			BigDecimal objDeclaredvalue = new BigDecimal("0");
			strDeclaredcontent = "";
			for (int i = 0; i < listCargo.size(); i++) {
				CargoinfoColumns objCIC = (CargoinfoColumns)listCargo.get(i);
				objDeclaredvalue = objDeclaredvalue.add(new BigDecimal(objCIC.getCicitotalprice()));
				strDeclaredcontent = strDeclaredcontent + objCIC.getCiciename() + ",";
			}
			if (!StringUtility.isNull(strDeclaredcontent) && strDeclaredcontent.length() > 35) {
				strDeclaredcontent = strDeclaredcontent.substring(0, 34);
			}
			strDeclaredvalue = objDeclaredvalue.divide(new BigDecimal("1"), 0, 2).toString();
		}
		builder.append("&packageCount=" + iTransferPieces);
		builder.append("&samePackageAttribute=true");
		builder.append("&unitOfMeasure=1");
		builder.append("&packageBean.packageType=02");
		builder.append("&packageBean.weight=" + strUnittransferweight);
		builder.append("&packageBean.length=");
		builder.append("&packageBean.width=");
		builder.append("&packageBean.height=");
		builder.append("&packageBean.declaredValue.insuredAmount=" + strDeclaredvalue);
		builder.append("&packageBean.piecesOnPallet=");
		builder.append("&shipmentBean.descriptionOfGoods=" + strDeclaredcontent);
		builder.append("&shipmentBean.movementReferenceNumber=");
		builder.append("&serviceBean.uomOunce=false");
		builder.append("&serviceBean.service=011");
		builder.append("&packageBean.reference.value#1=FC");
		builder.append("&packageBean.reference.value#2="+ objFIAColumns.getCwcustomerewbcode());
		builder.append("&packageBean.reference.value#3=");
		builder.append("&sameReferenceValue=true");
		/******************包裹**************************/
		
		String strChncode = objFIAColumns.getChncode_Cwspchn();
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
		builder.append("&transportationPayer.accountString=10_" + objTchnChannel.getChnPaymentaccount());
		builder.append("&TPInitcountry=CA");
		builder.append("&transportationPayer.thirdPartyAccount.country=CA");
		builder.append("&promoCodeAlias=");
		builder.append("&uisLocale=en_CA");
		builder.append("&uisRIFClient=UIS");
		builder.append("&businessPurpose=true");
		builder.append("&ADLModuleBean.payAtStoreADLNotification.adlEmail=");
		builder.append("&ADLModuleBean.payAtStoreADLNotification.adlNotificationLanguage=ENG_CA");
		builder.append("&ADLModuleBean.hasTaxID=false");
		builder.append("&ADLModuleBean.taxID=");
		builder.append("&ADLModuleBean.mailInvoice=");
		builder.append("&dutiesPayer.accountString=10_1AE415");
		builder.append("&TPInitcountry=CA");
		builder.append("&dutiesPayer.thirdPartyAccount.country=CA");
		builder.append("&associatedAccount.accountString=10_1AE415");
		builder.append("&TPInitcountry=CA");
		builder.append("&associatedAccount.thirdPartyAccount.country=CA");
		builder.append("&reviewDetails=false"); // 不复查信息，直接创建快件
		
		return builder.toString();
	}

}
