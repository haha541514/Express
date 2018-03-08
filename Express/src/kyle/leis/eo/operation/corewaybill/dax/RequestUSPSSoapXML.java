package kyle.leis.eo.operation.corewaybill.dax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.metrology.WeightConversion;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class RequestUSPSSoapXML extends RequestXML {
	private final String requesterID = "smp";
	private final int accountID = 971705;
	private final String passPhrase = "bqc1001000";

	@Override
	public String buildRequestXML(ForinputallColumns objFIAColumns,
			List listCargo, 
			List listPieces,
			PromptUtilityCollection objPUCollection)
			throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<LabelRequest ImageFormat=\"PNG\" Test=\"NO\">");
		stringBuilder.append("<RequesterID>" + requesterID + "</RequesterID>");// 请求标识
		stringBuilder.append("<AccountID>" + accountID + "</AccountID>");// 账号（必须为数字）
		stringBuilder.append("<PassPhrase>" + passPhrase + "</PassPhrase>");// 密码
		stringBuilder.append("<ReferenceID>"
				+ objFIAColumns.getCwcustomerewbcode() + "</ReferenceID>");
		// 包裹重量
		String strWeight = objFIAColumns.getTransfervolumeweight();
		if (StringUtility.isNull(objFIAColumns.getTransfervolumeweight())
				|| new BigDecimal(objFIAColumns.getTransfervolumeweight())
						.equals(new BigDecimal("0")))
			strWeight = objFIAColumns.getCwtransfergrossweight();
		String strUnittransferweight = new BigDecimal(strWeight).divide(
				new BigDecimal(objFIAColumns.getCwpieces()), 2, 4).toString();
		double weightQz = WeightConversion.convertKgToOunce(Double
				.valueOf(strUnittransferweight));
		stringBuilder.append("<WeightOz>"
				+ new BigDecimal(weightQz).divide(BigDecimal.ONE, 1,
						RoundingMode.HALF_UP) + "</WeightOz>");// 包裹的重量，以盎司为单位
		String mailClass = "FIRST";
		if (weightQz > 13.0d) {
			mailClass = "Priority";
		}
		stringBuilder.append("<MailClass>" + mailClass + "</MailClass>");// 邮件类别
		stringBuilder
				.append("<PartnerTransactionID>971705</PartnerTransactionID>");// 合作交易ID
		/**************** 收件人信息 ***************/
		stringBuilder.append("<ToName>" + objFIAColumns.getHwconsigneename()
				+ "</ToName>");
		stringBuilder.append("<ToCompany>"
				+ objFIAColumns.getHwconsigneecompany() + "</ToCompany>");
		// 截取地址，地址不能超过47个字符
		String strAddress = buildAddress(
				objFIAColumns.getHwconsigneeaddress1(), objFIAColumns
						.getHwconsigneeaddress2(), objFIAColumns
						.getHwconsigneeaddress3());
		String[] astrAddress = splitAddress(strAddress, 47);
		int size = astrAddress.length < 4 ? astrAddress.length : 4;
		for (int i = 0; i < size; i++) {
			stringBuilder.append("<ToAddress" + (i + 1) + ">" + astrAddress[i]
					+ "</ToAddress" + (i + 1) + ">");
		}
		stringBuilder.append("<ToCountryCode>"
				+ DistrictDemand.getCountryHubcodeByCity(objFIAColumns
						.getDtcode()) + "</ToCountryCode>");
		stringBuilder.append("<ToCity>" + objFIAColumns.getHwConsigneecity()
				+ "</ToCity>");
		stringBuilder.append("<ToState>"
				+ DistrictDemand.getDHLStateCode(objFIAColumns
						.getHwConsigneecity(), DistrictDemand
						.getDthubcodeByDtcode(objFIAColumns.getDtcode()),
						DistrictDemand.getCountryHubcodeByCity(objFIAColumns
								.getDtcode()), objFIAColumns
								.getHwconsigneepostcode()) + "</ToState>");// 收件人所在州代码
		stringBuilder.append("<ToPostalCode>"
				+ objFIAColumns.getHwconsigneepostcode() + "</ToPostalCode>");// 收件人邮编
		stringBuilder.append("<ToPhone>"
				+ objFIAColumns.getHwconsigneetelephone() + "</ToPhone>");
		/***************** 发件人信息 *****************/
		stringBuilder.append("<FromName>" + objFIAColumns.getHwshippername()
				+ "</FromName>");
		stringBuilder.append("<FromCompany>"
				+ objFIAColumns.getHwshippercompany() + "</FromCompany>");
		if (validateAddress(objFIAColumns.getHwshipperaddress1())) {
			stringBuilder.append("<ReturnAddress1>"
					+ objFIAColumns.getHwshipperaddress1()
					+ "</ReturnAddress1>");// 发件人地址
		}
		if (validateAddress(objFIAColumns.getHwshipperaddress2())) {
			stringBuilder.append("<ReturnAddress2>"
					+ objFIAColumns.getHwshipperaddress2()
					+ "</ReturnAddress2>");
		}
		if (validateAddress(objFIAColumns.getHwshipperaddress3())) {
			stringBuilder.append("<ReturnAddress3>"
					+ objFIAColumns.getHwshipperaddress3()
					+ "</ReturnAddress3>");
		}
		stringBuilder.append("<FromCountry>"
				+ DistrictDemand.getCountryEnameByCity(objFIAColumns
						.getHwDtcodeshipper()) + "</FromCountry>");
		stringBuilder.append("<FromCity>"
				+ DistrictDemand.getDtenameByDtcode(objFIAColumns
						.getHwDtcodeshipper()) + "</FromCity>");
		stringBuilder.append("<FromState>Guangdong</FromState>");
		stringBuilder.append("<FromPostalCode>"
				+ objFIAColumns.getHwshipperpostcode() + "</FromPostalCode>");
		stringBuilder.append("</LabelRequest>");
		return stringBuilder.toString();
	}

	private String buildAddress(String address1, String address2,
			String address3) {
		StringBuilder builder = new StringBuilder();
		if (validateAddress(address1)) {
			builder.append(address1);
		}
		if (validateAddress(address2)) {
			builder.append(" " + address2);
		}
		if (validateAddress(address3)) {
			builder.append(" " + address3);
		}
		return builder.toString().trim();
	}

	private boolean validateAddress(String address) {
		return !StringUtility.isNull(address) && !".".equals(address);
	}

	private String[] splitAddress(String strAddress, int addressLength) {
		int size = strAddress.length() / addressLength;
		if (strAddress.length() % addressLength != 0) {
			size++;
		}
		String[] astrAddress = new String[size];
		for (int i = 0; i < size; i++) {
			if (i < size - 1) {
				astrAddress[i] = strAddress.substring(i * addressLength,
						(i + 1) * addressLength);
			} else {
				astrAddress[i] = strAddress.substring(i * addressLength);
			}
		}
		return astrAddress;
	}

}
