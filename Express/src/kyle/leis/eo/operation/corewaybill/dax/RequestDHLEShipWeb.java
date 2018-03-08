package kyle.leis.eo.operation.corewaybill.dax;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.specialtype.da.SpecialtypeColumns;
import kyle.leis.eo.operation.specialtype.dax.SpecialtypeDemand;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;
import kyle.leis.hi.TchnChannel;

public class RequestDHLEShipWeb extends RequestXML {

	@Override
	public String buildRequestXML(ForinputallColumns objFIAColumns,
			List listCargo,
			List listPieces,
			PromptUtilityCollection objPUCollection)
			throws Exception {
		String strChncode = objFIAColumns.getChncode_Cwspchn();
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
		String strReference = objFIAColumns.getCwcustomerewbcode();
		String strSystemPE = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strSystemPE)
				&& (strSystemPE.startsWith("SBD") || strSystemPE.startsWith("SLY"))
				&& objTchnChannel.getTdiServerstructuregroup() != null
				&& "DHL_NC".equals(objTchnChannel.getTdiServerstructuregroup()
						.getSsgCode())) {
			if (strReference.length() >= 6)
				strReference = strReference
						.substring(strReference.length() - 6);
		}
		if (objTchnChannel.getTdiServerstructuregroup() != null && 
				("DHL_HKMY").equals(objTchnChannel.getTdiServerstructuregroup().getSsgCode())) {
			if (strReference.length() >= 6)
				strReference = strReference.substring(strReference.length() - 6, strReference.length());
			strReference = "SV11-Y-" + strReference;
		}		
		StringBuilder builder = new StringBuilder();
		// 付款人账户信息
		String pt = "S";
		if (objTchnChannel.getChnMasteraccount() != null
				&& !objTchnChannel.getChnMasteraccount().equals(objTchnChannel.getChnPaymentaccount())) {
			pt = "T";
		}
		builder.append("ShippingPaymentType=" + pt);
		builder.append("&BillingAccountNumber="
				+ objTchnChannel.getChnPaymentaccount());
		// 发件人信息
		builder.append("&ShipperID=" + objTchnChannel.getChnMasteraccount());
		builder.append("&Ship_PersonName="
				+ StringUtility.splitMaxLength(
						objFIAColumns.getHwshippername(), 35));
		builder.append("&ReferenceID=" + strReference);
		builder.append("&CompanyName="
				+ StringUtility.splitMaxLength(objFIAColumns
						.getHwshippercompany(), 35));
		builder.append("&CountryName="
				+ DistrictDemand.getCountryEnameByCity(objFIAColumns
						.getHwDtcodeshipper()));
		builder.append("&Ship_City="
				+ DistrictDemand.getDtenameByDtcode(objFIAColumns
						.getHwDtcodeshipper()));
		builder.append("&AddressLine0="
				+ StringUtility.splitMaxLength(objFIAColumns
						.getHwshipperaddress1(), 35));
		builder.append("&AddressLine1="
				+ StringUtility.splitMaxLength(objFIAColumns
						.getHwshipperaddress2(), 35));
		builder.append("&AddressLine2="
				+ StringUtility.splitMaxLength(objFIAColumns
						.getHwshipperaddress3(), 35));
		builder.append("&PostalCode=" + objFIAColumns.getHwshipperpostcode());
		builder.append("&PhoneNumber="
				+ objFIAColumns.getHwshippertelephone().replace("-", ""));
		// 收件人信息
		String strTocompany = objFIAColumns.getHwconsigneecompany();
		if (".".equals(strTocompany))
			strTocompany = "..";
		
		builder.append("&ToCompany="
				+ StringUtility.splitMaxLength(strTocompany, 35));
		
		String toCountryCode = DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode());
		builder.append("&ToCountryCode=" + toCountryCode);
		builder.append("&ToCountryName="
				+ DistrictDemand.getCountryEnameByCity(objFIAColumns
						.getDtcode()));
		builder.append("&DivisionCode="
				+ DistrictDemand.getDHLStateCode(objFIAColumns
						.getHwConsigneecity(), DistrictDemand
						.getDthubcodeByDtcode(objFIAColumns.getDtcode()),
						DistrictDemand.getCountryHubcodeByCity(objFIAColumns
								.getDtcode()), objFIAColumns
								.getHwconsigneepostcode()));// 所在州
		builder.append("&ToAddress0="
				+ StringUtility.splitMaxLength(objFIAColumns
						.getHwconsigneeaddress1(), 35));
		builder.append("&ToAddress1="
				+ StringUtility.splitMaxLength(objFIAColumns
						.getHwconsigneeaddress2(), 35));
		builder.append("&ToAddress2="
				+ StringUtility.splitMaxLength(objFIAColumns
						.getHwconsigneeaddress3(), 35));
		builder.append("&ToCity="
				+ StringUtility.splitMaxLength(objFIAColumns
						.getHwConsigneecity(), 35));
		builder.append("&ToPersonName="
				+ StringUtility.splitMaxLength(objFIAColumns
						.getHwconsigneename(), 35));
		if ("GB".equals(toCountryCode)) { // 英国邮编处理
			String toPostalCode = StringUtility.replaceWhenNull(objFIAColumns.getHwconsigneepostcode(), "");
			String[] toPostalCodes = toPostalCode.split("\\s+"); // 以空格分隔
			builder.append("&ToPostalCodeUK1=" + toPostalCodes[0]);
			if (toPostalCodes.length > 1) {
				builder.append("&ToPostalCodeUK2=" + toPostalCodes[1]);
			}
		} else {
			
			//String strCountryHubcode = DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode());
			String strPostcode = objFIAColumns.getHwconsigneepostcode(); //autoRebuildDHLPostcode(toCountryCode, objFIAColumns.getHwconsigneepostcode());
			if (!StringUtility.isNull(strPostcode) && strPostcode.startsWith("E_"))
				return strPostcode;					
			
			builder.append("&ToPostalCode="
					+ StringUtility.replaceWhenNull(strPostcode, ""));
		}
		builder.append("&ToPhoneNumber="
				+ objFIAColumns.getHwconsigneetelephone().replace("-", ""));
		// 快件信息
		String GlobalProductCode = "P";
		if (objFIAColumns.getCtcode().equals("ADOX")) {
			GlobalProductCode = "D";
		}
		builder.append("&GlobalProductCode=" + GlobalProductCode);// 货物类型（文件包裹）
		builder.append("&LocalProductCode=Express Worldwide");
		builder.append("&Total_Pics="
				+ Integer.parseInt(objFIAColumns.getCwpieces()));// 件数量
		String total_Weight = "0.5";
		if (new BigDecimal(objFIAColumns.getCwtransferchargeweight())
				.compareTo(new BigDecimal("0.5")) > 0) {
			total_Weight = new BigDecimal(objFIAColumns
					.getCwtransferchargeweight()).divide(new BigDecimal("1"),
					0, 1).toString();
		}
		builder.append("&Total_Weight=" + total_Weight);// 件重量
		String strGoods = "";
		String strWeight = objFIAColumns.getTransfervolumeweight();
		if (StringUtility.isNull(objFIAColumns.getTransfervolumeweight())
				|| new BigDecimal(objFIAColumns.getTransfervolumeweight())
						.equals(new BigDecimal("0")))
			strWeight = objFIAColumns.getCwtransfergrossweight();
		BigDecimal strUnittransferweight = new BigDecimal(strWeight).divide(
				new BigDecimal(objFIAColumns.getCwpieces()), 2, 4);
		int iTransferPieces = Integer.parseInt(objFIAColumns.getCwpieces());
		for (int i = 0; i < iTransferPieces; i++) {
			if (i == 0) {
				strGoods += i + "|1|" + strUnittransferweight + "|0|0|0";
			} else {
				strGoods += ";" + i + "|1|" + strUnittransferweight + "|0|0|0";
			}
		}
		builder.append("&GoodsStr=" + strGoods);// 货物信息（重量以及长宽高）
		// 发票信息
		String strDeclaredvalue = "0.00";// 申报价值
		String strDeclaredcurrency = "USD";// 申报货币
		String strDeclaredcontent = "DOC";// 说明
		String inviceDetailString = "";// 发票详情
		String madeInCountryCode = "CN";
		String madeInCountryName = "China";
		String goodsCode = "";
		BigInteger totalPieces = BigInteger.ZERO;
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
				if (i > 0) {
					inviceDetailString += ";";
				}
				inviceDetailString += objCIC.getCiciename() + "|"
						+ objCIC.getCicipieces() + "|" + goodsCode + "|"
						+ objCIC.getCiciunitprice() + "|" + madeInCountryCode
						+ "|" + madeInCountryName;
				totalPieces = totalPieces.add(new BigInteger(objCIC
						.getCicipieces()));
			}
			strDeclaredvalue = objDeclaredvalue.divide(new BigDecimal("1"), 2,
					2).toString();
		}
		builder.append("&MakeInvice=1");// 是否制作发票
		builder.append("&inviceType=1");// 发票类型
		builder.append("&inviceDetailString=" + inviceDetailString);
		builder.append("&Contents=" + strDeclaredcontent);
		builder.append("&DeclaredValue=" + strDeclaredvalue);
		builder.append("&DeclaredCurrencyCode=" + strDeclaredcurrency);
		String dutyType = "R";
		
		if(!StringUtility.isNull(objFIAColumns.getCwcode())) {
			List listSpecialtype = SpecialtypeDemand.load(objFIAColumns.getCwcode());
			if (listSpecialtype != null && listSpecialtype.size() > 0) {
				for (Object object : listSpecialtype) {
					SpecialtypeColumns specialtypeColumns = (SpecialtypeColumns) object;
					if ("A0201".equals(specialtypeColumns.getWbstcomp_idestcode())) {
						dutyType = "S";
						break;
					}
				}
			}
		}
		builder.append("&DutyPaymentType=" + dutyType);// 目的地关税与税金指定被认可的账号
		builder.append("&exportType=1");// 出口类型（Permanent(永久)）
		builder.append("&ttermsFp=6");// 贸易条款（DAP-Delivered at Place）
		builder.append("&TotalPieces=" + totalPieces.toString());// 发票件数
		builder.append("&TotalDeclaredValue=" + strDeclaredvalue);
		builder.append("&inviceCode=");// 发票号码
		builder.append("&date="
				+ DateFormatUtility.getSysdateString("yyyy-MM-dd"));
		builder.append("&note=");
		builder.append("&tdx=0");// 额外服务选项
		builder.append("&dayinType=dayinLabel");
		return builder.toString();
	}

}
