package kyle.leis.eo.operation.corewaybill.dax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.metrology.WeightConversion;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class RequestFedexWeb extends RequestXMLEX {

	@Override
	public String buildRequestXML(ForinputallColumns objFIAColumns,
			List listCargo,
			List listPieces,
			PromptUtilityCollection objPUCollection)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("mspData.profileId=");
		// 发件人信息
		senderInfo(objFIAColumns, sb);
		// 收件人信息
		consigneeInfo(objFIAColumns, sb);
		// 快件信息
		String strDeclaredvalue = shipmentInfo(objFIAColumns, listCargo, listPieces, sb);
		// 计费信息
		sb.append("&billingDisplay.isAccountChanged=false");
		sb.append("&billingData.selectedBillTransportationIndex=1");
		sb.append("&billingData.referenceData.yourReference=" + objFIAColumns.getCwcustomerewbcode());
		sb.append("&billingData.selectedReferenceType=");
		sb.append("&billingData.referenceData.purchaseOrderNumber=");
		sb.append("&billingData.referenceData.invoiceNumber=");
		sb.append("&billingData.referenceData.departmentNumber=");
		sb.append("&ssData.moduleJsInitState=");
		sb.append("&ssData.moduleStateExpanded=true");
		sb.append("&ssData.diaData.addressData.wabsID=");
		sb.append("&ssData.codData.addressData.wabsID=");
		sb.append("&ssData.diaData.addressData.countryCode=" 
				+ DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()));
		sb.append("&ssData.diaData.addressData.companyName=Select or enter");
		sb.append("&ssData.diaData.addressData.contactName=Select or enter");
		sb.append("&ssData.diaData.addressData.addressLine1=");
		sb.append("&ssData.diaData.addressData.addressLine2=");
		sb.append("&ssData.diaData.addressData.zipPostalCode=");
		sb.append("&ssData.diaData.addressData.city=Select or enter");
		sb.append("&ssData.diaData.addressData.phoneNumber=");
		sb.append("&ssData.diaData.addressData.phoneNumberExt=");
		sb.append("&psdData.mpsRowDataList[0].dryIceWeight=");
		sb.append("&psdData.mpsRowDataList[0].dangerousGoodsCodesString=");
		sb.append("&ssData.itarExemptionNumber=");
		sb.append("&ssData.codData.perPackageOrTotal=N");
		sb.append("&psdData.mpsRowDataList[0].codAmount=");
		sb.append("&ssData.codData.ddPayorderOrCheque=");
		sb.append("&ssData.codData.payableAt=");
		sb.append("&ssData.codData.drawnOn=");
		sb.append("&ssData.codData.department=");
		
		sb.append("&ssData.codData.addressData.countryCode=US");
		sb.append("&ssData.codData.addressData.companyName=Logan Technology Inc.");
		sb.append("&ssData.codData.addressData.companyName=Select or enter");
		sb.append("&ssData.codData.addressData.contactName=Jose Espinoza");
		sb.append("&ssData.codData.addressData.contactName=Select or enter");
		sb.append("&ssData.codData.addressData.addressLine1=1820 NW 82 AVE");
		sb.append("&ssData.codData.addressData.addressLine2=");
		sb.append("&ssData.codData.addressData.zipPostalCode=33126");
		sb.append("&ssData.codData.addressData.city=Select or enter");
		sb.append("&ssData.codData.addressData.phoneNumber=7865372138");
		sb.append("&ssData.codData.addressData.phoneNumberExt=");
		sb.append("&ssData.halData.addressData.addressLine1=");
		sb.append("&ssData.halData.addressData.addressLine2=");
		sb.append("&ssData.halData.addressData.zipPostalCode=");
		sb.append("&ssData.halData.addressData.city=Select or enter");
		
		sb.append("&ssData.deliveryDate=");
		sb.append("&ssData.signatureType=0");
		sb.append("&ssData.isUserChosenSignature=false");
		sb.append("&ssData.defaultSignature=");
		sb.append("&ssData.signatureTotalCarriageValue=" + strDeclaredvalue);
		sb.append("&ssData.signatureTotalCustomsValue=101");
		sb.append("&ssData.previousPage=null");
		sb.append("&ssData.signatureErrorCode=FEATURE.DISABLED");
		sb.append("&ssData.codDiaServiceType=");
		sb.append("&pickupDropoffData.pickupTypeCode=1");
		sb.append("&pickupDropoffData.moduleState=E");
		sb.append("&pickupDropoffData.moduleRequiredOrOptional=O");
		sb.append("&pickupDropoffData.accountAddressFlag=AA");
		sb.append("&pickupDropoffData.displayAlert=false");
		sb.append("&pickupDropoffData.carrierCode=");
		sb.append("&pickupDropoffData.locationCode=");
		sb.append("&pickupDropoffData.confirmatioNumberPickupDate=");
		sb.append("&pickupDropoffData.accAddrLinkDisplayStatus=true");
		
		sb.append("&pickupDropoffData.alternativeAddressData.contactName=Jose Espinoza");
		sb.append("&pickupDropoffData.alternativeAddressData.companyName=Logan Technology Inc.");
		sb.append("&pickupDropoffData.alternativeAddressData.city=");
		sb.append("&pickupDropoffData.pickupDate=" + DateFormatUtility.getSysdateString("MM/dd/yyyy"));
		sb.append("&pickupDropoffData.sdocType=");
		sb.append("&pickupDropoffData.isSDOCResidentialAllowed=");
		sb.append("&pickupDropoffData.pickupTypeCode=1");
		sb.append("&pickupDropoffData.confirmationNumber=");
		sb.append("&pickupDropoffData.accountAddressData.countryCode=US");
		sb.append("&pickupDropoffData.alternativeAddressData.countryCode=US");
		sb.append("&pickupDropoffData.accCountryName=United States");
		sb.append("&pickupDropoffData.altCountryName=United States");
		sb.append("&pickupDropoffData.accountAddressData.companyName=LOGAN TECHNOLOGY INC.");
		sb.append("&pickupDropoffData.alternativeAddressData.companyName=Logan Technology Inc.");
		sb.append("&pickupDropoffData.alternativeAddressData.contactID=");
		sb.append("&pickupDropoffData.accountAddressData.contactName=Jose Espinoza");
		sb.append("&pickupDropoffData.alternativeAddressData.contactName=Jose Espinoza");
		sb.append("&pickupDropoffData.accountAddressData.addressLine1=1820 NW 82ND AVE");
		sb.append("&pickupDropoffData.alternativeAddressData.addressLine1=1820 NW 82 AVE");
		sb.append("&pickupDropoffData.accountAddressData.addressLine2=");
		sb.append("&pickupDropoffData.alternativeAddressData.addressLine2=");
		sb.append("&pickupDropoffData.accountAddressData.zipPostalCode=331261042");
		sb.append("&pickupDropoffData.alternativeAddressData.zipPostalCode=33126");
		sb.append("&pickupDropoffData.accountAddressData.city=DORAL");
		sb.append("&pickupDropoffData.alternativeAddressData.city=DORAL");
		sb.append("&pickupDropoffData.accountAddressData.stateProvinceCode=FL");
		sb.append("&pickupDropoffData.accStateName=FL");
		sb.append("&pickupDropoffData.alternativeAddressData.stateProvinceCode=FL");
		sb.append("&pickupDropoffData.alternativeAddressData.stateProvinceCode=FL");
		sb.append("&pickupDropoffData.accountAddressData.phoneNumber=7865372138");
		sb.append("&pickupDropoffData.accountAddressData.phoneNumberExt=");
		sb.append("&pickupDropoffData.alternativeAddressData.phoneNumber=7865372138");
		sb.append("&pickupDropoffData.alternativeAddressData.phoneNumberExt=");
		sb.append("&pickupDropoffData.alternativeAddressData.residential=false");
		sb.append("&pickupDropoffData.serviceCode=empty");
		sb.append("&pickupDropoffData.numberOfPieces=1");
		sb.append("&pickupDropoffData.totalWeight=0");
		sb.append("&pickupDropoffData.weightUnit=LBS");
		sb.append("&pickupDropoffData.formattedPickupDate=" + DateFormatUtility.getSysdateString("MM/dd/yyyy"));
		sb.append("&pdm_pickupDate=");
		sb.append("&pickupDropoffData.packageReadyTime=0700");
		sb.append("&pickupDropoffData.companyCloseTime=1100");
		sb.append("&pickupDropoffData.personWithSkidNumber=");
		sb.append("&pickupDropoffData.packageLocationOrInstructions=");
		sb.append("&pickupDropoffData.dimProfile=empty");
		sb.append("&pickupDropoffData.dimLength=L");
		sb.append("&pickupDropoffData.dimWidth=W");
		sb.append("&pickupDropoffData.dimHeight=H");
		sb.append("&pickupDropoffData.dimUnit=I");
		sb.append("&pickupDropoffData.truckType=");
		sb.append("&pickupDropoffData.truckSize=");
		sb.append("&notificationData.senderNotifications.formatType=H");
		sb.append("&notificationData.senderNotifications.email=sales@logan-cam.com");
		sb.append("&notificationData.senderNotifications.notificationLanguage=en");
		sb.append("&notificationData.recipientNotifications.formatType=H");
		sb.append("&notificationData.recipientNotifications.email=");
		sb.append("&notificationData.recipientNotifications.notificationLanguage=en");
		sb.append("&notificationData.other1Notifications.formatType=H");
		sb.append("&notificationData.other1Notifications.email=");
		sb.append("&notificationData.other1Notifications.notificationLanguage=en");
		sb.append("&notificationData.other2Notifications.formatType=H");
		sb.append("&notificationData.other2Notifications.email=");
		sb.append("&notificationData.other2Notifications.notificationLanguage=en");
		sb.append("&notificationData.emailMessage=");
		sb.append("&notificationData.sendCopyEmailNotiRowIndex=");
		sb.append("&ratingData.ratingModuleMode=");
		sb.append("&ratingData.ratingModuleReqd=");
		sb.append("&ratingData.rateDisplayedOnShipPage=false");
		sb.append("&ratingData.dynamicRatingFlag=");
		sb.append("&ratingData.yourRate=");
		sb.append("&servicetype=FEDEX_GROUND");
		sb.append("&ratingData.ratingModuleState=");
		sb.append("&completeShipData.proscribedCountry=false");
		sb.append("&completeShipData.fromEU=false");
		sb.append("&completeShipData.toEU=false");
		sb.append("&completeShipData.dynamicModuleNumber=1,6");
		sb.append("&completeShipData.products=false");
		sb.append("&completeShipData.createShipProfileEnabled=true");
		sb.append("&completeShipData.saveShipProfileEnabled=false");
		sb.append("&completeShipData.saveShipProfileAsNewEnabled=false");
		sb.append("&completeShipData.saveForLaterShowed=true");
		sb.append("&completeShipData.saveForLaterClicked=false");
		sb.append("&completeShipData.pendingId=0");
		sb.append("&completeShipData.profileId=0");
		sb.append("&completeShipData.shipFromPendingShipment=");
		sb.append("&completeShipData.shipFromShipmentProfile=");
		sb.append("&completeShipData.shipmentStatus=");
		sb.append("&completeShipData.reviewShipmentProfilesFlg=");
		sb.append("&completeShipData.reviewPaginationInfo=");
		sb.append("&completeShipData.hasNextProfile=");
		sb.append("&completeShipData.hasPreviousProfile=");
		sb.append("&completeShipData.reviewEvent=");
		sb.append("&completeShipData.docShipEligibleForEEISED=false");
		sb.append("&completeShipData.dbFull=");
		sb.append("&completeShipData.nickNameDuplicateFlg=N");
		sb.append("&returnsTandCFlag=");
		sb.append("&orderData.externalOrderId=");
		sb.append("&orderData.middlewareVersion=");
		sb.append("&orderData.originatingSystem=");
		sb.append("&completeShipData.errorCode8258=false");
		sb.append("&completeShipData.profileNickName=");
		sb.append("&completeShipData.mobileShipmentEmailAddress=");

		return URLEncoder.encode(sb.toString(), 
				"UTF-8").replace("%3D", "=").replace("%26", "&");
	}

	/**
	 * 快件信息
	 * @param objFIAColumns
	 * @param listCargo
	 * @param sb
	 * @return
	 */
	private String shipmentInfo(ForinputallColumns objFIAColumns, 
			List listCargo,
			List listPieces,
			StringBuffer sb) {
		sb.append("&psdData.shipDate=" + DateFormatUtility.getSysdateString("MM/dd/yyyy"));
		sb.append("&psdData.expiryDate=");
		sb.append("&psdData.pickupDate=");
		sb.append("&psdData.arePackagesIdentical=true");
		sb.append("&psdData.pricingOption=FSR");
		sb.append("&psdData.pricingOptionAllowed=true");
		sb.append("&removeRelatedVO=true");
		sb.append("&psd_shipDate=" + DateFormatUtility.getSysdateString("MM/dd/yyyy"));
		// 重量
		int iTransferPieces = Integer.parseInt(objFIAColumns.getCwpieces());
		String strWeight = objFIAColumns.getCwtransferchargeweight();
		BigDecimal unittransferweight = new BigDecimal(strWeight).divide(
				new BigDecimal(objFIAColumns.getCwpieces()), 2, 4);
		double weightLbs = WeightConversion.convertKgToPound(unittransferweight.doubleValue());
		// 价值
		String strDeclaredvalue = "0.00";
		String strDeclaredcurrency = "USD";
		String strDeclaredcontent = "DOC";
		if (listCargo != null && listCargo.size() > 0) {
			BigDecimal objDeclaredvalue = new BigDecimal("0");
			strDeclaredcontent = "";
			for (int i = 0; i < listCargo.size(); i++) {
				CargoinfoColumns objCIC = (CargoinfoColumns) listCargo.get(i);
				objDeclaredvalue = objDeclaredvalue.add(new BigDecimal(objCIC.getCicitotalprice()));
				strDeclaredcurrency = objCIC.getCkckcode();
				strDeclaredcontent = strDeclaredcontent + objCIC.getCiciename() + ",";
			}
			if (!StringUtility.isNull(strDeclaredcontent) && strDeclaredcontent.length() > 90)
				strDeclaredcontent = strDeclaredcontent.substring(0, 89);
			strDeclaredvalue = objDeclaredvalue.divide(new BigDecimal("1"), 2,2).toString();
		}
		String unitValue = new BigDecimal(strDeclaredvalue).divide(
				new BigDecimal(objFIAColumns.getCwpieces()), 2, 4).toString();
		
		sb.append("&psdData.numberOfPackages=" + iTransferPieces);
		sb.append("&psdData.mpsRowDataList[0].quantity=" + iTransferPieces);
		sb.append("&psdData.pricingOption=FSR");
		sb.append("&psdData.arePackagesIdentical=true");
		sb.append("&psdData.weightUnitOfMeasure=LBS");
		sb.append("&psdData.mpsRowCount=1");
		sb.append("&psdData.mpsMaxRowIndex=1");
		sb.append("&psdData.mpsRowDataList[0].weight=" 
				+ new BigDecimal(weightLbs).divide(BigDecimal.ONE, 2, RoundingMode.HALF_UP));
		sb.append("&psdData.weightUnitOfMeasure=LBS");
		sb.append("&psdData.mpsRowDataList[0].carriageValue=" + unitValue);
		sb.append("&psdData.declaredValueCurrencyCode=" + strDeclaredcurrency);
		sb.append("&commodityData.documentDescriptionCode=empty");
		sb.append("&commodityData.descriptionForIntraEU=");
		sb.append("&commodityData.yourDocumentDescription=");
		sb.append("&commodityData.docShipEligibleForSED=");
		sb.append("&commodityData.productsAsString=");
		sb.append("&commodityData.displayReturnAlert=");
		sb.append("&psdData.serviceType=FedEx Ground");
		sb.append("&psdData.packageType=Your Packaging");
		
		sb.append("&psdData.mpsRowDataList[0].mpsDimensionSelect=manual");
		int iMinPieceIndex = getMinVolume(listPieces);
		if (iMinPieceIndex >= 0) {
			CorewaybillpiecesColumns corewaybill = (CorewaybillpiecesColumns)listPieces.get(iMinPieceIndex);
			sb.append("&psdData.mpsRowDataList[0].mpsLength=" + convertCmToIn(corewaybill.getCpcplength()));
			sb.append("&psdData.mpsRowDataList[0].mpsWidth=" + convertCmToIn(corewaybill.getCpcpwidth()));
			sb.append("&psdData.mpsRowDataList[0].mpsHeight=" + convertCmToIn(corewaybill.getCpcpheight()));
		} else {
			sb.append("&psdData.mpsRowDataList[0].mpsLength=1");
			sb.append("&psdData.mpsRowDataList[0].mpsWidth=1");
			sb.append("&psdData.mpsRowDataList[0].mpsHeight=1");
		}
		sb.append("&psdData.dimensionUnitOfMeasure=I");
		sb.append("&psdData.dimensionsProfileName=");
		sb.append("&psdData.bookingConfirmationNumber=");
		sb.append("&psdData.shipmentWillBeReadyByHH=1");
		sb.append("&psdData.shipmentWillBeReadyByMM=00");
		sb.append("&psdData.shipmentWillBeReadyByAMPM=AM");
		sb.append("&psdData.reasonForReturn=empty");
		sb.append("&psdData.specifyReason=");
		
		return strDeclaredvalue;
	}

	/**
	 * 收件人信息
	 * @param objFIAColumns
	 * @param sb
	 * @throws Exception
	 */
	private void consigneeInfo(ForinputallColumns objFIAColumns, StringBuffer sb)
			throws Exception {
		sb.append("&toDisplay.deniedPartyURL=");
		sb.append("&toData.srnNumber=");
		sb.append("&toData.addressData.wabsID=");
		sb.append("&toData.addressData.skipAddressCheck=false");
		sb.append("&toData.addressData.verified=false");
		sb.append("&toData.addressData.addressCheckPerformed=true");
		sb.append("&toData.addressData.addressCheckDate=");
		sb.append("&toData.addressData.addressSourceType=");
		sb.append("&toData.addressData.verifiedByINET=false");
		sb.append("&toData.addressData.uspsAddressType=");
		sb.append("&outlookPath=https://www.fedex.com/woas/WoasAction.do?&locale=en_us&appContextPath=");
		sb.append("&outlookReturnPath=https://www.fedex.com:443/shipping/jsp/WoasClient.jsp");
		sb.append("&origAddressFromWabsAsString=||Select or enter||empty|false");
		sb.append("&toAliasCountryCode=");
		sb.append("&toData.addressData.countryCode=" 
				+ DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()));
		sb.append("&toData.addressData.companyName=" + objFIAColumns.getHwconsigneecompany());
		sb.append("&toData.addressData.contactName=" + objFIAColumns.getHwconsigneename());
		sb.append("&toData.addressData.addressLine1=" + objFIAColumns.getHwconsigneeaddress1());
		sb.append("&toData.addressData.addressLine2=" + objFIAColumns.getHwconsigneeaddress2());
		sb.append("&toData.addressData.zipPostalCode=" + objFIAColumns.getHwconsigneepostcode());
		sb.append("&toData.addressData.city=" + objFIAColumns.getHwConsigneecity());
		sb.append("&toData.addressData.stateProvinceCode=" 
				+ DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity(), 
						DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getDtcode()),
						DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()), 
						objFIAColumns.getHwconsigneepostcode())); // 所在州
		sb.append("&toData.addressData.phoneNumber=" 
				+ StringUtility.replaceWhenNull(objFIAColumns.getHwconsigneetelephone(), "").replace(" ", ""));
		sb.append("&toData.addressData.phoneNumberExt=");
		sb.append("&toDisplay.addressCheckRequiredOnPageLoadFlag=N");
		sb.append("&toData.addressData.secAddrAlertShown=N");
		sb.append("&toData.addressData.secAddrAlertActedOn=N");
	}

	/**
	 * 发件人信息
	 * @param objFIAColumns
	 * @param sb
	 * @throws Exception
	 */
	private void senderInfo(ForinputallColumns objFIAColumns, StringBuffer sb)
			throws Exception {
		sb.append("&fromData.moduleStateExpanded=true");
		sb.append("&fromData.moduleJsInitState=full");
		sb.append("&fromData.addressData.wabsID=");
		sb.append("&fromData.savedSender=empty");
		
		String strNaHubcode = DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getHwDtcodeshipper());
		
		sb.append("&fromData.addressData.countryCode=" + strNaHubcode);
		sb.append("&fromData.addressData.companyName=" + objFIAColumns.getHwshippercompany());
		sb.append("&fromData.addressData.contactName=" + objFIAColumns.getHwshippername());
		sb.append("&fromData.addressData.addressLine1=" + objFIAColumns.getHwshipperaddress1());
		sb.append("&fromData.addressData.addressLine2=" + objFIAColumns.getHwshipperaddress2() + " " + objFIAColumns.getHwshipperaddress3());
		sb.append("&fromData.addressData.zipPostalCode=" + objFIAColumns.getHwshipperpostcode());
		sb.append("&fromData.addressData.city=" + DistrictDemand.getDtenameByDtcode(objFIAColumns.getHwDtcodeshipper()));
		sb.append("&fromData.addressData.stateProvinceCode=" + DistrictDemand.getDHLStateCode("", 
				DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getHwDtcodeshipper()), 
				strNaHubcode, 
				objFIAColumns.getHwshipperpostcode()));
		sb.append("&fromData.addressData.phoneNumber=" + objFIAColumns.getHwshippertelephone());
		sb.append("&fromData.addressData.phoneNumberExt=");
	}
	
	private int getMinVolume(List listCWPColumns) {
		if (listCWPColumns == null || listCWPColumns.size() < 1)
			return -1; 
		BigDecimal objMinVolume = new BigDecimal("99999");
		int iMinIndex = -1;
		for (int i = 0; i < listCWPColumns.size(); i++) {
			CorewaybillpiecesColumns corewaybill = (CorewaybillpiecesColumns)listCWPColumns.get(i);
			String strVolumeWeight = calcVolume(new BigDecimal(StringUtility.replaceWhenNull(corewaybill.getCpcplength(), "0")),
					new BigDecimal(StringUtility.replaceWhenNull(corewaybill.getCpcpwidth(), "0")),
					new BigDecimal(StringUtility.replaceWhenNull(corewaybill.getCpcpheight(), "0")));
			if (new BigDecimal(strVolumeWeight).compareTo(objMinVolume) < 0) {
				objMinVolume = new BigDecimal(strVolumeWeight);
				iMinIndex = i;
			}
		}
		return iMinIndex;
	}
	
	private String convertCmToIn(String strCM) {
		if (StringUtility.isNull(strCM))
			return "0";
	    return new BigDecimal(strCM).divide(new BigDecimal("2.54"), 2, 4).toString();
	}	
	
	
	private String calcVolume(BigDecimal length, 
			BigDecimal width, 
			BigDecimal height) {
		BigDecimal volume = length.multiply(width).multiply(height);
		volume = volume.divide(new BigDecimal("4000"), 2, BigDecimal.ROUND_HALF_UP);
		volume = volume.divide(new BigDecimal("0.5"), 0, BigDecimal.ROUND_CEILING);
		volume = volume.multiply(new BigDecimal("0.5"));
		return volume.toString();
	}	

}
