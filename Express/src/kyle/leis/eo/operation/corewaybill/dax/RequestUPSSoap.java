package kyle.leis.eo.operation.corewaybill.dax;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.hi.TchnChannel;

import com.WaybillcodeParam;
import com.ups.stub.ShipServiceStub.AccountAddressType;
import com.ups.stub.ShipServiceStub.AddressType;
import com.ups.stub.ShipServiceStub.BillShipperType;
import com.ups.stub.ShipServiceStub.BillThirdPartyChargeType;
import com.ups.stub.ShipServiceStub.ContactType;
import com.ups.stub.ShipServiceStub.DimensionsType;
import com.ups.stub.ShipServiceStub.InternationalFormType;
import com.ups.stub.ShipServiceStub.LabelImageFormatType;
import com.ups.stub.ShipServiceStub.LabelSpecificationType;
import com.ups.stub.ShipServiceStub.PackageType;
import com.ups.stub.ShipServiceStub.PackageWeightType;
import com.ups.stub.ShipServiceStub.PackagingType;
import com.ups.stub.ShipServiceStub.PaymentInfoType;
import com.ups.stub.ShipServiceStub.PhoneType;
import com.ups.stub.ShipServiceStub.ProductType;
import com.ups.stub.ShipServiceStub.RequestType;
import com.ups.stub.ShipServiceStub.ServiceType;
import com.ups.stub.ShipServiceStub.ShipAddressType;
import com.ups.stub.ShipServiceStub.ShipFromType;
import com.ups.stub.ShipServiceStub.ShipPhoneType;
import com.ups.stub.ShipServiceStub.ShipToAddressType;
import com.ups.stub.ShipServiceStub.ShipToType;
import com.ups.stub.ShipServiceStub.ShipUnitOfMeasurementType;
import com.ups.stub.ShipServiceStub.ShipmentChargeType;
import com.ups.stub.ShipServiceStub.ShipmentRequest;
import com.ups.stub.ShipServiceStub.ShipmentServiceOptions_type0;
import com.ups.stub.ShipServiceStub.ShipmentType;
import com.ups.stub.ShipServiceStub.ShipperType;
import com.ups.stub.ShipServiceStub.SoldToType;
import com.ups.stub.ShipServiceStub.TransactionReferenceType;
import com.ups.stub.ShipServiceStub.UnitOfMeasurementType;
import com.ups.stub.ShipServiceStub.UnitType;

public class RequestUPSSoap extends RequestXMLEX {

	@SuppressWarnings("unchecked")
	@Override
	public WaybillcodeParam buildRequest(ForinputallColumns objFIAColumns,
			List listCargo, 
			List listPieces,
			PromptUtilityCollection objPUCollection)
			throws Exception {
		// 渠道信息
		String strChncode = objFIAColumns.getChncode_Cwspchn();
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
		
		ShipmentRequest shipRequest = new ShipmentRequest();
		RequestType request = new RequestType();
		TransactionReferenceType transactionReference = new TransactionReferenceType();
		transactionReference.setCustomerContext(objFIAColumns.getCwcustomerewbcode());
		request.setTransactionReference(transactionReference);
		String[] requestOption = { "nonvalidate" }; // 是否验证邮编、地址
		request.setRequestOption(requestOption);
		shipRequest.setRequest(request);
		
		ShipmentType shpmnt = new ShipmentType();

		/** *******Shipper******************** */
		ShipperType shipper = new ShipperType();
		shipper.setName(objFIAColumns.getHwshippercompany());
		shipper.setAttentionName(objFIAColumns.getHwshippername());
		shipper.setShipperNumber(objTchnChannel.getChnMasteraccount()); // 发件人账号
		ShipPhoneType shipperPhone = new ShipPhoneType();
		shipperPhone.setNumber(objFIAColumns.getHwshippertelephone());
		shipper.setPhone(shipperPhone);
		ShipAddressType shipperAddress = new ShipAddressType();
		String[] addressLines = splitSAddress(objFIAColumns, 35, 3);
		shipperAddress.setAddressLine(addressLines);
		shipperAddress.setCity(DistrictDemand.getDtenameByDtcode(objFIAColumns.getHwDtcodeshipper()));
		shipperAddress.setPostalCode(objFIAColumns.getHwshipperpostcode());
		shipperAddress.setStateProvinceCode("");
		shipperAddress.setCountryCode(DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getHwDtcodeshipper()));
		shipper.setAddress(shipperAddress);
		shpmnt.setShipper(shipper);
		/********Shipper**********************/

