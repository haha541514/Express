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
		// �������˻���Ϣ
		String pt = "S";
		if (objTchnChannel.getChnMasteraccount() != null
				&& !objTchnChannel.getChnMasteraccount().equals(objTchnChannel.getChnPaymentaccount())) {
			pt = "T";
		}
		builder.append("ShippingPaymentType=" + pt);
		builder.append("&BillingAccountNumber="
				+ objTchnChannel.getChnPaymentaccount());
		// ��������Ϣ
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
		// �ռ�����Ϣ
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
								.getHwconsigneepostcode()));// ������
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
		if ("GB".equals(toCountryCode)) { // Ӣ���ʱദ��
			String toPostalCode = StringUtility.replaceWhenNull(objFIAColumns.getHwconsigneepostcode(), "");
			String[] toPostalCodes = toPostalCode.split("\\s+"); // �Կո�ָ�
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
		// �����Ϣ
		String GlobalProductCode = "P";
		if (objFIAColumns.getCtcode().equals("ADOX")) {
			GlobalProductCode = "D";
		}
		builder.append("&GlobalProductCode=" + GlobalProductCode);// �������ͣ��ļ�������
		builder.append("&LocalProductCode=Express Worldwide");
		builder.append("&Total_Pics="
				+ Integer.parseInt(objFIAColumns.getCwpieces()));// ������
		String total_Weight = "0.5";
		if (new BigDecimal(objFIAColumns.getCwtransferchargeweight())
				.compareTo(new BigDecimal("0.5")) > 0) {
			total_Weight = new BigDecimal(objFIAColumns
					.getCwtransferchargeweight()).divide(new BigDecimal("1"),
					0, 1).toString();
		}
		builder.append("&Total_Weight=" + total_Weight);// ������
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
		builder.append("&GoodsStr=" + strGoods);// ������Ϣ�������Լ�����ߣ�
		// ��Ʊ��Ϣ
		String strDeclaredvalue = "0.00";// �걨��ֵ
		String strDeclaredcurrency = "USD";// �걨����
		String strDeclaredcontent = "DOC";// ˵��
		String inviceDetailString = "";// ��Ʊ����
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
		builder.append("&MakeInvice=1");// �Ƿ�������Ʊ
		builder.append("&inviceType=1");// ��Ʊ����
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
		builder.append("&DutyPaymentType=" + dutyType);// Ŀ�ĵع�˰��˰��ָ�����Ͽɵ��˺�
		builder.append("&exportType=1");// �������ͣ�Permanent(����)��
		builder.append("&ttermsFp=6");// ó�����DAP-Delivered at Place��
		builder.append("&TotalPieces=" + totalPieces.toString());// ��Ʊ����
		builder.append("&TotalDeclaredValue=" + strDeclaredvalue);
		builder.append("&inviceCode=");// ��Ʊ����
		builder.append("&date="
				+ DateFormatUtility.getSysdateString("yyyy-MM-dd"));
		builder.append("&note=");
		builder.append("&tdx=0");// �������ѡ��
		builder.append("&dayinType=dayinLabel");
		return builder.toString();
	}

}
