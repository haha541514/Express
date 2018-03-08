package kyle.leis.eo.operation.corewaybill.dax;

import java.math.BigDecimal;
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
import kyle.leis.fs.waybillcode.dax.IWaybillcodeBasicData;
import kyle.leis.hi.TchnChannel;

public class RequestDHLXML extends RequestXML {
	private static String m_strModelContent;
	protected String DHL_SITE_ID = "ShenLianYun";
	protected String DHL_PASSWORD = "5T3e4Vs07Jk2";	
	
	public RequestDHLXML() {
		try {
			String strEnterprise = SystempropertyDemand.getEnterprise();
			if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("QQYX")) {
				DHL_SITE_ID = "1468636";
				DHL_PASSWORD = "3ai2d1CkAw";	
				//Siteid: 3705470
				//Password: oWdYbEF5kS
			}
		} catch (Exception ex) {
			
		}
	}
	
	protected String getRequestModelContent() throws Exception {
		if (StringUtility.isNull(m_strModelContent))
			m_strModelContent = getRequestModelContent("ShipmentValidateRequest_APToAP.xml");
		//m_strModelContent = getRequestModelContent("E:/jakarta-tomcat-5.5.9/webapps/Express/WEB-INF/ShipmentValidateRequest_APToAP.xml");
		return m_strModelContent;
	}
	
	public String buildRequestXML(ForinputallColumns objFIAColumns, 
			List listCargo, 
			List listPieces,
			PromptUtilityCollection objPUCollection) throws Exception {
		String strModelContent = getRequestModelContent();
		if (StringUtility.isNull(strModelContent)) return "";

		String strEnterprise = SystempropertyDemand.getEnterprise();
		
		String strChncode = objFIAColumns.getChncode_Cwspchn();
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
		if (!StringUtility.isNull(objTchnChannel.getChnRegistername())) {
			strModelContent = strModelContent.replace("$siteid$", objTchnChannel.getChnRegistername());
			strModelContent = strModelContent.replace("$password$", objTchnChannel.getChnRegisterpassword());
		} else {
			// siteID
			if (strEnterprise.startsWith("SLY")) {
				strModelContent = strModelContent.replace("$siteid$", DHL_SITE_ID);
				strModelContent = strModelContent.replace("$password$", DHL_PASSWORD);
			} else {
				return "E_001_没有设置DHL的访问规则";
			}
		}
		// 渠道账号
		strModelContent = strModelContent.replace("$masteraccount$", StringUtility.replaceWhenNull(objTchnChannel.getChnMasteraccount(), ""));
		// strModelContent = strModelContent.replace("$masteraccount$", "967080215");
		strModelContent = strModelContent.replace("$paymentaccount$", StringUtility.replaceWhenNull(objTchnChannel.getChnPaymentaccount(), ""));
		// strModelContent = strModelContent.replace("$paymentaccount$", "967080215");
		String strPaymenttype = "S";
		if (!StringUtility.isNull(objTchnChannel.getChnMasteraccount()) &&
				!objTchnChannel.getChnMasteraccount().equals(objTchnChannel.getChnPaymentaccount()))
			strPaymenttype = "R";
		strModelContent = strModelContent.replace("$paymenttype$", strPaymenttype);
		// 关税支付方式, R为DDU，S为DDP
		String strTaxPaidMode = "R";
		SpecialtypeColumns objSpecialtypeColumns = SpecialtypeDemand.load(objFIAColumns.getCwcode(), "A0201");
		if (!StringUtility.isNull(objTchnChannel.getChnMasteraccount()) && 
				objSpecialtypeColumns != null && 
				!StringUtility.isNull(objSpecialtypeColumns.getEstestname())) {
			strTaxPaidMode = "S";
			strModelContent = strModelContent.replace("$dutyaccountnumber$", "<DutyAccountNumber>" + 
					objTchnChannel.getChnMasteraccount() + "</DutyAccountNumber>");
		} else {
			strModelContent = strModelContent.replace("$dutyaccountnumber$", "");
		}
		strModelContent = strModelContent.replace("$ddpsign$", strTaxPaidMode);
		// 收件人信息
		strModelContent = strModelContent.replace("$consigneecompany$", StringUtility.splitMaxLength(objFIAColumns.getHwconsigneecompany(), 35));
		strModelContent = strModelContent.replace("$consigneeaddress1$", StringUtility.splitMaxLength(objFIAColumns.getHwconsigneeaddress1(), 35));
		strModelContent = strModelContent.replace("$consigneeaddress2$", StringUtility.splitMaxLength(objFIAColumns.getHwconsigneeaddress2(), 35));
		strModelContent = strModelContent.replace("$consigneeaddress3$", StringUtility.splitMaxLength(objFIAColumns.getHwconsigneeaddress3(), 35));
		strModelContent = strModelContent.replace("$consigneecity$", objFIAColumns.getHwConsigneecity());
		
		
		String strCountryHubcode = DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getDtcode());
		String strPostcode = autoRebuildDHLPostcode(strCountryHubcode, objFIAColumns.getHwconsigneepostcode());
		if (strPostcode.startsWith("E_"))
			return strPostcode;
		strModelContent = strModelContent.replace("$consigneepostcode$", strPostcode);
		strModelContent = strModelContent.replace("$consigneecountrycode$", strCountryHubcode);
		strModelContent = strModelContent.replace("$consigneecountryname$", DistrictDemand.getCountryEnameByCity(objFIAColumns.getDtcode()));
		strModelContent = strModelContent.replace("$consigneename$", StringUtility.splitMaxLength(objFIAColumns.getHwconsigneename(), 35));
		strModelContent = strModelContent.replace("$consigneetelephone$", objFIAColumns.getHwconsigneetelephone().replace("-", ""));
		strModelContent = StringUtility.replaceWhenNull(strModelContent,"$consigneefax$", objFIAColumns.getHwconsigneefax());
		// 发票信息
		String strDeclaredvalue = "0.00";
		String strDeclaredcurrency = "USD";
		String strDeclaredcontent = "DOC";
		if (listCargo != null && listCargo.size() > 0) {
			BigDecimal objDeclaredvalue = new BigDecimal("0");
			strDeclaredcontent = "";
			for (int i = 0; i < listCargo.size(); i++) {
				CargoinfoColumns objCIC = (CargoinfoColumns)listCargo.get(i);
				objDeclaredvalue = objDeclaredvalue.add(new BigDecimal(objCIC.getCicitotalprice()));
				if (StringUtility.isNull(objCIC.getCkckcode()))
					strDeclaredcurrency = "USD";
				else
					strDeclaredcurrency = objCIC.getCkckcode();
				
				strDeclaredcontent = strDeclaredcontent + objCIC.getCiciename() + ",";
			}
			if (!StringUtility.isNull(strDeclaredcontent) && 
					strDeclaredcontent.length() > 90)
				strDeclaredcontent = strDeclaredcontent.substring(0, 89);
			strDeclaredvalue = objDeclaredvalue.divide(new BigDecimal("1"), 2, 2).toString();
		}
		strModelContent = strModelContent.replace("$declaredvalue$", strDeclaredvalue);
		strModelContent = strModelContent.replace("$declaredcurrency$", strDeclaredcurrency);
		strModelContent = strModelContent.replace("$declarecontent$", strDeclaredcontent);
		// 客户运单号
		if (objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind() != null &&
				IWaybillcodeBasicData.BAC_DHLSOAPFROMWEB_MASTER.equals(objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind().getBckGroupcode())) {
			String strCustomerewbcode = objFIAColumns.getCwcustomerewbcode();
			/*
			if (strCustomerewbcode.length() > 6)
				strCustomerewbcode = strCustomerewbcode.substring(strCustomerewbcode.length() - 6);
			*/
			strModelContent = strModelContent.replace("$customerEWBCode$", strCustomerewbcode);
		}
		else
			strModelContent = strModelContent.replace("$customerEWBCode$", objFIAColumns.getCwcustomerewbcode());
		strModelContent = strModelContent.replace("$transferpieces$", objFIAColumns.getCwpieces());
		// 件重量
		String strWeight = objFIAColumns.getTransfervolumeweight();
		if (StringUtility.isNull(objFIAColumns.getTransfervolumeweight()) ||
				new BigDecimal(objFIAColumns.getTransfervolumeweight()).equals(new BigDecimal("0")))
			strWeight = objFIAColumns.getCwtransfergrossweight();
		String strUnittransferweight = new BigDecimal(strWeight).divide(new BigDecimal(objFIAColumns.getCwpieces()), 2, 4).toString();
		int iTransferPieces = Integer.parseInt(objFIAColumns.getCwpieces());
		StringBuffer strTransferPiecesWords = new StringBuffer(); 
		for (int i = 1; i <= iTransferPieces; i++) {
			strTransferPiecesWords.append("			<Piece>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				<PieceID>" + i + "</PieceID>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				<Weight>" + strUnittransferweight + "</Weight>");
			strTransferPiecesWords.append("\r\n");
			/*
			strTransferPiecesWords.append("				<PackageType>EE</PackageType>");
			strTransferPiecesWords.append("\r\n");	
			strTransferPiecesWords.append("				<Width>1</Width>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				<Height>1</Height>");
			strTransferPiecesWords.append("\r\n");
			strTransferPiecesWords.append("				<Depth>1</Depth>");
			strTransferPiecesWords.append("\r\n");
			*/
			strTransferPiecesWords.append("			</Piece>");
			strTransferPiecesWords.append("\r\n");
		}
		strModelContent = strModelContent.replace("$Pieces$", strTransferPiecesWords.toString());
		strModelContent = strModelContent.replace("$transferchargeweight$", objFIAColumns.getCwtransferchargeweight());
		// 货物类型，文件D，包裹P，ESI则为H
		String strProductcode = "P";
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("QQYX"))
			strProductcode = "F";
		if (objFIAColumns.getCtcode().equals("ADOX"))
			strProductcode = "D";
		if (objFIAColumns.getCtcode().equals("AWPX")) {
			String strWPXMapping = objTchnChannel.getChnWpxspsmappingname();
			if (!StringUtility.isNull(strWPXMapping) && strWPXMapping.equals("ESI"))
				strProductcode = "H";
		}
		// 制单日期
		strModelContent = strModelContent.replace("$productcode$", strProductcode);
		/*
		String strRecordDate =DateFormatUtility.getStandardDate(DateFormatUtility.getStandardDate(objFIAColumns.getHwrecorddate()));
		if (StringUtility.isNull(strRecordDate))
		*/
		String strRecordDate = DateFormatUtility.getStandardSysdate();
		strRecordDate = strRecordDate.substring(0, 10);
		strModelContent = strModelContent.replace("$recorddate$", strRecordDate);
		// 发件人信息
		strModelContent = strModelContent.replace("$shipperID$", "63104ID");
		strModelContent = strModelContent.replace("$shippercompany$", StringUtility.splitMaxLength(objFIAColumns.getHwshippercompany(), 35));
		strModelContent = strModelContent.replace("$shipperaddress1$", StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress1(), 35));
		strModelContent = strModelContent.replace("$shipperaddress2$", StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress2(), 35));
		strModelContent = strModelContent.replace("$shipperaddress3$", StringUtility.splitMaxLength(objFIAColumns.getHwshipperaddress3(), 35));
		strModelContent = strModelContent.replace("$shippercityname$", DistrictDemand.getDtenameByDtcode(objFIAColumns.getHwDtcodeshipper()));
		// strModelContent.replace("$shipperpostcode$", objFIAColumns.getHwshipperpostcode());
		strModelContent = strModelContent.replace("$shippercountrycode$", DistrictDemand.getCountryHubcodeByCity(objFIAColumns.getHwDtcodeshipper()));
		strModelContent = strModelContent.replace("$shippercountryname$", DistrictDemand.getCountryEnameByCity(objFIAColumns.getHwDtcodeshipper()));
		strModelContent = strModelContent.replace("$shippername$", StringUtility.splitMaxLength(objFIAColumns.getHwshippername(), 35));
		strModelContent = strModelContent.replace("$shippertelephone$", objFIAColumns.getHwshippertelephone().replace("-", ""));
		strModelContent = StringUtility.replaceWhenNull(strModelContent, "$shipperfax$", objFIAColumns.getHwshipperfax());
		// DDP则设置特殊类型
		if (strTaxPaidMode.equals("S")) {
			StringBuffer sb = new StringBuffer();
			sb.append("\r\n");
			sb.append("	<SpecialService>");
			sb.append("\r\n");
			sb.append("		<SpecialServiceType>DD</SpecialServiceType>");
			sb.append("\r\n");
			sb.append("	</SpecialService>");
			sb.append("\r\n");
			strModelContent = strModelContent.replace("$ddp$", sb.toString());
		} else {
			strModelContent = strModelContent.replace("$ddp$", "");
		}
		
		String strLabelImageFormat = "	<LabelImageFormat>PDF</LabelImageFormat>";
		strModelContent = strModelContent.replace("$labelimageformat$", strLabelImageFormat);
		
		/*
		if (objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind() != null &&
				IWaybillcodeBasicData.BAC_DHLSOAPFROMWEB_MASTER.equals(objTchnChannel.getTdiWaybillcodekindByChnMainbillcodekind().getBckGroupcode())) {
			String strLabelImageFormat = "	<LabelImageFormat>PDF</LabelImageFormat>";
			strModelContent = strModelContent.replace("$labelimageformat$", strLabelImageFormat);
		} else {
			strModelContent = strModelContent.replace("$labelimageformat$", "");
		}
		*/
		
		/*
		if (!FileUtility.exist("/usr/local/dhlrequest" + objFIAColumns.getCwcustomerewbcode() + ".xml"))
			FileUtility.createFile("/usr/local/", "dhlrequest" + objFIAColumns.getCwcustomerewbcode() + ".xml", strModelContent);		
		*/
		return strModelContent;
	}

}