		/**************ShipFrom*******************/
		ShipFromType shipFrom = new ShipFromType();
		shipFrom.setName(objFIAColumns.getHwshippercompany());
		shipFrom.setAttentionName(objFIAColumns.getHwshippername());
		ShipAddressType shipFromAddress = new ShipAddressType();
		shipFromAddress.setAddressLine(addressLines);
		shipFromAddress.setCity(DistrictDemand.getDtenameByDtcode(objFIAColumns.getHwDtcodeshipper()));
		shipFromAddress.setPostalCode(objFIAColumns.getHwshipperpostcode());
		shipFromAddress.setStateProvinceCode("");
		shipFromAddress.setCountryCode(DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getHwDtcodeshipper()));
		shipFrom.setAddress(shipFromAddress);
		shpmnt.setShipFrom(shipFrom);
		/*************ShipFrom**********************/

		/**************ShipTo*******************/
		ShipToType shipTo = new ShipToType();
		shipTo.setName(objFIAColumns.getHwconsigneecompany());
		shipTo.setAttentionName(objFIAColumns.getHwconsigneename());
		ShipToAddressType shipToAddress = new ShipToAddressType();
		ShipPhoneType shpPhone = new ShipPhoneType();
		shpPhone.setNumber(objFIAColumns.getHwconsigneetelephone());
		shipTo.setPhone(shpPhone);
		String[] shipToAddressLines = splitRAddress(objFIAColumns, 35, 3);
		shipToAddress.setAddressLine(shipToAddressLines);
		shipToAddress.setCity(objFIAColumns.getHwConsigneecity());
		shipToAddress.setStateProvinceCode(DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity(), 
				DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getDtcode()),
				DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()), 
				objFIAColumns.getHwconsigneepostcode()));
		shipToAddress.setPostalCode(objFIAColumns.getHwconsigneepostcode());
		shipToAddress.setCountryCode(DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()));
		shipTo.setAddress(shipToAddress);
		shpmnt.setShipTo(shipTo);
		/*************ShipTo********************* */

		/**********Service********************** */
		ServiceType service = new ServiceType();
		service.setCode("65");
		if (objTchnChannel.getTdiServerstructuregroup() != null &&
				objTchnChannel.getTdiServerstructuregroup().getSsgCode().startsWith("UPS_BLUE"))
			service.setCode("08");
		shpmnt.setService(service);
		/**********Service********************** */
		
		/********************Package***************** */
		String strWeight = objFIAColumns.getTransfervolumeweight();
		if (StringUtility.isNull(strWeight) ||new BigDecimal(strWeight).equals(BigDecimal.ZERO)){
			strWeight = objFIAColumns.getCwtransfergrossweight();
		}
		String strUnittransferweight = new BigDecimal(strWeight)
				.divide(new BigDecimal(objFIAColumns.getCwpieces()), 2, 4).toString();
		int iTransferPieces = Integer.parseInt(objFIAColumns.getCwpieces());
		PackageType[] pkgArray = new PackageType[iTransferPieces];
		for (int i = 0; i < iTransferPieces; i++) {
			PackageType pkg1 = new PackageType();
			PackagingType pkgingType = new PackagingType();
			pkgingType.setCode("02"); // 包裹类型
			pkg1.setPackaging(pkgingType);
			// 尺寸
			DimensionsType dimensionsType = new DimensionsType();
			ShipUnitOfMeasurementType unitOfMeasurementType = new ShipUnitOfMeasurementType();
			unitOfMeasurementType.setCode("CM");
			unitOfMeasurementType.setDescription("Centimeters");
			dimensionsType.setUnitOfMeasurement(unitOfMeasurementType);
			dimensionsType.setLength("0.1");
			dimensionsType.setHeight("0.1");
			dimensionsType.setWidth("0.1");
			pkg1.setDimensions(dimensionsType);
			// 重量
			PackageWeightType weight = new PackageWeightType();
			weight.setWeight(strUnittransferweight);
			ShipUnitOfMeasurementType shpUnitOfMeas = new ShipUnitOfMeasurementType();
			shpUnitOfMeas.setCode("KGS");
			shpUnitOfMeas.setDescription("Kilograms");
			weight.setUnitOfMeasurement(shpUnitOfMeas);
			pkg1.setPackageWeight(weight);
			pkgArray[i] = pkg1;
		}
		shpmnt.setPackage(pkgArray);
		/********************Package***************** */
		
		/********************Description***************** */
		String strDeclaredcontent = "DOC";
		String strCkcode = "USD";
		ProductType[] apt = null;
		if (listCargo != null && listCargo.size() > 0) {
			strDeclaredcontent = "";
			apt = new ProductType[listCargo.size()];
			for (int i = 0; i < listCargo.size(); i++) {
				apt[i] = new ProductType();
				CargoinfoColumns objCIC = (CargoinfoColumns)listCargo.get(i);
				strDeclaredcontent = strDeclaredcontent + objCIC.getCiciename() + ",";
				strCkcode = objCIC.getCkckcode();
				
				apt[i].setDescription(new String[] {objCIC.getCiciename()});
				UnitType unitType = new UnitType();
				unitType.setNumber(objCIC.getCicipieces());
				unitType.setValue(objCIC.getCicitotalprice());
				UnitOfMeasurementType unitOfMeasurementType = new UnitOfMeasurementType();
				unitOfMeasurementType.setCode("PCS");
				unitOfMeasurementType.setDescription("Pieces");
				unitType.setUnitOfMeasurement(unitOfMeasurementType);
				apt[i].setCommodityCode("621410");
				apt[i].setOriginCountryCode("HK");
				apt[i].setUnit(unitType);
			}
			if (!StringUtility.isNull(strDeclaredcontent) 
					&& strDeclaredcontent.length() > 35) {
				strDeclaredcontent = strDeclaredcontent.substring(0, 34);
			}
		}
		shpmnt.setDescription(strDeclaredcontent);
		// 发票
		if (listCargo != null && listCargo.size() > 0) {
			ShipmentServiceOptions_type0 sso = new ShipmentServiceOptions_type0();
			InternationalFormType ift = new InternationalFormType();
			ift.setFormType(new String[] { "01" });
			ift.setProduct(apt);
			ift.setCurrencyCode(strCkcode);
			ift.setInvoiceDate(DateFormatUtility.getCompactOnlyDateSysdate());
			ift.setReasonForExport("SALE");
			// 等于收件人资料
			ContactType ct = new ContactType();
			SoldToType stt = new SoldToType();
			
			stt.setName(objFIAColumns.getHwconsigneecompany());
			stt.setAttentionName(objFIAColumns.getHwconsigneename());
			PhoneType phoneType = new PhoneType();
			phoneType.setNumber(objFIAColumns.getHwconsigneetelephone());
			stt.setPhone(phoneType);
			
			AddressType addressType = new AddressType();
			addressType.setAddressLine(shipToAddressLines);
			addressType.setCity(objFIAColumns.getHwConsigneecity());
			addressType.setStateProvinceCode(DistrictDemand.getDHLStateCode(objFIAColumns.getHwConsigneecity(), 
					DistrictDemand.getDthubcodeByDtcode(objFIAColumns.getDtcode()),
					DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()), 
					objFIAColumns.getHwconsigneepostcode()));
			addressType.setPostalCode(objFIAColumns.getHwconsigneepostcode());
			addressType.setCountryCode(DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode()));
			stt.setAddress(addressType);			
			
			ct.setSoldTo(stt);
			ift.setContacts(ct);
			sso.setInternationalForms(ift);
			
			shpmnt.setShipmentServiceOptions(sso);
		}
		/********************Description***************** */

		/***************Payment Information***************** */
		PaymentInfoType payInfo = new PaymentInfoType();
		ShipmentChargeType shpmntCharge = new ShipmentChargeType();
		shpmntCharge.setType("01"); // 费用类型，01为运费，02为关税和普通税（国内快件不需要关税）
		
		if (objTchnChannel.getChnPaymentaccount().equals(objTchnChannel.getChnMasteraccount())) {
			BillShipperType billShipper = new BillShipperType();
			// 付款账号
			billShipper.setAccountNumber(objTchnChannel.getChnPaymentaccount()); 
			shpmntCharge.setBillShipper(billShipper);
		} else {
			// 第三方付款
			BillThirdPartyChargeType billThird = new BillThirdPartyChargeType();
			billThird.setAccountNumber(objTchnChannel.getChnPaymentaccount());
			AccountAddressType aat = new AccountAddressType();
			aat.setCountryCode("");
			aat.setPostalCode("");
			billThird.setAddress(aat);
			shpmntCharge.setBillThirdParty(billThird);
		}
		ShipmentChargeType[] shpmntChargeArray = { shpmntCharge };
		payInfo.setShipmentCharge(shpmntChargeArray);
		shpmnt.setPaymentInformation(payInfo);
		/** *************Payment Information***************** */
		
		
		/** **********Label Specification ******************** */
		LabelSpecificationType labelSpecType = new LabelSpecificationType();
		LabelImageFormatType labelImageFormat = new LabelImageFormatType();
		labelImageFormat.setCode("GIF");
		labelImageFormat.setDescription("GIF");
		labelSpecType.setLabelImageFormat(labelImageFormat);
		labelSpecType.setHTTPUserAgent("Mozilla/4.5");
		shipRequest.setLabelSpecification(labelSpecType);
		/** ***********Label Specification********************* */
		
		shipRequest.setShipment(shpmnt);
		
		WaybillcodeParam waybillcodeParam = new WaybillcodeParam();
		Map<String, ShipmentRequest> param = new HashMap<String, ShipmentRequest>();
		param.put("shipRequest", shipRequest);
		waybillcodeParam.setWaybillcodeParam(param);
		return waybillcodeParam;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String buildRequestXML(ForinputallColumns objFIAColumns,
			List listCargo,
			List listPieces,
			PromptUtilityCollection objPUCollection)
			throws Exception {
		return null;
	}

}
