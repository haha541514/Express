package kyle.leis.eo.operation.corewaybill.dax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.DateUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.specialtype.da.SpecialtypeColumns;
import kyle.leis.eo.operation.specialtype.dax.SpecialtypeDemand;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

import com.WaybillcodeParam;

public class RequestDHLMyWeb extends ARequestXML {

	@SuppressWarnings("unchecked")
	@Override
	public WaybillcodeParam buildRequest(ForinputallColumns objFIAColumns,
			List listCargo,
			List listPieces,
			PromptUtilityCollection objPUCollection)
			throws Exception {
		WaybillcodeParam waybillParam = new WaybillcodeParam();
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("srinfo", srInfo(objFIAColumns)); // 收件人和发件人信息
		mapParam.put("packageinfo",packageInfo(objFIAColumns, listCargo)); // 包裹信息
		mapParam.put("invoice", invoice(listCargo)); // 发票信息
		mapParam.put("service", service()); // 选择服务
		mapParam.put("acceptMultyData", accept());// 接受协议，提交服务
		waybillParam.setWaybillcodeParam(mapParam);
		return waybillParam;
	}

	/**
	 * 接受协议，提交服务
	 * @return
	 */
	private Map<String, String> accept() {
		Map<String, String> acceptMultyData = new HashMap<String, String>();
		acceptMultyData.put("hours", String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)));
		acceptMultyData.put("displayInsurance", "null");
		acceptMultyData.put("insuranceDisplayFlag", "Y");
		acceptMultyData.put("calBtnStatus", "");
		acceptMultyData.put("latestPickupTime", "<SPAN class=\"errorBoxText\" >The latest pickup time for " +
				"the selected delivery option is:<font color=\"#b40404\"> 18:30</font></<SPAN>");
		acceptMultyData.put("dct_plt_status_RdTr0", "true");
		acceptMultyData.put("dctExtraServiceInsuranceAmtkey", "100.000");
		acceptMultyData.put("dctDisplayInsuranceAmtkey", "100.00");
		acceptMultyData.put("dctExtraServiceInsuranceCurrCdkey", "HKD");
		acceptMultyData.put("dctExtraServiceSatDeliveryAmtkey", "NA");
		acceptMultyData.put("dctDisplayExtraServiceSatDeliveryAmtkey", "0.00");
		acceptMultyData.put("dctExtraServiceSatDeliveryCurrCdkey", "HKD");
		acceptMultyData.put("dctQuoteSatDelHiddenKey", "0.0");
		acceptMultyData.put("dctDisplayQuoteSatDelHiddenKey", "0.0");
		Date date = DateFormatUtility.getStandardDate(DateUtility.getMoveDate(1));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
		String delvDate = dateFormat.format(date);
		acceptMultyData.put("dctDelvDateHiddenKey", delvDate);
		acceptMultyData.put("dctDelvDateSatDelvHiddenKey", "");
		acceptMultyData.put("dctDelvTimeHiddenKey", "23:59");
		acceptMultyData.put("dctDelvTimeSatDelvHiddenKey", "");
		acceptMultyData.put("dctExtraServiceMexicoAmtkey", "NA");
		acceptMultyData.put("dctDisplayMexicoAmtkey", "0.00");
		acceptMultyData.put("dctExtraServiceMexicoCurrCdkey", "HKD");
		acceptMultyData.put("dctExtraServiceExtendedLiabilityAmtkey", "0");
		acceptMultyData.put("dctDisplayExtraExtendedLiabilityAmtkey", "0.00");
		acceptMultyData.put("dctExtraServiceExtendedLiabilityCurrCdkey", "HKD");
		acceptMultyData.put("dctExtraServicePromoCodekey", "NA");
		acceptMultyData.put("dctDisplayPromoCodekey", "0.00");
		acceptMultyData.put("dctExtraServicePromoCurrCdkey", "HKD");
		acceptMultyData.put("serviceCurrencyCd", "HKD");
		acceptMultyData.put("totalServicesKey", "1");
		acceptMultyData.put("isInsuranceKey", "true");
		acceptMultyData.put("isSatDeliveryKey", "false");
		acceptMultyData.put("currentInsuranceKey", "true");
		acceptMultyData.put("currentSaturdayKey", "false");
		acceptMultyData.put("currentMexicoKey", "false");
		acceptMultyData.put("bookingTime", "17:45");
		acceptMultyData.put("globalProductKey", "");
		acceptMultyData.put("serviceBandInfoStatus", "STATE1");
		acceptMultyData.put("isInsuranceTextValue", "Y");
		acceptMultyData.put("isPromoCodeApplied", "");
		acceptMultyData.put("discountAmount", "");
		acceptMultyData.put("addtionalSatDelFlag", "false");
		acceptMultyData.put("addtionalInsureFlag", "true");
		acceptMultyData.put("addtionalMexicoFlag", "false");
		acceptMultyData.put("months", "January|February|March|April|May|June|July|August|September|October|November|December");
		acceptMultyData.put("shippingInformation", "");
		acceptMultyData.put("usersCurrentDate", "");
		acceptMultyData.put("hidInsuranceOption", "1");
		acceptMultyData.put("hidCurCodeNoOfDigitsRight", "AUD2EUR2HKD2JPY0USD2");
		acceptMultyData.put("hidLocalCurCodeNoOfDigitsRight", "AUD2EUR2HKD2JPY0USD2");
		acceptMultyData.put("hidRegionCode", "AP");
		acceptMultyData.put("insuranceAmount", "");
		acceptMultyData.put("insuranceCurrencyCode", "");
		acceptMultyData.put("mexicoAmount", "");
		acceptMultyData.put("mexicoCurrencyCode", "");
		acceptMultyData.put("insDctAmtValue", "");
		acceptMultyData.put("insDctCurrencyValue", "");
		acceptMultyData.put("insValue", "");
		acceptMultyData.put("globalproductselected", "");
		acceptMultyData.put("currentTimeHours", "");
		acceptMultyData.put("currentTimeMinutes", "");
		acceptMultyData.put("departureDate", "0");
		acceptMultyData.put("rcaReadyByTimeVal", "");
		acceptMultyData.put("rcaCloseTimeVal", "");
		acceptMultyData.put("rcaCutOffTimeVal", "");
		acceptMultyData.put("usersCurrentDate", "");
		acceptMultyData.put("isErrorState", "false");
		acceptMultyData.put("emlTelHiddenId", "null");
		acceptMultyData.put("methodHiddenId", "null");
		acceptMultyData.put("countId", "1");
		acceptMultyData.put("emailAddress", "");
		acceptMultyData.put("shipmentNotificationEmail", "");
		acceptMultyData.put("hours", "");
		acceptMultyData.put("goConfirmationPageCheck", "ON");
		return acceptMultyData;
	}

	/**
	 * 选择服务
	 * @return
	 */
	private String service() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("invoiceTemplate=none");
		buffer.append("&invoiceType=PRO");
		buffer.append("&harmonizedCommCode=");
		buffer.append("&invoiceNumber=");
		buffer.append("&termsOfPayment=");
		buffer.append("&termsOfTrade=FCA");
		buffer.append("&exportReasonCode=P");
		buffer.append("&recipientTaxIdOrVAT=");
		buffer.append("&namedDestinationPort=");
		buffer.append("&remarks=");
		buffer.append("&otherCharges=");
		buffer.append("&itemDescription=");
		buffer.append("&unitValue=");
		buffer.append("&countryOfManufacture=");
		buffer.append("&netWeight=");
		buffer.append("&quantity=");
		buffer.append("&grossWeight=");
		buffer.append("&unitOfMeasure=PCS");
		buffer.append("&commodityCode=");
		buffer.append("&invoiceTemplateName=");
		buffer.append("&usersCurrentDate=" + DateFormatUtility.getDateString(new Date(), "yyyy-MM-dd"));
		buffer.append("&naftaText=null");
		buffer.append("&hours=" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		return buffer.toString();
	}

	/**
	 * 发票信息
	 * @param listCargo
	 */
	private String[] invoice(List<?> listCargo) {
		if (CollectionUtility.isNull(listCargo)) {
			return new String[]{};
		}
		String[] invoices = new String[listCargo.size()];
		for (int i = 0; i < listCargo.size(); i++) {
			CargoinfoColumns objCIC = (CargoinfoColumns)listCargo.get(i);
			StringBuffer buffer = new StringBuffer();
			buffer.append("invoiceTemplate=none");
			buffer.append("&invoiceType=PRO");
			buffer.append("&harmonizedCommCode=");
			buffer.append("&invoiceNumber=");
			buffer.append("&termsOfPayment=");
			buffer.append("&termsOfTrade=FCA");
			buffer.append("&exportReasonCode=P");
			buffer.append("&recipientTaxIdOrVAT=");
			buffer.append("&namedDestinationPort=");
			buffer.append("&remarks=");
			buffer.append("&otherCharges=");
			buffer.append("&itemDescription=" + objCIC.getCiciename() + " Sample");
			String unitValue = objCIC.getCiciunitprice();
			if (StringUtility.isNull(unitValue) || new BigDecimal(unitValue).equals(BigDecimal.ZERO)) {
				unitValue = new BigDecimal(objCIC.getCicitotalprice())
						.divide(new BigDecimal(objCIC.getCicipieces()), 2, RoundingMode.HALF_UP).toString();
			}
			buffer.append("&unitValue=" + unitValue);
			buffer.append("&countryOfManufacture=CN");
			buffer.append("&netWeight=0.010");
			buffer.append("&quantity=" + objCIC.getCicipieces());
			buffer.append("&grossWeight=0.010");
			buffer.append("&unitOfMeasure=PCS");
			buffer.append("&commodityCode=");
			buffer.append("&invoiceTemplateName=");
			buffer.append("&usersCurrentDate=");
			buffer.append("&naftaText=null");
			buffer.append("&hours=" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			invoices[i] = buffer.toString();
		}
		return invoices;
	}

	/**
	 * 包裹信息
	 * @param objFIAColumns
	 * @param strDeclaredvalue
	 * @param strDeclaredcurrency
	 * @param strDeclaredcontent
	 * @return
	 * @throws Exception
	 */
	private String packageInfo(ForinputallColumns objFIAColumns,List<?> listCargo ) throws Exception {
		StringBuffer buffer = new StringBuffer();
		String strDeclaredvalue = "0.00";
		String strDeclaredcurrency = "USD";
		String strDeclaredcontent = "DOC";
		if (listCargo != null && listCargo.size() > 0) {
			BigDecimal objDeclaredvalue = new BigDecimal("0");
			strDeclaredcontent = "";
			for (int i = 0; i < listCargo.size(); i++) {
				CargoinfoColumns objCIC = (CargoinfoColumns)listCargo.get(i);
				objDeclaredvalue = objDeclaredvalue.add(new BigDecimal(objCIC.getCicitotalprice()));
				if (StringUtility.isNull(objCIC.getCkckcode())) {
					strDeclaredcurrency = "USD";
				} else {
					strDeclaredcurrency = objCIC.getCkckcode();
				}
				strDeclaredcontent = strDeclaredcontent + objCIC.getCiciename() + " Sample,";
			}
			if (!StringUtility.isNull(strDeclaredcontent) && strDeclaredcontent.length() > 90) {
				strDeclaredcontent = strDeclaredcontent.substring(0, 89);
			}
			strDeclaredvalue = objDeclaredvalue.divide(new BigDecimal("1"), 2, 2).toString();
		}
		Calendar calendar = Calendar.getInstance();
		int usndcurrenttime = calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
		buffer.append("usndcurrenttime=" + usndcurrenttime);
		buffer.append("&UpdateAddress=");
		buffer.append("&COPY_REMOVE_FLAG=N");
		buffer.append("&insuranceDisplayFlag=N");
		buffer.append("&displayInsurance=Y");
		buffer.append("&fromShipmentDetails=Y");
		buffer.append("&expFtrChecked=");
		buffer.append("&packageType=YP");
		int cwPieces = Integer.valueOf(objFIAColumns.getCwpieces());
		String strWeight = objFIAColumns.getTransfervolumeweight();
		if (StringUtility.isNull(strWeight) || new BigDecimal(strWeight).equals(BigDecimal.ZERO)) {
			strWeight = objFIAColumns.getCwtransfergrossweight();
		}
		String strUnittransferweight = new BigDecimal(strWeight)
				.divide(new BigDecimal(cwPieces), 1, RoundingMode.HALF_UP).toString();
		for (int i = 0; i < cwPieces; i++) {
			buffer.append("&packageDimensionsListAdded=" + strUnittransferweight);
			buffer.append("&packageDimensionsListAdded=10");
			buffer.append("&packageDimensionsListAdded=10");
			buffer.append("&packageDimensionsListAdded=10");
			buffer.append("&packageDimensionsListAdded=");
		}
		buffer.append("&totalPieces=" + cwPieces);
		buffer.append("&totalWeight=" + new BigDecimal(strWeight).divide(BigDecimal.ONE, 1, RoundingMode.HALF_UP));
		buffer.append("&totalDimWeight=" + new BigDecimal(0.2 * cwPieces).divide(BigDecimal.ONE, 1, RoundingMode.HALF_UP));
		buffer.append("&contentDescription=" + strDeclaredcontent);
		buffer.append("&dutiableStatus=ON");
		String customerwbcode = objFIAColumns.getCwcustomerewbcode();
		buffer.append("&shipmentReference=" + (StringUtility.length(customerwbcode) > 6 
						? StringUtility.substring(customerwbcode, 0, 6) : customerwbcode));
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
		buffer.append("&startShipmentDate=" + dateFormat.format(new Date()));
		buffer.append("&declaredValue=" + strDeclaredvalue);
		buffer.append("&declaredValueCurrencyCode=" + strDeclaredcurrency);
		buffer.append("&insuranceValue=");
		buffer.append("&insuranceValueCurrencyCode=HKD");
		buffer.append("&invoiceCreationOnlineStatus=ON");
		buffer.append("&userInvoiceCheck=false");
		buffer.append("&hid1=none");
		buffer.append("&invoiceGeneration=yes");
		buffer.append("&paymentOption=S");
		buffer.append("&paymentAccountNumber=631164010");
		// 关税支付方式, R为DDU，S为DDP
		SpecialtypeColumns objSpecialtypeColumns = SpecialtypeDemand.load(objFIAColumns.getCwcode(), "A0201");
		if (objSpecialtypeColumns != null && StringUtility.isNull(objSpecialtypeColumns.getEstestname())) {
			buffer.append("&billDutiesTaxesTo=S");
			buffer.append("&dutyTaxAccountNumber=631164010");
		} else {
			buffer.append("&billDutiesTaxesTo=R");
			buffer.append("&dutyTaxAccountNumber=");
		}
		buffer.append("&hidCurCodeNoOfDigitsRight=AUD2EUR2HKD2JPY0USD2");
		buffer.append("&hidLocalCurCodeNoOfDigitsRight=AUD2EUR2HKD2JPY0USD2");
		buffer.append("&usersCurrentDate=" + DateFormatUtility.getDateString(new Date(), "yyyy-MM-dd"));
		buffer.append("&defWeight=0.5");
		buffer.append("&hours=" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		buffer.append("&defLength=");
		buffer.append("&defWidth=");
		buffer.append("&defHeight=");
		buffer.append("&defPieceContent=");
		buffer.append("&todayFlag=Y");
		buffer.append("&isCurrentDHLPackageChosen=N");
		return buffer.toString();
	}

	/**
	 * 发件人和收件人信息
	 * @param objFIAColumns
	 * @return
	 * @throws Exception
	 */
	private String srInfo(ForinputallColumns objFIAColumns) throws Exception {
		StringBuffer buffer = new StringBuffer();
		// 发件人信息
		buffer.append("senderShortName=");
		buffer.append("&senderContact=" + objFIAColumns.getHwshippername());
		buffer.append("&senderCompany=" + objFIAColumns.getHwshippercompany());
		buffer.append("&hiddenStreetNo=");
		buffer.append("&senderAddress1=" + objFIAColumns.getHwshipperaddress1());
		buffer.append("&senderAddress2=" + objFIAColumns.getHwshipperaddress2());
		buffer.append("&senderAddress3=" + objFIAColumns.getHwshipperaddress3());
		buffer.append("&senderCity=" + DistrictDemand.getDtenameByDtcode(objFIAColumns.getHwDtcodeshipper()));
		buffer.append("&senderSuburb= ");
		buffer.append("&sdrTextSuburb= ");
		buffer.append("&senderState=");
		buffer.append("&senderPhone=" + objFIAColumns.getHwshippertelephone());
		buffer.append("&senderMobileNumber=");
		buffer.append("&senderFax=");
		buffer.append("&senderEmailAddress=");
		buffer.append("&taxId=");
		// 收件人信息
		buffer.append("&recipientCountryCode=" + DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()));
		buffer.append("&recipientHiddenCountryCode=");
		buffer.append("&recipientShortName=");
		buffer.append("&recipientContact=" + objFIAColumns.getHwconsigneename());
		buffer.append("&recipientCompany=" + objFIAColumns.getHwconsigneecompany());
		buffer.append("&recipientLookupFlag=");
		buffer.append("&recipientAddress1=" + objFIAColumns.getHwconsigneeaddress1());
		buffer.append("&recipientAddress2=" + objFIAColumns.getHwconsigneeaddress2());
		buffer.append("&recipientAddress3=" + objFIAColumns.getHwconsigneeaddress3());
		buffer.append("&recipientPostalCode=" + objFIAColumns.getHwconsigneepostcode());
		buffer.append("&recipientCity=" + objFIAColumns.getHwConsigneecity());
		buffer.append("&rcvrTextSuburb= ");
		buffer.append("&recipientState=" + DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity(), 
					DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getDtcode()),
					DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()), 
					objFIAColumns.getHwconsigneepostcode()));
		buffer.append("&recipientPhone=" + objFIAColumns.getHwconsigneetelephone());
		buffer.append("&recipientMobileNumber=");
		buffer.append("&recipientFax=");
		buffer.append("&recipientEmailAddress=");
		buffer.append("&recipientTaxId=");
		buffer.append("&nfeKey=");
		buffer.append("&cteShipmentType=no_column_selected");
		buffer.append("&isNfeSelectedNo=");
		return buffer.toString();
	}

}
