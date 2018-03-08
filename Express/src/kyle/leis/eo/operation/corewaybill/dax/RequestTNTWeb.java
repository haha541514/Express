package kyle.leis.eo.operation.corewaybill.dax;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.hi.TchnChannel;

import org.apache.commons.lang.time.DateFormatUtils;

public class RequestTNTWeb extends RequestXML {
	private final int addressLength = 30;
	private StringBuilder builder;

	public RequestTNTWeb() {
		builder = new StringBuilder();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String buildRequestXML(ForinputallColumns objFIAColumns,
			List listCargo,
			List listPieces,
			PromptUtilityCollection objPUCollection)
			throws Exception {
		String strChncode = objFIAColumns.getChncode_Cwspchn();
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
		String strReference = objFIAColumns.getCwcustomerewbcode();
		builder.append("cmd=continue");
		builder.append("&AcceptTermsAndConditions=false");
		builder.append("&bypassOversizedShipmentValidation=");
		builder.append("&inConversation=1");
		builder.append("&byPassLmfValidation=false");
		builder.append("&beenSubmittedOnce=true");
		builder.append("&senderAddressBookAccessDate=");
		/********* 发件人信息 *********/
		senderInfo(objFIAColumns, objTchnChannel);
		collectionInfo(objFIAColumns, objTchnChannel);// 取件人信息
		/************ 收件人信息 *****************/
		receiverInfo(objFIAColumns);
		/************************* 快件信息 ***************/
		shippingInfo(objFIAColumns, listCargo, objTchnChannel, strReference);

		return builder.toString();
	}

	/**
	 * 发件人信息
	 * 
	 * @param objFIAColumns
	 * @param objTchnChannel
	 * @throws Exception
	 */
	private void senderInfo(ForinputallColumns objFIAColumns,
			TchnChannel objTchnChannel) throws Exception {
		builder.append("&senderAccountNumber="
				+ objTchnChannel.getChnMasteraccount());// 发件人账号
		builder.append("&senderAccountType=I");// 账号类型
		builder.append("&senderAddressList="
				+ objTchnChannel.getChnMasteraccount());
		builder.append("&senderCountry="
				+ DistrictDemand.getCountryHubcodeByCity(objFIAColumns
						.getHwDtcodeshipper()));
		builder.append("&senderCountryDisplay="
				+ DistrictDemand.getCountryEnameByCity(objFIAColumns
						.getHwDtcodeshipper()));
		String strAddress = objFIAColumns.getHwshipperaddress1() + " "
				+ objFIAColumns.getHwshipperaddress2() + " "
				+ objFIAColumns.getHwshipperaddress3();
		String[] addresses = splitAddress(strAddress, 3);
		builder.append("&senderAddress1=" + addresses[0]);
		builder.append("&senderAddress2=" + addresses[1]);
		builder.append("&senderAddress3=" + addresses[2]);
		builder.append("&senderPostcode="
				+ objFIAColumns.getHwshipperpostcode());
		builder.append("&senderTown="
				+ DistrictDemand.getDtenameByDtcode(objFIAColumns
						.getHwDtcodeshipper()));
		builder.append("&senderProvince=");
		builder.append("&senderCompanyName="
				+ StringUtility.splitMaxLength(objFIAColumns
						.getHwshippercompany(), 35));
		builder.append("&senderLMFValType=PR");
		builder.append("&senderContactPerson="
				+ StringUtility.splitMaxLength(
						objFIAColumns.getHwshippername(), 35));
		builder.append("&senderContactPhone="
				+ objFIAColumns.getHwshippertelephone().replace("-", ""));
		builder.append("&senderContactEmail=");
		builder.append("&senderVAT=");
	}

	/**
	 * 取件人信息
	 * 
	 * @throws Exception
	 */
	private void collectionInfo(ForinputallColumns objFIAColumns,
			TchnChannel objTchnChannel) throws Exception {
		builder.append("&collectionAccountNumber="
				+ objTchnChannel.getChnMasteraccount());
		
		String chnName = objTchnChannel.getChnSename();
		if ("HKTNT-BL-15N".equals(chnName) || "HKTNT-BL-48N".equals(chnName)
				|| "HKTNT-GL-ME".equals(chnName)) {
			collectionInfoByChn(objFIAColumns, chnName);
			return;
		}
		builder.append("&collectionShortReference=Airport");
		builder.append("&collectionAddressBookAccessDate=");
		builder.append("&collectionaddressBookLookupType=C");
		builder.append("&collectionCompanyName="
				+ StringUtility.splitMaxLength(objFIAColumns
						.getHwshippercompany(), 35));
		builder.append("&collectionCountry=HK");
		builder.append("&collectionPostcode=");
		builder.append("&collectionTown=CHEK LAP KOK");
		builder.append("&collectionpostCodeMask=");
		builder.append("&collectionLMFValType=PR");
		builder.append("&collectionAddress1=Unit 103 - 106, 1/F, Airport F");
		builder.append("&collectionAddress2=Airport Freight Forwarding Ctr");
		builder.append("&collectionAddress3=2 Chun Wan Road,");
		builder.append("&collectionProvince=");
		builder.append("&collectionContactPerson="
				+ StringUtility.splitMaxLength(
						objFIAColumns.getHwshippername(), 35));
		builder.append("&collectionContactPhone="
				+ objFIAColumns.getHwshippertelephone().replace("-", ""));
		builder.append("&collectionContactEmail=");
		builder.append("&collectionVAT=");
	}

	private void collectionInfoByChn(ForinputallColumns objFIAColumns, String chnName) {
		builder.append("&collectionShortReference=Airport");
		builder.append("&collectionAddressBookAccessDate=");
		builder.append("&collectionaddressBookLookupType=C");
		String company = "B-LINE LTD";
		if ("HKTNT-GL-ME".equals(chnName)) {
			company = "GOLD LIFE HK LTD";
		}
		builder.append("&collectionCompanyName=" + company);
		builder.append("&collectionCountry=HK");
		builder.append("&collectionPostcode=");
		builder.append("&collectionTown=KWAI CHUNG");
		builder.append("&collectionpostCodeMask=");
		builder.append("&collectionLMFValType=PR");
		builder.append("&collectionAddress1=SHAN HA ROAD");
		builder.append("&collectionAddress2=DD121 LOT1556RP");
		builder.append("&collectionAddress3=YUEN LONG HONGKONG");
		builder.append("&collectionProvince=");
		builder.append("&collectionContactPerson=Carmen");
		builder.append("&collectionContactPhone=0852-2419 9956");
		builder.append("&collectionContactEmail=");
		builder.append("&collectionVAT=");
	}

	/**
	 * 收件人信息
	 * 
	 * @param objFIAColumns
	 * @throws Exception
	 */
	private void receiverInfo(ForinputallColumns objFIAColumns)
			throws Exception {
		builder.append("&receiverCompanyName="
				+ StringUtility.splitMaxLength(objFIAColumns
						.getHwconsigneecompany(), 35));
		builder.append("&receiverCountry="
				+ DistrictDemand.getCountryHubcodeByCity(objFIAColumns
						.getDtcode()));
		builder.append("&receivercountryCode="
				+ DistrictDemand.getCountryHubcodeByCity(objFIAColumns
						.getDtcode()));
		builder.append("&receiverPostcode="
				+ objFIAColumns.getHwconsigneepostcode());
		builder.append("&receiverTown="
				+ StringUtility.splitMaxLength(objFIAColumns
						.getHwConsigneecity(), 35));
		builder.append("&receiverLMFValType=");
		String strAddress = objFIAColumns.getHwconsigneeaddress1() + " "
				+ objFIAColumns.getHwconsigneeaddress2() + " "
				+ objFIAColumns.getHwconsigneeaddress3();
		String[] addresses = splitAddress(strAddress, 3);
		builder.append("&receiverAddress1=" + addresses[0]);
		builder.append("&receiverAddress2=" + addresses[1]);
		builder.append("&receiverAddress3=" + addresses[2]);
		builder.append("&receiverProvince="
				+ DistrictDemand.getDHLStateCode(objFIAColumns
						.getHwConsigneecity(), DistrictDemand
						.getDthubcodeByDtcode(objFIAColumns.getDtcode()),
						DistrictDemand.getCountryHubcodeByCity(objFIAColumns
								.getDtcode()), objFIAColumns
								.getHwconsigneepostcode()));// 所在州
		builder.append("&receiverContactPerson="
				+ StringUtility.splitMaxLength(objFIAColumns
						.getHwconsigneename(), 35));
		builder.append("&receiverContactPhone="
				+ objFIAColumns.getHwconsigneetelephone().replace("-", ""));
		builder.append("&receiverContactEmail=");
		builder.append("&receiverVAT=");
	}

	/**
	 * 快件信息
	 * 
	 * @param objFIAColumns
	 * @param listCargo
	 * @param objTchnChannel
	 * @param strReference
	 */
	@SuppressWarnings("unchecked")
	private void shippingInfo(ForinputallColumns objFIAColumns, List listCargo,
			TchnChannel objTchnChannel, String strReference) {
		builder.append("&shipmentAccessDate=");
		builder.append("&TotalVolume="
				+ objFIAColumns.getTransfervolumeweight());// 体积重量
		builder.append("&TotalWeight="
				+ objFIAColumns.getCwtransferchargeweight());
		builder.append("&TotalQuantity=" + objFIAColumns.getCwpieces());
		String docOrNonDoc = "N";// 货物类型（包裹）
		String packageType = "BG";// 包裹类型
		if (objFIAColumns.getCtcode().equals("ADOX")) {
			docOrNonDoc = "D";// 文档
			packageType = "DOC";
		}
		builder.append("&DocOrNonDoc=" + docOrNonDoc);
		{
			/*************** 发票信息 *******************/
			cargoInfo(listCargo, objTchnChannel, strReference);
		}
		// 件重量
		String strWeight = objFIAColumns.getCwtransferchargeweight();
		String strUnittransferweight = new BigDecimal(strWeight).divide(
				new BigDecimal(objFIAColumns.getCwpieces()), 2, 4).toString();
		int iTransferPieces = Integer.parseInt(objFIAColumns.getCwpieces());
		for (int i = 0; i < iTransferPieces; i++) {
			builder.append("&PackageType=" + packageType);
			builder.append("&NumberOfPackages=1");
			builder.append("&Weight=" + strUnittransferweight);
		}
	}

	/**
	 * 发票信息
	 * 
	 * @param listCargo
	 * @param objTchnChannel
	 * @param strReference
	 */
	@SuppressWarnings("unchecked")
	private void cargoInfo(List listCargo, TchnChannel objTchnChannel,
			String strReference) {
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
		builder.append("&Value=" + strDeclaredvalue);
		builder.append("&ValueCurrency=" + strDeclaredcurrency);
		builder.append("&CustomControlled=Y");
		builder.append("&DangerousGoodsOptionDomestic=");
		builder.append("&DangerousGoodsOptionInternational=");
		builder.append("&DangerousGoodsOption=");
		builder.append("&UNNumber=");
		builder.append("&PackingClass=");
		String termsOfPayment = "S";// 付款类型
		String receiverAccountNumber = "";// 如果付款类型为S，则收件人账号设为空
		if (!objTchnChannel.getChnMasteraccount().equals(
				objTchnChannel.getChnPaymentaccount())) {
			termsOfPayment = "R";
			receiverAccountNumber = objTchnChannel.getChnPaymentaccount();
		}
		builder.append("&TermsOfPayment=" + termsOfPayment);
		builder.append("&ReceiverAccountNumber=" + receiverAccountNumber);
		builder.append("&Reference=" + strReference);
		builder.append("&SpecialInstructions=");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 2);
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			// 如果两天后是星期天，再往后推一天
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
		}
		String shippingDate = DateFormatUtils.format(calendar.getTime(),
				"yyyyMMdd");
		builder.append("&ShippingDate=" + shippingDate);
		builder.append("&goodsDescription=" + strDeclaredcontent);
	}

	/**
	 * 地址截取
	 * 
	 * @param strAddress
	 * @param length
	 * @return
	 */
	private String[] splitAddress(String strAddress, int length) {
		String[] astrAddress = new String[length];
		int index = addressLength - 1;
		for (int i = 0; i < length; i++) {
			astrAddress[i] = ".";
			if (strAddress.length() > i * index) {
				if (strAddress.length() > (i + 1) * index)
					astrAddress[i] = strAddress.substring(i * index, (i + 1)
							* index);
				else
					astrAddress[i] = strAddress.substring(i * index);
			}
		}
		return astrAddress;
	}

}
